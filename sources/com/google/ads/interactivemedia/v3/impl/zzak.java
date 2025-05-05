package com.google.ads.interactivemedia.v3.impl;

import android.app.Application;
import android.content.Context;
import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.BaseManager;
import com.google.ads.interactivemedia.v3.api.FriendlyObstruction;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.zza;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzc;
import com.google.ads.interactivemedia.v3.impl.data.zzg;
import com.google.ads.interactivemedia.v3.impl.data.zzh;
import com.google.ads.interactivemedia.v3.internal.zzel;
import com.google.ads.interactivemedia.v3.internal.zzem;
import com.google.ads.interactivemedia.v3.internal.zzen;
import com.google.ads.interactivemedia.v3.internal.zzgi;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzak implements BaseManager, zzem {
    /* access modifiers changed from: private */
    public final zzba zza;
    /* access modifiers changed from: private */
    public final String zzb;
    private final List zzc;
    private final zzat zzd;
    private final Context zze;
    private final zzb zzf;
    private final zzbt zzg;
    /* access modifiers changed from: private */
    public final zzcc zzh;
    /* access modifiers changed from: private */
    public final zzam zzi;
    private zzc zzj;
    private zza zzk;
    private AdProgressInfo zzl;
    private AdsRenderingSettings zzm;
    private boolean zzn = false;
    private final zzgi zzo;
    private zzen zzp;

    zzak(String str, zzba zzba, zzcc zzcc, BaseDisplayContainer baseDisplayContainer, zzb zzb2, zzbt zzbt, zzat zzat, ExecutorService executorService, Context context, boolean z) {
        String str2 = str;
        zzba zzba2 = zzba;
        zzb zzb3 = zzb2;
        zzbt zzbt2 = zzbt;
        zzat zzat2 = zzat;
        Context context2 = context;
        ArrayList arrayList = new ArrayList(1);
        this.zzc = arrayList;
        this.zzb = str2;
        this.zza = zzba2;
        this.zzh = zzcc;
        this.zze = context2;
        this.zzd = zzat2;
        this.zzm = new zzh();
        zzgi zzgi = new zzgi(context2, this.zzm);
        this.zzo = zzgi;
        zzaf zzaf = (zzaf) baseDisplayContainer;
        zzam zzam = r1;
        zzaf zzaf2 = zzaf;
        zzam zzam2 = new zzam(context, executorService, str, zzaf, zzat, zzgi, zzba);
        this.zzi = zzam;
        this.zzf = zzb3;
        zzb3.zzh(z);
        this.zzg = zzbt2;
        if (zzbt2 != null) {
            zzbt2.zze(str2);
            zzbt2.zzb(zzaf2.getAdContainer());
            arrayList.add(zzbt2);
            zzat2.zza(zzbt2);
            for (FriendlyObstruction zza2 : zzaf2.zzb()) {
                zzbt2.zza(zza2);
            }
            zzaf2.zzc(zzbt2);
        }
        zzba2.zzi(str2, JavaScriptMessage.MsgChannel.adsManager, new zzah(this));
        zzba2.zzi(str2, JavaScriptMessage.MsgChannel.nativeUi, new zzaj(this));
        zzba2.zzi(str2, JavaScriptMessage.MsgChannel.videoDisplay1, this.zzh);
        zzba2.zzi(str2, JavaScriptMessage.MsgChannel.videoDisplay2, this.zzh);
        zzba2.zzi(str2, JavaScriptMessage.MsgChannel.displayContainer, new zzai(this));
        zzba2.zzh(this.zzf, str2);
        Application zzb4 = zzel.zzb(context);
        if (zzb4 != null) {
            zzen zzen = new zzen(zzb4);
            this.zzp = zzen;
            zzen.zza(this);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(AdError.AdErrorType adErrorType, AdError.AdErrorCode adErrorCode, String str) {
        this.zzl = null;
        this.zzd.zzc(new zzd(new AdError(adErrorType, adErrorCode, str)));
    }

    private final void zzt(String str) {
        if (zzel.zzd(this.zze, this.zza.zza)) {
            this.zza.zzb().requestFocus();
            this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.userInteraction, JavaScriptMessage.MsgType.focusUiElement, str, (Object) null));
        }
    }

    private final boolean zzu() {
        return this.zzm.getFocusSkipButtonWhenAvailable();
    }

    public final void addAdErrorListener(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zzd.zza(adErrorListener);
    }

    public final void addAdEventListener(AdEvent.AdEventListener adEventListener) {
        this.zzc.add(adEventListener);
    }

    public void destroy() {
        this.zzn = true;
        zzbt zzbt = this.zzg;
        if (zzbt != null) {
            zzbt.zzg();
        }
        this.zza.zzl(this.zzb);
        this.zzf.zzi();
        zzen zzen = this.zzp;
        if (zzen != null) {
            zzen.zzb();
        }
        this.zzh.zzc();
    }

    public final void focus() {
        zzt(this.zzb);
    }

    public final VideoProgressUpdate getAdProgress() {
        return this.zzn ? VideoProgressUpdate.VIDEO_TIME_NOT_READY : this.zzh.getAdProgress();
    }

    public final AdProgressInfo getAdProgressInfo() {
        return this.zzl;
    }

    public final Ad getCurrentAd() {
        return this.zzj;
    }

    public final void init() {
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.init, this.zzb, zzb(this.zzm)));
        this.zzh.zzd();
    }

    public final void removeAdErrorListener(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zzd.zzd(adErrorListener);
    }

    public final void removeAdEventListener(AdEvent.AdEventListener adEventListener) {
        this.zzc.remove(adEventListener);
    }

    /* access modifiers changed from: package-private */
    public Map zzb(AdsRenderingSettings adsRenderingSettings) {
        HashMap hashMap = new HashMap();
        hashMap.put("adsRenderingSettings", zzg.builder(adsRenderingSettings).build());
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f4 A[LOOP:1: B:43:0x00ee->B:45:0x00f4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0107 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0108  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzc(com.google.ads.interactivemedia.v3.impl.zzag r9) {
        /*
            r8 = this;
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r0 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.ALL_ADS_COMPLETED
            com.google.ads.interactivemedia.v3.impl.JavaScriptMessage$MsgType r0 = com.google.ads.interactivemedia.v3.impl.JavaScriptMessage.MsgType.activate
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r0 = r9.zza
            int r1 = r0.ordinal()
            com.google.ads.interactivemedia.v3.impl.data.zzc r2 = r9.zzb
            r3 = 3
            r4 = 0
            if (r1 == r3) goto L_0x00db
            r3 = 17
            if (r1 == r3) goto L_0x0063
            r3 = 24
            if (r1 == r3) goto L_0x00db
            r3 = 5
            if (r1 == r3) goto L_0x005c
            r3 = 6
            if (r1 == r3) goto L_0x0055
            r3 = 20
            if (r1 == r3) goto L_0x0050
            r3 = 21
            if (r1 == r3) goto L_0x004a
            switch(r1) {
                case 13: goto L_0x003d;
                case 14: goto L_0x00db;
                case 15: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x00dd
        L_0x002b:
            if (r2 == 0) goto L_0x0030
            r8.zzs(r2)
        L_0x0030:
            boolean r1 = r8.zzu()
            if (r1 == 0) goto L_0x00dd
            java.lang.String r1 = r8.zzb
            r8.zzt(r1)
            goto L_0x00dd
        L_0x003d:
            boolean r1 = r8.zzu()
            if (r1 == 0) goto L_0x00dd
            java.lang.String r1 = r8.zzb
            r8.zzt(r1)
            goto L_0x00dd
        L_0x004a:
            com.google.ads.interactivemedia.v3.api.AdProgressInfo r1 = r9.zze
            r8.zzl = r1
            goto L_0x00dd
        L_0x0050:
            r8.zzs(r2)
            goto L_0x00dd
        L_0x0055:
            com.google.ads.interactivemedia.v3.impl.zzb r1 = r8.zzf
            r1.zzi()
            goto L_0x00dd
        L_0x005c:
            com.google.ads.interactivemedia.v3.impl.zzb r1 = r8.zzf
            r1.zzg()
            goto L_0x00dd
        L_0x0063:
            java.util.List r1 = r9.zzf
            if (r1 == 0) goto L_0x00d5
            android.content.Context r1 = r8.zze
            boolean r1 = com.google.ads.interactivemedia.v3.internal.zzel.zzc(r1, r4)
            if (r1 == 0) goto L_0x00d5
            java.util.List r1 = r9.zzf
            com.google.android.tv.ads.AdsControlsManager r2 = new com.google.android.tv.ads.AdsControlsManager     // Catch:{ RuntimeException -> 0x00ce }
            android.content.Context r3 = r8.zze     // Catch:{ RuntimeException -> 0x00ce }
            r2.<init>(r3)     // Catch:{ RuntimeException -> 0x00ce }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x00ce }
            r5 = 1
            r3.<init>(r5)     // Catch:{ RuntimeException -> 0x00ce }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ RuntimeException -> 0x00ce }
        L_0x0082:
            boolean r5 = r1.hasNext()     // Catch:{ RuntimeException -> 0x00ce }
            if (r5 == 0) goto L_0x00c2
            java.lang.Object r5 = r1.next()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.ads.interactivemedia.v3.impl.data.zzbk r5 = (com.google.ads.interactivemedia.v3.impl.data.zzbk) r5     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r6 = com.google.android.tv.ads.IconClickFallbackImage.builder()     // Catch:{ RuntimeException -> 0x00ce }
            int r7 = r5.width()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r6 = r6.setWidth(r7)     // Catch:{ RuntimeException -> 0x00ce }
            int r7 = r5.height()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r6 = r6.setHeight(r7)     // Catch:{ RuntimeException -> 0x00ce }
            java.lang.String r7 = r5.alternateText()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r6 = r6.setAltText(r7)     // Catch:{ RuntimeException -> 0x00ce }
            java.lang.String r7 = r5.imageUrl()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r6 = r6.setStaticResourceUri(r7)     // Catch:{ RuntimeException -> 0x00ce }
            java.lang.String r5 = r5.creativeType()     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage$Builder r5 = r6.setCreativeType(r5)     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImage r5 = r5.build()     // Catch:{ RuntimeException -> 0x00ce }
            r3.add(r5)     // Catch:{ RuntimeException -> 0x00ce }
            goto L_0x0082
        L_0x00c2:
            com.google.android.tv.ads.IconClickFallbackImages$Builder r1 = com.google.android.tv.ads.IconClickFallbackImages.builder(r3)     // Catch:{ RuntimeException -> 0x00ce }
            com.google.android.tv.ads.IconClickFallbackImages r1 = r1.build()     // Catch:{ RuntimeException -> 0x00ce }
            r2.handleIconClick(r1)     // Catch:{ RuntimeException -> 0x00ce }
            goto L_0x00dd
        L_0x00ce:
            r1 = move-exception
            java.lang.String r2 = "Failed to handle icon fallback image click."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzb(r2, r1)
            goto L_0x00dd
        L_0x00d5:
            java.lang.String r1 = "Received ICON_TAPPED event without icon click fallback image list."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r1)
            goto L_0x00dd
        L_0x00db:
            r8.zzl = r4
        L_0x00dd:
            com.google.ads.interactivemedia.v3.impl.zze r1 = new com.google.ads.interactivemedia.v3.impl.zze
            com.google.ads.interactivemedia.v3.impl.data.zzc r2 = r8.zzj
            java.util.Map r3 = r9.zzc
            com.google.ads.interactivemedia.v3.api.AdProgressInfo r9 = r9.zze
            r1.<init>(r0, r2, r3, r9)
            java.util.List r9 = r8.zzc
            java.util.Iterator r9 = r9.iterator()
        L_0x00ee:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x00fe
            java.lang.Object r2 = r9.next()
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventListener r2 = (com.google.ads.interactivemedia.v3.api.AdEvent.AdEventListener) r2
            r2.onAdEvent(r1)
            goto L_0x00ee
        L_0x00fe:
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r9 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.COMPLETED
            if (r0 == r9) goto L_0x0108
            com.google.ads.interactivemedia.v3.api.AdEvent$AdEventType r9 = com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType.SKIPPED
            if (r0 != r9) goto L_0x0107
            goto L_0x0108
        L_0x0107:
            return
        L_0x0108:
            r8.zzs(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.zzak.zzc(com.google.ads.interactivemedia.v3.impl.zzag):void");
    }

    /* access modifiers changed from: protected */
    public final zzcc zzg() {
        return this.zzh;
    }

    public final void zzk() {
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.appBackgrounding, this.zzb, (Object) null));
    }

    public final void zzl() {
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.appForegrounding, this.zzb, (Object) null));
    }

    public final void zzm() {
        zzc(new zzag(AdEvent.AdEventType.ICON_FALLBACK_IMAGE_CLOSED, this.zzj));
    }

    /* access modifiers changed from: package-private */
    public final void zzn(String str) {
        if (!this.zzo.zzb(str)) {
            zzq(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.navigationRequestedFailed, zzrp.zze("url", str));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzo(zza zza2) {
        this.zzk = zza2;
        zzc zzc2 = this.zzj;
        if (zzc2 != null) {
            zzc2.setAdUi(zza2);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzp() {
        this.zza.zzm(this.zzb);
        this.zzc.clear();
        this.zzd.zzb();
    }

    /* access modifiers changed from: protected */
    public final void zzq(JavaScriptMessage.MsgChannel msgChannel, JavaScriptMessage.MsgType msgType, Object obj) {
        this.zza.zzn(new JavaScriptMessage(msgChannel, msgType, this.zzb, obj));
    }

    /* access modifiers changed from: protected */
    public final void zzr(JavaScriptMessage.MsgType msgType) {
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsManager, msgType, this.zzb, (Object) null));
    }

    /* access modifiers changed from: package-private */
    public final void zzs(zzc zzc2) {
        this.zzj = zzc2;
        if (zzc2 != null) {
            zzc2.setAdUi(this.zzk);
        }
    }

    public final void init(AdsRenderingSettings adsRenderingSettings) {
        if (adsRenderingSettings != null) {
            this.zzm = adsRenderingSettings;
            this.zzo.zza(adsRenderingSettings);
        }
        Map zzb2 = zzb(this.zzm);
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.init, this.zzb, zzb2));
        this.zzh.zzd();
    }
}
