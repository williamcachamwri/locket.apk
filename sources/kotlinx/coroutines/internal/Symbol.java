package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.text.Typography;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u001e\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\b¢\u0006\u0002\u0010\nR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/internal/Symbol;", "", "symbol", "", "<init>", "(Ljava/lang/String;)V", "toString", "unbox", "T", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Symbol.kt */
public final class Symbol {
    public final String symbol;

    public final <T> T unbox(Object obj) {
        if (obj == this) {
            return null;
        }
        return obj;
    }

    public Symbol(String str) {
        this.symbol = str;
    }

    public String toString() {
        return "<" + this.symbol + Typography.greater;
    }
}
