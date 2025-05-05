package io.sentry.util;

import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.util.Properties;

public final class DebugMetaPropertiesApplier {
    public static String DEBUG_META_PROPERTIES_FILENAME = "sentry-debug-meta.properties";

    public static void applyToOptions(SentryOptions sentryOptions, Properties properties) {
        if (properties != null) {
            applyProguardUuid(sentryOptions, properties);
            applyBundleIds(sentryOptions, properties);
        }
    }

    private static void applyBundleIds(SentryOptions sentryOptions, Properties properties) {
        if (sentryOptions.getBundleIds().isEmpty()) {
            String property = properties.getProperty("io.sentry.bundle-ids");
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Bundle IDs found: %s", property);
            if (property != null) {
                for (String addBundleId : property.split(",", -1)) {
                    sentryOptions.addBundleId(addBundleId);
                }
            }
        }
    }

    private static void applyProguardUuid(SentryOptions sentryOptions, Properties properties) {
        if (sentryOptions.getProguardUuid() == null) {
            String proguardUuid = getProguardUuid(properties);
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Proguard UUID found: %s", proguardUuid);
            sentryOptions.setProguardUuid(proguardUuid);
        }
    }

    public static String getProguardUuid(Properties properties) {
        return properties.getProperty("io.sentry.ProguardUuids");
    }
}
