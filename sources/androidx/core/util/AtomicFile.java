package androidx.core.util;

import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
    private static final String LOG_TAG = "AtomicFile";
    private final File mBaseName;
    private final File mLegacyBackupName;
    private final File mNewName;

    public AtomicFile(File file) {
        this.mBaseName = file;
        this.mNewName = new File(file.getPath() + ".new");
        this.mLegacyBackupName = new File(file.getPath() + ".bak");
    }

    public File getBaseFile() {
        return this.mBaseName;
    }

    public void delete() {
        this.mBaseName.delete();
        this.mNewName.delete();
        this.mLegacyBackupName.delete();
    }

    public FileOutputStream startWrite() throws IOException {
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        try {
            File file = this.mNewName;
            return SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
        } catch (FileNotFoundException unused) {
            if (this.mNewName.getParentFile().mkdirs()) {
                try {
                    File file2 = this.mNewName;
                    return SentryFileOutputStream.Factory.create(new FileOutputStream(file2), file2);
                } catch (FileNotFoundException e) {
                    throw new IOException("Failed to create new file " + this.mNewName, e);
                }
            } else {
                throw new IOException("Failed to create directory for " + this.mNewName);
            }
        }
    }

    public void finishWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            if (!sync(fileOutputStream)) {
                SentryLogcatAdapter.e(LOG_TAG, "Failed to sync file output stream");
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                SentryLogcatAdapter.e(LOG_TAG, "Failed to close file output stream", e);
            }
            rename(this.mNewName, this.mBaseName);
        }
    }

    public void failWrite(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            if (!sync(fileOutputStream)) {
                SentryLogcatAdapter.e(LOG_TAG, "Failed to sync file output stream");
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                SentryLogcatAdapter.e(LOG_TAG, "Failed to close file output stream", e);
            }
            if (!this.mNewName.delete()) {
                SentryLogcatAdapter.e(LOG_TAG, "Failed to delete new file " + this.mNewName);
            }
        }
    }

    public FileInputStream openRead() throws FileNotFoundException {
        if (this.mLegacyBackupName.exists()) {
            rename(this.mLegacyBackupName, this.mBaseName);
        }
        if (this.mNewName.exists() && this.mBaseName.exists() && !this.mNewName.delete()) {
            SentryLogcatAdapter.e(LOG_TAG, "Failed to delete outdated new file " + this.mNewName);
        }
        File file = this.mBaseName;
        return SentryFileInputStream.Factory.create(new FileInputStream(file), file);
    }

    public byte[] readFully() throws IOException {
        FileInputStream openRead = openRead();
        try {
            byte[] bArr = new byte[openRead.available()];
            int i = 0;
            while (true) {
                int read = openRead.read(bArr, i, bArr.length - i);
                if (read <= 0) {
                    return bArr;
                }
                i += read;
                int available = openRead.available();
                if (available > bArr.length - i) {
                    byte[] bArr2 = new byte[(available + i)];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    bArr = bArr2;
                }
            }
        } finally {
            openRead.close();
        }
    }

    private static boolean sync(FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private static void rename(File file, File file2) {
        if (file2.isDirectory() && !file2.delete()) {
            SentryLogcatAdapter.e(LOG_TAG, "Failed to delete file which is a directory " + file2);
        }
        if (!file.renameTo(file2)) {
            SentryLogcatAdapter.e(LOG_TAG, "Failed to rename " + file + " to " + file2);
        }
    }
}
