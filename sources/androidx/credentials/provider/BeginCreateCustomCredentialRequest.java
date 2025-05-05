package androidx.credentials.provider;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginCreateCustomCredentialRequest;", "Landroidx/credentials/provider/BeginCreateCredentialRequest;", "type", "", "candidateQueryData", "Landroid/os/Bundle;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Ljava/lang/String;Landroid/os/Bundle;Landroidx/credentials/provider/CallingAppInfo;)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginCreateCustomCredentialRequest.kt */
public class BeginCreateCustomCredentialRequest extends BeginCreateCredentialRequest {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeginCreateCustomCredentialRequest(String str, Bundle bundle, CallingAppInfo callingAppInfo) {
        super(str, bundle, callingAppInfo);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
        if (!(str.length() > 0)) {
            throw new IllegalArgumentException("type should not be empty".toString());
        }
    }
}
