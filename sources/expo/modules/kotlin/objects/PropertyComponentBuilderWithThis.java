package expo.modules.kotlin.objects;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J.\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\u0004\b\u0001\u0010\u000b2\u0014\b\u0004\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u000b0\rH\bø\u0001\u0000JT\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000\"\u0006\b\u0001\u0010\u000b\u0018\u000128\b\u0004\u0010\f\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0010\u0012\b\b\u0005\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u0010\u0012\b\b\u0005\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\u000fH\bø\u0001\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0014"}, d2 = {"Lexpo/modules/kotlin/objects/PropertyComponentBuilderWithThis;", "ThisType", "Lexpo/modules/kotlin/objects/PropertyComponentBuilder;", "thisType", "Lkotlin/reflect/KType;", "name", "", "(Lkotlin/reflect/KType;Ljava/lang/String;)V", "getThisType", "()Lkotlin/reflect/KType;", "get", "T", "body", "Lkotlin/Function1;", "set", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "self", "newValue", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PropertyComponentBuilder.kt */
public final class PropertyComponentBuilderWithThis<ThisType> extends PropertyComponentBuilder {
    private final KType thisType;

    public final KType getThisType() {
        return this.thisType;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PropertyComponentBuilderWithThis(KType kType, String str) {
        super(str);
        Intrinsics.checkNotNullParameter(kType, "thisType");
        Intrinsics.checkNotNullParameter(str, "name");
        this.thisType = kType;
    }

    public final <T> PropertyComponentBuilderWithThis<ThisType> get(Function1<? super ThisType, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        PropertyComponentBuilderWithThis propertyComponentBuilderWithThis = this;
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent("get", new AnyType[]{new AnyType(getThisType())}, new PropertyComponentBuilderWithThis$get$1$1(function1));
        syncFunctionComponent.setOwnerType(getThisType());
        syncFunctionComponent.setCanTakeOwner(true);
        setGetter(syncFunctionComponent);
        return this;
    }

    public final /* synthetic */ <T> PropertyComponentBuilderWithThis<ThisType> set(Function2<? super ThisType, ? super T, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        PropertyComponentBuilderWithThis propertyComponentBuilderWithThis = this;
        Intrinsics.needClassReification();
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
        AnyType[] anyTypeArr = {new AnyType(getThisType()), new AnyType(new LazyKType(orCreateKotlinClass, false, PropertyComponentBuilderWithThis$set$$inlined$apply$lambda$1.INSTANCE))};
        Intrinsics.needClassReification();
        SyncFunctionComponent syncFunctionComponent = new SyncFunctionComponent("set", anyTypeArr, new PropertyComponentBuilderWithThis$set$1$2(function2));
        SyncFunctionComponent syncFunctionComponent2 = syncFunctionComponent;
        syncFunctionComponent.setOwnerType(getThisType());
        syncFunctionComponent.setCanTakeOwner(true);
        setSetter(syncFunctionComponent);
        return this;
    }
}
