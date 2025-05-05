package androidx.core.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CountDownLatch;

public final class PendingIntentCompat {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public static PendingIntent getActivities(Context context, int i, Intent[] intentArr, int i2, Bundle bundle, boolean z) {
        return PendingIntent.getActivities(context, i, intentArr, addMutabilityFlags(z, i2), bundle);
    }

    public static PendingIntent getActivities(Context context, int i, Intent[] intentArr, int i2, boolean z) {
        return PendingIntent.getActivities(context, i, intentArr, addMutabilityFlags(z, i2));
    }

    public static PendingIntent getActivity(Context context, int i, Intent intent, int i2, boolean z) {
        return PendingIntent.getActivity(context, i, intent, addMutabilityFlags(z, i2));
    }

    public static PendingIntent getActivity(Context context, int i, Intent intent, int i2, Bundle bundle, boolean z) {
        return PendingIntent.getActivity(context, i, intent, addMutabilityFlags(z, i2), bundle);
    }

    public static PendingIntent getBroadcast(Context context, int i, Intent intent, int i2, boolean z) {
        return PendingIntent.getBroadcast(context, i, intent, addMutabilityFlags(z, i2));
    }

    public static PendingIntent getForegroundService(Context context, int i, Intent intent, int i2, boolean z) {
        return Api26Impl.getForegroundService(context, i, intent, addMutabilityFlags(z, i2));
    }

    public static PendingIntent getService(Context context, int i, Intent intent, int i2, boolean z) {
        return PendingIntent.getService(context, i, intent, addMutabilityFlags(z, i2));
    }

    public static void send(PendingIntent pendingIntent, int i, PendingIntent.OnFinished onFinished, Handler handler) throws PendingIntent.CanceledException {
        GatedCallback gatedCallback = new GatedCallback(onFinished);
        try {
            pendingIntent.send(i, gatedCallback.getCallback(), handler);
            gatedCallback.complete();
            gatedCallback.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static void send(PendingIntent pendingIntent, Context context, int i, Intent intent, PendingIntent.OnFinished onFinished, Handler handler) throws PendingIntent.CanceledException {
        send(pendingIntent, context, i, intent, onFinished, handler, (String) null, (Bundle) null);
    }

    public static void send(PendingIntent pendingIntent, Context context, int i, Intent intent, PendingIntent.OnFinished onFinished, Handler handler, String str, Bundle bundle) throws PendingIntent.CanceledException {
        GatedCallback gatedCallback = new GatedCallback(onFinished);
        try {
            Api23Impl.send(pendingIntent, context, i, intent, onFinished, handler, str, bundle);
            gatedCallback.complete();
            gatedCallback.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private static int addMutabilityFlags(boolean z, int i) {
        int i2;
        if (!z) {
            i2 = 67108864;
        } else if (Build.VERSION.SDK_INT < 31) {
            return i;
        } else {
            i2 = 33554432;
        }
        return i | i2;
    }

    private PendingIntentCompat() {
    }

    private static class Api23Impl {
        private Api23Impl() {
        }

        public static void send(PendingIntent pendingIntent, Context context, int i, Intent intent, PendingIntent.OnFinished onFinished, Handler handler, String str, Bundle bundle) throws PendingIntent.CanceledException {
            pendingIntent.send(context, i, intent, onFinished, handler, str, bundle);
        }
    }

    private static class Api26Impl {
        private Api26Impl() {
        }

        public static PendingIntent getForegroundService(Context context, int i, Intent intent, int i2) {
            return PendingIntent.getForegroundService(context, i, intent, i2);
        }
    }

    private static class GatedCallback implements Closeable {
        private PendingIntent.OnFinished mCallback;
        private final CountDownLatch mComplete = new CountDownLatch(1);
        private boolean mSuccess;

        GatedCallback(PendingIntent.OnFinished onFinished) {
            this.mCallback = onFinished;
            this.mSuccess = false;
        }

        public PendingIntent.OnFinished getCallback() {
            if (this.mCallback == null) {
                return null;
            }
            return new PendingIntentCompat$GatedCallback$$ExternalSyntheticLambda0(this);
        }

        public void complete() {
            this.mSuccess = true;
        }

        public void close() {
            if (!this.mSuccess) {
                this.mCallback = null;
            }
            this.mComplete.countDown();
        }

        /* access modifiers changed from: private */
        public void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str, Bundle bundle) {
            boolean z = false;
            while (true) {
                try {
                    this.mComplete.await();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (Throwable th) {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            PendingIntent.OnFinished onFinished = this.mCallback;
            if (onFinished != null) {
                onFinished.onSendFinished(pendingIntent, intent, i, str, bundle);
                this.mCallback = null;
            }
        }
    }
}
