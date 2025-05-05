package expo.modules.localization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "", "invoke", "()[Ljava/lang/String;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocalizationUtils.kt */
final class LocalizationUtilsKt$ISOCurrencyCodes$2 extends Lambda implements Function0<String[]> {
    public static final LocalizationUtilsKt$ISOCurrencyCodes$2 INSTANCE = new LocalizationUtilsKt$ISOCurrencyCodes$2();

    LocalizationUtilsKt$ISOCurrencyCodes$2() {
        super(0);
    }

    public final String[] invoke() {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        Intrinsics.checkNotNullExpressionValue(availableCurrencies, "getAvailableCurrencies(...)");
        Iterable<Currency> iterable = availableCurrencies;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Currency currencyCode : iterable) {
            String currencyCode2 = currencyCode.getCurrencyCode();
            Intrinsics.checkNotNull(currencyCode2, "null cannot be cast to non-null type kotlin.String");
            arrayList.add(currencyCode2);
        }
        return (String[]) ((List) arrayList).toArray(new String[0]);
    }
}
