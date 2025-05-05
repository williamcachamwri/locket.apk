package com.squareup.kotlinpoet;

import com.adsbynimbus.render.mraid.HostKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b$\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001/B#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\nj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.¨\u00060"}, d2 = {"Lcom/squareup/kotlinpoet/KModifier;", "", "keyword", "", "targets", "", "Lcom/squareup/kotlinpoet/KModifier$Target;", "(Ljava/lang/String;ILjava/lang/String;[Lcom/squareup/kotlinpoet/KModifier$Target;)V", "getKeyword$kotlinpoet", "()Ljava/lang/String;", "[Lcom/squareup/kotlinpoet/KModifier$Target;", "checkTarget", "", "target", "checkTarget$kotlinpoet", "PUBLIC", "PROTECTED", "PRIVATE", "INTERNAL", "EXPECT", "ACTUAL", "FINAL", "OPEN", "ABSTRACT", "SEALED", "CONST", "EXTERNAL", "OVERRIDE", "LATEINIT", "TAILREC", "VARARG", "SUSPEND", "INNER", "ENUM", "ANNOTATION", "VALUE", "FUN", "COMPANION", "INLINE", "NOINLINE", "CROSSINLINE", "REIFIED", "INFIX", "OPERATOR", "DATA", "IN", "OUT", "Target", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: KModifier.kt */
public enum KModifier {
    PUBLIC("public", Target.PROPERTY),
    PROTECTED("protected", Target.PROPERTY),
    PRIVATE("private", Target.PROPERTY),
    INTERNAL("internal", Target.PROPERTY),
    EXPECT("expect", Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    ACTUAL("actual", Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    FINAL("final", Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    OPEN(TtmlNode.TEXT_EMPHASIS_MARK_OPEN, Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    ABSTRACT("abstract", Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    SEALED("sealed", Target.CLASS),
    CONST("const", Target.PROPERTY),
    EXTERNAL("external", Target.CLASS, Target.FUNCTION, Target.PROPERTY),
    OVERRIDE("override", Target.FUNCTION, Target.PROPERTY),
    LATEINIT("lateinit", Target.PROPERTY),
    TAILREC("tailrec", Target.FUNCTION),
    VARARG("vararg", Target.PARAMETER),
    SUSPEND("suspend", Target.FUNCTION),
    INNER("inner", Target.CLASS),
    ENUM("enum", Target.CLASS),
    ANNOTATION("annotation", Target.CLASS),
    VALUE("value", Target.CLASS),
    FUN("fun", Target.INTERFACE),
    COMPANION("companion", Target.CLASS),
    INLINE(HostKt.INLINE, Target.FUNCTION),
    NOINLINE("noinline", Target.PARAMETER),
    CROSSINLINE("crossinline", Target.PARAMETER),
    REIFIED("reified", Target.TYPE_PARAMETER),
    INFIX("infix", Target.FUNCTION),
    OPERATOR("operator", Target.FUNCTION),
    DATA("data", Target.CLASS),
    IN("in", Target.VARIANCE_ANNOTATION),
    OUT("out", Target.VARIANCE_ANNOTATION);
    
    private final String keyword;
    private final Target[] targets;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/squareup/kotlinpoet/KModifier$Target;", "", "(Ljava/lang/String;I)V", "CLASS", "VARIANCE_ANNOTATION", "PARAMETER", "TYPE_PARAMETER", "FUNCTION", "PROPERTY", "INTERFACE", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: KModifier.kt */
    public enum Target {
        CLASS,
        VARIANCE_ANNOTATION,
        PARAMETER,
        TYPE_PARAMETER,
        FUNCTION,
        PROPERTY,
        INTERFACE
    }

    private KModifier(String str, Target... targetArr) {
        this.keyword = str;
        this.targets = targetArr;
    }

    public final String getKeyword$kotlinpoet() {
        return this.keyword;
    }

    public final void checkTarget$kotlinpoet(Target target) {
        Intrinsics.checkNotNullParameter(target, "target");
        if (!ArraysKt.contains((T[]) this.targets, target)) {
            throw new IllegalArgumentException(("unexpected modifier " + this + " for " + target).toString());
        }
    }
}
