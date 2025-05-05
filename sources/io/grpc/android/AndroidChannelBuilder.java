package io.grpc.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ForwardingChannelBuilder;
import io.grpc.InternalManagedChannelProvider;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ManagedChannelProvider;
import io.grpc.MethodDescriptor;
import io.grpc.internal.GrpcUtil;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public final class AndroidChannelBuilder extends ForwardingChannelBuilder<AndroidChannelBuilder> {
    private static final String LOG_TAG = "AndroidChannelBuilder";
    @Nullable
    private static final ManagedChannelProvider OKHTTP_CHANNEL_PROVIDER = findOkHttp();
    @Nullable
    private Context context;
    private final ManagedChannelBuilder<?> delegateBuilder;

    private static ManagedChannelProvider findOkHttp() {
        try {
            try {
                try {
                    ManagedChannelProvider managedChannelProvider = (ManagedChannelProvider) Class.forName("io.grpc.okhttp.OkHttpChannelProvider").asSubclass(ManagedChannelProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                    if (InternalManagedChannelProvider.isAvailable(managedChannelProvider)) {
                        return managedChannelProvider;
                    }
                    SentryLogcatAdapter.w(LOG_TAG, "OkHttpChannelProvider.isAvailable() returned false");
                    return null;
                } catch (Exception e) {
                    SentryLogcatAdapter.w(LOG_TAG, "Failed to construct OkHttpChannelProvider", e);
                    return null;
                }
            } catch (ClassCastException e2) {
                SentryLogcatAdapter.w(LOG_TAG, "Couldn't cast OkHttpChannelProvider to ManagedChannelProvider", e2);
                return null;
            }
        } catch (ClassNotFoundException e3) {
            SentryLogcatAdapter.w(LOG_TAG, "Failed to find OkHttpChannelProvider", e3);
            return null;
        }
    }

    public static AndroidChannelBuilder forTarget(String str) {
        return new AndroidChannelBuilder(str);
    }

    public static AndroidChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    @Deprecated
    public static AndroidChannelBuilder fromBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        return usingBuilder(managedChannelBuilder);
    }

    public static AndroidChannelBuilder usingBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        return new AndroidChannelBuilder(managedChannelBuilder);
    }

    private AndroidChannelBuilder(String str) {
        ManagedChannelProvider managedChannelProvider = OKHTTP_CHANNEL_PROVIDER;
        if (managedChannelProvider != null) {
            this.delegateBuilder = InternalManagedChannelProvider.builderForTarget(managedChannelProvider, str);
            return;
        }
        throw new UnsupportedOperationException("Unable to load OkHttpChannelProvider");
    }

    private AndroidChannelBuilder(ManagedChannelBuilder<?> managedChannelBuilder) {
        this.delegateBuilder = (ManagedChannelBuilder) Preconditions.checkNotNull(managedChannelBuilder, "delegateBuilder");
    }

    public AndroidChannelBuilder context(Context context2) {
        this.context = context2;
        return this;
    }

    /* access modifiers changed from: protected */
    public ManagedChannelBuilder<?> delegate() {
        return this.delegateBuilder;
    }

    public ManagedChannel build() {
        return new AndroidChannel(this.delegateBuilder.build(), this.context);
    }

    static final class AndroidChannel extends ManagedChannel {
        /* access modifiers changed from: private */
        @Nullable
        public final ConnectivityManager connectivityManager;
        /* access modifiers changed from: private */
        @Nullable
        public final Context context;
        /* access modifiers changed from: private */
        public final ManagedChannel delegate;
        private final Object lock = new Object();
        private Runnable unregisterRunnable;

        AndroidChannel(ManagedChannel managedChannel, @Nullable Context context2) {
            this.delegate = managedChannel;
            this.context = context2;
            if (context2 != null) {
                this.connectivityManager = (ConnectivityManager) context2.getSystemService("connectivity");
                try {
                    configureNetworkMonitoring();
                } catch (SecurityException e) {
                    SentryLogcatAdapter.w(AndroidChannelBuilder.LOG_TAG, "Failed to configure network monitoring. Does app have ACCESS_NETWORK_STATE permission?", e);
                }
            } else {
                this.connectivityManager = null;
            }
        }

        private void configureNetworkMonitoring() {
            if (this.connectivityManager != null) {
                final DefaultNetworkCallback defaultNetworkCallback = new DefaultNetworkCallback();
                this.connectivityManager.registerDefaultNetworkCallback(defaultNetworkCallback);
                this.unregisterRunnable = new Runnable() {
                    public void run() {
                        AndroidChannel.this.connectivityManager.unregisterNetworkCallback(defaultNetworkCallback);
                    }
                };
                return;
            }
            final NetworkReceiver networkReceiver = new NetworkReceiver();
            this.context.registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.unregisterRunnable = new Runnable() {
                public void run() {
                    AndroidChannel.this.context.unregisterReceiver(networkReceiver);
                }
            };
        }

        private void unregisterNetworkListener() {
            synchronized (this.lock) {
                Runnable runnable = this.unregisterRunnable;
                if (runnable != null) {
                    runnable.run();
                    this.unregisterRunnable = null;
                }
            }
        }

        public ManagedChannel shutdown() {
            unregisterNetworkListener();
            return this.delegate.shutdown();
        }

        public boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public ManagedChannel shutdownNow() {
            unregisterNetworkListener();
            return this.delegate.shutdownNow();
        }

        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j, timeUnit);
        }

        public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
            return this.delegate.newCall(methodDescriptor, callOptions);
        }

        public String authority() {
            return this.delegate.authority();
        }

        public ConnectivityState getState(boolean z) {
            return this.delegate.getState(z);
        }

        public void notifyWhenStateChanged(ConnectivityState connectivityState, Runnable runnable) {
            this.delegate.notifyWhenStateChanged(connectivityState, runnable);
        }

        public void resetConnectBackoff() {
            this.delegate.resetConnectBackoff();
        }

        public void enterIdle() {
            this.delegate.enterIdle();
        }

        private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
            private DefaultNetworkCallback() {
            }

            public void onAvailable(Network network) {
                AndroidChannel.this.delegate.enterIdle();
            }

            public void onBlockedStatusChanged(Network network, boolean z) {
                if (!z) {
                    AndroidChannel.this.delegate.enterIdle();
                }
            }
        }

        private class NetworkReceiver extends BroadcastReceiver {
            private boolean isConnected;

            private NetworkReceiver() {
                this.isConnected = false;
            }

            public void onReceive(Context context, Intent intent) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                boolean z = this.isConnected;
                boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
                this.isConnected = z2;
                if (z2 && !z) {
                    AndroidChannel.this.delegate.enterIdle();
                }
            }
        }
    }
}
