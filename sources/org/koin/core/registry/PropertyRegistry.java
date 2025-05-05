package org.koin.core.registry;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.mp.KoinPlatformTools;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tJ\u001b\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\r\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u000b2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0013J)\u0010\u0014\u001a\u00020\u000b\"\b\b\u0000\u0010\u000f*\u00020\u00012\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u000fH\u0000¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/koin/core/registry/PropertyRegistry;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "get_koin$koin_core", "()Lorg/koin/core/Koin;", "_values", "", "", "close", "", "deleteProperty", "key", "getProperty", "T", "(Ljava/lang/String;)Ljava/lang/Object;", "saveProperties", "properties", "", "saveProperty", "value", "saveProperty$koin_core", "(Ljava/lang/String;Ljava/lang/Object;)V", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PropertyRegistry.kt */
public final class PropertyRegistry {
    private final Koin _koin;
    private final Map<String, Object> _values = KoinPlatformTools.INSTANCE.safeHashMap();

    public PropertyRegistry(Koin koin) {
        Intrinsics.checkNotNullParameter(koin, "_koin");
        this._koin = koin;
    }

    public final Koin get_koin$koin_core() {
        return this._koin;
    }

    public final void saveProperties(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "properties");
        Logger logger = this._koin.getLogger();
        String str = "load " + map.size() + " properties";
        Level level = Level.DEBUG;
        if (logger.isAt(level)) {
            logger.display(level, str);
        }
        this._values.putAll(map);
    }

    public final <T> void saveProperty$koin_core(String str, T t) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(t, "value");
        this._values.put(str, t);
    }

    public final void deleteProperty(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this._values.remove(str);
    }

    public final <T> T getProperty(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        T t = this._values.get(str);
        if (t == null) {
            return null;
        }
        return t;
    }

    public final void close() {
        this._values.clear();
    }
}
