package expo.modules.navigationbar;

import android.os.Bundle;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "visibility", "", "onSystemUiVisibilityChange"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NavigationBarModule.kt */
final class NavigationBarModule$definition$1$13$1 implements View.OnSystemUiVisibilityChangeListener {
    final /* synthetic */ NavigationBarModule this$0;

    NavigationBarModule$definition$1$13$1(NavigationBarModule navigationBarModule) {
        this.this$0 = navigationBarModule;
    }

    public final void onSystemUiVisibilityChange(int i) {
        String str = (i & 2) == 0 ? ViewProps.VISIBLE : "hidden";
        NavigationBarModule navigationBarModule = this.this$0;
        Bundle bundle = new Bundle();
        bundle.putString("visibility", str);
        bundle.putInt("rawVisibility", i);
        Unit unit = Unit.INSTANCE;
        navigationBarModule.sendEvent("ExpoNavigationBar.didChange", bundle);
    }
}
