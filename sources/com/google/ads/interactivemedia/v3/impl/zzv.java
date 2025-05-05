package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamManager;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.internal.zzahg;
import com.google.ads.interactivemedia.v3.internal.zzahh;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzuv;
import java.util.List;
import java.util.SortedSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzv implements zzbh {
    final /* synthetic */ zzy zza;

    zzv(zzy zzy) {
        this.zza = zzy;
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        Object obj;
        Object obj2;
        zzbu zzbu;
        StreamManager streamManager;
        String zzd = javaScriptMessage.zzd();
        JavaScriptMessage.MsgType zzb = javaScriptMessage.zzb();
        zzbu zzbu2 = (zzbu) javaScriptMessage.zzc();
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = zzb.ordinal();
        if (ordinal == 11) {
            zzbu zzbu3 = zzbu2;
            if (zzbu3 == null) {
                zzy zzy = this.zza;
                AdError.AdErrorType adErrorType = AdError.AdErrorType.LOAD;
                AdError.AdErrorCode adErrorCode = AdError.AdErrorCode.INTERNAL_ERROR;
                if (zzy.zzg.get(zzd) != null) {
                    obj = ((AdsRequest) this.zza.zzg.get(zzd)).getUserRequestContext();
                } else {
                    obj = this.zza.zzh.get(zzd) != null ? ((StreamRequest) this.zza.zzh.get(zzd)).getUserRequestContext() : new Object();
                }
                this.zza.zze.zzc(new zzd(new AdError(adErrorType, adErrorCode, "adsLoaded message did not contain cue points."), obj));
                return;
            }
            zzy zzy2 = this.zza;
            List<Float> list = zzbu3.adCuePoints;
            SortedSet<Float> sortedSet = zzbu3.internalCuePoints;
            boolean z = zzbu3.monitorAppLifecycle;
            AdDisplayContainer adDisplayContainer = (AdDisplayContainer) zzy2.zzk;
            AdsRequest adsRequest = (AdsRequest) zzy2.zzg.get(zzd);
            if (adsRequest == null) {
                this.zza.zze.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Request not found for session id: ".concat(String.valueOf(zzd))), new Object()));
                return;
            }
            ContentProgressProvider contentProgressProvider = adsRequest.getContentProgressProvider();
            AdError adError = null;
            zzar zzar = contentProgressProvider != null ? new zzar(contentProgressProvider, 200) : null;
            this.zza.zzi.zzc(adsRequest.getContentUrl());
            if (sortedSet != null && !sortedSet.isEmpty() && zzar == null) {
                adError = new AdError(AdError.AdErrorType.PLAY, AdError.AdErrorCode.PLAYLIST_NO_CONTENT_TRACKING, "Unable to handle cue points, no content progress provider configured.");
            }
            if (adError != null) {
                this.zza.zze.zzc(new zzd(adError, adsRequest.getUserRequestContext()));
                return;
            }
            zzy zzy3 = this.zza;
            zzba zze = zzy3.zzc;
            zzcd zzcd = new zzcd(zzy3.zzc.zzb(), adDisplayContainer.getAdContainer());
            zzy zzy4 = this.zza;
            zzbt zzf = zzy4.zzi;
            zzat zzat = new zzat(zzy4.zzp);
            zzy zzy5 = this.zza;
            zzy.zzn(this.zza, new zzaa((AdsManager) zzz.zza(zzd, zze, zzcd, adDisplayContainer, zzar, list, sortedSet, zzf, zzat, zzy5.zzo, zzy5.zza, z), adsRequest.getUserRequestContext()));
            zzahj zzc = this.zza.zzp.zzc(zzd);
            zzahg zzahg = (zzahg) zzc.zza().zzaz();
            zzahg zzc2 = zzahh.zzc();
            zzc2.zza(System.currentTimeMillis());
            zzahg.zzaj((zzahh) zzc2.zzal());
            zzahg zzahg2 = zzahg;
            zzc.zzg(zzahg);
            this.zza.zzp.zze(zzd);
        } else if (ordinal == 31) {
            zzbu zzbu4 = zzbu2;
            AdError.AdErrorType adErrorType2 = AdError.AdErrorType.LOAD;
            int i = zzbu4.errorCode;
            String zzf2 = zzba.zzf(zzbu4.errorMessage, zzbu4.innerError);
            if (this.zza.zzg.get(zzd) != null) {
                obj2 = ((AdsRequest) this.zza.zzg.get(zzd)).getUserRequestContext();
            } else {
                obj2 = this.zza.zzh.get(zzd) != null ? ((StreamRequest) this.zza.zzh.get(zzd)).getUserRequestContext() : new Object();
            }
            this.zza.zze.zzc(new zzd(new AdError(adErrorType2, i, zzf2), obj2));
        } else if (ordinal == 71) {
            String str = zzbu2.streamId;
            boolean z2 = zzbu2.monitorAppLifecycle;
            zzy zzy6 = this.zza;
            StreamDisplayContainer streamDisplayContainer = (StreamDisplayContainer) zzy6.zzk;
            StreamRequest streamRequest = (StreamRequest) zzy6.zzh.get(zzd);
            if (streamRequest == null) {
                this.zza.zze.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Request not found for session id: ".concat(String.valueOf(zzd))), new Object()));
                zzbu = zzbu2;
            } else {
                this.zza.zzi.zzc(streamRequest.getContentUrl());
                this.zza.zzi.zzd(true);
                zzy zzy7 = this.zza;
                zzba zze2 = zzy7.zzc;
                zzcd zzcd2 = new zzcd(zzy7.zzc.zzb(), streamDisplayContainer.getAdContainer());
                zzy zzy8 = this.zza;
                String manifestSuffix = streamRequest.getManifestSuffix();
                zzbt zzf3 = zzy8.zzi;
                zzat zzat2 = new zzat(zzy8.zzp);
                zzy zzy9 = this.zza;
                zzuv zzi = zzy9.zzo;
                Context zza2 = zzy9.zza;
                zzat zzat3 = zzat2;
                String str2 = zzd;
                zzbu zzbu5 = zzbu2;
                zzba zzba = zze2;
                new zzby(str2, zzba, streamDisplayContainer, new zzcb(str2, zze2, zzat3, streamDisplayContainer, manifestSuffix, new zzar(streamDisplayContainer.getVideoStreamPlayer(), 200), zzcd2, zza2), new zzb(zzd, zzba, streamDisplayContainer.getAdContainer()), zzf3, zzat3, zzi, zza2, str, z2);
                zzy.zzn(zzy7, new zzaa(streamManager, streamRequest.getUserRequestContext()));
                zzahj zzc3 = this.zza.zzp.zzc(zzd);
                zzahg zzahg3 = (zzahg) zzc3.zza().zzaz();
                zzahg zzc4 = zzahh.zzc();
                zzc4.zza(System.currentTimeMillis());
                zzahg3.zzaj((zzahh) zzc4.zzal());
                zzahg zzahg4 = zzahg3;
                zzc3.zzg(zzahg3);
                this.zza.zzp.zze(zzd);
                zzbu = zzbu5;
            }
            zzfk.zzc("Stream initialized with streamId: ".concat(String.valueOf(zzbu.streamId)));
        }
    }
}
