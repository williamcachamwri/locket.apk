package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.StructureKind;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "invoke", "()[Lkotlinx/serialization/descriptors/SerialDescriptor;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Enums.kt */
final class EnumDescriptor$elementDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {
    final /* synthetic */ int $elementsCount;
    final /* synthetic */ String $name;
    final /* synthetic */ EnumDescriptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EnumDescriptor$elementDescriptors$2(int i, String str, EnumDescriptor enumDescriptor) {
        super(0);
        this.$elementsCount = i;
        this.$name = str;
        this.this$0 = enumDescriptor;
    }

    public final SerialDescriptor[] invoke() {
        int i = this.$elementsCount;
        SerialDescriptor[] serialDescriptorArr = new SerialDescriptor[i];
        for (int i2 = 0; i2 < i; i2++) {
            serialDescriptorArr[i2] = SerialDescriptorsKt.buildSerialDescriptor$default(this.$name + FilenameUtils.EXTENSION_SEPARATOR + this.this$0.getElementName(i2), StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], (Function1) null, 8, (Object) null);
        }
        return serialDescriptorArr;
    }
}
