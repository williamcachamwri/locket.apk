package androidx.credentials.playservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.fido.Fido;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0017\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002J\"\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0014H\u0014J\u0012\u0010\u0017\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J \u0010\u0018\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/credentials/playservices/HiddenActivity;", "Landroid/app/Activity;", "()V", "mWaitingForActivityResult", "", "resultReceiver", "Landroid/os/ResultReceiver;", "handleBeginSignIn", "", "handleCreatePassword", "handleCreatePublicKeyCredential", "handleGetSignInIntent", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "restoreState", "setupFailure", "errName", "", "errMsg", "Companion", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HiddenActivity.kt */
public class HiddenActivity extends Activity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_VALUE = 1;
    private static final String KEY_AWAITING_RESULT = "androidx.credentials.playservices.AWAITING_RESULT";
    private static final String TAG = "HiddenActivity";
    /* access modifiers changed from: private */
    public boolean mWaitingForActivityResult;
    /* access modifiers changed from: private */
    public ResultReceiver resultReceiver;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        String stringExtra = getIntent().getStringExtra(CredentialProviderBaseController.TYPE_TAG);
        ResultReceiver resultReceiver2 = (ResultReceiver) getIntent().getParcelableExtra(CredentialProviderBaseController.RESULT_RECEIVER_TAG);
        this.resultReceiver = resultReceiver2;
        if (resultReceiver2 == null) {
            finish();
        }
        restoreState(bundle);
        if (!this.mWaitingForActivityResult) {
            if (stringExtra != null) {
                switch (stringExtra.hashCode()) {
                    case -441061071:
                        if (stringExtra.equals(CredentialProviderBaseController.BEGIN_SIGN_IN_TAG)) {
                            handleBeginSignIn();
                            return;
                        }
                        break;
                    case 15545322:
                        if (stringExtra.equals(CredentialProviderBaseController.CREATE_PUBLIC_KEY_CREDENTIAL_TAG)) {
                            handleCreatePublicKeyCredential();
                            return;
                        }
                        break;
                    case 1246634622:
                        if (stringExtra.equals(CredentialProviderBaseController.CREATE_PASSWORD_TAG)) {
                            handleCreatePassword();
                            return;
                        }
                        break;
                    case 1980564212:
                        if (stringExtra.equals(CredentialProviderBaseController.SIGN_IN_INTENT_TAG)) {
                            handleGetSignInIntent();
                            return;
                        }
                        break;
                }
            }
            SentryLogcatAdapter.w(TAG, "Activity handed an unsupported type");
            finish();
        }
    }

    private final void restoreState(Bundle bundle) {
        if (bundle != null) {
            this.mWaitingForActivityResult = bundle.getBoolean(KEY_AWAITING_RESULT, false);
        }
    }

    private final void handleCreatePublicKeyCredential() {
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = (PublicKeyCredentialCreationOptions) getIntent().getParcelableExtra(CredentialProviderBaseController.REQUEST_TAG);
        if ((publicKeyCredentialCreationOptions != null ? Fido.getFido2ApiClient((Activity) this).getRegisterPendingIntent(publicKeyCredentialCreationOptions).addOnSuccessListener(new HiddenActivity$$ExternalSyntheticLambda0(new HiddenActivity$handleCreatePublicKeyCredential$1$1(this, getIntent().getIntExtra(CredentialProviderBaseController.ACTIVITY_REQUEST_CODE_TAG, 1)))).addOnFailureListener(new HiddenActivity$$ExternalSyntheticLambda1(this)) : null) == null) {
            HiddenActivity hiddenActivity = this;
            SentryLogcatAdapter.w(TAG, "During create public key credential, request is null, so nothing to launch for public key credentials");
            finish();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleCreatePublicKeyCredential$lambda$2$lambda$0(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void handleCreatePublicKeyCredential$lambda$2$lambda$1(HiddenActivity hiddenActivity, Exception exc) {
        Intrinsics.checkNotNullParameter(hiddenActivity, "this$0");
        Intrinsics.checkNotNullParameter(exc, "e");
        String str = (!(exc instanceof ApiException) || !CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) exc).getStatusCode()))) ? CredentialProviderBaseController.CREATE_UNKNOWN : CredentialProviderBaseController.CREATE_INTERRUPTED;
        ResultReceiver resultReceiver2 = hiddenActivity.resultReceiver;
        Intrinsics.checkNotNull(resultReceiver2);
        hiddenActivity.setupFailure(resultReceiver2, str, "During create public key credential, fido registration failure: " + exc.getMessage());
    }

    /* access modifiers changed from: private */
    public final void setupFailure(ResultReceiver resultReceiver2, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(CredentialProviderBaseController.FAILURE_RESPONSE_TAG, true);
        bundle.putString(CredentialProviderBaseController.EXCEPTION_TYPE_TAG, str);
        bundle.putString(CredentialProviderBaseController.EXCEPTION_MESSAGE_TAG, str2);
        resultReceiver2.send(Integer.MAX_VALUE, bundle);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putBoolean(KEY_AWAITING_RESULT, this.mWaitingForActivityResult);
        super.onSaveInstanceState(bundle);
    }

    private final void handleGetSignInIntent() {
        GetSignInIntentRequest getSignInIntentRequest = (GetSignInIntentRequest) getIntent().getParcelableExtra(CredentialProviderBaseController.REQUEST_TAG);
        if ((getSignInIntentRequest != null ? Identity.getSignInClient((Activity) this).getSignInIntent(getSignInIntentRequest).addOnSuccessListener(new HiddenActivity$$ExternalSyntheticLambda2(new HiddenActivity$handleGetSignInIntent$1$1(this, getIntent().getIntExtra(CredentialProviderBaseController.ACTIVITY_REQUEST_CODE_TAG, 1)))).addOnFailureListener(new HiddenActivity$$ExternalSyntheticLambda3(this)) : null) == null) {
            HiddenActivity hiddenActivity = this;
            Log.i(TAG, "During get sign-in intent, params is null, nothing to launch for get sign-in intent");
            finish();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleGetSignInIntent$lambda$6$lambda$4(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void handleGetSignInIntent$lambda$6$lambda$5(HiddenActivity hiddenActivity, Exception exc) {
        Intrinsics.checkNotNullParameter(hiddenActivity, "this$0");
        Intrinsics.checkNotNullParameter(exc, "e");
        String str = (!(exc instanceof ApiException) || !CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) exc).getStatusCode()))) ? CredentialProviderBaseController.GET_NO_CREDENTIALS : CredentialProviderBaseController.GET_INTERRUPTED;
        ResultReceiver resultReceiver2 = hiddenActivity.resultReceiver;
        Intrinsics.checkNotNull(resultReceiver2);
        hiddenActivity.setupFailure(resultReceiver2, str, "During get sign-in intent, failure response from one tap: " + exc.getMessage());
    }

    private final void handleBeginSignIn() {
        BeginSignInRequest beginSignInRequest = (BeginSignInRequest) getIntent().getParcelableExtra(CredentialProviderBaseController.REQUEST_TAG);
        if ((beginSignInRequest != null ? Identity.getSignInClient((Activity) this).beginSignIn(beginSignInRequest).addOnSuccessListener(new HiddenActivity$$ExternalSyntheticLambda4(new HiddenActivity$handleBeginSignIn$1$1(this, getIntent().getIntExtra(CredentialProviderBaseController.ACTIVITY_REQUEST_CODE_TAG, 1)))).addOnFailureListener(new HiddenActivity$$ExternalSyntheticLambda5(this)) : null) == null) {
            HiddenActivity hiddenActivity = this;
            Log.i(TAG, "During begin sign in, params is null, nothing to launch for begin sign in");
            finish();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleBeginSignIn$lambda$10$lambda$8(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void handleBeginSignIn$lambda$10$lambda$9(HiddenActivity hiddenActivity, Exception exc) {
        Intrinsics.checkNotNullParameter(hiddenActivity, "this$0");
        Intrinsics.checkNotNullParameter(exc, "e");
        String str = (!(exc instanceof ApiException) || !CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) exc).getStatusCode()))) ? CredentialProviderBaseController.GET_NO_CREDENTIALS : CredentialProviderBaseController.GET_INTERRUPTED;
        ResultReceiver resultReceiver2 = hiddenActivity.resultReceiver;
        Intrinsics.checkNotNull(resultReceiver2);
        hiddenActivity.setupFailure(resultReceiver2, str, "During begin sign in, failure response from one tap: " + exc.getMessage());
    }

    private final void handleCreatePassword() {
        SavePasswordRequest savePasswordRequest = (SavePasswordRequest) getIntent().getParcelableExtra(CredentialProviderBaseController.REQUEST_TAG);
        if ((savePasswordRequest != null ? Identity.getCredentialSavingClient((Activity) this).savePassword(savePasswordRequest).addOnSuccessListener(new HiddenActivity$$ExternalSyntheticLambda6(new HiddenActivity$handleCreatePassword$1$1(this, getIntent().getIntExtra(CredentialProviderBaseController.ACTIVITY_REQUEST_CODE_TAG, 1)))).addOnFailureListener(new HiddenActivity$$ExternalSyntheticLambda7(this)) : null) == null) {
            HiddenActivity hiddenActivity = this;
            Log.i(TAG, "During save password, params is null, nothing to launch for create password");
            finish();
        }
    }

    /* access modifiers changed from: private */
    public static final void handleCreatePassword$lambda$14$lambda$12(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void handleCreatePassword$lambda$14$lambda$13(HiddenActivity hiddenActivity, Exception exc) {
        Intrinsics.checkNotNullParameter(hiddenActivity, "this$0");
        Intrinsics.checkNotNullParameter(exc, "e");
        String str = (!(exc instanceof ApiException) || !CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) exc).getStatusCode()))) ? CredentialProviderBaseController.CREATE_UNKNOWN : CredentialProviderBaseController.CREATE_INTERRUPTED;
        ResultReceiver resultReceiver2 = hiddenActivity.resultReceiver;
        Intrinsics.checkNotNull(resultReceiver2);
        hiddenActivity.setupFailure(resultReceiver2, str, "During save password, found password failure response from one tap " + exc.getMessage());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CredentialProviderBaseController.FAILURE_RESPONSE_TAG, false);
        bundle.putInt(CredentialProviderBaseController.ACTIVITY_REQUEST_CODE_TAG, i);
        bundle.putParcelable(CredentialProviderBaseController.RESULT_DATA_TAG, intent);
        ResultReceiver resultReceiver2 = this.resultReceiver;
        if (resultReceiver2 != null) {
            resultReceiver2.send(i2, bundle);
        }
        this.mWaitingForActivityResult = false;
        finish();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/credentials/playservices/HiddenActivity$Companion;", "", "()V", "DEFAULT_VALUE", "", "KEY_AWAITING_RESULT", "", "TAG", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HiddenActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
