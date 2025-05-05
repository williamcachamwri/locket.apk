package expo.modules.storereview;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import expo.modules.kotlin.Promise;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StoreReviewModule$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ StoreReviewModule$$ExternalSyntheticLambda1(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Task task) {
        StoreReviewModule.requestReview$lambda$5$lambda$4$lambda$3(this.f$0, task);
    }
}
