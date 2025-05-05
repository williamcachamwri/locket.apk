package expo.modules.clipboard;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.core.os.BundleKt;
import androidx.tracing.Trace;
import expo.modules.imagepicker.MediaTypes;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionBuilder;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.SuspendFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108BX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001e"}, d2 = {"Lexpo/modules/clipboard/ClipboardModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "clipboardCacheDir", "Ljava/io/File;", "getClipboardCacheDir", "()Ljava/io/File;", "clipboardCacheDir$delegate", "Lkotlin/Lazy;", "clipboardEventEmitter", "Lexpo/modules/clipboard/ClipboardModule$ClipboardEventEmitter;", "clipboardManager", "Landroid/content/ClipboardManager;", "getClipboardManager", "()Landroid/content/ClipboardManager;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "firstItem", "Landroid/content/ClipData$Item;", "getFirstItem", "(Landroid/content/ClipboardManager;)Landroid/content/ClipData$Item;", "clipboardHasItemWithType", "", "mimeType", "", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "ClipboardEventEmitter", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardModule.kt */
public final class ClipboardModule extends Module {
    private final Lazy clipboardCacheDir$delegate = LazyKt.lazy(new ClipboardModule$clipboardCacheDir$2(this));
    /* access modifiers changed from: private */
    public ClipboardEventEmitter clipboardEventEmitter;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardModule.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.clipboard.StringFormat[] r0 = expo.modules.clipboard.StringFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.clipboard.StringFormat r1 = expo.modules.clipboard.StringFormat.PLAIN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.clipboard.StringFormat r1 = expo.modules.clipboard.StringFormat.HTML     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.clipboard.ClipboardModule.WhenMappings.<clinit>():void");
        }
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoClipboard");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (GetStringOptions.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getStringAsync", new AnyType[0], new ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(GetStringOptions.class), false, ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$2.INSTANCE))}, new ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getStringAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (SetStringOptions.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$4.INSTANCE))}, new ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$5(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$6.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(SetStringOptions.class), false, ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$7.INSTANCE))}, new ClipboardModule$definition$lambda$12$$inlined$AsyncFunction$8(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setStringAsync", asyncFunction2);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("hasStringAsync", new AnyType[0], new ClipboardModule$definition$lambda$12$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("hasStringAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction3 = asyncFunctionComponent;
            AsyncFunctionBuilder AsyncFunction = moduleDefinitionBuilder.AsyncFunction("getImageAsync");
            AsyncFunction.setAsyncFunctionComponent(new SuspendFunctionComponent(AsyncFunction.getName(), new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(GetImageOptions.class), false, ClipboardModule$definition$lambda$12$$inlined$Coroutine$1.INSTANCE))}, new ClipboardModule$definition$lambda$12$$inlined$Coroutine$2((Continuation) null, this)));
            AsyncFunctionBuilder AsyncFunction2 = moduleDefinitionBuilder.AsyncFunction("setImageAsync");
            AsyncFunction2.setAsyncFunctionComponent(new SuspendFunctionComponent(AsyncFunction2.getName(), new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ClipboardModule$definition$lambda$12$$inlined$Coroutine$3.INSTANCE))}, new ClipboardModule$definition$lambda$12$$inlined$Coroutine$4((Continuation) null, this)));
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("hasImageAsync", new AnyType[0], new ClipboardModule$definition$lambda$12$$inlined$AsyncFunctionWithoutArgs$2(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("hasImageAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction4 = asyncFunctionComponent2;
            moduleDefinitionBuilder.Events(ClipboardModuleKt.CLIPBOARD_CHANGED_EVENT_NAME);
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new ClipboardModule$definition$lambda$12$$inlined$OnCreate$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new ClipboardModule$definition$lambda$12$$inlined$OnDestroy$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.ACTIVITY_ENTERS_BACKGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_BACKGROUND, new ClipboardModule$definition$lambda$12$$inlined$OnActivityEntersBackground$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.ACTIVITY_ENTERS_FOREGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_FOREGROUND, new ClipboardModule$definition$lambda$12$$inlined$OnActivityEntersForeground$1(this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new IllegalArgumentException("React Application Context is null".toString());
    }

    /* access modifiers changed from: private */
    public final ClipboardManager getClipboardManager() {
        Object systemService = getContext().getSystemService("clipboard");
        ClipboardManager clipboardManager = systemService instanceof ClipboardManager ? (ClipboardManager) systemService : null;
        if (clipboardManager != null) {
            return clipboardManager;
        }
        throw new ClipboardUnavailableException();
    }

    /* access modifiers changed from: private */
    public final File getClipboardCacheDir() {
        return (File) this.clipboardCacheDir$delegate.getValue();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\u0001J\r\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lexpo/modules/clipboard/ClipboardModule$ClipboardEventEmitter;", "", "(Lexpo/modules/clipboard/ClipboardModule;)V", "isListening", "", "listener", "Landroid/content/ClipboardManager$OnPrimaryClipChangedListener;", "maybeClipboardManager", "Landroid/content/ClipboardManager;", "timestamp", "", "attachListener", "detachListener", "", "()Lkotlin/Unit;", "pauseListening", "resumeListening", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardModule.kt */
    private final class ClipboardEventEmitter {
        private boolean isListening = true;
        private final ClipboardManager.OnPrimaryClipChangedListener listener;
        private final ClipboardManager maybeClipboardManager;
        private long timestamp = -1;

        public ClipboardEventEmitter() {
            Object obj;
            this.listener = new ClipboardModule$ClipboardEventEmitter$$ExternalSyntheticLambda0(ClipboardModule.this, this);
            try {
                Result.Companion companion = Result.Companion;
                ClipboardEventEmitter clipboardEventEmitter = this;
                obj = Result.m2444constructorimpl(ClipboardModule.this.getClipboardManager());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
            }
            this.maybeClipboardManager = (ClipboardManager) (Result.m2450isFailureimpl(obj) ? null : obj);
        }

        public final void resumeListening() {
            this.isListening = true;
        }

        public final void pauseListening() {
            this.isListening = false;
        }

        public final Object attachListener() {
            Unit unit;
            ClipboardManager clipboardManager = this.maybeClipboardManager;
            if (clipboardManager != null) {
                clipboardManager.addPrimaryClipChangedListener(this.listener);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            return unit == null ? Integer.valueOf(SentryLogcatAdapter.e(ClipboardModuleKt.TAG, "'CLIPBOARD_SERVICE' unavailable. Events won't be received")) : unit;
        }

        public final Unit detachListener() {
            ClipboardManager clipboardManager = this.maybeClipboardManager;
            if (clipboardManager == null) {
                return null;
            }
            clipboardManager.removePrimaryClipChangedListener(this.listener);
            return Unit.INSTANCE;
        }

        /* access modifiers changed from: private */
        public static final void listener$lambda$7(ClipboardModule clipboardModule, ClipboardEventEmitter clipboardEventEmitter) {
            ClipDescription primaryClipDescription;
            Intrinsics.checkNotNullParameter(clipboardModule, "this$0");
            Intrinsics.checkNotNullParameter(clipboardEventEmitter, "this$1");
            if (clipboardModule.getAppContext().getHasActiveReactInstance()) {
                ClipboardManager clipboardManager = clipboardEventEmitter.maybeClipboardManager;
                ContentType contentType = null;
                if (!clipboardEventEmitter.isListening) {
                    clipboardManager = null;
                }
                if (clipboardManager != null && (primaryClipDescription = clipboardManager.getPrimaryClipDescription()) != null && clipboardEventEmitter.timestamp != primaryClipDescription.getTimestamp()) {
                    clipboardEventEmitter.timestamp = primaryClipDescription.getTimestamp();
                    Pair[] pairArr = new Pair[1];
                    ContentType[] contentTypeArr = new ContentType[3];
                    ContentType contentType2 = ContentType.PLAIN_TEXT;
                    if (!ClipboardModuleKt.getHasTextContent(primaryClipDescription)) {
                        contentType2 = null;
                    }
                    contentTypeArr[0] = contentType2;
                    ContentType contentType3 = ContentType.HTML;
                    if (!primaryClipDescription.hasMimeType("text/html")) {
                        contentType3 = null;
                    }
                    contentTypeArr[1] = contentType3;
                    ContentType contentType4 = ContentType.IMAGE;
                    if (primaryClipDescription.hasMimeType(MediaTypes.ImageAllMimeType)) {
                        contentType = contentType4;
                    }
                    contentTypeArr[2] = contentType;
                    Iterable<ContentType> listOfNotNull = CollectionsKt.listOfNotNull((T[]) contentTypeArr);
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOfNotNull, 10));
                    for (ContentType jsName : listOfNotNull) {
                        arrayList.add(jsName.getJsName());
                    }
                    pairArr[0] = TuplesKt.to("contentTypes", (List) arrayList);
                    clipboardModule.sendEvent(ClipboardModuleKt.CLIPBOARD_CHANGED_EVENT_NAME, BundleKt.bundleOf(pairArr));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean clipboardHasItemWithType(String str) {
        ClipDescription primaryClipDescription = getClipboardManager().getPrimaryClipDescription();
        if (primaryClipDescription != null) {
            return primaryClipDescription.hasMimeType(str);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final ClipData.Item getFirstItem(ClipboardManager clipboardManager) {
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip == null) {
            return null;
        }
        if (!(primaryClip.getItemCount() > 0)) {
            primaryClip = null;
        }
        if (primaryClip != null) {
            return primaryClip.getItemAt(0);
        }
        return null;
    }
}
