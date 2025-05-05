package io.sentry.android.core;

import android.content.Context;
import android.content.pm.PackageInfo;
import io.sentry.DateUtils;
import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.ISerializer;
import io.sentry.Scope;
import io.sentry.SentryBaseEvent;
import io.sentry.SentryEnvelope;
import io.sentry.SentryEnvelopeItem;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.protocol.App;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.User;
import io.sentry.util.MapObjectWriter;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class InternalSentrySdk {
    public static Scope getCurrentScope() {
        AtomicReference atomicReference = new AtomicReference();
        HubAdapter.getInstance().configureScope(new InternalSentrySdk$$ExternalSyntheticLambda0(atomicReference));
        return (Scope) atomicReference.get();
    }

    public static Map<String, Object> serializeScope(Context context, SentryAndroidOptions sentryAndroidOptions, Scope scope) {
        ILogger logger;
        MapObjectWriter mapObjectWriter;
        HashMap hashMap = new HashMap();
        if (scope == null) {
            return hashMap;
        }
        try {
            logger = sentryAndroidOptions.getLogger();
            mapObjectWriter = new MapObjectWriter(hashMap);
            DeviceInfoUtil instance = DeviceInfoUtil.getInstance(context, sentryAndroidOptions);
            scope.getContexts().setDevice(instance.collectDeviceInformation(true, true));
            scope.getContexts().setOperatingSystem(instance.getOperatingSystem());
            User user = scope.getUser();
            if (user == null) {
                user = new User();
                scope.setUser(user);
            }
            if (user.getId() == null) {
                user.setId(Installation.id(context));
            }
        } catch (RuntimeException e) {
            logger.log(SentryLevel.ERROR, "Could not retrieve installation ID", (Throwable) e);
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().log(SentryLevel.ERROR, "Could not serialize scope.", th);
            return new HashMap();
        }
        App app = scope.getContexts().getApp();
        if (app == null) {
            app = new App();
        }
        app.setAppName(ContextUtils.getApplicationName(context, sentryAndroidOptions.getLogger()));
        app.setAppStartTime(DateUtils.toUtilDate(AppStartState.getInstance().getAppStartTime()));
        BuildInfoProvider buildInfoProvider = new BuildInfoProvider(sentryAndroidOptions.getLogger());
        PackageInfo packageInfo = ContextUtils.getPackageInfo(context, 4096, sentryAndroidOptions.getLogger(), buildInfoProvider);
        if (packageInfo != null) {
            ContextUtils.setAppPackageInfo(packageInfo, buildInfoProvider, app);
        }
        scope.getContexts().setApp(app);
        mapObjectWriter.name("user").value(logger, scope.getUser());
        mapObjectWriter.name("contexts").value(logger, scope.getContexts());
        mapObjectWriter.name("tags").value(logger, scope.getTags());
        mapObjectWriter.name("extras").value(logger, scope.getExtras());
        mapObjectWriter.name(SentryEvent.JsonKeys.FINGERPRINT).value(logger, scope.getFingerprint());
        mapObjectWriter.name("level").value(logger, scope.getLevel());
        mapObjectWriter.name(SentryBaseEvent.JsonKeys.BREADCRUMBS).value(logger, scope.getBreadcrumbs());
        return hashMap;
    }

    public static SentryId captureEnvelope(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        HubAdapter instance = HubAdapter.getInstance();
        SentryOptions options = instance.getOptions();
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            ISerializer serializer = options.getSerializer();
            SentryEnvelope read = options.getEnvelopeReader().read(byteArrayInputStream);
            if (read == null) {
                byteArrayInputStream.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            Session.State state = null;
            for (SentryEnvelopeItem next : read.getItems()) {
                arrayList.add(next);
                SentryEvent event = next.getEvent(serializer);
                if (event != null) {
                    if (event.isCrashed()) {
                        state = Session.State.Crashed;
                    }
                    if (event.isCrashed() || event.isErrored()) {
                        z = true;
                    }
                }
            }
            Session updateSession = updateSession(instance, options, state, z);
            if (updateSession != null) {
                arrayList.add(SentryEnvelopeItem.fromSession(serializer, updateSession));
            }
            SentryId captureEnvelope = instance.captureEnvelope(new SentryEnvelope(read.getHeader(), arrayList));
            byteArrayInputStream.close();
            return captureEnvelope;
        } catch (Throwable th) {
            options.getLogger().log(SentryLevel.ERROR, "Failed to capture envelope", th);
            return null;
        }
        throw th;
    }

    private static Session updateSession(IHub iHub, SentryOptions sentryOptions, Session.State state, boolean z) {
        AtomicReference atomicReference = new AtomicReference();
        iHub.configureScope(new InternalSentrySdk$$ExternalSyntheticLambda1(state, z, atomicReference, sentryOptions));
        return (Session) atomicReference.get();
    }

    static /* synthetic */ void lambda$updateSession$1(Session.State state, boolean z, AtomicReference atomicReference, SentryOptions sentryOptions, Scope scope) {
        Session session = scope.getSession();
        if (session == null) {
            sentryOptions.getLogger().log(SentryLevel.INFO, "Session is null on updateSession", new Object[0]);
        } else if (session.update(state, (String) null, z, (String) null)) {
            if (session.getStatus() == Session.State.Crashed) {
                session.end();
            }
            atomicReference.set(session);
        }
    }
}
