package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.RetryStrategy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CctTransportBackend$$ExternalSyntheticLambda1 implements RetryStrategy {
    public final Object shouldRetry(Object obj, Object obj2) {
        return CctTransportBackend.lambda$send$0((CctTransportBackend.HttpRequest) obj, (CctTransportBackend.HttpResponse) obj2);
    }
}
