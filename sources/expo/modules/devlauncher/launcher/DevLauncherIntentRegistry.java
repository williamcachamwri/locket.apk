package expo.modules.devlauncher.launcher;

import android.content.Intent;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0016J \u0010\u0012\u001a\u00020\u000f2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000ej\u0002`\u0010H\u0016J \u0010\u0014\u001a\u00020\u000f2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000ej\u0002`\u0010H\u0016R/\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048V@VX\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\f\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000ej\u0002`\u00100\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherIntentRegistry;", "Lexpo/modules/devlauncher/launcher/DevLauncherIntentRegistryInterface;", "()V", "<set-?>", "Landroid/content/Intent;", "intent", "getIntent", "()Landroid/content/Intent;", "setIntent", "(Landroid/content/Intent;)V", "intent$delegate", "Lkotlin/properties/ReadWriteProperty;", "pendingIntentListeners", "", "Lkotlin/Function1;", "", "Lexpo/modules/devlauncher/launcher/DevLauncherPendingIntentListener;", "consumePendingIntent", "subscribe", "listener", "unsubscribe", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherIntentRegistry.kt */
public final class DevLauncherIntentRegistry implements DevLauncherIntentRegistryInterface {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(DevLauncherIntentRegistry.class, "intent", "getIntent()Landroid/content/Intent;", 0))};
    private final ReadWriteProperty intent$delegate;
    /* access modifiers changed from: private */
    public final List<Function1<Intent, Unit>> pendingIntentListeners = new ArrayList();

    public DevLauncherIntentRegistry() {
        Delegates delegates = Delegates.INSTANCE;
        this.intent$delegate = new DevLauncherIntentRegistry$special$$inlined$observable$1((Object) null, this);
    }

    public Intent getIntent() {
        return (Intent) this.intent$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public void setIntent(Intent intent) {
        this.intent$delegate.setValue(this, $$delegatedProperties[0], intent);
    }

    public void subscribe(Function1<? super Intent, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.pendingIntentListeners.add(function1);
    }

    public void unsubscribe(Function1<? super Intent, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.pendingIntentListeners.remove(function1);
    }

    public Intent consumePendingIntent() {
        Intent intent = getIntent();
        setIntent((Intent) null);
        return intent;
    }
}
