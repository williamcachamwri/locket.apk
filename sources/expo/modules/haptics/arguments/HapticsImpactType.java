package expo.modules.haptics.arguments;

import expo.modules.devlauncher.launcher.manifest.DevLauncherUserInterface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lexpo/modules/haptics/arguments/HapticsImpactType;", "", "()V", "types", "", "", "Lexpo/modules/haptics/arguments/HapticsVibrationType;", "fromString", "style", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HapticsImpactType.kt */
public final class HapticsImpactType {
    public static final HapticsImpactType INSTANCE = new HapticsImpactType();
    private static final Map<String, HapticsVibrationType> types = MapsKt.mapOf(TuplesKt.to(DevLauncherUserInterface.LIGHT, new HapticsVibrationType(new long[]{0, 50}, new int[]{0, 30}, new long[]{0, 20})), TuplesKt.to("medium", new HapticsVibrationType(new long[]{0, 43}, new int[]{0, 50}, new long[]{0, 43})), TuplesKt.to("heavy", new HapticsVibrationType(new long[]{0, 60}, new int[]{0, 70}, new long[]{0, 61})));

    private HapticsImpactType() {
    }

    public final HapticsVibrationType fromString(String str) throws HapticsInvalidArgumentException {
        Intrinsics.checkNotNullParameter(str, TtmlNode.TAG_STYLE);
        HapticsVibrationType hapticsVibrationType = types.get(str);
        if (hapticsVibrationType != null) {
            return hapticsVibrationType;
        }
        throw new HapticsInvalidArgumentException("'style' must be one of ['light', 'medium', 'heavy']. Obtained " + str + "'.");
    }
}
