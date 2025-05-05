package com.google.firebase.firestore.remote;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

final class AndroidConnectivityMonitor implements ConnectivityMonitor {
    private static final String LOG_TAG = "AndroidConnectivityMonitor";
    private final List<Consumer<ConnectivityMonitor.NetworkStatus>> callbacks = new ArrayList();
    private final ConnectivityManager connectivityManager;
    private final Context context;
    private Runnable unregisterRunnable;

    AndroidConnectivityMonitor(Context context2) {
        Assert.hardAssert(context2 != null, "Context must be non-null", new Object[0]);
        this.context = context2;
        this.connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
        configureBackgroundStateListener();
        configureNetworkMonitoring();
    }

    public void addCallback(Consumer<ConnectivityMonitor.NetworkStatus> consumer) {
        synchronized (this.callbacks) {
            this.callbacks.add(consumer);
        }
    }

    public void shutdown() {
        Runnable runnable = this.unregisterRunnable;
        if (runnable != null) {
            runnable.run();
            this.unregisterRunnable = null;
        }
    }

    private void configureNetworkMonitoring() {
        if (this.connectivityManager != null) {
            DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
            this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
            this.unregisterRunnable = new AndroidConnectivityMonitor$$ExternalSyntheticLambda0(this, defaultNetworkCallback);
            return;
        }
        NetworkReceiver networkReceiver = new NetworkReceiver();
        this.context.registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.unregisterRunnable = new AndroidConnectivityMonitor$$ExternalSyntheticLambda1(this, networkReceiver);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureNetworkMonitoring$0$com-google-firebase-firestore-remote-AndroidConnectivityMonitor  reason: not valid java name */
    public /* synthetic */ void m745lambda$configureNetworkMonitoring$0$comgooglefirebasefirestoreremoteAndroidConnectivityMonitor(DefaultNetworkCallback defaultNetworkCallback) {
        this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureNetworkMonitoring$1$com-google-firebase-firestore-remote-AndroidConnectivityMonitor  reason: not valid java name */
    public /* synthetic */ void m746lambda$configureNetworkMonitoring$1$comgooglefirebasefirestoreremoteAndroidConnectivityMonitor(NetworkReceiver networkReceiver) {
        this.context.unregisterReceiver(networkReceiver);
    }

    private void configureBackgroundStateListener() {
        Application application = (Application) this.context.getApplicationContext();
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            public void onActivityStarted(Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }

            public void onActivityResumed(Activity activity) {
                if (atomicBoolean.compareAndSet(true, false)) {
                    AndroidConnectivityMonitor.this.raiseForegroundNotification();
                }
            }
        });
        application.registerComponentCallbacks(new ComponentCallbacks2() {
            public void onConfigurationChanged(Configuration configuration) {
            }

            public void onLowMemory() {
            }

            public void onTrimMemory(int i) {
                if (i == 20) {
                    atomicBoolean.set(true);
                }
            }
        });
    }

    public void raiseForegroundNotification() {
        Logger.debug(LOG_TAG, "App has entered the foreground.", new Object[0]);
        if (isConnected()) {
            raiseCallbacks(true);
        }
    }

    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        private DefaultNetworkCallback() {
        }

        public void onAvailable(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(true);
        }

        public void onLost(Network network) {
            AndroidConnectivityMonitor.this.raiseCallbacks(false);
        }
    }

    private class NetworkReceiver extends BroadcastReceiver {
        private boolean wasConnected;

        private NetworkReceiver() {
            this.wasConnected = false;
        }

        public void onReceive(Context context, Intent intent) {
            boolean access$300 = AndroidConnectivityMonitor.this.isConnected();
            if (AndroidConnectivityMonitor.this.isConnected() && !this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(true);
            } else if (!access$300 && this.wasConnected) {
                AndroidConnectivityMonitor.this.raiseCallbacks(false);
            }
            this.wasConnected = access$300;
        }
    }

    /* access modifiers changed from: private */
    public void raiseCallbacks(boolean z) {
        synchronized (this.callbacks) {
            for (Consumer accept : this.callbacks) {
                accept.accept(z ? ConnectivityMonitor.NetworkStatus.REACHABLE : ConnectivityMonitor.NetworkStatus.UNREACHABLE);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
