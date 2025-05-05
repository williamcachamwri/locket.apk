package expo.modules.devlauncher.launcher;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\b\u001a\u0004\u0018\u00010\u0003H&J \u0010\t\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\fj\u0002`\rH&J \u0010\u000e\u001a\u00020\n2\u0016\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\fj\u0002`\rH&R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherIntentRegistryInterface;", "", "intent", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "setIntent", "(Landroid/content/Intent;)V", "consumePendingIntent", "subscribe", "", "listener", "Lkotlin/Function1;", "Lexpo/modules/devlauncher/launcher/DevLauncherPendingIntentListener;", "unsubscribe", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherIntentRegistry.kt */
public interface DevLauncherIntentRegistryInterface {
    Intent consumePendingIntent();

    Intent getIntent();

    void setIntent(Intent intent);

    void subscribe(Function1<? super Intent, Unit> function1);

    void unsubscribe(Function1<? super Intent, Unit> function1);
}
