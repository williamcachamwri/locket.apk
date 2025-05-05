package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import androidx.core.app.BundleCompat;
import io.sentry.android.core.SentryLogcatAdapter;

public class CustomTabsSessionToken {
    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    final ICustomTabsCallback mCallbackBinder;
    private final PendingIntent mSessionId;

    static class MockCallback extends ICustomTabsCallback.Stub {
        public IBinder asBinder() {
            return this;
        }

        public void extraCallback(String str, Bundle bundle) {
        }

        public Bundle extraCallbackWithResult(String str, Bundle bundle) {
            return null;
        }

        public void onActivityResized(int i, int i2, Bundle bundle) {
        }

        public void onMessageChannelReady(Bundle bundle) {
        }

        public void onNavigationEvent(int i, Bundle bundle) {
        }

        public void onPostMessage(String str, Bundle bundle) {
        }

        public void onRelationshipValidationResult(int i, Uri uri, boolean z, Bundle bundle) {
        }

        MockCallback() {
        }
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        ICustomTabsCallback iCustomTabsCallback = null;
        if (extras == null) {
            return null;
        }
        IBinder binder = BundleCompat.getBinder(extras, CustomTabsIntent.EXTRA_SESSION);
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(CustomTabsIntent.EXTRA_SESSION_ID);
        if (binder == null && pendingIntent == null) {
            return null;
        }
        if (binder != null) {
            iCustomTabsCallback = ICustomTabsCallback.Stub.asInterface(binder);
        }
        return new CustomTabsSessionToken(iCustomTabsCallback, pendingIntent);
    }

    public static CustomTabsSessionToken createMockSessionTokenForTesting() {
        return new CustomTabsSessionToken(new MockCallback(), (PendingIntent) null);
    }

    CustomTabsSessionToken(ICustomTabsCallback iCustomTabsCallback, PendingIntent pendingIntent) {
        AnonymousClass1 r1;
        if (iCustomTabsCallback == null && pendingIntent == null) {
            throw new IllegalStateException("CustomTabsSessionToken must have either a session id or a callback (or both).");
        }
        this.mCallbackBinder = iCustomTabsCallback;
        this.mSessionId = pendingIntent;
        if (iCustomTabsCallback == null) {
            r1 = null;
        } else {
            r1 = new CustomTabsCallback() {
                public void onNavigationEvent(int i, Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.onNavigationEvent(i, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }

                public void extraCallback(String str, Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.extraCallback(str, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }

                public Bundle extraCallbackWithResult(String str, Bundle bundle) {
                    try {
                        return CustomTabsSessionToken.this.mCallbackBinder.extraCallbackWithResult(str, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                        return null;
                    }
                }

                public void onMessageChannelReady(Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.onMessageChannelReady(bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }

                public void onPostMessage(String str, Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.onPostMessage(str, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }

                public void onRelationshipValidationResult(int i, Uri uri, boolean z, Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.onRelationshipValidationResult(i, uri, z, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }

                public void onActivityResized(int i, int i2, Bundle bundle) {
                    try {
                        CustomTabsSessionToken.this.mCallbackBinder.onActivityResized(i, i2, bundle);
                    } catch (RemoteException unused) {
                        SentryLogcatAdapter.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                    }
                }
            };
        }
        this.mCallback = r1;
    }

    /* access modifiers changed from: package-private */
    public IBinder getCallbackBinder() {
        ICustomTabsCallback iCustomTabsCallback = this.mCallbackBinder;
        if (iCustomTabsCallback == null) {
            return null;
        }
        return iCustomTabsCallback.asBinder();
    }

    private IBinder getCallbackBinderAssertNotNull() {
        ICustomTabsCallback iCustomTabsCallback = this.mCallbackBinder;
        if (iCustomTabsCallback != null) {
            return iCustomTabsCallback.asBinder();
        }
        throw new IllegalStateException("CustomTabSessionToken must have valid binder or pending session");
    }

    /* access modifiers changed from: package-private */
    public PendingIntent getId() {
        return this.mSessionId;
    }

    public boolean hasCallback() {
        return this.mCallbackBinder != null;
    }

    public boolean hasId() {
        return this.mSessionId != null;
    }

    public int hashCode() {
        PendingIntent pendingIntent = this.mSessionId;
        if (pendingIntent != null) {
            return pendingIntent.hashCode();
        }
        return getCallbackBinderAssertNotNull().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomTabsSessionToken)) {
            return false;
        }
        CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) obj;
        PendingIntent id = customTabsSessionToken.getId();
        PendingIntent pendingIntent = this.mSessionId;
        boolean z = true;
        boolean z2 = pendingIntent == null;
        if (id != null) {
            z = false;
        }
        if (z2 != z) {
            return false;
        }
        if (pendingIntent != null) {
            return pendingIntent.equals(id);
        }
        return getCallbackBinderAssertNotNull().equals(customTabsSessionToken.getCallbackBinderAssertNotNull());
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    public boolean isAssociatedWith(CustomTabsSession customTabsSession) {
        return customTabsSession.getBinder().equals(this.mCallbackBinder);
    }
}
