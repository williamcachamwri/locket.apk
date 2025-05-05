package com.mrousavy.camera.react;

import androidx.media3.common.MimeTypes;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.mrousavy.camera.core.types.Video;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "video", "Lcom/mrousavy/camera/core/types/Video;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView+RecordVideo.kt */
final class CameraView_RecordVideoKt$startRecording$callback$1 extends Lambda implements Function1<Video, Unit> {
    final /* synthetic */ Callback $onRecordCallback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraView_RecordVideoKt$startRecording$callback$1(Callback callback) {
        super(1);
        this.$onRecordCallback = callback;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Video) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Video video) {
        Intrinsics.checkNotNullParameter(video, MimeTypes.BASE_TYPE_VIDEO);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("path", video.getPath());
        createMap.putDouble("duration", ((double) video.getDurationMs()) / 1000.0d);
        createMap.putInt("width", video.getSize().getWidth());
        createMap.putInt("height", video.getSize().getHeight());
        this.$onRecordCallback.invoke(createMap, null);
    }
}
