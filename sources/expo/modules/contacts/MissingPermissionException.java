package expo.modules.contacts;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/contacts/MissingPermissionException;", "Lexpo/modules/kotlin/exception/CodedException;", "permission", "", "(Ljava/lang/String;)V", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class MissingPermissionException extends CodedException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MissingPermissionException(String str) {
        super("Missing " + str + " permission", (Throwable) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "permission");
    }
}
