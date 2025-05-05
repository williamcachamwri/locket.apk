package io.invertase.firebase.perf;

import com.google.firebase.perf.FirebasePerformance;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UniversalFirebasePerfModule$$ExternalSyntheticLambda4 implements Callable {
    public final /* synthetic */ Boolean f$0;

    public /* synthetic */ UniversalFirebasePerfModule$$ExternalSyntheticLambda4(Boolean bool) {
        this.f$0 = bool;
    }

    public final Object call() {
        return FirebasePerformance.getInstance().setPerformanceCollectionEnabled(this.f$0);
    }
}
