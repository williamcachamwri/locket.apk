package com.google.firebase.messaging;

import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class EnhancedIntentService$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ EnhancedIntentService f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ EnhancedIntentService$$ExternalSyntheticLambda1(EnhancedIntentService enhancedIntentService, Intent intent) {
        this.f$0 = enhancedIntentService;
        this.f$1 = intent;
    }

    public final void onComplete(Task task) {
        this.f$0.m781lambda$onStartCommand$1$comgooglefirebasemessagingEnhancedIntentService(this.f$1, task);
    }
}
