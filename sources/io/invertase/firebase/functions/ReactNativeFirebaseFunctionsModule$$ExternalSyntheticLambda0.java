package io.invertase.firebase.functions;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnSuccessListener;
import io.invertase.firebase.common.RCTConvertFirebase;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda0(Promise promise) {
        this.f$0 = promise;
    }

    public final void onSuccess(Object obj) {
        this.f$0.resolve(RCTConvertFirebase.mapPutValue("data", obj, Arguments.createMap()));
    }
}
