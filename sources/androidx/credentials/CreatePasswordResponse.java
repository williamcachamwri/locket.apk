package androidx.credentials;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CreatePasswordResponse;", "Landroidx/credentials/CreateCredentialResponse;", "()V", "data", "Landroid/os/Bundle;", "(Landroid/os/Bundle;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePasswordResponse.kt */
public final class CreatePasswordResponse extends CreateCredentialResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public /* synthetic */ CreatePasswordResponse(Bundle bundle, DefaultConstructorMarker defaultConstructorMarker) {
        this(bundle);
    }

    @JvmStatic
    public static final CreatePasswordResponse createFrom$credentials_release(Bundle bundle) {
        return Companion.createFrom$credentials_release(bundle);
    }

    private CreatePasswordResponse(Bundle bundle) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle);
    }

    public CreatePasswordResponse() {
        this(new Bundle());
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/CreatePasswordResponse$Companion;", "", "()V", "createFrom", "Landroidx/credentials/CreatePasswordResponse;", "data", "Landroid/os/Bundle;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreatePasswordResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CreatePasswordResponse createFrom$credentials_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            return new CreatePasswordResponse(bundle, (DefaultConstructorMarker) null);
        }
    }
}
