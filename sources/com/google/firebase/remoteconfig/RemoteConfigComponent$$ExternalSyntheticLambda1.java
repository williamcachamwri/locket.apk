package com.google.firebase.remoteconfig;

import com.google.android.gms.common.util.BiConsumer;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.Personalization;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RemoteConfigComponent$$ExternalSyntheticLambda1 implements BiConsumer {
    public final /* synthetic */ Personalization f$0;

    public /* synthetic */ RemoteConfigComponent$$ExternalSyntheticLambda1(Personalization personalization) {
        this.f$0 = personalization;
    }

    public final void accept(Object obj, Object obj2) {
        this.f$0.logArmActive((String) obj, (ConfigContainer) obj2);
    }
}
