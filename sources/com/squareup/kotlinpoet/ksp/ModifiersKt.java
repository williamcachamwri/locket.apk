package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.Modifier;
import com.squareup.kotlinpoet.KModifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toKModifier", "Lcom/squareup/kotlinpoet/KModifier;", "Lcom/google/devtools/ksp/symbol/Modifier;", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: modifiers.kt */
public final class ModifiersKt {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: modifiers.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Modifier.values().length];
            iArr[Modifier.PUBLIC.ordinal()] = 1;
            iArr[Modifier.PRIVATE.ordinal()] = 2;
            iArr[Modifier.INTERNAL.ordinal()] = 3;
            iArr[Modifier.PROTECTED.ordinal()] = 4;
            iArr[Modifier.IN.ordinal()] = 5;
            iArr[Modifier.OUT.ordinal()] = 6;
            iArr[Modifier.OVERRIDE.ordinal()] = 7;
            iArr[Modifier.LATEINIT.ordinal()] = 8;
            iArr[Modifier.ENUM.ordinal()] = 9;
            iArr[Modifier.SEALED.ordinal()] = 10;
            iArr[Modifier.ANNOTATION.ordinal()] = 11;
            iArr[Modifier.DATA.ordinal()] = 12;
            iArr[Modifier.INNER.ordinal()] = 13;
            iArr[Modifier.FUN.ordinal()] = 14;
            iArr[Modifier.VALUE.ordinal()] = 15;
            iArr[Modifier.SUSPEND.ordinal()] = 16;
            iArr[Modifier.TAILREC.ordinal()] = 17;
            iArr[Modifier.OPERATOR.ordinal()] = 18;
            iArr[Modifier.INFIX.ordinal()] = 19;
            iArr[Modifier.INLINE.ordinal()] = 20;
            iArr[Modifier.EXTERNAL.ordinal()] = 21;
            iArr[Modifier.ABSTRACT.ordinal()] = 22;
            iArr[Modifier.FINAL.ordinal()] = 23;
            iArr[Modifier.OPEN.ordinal()] = 24;
            iArr[Modifier.VARARG.ordinal()] = 25;
            iArr[Modifier.NOINLINE.ordinal()] = 26;
            iArr[Modifier.CROSSINLINE.ordinal()] = 27;
            iArr[Modifier.REIFIED.ordinal()] = 28;
            iArr[Modifier.EXPECT.ordinal()] = 29;
            iArr[Modifier.ACTUAL.ordinal()] = 30;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final KModifier toKModifier(Modifier modifier) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[modifier.ordinal()]) {
            case 1:
                return KModifier.PUBLIC;
            case 2:
                return KModifier.PRIVATE;
            case 3:
                return KModifier.INTERNAL;
            case 4:
                return KModifier.PROTECTED;
            case 5:
                return KModifier.IN;
            case 6:
                return KModifier.OUT;
            case 7:
                return KModifier.OVERRIDE;
            case 8:
                return KModifier.LATEINIT;
            case 9:
                return KModifier.ENUM;
            case 10:
                return KModifier.SEALED;
            case 11:
                return KModifier.ANNOTATION;
            case 12:
                return KModifier.DATA;
            case 13:
                return KModifier.INNER;
            case 14:
                return KModifier.FUN;
            case 15:
                return KModifier.VALUE;
            case 16:
                return KModifier.SUSPEND;
            case 17:
                return KModifier.TAILREC;
            case 18:
                return KModifier.OPERATOR;
            case 19:
                return KModifier.INFIX;
            case 20:
                return KModifier.INLINE;
            case 21:
                return KModifier.EXTERNAL;
            case 22:
                return KModifier.ABSTRACT;
            case 23:
                return KModifier.FINAL;
            case 24:
                return KModifier.OPEN;
            case 25:
                return KModifier.VARARG;
            case 26:
                return KModifier.NOINLINE;
            case 27:
                return KModifier.CROSSINLINE;
            case 28:
                return KModifier.REIFIED;
            case 29:
                return KModifier.EXPECT;
            case 30:
                return KModifier.ACTUAL;
            default:
                return null;
        }
    }
}
