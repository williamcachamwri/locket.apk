package com.facebook.common.memory;

public enum MemoryTrimType {
    OnCloseToDalvikHeapLimit(0.5d),
    OnSystemMemoryCriticallyLowWhileAppInForeground(1.0d),
    OnSystemLowMemoryWhileAppInForeground(0.5d),
    OnSystemLowMemoryWhileAppInBackgroundLowSeverity(1.0d),
    OnAppBackgrounded(1.0d),
    OnJavaMemoryRed(1.0d),
    OnJavaMemoryYellow(0.5d),
    OnSystemMemoryRed(1.0d),
    OnSystemMemoryYellow(0.5d);
    
    private double mSuggestedTrimRatio;

    private MemoryTrimType(double d) {
        this.mSuggestedTrimRatio = d;
    }

    public double getSuggestedTrimRatio() {
        return this.mSuggestedTrimRatio;
    }
}
