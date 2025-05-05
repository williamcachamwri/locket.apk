package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB;\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bBO\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0016"}, d2 = {"Landroidx/credentials/CreatePasswordRequest;", "Landroidx/credentials/CreateCredentialRequest;", "id", "", "password", "origin", "preferImmediatelyAvailableCredentials", "", "isAutoSelectAllowed", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "preferDefaultProvider", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "displayInfo", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "credentialData", "Landroid/os/Bundle;", "candidateQueryData", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/credentials/CreateCredentialRequest$DisplayInfo;Ljava/lang/String;ZLandroid/os/Bundle;Landroid/os/Bundle;)V", "getId", "()Ljava/lang/String;", "getPassword", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePasswordRequest.kt */
public final class CreatePasswordRequest extends CreateCredentialRequest {
    public static final String BUNDLE_KEY_ID = "androidx.credentials.BUNDLE_KEY_ID";
    public static final String BUNDLE_KEY_PASSWORD = "androidx.credentials.BUNDLE_KEY_PASSWORD";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String id;
    private final String password;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePasswordRequest(String str, String str2) {
        this(str, str2, (String) null, false, false, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "password");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePasswordRequest(String str, String str2, String str3) {
        this(str, str2, str3, false, false, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "password");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePasswordRequest(String str, String str2, String str3, boolean z) {
        this(str, str2, str3, z, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(str2, "password");
    }

    public /* synthetic */ CreatePasswordRequest(String str, String str2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, String str3, boolean z2, Bundle bundle, Bundle bundle2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, displayInfo, str3, z2, bundle, bundle2);
    }

    @JvmStatic
    public static final CreatePasswordRequest createFrom$credentials_release(Bundle bundle, String str, Bundle bundle2) {
        return Companion.createFrom$credentials_release(bundle, str, bundle2);
    }

    @JvmStatic
    public static final Bundle toCandidateDataBundle$credentials_release() {
        return Companion.toCandidateDataBundle$credentials_release();
    }

    @JvmStatic
    public static final Bundle toCredentialDataBundle$credentials_release(String str, String str2) {
        return Companion.toCredentialDataBundle$credentials_release(str, str2);
    }

    public final String getId() {
        return this.id;
    }

    public final String getPassword() {
        return this.password;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ CreatePasswordRequest(java.lang.String r12, java.lang.String r13, boolean r14, androidx.credentials.CreateCredentialRequest.DisplayInfo r15, java.lang.String r16, boolean r17, android.os.Bundle r18, android.os.Bundle r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*
            r11 = this;
            r0 = r20
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0009
            r1 = 0
            r7 = r1
            goto L_0x000b
        L_0x0009:
            r7 = r16
        L_0x000b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0019
            androidx.credentials.CreatePasswordRequest$Companion r1 = Companion
            r3 = r12
            r4 = r13
            android.os.Bundle r1 = r1.toCredentialDataBundle$credentials_release(r12, r13)
            r9 = r1
            goto L_0x001d
        L_0x0019:
            r3 = r12
            r4 = r13
            r9 = r18
        L_0x001d:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0029
            androidx.credentials.CreatePasswordRequest$Companion r0 = Companion
            android.os.Bundle r0 = r0.toCandidateDataBundle$credentials_release()
            r10 = r0
            goto L_0x002b
        L_0x0029:
            r10 = r19
        L_0x002b:
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r8 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.CreatePasswordRequest.<init>(java.lang.String, java.lang.String, boolean, androidx.credentials.CreateCredentialRequest$DisplayInfo, java.lang.String, boolean, android.os.Bundle, android.os.Bundle, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private CreatePasswordRequest(String str, String str2, boolean z, CreateCredentialRequest.DisplayInfo displayInfo, String str3, boolean z2, Bundle bundle, Bundle bundle2) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, bundle, bundle2, false, z, displayInfo, str3, z2);
        String str4 = str2;
        this.id = str;
        this.password = str4;
        if (!(str4.length() > 0)) {
            throw new IllegalArgumentException("password should not be empty".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CreatePasswordRequest(String str, String str2, String str3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CreatePasswordRequest(java.lang.String r12, java.lang.String r13, java.lang.String r14, boolean r15, boolean r16) {
        /*
            r11 = this;
            r1 = r12
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "password"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            androidx.credentials.CreateCredentialRequest$DisplayInfo r4 = new androidx.credentials.CreateCredentialRequest$DisplayInfo
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3 = 0
            r4.<init>(r0, r3)
            r7 = 0
            r8 = 0
            r9 = 192(0xc0, float:2.69E-43)
            r10 = 0
            r0 = r11
            r3 = r16
            r5 = r14
            r6 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.CreatePasswordRequest.<init>(java.lang.String, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CreatePasswordRequest(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, boolean r16, boolean r17) {
        /*
            r11 = this;
            r1 = r12
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "password"
            r2 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            androidx.credentials.CreateCredentialRequest$DisplayInfo r4 = new androidx.credentials.CreateCredentialRequest$DisplayInfo
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3 = 0
            r5 = r15
            r4.<init>(r0, r3, r15)
            r7 = 0
            r8 = 0
            r9 = 192(0xc0, float:2.69E-43)
            r10 = 0
            r0 = r11
            r3 = r17
            r5 = r14
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.CreatePasswordRequest.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\tH\u0001¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\tH\u0001¢\u0006\u0002\b\u000eJ\u001d\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\u0012R\u0010\u0010\u0003\u001a\u00020\u00048\u0006XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/credentials/CreatePasswordRequest$Companion;", "", "()V", "BUNDLE_KEY_ID", "", "BUNDLE_KEY_PASSWORD", "createFrom", "Landroidx/credentials/CreatePasswordRequest;", "data", "Landroid/os/Bundle;", "origin", "candidateQueryData", "createFrom$credentials_release", "toCandidateDataBundle", "toCandidateDataBundle$credentials_release", "toCredentialDataBundle", "id", "password", "toCredentialDataBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreatePasswordRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle toCredentialDataBundle$credentials_release(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "id");
            Intrinsics.checkNotNullParameter(str2, "password");
            Bundle bundle = new Bundle();
            bundle.putString("androidx.credentials.BUNDLE_KEY_ID", str);
            bundle.putString("androidx.credentials.BUNDLE_KEY_PASSWORD", str2);
            return bundle;
        }

        @JvmStatic
        public final Bundle toCandidateDataBundle$credentials_release() {
            return new Bundle();
        }

        @JvmStatic
        public final CreatePasswordRequest createFrom$credentials_release(Bundle bundle, String str, Bundle bundle2) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_ID");
                Intrinsics.checkNotNull(string);
                String string2 = bundle.getString("androidx.credentials.BUNDLE_KEY_PASSWORD");
                Intrinsics.checkNotNull(string2);
                CreateCredentialRequest.DisplayInfo parseFromCredentialDataBundle = CreateCredentialRequest.DisplayInfo.Companion.parseFromCredentialDataBundle(bundle);
                if (parseFromCredentialDataBundle == null) {
                    parseFromCredentialDataBundle = new CreateCredentialRequest.DisplayInfo(string, (CharSequence) null);
                }
                boolean z = bundle.getBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", false);
                return new CreatePasswordRequest(string, string2, bundle.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), parseFromCredentialDataBundle, str, z, bundle, bundle2, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
