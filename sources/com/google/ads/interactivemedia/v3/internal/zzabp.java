package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzabp {
    private static final zzabk zza;

    static {
        zzabk zzabk;
        try {
            zzabk = new zzabo((zzabn) null);
        } catch (ReflectiveOperationException unused) {
            zzabk = new zzabm((zzabl) null);
        }
        zza = zzabk;
    }

    static /* bridge */ /* synthetic */ RuntimeException zza(ReflectiveOperationException reflectiveOperationException) {
        throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.10.1). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", reflectiveOperationException);
    }

    public static RuntimeException zzb(IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.10.1). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", illegalAccessException);
    }

    public static String zzc(Constructor constructor) {
        StringBuilder sb = new StringBuilder(constructor.getDeclaringClass().getName());
        zzn(constructor, sb);
        return sb.toString();
    }

    public static String zzd(Field field) {
        String name = field.getDeclaringClass().getName();
        String name2 = field.getName();
        return name + "#" + name2;
    }

    public static String zze(AccessibleObject accessibleObject, boolean z) {
        String str;
        if (accessibleObject instanceof Field) {
            str = "field '" + zzd((Field) accessibleObject) + "'";
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            StringBuilder sb = new StringBuilder(method.getName());
            zzn(method, sb);
            String sb2 = sb.toString();
            str = "method '" + method.getDeclaringClass().getName() + "#" + sb2 + "'";
        } else if (accessibleObject instanceof Constructor) {
            str = "constructor '" + zzc((Constructor) accessibleObject) + "'";
        } else {
            str = "<unknown AccessibleObject> ".concat(String.valueOf(accessibleObject.toString()));
        }
        if (!z || !Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String zzf(Constructor constructor) {
        try {
            constructor.setAccessible(true);
            return null;
        } catch (Exception e) {
            String zzc = zzc(constructor);
            String message = e.getMessage();
            String zzm = zzm(e);
            return "Failed making constructor '" + zzc + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + message + zzm;
        }
    }

    public static Constructor zzg(Class cls) {
        return zza.zza(cls);
    }

    public static Method zzh(Class cls, Field field) {
        return zza.zzb(cls, field);
    }

    public static void zzi(AccessibleObject accessibleObject) throws zzvx {
        try {
            accessibleObject.setAccessible(true);
        } catch (Exception e) {
            String zze = zze(accessibleObject, false);
            String zzm = zzm(e);
            throw new zzvx("Failed making " + zze + " accessible; either increase its visibility or write a custom TypeAdapter for its declaring type." + zzm, e);
        }
    }

    public static boolean zzj(Class cls) {
        if (Modifier.isStatic(cls.getModifiers())) {
            return false;
        }
        if (cls.isAnonymousClass()) {
            return true;
        }
        if (!cls.isLocalClass()) {
            return false;
        }
        return true;
    }

    public static boolean zzk(Class cls) {
        return zza.zzc(cls);
    }

    public static String[] zzl(Class cls) {
        return zza.zzd(cls);
    }

    private static String zzm(Exception exc) {
        if (!exc.getClass().getName().equals("java.lang.reflect.InaccessibleObjectException")) {
            return "";
        }
        String message = exc.getMessage();
        return "\nSee ".concat("https://github.com/google/gson/blob/main/Troubleshooting.md#".concat((message == null || !message.contains("to module com.google.gson")) ? "reflection-inaccessible" : "reflection-inaccessible-to-module-gson"));
    }

    private static void zzn(AccessibleObject accessibleObject, StringBuilder sb) {
        Class[] clsArr;
        sb.append('(');
        if (accessibleObject instanceof Method) {
            clsArr = ((Method) accessibleObject).getParameterTypes();
        } else {
            clsArr = ((Constructor) accessibleObject).getParameterTypes();
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(clsArr[i].getSimpleName());
        }
        sb.append(')');
    }
}
