package com.swmansion.gesturehandler.core;

import android.view.View;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;", "", "getHandlersForView", "Ljava/util/ArrayList;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "view", "Landroid/view/View;", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GestureHandlerRegistry.kt */
public interface GestureHandlerRegistry {
    ArrayList<GestureHandler<?>> getHandlersForView(View view);
}
