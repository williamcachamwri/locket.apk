package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxt;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxu;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxy;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzya;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyb;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzn implements BarcodeSource {
    private final zzyb zza;

    public zzn(zzyb zzyb) {
        this.zza = zzyb;
    }

    private static Barcode.CalendarDateTime zza(zzxq zzxq) {
        if (zzxq == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzxq.zzf(), zzxq.zzd(), zzxq.zza(), zzxq.zzb(), zzxq.zzc(), zzxq.zze(), zzxq.zzh(), zzxq.zzg());
    }

    public final Rect getBoundingBox() {
        Point[] zzo = this.zza.zzo();
        if (zzo == null) {
            return null;
        }
        int i = Integer.MIN_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        for (Point point : zzo) {
            i2 = Math.min(i2, point.x);
            i = Math.max(i, point.x);
            i3 = Math.min(i3, point.y);
            i4 = Math.max(i4, point.y);
        }
        return new Rect(i2, i3, i, i4);
    }

    public final Barcode.CalendarEvent getCalendarEvent() {
        zzxr zzc = this.zza.zzc();
        if (zzc != null) {
            return new Barcode.CalendarEvent(zzc.zzg(), zzc.zzc(), zzc.zzd(), zzc.zze(), zzc.zzf(), zza(zzc.zzb()), zza(zzc.zza()));
        }
        return null;
    }

    public final Barcode.ContactInfo getContactInfo() {
        List list;
        zzxs zzd = this.zza.zzd();
        Barcode.PersonName personName = null;
        if (zzd == null) {
            return null;
        }
        zzxw zza2 = zzd.zza();
        if (zza2 != null) {
            personName = new Barcode.PersonName(zza2.zzb(), zza2.zzf(), zza2.zze(), zza2.zza(), zza2.zzd(), zza2.zzc(), zza2.zzg());
        }
        Barcode.PersonName personName2 = personName;
        String zzb = zzd.zzb();
        String zzc = zzd.zzc();
        zzxx[] zzf = zzd.zzf();
        ArrayList arrayList = new ArrayList();
        if (zzf != null) {
            for (zzxx zzxx : zzf) {
                if (zzxx != null) {
                    arrayList.add(new Barcode.Phone(zzxx.zzb(), zzxx.zza()));
                }
            }
        }
        zzxu[] zze = zzd.zze();
        ArrayList arrayList2 = new ArrayList();
        if (zze != null) {
            for (zzxu zzxu : zze) {
                if (zzxu != null) {
                    arrayList2.add(new Barcode.Email(zzxu.zza(), zzxu.zzb(), zzxu.zzd(), zzxu.zzc()));
                }
            }
        }
        if (zzd.zzg() != null) {
            list = Arrays.asList((String[]) Preconditions.checkNotNull(zzd.zzg()));
        } else {
            list = new ArrayList();
        }
        List list2 = list;
        zzxp[] zzd2 = zzd.zzd();
        ArrayList arrayList3 = new ArrayList();
        if (zzd2 != null) {
            for (zzxp zzxp : zzd2) {
                if (zzxp != null) {
                    arrayList3.add(new Barcode.Address(zzxp.zza(), zzxp.zzb()));
                }
            }
        }
        return new Barcode.ContactInfo(personName2, zzb, zzc, arrayList, arrayList2, list2, arrayList3);
    }

    public final Point[] getCornerPoints() {
        return this.zza.zzo();
    }

    public final String getDisplayValue() {
        return this.zza.zzl();
    }

    public final Barcode.DriverLicense getDriverLicense() {
        zzxt zze = this.zza.zze();
        if (zze != null) {
            return new Barcode.DriverLicense(zze.zzf(), zze.zzh(), zze.zzn(), zze.zzl(), zze.zzi(), zze.zzc(), zze.zza(), zze.zzb(), zze.zzd(), zze.zzm(), zze.zzj(), zze.zzg(), zze.zze(), zze.zzk());
        }
        return null;
    }

    public final Barcode.Email getEmail() {
        zzxu zzf = this.zza.zzf();
        if (zzf == null) {
            return null;
        }
        return new Barcode.Email(zzf.zza(), zzf.zzb(), zzf.zzd(), zzf.zzc());
    }

    public final int getFormat() {
        return this.zza.zza();
    }

    public final Barcode.GeoPoint getGeoPoint() {
        zzxv zzg = this.zza.zzg();
        if (zzg != null) {
            return new Barcode.GeoPoint(zzg.zza(), zzg.zzb());
        }
        return null;
    }

    public final Barcode.Phone getPhone() {
        zzxx zzh = this.zza.zzh();
        if (zzh != null) {
            return new Barcode.Phone(zzh.zzb(), zzh.zza());
        }
        return null;
    }

    public final byte[] getRawBytes() {
        return this.zza.zzn();
    }

    public final String getRawValue() {
        return this.zza.zzm();
    }

    public final Barcode.Sms getSms() {
        zzxy zzi = this.zza.zzi();
        if (zzi != null) {
            return new Barcode.Sms(zzi.zza(), zzi.zzb());
        }
        return null;
    }

    public final Barcode.UrlBookmark getUrl() {
        zzxz zzj = this.zza.zzj();
        if (zzj != null) {
            return new Barcode.UrlBookmark(zzj.zza(), zzj.zzb());
        }
        return null;
    }

    public final int getValueType() {
        return this.zza.zzb();
    }

    public final Barcode.WiFi getWifi() {
        zzya zzk = this.zza.zzk();
        if (zzk != null) {
            return new Barcode.WiFi(zzk.zzc(), zzk.zzb(), zzk.zza());
        }
        return null;
    }
}
