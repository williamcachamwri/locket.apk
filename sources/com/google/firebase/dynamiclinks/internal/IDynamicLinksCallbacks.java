package com.google.firebase.dynamiclinks.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public interface IDynamicLinksCallbacks extends IInterface {
    public static final String DESCRIPTOR = "com.google.firebase.dynamiclinks.internal.IDynamicLinksCallbacks";

    public static class Default implements IDynamicLinksCallbacks {
        public IBinder asBinder() {
            return null;
        }

        public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
        }

        public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
        }
    }

    void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException;

    void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicLinksCallbacks {
        static final int TRANSACTION_onCreateShortDynamicLink = 2;
        static final int TRANSACTION_onGetDynamicLink = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IDynamicLinksCallbacks.DESCRIPTOR);
        }

        public static IDynamicLinksCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IDynamicLinksCallbacks.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDynamicLinksCallbacks)) {
                return new Proxy(iBinder);
            }
            return (IDynamicLinksCallbacks) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IDynamicLinksCallbacks.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 1) {
                    onGetDynamicLink((Status) _Parcel.readTypedObject(parcel, Status.CREATOR), (DynamicLinkData) _Parcel.readTypedObject(parcel, DynamicLinkData.CREATOR));
                } else if (i != 2) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    onCreateShortDynamicLink((Status) _Parcel.readTypedObject(parcel, Status.CREATOR), (ShortDynamicLinkImpl) _Parcel.readTypedObject(parcel, ShortDynamicLinkImpl.CREATOR));
                }
                return true;
            }
            parcel2.writeString(IDynamicLinksCallbacks.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IDynamicLinksCallbacks {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IDynamicLinksCallbacks.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onGetDynamicLink(Status status, DynamicLinkData dynamicLinkData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDynamicLinksCallbacks.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, status, 0);
                    _Parcel.writeTypedObject(obtain, dynamicLinkData, 0);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCreateShortDynamicLink(Status status, ShortDynamicLinkImpl shortDynamicLinkImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IDynamicLinksCallbacks.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, status, 0);
                    _Parcel.writeTypedObject(obtain, shortDynamicLinkImpl, 0);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
                return;
            }
            parcel.writeInt(0);
        }
    }
}
