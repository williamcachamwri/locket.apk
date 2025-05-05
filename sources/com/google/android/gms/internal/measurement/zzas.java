package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzas implements zzaq, Iterable<zzaq> {
    /* access modifiers changed from: private */
    public final String zza;

    public final int hashCode() {
        return this.zza.hashCode();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x019c, code lost:
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("indexOf", 2, r2);
        r3 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01aa, code lost:
        if (r24.size() > 0) goto L_0x01b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01ac, code lost:
        r4 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
        r5 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01b5, code lost:
        r5 = r23;
        r4 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01cb, code lost:
        if (r24.size() >= 2) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01ce, code lost:
        r0 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01f4, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r3.indexOf(r4, (int) com.google.android.gms.internal.measurement.zzg.zza(r0))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01f5, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("replace", 2, r2);
        r0 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x020b, code lost:
        if (r24.isEmpty() != false) goto L_0x022d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x020d, code lost:
        r0 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0221, code lost:
        if (r24.size() <= 1) goto L_0x022d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0223, code lost:
        r1 = r5.zza(r2.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x022d, code lost:
        r2 = r6.zza;
        r3 = r2.indexOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0233, code lost:
        if (r3 >= 0) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0235, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0238, code lost:
        if ((r1 instanceof com.google.android.gms.internal.measurement.zzal) == false) goto L_0x025f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x023a, code lost:
        r1 = ((com.google.android.gms.internal.measurement.zzal) r1).zza(r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzaq[]{new com.google.android.gms.internal.measurement.zzas(r0), new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r3)), r6}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x028b, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r2.substring(0, r3) + r1.zzf() + r2.substring(r3 + r0.length()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x028c, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("substring", 2, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x029c, code lost:
        if (r24.isEmpty() != false) goto L_0x02b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x029e, code lost:
        r1 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02b7, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02bd, code lost:
        if (r24.size() <= 1) goto L_0x02d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02bf, code lost:
        r2 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02d7, code lost:
        r2 = r0.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02db, code lost:
        r1 = java.lang.Math.min(java.lang.Math.max(r1, 0), r0.length());
        r2 = java.lang.Math.min(java.lang.Math.max(r2, 0), r0.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0305, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r0.substring(java.lang.Math.min(r1, r2), java.lang.Math.max(r1, r2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0306, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("split", 2, r2);
        r0 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0316, code lost:
        if (r0.length() != 0) goto L_0x0324;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0323, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0324, code lost:
        r1 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x032e, code lost:
        if (r24.isEmpty() == false) goto L_0x0335;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0330, code lost:
        r1.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0335, code lost:
        r3 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0348, code lost:
        if (r24.size() <= 1) goto L_0x0361;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x034a, code lost:
        r4 = com.google.android.gms.internal.measurement.zzg.zzc(r5.zza(r2.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0361, code lost:
        r4 = 2147483647L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0368, code lost:
        if (r4 != 0) goto L_0x0370;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x036f, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0370, code lost:
        r0 = r0.split(java.util.regex.Pattern.quote(r3), ((int) r4) + 1);
        r2 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0380, code lost:
        if (r3.isEmpty() == false) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0383, code lost:
        if (r0.length <= 0) goto L_0x039a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0385, code lost:
        r14 = r0[0].isEmpty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0395, code lost:
        if (r0[r0.length - 1].isEmpty() == false) goto L_0x039b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0397, code lost:
        r2 = r0.length - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x039a, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x039f, code lost:
        if (((long) r0.length) <= r4) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x03a1, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03a3, code lost:
        if (r14 >= r2) goto L_0x03b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03a5, code lost:
        r1.add(new com.google.android.gms.internal.measurement.zzas(r0[r14]));
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03b7, code lost:
        return new com.google.android.gms.internal.measurement.zzaf((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03b8, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("slice", 2, r2);
        r3 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03c8, code lost:
        if (r24.isEmpty() != false) goto L_0x03de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03ca, code lost:
        r7 = r5.zza(r2.get(0)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03de, code lost:
        r7 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03df, code lost:
        r7 = com.google.android.gms.internal.measurement.zzg.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03e5, code lost:
        if (r7 >= 0.0d) goto L_0x03f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03e7, code lost:
        r7 = java.lang.Math.max(((double) r3.length()) + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x03f2, code lost:
        r7 = java.lang.Math.min(r7, (double) r3.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x03fb, code lost:
        r4 = (int) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0401, code lost:
        if (r24.size() <= 1) goto L_0x0416;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0403, code lost:
        r7 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0416, code lost:
        r7 = (double) r3.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x041b, code lost:
        r7 = com.google.android.gms.internal.measurement.zzg.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0421, code lost:
        if (r7 >= 0.0d) goto L_0x042e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0423, code lost:
        r0 = java.lang.Math.max(((double) r3.length()) + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x042e, code lost:
        r0 = java.lang.Math.min(r7, (double) r3.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0448, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r3.substring(r4, java.lang.Math.max(0, ((int) r0) - r4) + r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0449, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("match", 1, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0459, code lost:
        if (r24.size() > 0) goto L_0x045e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x045b, code lost:
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x045e, code lost:
        r1 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x046d, code lost:
        r0 = java.util.regex.Pattern.compile(r1).matcher(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0479, code lost:
        if (r0.find() == false) goto L_0x0490;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x048f, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(new com.google.android.gms.internal.measurement.zzas(r0.group()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0492, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0493, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r5, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x04a6, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x04a7, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r5, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04bc, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x04bd, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("lastIndexOf", 2, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x04cd, code lost:
        if (r24.size() > 0) goto L_0x04d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x04cf, code lost:
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04d6, code lost:
        r1 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x04ea, code lost:
        if (r24.size() >= 2) goto L_0x04ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x04ec, code lost:
        r2 = Double.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x04ef, code lost:
        r2 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0506, code lost:
        if (java.lang.Double.isNaN(r2) == false) goto L_0x050b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0508, code lost:
        r2 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x050b, code lost:
        r2 = com.google.android.gms.internal.measurement.zzg.zza(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x051e, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0.lastIndexOf(r1, (int) r2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x051f, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r14, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0532, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0533, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("search", 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0542, code lost:
        if (r24.isEmpty() != false) goto L_0x0553;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0544, code lost:
        r0 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0553, code lost:
        r0 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0559, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r6.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0567, code lost:
        if (r0.find() == false) goto L_0x0578;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0577, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0.start()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0583, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0584, code lost:
        com.google.android.gms.internal.measurement.zzg.zza("toLowerCase", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x059a, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x059b, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x05a5, code lost:
        if (r24.isEmpty() == false) goto L_0x05a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x05a7, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x05a8, code lost:
        r0 = new java.lang.StringBuilder(r6.zza);
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x05b4, code lost:
        if (r14 >= r24.size()) goto L_0x05ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x05b6, code lost:
        r0.append(r5.zza(r2.get(r14)).zzf());
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x05d3, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x05d4, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(r4, 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x05e2, code lost:
        if (r24.isEmpty() != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x05e4, code lost:
        r14 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x05fd, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x05fe, code lost:
        r0 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0600, code lost:
        if (r14 < 0) goto L_0x0617;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x0606, code lost:
        if (r14 < r0.length()) goto L_0x0609;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0616, code lost:
        return new com.google.android.gms.internal.measurement.zzas(java.lang.String.valueOf(r0.charAt(r14)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0619, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzj;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x061a, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r17, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x062f, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0630, code lost:
        r0 = r6;
        r6 = r21;
        com.google.android.gms.internal.measurement.zzg.zza(r0, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0639, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x063a, code lost:
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zza(r3, 1, r2);
        r0 = r21.zza;
        r1 = r23.zza(r2.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x065b, code lost:
        if (io.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH.equals(r1.zzf()) == false) goto L_0x0660;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x065f, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0660, code lost:
        r1 = r1.zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x066e, code lost:
        if (r1 != java.lang.Math.floor(r1)) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x0670, code lost:
        r1 = (int) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0671, code lost:
        if (r1 < 0) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0677, code lost:
        if (r1 >= r0.length()) goto L_0x067c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x067b, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x067e, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b6, code lost:
        r4 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b8, code lost:
        r3 = r17;
        r6 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bc, code lost:
        r17 = "toLocaleLowerCase";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0145, code lost:
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x018f, code lost:
        r0 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0191, code lost:
        switch(r20) {
            case 0: goto L_0x063a;
            case 1: goto L_0x0630;
            case 2: goto L_0x061a;
            case 3: goto L_0x05d4;
            case 4: goto L_0x059b;
            case 5: goto L_0x0584;
            case 6: goto L_0x0533;
            case 7: goto L_0x051f;
            case 8: goto L_0x04bd;
            case 9: goto L_0x04a7;
            case 10: goto L_0x0493;
            case 11: goto L_0x0449;
            case 12: goto L_0x03b8;
            case 13: goto L_0x0306;
            case 14: goto L_0x028c;
            case 15: goto L_0x01f5;
            case 16: goto L_0x019c;
            default: goto L_0x0194;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x019b, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x067f  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r4 = "charAt"
            boolean r5 = r4.equals(r1)
            java.lang.String r6 = "concat"
            java.lang.String r7 = "indexOf"
            java.lang.String r8 = "replace"
            java.lang.String r9 = "substring"
            java.lang.String r10 = "split"
            java.lang.String r11 = "slice"
            java.lang.String r12 = "match"
            java.lang.String r13 = "lastIndexOf"
            java.lang.String r14 = "toLocaleUpperCase"
            java.lang.String r15 = "search"
            java.lang.String r2 = "toLowerCase"
            java.lang.String r0 = "toLocaleLowerCase"
            java.lang.String r3 = "toString"
            r16 = r4
            java.lang.String r4 = "hasOwnProperty"
            r17 = r14
            java.lang.String r14 = "toUpperCase"
            r18 = r14
            if (r5 != 0) goto L_0x009f
            boolean r5 = r6.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r4.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r7.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r13.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r12.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r8.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r15.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r11.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r10.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r9.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r2.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r0.equals(r1)
            if (r5 != 0) goto L_0x009f
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto L_0x009f
            r5 = r18
            boolean r18 = r5.equals(r1)
            r14 = r17
            if (r18 != 0) goto L_0x00a3
            boolean r17 = r14.equals(r1)
            if (r17 != 0) goto L_0x00a3
            r17 = r4
            java.lang.String r4 = "trim"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x009d
            goto L_0x00a5
        L_0x009d:
            r4 = 0
            goto L_0x00a6
        L_0x009f:
            r14 = r17
            r5 = r18
        L_0x00a3:
            r17 = r4
        L_0x00a5:
            r4 = 1
        L_0x00a6:
            if (r4 == 0) goto L_0x067f
            r22.hashCode()
            int r4 = r22.hashCode()
            r19 = r3
            r20 = -1
            switch(r4) {
                case -1789698943: goto L_0x017e;
                case -1776922004: goto L_0x016c;
                case -1464939364: goto L_0x015a;
                case -1361633751: goto L_0x0149;
                case -1354795244: goto L_0x013c;
                case -1137582698: goto L_0x0132;
                case -906336856: goto L_0x0129;
                case -726908483: goto L_0x0120;
                case -467511597: goto L_0x0116;
                case -399551817: goto L_0x010c;
                case 3568674: goto L_0x0100;
                case 103668165: goto L_0x00f6;
                case 109526418: goto L_0x00ec;
                case 109648666: goto L_0x00e1;
                case 530542161: goto L_0x00d6;
                case 1094496948: goto L_0x00cb;
                case 1943291465: goto L_0x00c0;
                default: goto L_0x00b6;
            }
        L_0x00b6:
            r4 = r16
        L_0x00b8:
            r3 = r17
            r6 = r19
        L_0x00bc:
            r17 = r0
            goto L_0x018f
        L_0x00c0:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00c7
            goto L_0x00b6
        L_0x00c7:
            r1 = 16
            goto L_0x0145
        L_0x00cb:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00d2
            goto L_0x00b6
        L_0x00d2:
            r1 = 15
            goto L_0x0145
        L_0x00d6:
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x00dd
            goto L_0x00b6
        L_0x00dd:
            r1 = 14
            goto L_0x0145
        L_0x00e1:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x00e8
            goto L_0x00b6
        L_0x00e8:
            r1 = 13
            goto L_0x0145
        L_0x00ec:
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x00f3
            goto L_0x00b6
        L_0x00f3:
            r1 = 12
            goto L_0x0145
        L_0x00f6:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L_0x00fd
            goto L_0x00b6
        L_0x00fd:
            r1 = 11
            goto L_0x0145
        L_0x0100:
            java.lang.String r4 = "trim"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0109
            goto L_0x00b6
        L_0x0109:
            r1 = 10
            goto L_0x0145
        L_0x010c:
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x0113
            goto L_0x00b6
        L_0x0113:
            r1 = 9
            goto L_0x0145
        L_0x0116:
            boolean r1 = r1.equals(r13)
            if (r1 != 0) goto L_0x011d
            goto L_0x00b6
        L_0x011d:
            r1 = 8
            goto L_0x0145
        L_0x0120:
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0127
            goto L_0x00b6
        L_0x0127:
            r1 = 7
            goto L_0x0145
        L_0x0129:
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x0130
            goto L_0x00b6
        L_0x0130:
            r1 = 6
            goto L_0x0145
        L_0x0132:
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x013a
            goto L_0x00b6
        L_0x013a:
            r1 = 5
            goto L_0x0145
        L_0x013c:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0144
            goto L_0x00b6
        L_0x0144:
            r1 = 4
        L_0x0145:
            r20 = r1
            goto L_0x00b6
        L_0x0149:
            r4 = r16
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0152
            goto L_0x0162
        L_0x0152:
            r3 = r17
            r6 = r19
            r20 = 3
            goto L_0x00bc
        L_0x015a:
            r4 = r16
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0164
        L_0x0162:
            goto L_0x00b8
        L_0x0164:
            r3 = r17
            r6 = r19
            r20 = 2
            goto L_0x00bc
        L_0x016c:
            r4 = r16
            r6 = r19
            boolean r1 = r1.equals(r6)
            r3 = r17
            if (r1 != 0) goto L_0x017a
            goto L_0x00bc
        L_0x017a:
            r20 = 1
            goto L_0x00bc
        L_0x017e:
            r4 = r16
            r3 = r17
            r6 = r19
            boolean r1 = r1.equals(r3)
            r17 = r0
            if (r1 != 0) goto L_0x018d
            goto L_0x018f
        L_0x018d:
            r20 = 0
        L_0x018f:
            r0 = 0
            switch(r20) {
                case 0: goto L_0x063a;
                case 1: goto L_0x0630;
                case 2: goto L_0x061a;
                case 3: goto L_0x05d4;
                case 4: goto L_0x059b;
                case 5: goto L_0x0584;
                case 6: goto L_0x0533;
                case 7: goto L_0x051f;
                case 8: goto L_0x04bd;
                case 9: goto L_0x04a7;
                case 10: goto L_0x0493;
                case 11: goto L_0x0449;
                case 12: goto L_0x03b8;
                case 13: goto L_0x0306;
                case 14: goto L_0x028c;
                case 15: goto L_0x01f5;
                case 16: goto L_0x019c;
                default: goto L_0x0194;
            }
        L_0x0194:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x019c:
            r2 = r24
            r3 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r7, r3, r2)
            r6 = r21
            java.lang.String r3 = r6.zza
            int r4 = r24.size()
            if (r4 > 0) goto L_0x01b5
            com.google.android.gms.internal.measurement.zzaq r4 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r4 = r4.zzf()
            r5 = r23
            goto L_0x01c6
        L_0x01b5:
            r4 = 0
            java.lang.Object r4 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            r5 = r23
            com.google.android.gms.internal.measurement.zzaq r4 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.String r4 = r4.zzf()
        L_0x01c6:
            int r7 = r24.size()
            r8 = 2
            if (r7 >= r8) goto L_0x01ce
            goto L_0x01e1
        L_0x01ce:
            r0 = 1
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
        L_0x01e1:
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            com.google.android.gms.internal.measurement.zzai r2 = new com.google.android.gms.internal.measurement.zzai
            int r0 = (int) r0
            int r0 = r3.indexOf(r4, r0)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r2.<init>(r0)
            return r2
        L_0x01f5:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r8, r0, r2)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r0 = r0.zzf()
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r3 = r24.isEmpty()
            if (r3 != 0) goto L_0x022d
            r3 = 0
            java.lang.Object r0 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
            int r3 = r24.size()
            r4 = 1
            if (r3 <= r4) goto L_0x022d
            java.lang.Object r1 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
        L_0x022d:
            java.lang.String r2 = r6.zza
            int r3 = r2.indexOf(r0)
            if (r3 >= 0) goto L_0x0236
            return r6
        L_0x0236:
            boolean r4 = r1 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x025f
            com.google.android.gms.internal.measurement.zzal r1 = (com.google.android.gms.internal.measurement.zzal) r1
            r4 = 3
            com.google.android.gms.internal.measurement.zzaq[] r4 = new com.google.android.gms.internal.measurement.zzaq[r4]
            com.google.android.gms.internal.measurement.zzas r7 = new com.google.android.gms.internal.measurement.zzas
            r7.<init>(r0)
            r8 = 0
            r4[r8] = r7
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r3
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r7.<init>(r8)
            r8 = 1
            r4[r8] = r7
            r7 = 2
            r4[r7] = r6
            java.util.List r4 = java.util.Arrays.asList(r4)
            com.google.android.gms.internal.measurement.zzaq r1 = r1.zza((com.google.android.gms.internal.measurement.zzh) r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r4)
        L_0x025f:
            com.google.android.gms.internal.measurement.zzas r4 = new com.google.android.gms.internal.measurement.zzas
            r5 = 0
            java.lang.String r5 = r2.substring(r5, r3)
            java.lang.String r1 = r1.zzf()
            int r0 = r0.length()
            int r3 = r3 + r0
            java.lang.String r0 = r2.substring(r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            return r4
        L_0x028c:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r9, r0, r2)
            java.lang.String r0 = r6.zza
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x02b7
            r1 = 0
            java.lang.Object r3 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r3 = (com.google.android.gms.internal.measurement.zzaq) r3
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            java.lang.Double r1 = r1.zze()
            double r3 = r1.doubleValue()
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r3)
            int r1 = (int) r3
            goto L_0x02b8
        L_0x02b7:
            r1 = 0
        L_0x02b8:
            int r3 = r24.size()
            r4 = 1
            if (r3 <= r4) goto L_0x02d7
            java.lang.Object r2 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r2 = r2.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
            int r2 = (int) r2
            goto L_0x02db
        L_0x02d7:
            int r2 = r0.length()
        L_0x02db:
            r3 = 0
            int r1 = java.lang.Math.max(r1, r3)
            int r4 = r0.length()
            int r1 = java.lang.Math.min(r1, r4)
            int r2 = java.lang.Math.max(r2, r3)
            int r3 = r0.length()
            int r2 = java.lang.Math.min(r2, r3)
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            int r4 = java.lang.Math.min(r1, r2)
            int r1 = java.lang.Math.max(r1, r2)
            java.lang.String r0 = r0.substring(r4, r1)
            r3.<init>(r0)
            return r3
        L_0x0306:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r10, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r0.length()
            if (r1 != 0) goto L_0x0324
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r1 = 1
            com.google.android.gms.internal.measurement.zzaq[] r1 = new com.google.android.gms.internal.measurement.zzaq[r1]
            r3 = 0
            r1[r3] = r6
            r0.<init>((com.google.android.gms.internal.measurement.zzaq[]) r1)
            return r0
        L_0x0324:
            r3 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r4 = r24.isEmpty()
            if (r4 == 0) goto L_0x0335
            r1.add(r6)
            goto L_0x03b2
        L_0x0335:
            java.lang.Object r4 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r3 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.String r3 = r3.zzf()
            int r4 = r24.size()
            r7 = 1
            if (r4 <= r7) goto L_0x0361
            java.lang.Object r2 = r2.get(r7)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r4 = r2.doubleValue()
            long r4 = com.google.android.gms.internal.measurement.zzg.zzc(r4)
            goto L_0x0364
        L_0x0361:
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x0364:
            r7 = 0
            int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0370
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x0370:
            java.lang.String r2 = java.util.regex.Pattern.quote(r3)
            int r7 = (int) r4
            r8 = 1
            int r7 = r7 + r8
            java.lang.String[] r0 = r0.split(r2, r7)
            int r2 = r0.length
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x039a
            int r3 = r0.length
            if (r3 <= 0) goto L_0x039a
            r3 = 0
            r3 = r0[r3]
            boolean r14 = r3.isEmpty()
            int r3 = r0.length
            r7 = 1
            int r3 = r3 - r7
            r3 = r0[r3]
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x039b
            int r2 = r0.length
            int r2 = r2 - r7
            goto L_0x039b
        L_0x039a:
            r14 = 0
        L_0x039b:
            int r3 = r0.length
            long r7 = (long) r3
            int r3 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x03a3
            int r2 = r2 + -1
        L_0x03a3:
            if (r14 >= r2) goto L_0x03b2
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            r4 = r0[r14]
            r3.<init>(r4)
            r1.add(r3)
            int r14 = r14 + 1
            goto L_0x03a3
        L_0x03b2:
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r1)
            return r0
        L_0x03b8:
            r6 = r21
            r5 = r23
            r2 = r24
            r3 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r11, r3, r2)
            java.lang.String r3 = r6.zza
            boolean r4 = r24.isEmpty()
            if (r4 != 0) goto L_0x03de
            r4 = 0
            java.lang.Object r7 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r7 = (com.google.android.gms.internal.measurement.zzaq) r7
            com.google.android.gms.internal.measurement.zzaq r4 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r7)
            java.lang.Double r4 = r4.zze()
            double r7 = r4.doubleValue()
            goto L_0x03df
        L_0x03de:
            r7 = r0
        L_0x03df:
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x03f2
            int r4 = r3.length()
            double r9 = (double) r4
            double r9 = r9 + r7
            double r7 = java.lang.Math.max(r9, r0)
            goto L_0x03fb
        L_0x03f2:
            int r4 = r3.length()
            double r9 = (double) r4
            double r7 = java.lang.Math.min(r7, r9)
        L_0x03fb:
            int r4 = (int) r7
            int r7 = r24.size()
            r8 = 1
            if (r7 <= r8) goto L_0x0416
            java.lang.Object r2 = r2.get(r8)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r7 = r2.doubleValue()
            goto L_0x041b
        L_0x0416:
            int r2 = r3.length()
            double r7 = (double) r2
        L_0x041b:
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x042e
            int r2 = r3.length()
            double r9 = (double) r2
            double r9 = r9 + r7
            double r0 = java.lang.Math.max(r9, r0)
            goto L_0x0437
        L_0x042e:
            int r0 = r3.length()
            double r0 = (double) r0
            double r0 = java.lang.Math.min(r7, r0)
        L_0x0437:
            int r0 = (int) r0
            int r0 = r0 - r4
            r1 = 0
            int r0 = java.lang.Math.max(r1, r0)
            int r0 = r0 + r4
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r3.substring(r4, r0)
            r1.<init>(r0)
            return r1
        L_0x0449:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r12, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r24.size()
            if (r1 > 0) goto L_0x045e
            java.lang.String r1 = ""
            goto L_0x046d
        L_0x045e:
            r1 = 0
            java.lang.Object r2 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.String r1 = r1.zzf()
        L_0x046d:
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0490
            com.google.android.gms.internal.measurement.zzaf r1 = new com.google.android.gms.internal.measurement.zzaf
            r2 = 1
            com.google.android.gms.internal.measurement.zzaq[] r2 = new com.google.android.gms.internal.measurement.zzaq[r2]
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.group()
            r3.<init>(r0)
            r0 = 0
            r2[r0] = r3
            r1.<init>((com.google.android.gms.internal.measurement.zzaq[]) r2)
            return r1
        L_0x0490:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzd
            return r0
        L_0x0493:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.trim()
            r1.<init>(r0)
            return r1
        L_0x04a7:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r2)
            r1.<init>(r0)
            return r1
        L_0x04bd:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r13, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r24.size()
            if (r1 > 0) goto L_0x04d6
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r1 = r1.zzf()
            goto L_0x04e5
        L_0x04d6:
            r1 = 0
            java.lang.Object r1 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
        L_0x04e5:
            int r3 = r24.size()
            r4 = 2
            if (r3 >= r4) goto L_0x04ef
            r2 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x0502
        L_0x04ef:
            r3 = 1
            java.lang.Object r2 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r2 = r2.doubleValue()
        L_0x0502:
            boolean r4 = java.lang.Double.isNaN(r2)
            if (r4 == 0) goto L_0x050b
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x050f
        L_0x050b:
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
        L_0x050f:
            com.google.android.gms.internal.measurement.zzai r4 = new com.google.android.gms.internal.measurement.zzai
            int r2 = (int) r2
            int r0 = r0.lastIndexOf(r1, r2)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r4.<init>(r0)
            return r4
        L_0x051f:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r14, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toUpperCase()
            r1.<init>(r0)
            return r1
        L_0x0533:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 0
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r15, r1, r2)
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x0553
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
            goto L_0x0559
        L_0x0553:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r0 = r0.zzf()
        L_0x0559:
            java.lang.String r1 = r6.zza
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0578
            com.google.android.gms.internal.measurement.zzai r1 = new com.google.android.gms.internal.measurement.zzai
            int r0 = r0.start()
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            return r1
        L_0x0578:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0584:
            r1 = 0
            r6 = r21
            r0 = r2
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            r1.<init>(r0)
            return r1
        L_0x059b:
            r6 = r21
            r5 = r23
            r2 = r24
            boolean r0 = r24.isEmpty()
            if (r0 == 0) goto L_0x05a8
            return r6
        L_0x05a8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = r6.zza
            r0.<init>(r1)
            r14 = 0
        L_0x05b0:
            int r1 = r24.size()
            if (r14 >= r1) goto L_0x05ca
            java.lang.Object r1 = r2.get(r14)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
            r0.append(r1)
            int r14 = r14 + 1
            goto L_0x05b0
        L_0x05ca:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            return r1
        L_0x05d4:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r4, r0, r2)
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x05fd
            r0 = 0
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            int r14 = (int) r0
            goto L_0x05fe
        L_0x05fd:
            r14 = 0
        L_0x05fe:
            java.lang.String r0 = r6.zza
            if (r14 < 0) goto L_0x0617
            int r1 = r0.length()
            if (r14 < r1) goto L_0x0609
            goto L_0x0617
        L_0x0609:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            char r0 = r0.charAt(r14)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            return r1
        L_0x0617:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzj
            return r0
        L_0x061a:
            r1 = 0
            r6 = r21
            r2 = r24
            r0 = r17
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toLowerCase()
            r1.<init>(r0)
            return r1
        L_0x0630:
            r1 = 0
            r2 = r24
            r0 = r6
            r6 = r21
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            return r6
        L_0x063a:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r3, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            java.lang.Object r1 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r2 = "length"
            java.lang.String r3 = r1.zzf()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0660
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzh
            return r0
        L_0x0660:
            java.lang.Double r1 = r1.zze()
            double r1 = r1.doubleValue()
            double r3 = java.lang.Math.floor(r1)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x067c
            int r1 = (int) r1
            if (r1 < 0) goto L_0x067c
            int r0 = r0.length()
            if (r1 >= r0) goto L_0x067c
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzh
            return r0
        L_0x067c:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzi
            return r0
        L_0x067f:
            r6 = r21
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "%s is not a String function"
            java.lang.Object[] r1 = new java.lang.Object[]{r22}
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzas.zza(java.lang.String, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }

    public final zzaq zzc() {
        return new zzas(this.zza);
    }

    public final Boolean zzd() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    public final Double zze() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public final String zzf() {
        return this.zza;
    }

    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    public final Iterator<zzaq> zzh() {
        return new zzav(this);
    }

    public final Iterator<zzaq> iterator() {
        return new zzau(this);
    }

    public zzas(String str) {
        if (str != null) {
            this.zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzas)) {
            return false;
        }
        return this.zza.equals(((zzas) obj).zza);
    }
}
