package expo.modules.crypto;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0007H\n¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$Function$16"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class CryptoModule$definition$lambda$6$$inlined$Function$4 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ CryptoModule $receiver$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CryptoModule$definition$lambda$6$$inlined$Function$4(CryptoModule cryptoModule) {
        super(1);
        this.$receiver$inlined = cryptoModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        DigestAlgorithm digestAlgorithm = objArr[0];
        if (digestAlgorithm != null) {
            DigestAlgorithm digestAlgorithm2 = digestAlgorithm;
            String str = objArr[1];
            if (str != null) {
                String str2 = str;
                DigestOptions digestOptions = objArr[2];
                if (digestOptions != null) {
                    return this.$receiver$inlined.digestString(digestAlgorithm2, str2, digestOptions);
                }
                throw new NullPointerException("null cannot be cast to non-null type expo.modules.crypto.DigestOptions");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.crypto.DigestAlgorithm");
    }
}
