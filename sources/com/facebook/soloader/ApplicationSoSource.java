package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.annotation.Nullable;

public class ApplicationSoSource extends SoSource implements RecoverableSoSource {
    private final int flags;
    private DirectorySoSource soSource;

    public String getName() {
        return "ApplicationSoSource";
    }

    public ApplicationSoSource(Context context, int i) {
        this.flags = i;
        this.soSource = new DirectorySoSource(getNativeLibDirFromContext(context), i);
    }

    private static File getNativeLibDirFromContext(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir);
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return this.soSource.loadLibrary(str, i, threadPolicy);
    }

    @Nullable
    public File getSoFileByName(String str) throws IOException {
        return this.soSource.getSoFileByName(str);
    }

    @Nullable
    public String getLibraryPath(String str) throws IOException {
        return this.soSource.getLibraryPath(str);
    }

    @Nullable
    public String[] getLibraryDependencies(String str) throws IOException {
        return this.soSource.getLibraryDependencies(str);
    }

    @Nullable
    public File unpackLibrary(String str) throws IOException {
        return this.soSource.unpackLibrary(str);
    }

    /* access modifiers changed from: protected */
    public void prepare(int i) throws IOException {
        this.soSource.prepare(i);
    }

    public void addToLdLibraryPath(Collection<String> collection) {
        this.soSource.addToLdLibraryPath(collection);
    }

    public String toString() {
        return getName() + "[" + this.soSource.toString() + "]";
    }

    public SoSource recover(Context context) {
        this.soSource = new DirectorySoSource(getNativeLibDirFromContext(context), this.flags | 1);
        return this;
    }
}
