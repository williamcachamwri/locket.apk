package com.locket.Locket;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.locket.Locket.Firebase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Firebase$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ Firebase.CustomOnCompleteListener f$0;
    public final /* synthetic */ Gson f$1;

    public /* synthetic */ Firebase$$ExternalSyntheticLambda1(Firebase.CustomOnCompleteListener customOnCompleteListener, Gson gson) {
        this.f$0 = customOnCompleteListener;
        this.f$1 = gson;
    }

    public final void onComplete(Task task) {
        Firebase.lambda$getLatestMomentWithUser$1(this.f$0, this.f$1, task);
    }
}
