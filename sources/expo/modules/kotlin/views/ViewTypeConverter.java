package expo.modules.kotlin.views;

import android.view.View;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.exception.NullArgumentException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.types.TypeConverter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J#\u0010\t\u001a\u0004\u0018\u00018\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lexpo/modules/kotlin/views/ViewTypeConverter;", "T", "Landroid/view/View;", "Lexpo/modules/kotlin/types/TypeConverter;", "type", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "getType", "()Lkotlin/reflect/KType;", "convert", "value", "", "context", "Lexpo/modules/kotlin/AppContext;", "(Ljava/lang/Object;Lexpo/modules/kotlin/AppContext;)Landroid/view/View;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "isTrivial", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewTypeConverter.kt */
public final class ViewTypeConverter<T extends View> extends TypeConverter<T> {
    private final KType type;

    public boolean isTrivial() {
        return false;
    }

    public final KType getType() {
        return this.type;
    }

    public ViewTypeConverter(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "type");
        this.type = kType;
    }

    public T convert(Object obj, AppContext appContext) {
        if (appContext != null) {
            appContext.assertMainThread$expo_modules_core_release();
            if (obj != null) {
                int intValue = ((Integer) obj).intValue();
                T findView = appContext.findView(intValue);
                if (this.type.isMarkedNullable() || findView != null) {
                    return findView;
                }
                KClassifier classifier = this.type.getClassifier();
                Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
                throw new Exceptions.ViewNotFound((KClass) classifier, intValue);
            } else if (this.type.isMarkedNullable()) {
                return null;
            } else {
                throw new NullArgumentException();
            }
        } else {
            throw new Exceptions.AppContextLost();
        }
    }

    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(CppType.INT, CppType.VIEW_TAG);
    }
}
