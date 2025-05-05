package expo.modules.devmenu;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lexpo/modules/devmenu/DevMenuReactRootViewContainer;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dispatchTouchEvent", "", "ev", "Landroid/view/MotionEvent;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuReactRootViewContainer.kt */
public final class DevMenuReactRootViewContainer extends FrameLayout {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuReactRootViewContainer(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        DevMenuManager.INSTANCE.onTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }
}
