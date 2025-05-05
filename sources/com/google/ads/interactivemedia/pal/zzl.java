package com.google.ads.interactivemedia.pal;

import com.google.ads.interactivemedia.pal.NonceRequest;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzl extends NonceRequest.Builder {
    private Boolean zza;
    private Boolean zzb;
    private Integer zzc;
    private Integer zzd;
    private Integer zze;
    private Boolean zzf;
    private Boolean zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private String zzn;
    private PlatformSignalCollector zzo;
    private Set zzp;
    private String zzq;

    zzl() {
    }

    /* synthetic */ zzl(NonceRequest nonceRequest, zzk zzk2) {
        this.zza = nonceRequest.zzb();
        this.zzb = nonceRequest.zzc();
        this.zzc = nonceRequest.zzf();
        this.zzd = nonceRequest.zzg();
        this.zze = nonceRequest.zzh();
        this.zzf = nonceRequest.zze();
        this.zzg = nonceRequest.zzd();
        this.zzh = nonceRequest.zzi();
        this.zzi = nonceRequest.zzj();
        this.zzj = nonceRequest.zzk();
        this.zzk = nonceRequest.zzl();
        this.zzl = nonceRequest.zzm();
        this.zzm = nonceRequest.zzn();
        this.zzn = nonceRequest.zzo();
        this.zzo = nonceRequest.zza();
        this.zzp = nonceRequest.zzq();
        this.zzq = nonceRequest.zzp();
    }

    public final NonceRequest build() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Set set;
        String str8;
        Boolean bool = this.zzb;
        if (bool == null || (str = this.zzh) == null || (str2 = this.zzi) == null || (str3 = this.zzj) == null || (str4 = this.zzk) == null || (str5 = this.zzl) == null || (str6 = this.zzm) == null || (str7 = this.zzn) == null || (set = this.zzp) == null || (str8 = this.zzq) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zzb == null) {
                sb.append(" iconsSupported");
            }
            if (this.zzh == null) {
                sb.append(" descriptionURL");
            }
            if (this.zzi == null) {
                sb.append(" omidPartnerName");
            }
            if (this.zzj == null) {
                sb.append(" omidPartnerVersion");
            }
            if (this.zzk == null) {
                sb.append(" omidVersion");
            }
            if (this.zzl == null) {
                sb.append(" playerType");
            }
            if (this.zzm == null) {
                sb.append(" playerVersion");
            }
            if (this.zzn == null) {
                sb.append(" ppid");
            }
            if (this.zzp == null) {
                sb.append(" supportedApiFrameworks");
            }
            if (this.zzq == null) {
                sb.append(" sessionId");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzn(this.zza, bool, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, str, str2, str3, str4, str5, str6, str7, this.zzo, set, str8, (zzm) null);
    }

    public final NonceRequest.Builder continuousPlayback(Boolean bool) {
        this.zza = bool;
        return this;
    }

    public final NonceRequest.Builder descriptionURL(String str) {
        if (str != null) {
            this.zzh = str;
            return this;
        }
        throw new NullPointerException("Null descriptionURL");
    }

    public final NonceRequest.Builder iconsSupported(Boolean bool) {
        if (bool != null) {
            this.zzb = bool;
            return this;
        }
        throw new NullPointerException("Null iconsSupported");
    }

    public final NonceRequest.Builder nonceLengthLimit(Integer num) {
        this.zzc = num;
        return this;
    }

    public final NonceRequest.Builder omidPartnerName(String str) {
        if (str != null) {
            this.zzi = str;
            return this;
        }
        throw new NullPointerException("Null omidPartnerName");
    }

    public final NonceRequest.Builder omidPartnerVersion(String str) {
        if (str != null) {
            this.zzj = str;
            return this;
        }
        throw new NullPointerException("Null omidPartnerVersion");
    }

    public final NonceRequest.Builder omidVersion(String str) {
        if (str != null) {
            this.zzk = str;
            return this;
        }
        throw new NullPointerException("Null omidVersion");
    }

    public final NonceRequest.Builder platformSignalCollector(PlatformSignalCollector platformSignalCollector) {
        this.zzo = platformSignalCollector;
        return this;
    }

    public final NonceRequest.Builder playerType(String str) {
        if (str != null) {
            this.zzl = str;
            return this;
        }
        throw new NullPointerException("Null playerType");
    }

    public final NonceRequest.Builder playerVersion(String str) {
        if (str != null) {
            this.zzm = str;
            return this;
        }
        throw new NullPointerException("Null playerVersion");
    }

    public final NonceRequest.Builder ppid(String str) {
        if (str != null) {
            this.zzn = str;
            return this;
        }
        throw new NullPointerException("Null ppid");
    }

    public final NonceRequest.Builder sessionId(String str) {
        if (str != null) {
            this.zzq = str;
            return this;
        }
        throw new NullPointerException("Null sessionId");
    }

    public final NonceRequest.Builder supportedApiFrameworks(Set<Integer> set) {
        if (set != null) {
            this.zzp = set;
            return this;
        }
        throw new NullPointerException("Null supportedApiFrameworks");
    }

    public final NonceRequest.Builder videoPlayerHeight(Integer num) {
        this.zzd = num;
        return this;
    }

    public final NonceRequest.Builder videoPlayerWidth(Integer num) {
        this.zze = num;
        return this;
    }

    public final NonceRequest.Builder willAdAutoPlay(Boolean bool) {
        this.zzg = bool;
        return this;
    }

    public final NonceRequest.Builder willAdPlayMuted(Boolean bool) {
        this.zzf = bool;
        return this;
    }
}
