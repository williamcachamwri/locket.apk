package io.sentry.android.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.DeduplicateMultithreadedEventProcessor;
import io.sentry.DefaultTransactionPerformanceCollector;
import io.sentry.ILogger;
import io.sentry.SendFireAndForgetEnvelopeSender;
import io.sentry.SendFireAndForgetOutboxSender;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.cache.AndroidEnvelopeCache;
import io.sentry.android.core.internal.debugmeta.AssetsDebugMetaLoader;
import io.sentry.android.core.internal.gestures.AndroidViewGestureTargetLocator;
import io.sentry.android.core.internal.modules.AssetsModulesLoader;
import io.sentry.android.core.internal.util.AndroidMainThreadChecker;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.android.fragment.FragmentLifecycleIntegration;
import io.sentry.android.timber.SentryTimberIntegration;
import io.sentry.cache.PersistingOptionsObserver;
import io.sentry.cache.PersistingScopeObserver;
import io.sentry.compose.gestures.ComposeGestureTargetLocator;
import io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter;
import io.sentry.transport.NoOpEnvelopeCache;
import io.sentry.util.LazyEvaluator;
import io.sentry.util.Objects;
import java.io.File;
import java.util.ArrayList;

final class AndroidOptionsInitializer {
    static final String COMPOSE_CLASS_NAME = "androidx.compose.ui.node.Owner";
    static final String SENTRY_COMPOSE_GESTURE_INTEGRATION_CLASS_NAME = "io.sentry.compose.gestures.ComposeGestureTargetLocator";
    static final String SENTRY_COMPOSE_VIEW_HIERARCHY_INTEGRATION_CLASS_NAME = "io.sentry.compose.viewhierarchy.ComposeViewHierarchyExporter";

    private AndroidOptionsInitializer() {
    }

    static void loadDefaultAndMetadataOptions(SentryAndroidOptions sentryAndroidOptions, Context context) {
        AndroidLogger androidLogger = new AndroidLogger();
        loadDefaultAndMetadataOptions(sentryAndroidOptions, context, androidLogger, new BuildInfoProvider(androidLogger));
    }

    static void loadDefaultAndMetadataOptions(SentryAndroidOptions sentryAndroidOptions, Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        Objects.requireNonNull(context, "The context is required.");
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        Objects.requireNonNull(sentryAndroidOptions, "The options object is required.");
        Objects.requireNonNull(iLogger, "The ILogger object is required.");
        sentryAndroidOptions.setLogger(iLogger);
        sentryAndroidOptions.setDateProvider(new SentryAndroidDateProvider());
        ManifestMetadataReader.applyMetadata(context, sentryAndroidOptions, buildInfoProvider);
        initializeCacheDirs(context, sentryAndroidOptions);
        readDefaultOptionValues(sentryAndroidOptions, context, buildInfoProvider);
    }

    static void initializeIntegrationsAndProcessors(SentryAndroidOptions sentryAndroidOptions, Context context, LoadClass loadClass, ActivityFramesTracker activityFramesTracker) {
        initializeIntegrationsAndProcessors(sentryAndroidOptions, context, new BuildInfoProvider(new AndroidLogger()), loadClass, activityFramesTracker);
    }

    static void initializeIntegrationsAndProcessors(SentryAndroidOptions sentryAndroidOptions, Context context, BuildInfoProvider buildInfoProvider, LoadClass loadClass, ActivityFramesTracker activityFramesTracker) {
        if (sentryAndroidOptions.getCacheDirPath() != null && (sentryAndroidOptions.getEnvelopeDiskCache() instanceof NoOpEnvelopeCache)) {
            sentryAndroidOptions.setEnvelopeDiskCache(new AndroidEnvelopeCache(sentryAndroidOptions));
        }
        sentryAndroidOptions.addEventProcessor(new DeduplicateMultithreadedEventProcessor(sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new DefaultAndroidEventProcessor(context, buildInfoProvider, sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new PerformanceAndroidEventProcessor(sentryAndroidOptions, activityFramesTracker));
        sentryAndroidOptions.addEventProcessor(new ScreenshotEventProcessor(sentryAndroidOptions, buildInfoProvider));
        sentryAndroidOptions.addEventProcessor(new ViewHierarchyEventProcessor(sentryAndroidOptions));
        sentryAndroidOptions.addEventProcessor(new AnrV2EventProcessor(context, sentryAndroidOptions, buildInfoProvider));
        sentryAndroidOptions.setTransportGate(new AndroidTransportGate(context, sentryAndroidOptions.getLogger()));
        sentryAndroidOptions.setTransactionProfiler(new AndroidTransactionProfiler(context, sentryAndroidOptions, buildInfoProvider, new SentryFrameMetricsCollector(context, (SentryOptions) sentryAndroidOptions, buildInfoProvider)));
        sentryAndroidOptions.setModulesLoader(new AssetsModulesLoader(context, sentryAndroidOptions.getLogger()));
        sentryAndroidOptions.setDebugMetaLoader(new AssetsDebugMetaLoader(context, sentryAndroidOptions.getLogger()));
        boolean isClassAvailable = loadClass.isClassAvailable("androidx.core.view.ScrollingView", (SentryOptions) sentryAndroidOptions);
        boolean isClassAvailable2 = loadClass.isClassAvailable(COMPOSE_CLASS_NAME, (SentryOptions) sentryAndroidOptions);
        if (sentryAndroidOptions.getGestureTargetLocators().isEmpty()) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new AndroidViewGestureTargetLocator(isClassAvailable));
            if (isClassAvailable2 && loadClass.isClassAvailable(SENTRY_COMPOSE_GESTURE_INTEGRATION_CLASS_NAME, (SentryOptions) sentryAndroidOptions)) {
                arrayList.add(new ComposeGestureTargetLocator(sentryAndroidOptions.getLogger()));
            }
            sentryAndroidOptions.setGestureTargetLocators(arrayList);
        }
        if (sentryAndroidOptions.getViewHierarchyExporters().isEmpty() && isClassAvailable2 && loadClass.isClassAvailable(SENTRY_COMPOSE_VIEW_HIERARCHY_INTEGRATION_CLASS_NAME, (SentryOptions) sentryAndroidOptions)) {
            ArrayList arrayList2 = new ArrayList(1);
            arrayList2.add(new ComposeViewHierarchyExporter(sentryAndroidOptions.getLogger()));
            sentryAndroidOptions.setViewHierarchyExporters(arrayList2);
        }
        sentryAndroidOptions.setMainThreadChecker(AndroidMainThreadChecker.getInstance());
        if (sentryAndroidOptions.getCollectors().isEmpty()) {
            sentryAndroidOptions.addCollector(new AndroidMemoryCollector());
            sentryAndroidOptions.addCollector(new AndroidCpuCollector(sentryAndroidOptions.getLogger(), buildInfoProvider));
        }
        sentryAndroidOptions.setTransactionPerformanceCollector(new DefaultTransactionPerformanceCollector(sentryAndroidOptions));
        if (sentryAndroidOptions.getCacheDirPath() != null) {
            sentryAndroidOptions.addScopeObserver(new PersistingScopeObserver(sentryAndroidOptions));
            sentryAndroidOptions.addOptionsObserver(new PersistingOptionsObserver(sentryAndroidOptions));
        }
    }

