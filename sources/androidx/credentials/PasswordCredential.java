package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\r"}, d2 = {"Landroidx/credentials/PasswordCredential;", "Landroidx/credentials/Credential;", "id", "", "password", "(Ljava/lang/String;Ljava/lang/String;)V", "data", "Landroid/os/Bundle;", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getId", "()Ljava/lang/String;", "getPassword", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PasswordCredential.kt */
public final class PasswordCredential extends Credential {
    public static final String BUNDLE_KEY_ID = "androidx.credentials.BUNDLE_KEY_ID";
    public static final String BUNDLE_KEY_PASSWORD = "androidx.credentials.BUNDLE_KEY_PASSWORD";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TYPE_PASSWORD_CREDENTIAL = "android.credentials.TYPE_PASSWORD_CREDENTIAL";
    private final String id;
    private final String password;

    public /* synthetic */ PasswordCredential(String str, String str2, Bundle bundle, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, bundle);
    }

    @JvmStatic
    public static final PasswordCredential createFrom$credentials_release(Bundle bundle) {
        return Companion.createFrom$credentials_release(bundle);
    }

    @JvmStatic
    public static final Bundle toBundle$credentials_release(String str, String str2) {
        return Companion.toBundle$credentials_release(str, str2);
    }

    public final String getId() {
        return this.id;
    }

    public final String getPassword() {
        return this.password;
    }

    private PasswordCredential(String str, String str2, Bundle bundle) {
        super(TYPE_PASSWORD_CREDENTIAL, bundle);
        this.id = str;
        this.password = str2;
        if (!(str2.length() > 0)) {
            throw new IllegalArgumentException("password should not be empty".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PasswordCredential(String str, String str2) {
        this(str, str2, Companion.toBundle$credentials_release(str, str2));
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "password");
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/credentials/PasswordCredential$Companion;", "", "()V", "BUNDLE_KEY_ID", "", "BUNDLE_KEY_PASSWORD", "TYPE_PASSWORD_CREDENTIAL", "createFrom", "Landroidx/credentials/PasswordCredential;", "data", "Landroid/os/Bundle;", "createFrom$credentials_release", "toBundle", "id", "password", "toBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PasswordCredential.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle toBundle$credentials_release(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str2, "password");
            Bundle bundle = new Bundle();
            bundle.putString("androidx.credentials.BUNDLE_KEY_ID", str);
            bundle.putString("androidx.credentials.BUNDLE_KEY_PASSWORD", str2);
            return bundle;
        }

        @JvmStatic
        public final PasswordCredential createFrom$credentials_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_ID");
                String string2 = bundle.getString("androidx.credentials.BUNDLE_KEY_PASSWORD");
                Intrinsics.checkNotNull(string);
                Intrinsics.checkNotNull(string2);
                return new PasswordCredential(string, string2, bundle, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
