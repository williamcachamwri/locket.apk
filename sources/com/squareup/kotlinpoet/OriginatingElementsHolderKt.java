package com.squareup.kotlinpoet;

import com.squareup.kotlinpoet.OriginatingElementsHolder;
import java.util.List;
import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¨\u0006\u0005"}, d2 = {"buildOriginatingElements", "Lcom/squareup/kotlinpoet/OriginatingElements;", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder$Builder;", "", "Ljavax/lang/model/element/Element;", "kotlinpoet"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: OriginatingElementsHolder.kt */
public final class OriginatingElementsHolderKt {
    public static final OriginatingElements buildOriginatingElements(OriginatingElementsHolder.Builder<?> builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return new OriginatingElements(UtilKt.toImmutableList(builder.getOriginatingElements()));
    }

    public static final OriginatingElements buildOriginatingElements(List<? extends Element> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return new OriginatingElements(UtilKt.toImmutableList(list));
    }
}
