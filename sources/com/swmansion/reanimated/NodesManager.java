package com.swmansion.reanimated;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerReanimatedHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.swmansion.reanimated.layoutReanimation.AnimationsManager;
import com.swmansion.reanimated.nativeProxy.NoopEventHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class NodesManager implements EventDispatcherListener {
    private ReaCompatibility compatibility;
    private double lastFrameTimeMs;
    private AnimationsManager mAnimationManager;
    private final AtomicBoolean mCallbackPosted = new AtomicBoolean();
    private final GuardedFrameCallback mChoreographerCallback;
    private final ReactContext mContext;
    private RCTEventEmitter mCustomEventHandler = new NoopEventHandler();
    protected final UIManagerModule.CustomEventNamesResolver mCustomEventNamesResolver;
    private final DeviceEventManagerModule.RCTDeviceEventEmitter mEventEmitter;
    private ConcurrentLinkedQueue<CopiedEvent> mEventQueue = new ConcurrentLinkedQueue<>();
    private List<OnAnimationFrame> mFrameCallbacks = new ArrayList();
    private NativeProxy mNativeProxy;
    private Queue<NativeUpdateOperation> mOperationsInBatch = new LinkedList();
    private ReactApplicationContext mReactApplicationContext;
    private final ReactChoreographer mReactChoreographer;
    private boolean mTryRunBatchUpdatesSynchronously = false;
    /* access modifiers changed from: private */
    public final UIImplementation mUIImplementation;
    /* access modifiers changed from: private */
    public final UIManagerModule mUIManager;
    public Set<String> nativeProps = Collections.emptySet();
    public Set<String> uiProps = Collections.emptySet();

    public interface OnAnimationFrame {
        void onAnimationFrame(double d);
    }

    public void scrollTo(int i, double d, double d2, boolean z) {
        try {
            NativeMethodsHelper.scrollTo(this.mUIManager.resolveView(i), d, d2, z);
        } catch (IllegalViewOperationException e) {
            e.printStackTrace();
        }
    }

    public void dispatchCommand(int i, String str, ReadableArray readableArray) {
        final int i2 = i;
        final String str2 = str;
        final ReadableArray readableArray2 = readableArray;
        this.mContext.runOnNativeModulesQueueThread(new GuardedRunnable(this.mContext.getExceptionHandler()) {
            public void runGuarded() {
                NodesManager.this.mUIManager.dispatchCommand(i2, str2, readableArray2);
            }
        });
    }

    public float[] measure(int i) {
        try {
            return NativeMethodsHelper.measure(this.mUIManager.resolveView(i));
        } catch (IllegalViewOperationException e) {
            e.printStackTrace();
            return new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
        }
    }

    public NativeProxy getNativeProxy() {
        return this.mNativeProxy;
    }

    public AnimationsManager getAnimationsManager() {
        return this.mAnimationManager;
    }

    public void onCatalystInstanceDestroy() {
        AnimationsManager animationsManager = this.mAnimationManager;
        if (animationsManager != null) {
            animationsManager.onCatalystInstanceDestroy();
        }
        NativeProxy nativeProxy = this.mNativeProxy;
        if (nativeProxy != null) {
            nativeProxy.onCatalystInstanceDestroy();
            this.mNativeProxy = null;
        }
    }

    public void initWithContext(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mNativeProxy = new NativeProxy(reactApplicationContext);
        this.mAnimationManager.setAndroidUIScheduler(getNativeProxy().getAndroidUIScheduler());
        ReaCompatibility reaCompatibility = new ReaCompatibility(reactApplicationContext);
        this.compatibility = reaCompatibility;
        reaCompatibility.registerFabricEventListener(this);
    }

    private final class NativeUpdateOperation {
        public WritableMap mNativeProps;
        public int mViewTag;

        public NativeUpdateOperation(int i, WritableMap writableMap) {
            this.mViewTag = i;
            this.mNativeProps = writableMap;
        }
    }

    public NodesManager(ReactContext reactContext) {
        this.mContext = reactContext;
        UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        this.mUIManager = uIManagerModule;
        this.mUIImplementation = uIManagerModule.getUIImplementation();
        this.mCustomEventNamesResolver = uIManagerModule.getDirectEventNamesResolver();
        this.mEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        this.mReactChoreographer = ReactChoreographer.getInstance();
        this.mChoreographerCallback = new GuardedFrameCallback(reactContext) {
            /* access modifiers changed from: protected */
            public void doFrameGuarded(long j) {
                NodesManager.this.onAnimationFrame(j);
            }
        };
        uIManagerModule.getEventDispatcher().addListener(this);
        this.mAnimationManager = new AnimationsManager(reactContext, uIManagerModule);
    }

    public void onHostPause() {
        if (this.mCallbackPosted.get()) {
            stopUpdatingOnAnimationFrame();
            this.mCallbackPosted.set(true);
        }
    }

    public boolean isAnimationRunning() {
        return this.mCallbackPosted.get();
    }

    public void onHostResume() {
        if (this.mCallbackPosted.getAndSet(false)) {
            startUpdatingOnAnimationFrame();
        }
    }

    public void startUpdatingOnAnimationFrame() {
        if (!this.mCallbackPosted.getAndSet(true)) {
            this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mChoreographerCallback);
        }
    }

    private void stopUpdatingOnAnimationFrame() {
        if (this.mCallbackPosted.getAndSet(false)) {
            this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mChoreographerCallback);
        }
    }

    public void performOperations() {
        if (!this.mOperationsInBatch.isEmpty()) {
            final Queue<NativeUpdateOperation> queue = this.mOperationsInBatch;
            this.mOperationsInBatch = new LinkedList();
            boolean z = this.mTryRunBatchUpdatesSynchronously;
            this.mTryRunBatchUpdatesSynchronously = false;
            Semaphore semaphore = new Semaphore(0);
            final boolean z2 = z;
            final Semaphore semaphore2 = semaphore;
            this.mContext.runOnNativeModulesQueueThread(new GuardedRunnable(this.mContext.getExceptionHandler()) {
                public void runGuarded() {
                    boolean isOperationQueueEmpty = UIManagerReanimatedHelper.isOperationQueueEmpty(NodesManager.this.mUIImplementation);
                    boolean z = z2 && isOperationQueueEmpty;
                    if (!z) {
                        semaphore2.release();
                    }
                    while (!queue.isEmpty()) {
                        NativeUpdateOperation nativeUpdateOperation = (NativeUpdateOperation) queue.remove();
                        ReactShadowNode resolveShadowNode = NodesManager.this.mUIImplementation.resolveShadowNode(nativeUpdateOperation.mViewTag);
                        if (resolveShadowNode != null) {
                            NodesManager.this.mUIManager.updateView(nativeUpdateOperation.mViewTag, resolveShadowNode.getViewClass(), nativeUpdateOperation.mNativeProps);
                        }
                    }
                    if (isOperationQueueEmpty) {
                        NodesManager.this.mUIImplementation.dispatchViewUpdates(-1);
                    }
                    if (z) {
                        semaphore2.release();
                    }
                }
            });
            if (z) {
                try {
                    semaphore.tryAcquire(16, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onAnimationFrame(long j) {
        double d = ((double) j) / 1000000.0d;
        if (d > this.lastFrameTimeMs) {
            this.lastFrameTimeMs = d;
            while (!this.mEventQueue.isEmpty()) {
                CopiedEvent poll = this.mEventQueue.poll();
                handleEvent(poll.getTargetTag(), poll.getEventName(), poll.getPayload());
            }
            if (!this.mFrameCallbacks.isEmpty()) {
                List<OnAnimationFrame> list = this.mFrameCallbacks;
                this.mFrameCallbacks = new ArrayList(list.size());
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    list.get(i).onAnimationFrame(d);
                }
            }
            performOperations();
        }
        this.mCallbackPosted.set(false);
        if (!this.mFrameCallbacks.isEmpty() || !this.mEventQueue.isEmpty()) {
            startUpdatingOnAnimationFrame();
        }
    }

    public void enqueueUpdateViewOnNativeThread(int i, WritableMap writableMap, boolean z) {
        if (z) {
            this.mTryRunBatchUpdatesSynchronously = true;
        }
        this.mOperationsInBatch.add(new NativeUpdateOperation(i, writableMap));
    }

    public void configureProps(Set<String> set, Set<String> set2) {
        this.uiProps = set;
        this.nativeProps = set2;
    }

    public void postOnAnimation(OnAnimationFrame onAnimationFrame) {
        this.mFrameCallbacks.add(onAnimationFrame);
        startUpdatingOnAnimationFrame();
    }

    public void onEventDispatch(Event event) {
        if (this.mNativeProxy != null) {
            if (UiThreadUtil.isOnUiThread()) {
                handleEvent(event);
                performOperations();
                return;
            }
            if (this.mNativeProxy.isAnyHandlerWaitingForEvent(this.mCustomEventNamesResolver.resolveCustomEventName(event.getEventName()), event.getViewTag())) {
                this.mEventQueue.offer(new CopiedEvent(event));
            }
            startUpdatingOnAnimationFrame();
        }
    }

    private void handleEvent(Event event) {
        event.dispatch(this.mCustomEventHandler);
    }

    private void handleEvent(int i, String str, @Nullable WritableMap writableMap) {
        this.mCustomEventHandler.receiveEvent(i, str, writableMap);
    }

    public UIManagerModule.CustomEventNamesResolver getEventNameResolver() {
        return this.mCustomEventNamesResolver;
    }

    public void registerEventHandler(RCTEventEmitter rCTEventEmitter) {
        this.mCustomEventHandler = rCTEventEmitter;
    }

    public void sendEvent(String str, WritableMap writableMap) {
        this.mEventEmitter.emit(str, writableMap);
    }

    public void updateProps(int i, Map<String, Object> map) {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (this.uiProps.contains(str)) {
                addProp(javaOnlyMap, str, value);
                z = true;
            } else if (this.nativeProps.contains(str)) {
                addProp(createMap2, str, value);
                z2 = true;
            } else {
                addProp(createMap, str, value);
                z3 = true;
            }
        }
        if (i != -1) {
            if (z) {
                this.mUIImplementation.synchronouslyUpdateViewOnUIThread(i, new ReactStylesDiffMap(javaOnlyMap));
            }
            if (z2) {
                enqueueUpdateViewOnNativeThread(i, createMap2, true);
            }
            if (z3) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putInt("viewTag", i);
                createMap3.putMap("props", createMap);
                sendEvent("onReanimatedPropsChange", createMap3);
            }
        }
    }

    public void synchronouslyUpdateUIProps(int i, ReadableMap readableMap) {
        this.compatibility.synchronouslyUpdateUIProps(i, readableMap);
    }

    public String obtainProp(int i, String str) {
        View resolveView = this.mUIManager.resolveView(i);
        String str2 = "error: unknown propName " + str + ", currently supported: opacity, zIndex";
        if (str.equals(ViewProps.OPACITY)) {
            return Float.toString(Float.valueOf(resolveView.getAlpha()).floatValue());
        }
        return str.equals(ViewProps.Z_INDEX) ? Float.toString(Float.valueOf(resolveView.getElevation()).floatValue()) : str2;
    }

    private static WritableMap copyReadableMap(ReadableMap readableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        return createMap;
    }

    private static WritableArray copyReadableArray(ReadableArray readableArray) {
        WritableArray createArray = Arguments.createArray();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass4.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    createArray.pushBoolean(readableArray.getBoolean(i));
                    break;
                case 2:
                    createArray.pushString(readableArray.getString(i));
                    break;
                case 3:
                    createArray.pushNull();
                    break;
                case 4:
                    createArray.pushDouble(readableArray.getDouble(i));
                    break;
                case 5:
                    createArray.pushMap(copyReadableMap(readableArray.getMap(i)));
                    break;
                case 6:
                    createArray.pushArray(copyReadableArray(readableArray.getArray(i)));
                    break;
                default:
                    throw new IllegalStateException("[Reanimated] Unknown type of ReadableArray.");
            }
        }
        return createArray;
    }

    /* renamed from: com.swmansion.reanimated.NodesManager$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.reanimated.NodesManager.AnonymousClass4.<clinit>():void");
        }
    }

    private static void addProp(WritableMap writableMap, String str, Object obj) {
        if (obj == null) {
            writableMap.putNull(str);
        } else if (obj instanceof Double) {
            writableMap.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Integer) {
            writableMap.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Number) {
            writableMap.putDouble(str, ((Number) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof String) {
            writableMap.putString(str, (String) obj);
        } else if (obj instanceof ReadableArray) {
            if (!(obj instanceof WritableArray)) {
                writableMap.putArray(str, copyReadableArray((ReadableArray) obj));
            } else {
                writableMap.putArray(str, (ReadableArray) obj);
            }
        } else if (!(obj instanceof ReadableMap)) {
            throw new IllegalStateException("[Reanimated] Unknown type of animated value.");
        } else if (!(obj instanceof WritableMap)) {
            writableMap.putMap(str, copyReadableMap((ReadableMap) obj));
        } else {
            writableMap.putMap(str, (ReadableMap) obj);
        }
    }
}
