package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzma {
    /* access modifiers changed from: private */
    public static final Object zza = new Object();
    /* access modifiers changed from: private */
    public static final String zzb = "zzma";
    private zzcc zzc;

    static /* synthetic */ boolean zzd() {
        return true;
    }

    public final synchronized zzbt zza() throws GeneralSecurityException {
        return this.zzc.zza();
    }

    static /* synthetic */ void zza(zzbt zzbt, zzce zzce, zzbg zzbg) {
        if (zzbg != null) {
            try {
                zzbt.zza(zzce, zzbg, new byte[0]);
            } catch (IOException e) {
                throw new GeneralSecurityException(e);
            }
        } else {
            zzcf.zza(zzbt, zzce, zzbq.zza());
        }
    }

    private zzma(zza zza2) {
        new zzmh(zza2.zza, zza2.zzb, zza2.zzc);
        this.zzc = zza2.zzi;
    }

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma$zza */
    /* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        /* access modifiers changed from: private */
        public Context zza = null;
        /* access modifiers changed from: private */
        public String zzb = null;
        /* access modifiers changed from: private */
        public String zzc = null;
        private String zzd = null;
        private zzbg zze = null;
        private boolean zzf = true;
        private zzbu zzg = null;
        private zzvu zzh = null;
        /* access modifiers changed from: private */
        public zzcc zzi;

        private final zzbg zzb() throws GeneralSecurityException {
            zzma.zzd();
            zzmf zzmf = new zzmf();
            try {
                boolean zzc2 = zzmf.zzc(this.zzd);
                try {
                    return zzmf.zza(this.zzd);
                } catch (GeneralSecurityException | ProviderException e) {
                    if (zzc2) {
                        SentryLogcatAdapter.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e);
                        return null;
                    }
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", new Object[]{this.zzd}), e);
                }
            } catch (GeneralSecurityException | ProviderException e2) {
                SentryLogcatAdapter.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                return null;
            }
        }

        private static zzcc zza(byte[] bArr) throws GeneralSecurityException, IOException {
            return zzcc.zza(zzbh.zza(zzbf.zza(bArr)));
        }

        private final zzcc zzb(byte[] bArr) throws GeneralSecurityException, IOException {
            try {
                this.zze = new zzmf().zza(this.zzd);
                try {
                    return zzcc.zza(zzbt.zza(zzbf.zza(bArr), this.zze, new byte[0]));
                } catch (IOException | GeneralSecurityException e) {
                    try {
                        return zza(bArr);
                    } catch (IOException unused) {
                        throw e;
                    }
                }
            } catch (GeneralSecurityException | ProviderException e2) {
                try {
                    zzcc zza2 = zza(bArr);
                    SentryLogcatAdapter.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                    return zza2;
                } catch (IOException unused2) {
                    throw e2;
                }
            }
        }

        public final zza zza(zzvu zzvu) {
            this.zzh = zzvu;
            return this;
        }

        public final zza zza(String str) {
            if (!str.startsWith("android-keystore://")) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            } else if (this.zzf) {
                this.zzd = str;
                return this;
            } else {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
        }

        public final zza zza(Context context, String str, String str2) throws IOException {
            if (context != null) {
                this.zza = context;
                this.zzb = str;
                this.zzc = str2;
                return this;
            }
            throw new IllegalArgumentException("need an Android context");
        }

        public final synchronized zzma zza() throws GeneralSecurityException, IOException {
            zzma zzma;
            if (this.zzb != null) {
                zzvu zzvu = this.zzh;
                if (zzvu != null && this.zzg == null) {
                    this.zzg = zzbu.zza(zzcp.zza(zzvu.zzk()));
                }
                synchronized (zzma.zza) {
                    byte[] zzb2 = zzb(this.zza, this.zzb, this.zzc);
                    if (zzb2 == null) {
                        if (this.zzd != null) {
                            this.zze = zzb();
                        }
                        zzbu zzbu = this.zzg;
                        if (zzbu != null) {
                            zzbt zza2 = zzbt.zza(zzbu);
                            zzma.zza(zza2, new zzmh(this.zza, this.zzb, this.zzc), this.zze);
                            this.zzi = zzcc.zza(zza2);
                        } else {
                            throw new GeneralSecurityException("cannot read or generate keyset");
                        }
                    } else if (this.zzd != null) {
                        zzma.zzd();
                        this.zzi = zzb(zzb2);
                    } else {
                        this.zzi = zza(zzb2);
                    }
                    zzma = new zzma(this);
                }
            } else {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            return zzma;
        }

        private static byte[] zzb(Context context, String str, String str2) throws IOException {
            SharedPreferences sharedPreferences;
            if (str != null) {
                Context applicationContext = context.getApplicationContext();
                if (str2 == null) {
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
                } else {
                    sharedPreferences = applicationContext.getSharedPreferences(str2, 0);
                }
                try {
                    String string = sharedPreferences.getString(str, (String) null);
                    if (string == null) {
                        return null;
                    }
                    return zzyt.zza(string);
                } catch (ClassCastException | IllegalArgumentException unused) {
                    throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", new Object[]{str}));
                }
            } else {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
        }
    }
}
