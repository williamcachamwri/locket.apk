package expo.modules.navigationbar;

import android.app.Activity;
import android.graphics.Color;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.Queues;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lexpo/modules/navigationbar/NavigationBarModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NavigationBarModule.kt */
public final class NavigationBarModule extends Module {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String VISIBILITY_EVENT_NAME = "ExpoNavigationBar.didChange";

    /* access modifiers changed from: private */
    public final Activity getActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNavigationBar");
            moduleDefinitionBuilder.Events(VISIBILITY_EVENT_NAME);
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("setBackgroundColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$1.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$2(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("setBackgroundColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$4.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$5(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("setBackgroundColorAsync", asyncFunction);
            asyncFunction.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getBackgroundColorAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getBackgroundColorAsync", asyncFunctionComponent);
            asyncFunctionComponent.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setBorderColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$6.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setBorderColorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Integer.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$9.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$10(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setBorderColorAsync", asyncFunction2);
            asyncFunction2.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getBorderColorAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$2(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getBorderColorAsync", asyncFunctionComponent2);
            asyncFunctionComponent2.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("setButtonStyleAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$11.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$12(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("setButtonStyleAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$13.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$14.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$15(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("setButtonStyleAsync", asyncFunction3);
            asyncFunction3.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent3 = new AsyncFunctionComponent("getButtonStyleAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$3(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getButtonStyleAsync", asyncFunctionComponent3);
            asyncFunctionComponent3.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("setVisibilityAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$16.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$17(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("setVisibilityAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$18.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$19.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$20(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("setVisibilityAsync", asyncFunction4);
            asyncFunction4.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent4 = new AsyncFunctionComponent("getVisibilityAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$4(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getVisibilityAsync", asyncFunctionComponent4);
            asyncFunctionComponent4.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("setPositionAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$21.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$22(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("setPositionAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$23.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$24.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$25(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("setPositionAsync", asyncFunction5);
            asyncFunction5.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent5 = new AsyncFunctionComponent("unstable_getPositionAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$5(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("unstable_getPositionAsync", asyncFunctionComponent5);
            asyncFunctionComponent5.runOnQueue(Queues.MAIN);
            ObjectDefinitionBuilder objectDefinitionBuilder6 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction6 = new AsyncFunctionWithPromiseComponent("setBehaviorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$26.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$27(this));
            } else {
                asyncFunction6 = new AsyncFunctionComponent("setBehaviorAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$28.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$29.INSTANCE))}, new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunction$30(this));
            }
            objectDefinitionBuilder6.getAsyncFunctions().put("setBehaviorAsync", asyncFunction6);
            asyncFunction6.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent6 = new AsyncFunctionComponent("getBehaviorAsync", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$6(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getBehaviorAsync", asyncFunctionComponent6);
            asyncFunctionComponent6.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent7 = new AsyncFunctionComponent("startObserving", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$7(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("startObserving", asyncFunctionComponent7);
            asyncFunctionComponent7.runOnQueue(Queues.MAIN);
            AsyncFunctionComponent asyncFunctionComponent8 = new AsyncFunctionComponent("stopObserving", new AnyType[0], new NavigationBarModule$definition$lambda$16$$inlined$AsyncFunctionWithoutArgs$8(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("stopObserving", asyncFunctionComponent8);
            asyncFunctionComponent8.runOnQueue(Queues.MAIN);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lexpo/modules/navigationbar/NavigationBarModule$Companion;", "", "()V", "VISIBILITY_EVENT_NAME", "", "colorToHex", "color", "", "expo-navigation-bar_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NavigationBarModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String colorToHex(int i) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("#%02x%02x%02x", Arrays.copyOf(new Object[]{Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i))}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
    }
}
