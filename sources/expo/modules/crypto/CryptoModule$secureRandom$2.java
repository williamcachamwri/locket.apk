package expo.modules.crypto;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/security/SecureRandom;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CryptoModule.kt */
final class CryptoModule$secureRandom$2 extends Lambda implements Function0<SecureRandom> {
    public static final CryptoModule$secureRandom$2 INSTANCE = new CryptoModule$secureRandom$2();

    CryptoModule$secureRandom$2() {
        super(0);
    }

    public final SecureRandom invoke() {
        return new SecureRandom();
    }
}
