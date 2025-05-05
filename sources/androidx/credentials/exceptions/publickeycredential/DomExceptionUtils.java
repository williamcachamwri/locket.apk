package androidx.credentials.exceptions.publickeycredential;

import androidx.credentials.exceptions.domerrors.AbortError;
import androidx.credentials.exceptions.domerrors.ConstraintError;
import androidx.credentials.exceptions.domerrors.DataCloneError;
import androidx.credentials.exceptions.domerrors.DataError;
import androidx.credentials.exceptions.domerrors.DomError;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.HierarchyRequestError;
import androidx.credentials.exceptions.domerrors.InUseAttributeError;
import androidx.credentials.exceptions.domerrors.InvalidCharacterError;
import androidx.credentials.exceptions.domerrors.InvalidModificationError;
import androidx.credentials.exceptions.domerrors.InvalidNodeTypeError;
import androidx.credentials.exceptions.domerrors.InvalidStateError;
import androidx.credentials.exceptions.domerrors.NamespaceError;
import androidx.credentials.exceptions.domerrors.NetworkError;
import androidx.credentials.exceptions.domerrors.NoModificationAllowedError;
import androidx.credentials.exceptions.domerrors.NotAllowedError;
import androidx.credentials.exceptions.domerrors.NotFoundError;
import androidx.credentials.exceptions.domerrors.NotReadableError;
import androidx.credentials.exceptions.domerrors.NotSupportedError;
import androidx.credentials.exceptions.domerrors.OperationError;
import androidx.credentials.exceptions.domerrors.OptOutError;
import androidx.credentials.exceptions.domerrors.QuotaExceededError;
import androidx.credentials.exceptions.domerrors.ReadOnlyError;
import androidx.credentials.exceptions.domerrors.SecurityError;
import androidx.credentials.exceptions.domerrors.SyntaxError;
import androidx.credentials.exceptions.domerrors.TimeoutError;
import androidx.credentials.exceptions.domerrors.TransactionInactiveError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.domerrors.VersionError;
import androidx.credentials.exceptions.domerrors.WrongDocumentError;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0001\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/DomExceptionUtils;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DomExceptionUtils.kt */
public final class DomExceptionUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SEPARATOR = "/";

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0005\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u00012\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u0002H\u0006H\b¢\u0006\u0004\b\u000b\u0010\fJ-\u0010\r\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u0002H\u0006H\u0002¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/DomExceptionUtils$Companion;", "", "()V", "SEPARATOR", "", "generateDomException", "T", "type", "prefix", "msg", "t", "generateDomException$credentials_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "generateException", "domError", "Landroidx/credentials/exceptions/domerrors/DomError;", "(Landroidx/credentials/exceptions/domerrors/DomError;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DomExceptionUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final /* synthetic */ <T> T generateDomException$credentials_release(String str, String str2, String str3, T t) {
            Intrinsics.checkNotNullParameter(str, "type");
            Intrinsics.checkNotNullParameter(str2, "prefix");
            if (Intrinsics.areEqual((Object) str, (Object) str2 + AbortError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_ABORT_ERROR)) {
                return generateException(new AbortError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + ConstraintError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_CONSTRAINT_ERROR)) {
                return generateException(new ConstraintError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + DataCloneError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DATA_CLONE_ERROR)) {
                return generateException(new DataCloneError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + DataError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DATA_ERROR)) {
                return generateException(new DataError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + EncodingError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_ENCODING_ERROR)) {
                return generateException(new EncodingError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + HierarchyRequestError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_HIERARCHY_REQUEST_ERROR)) {
                return generateException(new HierarchyRequestError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + InUseAttributeError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_IN_USE_ATTRIBUTE_ERROR)) {
                return generateException(new InUseAttributeError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + InvalidCharacterError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_CHARACTER_ERROR)) {
                return generateException(new InvalidCharacterError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + InvalidModificationError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_MODIFICATION_ERROR)) {
                return generateException(new InvalidModificationError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + InvalidNodeTypeError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_NODE_TYPE_ERROR)) {
                return generateException(new InvalidNodeTypeError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + InvalidStateError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_INVALID_STATE_ERROR)) {
                return generateException(new InvalidStateError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NamespaceError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NAMESPACE_ERROR)) {
                return generateException(new NamespaceError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NetworkError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NETWORK_ERROR)) {
                return generateException(new NetworkError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NoModificationAllowedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NO_MODIFICATION_ALLOWED_ERROR)) {
                return generateException(new NoModificationAllowedError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NotAllowedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_ALLOWED_ERROR)) {
                return generateException(new NotAllowedError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NotFoundError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_FOUND_ERROR)) {
                return generateException(new NotFoundError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NotReadableError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_READABLE_ERROR)) {
                return generateException(new NotReadableError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + NotSupportedError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_NOT_SUPPORTED_ERROR)) {
                return generateException(new NotSupportedError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + OperationError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPERATION_ERROR)) {
                return generateException(new OperationError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + OptOutError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_OPT_OUT_ERROR)) {
                return generateException(new OptOutError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + QuotaExceededError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_QUOTA_EXCEEDED_ERROR)) {
                return generateException(new QuotaExceededError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + ReadOnlyError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_READ_ONLY_ERROR)) {
                return generateException(new ReadOnlyError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + SecurityError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_SECURITY_ERROR)) {
                return generateException(new SecurityError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + SyntaxError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_SYNTAX_ERROR)) {
                return generateException(new SyntaxError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + TimeoutError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_TIMEOUT_ERROR)) {
                return generateException(new TimeoutError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + TransactionInactiveError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_TRANSACTION_INACTIVE_ERROR)) {
                return generateException(new TransactionInactiveError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + UnknownError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_UNKNOWN_ERROR)) {
                return generateException(new UnknownError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + VersionError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_VERSION_ERROR)) {
                return generateException(new VersionError(), str3, t);
            }
            if (Intrinsics.areEqual((Object) str, (Object) str2 + WrongDocumentError.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_WRONG_DOCUMENT_ERROR)) {
                return generateException(new WrongDocumentError(), str3, t);
            }
            throw new FrameworkClassParsingException();
        }

        /* access modifiers changed from: private */
        public final <T> T generateException(DomError domError, String str, T t) {
            if (t instanceof CreatePublicKeyCredentialDomException) {
                return (Object) new CreatePublicKeyCredentialDomException(domError, str);
            }
            if (t instanceof GetPublicKeyCredentialDomException) {
                return (Object) new GetPublicKeyCredentialDomException(domError, str);
            }
            throw new FrameworkClassParsingException();
        }
    }
}
