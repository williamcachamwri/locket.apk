package io.sentry.android.core;

import android.os.Debug;
import io.sentry.ICollector;
import io.sentry.MemoryCollectionData;
import io.sentry.PerformanceCollectionData;

public class AndroidMemoryCollector implements ICollector {
    public void setup() {
    }

    public void collect(PerformanceCollectionData performanceCollectionData) {
        performanceCollectionData.addMemoryData(new MemoryCollectionData(System.currentTimeMillis(), Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Debug.getNativeHeapSize() - Debug.getNativeHeapFreeSize()));
    }
}
