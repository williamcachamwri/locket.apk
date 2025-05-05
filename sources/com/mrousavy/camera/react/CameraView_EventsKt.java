package com.mrousavy.camera.react;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.mrousavy.camera.core.CameraError;
import com.mrousavy.camera.core.CodeScannerFrame;
import com.mrousavy.camera.core.UnknownCameraError;
import com.mrousavy.camera.core.types.CodeType;
import com.mrousavy.camera.core.types.Orientation;
import com.mrousavy.camera.core.types.ShutterType;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a \u0010\t\u001a\u00020\u0005*\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u000f\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003\u001a\n\u0010\u0010\u001a\u00020\u0005*\u00020\u0006\u001a\u0012\u0010\u0011\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0012\u0010\u0014\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0013\u001a\n\u0010\u0016\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u0017\u001a\u00020\u0005*\u00020\u0006\u001a\u0012\u0010\u0018\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a\u001a\n\u0010\u001b\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u001c\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u001d\u001a\u00020\u0005*\u00020\u0006\u001a\u0018\u0010\u001e\u001a\u00020\u0005*\u00020\u00062\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002¨\u0006!"}, d2 = {"errorToMap", "Lcom/facebook/react/bridge/WritableMap;", "error", "", "invokeOnAverageFpsChanged", "", "Lcom/mrousavy/camera/react/CameraView;", "averageFps", "", "invokeOnCodeScanned", "barcodes", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "scannerFrame", "Lcom/mrousavy/camera/core/CodeScannerFrame;", "invokeOnError", "invokeOnInitialized", "invokeOnOutputOrientationChanged", "outputOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "invokeOnPreviewOrientationChanged", "previewOrientation", "invokeOnPreviewStarted", "invokeOnPreviewStopped", "invokeOnShutter", "type", "Lcom/mrousavy/camera/core/types/ShutterType;", "invokeOnStarted", "invokeOnStopped", "invokeOnViewReady", "sendEvent", "event", "Lcom/facebook/react/uimanager/events/Event;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView+Events.kt */
public final class CameraView_EventsKt {
    public static final void invokeOnInitialized(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnInitialized()");
        sendEvent(cameraView, new CameraInitializedEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnStarted(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnStarted()");
        sendEvent(cameraView, new CameraStartedEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnStopped(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnStopped()");
        sendEvent(cameraView, new CameraStoppedEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnPreviewStarted(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnPreviewStarted()");
        sendEvent(cameraView, new CameraPreviewStartedEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnPreviewStopped(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnPreviewStopped()");
        sendEvent(cameraView, new CameraPreviewStoppedEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnShutter(CameraView cameraView, ShutterType shutterType) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(shutterType, "type");
        Log.i("CameraView", "invokeOnShutter(" + shutterType + ")");
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("type", shutterType.getUnionValue());
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap);
        sendEvent(cameraView, new CameraShutterEvent(surfaceId, id, createMap));
    }

    public static final void invokeOnOutputOrientationChanged(CameraView cameraView, Orientation orientation) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(orientation, "outputOrientation");
        Log.i("CameraView", "invokeOnOutputOrientationChanged(" + orientation + ")");
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("outputOrientation", orientation.getUnionValue());
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap);
        sendEvent(cameraView, new CameraOutputOrientationChangedEvent(surfaceId, id, createMap));
    }

    public static final void invokeOnPreviewOrientationChanged(CameraView cameraView, Orientation orientation) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(orientation, "previewOrientation");
        Log.i("CameraView", "invokeOnPreviewOrientationChanged(" + orientation + ")");
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        WritableMap createMap = Arguments.createMap();
        createMap.putString("previewOrientation", orientation.getUnionValue());
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap);
        sendEvent(cameraView, new CameraPreviewOrientationChangedEvent(surfaceId, id, createMap));
    }

    public static final void invokeOnError(CameraView cameraView, Throwable th) {
        CameraError cameraError;
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(th, "error");
        SentryLogcatAdapter.e("CameraView", "invokeOnError(...):");
        th.printStackTrace();
        if (th instanceof CameraError) {
            cameraError = (CameraError) th;
        } else {
            cameraError = new UnknownCameraError(th);
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString(UniversalFirebaseFunctionsModule.CODE_KEY, cameraError.getCode());
        createMap.putString("message", cameraError.getMessage());
        Throwable cause = cameraError.getCause();
        if (cause != null) {
            createMap.putMap("cause", errorToMap(cause));
        }
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap);
        sendEvent(cameraView, new CameraErrorEvent(surfaceId, id, createMap));
    }

    public static final void invokeOnViewReady(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        sendEvent(cameraView, new CameraViewReadyEvent(UIManagerHelper.getSurfaceId((View) cameraView), cameraView.getId()));
    }

    public static final void invokeOnAverageFpsChanged(CameraView cameraView, double d) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Log.i("CameraView", "invokeOnAverageFpsChanged(" + d + ")");
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("averageFps", d);
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap);
        sendEvent(cameraView, new AverageFpsChangedEvent(surfaceId, id, createMap));
    }

    public static final void invokeOnCodeScanned(CameraView cameraView, List<? extends Barcode> list, CodeScannerFrame codeScannerFrame) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(list, "barcodes");
        Intrinsics.checkNotNullParameter(codeScannerFrame, "scannerFrame");
        WritableArray createArray = Arguments.createArray();
        for (Barcode barcode : list) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("type", CodeType.Companion.fromBarcodeType(barcode.getFormat()).getUnionValue());
            createMap.putString("value", barcode.getRawValue());
            Rect boundingBox = barcode.getBoundingBox();
            if (boundingBox != null) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putInt(ViewHierarchyNode.JsonKeys.X, boundingBox.left);
                createMap2.putInt(ViewHierarchyNode.JsonKeys.Y, boundingBox.top);
                createMap2.putInt("width", boundingBox.right - boundingBox.left);
                createMap2.putInt("height", boundingBox.bottom - boundingBox.top);
                createMap.putMap(TypedValues.AttributesType.S_FRAME, createMap2);
            }
            Point[] cornerPoints = barcode.getCornerPoints();
            if (cornerPoints != null) {
                WritableArray createArray2 = Arguments.createArray();
                Intrinsics.checkNotNull(cornerPoints);
                for (Point point : cornerPoints) {
                    WritableMap createMap3 = Arguments.createMap();
                    createMap3.putInt(ViewHierarchyNode.JsonKeys.X, point.x);
                    createMap3.putInt(ViewHierarchyNode.JsonKeys.Y, point.y);
                    createArray2.pushMap(createMap3);
                }
                createMap.putArray("corners", createArray2);
            }
            createArray.pushMap(createMap);
        }
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putArray("codes", createArray);
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putInt("width", codeScannerFrame.getWidth());
        createMap5.putInt("height", codeScannerFrame.getHeight());
        createMap4.putMap(TypedValues.AttributesType.S_FRAME, createMap5);
        int surfaceId = UIManagerHelper.getSurfaceId((View) cameraView);
        int id = cameraView.getId();
        Intrinsics.checkNotNull(createMap4);
        sendEvent(cameraView, new CameraCodeScannedEvent(surfaceId, id, createMap4));
    }

    private static final void sendEvent(CameraView cameraView, Event<?> event) {
        Context context = cameraView.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, cameraView.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(event);
        }
    }

    private static final WritableMap errorToMap(Throwable th) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", th.getMessage());
        createMap.putString("stacktrace", ExceptionsKt.stackTraceToString(th));
        Throwable cause = th.getCause();
        if (cause != null) {
            createMap.putMap("cause", errorToMap(cause));
        }
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }
}
