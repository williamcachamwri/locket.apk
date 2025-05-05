package expo.modules.devmenu;

import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00028\u00012\u0006\u0010\n\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/devmenu/KeyValueCachedPropertyProxy;", "TKey", "TValue", "", "loader", "Lkotlin/Function1;", "container", "Ljava/util/WeakHashMap;", "(Lkotlin/jvm/functions/Function1;Ljava/util/WeakHashMap;)V", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyValueCachedProperty.kt */
public final class KeyValueCachedPropertyProxy<TKey, TValue> {
    private final WeakHashMap<TKey, TValue> container;
    private Function1<? super TKey, ? extends TValue> loader;

    public KeyValueCachedPropertyProxy(Function1<? super TKey, ? extends TValue> function1, WeakHashMap<TKey, TValue> weakHashMap) {
        Intrinsics.checkNotNullParameter(function1, "loader");
        Intrinsics.checkNotNullParameter(weakHashMap, TtmlNode.RUBY_CONTAINER);
        this.loader = function1;
        this.container = weakHashMap;
    }

    public final TValue get(TKey tkey) {
        if (!this.container.containsKey(tkey)) {
            this.container.put(tkey, this.loader.invoke(tkey));
        }
        TValue tvalue = this.container.get(tkey);
        Intrinsics.checkNotNull(tvalue);
        return tvalue;
    }
}
