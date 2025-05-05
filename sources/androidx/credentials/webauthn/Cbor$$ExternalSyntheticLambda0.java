package androidx.credentials.webauthn;

import java.util.Comparator;
import kotlin.jvm.internal.Ref;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Cbor$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ Ref.ObjectRef f$0;

    public /* synthetic */ Cbor$$ExternalSyntheticLambda0(Ref.ObjectRef objectRef) {
        this.f$0 = objectRef;
    }

    public final int compare(Object obj, Object obj2) {
        return Cbor.encode$lambda$0(this.f$0, (byte[]) obj, (byte[]) obj2);
    }
}
