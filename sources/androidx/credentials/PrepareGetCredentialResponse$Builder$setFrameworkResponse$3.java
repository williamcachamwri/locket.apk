package androidx.credentials;

import androidx.credentials.PrepareGetCredentialResponse;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrepareGetCredentialResponse.kt */
/* synthetic */ class PrepareGetCredentialResponse$Builder$setFrameworkResponse$3 extends FunctionReferenceImpl implements Function0<Boolean> {
    PrepareGetCredentialResponse$Builder$setFrameworkResponse$3(Object obj) {
        super(0, obj, PrepareGetCredentialResponse.Builder.class, "hasRemoteResults", "hasRemoteResults()Z", 0);
    }

    public final Boolean invoke() {
        return Boolean.valueOf(((PrepareGetCredentialResponse.Builder) this.receiver).hasRemoteResults());
    }
}
