package com.google.android.gms.internal.p001authapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zbu  reason: invalid package */
/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public interface zbu extends IInterface {
    void zbb(Status status, SavePasswordResult savePasswordResult) throws RemoteException;
}
