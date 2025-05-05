package expo.modules.notifications.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationsService.kt */
final class NotificationsService$onReceive$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Intent $intent;
    final /* synthetic */ BroadcastReceiver.PendingResult $pendingIntent;
    final /* synthetic */ NotificationsService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NotificationsService$onReceive$1(NotificationsService notificationsService, Context context, Intent intent, BroadcastReceiver.PendingResult pendingResult) {
        super(0);
        this.this$0 = notificationsService;
        this.$context = context;
        this.$intent = intent;
        this.$pendingIntent = pendingResult;
    }

    public final void invoke() {
        try {
            this.this$0.handleIntent(this.$context, this.$intent);
        } finally {
            this.$pendingIntent.finish();
        }
    }
}
