package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Objects;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zzsh {
    private static zzaf zza;
    private static final zzai zzb = zzai.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzrz zze;
    private final SharedPrefManager zzf;
    private final Task zzg = MLTaskExecutor.getInstance().scheduleCallable(new zzse(this));
    private final Task zzh;
    private final String zzi;
    private final int zzj;

    public zzsh(Context context, SharedPrefManager sharedPrefManager, zzrz zzrz, String str) {
        new HashMap();
        new HashMap();
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzrz;
        zzsv.zza();
        this.zzi = str;
        MLTaskExecutor instance = MLTaskExecutor.getInstance();
        Objects.requireNonNull(sharedPrefManager);
        this.zzh = instance.scheduleCallable(new zzsf(sharedPrefManager));
        zzai zzai = zzb;
        this.zzj = zzai.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzai.get(str)) : -1;
    }

    private static synchronized zzaf zzh() {
        synchronized (zzsh.class) {
            zzaf zzaf = zza;
            if (zzaf != null) {
                return zzaf;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzac zzac = new zzac();
            for (int i = 0; i < locales.size(); i++) {
                zzac.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzaf zzc2 = zzac.zzc();
            zza = zzc2;
            return zzc2;
        }
    }

    private final zzqt zzi(String str, String str2) {
        String str3;
        zzqt zzqt = new zzqt();
        zzqt.zzb(this.zzc);
        zzqt.zzc(this.zzd);
        zzqt.zzh(zzh());
        zzqt.zzg(true);
        zzqt.zzl(str);
        zzqt.zzj(str2);
        if (this.zzh.isSuccessful()) {
            str3 = (String) this.zzh.getResult();
        } else {
            str3 = this.zzf.getMlSdkInstanceId();
        }
        zzqt.zzi(str3);
        zzqt.zzd(10);
        zzqt.zzk(Integer.valueOf(this.zzj));
        return zzqt;
    }

    private final String zzj() {
        if (this.zzg.isSuccessful()) {
            return (String) this.zzg.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzry zzry, zzmv zzmv, String str) {
        zzry.zza(zzmv);
        zzry.zzc(zzi(zzry.zzd(), str));
        this.zze.zza(zzry);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzry zzry, zzsj zzsj, RemoteModel remoteModel) {
        zzry.zza(zzmv.MODEL_DOWNLOAD);
        zzry.zzc(zzi(zzsj.zze(), zzj()));
        zzry.zzb(zzst.zza(remoteModel, this.zzf, zzsj));
        this.zze.zza(zzry);
    }

    public final void zzd(zzry zzry, zzmv zzmv) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzsd(this, zzry, zzmv, zzj()));
    }

    public final void zze(zzry zzry, RemoteModel remoteModel, boolean z, int i) {
        zzsi zzh2 = zzsj.zzh();
        zzh2.zzf(false);
        zzh2.zzd(remoteModel.getModelType());
        zzh2.zza(zzna.FAILED);
        zzh2.zzb(zzmu.DOWNLOAD_FAILED);
        zzh2.zzc(i);
        zzg(zzry, remoteModel, zzh2.zzh());
    }

    public final void zzf(zzry zzry, RemoteModel remoteModel, zzmu zzmu, boolean z, ModelType modelType, zzna zzna) {
        zzsi zzh2 = zzsj.zzh();
        zzh2.zzf(z);
        zzh2.zzd(modelType);
        zzh2.zzb(zzmu);
        zzh2.zza(zzna);
        zzg(zzry, remoteModel, zzh2.zzh());
    }

    public final void zzg(zzry zzry, RemoteModel remoteModel, zzsj zzsj) {
        MLTaskExecutor.workerThreadExecutor().execute(new zzsg(this, zzry, zzsj, remoteModel));
    }
}
