package com.mrousavy.camera.core;

import android.location.Location;
import android.util.Log;
import android.util.Size;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.PendingRecording;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import com.mrousavy.camera.core.extensions.VideoRecordEvent_toCameraErrorKt;
import com.mrousavy.camera.core.types.RecordVideoOptions;
import com.mrousavy.camera.core.types.Video;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001ab\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\u000b2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0014"}, d2 = {"cancelRecording", "", "Lcom/mrousavy/camera/core/CameraSession;", "pauseRecording", "resumeRecording", "startRecording", "enableAudio", "", "options", "Lcom/mrousavy/camera/core/types/RecordVideoOptions;", "callback", "Lkotlin/Function1;", "Lcom/mrousavy/camera/core/types/Video;", "Lkotlin/ParameterName;", "name", "video", "onError", "Lcom/mrousavy/camera/core/CameraError;", "error", "stopRecording", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Video.kt */
public final class CameraSession_VideoKt {
    public static final void startRecording(CameraSession cameraSession, boolean z, RecordVideoOptions recordVideoOptions, Function1<? super Video, Unit> function1, Function1<? super CameraError, Unit> function12) {
        Intrinsics.checkNotNullParameter(cameraSession, "<this>");
        Intrinsics.checkNotNullParameter(recordVideoOptions, "options");
        Intrinsics.checkNotNullParameter(function1, "callback");
        Intrinsics.checkNotNullParameter(function12, "onError");
        if (cameraSession.getCamera$react_native_vision_camera_release() == null) {
            throw new CameraNotReadyError();
        } else if (cameraSession.getRecording$react_native_vision_camera_release() == null) {
            VideoCapture<Recorder> videoOutput$react_native_vision_camera_release = cameraSession.getVideoOutput$react_native_vision_camera_release();
            if (videoOutput$react_native_vision_camera_release != null) {
                FileOutputOptions.Builder builder = new FileOutputOptions.Builder(recordVideoOptions.getFile().getFile());
                Location location = cameraSession.getMetadataProvider$react_native_vision_camera_release().getLocation();
                if (location != null) {
                    double latitude = location.getLatitude();
                    Log.i(CameraSession.TAG, "Setting Video Location to " + latitude + ", " + location.getLongitude() + "...");
                    builder.setLocation(location);
                }
                FileOutputOptions build = builder.build();
                Intrinsics.checkNotNullExpressionValue(build, "build(...)");
                PendingRecording prepareRecording = videoOutput$react_native_vision_camera_release.getOutput().prepareRecording(cameraSession.getContext$react_native_vision_camera_release(), build);
                Intrinsics.checkNotNullExpressionValue(prepareRecording, "prepareRecording(...)");
                if (z) {
                    cameraSession.checkMicrophonePermission$react_native_vision_camera_release();
                    prepareRecording = PendingRecording.withAudioEnabled$default(prepareRecording, false, 1, (Object) null);
                }
                PendingRecording asPersistentRecording = prepareRecording.asPersistentRecording();
                cameraSession.setRecordingCanceled$react_native_vision_camera_release(false);
                cameraSession.setRecording$react_native_vision_camera_release(asPersistentRecording.start(CameraQueues.Companion.getCameraExecutor(), new CameraSession_VideoKt$$ExternalSyntheticLambda0(cameraSession, function12, recordVideoOptions, videoOutput$react_native_vision_camera_release, function1)));
                return;
            }
            throw new VideoNotEnabledError();
        } else {
            throw new RecordingInProgressError();
        }
    }

