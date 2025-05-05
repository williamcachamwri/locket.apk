package androidx.credentials.exceptions.publickeycredential;

import androidx.credentials.exceptions.CreateCredentialCustomException;
import androidx.credentials.exceptions.CreateCredentialException;
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
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/CreatePublicKeyCredentialDomException;", "Landroidx/credentials/exceptions/publickeycredential/CreatePublicKeyCredentialException;", "domError", "Landroidx/credentials/exceptions/domerrors/DomError;", "errorMessage", "", "(Landroidx/credentials/exceptions/domerrors/DomError;Ljava/lang/CharSequence;)V", "getDomError", "()Landroidx/credentials/exceptions/domerrors/DomError;", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePublicKeyCredentialDomException.kt */
public final class CreatePublicKeyCredentialDomException extends CreatePublicKeyCredentialException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION = "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION";
    private final DomError domError;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialDomException(DomError domError2) {
        this(domError2, (CharSequence) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(domError2, "domError");
    }

    @JvmStatic
    public static final CreateCredentialException createFrom(String str, String str2) {
        return Companion.createFrom(str, str2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CreatePublicKeyCredentialDomException(DomError domError2, CharSequence charSequence, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(domError2, (i & 2) != 0 ? null : charSequence);
    }

    public final DomError getDomError() {
        return this.domError;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CreatePublicKeyCredentialDomException(DomError domError2, CharSequence charSequence) {
        super("androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/" + domError2.getType(), charSequence);
        Intrinsics.checkNotNullParameter(domError2, "domError");
        this.domError = domError2;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/credentials/exceptions/publickeycredential/CreatePublicKeyCredentialDomException$Companion;", "", "()V", "TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION", "", "createFrom", "Landroidx/credentials/exceptions/CreateCredentialException;", "type", "msg", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreatePublicKeyCredentialDomException.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CreateCredentialException createFrom(String str, String str2) {
            Object obj;
            Intrinsics.checkNotNullParameter(str, "type");
            try {
                DomExceptionUtils.Companion companion = DomExceptionUtils.Companion;
                CreatePublicKeyCredentialDomException createPublicKeyCredentialDomException = new CreatePublicKeyCredentialDomException(new UnknownError(), (CharSequence) null, 2, (DefaultConstructorMarker) null);
                if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ABORT_ERROR")) {
                    obj = companion.generateException(new AbortError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_CONSTRAINT_ERROR")) {
                    obj = companion.generateException(new ConstraintError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_CLONE_ERROR")) {
                    obj = companion.generateException(new DataCloneError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_DATA_ERROR")) {
                    obj = companion.generateException(new DataError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_ENCODING_ERROR")) {
                    obj = companion.generateException(new EncodingError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_HIERARCHY_REQUEST_ERROR")) {
                    obj = companion.generateException(new HierarchyRequestError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_IN_USE_ATTRIBUTE_ERROR")) {
                    obj = companion.generateException(new InUseAttributeError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_CHARACTER_ERROR")) {
                    obj = companion.generateException(new InvalidCharacterError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_MODIFICATION_ERROR")) {
                    obj = companion.generateException(new InvalidModificationError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_NODE_TYPE_ERROR")) {
                    obj = companion.generateException(new InvalidNodeTypeError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_INVALID_STATE_ERROR")) {
                    obj = companion.generateException(new InvalidStateError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NAMESPACE_ERROR")) {
                    obj = companion.generateException(new NamespaceError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NETWORK_ERROR")) {
                    obj = companion.generateException(new NetworkError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NO_MODIFICATION_ALLOWED_ERROR")) {
                    obj = companion.generateException(new NoModificationAllowedError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_ALLOWED_ERROR")) {
                    obj = companion.generateException(new NotAllowedError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_FOUND_ERROR")) {
                    obj = companion.generateException(new NotFoundError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_READABLE_ERROR")) {
                    obj = companion.generateException(new NotReadableError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_NOT_SUPPORTED_ERROR")) {
                    obj = companion.generateException(new NotSupportedError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPERATION_ERROR")) {
                    obj = companion.generateException(new OperationError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_OPT_OUT_ERROR")) {
                    obj = companion.generateException(new OptOutError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_QUOTA_EXCEEDED_ERROR")) {
                    obj = companion.generateException(new QuotaExceededError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_READ_ONLY_ERROR")) {
                    obj = companion.generateException(new ReadOnlyError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SECURITY_ERROR")) {
                    obj = companion.generateException(new SecurityError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_SYNTAX_ERROR")) {
                    obj = companion.generateException(new SyntaxError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TIMEOUT_ERROR")) {
                    obj = companion.generateException(new TimeoutError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_TRANSACTION_INACTIVE_ERROR")) {
                    obj = companion.generateException(new TransactionInactiveError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_UNKNOWN_ERROR")) {
                    obj = companion.generateException(new UnknownError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_VERSION_ERROR")) {
                    obj = companion.generateException(new VersionError(), str2, createPublicKeyCredentialDomException);
                } else if (Intrinsics.areEqual((Object) str, (Object) "androidx.credentials.TYPE_CREATE_PUBLIC_KEY_CREDENTIAL_DOM_EXCEPTION/androidx.credentials.TYPE_WRONG_DOCUMENT_ERROR")) {
                    obj = companion.generateException(new WrongDocumentError(), str2, createPublicKeyCredentialDomException);
                } else {
                    throw new FrameworkClassParsingException();
                }
                return (CreateCredentialException) obj;
            } catch (FrameworkClassParsingException unused) {
                return new CreateCredentialCustomException(str, str2);
            }
        }
    }
}
