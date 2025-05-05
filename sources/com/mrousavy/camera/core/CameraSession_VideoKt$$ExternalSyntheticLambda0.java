package com.mrousavy.camera.core;

import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.core.util.Consumer;
import com.mrousavy.camera.core.types.RecordVideoOptions;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CameraSession_VideoKt$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ CameraSession f$0;
    public final /* synthetic */ Function1 f$1;
    public final /* synthetic */ RecordVideoOptions f$2;
    public final /* synthetic */ VideoCapture f$3;
    public final /* synthetic */ Function1 f$4;

    public /* synthetic */ CameraSession_VideoKt$$ExternalSyntheticLambda0(CameraSession cameraSession, Function1 function1, RecordVideoOptions recordVideoOptions, VideoCapture videoCapture, Function1 function12) {
        this.f$0 = cameraSession;
        this.f$1 = function1;
        this.f$2 = recordVideoOptions;
        this.f$3 = videoCapture;
        this.f$4 = function12;
    }

    public final void accept(Object obj) {
        CameraSession_VideoKt.startRecording$lambda$2(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (VideoRecordEvent) obj);
    }
}
