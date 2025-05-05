package com.tencent.mmkv;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.util.Log;
import com.reactnativemmkv.MmkvModule;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MMKV implements SharedPreferences, SharedPreferences.Editor {
    private static final int ASHMEM_MODE = 8;
    private static final int BACKUP_MODE = 16;
    private static final int CONTEXT_MODE_MULTI_PROCESS = 4;
    public static final int ExpireInDay = 86400;
    public static final int ExpireInHour = 3600;
    public static final int ExpireInMinute = 60;
    public static final int ExpireInMonth = 2592000;
    public static final int ExpireInYear = 946080000;
    public static final int ExpireNever = 0;
    public static final int MULTI_PROCESS_MODE = 2;
    public static final int SINGLE_PROCESS_MODE = 1;
    private static final Set<Long> checkedHandleSet = new HashSet();
    private static MMKVHandler gCallbackHandler;
    private static MMKVContentChangeNotification gContentChangeNotify;
    private static boolean gWantLogReDirecting = false;
    private static final MMKVLogLevel[] index2LogLevel = {MMKVLogLevel.LevelDebug, MMKVLogLevel.LevelInfo, MMKVLogLevel.LevelWarning, MMKVLogLevel.LevelError, MMKVLogLevel.LevelNone};
    private static boolean isProcessModeCheckerEnabled = true;
    private static final EnumMap<MMKVLogLevel, Integer> logLevel2Index;
    private static final HashMap<String, Parcelable.Creator<?>> mCreators = new HashMap<>();
    private static final EnumMap<MMKVRecoverStrategic, Integer> recoverIndex;
    private static String rootDir = null;
    private final long nativeHandle;

    public interface LibLoader {
        void loadLibrary(String str);
    }

    private native long actualSize(long j);

    private native String[] allKeys(long j, boolean z);

    public static native long backupAllToDirectory(String str);

    public static native boolean backupOneToDirectory(String str, String str2, String str3);

    private static native boolean checkProcessMode(long j);

    private native boolean containsKey(long j, String str);

    private native long count(long j, boolean z);

    private static native long createNB(int i);

    private native boolean decodeBool(long j, String str, boolean z);

    private native byte[] decodeBytes(long j, String str);

    private native double decodeDouble(long j, String str, double d);

    private native float decodeFloat(long j, String str, float f);

    private native int decodeInt(long j, String str, int i);

    private native long decodeLong(long j, String str, long j2);

    private native String decodeString(long j, String str, String str2);

    private native String[] decodeStringSet(long j, String str);

    private static native void destroyNB(long j, int i);

    private native boolean encodeBool(long j, String str, boolean z);

    private native boolean encodeBool_2(long j, String str, boolean z, int i);

    private native boolean encodeBytes(long j, String str, byte[] bArr);

    private native boolean encodeBytes_2(long j, String str, byte[] bArr, int i);

    private native boolean encodeDouble(long j, String str, double d);

    private native boolean encodeDouble_2(long j, String str, double d, int i);

    private native boolean encodeFloat(long j, String str, float f);

    private native boolean encodeFloat_2(long j, String str, float f, int i);

    private native boolean encodeInt(long j, String str, int i);

    private native boolean encodeInt_2(long j, String str, int i, int i2);

    private native boolean encodeLong(long j, String str, long j2);

    private native boolean encodeLong_2(long j, String str, long j2, int i);

    private native boolean encodeSet(long j, String str, String[] strArr);

    private native boolean encodeSet_2(long j, String str, String[] strArr, int i);

    private native boolean encodeString(long j, String str, String str2);

    private native boolean encodeString_2(long j, String str, String str2, int i);

    private static native long getDefaultMMKV(int i, String str);

    private static native long getMMKVWithAshmemFD(String str, int i, int i2, String str2);

    private static native long getMMKVWithID(String str, int i, String str2, String str3, long j);

    private static native long getMMKVWithIDAndSize(String str, int i, int i2, String str2);

    private native boolean isCompareBeforeSetEnabled();

    private native boolean isEncryptionEnabled();

    private native boolean isExpirationEnabled();

    public static native boolean isFileValid(String str, String str2);

    private static native void jniInitialize(String str, String str2, int i, boolean z);

    private native void nativeEnableCompareBeforeSet();

    public static native void onExit();

    public static native int pageSize();

    public static native boolean removeStorage(String str, String str2);

    private native void removeValueForKey(long j, String str);

    public static native long restoreAllFromDirectory(String str);

    public static native boolean restoreOneMMKVFromDirectory(String str, String str2, String str3);

    private static native void setCallbackHandler(boolean z, boolean z2);

    private static native void setLogLevel(int i);

    private static native void setWantsContentChangeNotify(boolean z);

    private native void sync(boolean z);

    private native long totalSize(long j);

    private native int valueSize(long j, String str, boolean z);

    public static native String version();

    private native int writeValueToNB(long j, String str, long j2, int i);

    public native int ashmemFD();

    public native int ashmemMetaFD();

    public native void checkContentChangedByOuterProcess();

    public native void checkReSetCryptKey(String str);

    public native void clearAll();

    public native void clearAllWithKeepingSpace();

    public native void clearMemoryCache();

    public native void close();

    public native String cryptKey();

    public native boolean disableAutoKeyExpire();

    public native void disableCompareBeforeSet();

    public SharedPreferences.Editor edit() {
        return this;
    }

    public native boolean enableAutoKeyExpire(int i);

    public native void lock();

    public native String mmapID();

    public native boolean reKey(String str);

    public native void removeValuesForKeys(String[] strArr);

    public native void trim();

    public native boolean tryLock();

    public native void unlock();

    static {
        EnumMap<MMKVRecoverStrategic, Integer> enumMap = new EnumMap<>(MMKVRecoverStrategic.class);
        recoverIndex = enumMap;
        enumMap.put(MMKVRecoverStrategic.OnErrorDiscard, 0);
        enumMap.put(MMKVRecoverStrategic.OnErrorRecover, 1);
        EnumMap<MMKVLogLevel, Integer> enumMap2 = new EnumMap<>(MMKVLogLevel.class);
        logLevel2Index = enumMap2;
        enumMap2.put(MMKVLogLevel.LevelDebug, 0);
        enumMap2.put(MMKVLogLevel.LevelInfo, 1);
        enumMap2.put(MMKVLogLevel.LevelWarning, 2);
        enumMap2.put(MMKVLogLevel.LevelError, 3);
        enumMap2.put(MMKVLogLevel.LevelNone, 4);
    }

    public static String initialize(Context context) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", (LibLoader) null, MMKVLogLevel.LevelInfo, (MMKVHandler) null);
    }

    public static String initialize(Context context, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", (LibLoader) null, mMKVLogLevel, (MMKVHandler) null);
    }

    public static String initialize(Context context, LibLoader libLoader) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", libLoader, MMKVLogLevel.LevelInfo, (MMKVHandler) null);
    }

    public static String initialize(Context context, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, context.getFilesDir().getAbsolutePath() + "/mmkv", libLoader, mMKVLogLevel, (MMKVHandler) null);
    }

    public static String initialize(Context context, String str) {
        return initialize(context, str, (LibLoader) null, MMKVLogLevel.LevelInfo, (MMKVHandler) null);
    }

    public static String initialize(Context context, String str, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, str, (LibLoader) null, mMKVLogLevel, (MMKVHandler) null);
    }

    public static String initialize(Context context, String str, LibLoader libLoader) {
        return initialize(context, str, libLoader, MMKVLogLevel.LevelInfo, (MMKVHandler) null);
    }

    public static String initialize(Context context, String str, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        return initialize(context, str, libLoader, mMKVLogLevel, (MMKVHandler) null);
    }

    public static String initialize(Context context, String str, LibLoader libLoader, MMKVLogLevel mMKVLogLevel, MMKVHandler mMKVHandler) {
        if ((context.getApplicationInfo().flags & 2) == 0) {
            disableProcessModeChecker();
        } else {
            enableProcessModeChecker();
        }
        String absolutePath = context.getCacheDir().getAbsolutePath();
        gCallbackHandler = mMKVHandler;
        if (mMKVHandler != null && mMKVHandler.wantLogRedirecting()) {
            gWantLogReDirecting = true;
        }
        String doInitialize = doInitialize(str, absolutePath, libLoader, mMKVLogLevel, gWantLogReDirecting);
        if (gCallbackHandler != null) {
            setCallbackHandler(gWantLogReDirecting, true);
        }
        return doInitialize;
    }

    private static String doInitialize(String str, String str2, LibLoader libLoader, MMKVLogLevel mMKVLogLevel, boolean z) {
        if (libLoader != null) {
            libLoader.loadLibrary("mmkv");
        } else {
            System.loadLibrary("mmkv");
        }
        jniInitialize(str, str2, logLevel2Int(mMKVLogLevel), z);
        rootDir = str;
        return str;
    }

    @Deprecated
    public static String initialize(String str) {
        return doInitialize(str, str + "/.tmp", (LibLoader) null, MMKVLogLevel.LevelInfo, false);
    }

    @Deprecated
    public static String initialize(String str, MMKVLogLevel mMKVLogLevel) {
        return doInitialize(str, str + "/.tmp", (LibLoader) null, mMKVLogLevel, false);
    }

    @Deprecated
    public static String initialize(String str, LibLoader libLoader) {
        return doInitialize(str, str + "/.tmp", libLoader, MMKVLogLevel.LevelInfo, false);
    }

    @Deprecated
    public static String initialize(String str, LibLoader libLoader, MMKVLogLevel mMKVLogLevel) {
        return doInitialize(str, str + "/.tmp", libLoader, mMKVLogLevel, false);
    }

    public static String getRootDir() {
        return rootDir;
    }

    /* renamed from: com.tencent.mmkv.MMKV$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$mmkv$MMKVLogLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tencent.mmkv.MMKVLogLevel[] r0 = com.tencent.mmkv.MMKVLogLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$tencent$mmkv$MMKVLogLevel = r0
                com.tencent.mmkv.MMKVLogLevel r1 = com.tencent.mmkv.MMKVLogLevel.LevelDebug     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$tencent$mmkv$MMKVLogLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tencent.mmkv.MMKVLogLevel r1 = com.tencent.mmkv.MMKVLogLevel.LevelWarning     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$tencent$mmkv$MMKVLogLevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tencent.mmkv.MMKVLogLevel r1 = com.tencent.mmkv.MMKVLogLevel.LevelError     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$tencent$mmkv$MMKVLogLevel     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tencent.mmkv.MMKVLogLevel r1 = com.tencent.mmkv.MMKVLogLevel.LevelNone     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$tencent$mmkv$MMKVLogLevel     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tencent.mmkv.MMKVLogLevel r1 = com.tencent.mmkv.MMKVLogLevel.LevelInfo     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mmkv.MMKV.AnonymousClass1.<clinit>():void");
        }
    }

    private static int logLevel2Int(MMKVLogLevel mMKVLogLevel) {
        int i = AnonymousClass1.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[mMKVLogLevel.ordinal()];
        if (i == 1) {
            return 0;
        }
        int i2 = 2;
        if (i != 2) {
            i2 = 3;
            if (i != 3) {
                i2 = 4;
                if (i != 4) {
                    return 1;
                }
            }
        }
        return i2;
    }

    public static void setLogLevel(MMKVLogLevel mMKVLogLevel) {
        setLogLevel(logLevel2Int(mMKVLogLevel));
    }

    public static MMKV mmkvWithID(String str) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, 1, (String) null, (String) null, 0), str, 1);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, int i) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, i, (String) null, (String) null, 0), str, i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, int i, long j) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, i, (String) null, (String) null, j), str, i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, int i, String str2) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, i, str2, (String) null, 0), str, i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, String str2) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, 1, (String) null, str2, 0), str, 1);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, String str2, long j) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, 1, (String) null, str2, j), str, 1);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, int i, String str2, String str3, long j) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, i, str2, str3, j), str, i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithID(String str, int i, String str2, String str3) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getMMKVWithID(str, i, str2, str3, 0), str, i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV backedUpMMKVWithID(String str, int i, String str2, String str3) throws RuntimeException {
        if (rootDir != null) {
            int i2 = i | 16;
            return checkProcessMode(getMMKVWithID(str, i2, str2, str3, 0), str, i2);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV mmkvWithAshmemID(Context context, String str, int i, int i2, String str2) throws RuntimeException {
        MMKV mmkv;
        if (rootDir != null) {
            String processNameByPID = MMKVContentProvider.getProcessNameByPID(context, Process.myPid());
            if (processNameByPID == null || processNameByPID.length() == 0) {
                simpleLog(MMKVLogLevel.LevelError, "process name detect fail, try again later");
                throw new IllegalStateException("process name detect fail, try again later");
            }
            if (processNameByPID.contains(":")) {
                Uri contentUri = MMKVContentProvider.contentUri(context);
                if (contentUri != null) {
                    simpleLog(MMKVLogLevel.LevelInfo, "getting parcelable mmkv in process, Uri = " + contentUri);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SIZE", i);
                    bundle.putInt("KEY_MODE", i2);
                    if (str2 != null) {
                        bundle.putString("KEY_CRYPT", str2);
                    }
                    Bundle call = context.getContentResolver().call(contentUri, "mmkvFromAshmemID", str, bundle);
                    if (call != null) {
                        call.setClassLoader(ParcelableMMKV.class.getClassLoader());
                        ParcelableMMKV parcelableMMKV = (ParcelableMMKV) call.getParcelable("KEY");
                        if (!(parcelableMMKV == null || (mmkv = parcelableMMKV.toMMKV()) == null)) {
                            simpleLog(MMKVLogLevel.LevelInfo, mmkv.mmapID() + " fd = " + mmkv.ashmemFD() + ", meta fd = " + mmkv.ashmemMetaFD());
                            return mmkv;
                        }
                    }
                } else {
                    simpleLog(MMKVLogLevel.LevelError, "MMKVContentProvider has invalid authority");
                    throw new IllegalStateException("MMKVContentProvider has invalid authority");
                }
            }
            simpleLog(MMKVLogLevel.LevelInfo, "getting mmkv in main process");
            long mMKVWithIDAndSize = getMMKVWithIDAndSize(str, i, i2 | 8, str2);
            if (mMKVWithIDAndSize != 0) {
                return new MMKV(mMKVWithIDAndSize);
            }
            throw new IllegalStateException("Fail to create an Ashmem MMKV instance [" + str + "]");
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV defaultMMKV() throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getDefaultMMKV(1, (String) null), "DefaultMMKV", 1);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    public static MMKV defaultMMKV(int i, String str) throws RuntimeException {
        if (rootDir != null) {
            return checkProcessMode(getDefaultMMKV(i, str), "DefaultMMKV", i);
        }
        throw new IllegalStateException("You should Call MMKV.initialize() first.");
    }

    private static MMKV checkProcessMode(long j, String str, int i) throws RuntimeException {
        String str2;
        if (j == 0) {
            throw new RuntimeException("Fail to create an MMKV instance [" + str + "] in JNI");
        } else if (!isProcessModeCheckerEnabled) {
            return new MMKV(j);
        } else {
            Set<Long> set = checkedHandleSet;
            synchronized (set) {
                if (!set.contains(Long.valueOf(j))) {
                    if (!checkProcessMode(j)) {
                        if (i == 1) {
                            str2 = "Opening a multi-process MMKV instance [" + str + "] with SINGLE_PROCESS_MODE!";
                        } else {
                            str2 = ("Opening an MMKV instance [" + str + "] with MULTI_PROCESS_MODE, ") + "while it's already been opened with SINGLE_PROCESS_MODE by someone somewhere else!";
                        }
                        throw new IllegalArgumentException(str2);
                    }
                    set.add(Long.valueOf(j));
                }
            }
            return new MMKV(j);
        }
    }

    public static void enableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = true;
        }
        Log.i(MmkvModule.NAME, "Enable checkProcessMode()");
    }

    public static void disableProcessModeChecker() {
        synchronized (checkedHandleSet) {
            isProcessModeCheckerEnabled = false;
        }
        Log.i(MmkvModule.NAME, "Disable checkProcessMode()");
    }

    public boolean encode(String str, boolean z) {
        return encodeBool(this.nativeHandle, str, z);
    }

    public boolean encode(String str, boolean z, int i) {
        return encodeBool_2(this.nativeHandle, str, z, i);
    }

    public boolean decodeBool(String str) {
        return decodeBool(this.nativeHandle, str, false);
    }

    public boolean decodeBool(String str, boolean z) {
        return decodeBool(this.nativeHandle, str, z);
    }

    public boolean encode(String str, int i) {
        return encodeInt(this.nativeHandle, str, i);
    }

    public boolean encode(String str, int i, int i2) {
        return encodeInt_2(this.nativeHandle, str, i, i2);
    }

    public int decodeInt(String str) {
        return decodeInt(this.nativeHandle, str, 0);
    }

    public int decodeInt(String str, int i) {
        return decodeInt(this.nativeHandle, str, i);
    }

    public boolean encode(String str, long j) {
        return encodeLong(this.nativeHandle, str, j);
    }

    public boolean encode(String str, long j, int i) {
        return encodeLong_2(this.nativeHandle, str, j, i);
    }

    public long decodeLong(String str) {
        return decodeLong(this.nativeHandle, str, 0);
    }

    public long decodeLong(String str, long j) {
        return decodeLong(this.nativeHandle, str, j);
    }

    public boolean encode(String str, float f) {
        return encodeFloat(this.nativeHandle, str, f);
    }

    public boolean encode(String str, float f, int i) {
        return encodeFloat_2(this.nativeHandle, str, f, i);
    }

    public float decodeFloat(String str) {
        return decodeFloat(this.nativeHandle, str, 0.0f);
    }

    public float decodeFloat(String str, float f) {
        return decodeFloat(this.nativeHandle, str, f);
    }

    public boolean encode(String str, double d) {
        return encodeDouble(this.nativeHandle, str, d);
    }

    public boolean encode(String str, double d, int i) {
        return encodeDouble_2(this.nativeHandle, str, d, i);
    }

    public double decodeDouble(String str) {
        return decodeDouble(this.nativeHandle, str, 0.0d);
    }

    public double decodeDouble(String str, double d) {
        return decodeDouble(this.nativeHandle, str, d);
    }

    public boolean encode(String str, String str2) {
        return encodeString(this.nativeHandle, str, str2);
    }

    public boolean encode(String str, String str2, int i) {
        return encodeString_2(this.nativeHandle, str, str2, i);
    }

    public String decodeString(String str) {
        return decodeString(this.nativeHandle, str, (String) null);
    }

    public String decodeString(String str, String str2) {
        return decodeString(this.nativeHandle, str, str2);
    }

    public boolean encode(String str, Set<String> set) {
        return encodeSet(this.nativeHandle, str, set == null ? null : (String[]) set.toArray(new String[0]));
    }

    public boolean encode(String str, Set<String> set, int i) {
        return encodeSet_2(this.nativeHandle, str, set == null ? null : (String[]) set.toArray(new String[0]), i);
    }

    public Set<String> decodeStringSet(String str) {
        return decodeStringSet(str, (Set<String>) null);
    }

    public Set<String> decodeStringSet(String str, Set<String> set) {
        return decodeStringSet(str, set, HashSet.class);
    }

    public Set<String> decodeStringSet(String str, Set<String> set, Class<? extends Set> cls) {
        String[] decodeStringSet = decodeStringSet(this.nativeHandle, str);
        if (decodeStringSet == null) {
            return set;
        }
        try {
            Set<String> set2 = (Set) cls.newInstance();
            set2.addAll(Arrays.asList(decodeStringSet));
            return set2;
        } catch (IllegalAccessException | InstantiationException unused) {
            return set;
        }
    }

    public boolean encode(String str, byte[] bArr) {
        return encodeBytes(this.nativeHandle, str, bArr);
    }

    public boolean encode(String str, byte[] bArr, int i) {
        return encodeBytes_2(this.nativeHandle, str, bArr, i);
    }

    public byte[] decodeBytes(String str) {
        return decodeBytes(str, (byte[]) null);
    }

    public byte[] decodeBytes(String str, byte[] bArr) {
        byte[] decodeBytes = decodeBytes(this.nativeHandle, str);
        return decodeBytes != null ? decodeBytes : bArr;
    }

    private byte[] getParcelableByte(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public boolean encode(String str, Parcelable parcelable) {
        if (parcelable == null) {
            return encodeBytes(this.nativeHandle, str, (byte[]) null);
        }
        return encodeBytes(this.nativeHandle, str, getParcelableByte(parcelable));
    }

    public boolean encode(String str, Parcelable parcelable, int i) {
        if (parcelable == null) {
            return encodeBytes_2(this.nativeHandle, str, (byte[]) null, i);
        }
        return encodeBytes_2(this.nativeHandle, str, getParcelableByte(parcelable), i);
    }

    public <T extends Parcelable> T decodeParcelable(String str, Class<T> cls) {
        return decodeParcelable(str, cls, (Parcelable) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: android.os.Parcelable$Creator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends android.os.Parcelable> T decodeParcelable(java.lang.String r5, java.lang.Class<T> r6, T r7) {
        /*
            r4 = this;
            java.lang.String r0 = "Parcelable protocol requires a non-null static Parcelable.Creator object called CREATOR on class "
            if (r6 != 0) goto L_0x0005
            return r7
        L_0x0005:
            long r1 = r4.nativeHandle
            byte[] r5 = r4.decodeBytes((long) r1, (java.lang.String) r5)
            if (r5 != 0) goto L_0x000e
            return r7
        L_0x000e:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length
            r3 = 0
            r1.unmarshall(r5, r3, r2)
            r1.setDataPosition(r3)
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x0061 }
            java.util.HashMap<java.lang.String, android.os.Parcelable$Creator<?>> r2 = mCreators     // Catch:{ Exception -> 0x0061 }
            monitor-enter(r2)     // Catch:{ Exception -> 0x0061 }
            java.lang.Object r3 = r2.get(r5)     // Catch:{ all -> 0x005c }
            android.os.Parcelable$Creator r3 = (android.os.Parcelable.Creator) r3     // Catch:{ all -> 0x005c }
            if (r3 != 0) goto L_0x003c
            java.lang.String r3 = "CREATOR"
            java.lang.reflect.Field r6 = r6.getField(r3)     // Catch:{ all -> 0x005c }
            r3 = 0
            java.lang.Object r6 = r6.get(r3)     // Catch:{ all -> 0x005c }
            r3 = r6
            android.os.Parcelable$Creator r3 = (android.os.Parcelable.Creator) r3     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x003c
            r2.put(r5, r3)     // Catch:{ all -> 0x005c }
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x0049
            java.lang.Object r5 = r3.createFromParcel(r1)     // Catch:{ Exception -> 0x0061 }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ Exception -> 0x0061 }
            r1.recycle()
            return r5
        L_0x0049:
            java.lang.Exception r6 = new java.lang.Exception     // Catch:{ Exception -> 0x0061 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0061 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0061 }
            java.lang.StringBuilder r5 = r2.append(r5)     // Catch:{ Exception -> 0x0061 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0061 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0061 }
            throw r6     // Catch:{ Exception -> 0x0061 }
        L_0x005c:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005c }
            throw r5     // Catch:{ Exception -> 0x0061 }
        L_0x005f:
            r5 = move-exception
            goto L_0x006f
        L_0x0061:
            r5 = move-exception
            com.tencent.mmkv.MMKVLogLevel r6 = com.tencent.mmkv.MMKVLogLevel.LevelError     // Catch:{ all -> 0x005f }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x005f }
            simpleLog(r6, r5)     // Catch:{ all -> 0x005f }
            r1.recycle()
            return r7
        L_0x006f:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mmkv.MMKV.decodeParcelable(java.lang.String, java.lang.Class, android.os.Parcelable):android.os.Parcelable");
    }

    public int getValueSize(String str) {
        return valueSize(this.nativeHandle, str, false);
    }

    public int getValueActualSize(String str) {
        return valueSize(this.nativeHandle, str, true);
    }

    public boolean containsKey(String str) {
        return containsKey(this.nativeHandle, str);
    }

    public String[] allKeys() {
        return allKeys(this.nativeHandle, false);
    }

    public String[] allNonExpireKeys() {
        return allKeys(this.nativeHandle, true);
    }

    public long count() {
        return count(this.nativeHandle, false);
    }

    public long countNonExpiredKeys() {
        return count(this.nativeHandle, true);
    }

    public long totalSize() {
        return totalSize(this.nativeHandle);
    }

    public long actualSize() {
        return actualSize(this.nativeHandle);
    }

    public void removeValueForKey(String str) {
        removeValueForKey(this.nativeHandle, str);
    }

    public void sync() {
        sync(true);
    }

    public void async() {
        sync(false);
    }

    public static boolean isFileValid(String str) {
        return isFileValid(str, (String) null);
    }

    public static boolean removeStorage(String str) {
        return removeStorage(str, (String) null);
    }

    public int importFromSharedPreferences(SharedPreferences sharedPreferences) {
        Map<String, ?> all = sharedPreferences.getAll();
        if (all == null || all.size() <= 0) {
            return 0;
        }
        for (Map.Entry next : all.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (!(str == null || value == null)) {
                if (value instanceof Boolean) {
                    encodeBool(this.nativeHandle, str, ((Boolean) value).booleanValue());
                } else if (value instanceof Integer) {
                    encodeInt(this.nativeHandle, str, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    encodeLong(this.nativeHandle, str, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    encodeFloat(this.nativeHandle, str, ((Float) value).floatValue());
                } else if (value instanceof Double) {
                    encodeDouble(this.nativeHandle, str, ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    encodeString(this.nativeHandle, str, (String) value);
                } else if (value instanceof Set) {
                    encode(str, (Set<String>) (Set) value);
                } else {
                    simpleLog(MMKVLogLevel.LevelError, "unknown type: " + value.getClass());
                }
            }
        }
        return all.size();
    }

    public void enableCompareBeforeSet() {
        if (isExpirationEnabled()) {
            SentryLogcatAdapter.e(MmkvModule.NAME, "enableCompareBeforeSet is invalid when Expiration is on");
        }
        if (isEncryptionEnabled()) {
            SentryLogcatAdapter.e(MmkvModule.NAME, "enableCompareBeforeSet is invalid when key encryption is on");
        }
        nativeEnableCompareBeforeSet();
    }

    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException("Intentionally Not Supported. Use allKeys() instead, getAll() not implement because type-erasure inside mmkv");
    }

    public String getString(String str, String str2) {
        return decodeString(this.nativeHandle, str, str2);
    }

    public SharedPreferences.Editor putString(String str, String str2) {
        encodeString(this.nativeHandle, str, str2);
        return this;
    }

    public SharedPreferences.Editor putString(String str, String str2, int i) {
        encodeString_2(this.nativeHandle, str, str2, i);
        return this;
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return decodeStringSet(str, set);
    }

    public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
        encode(str, set);
        return this;
    }

    public SharedPreferences.Editor putStringSet(String str, Set<String> set, int i) {
        encode(str, set, i);
        return this;
    }

    public SharedPreferences.Editor putBytes(String str, byte[] bArr) {
        encode(str, bArr);
        return this;
    }

    public SharedPreferences.Editor putBytes(String str, byte[] bArr, int i) {
        encode(str, bArr, i);
        return this;
    }

    public byte[] getBytes(String str, byte[] bArr) {
        return decodeBytes(str, bArr);
    }

    public int getInt(String str, int i) {
        return decodeInt(this.nativeHandle, str, i);
    }

    public SharedPreferences.Editor putInt(String str, int i) {
        encodeInt(this.nativeHandle, str, i);
        return this;
    }

    public SharedPreferences.Editor putInt(String str, int i, int i2) {
        encodeInt_2(this.nativeHandle, str, i, i2);
        return this;
    }

    public long getLong(String str, long j) {
        return decodeLong(this.nativeHandle, str, j);
    }

    public SharedPreferences.Editor putLong(String str, long j) {
        encodeLong(this.nativeHandle, str, j);
        return this;
    }

    public SharedPreferences.Editor putLong(String str, long j, int i) {
        encodeLong_2(this.nativeHandle, str, j, i);
        return this;
    }

    public float getFloat(String str, float f) {
        return decodeFloat(this.nativeHandle, str, f);
    }

    public SharedPreferences.Editor putFloat(String str, float f) {
        encodeFloat(this.nativeHandle, str, f);
        return this;
    }

    public SharedPreferences.Editor putFloat(String str, float f, int i) {
        encodeFloat_2(this.nativeHandle, str, f, i);
        return this;
    }

    public boolean getBoolean(String str, boolean z) {
        return decodeBool(this.nativeHandle, str, z);
    }

    public SharedPreferences.Editor putBoolean(String str, boolean z) {
        encodeBool(this.nativeHandle, str, z);
        return this;
    }

    public SharedPreferences.Editor putBoolean(String str, boolean z, int i) {
        encodeBool_2(this.nativeHandle, str, z, i);
        return this;
    }

    public SharedPreferences.Editor remove(String str) {
        removeValueForKey(str);
        return this;
    }

    public SharedPreferences.Editor clear() {
        clearAll();
        return this;
    }

    @Deprecated
    public boolean commit() {
        sync(true);
        return true;
    }

    @Deprecated
    public void apply() {
        sync(false);
    }

    public boolean contains(String str) {
        return containsKey(str);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        throw new UnsupportedOperationException("Intentionally Not implement in MMKV");
    }

    public static MMKV mmkvWithAshmemFD(String str, int i, int i2, String str2) throws RuntimeException {
        long mMKVWithAshmemFD = getMMKVWithAshmemFD(str, i, i2, str2);
        if (mMKVWithAshmemFD != 0) {
            return new MMKV(mMKVWithAshmemFD);
        }
        throw new RuntimeException("Fail to create an ashmem MMKV instance [" + str + "] in JNI");
    }

    public static NativeBuffer createNativeBuffer(int i) {
        long createNB = createNB(i);
        if (createNB <= 0) {
            return null;
        }
        return new NativeBuffer(createNB, i);
    }

    public static void destroyNativeBuffer(NativeBuffer nativeBuffer) {
        destroyNB(nativeBuffer.pointer, nativeBuffer.size);
    }

    public int writeValueToNativeBuffer(String str, NativeBuffer nativeBuffer) {
        return writeValueToNB(this.nativeHandle, str, nativeBuffer.pointer, nativeBuffer.size);
    }

    public static void registerHandler(MMKVHandler mMKVHandler) {
        gCallbackHandler = mMKVHandler;
        boolean wantLogRedirecting = mMKVHandler.wantLogRedirecting();
        gWantLogReDirecting = wantLogRedirecting;
        setCallbackHandler(wantLogRedirecting, true);
    }

    public static void unregisterHandler() {
        gCallbackHandler = null;
        setCallbackHandler(false, false);
        gWantLogReDirecting = false;
    }

    private static int onMMKVCRCCheckFail(String str) {
        MMKVRecoverStrategic mMKVRecoverStrategic = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategic = mMKVHandler.onMMKVCRCCheckFail(str);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + str + " is " + mMKVRecoverStrategic);
        Integer num = recoverIndex.get(mMKVRecoverStrategic);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static int onMMKVFileLengthError(String str) {
        MMKVRecoverStrategic mMKVRecoverStrategic = MMKVRecoverStrategic.OnErrorDiscard;
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler != null) {
            mMKVRecoverStrategic = mMKVHandler.onMMKVFileLengthError(str);
        }
        simpleLog(MMKVLogLevel.LevelInfo, "Recover strategic for " + str + " is " + mMKVRecoverStrategic);
        Integer num = recoverIndex.get(mMKVRecoverStrategic);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static void mmkvLogImp(int i, String str, int i2, String str2, String str3) {
        MMKVHandler mMKVHandler = gCallbackHandler;
        if (mMKVHandler == null || !gWantLogReDirecting) {
            int i3 = AnonymousClass1.$SwitchMap$com$tencent$mmkv$MMKVLogLevel[index2LogLevel[i].ordinal()];
            if (i3 == 1) {
                Log.d(MmkvModule.NAME, str3);
            } else if (i3 == 2) {
                SentryLogcatAdapter.w(MmkvModule.NAME, str3);
            } else if (i3 == 3) {
                SentryLogcatAdapter.e(MmkvModule.NAME, str3);
            } else if (i3 == 5) {
                Log.i(MmkvModule.NAME, str3);
            }
        } else {
            mMKVHandler.mmkvLog(index2LogLevel[i], str, i2, str2, str3);
        }
    }

    private static void simpleLog(MMKVLogLevel mMKVLogLevel, String str) {
        int i;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
        Integer num = logLevel2Index.get(mMKVLogLevel);
        if (num == null) {
            i = 0;
        } else {
            i = num.intValue();
        }
        mmkvLogImp(i, stackTraceElement.getFileName(), stackTraceElement.getLineNumber(), stackTraceElement.getMethodName(), str);
    }

    public static void registerContentChangeNotify(MMKVContentChangeNotification mMKVContentChangeNotification) {
        gContentChangeNotify = mMKVContentChangeNotification;
        setWantsContentChangeNotify(mMKVContentChangeNotification != null);
    }

    public static void unregisterContentChangeNotify() {
        gContentChangeNotify = null;
        setWantsContentChangeNotify(false);
    }

    private static void onContentChangedByOuterProcess(String str) {
        MMKVContentChangeNotification mMKVContentChangeNotification = gContentChangeNotify;
        if (mMKVContentChangeNotification != null) {
            mMKVContentChangeNotification.onContentChangedByOuterProcess(str);
        }
    }

    private MMKV(long j) {
        this.nativeHandle = j;
    }
}
