package org.koin.core.registry;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.koin.core.Koin;
import org.koin.core.error.NoPropertyFileFoundException;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003\u001a\u0012\u0010\t\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0001¨\u0006\u000b"}, d2 = {"readDataFromFile", "Ljava/util/Properties;", "content", "", "loadEnvironmentProperties", "", "Lorg/koin/core/registry/PropertyRegistry;", "loadPropertiesFromFile", "fileName", "saveProperties", "properties", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PropertyRegistryExt.kt */
public final class PropertyRegistryExtKt {
    public static final void saveProperties(PropertyRegistry propertyRegistry, Properties properties) {
        Intrinsics.checkNotNullParameter(propertyRegistry, "<this>");
        Intrinsics.checkNotNullParameter(properties, "properties");
        Logger logger = propertyRegistry.get_koin$koin_core().getLogger();
        String str = "load " + properties.size() + " properties";
        Level level = Level.DEBUG;
        if (logger.isAt(level)) {
            logger.display(level, str);
        }
        Map map = MapsKt.toMap(properties);
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
        for (Map.Entry entry : map.entrySet()) {
            propertyRegistry.saveProperty$koin_core((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public static final void loadPropertiesFromFile(PropertyRegistry propertyRegistry, String str) {
        String str2;
        Intrinsics.checkNotNullParameter(propertyRegistry, "<this>");
        Intrinsics.checkNotNullParameter(str, "fileName");
        Logger logger = propertyRegistry.get_koin$koin_core().getLogger();
        String str3 = "load properties from " + str;
        Level level = Level.DEBUG;
        if (logger.isAt(level)) {
            logger.display(level, str3);
        }
        URL resource = Koin.class.getResource(str);
        if (resource != null) {
            str2 = new String(TextStreamsKt.readBytes(resource), Charsets.UTF_8);
        } else {
            str2 = null;
        }
        if (str2 != null) {
            Logger logger2 = propertyRegistry.get_koin$koin_core().getLogger();
            String str4 = "loaded properties from file:'" + str + '\'';
            Level level2 = Level.INFO;
            if (logger2.isAt(level2)) {
                logger2.display(level2, str4);
            }
            saveProperties(propertyRegistry, readDataFromFile(str2));
            return;
        }
        throw new NoPropertyFileFoundException("No properties found for file '" + str + '\'');
    }

    private static final Properties readDataFromFile(String str) {
        Properties properties = new Properties();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        properties.load(new ByteArrayInputStream(bytes));
        return properties;
    }

    public static final void loadEnvironmentProperties(PropertyRegistry propertyRegistry) {
        Intrinsics.checkNotNullParameter(propertyRegistry, "<this>");
        Logger logger = propertyRegistry.get_koin$koin_core().getLogger();
        Level level = Level.DEBUG;
        if (logger.isAt(level)) {
            logger.display(level, "load properties from environment");
        }
        Properties properties = System.getProperties();
        Intrinsics.checkNotNullExpressionValue(properties, "sysProperties");
        saveProperties(propertyRegistry, properties);
        Map<String, String> map = System.getenv();
        Intrinsics.checkNotNullExpressionValue(map, "getenv()");
        Properties properties2 = new Properties();
        properties2.putAll(map);
        saveProperties(propertyRegistry, properties2);
    }
}
