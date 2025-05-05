package expo.modules.camera.next;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.video.FallbackStrategy;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import com.facebook.react.bridge.BaseJavaModule;
import com.google.common.util.concurrent.ListenableFuture;
import expo.modules.camera.BarcodeScannedEvent;
import expo.modules.camera.CameraMountErrorEvent;
import expo.modules.camera.PictureSavedEvent;
import expo.modules.camera.next.CameraExceptions;
import expo.modules.camera.next.analyzers.BarcodeAnalyzer;
import expo.modules.camera.next.records.BarcodeSettings;
import expo.modules.camera.next.records.BarcodeType;
import expo.modules.camera.next.records.CameraMode;
import expo.modules.camera.next.records.CameraType;
import expo.modules.camera.next.records.FlashMode;
import expo.modules.camera.next.records.VideoQuality;
import expo.modules.camera.next.utils.FileSystemUtils;
import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import expo.modules.interfaces.camera.CameraViewInterface;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import expo.modules.kotlin.viewevent.ViewEventDelegate;
import expo.modules.kotlin.views.ExpoView;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.Response;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000 \u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010W\u001a\u00020<J\b\u0010X\u001a\u00020<H\u0003J\b\u0010Y\u001a\u00020%H\u0002J\u001c\u0010Z\u001a\b\u0012\u0004\u0012\u00020M0[2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u000fH\u0002J0\u0010^\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020a0`\u0012\u0004\u0012\u00020a0_2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\u000f2\u0006\u0010d\u001a\u00020eH\u0002J\b\u0010f\u001a\u00020cH\u0002J\b\u0010g\u001a\u00020hH\u0016J\u0010\u0010i\u001a\u00020<2\u0006\u0010j\u001a\u00020]H\u0002J\u0010\u00104\u001a\u00020<2\u0006\u0010k\u001a\u00020lH\u0002J0\u0010m\u001a\u00020<2\u0006\u0010n\u001a\u00020/2\u0006\u0010o\u001a\u00020c2\u0006\u0010p\u001a\u00020c2\u0006\u0010q\u001a\u00020c2\u0006\u0010r\u001a\u00020cH\u0014J\u000e\u0010C\u001a\u00020<2\u0006\u0010s\u001a\u00020aJ\u0010\u0010t\u001a\u00020<2\u0006\u0010u\u001a\u00020vH\u0016J\u001e\u0010w\u001a\u00020<2\u0006\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}J\u0006\u0010~\u001a\u00020<J\u0012\u0010\u001a\u00020<2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001J\u0011\u0010\u0001\u001a\u00020<2\b\u0010\u0001\u001a\u00030\u0001J\u0015\u0010\u0001\u001a\u00020<2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016J\u000f\u0010\u0001\u001a\u00020<2\u0006\u0010P\u001a\u00020/J\u0010\u0010\u0001\u001a\u00020<2\u0007\u0010\u0001\u001a\u00020/J \u0010\u0001\u001a\u00020<2\u0007\u0010x\u001a\u00030\u00012\u0006\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}J\u0011\u0010\u0001\u001a\u00020<2\u0006\u0010k\u001a\u00020lH\u0002R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8BX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u0002\n\u0000R$\u0010)\u001a\u00020(2\u0006\u0010\u0017\u001a\u00020(@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R!\u00104\u001a\b\u0012\u0004\u0012\u000206058BX\u0002¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b7\u00108R!\u0010;\u001a\b\u0012\u0004\u0012\u00020<058BX\u0002¢\u0006\f\n\u0004\b>\u0010:\u001a\u0004\b=\u00108R!\u0010?\u001a\b\u0012\u0004\u0012\u00020@058BX\u0002¢\u0006\f\n\u0004\bB\u0010:\u001a\u0004\bA\u00108R!\u0010C\u001a\b\u0012\u0004\u0012\u00020D058BX\u0002¢\u0006\f\n\u0004\bF\u0010:\u001a\u0004\bE\u00108R\u000e\u0010G\u001a\u00020HX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010I\u001a\u0010\u0012\f\u0012\n K*\u0004\u0018\u00010\u001f0\u001f0JX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020OX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020/X\u000e¢\u0006\u0002\n\u0000R$\u0010R\u001a\u00020Q2\u0006\u0010\u0017\u001a\u00020Q@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010V¨\u0006\u0001"}, d2 = {"Lexpo/modules/camera/next/ExpoCameraView;", "Lexpo/modules/kotlin/views/ExpoView;", "Lexpo/modules/interfaces/camera/CameraViewInterface;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)V", "activeRecording", "Landroidx/camera/video/Recording;", "getActiveRecording", "()Landroidx/camera/video/Recording;", "setActiveRecording", "(Landroidx/camera/video/Recording;)V", "barcodeFormats", "", "Lexpo/modules/camera/next/records/BarcodeType;", "camera", "Landroidx/camera/core/Camera;", "getCamera", "()Landroidx/camera/core/Camera;", "setCamera", "(Landroidx/camera/core/Camera;)V", "value", "Lexpo/modules/camera/next/records/CameraMode;", "cameraMode", "getCameraMode", "()Lexpo/modules/camera/next/records/CameraMode;", "setCameraMode", "(Lexpo/modules/camera/next/records/CameraMode;)V", "cameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "currentActivity", "Landroidx/appcompat/app/AppCompatActivity;", "getCurrentActivity", "()Landroidx/appcompat/app/AppCompatActivity;", "imageAnalysisUseCase", "Landroidx/camera/core/ImageAnalysis;", "imageCaptureUseCase", "Landroidx/camera/core/ImageCapture;", "Lexpo/modules/camera/next/records/CameraType;", "lenFacing", "getLenFacing", "()Lexpo/modules/camera/next/records/CameraType;", "setLenFacing", "(Lexpo/modules/camera/next/records/CameraType;)V", "mute", "", "getMute", "()Z", "setMute", "(Z)V", "onBarcodeScanned", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "Lexpo/modules/camera/BarcodeScannedEvent;", "getOnBarcodeScanned", "()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "onBarcodeScanned$delegate", "Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", "onCameraReady", "", "getOnCameraReady", "onCameraReady$delegate", "onMountError", "Lexpo/modules/camera/CameraMountErrorEvent;", "getOnMountError", "onMountError$delegate", "onPictureSaved", "Lexpo/modules/camera/PictureSavedEvent;", "getOnPictureSaved", "onPictureSaved$delegate", "previewView", "Landroidx/camera/view/PreviewView;", "providerFuture", "Lcom/google/common/util/concurrent/ListenableFuture;", "kotlin.jvm.PlatformType", "recorder", "Landroidx/camera/video/Recorder;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "shouldScanBarcodes", "Lexpo/modules/camera/next/records/VideoQuality;", "videoQuality", "getVideoQuality", "()Lexpo/modules/camera/next/records/VideoQuality;", "setVideoQuality", "(Lexpo/modules/camera/next/records/VideoQuality;)V", "cancelCoroutineScope", "createCamera", "createImageAnalyzer", "createVideoCapture", "Landroidx/camera/video/VideoCapture;", "info", "Landroidx/camera/core/CameraInfo;", "getCornerPointsAndBoundingBox", "Lkotlin/Pair;", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "cornerPoints", "", "boundingBox", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult$BoundingBox;", "getDeviceOrientation", "getPreviewSizeAsArray", "", "observeCameraState", "cameraInfo", "barcode", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "onLayout", "changed", "left", "top", "right", "bottom", "response", "onViewAdded", "child", "Landroid/view/View;", "record", "options", "Lexpo/modules/camera/next/RecordingOptions;", "promise", "Lexpo/modules/kotlin/Promise;", "cacheDirectory", "Ljava/io/File;", "releaseCamera", "setBarcodeScannerSettings", "settings", "Lexpo/modules/camera/next/records/BarcodeSettings;", "setCameraFlashMode", "mode", "Lexpo/modules/camera/next/records/FlashMode;", "setPreviewTexture", "surfaceTexture", "Landroid/graphics/SurfaceTexture;", "setShouldScanBarcodes", "setTorchEnabled", "enabled", "takePicture", "Lexpo/modules/camera/next/PictureOptions;", "transformBarcodeScannerResultToViewCoordinates", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCameraView.kt */
public final class ExpoCameraView extends ExpoView implements CameraViewInterface {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private Recording activeRecording;
    private List<? extends BarcodeType> barcodeFormats = CollectionsKt.emptyList();
    private Camera camera;
    private CameraMode cameraMode;
    /* access modifiers changed from: private */
    public ProcessCameraProvider cameraProvider;
    private ImageAnalysis imageAnalysisUseCase;
    private ImageCapture imageCaptureUseCase;
    private CameraType lenFacing;
    private boolean mute;
    private final ViewEventDelegate onBarcodeScanned$delegate;
    private final ViewEventDelegate onCameraReady$delegate;
    private final ViewEventDelegate onMountError$delegate;
    private final ViewEventDelegate onPictureSaved$delegate;
    private PreviewView previewView;
    private final ListenableFuture<ProcessCameraProvider> providerFuture;
    private Recorder recorder;
    /* access modifiers changed from: private */
    public final CoroutineScope scope;
    private boolean shouldScanBarcodes;
    private VideoQuality videoQuality;

    public void setPreviewTexture(SurfaceTexture surfaceTexture) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpoCameraView(Context context, AppContext appContext) {
        super(context, appContext);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        ListenableFuture<ProcessCameraProvider> instance = ProcessCameraProvider.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.providerFuture = instance;
        this.previewView = new PreviewView(context);
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
        this.lenFacing = CameraType.BACK;
        this.cameraMode = CameraMode.PICTURE;
        this.videoQuality = VideoQuality.VIDEO1080P;
        View view = this;
        this.onCameraReady$delegate = new ViewEventDelegate(view, (Function1) null);
        this.onMountError$delegate = new ViewEventDelegate(view, (Function1) null);
        this.onBarcodeScanned$delegate = new ViewEventDelegate(view, ExpoCameraView$onBarcodeScanned$2.INSTANCE);
        this.onPictureSaved$delegate = new ViewEventDelegate(view, ExpoCameraView$onPictureSaved$2.INSTANCE);
        this.previewView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener(this) {
            final /* synthetic */ ExpoCameraView this$0;

            public void onChildViewRemoved(View view, View view2) {
            }

            {
                this.this$0 = r1;
            }

            public void onChildViewAdded(View view, View view2) {
                if (view != null) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(this.this$0.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.this$0.getMeasuredHeight(), 1073741824));
                }
                if (view != null) {
                    view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                }
            }
        });
        addView(this.previewView);
    }

    private final AppCompatActivity getCurrentActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        AppCompatActivity appCompatActivity = currentActivity instanceof AppCompatActivity ? (AppCompatActivity) currentActivity : null;
        if (appCompatActivity != null) {
            return appCompatActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public final Camera getCamera() {
        return this.camera;
    }

    public final void setCamera(Camera camera2) {
        this.camera = camera2;
    }

    public final Recording getActiveRecording() {
        return this.activeRecording;
    }

    public final void setActiveRecording(Recording recording) {
        this.activeRecording = recording;
    }

    public final CameraType getLenFacing() {
        return this.lenFacing;
    }

    public final void setLenFacing(CameraType cameraType) {
        Intrinsics.checkNotNullParameter(cameraType, "value");
        this.lenFacing = cameraType;
        createCamera();
    }

    public final CameraMode getCameraMode() {
        return this.cameraMode;
    }

    public final void setCameraMode(CameraMode cameraMode2) {
        Intrinsics.checkNotNullParameter(cameraMode2, "value");
        this.cameraMode = cameraMode2;
        createCamera();
    }

    public final VideoQuality getVideoQuality() {
        return this.videoQuality;
    }

    public final void setVideoQuality(VideoQuality videoQuality2) {
        Intrinsics.checkNotNullParameter(videoQuality2, "value");
        this.videoQuality = videoQuality2;
        createCamera();
    }

    public final boolean getMute() {
        return this.mute;
    }

    public final void setMute(boolean z) {
        this.mute = z;
    }

    static {
        Class<ExpoCameraView> cls = ExpoCameraView.class;
        $$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(cls, "onCameraReady", "getOnCameraReady()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(cls, "onMountError", "getOnMountError()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(cls, "onBarcodeScanned", "getOnBarcodeScanned()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(cls, "onPictureSaved", "getOnPictureSaved()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0))};
    }

    /* access modifiers changed from: private */
    public final ViewEventCallback<Unit> getOnCameraReady() {
        return this.onCameraReady$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final ViewEventCallback<CameraMountErrorEvent> getOnMountError() {
        return this.onMountError$delegate.getValue(this, $$delegatedProperties[1]);
    }

    private final ViewEventCallback<BarcodeScannedEvent> getOnBarcodeScanned() {
        return this.onBarcodeScanned$delegate.getValue(this, $$delegatedProperties[2]);
    }

    private final ViewEventCallback<PictureSavedEvent> getOnPictureSaved() {
        return this.onPictureSaved$delegate.getValue(this, $$delegatedProperties[3]);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.previewView.layout(0, 0, i3 - i, i4 - i2);
        postInvalidate(i, i2, i3, i4);
    }

    public void onViewAdded(View view) {
        Intrinsics.checkNotNullParameter(view, "child");
        PreviewView previewView2 = this.previewView;
        if (previewView2 != view) {
            removeView(previewView2);
            addView(this.previewView, 0);
        }
    }

    public final void takePicture(PictureOptions pictureOptions, Promise promise, File file) {
        Intrinsics.checkNotNullParameter(pictureOptions, "options");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Intrinsics.checkNotNullParameter(file, "cacheDirectory");
        ImageCapture imageCapture = this.imageCaptureUseCase;
        if (imageCapture != null) {
            imageCapture.m145lambda$takePicture$1$androidxcameracoreImageCapture(ContextCompat.getMainExecutor(getContext()), new ExpoCameraView$takePicture$1(pictureOptions, promise, file, this));
        }
    }

    public final void setCameraFlashMode(FlashMode flashMode) {
        Intrinsics.checkNotNullParameter(flashMode, "mode");
        ImageCapture imageCapture = this.imageCaptureUseCase;
        if (imageCapture != null) {
            imageCapture.setFlashMode(flashMode.mapToLens());
        }
    }

    public final void setTorchEnabled(boolean z) {
        Camera camera2;
        CameraControl cameraControl;
        CameraInfo cameraInfo;
        Camera camera3 = this.camera;
        boolean z2 = false;
        if (!(camera3 == null || (cameraInfo = camera3.getCameraInfo()) == null || !cameraInfo.hasFlashUnit())) {
            z2 = true;
        }
        if (z2 && (camera2 = this.camera) != null && (cameraControl = camera2.getCameraControl()) != null) {
            cameraControl.enableTorch(z);
        }
    }

    public final void record(RecordingOptions recordingOptions, Promise promise, File file) {
        Unit unit;
        Intrinsics.checkNotNullParameter(recordingOptions, "options");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Intrinsics.checkNotNullParameter(file, "cacheDirectory");
        FileOutputOptions build = ((FileOutputOptions.Builder) ((FileOutputOptions.Builder) new FileOutputOptions.Builder(FileSystemUtils.INSTANCE.generateOutputFile(file, "Camera", ".mp4")).setFileSizeLimit((long) recordingOptions.getMaxFileSize())).setDurationLimitMillis((long) recordingOptions.getMaxDuration())).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        Recorder recorder2 = this.recorder;
        if (recorder2 == null) {
            unit = null;
        } else if (ActivityCompat.checkSelfPermission(getContext(), "android.permission.RECORD_AUDIO") == 0) {
            Recording start = recorder2.prepareRecording(getContext(), build).withAudioEnabled().start(ContextCompat.getMainExecutor(getContext()), new ExpoCameraView$$ExternalSyntheticLambda0(promise));
            start.mute(this.mute);
            this.activeRecording = start;
            unit = Unit.INSTANCE;
        } else {
            return;
        }
        if (unit == null) {
            promise.reject("E_RECORDING_FAILED", "Starting video recording failed - could not create video file.", (Throwable) null);
        }
    }

    /* access modifiers changed from: private */
    public static final void record$lambda$3$lambda$1(Promise promise, VideoRecordEvent videoRecordEvent) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "$promise");
        if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
            VideoRecordEvent.Finalize finalize = (VideoRecordEvent.Finalize) videoRecordEvent;
            if (finalize.getError() > 0) {
                Throwable cause = finalize.getCause();
                if (cause == null || (str = cause.getMessage()) == null) {
                    str = "Video recording Failed: Unknown error";
                }
                promise.reject(new CameraExceptions.VideoRecordingFailed(str));
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("uri", finalize.getOutputResults().getOutputUri().toString());
            promise.resolve(bundle);
        }
    }

    private final void createCamera() {
        this.providerFuture.addListener(new ExpoCameraView$$ExternalSyntheticLambda2(this), ContextCompat.getMainExecutor(getContext()));
    }

    /* access modifiers changed from: private */
    public static final void createCamera$lambda$10(ExpoCameraView expoCameraView) {
        Intrinsics.checkNotNullParameter(expoCameraView, "this$0");
        Object obj = expoCameraView.providerFuture.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) obj;
        Preview build = new Preview.Builder().build();
        build.setSurfaceProvider(expoCameraView.previewView.getSurfaceProvider());
        Intrinsics.checkNotNullExpressionValue(build, "also(...)");
        CameraSelector build2 = new CameraSelector.Builder().requireLensFacing(expoCameraView.lenFacing.mapToCharacteristic()).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        expoCameraView.imageCaptureUseCase = new ImageCapture.Builder().build();
        List<CameraInfo> availableCameraInfos = processCameraProvider.getAvailableCameraInfos();
        Intrinsics.checkNotNullExpressionValue(availableCameraInfos, "getAvailableCameraInfos(...)");
        Collection arrayList = new ArrayList();
        for (Object next : availableCameraInfos) {
            Integer num = (Integer) Camera2CameraInfo.from((CameraInfo) next).getCameraCharacteristic(CameraCharacteristics.LENS_FACING);
            if (num != null && num.intValue() == expoCameraView.lenFacing.mapToCharacteristic()) {
                arrayList.add(next);
            }
        }
        VideoCapture<Recorder> createVideoCapture = expoCameraView.createVideoCapture((List) arrayList);
        expoCameraView.imageAnalysisUseCase = expoCameraView.createImageAnalyzer();
        UseCaseGroup.Builder builder = new UseCaseGroup.Builder();
        builder.addUseCase(build);
        if (expoCameraView.cameraMode == CameraMode.PICTURE) {
            ImageCapture imageCapture = expoCameraView.imageCaptureUseCase;
            if (imageCapture != null) {
                builder.addUseCase(imageCapture);
            }
            ImageAnalysis imageAnalysis = expoCameraView.imageAnalysisUseCase;
            if (imageAnalysis != null) {
                builder.addUseCase(imageAnalysis);
            }
        } else {
            builder.addUseCase(createVideoCapture);
        }
        UseCaseGroup build3 = builder.build();
        Intrinsics.checkNotNullExpressionValue(build3, "build(...)");
        try {
            processCameraProvider.unbindAll();
            Camera bindToLifecycle = processCameraProvider.bindToLifecycle((LifecycleOwner) expoCameraView.getCurrentActivity(), build2, build3);
            expoCameraView.camera = bindToLifecycle;
            if (bindToLifecycle != null) {
                CameraInfo cameraInfo = bindToLifecycle.getCameraInfo();
                Intrinsics.checkNotNullExpressionValue(cameraInfo, "getCameraInfo(...)");
                expoCameraView.observeCameraState(cameraInfo);
            }
            expoCameraView.cameraProvider = processCameraProvider;
        } catch (Exception unused) {
            expoCameraView.getOnMountError().invoke(new CameraMountErrorEvent("Camera component could not be rendered - is there any other instance running?"));
        }
    }

    private final ImageAnalysis createImageAnalyzer() {
        ImageAnalysis build = new ImageAnalysis.Builder().setResolutionSelector(new ResolutionSelector.Builder().setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY).build()).setBackpressureStrategy(0).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        if (this.shouldScanBarcodes) {
            build.setAnalyzer(ContextCompat.getMainExecutor(getContext()), new BarcodeAnalyzer(this.lenFacing, this.barcodeFormats, new ExpoCameraView$createImageAnalyzer$1$1(this)));
        }
        return build;
    }

    private final VideoCapture<Recorder> createVideoCapture(List<? extends CameraInfo> list) {
        Quality mapToQuality = this.videoQuality.mapToQuality();
        FallbackStrategy lowerQualityOrHigherThan = FallbackStrategy.lowerQualityOrHigherThan(mapToQuality);
        Intrinsics.checkNotNullExpressionValue(lowerQualityOrHigherThan, "lowerQualityOrHigherThan(...)");
        QualitySelector from = QualitySelector.from(mapToQuality, lowerQualityOrHigherThan);
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        Recorder build = new Recorder.Builder().setExecutor(ContextCompat.getMainExecutor(getContext())).setQualitySelector(from).build();
        this.recorder = build;
        Intrinsics.checkNotNullExpressionValue(build, "also(...)");
        VideoCapture<Recorder> build2 = new VideoCapture.Builder(build).setVideoStabilizationEnabled(true).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        return build2;
    }

    private final void observeCameraState(CameraInfo cameraInfo) {
        cameraInfo.getCameraState().observe(getCurrentActivity(), new ExpoCameraView$$ExternalSyntheticLambda1(new ExpoCameraView$observeCameraState$1(this)));
    }

    /* access modifiers changed from: private */
    public static final void observeCameraState$lambda$13(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    public final void setShouldScanBarcodes(boolean z) {
        this.shouldScanBarcodes = z;
        createCamera();
    }

    public final void setBarcodeScannerSettings(BarcodeSettings barcodeSettings) {
        List<BarcodeType> list;
        if (barcodeSettings == null || (list = barcodeSettings.getBarcodeTypes()) == null) {
            list = CollectionsKt.emptyList();
        }
        this.barcodeFormats = list;
    }

    private final int getDeviceOrientation() {
        Object systemService = getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return ((WindowManager) systemService).getDefaultDisplay().getRotation();
    }

    public final void releaseCamera() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(getAppContext().getMainQueue(), (CoroutineContext) null, (CoroutineStart) null, new ExpoCameraView$releaseCamera$1(this, (Continuation<? super ExpoCameraView$releaseCamera$1>) null), 3, (Object) null);
    }

    private final void transformBarcodeScannerResultToViewCoordinates(BarCodeScannerResult barCodeScannerResult) {
        List<Integer> cornerPoints = barCodeScannerResult.getCornerPoints();
        int width = this.previewView.getWidth();
        int height = this.previewView.getHeight();
        boolean z = this.lenFacing == CameraType.FRONT;
        boolean z2 = getDeviceOrientation() % 2 == 0;
        boolean z3 = getDeviceOrientation() % 2 != 0;
        if (z && z2) {
            Intrinsics.checkNotNull(cornerPoints);
            IntProgression step = RangesKt.step((IntProgression) RangesKt.until(0, cornerPoints.size()), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    int referenceImageHeight = barCodeScannerResult.getReferenceImageHeight();
                    Integer num = cornerPoints.get(first);
                    Intrinsics.checkNotNullExpressionValue(num, "get(...)");
                    cornerPoints.set(first, Integer.valueOf(referenceImageHeight - num.intValue()));
                    if (first == last) {
                        break;
                    }
                    first += step2;
                }
            }
        }
        if (z && z3) {
            Intrinsics.checkNotNull(cornerPoints);
            IntProgression step3 = RangesKt.step((IntProgression) RangesKt.until(1, cornerPoints.size()), 2);
            int first2 = step3.getFirst();
            int last2 = step3.getLast();
            int step4 = step3.getStep();
            if ((step4 > 0 && first2 <= last2) || (step4 < 0 && last2 <= first2)) {
                while (true) {
                    int referenceImageWidth = barCodeScannerResult.getReferenceImageWidth();
                    Integer num2 = cornerPoints.get(first2);
                    Intrinsics.checkNotNullExpressionValue(num2, "get(...)");
                    cornerPoints.set(first2, Integer.valueOf(referenceImageWidth - num2.intValue()));
                    if (first2 == last2) {
                        break;
                    }
                    first2 += step4;
                }
            }
        }
        Intrinsics.checkNotNull(cornerPoints);
        IntProgression step5 = RangesKt.step((IntProgression) RangesKt.until(1, cornerPoints.size()), 2);
        int first3 = step5.getFirst();
        int last3 = step5.getLast();
        int step6 = step5.getStep();
        if ((step6 > 0 && first3 <= last3) || (step6 < 0 && last3 <= first3)) {
            while (true) {
                cornerPoints.set(first3, Integer.valueOf(MathKt.roundToInt(((float) (cornerPoints.get(first3).intValue() * width)) / ((float) barCodeScannerResult.getReferenceImageWidth()))));
                if (first3 == last3) {
                    break;
                }
                first3 += step6;
            }
        }
        IntProgression step7 = RangesKt.step((IntProgression) RangesKt.until(0, cornerPoints.size()), 2);
        int first4 = step7.getFirst();
        int last4 = step7.getLast();
        int step8 = step7.getStep();
        if ((step8 > 0 && first4 <= last4) || (step8 < 0 && last4 <= first4)) {
            while (true) {
                cornerPoints.set(first4, Integer.valueOf(MathKt.roundToInt(((float) (cornerPoints.get(first4).intValue() * height)) / ((float) barCodeScannerResult.getReferenceImageHeight()))));
                if (first4 == last4) {
                    break;
                }
                first4 += step8;
            }
        }
        barCodeScannerResult.setCornerPoints(cornerPoints);
        barCodeScannerResult.setReferenceImageHeight(getHeight());
        barCodeScannerResult.setReferenceImageWidth(getWidth());
    }

    private final Pair<ArrayList<Bundle>, Bundle> getCornerPointsAndBoundingBox(List<Integer> list, BarCodeScannerResult.BoundingBox boundingBox) {
        float f = this.previewView.getResources().getDisplayMetrics().density;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, list.size() - 1, 2);
        if (progressionLastElement >= 0) {
            while (true) {
                Bundle bundle = new Bundle();
                bundle.putFloat(ViewHierarchyNode.JsonKeys.X, ((float) list.get(i + 1).intValue()) / f);
                bundle.putFloat(ViewHierarchyNode.JsonKeys.Y, ((float) list.get(i).intValue()) / f);
                arrayList.add(bundle);
                if (i == progressionLastElement) {
                    break;
                }
                i += 2;
            }
        }
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        bundle3.putFloat(ViewHierarchyNode.JsonKeys.X, ((float) boundingBox.getX()) / f);
        bundle3.putFloat(ViewHierarchyNode.JsonKeys.Y, ((float) boundingBox.getY()) / f);
        Unit unit = Unit.INSTANCE;
        bundle2.putParcelable("origin", bundle3);
        Bundle bundle4 = new Bundle();
        bundle4.putFloat("width", ((float) boundingBox.getWidth()) / f);
        bundle4.putFloat("height", ((float) boundingBox.getHeight()) / f);
        Unit unit2 = Unit.INSTANCE;
        bundle2.putParcelable("size", bundle4);
        return TuplesKt.to(arrayList, bundle2);
    }

    /* access modifiers changed from: private */
    public final void onBarcodeScanned(BarCodeScannerResult barCodeScannerResult) {
        if (this.shouldScanBarcodes) {
            transformBarcodeScannerResultToViewCoordinates(barCodeScannerResult);
            List<Integer> cornerPoints = barCodeScannerResult.getCornerPoints();
            Intrinsics.checkNotNullExpressionValue(cornerPoints, "getCornerPoints(...)");
            BarCodeScannerResult.BoundingBox boundingBox = barCodeScannerResult.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(boundingBox, "getBoundingBox(...)");
            Pair<ArrayList<Bundle>, Bundle> cornerPointsAndBoundingBox = getCornerPointsAndBoundingBox(cornerPoints, boundingBox);
            ViewEventCallback<BarcodeScannedEvent> onBarcodeScanned = getOnBarcodeScanned();
            int id = getId();
            String value = barCodeScannerResult.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            String raw = barCodeScannerResult.getRaw();
            Intrinsics.checkNotNullExpressionValue(raw, "getRaw(...)");
            onBarcodeScanned.invoke(new BarcodeScannedEvent(id, value, raw, barCodeScannerResult.getType(), cornerPointsAndBoundingBox.component1(), cornerPointsAndBoundingBox.component2()));
        }
    }

    public int[] getPreviewSizeAsArray() {
        return new int[]{this.previewView.getWidth(), this.previewView.getHeight()};
    }

    public final void onPictureSaved(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, Response.TYPE);
        ViewEventCallback<PictureSavedEvent> onPictureSaved = getOnPictureSaved();
        int i = bundle.getInt("id");
        Bundle bundle2 = bundle.getBundle("data");
        Intrinsics.checkNotNull(bundle2);
        onPictureSaved.invoke(new PictureSavedEvent(i, bundle2));
    }

    public final void cancelCoroutineScope() {
        try {
            CoroutineScopeKt.cancel(this.scope, new ModuleDestroyedException((String) null, 1, (DefaultConstructorMarker) null));
        } catch (Exception unused) {
            SentryLogcatAdapter.e(CameraViewNextModule.Companion.getTAG$expo_camera_release(), "The scope does not have a job in it");
        }
    }
}
