package expo.modules.intentlauncher;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\u001a\u0018\u0010\u0005\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"ATTR_DATA", "", "ATTR_EXTRA", "REQUEST_CODE", "", "toBundle", "Landroid/os/Bundle;", "", "", "expo-intent-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: IntentLauncherModule.kt */
public final class IntentLauncherModuleKt {
    private static final String ATTR_DATA = "data";
    private static final String ATTR_EXTRA = "extra";
    private static final int REQUEST_CODE = 12;

    /* access modifiers changed from: private */
    public static final Bundle toBundle(Map<String, ? extends Object> map) {
        Pair[] pairArr = (Pair[]) MapsKt.toList(map).toArray(new Pair[0]);
        return BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length));
    }
}
