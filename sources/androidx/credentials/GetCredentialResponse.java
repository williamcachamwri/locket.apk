package androidx.credentials;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/GetCredentialResponse;", "", "credential", "Landroidx/credentials/Credential;", "(Landroidx/credentials/Credential;)V", "getCredential", "()Landroidx/credentials/Credential;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GetCredentialResponse.kt */
public final class GetCredentialResponse {
    private final Credential credential;

    public GetCredentialResponse(Credential credential2) {
        Intrinsics.checkNotNullParameter(credential2, "credential");
        this.credential = credential2;
    }

    public final Credential getCredential() {
        return this.credential;
    }
}
