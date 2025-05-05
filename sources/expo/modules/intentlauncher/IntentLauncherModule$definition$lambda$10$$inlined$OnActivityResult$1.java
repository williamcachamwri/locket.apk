package expo.modules.intentlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.OnActivityResultPayload;
import io.sentry.SentryBaseEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "sender", "Landroid/app/Activity;", "payload", "Lexpo/modules/kotlin/events/OnActivityResultPayload;", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnActivityResult$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class IntentLauncherModule$definition$lambda$10$$inlined$OnActivityResult$1 extends Lambda implements Function2<Activity, OnActivityResultPayload, Unit> {
    final /* synthetic */ IntentLauncherModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public IntentLauncherModule$definition$lambda$10$$inlined$OnActivityResult$1(IntentLauncherModule intentLauncherModule) {
        super(2);
        this.this$0 = intentLauncherModule;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Activity) obj, (OnActivityResultPayload) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Activity activity, OnActivityResultPayload onActivityResultPayload) {
        Bundle extras;
        Intrinsics.checkNotNullParameter(activity, "sender");
        Intrinsics.checkNotNullParameter(onActivityResultPayload, "payload");
        if (onActivityResultPayload.getRequestCode() == 12) {
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", onActivityResultPayload.getResultCode());
            if (onActivityResultPayload.getData() != null) {
                Intent data = onActivityResultPayload.getData();
                if (data != null) {
                    bundle.putString("data", data.toString());
                }
                Intent data2 = onActivityResultPayload.getData();
                if (!(data2 == null || (extras = data2.getExtras()) == null)) {
                    bundle.putBundle(SentryBaseEvent.JsonKeys.EXTRA, extras);
                }
            }
            Promise access$getPendingPromise$p = this.this$0.pendingPromise;
            if (access$getPendingPromise$p != null) {
                access$getPendingPromise$p.resolve(bundle);
            }
            this.this$0.pendingPromise = null;
        }
    }
}
