package com.facebook.soloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;
import android.text.TextUtils;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.soloader.nativeloader.SystemDelegate;
import com.facebook.soloader.recovery.DefaultRecoveryStrategyFactory;
import com.facebook.soloader.recovery.RecoveryStrategy;
import com.facebook.soloader.recovery.RecoveryStrategyFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

public class SoLoader {
    static final boolean DEBUG = false;
    public static final int SOLOADER_ALLOW_ASYNC_INIT = 2;
    public static final int SOLOADER_DISABLE_BACKUP_SOSOURCE = 8;
    public static final int SOLOADER_DISABLE_FS_SYNC_JOB = 256;
    @Deprecated
    public static final int SOLOADER_DONT_TREAT_AS_SYSTEMAPP = 32;
    @Deprecated
    public static final int SOLOADER_ENABLE_DIRECT_SOSOURCE = 64;
    public static final int SOLOADER_ENABLE_EXOPACKAGE = 1;
    public static final int SOLOADER_ENABLE_SYSTEMLOAD_WRAPPER_SOSOURCE = 512;
    public static final int SOLOADER_EXPLICITLY_ENABLE_BACKUP_SOSOURCE = 128;
    public static final int SOLOADER_LOOK_IN_ZIP = 4;
    public static final int SOLOADER_SKIP_MERGED_JNI_ONLOAD = 16;
    private static final String SO_STORE_NAME_MAIN = "lib-main";
    private static final String SO_STORE_NAME_SPLIT = "lib-";
    static final boolean SYSTRACE_LIBRARY_LOADING = true;
    public static final String TAG = "SoLoader";
    public static String VERSION = "0.10.5";
    private static boolean isEnabled = true;
    private static int sAppType = 0;
    static Context sApplicationContext = null;
    private static int sFlags;
    private static final Set<String> sLoadedAndMergedLibraries = Collections.newSetFromMap(new ConcurrentHashMap());
    /* access modifiers changed from: private */
    public static final HashSet<String> sLoadedLibraries = new HashSet<>();
    /* access modifiers changed from: private */
    public static final Map<String, Object> sLoadingLibraries = new HashMap();
    /* access modifiers changed from: private */
    @Nullable
    public static RecoveryStrategyFactory sRecoveryStrategyFactory = null;
    @Nullable
    static SoFileLoader sSoFileLoader;
    /* access modifiers changed from: private */
    @Nullable
    public static volatile SoSource[] sSoSources = null;
    /* access modifiers changed from: private */
    public static final ReentrantReadWriteLock sSoSourcesLock = new ReentrantReadWriteLock();
    /* access modifiers changed from: private */
    public static final AtomicInteger sSoSourcesVersion = new AtomicInteger(0);
    @Nullable
    private static SystemLoadLibraryWrapper sSystemLoadLibraryWrapper = null;

    interface AppType {
        public static final int SYSTEM_APP = 2;
        public static final int THIRD_PARTY_APP = 1;
        public static final int UNSET = 0;
        public static final int UPDATED_SYSTEM_APP = 3;
    }

    public static void init(Context context, int i) throws IOException {
        init(context, i, (SoFileLoader) null);
    }

