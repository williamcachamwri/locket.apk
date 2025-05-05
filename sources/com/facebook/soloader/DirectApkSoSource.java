package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class DirectApkSoSource extends SoSource implements RecoverableSoSource {
    private final Map<String, Set<String>> mDepsCache = new HashMap();
    private final Set<String> mDirectApkLdPaths;
    private final Map<String, Set<String>> mLibsInApkCache = new HashMap();

    public String getName() {
        return "DirectApkSoSource";
    }

    public DirectApkSoSource(Context context) {
        this.mDirectApkLdPaths = getDirectApkLdPaths(context);
    }

    public DirectApkSoSource(Set<String> set) {
        this.mDirectApkLdPaths = set;
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader != null) {
            for (String next : this.mDirectApkLdPaths) {
                Set set = this.mLibsInApkCache.get(next);
                if (TextUtils.isEmpty(next) || set == null || !set.contains(str)) {
                    LogUtil.v(SoLoader.TAG, str + " not found on " + next);
                } else {
                    loadDependencies(next, str, i, threadPolicy);
                    try {
                        i |= 4;
                        SoLoader.sSoFileLoader.load(next + File.separator + str, i);
                        LogUtil.d(SoLoader.TAG, str + " found on " + next);
                        return 1;
                    } catch (UnsatisfiedLinkError e) {
                        LogUtil.w(SoLoader.TAG, str + " not found on " + next + " flag: " + i, e);
                    }
                }
            }
            return 0;
        }
        throw new IllegalStateException("SoLoader.init() not yet called");
    }

    public File unpackLibrary(String str) throws IOException {
        throw new UnsupportedOperationException("DirectAPKSoSource doesn't support unpackLibrary");
    }

    public boolean isValid() {
        return !this.mDirectApkLdPaths.isEmpty();
    }

    @Nullable
    public String getLibraryPath(String str) throws IOException {
        for (String next : this.mDirectApkLdPaths) {
            Set set = this.mLibsInApkCache.get(next);
            if (!TextUtils.isEmpty(next) && set != null && set.contains(str)) {
                return next + File.separator + str;
            }
        }
        return null;
    }

    static Set<String> getDirectApkLdPaths(Context context) {
        HashSet hashSet = new HashSet();
        String fallbackApkLdPath = getFallbackApkLdPath(context.getApplicationInfo().sourceDir);
        if (fallbackApkLdPath != null) {
            hashSet.add(fallbackApkLdPath);
        }
        if (context.getApplicationInfo().splitSourceDirs != null) {
            for (String fallbackApkLdPath2 : context.getApplicationInfo().splitSourceDirs) {
                String fallbackApkLdPath3 = getFallbackApkLdPath(fallbackApkLdPath2);
                if (fallbackApkLdPath3 != null) {
                    hashSet.add(fallbackApkLdPath3);
                }
            }
        }
        return hashSet;
    }

    @Nullable
    private static String getFallbackApkLdPath(String str) {
        String[] supportedAbis = SysUtil.getSupportedAbis();
        String str2 = "null";
        if (str == null || str.isEmpty()) {
            if (str != null) {
                str2 = "empty";
            }
            LogUtil.w(SoLoader.TAG, "Cannot compute fallback path, apk path is ".concat(str2));
            return null;
        } else if (supportedAbis != null && supportedAbis.length != 0) {
            return str + "!/lib/" + supportedAbis[0];
        } else {
            if (supportedAbis != null) {
                str2 = "empty";
            }
            LogUtil.w(SoLoader.TAG, "Cannot compute fallback path, supportedAbis is ".concat(str2));
            return null;
        }
    }

    private void loadDependencies(String str, String str2, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        Set<String> depsFromCache = getDepsFromCache(str, str2);
        if (depsFromCache == null) {
            buildLibDepsCache(str, str2);
            depsFromCache = getDepsFromCache(str, str2);
        }
        if (depsFromCache != null) {
            for (String loadDependency : depsFromCache) {
                SoLoader.loadDependency(loadDependency, i, threadPolicy);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepare(int i) throws IOException {
        prepare();
    }

    private void prepare() throws IOException {
        int indexOf;
        int i;
        String str = null;
        for (String next : this.mDirectApkLdPaths) {
            if (!TextUtils.isEmpty(next) && (indexOf = next.indexOf(33)) >= 0 && (i = indexOf + 2) < next.length()) {
                str = next.substring(i);
            }
            if (!TextUtils.isEmpty(str)) {
                ZipFile zipFile = new ZipFile(getApkPathFromLdPath(next));
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                        if (zipEntry != null && zipEntry.getMethod() == 0 && zipEntry.getName().startsWith(str) && zipEntry.getName().endsWith(".so")) {
                            appendLibsInApkCache(next, zipEntry.getName().substring(str.length() + 1));
                        }
                    }
                    zipFile.close();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        return;
        throw th;
    }

    private void buildLibDepsCache(String str, String str2) throws IOException {
        ZipFile zipFile = new ZipFile(getApkPathFromLdPath(str));
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (zipEntry != null && zipEntry.getName().endsWith("/" + str2)) {
                    buildLibDepsCacheImpl(str, zipFile, zipEntry, str2);
                }
            }
            zipFile.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void buildLibDepsCacheImpl(String str, ZipFile zipFile, ZipEntry zipEntry, String str2) throws IOException {
        ElfZipFileChannel elfZipFileChannel = new ElfZipFileChannel(zipFile, zipEntry);
        try {
            for (String str3 : NativeDeps.getDependencies(str2, elfZipFileChannel)) {
                if (!str3.startsWith("/")) {
                    appendDepsCache(str, str2, str3);
                }
            }
            elfZipFileChannel.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String toString() {
        return getName() + "[root = " + this.mDirectApkLdPaths.toString() + AbstractJsonLexerKt.END_LIST;
    }

    private void appendLibsInApkCache(String str, String str2) {
        synchronized (this.mLibsInApkCache) {
            if (!this.mLibsInApkCache.containsKey(str)) {
                this.mLibsInApkCache.put(str, new HashSet());
            }
            this.mLibsInApkCache.get(str).add(str2);
        }
    }

    private void appendDepsCache(String str, String str2, String str3) {
        synchronized (this.mDepsCache) {
            String str4 = str + str2;
            if (!this.mDepsCache.containsKey(str4)) {
                this.mDepsCache.put(str4, new HashSet());
            }
            this.mDepsCache.get(str4).add(str3);
        }
    }

    @Nullable
    private Set<String> getDepsFromCache(String str, String str2) {
        Set<String> set;
        synchronized (this.mDepsCache) {
            set = this.mDepsCache.get(str + str2);
        }
        return set;
    }

    private static String getApkPathFromLdPath(String str) {
        return str.substring(0, str.indexOf(33));
    }

    public SoSource recover(Context context) {
        DirectApkSoSource directApkSoSource = new DirectApkSoSource(context);
        try {
            directApkSoSource.prepare();
            return directApkSoSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
