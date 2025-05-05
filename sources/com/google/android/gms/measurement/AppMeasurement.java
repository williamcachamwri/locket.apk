package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdw;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhy;
import com.google.android.gms.measurement.internal.zzjf;
import com.google.android.gms.measurement.internal.zzjl;
import com.google.android.gms.measurement.internal.zzjm;
import com.google.android.gms.measurement.internal.zzlb;
import com.google.android.gms.measurement.internal.zzlh;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zza zzb;

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.mAppId = (String) zzjf.zza(bundle, "app_id", String.class, null);
            this.mOrigin = (String) zzjf.zza(bundle, "origin", String.class, null);
            this.mName = (String) zzjf.zza(bundle, "name", String.class, null);
            this.mValue = zzjf.zza(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
            this.mTriggerTimeout = ((Long) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
            this.mTimedOutEventParams = (Bundle) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
            this.mTriggeredEventName = (String) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
            this.mTriggeredEventParams = (Bundle) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
            this.mTimeToLive = ((Long) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
            this.mExpiredEventParams = (Bundle) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
            this.mActive = ((Boolean) zzjf.zza(bundle, "active", Boolean.class, false)).booleanValue();
            this.mCreationTimestamp = ((Long) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzjf.zza(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.class, 0L)).longValue();
        }

        public ConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
            Preconditions.checkNotNull(conditionalUserProperty);
            this.mAppId = conditionalUserProperty.mAppId;
            this.mOrigin = conditionalUserProperty.mOrigin;
            this.mCreationTimestamp = conditionalUserProperty.mCreationTimestamp;
            this.mName = conditionalUserProperty.mName;
            Object obj = conditionalUserProperty.mValue;
            if (obj != null) {
                Object zza = zzlh.zza(obj);
                this.mValue = zza;
                if (zza == null) {
                    this.mValue = conditionalUserProperty.mValue;
                }
            }
            this.mActive = conditionalUserProperty.mActive;
            this.mTriggerEventName = conditionalUserProperty.mTriggerEventName;
            this.mTriggerTimeout = conditionalUserProperty.mTriggerTimeout;
            this.mTimedOutEventName = conditionalUserProperty.mTimedOutEventName;
            if (conditionalUserProperty.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(conditionalUserProperty.mTimedOutEventParams);
            }
            this.mTriggeredEventName = conditionalUserProperty.mTriggeredEventName;
            if (conditionalUserProperty.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(conditionalUserProperty.mTriggeredEventParams);
            }
            this.mTriggeredTimestamp = conditionalUserProperty.mTriggeredTimestamp;
            this.mTimeToLive = conditionalUserProperty.mTimeToLive;
            this.mExpiredEventName = conditionalUserProperty.mExpiredEventName;
            if (conditionalUserProperty.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(conditionalUserProperty.mExpiredEventParams);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    public interface EventInterceptor extends zzjm {
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    public interface OnEventListener extends zzjl {
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    private static abstract class zza implements zzlb {
        private zza() {
        }

        /* access modifiers changed from: package-private */
        public abstract Boolean zza();

        /* access modifiers changed from: package-private */
        public abstract Map<String, Object> zza(boolean z);

        /* access modifiers changed from: package-private */
        public abstract Double zzb();

        /* access modifiers changed from: package-private */
        public abstract Integer zzc();

        /* access modifiers changed from: package-private */
        public abstract Long zzd();

        /* access modifiers changed from: package-private */
        public abstract String zze();
    }

    public int getMaxUserProperties(String str) {
        return this.zzb.zza(str);
    }

    public long generateEventId() {
        return this.zzb.zzf();
    }

    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zza(context, (String) null, (String) null);
    }

    private static AppMeasurement zza(Context context, String str, String str2) {
        if (zza == null) {
            synchronized (AppMeasurement.class) {
                if (zza == null) {
                    zzlb zza2 = zza(context, (Bundle) null);
                    if (zza2 != null) {
                        zza = new AppMeasurement(zza2);
                    } else {
                        zza = new AppMeasurement(zzhy.zza(context, new zzdw(0, 0, true, (String) null, (String) null, (String) null, (Bundle) null, (String) null), (Long) null));
                    }
                }
            }
        }
        return zza;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.measurement.internal.zzlb zza(android.content.Context r5, android.os.Bundle r6) {
        /*
            r6 = 0
            java.lang.String r0 = "com.google.firebase.analytics.FirebaseAnalytics"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0025 }
            java.lang.String r1 = "getScionFrontendApiImplementation"
            r2 = 2
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{  }
            java.lang.Class<android.content.Context> r3 = android.content.Context.class
            r4 = 0
            r2[r4] = r3     // Catch:{  }
            java.lang.Class<android.os.Bundle> r3 = android.os.Bundle.class
            r4 = 1
            r2[r4] = r3     // Catch:{  }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r2)     // Catch:{  }
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}     // Catch:{  }
            java.lang.Object r5 = r0.invoke(r6, r5)     // Catch:{  }
            com.google.android.gms.measurement.internal.zzlb r5 = (com.google.android.gms.measurement.internal.zzlb) r5     // Catch:{  }
            return r5
        L_0x0025:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.AppMeasurement.zza(android.content.Context, android.os.Bundle):com.google.android.gms.measurement.internal.zzlb");
    }

    public Boolean getBoolean() {
        return this.zzb.zza();
    }

    public Double getDouble() {
        return this.zzb.zzb();
    }

    public Integer getInteger() {
        return this.zzb.zzc();
    }

    public Long getLong() {
        return this.zzb.zzd();
    }

    public String getAppInstanceId() {
        return this.zzb.zzg();
    }

    public String getCurrentScreenClass() {
        return this.zzb.zzh();
    }

    public String getCurrentScreenName() {
        return this.zzb.zzi();
    }

    public String getGmpAppId() {
        return this.zzb.zzj();
    }

    public String getString() {
        return this.zzb.zze();
    }

    public List<ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        int i;
        List<Bundle> zza2 = this.zzb.zza(str, str2);
        if (zza2 == null) {
            i = 0;
        } else {
            i = zza2.size();
        }
        ArrayList arrayList = new ArrayList(i);
        for (Bundle conditionalUserProperty : zza2) {
            arrayList.add(new ConditionalUserProperty(conditionalUserProperty));
        }
        return arrayList;
    }

    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzb.zza(z);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return this.zzb.zza(str, str2, z);
    }

    private AppMeasurement(zzhy zzhy) {
        this.zzb = new zzb(zzhy);
    }

    private AppMeasurement(zzlb zzlb) {
        this.zzb = new zza(zzlb);
    }

    public void beginAdUnitExposure(String str) {
        this.zzb.zzb(str);
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        this.zzb.zza(str, str2, bundle);
    }

    public void endAdUnitExposure(String str) {
        this.zzb.zzc(str);
    }

    public void logEventInternal(String str, String str2, Bundle bundle) {
        this.zzb.zzb(str, str2, bundle);
    }

    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzb.zza(str, str2, bundle, j);
    }

    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzb.zza((zzjl) onEventListener);
    }

    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        zza zza2 = this.zzb;
        Bundle bundle = new Bundle();
        if (conditionalUserProperty.mAppId != null) {
            bundle.putString("app_id", conditionalUserProperty.mAppId);
        }
        if (conditionalUserProperty.mOrigin != null) {
            bundle.putString("origin", conditionalUserProperty.mOrigin);
        }
        if (conditionalUserProperty.mName != null) {
            bundle.putString("name", conditionalUserProperty.mName);
        }
        if (conditionalUserProperty.mValue != null) {
            zzjf.zza(bundle, conditionalUserProperty.mValue);
        }
        if (conditionalUserProperty.mTriggerEventName != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, conditionalUserProperty.mTriggerEventName);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, conditionalUserProperty.mTriggerTimeout);
        if (conditionalUserProperty.mTimedOutEventName != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, conditionalUserProperty.mTimedOutEventName);
        }
        if (conditionalUserProperty.mTimedOutEventParams != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, conditionalUserProperty.mTimedOutEventParams);
        }
        if (conditionalUserProperty.mTriggeredEventName != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, conditionalUserProperty.mTriggeredEventName);
        }
        if (conditionalUserProperty.mTriggeredEventParams != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, conditionalUserProperty.mTriggeredEventParams);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, conditionalUserProperty.mTimeToLive);
        if (conditionalUserProperty.mExpiredEventName != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, conditionalUserProperty.mExpiredEventName);
        }
        if (conditionalUserProperty.mExpiredEventParams != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, conditionalUserProperty.mExpiredEventParams);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, conditionalUserProperty.mCreationTimestamp);
        bundle.putBoolean("active", conditionalUserProperty.mActive);
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, conditionalUserProperty.mTriggeredTimestamp);
        zza2.zza(bundle);
    }

    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzb.zza((zzjm) eventInterceptor);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzb.zzb((zzjl) onEventListener);
    }
}
