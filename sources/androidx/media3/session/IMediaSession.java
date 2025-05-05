package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.view.Surface;
import androidx.media3.session.IMediaController;

public interface IMediaSession extends IInterface {
    public static final String DESCRIPTOR = "androidx.media3.session.IMediaSession";

    public static class Default implements IMediaSession {
        public void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void addMediaItemWithIndex(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException {
        }

        public void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException {
        }

        public void addMediaItemsWithIndex(IMediaController iMediaController, int i, int i2, IBinder iBinder) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }

        public void clearMediaItems(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void connect(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void decreaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void flushCommandQueue(IMediaController iMediaController) throws RemoteException {
        }

        public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException {
        }

        public void getItem(IMediaController iMediaController, int i, String str) throws RemoteException {
        }

        public void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException {
        }

        public void increaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void moveMediaItem(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
        }

        public void moveMediaItems(IMediaController iMediaController, int i, int i2, int i3, int i4) throws RemoteException {
        }

        public void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2) throws RemoteException {
        }

        public void pause(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void play(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void prepare(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void release(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void removeMediaItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void removeMediaItems(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
        }

        public void replaceMediaItem(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException {
        }

        public void replaceMediaItems(IMediaController iMediaController, int i, int i2, int i3, IBinder iBinder) throws RemoteException {
        }

        public void search(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
        }

        public void seekBack(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekForward(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException {
        }

        public void seekToDefaultPosition(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void seekToNext(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekToNextMediaItem(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekToPrevious(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekToPreviousMediaItem(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void seekToWithMediaItemIndex(IMediaController iMediaController, int i, int i2, long j) throws RemoteException {
        }

        public void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException {
        }

        public void setDeviceMuted(IMediaController iMediaController, int i, boolean z) throws RemoteException {
        }

        public void setDeviceMutedWithFlags(IMediaController iMediaController, int i, boolean z, int i2) throws RemoteException {
        }

        public void setDeviceVolume(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
        }

        public void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException {
        }

        public void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, long j) throws RemoteException {
        }

        public void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException {
        }

        public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, boolean z) throws RemoteException {
        }

        public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, int i2, long j) throws RemoteException {
        }

        public void setPlayWhenReady(IMediaController iMediaController, int i, boolean z) throws RemoteException {
        }

        public void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException {
        }

        public void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void setRating(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void setRatingWithMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
        }

        public void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
        }

        public void setShuffleModeEnabled(IMediaController iMediaController, int i, boolean z) throws RemoteException {
        }

        public void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
        }

        public void setVideoSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException {
        }

        public void setVolume(IMediaController iMediaController, int i, float f) throws RemoteException {
        }

        public void stop(IMediaController iMediaController, int i) throws RemoteException {
        }

        public void subscribe(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
        }

        public void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException {
        }
    }

    void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void addMediaItemWithIndex(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException;

    void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException;

    void addMediaItemsWithIndex(IMediaController iMediaController, int i, int i2, IBinder iBinder) throws RemoteException;

    void clearMediaItems(IMediaController iMediaController, int i) throws RemoteException;

    void connect(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void decreaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException;

    void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void flushCommandQueue(IMediaController iMediaController) throws RemoteException;

    void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException;

    void getItem(IMediaController iMediaController, int i, String str) throws RemoteException;

    void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException;

    void increaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException;

    void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void moveMediaItem(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void moveMediaItems(IMediaController iMediaController, int i, int i2, int i3, int i4) throws RemoteException;

    void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void pause(IMediaController iMediaController, int i) throws RemoteException;

    void play(IMediaController iMediaController, int i) throws RemoteException;

    void prepare(IMediaController iMediaController, int i) throws RemoteException;

    void release(IMediaController iMediaController, int i) throws RemoteException;

    void removeMediaItem(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void removeMediaItems(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void replaceMediaItem(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException;

    void replaceMediaItems(IMediaController iMediaController, int i, int i2, int i3, IBinder iBinder) throws RemoteException;

    void search(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void seekBack(IMediaController iMediaController, int i) throws RemoteException;

    void seekForward(IMediaController iMediaController, int i) throws RemoteException;

    void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException;

    void seekToDefaultPosition(IMediaController iMediaController, int i) throws RemoteException;

    void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void seekToNext(IMediaController iMediaController, int i) throws RemoteException;

    void seekToNextMediaItem(IMediaController iMediaController, int i) throws RemoteException;

    void seekToPrevious(IMediaController iMediaController, int i) throws RemoteException;

    void seekToPreviousMediaItem(IMediaController iMediaController, int i) throws RemoteException;

    void seekToWithMediaItemIndex(IMediaController iMediaController, int i, int i2, long j) throws RemoteException;

    void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException;

    void setDeviceMuted(IMediaController iMediaController, int i, boolean z) throws RemoteException;

    void setDeviceMutedWithFlags(IMediaController iMediaController, int i, boolean z, int i2) throws RemoteException;

    void setDeviceVolume(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException;

    void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, long j) throws RemoteException;

    void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException;

    void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, boolean z) throws RemoteException;

    void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, int i2, long j) throws RemoteException;

    void setPlayWhenReady(IMediaController iMediaController, int i, boolean z) throws RemoteException;

    void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException;

    void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void setRating(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void setRatingWithMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setShuffleModeEnabled(IMediaController iMediaController, int i, boolean z) throws RemoteException;

    void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException;

    void setVideoSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException;

    void setVolume(IMediaController iMediaController, int i, float f) throws RemoteException;

    void stop(IMediaController iMediaController, int i) throws RemoteException;

    void subscribe(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        static final int TRANSACTION_addMediaItem = 3029;
        static final int TRANSACTION_addMediaItemWithIndex = 3030;
        static final int TRANSACTION_addMediaItems = 3031;
        static final int TRANSACTION_addMediaItemsWithIndex = 3032;
        static final int TRANSACTION_clearMediaItems = 3021;
        static final int TRANSACTION_connect = 3015;
        static final int TRANSACTION_decreaseDeviceVolume = 3005;
        static final int TRANSACTION_decreaseDeviceVolumeWithFlags = 3053;
        static final int TRANSACTION_flushCommandQueue = 3045;
        static final int TRANSACTION_getChildren = 4003;
        static final int TRANSACTION_getItem = 4002;
        static final int TRANSACTION_getLibraryRoot = 4001;
        static final int TRANSACTION_getSearchResult = 4005;
        static final int TRANSACTION_increaseDeviceVolume = 3004;
        static final int TRANSACTION_increaseDeviceVolumeWithFlags = 3052;
        static final int TRANSACTION_moveMediaItem = 3022;
        static final int TRANSACTION_moveMediaItems = 3023;
        static final int TRANSACTION_onControllerResult = 3014;
        static final int TRANSACTION_onCustomCommand = 3016;
        static final int TRANSACTION_pause = 3025;
        static final int TRANSACTION_play = 3024;
        static final int TRANSACTION_prepare = 3026;
        static final int TRANSACTION_release = 3035;
        static final int TRANSACTION_removeMediaItem = 3019;
        static final int TRANSACTION_removeMediaItems = 3020;
        static final int TRANSACTION_replaceMediaItem = 3055;
        static final int TRANSACTION_replaceMediaItems = 3056;
        static final int TRANSACTION_search = 4004;
        static final int TRANSACTION_seekBack = 3040;
        static final int TRANSACTION_seekForward = 3041;
        static final int TRANSACTION_seekTo = 3038;
        static final int TRANSACTION_seekToDefaultPosition = 3036;
        static final int TRANSACTION_seekToDefaultPositionWithMediaItemIndex = 3037;
        static final int TRANSACTION_seekToNext = 3047;
        static final int TRANSACTION_seekToNextMediaItem = 3043;
        static final int TRANSACTION_seekToPrevious = 3046;
        static final int TRANSACTION_seekToPreviousMediaItem = 3042;
        static final int TRANSACTION_seekToWithMediaItemIndex = 3039;
        static final int TRANSACTION_setAudioAttributes = 3057;
        static final int TRANSACTION_setDeviceMuted = 3006;
        static final int TRANSACTION_setDeviceMutedWithFlags = 3054;
        static final int TRANSACTION_setDeviceVolume = 3003;
        static final int TRANSACTION_setDeviceVolumeWithFlags = 3051;
        static final int TRANSACTION_setMediaItem = 3007;
        static final int TRANSACTION_setMediaItemWithResetPosition = 3009;
        static final int TRANSACTION_setMediaItemWithStartPosition = 3008;
        static final int TRANSACTION_setMediaItems = 3010;
        static final int TRANSACTION_setMediaItemsWithResetPosition = 3011;
        static final int TRANSACTION_setMediaItemsWithStartIndex = 3012;
        static final int TRANSACTION_setPlayWhenReady = 3013;
        static final int TRANSACTION_setPlaybackParameters = 3027;
        static final int TRANSACTION_setPlaybackSpeed = 3028;
        static final int TRANSACTION_setPlaylistMetadata = 3033;
        static final int TRANSACTION_setRating = 3050;
        static final int TRANSACTION_setRatingWithMediaId = 3049;
        static final int TRANSACTION_setRepeatMode = 3017;
        static final int TRANSACTION_setShuffleModeEnabled = 3018;
        static final int TRANSACTION_setTrackSelectionParameters = 3048;
        static final int TRANSACTION_setVideoSurface = 3044;
        static final int TRANSACTION_setVolume = 3002;
        static final int TRANSACTION_stop = 3034;
        static final int TRANSACTION_subscribe = 4006;
        static final int TRANSACTION_unsubscribe = 4007;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IMediaSession.DESCRIPTOR);
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IMediaSession.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IMediaSession.DESCRIPTOR);
            }
            if (i != 1598968902) {
                boolean z = false;
                switch (i) {
                    case 3002:
                        setVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                        break;
                    case 3003:
                        setDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case 3004:
                        increaseDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case 3005:
                        decreaseDeviceVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case 3006:
                        IMediaController asInterface = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setDeviceMuted(asInterface, readInt, z);
                        break;
                    case TRANSACTION_setMediaItem /*3007*/:
                        setMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setMediaItemWithStartPosition /*3008*/:
                        setMediaItemWithStartPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), parcel.readLong());
                        break;
                    case TRANSACTION_setMediaItemWithResetPosition /*3009*/:
                        IMediaController asInterface2 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt2 = parcel.readInt();
                        Bundle bundle = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setMediaItemWithResetPosition(asInterface2, readInt2, bundle, z);
                        break;
                    case TRANSACTION_setMediaItems /*3010*/:
                        setMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                        break;
                    case TRANSACTION_setMediaItemsWithResetPosition /*3011*/:
                        IMediaController asInterface3 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt3 = parcel.readInt();
                        IBinder readStrongBinder = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setMediaItemsWithResetPosition(asInterface3, readInt3, readStrongBinder, z);
                        break;
                    case TRANSACTION_setMediaItemsWithStartIndex /*3012*/:
                        setMediaItemsWithStartIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readLong());
                        break;
                    case TRANSACTION_setPlayWhenReady /*3013*/:
                        IMediaController asInterface4 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt4 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setPlayWhenReady(asInterface4, readInt4, z);
                        break;
                    case TRANSACTION_onControllerResult /*3014*/:
                        onControllerResult(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_connect /*3015*/:
                        connect(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_onCustomCommand /*3016*/:
                        onCustomCommand(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setRepeatMode /*3017*/:
                        setRepeatMode(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_setShuffleModeEnabled /*3018*/:
                        IMediaController asInterface5 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt5 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setShuffleModeEnabled(asInterface5, readInt5, z);
                        break;
                    case TRANSACTION_removeMediaItem /*3019*/:
                        removeMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_removeMediaItems /*3020*/:
                        removeMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_clearMediaItems /*3021*/:
                        clearMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_moveMediaItem /*3022*/:
                        moveMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_moveMediaItems /*3023*/:
                        moveMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_play /*3024*/:
                        play(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_pause /*3025*/:
                        pause(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_prepare /*3026*/:
                        prepare(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_setPlaybackParameters /*3027*/:
                        setPlaybackParameters(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setPlaybackSpeed /*3028*/:
                        setPlaybackSpeed(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                        break;
                    case TRANSACTION_addMediaItem /*3029*/:
                        addMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_addMediaItemWithIndex /*3030*/:
                        addMediaItemWithIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_addMediaItems /*3031*/:
                        addMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                        break;
                    case TRANSACTION_addMediaItemsWithIndex /*3032*/:
                        addMediaItemsWithIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                        break;
                    case TRANSACTION_setPlaylistMetadata /*3033*/:
                        setPlaylistMetadata(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_stop /*3034*/:
                        stop(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_release /*3035*/:
                        release(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekToDefaultPosition /*3036*/:
                        seekToDefaultPosition(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekToDefaultPositionWithMediaItemIndex /*3037*/:
                        seekToDefaultPositionWithMediaItemIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_seekTo /*3038*/:
                        seekTo(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong());
                        break;
                    case TRANSACTION_seekToWithMediaItemIndex /*3039*/:
                        seekToWithMediaItemIndex(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readLong());
                        break;
                    case TRANSACTION_seekBack /*3040*/:
                        seekBack(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekForward /*3041*/:
                        seekForward(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekToPreviousMediaItem /*3042*/:
                        seekToPreviousMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekToNextMediaItem /*3043*/:
                        seekToNextMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_setVideoSurface /*3044*/:
                        setVideoSurface(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Surface) _Parcel.readTypedObject(parcel, Surface.CREATOR));
                        break;
                    case TRANSACTION_flushCommandQueue /*3045*/:
                        flushCommandQueue(IMediaController.Stub.asInterface(parcel.readStrongBinder()));
                        break;
                    case TRANSACTION_seekToPrevious /*3046*/:
                        seekToPrevious(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_seekToNext /*3047*/:
                        seekToNext(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        break;
                    case TRANSACTION_setTrackSelectionParameters /*3048*/:
                        setTrackSelectionParameters(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setRatingWithMediaId /*3049*/:
                        setRatingWithMediaId(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setRating /*3050*/:
                        setRating(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_setDeviceVolumeWithFlags /*3051*/:
                        setDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_increaseDeviceVolumeWithFlags /*3052*/:
                        increaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_decreaseDeviceVolumeWithFlags /*3053*/:
                        decreaseDeviceVolumeWithFlags(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        break;
                    case TRANSACTION_setDeviceMutedWithFlags /*3054*/:
                        IMediaController asInterface6 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt6 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setDeviceMutedWithFlags(asInterface6, readInt6, z, parcel.readInt());
                        break;
                    case TRANSACTION_replaceMediaItem /*3055*/:
                        replaceMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                        break;
                    case TRANSACTION_replaceMediaItems /*3056*/:
                        replaceMediaItems(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                        break;
                    case TRANSACTION_setAudioAttributes /*3057*/:
                        IMediaController asInterface7 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt7 = parcel.readInt();
                        Bundle bundle2 = (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setAudioAttributes(asInterface7, readInt7, bundle2, z);
                        break;
                    default:
                        switch (i) {
                            case 4001:
                                getLibraryRoot(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                break;
                            case 4002:
                                getItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                                break;
                            case 4003:
                                getChildren(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                break;
                            case 4004:
                                search(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                break;
                            case 4005:
                                getSearchResult(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                break;
                            case 4006:
                                subscribe(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), (Bundle) _Parcel.readTypedObject(parcel, Bundle.CREATOR));
                                break;
                            case TRANSACTION_unsubscribe /*4007*/:
                                unsubscribe(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                                break;
                            default:
                                return super.onTransact(i, parcel, parcel2, i2);
                        }
                }
                return true;
            }
            parcel2.writeString(IMediaSession.DESCRIPTOR);
            return true;
        }

        private static class Proxy implements IMediaSession {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IMediaSession.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void setVolume(IMediaController iMediaController, int i, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeFloat(f);
                    this.mRemote.transact(3002, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setDeviceVolume(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3003, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(Stub.TRANSACTION_setDeviceVolumeWithFlags, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void increaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(3004, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_increaseDeviceVolumeWithFlags, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void decreaseDeviceVolume(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(3005, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_decreaseDeviceVolumeWithFlags, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setDeviceMuted(IMediaController iMediaController, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(3006, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setDeviceMutedWithFlags(IMediaController iMediaController, int i, boolean z, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_setDeviceMutedWithFlags, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    int i2 = 0;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_setAudioAttributes, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    obtain.writeLong(j);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemWithStartPosition, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    int i2 = 0;
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemWithResetPosition, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemsWithResetPosition, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(Stub.TRANSACTION_setMediaItemsWithStartIndex, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlayWhenReady(IMediaController iMediaController, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlayWhenReady, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onControllerResult, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void connect(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_connect, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    _Parcel.writeTypedObject(obtain, bundle2, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onCustomCommand, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_setRepeatMode, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setShuffleModeEnabled(IMediaController iMediaController, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_setShuffleModeEnabled, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void removeMediaItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_removeMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void removeMediaItems(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(Stub.TRANSACTION_removeMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void clearMediaItems(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_clearMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void moveMediaItem(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(Stub.TRANSACTION_moveMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void moveMediaItems(IMediaController iMediaController, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(Stub.TRANSACTION_moveMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void replaceMediaItem(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_replaceMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void replaceMediaItems(IMediaController iMediaController, int i, int i2, int i3, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_replaceMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void play(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_play, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void pause(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_pause, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepare(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_prepare, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaybackParameters, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeFloat(f);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaybackSpeed, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void addMediaItemWithIndex(IMediaController iMediaController, int i, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItemWithIndex, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItems, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void addMediaItemsWithIndex(IMediaController iMediaController, int i, int i2, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_addMediaItemsWithIndex, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setPlaylistMetadata, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void stop(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_stop, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void release(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_release, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToDefaultPosition(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekToDefaultPosition, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_seekToDefaultPositionWithMediaItemIndex, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(Stub.TRANSACTION_seekTo, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToWithMediaItemIndex(IMediaController iMediaController, int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(Stub.TRANSACTION_seekToWithMediaItemIndex, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekBack(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekBack, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekForward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekForward, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToPreviousMediaItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekToPreviousMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToNextMediaItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekToNextMediaItem, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setVideoSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, surface, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setVideoSurface, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void flushCommandQueue(IMediaController iMediaController) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    this.mRemote.transact(Stub.TRANSACTION_flushCommandQueue, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToPrevious(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekToPrevious, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekToNext(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_seekToNext, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setTrackSelectionParameters, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRatingWithMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setRatingWithMediaId, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRating(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(Stub.TRANSACTION_setRating, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4001, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getItem(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(4002, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4003, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void search(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4004, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4005, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void subscribe(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, bundle, 0);
                    this.mRemote.transact(4006, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IMediaSession.DESCRIPTOR);
                    obtain.writeStrongInterface(iMediaController);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(Stub.TRANSACTION_unsubscribe, obtain, (Parcel) null, 1);
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
