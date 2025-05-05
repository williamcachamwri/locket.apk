package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaea  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaea implements zzadf {
    final /* synthetic */ zzady zza;

    zzaea(zzady zzady) {
        this.zza = zzady;
    }

    private final void zza(zzaeg zzaeg) {
        this.zza.zzi.execute(new zzaed(this, zzaeg));
    }

    private final void zza(Status status, AuthCredential authCredential, String str, String str2) {
        zzady.zza(this.zza, status);
        this.zza.zzn = authCredential;
        this.zza.zzo = str;
        this.zza.zzp = str2;
        if (this.zza.zzf != null) {
            this.zza.zzf.zza(status);
        }
        this.zza.zza(status);
    }

    public final void zza(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        this.zza.zzu = true;
        zza((zzaeg) new zzaeb(this, str));
    }

    public final void zzb(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        zza((zzaeg) new zzadz(this, str));
    }

    public final void zza(zzafl zzafl) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 3, "Unexpected response type " + this.zza.zza);
        this.zza.zzl = zzafl;
        zzady.zza(this.zza);
    }

    public final void zza() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 5, "Unexpected response type " + this.zza.zza);
        zzady.zza(this.zza);
    }

    public final void zza(zzzi zzzi) {
        zza(zzzi.zza(), zzzi.zzb(), zzzi.zzc(), zzzi.zzd());
    }

    public final void zza(zzzl zzzl) {
        this.zza.zzq = zzzl;
        this.zza.zza(zzaq.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 2, "Unexpected response type " + this.zza.zza);
        zza(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zza(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        if (this.zza.zza == 8) {
            this.zza.zzu = true;
            zza((zzaeg) new zzaee(this, status));
            return;
        }
        zzady.zza(this.zza, status);
        this.zza.zza(status);
    }

    public final void zza(zzagh zzagh) throws RemoteException {
        this.zza.zzs = zzagh;
        zzady.zza(this.zza);
    }

    public final void zza(zzagm zzagm) throws RemoteException {
        this.zza.zzr = zzagm;
        zzady.zza(this.zza);
    }

    public final void zza(zzagl zzagl, zzage zzage) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 2, "Unexpected response type: " + this.zza.zza);
        this.zza.zzj = zzagl;
        this.zza.zzk = zzage;
        zzady.zza(this.zza);
    }

    public final void zza(zzagv zzagv) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 4, "Unexpected response type " + this.zza.zza);
        this.zza.zzm = zzagv;
        zzady.zza(this.zza);
    }

    public final void zza(zzaha zzaha) throws RemoteException {
        zzady.zza(this.zza);
    }

    public final void zzb() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 6, "Unexpected response type " + this.zza.zza);
        zzady.zza(this.zza);
    }

    public final void zzc(String str) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 7, "Unexpected response type " + this.zza.zza);
        zzady.zza(this.zza);
    }

    public final void zzc() throws RemoteException {
        Preconditions.checkState(this.zza.zza == 9, "Unexpected response type " + this.zza.zza);
        zzady.zza(this.zza);
    }

    public final void zza(zzahh zzahh) throws RemoteException {
        this.zza.zzt = zzahh;
        zzady.zza(this.zza);
    }

    public final void zza(zzagl zzagl) throws RemoteException {
        boolean z = true;
        if (this.zza.zza != 1) {
            z = false;
        }
        Preconditions.checkState(z, "Unexpected response type: " + this.zza.zza);
        this.zza.zzj = zzagl;
        zzady.zza(this.zza);
    }

    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Preconditions.checkState(this.zza.zza == 8, "Unexpected response type " + this.zza.zza);
        this.zza.zzu = true;
        zza((zzaeg) new zzaec(this, phoneAuthCredential));
    }
}
