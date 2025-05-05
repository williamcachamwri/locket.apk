package androidx.credentials.provider.utils;

import android.content.pm.SigningInfo;
import android.service.credentials.ClearCredentialStateRequest;
import androidx.credentials.provider.CallingAppInfo;
import androidx.credentials.provider.ProviderClearCredentialStateRequest;
import io.sentry.SentryBaseEvent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/utils/ClearCredentialUtil;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ClearCredentialUtil.kt */
public final class ClearCredentialUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    public static final ProviderClearCredentialStateRequest convertToJetpackRequest$credentials_release(ClearCredentialStateRequest clearCredentialStateRequest) {
        return Companion.convertToJetpackRequest$credentials_release(clearCredentialStateRequest);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/provider/utils/ClearCredentialUtil$Companion;", "", "()V", "convertToJetpackRequest", "Landroidx/credentials/provider/ProviderClearCredentialStateRequest;", "request", "Landroid/service/credentials/ClearCredentialStateRequest;", "convertToJetpackRequest$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ClearCredentialUtil.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ProviderClearCredentialStateRequest convertToJetpackRequest$credentials_release(ClearCredentialStateRequest clearCredentialStateRequest) {
            Intrinsics.checkNotNullParameter(clearCredentialStateRequest, SentryBaseEvent.JsonKeys.REQUEST);
            String packageName = clearCredentialStateRequest.getCallingAppInfo().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "request.callingAppInfo.packageName");
            SigningInfo signingInfo = clearCredentialStateRequest.getCallingAppInfo().getSigningInfo();
            Intrinsics.checkNotNullExpressionValue(signingInfo, "request.callingAppInfo.signingInfo");
            return new ProviderClearCredentialStateRequest(new CallingAppInfo(packageName, signingInfo, clearCredentialStateRequest.getCallingAppInfo().getOrigin()));
        }
    }
}
