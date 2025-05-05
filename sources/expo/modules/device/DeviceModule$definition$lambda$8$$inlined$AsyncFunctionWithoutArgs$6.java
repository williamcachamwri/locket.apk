package expo.modules.device;

import android.content.pm.FeatureInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$6 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ DeviceModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeviceModule$definition$lambda$8$$inlined$AsyncFunctionWithoutArgs$6(DeviceModule deviceModule) {
        super(1);
        this.this$0 = deviceModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        FeatureInfo[] systemAvailableFeatures = this.this$0.getContext().getApplicationContext().getPackageManager().getSystemAvailableFeatures();
        Intrinsics.checkNotNullExpressionValue(systemAvailableFeatures, "getSystemAvailableFeatures(...)");
        Iterable<FeatureInfo> filterNotNull = ArraysKt.filterNotNull(systemAvailableFeatures);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
        for (FeatureInfo featureInfo : filterNotNull) {
            arrayList.add(featureInfo.name);
        }
        return (List) arrayList;
    }
}
