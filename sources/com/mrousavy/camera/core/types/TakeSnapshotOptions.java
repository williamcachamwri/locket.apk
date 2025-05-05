package com.mrousavy.camera.core.types;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.utils.FileUtils;
import com.mrousavy.camera.core.utils.OutputFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/mrousavy/camera/core/types/TakeSnapshotOptions;", "", "file", "Lcom/mrousavy/camera/core/utils/OutputFile;", "quality", "", "(Lcom/mrousavy/camera/core/utils/OutputFile;I)V", "getFile", "()Lcom/mrousavy/camera/core/utils/OutputFile;", "getQuality", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TakeSnapshotOptions.kt */
public final class TakeSnapshotOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final OutputFile file;
    private final int quality;

    public static /* synthetic */ TakeSnapshotOptions copy$default(TakeSnapshotOptions takeSnapshotOptions, OutputFile outputFile, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            outputFile = takeSnapshotOptions.file;
        }
        if ((i2 & 2) != 0) {
            i = takeSnapshotOptions.quality;
        }
        return takeSnapshotOptions.copy(outputFile, i);
    }

    public final OutputFile component1() {
        return this.file;
    }

    public final int component2() {
        return this.quality;
    }

    public final TakeSnapshotOptions copy(OutputFile outputFile, int i) {
        Intrinsics.checkNotNullParameter(outputFile, "file");
        return new TakeSnapshotOptions(outputFile, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TakeSnapshotOptions)) {
            return false;
        }
        TakeSnapshotOptions takeSnapshotOptions = (TakeSnapshotOptions) obj;
        return Intrinsics.areEqual((Object) this.file, (Object) takeSnapshotOptions.file) && this.quality == takeSnapshotOptions.quality;
    }

    public int hashCode() {
        return (this.file.hashCode() * 31) + Integer.hashCode(this.quality);
    }

    public String toString() {
        OutputFile outputFile = this.file;
        return "TakeSnapshotOptions(file=" + outputFile + ", quality=" + this.quality + ")";
    }

    public TakeSnapshotOptions(OutputFile outputFile, int i) {
        Intrinsics.checkNotNullParameter(outputFile, "file");
        this.file = outputFile;
        this.quality = i;
    }

    public final OutputFile getFile() {
        return this.file;
    }

    public final int getQuality() {
        return this.quality;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/types/TakeSnapshotOptions$Companion;", "", "()V", "fromJSValue", "Lcom/mrousavy/camera/core/types/TakeSnapshotOptions;", "context", "Landroid/content/Context;", "map", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TakeSnapshotOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TakeSnapshotOptions fromJSValue(Context context, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(readableMap, "map");
            int i = readableMap.hasKey("quality") ? readableMap.getInt("quality") : 100;
            File directory = readableMap.hasKey("path") ? FileUtils.Companion.getDirectory(readableMap.getString("path")) : context.getCacheDir();
            Intrinsics.checkNotNull(directory);
            return new TakeSnapshotOptions(new OutputFile(context, directory, ".jpg"), i);
        }
    }
}
