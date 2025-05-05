package expo.modules.notifications.serverregistration;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\t\u001a\u00020\u0014H\u0016R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8DX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lexpo/modules/notifications/serverregistration/ServerRegistrationModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "installationId", "Lexpo/modules/notifications/serverregistration/InstallationId;", "getInstallationId", "()Lexpo/modules/notifications/serverregistration/InstallationId;", "installationId$delegate", "Lkotlin/Lazy;", "mRegistrationInfo", "Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "getMRegistrationInfo", "()Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "mRegistrationInfo$delegate", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ServerRegistrationModule.kt */
public class ServerRegistrationModule extends Module {
    private final Lazy installationId$delegate = LazyKt.lazy(new ServerRegistrationModule$installationId$2(this));
    private final Lazy mRegistrationInfo$delegate = LazyKt.lazy(new ServerRegistrationModule$mRegistrationInfo$2(this));

    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* access modifiers changed from: protected */
    public final InstallationId getInstallationId() {
        return (InstallationId) this.installationId$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final RegistrationInfo getMRegistrationInfo() {
        return (RegistrationInfo) this.mRegistrationInfo$delegate.getValue();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("NotificationsServerRegistrationModule");
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getInstallationIdAsync", new AnyType[0], new ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getInstallationIdAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getRegistrationInfoAsync", new AnyType[0], new ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunctionWithoutArgs$2(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getRegistrationInfoAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction3 = asyncFunctionComponent2;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("setRegistrationInfoAsync", new AnyType[0], new ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("setRegistrationInfoAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new ServerRegistrationModule$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("setRegistrationInfoAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: getInstallationId  reason: collision with other method in class */
    public String m2325getInstallationId() {
        String orCreateUUID = getInstallationId().getOrCreateUUID();
        Intrinsics.checkNotNullExpressionValue(orCreateUUID, "getOrCreateUUID(...)");
        return orCreateUUID;
    }
}
