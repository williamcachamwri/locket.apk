package io.sentry.internal.debugmeta;

import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.util.ClassLoaderUtils;
import io.sentry.util.DebugMetaPropertiesApplier;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ResourcesDebugMetaLoader implements IDebugMetaLoader {
    private final ClassLoader classLoader;
    private final ILogger logger;

    public ResourcesDebugMetaLoader(ILogger iLogger) {
        this(iLogger, ResourcesDebugMetaLoader.class.getClassLoader());
    }

    ResourcesDebugMetaLoader(ILogger iLogger, ClassLoader classLoader2) {
        this.logger = iLogger;
        this.classLoader = ClassLoaderUtils.classLoaderOrDefault(classLoader2);
    }

    public Properties loadDebugMeta() {
        InputStream resourceAsStream;
        BufferedInputStream bufferedInputStream;
        try {
            resourceAsStream = this.classLoader.getResourceAsStream(DebugMetaPropertiesApplier.DEBUG_META_PROPERTIES_FILENAME);
            if (resourceAsStream == null) {
                this.logger.log(SentryLevel.INFO, "%s file was not found.", DebugMetaPropertiesApplier.DEBUG_META_PROPERTIES_FILENAME);
            } else {
                try {
                    bufferedInputStream = new BufferedInputStream(resourceAsStream);
                    Properties properties = new Properties();
                    properties.load(bufferedInputStream);
                    bufferedInputStream.close();
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                    return properties;
                } catch (IOException e) {
                    this.logger.log(SentryLevel.ERROR, e, "Failed to load %s", DebugMetaPropertiesApplier.DEBUG_META_PROPERTIES_FILENAME);
                } catch (RuntimeException e2) {
                    this.logger.log(SentryLevel.ERROR, e2, "%s file is malformed.", DebugMetaPropertiesApplier.DEBUG_META_PROPERTIES_FILENAME);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (resourceAsStream == null) {
                return null;
            }
            resourceAsStream.close();
            return null;
            throw th;
            throw th;
        } catch (IOException e3) {
            this.logger.log(SentryLevel.ERROR, e3, "Failed to load %s", DebugMetaPropertiesApplier.DEBUG_META_PROPERTIES_FILENAME);
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }
}
