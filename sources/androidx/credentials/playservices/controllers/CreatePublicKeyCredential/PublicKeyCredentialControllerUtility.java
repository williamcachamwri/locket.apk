package androidx.credentials.playservices.controllers.CreatePublicKeyCredential;

import android.util.Base64;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.GetPublicKeyCredentialOption;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.domerrors.AbortError;
import androidx.credentials.exceptions.domerrors.ConstraintError;
import androidx.credentials.exceptions.domerrors.DataError;
import androidx.credentials.exceptions.domerrors.DomError;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.InvalidStateError;
import androidx.credentials.exceptions.domerrors.NetworkError;
import androidx.credentials.exceptions.domerrors.NotAllowedError;
import androidx.credentials.exceptions.domerrors.NotReadableError;
import androidx.credentials.exceptions.domerrors.NotSupportedError;
import androidx.credentials.exceptions.domerrors.SecurityError;
import androidx.credentials.exceptions.domerrors.TimeoutError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException;
import androidx.credentials.exceptions.publickeycredential.GetPublicKeyCredentialDomException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.COSEAlgorithmIdentifier;
import com.google.android.gms.fido.fido2.api.common.ErrorCode;
import com.google.android.gms.fido.fido2.api.common.FidoAppIdExtension;
import com.google.android.gms.fido.fido2.api.common.GoogleThirdPartyPaymentExtension;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import com.google.android.gms.fido.fido2.api.common.ResidentKeyRequirement;
import com.google.android.gms.fido.fido2.api.common.UserVerificationMethodExtension;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.ProfilingTraceData;
import io.sentry.SentryBaseEvent;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/playservices/controllers/CreatePublicKeyCredential/PublicKeyCredentialControllerUtility;", "", "()V", "Companion", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PublicKeyCredentialControllerUtility.kt */
public final class PublicKeyCredentialControllerUtility {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int FLAGS = 11;
    /* access modifiers changed from: private */
    public static final String JSON_KEY_ALG = "alg";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_APPID = "appid";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_ATTESTATION = "attestation";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_ATTESTATION_OBJ = "attestationObject";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_AUTH_ATTACHMENT = "authenticatorAttachment";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_AUTH_DATA = "authenticatorData";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_AUTH_SELECTION = "authenticatorSelection";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_CHALLENGE = ClientData.KEY_CHALLENGE;
    /* access modifiers changed from: private */
    public static final String JSON_KEY_CLIENT_DATA = "clientDataJSON";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_CLIENT_EXTENSION_RESULTS = "clientExtensionResults";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_CRED_PROPS = "credProps";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_DISPLAY_NAME = "displayName";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_EXCLUDE_CREDENTIALS = "excludeCredentials";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_EXTENSTIONS = "extensions";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_ICON = "icon";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_ID = "id";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_KEY_PROTECTION_TYPE = "keyProtectionType";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_MATCHER_PROTECTION_TYPE = "matcherProtectionType";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_NAME = "name";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_PUB_KEY_CRED_PARAMS = "pubKeyCredParams";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RAW_ID = "rawId";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_REQUIRE_RES_KEY = "requireResidentKey";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RESPONSE = Response.TYPE;
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RES_KEY = "residentKey";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RK = "rk";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RP = "rp";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_RPID = "rpId";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_SIGNATURE = "signature";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_THIRD_PARTY_PAYMENT = "thirdPartyPayment";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_TIMEOUT = ProfilingTraceData.TRUNCATION_REASON_TIMEOUT;
    /* access modifiers changed from: private */
    public static final String JSON_KEY_TRANSPORTS = "transports";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_TYPE = "type";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_USER = "user";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_USER_HANDLE = "userHandle";
    /* access modifiers changed from: private */
    public static final String JSON_KEY_USER_VERIFICATION_METHOD = "userVerificationMethod";
    private static final String TAG = "PublicKeyUtility";
    /* access modifiers changed from: private */
    public static final LinkedHashMap<ErrorCode, DomError> orderedErrorCodeToExceptions = MapsKt.linkedMapOf(TuplesKt.to(ErrorCode.UNKNOWN_ERR, new UnknownError()), TuplesKt.to(ErrorCode.ABORT_ERR, new AbortError()), TuplesKt.to(ErrorCode.ATTESTATION_NOT_PRIVATE_ERR, new NotReadableError()), TuplesKt.to(ErrorCode.CONSTRAINT_ERR, new ConstraintError()), TuplesKt.to(ErrorCode.DATA_ERR, new DataError()), TuplesKt.to(ErrorCode.INVALID_STATE_ERR, new InvalidStateError()), TuplesKt.to(ErrorCode.ENCODING_ERR, new EncodingError()), TuplesKt.to(ErrorCode.NETWORK_ERR, new NetworkError()), TuplesKt.to(ErrorCode.NOT_ALLOWED_ERR, new NotAllowedError()), TuplesKt.to(ErrorCode.NOT_SUPPORTED_ERR, new NotSupportedError()), TuplesKt.to(ErrorCode.SECURITY_ERR, new SecurityError()), TuplesKt.to(ErrorCode.TIMEOUT_ERR, new TimeoutError()));

