package expo.modules.devmenu;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSIModulePackage;
import java.lang.reflect.Field;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a=\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\n\u001a2\u0010\u000b\u001a\u0002H\u0003\"\u0006\b\u0000\u0010\u0003\u0018\u00012\u0006\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u000e\u001a\u00020\u0001H\b¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0010\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0011\u001a\u00020\u00012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\u0012\u001a\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0001\u001a\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0001\u001a*\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"DEV_MENU_TAG", "", "constructFromClass", "T", "clazz", "Ljava/lang/Class;", "argsType", "", "args", "", "(Ljava/lang/Class;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;", "getPrivateFiled", "obj", "objClass", "field", "(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "getVendoredClass", "className", "(Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;", "getVendoredJNIPackage", "Lcom/facebook/react/bridge/JSIModulePackage;", "getVendoredPackage", "Lcom/facebook/react/ReactPackage;", "setPrivateField", "", "newValue", "expo-dev-menu_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuUtils.kt */
public final class DevMenuUtilsKt {
    public static final String DEV_MENU_TAG = "ExpoDevMenu";

    public static final ReactPackage getVendoredPackage(String str) {
        Intrinsics.checkNotNullParameter(str, "className");
        return (ReactPackage) getVendoredClass(str, new Class[0], new Object[0]);
    }

    public static final JSIModulePackage getVendoredJNIPackage(String str) {
        Intrinsics.checkNotNullParameter(str, "className");
        return (JSIModulePackage) getVendoredClass(str, new Class[0], new Object[0]);
    }

    public static final <T> T getVendoredClass(String str, Class<?>[] clsArr, Object[] objArr) {
        Class cls;
        Intrinsics.checkNotNullParameter(str, "className");
        Intrinsics.checkNotNullParameter(clsArr, "argsType");
        Intrinsics.checkNotNullParameter(objArr, "args");
        try {
            cls = Class.forName("devmenu." + str);
        } catch (ClassNotFoundException unused) {
            cls = Class.forName(str);
        }
        Intrinsics.checkNotNull(cls);
        return constructFromClass(cls, clsArr, objArr);
    }

    public static final <T> T constructFromClass(Class<?> cls, Class<?>[] clsArr, Object[] objArr) {
        Intrinsics.checkNotNullParameter(cls, "clazz");
        Intrinsics.checkNotNullParameter(clsArr, "argsType");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return cls.getConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length)).newInstance(Arrays.copyOf(objArr, objArr.length));
    }

    public static final void setPrivateField(Object obj, Class<?> cls, String str, Object obj2) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(cls, "objClass");
        Intrinsics.checkNotNullParameter(str, "field");
        Intrinsics.checkNotNullParameter(obj2, "newValue");
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    public static final /* synthetic */ <T> T getPrivateFiled(Object obj, Class<?> cls, String str) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(cls, "objClass");
        Intrinsics.checkNotNullParameter(str, "field");
        Field declaredField = cls.getDeclaredField(str);
        Field field = declaredField;
        declaredField.setAccessible(true);
        T t = declaredField.get(obj);
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        Object obj2 = (Object) t;
        return t;
    }
}
