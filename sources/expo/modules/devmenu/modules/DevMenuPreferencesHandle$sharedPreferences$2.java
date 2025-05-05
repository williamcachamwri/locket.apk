package expo.modules.devmenu.modules;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuPreferences.kt */
final class DevMenuPreferencesHandle$sharedPreferences$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ Context $context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuPreferencesHandle$sharedPreferences$2(Context context) {
        super(0);
        this.$context = context;
    }

    public final SharedPreferences invoke() {
        return this.$context.getSharedPreferences("expo.modules.devmenu.sharedpreferences", 0);
    }
}
