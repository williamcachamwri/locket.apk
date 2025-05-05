package io.sentry;

import io.sentry.util.Objects;
import java.security.SecureRandom;

final class TracesSampler {
    private static final Double DEFAULT_TRACES_SAMPLE_RATE = Double.valueOf(1.0d);
    private final SentryOptions options;
    private final SecureRandom random;

    public TracesSampler(SentryOptions sentryOptions) {
        this((SentryOptions) Objects.requireNonNull(sentryOptions, "options are required"), new SecureRandom());
    }

    TracesSampler(SentryOptions sentryOptions, SecureRandom secureRandom) {
        this.options = sentryOptions;
        this.random = secureRandom;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004e A[SYNTHETIC, Splitter:B:20:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.sentry.TracesSamplingDecision sample(io.sentry.SamplingContext r9) {
        /*
            r8 = this;
            io.sentry.TransactionContext r0 = r9.getTransactionContext()
            io.sentry.TracesSamplingDecision r0 = r0.getSamplingDecision()
            if (r0 == 0) goto L_0x000b
            return r0
        L_0x000b:
            io.sentry.SentryOptions r0 = r8.options
            io.sentry.SentryOptions$ProfilesSamplerCallback r0 = r0.getProfilesSampler()
            r1 = 0
            if (r0 == 0) goto L_0x002d
            io.sentry.SentryOptions r0 = r8.options     // Catch:{ all -> 0x001f }
            io.sentry.SentryOptions$ProfilesSamplerCallback r0 = r0.getProfilesSampler()     // Catch:{ all -> 0x001f }
            java.lang.Double r0 = r0.sample(r9)     // Catch:{ all -> 0x001f }
            goto L_0x002e
        L_0x001f:
            r0 = move-exception
            io.sentry.SentryOptions r2 = r8.options
            io.sentry.ILogger r2 = r2.getLogger()
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.ERROR
            java.lang.String r4 = "Error in the 'ProfilesSamplerCallback' callback."
            r2.log((io.sentry.SentryLevel) r3, (java.lang.String) r4, (java.lang.Throwable) r0)
        L_0x002d:
            r0 = r1
        L_0x002e:
            if (r0 != 0) goto L_0x0036
            io.sentry.SentryOptions r0 = r8.options
            java.lang.Double r0 = r0.getProfilesSampleRate()
        L_0x0036:
            r2 = 0
            if (r0 == 0) goto L_0x0041
            boolean r3 = r8.sample((java.lang.Double) r0)
            if (r3 == 0) goto L_0x0041
            r3 = 1
            goto L_0x0042
        L_0x0041:
            r3 = r2
        L_0x0042:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            io.sentry.SentryOptions r4 = r8.options
            io.sentry.SentryOptions$TracesSamplerCallback r4 = r4.getTracesSampler()
            if (r4 == 0) goto L_0x0078
            io.sentry.SentryOptions r4 = r8.options     // Catch:{ all -> 0x0059 }
            io.sentry.SentryOptions$TracesSamplerCallback r4 = r4.getTracesSampler()     // Catch:{ all -> 0x0059 }
            java.lang.Double r4 = r4.sample(r9)     // Catch:{ all -> 0x0059 }
            goto L_0x0068
        L_0x0059:
            r4 = move-exception
            io.sentry.SentryOptions r5 = r8.options
            io.sentry.ILogger r5 = r5.getLogger()
            io.sentry.SentryLevel r6 = io.sentry.SentryLevel.ERROR
            java.lang.String r7 = "Error in the 'TracesSamplerCallback' callback."
            r5.log((io.sentry.SentryLevel) r6, (java.lang.String) r7, (java.lang.Throwable) r4)
            r4 = r1
        L_0x0068:
            if (r4 == 0) goto L_0x0078
            io.sentry.TracesSamplingDecision r9 = new io.sentry.TracesSamplingDecision
            boolean r1 = r8.sample((java.lang.Double) r4)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r9.<init>(r1, r4, r3, r0)
            return r9
        L_0x0078:
            io.sentry.TransactionContext r9 = r9.getTransactionContext()
            io.sentry.TracesSamplingDecision r9 = r9.getParentSamplingDecision()
            if (r9 == 0) goto L_0x0083
            return r9
        L_0x0083:
            io.sentry.SentryOptions r9 = r8.options
            java.lang.Double r9 = r9.getTracesSampleRate()
            io.sentry.SentryOptions r4 = r8.options
            java.lang.Boolean r4 = r4.getEnableTracing()
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x009a
            java.lang.Double r4 = DEFAULT_TRACES_SAMPLE_RATE
            goto L_0x009b
        L_0x009a:
            r4 = r1
        L_0x009b:
            if (r9 != 0) goto L_0x009e
            r9 = r4
        L_0x009e:
            if (r9 == 0) goto L_0x00ae
            io.sentry.TracesSamplingDecision r1 = new io.sentry.TracesSamplingDecision
            boolean r2 = r8.sample((java.lang.Double) r9)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r1.<init>(r2, r9, r3, r0)
            return r1
        L_0x00ae:
            io.sentry.TracesSamplingDecision r9 = new io.sentry.TracesSamplingDecision
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r9.<init>(r0, r1, r2, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.TracesSampler.sample(io.sentry.SamplingContext):io.sentry.TracesSamplingDecision");
    }

    private boolean sample(Double d) {
        return d.doubleValue() >= this.random.nextDouble();
    }
}
