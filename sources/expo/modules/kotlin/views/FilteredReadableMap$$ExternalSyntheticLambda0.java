package expo.modules.kotlin.views;

import expo.modules.kotlin.Filter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FilteredReadableMap$$ExternalSyntheticLambda0 implements Filter {
    public final /* synthetic */ FilteredReadableMap f$0;

    public /* synthetic */ FilteredReadableMap$$ExternalSyntheticLambda0(FilteredReadableMap filteredReadableMap) {
        this.f$0 = filteredReadableMap;
    }

    public final boolean apply(Object obj) {
        return FilteredReadableMap.keySetIterator$lambda$1(this.f$0, (String) obj);
    }
}