    public static void init(Context context, int i, @Nullable SoFileLoader soFileLoader) throws IOException {
        if (isInitialized()) {
            LogUtil.w(TAG, "SoLoader already initialized");
            return;
        }
        LogUtil.w(TAG, "Initializing SoLoader: " + i);
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            boolean initEnableConfig = initEnableConfig(context);
            isEnabled = initEnableConfig;
            if (initEnableConfig) {
                int appType = getAppType(context);
                sAppType = appType;
                if ((i & 128) == 0 && SysUtil.isSupportedDirectLoad(context, appType)) {
                    i |= 8;
                }
                initSoLoader(context, soFileLoader);
                initSoSources(context, i);
                LogUtil.v(TAG, "Init SoLoader delegate");
                NativeLoader.initIfUninitialized(new NativeLoaderToSoLoaderDelegate());
            } else {
                initDummySoSource();
                LogUtil.v(TAG, "Init System Loader delegate");
                NativeLoader.initIfUninitialized(new SystemDelegate());
            }
            LogUtil.w(TAG, "SoLoader initialized: " + i);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
        }
    }

    public static void init(Context context, boolean z) {
        try {
            init(context, z ? 1 : 0, (SoFileLoader) null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean initEnableConfig(android.content.Context r4) {
        /*
            r0 = 0
            java.lang.String r1 = r4.getPackageName()     // Catch:{ Exception -> 0x0014 }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ Exception -> 0x0012 }
            r2 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo(r1, r2)     // Catch:{ Exception -> 0x0012 }
            android.os.Bundle r0 = r4.metaData     // Catch:{ Exception -> 0x0012 }
            goto L_0x0030
        L_0x0012:
            r4 = move-exception
            goto L_0x0016
        L_0x0014:
            r4 = move-exception
            r1 = r0
        L_0x0016:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unexpected issue with package manager ("
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = ")"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "SoLoader"
            com.facebook.soloader.LogUtil.w(r2, r1, r4)
        L_0x0030:
            r4 = 1
            if (r0 == 0) goto L_0x003d
            java.lang.String r1 = "com.facebook.soloader.enabled"
            boolean r0 = r0.getBoolean(r1, r4)
            if (r0 == 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r4 = 0
        L_0x003d:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.initEnableConfig(android.content.Context):boolean");
    }

    private static void initSoSources(Context context, int i) throws IOException {
        if (sSoSources == null) {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (sSoSources != null) {
                    reentrantReadWriteLock.writeLock().unlock();
                    return;
                }
                sFlags = i;
                ArrayList arrayList = new ArrayList();
                if ((i & 512) != 0) {
                    addSystemLoadWrapperSoSource(context, arrayList);
                } else {
                    addSystemLibSoSource(arrayList);
                    if (context != null) {
                        if ((i & 1) != 0) {
                            addApplicationSoSource(arrayList, getApplicationSoSourceFlags());
                            LogUtil.d(TAG, "Adding exo package source: lib-main");
                            arrayList.add(0, new ExoSoSource(context, SO_STORE_NAME_MAIN));
                        } else {
                            if (SysUtil.isSupportedDirectLoad(context, sAppType)) {
                                addDirectApkSoSource(context, arrayList);
                            }
                            addApplicationSoSource(arrayList, getApplicationSoSourceFlags());
                            addBackupSoSource(context, arrayList, 1);
                        }
                    }
                }
                SoSource[] soSourceArr = (SoSource[]) arrayList.toArray(new SoSource[arrayList.size()]);
                int makePrepareFlags = makePrepareFlags();
                int length = soSourceArr.length;
                while (true) {
                    int i2 = length - 1;
                    if (length > 0) {
                        LogUtil.d(TAG, "Preparing SO source: " + soSourceArr[i2]);
                        boolean z = SYSTRACE_LIBRARY_LOADING;
                        if (z) {
                            Api18TraceUtils.beginTraceSection(TAG, "_", soSourceArr[i2].getClass().getSimpleName());
                        }
                        soSourceArr[i2].prepare(makePrepareFlags);
                        if (z) {
                            Api18TraceUtils.endSection();
                        }
                        length = i2;
                    } else {
                        sSoSources = soSourceArr;
                        sSoSourcesVersion.getAndIncrement();
                        LogUtil.d(TAG, "init finish: " + sSoSources.length + " SO sources prepared");
                        return;
                    }
                }
            } finally {
                sSoSourcesLock.writeLock().unlock();
            }
        }
    }

    private static void initDummySoSource() {
        if (sSoSources == null) {
            ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
            reentrantReadWriteLock.writeLock().lock();
            try {
                if (sSoSources != null) {
                    reentrantReadWriteLock.writeLock().unlock();
                    return;
                }
                sSoSources = new SoSource[0];
                reentrantReadWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                sSoSourcesLock.writeLock().unlock();
                throw th;
            }
        }
    }

    private static int getApplicationSoSourceFlags() {
        int i = sAppType;
        if (i == 1) {
            return 0;
        }
        if (i == 2 || i == 3) {
            return 1;
        }
        throw new RuntimeException("Unsupported app type, we should not reach here");
    }

    private static void addDirectApkSoSource(Context context, ArrayList<SoSource> arrayList) {
        DirectApkSoSource directApkSoSource = new DirectApkSoSource(context);
        LogUtil.d(TAG, "validating/adding directApk source: " + directApkSoSource.toString());
        if (directApkSoSource.isValid()) {
            arrayList.add(0, directApkSoSource);
        }
    }

    private static void addApplicationSoSource(ArrayList<SoSource> arrayList, int i) {
        ApplicationSoSource applicationSoSource = new ApplicationSoSource(sApplicationContext, i);
        LogUtil.d(TAG, "Adding application source: " + applicationSoSource.toString());
        arrayList.add(0, applicationSoSource);
    }

    private static void addBackupSoSource(Context context, ArrayList<SoSource> arrayList, int i) throws IOException {
        if ((sFlags & 8) != 0) {
            File soStorePath = UnpackingSoSource.getSoStorePath(context, SO_STORE_NAME_MAIN);
            try {
                if (soStorePath.exists()) {
                    SysUtil.dumbDelete(soStorePath);
                }
            } catch (Throwable th) {
                LogUtil.w(TAG, "Failed to delete " + soStorePath.getCanonicalPath(), th);
            }
        } else {
            File file = new File(context.getApplicationInfo().sourceDir);
            ArrayList arrayList2 = new ArrayList();
            BackupSoSource backupSoSource = new BackupSoSource(context, file, SO_STORE_NAME_MAIN, i);
            arrayList2.add(backupSoSource);
            LogUtil.d(TAG, "adding backup source from : " + backupSoSource.toString());
            addBackupSoSourceFromSplitApk(context, i, arrayList2);
            arrayList.addAll(0, arrayList2);
        }
    }

    private static void addBackupSoSourceFromSplitApk(Context context, int i, ArrayList<UnpackingSoSource> arrayList) throws IOException {
        if (context.getApplicationInfo().splitSourceDirs != null) {
            LogUtil.d(TAG, "adding backup sources from split apks");
            String[] strArr = context.getApplicationInfo().splitSourceDirs;
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i3 + 1;
                BackupSoSource backupSoSource = new BackupSoSource(context, new File(strArr[i2]), SO_STORE_NAME_SPLIT + i3, i);
                LogUtil.d(TAG, "adding backup source: " + backupSoSource.toString());
                if (backupSoSource.hasZippedLibs()) {
                    arrayList.add(backupSoSource);
                }
                i2++;
                i3 = i4;
            }
        }
    }

    private static void addSystemLibSoSource(ArrayList<SoSource> arrayList) {
        String str = SysUtil.is64Bit() ? "/system/lib64:/vendor/lib64" : "/system/lib:/vendor/lib";
        String str2 = System.getenv("LD_LIBRARY_PATH");
        if (str2 != null && !str2.equals("")) {
            str = str2 + ":" + str;
        }
        for (String str3 : new HashSet(Arrays.asList(str.split(":")))) {
            LogUtil.d(TAG, "adding system library source: " + str3);
            arrayList.add(new DirectorySoSource(new File(str3), 2));
        }
    }

    private static void addSystemLoadWrapperSoSource(Context context, ArrayList<SoSource> arrayList) {
        SystemLoadWrapperSoSource systemLoadWrapperSoSource = new SystemLoadWrapperSoSource();
        LogUtil.d(TAG, "adding systemLoadWrapper source: " + systemLoadWrapperSoSource);
        arrayList.add(0, systemLoadWrapperSoSource);
    }

    private static int makePrepareFlags() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            int i = sFlags;
            int i2 = (i & 2) != 0 ? 1 : 0;
            if ((i & 256) != 0) {
                i2 |= 4;
            }
            reentrantReadWriteLock.writeLock().unlock();
            return i2;
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    private static synchronized void initSoLoader(@Nullable Context context, @Nullable SoFileLoader soFileLoader) {
        synchronized (SoLoader.class) {
            if (context != null) {
                try {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext == null) {
                        LogUtil.w(TAG, "context.getApplicationContext returned null, holding reference to original context.ApplicationSoSource fallbacks to: " + context.getApplicationInfo().nativeLibraryDir);
                    } else {
                        context = applicationContext;
                    }
                    sApplicationContext = context;
                    sRecoveryStrategyFactory = new DefaultRecoveryStrategyFactory(context);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (soFileLoader == null && sSoFileLoader != null) {
                return;
            }
            if (soFileLoader != null) {
                sSoFileLoader = soFileLoader;
            } else {
                sSoFileLoader = new SoFileLoaderImpl();
            }
        }
    }

    private static int getAppType(Context context) {
        int i = sAppType;
        if (i != 0) {
            return i;
        }
        int i2 = 1;
        if (context == null) {
            LogUtil.d(TAG, "context is null, fallback to THIRD_PARTY_APP appType");
            return 1;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if ((applicationInfo.flags & 1) != 0) {
            i2 = (applicationInfo.flags & 128) != 0 ? 3 : 2;
        }
        LogUtil.d(TAG, "ApplicationInfo.flags is: " + applicationInfo.flags + " appType is: " + i2);
        return i2;
    }

    public static void setInTestMode() {
        TestOnlyUtils.setSoSources(new SoSource[]{new NoopSoSource()});
    }

    public static void deinitForTest() {
        TestOnlyUtils.setSoSources((SoSource[]) null);
    }

    static class TestOnlyUtils {
        TestOnlyUtils() {
        }

        static void setSoSources(SoSource[] soSourceArr) {
            SoLoader.sSoSourcesLock.writeLock().lock();
            try {
                SoSource[] unused = SoLoader.sSoSources = soSourceArr;
                SoLoader.sSoSourcesVersion.getAndIncrement();
            } finally {
                SoLoader.sSoSourcesLock.writeLock().unlock();
            }
        }

        static void setSoFileLoader(SoFileLoader soFileLoader) {
            SoLoader.sSoFileLoader = soFileLoader;
        }

        static void resetStatus() {
            synchronized (SoLoader.class) {
                SoLoader.sLoadedLibraries.clear();
                SoLoader.sLoadingLibraries.clear();
                SoLoader.sSoFileLoader = null;
                SoLoader.sApplicationContext = null;
                RecoveryStrategyFactory unused = SoLoader.sRecoveryStrategyFactory = null;
            }
            setSoSources((SoSource[]) null);
        }

        static void setContext(Context context) {
            SoLoader.sApplicationContext = context;
        }
    }

    public static void setSystemLoadLibraryWrapper(SystemLoadLibraryWrapper systemLoadLibraryWrapper) {
        sSystemLoadLibraryWrapper = systemLoadLibraryWrapper;
    }

    public static final class WrongAbiError extends UnsatisfiedLinkError {
        WrongAbiError(Throwable th, String str) {
            super("APK was built for a different platform. Supported ABIs: " + Arrays.toString(SysUtil.getSupportedAbis()) + " error: " + str);
            initCause(th);
        }
    }

    @Nullable
    public static String getLibraryPath(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String str2 = null;
            if (sSoSources != null) {
                int i = 0;
                while (str2 == null && i < sSoSources.length) {
                    str2 = sSoSources[i].getLibraryPath(str);
                    i++;
                }
            }
            return str2;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static SoSource[] cloneSoSources() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            SoSource[] soSourceArr = sSoSources == null ? new SoSource[0] : (SoSource[]) sSoSources.clone();
            reentrantReadWriteLock.readLock().unlock();
            return soSourceArr;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    @Nullable
    public static String[] getLibraryDependencies(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            String[] strArr = null;
            if (sSoSources != null) {
                int i = 0;
                while (strArr == null && i < sSoSources.length) {
                    strArr = sSoSources[i].getLibraryDependencies(str);
                    i++;
                }
            }
            return strArr;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    @Nullable
    public static File getSoFile(String str) {
        String mapLibName = MergedSoMapping.mapLibName(str);
        if (mapLibName != null) {
            str = mapLibName;
        }
        String mapLibraryName = System.mapLibraryName(str);
        sSoSourcesLock.readLock().lock();
        try {
            if (sSoSources != null) {
                int i = 0;
                while (i < sSoSources.length) {
                    try {
                        File soFileByName = sSoSources[i].getSoFileByName(mapLibraryName);
                        if (soFileByName != null) {
                            return soFileByName;
                        }
                        i++;
                    } catch (IOException unused) {
                    }
                }
            }
            sSoSourcesLock.readLock().unlock();
            return null;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    public static boolean loadLibrary(String str) {
        return isEnabled ? loadLibrary(str, 0) : NativeLoader.loadLibrary(str);
    }

    public static boolean loadLibrary(String str, int i) throws UnsatisfiedLinkError {
        SystemLoadLibraryWrapper systemLoadLibraryWrapper;
        Boolean loadLibraryOnNonAndroid = loadLibraryOnNonAndroid(str);
        if (loadLibraryOnNonAndroid != null) {
            return loadLibraryOnNonAndroid.booleanValue();
        }
        if (!isEnabled) {
            return NativeLoader.loadLibrary(str);
        }
        int i2 = sAppType;
        if ((i2 == 2 || i2 == 3) && (systemLoadLibraryWrapper = sSystemLoadLibraryWrapper) != null) {
            systemLoadLibraryWrapper.loadLibrary(str);
            return true;
        }
        String mapLibName = MergedSoMapping.mapLibName(str);
        return loadLibraryBySoName(System.mapLibraryName(mapLibName != null ? mapLibName : str), str, mapLibName, i, (StrictMode.ThreadPolicy) null);
    }

    @Nullable
    private static Boolean loadLibraryOnNonAndroid(String str) {
        Boolean valueOf;
        if (sSoSources != null) {
            return null;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                if ("http://www.android.com/".equals(System.getProperty("java.vendor.url"))) {
                    assertInitialized();
                } else {
                    synchronized (SoLoader.class) {
                        boolean z = !sLoadedLibraries.contains(str);
                        if (z) {
                            SystemLoadLibraryWrapper systemLoadLibraryWrapper = sSystemLoadLibraryWrapper;
                            if (systemLoadLibraryWrapper != null) {
                                systemLoadLibraryWrapper.loadLibrary(str);
                            } else {
                                System.loadLibrary(str);
                            }
                        }
                        valueOf = Boolean.valueOf(z);
                    }
                    reentrantReadWriteLock.readLock().unlock();
                    return valueOf;
                }
            }
            reentrantReadWriteLock.readLock().unlock();
            return null;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    static void loadDependency(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        loadLibraryBySoNameImpl(str, (String) null, (String) null, i | 1, threadPolicy);
    }

    private static boolean loadLibraryBySoName(String str, @Nullable String str2, @Nullable String str3, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        RecoveryStrategy recoveryStrategy = null;
        while (true) {
            try {
                return loadLibraryBySoNameImpl(str, str2, str3, i, threadPolicy);
            } catch (UnsatisfiedLinkError e) {
                LogUtil.w(TAG, "Starting recovery for " + str, e);
                sSoSourcesLock.writeLock().lock();
                if (recoveryStrategy == null) {
                    try {
                        recoveryStrategy = getRecoveryStrategy();
                    } catch (NoBaseApkException e2) {
                        LogUtil.e(TAG, "Base APK not found during recovery", e2);
                        throw e2;
                    } catch (Exception e3) {
                        LogUtil.e(TAG, "Got an exception during recovery, will throw the initial error instead", e3);
                        throw e;
                    } catch (Throwable th) {
                        sSoSourcesLock.writeLock().unlock();
                        throw th;
                    }
                }
                if (recoveryStrategy == null || !recoveryStrategy.recover(e, sSoSources)) {
                    sSoSourcesLock.writeLock().unlock();
                    LogUtil.w(TAG, "Failed to recover");
                    throw e;
                }
                sSoSourcesVersion.getAndIncrement();
                LogUtil.w(TAG, "Attempting to load library again");
                sSoSourcesLock.writeLock().unlock();
            }
        }
        sSoSourcesLock.writeLock().unlock();
        LogUtil.w(TAG, "Failed to recover");
        throw e;
    }

    @Nullable
    private static synchronized RecoveryStrategy getRecoveryStrategy() {
        RecoveryStrategy recoveryStrategy;
        synchronized (SoLoader.class) {
            RecoveryStrategyFactory recoveryStrategyFactory = sRecoveryStrategyFactory;
            recoveryStrategy = recoveryStrategyFactory == null ? null : recoveryStrategyFactory.get();
        }
        return recoveryStrategy;
    }

    static synchronized void setRecoveryStrategyFactory(RecoveryStrategyFactory recoveryStrategyFactory) {
        synchronized (SoLoader.class) {
            sRecoveryStrategyFactory = recoveryStrategyFactory;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x014c, code lost:
        return !r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x014f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0150, code lost:
        sSoSourcesLock.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0159, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        r7 = sSoSourcesLock;
        r7.readLock().lock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        monitor-enter(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        if (r10 != false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
        r13 = com.facebook.soloader.SoLoader.class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        monitor-enter(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r9.contains(r15) == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005c, code lost:
        if (r17 != null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005e, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0060, code lost:
        r7.readLock().unlock();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006a, code lost:
        if (r10 != false) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        com.facebook.soloader.LogUtil.d(TAG, "About to load: " + r15);
        doLoadLibraryBySoName(r15, r0, r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        com.facebook.soloader.LogUtil.d(TAG, "Loaded: " + r15);
        r3 = com.facebook.soloader.SoLoader.class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0097, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r9.add(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009b, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a1, code lost:
        r1 = r0.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a5, code lost:
        if (r1 == null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c0, code lost:
        throw new com.facebook.soloader.SoLoader.WrongAbiError(r0, r1.substring(r1.lastIndexOf("unexpected e_machine:")));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c1, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00ca, code lost:
        if ((r0 & 16) != 0) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00d0, code lost:
        if (android.text.TextUtils.isEmpty(r16) != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00d8, code lost:
        if (sLoadedAndMergedLibraries.contains(r2) == false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00da, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00db, code lost:
        if (r17 == null) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00dd, code lost:
        if (r8 != false) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00df, code lost:
        r0 = SYSTRACE_LIBRARY_LOADING;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00e1, code lost:
        if (r0 == false) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00e3, code lost:
        com.facebook.soloader.Api18TraceUtils.beginTraceSection("MergedSoMapping.invokeJniOnload[", r2, "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        com.facebook.soloader.LogUtil.d(TAG, "About to merge: " + r2 + " / " + r15);
        com.facebook.soloader.MergedSoMapping.invokeJniOnload(r16);
        sLoadedAndMergedLibraries.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x010e, code lost:
        if (r0 == false) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0114, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0116, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0139, code lost:
        throw new java.lang.RuntimeException("Failed to call JNI_OnLoad from '" + r2 + "', which has been merged into '" + r15 + "'.  See comment for details.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x013c, code lost:
        if (SYSTRACE_LIBRARY_LOADING != false) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x013e, code lost:
        com.facebook.soloader.Api18TraceUtils.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0141, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0142, code lost:
        monitor-exit(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0143, code lost:
        r7.readLock().unlock();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean loadLibraryBySoNameImpl(java.lang.String r15, @javax.annotation.Nullable java.lang.String r16, @javax.annotation.Nullable java.lang.String r17, int r18, @javax.annotation.Nullable android.os.StrictMode.ThreadPolicy r19) {
        /*
            r1 = r15
            r2 = r16
            r0 = r18
            java.lang.String r3 = "Loaded: "
            java.lang.String r4 = "Failed to call JNI_OnLoad from '"
            java.lang.String r5 = "About to load: "
            java.lang.String r6 = "About to merge: "
            boolean r7 = android.text.TextUtils.isEmpty(r16)
            r8 = 0
            if (r7 != 0) goto L_0x001d
            java.util.Set<java.lang.String> r7 = sLoadedAndMergedLibraries
            boolean r7 = r7.contains(r2)
            if (r7 == 0) goto L_0x001d
            return r8
        L_0x001d:
            java.lang.Class<com.facebook.soloader.SoLoader> r7 = com.facebook.soloader.SoLoader.class
            monitor-enter(r7)
            java.util.HashSet<java.lang.String> r9 = sLoadedLibraries     // Catch:{ all -> 0x015a }
            boolean r10 = r9.contains(r15)     // Catch:{ all -> 0x015a }
            r11 = 1
            if (r10 == 0) goto L_0x002f
            if (r17 != 0) goto L_0x002d
            monitor-exit(r7)     // Catch:{ all -> 0x015a }
            return r8
        L_0x002d:
            r10 = r11
            goto L_0x0030
        L_0x002f:
            r10 = r8
        L_0x0030:
            java.util.Map<java.lang.String, java.lang.Object> r12 = sLoadingLibraries     // Catch:{ all -> 0x015a }
            boolean r13 = r12.containsKey(r15)     // Catch:{ all -> 0x015a }
            if (r13 == 0) goto L_0x003d
            java.lang.Object r12 = r12.get(r15)     // Catch:{ all -> 0x015a }
            goto L_0x0046
        L_0x003d:
            java.lang.Object r13 = new java.lang.Object     // Catch:{ all -> 0x015a }
            r13.<init>()     // Catch:{ all -> 0x015a }
            r12.put(r15, r13)     // Catch:{ all -> 0x015a }
            r12 = r13
        L_0x0046:
            monitor-exit(r7)     // Catch:{ all -> 0x015a }
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = sSoSourcesLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r13 = r7.readLock()
            r13.lock()
            monitor-enter(r12)     // Catch:{ all -> 0x014f }
            if (r10 != 0) goto L_0x00c8
            java.lang.Class<com.facebook.soloader.SoLoader> r13 = com.facebook.soloader.SoLoader.class
            monitor-enter(r13)     // Catch:{ all -> 0x00c5 }
            boolean r14 = r9.contains(r15)     // Catch:{ all -> 0x00c2 }
            if (r14 == 0) goto L_0x0069
            if (r17 != 0) goto L_0x0068
            monitor-exit(r13)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r12)     // Catch:{ all -> 0x00c5 }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r7.readLock()
            r0.unlock()
            return r8
        L_0x0068:
            r10 = r11
        L_0x0069:
            monitor-exit(r13)     // Catch:{ all -> 0x00c2 }
            if (r10 != 0) goto L_0x00c8
            java.lang.String r13 = "SoLoader"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            r14.<init>(r5)     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            java.lang.StringBuilder r5 = r14.append(r15)     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            java.lang.String r5 = r5.toString()     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            com.facebook.soloader.LogUtil.d(r13, r5)     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            r5 = r19
            doLoadLibraryBySoName(r15, r0, r5)     // Catch:{ UnsatisfiedLinkError -> 0x00a0 }
            java.lang.String r5 = "SoLoader"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c5 }
            r13.<init>(r3)     // Catch:{ all -> 0x00c5 }
            java.lang.StringBuilder r3 = r13.append(r15)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00c5 }
            com.facebook.soloader.LogUtil.d(r5, r3)     // Catch:{ all -> 0x00c5 }
            java.lang.Class<com.facebook.soloader.SoLoader> r3 = com.facebook.soloader.SoLoader.class
            monitor-enter(r3)     // Catch:{ all -> 0x00c5 }
            r9.add(r15)     // Catch:{ all -> 0x009d }
            monitor-exit(r3)     // Catch:{ all -> 0x009d }
            goto L_0x00c8
        L_0x009d:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x009d }
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x00a0:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()     // Catch:{ all -> 0x00c5 }
            if (r1 == 0) goto L_0x00c1
            java.lang.String r2 = "unexpected e_machine:"
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x00c5 }
            if (r2 == 0) goto L_0x00c1
            java.lang.String r2 = "unexpected e_machine:"
            int r2 = r1.lastIndexOf(r2)     // Catch:{ all -> 0x00c5 }
            java.lang.String r1 = r1.substring(r2)     // Catch:{ all -> 0x00c5 }
            com.facebook.soloader.SoLoader$WrongAbiError r2 = new com.facebook.soloader.SoLoader$WrongAbiError     // Catch:{ all -> 0x00c5 }
            r2.<init>(r0, r1)     // Catch:{ all -> 0x00c5 }
            throw r2     // Catch:{ all -> 0x00c5 }
        L_0x00c1:
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x00c2:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r0 = move-exception
            goto L_0x014d
        L_0x00c8:
            r0 = r0 & 16
            if (r0 != 0) goto L_0x0142
            boolean r0 = android.text.TextUtils.isEmpty(r16)     // Catch:{ all -> 0x00c5 }
            if (r0 != 0) goto L_0x00db
            java.util.Set<java.lang.String> r0 = sLoadedAndMergedLibraries     // Catch:{ all -> 0x00c5 }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x00db
            r8 = r11
        L_0x00db:
            if (r17 == 0) goto L_0x0142
            if (r8 != 0) goto L_0x0142
            boolean r0 = SYSTRACE_LIBRARY_LOADING     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x00ea
            java.lang.String r3 = "MergedSoMapping.invokeJniOnload["
            java.lang.String r5 = "]"
            com.facebook.soloader.Api18TraceUtils.beginTraceSection(r3, r2, r5)     // Catch:{ all -> 0x00c5 }
        L_0x00ea:
            java.lang.String r3 = "SoLoader"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            r5.<init>(r6)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            java.lang.StringBuilder r5 = r5.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            java.lang.String r6 = " / "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            java.lang.StringBuilder r5 = r5.append(r15)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            java.lang.String r5 = r5.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            com.facebook.soloader.LogUtil.d(r3, r5)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            com.facebook.soloader.MergedSoMapping.invokeJniOnload(r16)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            java.util.Set<java.lang.String> r3 = sLoadedAndMergedLibraries     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            r3.add(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0116 }
            if (r0 == 0) goto L_0x0142
            com.facebook.soloader.Api18TraceUtils.endSection()     // Catch:{ all -> 0x00c5 }
            goto L_0x0142
        L_0x0114:
            r0 = move-exception
            goto L_0x013a
        L_0x0116:
            r0 = move-exception
            java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ all -> 0x0114 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0114 }
            r5.<init>(r4)     // Catch:{ all -> 0x0114 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ all -> 0x0114 }
            java.lang.String r4 = "', which has been merged into '"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ all -> 0x0114 }
            java.lang.StringBuilder r1 = r2.append(r15)     // Catch:{ all -> 0x0114 }
            java.lang.String r2 = "'.  See comment for details."
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0114 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0114 }
            r3.<init>(r1, r0)     // Catch:{ all -> 0x0114 }
            throw r3     // Catch:{ all -> 0x0114 }
        L_0x013a:
            boolean r1 = SYSTRACE_LIBRARY_LOADING     // Catch:{ all -> 0x00c5 }
            if (r1 == 0) goto L_0x0141
            com.facebook.soloader.Api18TraceUtils.endSection()     // Catch:{ all -> 0x00c5 }
        L_0x0141:
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x0142:
            monitor-exit(r12)     // Catch:{ all -> 0x00c5 }
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r7.readLock()
            r0.unlock()
            r0 = r10 ^ 1
            return r0
        L_0x014d:
            monitor-exit(r12)     // Catch:{ all -> 0x00c5 }
            throw r0     // Catch:{ all -> 0x014f }
        L_0x014f:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = sSoSourcesLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r1.readLock()
            r1.unlock()
            throw r0
        L_0x015a:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x015a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SoLoader.loadLibraryBySoNameImpl(java.lang.String, java.lang.String, java.lang.String, int, android.os.StrictMode$ThreadPolicy):boolean");
    }

    public static File unpackLibraryAndDependencies(String str) throws UnsatisfiedLinkError {
        assertInitialized();
        try {
            return unpackLibraryBySoName(System.mapLibraryName(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doLoadLibraryBySoName(String str, int i, @Nullable StrictMode.ThreadPolicy threadPolicy) throws UnsatisfiedLinkError {
        boolean z;
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                reentrantReadWriteLock.readLock().unlock();
                if (threadPolicy == null) {
                    threadPolicy = StrictMode.allowThreadDiskReads();
                    z = true;
                } else {
                    z = false;
                }
                if (SYSTRACE_LIBRARY_LOADING) {
                    Api18TraceUtils.beginTraceSection("SoLoader.loadLibrary[", str, "]");
                }
                try {
                    reentrantReadWriteLock.readLock().lock();
                    for (SoSource loadLibrary : sSoSources) {
                        if (loadLibrary.loadLibrary(str, i, threadPolicy) != 0) {
                            if (SYSTRACE_LIBRARY_LOADING) {
                                Api18TraceUtils.endSection();
                            }
                            if (z) {
                                StrictMode.setThreadPolicy(threadPolicy);
                                return;
                            }
                            return;
                        }
                    }
                    throw SoLoaderDSONotFoundError.create(str, sApplicationContext, sSoSources);
                } catch (IOException e) {
                    SoLoaderULError soLoaderULError = new SoLoaderULError(str, e.toString());
                    soLoaderULError.initCause(e);
                    throw soLoaderULError;
                } catch (Throwable th) {
                    if (SYSTRACE_LIBRARY_LOADING) {
                        Api18TraceUtils.endSection();
                    }
                    if (z) {
                        StrictMode.setThreadPolicy(threadPolicy);
                    }
                    throw th;
                }
            } else {
                LogUtil.e(TAG, "Could not load: " + str + " because SoLoader is not initialized");
                throw new UnsatisfiedLinkError("SoLoader not initialized, couldn't find DSO to load: " + str);
            }
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    static File unpackLibraryBySoName(String str) throws IOException {
        sSoSourcesLock.readLock().lock();
        try {
            for (SoSource unpackLibrary : sSoSources) {
                File unpackLibrary2 = unpackLibrary.unpackLibrary(str);
                if (unpackLibrary2 != null) {
                    return unpackLibrary2;
                }
            }
            sSoSourcesLock.readLock().unlock();
            throw new FileNotFoundException(str);
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    private static void assertInitialized() {
        if (!isInitialized()) {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
    }

    public static boolean isInitialized() {
        boolean z = true;
        if (sSoSources != null) {
            return true;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources == null) {
                z = false;
            }
            reentrantReadWriteLock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static int getSoSourcesVersion() {
        return sSoSourcesVersion.get();
    }

    public static void prependSoSource(SoSource soSource) throws IOException {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.writeLock().lock();
        try {
            assertInitialized();
            soSource.prepare(makePrepareFlags());
            SoSource[] soSourceArr = new SoSource[(sSoSources.length + 1)];
            soSourceArr[0] = soSource;
            System.arraycopy(sSoSources, 0, soSourceArr, 1, sSoSources.length);
            sSoSources = soSourceArr;
            sSoSourcesVersion.getAndIncrement();
            LogUtil.d(TAG, "Prepended to SO sources: " + soSource);
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            sSoSourcesLock.writeLock().unlock();
            throw th;
        }
    }

    public static String makeLdLibraryPath() {
        sSoSourcesLock.readLock().lock();
        try {
            assertInitialized();
            ArrayList arrayList = new ArrayList();
            SoSource[] soSourceArr = sSoSources;
            if (soSourceArr != null) {
                for (SoSource addToLdLibraryPath : soSourceArr) {
                    addToLdLibraryPath.addToLdLibraryPath(arrayList);
                }
            }
            String join = TextUtils.join(":", arrayList);
            LogUtil.d(TAG, "makeLdLibraryPath final path: " + join);
            return join;
        } finally {
            sSoSourcesLock.readLock().unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public static boolean areSoSourcesAbisSupported() {
        ReentrantReadWriteLock reentrantReadWriteLock = sSoSourcesLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            if (sSoSources != null) {
                String[] supportedAbis = SysUtil.getSupportedAbis();
                for (SoSource soSourceAbis : sSoSources) {
                    String[] soSourceAbis2 = soSourceAbis.getSoSourceAbis();
                    int length = soSourceAbis2.length;
                    int i = 0;
                    while (i < length) {
                        String str = soSourceAbis2[i];
                        boolean z = false;
                        for (int i2 = 0; i2 < supportedAbis.length && !z; i2++) {
                            z = str.equals(supportedAbis[i2]);
                        }
                        if (!z) {
                            LogUtil.e(TAG, "abi not supported: " + str);
                            reentrantReadWriteLock = sSoSourcesLock;
                        } else {
                            i++;
                        }
                    }
                }
                sSoSourcesLock.readLock().unlock();
                return true;
            }
            reentrantReadWriteLock.readLock().unlock();
            return false;
        } catch (Throwable th) {
            sSoSourcesLock.readLock().unlock();
            throw th;
        }
    }

    public static boolean useDepsFile(Context context, boolean z, boolean z2) {
        return NativeDeps.useDepsFile(context, z, z2);
    }

    public static int getLoadedLibrariesCount() {
        return sLoadedLibraries.size();
    }
}
