package org.koin.dsl;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.Message;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.instance.InstanceBuilderKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "T", "", "Lorg/koin/core/scope/Scope;", "params", "Lorg/koin/core/parameter/ParametersHolder;", "invoke", "(Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: ModuleExt.kt */
public final class ModuleExtKt$factory$1 extends Lambda implements Function2<Scope, ParametersHolder, T> {
    public static final ModuleExtKt$factory$1 INSTANCE = new ModuleExtKt$factory$1();

    public ModuleExtKt$factory$1() {
        super(2);
    }

    public final T invoke(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "$this$factory");
        Intrinsics.checkNotNullParameter(parametersHolder, Message.JsonKeys.PARAMS);
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return InstanceBuilderKt.newInstance(scope, Reflection.getOrCreateKotlinClass(Object.class), parametersHolder);
    }
}
