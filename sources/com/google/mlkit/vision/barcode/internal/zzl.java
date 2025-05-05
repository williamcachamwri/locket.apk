package com.google.mlkit.vision.barcode.internal;

import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzft;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqh;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzra;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzws;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzl extends MLTask {
    static boolean zza = true;
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private final BarcodeScannerOptions zzc;
    private final zzm zzd;
    private final zzwp zze;
    private final zzwr zzf;
    private final BitmapInStreamingChecker zzg = new BitmapInStreamingChecker();
    private boolean zzh;

    public zzl(MlKitContext mlKitContext, BarcodeScannerOptions barcodeScannerOptions, zzm zzm, zzwp zzwp) {
        Preconditions.checkNotNull(mlKitContext, "MlKitContext can not be null");
        Preconditions.checkNotNull(barcodeScannerOptions, "BarcodeScannerOptions can not be null");
        this.zzc = barcodeScannerOptions;
        this.zzd = zzm;
        this.zze = zzwp;
        this.zzf = zzwr.zza(mlKitContext.getApplicationContext());
    }

    private final void zzf(zzrb zzrb, long j, InputImage inputImage, List list) {
        zzcp zzcp = new zzcp();
        zzcp zzcp2 = new zzcp();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Barcode barcode = (Barcode) it.next();
                zzcp.zzd(zzb.zza(barcode.getFormat()));
                zzcp2.zzd(zzb.zzb(barcode.getValueType()));
            }
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.zze.zzf(new zzj(this, elapsedRealtime, zzrb, zzcp, zzcp2, inputImage), zzrc.ON_DEVICE_BARCODE_DETECT);
        zzfr zzfr = new zzfr();
        zzfr.zze(zzrb);
        zzfr.zzf(Boolean.valueOf(zza));
        zzfr.zzg(zzb.zzc(this.zzc));
        zzfr.zzc(zzcp.zzf());
        zzfr.zzd(zzcp2.zzf());
        MLTaskExecutor.workerThreadExecutor().execute(new zzwn(this.zze, zzrc.AGGREGATED_ON_DEVICE_BARCODE_DETECTION, zzfr.zzh(), elapsedRealtime, new zzk(this)));
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = this.zzh;
        long j2 = currentTimeMillis - elapsedRealtime;
        this.zzf.zzc(true != z ? 24301 : 24302, zzrb.zza(), j2, currentTimeMillis);
    }

    public final synchronized void load() throws MlKitException {
        this.zzh = this.zzd.zzc();
    }

    public final synchronized void release() {
        zzra zzra;
        this.zzd.zzb();
        zza = true;
        zzrd zzrd = new zzrd();
        if (this.zzh) {
            zzra = zzra.TYPE_THICK;
        } else {
            zzra = zzra.TYPE_THIN;
        }
        zzwp zzwp = this.zze;
        zzrd.zze(zzra);
        zzrp zzrp = new zzrp();
        zzrp.zzi(zzb.zzc(this.zzc));
        zzrd.zzg(zzrp.zzj());
        zzwp.zzd(zzws.zzf(zzrd), zzrc.ON_DEVICE_BARCODE_CLOSE);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzwe zzc(long j, zzrb zzrb, zzcp zzcp, zzcp zzcp2, InputImage inputImage) {
        zzqi zzqi;
        zzra zzra;
        zzrp zzrp = new zzrp();
        zzqo zzqo = new zzqo();
        zzqo.zzc(Long.valueOf(j));
        zzqo.zzd(zzrb);
        zzqo.zze(Boolean.valueOf(zza));
        zzqo.zza(true);
        zzqo.zzb(true);
        zzrp.zzh(zzqo.zzf());
        zzrp.zzi(zzb.zzc(this.zzc));
        zzrp.zze(zzcp.zzf());
        zzrp.zzf(zzcp2.zzf());
        int format = inputImage.getFormat();
        int mobileVisionImageSize = zzb.getMobileVisionImageSize(inputImage);
        zzqh zzqh = new zzqh();
        if (format != -1) {
            zzqi = format != 35 ? format != 842094169 ? format != 16 ? format != 17 ? zzqi.UNKNOWN_FORMAT : zzqi.NV21 : zzqi.NV16 : zzqi.YV12 : zzqi.YUV_420_888;
        } else {
            zzqi = zzqi.BITMAP;
        }
        zzqh.zza(zzqi);
        zzqh.zzb(Integer.valueOf(mobileVisionImageSize));
        zzrp.zzg(zzqh.zzd());
        zzrd zzrd = new zzrd();
        if (this.zzh) {
            zzra = zzra.TYPE_THICK;
        } else {
            zzra = zzra.TYPE_THIN;
        }
        zzrd.zze(zzra);
        zzrd.zzg(zzrp.zzj());
        return zzws.zzf(zzrd);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzwe zzd(zzft zzft, int i, zzqd zzqd) {
        zzra zzra;
        zzrd zzrd = new zzrd();
        if (this.zzh) {
            zzra = zzra.TYPE_THICK;
        } else {
            zzra = zzra.TYPE_THIN;
        }
        zzrd.zze(zzra);
        zzfq zzfq = new zzfq();
        zzfq.zza(Integer.valueOf(i));
        zzfq.zzc(zzft);
        zzfq.zzb(zzqd);
        zzrd.zzd(zzfq.zze());
        return zzws.zzf(zzrd);
    }

    /* renamed from: zze */
    public final synchronized List run(InputImage inputImage) throws MlKitException {
        zzrb zzrb;
        List zza2;
        BitmapInStreamingChecker bitmapInStreamingChecker = this.zzg;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        bitmapInStreamingChecker.check(inputImage);
        try {
            zza2 = this.zzd.zza(inputImage);
            zzf(zzrb.NO_ERROR, elapsedRealtime, inputImage, zza2);
            zza = false;
        } catch (MlKitException e) {
            if (e.getErrorCode() == 14) {
                zzrb = zzrb.MODEL_NOT_DOWNLOADED;
            } else {
                zzrb = zzrb.UNKNOWN_ERROR;
            }
            zzf(zzrb, elapsedRealtime, inputImage, (List) null);
            throw e;
        }
        return zza2;
    }
}
