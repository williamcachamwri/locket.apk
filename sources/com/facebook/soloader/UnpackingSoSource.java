package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.annotation.Nullable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public abstract class UnpackingSoSource extends DirectorySoSource implements AsyncInitSoSource {
    protected static final String DEPS_FILE_NAME = "dso_deps";
    protected static final String LOCK_FILE_NAME = "dso_lock";
    private static final byte STATE_CLEAN = 1;
    private static final byte STATE_DIRTY = 0;
    protected static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    @Nullable
    private String[] mAbis;
    protected final Context mContext;

    private static boolean forceRefresh(int i) {
        return (i & 2) != 0;
    }

    private static boolean rewriteStateAsync(int i) {
        return (i & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker(boolean z) throws IOException;

    protected UnpackingSoSource(Context context, String str) {
        super(getSoStorePath(context, str), 1);
        this.mContext = context;
    }

    protected UnpackingSoSource(Context context, File file) {
        super(file, 1);
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + "/" + str);
    }

    public String[] getSoSourceAbis() {
        String[] strArr = this.mAbis;
        return strArr == null ? super.getSoSourceAbis() : strArr;
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    protected static final class InputDso implements Closeable {
        /* access modifiers changed from: private */
        public final InputStream content;
        private final Dso dso;

        public InputDso(Dso dso2, InputStream inputStream) {
            this.dso = dso2;
            this.content = inputStream;
        }

        public Dso getDso() {
            return this.dso;
        }

        public int available() throws IOException {
            return this.content.available();
        }

        public void close() throws IOException {
            this.content.close();
        }
    }

    /* access modifiers changed from: private */
    public static void writeState(File file, byte b, boolean z) throws IOException {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            if (z) {
                randomAccessFile.getFD().sync();
            }
            randomAccessFile.close();
            return;
        } catch (SyncFailedException e) {
            LogUtil.w(TAG, "state file sync failed", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void deleteSoFiles() throws IOException {
        File[] listFiles = this.soDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.equals(UnpackingSoSource.STATE_FILE_NAME) && !str.equals(UnpackingSoSource.LOCK_FILE_NAME) && !str.equals(UnpackingSoSource.DEPS_FILE_NAME);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                LogUtil.v(TAG, "Deleting " + file);
                SysUtil.dumbDelete(file);
            }
            return;
        }
        throw new IOException("unable to list directory " + this.soDirectory);
    }

    protected static abstract class Unpacker implements Closeable {
        public void close() throws IOException {
        }

        public abstract Dso[] getDsos() throws IOException;

        public abstract void unpack(File file) throws IOException;

        protected Unpacker() {
        }

        public void extractDso(InputDso inputDso, byte[] bArr, File file) throws IOException {
            RandomAccessFile randomAccessFile;
            Throwable th;
            RandomAccessFile randomAccessFile2;
            File file2 = file;
            LogUtil.i(UnpackingSoSource.TAG, "extracting DSO " + inputDso.getDso().name);
            File file3 = new File(file2, inputDso.getDso().name);
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(file3, "rw");
                try {
                    int available = inputDso.available();
                    if (available > 1) {
                        try {
                            randomAccessFile2 = randomAccessFile3;
                            try {
                                SysUtil.fallocateIfSupported(randomAccessFile3.getFD(), (long) available);
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            randomAccessFile2 = randomAccessFile3;
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            try {
                                randomAccessFile.close();
                            } catch (Throwable th4) {
                                th.addSuppressed(th4);
                            }
                            throw th;
                        }
                    } else {
                        randomAccessFile2 = randomAccessFile3;
                    }
                    try {
                        randomAccessFile = randomAccessFile2;
                    } catch (Throwable th5) {
                        th = th5;
                        randomAccessFile = randomAccessFile2;
                        th = th;
                        randomAccessFile.close();
                        throw th;
                    }
                    try {
                        SysUtil.copyBytes(randomAccessFile, inputDso.content, Integer.MAX_VALUE, bArr);
                        randomAccessFile.setLength(randomAccessFile.getFilePointer());
                        if (file3.setExecutable(true, false)) {
                            randomAccessFile.close();
                            if (file3.exists() && !file3.setWritable(false)) {
                                LogUtil.e(SoLoader.TAG, "Error removing " + file3 + " write permission from directory " + file2 + " (writable: " + file.canWrite() + ")");
                                return;
                            }
                            return;
                        }
                        throw new IOException("cannot make file executable: " + file3);
                    } catch (Throwable th6) {
                        th = th6;
                        th = th;
                        randomAccessFile.close();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    randomAccessFile = randomAccessFile3;
                    th = th;
                    randomAccessFile.close();
                    throw th;
                }
            } catch (IOException e) {
                LogUtil.e(UnpackingSoSource.TAG, "error extracting dso  " + file3 + " due to: " + e);
                SysUtil.dumbDelete(file3);
                throw e;
            } catch (Throwable th8) {
                if (file3.exists() && !file3.setWritable(false)) {
                    LogUtil.e(SoLoader.TAG, "Error removing " + file3 + " write permission from directory " + file2 + " (writable: " + file.canWrite() + ")");
                }
                throw th8;
            }
        }
    }

    /* access modifiers changed from: protected */
    public MessageDigest getHashingAlgorithm() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
    }

    /* access modifiers changed from: protected */
    public String computeFileHash(File file) {
        DigestInputStream digestInputStream;
        try {
            MessageDigest hashingAlgorithm = getHashingAlgorithm();
            try {
                FileInputStream create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
                try {
                    digestInputStream = new DigestInputStream(create, hashingAlgorithm);
                    while (digestInputStream.read(new byte[8192]) != -1) {
                    }
                    byte[] digest = hashingAlgorithm.digest();
                    StringBuilder sb = new StringBuilder(digest.length * 2);
                    int length = digest.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                    }
                    String sb2 = sb.toString();
                    digestInputStream.close();
                    create.close();
                    return sb2;
                } catch (Throwable th) {
                    create.close();
                    throw th;
                }
                throw th;
            } catch (IOException e) {
                LogUtil.w(TAG, "Failed to calculate hash for " + file.getName(), e);
                return "-1";
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.w(TAG, "Failed to calculate hash for " + file.getName(), e2);
            return "-1";
        }
    }

    private boolean libraryIsCorrupted(Dso dso, File file) {
        if (file.exists() && dso.hash.equals(computeFileHash(file))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean depsChanged(byte[] bArr, byte[] bArr2) {
        return !Arrays.equals(bArr, bArr2);
    }

    /* access modifiers changed from: protected */
    public boolean depsChanged(byte[] bArr) {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(new File(this.soDirectory, DEPS_FILE_NAME), "rw");
            if (randomAccessFile.length() == 0) {
                randomAccessFile.close();
                return true;
            }
            int length = (int) randomAccessFile.length();
            byte[] bArr2 = new byte[length];
            if (randomAccessFile.read(bArr2) != length) {
                LogUtil.v(TAG, "short read of so store deps file: marking unclean");
                randomAccessFile.close();
                return true;
            }
            boolean depsChanged = depsChanged(bArr2, bArr);
            randomAccessFile.close();
            return depsChanged;
        } catch (IOException e) {
            LogUtil.w(TAG, "failed to compare whether deps changed", e);
            return true;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean refreshLocked(final FileLocker fileLocker, int i) throws IOException {
        byte b;
        final File file = new File(this.soDirectory, STATE_FILE_NAME);
        byte[] depsBlock = getDepsBlock();
        boolean forceRefresh = forceRefresh(i);
        if (forceRefresh || depsChanged(depsBlock)) {
            b = 0;
        } else {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                if (randomAccessFile.length() == 1) {
                    b = randomAccessFile.readByte();
                    if (b == 1) {
                        LogUtil.v(TAG, "dso store " + this.soDirectory + " regeneration not needed: state file clean");
                        randomAccessFile.close();
                    }
                }
            } catch (IOException e) {
                LogUtil.v(TAG, "dso store " + this.soDirectory + " regeneration interrupted: " + e.getMessage());
            } catch (Throwable th) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
            b = 0;
            randomAccessFile.close();
        }
        if (b == 1) {
            return false;
        }
        final boolean z = (i & 4) == 0;
        LogUtil.v(TAG, "so store dirty: regenerating");
        writeState(file, (byte) 0, z);
        deleteSoFiles();
        Unpacker makeUnpacker = makeUnpacker(forceRefresh);
        try {
            makeUnpacker.unpack(this.soDirectory);
            if (makeUnpacker != null) {
                makeUnpacker.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(this.soDirectory, DEPS_FILE_NAME), "rw");
            try {
                randomAccessFile2.write(depsBlock);
                randomAccessFile2.setLength(randomAccessFile2.getFilePointer());
                randomAccessFile2.close();
                AnonymousClass2 r2 = new Runnable() {
                    public void run() {
                        LogUtil.v(UnpackingSoSource.TAG, "starting syncer worker");
                        try {
                            if (z) {
                                SysUtil.fsyncAll(UnpackingSoSource.this.soDirectory);
                            }
                            UnpackingSoSource.writeState(file, (byte) 1, z);
                            LogUtil.v(UnpackingSoSource.TAG, "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                            fileLocker.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (Throwable th) {
                            LogUtil.v(UnpackingSoSource.TAG, "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                            fileLocker.close();
                            throw th;
                        }
                    }
                };
                if (rewriteStateAsync(i)) {
                    new Thread(r2, "SoSync:" + this.soDirectory.getName()).start();
                } else {
                    r2.run();
                }
                return true;
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
        throw th;
        throw th;
    }

    public void waitUntilInitCompleted() {
        try {
            FileLocker orCreateLockOnDir = SysUtil.getOrCreateLockOnDir(this.soDirectory, new File(this.soDirectory, LOCK_FILE_NAME));
            if (orCreateLockOnDir != null) {
                orCreateLockOnDir.close();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Encountered exception during wait for unpacking trying to acquire file lock for " + getClass().getName() + " (" + this.soDirectory + "): ", e);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() throws IOException {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker(false);
        try {
            Dso[] dsos = makeUnpacker.getDsos();
            obtain.writeInt(dsos.length);
            for (Dso dso : dsos) {
                obtain.writeString(dso.name);
                obtain.writeString(dso.hash);
            }
            if (makeUnpacker != null) {
                makeUnpacker.close();
            }
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0118 A[SYNTHETIC, Splitter:B:36:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x012e A[Catch:{ all -> 0x0145 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepare(int r17) throws java.io.IOException {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r2 = " (syncer thread started)"
            java.lang.String r3 = "error removing "
            java.lang.String r4 = "fb-UnpackingSoSource"
            java.lang.String r5 = "releasing dso store lock for "
            java.lang.String r6 = "not releasing dso store lock for "
            java.lang.String r0 = "dso store is up-to-date: "
            java.lang.String r7 = "locked dso store "
            java.io.File r8 = r1.soDirectory
            com.facebook.soloader.SysUtil.mkdirOrThrow(r8)
            java.io.File r8 = r1.soDirectory
            boolean r8 = r8.canWrite()
            r9 = 1
            java.lang.String r10 = " write permission"
            java.lang.String r11 = "error adding "
            if (r8 != 0) goto L_0x0048
            java.io.File r8 = r1.soDirectory
            boolean r8 = r8.setWritable(r9)
            if (r8 == 0) goto L_0x002b
            goto L_0x0048
        L_0x002b:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r11)
            java.io.File r3 = r1.soDirectory
            java.lang.String r3 = r3.getCanonicalPath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0048:
            r8 = 0
            r12 = 0
            java.io.File r13 = new java.io.File     // Catch:{ all -> 0x0115 }
            java.io.File r14 = r1.soDirectory     // Catch:{ all -> 0x0115 }
            java.lang.String r15 = "dso_lock"
            r13.<init>(r14, r15)     // Catch:{ all -> 0x0115 }
            java.io.File r14 = r1.soDirectory     // Catch:{ all -> 0x0115 }
            com.facebook.soloader.FileLocker r13 = com.facebook.soloader.SysUtil.getOrCreateLockOnDir(r14, r13)     // Catch:{ all -> 0x0115 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
            r14.<init>(r7)     // Catch:{ all -> 0x0112 }
            java.io.File r7 = r1.soDirectory     // Catch:{ all -> 0x0112 }
            java.lang.StringBuilder r7 = r14.append(r7)     // Catch:{ all -> 0x0112 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0112 }
            com.facebook.soloader.LogUtil.v(r4, r7)     // Catch:{ all -> 0x0112 }
            java.io.File r7 = r1.soDirectory     // Catch:{ all -> 0x0112 }
            boolean r7 = r7.canWrite()     // Catch:{ all -> 0x0112 }
            if (r7 != 0) goto L_0x0099
            java.io.File r7 = r1.soDirectory     // Catch:{ all -> 0x0112 }
            boolean r7 = r7.setWritable(r9)     // Catch:{ all -> 0x0112 }
            if (r7 == 0) goto L_0x007c
            goto L_0x0099
        L_0x007c:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0112 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
            r7.<init>(r11)     // Catch:{ all -> 0x0112 }
            java.io.File r9 = r1.soDirectory     // Catch:{ all -> 0x0112 }
            java.lang.String r9 = r9.getCanonicalPath()     // Catch:{ all -> 0x0112 }
            java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ all -> 0x0112 }
            java.lang.StringBuilder r7 = r7.append(r10)     // Catch:{ all -> 0x0112 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0112 }
            r0.<init>(r7)     // Catch:{ all -> 0x0112 }
            throw r0     // Catch:{ all -> 0x0112 }
        L_0x0099:
            r7 = r17
            boolean r7 = r1.refreshLocked(r13, r7)     // Catch:{ all -> 0x0112 }
            if (r7 == 0) goto L_0x00a2
            goto L_0x00b5
        L_0x00a2:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0112 }
            r7.<init>(r0)     // Catch:{ all -> 0x0112 }
            java.io.File r0 = r1.soDirectory     // Catch:{ all -> 0x0112 }
            java.lang.StringBuilder r0 = r7.append(r0)     // Catch:{ all -> 0x0112 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0112 }
            com.facebook.soloader.LogUtil.i(r4, r0)     // Catch:{ all -> 0x0112 }
            r12 = r13
        L_0x00b5:
            if (r12 == 0) goto L_0x00cd
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0145 }
            r0.<init>(r5)     // Catch:{ all -> 0x0145 }
            java.io.File r2 = r1.soDirectory     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0145 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0145 }
            com.facebook.soloader.LogUtil.v(r4, r0)     // Catch:{ all -> 0x0145 }
            r12.close()     // Catch:{ all -> 0x0145 }
            goto L_0x00e3
        L_0x00cd:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0145 }
            r0.<init>(r6)     // Catch:{ all -> 0x0145 }
            java.io.File r5 = r1.soDirectory     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0145 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0145 }
            com.facebook.soloader.LogUtil.v(r4, r0)     // Catch:{ all -> 0x0145 }
        L_0x00e3:
            java.io.File r0 = r1.soDirectory
            boolean r0 = r0.canWrite()
            if (r0 == 0) goto L_0x0111
            java.io.File r0 = r1.soDirectory
            boolean r0 = r0.setWritable(r8)
            if (r0 == 0) goto L_0x00f4
            goto L_0x0111
        L_0x00f4:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.io.File r3 = r1.soDirectory
            java.lang.String r3 = r3.getCanonicalPath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0111:
            return
        L_0x0112:
            r0 = move-exception
            r12 = r13
            goto L_0x0116
        L_0x0115:
            r0 = move-exception
        L_0x0116:
            if (r12 == 0) goto L_0x012e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0145 }
            r2.<init>(r5)     // Catch:{ all -> 0x0145 }
            java.io.File r5 = r1.soDirectory     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ all -> 0x0145 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0145 }
            com.facebook.soloader.LogUtil.v(r4, r2)     // Catch:{ all -> 0x0145 }
            r12.close()     // Catch:{ all -> 0x0145 }
            goto L_0x0144
        L_0x012e:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0145 }
            r5.<init>(r6)     // Catch:{ all -> 0x0145 }
            java.io.File r6 = r1.soDirectory     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ all -> 0x0145 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0145 }
            com.facebook.soloader.LogUtil.v(r4, r2)     // Catch:{ all -> 0x0145 }
        L_0x0144:
            throw r0     // Catch:{ all -> 0x0145 }
        L_0x0145:
            r0 = move-exception
            java.io.File r2 = r1.soDirectory
            boolean r2 = r2.canWrite()
            if (r2 == 0) goto L_0x0173
            java.io.File r2 = r1.soDirectory
            boolean r2 = r2.setWritable(r8)
            if (r2 != 0) goto L_0x0173
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.io.File r3 = r1.soDirectory
            java.lang.String r3 = r3.getCanonicalPath()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L_0x0173:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.prepare(int):void");
    }

    @Nullable
    public String getLibraryPath(String str) throws IOException {
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            return null;
        }
        return soFileByName.getCanonicalPath();
    }

    public void prepareForceRefresh() throws IOException {
        prepare(2);
    }
}
