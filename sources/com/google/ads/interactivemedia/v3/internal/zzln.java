package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzln extends zzlh implements zzlo {
    public zzln() {
        super("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String zzj = zzj();
                parcel2.writeNoException();
                parcel2.writeString(zzj);
                return true;
            case 2:
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                zzli.zzb(parcel);
                zzn(readString, readString2);
                parcel2.writeNoException();
                return true;
            case 3:
                IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                boolean zzp = zzp(asInterface);
                parcel2.writeNoException();
                parcel2.writeInt(zzp ? 1 : 0);
                return true;
            case 4:
                IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                boolean zzq = zzq(asInterface2);
                parcel2.writeNoException();
                parcel2.writeInt(zzq ? 1 : 0);
                return true;
            case 5:
                String readString3 = parcel.readString();
                zzli.zzb(parcel);
                zzo(readString3);
                parcel2.writeNoException();
                return true;
            case 6:
                IObjectWrapper asInterface3 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface4 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                IObjectWrapper zzd = zzd(asInterface3, asInterface4);
                parcel2.writeNoException();
                zzli.zzd(parcel2, zzd);
                return true;
            case 7:
                IObjectWrapper asInterface5 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                String zzf = zzf(asInterface5);
                parcel2.writeNoException();
                parcel2.writeString(zzf);
                return true;
            case 8:
                IObjectWrapper asInterface6 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                String readString4 = parcel.readString();
                zzli.zzb(parcel);
                String zze = zze(asInterface6, readString4);
                parcel2.writeNoException();
                parcel2.writeString(zze);
                return true;
            case 9:
                IObjectWrapper asInterface7 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                zzl(asInterface7);
                parcel2.writeNoException();
                return true;
            case 10:
                IObjectWrapper asInterface8 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface9 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                IObjectWrapper zzc = zzc(asInterface8, asInterface9);
                parcel2.writeNoException();
                zzli.zzd(parcel2, zzc);
                return true;
            case 11:
                parcel.readString();
                int i3 = zzli.zza;
                parcel.readInt();
                zzli.zzb(parcel);
                parcel2.writeNoException();
                parcel2.writeInt(0);
                return true;
            case 12:
                IObjectWrapper asInterface10 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                byte[] createByteArray = parcel.createByteArray();
                zzli.zzb(parcel);
                String zzg = zzg(asInterface10, createByteArray);
                parcel2.writeNoException();
                parcel2.writeString(zzg);
                return true;
            case 13:
                IObjectWrapper asInterface11 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                String zzi = zzi(asInterface11);
                parcel2.writeNoException();
                parcel2.writeString(zzi);
                return true;
            case 14:
                IObjectWrapper asInterface12 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface13 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface14 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                String zzk = zzk(asInterface12, asInterface13, asInterface14);
                parcel2.writeNoException();
                parcel2.writeString(zzk);
                return true;
            case 15:
                IObjectWrapper asInterface15 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                zzm(asInterface15);
                parcel2.writeNoException();
                return true;
            case 17:
                IObjectWrapper asInterface16 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface17 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface18 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                IObjectWrapper asInterface19 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                zzli.zzb(parcel);
                String zzh = zzh(asInterface16, asInterface17, asInterface18, asInterface19);
                parcel2.writeNoException();
                parcel2.writeString(zzh);
                return true;
            case 18:
                boolean zzr = zzr();
                parcel2.writeNoException();
                int i4 = zzli.zza;
                parcel2.writeInt(zzr ? 1 : 0);
                return true;
            case 19:
                boolean zzs = zzs();
                parcel2.writeNoException();
                int i5 = zzli.zza;
                parcel2.writeInt(zzs ? 1 : 0);
                return true;
            case 20:
                int zzb = zzb();
                parcel2.writeNoException();
                parcel2.writeInt(zzb);
                return true;
            default:
                return false;
        }
    }
}
