package androidx.credentials;

import android.content.Context;
import android.credentials.ClearCredentialStateRequest;
import android.credentials.CreateCredentialRequest;
import android.credentials.Credential;
import android.credentials.CredentialManager;
import android.credentials.CredentialOption;
import android.credentials.GetCredentialRequest;
import android.credentials.GetCredentialResponse;
import android.credentials.PrepareGetCredentialResponse;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.credentials.Credential;
import androidx.credentials.PrepareGetCredentialResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialCustomException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialCustomException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.NoCredentialException;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialException;
import androidx.credentials.exceptions.publickeycredential.GetPublicKeyCredentialException;
import androidx.credentials.internal.FrameworkImplHelper;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.Response;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 =2\u00020\u0001:\u0001=B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\rH\u0002J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0000¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0019\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u0016\u0010$\u001a\u00020#2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0002J8\u0010(\u001a\u00020'2\u0006\u0010\t\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0014\u0010.\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u000100\u0012\u0004\u0012\u0002010/H\u0016J>\u00102\u001a\u00020'2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u00180/H\u0016J>\u00104\u001a\u00020'2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001d0/H\u0016J>\u00104\u001a\u00020'2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00105\u001a\u0002062\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u001d0/H\u0016J6\u00107\u001a\u00020'2\u0006\u0010\t\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001d0/H\u0016J\u0018\u00108\u001a\u00020'2\u0006\u0010\t\u001a\u00020\n2\u0006\u00109\u001a\u00020:H\u0003J\u0018\u0010;\u001a\u00020'2\u0006\u0010\t\u001a\u00020\r2\u0006\u00109\u001a\u00020<H\u0003R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl;", "Landroidx/credentials/CredentialProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "credentialManager", "Landroid/credentials/CredentialManager;", "convertCreateRequestToFrameworkClass", "Landroid/credentials/CreateCredentialRequest;", "request", "Landroidx/credentials/CreateCredentialRequest;", "convertGetRequestToFrameworkClass", "Landroid/credentials/GetCredentialRequest;", "Landroidx/credentials/GetCredentialRequest;", "convertGetResponseToJetpackClass", "Landroidx/credentials/GetCredentialResponse;", "response", "Landroid/credentials/GetCredentialResponse;", "convertGetResponseToJetpackClass$credentials_release", "convertPrepareGetResponseToJetpackClass", "Landroidx/credentials/PrepareGetCredentialResponse;", "Landroid/credentials/PrepareGetCredentialResponse;", "convertPrepareGetResponseToJetpackClass$credentials_release", "convertToJetpackCreateException", "Landroidx/credentials/exceptions/CreateCredentialException;", "error", "Landroid/credentials/CreateCredentialException;", "convertToJetpackCreateException$credentials_release", "convertToJetpackGetException", "Landroidx/credentials/exceptions/GetCredentialException;", "Landroid/credentials/GetCredentialException;", "convertToJetpackGetException$credentials_release", "createFrameworkClearCredentialRequest", "Landroid/credentials/ClearCredentialStateRequest;", "isAvailableOnDevice", "", "isCredmanDisabled", "handleNullCredMan", "Lkotlin/Function0;", "", "onClearCredential", "Landroidx/credentials/ClearCredentialStateRequest;", "cancellationSignal", "Landroid/os/CancellationSignal;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/credentials/CredentialManagerCallback;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onCreateCredential", "Landroidx/credentials/CreateCredentialResponse;", "onGetCredential", "pendingGetCredentialHandle", "Landroidx/credentials/PrepareGetCredentialResponse$PendingGetCredentialHandle;", "onPrepareCredential", "setOriginForCreateRequest", "builder", "Landroid/credentials/CreateCredentialRequest$Builder;", "setOriginForGetRequest", "Landroid/credentials/GetCredentialRequest$Builder;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFrameworkImpl.kt */
public final class CredentialProviderFrameworkImpl implements CredentialProvider {
    private static final String CREATE_DOM_EXCEPTION_PREFIX = "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String GET_DOM_EXCEPTION_PREFIX = "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private static final String TAG = "CredManProvService";
    private final CredentialManager credentialManager;

    public CredentialProviderFrameworkImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.credentialManager = (CredentialManager) context.getSystemService("credential");
    }

    public void onPrepareCredential(GetCredentialRequest getCredentialRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<PrepareGetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!isCredmanDisabled(new CredentialProviderFrameworkImpl$onPrepareCredential$1(credentialManagerCallback))) {
            OutcomeReceiver credentialProviderFrameworkImpl$onPrepareCredential$outcome$1 = new CredentialProviderFrameworkImpl$onPrepareCredential$outcome$1(credentialManagerCallback, this);
            CredentialManager credentialManager2 = this.credentialManager;
            Intrinsics.checkNotNull(credentialManager2);
            credentialManager2.prepareGetCredential(convertGetRequestToFrameworkClass(getCredentialRequest), cancellationSignal, executor, credentialProviderFrameworkImpl$onPrepareCredential$outcome$1);
        }
    }

    public void onGetCredential(Context context, PrepareGetCredentialResponse.PendingGetCredentialHandle pendingGetCredentialHandle, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pendingGetCredentialHandle, "pendingGetCredentialHandle");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!isCredmanDisabled(new CredentialProviderFrameworkImpl$onGetCredential$1(credentialManagerCallback))) {
            OutcomeReceiver credentialProviderFrameworkImpl$onGetCredential$outcome$1 = new CredentialProviderFrameworkImpl$onGetCredential$outcome$1(credentialManagerCallback, this);
            CredentialManager credentialManager2 = this.credentialManager;
            Intrinsics.checkNotNull(credentialManager2);
            PrepareGetCredentialResponse.PendingGetCredentialHandle frameworkHandle = pendingGetCredentialHandle.getFrameworkHandle();
            Intrinsics.checkNotNull(frameworkHandle);
            credentialManager2.getCredential(context, frameworkHandle, cancellationSignal, executor, credentialProviderFrameworkImpl$onGetCredential$outcome$1);
        }
    }

    public void onGetCredential(Context context, GetCredentialRequest getCredentialRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!isCredmanDisabled(new CredentialProviderFrameworkImpl$onGetCredential$2(credentialManagerCallback))) {
            OutcomeReceiver credentialProviderFrameworkImpl$onGetCredential$outcome$2 = new CredentialProviderFrameworkImpl$onGetCredential$outcome$2(credentialManagerCallback, this);
            CredentialManager credentialManager2 = this.credentialManager;
            Intrinsics.checkNotNull(credentialManager2);
            credentialManager2.getCredential(context, convertGetRequestToFrameworkClass(getCredentialRequest), cancellationSignal, executor, credentialProviderFrameworkImpl$onGetCredential$outcome$2);
        }
    }

    private final boolean isCredmanDisabled(Function0<Unit> function0) {
        if (this.credentialManager != null) {
            return false;
        }
        function0.invoke();
        return true;
    }

    public void onCreateCredential(Context context, CreateCredentialRequest createCredentialRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(createCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!isCredmanDisabled(new CredentialProviderFrameworkImpl$onCreateCredential$1(credentialManagerCallback))) {
            OutcomeReceiver credentialProviderFrameworkImpl$onCreateCredential$outcome$1 = new CredentialProviderFrameworkImpl$onCreateCredential$outcome$1(credentialManagerCallback, createCredentialRequest, this);
            CredentialManager credentialManager2 = this.credentialManager;
            Intrinsics.checkNotNull(credentialManager2);
            credentialManager2.createCredential(context, convertCreateRequestToFrameworkClass(createCredentialRequest, context), cancellationSignal, executor, credentialProviderFrameworkImpl$onCreateCredential$outcome$1);
        }
    }

    private final CreateCredentialRequest convertCreateRequestToFrameworkClass(CreateCredentialRequest createCredentialRequest, Context context) {
        CreateCredentialRequest.Builder alwaysSendAppInfoToProvider = new CreateCredentialRequest.Builder(createCredentialRequest.getType(), FrameworkImplHelper.Companion.getFinalCreateCredentialData(createCredentialRequest, context), createCredentialRequest.getCandidateQueryData()).setIsSystemProviderRequired(createCredentialRequest.isSystemProviderRequired()).setAlwaysSendAppInfoToProvider(true);
        Intrinsics.checkNotNullExpressionValue(alwaysSendAppInfoToProvider, "Builder(request.type,\n  …ndAppInfoToProvider(true)");
        setOriginForCreateRequest(createCredentialRequest, alwaysSendAppInfoToProvider);
        CreateCredentialRequest build = alwaysSendAppInfoToProvider.build();
        Intrinsics.checkNotNullExpressionValue(build, "createCredentialRequestBuilder.build()");
        return build;
    }

    private final void setOriginForCreateRequest(CreateCredentialRequest createCredentialRequest, CreateCredentialRequest.Builder builder) {
        if (createCredentialRequest.getOrigin() != null) {
            builder.setOrigin(createCredentialRequest.getOrigin());
        }
    }

    private final GetCredentialRequest convertGetRequestToFrameworkClass(GetCredentialRequest getCredentialRequest) {
        GetCredentialRequest.Builder builder = new GetCredentialRequest.Builder(GetCredentialRequest.Companion.toRequestDataBundle(getCredentialRequest));
        for (CredentialOption credentialOption : getCredentialRequest.getCredentialOptions()) {
            builder.addCredentialOption(new CredentialOption.Builder(credentialOption.getType(), credentialOption.getRequestData(), credentialOption.getCandidateQueryData()).setIsSystemProviderRequired(credentialOption.isSystemProviderRequired()).setAllowedProviders(credentialOption.getAllowedProviders()).build());
        }
        setOriginForGetRequest(getCredentialRequest, builder);
        GetCredentialRequest build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    private final void setOriginForGetRequest(GetCredentialRequest getCredentialRequest, GetCredentialRequest.Builder builder) {
        if (getCredentialRequest.getOrigin() != null) {
            builder.setOrigin(getCredentialRequest.getOrigin());
        }
    }

    private final ClearCredentialStateRequest createFrameworkClearCredentialRequest() {
        return new ClearCredentialStateRequest(new Bundle());
    }

    public final GetCredentialException convertToJetpackGetException$credentials_release(android.credentials.GetCredentialException getCredentialException) {
        Intrinsics.checkNotNullParameter(getCredentialException, "error");
        String type = getCredentialException.getType();
        switch (type.hashCode()) {
            case -781118336:
                if (type.equals(GetCredentialUnknownException.TYPE_GET_CREDENTIAL_UNKNOWN_EXCEPTION)) {
                    return new GetCredentialUnknownException(getCredentialException.getMessage());
                }
                break;
            case -45448328:
                if (type.equals(GetCredentialInterruptedException.TYPE_GET_CREDENTIAL_INTERRUPTED_EXCEPTION)) {
                    return new GetCredentialInterruptedException(getCredentialException.getMessage());
                }
                break;
            case 580557411:
                if (type.equals(GetCredentialCancellationException.TYPE_GET_CREDENTIAL_CANCELLATION_EXCEPTION)) {
                    return new GetCredentialCancellationException(getCredentialException.getMessage());
                }
                break;
            case 627896683:
                if (type.equals(NoCredentialException.TYPE_FRAMEWORK_TYPE_NO_CREDENTIAL)) {
                    return new NoCredentialException(getCredentialException.getMessage());
                }
                break;
        }
        String type2 = getCredentialException.getType();
        Intrinsics.checkNotNullExpressionValue(type2, "error.type");
        if (StringsKt.startsWith$default(type2, "androidx.credentials.TYPE_GET_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false, 2, (Object) null)) {
            GetPublicKeyCredentialException.Companion companion = GetPublicKeyCredentialException.Companion;
            String type3 = getCredentialException.getType();
            Intrinsics.checkNotNullExpressionValue(type3, "error.type");
            return companion.createFrom(type3, getCredentialException.getMessage());
        }
        String type4 = getCredentialException.getType();
        Intrinsics.checkNotNullExpressionValue(type4, "error.type");
        return new GetCredentialCustomException(type4, getCredentialException.getMessage());
    }

    public final CreateCredentialException convertToJetpackCreateException$credentials_release(android.credentials.CreateCredentialException createCredentialException) {
        Intrinsics.checkNotNullParameter(createCredentialException, "error");
        String type = createCredentialException.getType();
        switch (type.hashCode()) {
            case -2055374133:
                if (type.equals(CreateCredentialCancellationException.TYPE_CREATE_CREDENTIAL_CANCELLATION_EXCEPTION)) {
                    return new CreateCredentialCancellationException(createCredentialException.getMessage());
                }
                break;
            case 1316905704:
                if (type.equals(CreateCredentialUnknownException.TYPE_CREATE_CREDENTIAL_UNKNOWN_EXCEPTION)) {
                    return new CreateCredentialUnknownException(createCredentialException.getMessage());
                }
                break;
            case 2092588512:
                if (type.equals(CreateCredentialInterruptedException.TYPE_CREATE_CREDENTIAL_INTERRUPTED_EXCEPTION)) {
                    return new CreateCredentialInterruptedException(createCredentialException.getMessage());
                }
                break;
            case 2131915191:
                if (type.equals(CreateCredentialNoCreateOptionException.TYPE_CREATE_CREDENTIAL_NO_CREATE_OPTION)) {
                    return new CreateCredentialNoCreateOptionException(createCredentialException.getMessage());
                }
                break;
        }
        String type2 = createCredentialException.getType();
        Intrinsics.checkNotNullExpressionValue(type2, "error.type");
        if (StringsKt.startsWith$default(type2, "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", false, 2, (Object) null)) {
            CreatePublicKeyCredentialException.Companion companion = CreatePublicKeyCredentialException.Companion;
            String type3 = createCredentialException.getType();
            Intrinsics.checkNotNullExpressionValue(type3, "error.type");
            return companion.createFrom(type3, createCredentialException.getMessage());
        }
        String type4 = createCredentialException.getType();
        Intrinsics.checkNotNullExpressionValue(type4, "error.type");
        return new CreateCredentialCustomException(type4, createCredentialException.getMessage());
    }

    public final GetCredentialResponse convertGetResponseToJetpackClass$credentials_release(GetCredentialResponse getCredentialResponse) {
        Intrinsics.checkNotNullParameter(getCredentialResponse, Response.TYPE);
        Credential credential = getCredentialResponse.getCredential();
        Intrinsics.checkNotNullExpressionValue(credential, "response.credential");
        Credential.Companion companion = Credential.Companion;
        String type = credential.getType();
        Intrinsics.checkNotNullExpressionValue(type, "credential.type");
        Bundle data = credential.getData();
        Intrinsics.checkNotNullExpressionValue(data, "credential.data");
        return new GetCredentialResponse(companion.createFrom(type, data));
    }

    public final PrepareGetCredentialResponse convertPrepareGetResponseToJetpackClass$credentials_release(android.credentials.PrepareGetCredentialResponse prepareGetCredentialResponse) {
        Intrinsics.checkNotNullParameter(prepareGetCredentialResponse, Response.TYPE);
        return new PrepareGetCredentialResponse.Builder().setFrameworkResponse(prepareGetCredentialResponse).setPendingGetCredentialHandle(new PrepareGetCredentialResponse.PendingGetCredentialHandle(prepareGetCredentialResponse.getPendingGetCredentialHandle())).build();
    }

    public boolean isAvailableOnDevice() {
        return Build.VERSION.SDK_INT >= 34;
    }

    public void onClearCredential(ClearCredentialStateRequest clearCredentialStateRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(clearCredentialStateRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        Log.i(TAG, "In CredentialProviderFrameworkImpl onClearCredential");
        if (!isCredmanDisabled(new CredentialProviderFrameworkImpl$onClearCredential$1(credentialManagerCallback))) {
            OutcomeReceiver credentialProviderFrameworkImpl$onClearCredential$outcome$1 = new CredentialProviderFrameworkImpl$onClearCredential$outcome$1(credentialManagerCallback);
            CredentialManager credentialManager2 = this.credentialManager;
            Intrinsics.checkNotNull(credentialManager2);
            credentialManager2.clearCredentialState(createFrameworkClearCredentialRequest(), cancellationSignal, executor, credentialProviderFrameworkImpl$onClearCredential$outcome$1);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl$Companion;", "", "()V", "CREATE_DOM_EXCEPTION_PREFIX", "", "GET_DOM_EXCEPTION_PREFIX", "TAG", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialProviderFrameworkImpl.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
