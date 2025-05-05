package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class DirectorySoSource extends SoSource {
    public static final int ON_LD_LIBRARY_PATH = 2;
    public static final int RESOLVE_DEPENDENCIES = 1;
    protected final List<String> denyList;
    protected final int flags;
    protected final File soDirectory;

    public String getName() {
        return "DirectorySoSource";
    }

    public DirectorySoSource(File file, int i) {
        this(file, i, new String[0]);
    }

    public DirectorySoSource(File file, int i, String[] strArr) {
        this.soDirectory = file;
        this.flags = i;
        this.denyList = Arrays.asList(strArr);
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
    }

    /* access modifiers changed from: protected */
    public int loadLibraryFrom(String str, int i, File file, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        if (SoLoader.sSoFileLoader != null) {
            boolean z = false;
            if (this.denyList.contains(str)) {
                LogUtil.d(SoLoader.TAG, str + " is on the denyList, skip loading from " + file.getCanonicalPath());
                return 0;
            }
            File soFileByName = getSoFileByName(str);
            if (soFileByName == null) {
                LogUtil.v(SoLoader.TAG, str + " file not found on " + file.getCanonicalPath());
                return 0;
            }
            String canonicalPath = soFileByName.getCanonicalPath();
            LogUtil.d(SoLoader.TAG, str + " file found at " + canonicalPath);
            if ((i & 1) == 0 || (this.flags & 2) == 0) {
                if ((this.flags & 1) != 0) {
                    z = true;
                }
                if (z) {
                    ElfFileChannel elfFileChannel = new ElfFileChannel(soFileByName);
                    try {
                        NativeDeps.loadDependencies(str, elfFileChannel, i, threadPolicy);
                        elfFileChannel.close();
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else {
                    LogUtil.d(SoLoader.TAG, "Not resolving dependencies for " + str);
                }
                try {
                    SoLoader.sSoFileLoader.load(canonicalPath, i);
                    return 1;
                } catch (UnsatisfiedLinkError e) {
                    throw SoLoaderULErrorFactory.create(str, e);
                }
            } else {
                LogUtil.d(SoLoader.TAG, str + " loaded implicitly");
                return 2;
            }
        } else {
            throw new IllegalStateException("SoLoader.init() not yet called");
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public File getSoFileByName(String str) throws IOException {
        File file = new File(this.soDirectory, str);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    @Nullable
    public String getLibraryPath(String str) throws IOException {
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            return null;
        }
        return soFileByName.getCanonicalPath();
    }

    @Nullable
    public String[] getLibraryDependencies(String str) throws IOException {
        File soFileByName = getSoFileByName(str);
        if (soFileByName == null) {
            return null;
        }
        ElfFileChannel elfFileChannel = new ElfFileChannel(soFileByName);
        try {
            String[] dependencies = NativeDeps.getDependencies(str, elfFileChannel);
            elfFileChannel.close();
            return dependencies;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    @Nullable
    public File unpackLibrary(String str) throws IOException {
        return getSoFileByName(str);
    }

    public void addToLdLibraryPath(Collection<String> collection) {
        try {
            collection.add(this.soDirectory.getCanonicalPath());
        } catch (IOException e) {
            LogUtil.e(SoLoader.TAG, "Failed to get canonical path for " + this.soDirectory.getName() + " due to " + e.toString() + ", falling to the absolute one");
            collection.add(this.soDirectory.getAbsolutePath());
        }
    }

    public String toString() {
        String str;
        try {
            str = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            str = this.soDirectory.getName();
        }
        return getName() + "[root = " + str + " flags = " + this.flags + AbstractJsonLexerKt.END_LIST;
    }
}
