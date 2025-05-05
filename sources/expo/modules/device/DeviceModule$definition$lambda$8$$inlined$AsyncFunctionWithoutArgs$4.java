package expo.modules.device;

import android.os.Build;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$4 extends Lambda implements Function1<Object[], Object> {
    public DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$4() {
        super(1);
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        boolean z = true;
        boolean z2 = !DeviceModule.Companion.isRunningOnEmulator();
        String str = Build.TAGS;
        if ((!z2 || str == null || !StringsKt.contains$default((CharSequence) str, (CharSequence) "test-keys", false, 2, (Object) null)) && !new File("/system/app/Superuser.apk").exists() && (!z2 || !new File("/system/xbin/su").exists())) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
