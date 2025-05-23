package androidx.credentials.playservices;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: HiddenActivity.kt */
final class HiddenActivity$handleGetSignInIntent$1$1 extends Lambda implements Function1<PendingIntent, Unit> {
    final /* synthetic */ int $requestCode;
    final /* synthetic */ HiddenActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HiddenActivity$handleGetSignInIntent$1$1(HiddenActivity hiddenActivity, int i) {
        super(1);
        this.this$0 = hiddenActivity;
        this.$requestCode = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PendingIntent) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PendingIntent pendingIntent) {
        try {
            this.this$0.mWaitingForActivityResult = true;
            this.this$0.startIntentSenderForResult(pendingIntent.getIntentSender(), this.$requestCode, (Intent) null, 0, 0, 0, (Bundle) null);
        } catch (IntentSender.SendIntentException e) {
            HiddenActivity hiddenActivity = this.this$0;
            ResultReceiver access$getResultReceiver$p = hiddenActivity.resultReceiver;
            Intrinsics.checkNotNull(access$getResultReceiver$p);
            hiddenActivity.setupFailure(access$getResultReceiver$p, CredentialProviderBaseController.GET_UNKNOWN, "During get sign-in intent, one tap ui intent sender failure: " + e.getMessage());
        }
    }
}
