package io.sentry;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public final class SentryWrapper {
    public static <U> Callable<U> wrapCallable(Callable<U> callable) {
        IHub currentHub = Sentry.getCurrentHub();
        return new SentryWrapper$$ExternalSyntheticLambda1(currentHub.clone(), callable, currentHub);
    }

    static /* synthetic */ Object lambda$wrapCallable$0(IHub iHub, Callable callable, IHub iHub2) throws Exception {
        Sentry.setCurrentHub(iHub);
        try {
            return callable.call();
        } finally {
            Sentry.setCurrentHub(iHub2);
        }
    }

    public static <U> Supplier<U> wrapSupplier(Supplier<U> supplier) {
        IHub currentHub = Sentry.getCurrentHub();
        return new SentryWrapper$$ExternalSyntheticLambda0(currentHub.clone(), supplier, currentHub);
    }

    static /* synthetic */ Object lambda$wrapSupplier$1(IHub iHub, Supplier supplier, IHub iHub2) {
        Sentry.setCurrentHub(iHub);
        try {
            return supplier.get();
        } finally {
            Sentry.setCurrentHub(iHub2);
        }
    }
}
