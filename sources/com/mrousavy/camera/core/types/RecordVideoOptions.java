package com.mrousavy.camera.core.types;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.utils.FileUtils;
import com.mrousavy.camera.core.utils.OutputFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lcom/mrousavy/camera/core/types/RecordVideoOptions;", "", "file", "Lcom/mrousavy/camera/core/utils/OutputFile;", "videoCodec", "Lcom/mrousavy/camera/core/types/VideoCodec;", "(Lcom/mrousavy/camera/core/utils/OutputFile;Lcom/mrousavy/camera/core/types/VideoCodec;)V", "getFile", "()Lcom/mrousavy/camera/core/utils/OutputFile;", "getVideoCodec", "()Lcom/mrousavy/camera/core/types/VideoCodec;", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecordVideoOptions.kt */
public final class RecordVideoOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final OutputFile file;
    private final VideoCodec videoCodec;

    public RecordVideoOptions(OutputFile outputFile, VideoCodec videoCodec2) {
        Intrinsics.checkNotNullParameter(outputFile, "file");
        Intrinsics.checkNotNullParameter(videoCodec2, "videoCodec");
        this.file = outputFile;
        this.videoCodec = videoCodec2;
    }

    public final OutputFile getFile() {
        return this.file;
    }

    public final VideoCodec getVideoCodec() {
        return this.videoCodec;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/types/RecordVideoOptions$Companion;", "", "()V", "fromJSValue", "Lcom/mrousavy/camera/core/types/RecordVideoOptions;", "context", "Landroid/content/Context;", "map", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RecordVideoOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RecordVideoOptions fromJSValue(Context context, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(readableMap, "map");
            File directory = readableMap.hasKey("path") ? FileUtils.Companion.getDirectory(readableMap.getString("path")) : context.getCacheDir();
            VideoFileType fromUnionValue = readableMap.hasKey("fileType") ? VideoFileType.Companion.fromUnionValue(readableMap.getString("fileType")) : VideoFileType.MOV;
            VideoCodec fromUnionValue2 = readableMap.hasKey("videoCodec") ? VideoCodec.Companion.fromUnionValue(readableMap.getString("videoCodec")) : VideoCodec.H264;
            Intrinsics.checkNotNull(directory);
            return new RecordVideoOptions(new OutputFile(context, directory, fromUnionValue.toExtension()), fromUnionValue2);
        }
    }
}
