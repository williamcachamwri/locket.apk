package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public interface IMediaController extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaController";

    public static class Default implements IMediaController {
        public IBinder asBinder() {
            return null;
        }

        public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException {
        }

        public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        public void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
        }

        public void onConnected(int i, Bundle bundle) throws RemoteException {
        }

        public void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        public void onDisconnected(int i) throws RemoteException {
        }

        public void onError(int i, Bundle bundle) throws RemoteException {
        }

        public void onExtrasChanged(int i, Bundle bundle) throws RemoteException {
        }

        public void onLibraryResult(int i, Bundle bundle) throws RemoteException {
        }

        public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException {
        }

        public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException {
        }

        public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        public void onRenderedFirstFrame(int i) throws RemoteException {
        }

        public void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
        }

        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
        }

        public void onSessionResult(int i, Bundle bundle) throws RemoteException {
        }

        public void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException {
        }

        public void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException {
        }
    }

    void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException;

    void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException;

    void onConnected(int i, Bundle bundle) throws RemoteException;

    void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onDisconnected(int i) throws RemoteException;

    void onError(int i, Bundle bundle) throws RemoteException;

    void onExtrasChanged(int i, Bundle bundle) throws RemoteException;

    void onLibraryResult(int i, Bundle bundle) throws RemoteException;

    void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException;

    void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException;

    void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onRenderedFirstFrame(int i) throws RemoteException;

    void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException;

    void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException;

    void onSessionResult(int i, Bundle bundle) throws RemoteException;

    void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException;

    void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController {
        static final int TRANSACTION_onAvailableCommandsChangedFromPlayer = 3009;
        static final int TRANSACTION_onAvailableCommandsChangedFromSession = 3010;
        static final int TRANSACTION_onChildrenChanged = 4001;
        static final int TRANSACTION_onConnected = 3001;
        static final int TRANSACTION_onCustomCommand = 3005;
        static final int TRANSACTION_onDisconnected = 3006;
        static final int TRANSACTION_onError = 3015;
        static final int TRANSACTION_onExtrasChanged = 3012;
        static final int TRANSACTION_onLibraryResult = 3003;
        static final int TRANSACTION_onPeriodicSessionPositionInfoChanged = 3008;
        static final int TRANSACTION_onPlayerInfoChanged = 3007;
        static final int TRANSACTION_onPlayerInfoChangedWithExclusions = 3013;
        static final int TRANSACTION_onRenderedFirstFrame = 3011;
        static final int TRANSACTION_onSearchResultChanged = 4002;
        static final int TRANSACTION_onSessionActivityChanged = 3014;
        static final int TRANSACTION_onSessionResult = 3002;
        static final int TRANSACTION_onSetCustomLayout = 3004;
        static final int TRANSACTION_onSetMediaButtonPreferences = 3016;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMediaController.DESCRIPTOR);
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMediaController.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaController)) {
                return new Proxy(iBinder);
            }
            return (IMediaController) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMediaController.DESCRIPTOR);
            }
            if (i != 1598968902) {
                if (i == 4001) {
                    onChildrenChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                } else if (i != 4002) {
                    switch (i) {
                        case 3001:
                            onConnected(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case 3002:
                            onSessionResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case 3003:
                            onLibraryResult(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case 3004:
                            onSetCustomLayout(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                            break;
                        case 3005:
                            onCustomCommand(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case 3006:
                            onDisconnected(parcel.readInt());
                            break;
                        case TRANSACTION_onPlayerInfoChanged /*3007*/:
                            onPlayerInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                            break;
                        case TRANSACTION_onPeriodicSessionPositionInfoChanged /*3008*/:
                            onPeriodicSessionPositionInfoChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onAvailableCommandsChangedFromPlayer /*3009*/:
                            onAvailableCommandsChangedFromPlayer(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onAvailableCommandsChangedFromSession /*3010*/:
                            onAvailableCommandsChangedFromSession(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onRenderedFirstFrame /*3011*/:
                            onRenderedFirstFrame(parcel.readInt());
                            break;
                        case TRANSACTION_onExtrasChanged /*3012*/:
                            onExtrasChanged(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onPlayerInfoChangedWithExclusions /*3013*/:
                            onPlayerInfoChangedWithExclusions(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onSessionActivityChanged /*3014*/:
                            onSessionActivityChanged(parcel.readInt(), (PendingIntent) _Parcel.readTypedObject(parcel, PendingIntent.CREATOR));
                            break;
                        case TRANSACTION_onError /*3015*/:
                            onError(parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                            break;
                        case TRANSACTION_onSetMediaButtonPreferences /*3016*/:
                            onSetMediaButtonPreferences(parcel.readInt(), parcel.createTypedArrayList(Bundle.CREATOR));
                            break;
                        default:
                            return super.onTransact(i, parcel, parcel2, i2);
                    }
                } else {
                    onSearchResultChanged(parcel.readInt(), parcel.readString(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                }
                return true;
            }
            parcel2.writeString(IMediaController.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IMediaController {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IMediaController.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onConnected(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3001, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSessionResult(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3002, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onLibraryResult(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(3003, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSetCustomLayout(int i, List<Bundle> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedList(obtain, list, 0);
                    this.mRemote.transact(3004, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomCommand(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    _Parcel.writeTypedObject(obtain, bundle2, 0);
                    this.mRemote.transact(3005, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDisconnected(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3006, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    int i2 = 0;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChanged, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    _Parcel.writeTypedObject(obtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPlayerInfoChangedWithExclusions, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onPeriodicSessionPositionInfoChanged, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromPlayer, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    _Parcel.writeTypedObject(obtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onAvailableCommandsChangedFromSession, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRenderedFirstFrame(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_onRenderedFirstFrame, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onExtrasChanged(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onExtrasChanged, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, pendingIntent, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSessionActivityChanged, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onError(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onError, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSetMediaButtonPreferences(int i, List<Bundle> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    _Parcel.writeTypedList(obtain, list, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onSetMediaButtonPreferences, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onChildrenChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4001, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaController.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4002, obtain, (Parcel) null, 1);
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

        /* access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedList(Parcel parcel, List<T> list, int i) {
            if (list == null) {
                parcel.writeInt(-1);
                return;
            }
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                writeTypedObject(parcel, (Parcelable) list.get(i2), i);
            }
        }
    }
}
