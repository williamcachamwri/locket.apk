package androidx.credentials.webauthn;

import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.webauthn.WebAuthnUtils;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/webauthn/WebAuthnUtilsApi28;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebAuthnUtilsApi28.kt */
public final class WebAuthnUtilsApi28 {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/webauthn/WebAuthnUtilsApi28$Companion;", "", "()V", "appInfoToOrigin", "", "info", "Landroidx/credentials/provider/CallingAppInfo;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebAuthnUtilsApi28.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String appInfoToOrigin(CallingAppInfo callingAppInfo) {
            Intrinsics.checkNotNullParameter(callingAppInfo, "info");
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(callingAppInfo.getSigningInfo().getApkContentsSigners()[0].toByteArray());
            StringBuilder sb = new StringBuilder("android:apk-key-hash:");
            WebAuthnUtils.Companion companion = WebAuthnUtils.Companion;
            Intrinsics.checkNotNullExpressionValue(digest, "certHash");
            return sb.append(companion.b64Encode(digest)).toString();
        }
    }
}
