package io.invertase.firebase.dynamiclinks;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda5 implements OnCompleteListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebaseDynamicLinksModule$$ExternalSyntheticLambda5(Promise promise) {
        this.f$0 = promise;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebaseDynamicLinksModule.lambda$buildShortLink$3(this.f$0, task);
    }
}
