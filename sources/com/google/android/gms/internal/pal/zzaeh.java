package com.google.android.gms.internal.pal;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaeh {
    static String zza(zzaef zzaef, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzaef, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zzb : (List) obj) {
                zzb(sb, i, str, zzb);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zzb2 : ((Map) obj).entrySet()) {
                zzb(sb, i, str, zzb2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzafg.zza(zzaby.zzp((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzaby) {
                sb.append(": \"");
                sb.append(zzafg.zza((zzaby) obj));
                sb.append('\"');
            } else if (obj instanceof zzacz) {
                sb.append(" {");
                zzd((zzacz) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i4 = i + 2;
                zzb(sb, i4, "key", entry.getKey());
                zzb(sb, i4, "value", entry.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static final String zzc(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zzd(zzaef zzaef, StringBuilder sb, int i) {
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzaef.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String substring = str.startsWith("get") ? str.substring(3) : str;
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List")) {
                String concat = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 4)));
                Method method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzc(concat), zzacz.zzaD(method2, zzaef, new Object[0]));
                }
            }
            if (substring.endsWith("Map") && !substring.equals("Map")) {
                String concat2 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1, substring.length() - 3)));
                Method method3 = (Method) hashMap.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzc(concat2), zzacz.zzaD(method3, zzaef, new Object[0]));
                }
            }
            if (((Method) hashMap2.get("set".concat(String.valueOf(substring)))) != null && (!substring.endsWith("Bytes") || !hashMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                String concat3 = String.valueOf(substring.substring(0, 1).toLowerCase()).concat(String.valueOf(substring.substring(1)));
                Method method4 = (Method) hashMap.get("get".concat(String.valueOf(substring)));
                Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                if (method4 != null) {
                    Object zzaD = zzacz.zzaD(method4, zzaef, new Object[0]);
                    if (method5 == null) {
                        if (zzaD instanceof Boolean) {
                            if (!((Boolean) zzaD).booleanValue()) {
                            }
                        } else if (zzaD instanceof Integer) {
                            if (((Integer) zzaD).intValue() == 0) {
                            }
                        } else if (zzaD instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzaD).floatValue()) == 0) {
                            }
                        } else if (!(zzaD instanceof Double)) {
                            if (zzaD instanceof String) {
                                z = zzaD.equals("");
                            } else if (zzaD instanceof zzaby) {
                                z = zzaD.equals(zzaby.zzb);
                            } else if (zzaD instanceof zzaef) {
                                if (zzaD == ((zzaef) zzaD).zzaq()) {
                                }
                            } else if ((zzaD instanceof Enum) && ((Enum) zzaD).ordinal() == 0) {
                            }
                            if (z) {
                            }
                        } else if (Double.doubleToRawLongBits(((Double) zzaD).doubleValue()) == 0) {
                        }
                    } else if (!((Boolean) zzacz.zzaD(method5, zzaef, new Object[0])).booleanValue()) {
                    }
                    zzb(sb, i, zzc(concat3), zzaD);
                }
            }
        }
        if (!(zzaef instanceof zzacw)) {
            zzafj zzafj = ((zzacz) zzaef).zzc;
            if (zzafj != null) {
                zzafj.zzg(sb, i);
                return;
            }
            return;
        }
        zzacr zzacr = ((zzacw) zzaef).zzb;
        throw null;
    }
}
