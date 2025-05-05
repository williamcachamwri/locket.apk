package androidx.lifecycle;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\r"}, d2 = {"Landroidx/lifecycle/CompositeGeneratedAdaptersObserver;", "Landroidx/lifecycle/LifecycleEventObserver;", "generatedAdapters", "", "Landroidx/lifecycle/GeneratedAdapter;", "([Landroidx/lifecycle/GeneratedAdapter;)V", "[Landroidx/lifecycle/GeneratedAdapter;", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CompositeGeneratedAdaptersObserver.jvm.kt */
public final class CompositeGeneratedAdaptersObserver implements LifecycleEventObserver {
    private final GeneratedAdapter[] generatedAdapters;

    public CompositeGeneratedAdaptersObserver(GeneratedAdapter[] generatedAdapterArr) {
        Intrinsics.checkNotNullParameter(generatedAdapterArr, "generatedAdapters");
        this.generatedAdapters = generatedAdapterArr;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        MethodCallsLogger methodCallsLogger = new MethodCallsLogger();
        for (GeneratedAdapter callMethods : this.generatedAdapters) {
            callMethods.callMethods(lifecycleOwner, event, false, methodCallsLogger);
        }
        for (GeneratedAdapter callMethods2 : this.generatedAdapters) {
            callMethods2.callMethods(lifecycleOwner, event, true, methodCallsLogger);
        }
    }
}
