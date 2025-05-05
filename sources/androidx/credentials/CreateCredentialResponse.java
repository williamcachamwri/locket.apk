package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/credentials/CreateCredentialResponse;", "", "type", "", "data", "Landroid/os/Bundle;", "(Ljava/lang/String;Landroid/os/Bundle;)V", "getData", "()Landroid/os/Bundle;", "getType", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreateCredentialResponse.kt */
public abstract class CreateCredentialResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Bundle data;
    private final String type;

    @JvmStatic
    public static final CreateCredentialResponse createFrom(String str, Bundle bundle) {
        return Companion.createFrom(str, bundle);
    }

    public CreateCredentialResponse(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "data");
        this.type = str;
        this.data = bundle;
    }

    public final String getType() {
        return this.type;
    }

    public final Bundle getData() {
        return this.data;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/CreateCredentialResponse$Companion;", "", "()V", "createFrom", "Landroidx/credentials/CreateCredentialResponse;", "type", "", "data", "Landroid/os/Bundle;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreateCredentialResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CreateCredentialResponse createFrom(String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(str, "type");
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                if (Intrinsics.areEqual((Object) str, (Object) PasswordCredential.TYPE_PASSWORD_CREDENTIAL)) {
                    return CreatePasswordResponse.Companion.createFrom$credentials_release(bundle);
                }
                if (Intrinsics.areEqual((Object) str, (Object) PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL)) {
                    return CreatePublicKeyCredentialResponse.Companion.createFrom$credentials_release(bundle);
                }
                throw new FrameworkClassParsingException();
            } catch (FrameworkClassParsingException unused) {
                return new CreateCustomCredentialResponse(str, bundle);
            }
        }
    }
}
