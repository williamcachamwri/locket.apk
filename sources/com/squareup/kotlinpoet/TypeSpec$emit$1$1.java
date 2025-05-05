package com.squareup.kotlinpoet;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "param", "Lcom/squareup/kotlinpoet/ParameterSpec;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: TypeSpec.kt */
final class TypeSpec$emit$1$1 extends Lambda implements Function1<ParameterSpec, Unit> {
    final /* synthetic */ CodeWriter $codeWriter;
    final /* synthetic */ Map<String, PropertySpec> $constructorProperties;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TypeSpec$emit$1$1(Map<String, PropertySpec> map, CodeWriter codeWriter) {
        super(1);
        this.$constructorProperties = map;
        this.$codeWriter = codeWriter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ParameterSpec) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ParameterSpec parameterSpec) {
        Intrinsics.checkNotNullParameter(parameterSpec, "param");
        PropertySpec propertySpec = this.$constructorProperties.get(parameterSpec.getName());
        if (propertySpec != null) {
            PropertySpec.emit$kotlinpoet$default(propertySpec, this.$codeWriter, SetsKt.setOf(KModifier.PUBLIC), false, false, true, false, 8, (Object) null);
            parameterSpec.emitDefaultValue$kotlinpoet(this.$codeWriter);
            return;
        }
        ParameterSpec.emit$kotlinpoet$default(parameterSpec, this.$codeWriter, false, true, false, 2, (Object) null);
    }
}
