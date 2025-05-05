package expo.modules.devmenu;

import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u001a\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0003B\u0019\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\bJ)\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\f\u001a\u00020\u00042\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0002R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/devmenu/KeyValueCachedProperty;", "TKey", "TValue", "Lkotlin/properties/ReadOnlyProperty;", "", "Lexpo/modules/devmenu/KeyValueCachedPropertyProxy;", "loader", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)V", "container", "Ljava/util/WeakHashMap;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeyValueCachedProperty.kt */
public final class KeyValueCachedProperty<TKey, TValue> implements ReadOnlyProperty<Object, KeyValueCachedPropertyProxy<TKey, TValue>> {
    private final WeakHashMap<TKey, TValue> container = new WeakHashMap<>();
    private final Function1<TKey, TValue> loader;

    public KeyValueCachedProperty(Function1<? super TKey, ? extends TValue> function1) {
        Intrinsics.checkNotNullParameter(function1, "loader");
        this.loader = function1;
    }

    public KeyValueCachedPropertyProxy<TKey, TValue> getValue(Object obj, KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(obj, "thisRef");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        return new KeyValueCachedPropertyProxy<>(this.loader, this.container);
    }
}
