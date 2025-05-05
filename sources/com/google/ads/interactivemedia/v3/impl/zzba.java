package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbn;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.internal.zzahg;
import com.google.ads.interactivemedia.v3.internal.zzahh;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzuk;
import com.google.ads.interactivemedia.v3.internal.zzuu;
import com.google.ads.interactivemedia.v3.internal.zzvd;
import com.google.ads.interactivemedia.v3.internal.zzvr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzba implements zzbh, zzbi {
    final TestingConfiguration zza;
    private final Map zzb = new HashMap();
    private final Map zzc = new HashMap();
    private final Set zzd = new HashSet();
    private final Context zze;
    private final zzbk zzf;
    private final Queue zzg = new ConcurrentLinkedQueue();
    private final ExecutorService zzh;
    private final zzvd zzi = zzvd.zzs();
    private final zzvd zzj = zzvd.zzs();
    private zzbs zzk;
    private boolean zzl = false;

    protected zzba(zzbk zzbk, Context context, TestingConfiguration testingConfiguration, ExecutorService executorService) {
        this.zze = context;
        this.zza = testingConfiguration;
        this.zzf = zzbk;
        zzbk.zzi(this);
        this.zzh = executorService;
    }

    public static zzba zzc(Context context, TestingConfiguration testingConfiguration, Uri uri, zzahj zzahj, ExecutorService executorService) {
        long currentTimeMillis = System.currentTimeMillis();
        WebView webView = new WebView(context);
        zzahg zzc2 = zzahh.zzc();
        zzc2.zzb(currentTimeMillis);
        zzc2.zza(System.currentTimeMillis());
        zzahj.zzn((zzahh) zzc2.zzal());
        zzba zzba = new zzba(zzbk.zzb(webView, uri, zzahj), context, testingConfiguration, executorService);
        zzuk.zza(zzba.zzj, zzba.zzi).zza(new zzay(zzba), zzba.zzh);
        return zzba;
    }

    static String zzf(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        return str + " Caused by: " + str2;
    }

    private static final void zzo(String str, JavaScriptMessage.MsgType msgType) {
        String valueOf = String.valueOf(msgType);
        zzfk.zzc("Illegal message type " + valueOf + " received for " + str + " channel");
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        String str;
        String str2;
        zzbh zzbh;
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        String zzd2 = javaScriptMessage.zzd();
        JavaScriptMessage.MsgType zzb2 = javaScriptMessage.zzb();
        zzfk.zzc("Received js message: " + javaScriptMessage.zza().name() + " [" + zzb2.name() + "]");
        if (!this.zzb.containsKey(zzd2) || (zzbh = (zzbh) ((Map) this.zzb.get(zzd2)).get(javaScriptMessage.zza())) == null) {
            int ordinal = javaScriptMessage.zza().ordinal();
            if (ordinal != 0) {
                if (ordinal != 5) {
                    if (ordinal == 7) {
                        zzbs zzbs = this.zzk;
                        if (zzbs != null) {
                            zzbs.zzc(zzb2, zzd2, zzbu.networkRequest);
                            return;
                        } else {
                            zzfk.zza("Native network handler not initialized.");
                            return;
                        }
                    } else if (ordinal != 12) {
                        zzfk.zza("Unknown message channel: ".concat(String.valueOf(String.valueOf(javaScriptMessage.zza()))));
                        return;
                    }
                }
                int ordinal2 = zzb2.ordinal();
                if (ordinal2 == 44) {
                    this.zzj.zzc(zzbu);
                    this.zzl = true;
                } else if (ordinal2 != 48) {
                    zzo("other", zzb2);
                } else if (zzbu.ln == null || (str = zzbu.n) == null || (str2 = zzbu.m) == null) {
                    zzfk.zza("Invalid logging message data: ".concat(String.valueOf(String.valueOf(zzbu))));
                } else {
                    String str3 = "JsMessage (" + str + "): " + str2;
                    char charAt = zzbu.ln.charAt(0);
                    if (charAt != 'D') {
                        if (charAt != 'E') {
                            if (charAt != 'I') {
                                if (charAt != 'S') {
                                    if (charAt != 'V') {
                                        if (charAt != 'W') {
                                            zzfk.zzd("Unrecognized log level: ".concat(String.valueOf(zzbu.ln)));
                                            zzfk.zzd(str3);
                                            return;
                                        }
                                        zzfk.zzd(str3);
                                        return;
                                    }
                                }
                            }
                        }
                        zzfk.zza(str3);
                        return;
                    }
                    zzfk.zzc(str3);
                }
            } else if (!this.zzd.contains(zzd2)) {
                zzaz zzaz = (zzaz) this.zzc.get(zzd2);
                if (zzaz == null) {
                    zzfk.zzd("Received monitor message: " + String.valueOf(zzb2) + " for invalid session id: " + zzd2);
                } else if (zzbu == null) {
                    zzfk.zzd("Received monitor message: " + String.valueOf(zzb2) + " for session id: " + zzd2 + " with no data");
                } else if (zzb2.ordinal() != 37) {
                    zzo(JavaScriptMessage.MsgChannel.activityMonitor.toString(), zzb2);
                } else {
                    zzaz.zzf(zzbu.queryId, zzbu.eventId);
                }
            }
        } else {
            zzbh.zza(javaScriptMessage);
        }
    }

    public final WebView zzb() {
        return this.zzf.zza();
    }

    public final zzuu zzd(Map map) {
        String zzf2 = new zzvr().zzf(map);
        return this.zzf.zzc("google.ima.NativeBridge.calculateIdlessState(" + zzf2 + ")");
    }

    /* access modifiers changed from: package-private */
    public final zzuu zze() {
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzg() throws Exception {
        zzbq zzbq;
        zzbn zzbn = (zzbn) this.zzi.get();
        boolean z = ((zzbu) this.zzj.get()).enableGks;
        Context context = this.zze;
        if (z) {
            zzbq = new zzbr(context, zzbn);
        } else {
            zzbq = new zzbp((zzbo) null);
        }
        this.zzk = new zzbs(context, this, this.zzh, zzbq);
        return null;
    }

    public final void zzh(zzaz zzaz, String str) {
        this.zzc.put(str, zzaz);
    }

    public final void zzi(String str, JavaScriptMessage.MsgChannel msgChannel, zzbh zzbh) {
        if (!this.zzb.containsKey(str)) {
            this.zzb.put(str, new HashMap());
        }
        ((Map) this.zzb.get(str)).put(msgChannel, zzbh);
    }

    /* access modifiers changed from: package-private */
    public final void zzj(zzbn zzbn) {
        this.zzi.zzc(zzbn);
    }

    /* access modifiers changed from: package-private */
    public final void zzk() {
        this.zzf.zzd();
    }

    public final void zzl(String str) {
        this.zzc.remove(str);
        this.zzd.add(str);
    }

    public final void zzm(String str) {
        this.zzb.remove(str);
    }

    public final void zzn(JavaScriptMessage javaScriptMessage) {
        String name = javaScriptMessage.zza().name();
        String name2 = javaScriptMessage.zzb().name();
        zzfk.zzc("Sending js message: " + name + " [" + name2 + "]");
        this.zzg.add(javaScriptMessage);
        if (this.zzl) {
            JavaScriptMessage javaScriptMessage2 = (JavaScriptMessage) this.zzg.poll();
            while (javaScriptMessage2 != null) {
                this.zzf.zzj(javaScriptMessage2);
                javaScriptMessage2 = (JavaScriptMessage) this.zzg.poll();
            }
        }
    }
}
