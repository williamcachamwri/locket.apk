package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzoo extends zzlr {
    private final int zze;

    public zzoo(Context context, Looper looper, BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener, int i) {
        super(context, looper, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, baseConnectionCallbacks, baseOnConnectionFailedListener, (String) null);
        this.zze = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.gass.internal.IGassService");
        return queryLocalInterface instanceof zzot ? (zzot) queryLocalInterface : new zzot(iBinder);
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

    public final zzot zzp() throws DeadObjectException {
        return (zzot) super.getService();
    }
}
