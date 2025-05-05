package expo.modules.intentlauncher;

import android.app.Activity;
import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/intentlauncher/IntentLauncherModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "pendingPromise", "Lexpo/modules/kotlin/Promise;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-intent-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IntentLauncherModule.kt */
public final class IntentLauncherModule extends Module {
    /* access modifiers changed from: private */
    public Promise pendingPromise;

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* access modifiers changed from: private */
    public final Activity getCurrentActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoIntentLauncher");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("startActivity", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(IntentLauncherParams.class), false, IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$2.INSTANCE))}, new IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$3(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("startActivity", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(IntentLauncherParams.class), false, IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$6.INSTANCE))}, new IntentLauncherModule$definition$lambda$10$$inlined$AsyncFunction$7(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("startActivity", asyncFunction);
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new IntentLauncherModule$definition$lambda$10$$inlined$OnActivityResult$1(this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
