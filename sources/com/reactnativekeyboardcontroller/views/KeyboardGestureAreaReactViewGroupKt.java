package com.reactnativekeyboardcontroller.views;

import com.reactnativekeyboardcontroller.interactive.interpolators.Interpolator;
import com.reactnativekeyboardcontroller.interactive.interpolators.IosInterpolator;
import com.reactnativekeyboardcontroller.interactive.interpolators.LinearInterpolator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001d\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"interpolators", "", "", "Lcom/reactnativekeyboardcontroller/interactive/interpolators/Interpolator;", "getInterpolators", "()Ljava/util/Map;", "react-native-keyboard-controller_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyboardGestureAreaReactViewGroup.kt */
public final class KeyboardGestureAreaReactViewGroupKt {
    private static final Map<String, Interpolator> interpolators = MapsKt.mapOf(TuplesKt.to("linear", new LinearInterpolator()), TuplesKt.to("ios", new IosInterpolator()));

    public static final Map<String, Interpolator> getInterpolators() {
        return interpolators;
    }
}
