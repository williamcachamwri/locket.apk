package expo.modules.device;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.tracing.Trace;
import com.amplitude.api.Constants;
import com.facebook.device.yearclass.YearClass;
import expo.modules.core.utilities.EmulatorUtilities;
import expo.modules.kotlin.Promise;
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
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lexpo/modules/device/DeviceModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "deviceYearClass", "", "getDeviceYearClass", "()I", "systemName", "", "getSystemName", "()Ljava/lang/String;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "Companion", "DeviceType", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DeviceModule.kt */
public final class DeviceModule extends Module {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lexpo/modules/device/DeviceModule$DeviceType;", "", "JSValue", "", "(Ljava/lang/String;II)V", "getJSValue", "()I", "UNKNOWN", "PHONE", "TABLET", "DESKTOP", "TV", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DeviceModule.kt */
    public enum DeviceType {
        UNKNOWN(0),
        PHONE(1),
        TABLET(2),
        DESKTOP(3),
        TV(4);
        
        private final int JSValue;

        public static EnumEntries<DeviceType> getEntries() {
            return $ENTRIES;
        }

        private DeviceType(int i) {
            this.JSValue = i;
        }

        public final int getJSValue() {
            return this.JSValue;
        }

        static {
            DeviceType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoDevice");
            moduleDefinitionBuilder.Constants((Function0<? extends Map<String, ? extends Object>>) new DeviceModule$definition$1$1(this, moduleDefinitionBuilder));
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getDeviceTypeAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getDeviceTypeAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getUptimeAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$2());
            moduleDefinitionBuilder.getAsyncFunctions().put("getUptimeAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction3 = asyncFunctionComponent2;
            AsyncFunctionComponent asyncFunctionComponent3 = new AsyncFunctionComponent("getMaxMemoryAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$3());
            moduleDefinitionBuilder.getAsyncFunctions().put("getMaxMemoryAsync", asyncFunctionComponent3);
            AsyncFunction asyncFunction4 = asyncFunctionComponent3;
            AsyncFunctionComponent asyncFunctionComponent4 = new AsyncFunctionComponent("isRootedExperimentalAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$4());
            moduleDefinitionBuilder.getAsyncFunctions().put("isRootedExperimentalAsync", asyncFunctionComponent4);
            AsyncFunction asyncFunction5 = asyncFunctionComponent4;
            AsyncFunctionComponent asyncFunctionComponent5 = new AsyncFunctionComponent("isSideLoadingEnabledAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$5(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("isSideLoadingEnabledAsync", asyncFunctionComponent5);
            AsyncFunction asyncFunction6 = asyncFunctionComponent5;
            AsyncFunctionComponent asyncFunctionComponent6 = new AsyncFunctionComponent("getPlatformFeaturesAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$6(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getPlatformFeaturesAsync", asyncFunctionComponent6);
            AsyncFunction asyncFunction7 = asyncFunctionComponent6;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("hasPlatformFeatureAsync", new AnyType[0], new DeviceModule$definition$lambda$8$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("hasPlatformFeatureAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, DeviceModule$definition$lambda$8$$inlined$AsyncFunction$2.INSTANCE))}, new DeviceModule$definition$lambda$8$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("hasPlatformFeatureAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final int getDeviceYearClass() {
        return YearClass.get(getContext());
    }

    /* access modifiers changed from: private */
    public final String getSystemName() {
        String str = Build.VERSION.BASE_OS;
        Intrinsics.checkNotNull(str);
        if (!(str.length() > 0)) {
            str = null;
        }
        return str == null ? Constants.PLATFORM : str;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005¨\u0006\f"}, d2 = {"Lexpo/modules/device/DeviceModule$Companion;", "", "()V", "isRunningOnEmulator", "", "()Z", "getDeviceType", "Lexpo/modules/device/DeviceModule$DeviceType;", "context", "Landroid/content/Context;", "getDeviceTypeFromPhysicalSize", "getDeviceTypeFromResourceConfiguration", "expo-device_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DeviceModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final boolean isRunningOnEmulator() {
            return EmulatorUtilities.INSTANCE.isRunningOnEmulator();
        }

        /* access modifiers changed from: private */
        public final DeviceType getDeviceType(Context context) {
            if (context.getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
                return DeviceType.TV;
            }
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
                return DeviceType.TV;
            }
            DeviceType deviceTypeFromResourceConfiguration = getDeviceTypeFromResourceConfiguration(context);
            return deviceTypeFromResourceConfiguration != DeviceType.UNKNOWN ? deviceTypeFromResourceConfiguration : getDeviceTypeFromPhysicalSize(context);
        }

        private final DeviceType getDeviceTypeFromResourceConfiguration(Context context) {
            int i = context.getResources().getConfiguration().smallestScreenWidthDp;
            if (i == 0) {
                return DeviceType.UNKNOWN;
            }
            if (i >= 600) {
                return DeviceType.TABLET;
            }
            return DeviceType.PHONE;
        }

        private final DeviceType getDeviceTypeFromPhysicalSize(Context context) {
            double d;
            double d2;
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return DeviceType.UNKNOWN;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                Rect bounds = windowManager.getCurrentWindowMetrics().getBounds();
                Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
                double d3 = (double) context.getResources().getConfiguration().densityDpi;
                d2 = ((double) bounds.width()) / d3;
                d = ((double) bounds.height()) / d3;
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
                d2 = ((double) displayMetrics.widthPixels) / ((double) displayMetrics.xdpi);
                d = ((double) displayMetrics.heightPixels) / ((double) displayMetrics.ydpi);
            }
            double sqrt = Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(d, 2.0d));
            boolean z = false;
            if (3.0d <= sqrt && sqrt <= 6.9d) {
                z = true;
            }
            if (z) {
                return DeviceType.PHONE;
            }
            if (sqrt <= 6.9d || sqrt > 18.0d) {
                return DeviceType.UNKNOWN;
            }
            return DeviceType.TABLET;
        }
    }
}
