package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialResponse;", "Landroidx/credentials/CreateCredentialResponse;", "registrationResponseJson", "", "(Ljava/lang/String;)V", "data", "Landroid/os/Bundle;", "(Ljava/lang/String;Landroid/os/Bundle;)V", "getRegistrationResponseJson", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePublicKeyCredentialResponse.kt */
public final class CreatePublicKeyCredentialResponse extends CreateCredentialResponse {
    public static final String BUNDLE_KEY_REGISTRATION_RESPONSE_JSON = "androidx.credentials.BUNDLE_KEY_REGISTRATION_RESPONSE_JSON";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String registrationResponseJson;

    public /* synthetic */ CreatePublicKeyCredentialResponse(String str, Bundle bundle, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bundle);
    }

    @JvmStatic
    public static final CreatePublicKeyCredentialResponse createFrom$credentials_release(Bundle bundle) {
        return Companion.createFrom$credentials_release(bundle);
    }

    @JvmStatic
    public static final Bundle toBundle$credentials_release(String str) {
        return Companion.toBundle$credentials_release(str);
    }

    public final String getRegistrationResponseJson() {
        return this.registrationResponseJson;
    }

    private CreatePublicKeyCredentialResponse(String str, Bundle bundle) {
        super(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, bundle);
        this.registrationResponseJson = str;
        if (!RequestValidationHelper.Companion.isValidJSON(str)) {
            throw new IllegalArgumentException("registrationResponseJson must not be empty, and must be a valid JSON".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialResponse(String str) {
        this(str, Companion.toBundle$credentials_release(str));
        Intrinsics.checkNotNullParameter(str, "registrationResponseJson");
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialResponse$Companion;", "", "()V", "BUNDLE_KEY_REGISTRATION_RESPONSE_JSON", "", "createFrom", "Landroidx/credentials/CreatePublicKeyCredentialResponse;", "data", "Landroid/os/Bundle;", "createFrom$credentials_release", "toBundle", "registrationResponseJson", "toBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreatePublicKeyCredentialResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle toBundle$credentials_release(String str) {
            Intrinsics.checkNotNullParameter(str, "registrationResponseJson");
            Bundle bundle = new Bundle();
            bundle.putString(CreatePublicKeyCredentialResponse.BUNDLE_KEY_REGISTRATION_RESPONSE_JSON, str);
            return bundle;
        }

        @JvmStatic
        public final CreatePublicKeyCredentialResponse createFrom$credentials_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString(CreatePublicKeyCredentialResponse.BUNDLE_KEY_REGISTRATION_RESPONSE_JSON);
                Intrinsics.checkNotNull(string);
                return new CreatePublicKeyCredentialResponse(string, bundle, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
