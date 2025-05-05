package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzjz implements zzkn {
    private static final Charset zza = Charset.forName("UTF-8");
    private final InputStream zzb;

    private zzjz(InputStream inputStream) {
        this.zzb = inputStream;
    }

    public static zzkn zza(InputStream inputStream) throws IOException {
        return new zzjz(inputStream);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0207 A[Catch:{ zzzc | IllegalStateException -> 0x022f, all -> 0x022d }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e6 A[Catch:{ zzzc | IllegalStateException -> 0x022f, all -> 0x022d }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012d A[FALL_THROUGH] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0150 A[Catch:{ zzzc | IllegalStateException -> 0x022f, all -> 0x022d }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x016a A[Catch:{ zzzc | IllegalStateException -> 0x022f, all -> 0x022d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.pal.zzwb zzb() throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r0 = "keyMaterialType"
            java.lang.String r2 = "value"
            java.lang.String r3 = "typeUrl"
            java.lang.String r4 = "outputPrefixType"
            java.lang.String r5 = "keyId"
            java.lang.String r6 = "status"
            java.lang.String r7 = "keyData"
            java.lang.String r8 = "primaryKeyId"
            java.lang.String r9 = "key"
            com.google.android.gms.internal.pal.zzabc r10 = new com.google.android.gms.internal.pal.zzabc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.io.StringReader r11 = new java.io.StringReader     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r12 = new java.lang.String     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.io.InputStream r13 = r1.zzb     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            byte[] r13 = com.google.android.gms.internal.pal.zzlh.zzc(r13)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.nio.charset.Charset r14 = zza     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r12.<init>(r13, r14)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r11.<init>(r12)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r10.<init>(r11)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r10 = com.google.android.gms.internal.pal.zzzs.zza(r10)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzzb r10 = r10.zzf()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            boolean r11 = r10.zzi(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r11 == 0) goto L_0x0225
            com.google.android.gms.internal.pal.zzyx r11 = r10.zzb(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r11 = r11.zzb()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r11 == 0) goto L_0x0225
            com.google.android.gms.internal.pal.zzvy r11 = com.google.android.gms.internal.pal.zzwb.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            boolean r12 = r10.zzi(r8)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r12 == 0) goto L_0x0058
            com.google.android.gms.internal.pal.zzyy r8 = r10.zzc(r8)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r8 = r8.zza()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r11.zzb(r8)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x0058:
            com.google.android.gms.internal.pal.zzyx r8 = r10.zzb(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r10 = 0
        L_0x005d:
            int r12 = r8.zzb()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r10 >= r12) goto L_0x0217
            com.google.android.gms.internal.pal.zzyy r12 = r8.zzc(r10)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzzb r12 = r12.zzf()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            boolean r13 = r12.zzi(r7)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r13 == 0) goto L_0x020f
            boolean r13 = r12.zzi(r6)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r13 == 0) goto L_0x020f
            boolean r13 = r12.zzi(r5)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r13 == 0) goto L_0x020f
            boolean r13 = r12.zzi(r4)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r13 == 0) goto L_0x020f
            com.google.android.gms.internal.pal.zzvz r13 = com.google.android.gms.internal.pal.zzwa.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r14 = r12.zzc(r6)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r14 = r14.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r15 = r14.hashCode()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r9 = -891611359(0xffffffffcadb1721, float:-7179152.5)
            r16 = -1
            r17 = r6
            r6 = 1
            if (r15 == r9) goto L_0x00bc
            r9 = 478389753(0x1c83a5f9, float:8.711756E-22)
            if (r15 == r9) goto L_0x00b2
            r9 = 1053567612(0x3ecc2a7c, float:0.39876163)
            if (r15 == r9) goto L_0x00a8
            goto L_0x00c6
        L_0x00a8:
            java.lang.String r9 = "DISABLED"
            boolean r9 = r14.equals(r9)
            if (r9 == 0) goto L_0x00c6
            r9 = r6
            goto L_0x00c8
        L_0x00b2:
            java.lang.String r9 = "DESTROYED"
            boolean r9 = r14.equals(r9)
            if (r9 == 0) goto L_0x00c6
            r9 = 2
            goto L_0x00c8
        L_0x00bc:
            java.lang.String r9 = "ENABLED"
            boolean r9 = r14.equals(r9)
            if (r9 == 0) goto L_0x00c6
            r9 = 0
            goto L_0x00c8
        L_0x00c6:
            r9 = r16
        L_0x00c8:
            r18 = 4
            if (r9 == 0) goto L_0x00e6
            if (r9 == r6) goto L_0x00e3
            r15 = 2
            if (r9 != r15) goto L_0x00d3
            r9 = 5
            goto L_0x00e7
        L_0x00d3:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "unknown status: "
            java.lang.String r3 = java.lang.String.valueOf(r14)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = r2.concat(r3)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x00e3:
            r9 = r18
            goto L_0x00e7
        L_0x00e6:
            r9 = 3
        L_0x00e7:
            r13.zzd(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r9 = r12.zzc(r5)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r9 = r9.zza()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r13.zzb(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r9 = r12.zzc(r4)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r9 = r9.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r14 = r9.hashCode()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            switch(r14) {
                case -2053249079: goto L_0x0123;
                case 80904: goto L_0x0119;
                case 2575090: goto L_0x010f;
                case 1761684556: goto L_0x0105;
                default: goto L_0x0104;
            }
        L_0x0104:
            goto L_0x012d
        L_0x0105:
            java.lang.String r14 = "CRUNCHY"
            boolean r14 = r9.equals(r14)
            if (r14 == 0) goto L_0x012d
            r14 = 3
            goto L_0x012f
        L_0x010f:
            java.lang.String r14 = "TINK"
            boolean r14 = r9.equals(r14)
            if (r14 == 0) goto L_0x012d
            r14 = 0
            goto L_0x012f
        L_0x0119:
            java.lang.String r14 = "RAW"
            boolean r14 = r9.equals(r14)
            if (r14 == 0) goto L_0x012d
            r14 = r6
            goto L_0x012f
        L_0x0123:
            java.lang.String r14 = "LEGACY"
            boolean r14 = r9.equals(r14)
            if (r14 == 0) goto L_0x012d
            r14 = 2
            goto L_0x012f
        L_0x012d:
            r14 = r16
        L_0x012f:
            if (r14 == 0) goto L_0x0150
            if (r14 == r6) goto L_0x014e
            r15 = 2
            if (r14 == r15) goto L_0x014b
            r15 = 3
            if (r14 != r15) goto L_0x013b
            r15 = 6
            goto L_0x0151
        L_0x013b:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "unknown output prefix type: "
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = r2.concat(r3)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x014b:
            r15 = r18
            goto L_0x0151
        L_0x014e:
            r15 = 5
            goto L_0x0151
        L_0x0150:
            r15 = 3
        L_0x0151:
            r13.zzc(r15)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzzb r9 = r12.zze(r7)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            boolean r12 = r9.zzi(r3)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r12 == 0) goto L_0x0207
            boolean r12 = r9.zzi(r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r12 == 0) goto L_0x0207
            boolean r12 = r9.zzi(r0)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            if (r12 == 0) goto L_0x0207
            com.google.android.gms.internal.pal.zzyy r12 = r9.zzc(r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r12 = r12.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r14 = 2
            byte[] r12 = com.google.android.gms.internal.pal.zzxn.zza(r12, r14)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzvl r14 = com.google.android.gms.internal.pal.zzvo.zza()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r15 = r9.zzc(r3)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r15 = r15.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r14.zzb(r15)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzaby r12 = com.google.android.gms.internal.pal.zzaby.zzn(r12)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r14.zzc(r12)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzyy r9 = r9.zzc(r0)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r9 = r9.zzd()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r12 = r9.hashCode()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            switch(r12) {
                case -1881281466: goto L_0x01bb;
                case -1609477353: goto L_0x01b1;
                case 249237018: goto L_0x01a7;
                case 1534613202: goto L_0x019d;
                default: goto L_0x019c;
            }
        L_0x019c:
            goto L_0x01c5
        L_0x019d:
            java.lang.String r12 = "ASYMMETRIC_PUBLIC"
            boolean r12 = r9.equals(r12)
            if (r12 == 0) goto L_0x01c5
            r12 = 2
            goto L_0x01c7
        L_0x01a7:
            java.lang.String r12 = "ASYMMETRIC_PRIVATE"
            boolean r12 = r9.equals(r12)
            if (r12 == 0) goto L_0x01c5
            r12 = r6
            goto L_0x01c7
        L_0x01b1:
            java.lang.String r12 = "SYMMETRIC"
            boolean r12 = r9.equals(r12)
            if (r12 == 0) goto L_0x01c5
            r12 = 0
            goto L_0x01c7
        L_0x01bb:
            java.lang.String r12 = "REMOTE"
            boolean r12 = r9.equals(r12)
            if (r12 == 0) goto L_0x01c5
            r12 = 3
            goto L_0x01c7
        L_0x01c5:
            r12 = r16
        L_0x01c7:
            if (r12 == 0) goto L_0x01ea
            if (r12 == r6) goto L_0x01e7
            r6 = 2
            if (r12 == r6) goto L_0x01e4
            r6 = 3
            if (r12 != r6) goto L_0x01d4
            com.google.android.gms.internal.pal.zzvn r6 = com.google.android.gms.internal.pal.zzvn.REMOTE     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            goto L_0x01ec
        L_0x01d4:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "unknown key material type: "
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = r2.concat(r3)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x01e4:
            com.google.android.gms.internal.pal.zzvn r6 = com.google.android.gms.internal.pal.zzvn.ASYMMETRIC_PUBLIC     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            goto L_0x01ec
        L_0x01e7:
            com.google.android.gms.internal.pal.zzvn r6 = com.google.android.gms.internal.pal.zzvn.ASYMMETRIC_PRIVATE     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            goto L_0x01ec
        L_0x01ea:
            com.google.android.gms.internal.pal.zzvn r6 = com.google.android.gms.internal.pal.zzvn.SYMMETRIC     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x01ec:
            r14.zza(r6)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzacz r6 = r14.zzan()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzvo r6 = (com.google.android.gms.internal.pal.zzvo) r6     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r13.zza(r6)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzacz r6 = r13.zzan()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzwa r6 = (com.google.android.gms.internal.pal.zzwa) r6     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            r11.zza(r6)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            int r10 = r10 + 1
            r6 = r17
            goto L_0x005d
        L_0x0207:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "invalid keyData"
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x020f:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "invalid key"
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x0217:
            com.google.android.gms.internal.pal.zzacz r0 = r11.zzan()     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            com.google.android.gms.internal.pal.zzwb r0 = (com.google.android.gms.internal.pal.zzwb) r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.io.InputStream r2 = r1.zzb
            if (r2 == 0) goto L_0x0224
            r2.close()
        L_0x0224:
            return r0
        L_0x0225:
            com.google.android.gms.internal.pal.zzzc r0 = new com.google.android.gms.internal.pal.zzzc     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            java.lang.String r2 = "invalid keyset"
            r0.<init>((java.lang.String) r2)     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
            throw r0     // Catch:{ zzzc -> 0x0231, IllegalStateException -> 0x022f }
        L_0x022d:
            r0 = move-exception
            goto L_0x0238
        L_0x022f:
            r0 = move-exception
            goto L_0x0232
        L_0x0231:
            r0 = move-exception
        L_0x0232:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x022d }
            r2.<init>(r0)     // Catch:{ all -> 0x022d }
            throw r2     // Catch:{ all -> 0x022d }
        L_0x0238:
            java.io.InputStream r2 = r1.zzb
            if (r2 == 0) goto L_0x023f
            r2.close()
        L_0x023f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzjz.zzb():com.google.android.gms.internal.pal.zzwb");
    }
}