    static void installDefaultIntegrations(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, LoadClass loadClass, ActivityFramesTracker activityFramesTracker, boolean z, boolean z2) {
        LazyEvaluator lazyEvaluator = new LazyEvaluator(new AndroidOptionsInitializer$$ExternalSyntheticLambda0(sentryAndroidOptions));
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new SendFireAndForgetEnvelopeSender(new AndroidOptionsInitializer$$ExternalSyntheticLambda1(sentryAndroidOptions)), lazyEvaluator));
        sentryAndroidOptions.addIntegration(new NdkIntegration(isNdkAvailable(buildInfoProvider) ? loadClass.loadClass(NdkIntegration.SENTRY_NDK_CLASS_NAME, sentryAndroidOptions.getLogger()) : null));
        sentryAndroidOptions.addIntegration(EnvelopeFileObserverIntegration.getOutboxFileObserver());
        sentryAndroidOptions.addIntegration(new SendCachedEnvelopeIntegration(new SendFireAndForgetOutboxSender(new AndroidOptionsInitializer$$ExternalSyntheticLambda2(sentryAndroidOptions)), lazyEvaluator));
        sentryAndroidOptions.addIntegration(new AppLifecycleIntegration());
        sentryAndroidOptions.addIntegration(AnrIntegrationFactory.create(context, buildInfoProvider));
        if (context instanceof Application) {
            Application application = (Application) context;
            sentryAndroidOptions.addIntegration(new ActivityLifecycleIntegration(application, buildInfoProvider, activityFramesTracker));
            sentryAndroidOptions.addIntegration(new CurrentActivityIntegration(application));
            sentryAndroidOptions.addIntegration(new UserInteractionIntegration(application, loadClass));
            if (z) {
                sentryAndroidOptions.addIntegration(new FragmentLifecycleIntegration(application, true, true));
            }
        } else {
            sentryAndroidOptions.getLogger().log(SentryLevel.WARNING, "ActivityLifecycle, FragmentLifecycle and UserInteraction Integrations need an Application class to be installed.", new Object[0]);
        }
        if (z2) {
            sentryAndroidOptions.addIntegration(new SentryTimberIntegration());
        }
        sentryAndroidOptions.addIntegration(new AppComponentsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new SystemEventsBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new NetworkBreadcrumbsIntegration(context, buildInfoProvider, sentryAndroidOptions.getLogger()));
        sentryAndroidOptions.addIntegration(new TempSensorBreadcrumbsIntegration(context));
        sentryAndroidOptions.addIntegration(new PhoneStateBreadcrumbsIntegration(context));
    }

    private static void readDefaultOptionValues(SentryAndroidOptions sentryAndroidOptions, Context context, BuildInfoProvider buildInfoProvider) {
        PackageInfo packageInfo = ContextUtils.getPackageInfo(context, sentryAndroidOptions.getLogger(), buildInfoProvider);
        if (packageInfo != null) {
            if (sentryAndroidOptions.getRelease() == null) {
                sentryAndroidOptions.setRelease(getSentryReleaseVersion(packageInfo, ContextUtils.getVersionCode(packageInfo, buildInfoProvider)));
            }
            String str = packageInfo.packageName;
            if (str != null && !str.startsWith("android.")) {
                sentryAndroidOptions.addInAppInclude(str);
            }
        }
        if (sentryAndroidOptions.getDistinctId() == null) {
            try {
                sentryAndroidOptions.setDistinctId(Installation.id(context));
            } catch (RuntimeException e) {
                sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Could not generate distinct Id.", (Throwable) e);
            }
        }
    }

    private static String getSentryReleaseVersion(PackageInfo packageInfo, String str) {
        return packageInfo.packageName + "@" + packageInfo.versionName + "+" + str;
    }

    private static void initializeCacheDirs(Context context, SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setCacheDirPath(new File(context.getCacheDir(), "sentry").getAbsolutePath());
    }

    private static boolean isNdkAvailable(BuildInfoProvider buildInfoProvider) {
        return buildInfoProvider.getSdkInfoVersion() >= 16;
    }
}
