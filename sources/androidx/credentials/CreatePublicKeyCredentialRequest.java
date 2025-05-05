package androidx.credentials;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B;\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nB=\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\fBQ\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialRequest;", "Landroidx/credentials/CreateCredentialRequest;", "requestJson", "", "clientDataHash", "", "preferImmediatelyAvailableCredentials", "", "origin", "isAutoSelectAllowed", "(Ljava/lang/String;[BZLjava/lang/String;Z)V", "preferDefaultProvider", "(Ljava/lang/String;[BZLjava/lang/String;Ljava/lang/String;Z)V", "displayInfo", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "credentialData", "Landroid/os/Bundle;", "candidateQueryData", "(Ljava/lang/String;[BZZLandroidx/credentials/CreateCredentialRequest$DisplayInfo;Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;)V", "getClientDataHash", "()[B", "getRequestJson", "()Ljava/lang/String;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePublicKeyCredentialRequest.kt */
public final class CreatePublicKeyCredentialRequest extends CreateCredentialRequest {
    public static final String BUNDLE_KEY_CLIENT_DATA_HASH = "androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH";
    public static final String BUNDLE_KEY_REQUEST_JSON = "androidx.credentials.BUNDLE_KEY_REQUEST_JSON";
    public static final String BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST = "androidx.credentials.BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final byte[] clientDataHash;
    private final String requestJson;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str) {
        this(str, (byte[]) null, false, (String) null, false, 30, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str, byte[] bArr) {
        this(str, bArr, false, (String) null, false, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z) {
        this(str, bArr, z, (String) null, false, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, String str2) {
        this(str, bArr, z, str2, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    public /* synthetic */ CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, boolean z2, CreateCredentialRequest.DisplayInfo displayInfo, String str2, Bundle bundle, Bundle bundle2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, bArr, z, z2, displayInfo, str2, bundle, bundle2);
    }

    @JvmStatic
    public static final CreatePublicKeyCredentialRequest createFrom$credentials_release(Bundle bundle, String str, Bundle bundle2) {
        return Companion.createFrom$credentials_release(bundle, str, bundle2);
    }

    @JvmStatic
    public static final CreateCredentialRequest.DisplayInfo getRequestDisplayInfo$credentials_release(String str, String str2) {
        return Companion.getRequestDisplayInfo$credentials_release(str, str2);
    }

    @JvmStatic
    public static final Bundle toCandidateDataBundle$credentials_release(String str, byte[] bArr) {
        return Companion.toCandidateDataBundle$credentials_release(str, bArr);
    }

    @JvmStatic
    public static final Bundle toCredentialDataBundle$credentials_release(String str, byte[] bArr) {
        return Companion.toCredentialDataBundle$credentials_release(str, bArr);
    }

    public final String getRequestJson() {
        return this.requestJson;
    }

    public final byte[] getClientDataHash() {
        return this.clientDataHash;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* synthetic */ CreatePublicKeyCredentialRequest(java.lang.String r10, byte[] r11, boolean r12, boolean r13, androidx.credentials.CreateCredentialRequest.DisplayInfo r14, java.lang.String r15, android.os.Bundle r16, android.os.Bundle r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r9 = this;
            r1 = r10
            r2 = r11
            r0 = r18
            r3 = r0 & 32
            if (r3 == 0) goto L_0x000b
            r3 = 0
            r6 = r3
            goto L_0x000c
        L_0x000b:
            r6 = r15
        L_0x000c:
            r3 = r0 & 64
            if (r3 == 0) goto L_0x0018
            androidx.credentials.CreatePublicKeyCredentialRequest$Companion r3 = Companion
            android.os.Bundle r3 = r3.toCredentialDataBundle$credentials_release(r10, r11)
            r7 = r3
            goto L_0x001a
        L_0x0018:
            r7 = r16
        L_0x001a:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0026
            androidx.credentials.CreatePublicKeyCredentialRequest$Companion r0 = Companion
            android.os.Bundle r0 = r0.toCandidateDataBundle$credentials_release(r10, r11)
            r8 = r0
            goto L_0x0028
        L_0x0026:
            r8 = r17
        L_0x0028:
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.CreatePublicKeyCredentialRequest.<init>(java.lang.String, byte[], boolean, boolean, androidx.credentials.CreateCredentialRequest$DisplayInfo, java.lang.String, android.os.Bundle, android.os.Bundle, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, boolean z2, CreateCredentialRequest.DisplayInfo displayInfo, String str2, Bundle bundle, Bundle bundle2) {
        super(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, bundle, bundle2, false, z, displayInfo, str2, z2);
        this.requestJson = str;
        this.clientDataHash = bArr;
        if (!RequestValidationHelper.Companion.isValidJSON(str)) {
            throw new IllegalArgumentException("requestJson must not be empty, and must be a valid JSON".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, String str2, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? false : z2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, String str2, boolean z2) {
        this(str, bArr, z2, z, Companion.getRequestDisplayInfo$credentials_release$default(Companion, str, (String) null, 2, (Object) null), str2, (Bundle) null, (Bundle) null, 192, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialRequest(String str, byte[] bArr, boolean z, String str2, String str3, boolean z2) {
        this(str, bArr, z2, z, Companion.getRequestDisplayInfo$credentials_release(str, str3), str2, (Bundle) null, (Bundle) null, 192, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "requestJson");
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\nH\u0001¢\u0006\u0002\b\rJ!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0001¢\u0006\u0002\b\u0012J\u001f\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0001¢\u0006\u0002\b\u0016J!\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0001¢\u0006\u0002\b\u0018R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/credentials/CreatePublicKeyCredentialRequest$Companion;", "", "()V", "BUNDLE_KEY_CLIENT_DATA_HASH", "", "BUNDLE_KEY_REQUEST_JSON", "BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST", "createFrom", "Landroidx/credentials/CreatePublicKeyCredentialRequest;", "data", "Landroid/os/Bundle;", "origin", "candidateQueryData", "createFrom$credentials_release", "getRequestDisplayInfo", "Landroidx/credentials/CreateCredentialRequest$DisplayInfo;", "requestJson", "defaultProvider", "getRequestDisplayInfo$credentials_release", "toCandidateDataBundle", "clientDataHash", "", "toCandidateDataBundle$credentials_release", "toCredentialDataBundle", "toCredentialDataBundle$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreatePublicKeyCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ CreateCredentialRequest.DisplayInfo getRequestDisplayInfo$credentials_release$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            return companion.getRequestDisplayInfo$credentials_release(str, str2);
        }

        @JvmStatic
        public final CreateCredentialRequest.DisplayInfo getRequestDisplayInfo$credentials_release(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "requestJson");
            try {
                JSONObject jSONObject = new JSONObject(str).getJSONObject("user");
                String string = jSONObject.getString("name");
                String string2 = jSONObject.isNull("displayName") ? null : jSONObject.getString("displayName");
                Intrinsics.checkNotNullExpressionValue(string, "userName");
                return new CreateCredentialRequest.DisplayInfo((CharSequence) string, (CharSequence) string2, (Icon) null, str2);
            } catch (Exception unused) {
                throw new IllegalArgumentException("user.name must be defined in requestJson");
            }
        }

        public static /* synthetic */ Bundle toCredentialDataBundle$credentials_release$default(Companion companion, String str, byte[] bArr, int i, Object obj) {
            if ((i & 2) != 0) {
                bArr = null;
            }
            return companion.toCredentialDataBundle$credentials_release(str, bArr);
        }

        @JvmStatic
        public final Bundle toCredentialDataBundle$credentials_release(String str, byte[] bArr) {
            Intrinsics.checkNotNullParameter(str, "requestJson");
            Bundle bundle = new Bundle();
            bundle.putString(PublicKeyCredential.BUNDLE_KEY_SUBTYPE, CreatePublicKeyCredentialRequest.BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST);
            bundle.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", str);
            bundle.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", bArr);
            return bundle;
        }

        @JvmStatic
        public final Bundle toCandidateDataBundle$credentials_release(String str, byte[] bArr) {
            Intrinsics.checkNotNullParameter(str, "requestJson");
            Bundle bundle = new Bundle();
            bundle.putString(PublicKeyCredential.BUNDLE_KEY_SUBTYPE, CreatePublicKeyCredentialRequest.BUNDLE_VALUE_SUBTYPE_CREATE_PUBLIC_KEY_CREDENTIAL_REQUEST);
            bundle.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", str);
            bundle.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", bArr);
            return bundle;
        }

        @JvmStatic
        public final CreatePublicKeyCredentialRequest createFrom$credentials_release(Bundle bundle, String str, Bundle bundle2) {
            Intrinsics.checkNotNullParameter(bundle, "data");
            Intrinsics.checkNotNullParameter(bundle2, "candidateQueryData");
            try {
                String string = bundle.getString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON");
                Intrinsics.checkNotNull(string);
                byte[] byteArray = bundle.getByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH");
                boolean z = bundle.getBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", false);
                CreateCredentialRequest.DisplayInfo parseFromCredentialDataBundle = CreateCredentialRequest.DisplayInfo.Companion.parseFromCredentialDataBundle(bundle);
                if (parseFromCredentialDataBundle == null) {
                    parseFromCredentialDataBundle = getRequestDisplayInfo$credentials_release$default(this, string, (String) null, 2, (Object) null);
                }
                return new CreatePublicKeyCredentialRequest(string, byteArray, bundle.getBoolean("androidx.credentials.BUNDLE_KEY_IS_AUTO_SELECT_ALLOWED", false), z, parseFromCredentialDataBundle, str, bundle, bundle2, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
