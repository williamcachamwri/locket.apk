package androidx.credentials;

import androidx.credentials.PrepareGetCredentialResponse;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrepareGetCredentialResponse.kt */
/* synthetic */ class PrepareGetCredentialResponse$Builder$setFrameworkResponse$1 extends FunctionReferenceImpl implements Function1<String, Boolean> {
    PrepareGetCredentialResponse$Builder$setFrameworkResponse$1(Object obj) {
        super(1, obj, PrepareGetCredentialResponse.Builder.class, "hasCredentialType", "hasCredentialType(Ljava/lang/String;)Z", 0);
    }

    public final Boolean invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return Boolean.valueOf(((PrepareGetCredentialResponse.Builder) this.receiver).hasCredentialType(str));
    }
}
