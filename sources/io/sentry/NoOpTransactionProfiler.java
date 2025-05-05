package io.sentry;

import java.util.List;

public final class NoOpTransactionProfiler implements ITransactionProfiler {
    private static final NoOpTransactionProfiler instance = new NoOpTransactionProfiler();

    public void close() {
    }

    public ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list) {
        return null;
    }

    public void onTransactionStart(ITransaction iTransaction) {
    }

    private NoOpTransactionProfiler() {
    }

    public static NoOpTransactionProfiler getInstance() {
        return instance;
    }
}
