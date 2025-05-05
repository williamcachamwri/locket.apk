package expo.modules.kotlin.sharedobjects;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedRef;", "RefType", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "ref", "(Ljava/lang/Object;)V", "getRef", "()Ljava/lang/Object;", "Ljava/lang/Object;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SharedRef.kt */
public class SharedRef<RefType> extends SharedObject {
    private final RefType ref;

    public SharedRef(RefType reftype) {
        this.ref = reftype;
    }

    public final RefType getRef() {
        return this.ref;
    }
}
