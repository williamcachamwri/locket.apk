package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfq {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    static String zza(zzfo zzfo, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzfo, sb, 0);
        return sb.toString();
    }

    static void zzb(StringBuilder sb, int i, String str, Object obj) {
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
            zzc(i, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i2 = 1; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzgw.zza(new zzcy(((String) obj).getBytes(zzem.zzb))));
                sb.append('\"');
            } else if (obj instanceof zzdb) {
                sb.append(": \"");
                sb.append(zzgw.zza((zzdb) obj));
                sb.append('\"');
            } else if (obj instanceof zzed) {
                sb.append(" {");
                zzd((zzed) obj, sb, i + 2);
                sb.append("\n");
                zzc(i, sb);
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i3 = i + 2;
                zzb(sb, i3, "key", entry.getKey());
                zzb(sb, i3, "value", entry.getValue());
                sb.append("\n");
                zzc(i, sb);
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    private static void zzd(zzfo zzfo, StringBuilder sb, int i) {
        int i2;
        boolean z;
        Method method;
        Method method2;
        zzfo zzfo2 = zzfo;
        StringBuilder sb2 = sb;
        int i3 = i;
        HashSet hashSet = new HashSet();
        HashMap hashMap = new HashMap();
        TreeMap treeMap = new TreeMap();
        Method[] declaredMethods = zzfo.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i2 = 3;
            if (i4 >= length) {
                break;
            }
            Method method3 = declaredMethods[i4];
            if (!Modifier.isStatic(method3.getModifiers()) && method3.getName().length() >= 3) {
                if (method3.getName().startsWith("set")) {
                    hashSet.add(method3.getName());
                } else if (Modifier.isPublic(method3.getModifiers()) && method3.getParameterTypes().length == 0) {
                    if (method3.getName().startsWith("has")) {
                        hashMap.put(method3.getName(), method3);
                    } else if (method3.getName().startsWith("get")) {
                        treeMap.put(method3.getName(), method3);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String substring = ((String) entry.getKey()).substring(i2);
            if (substring.endsWith("List") && !substring.endsWith("OrBuilderList") && !substring.equals("List") && (method2 = (Method) entry.getValue()) != null && method2.getReturnType().equals(List.class)) {
                zzb(sb2, i3, substring.substring(0, substring.length() - 4), zzed.zzQ(method2, zzfo2, new Object[0]));
            } else if (substring.endsWith("Map") && !substring.equals("Map") && (method = (Method) entry.getValue()) != null && method.getReturnType().equals(Map.class) && !method.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method.getModifiers())) {
                zzb(sb2, i3, substring.substring(0, substring.length() - 3), zzed.zzQ(method, zzfo2, new Object[0]));
            } else if (hashSet.contains("set".concat(String.valueOf(substring))) && (!substring.endsWith("Bytes") || !treeMap.containsKey("get".concat(String.valueOf(substring.substring(0, substring.length() - 5)))))) {
                Method method4 = (Method) entry.getValue();
                Method method5 = (Method) hashMap.get("has".concat(String.valueOf(substring)));
                if (method4 != null) {
                    Object zzQ = zzed.zzQ(method4, zzfo2, new Object[0]);
                    if (method5 == null) {
                        if (zzQ instanceof Boolean) {
                            if (!((Boolean) zzQ).booleanValue()) {
                            }
                        } else if (zzQ instanceof Integer) {
                            if (((Integer) zzQ).intValue() == 0) {
                            }
                        } else if (zzQ instanceof Float) {
                            if (Float.floatToRawIntBits(((Float) zzQ).floatValue()) == 0) {
                            }
                        } else if (!(zzQ instanceof Double)) {
                            if (zzQ instanceof String) {
                                z = zzQ.equals("");
                            } else if (zzQ instanceof zzdb) {
                                z = zzQ.equals(zzdb.zzb);
                            } else if (zzQ instanceof zzfo) {
                                if (zzQ == ((zzfo) zzQ).zzab()) {
                                }
                            } else if ((zzQ instanceof Enum) && ((Enum) zzQ).ordinal() == 0) {
                            }
                            if (z) {
                            }
                        } else if (Double.doubleToRawLongBits(((Double) zzQ).doubleValue()) == 0) {
                        }
                    } else if (!((Boolean) zzed.zzQ(method5, zzfo2, new Object[0])).booleanValue()) {
                    }
                    zzb(sb2, i3, substring, zzQ);
                }
            }
            i2 = 3;
        }
        if (zzfo2 instanceof zzdz) {
            Iterator zzf = ((zzdz) zzfo2).zza.zzf();
            while (zzf.hasNext()) {
                Map.Entry entry2 = (Map.Entry) zzf.next();
                zzb(sb2, i3, "[" + ((zzea) entry2.getKey()).zza + "]", entry2.getValue());
            }
        }
        zzgz zzgz = ((zzed) zzfo2).zzc;
        if (zzgz != null) {
            zzgz.zzi(sb2, i3);
        }
    }
}
