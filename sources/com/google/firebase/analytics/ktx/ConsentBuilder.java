package com.google.firebase.analytics.ktx;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/google/firebase/analytics/ktx/ConsentBuilder;", "", "<init>", "()V", "adStorage", "Lcom/google/firebase/analytics/FirebaseAnalytics$ConsentStatus;", "getAdStorage", "()Lcom/google/firebase/analytics/FirebaseAnalytics$ConsentStatus;", "setAdStorage", "(Lcom/google/firebase/analytics/FirebaseAnalytics$ConsentStatus;)V", "analyticsStorage", "getAnalyticsStorage", "setAnalyticsStorage", "asMap", "", "Lcom/google/firebase/analytics/FirebaseAnalytics$ConsentType;", "java.com.google.android.gmscore.integ.client.measurement_api_measurement_api"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.")
/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
public final class ConsentBuilder {
    private FirebaseAnalytics.ConsentStatus zza;
    private FirebaseAnalytics.ConsentStatus zzb;

    public final FirebaseAnalytics.ConsentStatus getAdStorage() {
        return this.zza;
    }

    public final FirebaseAnalytics.ConsentStatus getAnalyticsStorage() {
        return this.zzb;
    }

    public final Map<FirebaseAnalytics.ConsentType, FirebaseAnalytics.ConsentStatus> asMap() {
        Map<FirebaseAnalytics.ConsentType, FirebaseAnalytics.ConsentStatus> linkedHashMap = new LinkedHashMap<>();
        FirebaseAnalytics.ConsentStatus consentStatus = this.zza;
        if (consentStatus != null) {
            linkedHashMap.put(FirebaseAnalytics.ConsentType.AD_STORAGE, consentStatus);
        }
        FirebaseAnalytics.ConsentStatus consentStatus2 = this.zzb;
        if (consentStatus2 != null) {
            linkedHashMap.put(FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, consentStatus2);
        }
        return linkedHashMap;
    }

    public final void setAdStorage(FirebaseAnalytics.ConsentStatus consentStatus) {
        this.zza = consentStatus;
    }

    public final void setAnalyticsStorage(FirebaseAnalytics.ConsentStatus consentStatus) {
        this.zzb = consentStatus;
    }
}
