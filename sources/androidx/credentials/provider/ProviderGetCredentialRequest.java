package androidx.credentials.provider;

import androidx.credentials.CredentialOption;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/credentials/provider/ProviderGetCredentialRequest;", "", "credentialOptions", "", "Landroidx/credentials/CredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Ljava/util/List;Landroidx/credentials/provider/CallingAppInfo;)V", "getCallingAppInfo", "()Landroidx/credentials/provider/CallingAppInfo;", "getCredentialOptions", "()Ljava/util/List;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProviderGetCredentialRequest.kt */
public final class ProviderGetCredentialRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final CallingAppInfo callingAppInfo;
    private final List<CredentialOption> credentialOptions;

    @JvmStatic
    public static final ProviderGetCredentialRequest createFrom$credentials_release(List<? extends CredentialOption> list, CallingAppInfo callingAppInfo2) {
        return Companion.createFrom$credentials_release(list, callingAppInfo2);
    }

    public ProviderGetCredentialRequest(List<? extends CredentialOption> list, CallingAppInfo callingAppInfo2) {
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
        Intrinsics.checkNotNullParameter(callingAppInfo2, "callingAppInfo");
        this.credentialOptions = list;
        this.callingAppInfo = callingAppInfo2;
    }

    public final List<CredentialOption> getCredentialOptions() {
        return this.credentialOptions;
    }

    public final CallingAppInfo getCallingAppInfo() {
        return this.callingAppInfo;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/credentials/provider/ProviderGetCredentialRequest$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/ProviderGetCredentialRequest;", "options", "", "Landroidx/credentials/CredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "createFrom$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ProviderGetCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ProviderGetCredentialRequest createFrom$credentials_release(List<? extends CredentialOption> list, CallingAppInfo callingAppInfo) {
            Intrinsics.checkNotNullParameter(list, "options");
            Intrinsics.checkNotNullParameter(callingAppInfo, "callingAppInfo");
            return new ProviderGetCredentialRequest(list, callingAppInfo);
        }
    }
}
