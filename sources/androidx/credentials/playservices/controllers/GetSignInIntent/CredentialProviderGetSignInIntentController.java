package androidx.credentials.playservices.controllers.GetSignInIntent;

import android.content.Context;
import android.content.Intent;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialUnsupportedException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.HiddenActivity;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import io.sentry.SentryBaseEvent;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.Response;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u001d\b\u0000\u0018\u0000 .2 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001.B\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0002H\u0017J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0004H\u0014J\u0010\u0010#\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u0004H\u0007J'\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(2\b\u0010*\u001a\u0004\u0018\u00010+H\u0000¢\u0006\u0002\b,J6\u0010-\u001a\u00020&2\u0006\u0010 \u001a\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R0\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000b8\u0006@\u0006X.¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\rR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X.¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\r\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0004\n\u0002\u0010\u001e¨\u0006/"}, d2 = {"Landroidx/credentials/playservices/controllers/GetSignInIntent/CredentialProviderGetSignInIntentController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/GetCredentialRequest;", "Lcom/google/android/gms/auth/api/identity/GetSignInIntentRequest;", "Lcom/google/android/gms/auth/api/identity/SignInCredential;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callback", "Landroidx/credentials/CredentialManagerCallback;", "getCallback$annotations", "()V", "getCallback", "()Landroidx/credentials/CredentialManagerCallback;", "setCallback", "(Landroidx/credentials/CredentialManagerCallback;)V", "cancellationSignal", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "executor", "Ljava/util/concurrent/Executor;", "getExecutor$annotations", "getExecutor", "()Ljava/util/concurrent/Executor;", "setExecutor", "(Ljava/util/concurrent/Executor;)V", "resultReceiver", "androidx/credentials/playservices/controllers/GetSignInIntent/CredentialProviderGetSignInIntentController$resultReceiver$1", "Landroidx/credentials/playservices/controllers/GetSignInIntent/CredentialProviderGetSignInIntentController$resultReceiver$1;", "convertRequestToPlayServices", "request", "convertResponseToCredentialManager", "response", "createGoogleIdCredential", "Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "handleResponse", "", "uniqueRequestCode", "", "resultCode", "data", "Landroid/content/Intent;", "handleResponse$credentials_play_services_auth_release", "invokePlayServices", "Companion", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderGetSignInIntentController.kt */
public final class CredentialProviderGetSignInIntentController extends CredentialProviderController<GetCredentialRequest, GetSignInIntentRequest, SignInCredential, GetCredentialResponse, GetCredentialException> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "GetSignInIntent";
    /* access modifiers changed from: private */
    public static CredentialProviderGetSignInIntentController controller;
    public CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback;
    /* access modifiers changed from: private */
    public CancellationSignal cancellationSignal;
    private final Context context;
    public Executor executor;
    private final CredentialProviderGetSignInIntentController$resultReceiver$1 resultReceiver = new CredentialProviderGetSignInIntentController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));

    public static /* synthetic */ void getCallback$annotations() {
    }

    private static /* synthetic */ void getCancellationSignal$annotations() {
    }

    public static /* synthetic */ void getExecutor$annotations() {
    }

    @JvmStatic
    public static final CredentialProviderGetSignInIntentController getInstance(Context context2) {
        return Companion.getInstance(context2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CredentialProviderGetSignInIntentController(Context context2) {
        super(context2);
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final CredentialManagerCallback<GetCredentialResponse, GetCredentialException> getCallback() {
        CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback = this.callback;
        if (credentialManagerCallback != null) {
            return credentialManagerCallback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("callback");
        return null;
    }

    public final void setCallback(CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "<set-?>");
        this.callback = credentialManagerCallback;
    }

    public final Executor getExecutor() {
        Executor executor2 = this.executor;
        if (executor2 != null) {
            return executor2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("executor");
        return null;
    }

    public final void setExecutor(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "<set-?>");
        this.executor = executor2;
    }

    public void invokePlayServices(GetCredentialRequest getCredentialRequest, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback, Executor executor2, CancellationSignal cancellationSignal2) {
        Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.cancellationSignal = cancellationSignal2;
        setCallback(credentialManagerCallback);
        setExecutor(executor2);
        if (!CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth_release(cancellationSignal2)) {
            try {
                GetSignInIntentRequest convertRequestToPlayServices = convertRequestToPlayServices(getCredentialRequest);
                Intent intent = new Intent(this.context, HiddenActivity.class);
                intent.putExtra(CredentialProviderBaseController.REQUEST_TAG, convertRequestToPlayServices);
                generateHiddenActivityIntent(this.resultReceiver, intent, CredentialProviderBaseController.SIGN_IN_INTENT_TAG);
                this.context.startActivity(intent);
            } catch (Exception e) {
                if (e instanceof GetCredentialUnsupportedException) {
                    CredentialProviderController.Companion companion = CredentialProviderController.Companion;
                    CredentialProviderController.cancelOrCallbackExceptionOrResult(cancellationSignal2, new CredentialProviderGetSignInIntentController$invokePlayServices$1(this, e));
                    return;
                }
                CredentialProviderController.Companion companion2 = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(cancellationSignal2, new CredentialProviderGetSignInIntentController$invokePlayServices$2(this));
            }
        }
    }

    public GetSignInIntentRequest convertRequestToPlayServices(GetCredentialRequest getCredentialRequest) {
        Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        if (getCredentialRequest.getCredentialOptions().size() == 1) {
            CredentialOption credentialOption = getCredentialRequest.getCredentialOptions().get(0);
            Intrinsics.checkNotNull(credentialOption, "null cannot be cast to non-null type com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption");
            GetSignInWithGoogleOption getSignInWithGoogleOption = (GetSignInWithGoogleOption) credentialOption;
            GetSignInIntentRequest build = GetSignInIntentRequest.builder().setServerClientId(getSignInWithGoogleOption.getServerClientId()).filterByHostedDomain(getSignInWithGoogleOption.getHostedDomainFilter()).setNonce(getSignInWithGoogleOption.getNonce()).build();
            Intrinsics.checkNotNullExpressionValue(build, "builder()\n            .s…nce)\n            .build()");
            return build;
        }
        throw new GetCredentialUnsupportedException("GetSignInWithGoogleOption cannot be combined with other options.");
    }

    /* access modifiers changed from: protected */
    public GetCredentialResponse convertResponseToCredentialManager(SignInCredential signInCredential) {
        Credential credential;
        Intrinsics.checkNotNullParameter(signInCredential, Response.TYPE);
        if (signInCredential.getGoogleIdToken() != null) {
            credential = createGoogleIdCredential(signInCredential);
        } else {
            SentryLogcatAdapter.w(TAG, "Credential returned but no google Id found");
            credential = null;
        }
        if (credential != null) {
            return new GetCredentialResponse(credential);
        }
        throw new GetCredentialUnknownException("When attempting to convert get response, null credential found");
    }

    public final GoogleIdTokenCredential createGoogleIdCredential(SignInCredential signInCredential) {
        Intrinsics.checkNotNullParameter(signInCredential, Response.TYPE);
        GoogleIdTokenCredential.Builder builder = new GoogleIdTokenCredential.Builder();
        String id = signInCredential.getId();
        Intrinsics.checkNotNullExpressionValue(id, "response.id");
        GoogleIdTokenCredential.Builder id2 = builder.setId(id);
        try {
            String googleIdToken = signInCredential.getGoogleIdToken();
            Intrinsics.checkNotNull(googleIdToken);
            id2.setIdToken(googleIdToken);
            if (signInCredential.getDisplayName() != null) {
                id2.setDisplayName(signInCredential.getDisplayName());
            }
            if (signInCredential.getGivenName() != null) {
                id2.setGivenName(signInCredential.getGivenName());
            }
            if (signInCredential.getFamilyName() != null) {
                id2.setFamilyName(signInCredential.getFamilyName());
            }
            if (signInCredential.getPhoneNumber() != null) {
                id2.setPhoneNumber(signInCredential.getPhoneNumber());
            }
            if (signInCredential.getProfilePictureUri() != null) {
                id2.setProfilePictureUri(signInCredential.getProfilePictureUri());
            }
            return id2.build();
        } catch (Exception unused) {
            throw new GetCredentialUnknownException("When attempting to convert get response, null Google ID Token found");
        }
    }

    public final void handleResponse$credentials_play_services_auth_release(int i, int i2, Intent intent) {
        CredentialProviderBaseController.Companion companion = CredentialProviderBaseController.Companion;
        if (i != CredentialProviderBaseController.getCONTROLLER_REQUEST_CODE()) {
            StringBuilder sb = new StringBuilder("Returned request code ");
            CredentialProviderBaseController.Companion companion2 = CredentialProviderBaseController.Companion;
            SentryLogcatAdapter.w(TAG, sb.append(CredentialProviderBaseController.getCONTROLLER_REQUEST_CODE()).append(" which  does not match what was given ").append(i).toString());
            return;
        }
        CredentialProviderController.Companion companion3 = CredentialProviderController.Companion;
        if (!CredentialProviderController.maybeReportErrorResultCodeGet(i2, CredentialProviderGetSignInIntentController$handleResponse$1.INSTANCE, new CredentialProviderGetSignInIntentController$handleResponse$2(this), this.cancellationSignal)) {
            try {
                SignInCredential signInCredentialFromIntent = Identity.getSignInClient(this.context).getSignInCredentialFromIntent(intent);
                Intrinsics.checkNotNullExpressionValue(signInCredentialFromIntent, "getSignInClient(context)…redentialFromIntent(data)");
                GetCredentialResponse convertResponseToCredentialManager = convertResponseToCredentialManager(signInCredentialFromIntent);
                CredentialProviderController.Companion companion4 = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(this.cancellationSignal, new CredentialProviderGetSignInIntentController$handleResponse$3(this, convertResponseToCredentialManager));
            } catch (ApiException e) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = new GetCredentialUnknownException(e.getMessage());
                if (e.getStatusCode() == 16) {
                    objectRef.element = new GetCredentialCancellationException(e.getMessage());
                } else if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(e.getStatusCode()))) {
                    objectRef.element = new GetCredentialInterruptedException(e.getMessage());
                }
                CredentialProviderController.Companion companion5 = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(this.cancellationSignal, new CredentialProviderGetSignInIntentController$handleResponse$4(this, objectRef));
            } catch (GetCredentialException e2) {
                CredentialProviderController.Companion companion6 = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(this.cancellationSignal, new CredentialProviderGetSignInIntentController$handleResponse$5(this, e2));
            } catch (Throwable th) {
                GetCredentialUnknownException getCredentialUnknownException = new GetCredentialUnknownException(th.getMessage());
                CredentialProviderController.Companion companion7 = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(this.cancellationSignal, new CredentialProviderGetSignInIntentController$handleResponse$6(this, getCredentialUnknownException));
            }
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/credentials/playservices/controllers/GetSignInIntent/CredentialProviderGetSignInIntentController$Companion;", "", "()V", "TAG", "", "controller", "Landroidx/credentials/playservices/controllers/GetSignInIntent/CredentialProviderGetSignInIntentController;", "getInstance", "context", "Landroid/content/Context;", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialProviderGetSignInIntentController.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CredentialProviderGetSignInIntentController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (CredentialProviderGetSignInIntentController.controller == null) {
                CredentialProviderGetSignInIntentController.controller = new CredentialProviderGetSignInIntentController(context);
            }
            CredentialProviderGetSignInIntentController access$getController$cp = CredentialProviderGetSignInIntentController.controller;
            Intrinsics.checkNotNull(access$getController$cp);
            return access$getController$cp;
        }
    }
}
