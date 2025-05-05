package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B=\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/credentials/CredentialOption;", "", "type", "", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "isSystemProviderRequired", "", "isAutoSelectAllowed", "allowedProviders", "", "Landroid/content/ComponentName;", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;ZZLjava/util/Set;)V", "getAllowedProviders", "()Ljava/util/Set;", "getCandidateQueryData", "()Landroid/os/Bundle;", "()Z", "getRequestData", "getType", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialOption.kt */
public abstract class CredentialOption {
    public static final String BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED = "androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Set<ComponentName> allowedProviders;
    private final Bundle candidateQueryData;
    private final boolean isAutoSelectAllowed;
    private final boolean isSystemProviderRequired;
    private final Bundle requestData;
    private final String type;

    @JvmStatic
    public static final CredentialOption createFrom(String str, Bundle bundle, Bundle bundle2, boolean z, Set<ComponentName> set) {
        return Companion.createFrom(str, bundle, bundle2, z, set);
    }

    public CredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z2, Set<ComponentName> set) {
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "requestData");
        Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
        Intrinsics.checkNotNullParameter(set, "allowedProviders");
        this.type = str;
        this.requestData = bundle;
        this.candidateQueryData = bundle2;
        this.isSystemProviderRequired = z;
        this.isAutoSelectAllowed = z2;
        this.allowedProviders = set;
        bundle.putBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", z2);
        bundle2.putBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", z2);
    }

    public final String getType() {
        return this.type;
    }

    public final Bundle getRequestData() {
        return this.requestData;
    }

    public final Bundle getCandidateQueryData() {
        return this.candidateQueryData;
    }

    public final boolean isSystemProviderRequired() {
        return this.isSystemProviderRequired;
    }

    public final boolean isAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    public final Set<ComponentName> getAllowedProviders() {
        return this.allowedProviders;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0007J\u0015\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/credentials/CredentialOption$Companion;", "", "()V", "BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", "", "createFrom", "Landroidx/credentials/CredentialOption;", "type", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "requireSystemProvider", "", "allowedProviders", "", "Landroid/content/ComponentName;", "extractAutoSelectValue", "data", "extractAutoSelectValue$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean extractAutoSelectValue$credentials_release(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            return bundle.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED");
        }

        @JvmStatic
        public final CredentialOption createFrom(String str, Bundle bundle, Bundle bundle2, boolean z, Set<ComponentName> set) {
            Intrinsics.checkNotNullParameter(str, "type");
            Intrinsics.checkNotNullParameter(bundle, "requestData");
            Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
            Intrinsics.checkNotNullParameter(set, "allowedProviders");
            try {
                if (Intrinsics.areEqual((Object) str, (Object) PasswordCredential.TYPE_PASSWORD_CREDENTIAL)) {
                    return GetPasswordOption.Companion.createFrom$credentials_release(bundle, set, bundle2);
                }
                if (Intrinsics.areEqual((Object) str, (Object) PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL)) {
                    String string = bundle.getString(PublicKeyCredential.BUNDLE_KEY_SUBTYPE);
                    if (string != null && string.hashCode() == -613058807 && string.equals(GetPublicKeyCredentialOption.BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION)) {
                        return GetPublicKeyCredentialOption.Companion.createFrom$credentials_release(bundle, set, bundle2);
                    }
                    throw new FrameworkClassParsingException();
                }
                throw new FrameworkClassParsingException();
            } catch (FrameworkClassParsingException unused) {
                return new GetCustomCredentialOption(str, bundle, bundle2, z, bundle.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), set);
            }
        }
    }
}
