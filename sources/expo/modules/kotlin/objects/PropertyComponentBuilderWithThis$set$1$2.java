package expo.modules.kotlin.objects;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u00032\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "T", "ThisType", "it", "", "", "invoke", "([Ljava/lang/Object;)V"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: PropertyComponentBuilder.kt */
final class PropertyComponentBuilderWithThis$set$1$2 extends Lambda implements Function1<Object[], Unit> {
    final /* synthetic */ Function2<ThisType, T, Unit> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PropertyComponentBuilderWithThis$set$1$2(Function2<? super ThisType, ? super T, Unit> function2) {
        super(1);
        this.$body = function2;
    }

    public final void invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Function2<ThisType, T, Unit> function2 = this.$body;
        Object obj = objArr[0];
        Object obj2 = obj;
        Object obj3 = objArr[1];
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        Object obj4 = obj3;
        function2.invoke(obj, obj3);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Object[]) obj);
        return Unit.INSTANCE;
    }
}
