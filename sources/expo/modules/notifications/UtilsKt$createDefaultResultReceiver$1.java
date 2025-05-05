package expo.modules.notifications;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b"}, d2 = {"expo/modules/notifications/UtilsKt$createDefaultResultReceiver$1", "Landroid/os/ResultReceiver;", "onReceiveResult", "", "resultCode", "", "resultData", "Landroid/os/Bundle;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt$createDefaultResultReceiver$1 extends ResultReceiver {
    final /* synthetic */ Function2<Integer, Bundle, Unit> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$createDefaultResultReceiver$1(Handler handler, Function2<? super Integer, ? super Bundle, Unit> function2) {
        super(handler);
        this.$body = function2;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        super.onReceiveResult(i, bundle);
        this.$body.invoke(Integer.valueOf(i), bundle);
    }
}
