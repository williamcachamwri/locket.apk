package com.squareup.kotlinpoet;

import javax.lang.model.element.Element;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000b\u0010\u0003\u001a\u00070\u0004¢\u0006\u0002\b\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lkotlin/sequences/Sequence;", "Ljavax/lang/model/element/Element;", "it", "Lcom/squareup/kotlinpoet/OriginatingElementsHolder;", "Lkotlin/internal/NoInfer;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: FileSpec.kt */
final class FileSpec$writeTo$originatingElements$1 extends Lambda implements Function1<OriginatingElementsHolder, Sequence<? extends Element>> {
    public static final FileSpec$writeTo$originatingElements$1 INSTANCE = new FileSpec$writeTo$originatingElements$1();

    FileSpec$writeTo$originatingElements$1() {
        super(1);
    }

    public final Sequence<Element> invoke(OriginatingElementsHolder originatingElementsHolder) {
        Intrinsics.checkNotNullParameter(originatingElementsHolder, "it");
        return CollectionsKt.asSequence(originatingElementsHolder.getOriginatingElements());
    }
}
