package androidx.credentials.provider;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.credentials.Credential;
import android.os.Bundle;
import android.service.credentials.BeginGetCredentialRequest;
import android.service.credentials.CreateCredentialRequest;
import android.service.credentials.GetCredentialRequest;
import android.util.Log;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.provider.ProviderGetCredentialRequest;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import io.sentry.protocol.Response;
import java.util.List;
import java.util.stream.Collectors;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/provider/PendingIntentHandler;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PendingIntentHandler.kt */
public final class PendingIntentHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "PendingIntentHandler";

    @JvmStatic
    public static final BeginGetCredentialRequest retrieveBeginGetCredentialRequest(Intent intent) {
        return Companion.retrieveBeginGetCredentialRequest(intent);
    }

    @JvmStatic
    public static final ProviderCreateCredentialRequest retrieveProviderCreateCredentialRequest(Intent intent) {
        return Companion.retrieveProviderCreateCredentialRequest(intent);
    }

    @JvmStatic
    public static final ProviderGetCredentialRequest retrieveProviderGetCredentialRequest(Intent intent) {
        return Companion.retrieveProviderGetCredentialRequest(intent);
    }

    @JvmStatic
    public static final void setBeginGetCredentialResponse(Intent intent, BeginGetCredentialResponse beginGetCredentialResponse) {
        Companion.setBeginGetCredentialResponse(intent, beginGetCredentialResponse);
    }

    @JvmStatic
    public static final void setCreateCredentialException(Intent intent, CreateCredentialException createCredentialException) {
        Companion.setCreateCredentialException(intent, createCredentialException);
    }

    @JvmStatic
    public static final void setCreateCredentialResponse(Intent intent, CreateCredentialResponse createCredentialResponse) {
        Companion.setCreateCredentialResponse(intent, createCredentialResponse);
    }

    @JvmStatic
    public static final void setGetCredentialException(Intent intent, GetCredentialException getCredentialException) {
        Companion.setGetCredentialException(intent, getCredentialException);
    }

    @JvmStatic
    public static final void setGetCredentialResponse(Intent intent, GetCredentialResponse getCredentialResponse) {
        Companion.setGetCredentialResponse(intent, getCredentialResponse);
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0015H\u0007J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0017H\u0007J\u0018\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0019H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/credentials/provider/PendingIntentHandler$Companion;", "", "()V", "TAG", "", "retrieveBeginGetCredentialRequest", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "intent", "Landroid/content/Intent;", "retrieveProviderCreateCredentialRequest", "Landroidx/credentials/provider/ProviderCreateCredentialRequest;", "retrieveProviderGetCredentialRequest", "Landroidx/credentials/provider/ProviderGetCredentialRequest;", "setBeginGetCredentialResponse", "", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "setCreateCredentialException", "exception", "Landroidx/credentials/exceptions/CreateCredentialException;", "setCreateCredentialResponse", "Landroidx/credentials/CreateCredentialResponse;", "setGetCredentialException", "Landroidx/credentials/exceptions/GetCredentialException;", "setGetCredentialResponse", "Landroidx/credentials/GetCredentialResponse;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PendingIntentHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ProviderCreateCredentialRequest retrieveProviderCreateCredentialRequest(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            ProviderCreateCredentialRequest providerCreateCredentialRequest = (CreateCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.CREATE_CREDENTIAL_REQUEST", CreateCredentialRequest.class);
            if (providerCreateCredentialRequest == null) {
                Log.i(PendingIntentHandler.TAG, "Request not found in pendingIntent");
                return providerCreateCredentialRequest;
            }
            CreateCredentialRequest.Companion companion = androidx.credentials.CreateCredentialRequest.Companion;
            String type = providerCreateCredentialRequest.getType();
            Intrinsics.checkNotNullExpressionValue(type, "frameworkReq.type");
            Bundle data = providerCreateCredentialRequest.getData();
            Intrinsics.checkNotNullExpressionValue(data, "frameworkReq.data");
            Bundle data2 = providerCreateCredentialRequest.getData();
            Intrinsics.checkNotNullExpressionValue(data2, "frameworkReq.data");
            androidx.credentials.CreateCredentialRequest createFrom = companion.createFrom(type, data, data2, false, providerCreateCredentialRequest.getCallingAppInfo().getOrigin());
            if (createFrom == null) {
                return null;
            }
            String packageName = providerCreateCredentialRequest.getCallingAppInfo().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "frameworkReq.callingAppInfo.packageName");
            SigningInfo signingInfo = providerCreateCredentialRequest.getCallingAppInfo().getSigningInfo();
            Intrinsics.checkNotNullExpressionValue(signingInfo, "frameworkReq.callingAppInfo.signingInfo");
            return new ProviderCreateCredentialRequest(createFrom, new CallingAppInfo(packageName, signingInfo, providerCreateCredentialRequest.getCallingAppInfo().getOrigin()));
        }

        @JvmStatic
        public final BeginGetCredentialRequest retrieveBeginGetCredentialRequest(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            BeginGetCredentialRequest beginGetCredentialRequest = (BeginGetCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_REQUEST", BeginGetCredentialRequest.class);
            if (beginGetCredentialRequest != null) {
                return BeginGetCredentialUtil.Companion.convertToJetpackRequest$credentials_release(beginGetCredentialRequest);
            }
            return null;
        }

        @JvmStatic
        public final void setCreateCredentialResponse(Intent intent, CreateCredentialResponse createCredentialResponse) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(createCredentialResponse, Response.TYPE);
            intent.putExtra("android.service.credentials.extra.CREATE_CREDENTIAL_RESPONSE", new android.credentials.CreateCredentialResponse(createCredentialResponse.getData()));
        }

        @JvmStatic
        public final ProviderGetCredentialRequest retrieveProviderGetCredentialRequest(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            GetCredentialRequest getCredentialRequest = (GetCredentialRequest) intent.getParcelableExtra("android.service.credentials.extra.GET_CREDENTIAL_REQUEST", GetCredentialRequest.class);
            if (getCredentialRequest == null) {
                Log.i(PendingIntentHandler.TAG, "Get request from framework is null");
                return null;
            }
            ProviderGetCredentialRequest.Companion companion = ProviderGetCredentialRequest.Companion;
            Object collect = getCredentialRequest.getCredentialOptions().stream().map(new PendingIntentHandler$Companion$$ExternalSyntheticLambda0(PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1.INSTANCE)).collect(Collectors.toList());
            Intrinsics.checkNotNullExpressionValue(collect, "frameworkReq.credentialO…lect(Collectors.toList())");
            String packageName = getCredentialRequest.getCallingAppInfo().getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "frameworkReq.callingAppInfo.packageName");
            SigningInfo signingInfo = getCredentialRequest.getCallingAppInfo().getSigningInfo();
            Intrinsics.checkNotNullExpressionValue(signingInfo, "frameworkReq.callingAppInfo.signingInfo");
            return companion.createFrom$credentials_release((List) collect, new CallingAppInfo(packageName, signingInfo, getCredentialRequest.getCallingAppInfo().getOrigin()));
        }

        /* access modifiers changed from: private */
        public static final CredentialOption retrieveProviderGetCredentialRequest$lambda$1(Function1 function1, Object obj) {
            Intrinsics.checkNotNullParameter(function1, "$tmp0");
            return (CredentialOption) function1.invoke(obj);
        }

        @JvmStatic
        public final void setGetCredentialResponse(Intent intent, GetCredentialResponse getCredentialResponse) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(getCredentialResponse, Response.TYPE);
            intent.putExtra("android.service.credentials.extra.GET_CREDENTIAL_RESPONSE", new android.credentials.GetCredentialResponse(new Credential(getCredentialResponse.getCredential().getType(), getCredentialResponse.getCredential().getData())));
        }

        @JvmStatic
        public final void setBeginGetCredentialResponse(Intent intent, BeginGetCredentialResponse beginGetCredentialResponse) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
            intent.putExtra("android.service.credentials.extra.BEGIN_GET_CREDENTIAL_RESPONSE", BeginGetCredentialUtil.Companion.convertToFrameworkResponse(beginGetCredentialResponse));
        }

        @JvmStatic
        public final void setGetCredentialException(Intent intent, GetCredentialException getCredentialException) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(getCredentialException, "exception");
            intent.putExtra("android.service.credentials.extra.GET_CREDENTIAL_EXCEPTION", new android.credentials.GetCredentialException(getCredentialException.getType(), getCredentialException.getMessage()));
        }

        @JvmStatic
        public final void setCreateCredentialException(Intent intent, CreateCredentialException createCredentialException) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(createCredentialException, "exception");
            intent.putExtra("android.service.credentials.extra.CREATE_CREDENTIAL_EXCEPTION", new android.credentials.CreateCredentialException(createCredentialException.getType(), createCredentialException.getMessage()));
        }
    }
}
