package expo.modules.devlauncher.koin;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.component.KoinComponent;
import org.koin.core.component.KoinScopeComponent;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "T", "", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: DevLauncherKoinComponent.kt */
public final class DevLauncherKoinComponentKt$optInject$1 extends Lambda implements Function0<T> {
    final /* synthetic */ Function0<ParametersHolder> $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ DevLauncherKoinComponent $this_optInject;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevLauncherKoinComponentKt$optInject$1(DevLauncherKoinComponent devLauncherKoinComponent, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        super(0);
        this.$this_optInject = devLauncherKoinComponent;
        this.$qualifier = qualifier;
        this.$parameters = function0;
    }

    public final T invoke() {
        Scope rootScope;
        Class<Object> cls;
        try {
            KoinComponent koinComponent = this.$this_optInject;
            Qualifier qualifier = this.$qualifier;
            Function0<ParametersHolder> function0 = this.$parameters;
            if (koinComponent instanceof KoinScopeComponent) {
                rootScope = ((KoinScopeComponent) koinComponent).getScope();
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                cls = Object.class;
            } else {
                rootScope = koinComponent.getKoin().getScopeRegistry().getRootScope();
                Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                cls = Object.class;
            }
            return rootScope.get(Reflection.getOrCreateKotlinClass(cls), qualifier, function0);
        } catch (Exception unused) {
            return null;
        }
    }
}
