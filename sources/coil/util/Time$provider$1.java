package coil.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Time.kt */
/* synthetic */ class Time$provider$1 extends FunctionReferenceImpl implements Function0<Long> {
    public static final Time$provider$1 INSTANCE = new Time$provider$1();

    Time$provider$1() {
        super(0, System.class, "currentTimeMillis", "currentTimeMillis()J", 0);
    }

    public final Long invoke() {
        return Long.valueOf(System.currentTimeMillis());
    }
}
