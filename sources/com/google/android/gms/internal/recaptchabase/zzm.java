package com.google.android.gms.internal.recaptchabase;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.recaptchabase.zze;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001cH\u0016¨\u0006\u001f"}, d2 = {"Lcom/google/android/gms/recaptchabase/internal/RecaptchaBaseClientImpl;", "Lcom/google/android/gms/common/internal/GmsClient;", "Lcom/google/android/gms/recaptchabase/internal/IRecaptchaBaseService;", "context", "Landroid/content/Context;", "looper", "Landroid/os/Looper;", "clientSettings", "Lcom/google/android/gms/common/internal/ClientSettings;", "connectionCallbacks", "Lcom/google/android/gms/common/api/internal/ConnectionCallbacks;", "connectionFailedListener", "Lcom/google/android/gms/common/api/internal/OnConnectionFailedListener;", "<init>", "(Landroid/content/Context;Landroid/os/Looper;Lcom/google/android/gms/common/internal/ClientSettings;Lcom/google/android/gms/common/api/internal/ConnectionCallbacks;Lcom/google/android/gms/common/api/internal/OnConnectionFailedListener;)V", "getStartServiceAction", "", "getServiceDescriptor", "createServiceInterface", "iBinder", "Landroid/os/IBinder;", "getApiFeatures", "", "Lcom/google/android/gms/common/Feature;", "()[Lcom/google/android/gms/common/Feature;", "getMinApkVersion", "", "getUseDynamicLookup", "", "usesClientTelemetry", "Companion", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class zzm extends GmsClient {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzm(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 380, clientSettings, connectionCallbacks, onConnectionFailedListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(looper, "looper");
        Intrinsics.checkNotNullParameter(clientSettings, "clientSettings");
        Intrinsics.checkNotNullParameter(connectionCallbacks, "connectionCallbacks");
        Intrinsics.checkNotNullParameter(onConnectionFailedListener, "connectionFailedListener");
    }

    public final /* bridge */ /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        zzf zzf;
        Intrinsics.checkNotNullParameter(iBinder, "iBinder");
        if (iBinder == null) {
            zzf = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.recaptchabase.internal.IRecaptchaBaseService");
            zzf = queryLocalInterface instanceof zzf ? (zzf) queryLocalInterface : new zzf(iBinder);
        }
        return zzf;
    }

    public final Feature[] getApiFeatures() {
        Feature[] featureArr = zze.zzc;
        Intrinsics.checkNotNullExpressionValue(featureArr, "ALL_FEATURES");
        return featureArr;
    }

    public final int getMinApkVersion() {
        return 17895000;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.recaptchabase.internal.IRecaptchaBaseService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.recaptchabase.service.START";
    }

    /* access modifiers changed from: protected */
    public final boolean getUseDynamicLookup() {
        return true;
    }

    public final boolean usesClientTelemetry() {
        return false;
    }
}
