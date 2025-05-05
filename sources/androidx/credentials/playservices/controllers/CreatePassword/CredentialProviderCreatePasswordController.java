package androidx.credentials.playservices.controllers.CreatePassword;

import android.content.Context;
import android.content.Intent;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePasswordRequest;
import androidx.credentials.CreatePasswordResponse;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.HiddenActivity;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.auth.api.identity.SignInPassword;
import io.sentry.SentryBaseEvent;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.Response;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000K\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0014\b\u0000\u0018\u0000 !2 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001!B\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0002H\u0017J\u0015\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0004H\u0017¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001fJ6\u0010 \u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00022\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R$\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000b8\u0002@\u0002X.¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\rR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\""}, d2 = {"Landroidx/credentials/playservices/controllers/CreatePassword/CredentialProviderCreatePasswordController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/CreatePasswordRequest;", "Lcom/google/android/gms/auth/api/identity/SavePasswordRequest;", "", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "callback", "Landroidx/credentials/CredentialManagerCallback;", "getCallback$annotations", "()V", "cancellationSignal", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "executor", "Ljava/util/concurrent/Executor;", "resultReceiver", "androidx/credentials/playservices/controllers/CreatePassword/CredentialProviderCreatePasswordController$resultReceiver$1", "Landroidx/credentials/playservices/controllers/CreatePassword/CredentialProviderCreatePasswordController$resultReceiver$1;", "convertRequestToPlayServices", "request", "convertResponseToCredentialManager", "response", "(Lkotlin/Unit;)Landroidx/credentials/CreateCredentialResponse;", "handleResponse", "uniqueRequestCode", "", "resultCode", "handleResponse$credentials_play_services_auth_release", "invokePlayServices", "Companion", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderCreatePasswordController.kt */
public final class CredentialProviderCreatePasswordController extends CredentialProviderController<CreatePasswordRequest, SavePasswordRequest, Unit, CreateCredentialResponse, CreateCredentialException> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CreatePassword";
    /* access modifiers changed from: private */
    public static CredentialProviderCreatePasswordController controller;
    /* access modifiers changed from: private */
    public CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> callback;
    /* access modifiers changed from: private */
    public CancellationSignal cancellationSignal;
    private final Context context;
    /* access modifiers changed from: private */
    public Executor executor;
    private final CredentialProviderCreatePasswordController$resultReceiver$1 resultReceiver = new CredentialProviderCreatePasswordController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));

    private static /* synthetic */ void getCallback$annotations() {
    }

    private static /* synthetic */ void getCancellationSignal$annotations() {
    }

    @JvmStatic
    public static final CredentialProviderCreatePasswordController getInstance(Context context2) {
        return Companion.getInstance(context2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CredentialProviderCreatePasswordController(Context context2) {
        super(context2);
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public void invokePlayServices(CreatePasswordRequest createPasswordRequest, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> credentialManagerCallback, Executor executor2, CancellationSignal cancellationSignal2) {
        Intrinsics.checkNotNullParameter(createPasswordRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(credentialManagerCallback, "callback");
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.cancellationSignal = cancellationSignal2;
        this.callback = credentialManagerCallback;
        this.executor = executor2;
        if (!CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth_release(cancellationSignal2)) {
            SavePasswordRequest convertRequestToPlayServices = convertRequestToPlayServices(createPasswordRequest);
            Intent intent = new Intent(this.context, HiddenActivity.class);
            intent.putExtra(CredentialProviderBaseController.REQUEST_TAG, convertRequestToPlayServices);
            generateHiddenActivityIntent(this.resultReceiver, intent, CredentialProviderBaseController.CREATE_PASSWORD_TAG);
            try {
                this.context.startActivity(intent);
            } catch (Exception unused) {
                CredentialProviderController.Companion companion = CredentialProviderController.Companion;
                CredentialProviderController.cancelOrCallbackExceptionOrResult(cancellationSignal2, new CredentialProviderCreatePasswordController$invokePlayServices$1(this));
            }
        }
    }

    public final void handleResponse$credentials_play_services_auth_release(int i, int i2) {
        CredentialProviderBaseController.Companion companion = CredentialProviderBaseController.Companion;
        if (i != CredentialProviderBaseController.getCONTROLLER_REQUEST_CODE()) {
            StringBuilder sb = new StringBuilder("Returned request code ");
            CredentialProviderBaseController.Companion companion2 = CredentialProviderBaseController.Companion;
            SentryLogcatAdapter.w(TAG, sb.append(CredentialProviderBaseController.getCONTROLLER_REQUEST_CODE()).append(" which does not match what was given ").append(i).toString());
            return;
        }
        CredentialProviderController.Companion companion3 = CredentialProviderController.Companion;
        if (!CredentialProviderController.maybeReportErrorResultCodeCreate(i2, CredentialProviderCreatePasswordController$handleResponse$1.INSTANCE, new CredentialProviderCreatePasswordController$handleResponse$2(this), this.cancellationSignal)) {
            CreateCredentialResponse convertResponseToCredentialManager = convertResponseToCredentialManager(Unit.INSTANCE);
            CredentialProviderController.Companion companion4 = CredentialProviderController.Companion;
            CredentialProviderController.cancelOrCallbackExceptionOrResult(this.cancellationSignal, new CredentialProviderCreatePasswordController$handleResponse$3(this, convertResponseToCredentialManager));
        }
    }

    public SavePasswordRequest convertRequestToPlayServices(CreatePasswordRequest createPasswordRequest) {
        Intrinsics.checkNotNullParameter(createPasswordRequest, SentryBaseEvent.JsonKeys.REQUEST);
        SavePasswordRequest build = SavePasswordRequest.builder().setSignInPassword(new SignInPassword(createPasswordRequest.getId(), createPasswordRequest.getPassword())).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder().setSignInPassw…ssword)\n        ).build()");
        return build;
    }

    public CreateCredentialResponse convertResponseToCredentialManager(Unit unit) {
        Intrinsics.checkNotNullParameter(unit, Response.TYPE);
        return new CreatePasswordResponse();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/credentials/playservices/controllers/CreatePassword/CredentialProviderCreatePasswordController$Companion;", "", "()V", "TAG", "", "controller", "Landroidx/credentials/playservices/controllers/CreatePassword/CredentialProviderCreatePasswordController;", "getInstance", "context", "Landroid/content/Context;", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialProviderCreatePasswordController.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CredentialProviderCreatePasswordController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (CredentialProviderCreatePasswordController.controller == null) {
                CredentialProviderCreatePasswordController.controller = new CredentialProviderCreatePasswordController(context);
            }
            CredentialProviderCreatePasswordController access$getController$cp = CredentialProviderCreatePasswordController.controller;
            Intrinsics.checkNotNull(access$getController$cp);
            return access$getController$cp;
        }
    }
}
