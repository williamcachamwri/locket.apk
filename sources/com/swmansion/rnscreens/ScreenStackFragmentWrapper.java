package com.swmansion.rnscreens;

import androidx.appcompat.widget.Toolbar;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lcom/swmansion/rnscreens/ScreenStackFragmentWrapper;", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "canNavigateBack", "", "dismiss", "", "removeToolbar", "setToolbar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "setToolbarShadowHidden", "hidden", "setToolbarTranslucent", "translucent", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ScreenStackFragmentWrapper.kt */
public interface ScreenStackFragmentWrapper extends ScreenFragmentWrapper {
    boolean canNavigateBack();

    void dismiss();

    void removeToolbar();

    void setToolbar(Toolbar toolbar);

    void setToolbarShadowHidden(boolean z);

    void setToolbarTranslucent(boolean z);
}
