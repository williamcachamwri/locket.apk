package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0017\u0010\u0016\u001a\u00020\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018H\b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0014\u0010\n\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\"\u001c\u0010\f\u001a\u00020\u00078\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\t\"\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0019"}, d2 = {"DEBUG_PROPERTY_NAME", "", "STACKTRACE_RECOVERY_PROPERTY_NAME", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_ON", "DEBUG_PROPERTY_VALUE_OFF", "ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "DEBUG", "getDEBUG", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES$annotations", "()V", "getRECOVER_STACK_TRACES", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "resetCoroutineId", "", "assert", "value", "Lkotlin/Function0;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: Debug.kt */
public final class DebugKt {
    private static final boolean ASSERTIONS_ENABLED = false;
    private static final AtomicLong COROUTINE_ID = new AtomicLong(0);
    private static final boolean DEBUG;
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";
    private static final boolean RECOVER_STACK_TRACES;
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    public static /* synthetic */ void getRECOVER_STACK_TRACES$annotations() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        if (r1.equals("on") != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        if (r1.equals("") != false) goto L_0x0045;
     */
    static {
        /*
            java.lang.Class<kotlinx.coroutines.CoroutineId> r0 = kotlinx.coroutines.CoroutineId.class
            r0 = 0
            ASSERTIONS_ENABLED = r0
            java.lang.String r1 = "kotlinx.coroutines.debug"
            java.lang.String r1 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r1)
            r2 = 1
            if (r1 == 0) goto L_0x0066
            int r3 = r1.hashCode()
            if (r3 == 0) goto L_0x003d
            r4 = 3551(0xddf, float:4.976E-42)
            if (r3 == r4) goto L_0x0034
            r4 = 109935(0x1ad6f, float:1.54052E-40)
            if (r3 == r4) goto L_0x002b
            r4 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r3 != r4) goto L_0x0047
            java.lang.String r3 = "auto"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0047
            goto L_0x0066
        L_0x002b:
            java.lang.String r3 = "off"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0047
            goto L_0x0066
        L_0x0034:
            java.lang.String r3 = "on"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0047
            goto L_0x0045
        L_0x003d:
            java.lang.String r3 = ""
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x0047
        L_0x0045:
            r1 = r2
            goto L_0x0067
        L_0x0047:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "System property 'kotlinx.coroutines.debug' has unrecognized value '"
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            r2 = 39
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0066:
            r1 = r0
        L_0x0067:
            DEBUG = r1
            if (r1 == 0) goto L_0x0074
            java.lang.String r1 = "kotlinx.coroutines.stacktrace.recovery"
            boolean r1 = kotlinx.coroutines.internal.SystemPropsKt.systemProp((java.lang.String) r1, (boolean) r2)
            if (r1 == 0) goto L_0x0074
            r0 = r2
        L_0x0074:
            RECOVER_STACK_TRACES = r0
            java.util.concurrent.atomic.AtomicLong r0 = new java.util.concurrent.atomic.AtomicLong
            r1 = 0
            r0.<init>(r1)
            COROUTINE_ID = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DebugKt.<clinit>():void");
    }

    public static final boolean getASSERTIONS_ENABLED() {
        return ASSERTIONS_ENABLED;
    }

    public static final boolean getDEBUG() {
        return DEBUG;
    }

    public static final boolean getRECOVER_STACK_TRACES() {
        return RECOVER_STACK_TRACES;
    }

    public static final AtomicLong getCOROUTINE_ID() {
        return COROUTINE_ID;
    }

    public static final void resetCoroutineId() {
        COROUTINE_ID.set(0);
    }

    /* renamed from: assert  reason: not valid java name */
    private static final void m1772assert(Function0<Boolean> function0) {
        if (getASSERTIONS_ENABLED() && !function0.invoke().booleanValue()) {
            throw new AssertionError();
        }
    }
}
