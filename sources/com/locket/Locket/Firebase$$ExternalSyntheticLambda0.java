package com.locket.Locket;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.locket.Locket.Firebase;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Firebase$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ List f$4;
    public final /* synthetic */ Integer f$5;
    public final /* synthetic */ Integer f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ Integer f$8;
    public final /* synthetic */ Firebase.CustomOnCompleteListener f$9;

    public /* synthetic */ Firebase$$ExternalSyntheticLambda0(String str, String str2, String str3, String str4, List list, Integer num, Integer num2, String str5, Integer num3, Firebase.CustomOnCompleteListener customOnCompleteListener) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = str3;
        this.f$3 = str4;
        this.f$4 = list;
        this.f$5 = num;
        this.f$6 = num2;
        this.f$7 = str5;
        this.f$8 = num3;
        this.f$9 = customOnCompleteListener;
    }

    public final void onComplete(Task task) {
        Firebase.lambda$getLatestMomentWithUser$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, task);
    }
}
