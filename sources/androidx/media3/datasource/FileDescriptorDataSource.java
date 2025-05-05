package androidx.media3.datasource;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.Sets;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Set;

public class FileDescriptorDataSource extends BaseDataSource {
    private static final Set<FileDescriptor> inUseFileDescriptors = Sets.newConcurrentHashSet();
    private long bytesRemaining;
    private final FileDescriptor fileDescriptor;
    private FileInputStream inputStream;
    private final long length;
    private final long offset;
    private boolean opened;
    private Uri uri;

    public FileDescriptorDataSource(FileDescriptor fileDescriptor2, long j, long j2) {
        super(false);
        this.fileDescriptor = (FileDescriptor) Assertions.checkNotNull(fileDescriptor2);
        this.offset = j;
        this.length = j2;
    }

    public long open(DataSpec dataSpec) throws DataSourceException {
        try {
            this.uri = dataSpec.uri;
            transferInitializing(dataSpec);
            if (inUseFileDescriptors.add(this.fileDescriptor)) {
                if (this.length != -1) {
                    if (dataSpec.position > this.length) {
                        throw new DataSourceException(2008);
                    }
                }
                seekFileDescriptor(this.fileDescriptor, this.offset + dataSpec.position);
                FileDescriptor fileDescriptor2 = this.fileDescriptor;
                FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(fileDescriptor2), fileDescriptor2);
                this.inputStream = create;
                long j = this.length;
                if (j == -1) {
                    FileChannel channel = create.getChannel();
                    long size = channel.size();
                    if (size == 0) {
                        this.bytesRemaining = -1;
                    } else {
                        long position = size - channel.position();
                        this.bytesRemaining = position;
                        if (position < 0) {
                            throw new DataSourceException(2008);
                        }
                    }
                } else {
                    long j2 = j - dataSpec.position;
                    this.bytesRemaining = j2;
                    if (j2 < 0) {
                        throw new DataSourceException(2008);
                    }
                }
                if (dataSpec.length != -1) {
                    long j3 = this.bytesRemaining;
                    this.bytesRemaining = j3 == -1 ? dataSpec.length : Math.min(j3, dataSpec.length);
                }
                this.opened = true;
                transferStarted(dataSpec);
                return dataSpec.length != -1 ? dataSpec.length : this.bytesRemaining;
            }
            throw new DataSourceException((Throwable) new IllegalStateException("Attempted to re-use an already in-use file descriptor"), -2);
        } catch (DataSourceException e) {
            throw e;
        } catch (IOException e2) {
            throw new DataSourceException((Throwable) e2, e2 instanceof FileNotFoundException ? 2005 : 2000);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws DataSourceException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            i2 = (int) Math.min(j, (long) i2);
        }
        try {
            int read = ((FileInputStream) Util.castNonNull(this.inputStream)).read(bArr, i, i2);
            if (read == -1) {
                return -1;
            }
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) read);
            }
            bytesTransferred(read);
            return read;
        } catch (IOException e) {
            throw new DataSourceException((Throwable) e, 2000);
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public void close() throws DataSourceException {
        this.uri = null;
        inUseFileDescriptors.remove(this.fileDescriptor);
        try {
            FileInputStream fileInputStream = this.inputStream;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new DataSourceException((Throwable) e, 2000);
        } catch (Throwable th) {
            this.inputStream = null;
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
            throw th;
        }
    }

    private static void seekFileDescriptor(FileDescriptor fileDescriptor2, long j) throws DataSourceException {
        try {
            Os.lseek(fileDescriptor2, j, OsConstants.SEEK_SET);
        } catch (ErrnoException e) {
            throw new DataSourceException((Throwable) e, 2000);
        }
    }
}
