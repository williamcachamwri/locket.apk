package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/credentials/GetCustomCredentialOption;", "Landroidx/credentials/CredentialOption;", "type", "", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "isSystemProviderRequired", "", "isAutoSelectAllowed", "allowedProviders", "", "Landroid/content/ComponentName;", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;ZZLjava/util/Set;)V", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GetCustomCredentialOption.kt */
public class GetCustomCredentialOption extends CredentialOption {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z) {
        this(str, bundle, bundle2, z, false, (Set) null, 48, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "requestData");
        Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z2) {
        this(str, bundle, bundle2, z, z2, (Set) null, 32, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "requestData");
        Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z2, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bundle, bundle2, z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? SetsKt.emptySet() : set);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetCustomCredentialOption(String str, Bundle bundle, Bundle bundle2, boolean z, boolean z2, Set<ComponentName> set) {
        super(str, bundle, bundle2, z, z2, set);
        Intrinsics.checkNotNullParameter(str, "type");
        Intrinsics.checkNotNullParameter(bundle, "requestData");
        Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
        Intrinsics.checkNotNullParameter(set, "allowedProviders");
        if (!(str.length() > 0)) {
            throw new IllegalArgumentException("type should not be empty".toString());
        }
    }
}
