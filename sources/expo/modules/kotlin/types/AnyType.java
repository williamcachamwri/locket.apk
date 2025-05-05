package expo.modules.kotlin.types;

import android.view.View;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.ExpectedType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\r\u001a\u0004\u0018\u00010\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u0014\"\u0006\b\u0000\u0010\u0015\u0018\u0001H\b¢\u0006\u0002\b\u0016R\u001f\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lexpo/modules/kotlin/types/AnyType;", "", "kType", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "converter", "Lexpo/modules/kotlin/types/TypeConverter;", "getConverter", "()Lexpo/modules/kotlin/types/TypeConverter;", "converter$delegate", "Lkotlin/Lazy;", "getKType", "()Lkotlin/reflect/KType;", "convert", "value", "appContext", "Lexpo/modules/kotlin/AppContext;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "inheritFrom", "", "T", "inheritFrom$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AnyType.kt */
public final class AnyType {
    private final Lazy converter$delegate = LazyKt.lazy(new AnyType$converter$2(this));
    private final KType kType;

    public AnyType(KType kType2) {
        Intrinsics.checkNotNullParameter(kType2, "kType");
        this.kType = kType2;
    }

    public final KType getKType() {
        return this.kType;
    }

    private final TypeConverter<?> getConverter() {
        return (TypeConverter) this.converter$delegate.getValue();
    }

    public static /* synthetic */ Object convert$default(AnyType anyType, Object obj, AppContext appContext, int i, Object obj2) {
        if ((i & 2) != 0) {
            appContext = null;
        }
        return anyType.convert(obj, appContext);
    }

    public final Object convert(Object obj, AppContext appContext) {
        return getConverter().convert(obj, appContext);
    }

    public final ExpectedType getCppRequiredTypes() {
        return getConverter().getCppRequiredTypes();
    }

    public final /* synthetic */ <T> boolean inheritFrom$expo_modules_core_release() {
        KClassifier classifier = getKType().getClassifier();
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass == null) {
            return false;
        }
        return View.class.isAssignableFrom(JvmClassMappingKt.getJavaClass(kClass));
    }
}
