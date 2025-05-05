package androidx.activity;

import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0013\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¨\u0006\u0007"}, d2 = {"Landroidx/activity/EdgeToEdgeApi28;", "Landroidx/activity/EdgeToEdgeApi26;", "()V", "adjustLayoutInDisplayCutoutMode", "", "window", "Landroid/view/Window;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EdgeToEdge.kt */
class EdgeToEdgeApi28 extends EdgeToEdgeApi26 {
    public void adjustLayoutInDisplayCutoutMode(Window window) {
        Intrinsics.checkNotNullParameter(window, "window");
        window.getAttributes().layoutInDisplayCutoutMode = 1;
    }
}
