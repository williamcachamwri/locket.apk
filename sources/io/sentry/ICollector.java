package io.sentry;

public interface ICollector {
    void collect(PerformanceCollectionData performanceCollectionData);

    void setup();
}
