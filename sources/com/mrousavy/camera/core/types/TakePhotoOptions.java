package com.mrousavy.camera.core.types;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.utils.FileUtils;
import com.mrousavy.camera.core.utils.OutputFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/mrousavy/camera/core/types/TakePhotoOptions;", "", "file", "Lcom/mrousavy/camera/core/utils/OutputFile;", "flash", "Lcom/mrousavy/camera/core/types/Flash;", "enableShutterSound", "", "(Lcom/mrousavy/camera/core/utils/OutputFile;Lcom/mrousavy/camera/core/types/Flash;Z)V", "getEnableShutterSound", "()Z", "getFile", "()Lcom/mrousavy/camera/core/utils/OutputFile;", "getFlash", "()Lcom/mrousavy/camera/core/types/Flash;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TakePhotoOptions.kt */
public final class TakePhotoOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean enableShutterSound;
    private final OutputFile file;
    private final Flash flash;

    public static /* synthetic */ TakePhotoOptions copy$default(TakePhotoOptions takePhotoOptions, OutputFile outputFile, Flash flash2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            outputFile = takePhotoOptions.file;
        }
        if ((i & 2) != 0) {
            flash2 = takePhotoOptions.flash;
        }
        if ((i & 4) != 0) {
            z = takePhotoOptions.enableShutterSound;
        }
        return takePhotoOptions.copy(outputFile, flash2, z);
    }

    public final OutputFile component1() {
        return this.file;
    }

    public final Flash component2() {
        return this.flash;
    }

    public final boolean component3() {
        return this.enableShutterSound;
    }

    public final TakePhotoOptions copy(OutputFile outputFile, Flash flash2, boolean z) {
        Intrinsics.checkNotNullParameter(outputFile, "file");
        Intrinsics.checkNotNullParameter(flash2, "flash");
        return new TakePhotoOptions(outputFile, flash2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TakePhotoOptions)) {
            return false;
        }
        TakePhotoOptions takePhotoOptions = (TakePhotoOptions) obj;
        return Intrinsics.areEqual((Object) this.file, (Object) takePhotoOptions.file) && this.flash == takePhotoOptions.flash && this.enableShutterSound == takePhotoOptions.enableShutterSound;
    }

    public int hashCode() {
        return (((this.file.hashCode() * 31) + this.flash.hashCode()) * 31) + Boolean.hashCode(this.enableShutterSound);
    }

    public String toString() {
        OutputFile outputFile = this.file;
        Flash flash2 = this.flash;
        return "TakePhotoOptions(file=" + outputFile + ", flash=" + flash2 + ", enableShutterSound=" + this.enableShutterSound + ")";
    }

    public TakePhotoOptions(OutputFile outputFile, Flash flash2, boolean z) {
        Intrinsics.checkNotNullParameter(outputFile, "file");
        Intrinsics.checkNotNullParameter(flash2, "flash");
        this.file = outputFile;
        this.flash = flash2;
        this.enableShutterSound = z;
    }

    public final boolean getEnableShutterSound() {
        return this.enableShutterSound;
    }

    public final OutputFile getFile() {
        return this.file;
    }

    public final Flash getFlash() {
        return this.flash;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/types/TakePhotoOptions$Companion;", "", "()V", "fromJS", "Lcom/mrousavy/camera/core/types/TakePhotoOptions;", "context", "Landroid/content/Context;", "map", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TakePhotoOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TakePhotoOptions fromJS(Context context, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(readableMap, "map");
            Flash fromUnionValue = readableMap.hasKey("flash") ? Flash.Companion.fromUnionValue(readableMap.getString("flash")) : Flash.OFF;
            boolean z = readableMap.hasKey("enableShutterSound") ? readableMap.getBoolean("enableShutterSound") : false;
            File directory = readableMap.hasKey("path") ? FileUtils.Companion.getDirectory(readableMap.getString("path")) : context.getCacheDir();
            Intrinsics.checkNotNull(directory);
            return new TakePhotoOptions(new OutputFile(context, directory, ".jpg"), fromUnionValue, z);
        }
    }
}
