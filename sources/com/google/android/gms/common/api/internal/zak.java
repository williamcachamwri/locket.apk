package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.squareup.kotlinpoet.FileSpecKt;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zak extends zap {
    private final SparseArray zad = new SparseArray();

    private zak(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment, GoogleApiAvailability.getInstance());
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public static zak zaa(LifecycleActivity lifecycleActivity) {
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        zak zak = (zak) fragment.getCallbackOrNull("AutoManageHelper", zak.class);
        if (zak != null) {
            return zak;
        }
        return new zak(fragment);
    }

    private final zaj zai(int i) {
        if (this.zad.size() <= i) {
            return null;
        }
        SparseArray sparseArray = this.zad;
        return (zaj) sparseArray.get(sparseArray.keyAt(i));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zai = zai(i);
            if (zai != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zai.zaa);
                printWriter.println(":");
                zai.zab.dump(String.valueOf(str).concat(FileSpecKt.DEFAULT_INDENT), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        SparseArray sparseArray = this.zad;
        boolean z = this.zaa;
        String valueOf = String.valueOf(sparseArray);
        Log.d("AutoManageHelper", "onStart " + z + " " + valueOf);
        if (this.zab.get() == null) {
            for (int i = 0; i < this.zad.size(); i++) {
                zaj zai = zai(i);
                if (zai != null) {
                    zai.zab.connect();
                }
            }
        }
    }

    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zai = zai(i);
            if (zai != null) {
                zai.zab.disconnect();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zab(ConnectionResult connectionResult, int i) {
        SentryLogcatAdapter.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            SentryLogcatAdapter.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zaj zaj = (zaj) this.zad.get(i);
        if (zaj != null) {
            zae(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaj.zac;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zac() {
        for (int i = 0; i < this.zad.size(); i++) {
            zaj zai = zai(i);
            if (zai != null) {
                zai.zab.connect();
            }
        }
    }

    public final void zad(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        int indexOfKey = this.zad.indexOfKey(i);
        Preconditions.checkState(indexOfKey < 0, "Already managing a GoogleApiClient with id " + i);
        zam zam = (zam) this.zab.get();
        boolean z = this.zaa;
        String valueOf = String.valueOf(zam);
        Log.d("AutoManageHelper", "starting AutoManage for client " + i + " " + z + " " + valueOf);
        zaj zaj = new zaj(this, i, googleApiClient, onConnectionFailedListener);
        googleApiClient.registerConnectionFailedListener(zaj);
        this.zad.put(i, zaj);
        if (this.zaa && zam == null) {
            Log.d("AutoManageHelper", "connecting ".concat(googleApiClient.toString()));
            googleApiClient.connect();
        }
    }

    public final void zae(int i) {
        zaj zaj = (zaj) this.zad.get(i);
        this.zad.remove(i);
        if (zaj != null) {
            zaj.zab.unregisterConnectionFailedListener(zaj);
            zaj.zab.disconnect();
        }
    }
}