    /* access modifiers changed from: private */
    public static final void startRecording$lambda$2(CameraSession cameraSession, Function1 function1, RecordVideoOptions recordVideoOptions, VideoCapture videoCapture, Function1 function12, VideoRecordEvent videoRecordEvent) {
        Intrinsics.checkNotNullParameter(cameraSession, "$this_startRecording");
        Intrinsics.checkNotNullParameter(function1, "$onError");
        Intrinsics.checkNotNullParameter(recordVideoOptions, "$options");
        Intrinsics.checkNotNullParameter(videoCapture, "$videoOutput");
        Intrinsics.checkNotNullParameter(function12, "$callback");
        if (videoRecordEvent instanceof VideoRecordEvent.Start) {
            Log.i(CameraSession.TAG, "Recording started!");
        } else if (videoRecordEvent instanceof VideoRecordEvent.Resume) {
            Log.i(CameraSession.TAG, "Recording resumed!");
        } else if (videoRecordEvent instanceof VideoRecordEvent.Pause) {
            Log.i(CameraSession.TAG, "Recording paused!");
        } else if (videoRecordEvent instanceof VideoRecordEvent.Status) {
            Log.i(CameraSession.TAG, "Status update! Recorded " + ((VideoRecordEvent.Status) videoRecordEvent).getRecordingStats().getNumBytesRecorded() + " bytes.");
        } else if (!(videoRecordEvent instanceof VideoRecordEvent.Finalize)) {
        } else {
            if (cameraSession.isRecordingCanceled$react_native_vision_camera_release()) {
                Log.i(CameraSession.TAG, "Recording was canceled, deleting file..");
                function1.invoke(new RecordingCanceledError());
                try {
                    recordVideoOptions.getFile().getFile().delete();
                } catch (Throwable th) {
                    cameraSession.getCallback$react_native_vision_camera_release().onError(new FileIOError(th));
                }
            } else {
                Log.i(CameraSession.TAG, "Recording stopped!");
                Intrinsics.checkNotNull(videoRecordEvent);
                VideoRecordEvent.Finalize finalize = (VideoRecordEvent.Finalize) videoRecordEvent;
                RecorderError cameraError = VideoRecordEvent_toCameraErrorKt.getCameraError(finalize);
                if (cameraError != null) {
                    if (cameraError.getWasVideoRecorded()) {
                        SentryLogcatAdapter.e(CameraSession.TAG, "Video Recorder encountered an error, but the video was recorded anyways.", cameraError);
                    } else {
                        SentryLogcatAdapter.e(CameraSession.TAG, "Video Recorder encountered a fatal error!", cameraError);
                        function1.invoke(cameraError);
                        return;
                    }
                }
                long recordedDurationNanos = finalize.getRecordingStats().getRecordedDurationNanos() / ((long) 1000000);
                Log.i(CameraSession.TAG, "Successfully completed video recording! Captured " + (((double) recordedDurationNanos) / 1000.0d) + " seconds.");
                String path = finalize.getOutputResults().getOutputUri().getPath();
                if (path != null) {
                    Size attachedSurfaceResolution = videoCapture.getAttachedSurfaceResolution();
                    if (attachedSurfaceResolution == null) {
                        attachedSurfaceResolution = new Size(0, 0);
                    }
                    function12.invoke(new Video(path, recordedDurationNanos, attachedSurfaceResolution));
                    return;
                }
                throw new UnknownRecorderError(false, (Throwable) null);
            }
        }
    }

    public static final void stopRecording(CameraSession cameraSession) {
        Intrinsics.checkNotNullParameter(cameraSession, "<this>");
        Recording recording$react_native_vision_camera_release = cameraSession.getRecording$react_native_vision_camera_release();
        if (recording$react_native_vision_camera_release != null) {
            recording$react_native_vision_camera_release.stop();
            cameraSession.setRecording$react_native_vision_camera_release((Recording) null);
            return;
        }
        throw new NoRecordingInProgressError();
    }

    public static final void cancelRecording(CameraSession cameraSession) {
        Intrinsics.checkNotNullParameter(cameraSession, "<this>");
        cameraSession.setRecordingCanceled$react_native_vision_camera_release(true);
        stopRecording(cameraSession);
    }

    public static final void pauseRecording(CameraSession cameraSession) {
        Intrinsics.checkNotNullParameter(cameraSession, "<this>");
        Recording recording$react_native_vision_camera_release = cameraSession.getRecording$react_native_vision_camera_release();
        if (recording$react_native_vision_camera_release != null) {
            recording$react_native_vision_camera_release.pause();
            return;
        }
        throw new NoRecordingInProgressError();
    }

    public static final void resumeRecording(CameraSession cameraSession) {
        Intrinsics.checkNotNullParameter(cameraSession, "<this>");
        Recording recording$react_native_vision_camera_release = cameraSession.getRecording$react_native_vision_camera_release();
        if (recording$react_native_vision_camera_release != null) {
            recording$react_native_vision_camera_release.resume();
            return;
        }
        throw new NoRecordingInProgressError();
    }
}
