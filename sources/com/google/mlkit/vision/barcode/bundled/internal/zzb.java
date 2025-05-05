package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbk;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zzc;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.barhopper.deeplearning.zzm;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzn;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzb extends zzbk {
    private static final int[] zza = {5, 7, 7, 7, 5, 5};
    private static final double[][] zzb = {new double[]{0.075d, 1.0d}, new double[]{0.1d, 1.0d}, new double[]{0.125d, 1.0d}, new double[]{0.2d, 2.0d}, new double[]{0.2d, 0.5d}, new double[]{0.15d, 1.0d}, new double[]{0.2d, 1.0d}, new double[]{0.25d, 1.0d}, new double[]{0.35d, 2.0d}, new double[]{0.35d, 0.5d}, new double[]{0.35d, 3.0d}, new double[]{0.35d, 0.3333d}, new double[]{0.3d, 1.0d}, new double[]{0.4d, 1.0d}, new double[]{0.5d, 1.0d}, new double[]{0.5d, 2.0d}, new double[]{0.5d, 0.5d}, new double[]{0.5d, 3.0d}, new double[]{0.5d, 0.3333d}, new double[]{0.6d, 1.0d}, new double[]{0.8d, 1.0d}, new double[]{1.0d, 1.0d}, new double[]{0.65d, 2.0d}, new double[]{0.65d, 0.5d}, new double[]{0.65d, 3.0d}, new double[]{0.65d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.8d, 2.0d}, new double[]{0.8d, 0.5d}, new double[]{0.8d, 3.0d}, new double[]{0.8d, 0.3333d}, new double[]{1.0d, 1.0d}, new double[]{0.95d, 2.0d}, new double[]{0.95d, 0.5d}, new double[]{0.95d, 3.0d}, new double[]{0.95d, 0.3333d}};
    private final Context zzc;
    private final RecognitionOptions zzd;
    private BarhopperV3 zze;

    zzb(Context context, zzbc zzbc) {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        this.zzd = recognitionOptions;
        this.zzc = context;
        recognitionOptions.setBarcodeFormats(zzbc.zza());
        recognitionOptions.setOutputUnrecognizedBarcodes(zzbc.zzb());
    }

    private static zzap zze(zzn zzn, String str, String str2) {
        String str3 = null;
        if (zzn == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        int zzf = zzn.zzf();
        int zzd2 = zzn.zzd();
        int zza2 = zzn.zza();
        int zzb2 = zzn.zzb();
        int zzc2 = zzn.zzc();
        int zze2 = zzn.zze();
        boolean zzj = zzn.zzj();
        if (matcher.find()) {
            str3 = matcher.group(1);
        }
        return new zzap(zzf, zzd2, zza2, zzb2, zzc2, zze2, zzj, str3);
    }

    private final BarhopperProto$BarhopperResponse zzf(ByteBuffer byteBuffer, zzbu zzbu) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), byteBuffer, this.zzd);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), byteBuffer.array(), this.zzd);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzbu.zzd(), zzbu.zza(), bArr, this.zzd);
    }

    /* JADX WARNING: type inference failed for: r4v54, types: [com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzb(com.google.android.gms.dynamic.IObjectWrapper r55, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu r56) {
        /*
            r54 = this;
            r0 = r54
            r1 = r56
            int r2 = r56.zzb()
            r3 = -1
            r4 = 0
            if (r2 == r3) goto L_0x0058
            r5 = 17
            if (r2 == r5) goto L_0x004d
            r5 = 35
            if (r2 == r5) goto L_0x0032
            r5 = 842094169(0x32315659, float:1.0322389E-8)
            if (r2 != r5) goto L_0x001a
            goto L_0x004d
        L_0x001a:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            int r1 = r56.zzb()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unsupported image format: "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0032:
            java.lang.Object r2 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r55)
            android.media.Image r2 = (android.media.Image) r2
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            android.media.Image r2 = (android.media.Image) r2
            android.media.Image$Plane[] r2 = r2.getPlanes()
            r2 = r2[r4]
            java.nio.ByteBuffer r2 = r2.getBuffer()
            com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse r2 = r0.zzf(r2, r1)
            goto L_0x006c
        L_0x004d:
            java.lang.Object r2 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r55)
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2
            com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse r2 = r0.zzf(r2, r1)
            goto L_0x006c
        L_0x0058:
            com.google.android.libraries.barhopper.BarhopperV3 r2 = r0.zze
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)
            com.google.android.libraries.barhopper.BarhopperV3 r2 = (com.google.android.libraries.barhopper.BarhopperV3) r2
            java.lang.Object r5 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r55)
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            com.google.android.libraries.barhopper.RecognitionOptions r6 = r0.zzd
            com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse r2 = r2.recognize(r5, r6)
        L_0x006c:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            com.google.mlkit.vision.common.internal.ImageUtils r6 = com.google.mlkit.vision.common.internal.ImageUtils.getInstance()
            int r7 = r56.zzd()
            int r8 = r56.zza()
            int r9 = r56.zzc()
            android.graphics.Matrix r6 = r6.getUprightRotationMatrix(r7, r8, r9)
            java.util.List r2 = r2.zzc()
            java.util.Iterator r2 = r2.iterator()
        L_0x008d:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x046f
            java.lang.Object r7 = r2.next()
            com.google.photos.vision.barhopper.zzc r7 = (com.google.photos.vision.barhopper.zzc) r7
            int r8 = r7.zza()
            r9 = 8
            r10 = 1
            if (r8 <= 0) goto L_0x010d
            if (r6 == 0) goto L_0x010d
            float[] r8 = new float[r9]
            java.util.List r11 = r7.zzo()
            int r12 = r7.zza()
            r13 = r4
        L_0x00af:
            if (r13 >= r12) goto L_0x00d1
            java.lang.Object r14 = r11.get(r13)
            com.google.photos.vision.barhopper.zzae r14 = (com.google.photos.vision.barhopper.zzae) r14
            int r14 = r14.zza()
            float r14 = (float) r14
            int r15 = r13 + r13
            r8[r15] = r14
            int r15 = r15 + r10
            java.lang.Object r14 = r11.get(r13)
            com.google.photos.vision.barhopper.zzae r14 = (com.google.photos.vision.barhopper.zzae) r14
            int r14 = r14.zzb()
            float r14 = (float) r14
            r8[r15] = r14
            int r13 = r13 + 1
            goto L_0x00af
        L_0x00d1:
            r6.mapPoints(r8)
            int r11 = r56.zzc()
            r13 = r4
        L_0x00d9:
            if (r13 >= r12) goto L_0x010d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx r7 = r7.zzG()
            com.google.photos.vision.barhopper.zzb r7 = (com.google.photos.vision.barhopper.zzb) r7
            int r14 = r13 + r11
            int r14 = r14 % r12
            int r15 = r13 + r13
            com.google.photos.vision.barhopper.zzad r9 = com.google.photos.vision.barhopper.zzae.zzc()
            r4 = r8[r15]
            int r4 = (int) r4
            r9.zza(r4)
            int r15 = r15 + r10
            r4 = r8[r15]
            int r4 = (int) r4
            r9.zzb(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed r4 = r9.zzj()
            com.google.photos.vision.barhopper.zzae r4 = (com.google.photos.vision.barhopper.zzae) r4
            r7.zza(r14, r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed r4 = r7.zzj()
            r7 = r4
            com.google.photos.vision.barhopper.zzc r7 = (com.google.photos.vision.barhopper.zzc) r7
            int r13 = r13 + 1
            r4 = 0
            r9 = 8
            goto L_0x00d9
        L_0x010d:
            boolean r4 = r7.zzt()
            if (r4 == 0) goto L_0x0130
            com.google.photos.vision.barhopper.zzy r4 = r7.zzh()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat
            int r11 = r4.zzf()
            int r11 = r11 + r3
            java.lang.String r12 = r4.zzc()
            java.lang.String r13 = r4.zze()
            java.lang.String r4 = r4.zzd()
            r9.<init>(r11, r12, r13, r4)
            r24 = r9
            goto L_0x0132
        L_0x0130:
            r24 = 0
        L_0x0132:
            boolean r4 = r7.zzv()
            if (r4 == 0) goto L_0x014d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci r4 = r7.zzb()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw
            int r11 = r4.zzd()
            int r11 = r11 + r3
            java.lang.String r4 = r4.zzc()
            r9.<init>(r11, r4)
            r25 = r9
            goto L_0x014f
        L_0x014d:
            r25 = 0
        L_0x014f:
            boolean r4 = r7.zzw()
            if (r4 == 0) goto L_0x0169
            com.google.photos.vision.barhopper.zzag r4 = r7.zzj()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax
            java.lang.String r11 = r4.zzc()
            java.lang.String r4 = r4.zzd()
            r9.<init>(r11, r4)
            r26 = r9
            goto L_0x016b
        L_0x0169:
            r26 = 0
        L_0x016b:
            boolean r4 = r7.zzy()
            if (r4 == 0) goto L_0x018a
            com.google.photos.vision.barhopper.zzao r4 = r7.zzl()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaz
            java.lang.String r11 = r4.zzd()
            java.lang.String r12 = r4.zzc()
            int r4 = r4.zze()
            int r4 = r4 + r3
            r9.<init>(r11, r12, r4)
            r27 = r9
            goto L_0x018c
        L_0x018a:
            r27 = 0
        L_0x018c:
            boolean r4 = r7.zzx()
            if (r4 == 0) goto L_0x01a6
            com.google.photos.vision.barhopper.zzaj r4 = r7.zzk()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay
            java.lang.String r11 = r4.zzc()
            java.lang.String r4 = r4.zzd()
            r9.<init>(r11, r4)
            r28 = r9
            goto L_0x01a8
        L_0x01a6:
            r28 = 0
        L_0x01a8:
            boolean r4 = r7.zzu()
            if (r4 == 0) goto L_0x01c2
            com.google.photos.vision.barhopper.zzac r4 = r7.zzi()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau
            double r11 = r4.zza()
            double r13 = r4.zzb()
            r9.<init>(r11, r13)
            r29 = r9
            goto L_0x01c4
        L_0x01c2:
            r29 = 0
        L_0x01c4:
            boolean r4 = r7.zzq()
            if (r4 == 0) goto L_0x0226
            com.google.photos.vision.barhopper.zzp r4 = r7.zzd()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq
            java.lang.String r31 = r4.zzj()
            java.lang.String r32 = r4.zze()
            java.lang.String r33 = r4.zzf()
            java.lang.String r34 = r4.zzh()
            java.lang.String r35 = r4.zzi()
            com.google.photos.vision.barhopper.zzn r11 = r4.zzb()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r12 = r7.zzm()
            boolean r12 = r12.zzn()
            if (r12 == 0) goto L_0x01fb
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r12 = r7.zzm()
            java.lang.String r12 = r12.zzu()
            goto L_0x01fc
        L_0x01fb:
            r12 = 0
        L_0x01fc:
            java.lang.String r13 = "DTSTART:([0-9TZ]*)"
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap r36 = zze(r11, r12, r13)
            com.google.photos.vision.barhopper.zzn r4 = r4.zza()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = r7.zzm()
            boolean r11 = r11.zzn()
            if (r11 == 0) goto L_0x0219
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = r7.zzm()
            java.lang.String r11 = r11.zzu()
            goto L_0x021a
        L_0x0219:
            r11 = 0
        L_0x021a:
            java.lang.String r12 = "DTEND:([0-9TZ]*)"
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap r37 = zze(r4, r11, r12)
            r30 = r9
            r30.<init>(r31, r32, r33, r34, r35, r36, r37)
            goto L_0x0228
        L_0x0226:
            r30 = 0
        L_0x0228:
            boolean r4 = r7.zzr()
            if (r4 == 0) goto L_0x0352
            com.google.photos.vision.barhopper.zzr r4 = r7.zze()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar r9 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd r11 = r4.zza()
            if (r11 == 0) goto L_0x0260
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav r12 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav
            java.lang.String r32 = r11.zzd()
            java.lang.String r33 = r11.zzi()
            java.lang.String r34 = r11.zzh()
            java.lang.String r35 = r11.zzc()
            java.lang.String r36 = r11.zzf()
            java.lang.String r37 = r11.zze()
            java.lang.String r38 = r11.zzj()
            r31 = r12
            r31.<init>(r32, r33, r34, r35, r36, r37, r38)
            r32 = r12
            goto L_0x0262
        L_0x0260:
            r32 = 0
        L_0x0262:
            java.lang.String r33 = r4.zzd()
            java.lang.String r34 = r4.zze()
            java.util.List r11 = r4.zzi()
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x0277
            r35 = 0
            goto L_0x02a5
        L_0x0277:
            int r12 = r11.size()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw[] r12 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw[r12]
            r13 = 0
        L_0x027e:
            int r14 = r11.size()
            if (r13 >= r14) goto L_0x02a3
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw r14 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw
            java.lang.Object r15 = r11.get(r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci r15 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci) r15
            int r15 = r15.zzd()
            int r15 = r15 + r3
            java.lang.Object r17 = r11.get(r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci r17 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci) r17
            java.lang.String r8 = r17.zzc()
            r14.<init>(r15, r8)
            r12[r13] = r14
            int r13 = r13 + 1
            goto L_0x027e
        L_0x02a3:
            r35 = r12
        L_0x02a5:
            java.util.List r8 = r4.zzh()
            boolean r11 = r8.isEmpty()
            if (r11 == 0) goto L_0x02b2
            r36 = 0
            goto L_0x02f6
        L_0x02b2:
            int r11 = r8.size()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat[] r11 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat[r11]
            r12 = 0
        L_0x02b9:
            int r13 = r8.size()
            if (r12 >= r13) goto L_0x02f4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat r13 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat
            java.lang.Object r14 = r8.get(r12)
            com.google.photos.vision.barhopper.zzy r14 = (com.google.photos.vision.barhopper.zzy) r14
            int r14 = r14.zzf()
            int r14 = r14 + r3
            java.lang.Object r15 = r8.get(r12)
            com.google.photos.vision.barhopper.zzy r15 = (com.google.photos.vision.barhopper.zzy) r15
            java.lang.String r15 = r15.zzc()
            java.lang.Object r17 = r8.get(r12)
            com.google.photos.vision.barhopper.zzy r17 = (com.google.photos.vision.barhopper.zzy) r17
            java.lang.String r10 = r17.zze()
            java.lang.Object r17 = r8.get(r12)
            com.google.photos.vision.barhopper.zzy r17 = (com.google.photos.vision.barhopper.zzy) r17
            java.lang.String r3 = r17.zzd()
            r13.<init>(r14, r15, r10, r3)
            r11[r12] = r13
            int r12 = r12 + 1
            r3 = -1
            r10 = 1
            goto L_0x02b9
        L_0x02f4:
            r36 = r11
        L_0x02f6:
            java.util.List r3 = r4.zzj()
            r8 = 0
            java.lang.String[] r10 = new java.lang.String[r8]
            java.lang.Object[] r3 = r3.toArray(r10)
            r37 = r3
            java.lang.String[] r37 = (java.lang.String[]) r37
            java.util.List r3 = r4.zzf()
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x0313
            r13 = 0
            r38 = 0
            goto L_0x034c
        L_0x0313:
            int r4 = r3.size()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao[] r4 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao[r4]
            r8 = 0
        L_0x031a:
            int r10 = r3.size()
            if (r8 >= r10) goto L_0x0349
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao r10 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao
            java.lang.Object r11 = r3.get(r8)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb r11 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb) r11
            int r11 = r11.zzc()
            r12 = -1
            int r11 = r11 + r12
            java.lang.Object r12 = r3.get(r8)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb r12 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb) r12
            java.util.List r12 = r12.zzb()
            r13 = 0
            java.lang.String[] r14 = new java.lang.String[r13]
            java.lang.Object[] r12 = r12.toArray(r14)
            java.lang.String[] r12 = (java.lang.String[]) r12
            r10.<init>(r11, r12)
            r4[r8] = r10
            int r8 = r8 + 1
            goto L_0x031a
        L_0x0349:
            r13 = 0
            r38 = r4
        L_0x034c:
            r31 = r9
            r31.<init>(r32, r33, r34, r35, r36, r37, r38)
            goto L_0x0355
        L_0x0352:
            r13 = 0
            r31 = 0
        L_0x0355:
            boolean r3 = r7.zzs()
            if (r3 == 0) goto L_0x03a1
            com.google.photos.vision.barhopper.zzt r3 = r7.zzf()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas r4 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas
            java.lang.String r40 = r3.zzi()
            java.lang.String r41 = r3.zzk()
            java.lang.String r42 = r3.zzq()
            java.lang.String r43 = r3.zzo()
            java.lang.String r44 = r3.zzl()
            java.lang.String r45 = r3.zze()
            java.lang.String r46 = r3.zzc()
            java.lang.String r47 = r3.zzd()
            java.lang.String r48 = r3.zzf()
            java.lang.String r49 = r3.zzp()
            java.lang.String r50 = r3.zzm()
            java.lang.String r51 = r3.zzj()
            java.lang.String r52 = r3.zzh()
            java.lang.String r53 = r3.zzn()
            r39 = r4
            r39.<init>(r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53)
            r32 = r4
            goto L_0x03a3
        L_0x03a1:
            r32 = 0
        L_0x03a3:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba r3 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba
            int r4 = r7.zzz()
            r8 = -1
            int r4 = r4 + r8
            switch(r4) {
                case 0: goto L_0x03d4;
                case 1: goto L_0x03d2;
                case 2: goto L_0x03d0;
                case 3: goto L_0x03ce;
                case 4: goto L_0x03cb;
                case 5: goto L_0x03c8;
                case 6: goto L_0x03c5;
                case 7: goto L_0x03c2;
                case 8: goto L_0x03bf;
                case 9: goto L_0x03bc;
                case 10: goto L_0x03b9;
                case 11: goto L_0x03b6;
                case 12: goto L_0x03b3;
                case 13: goto L_0x03b0;
                default: goto L_0x03ae;
            }
        L_0x03ae:
            r4 = -1
            goto L_0x03d5
        L_0x03b0:
            r4 = 4096(0x1000, float:5.74E-42)
            goto L_0x03d5
        L_0x03b3:
            r4 = 2048(0x800, float:2.87E-42)
            goto L_0x03d5
        L_0x03b6:
            r4 = 1024(0x400, float:1.435E-42)
            goto L_0x03d5
        L_0x03b9:
            r4 = 512(0x200, float:7.175E-43)
            goto L_0x03d5
        L_0x03bc:
            r4 = 256(0x100, float:3.59E-43)
            goto L_0x03d5
        L_0x03bf:
            r4 = 128(0x80, float:1.794E-43)
            goto L_0x03d5
        L_0x03c2:
            r4 = 64
            goto L_0x03d5
        L_0x03c5:
            r4 = 32
            goto L_0x03d5
        L_0x03c8:
            r4 = 16
            goto L_0x03d5
        L_0x03cb:
            r4 = 8
            goto L_0x03d5
        L_0x03ce:
            r4 = 4
            goto L_0x03d5
        L_0x03d0:
            r4 = 2
            goto L_0x03d5
        L_0x03d2:
            r4 = 1
            goto L_0x03d5
        L_0x03d4:
            r4 = r13
        L_0x03d5:
            java.lang.String r10 = r7.zzn()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = r7.zzm()
            boolean r11 = r11.zzn()
            if (r11 == 0) goto L_0x03ee
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = r7.zzm()
            java.lang.String r11 = r11.zzu()
            r20 = r11
            goto L_0x03f0
        L_0x03ee:
            r20 = 0
        L_0x03f0:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = r7.zzm()
            byte[] r21 = r11.zzx()
            java.util.List r11 = r7.zzo()
            boolean r12 = r11.isEmpty()
            if (r12 == 0) goto L_0x0405
            r22 = 0
            goto L_0x0432
        L_0x0405:
            int r12 = r11.size()
            android.graphics.Point[] r12 = new android.graphics.Point[r12]
            r14 = r13
        L_0x040c:
            int r15 = r11.size()
            if (r14 >= r15) goto L_0x0430
            android.graphics.Point r15 = new android.graphics.Point
            java.lang.Object r16 = r11.get(r14)
            com.google.photos.vision.barhopper.zzae r16 = (com.google.photos.vision.barhopper.zzae) r16
            int r8 = r16.zza()
            java.lang.Object r16 = r11.get(r14)
            com.google.photos.vision.barhopper.zzae r16 = (com.google.photos.vision.barhopper.zzae) r16
            int r9 = r16.zzb()
            r15.<init>(r8, r9)
            r12[r14] = r15
            int r14 = r14 + 1
            goto L_0x040c
        L_0x0430:
            r22 = r12
        L_0x0432:
            int r7 = r7.zzA()
            r8 = -1
            int r7 = r7 + r8
            switch(r7) {
                case 1: goto L_0x045d;
                case 2: goto L_0x045a;
                case 3: goto L_0x0456;
                case 4: goto L_0x0453;
                case 5: goto L_0x0451;
                case 6: goto L_0x044f;
                case 7: goto L_0x044d;
                case 8: goto L_0x044a;
                case 9: goto L_0x0447;
                case 10: goto L_0x0444;
                case 11: goto L_0x0441;
                case 12: goto L_0x043e;
                default: goto L_0x043b;
            }
        L_0x043b:
            r23 = r13
            goto L_0x045f
        L_0x043e:
            r9 = 12
            goto L_0x0457
        L_0x0441:
            r9 = 11
            goto L_0x0457
        L_0x0444:
            r9 = 10
            goto L_0x0457
        L_0x0447:
            r9 = 9
            goto L_0x0457
        L_0x044a:
            r23 = 8
            goto L_0x045f
        L_0x044d:
            r9 = 7
            goto L_0x0457
        L_0x044f:
            r9 = 6
            goto L_0x0457
        L_0x0451:
            r9 = 5
            goto L_0x0457
        L_0x0453:
            r23 = 4
            goto L_0x045f
        L_0x0456:
            r9 = 3
        L_0x0457:
            r23 = r9
            goto L_0x045f
        L_0x045a:
            r23 = 2
            goto L_0x045f
        L_0x045d:
            r23 = 1
        L_0x045f:
            r17 = r3
            r18 = r4
            r19 = r10
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            r5.add(r3)
            r3 = r8
            r4 = r13
            goto L_0x008d
        L_0x046f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.barcode.bundled.internal.zzb.zzb(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbu):java.util.List");
    }

    public final void zzc() {
        InputStream open;
        if (this.zze == null) {
            this.zze = new BarhopperV3();
            zzh zza2 = zzi.zza();
            zze zza3 = zzf.zza();
            int i = 16;
            int i2 = 0;
            for (int i3 = 0; i3 < 6; i3++) {
                com.google.barhopper.deeplearning.zzb zza4 = zzc.zza();
                zza4.zzc(i);
                zza4.zzd(i);
                for (int i4 = 0; i4 < zza[i3]; i4++) {
                    double[] dArr = zzb[i2];
                    float sqrt = (float) Math.sqrt(dArr[1]);
                    float f = (float) (dArr[0] * 320.0d);
                    zza4.zza(f / sqrt);
                    zza4.zzb(f * sqrt);
                    i2++;
                }
                i += i;
                zza3.zza(zza4);
            }
            zza2.zza(zza3);
            try {
                InputStream open2 = this.zzc.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
                try {
                    InputStream open3 = this.zzc.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
                    try {
                        open = this.zzc.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                        zzk zza5 = BarhopperV3Options.zza();
                        zza2.zzb(zzdb.zzs(open2));
                        zza5.zza(zza2);
                        zzm zza6 = com.google.barhopper.deeplearning.zzn.zza();
                        zza6.zza(zzdb.zzs(open3));
                        zza6.zzb(zzdb.zzs(open));
                        zza5.zzb(zza6);
                        ((BarhopperV3) Preconditions.checkNotNull(this.zze)).create((BarhopperV3Options) zza5.zzj());
                        if (open != null) {
                            open.close();
                        }
                        if (open3 != null) {
                            open3.close();
                        }
                        if (open2 != null) {
                            open2.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        if (open3 != null) {
                            open3.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (open2 != null) {
                        open2.close();
                    }
                    throw th2;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Failed to open Barcode models", e);
            } catch (Throwable th3) {
                zza.zza(th2, th3);
            }
        } else {
            return;
        }
        throw th;
    }

    public final void zzd() {
        BarhopperV3 barhopperV3 = this.zze;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.zze = null;
        }
    }
}
