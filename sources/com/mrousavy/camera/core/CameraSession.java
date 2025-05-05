package com.mrousavy.camera.core;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import androidx.camera.core.Camera;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.media3.common.MimeTypes;
import com.facebook.react.bridge.UiThreadUtil;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.mrousavy.camera.core.OrientationManager;
import com.mrousavy.camera.core.types.Orientation;
import com.mrousavy.camera.core.types.ShutterType;
import com.mrousavy.camera.frameprocessors.Frame;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0004\u0001\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010u\u001a\u00020vH\u0000¢\u0006\u0002\bwJ\r\u0010x\u001a\u00020vH\u0000¢\u0006\u0002\byJ\b\u0010z\u001a\u00020vH\u0016J2\u0010{\u001a\u00020v2!\u0010|\u001a\u001d\u0012\u0013\u0012\u00110!¢\u0006\f\b~\u0012\b\b\u0012\u0004\b\b( \u0012\u0004\u0012\u00020v0}H@¢\u0006\u0003\u0010\u0001J\t\u0010\u0001\u001a\u00020vH\u0002J\u0011\u0010\u0001\u001a\u00020v2\u0006\u0010S\u001a\u00020TH\u0016J\u0012\u0010\u0001\u001a\u00020v2\u0007\u0010\u0001\u001a\u00020TH\u0016R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R \u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001d\"\u0004\b1\u0010\u001fR\u001a\u00102\u001a\u000203X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u000203X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00105\"\u0004\b:\u00107R\u0014\u0010;\u001a\u00020<8VX\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020@X\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020DX\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020HX\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020LX\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020PX\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020T8F¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001c\u0010W\u001a\u0004\u0018\u00010XX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001c\u0010]\u001a\u0004\u0018\u00010^X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\u001c\u0010c\u001a\u0004\u0018\u00010dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001c\u0010i\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\"\u0010o\u001a\n\u0012\u0004\u0012\u00020d\u0018\u00010pX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010r\"\u0004\bs\u0010t¨\u0006\u0001"}, d2 = {"Lcom/mrousavy/camera/core/CameraSession;", "Ljava/io/Closeable;", "Landroidx/lifecycle/LifecycleOwner;", "Lcom/mrousavy/camera/core/OrientationManager$Callback;", "context", "Landroid/content/Context;", "callback", "Lcom/mrousavy/camera/core/CameraSession$Callback;", "(Landroid/content/Context;Lcom/mrousavy/camera/core/CameraSession$Callback;)V", "audioManager", "Landroid/media/AudioManager;", "getAudioManager$react_native_vision_camera_release", "()Landroid/media/AudioManager;", "getCallback$react_native_vision_camera_release", "()Lcom/mrousavy/camera/core/CameraSession$Callback;", "camera", "Landroidx/camera/core/Camera;", "getCamera$react_native_vision_camera_release", "()Landroidx/camera/core/Camera;", "setCamera$react_native_vision_camera_release", "(Landroidx/camera/core/Camera;)V", "cameraProvider", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "getCameraProvider$react_native_vision_camera_release", "()Lcom/google/common/util/concurrent/ListenableFuture;", "codeScannerOutput", "Landroidx/camera/core/ImageAnalysis;", "getCodeScannerOutput$react_native_vision_camera_release", "()Landroidx/camera/core/ImageAnalysis;", "setCodeScannerOutput$react_native_vision_camera_release", "(Landroidx/camera/core/ImageAnalysis;)V", "configuration", "Lcom/mrousavy/camera/core/CameraConfiguration;", "getConfiguration$react_native_vision_camera_release", "()Lcom/mrousavy/camera/core/CameraConfiguration;", "setConfiguration$react_native_vision_camera_release", "(Lcom/mrousavy/camera/core/CameraConfiguration;)V", "getContext$react_native_vision_camera_release", "()Landroid/content/Context;", "currentUseCases", "", "Landroidx/camera/core/UseCase;", "getCurrentUseCases$react_native_vision_camera_release", "()Ljava/util/List;", "setCurrentUseCases$react_native_vision_camera_release", "(Ljava/util/List;)V", "frameProcessorOutput", "getFrameProcessorOutput$react_native_vision_camera_release", "setFrameProcessorOutput$react_native_vision_camera_release", "isDestroyed", "", "isDestroyed$react_native_vision_camera_release", "()Z", "setDestroyed$react_native_vision_camera_release", "(Z)V", "isRecordingCanceled", "isRecordingCanceled$react_native_vision_camera_release", "setRecordingCanceled$react_native_vision_camera_release", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycleRegistry$react_native_vision_camera_release", "()Landroidx/lifecycle/LifecycleRegistry;", "mainExecutor", "Ljava/util/concurrent/Executor;", "getMainExecutor$react_native_vision_camera_release", "()Ljava/util/concurrent/Executor;", "metadataProvider", "Lcom/mrousavy/camera/core/MetadataProvider;", "getMetadataProvider$react_native_vision_camera_release", "()Lcom/mrousavy/camera/core/MetadataProvider;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "getMutex$react_native_vision_camera_release", "()Lkotlinx/coroutines/sync/Mutex;", "orientationManager", "Lcom/mrousavy/camera/core/OrientationManager;", "getOrientationManager$react_native_vision_camera_release", "()Lcom/mrousavy/camera/core/OrientationManager;", "outputOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "getOutputOrientation", "()Lcom/mrousavy/camera/core/types/Orientation;", "photoOutput", "Landroidx/camera/core/ImageCapture;", "getPhotoOutput$react_native_vision_camera_release", "()Landroidx/camera/core/ImageCapture;", "setPhotoOutput$react_native_vision_camera_release", "(Landroidx/camera/core/ImageCapture;)V", "previewOutput", "Landroidx/camera/core/Preview;", "getPreviewOutput$react_native_vision_camera_release", "()Landroidx/camera/core/Preview;", "setPreviewOutput$react_native_vision_camera_release", "(Landroidx/camera/core/Preview;)V", "recorderOutput", "Landroidx/camera/video/Recorder;", "getRecorderOutput$react_native_vision_camera_release", "()Landroidx/camera/video/Recorder;", "setRecorderOutput$react_native_vision_camera_release", "(Landroidx/camera/video/Recorder;)V", "recording", "Landroidx/camera/video/Recording;", "getRecording$react_native_vision_camera_release", "()Landroidx/camera/video/Recording;", "setRecording$react_native_vision_camera_release", "(Landroidx/camera/video/Recording;)V", "videoOutput", "Landroidx/camera/video/VideoCapture;", "getVideoOutput$react_native_vision_camera_release", "()Landroidx/camera/video/VideoCapture;", "setVideoOutput$react_native_vision_camera_release", "(Landroidx/camera/video/VideoCapture;)V", "checkCameraPermission", "", "checkCameraPermission$react_native_vision_camera_release", "checkMicrophonePermission", "checkMicrophonePermission$react_native_vision_camera_release", "close", "configure", "lambda", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "configureOrientation", "onOutputOrientationChanged", "onPreviewOrientationChanged", "previewOrientation", "Callback", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession.kt */
public final class CameraSession implements Closeable, LifecycleOwner, OrientationManager.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "CameraSession";
    private final AudioManager audioManager;
    private final Callback callback;
    private Camera camera;
    private final ListenableFuture<ProcessCameraProvider> cameraProvider;
    private ImageAnalysis codeScannerOutput;
    private CameraConfiguration configuration;
    private final Context context;
    private List<? extends UseCase> currentUseCases = CollectionsKt.emptyList();
    private ImageAnalysis frameProcessorOutput;
    private boolean isDestroyed;
    private boolean isRecordingCanceled;
    private final LifecycleRegistry lifecycleRegistry;
    private final Executor mainExecutor;
    private final MetadataProvider metadataProvider;
    private final Mutex mutex;
    private final OrientationManager orientationManager;
    private ImageCapture photoOutput;
    private Preview previewOutput;
    private Recorder recorderOutput;
    private Recording recording;
    private VideoCapture<Recorder> videoOutput;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0012H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0003H&¨\u0006\u001a"}, d2 = {"Lcom/mrousavy/camera/core/CameraSession$Callback;", "", "onCodeScanned", "", "codes", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "scannerFrame", "Lcom/mrousavy/camera/core/CodeScannerFrame;", "onError", "error", "", "onFrame", "frame", "Lcom/mrousavy/camera/frameprocessors/Frame;", "onInitialized", "onOutputOrientationChanged", "outputOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "onPreviewOrientationChanged", "previewOrientation", "onShutter", "type", "Lcom/mrousavy/camera/core/types/ShutterType;", "onStarted", "onStopped", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraSession.kt */
    public interface Callback {
        void onCodeScanned(List<? extends Barcode> list, CodeScannerFrame codeScannerFrame);

        void onError(Throwable th);

        void onFrame(Frame frame);

        void onInitialized();

        void onOutputOrientationChanged(Orientation orientation);

        void onPreviewOrientationChanged(Orientation orientation);

        void onShutter(ShutterType shutterType);

        void onStarted();

        void onStopped();
    }

    public CameraSession(Context context2, Callback callback2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.context = context2;
        this.callback = callback2;
        this.cameraProvider = ProcessCameraProvider.Companion.getInstance(context2);
        this.metadataProvider = new MetadataProvider(context2);
        this.orientationManager = new OrientationManager(context2, this);
        this.mutex = MutexKt.Mutex$default(false, 1, (Object) null);
        LifecycleRegistry lifecycleRegistry2 = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry2;
        Object systemService = context2.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
        this.audioManager = (AudioManager) systemService;
        Executor mainExecutor2 = ContextCompat.getMainExecutor(context2);
        Intrinsics.checkNotNullExpressionValue(mainExecutor2, "getMainExecutor(...)");
        this.mainExecutor = mainExecutor2;
        lifecycleRegistry2.setCurrentState(Lifecycle.State.CREATED);
        getLifecycle().addObserver(new LifecycleEventObserver() {
            public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
                Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
                Log.i(CameraSession.TAG, "Camera Lifecycle changed to " + event.getTargetState() + "!");
            }
        });
    }

    public final Callback getCallback$react_native_vision_camera_release() {
        return this.callback;
    }

    public final Context getContext$react_native_vision_camera_release() {
        return this.context;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/CameraSession$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraSession.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final CameraConfiguration getConfiguration$react_native_vision_camera_release() {
        return this.configuration;
    }

    public final void setConfiguration$react_native_vision_camera_release(CameraConfiguration cameraConfiguration) {
        this.configuration = cameraConfiguration;
    }

    public final ListenableFuture<ProcessCameraProvider> getCameraProvider$react_native_vision_camera_release() {
        return this.cameraProvider;
    }

    public final Camera getCamera$react_native_vision_camera_release() {
        return this.camera;
    }

    public final void setCamera$react_native_vision_camera_release(Camera camera2) {
        this.camera = camera2;
    }

    public final Preview getPreviewOutput$react_native_vision_camera_release() {
        return this.previewOutput;
    }

    public final void setPreviewOutput$react_native_vision_camera_release(Preview preview) {
        this.previewOutput = preview;
    }

    public final ImageCapture getPhotoOutput$react_native_vision_camera_release() {
        return this.photoOutput;
    }

    public final void setPhotoOutput$react_native_vision_camera_release(ImageCapture imageCapture) {
        this.photoOutput = imageCapture;
    }

    public final VideoCapture<Recorder> getVideoOutput$react_native_vision_camera_release() {
        return this.videoOutput;
    }

    public final void setVideoOutput$react_native_vision_camera_release(VideoCapture<Recorder> videoCapture) {
        this.videoOutput = videoCapture;
    }

    public final ImageAnalysis getFrameProcessorOutput$react_native_vision_camera_release() {
        return this.frameProcessorOutput;
    }

    public final void setFrameProcessorOutput$react_native_vision_camera_release(ImageAnalysis imageAnalysis) {
        this.frameProcessorOutput = imageAnalysis;
    }

    public final ImageAnalysis getCodeScannerOutput$react_native_vision_camera_release() {
        return this.codeScannerOutput;
    }

    public final void setCodeScannerOutput$react_native_vision_camera_release(ImageAnalysis imageAnalysis) {
        this.codeScannerOutput = imageAnalysis;
    }

    public final List<UseCase> getCurrentUseCases$react_native_vision_camera_release() {
        return this.currentUseCases;
    }

    public final void setCurrentUseCases$react_native_vision_camera_release(List<? extends UseCase> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.currentUseCases = list;
    }

    public final MetadataProvider getMetadataProvider$react_native_vision_camera_release() {
        return this.metadataProvider;
    }

    public final OrientationManager getOrientationManager$react_native_vision_camera_release() {
        return this.orientationManager;
    }

    public final Recorder getRecorderOutput$react_native_vision_camera_release() {
        return this.recorderOutput;
    }

    public final void setRecorderOutput$react_native_vision_camera_release(Recorder recorder) {
        this.recorderOutput = recorder;
    }

    public final Mutex getMutex$react_native_vision_camera_release() {
        return this.mutex;
    }

    public final boolean isDestroyed$react_native_vision_camera_release() {
        return this.isDestroyed;
    }

    public final void setDestroyed$react_native_vision_camera_release(boolean z) {
        this.isDestroyed = z;
    }

    public final LifecycleRegistry getLifecycleRegistry$react_native_vision_camera_release() {
        return this.lifecycleRegistry;
    }

    public final Recording getRecording$react_native_vision_camera_release() {
        return this.recording;
    }

    public final void setRecording$react_native_vision_camera_release(Recording recording2) {
        this.recording = recording2;
    }

    public final boolean isRecordingCanceled$react_native_vision_camera_release() {
        return this.isRecordingCanceled;
    }

    public final void setRecordingCanceled$react_native_vision_camera_release(boolean z) {
        this.isRecordingCanceled = z;
    }

    public final AudioManager getAudioManager$react_native_vision_camera_release() {
        return this.audioManager;
    }

    public final Executor getMainExecutor$react_native_vision_camera_release() {
        return this.mainExecutor;
    }

    public final Orientation getOutputOrientation() {
        return this.orientationManager.getOutputOrientation();
    }

    public void close() {
        Log.i(TAG, "Closing CameraSession...");
        this.isDestroyed = true;
        this.orientationManager.stopOrientationUpdates();
        if (UiThreadUtil.isOnUiThread()) {
            getLifecycleRegistry$react_native_vision_camera_release().setCurrentState(Lifecycle.State.DESTROYED);
        } else {
            UiThreadUtil.runOnUiThread(new CameraSession$close$$inlined$runOnUiThread$1(this));
        }
    }

    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r14 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x019e, code lost:
        r13.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01a1, code lost:
        return r14;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:85:0x019c */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cc A[Catch:{ all -> 0x01a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d3 A[Catch:{ all -> 0x01a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0120 A[Catch:{ all -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0129 A[Catch:{ all -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0132 A[Catch:{ all -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0141 A[Catch:{ all -> 0x004a }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object configure(kotlin.jvm.functions.Function1<? super com.mrousavy.camera.core.CameraConfiguration, kotlin.Unit> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            java.lang.String r0 = "configure { ... }: Updating CameraSession Configuration... "
            java.lang.String r1 = "configure { ... }: Completed CameraSession Configuration! (State: "
            java.lang.String r2 = "Failed to configure CameraSession! Error: "
            boolean r3 = r14 instanceof com.mrousavy.camera.core.CameraSession$configure$1
            if (r3 == 0) goto L_0x001a
            r3 = r14
            com.mrousavy.camera.core.CameraSession$configure$1 r3 = (com.mrousavy.camera.core.CameraSession$configure$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r14 = r3.label
            int r14 = r14 - r5
            r3.label = r14
            goto L_0x001f
        L_0x001a:
            com.mrousavy.camera.core.CameraSession$configure$1 r3 = new com.mrousavy.camera.core.CameraSession$configure$1
            r3.<init>(r12, r14)
        L_0x001f:
            java.lang.Object r14 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 3
            r7 = 2
            r8 = 1
            r9 = 0
            java.lang.String r10 = "CameraSession"
            if (r5 == 0) goto L_0x007a
            if (r5 == r8) goto L_0x006b
            if (r5 == r7) goto L_0x0055
            if (r5 != r6) goto L_0x004d
            java.lang.Object r13 = r3.L$3
            com.mrousavy.camera.core.CameraConfiguration$Difference r13 = (com.mrousavy.camera.core.CameraConfiguration.Difference) r13
            java.lang.Object r0 = r3.L$2
            com.mrousavy.camera.core.CameraConfiguration r0 = (com.mrousavy.camera.core.CameraConfiguration) r0
            java.lang.Object r4 = r3.L$1
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r3 = r3.L$0
            com.mrousavy.camera.core.CameraSession r3 = (com.mrousavy.camera.core.CameraSession) r3
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x004a }
            goto L_0x0115
        L_0x004a:
            r14 = move-exception
            goto L_0x016d
        L_0x004d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0055:
            java.lang.Object r13 = r3.L$3
            kotlinx.coroutines.sync.Mutex r13 = (kotlinx.coroutines.sync.Mutex) r13
            java.lang.Object r5 = r3.L$2
            androidx.camera.lifecycle.ProcessCameraProvider r5 = (androidx.camera.lifecycle.ProcessCameraProvider) r5
            java.lang.Object r7 = r3.L$1
            kotlin.jvm.functions.Function1 r7 = (kotlin.jvm.functions.Function1) r7
            java.lang.Object r8 = r3.L$0
            com.mrousavy.camera.core.CameraSession r8 = (com.mrousavy.camera.core.CameraSession) r8
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r5
            r5 = r8
            goto L_0x00b1
        L_0x006b:
            java.lang.Object r13 = r3.L$1
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r5 = r3.L$0
            com.mrousavy.camera.core.CameraSession r5 = (com.mrousavy.camera.core.CameraSession) r5
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0077 }
            goto L_0x009a
        L_0x0077:
            r13 = move-exception
            goto L_0x01a9
        L_0x007a:
            kotlin.ResultKt.throwOnFailure(r14)
            boolean r14 = com.facebook.react.bridge.UiThreadUtil.isOnUiThread()
            if (r14 == 0) goto L_0x01c7
            java.lang.String r14 = "configure { ... }: Waiting for lock..."
            android.util.Log.i(r10, r14)
            com.google.common.util.concurrent.ListenableFuture<androidx.camera.lifecycle.ProcessCameraProvider> r14 = r12.cameraProvider     // Catch:{ all -> 0x01a7 }
            java.util.concurrent.Executor r5 = r12.mainExecutor     // Catch:{ all -> 0x01a7 }
            r3.L$0 = r12     // Catch:{ all -> 0x01a7 }
            r3.L$1 = r13     // Catch:{ all -> 0x01a7 }
            r3.label = r8     // Catch:{ all -> 0x01a7 }
            java.lang.Object r14 = com.mrousavy.camera.core.extensions.ListenableFuture_awaitKt.await(r14, r5, r3)     // Catch:{ all -> 0x01a7 }
            if (r14 != r4) goto L_0x0099
            return r4
        L_0x0099:
            r5 = r12
        L_0x009a:
            androidx.camera.lifecycle.ProcessCameraProvider r14 = (androidx.camera.lifecycle.ProcessCameraProvider) r14     // Catch:{ all -> 0x0077 }
            kotlinx.coroutines.sync.Mutex r8 = r5.mutex
            r3.L$0 = r5
            r3.L$1 = r13
            r3.L$2 = r14
            r3.L$3 = r8
            r3.label = r7
            java.lang.Object r7 = r8.lock(r9, r3)
            if (r7 != r4) goto L_0x00af
            return r4
        L_0x00af:
            r7 = r13
            r13 = r8
        L_0x00b1:
            com.mrousavy.camera.core.CameraConfiguration$Companion r8 = com.mrousavy.camera.core.CameraConfiguration.Companion     // Catch:{ all -> 0x01a2 }
            com.mrousavy.camera.core.CameraConfiguration r11 = r5.configuration     // Catch:{ all -> 0x01a2 }
            com.mrousavy.camera.core.CameraConfiguration r8 = r8.copyOf(r11)     // Catch:{ all -> 0x01a2 }
            r7.invoke(r8)     // Catch:{ AbortThrow -> 0x019c }
            com.mrousavy.camera.core.CameraConfiguration$Companion r7 = com.mrousavy.camera.core.CameraConfiguration.Companion     // Catch:{ all -> 0x01a2 }
            com.mrousavy.camera.core.CameraConfiguration r11 = r5.configuration     // Catch:{ all -> 0x01a2 }
            com.mrousavy.camera.core.CameraConfiguration$Difference r7 = r7.difference(r11, r8)     // Catch:{ all -> 0x01a2 }
            r5.configuration = r8     // Catch:{ all -> 0x01a2 }
            boolean r11 = r7.getHasChanges()     // Catch:{ all -> 0x01a2 }
            if (r11 != 0) goto L_0x00d3
            java.lang.String r14 = "Nothing changed, aborting configure { ... }"
            android.util.Log.i(r10, r14)     // Catch:{ all -> 0x01a2 }
            goto L_0x0191
        L_0x00d3:
            boolean r11 = r5.isDestroyed     // Catch:{ all -> 0x01a2 }
            if (r11 == 0) goto L_0x00de
            java.lang.String r14 = "CameraSession is already destroyed. Skipping configure { ... }"
            android.util.Log.i(r10, r14)     // Catch:{ all -> 0x01a2 }
            goto L_0x0191
        L_0x00de:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a2 }
            r11.<init>(r0)     // Catch:{ all -> 0x01a2 }
            java.lang.StringBuilder r0 = r11.append(r7)     // Catch:{ all -> 0x01a2 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01a2 }
            android.util.Log.i(r10, r0)     // Catch:{ all -> 0x01a2 }
            boolean r0 = r7.getOutputsChanged()     // Catch:{ all -> 0x0169 }
            if (r0 == 0) goto L_0x00fa
            com.mrousavy.camera.core.CameraSession_ConfigurationKt.configureOutputs(r5, r8)     // Catch:{ all -> 0x0169 }
            r5.configureOrientation()     // Catch:{ all -> 0x0169 }
        L_0x00fa:
            boolean r0 = r7.getDeviceChanged()     // Catch:{ all -> 0x0169 }
            if (r0 == 0) goto L_0x0117
            r3.L$0 = r5     // Catch:{ all -> 0x0169 }
            r3.L$1 = r13     // Catch:{ all -> 0x0169 }
            r3.L$2 = r8     // Catch:{ all -> 0x0169 }
            r3.L$3 = r7     // Catch:{ all -> 0x0169 }
            r3.label = r6     // Catch:{ all -> 0x0169 }
            java.lang.Object r14 = com.mrousavy.camera.core.CameraSession_ConfigurationKt.configureCamera(r5, r14, r8, r3)     // Catch:{ all -> 0x0169 }
            if (r14 != r4) goto L_0x0111
            return r4
        L_0x0111:
            r4 = r13
            r3 = r5
            r13 = r7
            r0 = r8
        L_0x0115:
            r8 = r0
            goto L_0x011a
        L_0x0117:
            r4 = r13
            r3 = r5
            r13 = r7
        L_0x011a:
            boolean r14 = r13.getSidePropsChanged()     // Catch:{ all -> 0x004a }
            if (r14 == 0) goto L_0x0123
            com.mrousavy.camera.core.CameraSession_ConfigurationKt.configureSideProps(r3, r8)     // Catch:{ all -> 0x004a }
        L_0x0123:
            boolean r14 = r13.isActiveChanged()     // Catch:{ all -> 0x004a }
            if (r14 == 0) goto L_0x012c
            com.mrousavy.camera.core.CameraSession_ConfigurationKt.configureIsActive(r3, r8)     // Catch:{ all -> 0x004a }
        L_0x012c:
            boolean r14 = r13.getOrientationChanged()     // Catch:{ all -> 0x004a }
            if (r14 == 0) goto L_0x013b
            com.mrousavy.camera.core.OrientationManager r14 = r3.orientationManager     // Catch:{ all -> 0x004a }
            com.mrousavy.camera.core.types.OutputOrientation r0 = r8.getOutputOrientation()     // Catch:{ all -> 0x004a }
            r14.setTargetOutputOrientation(r0)     // Catch:{ all -> 0x004a }
        L_0x013b:
            boolean r14 = r13.getLocationChanged()     // Catch:{ all -> 0x004a }
            if (r14 == 0) goto L_0x014a
            com.mrousavy.camera.core.MetadataProvider r14 = r3.metadataProvider     // Catch:{ all -> 0x004a }
            boolean r0 = r8.getEnableLocation()     // Catch:{ all -> 0x004a }
            r14.enableLocationUpdates(r0)     // Catch:{ all -> 0x004a }
        L_0x014a:
            androidx.lifecycle.Lifecycle r14 = r3.getLifecycle()     // Catch:{ all -> 0x004a }
            androidx.lifecycle.Lifecycle$State r14 = r14.getCurrentState()     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x004a }
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r14 = r0.append(r14)     // Catch:{ all -> 0x004a }
            java.lang.String r0 = ")"
            java.lang.StringBuilder r14 = r14.append(r0)     // Catch:{ all -> 0x004a }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x004a }
            android.util.Log.i(r10, r14)     // Catch:{ all -> 0x004a }
            goto L_0x0190
        L_0x0169:
            r14 = move-exception
            r4 = r13
            r3 = r5
            r13 = r7
        L_0x016d:
            java.lang.String r0 = r14.getMessage()     // Catch:{ all -> 0x0199 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0199 }
            r1.<init>(r2)     // Catch:{ all -> 0x0199 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x0199 }
            java.lang.String r1 = ", Config-Diff: "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0199 }
            java.lang.StringBuilder r13 = r0.append(r13)     // Catch:{ all -> 0x0199 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0199 }
            io.sentry.android.core.SentryLogcatAdapter.e(r10, r13, r14)     // Catch:{ all -> 0x0199 }
            com.mrousavy.camera.core.CameraSession$Callback r13 = r3.callback     // Catch:{ all -> 0x0199 }
            r13.onError(r14)     // Catch:{ all -> 0x0199 }
        L_0x0190:
            r13 = r4
        L_0x0191:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01a2 }
            r13.unlock(r9)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x0199:
            r14 = move-exception
            r13 = r4
            goto L_0x01a3
        L_0x019c:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01a2 }
            r13.unlock(r9)
            return r14
        L_0x01a2:
            r14 = move-exception
        L_0x01a3:
            r13.unlock(r9)
            throw r14
        L_0x01a7:
            r13 = move-exception
            r5 = r12
        L_0x01a9:
            java.lang.String r14 = r13.getMessage()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Failed to get CameraProvider! Error: "
            r0.<init>(r1)
            java.lang.StringBuilder r14 = r0.append(r14)
            java.lang.String r14 = r14.toString()
            io.sentry.android.core.SentryLogcatAdapter.e(r10, r14, r13)
            com.mrousavy.camera.core.CameraSession$Callback r14 = r5.callback
            r14.onError(r13)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x01c7:
            java.lang.Error r13 = new java.lang.Error
            java.lang.String r14 = "configure { ... } must be called from the Main UI Thread!"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraSession.configure(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void checkCameraPermission$react_native_vision_camera_release() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.CAMERA") != 0) {
            throw new CameraPermissionError();
        }
    }

    public final void checkMicrophonePermission$react_native_vision_camera_release() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.RECORD_AUDIO") != 0) {
            throw new MicrophonePermissionError();
        }
    }

    public void onOutputOrientationChanged(Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "outputOrientation");
        Log.i(TAG, "Output orientation changed! " + orientation);
        configureOrientation();
        this.callback.onOutputOrientationChanged(orientation);
    }

    public void onPreviewOrientationChanged(Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "previewOrientation");
        Log.i(TAG, "Preview orientation changed! " + orientation);
        configureOrientation();
        this.callback.onPreviewOrientationChanged(orientation);
    }

    private final void configureOrientation() {
        int surfaceRotation = this.orientationManager.getPreviewOrientation().toSurfaceRotation();
        Preview preview = this.previewOutput;
        if (preview != null) {
            preview.setTargetRotation(surfaceRotation);
        }
        ImageAnalysis imageAnalysis = this.codeScannerOutput;
        if (imageAnalysis != null) {
            imageAnalysis.setTargetRotation(surfaceRotation);
        }
        int surfaceRotation2 = this.orientationManager.getOutputOrientation().toSurfaceRotation();
        ImageCapture imageCapture = this.photoOutput;
        if (imageCapture != null) {
            imageCapture.setTargetRotation(surfaceRotation2);
        }
        VideoCapture<Recorder> videoCapture = this.videoOutput;
        if (videoCapture != null) {
            videoCapture.setTargetRotation(surfaceRotation2);
        }
    }
}
