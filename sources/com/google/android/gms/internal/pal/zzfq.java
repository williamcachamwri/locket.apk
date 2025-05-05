package com.google.android.gms.internal.pal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzfq extends zzfk implements zzfr {
    public zzfq() {
        super("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                parcel2.writeNoException();
                parcel2.writeString(zzj());
                break;
            case 2:
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                zzfl.zzb(parcel);
                zzn(readString, readString2);
                parcel2.writeNoException();
                break;
            case 3:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                boolean zzp = zzp(asInterface);
                parcel2.writeNoException();
                zzfl.zzc(parcel2, zzp);
                break;
            case 4:
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                boolean zzq = zzq(asInterface2);
                parcel2.writeNoException();
                zzfl.zzc(parcel2, zzq);
                break;
            case 5:
                String readString3 = parcel.readString();
                zzfl.zzb(parcel);
                zzo(readString3);
                parcel2.writeNoException();
                break;
            case 6:
                IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                IObjectWrapper zzd = zzd(asInterface3, asInterface4);
                parcel2.writeNoException();
                zzfl.zze(parcel2, zzd);
                break;
            case 7:
                IObjectWrapper asInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                String zzf = zzf(asInterface5);
                parcel2.writeNoException();
                parcel2.writeString(zzf);
                break;
            case 8:
                IObjectWrapper asInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String readString4 = parcel.readString();
                zzfl.zzb(parcel);
                String zze = zze(asInterface6, readString4);
                parcel2.writeNoException();
                parcel2.writeString(zze);
                break;
            case 9:
                IObjectWrapper asInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                zzl(asInterface7);
                parcel2.writeNoException();
                break;
            case 10:
                IObjectWrapper asInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                IObjectWrapper zzc = zzc(asInterface8, asInterface9);
                parcel2.writeNoException();
                zzfl.zze(parcel2, zzc);
                break;
            case 11:
                String readString5 = parcel.readString();
                boolean zzf2 = zzfl.zzf(parcel);
                zzfl.zzb(parcel);
                boolean zzr = zzr(readString5, zzf2);
                parcel2.writeNoException();
                zzfl.zzc(parcel2, zzr);
                break;
            case 12:
                IObjectWrapper asInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                byte[] createByteArray = parcel.createByteArray();
                zzfl.zzb(parcel);
                String zzg = zzg(asInterface10, createByteArray);
                parcel2.writeNoException();
                parcel2.writeString(zzg);
                break;
            case 13:
                IObjectWrapper asInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                String zzi = zzi(asInterface11);
                parcel2.writeNoException();
                parcel2.writeString(zzi);
                break;
            case 14:
                IObjectWrapper asInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface13 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface14 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                String zzk = zzk(asInterface12, asInterface13, asInterface14);
                parcel2.writeNoException();
                parcel2.writeString(zzk);
                break;
            case 15:
                IObjectWrapper asInterface15 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                zzm(asInterface15);
                parcel2.writeNoException();
                break;
            case 17:
                IObjectWrapper asInterface16 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface17 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface18 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface19 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzfl.zzb(parcel);
                String zzh = zzh(asInterface16, asInterface17, asInterface18, asInterface19);
                parcel2.writeNoException();
                parcel2.writeString(zzh);
                break;
            case 18:
                parcel2.writeNoException();
                zzfl.zzc(parcel2, true);
                break;
            case 19:
                parcel2.writeNoException();
                zzfl.zzc(parcel2, true);
                break;
            case 20:
                int zzb = zzb();
                parcel2.writeNoException();
                parcel2.writeInt(zzb);
                break;
            default:
                return false;
        }
        return true;
    }
}
