package org.apache.commons.io.output;

import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;

public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private boolean closed;
    private OutputStream currentOutputStream;
    private final File directory;
    private ByteArrayOutputStream memoryOutputStream;
    private File outputFile;
    private final String prefix;
    private final String suffix;

    public DeferredFileOutputStream(int i, File file) {
        this(i, file, (String) null, (String) null, (File) null, 1024);
    }

    public DeferredFileOutputStream(int i, int i2, File file) {
        this(i, file, (String) null, (String) null, (File) null, i2);
        if (i2 < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    public DeferredFileOutputStream(int i, String str, String str2, File file) {
        this(i, (File) null, str, str2, file, 1024);
        if (str == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
    }

    public DeferredFileOutputStream(int i, int i2, String str, String str2, File file) {
        this(i, (File) null, str, str2, file, i2);
        if (str == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Initial buffer size must be atleast 0.");
        }
    }

    private DeferredFileOutputStream(int i, File file, String str, String str2, File file2, int i2) {
        super(i);
        this.closed = false;
        this.outputFile = file;
        this.prefix = str;
        this.suffix = str2;
        this.directory = file2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2);
        this.memoryOutputStream = byteArrayOutputStream;
        this.currentOutputStream = byteArrayOutputStream;
    }

    /* access modifiers changed from: protected */
    public OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }

    /* access modifiers changed from: protected */
    public void thresholdReached() throws IOException {
        String str = this.prefix;
        if (str != null) {
            this.outputFile = File.createTempFile(str, this.suffix, this.directory);
        }
        FileUtils.forceMkdirParent(this.outputFile);
        File file = this.outputFile;
        FileOutputStream create = SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
        try {
            this.memoryOutputStream.writeTo(create);
            this.currentOutputStream = create;
            this.memoryOutputStream = null;
        } catch (IOException e) {
            create.close();
            throw e;
        }
    }

    public boolean isInMemory() {
        return !isThresholdExceeded();
    }

    public byte[] getData() {
        ByteArrayOutputStream byteArrayOutputStream = this.memoryOutputStream;
        if (byteArrayOutputStream != null) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    public File getFile() {
        return this.outputFile;
    }

    public void close() throws IOException {
        super.close();
        this.closed = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(java.io.OutputStream r3) throws java.io.IOException {
        /*
            r2 = this;
            boolean r0 = r2.closed
            if (r0 == 0) goto L_0x0032
            boolean r0 = r2.isInMemory()
            if (r0 == 0) goto L_0x0010
            org.apache.commons.io.output.ByteArrayOutputStream r0 = r2.memoryOutputStream
            r0.writeTo(r3)
            goto L_0x0023
        L_0x0010:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            java.io.File r1 = r2.outputFile
            r0.<init>(r1)
            java.io.FileInputStream r0 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r0, (java.io.File) r1)
            org.apache.commons.io.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r3)     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return
        L_0x0024:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0026 }
        L_0x0026:
            r1 = move-exception
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0031:
            throw r1
        L_0x0032:
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r0 = "Stream not closed"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.output.DeferredFileOutputStream.writeTo(java.io.OutputStream):void");
    }
}
