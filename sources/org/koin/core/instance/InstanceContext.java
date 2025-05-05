package org.koin.core.instance;

import expo.modules.interfaces.permissions.PermissionsResponse;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.scope.Scope;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/koin/core/instance/InstanceContext;", "", "logger", "Lorg/koin/core/logger/Logger;", "scope", "Lorg/koin/core/scope/Scope;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "(Lorg/koin/core/logger/Logger;Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)V", "getLogger", "()Lorg/koin/core/logger/Logger;", "getParameters", "()Lorg/koin/core/parameter/ParametersHolder;", "getScope", "()Lorg/koin/core/scope/Scope;", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InstanceContext.kt */
public final class InstanceContext {
    private final Logger logger;
    private final ParametersHolder parameters;
    private final Scope scope;

    public InstanceContext(Logger logger2, Scope scope2, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(logger2, SentryEvent.JsonKeys.LOGGER);
        Intrinsics.checkNotNullParameter(scope2, PermissionsResponse.SCOPE_KEY);
        this.logger = logger2;
        this.scope = scope2;
        this.parameters = parametersHolder;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InstanceContext(Logger logger2, Scope scope2, ParametersHolder parametersHolder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(logger2, scope2, (i & 4) != 0 ? null : parametersHolder);
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final Scope getScope() {
        return this.scope;
    }

    public final ParametersHolder getParameters() {
        return this.parameters;
    }
}
