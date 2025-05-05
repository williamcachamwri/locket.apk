package io.sentry.android.core;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import io.sentry.Breadcrumb;
import io.sentry.IHub;
import io.sentry.Integration;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.internal.util.Permissions;
import io.sentry.util.Objects;
import java.io.Closeable;
import java.io.IOException;

public final class PhoneStateBreadcrumbsIntegration implements Integration, Closeable {
    private final Context context;
    PhoneStateChangeListener listener;
    private SentryAndroidOptions options;
    private TelephonyManager telephonyManager;

    public PhoneStateBreadcrumbsIntegration(Context context2) {
        this.context = (Context) Objects.requireNonNull(context2, "Context is required");
    }

    public void register(IHub iHub, SentryOptions sentryOptions) {
        Objects.requireNonNull(iHub, "Hub is required");
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) Objects.requireNonNull(sentryOptions instanceof SentryAndroidOptions ? (SentryAndroidOptions) sentryOptions : null, "SentryAndroidOptions is required");
        this.options = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "enableSystemEventBreadcrumbs enabled: %s", Boolean.valueOf(this.options.isEnableSystemEventBreadcrumbs()));
        if (this.options.isEnableSystemEventBreadcrumbs() && Permissions.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager2 = (TelephonyManager) this.context.getSystemService("phone");
            this.telephonyManager = telephonyManager2;
            if (telephonyManager2 != null) {
                try {
                    PhoneStateChangeListener phoneStateChangeListener = new PhoneStateChangeListener(iHub);
                    this.listener = phoneStateChangeListener;
                    this.telephonyManager.listen(phoneStateChangeListener, 32);
                    sentryOptions.getLogger().log(SentryLevel.DEBUG, "PhoneStateBreadcrumbsIntegration installed.", new Object[0]);
                    addIntegrationToSdkVersion();
                } catch (Throwable th) {
                    this.options.getLogger().log(SentryLevel.INFO, th, "TelephonyManager is not available or ready to use.", new Object[0]);
                }
            } else {
                this.options.getLogger().log(SentryLevel.INFO, "TelephonyManager is not available", new Object[0]);
            }
        }
    }

    public void close() throws IOException {
        PhoneStateChangeListener phoneStateChangeListener;
        TelephonyManager telephonyManager2 = this.telephonyManager;
        if (telephonyManager2 != null && (phoneStateChangeListener = this.listener) != null) {
            telephonyManager2.listen(phoneStateChangeListener, 0);
            this.listener = null;
            SentryAndroidOptions sentryAndroidOptions = this.options;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "PhoneStateBreadcrumbsIntegration removed.", new Object[0]);
            }
        }
    }

    static final class PhoneStateChangeListener extends PhoneStateListener {
        private final IHub hub;

        PhoneStateChangeListener(IHub iHub) {
            this.hub = iHub;
        }

        public void onCallStateChanged(int i, String str) {
            if (i == 1) {
                Breadcrumb breadcrumb = new Breadcrumb();
                breadcrumb.setType("system");
                breadcrumb.setCategory("device.event");
                breadcrumb.setData("action", "CALL_STATE_RINGING");
                breadcrumb.setMessage("Device ringing");
                breadcrumb.setLevel(SentryLevel.INFO);
                this.hub.addBreadcrumb(breadcrumb);
            }
        }
    }
}