    @JvmStatic
    public static final PublicKeyCredentialCreationOptions convert(CreatePublicKeyCredentialRequest createPublicKeyCredentialRequest) {
        return Companion.convert(createPublicKeyCredentialRequest);
    }

    @Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\bH\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J7\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020X2\u000e\u0010Z\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060[2\u0006\u0010\\\u001a\u00020]H\u0000¢\u0006\u0004\b^\u0010_J\u000e\u0010`\u001a\u00020X2\u0006\u0010a\u001a\u00020\u0006J\u000e\u0010b\u001a\u00020\u00062\u0006\u0010c\u001a\u00020XJ\u001f\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020P2\b\u0010g\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\bhJ\u000e\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u0004J\u0010\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020oH\u0007J\u0015\u0010p\u001a\u00020m2\u0006\u0010\\\u001a\u00020]H\u0000¢\u0006\u0002\bqJ\u000e\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020uJ\u0010\u0010v\u001a\u00020w2\u0006\u0010t\u001a\u00020uH\u0007J\u0010\u0010x\u001a\u00020X2\u0006\u0010\\\u001a\u00020]H\u0002J\u001d\u0010y\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0002\b|J\u001d\u0010}\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0002\b~J\u001e\u0010\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0003\b\u0001J\u001f\u0010\u0001\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0003\b\u0001J\u001f\u0010\u0001\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0003\b\u0001J\u001f\u0010\u0001\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u0010z\u001a\u00020{H\u0000¢\u0006\u0003\b\u0001J\u0014\u0010\u0001\u001a\u0005\u0018\u00010\u00012\b\u0010\u0001\u001a\u00030\u0001J\u0011\u0010\u0001\u001a\u00020\u00062\b\u0010\u0001\u001a\u00030\u0001R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0014\u0010\u0015\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0014\u0010\u0017\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0014\u0010\u0019\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0014\u0010\u001b\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0014\u0010\u001d\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0014\u0010\u001f\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0014\u0010!\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0014\u0010#\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\bR\u0014\u0010%\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u0014\u0010'\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\bR\u0014\u0010)\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\bR\u0014\u0010+\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u0014\u0010-\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\bR\u0014\u0010/\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\bR\u0014\u00101\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\bR\u0014\u00103\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\bR\u0014\u00105\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\bR\u0014\u00107\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\bR\u0014\u00109\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\bR\u0014\u0010;\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\bR\u0014\u0010=\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\bR\u0014\u0010?\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\bR\u0014\u0010A\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\bR\u0014\u0010C\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\bR\u0014\u0010E\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\bR\u0014\u0010G\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\bR\u0014\u0010I\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\bR\u0014\u0010K\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\bR\u000e\u0010M\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R0\u0010N\u001a\u001e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020Q0Oj\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020Q`RX\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010T¨\u0006\u0001"}, d2 = {"Landroidx/credentials/playservices/controllers/CreatePublicKeyCredential/PublicKeyCredentialControllerUtility$Companion;", "", "()V", "FLAGS", "", "JSON_KEY_ALG", "", "getJSON_KEY_ALG$credentials_play_services_auth_release", "()Ljava/lang/String;", "JSON_KEY_APPID", "getJSON_KEY_APPID$credentials_play_services_auth_release", "JSON_KEY_ATTESTATION", "getJSON_KEY_ATTESTATION$credentials_play_services_auth_release", "JSON_KEY_ATTESTATION_OBJ", "getJSON_KEY_ATTESTATION_OBJ$credentials_play_services_auth_release", "JSON_KEY_AUTH_ATTACHMENT", "getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth_release", "JSON_KEY_AUTH_DATA", "getJSON_KEY_AUTH_DATA$credentials_play_services_auth_release", "JSON_KEY_AUTH_SELECTION", "getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth_release", "JSON_KEY_CHALLENGE", "getJSON_KEY_CHALLENGE$credentials_play_services_auth_release", "JSON_KEY_CLIENT_DATA", "getJSON_KEY_CLIENT_DATA$credentials_play_services_auth_release", "JSON_KEY_CLIENT_EXTENSION_RESULTS", "getJSON_KEY_CLIENT_EXTENSION_RESULTS$credentials_play_services_auth_release", "JSON_KEY_CRED_PROPS", "getJSON_KEY_CRED_PROPS$credentials_play_services_auth_release", "JSON_KEY_DISPLAY_NAME", "getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth_release", "JSON_KEY_EXCLUDE_CREDENTIALS", "getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth_release", "JSON_KEY_EXTENSTIONS", "getJSON_KEY_EXTENSTIONS$credentials_play_services_auth_release", "JSON_KEY_ICON", "getJSON_KEY_ICON$credentials_play_services_auth_release", "JSON_KEY_ID", "getJSON_KEY_ID$credentials_play_services_auth_release", "JSON_KEY_KEY_PROTECTION_TYPE", "getJSON_KEY_KEY_PROTECTION_TYPE$credentials_play_services_auth_release", "JSON_KEY_MATCHER_PROTECTION_TYPE", "getJSON_KEY_MATCHER_PROTECTION_TYPE$credentials_play_services_auth_release", "JSON_KEY_NAME", "getJSON_KEY_NAME$credentials_play_services_auth_release", "JSON_KEY_PUB_KEY_CRED_PARAMS", "getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth_release", "JSON_KEY_RAW_ID", "getJSON_KEY_RAW_ID$credentials_play_services_auth_release", "JSON_KEY_REQUIRE_RES_KEY", "getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth_release", "JSON_KEY_RESPONSE", "getJSON_KEY_RESPONSE$credentials_play_services_auth_release", "JSON_KEY_RES_KEY", "getJSON_KEY_RES_KEY$credentials_play_services_auth_release", "JSON_KEY_RK", "getJSON_KEY_RK$credentials_play_services_auth_release", "JSON_KEY_RP", "getJSON_KEY_RP$credentials_play_services_auth_release", "JSON_KEY_RPID", "getJSON_KEY_RPID$credentials_play_services_auth_release", "JSON_KEY_SIGNATURE", "getJSON_KEY_SIGNATURE$credentials_play_services_auth_release", "JSON_KEY_THIRD_PARTY_PAYMENT", "getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth_release", "JSON_KEY_TIMEOUT", "getJSON_KEY_TIMEOUT$credentials_play_services_auth_release", "JSON_KEY_TRANSPORTS", "getJSON_KEY_TRANSPORTS$credentials_play_services_auth_release", "JSON_KEY_TYPE", "getJSON_KEY_TYPE$credentials_play_services_auth_release", "JSON_KEY_USER", "getJSON_KEY_USER$credentials_play_services_auth_release", "JSON_KEY_USER_HANDLE", "getJSON_KEY_USER_HANDLE$credentials_play_services_auth_release", "JSON_KEY_USER_VERIFICATION_METHOD", "getJSON_KEY_USER_VERIFICATION_METHOD$credentials_play_services_auth_release", "TAG", "orderedErrorCodeToExceptions", "Ljava/util/LinkedHashMap;", "Lcom/google/android/gms/fido/fido2/api/common/ErrorCode;", "Landroidx/credentials/exceptions/domerrors/DomError;", "Lkotlin/collections/LinkedHashMap;", "getOrderedErrorCodeToExceptions$credentials_play_services_auth_release", "()Ljava/util/LinkedHashMap;", "addAuthenticatorAttestationResponse", "", "clientDataJSON", "", "attestationObject", "transportArray", "", "json", "Lorg/json/JSONObject;", "addAuthenticatorAttestationResponse$credentials_play_services_auth_release", "([B[B[Ljava/lang/String;Lorg/json/JSONObject;)V", "b64Decode", "str", "b64Encode", "data", "beginSignInPublicKeyCredentialResponseContainsError", "Landroidx/credentials/exceptions/GetCredentialException;", "code", "msg", "beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth_release", "checkAlgSupported", "", "alg", "convert", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", "request", "Landroidx/credentials/CreatePublicKeyCredentialRequest;", "convertJSON", "convertJSON$credentials_play_services_auth_release", "convertToPlayAuthPasskeyJsonRequest", "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeyJsonRequestOptions;", "option", "Landroidx/credentials/GetPublicKeyCredentialOption;", "convertToPlayAuthPasskeyRequest", "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest$PasskeysRequestOptions;", "getChallenge", "parseOptionalAuthenticatorSelection", "builder", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions$Builder;", "parseOptionalAuthenticatorSelection$credentials_play_services_auth_release", "parseOptionalExtensions", "parseOptionalExtensions$credentials_play_services_auth_release", "parseOptionalTimeout", "parseOptionalTimeout$credentials_play_services_auth_release", "parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials", "parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth_release", "parseRequiredChallengeAndUser", "parseRequiredChallengeAndUser$credentials_play_services_auth_release", "parseRequiredRpAndParams", "parseRequiredRpAndParams$credentials_play_services_auth_release", "publicKeyCredentialResponseContainsError", "Landroidx/credentials/exceptions/CreateCredentialException;", "cred", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredential;", "toAssertPasskeyResponse", "Lcom/google/android/gms/auth/api/identity/SignInCredential;", "credentials-play-services-auth_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PublicKeyCredentialControllerUtility.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getJSON_KEY_CLIENT_DATA$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_CLIENT_DATA;
        }

        public final String getJSON_KEY_ATTESTATION_OBJ$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_ATTESTATION_OBJ;
        }

        public final String getJSON_KEY_AUTH_DATA$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_AUTH_DATA;
        }

