package androidx.credentials;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CustomCredential;", "Landroidx/credentials/Credential;", "type", "", "data", "Landroid/os/Bundle;", "(Ljava/lang/String;Landroid/os/Bundle;)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CustomCredential.kt */
public class CustomCredential extends Credential {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomCredential(String str, Bundle bundle) {
        super(str, bundle);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (!(str.length() > 0)) {
            throw new IllegalArgumentException("type should not be empty".toString());
        }
    }
}
