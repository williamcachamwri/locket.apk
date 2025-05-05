package com.swmansion.gesturehandler.react;

import android.content.Context;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.soloader.SoLoader;
import com.swmansion.common.GestureHandlerStateManager;
import com.swmansion.gesturehandler.NativeRNGestureHandlerModuleSpec;
import com.swmansion.gesturehandler.ReactContextExtensionsKt;
import com.swmansion.gesturehandler.ReanimatedEventDispatcher;
import com.swmansion.gesturehandler.core.FlingGestureHandler;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.HoverGestureHandler;
import com.swmansion.gesturehandler.core.LongPressGestureHandler;
import com.swmansion.gesturehandler.core.ManualGestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import com.swmansion.gesturehandler.core.RotationGestureHandler;
import com.swmansion.gesturehandler.core.TapGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerEvent;
import com.swmansion.gesturehandler.react.eventbuilders.FlingGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.HoverGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.LongPressGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.ManualGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.NativeGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.PanGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.PinchGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.RotationGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.TapGestureHandlerEventDataBuilder;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000£\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011*\u0001\u0007\b\u0007\u0018\u0000 S2\u00020\u00012\u00020\u0002:\u000bSTUVWXYZ[\\]B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0017J \u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0017J0\u0010#\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\u0006\u0010!\u001a\u00020\"H\u0002J\u0011\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*H J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J.\u0010,\u001a\n\u0012\u0004\u0012\u0002H$\u0018\u00010\u000b\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H$0%H\u0002J\u0012\u0010.\u001a\u0004\u0018\u00010\u00172\u0006\u0010/\u001a\u00020'H\u0002J\b\u00100\u001a\u00020\u0019H\u0017J\u0014\u00101\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020302H\u0016J\b\u00104\u001a\u00020 H\u0016J\b\u00105\u001a\u00020\u0019H\u0017J\u0018\u00106\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u00107\u001a\u000208H\u0017J\b\u00109\u001a\u000208H\u0017J\b\u0010:\u001a\u00020\u0019H\u0016J%\u0010;\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\u0006\u0010-\u001a\u0002H$H\u0002¢\u0006\u0002\u0010<J5\u0010=\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\u0006\u0010-\u001a\u0002H$2\u0006\u0010>\u001a\u00020'2\u0006\u0010?\u001a\u00020'H\u0002¢\u0006\u0002\u0010@J%\u0010A\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\u0006\u0010-\u001a\u0002H$H\u0002¢\u0006\u0002\u0010<J\u000e\u0010B\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u0017J\u0018\u0010D\u001a\u00020\u00192\u0006\u0010E\u001a\u00020 2\u0006\u0010F\u001a\u00020GH\u0002J%\u0010H\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0I2\u0006\u0010J\u001a\u0002H$H\u0002¢\u0006\u0002\u0010KJ\u0010\u0010L\u001a\u00020\u00192\u0006\u0010J\u001a\u00020MH\u0002J%\u0010N\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0I2\u0006\u0010J\u001a\u0002H$H\u0002¢\u0006\u0002\u0010KJ\u0018\u0010O\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'2\u0006\u0010>\u001a\u00020'H\u0016J\u000e\u0010P\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u0017J\u0018\u0010Q\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0017J(\u0010R\u001a\u00020\u0019\"\u000e\b\u0000\u0010$*\b\u0012\u0004\u0012\u0002H$0%2\u0006\u0010&\u001a\u00020'2\u0006\u0010!\u001a\u00020\"H\u0002R\u0010\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u001a\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\nX\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\u0002\n\u0000¨\u0006^"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule;", "Lcom/swmansion/gesturehandler/NativeRNGestureHandlerModuleSpec;", "Lcom/swmansion/common/GestureHandlerStateManager;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "eventListener", "com/swmansion/gesturehandler/react/RNGestureHandlerModule$eventListener$1", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$eventListener$1;", "handlerFactories", "", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "[Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "interactionManager", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerInteractionManager;", "reanimatedEventDispatcher", "Lcom/swmansion/gesturehandler/ReanimatedEventDispatcher;", "registry", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRegistry;", "getRegistry", "()Lcom/swmansion/gesturehandler/react/RNGestureHandlerRegistry;", "roots", "", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerRootHelper;", "attachGestureHandler", "", "handlerTagDouble", "", "viewTagDouble", "actionTypeDouble", "createGestureHandler", "handlerName", "", "config", "Lcom/facebook/react/bridge/ReadableMap;", "createGestureHandlerHelper", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "handlerTag", "", "decorateRuntime", "jsiPtr", "", "dropGestureHandler", "findFactoryForHandler", "handler", "findRootHelperForViewAncestor", "viewTag", "flushOperations", "getConstants", "", "", "getName", "handleClearJSResponder", "handleSetJSResponder", "blockNativeResponder", "", "install", "invalidate", "onHandlerUpdate", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)V", "onStateChange", "newState", "oldState", "(Lcom/swmansion/gesturehandler/core/GestureHandler;II)V", "onTouchEvent", "registerRootHelper", "root", "sendEventForDeviceEvent", "eventName", "data", "Lcom/facebook/react/bridge/WritableMap;", "sendEventForDirectEvent", "Lcom/facebook/react/uimanager/events/Event;", "event", "(Lcom/facebook/react/uimanager/events/Event;)V", "sendEventForNativeAnimatedEvent", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerEvent;", "sendEventForReanimated", "setGestureHandlerState", "unregisterRootHelper", "updateGestureHandler", "updateGestureHandlerHelper", "Companion", "FlingGestureHandlerFactory", "HandlerFactory", "HoverGestureHandlerFactory", "LongPressGestureHandlerFactory", "ManualGestureHandlerFactory", "NativeViewGestureHandlerFactory", "PanGestureHandlerFactory", "PinchGestureHandlerFactory", "RotationGestureHandlerFactory", "TapGestureHandlerFactory", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@ReactModule(name = "RNGestureHandlerModule")
/* compiled from: RNGestureHandlerModule.kt */
public final class RNGestureHandlerModule extends NativeRNGestureHandlerModuleSpec implements GestureHandlerStateManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_MANUAL_ACTIVATION = "manualActivation";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NEEDS_POINTER_DATA = "needsPointerData";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVATE_AFTER_LONG_PRESS = "activateAfterLongPress";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String NAME = "RNGestureHandlerModule";
    private final RNGestureHandlerModule$eventListener$1 eventListener = new RNGestureHandlerModule$eventListener$1(this);
    private final HandlerFactory<?>[] handlerFactories = {new NativeViewGestureHandlerFactory(), new TapGestureHandlerFactory(), new LongPressGestureHandlerFactory(), new PanGestureHandlerFactory(), new PinchGestureHandlerFactory(), new RotationGestureHandlerFactory(), new FlingGestureHandlerFactory(), new ManualGestureHandlerFactory(), new HoverGestureHandlerFactory()};
    private final RNGestureHandlerInteractionManager interactionManager = new RNGestureHandlerInteractionManager();
    private final ReanimatedEventDispatcher reanimatedEventDispatcher = new ReanimatedEventDispatcher();
    private final RNGestureHandlerRegistry registry = new RNGestureHandlerRegistry();
    private final List<RNGestureHandlerRootHelper> roots = new ArrayList();

    private final native void decorateRuntime(long j);

    @ReactMethod
    public void flushOperations() {
    }

    public String getName() {
        return "RNGestureHandlerModule";
    }

    @ReactMethod
    public void handleClearJSResponder() {
    }

    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0017\u0010\u0013\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00182\u0006\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0019R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/facebook/react/bridge/ReadableMap;)V", "create", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static abstract class HandlerFactory<T extends GestureHandler<T>> {
        public abstract T create(Context context);

        public abstract GestureHandlerEventDataBuilder<T> createEventBuilder(T t);

        public abstract String getName();

        public abstract Class<T> getType();

        public void configure(T t, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(t, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            t.resetConfig();
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                t.setShouldCancelWhenOutside(readableMap.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (readableMap.hasKey("enabled")) {
                t.setEnabled(readableMap.getBoolean("enabled"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.Companion.handleHitSlopProperty(t, readableMap);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA)) {
                t.setNeedsPointerData(readableMap.getBoolean(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION)) {
                t.setManualActivation(readableMap.getBoolean(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION));
            }
            if (readableMap.hasKey("mouseButton")) {
                t.setMouseButton(readableMap.getInt("mouseButton"));
            }
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$NativeViewGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/NativeViewGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/NativeGestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class NativeViewGestureHandlerFactory extends HandlerFactory<NativeViewGestureHandler> {
        private final String name = "NativeViewGestureHandler";
        private final Class<NativeViewGestureHandler> type = NativeViewGestureHandler.class;

        public Class<NativeViewGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public NativeViewGestureHandler create(Context context) {
            return new NativeViewGestureHandler();
        }

        public void configure(NativeViewGestureHandler nativeViewGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(nativeViewGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                nativeViewGestureHandler.setShouldActivateOnStart(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                nativeViewGestureHandler.setDisallowInterruption(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION));
            }
        }

        public NativeGestureHandlerEventDataBuilder createEventBuilder(NativeViewGestureHandler nativeViewGestureHandler) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            return new NativeGestureHandlerEventDataBuilder(nativeViewGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$TapGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/TapGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/TapGestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class TapGestureHandlerFactory extends HandlerFactory<TapGestureHandler> {
        private final String name = "TapGestureHandler";
        private final Class<TapGestureHandler> type = TapGestureHandler.class;

        public Class<TapGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public TapGestureHandler create(Context context) {
            return new TapGestureHandler();
        }

        public void configure(TapGestureHandler tapGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(tapGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(tapGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                tapGestureHandler.setNumberOfTaps(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                tapGestureHandler.setMaxDurationMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                tapGestureHandler.setMaxDelayMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                tapGestureHandler.setMaxDx(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                tapGestureHandler.setMaxDy(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)));
            }
            if (readableMap.hasKey("maxDist")) {
                tapGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
            if (readableMap.hasKey("minPointers")) {
                tapGestureHandler.setMinNumberOfPointers(readableMap.getInt("minPointers"));
            }
        }

        public TapGestureHandlerEventDataBuilder createEventBuilder(TapGestureHandler tapGestureHandler) {
            Intrinsics.checkNotNullParameter(tapGestureHandler, "handler");
            return new TapGestureHandlerEventDataBuilder(tapGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$LongPressGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/LongPressGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/LongPressGestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class LongPressGestureHandlerFactory extends HandlerFactory<LongPressGestureHandler> {
        private final String name = "LongPressGestureHandler";
        private final Class<LongPressGestureHandler> type = LongPressGestureHandler.class;

        public Class<LongPressGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public LongPressGestureHandler create(Context context) {
            Intrinsics.checkNotNull(context);
            return new LongPressGestureHandler(context);
        }

        public void configure(LongPressGestureHandler longPressGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(longPressGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(longPressGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                longPressGestureHandler.setMinDurationMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS));
            }
            if (readableMap.hasKey("maxDist")) {
                longPressGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
        }

        public LongPressGestureHandlerEventDataBuilder createEventBuilder(LongPressGestureHandler longPressGestureHandler) {
            Intrinsics.checkNotNullParameter(longPressGestureHandler, "handler");
            return new LongPressGestureHandlerEventDataBuilder(longPressGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$PanGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/PanGestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class PanGestureHandlerFactory extends HandlerFactory<PanGestureHandler> {
        private final String name = "PanGestureHandler";
        private final Class<PanGestureHandler> type = PanGestureHandler.class;

        public Class<PanGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public PanGestureHandler create(Context context) {
            return new PanGestureHandler(context);
        }

        public void configure(PanGestureHandler panGestureHandler, ReadableMap readableMap) {
            boolean z;
            Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(panGestureHandler, readableMap);
            boolean z2 = true;
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                panGestureHandler.setActiveOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)));
                z = true;
            } else {
                z = false;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                panGestureHandler.setActiveOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                panGestureHandler.setFailOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                panGestureHandler.setFailOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                panGestureHandler.setActiveOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                panGestureHandler.setActiveOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                panGestureHandler.setFailOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                panGestureHandler.setFailOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                panGestureHandler.setMinVelocity(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                panGestureHandler.setMinVelocityX(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                panGestureHandler.setMinVelocityY(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)));
            } else {
                z2 = z;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                panGestureHandler.setMinDist(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST)));
            } else if (z2) {
                panGestureHandler.setMinDist(Float.MAX_VALUE);
            }
            if (readableMap.hasKey("minPointers")) {
                panGestureHandler.setMinPointers(readableMap.getInt("minPointers"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                panGestureHandler.setMaxPointers(readableMap.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                panGestureHandler.setAverageTouches(readableMap.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS)) {
                panGestureHandler.setActivateAfterLongPress((long) readableMap.getInt(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS));
            }
        }

        public PanGestureHandlerEventDataBuilder createEventBuilder(PanGestureHandler panGestureHandler) {
            Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
            return new PanGestureHandlerEventDataBuilder(panGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$PinchGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/PinchGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/PinchGestureHandlerEventDataBuilder;", "handler", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class PinchGestureHandlerFactory extends HandlerFactory<PinchGestureHandler> {
        private final String name = "PinchGestureHandler";
        private final Class<PinchGestureHandler> type = PinchGestureHandler.class;

        public Class<PinchGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public PinchGestureHandler create(Context context) {
            return new PinchGestureHandler();
        }

        public PinchGestureHandlerEventDataBuilder createEventBuilder(PinchGestureHandler pinchGestureHandler) {
            Intrinsics.checkNotNullParameter(pinchGestureHandler, "handler");
            return new PinchGestureHandlerEventDataBuilder(pinchGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$FlingGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/FlingGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "configure", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/FlingGestureHandlerEventDataBuilder;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class FlingGestureHandlerFactory extends HandlerFactory<FlingGestureHandler> {
        private final String name = "FlingGestureHandler";
        private final Class<FlingGestureHandler> type = FlingGestureHandler.class;

        public Class<FlingGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public FlingGestureHandler create(Context context) {
            return new FlingGestureHandler();
        }

        public void configure(FlingGestureHandler flingGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(flingGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(flingGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                flingGestureHandler.setNumberOfPointersRequired(readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_DIRECTION)) {
                flingGestureHandler.setDirection(readableMap.getInt(RNGestureHandlerModule.KEY_DIRECTION));
            }
        }

        public FlingGestureHandlerEventDataBuilder createEventBuilder(FlingGestureHandler flingGestureHandler) {
            Intrinsics.checkNotNullParameter(flingGestureHandler, "handler");
            return new FlingGestureHandlerEventDataBuilder(flingGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$RotationGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/RotationGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/RotationGestureHandlerEventDataBuilder;", "handler", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class RotationGestureHandlerFactory extends HandlerFactory<RotationGestureHandler> {
        private final String name = "RotationGestureHandler";
        private final Class<RotationGestureHandler> type = RotationGestureHandler.class;

        public Class<RotationGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public RotationGestureHandler create(Context context) {
            return new RotationGestureHandler();
        }

        public RotationGestureHandlerEventDataBuilder createEventBuilder(RotationGestureHandler rotationGestureHandler) {
            Intrinsics.checkNotNullParameter(rotationGestureHandler, "handler");
            return new RotationGestureHandlerEventDataBuilder(rotationGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$ManualGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/ManualGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/ManualGestureHandlerEventDataBuilder;", "handler", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class ManualGestureHandlerFactory extends HandlerFactory<ManualGestureHandler> {
        private final String name = "ManualGestureHandler";
        private final Class<ManualGestureHandler> type = ManualGestureHandler.class;

        public Class<ManualGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public ManualGestureHandler create(Context context) {
            return new ManualGestureHandler();
        }

        public ManualGestureHandlerEventDataBuilder createEventBuilder(ManualGestureHandler manualGestureHandler) {
            Intrinsics.checkNotNullParameter(manualGestureHandler, "handler");
            return new ManualGestureHandlerEventDataBuilder(manualGestureHandler);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HoverGestureHandlerFactory;", "Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$HandlerFactory;", "Lcom/swmansion/gesturehandler/core/HoverGestureHandler;", "()V", "name", "", "getName", "()Ljava/lang/String;", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "create", "context", "Landroid/content/Context;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/HoverGestureHandlerEventDataBuilder;", "handler", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    private static final class HoverGestureHandlerFactory extends HandlerFactory<HoverGestureHandler> {
        private final String name = "HoverGestureHandler";
        private final Class<HoverGestureHandler> type = HoverGestureHandler.class;

        public Class<HoverGestureHandler> getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public HoverGestureHandler create(Context context) {
            return new HoverGestureHandler();
        }

        public HoverGestureHandlerEventDataBuilder createEventBuilder(HoverGestureHandler hoverGestureHandler) {
            Intrinsics.checkNotNullParameter(hoverGestureHandler, "handler");
            return new HoverGestureHandlerEventDataBuilder(hoverGestureHandler);
        }
    }

    public final RNGestureHandlerRegistry getRegistry() {
        return this.registry;
    }

    private final <T extends GestureHandler<T>> void createGestureHandlerHelper(String str, int i, ReadableMap readableMap) {
        if (this.registry.getHandler(i) == null) {
            for (HandlerFactory handlerFactory : this.handlerFactories) {
                if (Intrinsics.areEqual((Object) handlerFactory.getName(), (Object) str)) {
                    GestureHandler create = handlerFactory.create(getReactApplicationContext());
                    create.setTag(i);
                    create.setOnTouchEventListener(this.eventListener);
                    this.registry.registerHandler(create);
                    this.interactionManager.configureInteractions(create, readableMap);
                    handlerFactory.configure(create, readableMap);
                    return;
                }
            }
            throw new JSApplicationIllegalArgumentException("Invalid handler name " + str);
        }
        throw new IllegalStateException("Handler with tag " + i + " already exists. Please ensure that no Gesture instance is used across multiple GestureDetectors.");
    }

    @ReactMethod
    public void createGestureHandler(String str, double d, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(str, "handlerName");
        Intrinsics.checkNotNullParameter(readableMap, "config");
        createGestureHandlerHelper(str, (int) d, readableMap);
    }

    @ReactMethod
    public void attachGestureHandler(double d, double d2, double d3) {
        int i = (int) d;
        if (!this.registry.attachHandlerToView(i, (int) d2, (int) d3)) {
            throw new JSApplicationIllegalArgumentException("Handler with tag " + i + " does not exists");
        }
    }

    private final <T extends GestureHandler<T>> void updateGestureHandlerHelper(int i, ReadableMap readableMap) {
        HandlerFactory<T> findFactoryForHandler;
        GestureHandler<?> handler = this.registry.getHandler(i);
        if (handler != null && (findFactoryForHandler = findFactoryForHandler(handler)) != null) {
            this.interactionManager.dropRelationsForHandlerWithTag(i);
            this.interactionManager.configureInteractions(handler, readableMap);
            findFactoryForHandler.configure(handler, readableMap);
        }
    }

    @ReactMethod
    public void updateGestureHandler(double d, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        updateGestureHandlerHelper((int) d, readableMap);
    }

    @ReactMethod
    public void dropGestureHandler(double d) {
        int i = (int) d;
        this.interactionManager.dropRelationsForHandlerWithTag(i);
        this.registry.dropHandler(i);
    }

    @ReactMethod
    public void handleSetJSResponder(double d, boolean z) {
        int i = (int) d;
        RNGestureHandlerRootHelper findRootHelperForViewAncestor = findRootHelperForViewAncestor(i);
        if (findRootHelperForViewAncestor != null) {
            findRootHelperForViewAncestor.handleSetJSResponder(i, z);
        }
    }

    public void setGestureHandlerState(int i, int i2) {
        GestureHandler<?> handler = this.registry.getHandler(i);
        if (handler == null) {
            return;
        }
        if (i2 == 1) {
            handler.fail();
        } else if (i2 == 2) {
            handler.begin();
        } else if (i2 == 3) {
            handler.cancel();
        } else if (i2 == 4) {
            handler.activate(true);
        } else if (i2 == 5) {
            handler.end();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean install() {
        getReactApplicationContext().runOnJSQueueThread(new RNGestureHandlerModule$$ExternalSyntheticLambda0(this));
        return true;
    }

    /* access modifiers changed from: private */
    public static final void install$lambda$2(RNGestureHandlerModule rNGestureHandlerModule) {
        Intrinsics.checkNotNullParameter(rNGestureHandlerModule, "this$0");
        try {
            SoLoader.loadLibrary("gesturehandler");
            JavaScriptContextHolder javaScriptContextHolder = rNGestureHandlerModule.getReactApplicationContext().getJavaScriptContextHolder();
            Intrinsics.checkNotNull(javaScriptContextHolder);
            rNGestureHandlerModule.decorateRuntime(javaScriptContextHolder.get());
        } catch (Exception unused) {
            SentryLogcatAdapter.w("[RNGestureHandler]", "Could not install JSI bindings.");
        }
    }

    public Map<String, Object> getConstants() {
        return MapsKt.mapOf(TuplesKt.to("State", MapsKt.mapOf(TuplesKt.to("UNDETERMINED", 0), TuplesKt.to("BEGAN", 2), TuplesKt.to("ACTIVE", 4), TuplesKt.to("CANCELLED", 3), TuplesKt.to("FAILED", 1), TuplesKt.to("END", 5))), TuplesKt.to("Direction", MapsKt.mapOf(TuplesKt.to("RIGHT", 1), TuplesKt.to("LEFT", 2), TuplesKt.to("UP", 4), TuplesKt.to("DOWN", 8))));
    }

    public void invalidate() {
        this.registry.dropAllHandlers();
        this.interactionManager.reset();
        synchronized (this.roots) {
            while (!this.roots.isEmpty()) {
                int size = this.roots.size();
                this.roots.get(0).tearDown();
                if (this.roots.size() >= size) {
                    throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        super.invalidate();
    }

    public final void registerRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        Intrinsics.checkNotNullParameter(rNGestureHandlerRootHelper, "root");
        synchronized (this.roots) {
            if (!this.roots.contains(rNGestureHandlerRootHelper)) {
                this.roots.add(rNGestureHandlerRootHelper);
            } else {
                throw new IllegalStateException("Root helper" + rNGestureHandlerRootHelper + " already registered");
            }
        }
    }

    public final void unregisterRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        Intrinsics.checkNotNullParameter(rNGestureHandlerRootHelper, "root");
        synchronized (this.roots) {
            this.roots.remove(rNGestureHandlerRootHelper);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper findRootHelperForViewAncestor(int r8) {
        /*
            r7 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r7.getReactApplicationContext()
            java.lang.String r1 = "getReactApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.facebook.react.bridge.ReactContext r0 = (com.facebook.react.bridge.ReactContext) r0
            com.facebook.react.uimanager.UIManagerModule r0 = com.swmansion.gesturehandler.react.ExtensionsKt.getUIManager(r0)
            int r8 = r0.resolveRootTagFromReactTag(r8)
            r0 = 0
            r1 = 1
            if (r8 >= r1) goto L_0x0018
            return r0
        L_0x0018:
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r2 = r7.roots
            monitor-enter(r2)
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r3 = r7.roots     // Catch:{ all -> 0x004e }
            java.lang.Iterable r3 = (java.lang.Iterable) r3     // Catch:{ all -> 0x004e }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x004e }
        L_0x0023:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x004e }
            if (r4 == 0) goto L_0x004a
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x004e }
            r5 = r4
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper r5 = (com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper) r5     // Catch:{ all -> 0x004e }
            android.view.ViewGroup r6 = r5.getRootView()     // Catch:{ all -> 0x004e }
            boolean r6 = r6 instanceof com.facebook.react.ReactRootView     // Catch:{ all -> 0x004e }
            if (r6 == 0) goto L_0x0046
            android.view.ViewGroup r5 = r5.getRootView()     // Catch:{ all -> 0x004e }
            com.facebook.react.ReactRootView r5 = (com.facebook.react.ReactRootView) r5     // Catch:{ all -> 0x004e }
            int r5 = r5.getRootViewTag()     // Catch:{ all -> 0x004e }
            if (r5 != r8) goto L_0x0046
            r5 = r1
            goto L_0x0047
        L_0x0046:
            r5 = 0
        L_0x0047:
            if (r5 == 0) goto L_0x0023
            r0 = r4
        L_0x004a:
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper r0 = (com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper) r0     // Catch:{ all -> 0x004e }
            monitor-exit(r2)
            return r0
        L_0x004e:
            r8 = move-exception
            monitor-exit(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerModule.findRootHelperForViewAncestor(int):com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper");
    }

    private final <T extends GestureHandler<T>> HandlerFactory<T> findFactoryForHandler(GestureHandler<T> gestureHandler) {
        for (HandlerFactory<?> handlerFactory : this.handlerFactories) {
            if (Intrinsics.areEqual((Object) handlerFactory.getType(), (Object) gestureHandler.getClass())) {
                return handlerFactory;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onHandlerUpdate(T t) {
        HandlerFactory findFactoryForHandler;
        if (t.getTag() < 0 || t.getState() != 4 || (findFactoryForHandler = findFactoryForHandler(t)) == null) {
            return;
        }
        if (t.getActionType() == 1) {
            sendEventForReanimated(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.Companion, t, findFactoryForHandler.createEventBuilder(t), false, 4, (Object) null));
        } else if (t.getActionType() == 2) {
            sendEventForNativeAnimatedEvent(RNGestureHandlerEvent.Companion.obtain(t, findFactoryForHandler.createEventBuilder(t), true));
        } else if (t.getActionType() == 3) {
            sendEventForDirectEvent(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.Companion, t, findFactoryForHandler.createEventBuilder(t), false, 4, (Object) null));
        } else if (t.getActionType() == 4) {
            sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerEvent.Companion.createEventData(findFactoryForHandler.createEventBuilder(t)));
        }
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onStateChange(T t, int i, int i2) {
        HandlerFactory findFactoryForHandler;
        if (t.getTag() < 0 || (findFactoryForHandler = findFactoryForHandler(t)) == null) {
            return;
        }
        if (t.getActionType() == 1) {
            sendEventForReanimated(RNGestureHandlerStateChangeEvent.Companion.obtain(t, i, i2, findFactoryForHandler.createEventBuilder(t)));
        } else if (t.getActionType() == 2 || t.getActionType() == 3) {
            sendEventForDirectEvent(RNGestureHandlerStateChangeEvent.Companion.obtain(t, i, i2, findFactoryForHandler.createEventBuilder(t)));
        } else if (t.getActionType() == 4) {
            sendEventForDeviceEvent(RNGestureHandlerStateChangeEvent.EVENT_NAME, RNGestureHandlerStateChangeEvent.Companion.createEventData(findFactoryForHandler.createEventBuilder(t), i, i2));
        }
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler<T>> void onTouchEvent(T t) {
        if (t.getTag() >= 0) {
            if (t.getState() != 2 && t.getState() != 4 && t.getState() != 0 && t.getView() == null) {
                return;
            }
            if (t.getActionType() == 1) {
                sendEventForReanimated(RNGestureHandlerTouchEvent.Companion.obtain(t));
            } else if (t.getActionType() == 4) {
                sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerTouchEvent.Companion.createEventData(t));
            }
        }
    }

    private final <T extends Event<T>> void sendEventForReanimated(T t) {
        sendEventForDirectEvent(t);
    }

    private final void sendEventForNativeAnimatedEvent(RNGestureHandlerEvent rNGestureHandlerEvent) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, rNGestureHandlerEvent);
    }

    private final <T extends Event<T>> void sendEventForDirectEvent(T t) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, t);
    }

    private final void sendEventForDeviceEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ExtensionsKt.getDeviceEventEmitter(reactApplicationContext).emit(str, writableMap);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010/\u001a\u0002002\n\u00101\u001a\u0006\u0012\u0002\b\u0003022\u0006\u00103\u001a\u000204H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNGestureHandlerModule$Companion;", "", "()V", "KEY_DIRECTION", "", "KEY_ENABLED", "KEY_HIT_SLOP", "KEY_HIT_SLOP_BOTTOM", "KEY_HIT_SLOP_HEIGHT", "KEY_HIT_SLOP_HORIZONTAL", "KEY_HIT_SLOP_LEFT", "KEY_HIT_SLOP_RIGHT", "KEY_HIT_SLOP_TOP", "KEY_HIT_SLOP_VERTICAL", "KEY_HIT_SLOP_WIDTH", "KEY_LONG_PRESS_MAX_DIST", "KEY_LONG_PRESS_MIN_DURATION_MS", "KEY_MANUAL_ACTIVATION", "KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION", "KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START", "KEY_NEEDS_POINTER_DATA", "KEY_NUMBER_OF_POINTERS", "KEY_PAN_ACTIVATE_AFTER_LONG_PRESS", "KEY_PAN_ACTIVE_OFFSET_X_END", "KEY_PAN_ACTIVE_OFFSET_X_START", "KEY_PAN_ACTIVE_OFFSET_Y_END", "KEY_PAN_ACTIVE_OFFSET_Y_START", "KEY_PAN_AVG_TOUCHES", "KEY_PAN_FAIL_OFFSET_RANGE_X_END", "KEY_PAN_FAIL_OFFSET_RANGE_X_START", "KEY_PAN_FAIL_OFFSET_RANGE_Y_END", "KEY_PAN_FAIL_OFFSET_RANGE_Y_START", "KEY_PAN_MAX_POINTERS", "KEY_PAN_MIN_DIST", "KEY_PAN_MIN_POINTERS", "KEY_PAN_MIN_VELOCITY", "KEY_PAN_MIN_VELOCITY_X", "KEY_PAN_MIN_VELOCITY_Y", "KEY_SHOULD_CANCEL_WHEN_OUTSIDE", "KEY_TAP_MAX_DELAY_MS", "KEY_TAP_MAX_DELTA_X", "KEY_TAP_MAX_DELTA_Y", "KEY_TAP_MAX_DIST", "KEY_TAP_MAX_DURATION_MS", "KEY_TAP_MIN_POINTERS", "KEY_TAP_NUMBER_OF_TAPS", "NAME", "handleHitSlopProperty", "", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNGestureHandlerModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void handleHitSlopProperty(GestureHandler<?> gestureHandler, ReadableMap readableMap) {
            if (readableMap.getType(RNGestureHandlerModule.KEY_HIT_SLOP) == ReadableType.Number) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP));
                gestureHandler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
                return;
            }
            ReadableMap map = readableMap.getMap(RNGestureHandlerModule.KEY_HIT_SLOP);
            Intrinsics.checkNotNull(map);
            float f = Float.NaN;
            float pixelFromDIP2 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL)) : Float.NaN;
            float f2 = pixelFromDIP2;
            float pixelFromDIP3 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL)) : Float.NaN;
            float f3 = pixelFromDIP3;
            if (map.hasKey("left")) {
                pixelFromDIP2 = PixelUtil.toPixelFromDIP(map.getDouble("left"));
            }
            float f4 = pixelFromDIP2;
            if (map.hasKey("top")) {
                pixelFromDIP3 = PixelUtil.toPixelFromDIP(map.getDouble("top"));
            }
            float f5 = pixelFromDIP3;
            if (map.hasKey("right")) {
                f2 = PixelUtil.toPixelFromDIP(map.getDouble("right"));
            }
            float f6 = f2;
            if (map.hasKey("bottom")) {
                f3 = PixelUtil.toPixelFromDIP(map.getDouble("bottom"));
            }
            float f7 = f3;
            float pixelFromDIP4 = map.hasKey("width") ? PixelUtil.toPixelFromDIP(map.getDouble("width")) : Float.NaN;
            if (map.hasKey("height")) {
                f = PixelUtil.toPixelFromDIP(map.getDouble("height"));
            }
            gestureHandler.setHitSlop(f4, f5, f6, f7, pixelFromDIP4, f);
        }
    }
}
