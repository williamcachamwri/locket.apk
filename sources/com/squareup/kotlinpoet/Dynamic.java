package com.squareup.kotlinpoet;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0016\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u000bH\u0016J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0010¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/squareup/kotlinpoet/Dynamic;", "Lcom/squareup/kotlinpoet/TypeName;", "()V", "copy", "", "nullable", "", "annotations", "", "Lcom/squareup/kotlinpoet/AnnotationSpec;", "tags", "", "Lkotlin/reflect/KClass;", "", "emit", "Lcom/squareup/kotlinpoet/CodeWriter;", "out", "emit$kotlinpoet", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Dynamic.kt */
public final class Dynamic extends TypeName {
    public static final Dynamic INSTANCE = new Dynamic();

    private Dynamic() {
        super(false, CollectionsKt.emptyList(), new TagMap(MapsKt.emptyMap()), (DefaultConstructorMarker) null);
    }

    public Void copy(boolean z, List<AnnotationSpec> list, Map<KClass<?>, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(list, "annotations");
        Intrinsics.checkNotNullParameter(map, "tags");
        throw new UnsupportedOperationException("dynamic doesn't support copying");
    }

    public CodeWriter emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        CodeWriter.emit$default(codeWriter, "dynamic", false, 2, (Object) null);
        return codeWriter;
    }
}
