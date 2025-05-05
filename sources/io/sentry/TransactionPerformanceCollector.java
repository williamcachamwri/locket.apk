package io.sentry;

import java.util.List;

public interface TransactionPerformanceCollector {
    void close();

    void start(ITransaction iTransaction);

    List<PerformanceCollectionData> stop(ITransaction iTransaction);
}
