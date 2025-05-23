package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a,\u0012\u0004\u0012\u00020\u0002 \u0004*\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00030\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003\"\u0006\b\u0000\u0010\u0005 \u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lkotlin/reflect/KParameter;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "R", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KCallableImpl.kt */
final class KCallableImpl$_parameters$1 extends Lambda implements Function0<ArrayList<KParameter>> {
    final /* synthetic */ KCallableImpl<R> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KCallableImpl$_parameters$1(KCallableImpl<? extends R> kCallableImpl) {
        super(0);
        this.this$0 = kCallableImpl;
    }

    public final ArrayList<KParameter> invoke() {
        int i;
        final CallableMemberDescriptor descriptor = this.this$0.getDescriptor();
        ArrayList<KParameter> arrayList = new ArrayList<>();
        final int i2 = 0;
        if (!this.this$0.isBound()) {
            final ReceiverParameterDescriptor instanceReceiverParameter = UtilKt.getInstanceReceiverParameter(descriptor);
            if (instanceReceiverParameter != null) {
                arrayList.add(new KParameterImpl(this.this$0, 0, KParameter.Kind.INSTANCE, new Function0<ParameterDescriptor>() {
                    public final ParameterDescriptor invoke() {
                        return instanceReceiverParameter;
                    }
                }));
                i = 1;
            } else {
                i = 0;
            }
            final ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                arrayList.add(new KParameterImpl(this.this$0, i, KParameter.Kind.EXTENSION_RECEIVER, new Function0<ParameterDescriptor>() {
                    public final ParameterDescriptor invoke() {
                        return extensionReceiverParameter;
                    }
                }));
                i++;
            }
        } else {
            i = 0;
        }
        int size = descriptor.getValueParameters().size();
        while (i2 < size) {
            arrayList.add(new KParameterImpl(this.this$0, i, KParameter.Kind.VALUE, new Function0<ParameterDescriptor>() {
                public final ParameterDescriptor invoke() {
                    ValueParameterDescriptor valueParameterDescriptor = descriptor.getValueParameters().get(i2);
                    Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor, "get(...)");
                    return valueParameterDescriptor;
                }
            }));
            i2++;
            i++;
        }
        if (this.this$0.isAnnotationConstructor() && (descriptor instanceof JavaCallableMemberDescriptor)) {
            List list = arrayList;
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new KCallableImpl$_parameters$1$invoke$$inlined$sortBy$1());
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }
}
