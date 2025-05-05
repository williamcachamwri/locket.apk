package com.google.firebase.analytics.ktx;

import android.os.Bundle;
import android.os.Parcelable;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\t\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000eH\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0007J#\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0007¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/google/firebase/analytics/ktx/ParametersBuilder;", "", "<init>", "()V", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "param", "", "key", "", "value", "", "", "", "(Ljava/lang/String;[Landroid/os/Bundle;)V", "java.com.google.android.gmscore.integ.client.measurement_api_measurement_api"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
public final class ParametersBuilder {
    private final Bundle zza = new Bundle();

    public final Bundle getBundle() {
        return this.zza;
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
    public final void param(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.zza.putDouble(str, d);
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
    public final void param(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.zza.putLong(str, j);
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
    public final void param(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(bundle, "value");
        this.zza.putBundle(str, bundle);
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
    public final void param(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.zza.putString(str, str2);
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
    public final void param(String str, Bundle[] bundleArr) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(bundleArr, "value");
        this.zza.putParcelableArray(str, (Parcelable[]) bundleArr);
    }
}
