package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdm;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.internal.measurement.zzdp;
import com.google.android.gms.internal.measurement.zzdu;
import com.google.android.gms.internal.measurement.zzdw;
import io.sentry.protocol.App;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
public class AppMeasurementDynamiteService extends zzdm {
    zzhy zza = null;
    private final Map<Integer, zzjl> zzb = new ArrayMap();

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
    class zza implements zzjm {
        private zzdp zza;

        zza(zzdp zzdp) {
            this.zza = zzdp;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                if (AppMeasurementDynamiteService.this.zza != null) {
                    AppMeasurementDynamiteService.this.zza.zzj().zzu().zza("Event interceptor threw exception", e);
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
    class zzb implements zzjl {
        private zzdp zza;

        zzb(zzdp zzdp) {
            this.zza = zzdp;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zza.zza(str, str2, bundle, j);
            } catch (RemoteException e) {
                if (AppMeasurementDynamiteService.this.zza != null) {
                    AppMeasurementDynamiteService.this.zza.zzj().zzu().zza("Event listener threw exception", e);
                }
            }
        }
    }

    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zze().zza(str, j);
    }

    @EnsuresNonNull({"scion"})
    private final void zza() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle);
    }

    public void clearMeasurementEnabled(long j) throws RemoteException {
        zza();
        this.zza.zzp().zza((Boolean) null);
    }

    public void endAdUnitExposure(String str, long j) throws RemoteException {
        zza();
        this.zza.zze().zzb(str, j);
    }

    public void generateEventId(zzdo zzdo) throws RemoteException {
        zza();
        long zzn = this.zza.zzt().zzn();
        zza();
        this.zza.zzt().zza(zzdo, zzn);
    }

    public void getAppInstanceId(zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzi(this, zzdo));
    }

    public void getCachedAppInstanceId(zzdo zzdo) throws RemoteException {
        zza();
        zza(zzdo, this.zza.zzp().zzag());
    }

    public void getConditionalUserProperties(String str, String str2, zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzl(this, zzdo, str, str2));
    }

    public void getCurrentScreenClass(zzdo zzdo) throws RemoteException {
        zza();
        zza(zzdo, this.zza.zzp().zzah());
    }

    public void getCurrentScreenName(zzdo zzdo) throws RemoteException {
        zza();
        zza(zzdo, this.zza.zzp().zzai());
    }

    public void getGmpAppId(zzdo zzdo) throws RemoteException {
        zza();
        zza(zzdo, this.zza.zzp().zzaj());
    }

    public void getMaxUserProperties(String str, zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzp();
        zzjq.zza(str);
        zza();
        this.zza.zzt().zza(zzdo, 25);
    }

    public void getSessionId(zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzp().zza(zzdo);
    }

    public void getTestFlag(zzdo zzdo, int i) throws RemoteException {
        zza();
        if (i == 0) {
            this.zza.zzt().zza(zzdo, this.zza.zzp().zzak());
        } else if (i == 1) {
            this.zza.zzt().zza(zzdo, this.zza.zzp().zzaf().longValue());
        } else if (i == 2) {
            zzos zzt = this.zza.zzt();
            double doubleValue = this.zza.zzp().zzad().doubleValue();
            Bundle bundle = new Bundle();
            bundle.putDouble("r", doubleValue);
            try {
                zzdo.zza(bundle);
            } catch (RemoteException e) {
                zzt.zzu.zzj().zzu().zza("Error returning double value to wrapper", e);
            }
        } else if (i == 3) {
            this.zza.zzt().zza(zzdo, this.zza.zzp().zzae().intValue());
        } else if (i == 4) {
            this.zza.zzt().zza(zzdo, this.zza.zzp().zzac().booleanValue());
        }
    }

    public void getUserProperties(String str, String str2, boolean z, zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzj(this, zzdo, str, str2, z));
    }

    public void initForTests(Map map) throws RemoteException {
        zza();
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzdw zzdw, long j) throws RemoteException {
        zzhy zzhy = this.zza;
        if (zzhy == null) {
            this.zza = zzhy.zza((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(iObjectWrapper)), zzdw, Long.valueOf(j));
        } else {
            zzhy.zzj().zzu().zza("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzdo zzdo) throws RemoteException {
        zza();
        this.zza.zzl().zzb((Runnable) new zzn(this, zzdo));
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, bundle, z, z2, j);
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzdo zzdo, long j) throws RemoteException {
        Bundle bundle2;
        zza();
        Preconditions.checkNotEmpty(str2);
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("_o", App.TYPE);
        this.zza.zzl().zzb((Runnable) new zzk(this, zzdo, new zzbf(str2, new zzbe(bundle), App.TYPE, j), str));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Object obj;
        Object obj2;
        zza();
        Object obj3 = null;
        if (iObjectWrapper == null) {
            obj = null;
        } else {
            obj = ObjectWrapper.unwrap(iObjectWrapper);
        }
        if (iObjectWrapper2 == null) {
            obj2 = null;
        } else {
            obj2 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        if (iObjectWrapper3 != null) {
            obj3 = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.zza.zzj().zza(i, true, false, str, obj, obj2, obj3);
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdo zzdo, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        Bundle bundle = new Bundle();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzdo.zza(bundle);
        } catch (RemoteException e) {
            this.zza.zzj().zzu().zza("Error returning bundle value to wrapper", e);
        }
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        zza();
        Application.ActivityLifecycleCallbacks zzaa = this.zza.zzp().zzaa();
        if (zzaa != null) {
            this.zza.zzp().zzao();
            zzaa.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void performAction(Bundle bundle, zzdo zzdo, long j) throws RemoteException {
        zza();
        zzdo.zza((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzdp zzdp) throws RemoteException {
        zzjl zzjl;
        zza();
        synchronized (this.zzb) {
            zzjl = this.zzb.get(Integer.valueOf(zzdp.zza()));
            if (zzjl == null) {
                zzjl = new zzb(zzdp);
                this.zzb.put(Integer.valueOf(zzdp.zza()), zzjl);
            }
        }
        this.zza.zzp().zza(zzjl);
    }

    public void resetAnalyticsData(long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(j);
    }

    private final void zza(zzdo zzdo, String str) {
        zza();
        this.zza.zzt().zza(zzdo, str);
    }

    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        zza();
        if (bundle == null) {
            this.zza.zzj().zzg().zza("Conditional user property must not be null");
        } else {
            this.zza.zzp().zzb(bundle, j);
        }
    }

    public void setConsent(Bundle bundle, long j) throws RemoteException {
        zza();
        this.zza.zzp().zzc(bundle, j);
    }

    public void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        zza();
        this.zza.zzp().zzd(bundle, j);
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        zza();
        this.zza.zzq().zza((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        zza();
        this.zza.zzp().zzc(z);
    }

    public void setDefaultEventParameters(Bundle bundle) {
        zza();
        this.zza.zzp().zzc(bundle);
    }

    public void setEventInterceptor(zzdp zzdp) throws RemoteException {
        zza();
        zza zza2 = new zza(zzdp);
        if (this.zza.zzl().zzg()) {
            this.zza.zzp().zza((zzjm) zza2);
        } else {
            this.zza.zzl().zzb((Runnable) new zzm(this, zza2));
        }
    }

    public void setInstanceIdProvider(zzdu zzdu) throws RemoteException {
        zza();
    }

    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(Boolean.valueOf(z));
    }

    public void setMinimumSessionDuration(long j) throws RemoteException {
        zza();
    }

    public void setSessionTimeoutDuration(long j) throws RemoteException {
        zza();
        this.zza.zzp().zzc(j);
    }

    public void setSgtmDebugInfo(Intent intent) throws RemoteException {
        zza();
        this.zza.zzp().zza(intent);
    }

    public void setUserId(String str, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, j);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        zza();
        this.zza.zzp().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void unregisterOnMeasurementEventListener(zzdp zzdp) throws RemoteException {
        zzjl remove;
        zza();
        synchronized (this.zzb) {
            remove = this.zzb.remove(Integer.valueOf(zzdp.zza()));
        }
        if (remove == null) {
            remove = new zzb(zzdp);
        }
        this.zza.zzp().zzb(remove);
    }
}
