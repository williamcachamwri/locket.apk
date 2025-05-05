package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import com.facebook.soloader.MinElf;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Stack;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

public final class SysUtil {
    private static final long APK_DEP_BLOCK_METADATA_LENGTH = 20;
    private static final byte APK_SIGNATURE_VERSION = 1;
    private static final String TAG = "SysUtil";

    public static int findAbiScore(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (str2 != null && str.equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            LogUtil.e(TAG, "Enable write permission failed: " + parentFile);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        return MarshmallowSysdeps.getSupportedAbis();
    }

    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
        LollipopSysdeps.fallocateIfSupported(fileDescriptor, j);
    }

    public static void dumbDelete(File file) throws IOException {
        Stack stack = new Stack();
        stack.push(file);
        ArrayList arrayList = new ArrayList();
        while (!stack.isEmpty()) {
            File file2 = (File) stack.pop();
            if (!file2.isDirectory()) {
                deleteOrThrow(file2);
            } else {
                arrayList.add(file2);
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File push : listFiles) {
                        stack.push(push);
                    }
                }
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            deleteOrThrow((File) arrayList.get(size));
        }
    }

    private static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            try {
                if (is64Bit()) {
                    treeSet.add(MinElf.ISA.AARCH64);
                    treeSet.add(MinElf.ISA.X86_64);
                } else {
                    treeSet.add(MinElf.ISA.ARM);
                    treeSet.add(MinElf.ISA.X86);
                }
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (treeSet.contains(str)) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            } catch (ErrnoException e) {
                LogUtil.e(SysUtil.TAG, String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", new Object[]{Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()}));
                return Build.SUPPORTED_ABIS;
            }
        }

        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (ErrnoException e) {
                if (e.errno != OsConstants.EOPNOTSUPP && e.errno != OsConstants.ENOSYS && e.errno != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }

        public static boolean is64Bit() throws ErrnoException {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    private static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            if (is64Bit()) {
                treeSet.add(MinElf.ISA.AARCH64);
                treeSet.add(MinElf.ISA.X86_64);
            } else {
                treeSet.add(MinElf.ISA.ARM);
                treeSet.add(MinElf.ISA.X86);
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        public static boolean is64Bit() {
            return Process.is64Bit();
        }

        public static boolean isSupportedDirectLoad(@Nullable Context context, int i) {
            if (i == 2) {
                return true;
            }
            return isDisabledExtractNativeLibs(context);
        }

        public static boolean isDisabledExtractNativeLibs(@Nullable Context context) {
            return context != null && (context.getApplicationInfo().flags & 268435456) == 0;
        }

        private static boolean isApkUncompressedDso(Context context) throws IOException {
            ZipFile zipFile = new ZipFile(new File(context.getApplicationInfo().sourceDir));
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (true) {
                    boolean z = false;
                    if (entries.hasMoreElements()) {
                        ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                        if (zipEntry != null && zipEntry.getName().endsWith(".so") && zipEntry.getName().contains("/lib") && !zipEntry.getName().startsWith("assets/")) {
                            if (zipEntry.getMethod() == 0) {
                                z = true;
                            }
                            zipFile.close();
                            return z;
                        }
                    } else {
                        zipFile.close();
                        return false;
                    }
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }
    }

    public static void mkdirOrThrow(File file) throws IOException {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    static int copyBytes(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i - i2));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    public static void fsyncAll(File file) throws IOException {
        RandomAccessFile randomAccessFile;
        Stack stack = new Stack();
        stack.push(file);
        while (!stack.isEmpty()) {
            File file2 = (File) stack.pop();
            if (file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File push : listFiles) {
                        stack.push(push);
                    }
                } else {
                    throw new IOException("cannot list directory " + file2);
                }
            } else if (!file2.getPath().endsWith("_lock")) {
                try {
                    randomAccessFile = new RandomAccessFile(file2, "r");
                    randomAccessFile.getFD().sync();
                    randomAccessFile.close();
                } catch (IOException e) {
                    LogUtil.e(TAG, "Syncing failed for " + file2 + ": " + e.getMessage());
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                continue;
            }
        }
        return;
        throw th;
    }

    private static long getParcelPadSize(long j) {
        return j + ((4 - (j % 4)) % 4);
    }

    public static long getApkDepBlockLength(File file) throws IOException {
        return getParcelPadSize(((long) (file.getCanonicalFile().getPath().length() + 1)) * 2) + 20;
    }

    public static byte[] makeApkDepBlock(File file, Context context) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 1);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(getAppVersionCode(context));
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }

    public static boolean is64Bit() {
        return MarshmallowSysdeps.is64Bit();
    }

    public static boolean isSupportedDirectLoad(@Nullable Context context, int i) {
        return MarshmallowSysdeps.isSupportedDirectLoad(context, i);
    }

    public static boolean isDisabledExtractNativeLibs(Context context) {
        return MarshmallowSysdeps.isDisabledExtractNativeLibs(context);
    }

    @Nullable
    public static FileLocker getOrCreateLockOnDir(File file, File file2) throws IOException {
        boolean z;
        try {
            return getFileLocker(file2);
        } catch (FileNotFoundException e) {
            z = true;
            if (file.setWritable(true)) {
                FileLocker fileLocker = getFileLocker(file2);
                if (!file.setWritable(false)) {
                    LogUtil.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
                }
                return fileLocker;
            }
            throw e;
        } catch (Throwable th) {
            th = th;
        }
        if (z && !file.setWritable(false)) {
            LogUtil.w(TAG, "error removing " + file.getCanonicalPath() + " write permission");
        }
        throw th;
    }

    public static FileLocker getFileLocker(File file) throws IOException {
        return FileLocker.lock(file);
    }

    public static String getBaseName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
    }

    @Nullable
    public static String makeNonZipPath(@Nullable String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(":");
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            if (!str2.contains("!")) {
                arrayList.add(str2);
            }
        }
        return TextUtils.join(":", arrayList);
    }

    @Nullable
    public static String getClassLoaderLdLoadLibrary() {
        ClassLoader classLoader = SoLoader.class.getClassLoader();
        if (classLoader == null || (classLoader instanceof BaseDexClassLoader)) {
            try {
                return (String) BaseDexClassLoader.class.getMethod("getLdLibraryPath", new Class[0]).invoke((BaseDexClassLoader) classLoader, new Object[0]);
            } catch (Exception e) {
                LogUtil.e(TAG, "Cannot call getLdLibraryPath", e);
                return null;
            }
        } else {
            throw new IllegalStateException("ClassLoader " + classLoader.getClass().getName() + " should be of type BaseDexClassLoader");
        }
    }

    @Nullable
    public static Method getNativeLoadRuntimeMethod() {
        if (Build.VERSION.SDK_INT > 27) {
            return null;
        }
        try {
            Method declaredMethod = Runtime.class.getDeclaredMethod("nativeLoad", new Class[]{String.class, ClassLoader.class, String.class});
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception e) {
            LogUtil.w(TAG, "Cannot get nativeLoad method", e);
            return null;
        }
    }
}
