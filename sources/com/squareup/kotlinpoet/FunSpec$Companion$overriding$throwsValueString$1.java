package com.squareup.kotlinpoet;

import javax.lang.model.type.TypeMirror;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Ljavax/lang/model/type/TypeMirror;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FunSpec.kt */
final class FunSpec$Companion$overriding$throwsValueString$1 extends Lambda implements Function1<TypeMirror, CharSequence> {
    public static final FunSpec$Companion$overriding$throwsValueString$1 INSTANCE = new FunSpec$Companion$overriding$throwsValueString$1();

    FunSpec$Companion$overriding$throwsValueString$1() {
        super(1);
    }

    public final CharSequence invoke(TypeMirror typeMirror) {
        return "%T::class";
    }
}