        public final String getJSON_KEY_SIGNATURE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_SIGNATURE;
        }

        public final String getJSON_KEY_USER_HANDLE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_USER_HANDLE;
        }

        public final String getJSON_KEY_RESPONSE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RESPONSE;
        }

        public final String getJSON_KEY_ID$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_ID;
        }

        public final String getJSON_KEY_RAW_ID$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RAW_ID;
        }

        public final String getJSON_KEY_TYPE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_TYPE;
        }

        public final String getJSON_KEY_RPID$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RPID;
        }

        public final String getJSON_KEY_CHALLENGE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_CHALLENGE;
        }

        public final String getJSON_KEY_APPID$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_APPID;
        }

        public final String getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_THIRD_PARTY_PAYMENT;
        }

        public final String getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_AUTH_SELECTION;
        }

        public final String getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_REQUIRE_RES_KEY;
        }

        public final String getJSON_KEY_RES_KEY$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RES_KEY;
        }

        public final String getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_AUTH_ATTACHMENT;
        }

        public final String getJSON_KEY_TIMEOUT$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_TIMEOUT;
        }

        public final String getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_EXCLUDE_CREDENTIALS;
        }

        public final String getJSON_KEY_TRANSPORTS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_TRANSPORTS;
        }

        public final String getJSON_KEY_RP$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RP;
        }

        public final String getJSON_KEY_NAME$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_NAME;
        }

        public final String getJSON_KEY_ICON$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_ICON;
        }

        public final String getJSON_KEY_ALG$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_ALG;
        }

        public final String getJSON_KEY_USER$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_USER;
        }

        public final String getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_DISPLAY_NAME;
        }

        public final String getJSON_KEY_USER_VERIFICATION_METHOD$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_USER_VERIFICATION_METHOD;
        }

        public final String getJSON_KEY_KEY_PROTECTION_TYPE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_KEY_PROTECTION_TYPE;
        }

        public final String getJSON_KEY_MATCHER_PROTECTION_TYPE$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_MATCHER_PROTECTION_TYPE;
        }

        public final String getJSON_KEY_EXTENSTIONS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_EXTENSTIONS;
        }

        public final String getJSON_KEY_ATTESTATION$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_ATTESTATION;
        }

        public final String getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_PUB_KEY_CRED_PARAMS;
        }

        public final String getJSON_KEY_CLIENT_EXTENSION_RESULTS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_CLIENT_EXTENSION_RESULTS;
        }

        public final String getJSON_KEY_RK$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_RK;
        }

        public final String getJSON_KEY_CRED_PROPS$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.JSON_KEY_CRED_PROPS;
        }

        @JvmStatic
        public final PublicKeyCredentialCreationOptions convert(CreatePublicKeyCredentialRequest createPublicKeyCredentialRequest) {
            Intrinsics.checkNotNullParameter(createPublicKeyCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            return convertJSON$credentials_play_services_auth_release(new JSONObject(createPublicKeyCredentialRequest.getRequestJson()));
        }

        public final PublicKeyCredentialCreationOptions convertJSON$credentials_play_services_auth_release(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            PublicKeyCredentialCreationOptions.Builder builder = new PublicKeyCredentialCreationOptions.Builder();
            parseRequiredChallengeAndUser$credentials_play_services_auth_release(jSONObject, builder);
            parseRequiredRpAndParams$credentials_play_services_auth_release(jSONObject, builder);
            parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth_release(jSONObject, builder);
            parseOptionalTimeout$credentials_play_services_auth_release(jSONObject, builder);
            parseOptionalAuthenticatorSelection$credentials_play_services_auth_release(jSONObject, builder);
            parseOptionalExtensions$credentials_play_services_auth_release(jSONObject, builder);
            PublicKeyCredentialCreationOptions build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
            return build;
        }

        public final void addAuthenticatorAttestationResponse$credentials_play_services_auth_release(byte[] bArr, byte[] bArr2, String[] strArr, JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(bArr, "clientDataJSON");
            Intrinsics.checkNotNullParameter(bArr2, "attestationObject");
            Intrinsics.checkNotNullParameter(strArr, "transportArray");
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(getJSON_KEY_CLIENT_DATA$credentials_play_services_auth_release(), b64Encode(bArr));
            jSONObject2.put(getJSON_KEY_ATTESTATION_OBJ$credentials_play_services_auth_release(), b64Encode(bArr2));
            jSONObject2.put(getJSON_KEY_TRANSPORTS$credentials_play_services_auth_release(), new JSONArray(strArr));
            jSONObject.put(getJSON_KEY_RESPONSE$credentials_play_services_auth_release(), jSONObject2);
        }

        public final String toAssertPasskeyResponse(SignInCredential signInCredential) {
            Intrinsics.checkNotNullParameter(signInCredential, "cred");
            JSONObject jSONObject = new JSONObject();
            PublicKeyCredential publicKeyCredential = signInCredential.getPublicKeyCredential();
            AuthenticatorResponse response = publicKeyCredential != null ? publicKeyCredential.getResponse() : null;
            Intrinsics.checkNotNull(response);
            if (response instanceof AuthenticatorErrorResponse) {
                AuthenticatorErrorResponse authenticatorErrorResponse = (AuthenticatorErrorResponse) response;
                ErrorCode errorCode = authenticatorErrorResponse.getErrorCode();
                Intrinsics.checkNotNullExpressionValue(errorCode, "authenticatorResponse.errorCode");
                throw beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth_release(errorCode, authenticatorErrorResponse.getErrorMessage());
            } else if (response instanceof AuthenticatorAssertionResponse) {
                try {
                    String json = publicKeyCredential.toJson();
                    Intrinsics.checkNotNullExpressionValue(json, "publicKeyCred.toJson()");
                    return json;
                } catch (Throwable th) {
                    throw new GetCredentialUnknownException("The PublicKeyCredential response json had an unexpected exception when parsing: " + th.getMessage());
                }
            } else {
                SentryLogcatAdapter.e(PublicKeyCredentialControllerUtility.TAG, "AuthenticatorResponse expected assertion response but got: " + response.getClass().getName());
                String jSONObject2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "json.toString()");
                return jSONObject2;
            }
        }

        public final BeginSignInRequest.PasskeyJsonRequestOptions convertToPlayAuthPasskeyJsonRequest(GetPublicKeyCredentialOption getPublicKeyCredentialOption) {
            Intrinsics.checkNotNullParameter(getPublicKeyCredentialOption, "option");
            BeginSignInRequest.PasskeyJsonRequestOptions build = new BeginSignInRequest.PasskeyJsonRequestOptions.Builder().setSupported(true).setRequestJson(getPublicKeyCredentialOption.getRequestJson()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder()\n        .setSu…estJson)\n        .build()");
            return build;
        }

        @Deprecated(message = "Upgrade GMS version so 'convertToPlayAuthPasskeyJsonRequest' is used")
        public final BeginSignInRequest.PasskeysRequestOptions convertToPlayAuthPasskeyRequest(GetPublicKeyCredentialOption getPublicKeyCredentialOption) {
            Intrinsics.checkNotNullParameter(getPublicKeyCredentialOption, "option");
            JSONObject jSONObject = new JSONObject(getPublicKeyCredentialOption.getRequestJson());
            String optString = jSONObject.optString(getJSON_KEY_RPID$credentials_play_services_auth_release(), "");
            Intrinsics.checkNotNullExpressionValue(optString, "rpId");
            if (!(optString.length() == 0)) {
                BeginSignInRequest.PasskeysRequestOptions build = new BeginSignInRequest.PasskeysRequestOptions.Builder().setSupported(true).setRpId(optString).setChallenge(getChallenge(jSONObject)).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder()\n        .setSu…allenge)\n        .build()");
                return build;
            }
            throw new JSONException("GetPublicKeyCredentialOption - rpId not specified in the request or is unexpectedly empty");
        }

        private final byte[] getChallenge(JSONObject jSONObject) {
            String optString = jSONObject.optString(getJSON_KEY_CHALLENGE$credentials_play_services_auth_release(), "");
            Intrinsics.checkNotNullExpressionValue(optString, "challengeB64");
            if (!(optString.length() == 0)) {
                return b64Decode(optString);
            }
            throw new JSONException("Challenge not found in request or is unexpectedly empty");
        }

        public final CreateCredentialException publicKeyCredentialResponseContainsError(PublicKeyCredential publicKeyCredential) {
            Intrinsics.checkNotNullParameter(publicKeyCredential, "cred");
            AuthenticatorResponse response = publicKeyCredential.getResponse();
            Intrinsics.checkNotNullExpressionValue(response, "cred.response");
            if (!(response instanceof AuthenticatorErrorResponse)) {
                return null;
            }
            AuthenticatorErrorResponse authenticatorErrorResponse = (AuthenticatorErrorResponse) response;
            ErrorCode errorCode = authenticatorErrorResponse.getErrorCode();
            Intrinsics.checkNotNullExpressionValue(errorCode, "authenticatorResponse.errorCode");
            DomError domError = getOrderedErrorCodeToExceptions$credentials_play_services_auth_release().get(errorCode);
            String errorMessage = authenticatorErrorResponse.getErrorMessage();
            if (domError == null) {
                return new CreatePublicKeyCredentialDomException(new UnknownError(), "unknown fido gms exception - " + errorMessage);
            }
            if (errorCode == ErrorCode.CONSTRAINT_ERR) {
                boolean z = false;
                if (errorMessage != null && StringsKt.contains$default((CharSequence) errorMessage, (CharSequence) "Unable to get sync account", false, 2, (Object) null)) {
                    z = true;
                }
                if (z) {
                    return new CreateCredentialCancellationException("Passkey registration was cancelled by the user.");
                }
            }
            return new CreatePublicKeyCredentialDomException(domError, errorMessage);
        }

        public final GetCredentialException beginSignInPublicKeyCredentialResponseContainsError$credentials_play_services_auth_release(ErrorCode errorCode, String str) {
            Intrinsics.checkNotNullParameter(errorCode, UniversalFirebaseFunctionsModule.CODE_KEY);
            DomError domError = getOrderedErrorCodeToExceptions$credentials_play_services_auth_release().get(errorCode);
            if (domError == null) {
                return new GetPublicKeyCredentialDomException(new UnknownError(), "unknown fido gms exception - " + str);
            }
            if (errorCode == ErrorCode.CONSTRAINT_ERR) {
                boolean z = false;
                if (str != null && StringsKt.contains$default((CharSequence) str, (CharSequence) "Unable to get sync account", false, 2, (Object) null)) {
                    z = true;
                }
                if (z) {
                    return new GetCredentialCancellationException("Passkey retrieval was cancelled by the user.");
                }
            }
            return new GetPublicKeyCredentialDomException(domError, str);
        }

        public final void parseOptionalExtensions$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            Intrinsics.checkNotNullParameter(builder, "builder");
            if (jSONObject.has(getJSON_KEY_EXTENSTIONS$credentials_play_services_auth_release())) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(getJSON_KEY_EXTENSTIONS$credentials_play_services_auth_release());
                AuthenticationExtensions.Builder builder2 = new AuthenticationExtensions.Builder();
                String optString = jSONObject2.optString(getJSON_KEY_APPID$credentials_play_services_auth_release(), "");
                Intrinsics.checkNotNullExpressionValue(optString, "appIdExtension");
                if (optString.length() > 0) {
                    builder2.setFido2Extension(new FidoAppIdExtension(optString));
                }
                if (jSONObject2.optBoolean(getJSON_KEY_THIRD_PARTY_PAYMENT$credentials_play_services_auth_release(), false)) {
                    builder2.setGoogleThirdPartyPaymentExtension(new GoogleThirdPartyPaymentExtension(true));
                }
                if (jSONObject2.optBoolean("uvm", false)) {
                    builder2.setUserVerificationMethodExtension(new UserVerificationMethodExtension(true));
                }
                builder.setAuthenticationExtensions(builder2.build());
            }
        }

        public final void parseOptionalAuthenticatorSelection$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            Intrinsics.checkNotNullParameter(builder, "builder");
            if (jSONObject.has(getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth_release())) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(getJSON_KEY_AUTH_SELECTION$credentials_play_services_auth_release());
                AuthenticatorSelectionCriteria.Builder builder2 = new AuthenticatorSelectionCriteria.Builder();
                boolean z = false;
                boolean optBoolean = jSONObject2.optBoolean(getJSON_KEY_REQUIRE_RES_KEY$credentials_play_services_auth_release(), false);
                String optString = jSONObject2.optString(getJSON_KEY_RES_KEY$credentials_play_services_auth_release(), "");
                Intrinsics.checkNotNullExpressionValue(optString, "residentKey");
                builder2.setRequireResidentKey(Boolean.valueOf(optBoolean)).setResidentKeyRequirement(optString.length() > 0 ? ResidentKeyRequirement.fromString(optString) : null);
                String optString2 = jSONObject2.optString(getJSON_KEY_AUTH_ATTACHMENT$credentials_play_services_auth_release(), "");
                Intrinsics.checkNotNullExpressionValue(optString2, "authenticatorAttachmentString");
                if (optString2.length() > 0) {
                    z = true;
                }
                if (z) {
                    builder2.setAttachment(Attachment.fromString(optString2));
                }
                builder.setAuthenticatorSelection(builder2.build());
            }
        }

        public final void parseOptionalTimeout$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            Intrinsics.checkNotNullParameter(builder, "builder");
            if (jSONObject.has(getJSON_KEY_TIMEOUT$credentials_play_services_auth_release())) {
                builder.setTimeoutSeconds(Double.valueOf(((double) jSONObject.getLong(getJSON_KEY_TIMEOUT$credentials_play_services_auth_release())) / ((double) 1000)));
            }
        }

        public final void parseOptionalWithRequiredDefaultsAttestationAndExcludeCredentials$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            List list;
            JSONObject jSONObject2 = jSONObject;
            PublicKeyCredentialCreationOptions.Builder builder2 = builder;
            Intrinsics.checkNotNullParameter(jSONObject2, "json");
            Intrinsics.checkNotNullParameter(builder2, "builder");
            List arrayList = new ArrayList();
            if (jSONObject2.has(getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth_release())) {
                JSONArray jSONArray = jSONObject2.getJSONArray(getJSON_KEY_EXCLUDE_CREDENTIALS$credentials_play_services_auth_release());
                int length = jSONArray.length();
                int i = 0;
                while (i < length) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    String string = jSONObject3.getString(getJSON_KEY_ID$credentials_play_services_auth_release());
                    Intrinsics.checkNotNullExpressionValue(string, "descriptorJSON.getString(JSON_KEY_ID)");
                    byte[] b64Decode = b64Decode(string);
                    String string2 = jSONObject3.getString(getJSON_KEY_TYPE$credentials_play_services_auth_release());
                    Intrinsics.checkNotNullExpressionValue(string2, "descriptorType");
                    if (!(string2.length() == 0)) {
                        if (!(b64Decode.length == 0)) {
                            if (jSONObject3.has(getJSON_KEY_TRANSPORTS$credentials_play_services_auth_release())) {
                                list = new ArrayList();
                                JSONArray jSONArray2 = jSONObject3.getJSONArray(getJSON_KEY_TRANSPORTS$credentials_play_services_auth_release());
                                int length2 = jSONArray2.length();
                                int i2 = 0;
                                while (i2 < length2) {
                                    try {
                                        Transport fromString = Transport.fromString(jSONArray2.getString(i2));
                                        Intrinsics.checkNotNullExpressionValue(fromString, "fromString(descriptorTransports.getString(j))");
                                        ((ArrayList) list).add(fromString);
                                        i2++;
                                    } catch (Transport.UnsupportedTransportException e) {
                                        throw new CreatePublicKeyCredentialDomException(new EncodingError(), e.getMessage());
                                    }
                                }
                            } else {
                                list = null;
                            }
                            arrayList.add(new PublicKeyCredentialDescriptor(string2, b64Decode, list));
                            i++;
                        } else {
                            throw new JSONException("PublicKeyCredentialDescriptor id value is not found or unexpectedly empty");
                        }
                    } else {
                        throw new JSONException("PublicKeyCredentialDescriptor type value is not found or unexpectedly empty");
                    }
                }
            }
            builder2.setExcludeList(arrayList);
            String str = "none";
            String optString = jSONObject2.optString(getJSON_KEY_ATTESTATION$credentials_play_services_auth_release(), str);
            Intrinsics.checkNotNullExpressionValue(optString, "attestationString");
            if (!(optString.length() == 0)) {
                str = optString;
            }
            builder2.setAttestationConveyancePreference(AttestationConveyancePreference.fromString(str));
        }

        public final void parseRequiredRpAndParams$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            Intrinsics.checkNotNullParameter(builder, "builder");
            JSONObject jSONObject2 = jSONObject.getJSONObject(getJSON_KEY_RP$credentials_play_services_auth_release());
            String string = jSONObject2.getString(getJSON_KEY_ID$credentials_play_services_auth_release());
            String optString = jSONObject2.optString(getJSON_KEY_NAME$credentials_play_services_auth_release(), "");
            String optString2 = jSONObject2.optString(getJSON_KEY_ICON$credentials_play_services_auth_release(), "");
            Intrinsics.checkNotNull(optString2);
            if (optString2.length() == 0) {
                optString2 = null;
            }
            Intrinsics.checkNotNullExpressionValue(optString, "rpName");
            if (!(optString.length() == 0)) {
                Intrinsics.checkNotNullExpressionValue(string, "rpId");
                if (!(string.length() == 0)) {
                    builder.setRp(new PublicKeyCredentialRpEntity(string, optString, optString2));
                    JSONArray jSONArray = jSONObject.getJSONArray(getJSON_KEY_PUB_KEY_CRED_PARAMS$credentials_play_services_auth_release());
                    List arrayList = new ArrayList();
                    int length = jSONArray.length();
                    int i = 0;
                    while (i < length) {
                        JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                        int i2 = (int) jSONObject3.getLong(getJSON_KEY_ALG$credentials_play_services_auth_release());
                        String optString3 = jSONObject3.optString(getJSON_KEY_TYPE$credentials_play_services_auth_release(), "");
                        Intrinsics.checkNotNullExpressionValue(optString3, "typeParam");
                        if (!(optString3.length() == 0)) {
                            if (checkAlgSupported(i2)) {
                                arrayList.add(new PublicKeyCredentialParameters(optString3, i2));
                            }
                            i++;
                        } else {
                            throw new JSONException("PublicKeyCredentialCreationOptions PublicKeyCredentialParameter type missing or unexpectedly empty");
                        }
                    }
                    builder.setParameters(arrayList);
                    return;
                }
                throw new JSONException("PublicKeyCredentialCreationOptions rp ID is missing or unexpectedly empty");
            }
            throw new JSONException("PublicKeyCredentialCreationOptions rp name is missing or unexpectedly empty");
        }

        public final void parseRequiredChallengeAndUser$credentials_play_services_auth_release(JSONObject jSONObject, PublicKeyCredentialCreationOptions.Builder builder) {
            Intrinsics.checkNotNullParameter(jSONObject, "json");
            Intrinsics.checkNotNullParameter(builder, "builder");
            builder.setChallenge(getChallenge(jSONObject));
            JSONObject jSONObject2 = jSONObject.getJSONObject(getJSON_KEY_USER$credentials_play_services_auth_release());
            String string = jSONObject2.getString(getJSON_KEY_ID$credentials_play_services_auth_release());
            Intrinsics.checkNotNullExpressionValue(string, "user.getString(JSON_KEY_ID)");
            byte[] b64Decode = b64Decode(string);
            String string2 = jSONObject2.getString(getJSON_KEY_NAME$credentials_play_services_auth_release());
            String string3 = jSONObject2.getString(getJSON_KEY_DISPLAY_NAME$credentials_play_services_auth_release());
            String optString = jSONObject2.optString(getJSON_KEY_ICON$credentials_play_services_auth_release(), "");
            Intrinsics.checkNotNullExpressionValue(string3, "displayName");
            boolean z = true;
            if (!(string3.length() == 0)) {
                if (!(b64Decode.length == 0)) {
                    Intrinsics.checkNotNullExpressionValue(string2, "userName");
                    if (string2.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        builder.setUser(new PublicKeyCredentialUserEntity(b64Decode, string2, optString, string3));
                        return;
                    }
                    throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing user name or they are unexpectedly empty");
                }
                throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing user id or they are unexpectedly empty");
            }
            throw new JSONException("PublicKeyCredentialCreationOptions UserEntity missing displayName or they are unexpectedly empty");
        }

        public final byte[] b64Decode(String str) {
            Intrinsics.checkNotNullParameter(str, "str");
            byte[] decode = Base64.decode(str, 11);
            Intrinsics.checkNotNullExpressionValue(decode, "decode(str, FLAGS)");
            return decode;
        }

        public final String b64Encode(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "data");
            String encodeToString = Base64.encodeToString(bArr, 11);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(data, FLAGS)");
            return encodeToString;
        }

        public final boolean checkAlgSupported(int i) {
            try {
                COSEAlgorithmIdentifier.fromCoseValue(i);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }

        public final LinkedHashMap<ErrorCode, DomError> getOrderedErrorCodeToExceptions$credentials_play_services_auth_release() {
            return PublicKeyCredentialControllerUtility.orderedErrorCodeToExceptions;
        }
    }
}
