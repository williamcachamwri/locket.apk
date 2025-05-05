package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;

/* compiled from: StaticScopeForKotlinEnum.kt */
final class StaticScopeForKotlinEnum$properties$2 extends Lambda implements Function0<List<? extends PropertyDescriptor>> {
    final /* synthetic */ StaticScopeForKotlinEnum this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticScopeForKotlinEnum$properties$2(StaticScopeForKotlinEnum staticScopeForKotlinEnum) {
        super(0);
        this.this$0 = staticScopeForKotlinEnum;
    }

    public final List<PropertyDescriptor> invoke() {
        if (this.this$0.enumEntriesCanBeUsed) {
            return CollectionsKt.listOfNotNull(DescriptorFactory.createEnumEntriesProperty(this.this$0.containingClass));
        }
        return CollectionsKt.emptyList();
    }
}
