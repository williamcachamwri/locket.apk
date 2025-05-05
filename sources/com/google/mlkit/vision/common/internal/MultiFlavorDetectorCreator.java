package com.google.mlkit.vision.common.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class MultiFlavorDetectorCreator {
    private final Map zza = new HashMap();

    /* compiled from: com.google.mlkit:vision-common@@17.3.0 */
    public interface DetectorCreator<DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> {
        DetectorT create(OptionsT optionst);
    }

    /* compiled from: com.google.mlkit:vision-common@@17.3.0 */
    public interface DetectorOptions<DetectorT> {
    }

    /* compiled from: com.google.mlkit:vision-common@@17.3.0 */
    public interface MultiFlavorDetector {
    }

    /* compiled from: com.google.mlkit:vision-common@@17.3.0 */
    public static class Registration {
        private final Class zza;
        private final Provider zzb;
        private final int zzc;

        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(Class<? extends OptionsT> cls, Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider) {
            this(cls, provider, 100);
        }

        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(Class<? extends OptionsT> cls, Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider, int i) {
            this.zza = cls;
            this.zzb = provider;
            this.zzc = i;
        }

        /* access modifiers changed from: package-private */
        public final int zza() {
            return this.zzc;
        }

        /* access modifiers changed from: package-private */
        public final Provider zzb() {
            return this.zzb;
        }

        /* access modifiers changed from: package-private */
        public final Class zzc() {
            return this.zza;
        }
    }

    MultiFlavorDetectorCreator(Set set) {
        HashMap hashMap = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Registration registration = (Registration) it.next();
            Class zzc = registration.zzc();
            if (!this.zza.containsKey(zzc) || registration.zza() >= ((Integer) Preconditions.checkNotNull((Integer) hashMap.get(zzc))).intValue()) {
                this.zza.put(zzc, registration.zzb());
                hashMap.put(zzc, Integer.valueOf(registration.zza()));
            }
        }
    }

    public static synchronized MultiFlavorDetectorCreator getInstance() {
        MultiFlavorDetectorCreator multiFlavorDetectorCreator;
        synchronized (MultiFlavorDetectorCreator.class) {
            multiFlavorDetectorCreator = (MultiFlavorDetectorCreator) MlKitContext.getInstance().get(MultiFlavorDetectorCreator.class);
        }
        return multiFlavorDetectorCreator;
    }

    public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> DetectorT create(OptionsT optionst) {
        return ((DetectorCreator) ((Provider) Preconditions.checkNotNull((Provider) this.zza.get(optionst.getClass()))).get()).create(optionst);
    }
}
