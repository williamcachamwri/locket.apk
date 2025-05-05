package com.google.ads.interactivemedia.v3.api;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;
import com.google.ads.interactivemedia.v3.impl.AdsRequestImpl;
import com.google.ads.interactivemedia.v3.impl.ImaSdkSettingsImpl;
import com.google.ads.interactivemedia.v3.impl.data.zzbg;
import com.google.ads.interactivemedia.v3.impl.data.zzbh;
import com.google.ads.interactivemedia.v3.impl.data.zzh;
import com.google.ads.interactivemedia.v3.impl.zzac;
import com.google.ads.interactivemedia.v3.impl.zzal;
import com.google.ads.interactivemedia.v3.impl.zzbx;
import com.google.ads.interactivemedia.v3.impl.zzbz;
import com.google.ads.interactivemedia.v3.impl.zzc;
import com.google.ads.interactivemedia.v3.impl.zzy;
import com.google.ads.interactivemedia.v3.internal.zzahg;
import com.google.ads.interactivemedia.v3.internal.zzahh;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfe;
import com.google.ads.interactivemedia.v3.internal.zzff;
import com.google.ads.interactivemedia.v3.internal.zzfj;
import com.google.ads.interactivemedia.v3.internal.zzgh;
import com.google.ads.interactivemedia.v3.internal.zzvf;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class ImaSdkFactory {
    private static ImaSdkFactory zza;
    private static final zzff zzb = new zzff();
    private int zzc = 0;
    private ExecutorService zzd;

    private ImaSdkFactory() {
    }

    private AdsLoader createAdsLoader(Context context, Uri uri, BaseDisplayContainer baseDisplayContainer, ImaSdkSettings imaSdkSettings) {
        Uri uri2;
        if (uri == null) {
            uri2 = zzgh.zza(imaSdkSettings, context.getPackageName());
        } else {
            uri2 = zzgh.zzb(uri, imaSdkSettings, context.getPackageName());
        }
        return zzb(context, uri2, imaSdkSettings, baseDisplayContainer, zza());
    }

    public static ImaSdkFactory getInstance() {
        if (zza == null) {
            zza = new ImaSdkFactory();
        }
        return zza;
    }

    private final AdsLoader zzb(Context context, Uri uri, ImaSdkSettings imaSdkSettings, BaseDisplayContainer baseDisplayContainer, ExecutorService executorService) {
        long currentTimeMillis = System.currentTimeMillis();
        zzfe zza2 = zzb.zza(context, uri, imaSdkSettings.getTestingConfig(), executorService);
        int i = this.zzc;
        this.zzc = i + 1;
        zzfj zzfj = new zzfj(i);
        zzfj.zzf(zza2.zze());
        zzy zzc2 = zzy.zzc(zza2.zzb(), context, imaSdkSettings, baseDisplayContainer, zzfj, zza2.zzf(), zza2.zzd());
        zzahj zzb2 = zzfj.zzb();
        zzahg zzc3 = zzahh.zzc();
        zzc3.zzb(currentTimeMillis);
        zzc3.zza(System.currentTimeMillis());
        zzb2.zzb(zzc3);
        return zzc2;
    }

    @Deprecated
    public AdDisplayContainer createAdDisplayContainer() {
        return new zzc((ViewGroup) null, (VideoAdPlayer) null);
    }

    public AdsRenderingSettings createAdsRenderingSettings() {
        return new zzh();
    }

    public AdsRequest createAdsRequest() {
        return new AdsRequestImpl();
    }

    public CompanionAdSlot createCompanionAdSlot() {
        return new zzal();
    }

    public FriendlyObstruction createFriendlyObstruction(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        zzbg builder = zzbh.builder();
        builder.view(view);
        builder.purpose(friendlyObstructionPurpose);
        builder.detailedReason(str);
        return builder.build();
    }

    public ImaSdkSettings createImaSdkSettings() {
        return new ImaSdkSettingsImpl();
    }

    public StreamRequest createLiveStreamRequest(String str, String str2) {
        zzbz zzbz = new zzbz();
        zzbz.zzf(str);
        zzbz.zze(str2);
        return zzbz;
    }

    public StreamRequest createPodStreamRequest(String str, String str2, String str3) {
        zzbz zzbz = new zzbz();
        zzbz.zzk(str);
        zzbz.zzi(str2);
        zzbz.zze(str3);
        return zzbz;
    }

    public StreamRequest createPodVodStreamRequest(String str) {
        zzbz zzbz = new zzbz();
        zzbz.zzk(str);
        return zzbz;
    }

    @Deprecated
    public StreamDisplayContainer createStreamDisplayContainer() {
        return new zzbx((ViewGroup) null, (VideoStreamPlayer) null);
    }

    public StreamRequest createVideoStitcherLiveStreamRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        zzbz zzbz = new zzbz();
        zzbz.zzk(str);
        zzbz.zzi(str2);
        zzbz.zzj(str3);
        zzbz.zzn(str4);
        zzbz.zzm(str5);
        zzbz.zzl(str6);
        return zzbz;
    }

    public StreamRequest createVideoStitcherVodStreamRequest(String str, String str2, String str3, String str4, String str5) {
        zzbz zzbz = new zzbz();
        zzbz.zzk(str);
        zzbz.zzn(str2);
        zzbz.zzm(str3);
        zzbz.zzl(str4);
        zzbz.zzp(str5);
        return zzbz;
    }

    public StreamRequest createVodStreamRequest(String str, String str2, String str3) {
        zzbz zzbz = new zzbz();
        zzbz.zzg(str);
        zzbz.zzo(str2);
        zzbz.zze(str3);
        return zzbz;
    }

    public void initialize(Context context, ImaSdkSettings imaSdkSettings) {
        zzb.zzb(context, zzgh.zza(imaSdkSettings, context.getPackageName()), imaSdkSettings.getTestingConfig(), zza());
    }

    public final ExecutorService zza() {
        if (this.zzd == null) {
            zzvf zzvf = new zzvf();
            zzvf.zza("imasdk-%d");
            this.zzd = Executors.newCachedThreadPool(zzvf.zzb());
        }
        return this.zzd;
    }

    @Deprecated
    public AdDisplayContainer createAudioAdDisplayContainer(Context context) {
        context.getClass();
        return new zzac(context, (VideoAdPlayer) null);
    }

    public static AdDisplayContainer createAdDisplayContainer(ViewGroup viewGroup, VideoAdPlayer videoAdPlayer) {
        viewGroup.getClass();
        videoAdPlayer.getClass();
        return new zzc(viewGroup, videoAdPlayer);
    }

    public static StreamDisplayContainer createStreamDisplayContainer(ViewGroup viewGroup, VideoStreamPlayer videoStreamPlayer) {
        viewGroup.getClass();
        videoStreamPlayer.getClass();
        return new zzbx(viewGroup, videoStreamPlayer);
    }

    public static AdDisplayContainer createAudioAdDisplayContainer(Context context, VideoAdPlayer videoAdPlayer) {
        context.getClass();
        videoAdPlayer.getClass();
        return new zzac(context, videoAdPlayer);
    }

    private void initialize(Context context, ImaSdkSettings imaSdkSettings, Uri uri) {
        zzb.zzb(context, zzgh.zzb(uri, imaSdkSettings, context.getPackageName()), imaSdkSettings.getTestingConfig(), zza());
    }

    public AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, AdDisplayContainer adDisplayContainer) {
        return zzb(context, zzgh.zza(imaSdkSettings, context.getPackageName()), imaSdkSettings, adDisplayContainer, zza());
    }

    public StreamRequest createVideoStitcherVodStreamRequest(String str, String str2, String str3, String str4, String str5, String str6) {
        zzbz zzbz = new zzbz();
        zzbz.zzh(str);
        zzbz.zzk(str2);
        zzbz.zzn(str3);
        zzbz.zzm(str4);
        zzbz.zzl(str5);
        zzbz.zzd(str6);
        return zzbz;
    }

    public AdsLoader createAdsLoader(Context context, ImaSdkSettings imaSdkSettings, StreamDisplayContainer streamDisplayContainer) {
        return zzb(context, zzgh.zza(imaSdkSettings, context.getPackageName()), imaSdkSettings, streamDisplayContainer, zza());
    }
}
