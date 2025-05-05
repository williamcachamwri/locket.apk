package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzey {
    private final HttpURLConnection zza;

    public zzey(HttpURLConnection httpURLConnection) {
        this.zza = httpURLConnection;
    }

    private final InputStream zzf() {
        try {
            return this.zza.getInputStream();
        } catch (UnknownServiceException e) {
            throw new zzbf(zzbd.zzc, zzbc.zzaf, e.getMessage());
        } catch (IOException e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzae, e2.getMessage());
        } catch (Exception e3) {
            throw new zzbf(zzbd.zzc, zzbc.zzak, e3.getMessage());
        }
    }

    private final OutputStream zzg() {
        try {
            return this.zza.getOutputStream();
        } catch (UnknownServiceException e) {
            throw new zzbf(zzbd.zzc, zzbc.zzaf, e.getMessage());
        } catch (IOException e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzae, e2.getMessage());
        } catch (Exception e3) {
            throw new zzbf(zzbd.zzc, zzbc.zzak, e3.getMessage());
        }
    }

    public final zzok zza(zzok zzok) {
        zzbf zzbf;
        try {
            int responseCode = this.zza.getResponseCode();
            if (responseCode != 200) {
                if (responseCode == 400) {
                    zzbf = new zzbf(zzbd.zzc, zzbc.zzax, (String) null);
                } else if (responseCode == 503) {
                    zzbf = new zzbf(zzbd.zzi, zzbc.zzJ, (String) null);
                } else if (responseCode == 403) {
                    zzbf = new zzbf(zzbd.zzi, zzbc.zzJ, (String) null);
                } else if (responseCode != 404) {
                    zzbf = new zzbf(zzbd.zzc, zzbc.zzK, (String) null);
                } else {
                    zzbf = new zzbf(zzbd.zzc, zzbc.zzi, (String) null);
                }
                throw zzbf;
            }
            byte[] readBytes = ByteStreamsKt.readBytes(zzf());
            if (readBytes.length != 0) {
                try {
                    Object zzb = zzok.zzD().zzb(readBytes);
                    Intrinsics.checkNotNull(zzb, "null cannot be cast to non-null type T of com.google.android.libraries.abuse.recaptcha.network.CaptchaFeConnection.getResponse");
                    return (zzok) zzb;
                } catch (Exception e) {
                    throw new zzbf(zzbd.zzc, zzbc.zzG, e.getMessage());
                }
            } else {
                throw new zzbf(zzbd.zzc, zzbc.zzaw, (String) null);
            }
        } catch (Exception e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzah, e2.getMessage());
        }
    }

    public final HttpURLConnection zzb() {
        return this.zza;
    }

    public final void zzd() {
        this.zza.disconnect();
    }

    public final void zze(byte[] bArr) {
        try {
            zzg().write(bArr);
        } catch (zzbf e) {
            throw e;
        } catch (IOException e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzag, e2.getMessage());
        } catch (Exception e3) {
            throw new zzbf(zzbd.zzc, zzbc.zzal, e3.getMessage());
        }
    }

    public final void zzc() {
        try {
            this.zza.connect();
        } catch (SocketTimeoutException e) {
            throw new zzbf(zzbd.zzc, zzbc.zzac, e.getMessage());
        } catch (IOException e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzad, e2.getMessage());
        } catch (Exception e3) {
            throw new zzbf(zzbd.zzc, zzbc.zzaj, e3.getMessage());
        }
    }
}
