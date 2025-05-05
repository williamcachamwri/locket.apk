package expo.modules.storereview;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewManager;
import expo.modules.kotlin.Promise;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StoreReviewModule$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;
    public final /* synthetic */ ReviewManager f$1;
    public final /* synthetic */ StoreReviewModule f$2;

    public /* synthetic */ StoreReviewModule$$ExternalSyntheticLambda0(Promise promise, ReviewManager reviewManager, StoreReviewModule storeReviewModule) {
        this.f$0 = promise;
        this.f$1 = reviewManager;
        this.f$2 = storeReviewModule;
    }

    public final void onComplete(Task task) {
        StoreReviewModule.requestReview$lambda$5(this.f$0, this.f$1, this.f$2, task);
    }
}
