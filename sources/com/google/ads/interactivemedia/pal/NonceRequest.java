package com.google.ads.interactivemedia.pal;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class NonceRequest {

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    public static abstract class Builder {
        public abstract NonceRequest build();

        public abstract Builder continuousPlayback(Boolean bool);

        public abstract Builder descriptionURL(String str);

        public abstract Builder iconsSupported(Boolean bool);

        public abstract Builder nonceLengthLimit(Integer num);

        public abstract Builder omidPartnerName(String str);

        public abstract Builder omidPartnerVersion(String str);

        public abstract Builder omidVersion(String str);

        public abstract Builder platformSignalCollector(PlatformSignalCollector platformSignalCollector);

        public abstract Builder playerType(String str);

        public abstract Builder playerVersion(String str);

        public abstract Builder ppid(String str);

        public abstract Builder sessionId(String str);

        public abstract Builder supportedApiFrameworks(Set<Integer> set);

        public abstract Builder videoPlayerHeight(Integer num);

        public abstract Builder videoPlayerWidth(Integer num);

        public abstract Builder willAdAutoPlay(Boolean bool);

        public abstract Builder willAdPlayMuted(Boolean bool);
    }

    public static Builder builder() {
        zzl zzl = new zzl();
        zzl.willAdPlayMuted((Boolean) null);
        zzl.willAdAutoPlay((Boolean) null);
        zzl.continuousPlayback((Boolean) null);
        zzl.iconsSupported(false);
        zzl.nonceLengthLimit((Integer) null);
        zzl.videoPlayerHeight((Integer) null);
        zzl.videoPlayerWidth((Integer) null);
        zzl.platformSignalCollector((PlatformSignalCollector) null);
        zzl.descriptionURL("");
        zzl.omidPartnerName("");
        zzl.omidPartnerVersion("");
        zzl.omidVersion("");
        zzl.playerType("");
        zzl.playerVersion("");
        zzl.ppid("");
        zzl.supportedApiFrameworks(new TreeSet());
        zzl.sessionId(UUID.randomUUID().toString());
        return zzl;
    }

    public abstract Builder toBuilder();

    public abstract PlatformSignalCollector zza();

    public abstract Boolean zzb();

    public abstract Boolean zzc();

    public abstract Boolean zzd();

    public abstract Boolean zze();

    public abstract Integer zzf();

    public abstract Integer zzg();

    public abstract Integer zzh();

    public abstract String zzi();

    public abstract String zzj();

    public abstract String zzk();

    public abstract String zzl();

    public abstract String zzm();

    public abstract String zzn();

    public abstract String zzo();

    public abstract String zzp();

    public abstract Set zzq();
}
