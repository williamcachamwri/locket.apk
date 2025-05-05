package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public final class zzdl extends zzbu implements zzdj {
    zzdl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public final void beginAdUnitExposure(String str, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j);
        zzb(23, a_);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzb(9, a_);
    }

    public final void clearMeasurementEnabled(long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        zzb(43, a_);
    }

    public final void endAdUnitExposure(String str, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j);
        zzb(24, a_);
    }

    public final void generateEventId(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(22, a_);
    }

    public final void getAppInstanceId(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(20, a_);
    }

    public final void getCachedAppInstanceId(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(19, a_);
    }

    public final void getConditionalUserProperties(String str, String str2, zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(10, a_);
    }

    public final void getCurrentScreenClass(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(17, a_);
    }

    public final void getCurrentScreenName(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(16, a_);
    }

    public final void getGmpAppId(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(21, a_);
    }

    public final void getMaxUserProperties(String str, zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(6, a_);
    }

    public final void getSessionId(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(46, a_);
    }

    public final void getTestFlag(zzdo zzdo, int i) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        a_.writeInt(i);
        zzb(38, a_);
    }

    public final void getUserProperties(String str, String str2, boolean z, zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, z);
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(5, a_);
    }

    public final void initForTests(Map map) throws RemoteException {
        Parcel a_ = a_();
        a_.writeMap(map);
        zzb(37, a_);
    }

    public final void initialize(IObjectWrapper iObjectWrapper, zzdw zzdw, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (Parcelable) zzdw);
        a_.writeLong(j);
        zzb(1, a_);
    }

    public final void isDataCollectionEnabled(zzdo zzdo) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdo);
        zzb(40, a_);
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, z);
        zzbw.zza(a_, z2);
        a_.writeLong(j);
        zzb(2, a_);
    }

    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzdo zzdo, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (IInterface) zzdo);
        a_.writeLong(j);
        zzb(3, a_);
    }

    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeInt(i);
        a_.writeString(str);
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (IInterface) iObjectWrapper2);
        zzbw.zza(a_, (IInterface) iObjectWrapper3);
        zzb(33, a_);
    }

    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j);
        zzb(27, a_);
    }

    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j);
        zzb(28, a_);
    }

    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j);
        zzb(29, a_);
    }

    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j);
        zzb(30, a_);
    }

    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdo zzdo, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, (IInterface) zzdo);
        a_.writeLong(j);
        zzb(31, a_);
    }

    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j);
        zzb(25, a_);
    }

    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeLong(j);
        zzb(26, a_);
    }

    public final void performAction(Bundle bundle, zzdo zzdo, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzbw.zza(a_, (IInterface) zzdo);
        a_.writeLong(j);
        zzb(32, a_);
    }

    public final void registerOnMeasurementEventListener(zzdp zzdp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdp);
        zzb(35, a_);
    }

    public final void resetAnalyticsData(long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        zzb(12, a_);
    }

    public final void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j);
        zzb(8, a_);
    }

    public final void setConsent(Bundle bundle, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j);
        zzb(44, a_);
    }

    public final void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        a_.writeLong(j);
        zzb(45, a_);
    }

    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeLong(j);
        zzb(15, a_);
    }

    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, z);
        zzb(39, a_);
    }

    public final void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) bundle);
        zzb(42, a_);
    }

    public final void setEventInterceptor(zzdp zzdp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdp);
        zzb(34, a_);
    }

    public final void setInstanceIdProvider(zzdu zzdu) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdu);
        zzb(18, a_);
    }

    public final void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, z);
        a_.writeLong(j);
        zzb(11, a_);
    }

    public final void setMinimumSessionDuration(long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        zzb(13, a_);
    }

    public final void setSessionTimeoutDuration(long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        zzb(14, a_);
    }

    public final void setSgtmDebugInfo(Intent intent) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (Parcelable) intent);
        zzb(48, a_);
    }

    public final void setUserId(String str, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeLong(j);
        zzb(7, a_);
    }

    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzbw.zza(a_, (IInterface) iObjectWrapper);
        zzbw.zza(a_, z);
        a_.writeLong(j);
        zzb(4, a_);
    }

    public final void unregisterOnMeasurementEventListener(zzdp zzdp) throws RemoteException {
        Parcel a_ = a_();
        zzbw.zza(a_, (IInterface) zzdp);
        zzb(36, a_);
    }
}
