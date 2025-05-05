package androidx.credentials.playservices;

import android.content.Context;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePasswordRequest;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CredentialOption;
import androidx.credentials.CredentialProvider;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.playservices.controllers.BeginSignIn.CredentialProviderBeginSignInController;
import androidx.credentials.playservices.controllers.CreatePassword.CredentialProviderCreatePasswordController;
import androidx.credentials.playservices.controllers.CreatePublicKeyCredential.CredentialProviderCreatePublicKeyCredentialController;
import androidx.credentials.playservices.controllers.GetSignInIntent.CredentialProviderGetSignInIntentController;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import io.sentry.SentryBaseEvent;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J8\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0012\u0004\u0012\u00020\u001c0\u001aH\u0016J>\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001aH\u0016J>\u0010!\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\"2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\u001aH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006&"}, d2 = {"Landroidx/credentials/playservices/CredentialProviderPlayServicesImpl;", "Landroidx/credentials/CredentialProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "googleApiAvailability", "Lcom/google/android/gms/common/GoogleApiAvailability;", "getGoogleApiAvailability$annotations", "()V", "getGoogleApiAvailability", "()Lcom/google/android/gms/common/GoogleApiAvailability;", "setGoogleApiAvailability", "(Lcom/google/android/gms/common/GoogleApiAvailability;)V", "isAvailableOnDevice", "", "isGooglePlayServicesAvailable", "", "onClearCredential", "", "request", "Landroidx/credentials/ClearCredentialStateRequest;", "cancellationSignal", "Landroid/os/CancellationSignal;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/credentials/CredentialManagerCallback;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onCreateCredential", "Landroidx/credentials/CreateCredentialRequest;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onGetCredential", "Landroidx/credentials/GetCredentialRequest;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "Companion", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderPlayServicesImpl.kt */
public final class CredentialProviderPlayServicesImpl implements CredentialProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "PlayServicesImpl";
    private final Context context;
    private GoogleApiAvailability googleApiAvailability;

    public static /* synthetic */ void getGoogleApiAvailability$annotations() {
    }

    public CredentialProviderPlayServicesImpl(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        this.googleApiAvailability = instance;
    }

    public final GoogleApiAvailability getGoogleApiAvailability() {
        return this.googleApiAvailability;
    }

    public final void setGoogleApiAvailability(GoogleApiAvailability googleApiAvailability2) {
        Intrinsics.checkNotNullParameter(googleApiAvailability2, "<set-?>");
        this.googleApiAvailability = googleApiAvailability2;
    }

    public void onGetCredential(Context context2, GetCredentialRequest getCredentialRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        Companion companion = Companion;
        if (!companion.cancellationReviewer$credentials_play_services_auth_release(cancellationSignal)) {
            if (companion.isGetSignInIntentRequest$credentials_play_services_auth_release(getCredentialRequest)) {
                new CredentialProviderGetSignInIntentController(context2).invokePlayServices(getCredentialRequest, credentialManagerCallback, executor, cancellationSignal);
            } else {
                new CredentialProviderBeginSignInController(context2).invokePlayServices(getCredentialRequest, credentialManagerCallback, executor, cancellationSignal);
            }
        }
    }

    public void onCreateCredential(Context context2, CreateCredentialRequest createCredentialRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(createCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!Companion.cancellationReviewer$credentials_play_services_auth_release(cancellationSignal)) {
            if (createCredentialRequest instanceof CreatePasswordRequest) {
                CredentialProviderCreatePasswordController.Companion.getInstance(context2).invokePlayServices((CreatePasswordRequest) createCredentialRequest, credentialManagerCallback, executor, cancellationSignal);
            } else if (createCredentialRequest instanceof CreatePublicKeyCredentialRequest) {
                CredentialProviderCreatePublicKeyCredentialController.Companion.getInstance(context2).invokePlayServices((CreatePublicKeyCredentialRequest) createCredentialRequest, credentialManagerCallback, executor, cancellationSignal);
            } else {
                throw new UnsupportedOperationException("Create Credential request is unsupported, not password or publickeycredential");
            }
        }
    }

    public boolean isAvailableOnDevice() {
        return isGooglePlayServicesAvailable(this.context) == 0;
    }

    private final int isGooglePlayServicesAvailable(Context context2) {
        return this.googleApiAvailability.isGooglePlayServicesAvailable(context2);
    }

    public void onClearCredential(ClearCredentialStateRequest clearCredentialStateRequest, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback<Void, ClearCredentialException> credentialManagerCallback) {
        Intrinsics.checkNotNullParameter(clearCredentialStateRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        if (!Companion.cancellationReviewer$credentials_play_services_auth_release(cancellationSignal)) {
            Identity.getSignInClient(this.context).signOut().addOnSuccessListener(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda0(new CredentialProviderPlayServicesImpl$onClearCredential$1(cancellationSignal, executor, credentialManagerCallback))).addOnFailureListener(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda1(this, cancellationSignal, executor, credentialManagerCallback));
        }
    }

    /* access modifiers changed from: private */
    public static final void onClearCredential$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void onClearCredential$lambda$2(CredentialProviderPlayServicesImpl credentialProviderPlayServicesImpl, CancellationSignal cancellationSignal, Executor executor, CredentialManagerCallback credentialManagerCallback, Exception exc) {
        Intrinsics.checkNotNullParameter(credentialProviderPlayServicesImpl, "this$0");
        Intrinsics.checkNotNullParameter(executor, "$executor");
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "$callback");
        Intrinsics.checkNotNullParameter(exc, "e");
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth_release(cancellationSignal, new CredentialProviderPlayServicesImpl$onClearCredential$2$1$1(exc, executor, credentialManagerCallback));
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\tJ%\u0010\n\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0000¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/credentials/playservices/CredentialProviderPlayServicesImpl$Companion;", "", "()V", "TAG", "", "cancellationReviewer", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "cancellationReviewer$credentials_play_services_auth_release", "cancellationReviewerWithCallback", "", "callback", "Lkotlin/Function0;", "cancellationReviewerWithCallback$credentials_play_services_auth_release", "isGetSignInIntentRequest", "request", "Landroidx/credentials/GetCredentialRequest;", "isGetSignInIntentRequest$credentials_play_services_auth_release", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialProviderPlayServicesImpl.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void cancellationReviewerWithCallback$credentials_play_services_auth_release(CancellationSignal cancellationSignal, Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function0, "callback");
            if (!cancellationReviewer$credentials_play_services_auth_release(cancellationSignal)) {
                function0.invoke();
            }
        }

        public final boolean cancellationReviewer$credentials_play_services_auth_release(CancellationSignal cancellationSignal) {
            if (cancellationSignal == null) {
                Log.i(CredentialProviderPlayServicesImpl.TAG, "No cancellationSignal found");
                return false;
            } else if (!cancellationSignal.isCanceled()) {
                return false;
            } else {
                Log.i(CredentialProviderPlayServicesImpl.TAG, "the flow has been canceled");
                return true;
            }
        }

        public final boolean isGetSignInIntentRequest$credentials_play_services_auth_release(GetCredentialRequest getCredentialRequest) {
            Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            for (CredentialOption credentialOption : getCredentialRequest.getCredentialOptions()) {
                if (credentialOption instanceof GetSignInWithGoogleOption) {
                    return true;
                }
            }
            return false;
        }
    }
}
