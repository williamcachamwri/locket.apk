package expo.modules.device;

import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DeviceModule.kt */
final class DeviceModule$definition$1$1 extends Lambda implements Function0<Map<String, ? extends Object>> {
    final /* synthetic */ ModuleDefinitionBuilder $this_ModuleDefinition;
    final /* synthetic */ DeviceModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DeviceModule$definition$1$1(DeviceModule deviceModule, ModuleDefinitionBuilder moduleDefinitionBuilder) {
        super(0);
        this.this$0 = deviceModule;
        this.$this_ModuleDefinition = moduleDefinitionBuilder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x00bc, code lost:
        if (java.lang.Boolean.valueOf(true ^ r3).booleanValue() != false) goto L_0x00c0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.String, java.lang.Object> invoke() {
        /*
            r6 = this;
            r0 = 17
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            expo.modules.device.DeviceModule$Companion r1 = expo.modules.device.DeviceModule.Companion
            boolean r1 = r1.isRunningOnEmulator()
            r2 = 1
            r1 = r1 ^ r2
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r3 = "isDevice"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r3, r1)
            r3 = 0
            r0[r3] = r1
            java.lang.String r1 = "brand"
            java.lang.String r4 = android.os.Build.BRAND
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r0[r2] = r1
            java.lang.String r1 = "manufacturer"
            java.lang.String r4 = android.os.Build.MANUFACTURER
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 2
            r0[r4] = r1
            java.lang.String r1 = "modelName"
            java.lang.String r4 = android.os.Build.MODEL
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 3
            r0[r4] = r1
            java.lang.String r1 = "designName"
            java.lang.String r4 = android.os.Build.DEVICE
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 4
            r0[r4] = r1
            java.lang.String r1 = "productName"
            java.lang.String r4 = android.os.Build.DEVICE
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 5
            r0[r4] = r1
            expo.modules.device.DeviceModule r1 = r6.this$0
            int r1 = r1.getDeviceYearClass()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r4 = "deviceYearClass"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r4, r1)
            r4 = 6
            r0[r4] = r1
            expo.modules.device.DeviceModule r1 = r6.this$0
            android.app.ActivityManager$MemoryInfo r4 = new android.app.ActivityManager$MemoryInfo
            r4.<init>()
            android.content.Context r1 = r1.getContext()
            java.lang.String r5 = "activity"
            java.lang.Object r1 = r1.getSystemService(r5)
            java.lang.String r5 = "null cannot be cast to non-null type android.app.ActivityManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r5)
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1
            r1.getMemoryInfo(r4)
            long r4 = r4.totalMem
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            java.lang.String r4 = "totalMemory"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r4, r1)
            r4 = 7
            r0[r4] = r1
            expo.modules.device.DeviceModule r1 = r6.this$0
            expo.modules.device.DeviceModule$Companion r4 = expo.modules.device.DeviceModule.Companion
            android.content.Context r1 = r1.getContext()
            expo.modules.device.DeviceModule$DeviceType r1 = r4.getDeviceType(r1)
            int r1 = r1.getJSValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r4 = "deviceType"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r4, r1)
            r4 = 8
            r0[r4] = r1
            java.lang.String[] r1 = android.os.Build.SUPPORTED_ABIS
            r4 = 0
            if (r1 == 0) goto L_0x00bf
            int r5 = r1.length
            if (r5 != 0) goto L_0x00b3
            r3 = r2
        L_0x00b3:
            r2 = r2 ^ r3
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x00bf
            goto L_0x00c0
        L_0x00bf:
            r1 = r4
        L_0x00c0:
            java.lang.String r2 = "supportedCpuArchitectures"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 9
            r0[r2] = r1
            expo.modules.device.DeviceModule r1 = r6.this$0
            java.lang.String r1 = r1.getSystemName()
            java.lang.String r2 = "osName"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 10
            r0[r2] = r1
            java.lang.String r1 = "osVersion"
            java.lang.String r2 = android.os.Build.VERSION.RELEASE
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            r2 = 11
            r0[r2] = r1
            java.lang.String r1 = "osBuildId"
            java.lang.String r2 = android.os.Build.DISPLAY
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            r2 = 12
            r0[r2] = r1
            java.lang.String r1 = "osInternalBuildId"
            java.lang.String r2 = android.os.Build.ID
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            r2 = 13
            r0[r2] = r1
            java.lang.String r1 = "osBuildFingerprint"
            java.lang.String r2 = android.os.Build.FINGERPRINT
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r2)
            r2 = 14
            r0[r2] = r1
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "platformApiLevel"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 15
            r0[r2] = r1
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r1 > r2) goto L_0x0131
            expo.modules.device.DeviceModule r1 = r6.this$0
            android.content.Context r1 = r1.getContext()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "bluetooth_name"
            java.lang.String r1 = android.provider.Settings.Secure.getString(r1, r2)
            goto L_0x0141
        L_0x0131:
            expo.modules.device.DeviceModule r1 = r6.this$0
            android.content.Context r1 = r1.getContext()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "device_name"
            java.lang.String r1 = android.provider.Settings.Global.getString(r1, r2)
        L_0x0141:
            java.lang.String r2 = "deviceName"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 16
            r0[r2] = r1
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.device.DeviceModule$definition$1$1.invoke():java.util.Map");
    }
}
