package com.facebook.soloader;

import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class SoFileLoaderImpl implements SoFileLoader {
    private static final String TAG = "SoFileLoaderImpl";
    @Nullable
    private final String mLocalLdLibraryPath;
    @Nullable
    private final String mLocalLdLibraryPathNoZips;
    @Nullable
    private final Method mNativeLoadRuntimeMethod;
    private final Runtime mRuntime = Runtime.getRuntime();

    public SoFileLoaderImpl() {
        Method nativeLoadRuntimeMethod = SysUtil.getNativeLoadRuntimeMethod();
        this.mNativeLoadRuntimeMethod = nativeLoadRuntimeMethod;
        String classLoaderLdLoadLibrary = nativeLoadRuntimeMethod != null ? SysUtil.getClassLoaderLdLoadLibrary() : null;
        this.mLocalLdLibraryPath = classLoaderLdLoadLibrary;
        this.mLocalLdLibraryPathNoZips = SysUtil.makeNonZipPath(classLoaderLdLoadLibrary);
    }

    public void loadBytes(String str, ElfByteChannel elfByteChannel, int i) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:22|23|32|33|34|35|37|38|39) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        com.facebook.soloader.LogUtil.e(TAG, "Error when loading library: " + r2 + ", library hash is " + getLibHash(r10) + ", LD_LIBRARY_PATH is " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0092, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b7, code lost:
        if (r1 != null) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b9, code lost:
        com.facebook.soloader.LogUtil.e(TAG, "Error when loading library: " + r1 + ", library hash is " + getLibHash(r10) + ", LD_LIBRARY_PATH is " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00e5, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0094 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(java.lang.String r10, int r11) {
        /*
            r9 = this;
            java.lang.String r0 = "nativeLoad() returned error for "
            java.lang.reflect.Method r1 = r9.mNativeLoadRuntimeMethod
            if (r1 != 0) goto L_0x000a
            java.lang.System.load(r10)
            return
        L_0x000a:
            r1 = 4
            r11 = r11 & r1
            r2 = 1
            r3 = 0
            if (r11 != r1) goto L_0x0012
            r11 = r2
            goto L_0x0013
        L_0x0012:
            r11 = r3
        L_0x0013:
            if (r11 == 0) goto L_0x0018
            java.lang.String r11 = r9.mLocalLdLibraryPath
            goto L_0x001a
        L_0x0018:
            java.lang.String r11 = r9.mLocalLdLibraryPathNoZips
        L_0x001a:
            r1 = 0
            java.lang.Runtime r4 = r9.mRuntime     // Catch:{ IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0094 }
            monitor-enter(r4)     // Catch:{ IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0094 }
            java.lang.reflect.Method r5 = r9.mNativeLoadRuntimeMethod     // Catch:{ all -> 0x008f }
            java.lang.Runtime r6 = r9.mRuntime     // Catch:{ all -> 0x008f }
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x008f }
            r7[r3] = r10     // Catch:{ all -> 0x008f }
            java.lang.Class<com.facebook.soloader.SoLoader> r3 = com.facebook.soloader.SoLoader.class
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x008f }
            r7[r2] = r3     // Catch:{ all -> 0x008f }
            r2 = 2
            r7[r2] = r11     // Catch:{ all -> 0x008f }
            java.lang.Object r2 = r5.invoke(r6, r7)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x008f }
            if (r2 != 0) goto L_0x006d
            monitor-exit(r4)     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x0069
            java.lang.String r0 = "SoFileLoaderImpl"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Error when loading library: "
            r1.<init>(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ", library hash is "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r10 = r9.getLibHash(r10)
            java.lang.StringBuilder r10 = r1.append(r10)
            java.lang.String r1 = ", LD_LIBRARY_PATH is "
            java.lang.StringBuilder r10 = r10.append(r1)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.facebook.soloader.LogUtil.e(r0, r10)
        L_0x0069:
            return
        L_0x006a:
            r0 = move-exception
            r1 = r2
            goto L_0x0090
        L_0x006d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x006a }
            r1.<init>(r0)     // Catch:{ all -> 0x006a }
            java.lang.StringBuilder r0 = r1.append(r10)     // Catch:{ all -> 0x006a }
            java.lang.String r1 = ": "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x006a }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x006a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x006a }
            com.facebook.soloader.SoLoaderULError r1 = new com.facebook.soloader.SoLoaderULError     // Catch:{ all -> 0x008a }
            r1.<init>(r10, r0)     // Catch:{ all -> 0x008a }
            throw r1     // Catch:{ all -> 0x008a }
        L_0x008a:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0090
        L_0x008f:
            r0 = move-exception
        L_0x0090:
            monitor-exit(r4)     // Catch:{ all -> 0x008f }
            throw r0     // Catch:{ IllegalAccessException | IllegalArgumentException | InvocationTargetException -> 0x0094 }
        L_0x0092:
            r0 = move-exception
            goto L_0x00b7
        L_0x0094:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0092 }
            r0.<init>()     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = "nativeLoad() error during invocation for "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0092 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = ": "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0092 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0092 }
            java.lang.String r1 = r0.toString()     // Catch:{ all -> 0x0092 }
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0092 }
            r0.<init>(r1)     // Catch:{ all -> 0x0092 }
            throw r0     // Catch:{ all -> 0x0092 }
        L_0x00b7:
            if (r1 == 0) goto L_0x00e5
            java.lang.String r2 = "SoFileLoaderImpl"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Error when loading library: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = ", library hash is "
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r10 = r9.getLibHash(r10)
            java.lang.StringBuilder r10 = r1.append(r10)
            java.lang.String r1 = ", LD_LIBRARY_PATH is "
            java.lang.StringBuilder r10 = r10.append(r1)
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.facebook.soloader.LogUtil.e(r2, r10)
        L_0x00e5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoFileLoaderImpl.load(java.lang.String, int):void");
    }

    private String getLibHash(String str) {
        FileInputStream create;
        try {
            File file = new File(str);
            MessageDigest instance = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            create = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = create.read(bArr);
                if (read > 0) {
                    instance.update(bArr, 0, read);
                } else {
                    String format = String.format("%32x", new Object[]{new BigInteger(1, instance.digest())});
                    create.close();
                    return format;
                }
            }
        } catch (IOException | SecurityException | NoSuchAlgorithmException e) {
            return e.toString();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
