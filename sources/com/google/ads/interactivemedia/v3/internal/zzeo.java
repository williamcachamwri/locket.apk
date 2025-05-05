package com.google.ads.interactivemedia.v3.internal;

import android.net.Uri;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.zzba;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzeo extends zzfe {
    private final Uri zza;
    private final TestingConfiguration zzb;
    private final zzba zzc;
    private final zzahj zzd;
    private final ExecutorService zze;
    private final zzfl zzf;

    zzeo(Uri uri, TestingConfiguration testingConfiguration, zzba zzba, zzahj zzahj, ExecutorService executorService, zzfl zzfl) {
        if (uri != null) {
            this.zza = uri;
            this.zzb = testingConfiguration;
            this.zzc = zzba;
            if (zzahj != null) {
                this.zzd = zzahj;
                if (executorService != null) {
                    this.zze = executorService;
                    this.zzf = zzfl;
                    return;
                }
                throw new NullPointerException("Null executorService");
            }
            throw new NullPointerException("Null latencyEventsBuilder");
        }
        throw new NullPointerException("Null javaScriptNativeBridgeUri");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r1 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.ads.interactivemedia.v3.internal.zzfe
            r2 = 0
            if (r1 == 0) goto L_0x005e
            com.google.ads.interactivemedia.v3.internal.zzfe r5 = (com.google.ads.interactivemedia.v3.internal.zzfe) r5
            android.net.Uri r1 = r4.zza
            android.net.Uri r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration r1 = r4.zzb
            if (r1 != 0) goto L_0x0022
            com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration r1 = r5.zzc()
            if (r1 != 0) goto L_0x005e
            goto L_0x002d
        L_0x0022:
            com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x002d
            goto L_0x005e
        L_0x002d:
            com.google.ads.interactivemedia.v3.impl.zzba r1 = r4.zzc
            com.google.ads.interactivemedia.v3.impl.zzba r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            com.google.ads.interactivemedia.v3.internal.zzahj r1 = r4.zzd
            com.google.ads.interactivemedia.v3.internal.zzahj r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            java.util.concurrent.ExecutorService r1 = r4.zze
            java.util.concurrent.ExecutorService r3 = r5.zzf()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            com.google.ads.interactivemedia.v3.internal.zzfl r1 = r4.zzf
            com.google.ads.interactivemedia.v3.internal.zzfl r5 = r5.zzd()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x005e
            return r0
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzeo.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i;
        int hashCode = this.zza.hashCode() ^ 1000003;
        TestingConfiguration testingConfiguration = this.zzb;
        if (testingConfiguration == null) {
            i = 0;
        } else {
            i = testingConfiguration.hashCode();
        }
        return (((((((((hashCode * 1000003) ^ i) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode();
    }

    public final String toString() {
        zzfl zzfl = this.zzf;
        ExecutorService executorService = this.zze;
        zzahj zzahj = this.zzd;
        zzba zzba = this.zzc;
        TestingConfiguration testingConfiguration = this.zzb;
        String obj = this.zza.toString();
        String valueOf = String.valueOf(testingConfiguration);
        String obj2 = zzba.toString();
        String obj3 = zzahj.toString();
        String obj4 = executorService.toString();
        String obj5 = zzfl.toString();
        return "JsComponent{javaScriptNativeBridgeUri=" + obj + ", testingConfiguration=" + valueOf + ", jsMessageRouter=" + obj2 + ", latencyEventsBuilder=" + obj3 + ", executorService=" + obj4 + ", omidInitializer=" + obj5 + "}";
    }

    /* access modifiers changed from: package-private */
    public final Uri zza() {
        return this.zza;
    }

    public final zzba zzb() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final TestingConfiguration zzc() {
        return this.zzb;
    }

    public final zzfl zzd() {
        return this.zzf;
    }

    public final zzahj zze() {
        return this.zzd;
    }

    public final ExecutorService zzf() {
        return this.zze;
    }
}
