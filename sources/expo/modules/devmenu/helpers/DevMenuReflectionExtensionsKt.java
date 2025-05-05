package expo.modules.devmenu.helpers;

import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0007\u001a5\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u00022\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"getPrivateDeclaredFieldValue", "R", "T", "Ljava/lang/Class;", "filedName", "", "obj", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "setPrivateDeclaredFieldValue", "", "newValue", "", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", "expo-dev-menu_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuReflectionExtensions.kt */
public final class DevMenuReflectionExtensionsKt {
    public static final <T, R> R getPrivateDeclaredFieldValue(Class<? extends T> cls, String str, T t) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(str, "filedName");
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(t);
    }

    public static final <T> void setPrivateDeclaredFieldValue(Class<? extends T> cls, String str, T t, Object obj) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(str, "filedName");
        Intrinsics.checkNotNullParameter(obj, "newValue");
        Field declaredField = cls.getDeclaredField(str);
        Field declaredField2 = Field.class.getDeclaredField("accessFlags");
        declaredField.setAccessible(true);
        declaredField2.setAccessible(true);
        declaredField2.setInt(declaredField, declaredField.getModifiers() & -17);
        declaredField.set(t, obj);
    }
}
