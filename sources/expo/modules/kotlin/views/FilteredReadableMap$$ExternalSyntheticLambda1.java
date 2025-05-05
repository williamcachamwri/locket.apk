package expo.modules.kotlin.views;

import expo.modules.kotlin.Filter;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FilteredReadableMap$$ExternalSyntheticLambda1 implements Filter {
    public final /* synthetic */ FilteredReadableMap f$0;

    public /* synthetic */ FilteredReadableMap$$ExternalSyntheticLambda1(FilteredReadableMap filteredReadableMap) {
        this.f$0 = filteredReadableMap;
    }

    public final boolean apply(Object obj) {
        return FilteredReadableMap.getEntryIterator$lambda$0(this.f$0, (Map.Entry) obj);
    }
}
