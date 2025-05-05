package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
class SyncTask implements Runnable {
    /* access modifiers changed from: private */
    public final FirebaseInstanceId iid;
    private final long nextDelaySeconds;
    ExecutorService processorExecutor = FirebaseIidExecutors.newCachedSingleThreadExecutor();
    private final PowerManager.WakeLock syncWakeLock;

    /* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
    static class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Nullable
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask syncTask) {
            this.task = syncTask;
        }

        public void onReceive(Context context, Intent intent) {
            SyncTask syncTask = this.task;
            if (syncTask != null && syncTask.isDeviceConnected()) {
                if (FirebaseInstanceId.isDebugLogEnabled()) {
                    Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
                }
                this.task.iid.enqueueTaskWithDelaySeconds(this.task, 0);
                this.task.getContext().unregisterReceiver(this);
                this.task = null;
            }
        }

        public void registerReceiver() {
            if (FirebaseInstanceId.isDebugLogEnabled()) {
                Log.d("FirebaseInstanceId", "Connectivity change received registered");
            }
            this.task.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    SyncTask(FirebaseInstanceId firebaseInstanceId, long j) {
        this.iid = firebaseInstanceId;
        this.nextDelaySeconds = j;
        PowerManager.WakeLock newWakeLock = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
        this.syncWakeLock = newWakeLock;
        newWakeLock.setReferenceCounted(false);
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.iid.getApp().getApplicationContext();
    }

    /* access modifiers changed from: package-private */
    public boolean isDeviceConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* access modifiers changed from: package-private */
    public boolean maybeRefreshToken() throws IOException {
        if (!this.iid.tokenNeedsRefresh(this.iid.getTokenWithoutTriggeringSync())) {
            return true;
        }
        try {
            if (this.iid.blockingGetMasterToken() == null) {
                SentryLogcatAdapter.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            return true;
        } catch (IOException e) {
            if (GmsRpc.isErrorMessageForRetryableError(e.getMessage())) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 52);
                sb.append("Token retrieval failed: ");
                sb.append(message);
                sb.append(". Will retry token retrieval");
                SentryLogcatAdapter.w("FirebaseInstanceId", sb.toString());
                return false;
            } else if (e.getMessage() == null) {
                SentryLogcatAdapter.w("FirebaseInstanceId", "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            } else {
                throw e;
            }
        } catch (SecurityException unused) {
            SentryLogcatAdapter.w("FirebaseInstanceId", "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    public void run() {
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
            this.syncWakeLock.acquire();
        }
        try {
            this.iid.setSyncScheduledOrRunning(true);
            if (!this.iid.isGmsCorePresent()) {
                this.iid.setSyncScheduledOrRunning(false);
                if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    return;
                }
            } else if (!ServiceStarter.getInstance().hasAccessNetworkStatePermission(getContext()) || isDeviceConnected()) {
                if (maybeRefreshToken()) {
                    this.iid.setSyncScheduledOrRunning(false);
                } else {
                    this.iid.syncWithDelaySecondsInternal(this.nextDelaySeconds);
                }
                if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    return;
                }
            } else {
                new ConnectivityChangeReceiver(this).registerReceiver();
                if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    return;
                }
            }
        } catch (IOException e) {
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 93);
            sb.append("Topic sync or token retrieval failed on hard failure exceptions: ");
            sb.append(message);
            sb.append(". Won't retry the operation.");
            SentryLogcatAdapter.e("FirebaseInstanceId", sb.toString());
            this.iid.setSyncScheduledOrRunning(false);
            if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                return;
            }
        } catch (Throwable th) {
            if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                this.syncWakeLock.release();
            }
            throw th;
        }
        this.syncWakeLock.release();
    }
}
