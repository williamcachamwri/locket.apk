package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzaw;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzady  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
abstract class zzady<ResultT, CallbackT> implements zzaek<ResultT> {
    protected final int zza;
    protected final zzaea zzb = new zzaea(this);
    protected FirebaseApp zzc;
    protected FirebaseUser zzd;
    protected CallbackT zze;
    protected zzaw zzf;
    protected zzadw<ResultT> zzg;
    protected final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzh = new ArrayList();
    protected Executor zzi;
    protected zzagl zzj;
    protected zzage zzk;
    protected zzafl zzl;
    protected zzagv zzm;
    protected AuthCredential zzn;
    protected String zzo;
    protected String zzp;
    protected zzzl zzq;
    protected zzagm zzr;
    protected zzagh zzs;
    protected zzahh zzt;
    /* access modifiers changed from: private */
    public boolean zzu;

    public final zzady<ResultT, CallbackT> zza(CallbackT callbackt) {
        this.zze = Preconditions.checkNotNull(callbackt, "external callback cannot be null");
        return this;
    }

    public abstract void zzb();

    public final zzady<ResultT, CallbackT> zza(zzaw zzaw) {
        this.zzf = (zzaw) Preconditions.checkNotNull(zzaw, "external failure callback cannot be null");
        return this;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzady$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    private static class zza extends LifecycleCallback {
        private final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zza;

        private zza(LifecycleFragment lifecycleFragment, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("PhoneAuthActivityStopCallback", this);
            this.zza = list;
        }

        public static void zza(Activity activity, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            LifecycleFragment fragment = getFragment(activity);
            if (((zza) fragment.getCallbackOrNull("PhoneAuthActivityStopCallback", zza.class)) == null) {
                new zza(fragment, list);
            }
        }

        public void onStop() {
            synchronized (this.zza) {
                this.zza.clear();
            }
        }
    }

    public final zzady<ResultT, CallbackT> zza(FirebaseApp firebaseApp) {
        this.zzc = (FirebaseApp) Preconditions.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzady<ResultT, CallbackT> zza(FirebaseUser firebaseUser) {
        this.zzd = (FirebaseUser) Preconditions.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }

    public final zzady<ResultT, CallbackT> zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, String str) {
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zza2 = zzaer.zza(str, onVerificationStateChangedCallbacks, this);
        synchronized (this.zzh) {
            this.zzh.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) Preconditions.checkNotNull(zza2));
        }
        if (activity != null) {
            zza.zza(activity, this.zzh);
        }
        this.zzi = (Executor) Preconditions.checkNotNull(executor);
        return this;
    }

    static /* synthetic */ void zza(zzady zzady) {
        zzady.zzb();
        Preconditions.checkState(zzady.zzu, "no success or failure set on method implementation");
    }

    static /* synthetic */ void zza(zzady zzady, Status status) {
        zzaw zzaw = zzady.zzf;
        if (zzaw != null) {
            zzaw.zza(status);
        }
    }

    public zzady(int i) {
        this.zza = i;
    }

    public final void zza(Status status) {
        this.zzu = true;
        this.zzg.zza(null, status);
    }

    public final void zzb(ResultT resultt) {
        this.zzu = true;
        this.zzg.zza(resultt, (Status) null);
    }
}
