package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B1\b\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tB;\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/credentials/GetPasswordOption;", "Landroidx/credentials/CredentialOption;", "allowedUserIds", "", "", "isAutoSelectAllowed", "", "allowedProviders", "Landroid/content/ComponentName;", "(Ljava/util/Set;ZLjava/util/Set;)V", "requestData", "Landroid/os/Bundle;", "candidateQueryData", "(Ljava/util/Set;ZLjava/util/Set;Landroid/os/Bundle;Landroid/os/Bundle;)V", "getAllowedUserIds", "()Ljava/util/Set;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GetPasswordOption.kt */
public final class GetPasswordOption extends CredentialOption {
    public static final String BUNDLE_KEY_ALLOWED_USER_IDS = "androidx.credentials.BUNDLE_KEY_ALLOWED_USER_IDS";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Set<String> allowedUserIds;

    public GetPasswordOption() {
        this((Set) null, false, (Set) null, 7, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetPasswordOption(Set<String> set) {
        this((Set) set, false, (Set) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(set, "allowedUserIds");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetPasswordOption(Set<String> set, boolean z) {
        this((Set) set, z, (Set) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(set, "allowedUserIds");
    }

    public /* synthetic */ GetPasswordOption(Set set, boolean z, Set set2, Bundle bundle, Bundle bundle2, DefaultConstructorMarker defaultConstructorMarker) {
        this((Set<String>) set, z, (Set<ComponentName>) set2, bundle, bundle2);
    }

    @JvmStatic
    public static final GetPasswordOption createFrom$credentials_release(Bundle bundle, Set<ComponentName> set, Bundle bundle2) {
        return Companion.createFrom$credentials_release(bundle, set, bundle2);
    }

    @JvmStatic
    public static final Bundle toBundle$credentials_release(Set<String> set) {
        return Companion.toBundle$credentials_release(set);
    }

    public final Set<String> getAllowedUserIds() {
        return this.allowedUserIds;
    }

    private GetPasswordOption(Set<String> set, boolean z, Set<ComponentName> set2, Bundle bundle, Bundle bundle2) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, bundle2, false, z, set2);
        this.allowedUserIds = set;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GetPasswordOption(Set set, boolean z, Set set2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? SetsKt.emptySet() : set, (i & 2) != 0 ? false : z, (i & 4) != 0 ? SetsKt.emptySet() : set2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GetPasswordOption(java.util.Set<java.lang.String> r8, boolean r9, java.util.Set<android.content.ComponentName> r10) {
        /*
            r7 = this;
            java.lang.String r0 = "allowedUserIds"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "allowedProviders"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            androidx.credentials.GetPasswordOption$Companion r0 = Companion
            android.os.Bundle r5 = r0.toBundle$credentials_release(r8)
            android.os.Bundle r6 = r0.toBundle$credentials_release(r8)
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>((java.util.Set<java.lang.String>) r2, (boolean) r3, (java.util.Set<android.content.ComponentName>) r4, (android.os.Bundle) r5, (android.os.Bundle) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.GetPasswordOption.<init>(java.util.Set, boolean, java.util.Set):void");
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\bH\u0001¢\u0006\u0002\b\rJ\u001b\u0010\u000e\u001a\u00020\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0001¢\u0006\u0002\b\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/GetPasswordOption$Companion;", "", "()V", "BUNDLE_KEY_ALLOWED_USER_IDS", "", "createFrom", "Landroidx/credentials/GetPasswordOption;", "data", "Landroid/os/Bundle;", "allowedProviders", "", "Landroid/content/ComponentName;", "candidateQueryData", "createFrom$credentials_release", "toBundle", "allowUserIds", "toBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: GetPasswordOption.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final GetPasswordOption createFrom$credentials_release(Bundle bundle, Set<ComponentName> set, Bundle bundle2) {
            Set set2;
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(set, "allowedProviders");
            Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
            ArrayList<String> stringArrayList = bundle.getStringArrayList(GetPasswordOption.BUNDLE_KEY_ALLOWED_USER_IDS);
            if (stringArrayList == null || (set2 = CollectionsKt.toSet(stringArrayList)) == null) {
                set2 = SetsKt.emptySet();
            }
            return new GetPasswordOption(set2, bundle.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), set, bundle, bundle2, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final Bundle toBundle$credentials_release(Set<String> set) {
            Intrinsics.checkNotNullParameter(set, "allowUserIds");
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(GetPasswordOption.BUNDLE_KEY_ALLOWED_USER_IDS, new ArrayList(set));
            return bundle;
        }
    }
}
