package expo.modules.filesystem;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u0001\"\u0006\b\u0004\u0010\u0006\u0018\u0001\"\u0006\b\u0005\u0010\u0007\u0018\u0001\"\u0006\b\u0006\u0010\b\u0018\u0001H\n¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"<anonymous>", "Lkotlin/reflect/KType;", "R", "P0", "P1", "P2", "P3", "P4", "P5", "invoke", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$56"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$103 extends Lambda implements Function0<KType> {
    public static final FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$103 INSTANCE = new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$103();

    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$103() {
        super(0);
    }

    public final KType invoke() {
        return Reflection.typeOf(Promise.class);
    }
}
