package androidx.camera.extensions.internal;

import androidx.camera.core.impl.Config;
import androidx.camera.extensions.internal.RequestOptionConfig;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RequestOptionConfig$Builder$$ExternalSyntheticLambda0 implements Config.OptionMatcher {
    public final /* synthetic */ RequestOptionConfig.Builder f$0;
    public final /* synthetic */ Config f$1;

    public /* synthetic */ RequestOptionConfig$Builder$$ExternalSyntheticLambda0(RequestOptionConfig.Builder builder, Config config) {
        this.f$0 = builder;
        this.f$1 = config;
    }

    public final boolean onOptionMatched(Config.Option option) {
        return this.f$0.mMutableOptionsBundle.insertOption(option, this.f$1.getOptionPriority(option), this.f$1.retrieveOption(option));
    }
}
