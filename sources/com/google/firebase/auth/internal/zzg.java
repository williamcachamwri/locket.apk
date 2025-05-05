package com.google.firebase.auth.internal;

import android.util.Base64;
import com.google.android.gms.internal.p002firebaseauthapi.zzagh;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import io.sentry.android.core.SentryLogcatAdapter;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzg implements Continuation<zzagh, Task<IntegrityTokenResponse>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ IntegrityManager zzb;
    private final /* synthetic */ zza zzc;

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            this.zzc.zzc = ((zzagh) task.getResult()).zza();
            return this.zzb.requestIntegrityToken(IntegrityTokenRequest.builder().setCloudProjectNumber(Long.parseLong(((zzagh) task.getResult()).zza())).setNonce(new String(Base64.encode(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(this.zza.getBytes("UTF-8")), 11))).build());
        }
        SentryLogcatAdapter.e(zza.zza, "Problem retrieving Play Integrity producer project:  " + task.getException().getMessage());
        return Tasks.forException(task.getException());
    }

    zzg(zza zza2, String str, IntegrityManager integrityManager) {
        this.zza = str;
        this.zzb = integrityManager;
        this.zzc = zza2;
    }
}
