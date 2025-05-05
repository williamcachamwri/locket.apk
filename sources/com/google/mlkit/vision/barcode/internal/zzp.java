package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.mlkit_vision_barcode.zzi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzm;
import com.google.android.gms.internal.mlkit_vision_barcode.zzn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzt;
import com.google.android.gms.internal.mlkit_vision_barcode.zzu;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzp implements BarcodeSource {
    private final zzu zza;

    public zzp(zzu zzu) {
        this.zza = zzu;
    }

    private static Barcode.CalendarDateTime zza(zzj zzj) {
        if (zzj == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzj.zza, zzj.zzb, zzj.zzc, zzj.zzd, zzj.zze, zzj.zzf, zzj.zzg, zzj.zzh);
    }

    public final Rect getBoundingBox() {
        zzu zzu = this.zza;
        if (zzu.zze == null) {
            return null;
        }
        int i = 0;
        int i2 = Integer.MIN_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (true) {
            Point[] pointArr = zzu.zze;
            if (i >= pointArr.length) {
                return new Rect(i3, i4, i2, i5);
            }
            Point point = pointArr[i];
            i3 = Math.min(i3, point.x);
            i2 = Math.max(i2, point.x);
            i4 = Math.min(i4, point.y);
            i5 = Math.max(i5, point.y);
            i++;
        }
    }

    public final Barcode.CalendarEvent getCalendarEvent() {
        zzk zzk = this.zza.zzl;
        if (zzk == null) {
            return null;
        }
        return new Barcode.CalendarEvent(zzk.zza, zzk.zzb, zzk.zzc, zzk.zzd, zzk.zze, zza(zzk.zzf), zza(zzk.zzg));
    }

    public final Barcode.ContactInfo getContactInfo() {
        zzl zzl = this.zza.zzm;
        Barcode.PersonName personName = null;
        if (zzl == null) {
            return null;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.zzp zzp = zzl.zza;
        if (zzp != null) {
            personName = new Barcode.PersonName(zzp.zza, zzp.zzb, zzp.zzc, zzp.zzd, zzp.zze, zzp.zzf, zzp.zzg);
        }
        Barcode.PersonName personName2 = personName;
        String str = zzl.zzb;
        String str2 = zzl.zzc;
        zzq[] zzqArr = zzl.zzd;
        ArrayList arrayList = new ArrayList();
        if (zzqArr != null) {
            for (zzq zzq : zzqArr) {
                if (zzq != null) {
                    arrayList.add(new Barcode.Phone(zzq.zzb, zzq.zza));
                }
            }
        }
        zzn[] zznArr = zzl.zze;
        ArrayList arrayList2 = new ArrayList();
        if (zznArr != null) {
            for (zzn zzn : zznArr) {
                if (zzn != null) {
                    arrayList2.add(new Barcode.Email(zzn.zza, zzn.zzb, zzn.zzc, zzn.zzd));
                }
            }
        }
        String[] strArr = zzl.zzf;
        List asList = strArr != null ? Arrays.asList(strArr) : new ArrayList();
        zzi[] zziArr = zzl.zzg;
        ArrayList arrayList3 = new ArrayList();
        if (zziArr != null) {
            for (zzi zzi : zziArr) {
                if (zzi != null) {
                    arrayList3.add(new Barcode.Address(zzi.zza, zzi.zzb));
                }
            }
        }
        return new Barcode.ContactInfo(personName2, str, str2, arrayList, arrayList2, asList, arrayList3);
    }

    public final Point[] getCornerPoints() {
        return this.zza.zze;
    }

    public final String getDisplayValue() {
        return this.zza.zzc;
    }

    public final Barcode.DriverLicense getDriverLicense() {
        zzm zzm = this.zza.zzn;
        if (zzm == null) {
            return null;
        }
        return new Barcode.DriverLicense(zzm.zza, zzm.zzb, zzm.zzc, zzm.zzd, zzm.zze, zzm.zzf, zzm.zzg, zzm.zzh, zzm.zzi, zzm.zzj, zzm.zzk, zzm.zzl, zzm.zzm, zzm.zzn);
    }

    public final Barcode.Email getEmail() {
        zzn zzn = this.zza.zzf;
        if (zzn != null) {
            return new Barcode.Email(zzn.zza, zzn.zzb, zzn.zzc, zzn.zzd);
        }
        return null;
    }

    public final int getFormat() {
        return this.zza.zza;
    }

    public final Barcode.GeoPoint getGeoPoint() {
        zzo zzo = this.zza.zzk;
        if (zzo != null) {
            return new Barcode.GeoPoint(zzo.zza, zzo.zzb);
        }
        return null;
    }

    public final Barcode.Phone getPhone() {
        zzq zzq = this.zza.zzg;
        if (zzq != null) {
            return new Barcode.Phone(zzq.zzb, zzq.zza);
        }
        return null;
    }

    public final byte[] getRawBytes() {
        return this.zza.zzo;
    }

    public final String getRawValue() {
        return this.zza.zzb;
    }

    public final Barcode.Sms getSms() {
        zzr zzr = this.zza.zzh;
        if (zzr != null) {
            return new Barcode.Sms(zzr.zza, zzr.zzb);
        }
        return null;
    }

    public final Barcode.UrlBookmark getUrl() {
        zzs zzs = this.zza.zzj;
        if (zzs != null) {
            return new Barcode.UrlBookmark(zzs.zza, zzs.zzb);
        }
        return null;
    }

    public final int getValueType() {
        return this.zza.zzd;
    }

    public final Barcode.WiFi getWifi() {
        zzt zzt = this.zza.zzi;
        if (zzt != null) {
            return new Barcode.WiFi(zzt.zza, zzt.zzb, zzt.zzc);
        }
        return null;
    }
}
