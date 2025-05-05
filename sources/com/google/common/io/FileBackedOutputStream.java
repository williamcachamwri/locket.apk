package com.google.common.io;

import com.google.common.base.Preconditions;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public final class FileBackedOutputStream extends OutputStream {
    @CheckForNull
    private File file;
    private final int fileThreshold;
    @CheckForNull
    private MemoryOutput memory;
    private OutputStream out;
    private final boolean resetOnFinalize;
    private final ByteSource source;

    private static class MemoryOutput extends ByteArrayOutputStream {
        private MemoryOutput() {
        }

        /* access modifiers changed from: package-private */
        public byte[] getBuffer() {
            return this.buf;
        }

        /* access modifiers changed from: package-private */
        public int getCount() {
            return this.count;
        }
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public synchronized File getFile() {
        return this.file;
    }

    public FileBackedOutputStream(int i) {
        this(i, false);
    }

    public FileBackedOutputStream(int i, boolean z) {
        Preconditions.checkArgument(i >= 0, "fileThreshold must be non-negative, but was %s", i);
        this.fileThreshold = i;
        this.resetOnFinalize = z;
        MemoryOutput memoryOutput = new MemoryOutput();
        this.memory = memoryOutput;
        this.out = memoryOutput;
        if (z) {
            this.source = new ByteSource() {
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }

                /* access modifiers changed from: protected */
                public void finalize() {
                    try {
                        FileBackedOutputStream.this.reset();
                    } catch (Throwable th) {
                        th.printStackTrace(System.err);
                    }
                }
            };
        } else {
            this.source = new ByteSource() {
                public InputStream openStream() throws IOException {
                    return FileBackedOutputStream.this.openInputStream();
                }
            };
        }
    }

    public ByteSource asByteSource() {
        return this.source;
    }

    /* access modifiers changed from: private */
    public synchronized InputStream openInputStream() throws IOException {
        if (this.file != null) {
            File file2 = this.file;
            return SentryFileInputStream.Factory.create(new FileInputStream(file2), file2);
        }
        Objects.requireNonNull(this.memory);
        return new ByteArrayInputStream(this.memory.getBuffer(), 0, this.memory.getCount());
    }

    public synchronized void reset() throws IOException {
        try {
            close();
            MemoryOutput memoryOutput = this.memory;
            if (memoryOutput == null) {
                this.memory = new MemoryOutput();
            } else {
                memoryOutput.reset();
            }
            this.out = this.memory;
            File file2 = this.file;
            if (file2 != null) {
                this.file = null;
                if (!file2.delete()) {
                    throw new IOException("Could not delete: " + file2);
                }
            }
        } catch (Throwable th) {
            if (this.memory == null) {
                this.memory = new MemoryOutput();
            } else {
                this.memory.reset();
            }
            this.out = this.memory;
            File file3 = this.file;
            if (file3 != null) {
                this.file = null;
                if (!file3.delete()) {
                    throw new IOException("Could not delete: " + file3);
                }
            }
            throw th;
        }
    }

    public synchronized void write(int i) throws IOException {
        update(1);
        this.out.write(i);
    }

    public synchronized void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public synchronized void write(byte[] bArr, int i, int i2) throws IOException {
        update(i2);
        this.out.write(bArr, i, i2);
    }

    public synchronized void close() throws IOException {
        this.out.close();
    }

    public synchronized void flush() throws IOException {
        this.out.flush();
    }

    private void update(int i) throws IOException {
        MemoryOutput memoryOutput = this.memory;
        if (memoryOutput != null && memoryOutput.getCount() + i > this.fileThreshold) {
            File createTempFile = TempFileCreator.INSTANCE.createTempFile("FileBackedOutputStream");
            if (this.resetOnFinalize) {
                createTempFile.deleteOnExit();
            }
            try {
                FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(createTempFile), createTempFile);
                create.write(this.memory.getBuffer(), 0, this.memory.getCount());
                create.flush();
                this.out = create;
                this.file = createTempFile;
                this.memory = null;
            } catch (IOException e) {
                createTempFile.delete();
                throw e;
            }
        }
    }
}
