package io.sentry;

import io.sentry.clientreport.ClientReport;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SentryEnvelopeItem$$ExternalSyntheticLambda6 implements Callable {
    public final /* synthetic */ ISerializer f$0;
    public final /* synthetic */ ClientReport f$1;

    public /* synthetic */ SentryEnvelopeItem$$ExternalSyntheticLambda6(ISerializer iSerializer, ClientReport clientReport) {
        this.f$0 = iSerializer;
        this.f$1 = clientReport;
    }

    public final Object call() {
        return SentryEnvelopeItem.lambda$fromClientReport$18(this.f$0, this.f$1);
    }
}
