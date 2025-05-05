package com.squareup.kotlinpoet.ksp;

import com.google.devtools.ksp.symbol.Visibility;
import com.squareup.kotlinpoet.KModifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toKModifier", "Lcom/squareup/kotlinpoet/KModifier;", "Lcom/google/devtools/ksp/symbol/Visibility;", "ksp"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: visibilities.kt */
public final class VisibilitiesKt {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: visibilities.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Visibility.values().length];
            iArr[Visibility.PUBLIC.ordinal()] = 1;
            iArr[Visibility.PRIVATE.ordinal()] = 2;
            iArr[Visibility.PROTECTED.ordinal()] = 3;
            iArr[Visibility.INTERNAL.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final KModifier toKModifier(Visibility visibility) {
        Intrinsics.checkNotNullParameter(visibility, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[visibility.ordinal()];
        if (i == 1) {
            return KModifier.PUBLIC;
        }
        if (i == 2) {
            return KModifier.PRIVATE;
        }
        if (i == 3) {
            return KModifier.PROTECTED;
        }
        if (i != 4) {
            return null;
        }
        return KModifier.INTERNAL;
    }
}
