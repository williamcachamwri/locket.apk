package androidx.credentials.provider;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginGetCustomCredentialOption;", "Landroidx/credentials/provider/BeginGetCredentialOption;", "id", "", "type", "candidateQueryData", "Landroid/os/Bundle;", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCustomCredentialOption.kt */
public class BeginGetCustomCredentialOption extends BeginGetCredentialOption {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final BeginGetCustomCredentialOption createFrom$credentials_release(Bundle bundle, String str, String str2) {
        return Companion.createFrom$credentials_release(bundle, str, str2);
    }

    @JvmStatic
    public static final BeginGetCustomCredentialOption createFromEntrySlice$credentials_release(Bundle bundle, String str, String str2) {
        return Companion.createFromEntrySlice$credentials_release(bundle, str, str2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeginGetCustomCredentialOption(String str, String str2, Bundle bundle) {
        super(str, str2, bundle);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "type");
        Intrinsics.checkNotNullParameter(bundle, "candidateQueryData");
        boolean z = true;
        if (str.length() > 0) {
            if (!(str2.length() <= 0 ? false : z)) {
                throw new IllegalArgumentException("type should not be empty".toString());
            }
            return;
        }
        throw new IllegalArgumentException("id should not be empty".toString());
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\nJ%\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"Landroidx/credentials/provider/BeginGetCustomCredentialOption$Companion;", "", "()V", "createFrom", "Landroidx/credentials/provider/BeginGetCustomCredentialOption;", "data", "Landroid/os/Bundle;", "id", "", "type", "createFrom$credentials_release", "createFromEntrySlice", "createFromEntrySlice$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCustomCredentialOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BeginGetCustomCredentialOption createFrom$credentials_release(Bundle bundle, String str, String str2) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str2, "type");
            return new BeginGetCustomCredentialOption(str, str2, bundle);
        }

        @JvmStatic
        public final BeginGetCustomCredentialOption createFromEntrySlice$credentials_release(Bundle bundle, String str, String str2) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str2, "type");
            return new BeginGetCustomCredentialOption(str, str2, bundle);
        }
    }
}
