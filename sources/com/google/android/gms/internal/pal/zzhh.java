package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzhh extends zzfu {
    private final int zze = 9200000;

    public zzhh(Context context, Looper looper, BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener, int i) {
        super(context, looper, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, baseConnectionCallbacks, baseOnConnectionFailedListener, (String) null);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gass.internal.IGassService");
        return queryLocalInterface instanceof zzhm ? (zzhm) queryLocalInterface : new zzhm(iBinder);
    }

    public final int getMinApkVersion() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.gass.internal.IGassService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.gass.START";
    }

    public final zzhm zzp() throws DeadObjectException {
        return (zzhm) super.getService();
    }
}
