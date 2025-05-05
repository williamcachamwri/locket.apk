package com.mrousavy.camera.core.utils;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\n \u0010*\u0004\u0018\u00010\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/mrousavy/camera/core/utils/OutputFile;", "", "context", "Landroid/content/Context;", "directory", "Ljava/io/File;", "extension", "", "(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)V", "getContext", "()Landroid/content/Context;", "getDirectory", "()Ljava/io/File;", "getExtension", "()Ljava/lang/String;", "file", "kotlin.jvm.PlatformType", "getFile", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OutputFile.kt */
public final class OutputFile {
    private final Context context;
    private final File directory;
    private final String extension;
    private final File file;

    public static /* synthetic */ OutputFile copy$default(OutputFile outputFile, Context context2, File file2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            context2 = outputFile.context;
        }
        if ((i & 2) != 0) {
            file2 = outputFile.directory;
        }
        if ((i & 4) != 0) {
            str = outputFile.extension;
        }
        return outputFile.copy(context2, file2, str);
    }

    public final Context component1() {
        return this.context;
    }

    public final File component2() {
        return this.directory;
    }

    public final String component3() {
        return this.extension;
    }

    public final OutputFile copy(Context context2, File file2, String str) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(file2, "directory");
        Intrinsics.checkNotNullParameter(str, "extension");
        return new OutputFile(context2, file2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OutputFile)) {
            return false;
        }
        OutputFile outputFile = (OutputFile) obj;
        return Intrinsics.areEqual((Object) this.context, (Object) outputFile.context) && Intrinsics.areEqual((Object) this.directory, (Object) outputFile.directory) && Intrinsics.areEqual((Object) this.extension, (Object) outputFile.extension);
    }

    public int hashCode() {
        return (((this.context.hashCode() * 31) + this.directory.hashCode()) * 31) + this.extension.hashCode();
    }

    public String toString() {
        Context context2 = this.context;
        File file2 = this.directory;
        return "OutputFile(context=" + context2 + ", directory=" + file2 + ", extension=" + this.extension + ")";
    }

    public OutputFile(Context context2, File file2, String str) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(file2, "directory");
        Intrinsics.checkNotNullParameter(str, "extension");
        this.context = context2;
        this.directory = file2;
        this.extension = str;
        File createTempFile = File.createTempFile("mrousavy", str, file2);
        this.file = createTempFile;
        String absolutePath = file2.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        String absolutePath2 = context2.getCacheDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
        if (StringsKt.contains$default((CharSequence) absolutePath, (CharSequence) absolutePath2, false, 2, (Object) null)) {
            createTempFile.deleteOnExit();
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public final File getDirectory() {
        return this.directory;
    }

    public final String getExtension() {
        return this.extension;
    }

    public final File getFile() {
        return this.file;
    }
}
