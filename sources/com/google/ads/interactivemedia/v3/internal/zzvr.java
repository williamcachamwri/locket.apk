package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvr {
    static final zzvl zza = zzvl.zza;
    static final int zzd = 1;
    static final int zze = 1;
    static final int zzf = 2;
    public static final /* synthetic */ int zzg = 0;
    final List zzb;
    final zzvl zzc;
    private final ThreadLocal zzh;
    private final ConcurrentMap zzi;
    private final zzxl zzj;
    private final zzyx zzk;

    public zzvr() {
        this(zzxn.zza, zzd, Collections.emptyMap(), false, false, false, true, zza, (zzwg) null, false, true, 1, (String) null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zze, zzf, Collections.emptyList());
    }

    static void zzg(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static final void zzi(zzvw zzvw, zzace zzace) throws zzvx {
        zzwg zzn = zzace.zzn();
        boolean zzt = zzace.zzt();
        boolean zzs = zzace.zzs();
        zzace.zzp(true);
        zzace.zzq(false);
        if (zzace.zzn() == zzwg.LEGACY_STRICT) {
            zzace.zzr(zzwg.LENIENT);
        }
        try {
            zzabh.zzV.write(zzace, zzvw);
            zzace.zzr(zzn);
            zzace.zzp(zzt);
            zzace.zzq(zzs);
        } catch (IOException e) {
            throw new zzvx((Throwable) e);
        } catch (AssertionError e2) {
            String message = e2.getMessage();
            throw new AssertionError("AssertionError (GSON 2.10.1): " + message, e2);
        } catch (Throwable th) {
            zzace.zzr(zzn);
            zzace.zzp(zzt);
            zzace.zzq(zzs);
            throw th;
        }
    }

    public final String toString() {
        zzxl zzxl = this.zzj;
        String valueOf = String.valueOf(this.zzb);
        String obj = zzxl.toString();
        return "{serializeNulls:false,factories:" + valueOf + ",instanceCreators:" + obj + "}";
    }

    public final zzwj zza(zzaca zzaca) {
        boolean z;
        Objects.requireNonNull(zzaca, "type must not be null");
        zzwj zzwj = (zzwj) this.zzi.get(zzaca);
        if (zzwj != null) {
            return zzwj;
        }
        Map map = (Map) this.zzh.get();
        boolean z2 = false;
        if (map == null) {
            map = new HashMap();
            this.zzh.set(map);
            z = true;
        } else {
            zzwj zzwj2 = (zzwj) map.get(zzaca);
            if (zzwj2 != null) {
                return zzwj2;
            }
            z = false;
        }
        try {
            zzvq zzvq = new zzvq();
            map.put(zzaca, zzvq);
            Iterator it = this.zzb.iterator();
            zzwj zzwj3 = null;
            while (true) {
                if (it.hasNext()) {
                    zzwj3 = ((zzwk) it.next()).zza(this, zzaca);
                    if (zzwj3 != null) {
                        zzvq.zzb(zzwj3);
                        map.put(zzaca, zzwj3);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                this.zzh.remove();
                z2 = true;
            }
            if (zzwj3 != null) {
                if (z2) {
                    this.zzi.putAll(map);
                }
                return zzwj3;
            }
            throw new IllegalArgumentException("GSON (2.10.1) cannot handle ".concat(String.valueOf(String.valueOf(zzaca))));
        } catch (Throwable th) {
            if (z) {
                this.zzh.remove();
            }
            throw th;
        }
    }

    public final zzwj zzb(zzwk zzwk, zzaca zzaca) {
        Objects.requireNonNull(zzwk, "skipPast must not be null");
        Objects.requireNonNull(zzaca, "type must not be null");
        if (this.zzk.zzc(zzaca, zzwk)) {
            zzwk = this.zzk;
        }
        boolean z = false;
        for (zzwk zzwk2 : this.zzb) {
            if (z) {
                zzwj zza2 = zzwk2.zza(this, zzaca);
                if (zza2 != null) {
                    return zza2;
                }
            } else if (zzwk2 == zzwk) {
                z = true;
            }
        }
        if (!z) {
            return zza(zzaca);
        }
        throw new IllegalArgumentException("GSON cannot serialize or deserialize ".concat(String.valueOf(String.valueOf(zzaca))));
    }

    public final zzace zzc(Writer writer) throws IOException {
        zzace zzace = new zzace(writer);
        zzace.zzo(this.zzc);
        zzace.zzp(true);
        zzace.zzr(zzwg.LEGACY_STRICT);
        zzace.zzq(false);
        return zzace;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3 = r7.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        throw new java.lang.AssertionError("AssertionError (GSON 2.10.1): " + r3, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.zzwe((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.zzwe((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.zzwe((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        r6.zzw(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024 A[ExcHandler: AssertionError (r7v7 'e' java.lang.AssertionError A[CUSTOM_DECLARE]), Splitter:B:3:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b A[Catch:{ EOFException -> 0x0020, IllegalStateException -> 0x0042, IOException -> 0x003b, AssertionError -> 0x0024, all -> 0x0022 }, ExcHandler: IOException (r7v6 'e' java.io.IOException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:3:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042 A[Catch:{ EOFException -> 0x0020, IllegalStateException -> 0x0042, IOException -> 0x003b, AssertionError -> 0x0024, all -> 0x0022 }, ExcHandler: IllegalStateException (r7v5 'e' java.lang.IllegalStateException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:3:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052 A[SYNTHETIC, Splitter:B:26:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zzd(com.google.ads.interactivemedia.v3.internal.zzacc r6, com.google.ads.interactivemedia.v3.internal.zzaca r7) throws com.google.ads.interactivemedia.v3.internal.zzvx, com.google.ads.interactivemedia.v3.internal.zzwe {
        /*
            r5 = this;
            java.lang.String r0 = "AssertionError (GSON 2.10.1): "
            com.google.ads.interactivemedia.v3.internal.zzwg r1 = r6.zzt()
            com.google.ads.interactivemedia.v3.internal.zzwg r2 = r6.zzt()
            com.google.ads.interactivemedia.v3.internal.zzwg r3 = com.google.ads.interactivemedia.v3.internal.zzwg.LEGACY_STRICT
            if (r2 != r3) goto L_0x0013
            com.google.ads.interactivemedia.v3.internal.zzwg r2 = com.google.ads.interactivemedia.v3.internal.zzwg.LENIENT
            r6.zzw(r2)
        L_0x0013:
            r6.zzr()     // Catch:{ EOFException -> 0x0049, IllegalStateException -> 0x0042, IOException -> 0x003b, AssertionError -> 0x0024 }
            r2 = 0
            com.google.ads.interactivemedia.v3.internal.zzwj r7 = r5.zza(r7)     // Catch:{ EOFException -> 0x0020, IllegalStateException -> 0x0042, IOException -> 0x003b, AssertionError -> 0x0024 }
            java.lang.Object r7 = r7.read(r6)     // Catch:{ EOFException -> 0x0020, IllegalStateException -> 0x0042, IOException -> 0x003b, AssertionError -> 0x0024 }
            goto L_0x004e
        L_0x0020:
            r7 = move-exception
            goto L_0x004b
        L_0x0022:
            r7 = move-exception
            goto L_0x0058
        L_0x0024:
            r7 = move-exception
            java.lang.AssertionError r2 = new java.lang.AssertionError     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = r7.getMessage()     // Catch:{ all -> 0x0022 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0022 }
            r4.<init>(r0)     // Catch:{ all -> 0x0022 }
            r4.append(r3)     // Catch:{ all -> 0x0022 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0022 }
            r2.<init>(r0, r7)     // Catch:{ all -> 0x0022 }
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x003b:
            r7 = move-exception
            com.google.ads.interactivemedia.v3.internal.zzwe r0 = new com.google.ads.interactivemedia.v3.internal.zzwe     // Catch:{ all -> 0x0022 }
            r0.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0022 }
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0042:
            r7 = move-exception
            com.google.ads.interactivemedia.v3.internal.zzwe r0 = new com.google.ads.interactivemedia.v3.internal.zzwe     // Catch:{ all -> 0x0022 }
            r0.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0022 }
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0049:
            r7 = move-exception
            r2 = 1
        L_0x004b:
            if (r2 == 0) goto L_0x0052
            r7 = 0
        L_0x004e:
            r6.zzw(r1)
            return r7
        L_0x0052:
            com.google.ads.interactivemedia.v3.internal.zzwe r0 = new com.google.ads.interactivemedia.v3.internal.zzwe     // Catch:{ all -> 0x0022 }
            r0.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0022 }
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0058:
            r6.zzw(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzvr.zzd(com.google.ads.interactivemedia.v3.internal.zzacc, com.google.ads.interactivemedia.v3.internal.zzaca):java.lang.Object");
    }

    public final Object zze(String str, Class cls) throws zzwe {
        Object obj;
        zzaca zza2 = zzaca.zza(cls);
        if (str == null) {
            obj = null;
        } else {
            zzacc zzacc = new zzacc(new StringReader(str));
            zzacc.zzw(zzwg.LEGACY_STRICT);
            Object zzd2 = zzd(zzacc, zza2);
            if (zzd2 != null) {
                try {
                    if (zzacc.zzr() != 10) {
                        throw new zzwe("JSON document was not fully consumed.");
                    }
                } catch (zzacf e) {
                    throw new zzwe((Throwable) e);
                } catch (IOException e2) {
                    throw new zzvx((Throwable) e2);
                }
            }
            obj = zzd2;
        }
        if (cls == Integer.TYPE) {
            cls = Integer.class;
        } else if (cls == Float.TYPE) {
            cls = Float.class;
        } else if (cls == Byte.TYPE) {
            cls = Byte.class;
        } else if (cls == Double.TYPE) {
            cls = Double.class;
        } else if (cls == Long.TYPE) {
            cls = Long.class;
        } else if (cls == Character.TYPE) {
            cls = Character.class;
        } else if (cls == Boolean.TYPE) {
            cls = Boolean.class;
        } else if (cls == Short.TYPE) {
            cls = Short.class;
        } else if (cls == Void.TYPE) {
            cls = Void.class;
        }
        return cls.cast(obj);
    }

    public final String zzf(Object obj) {
        if (obj == null) {
            zzvy zzvy = zzvy.zza;
            StringWriter stringWriter = new StringWriter();
            try {
                zzi(zzvy, zzc(stringWriter));
                return stringWriter.toString();
            } catch (IOException e) {
                throw new zzvx((Throwable) e);
            }
        } else {
            StringWriter stringWriter2 = new StringWriter();
            try {
                zzh(obj, obj.getClass(), zzc(stringWriter2));
                return stringWriter2.toString();
            } catch (IOException e2) {
                throw new zzvx((Throwable) e2);
            }
        }
    }

    public final void zzh(Object obj, Type type, zzace zzace) throws zzvx {
        zzwj zza2 = zza(zzaca.zzb(type));
        zzwg zzn = zzace.zzn();
        if (zzace.zzn() == zzwg.LEGACY_STRICT) {
            zzace.zzr(zzwg.LENIENT);
        }
        boolean zzt = zzace.zzt();
        boolean zzs = zzace.zzs();
        zzace.zzp(true);
        zzace.zzq(false);
        try {
            zza2.write(zzace, obj);
            zzace.zzr(zzn);
            zzace.zzp(zzt);
            zzace.zzq(zzs);
        } catch (IOException e) {
            throw new zzvx((Throwable) e);
        } catch (AssertionError e2) {
            String message = e2.getMessage();
            throw new AssertionError("AssertionError (GSON 2.10.1): " + message, e2);
        } catch (Throwable th) {
            zzace.zzr(zzn);
            zzace.zzp(zzt);
            zzace.zzq(zzs);
            throw th;
        }
    }

    zzvr(zzxn zzxn, int i, Map map, boolean z, boolean z2, boolean z3, boolean z4, zzvl zzvl, zzwg zzwg, boolean z5, boolean z6, int i2, String str, int i3, int i4, List list, List list2, List list3, int i5, int i6, List list4) {
        zzwj zzwj;
        zzwj zzwj2;
        this.zzh = new ThreadLocal();
        this.zzi = new ConcurrentHashMap();
        Map map2 = map;
        zzxl zzxl = new zzxl(map, true, list4);
        this.zzj = zzxl;
        this.zzc = zzvl;
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzabh.zzW);
        arrayList.add(zzzi.zza(i5));
        zzxn zzxn2 = zzxn;
        arrayList.add(zzxn);
        arrayList.addAll(list3);
        arrayList.add(zzabh.zzC);
        arrayList.add(zzabh.zzm);
        arrayList.add(zzabh.zzg);
        arrayList.add(zzabh.zzi);
        arrayList.add(zzabh.zzk);
        zzwj zzwj3 = zzabh.zzt;
        arrayList.add(zzabh.zzc(Long.TYPE, Long.class, zzwj3));
        Class cls = Double.TYPE;
        if (z5) {
            zzwj = zzabh.zzv;
        } else {
            zzwj = new zzvm(this);
        }
        arrayList.add(zzabh.zzc(cls, Double.class, zzwj));
        Class cls2 = Float.TYPE;
        if (z5) {
            zzwj2 = zzabh.zzu;
        } else {
            zzwj2 = new zzvn(this);
        }
        arrayList.add(zzabh.zzc(cls2, Float.class, zzwj2));
        arrayList.add(zzzf.zza(i6));
        arrayList.add(zzabh.zzo);
        arrayList.add(zzabh.zzq);
        arrayList.add(zzabh.zzb(AtomicLong.class, new zzvo(zzwj3).nullSafe()));
        arrayList.add(zzabh.zzb(AtomicLongArray.class, new zzvp(zzwj3).nullSafe()));
        arrayList.add(zzabh.zzs);
        arrayList.add(zzabh.zzx);
        arrayList.add(zzabh.zzE);
        arrayList.add(zzabh.zzG);
        arrayList.add(zzabh.zzb(BigDecimal.class, zzabh.zzz));
        arrayList.add(zzabh.zzb(BigInteger.class, zzabh.zzA));
        arrayList.add(zzabh.zzb(zzxq.class, zzabh.zzB));
        arrayList.add(zzabh.zzI);
        arrayList.add(zzabh.zzK);
        arrayList.add(zzabh.zzO);
        arrayList.add(zzabh.zzQ);
        arrayList.add(zzabh.zzU);
        arrayList.add(zzabh.zzM);
        arrayList.add(zzabh.zzd);
        arrayList.add(zzyu.zza);
        arrayList.add(zzabh.zzS);
        if (zzabz.zza) {
            arrayList.add(zzabz.zzc);
            arrayList.add(zzabz.zzb);
            arrayList.add(zzabz.zzd);
        }
        arrayList.add(zzyn.zza);
        arrayList.add(zzabh.zzb);
        arrayList.add(new zzyp(zzxl));
        arrayList.add(new zzzd(zzxl, false));
        zzyx zzyx = new zzyx(zzxl);
        this.zzk = zzyx;
        arrayList.add(zzyx);
        arrayList.add(zzabh.zzX);
        arrayList.add(new zzzq(zzxl, i, zzxn, zzyx, list4));
        this.zzb = Collections.unmodifiableList(arrayList);
    }
}
