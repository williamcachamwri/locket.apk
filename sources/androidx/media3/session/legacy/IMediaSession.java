package androidx.media3.session.legacy;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.ArrayList;
import java.util.List;

public interface IMediaSession extends IInterface {
    void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException;

    void adjustVolume(int i, int i2, String str) throws RemoteException;

    void fastForward() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    long getFlags() throws RemoteException;

    PendingIntent getLaunchPendingIntent() throws RemoteException;

    MediaMetadataCompat getMetadata() throws RemoteException;

    String getPackageName() throws RemoteException;

    PlaybackStateCompat getPlaybackState() throws RemoteException;

    List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException;

    CharSequence getQueueTitle() throws RemoteException;

    int getRatingType() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    Bundle getSessionInfo() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    String getTag() throws RemoteException;

    ParcelableVolumeInfo getVolumeAttributes() throws RemoteException;

    boolean isCaptioningEnabled() throws RemoteException;

    boolean isShuffleModeEnabledRemoved() throws RemoteException;

    boolean isTransportControlEnabled() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playFromMediaId(String str, Bundle bundle) throws RemoteException;

    void playFromSearch(String str, Bundle bundle) throws RemoteException;

    void playFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void prepare() throws RemoteException;

    void prepareFromMediaId(String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException;

    void previous() throws RemoteException;

    void rate(RatingCompat ratingCompat) throws RemoteException;

    void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException;

    void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException;

    void removeQueueItemAt(int i) throws RemoteException;

    void rewind() throws RemoteException;

    void seekTo(long j) throws RemoteException;

    void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException;

    void sendCustomAction(String str, Bundle bundle) throws RemoteException;

    boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException;

    void setCaptioningEnabled(boolean z) throws RemoteException;

    void setPlaybackSpeed(float f) throws RemoteException;

    void setRepeatMode(int i) throws RemoteException;

    void setShuffleMode(int i) throws RemoteException;

    void setShuffleModeEnabledRemoved(boolean z) throws RemoteException;

    void setVolumeTo(int i, int i2, String str) throws RemoteException;

    void skipToQueueItem(long j) throws RemoteException;

    void stop() throws RemoteException;

    void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
        static final int TRANSACTION_addQueueItem = 41;
        static final int TRANSACTION_addQueueItemAt = 42;
        static final int TRANSACTION_adjustVolume = 11;
        static final int TRANSACTION_fastForward = 22;
        static final int TRANSACTION_getExtras = 31;
        static final int TRANSACTION_getFlags = 9;
        static final int TRANSACTION_getLaunchPendingIntent = 8;
        static final int TRANSACTION_getMetadata = 27;
        static final int TRANSACTION_getPackageName = 6;
        static final int TRANSACTION_getPlaybackState = 28;
        static final int TRANSACTION_getQueue = 29;
        static final int TRANSACTION_getQueueTitle = 30;
        static final int TRANSACTION_getRatingType = 32;
        static final int TRANSACTION_getRepeatMode = 37;
        static final int TRANSACTION_getSessionInfo = 50;
        static final int TRANSACTION_getShuffleMode = 47;
        static final int TRANSACTION_getTag = 7;
        static final int TRANSACTION_getVolumeAttributes = 10;
        static final int TRANSACTION_isCaptioningEnabled = 45;
        static final int TRANSACTION_isShuffleModeEnabledRemoved = 38;
        static final int TRANSACTION_isTransportControlEnabled = 5;
        static final int TRANSACTION_next = 20;
        static final int TRANSACTION_pause = 18;
        static final int TRANSACTION_play = 13;
        static final int TRANSACTION_playFromMediaId = 14;
        static final int TRANSACTION_playFromSearch = 15;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 33;
        static final int TRANSACTION_prepareFromMediaId = 34;
        static final int TRANSACTION_prepareFromSearch = 35;
        static final int TRANSACTION_prepareFromUri = 36;
        static final int TRANSACTION_previous = 21;
        static final int TRANSACTION_rate = 25;
        static final int TRANSACTION_rateWithExtras = 51;
        static final int TRANSACTION_registerCallbackListener = 3;
        static final int TRANSACTION_removeQueueItem = 43;
        static final int TRANSACTION_removeQueueItemAt = 44;
        static final int TRANSACTION_rewind = 23;
        static final int TRANSACTION_seekTo = 24;
        static final int TRANSACTION_sendCommand = 1;
        static final int TRANSACTION_sendCustomAction = 26;
        static final int TRANSACTION_sendMediaButton = 2;
        static final int TRANSACTION_setCaptioningEnabled = 46;
        static final int TRANSACTION_setPlaybackSpeed = 49;
        static final int TRANSACTION_setRepeatMode = 39;
        static final int TRANSACTION_setShuffleMode = 48;
        static final int TRANSACTION_setShuffleModeEnabledRemoved = 40;
        static final int TRANSACTION_setVolumeTo = 12;
        static final int TRANSACTION_skipToQueueItem = 17;
        static final int TRANSACTION_stop = 19;
        static final int TRANSACTION_unregisterCallbackListener = 4;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: androidx.media3.session.legacy.MediaSessionCompat$ResultReceiverWrapper} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: android.view.KeyEvent} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: androidx.media3.session.legacy.RatingCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: androidx.media3.session.legacy.MediaDescriptionCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: androidx.media3.session.legacy.MediaDescriptionCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: androidx.media3.session.legacy.MediaDescriptionCompat} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r3v0 */
        /* JADX WARNING: type inference failed for: r3v43 */
        /* JADX WARNING: type inference failed for: r3v44 */
        /* JADX WARNING: type inference failed for: r3v45 */
        /* JADX WARNING: type inference failed for: r3v46 */
        /* JADX WARNING: type inference failed for: r3v47 */
        /* JADX WARNING: type inference failed for: r3v48 */
        /* JADX WARNING: type inference failed for: r3v49 */
        /* JADX WARNING: type inference failed for: r3v50 */
        /* JADX WARNING: type inference failed for: r3v51 */
        /* JADX WARNING: type inference failed for: r3v52 */
        /* JADX WARNING: type inference failed for: r3v53 */
        /* JADX WARNING: type inference failed for: r3v54 */
        /* JADX WARNING: type inference failed for: r3v55 */
        /* JADX WARNING: type inference failed for: r3v56 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "android.support.v4.media.session.IMediaSession"
                if (r5 == r0) goto L_0x05db
                r0 = 0
                r3 = 0
                switch(r5) {
                    case 1: goto L_0x05a8;
                    case 2: goto L_0x057f;
                    case 3: goto L_0x0567;
                    case 4: goto L_0x054f;
                    case 5: goto L_0x0535;
                    case 6: goto L_0x051b;
                    case 7: goto L_0x0501;
                    case 8: goto L_0x04d8;
                    case 9: goto L_0x04be;
                    case 10: goto L_0x0495;
                    case 11: goto L_0x0479;
                    case 12: goto L_0x045d;
                    case 13: goto L_0x044d;
                    case 14: goto L_0x042a;
                    case 15: goto L_0x0407;
                    case 16: goto L_0x03d8;
                    case 17: goto L_0x03c4;
                    case 18: goto L_0x03b4;
                    case 19: goto L_0x03a4;
                    case 20: goto L_0x0394;
                    case 21: goto L_0x0384;
                    case 22: goto L_0x0374;
                    case 23: goto L_0x0364;
                    case 24: goto L_0x0350;
                    case 25: goto L_0x0331;
                    case 26: goto L_0x030e;
                    case 27: goto L_0x02e5;
                    case 28: goto L_0x02bc;
                    case 29: goto L_0x02a2;
                    case 30: goto L_0x0279;
                    case 31: goto L_0x0250;
                    case 32: goto L_0x0236;
                    case 33: goto L_0x0226;
                    case 34: goto L_0x0203;
                    case 35: goto L_0x01e0;
                    case 36: goto L_0x01b1;
                    case 37: goto L_0x0197;
                    case 38: goto L_0x017d;
                    case 39: goto L_0x0169;
                    case 40: goto L_0x0152;
                    case 41: goto L_0x0133;
                    case 42: goto L_0x0110;
                    case 43: goto L_0x00f1;
                    case 44: goto L_0x00dd;
                    case 45: goto L_0x00c3;
                    case 46: goto L_0x00ac;
                    case 47: goto L_0x0092;
                    case 48: goto L_0x007e;
                    case 49: goto L_0x006a;
                    case 50: goto L_0x0041;
                    case 51: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r5 = super.onTransact(r5, r6, r7, r8)
                return r5
            L_0x0012:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0024
                android.os.Parcelable$Creator<androidx.media3.session.legacy.RatingCompat> r5 = androidx.media3.session.legacy.RatingCompat.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                androidx.media3.session.legacy.RatingCompat r5 = (androidx.media3.session.legacy.RatingCompat) r5
                goto L_0x0025
            L_0x0024:
                r5 = r3
            L_0x0025:
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0034
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0034:
                r4.rateWithExtras(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0041:
                r6.enforceInterface(r2)
                android.os.Bundle r5 = r4.getSessionInfo()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x0060
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x0069
            L_0x0060:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x0069:
                return r1
            L_0x006a:
                r6.enforceInterface(r2)
                float r5 = r6.readFloat()
                r4.setPlaybackSpeed(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x007e:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                r4.setShuffleMode(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0092:
                r6.enforceInterface(r2)
                int r5 = r4.getShuffleMode()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x00ac:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x00b6
                r0 = r1
            L_0x00b6:
                r4.setCaptioningEnabled(r0)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x00c3:
                r6.enforceInterface(r2)
                boolean r5 = r4.isCaptioningEnabled()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x00dd:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                r4.removeQueueItemAt(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x00f1:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0103
                android.os.Parcelable$Creator<androidx.media3.session.legacy.MediaDescriptionCompat> r5 = androidx.media3.session.legacy.MediaDescriptionCompat.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                androidx.media3.session.legacy.MediaDescriptionCompat r3 = (androidx.media3.session.legacy.MediaDescriptionCompat) r3
            L_0x0103:
                r4.removeQueueItem(r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0110:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0122
                android.os.Parcelable$Creator<androidx.media3.session.legacy.MediaDescriptionCompat> r5 = androidx.media3.session.legacy.MediaDescriptionCompat.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                androidx.media3.session.legacy.MediaDescriptionCompat r3 = (androidx.media3.session.legacy.MediaDescriptionCompat) r3
            L_0x0122:
                int r5 = r6.readInt()
                r4.addQueueItemAt(r3, r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0133:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0145
                android.os.Parcelable$Creator<androidx.media3.session.legacy.MediaDescriptionCompat> r5 = androidx.media3.session.legacy.MediaDescriptionCompat.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                androidx.media3.session.legacy.MediaDescriptionCompat r3 = (androidx.media3.session.legacy.MediaDescriptionCompat) r3
            L_0x0145:
                r4.addQueueItem(r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0152:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x015c
                r0 = r1
            L_0x015c:
                r4.setShuffleModeEnabledRemoved(r0)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0169:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                r4.setRepeatMode(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x017d:
                r6.enforceInterface(r2)
                boolean r5 = r4.isShuffleModeEnabledRemoved()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x0197:
                r6.enforceInterface(r2)
                int r5 = r4.getRepeatMode()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x01b1:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x01c3
                android.os.Parcelable$Creator r5 = android.net.Uri.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                android.net.Uri r5 = (android.net.Uri) r5
                goto L_0x01c4
            L_0x01c3:
                r5 = r3
            L_0x01c4:
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x01d3
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01d3:
                r4.prepareFromUri(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x01e0:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x01f6
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x01f6:
                r4.prepareFromSearch(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0203:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0219
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0219:
                r4.prepareFromMediaId(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0226:
                r6.enforceInterface(r2)
                r4.prepare()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0236:
                r6.enforceInterface(r2)
                int r5 = r4.getRatingType()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x0250:
                r6.enforceInterface(r2)
                android.os.Bundle r5 = r4.getExtras()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x026f
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x0278
            L_0x026f:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x0278:
                return r1
            L_0x0279:
                r6.enforceInterface(r2)
                java.lang.CharSequence r5 = r4.getQueueTitle()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x0298
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                android.text.TextUtils.writeToParcel(r5, r7, r1)
                goto L_0x02a1
            L_0x0298:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x02a1:
                return r1
            L_0x02a2:
                r6.enforceInterface(r2)
                java.util.List r5 = r4.getQueue()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeTypedList(r5)
                return r1
            L_0x02bc:
                r6.enforceInterface(r2)
                androidx.media3.session.legacy.PlaybackStateCompat r5 = r4.getPlaybackState()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x02db
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x02e4
            L_0x02db:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x02e4:
                return r1
            L_0x02e5:
                r6.enforceInterface(r2)
                androidx.media3.session.legacy.MediaMetadataCompat r5 = r4.getMetadata()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x0304
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x030d
            L_0x0304:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x030d:
                return r1
            L_0x030e:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0324
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0324:
                r4.sendCustomAction(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0331:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0343
                android.os.Parcelable$Creator<androidx.media3.session.legacy.RatingCompat> r5 = androidx.media3.session.legacy.RatingCompat.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                androidx.media3.session.legacy.RatingCompat r3 = (androidx.media3.session.legacy.RatingCompat) r3
            L_0x0343:
                r4.rate(r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0350:
                r6.enforceInterface(r2)
                long r5 = r6.readLong()
                r4.seekTo(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0364:
                r6.enforceInterface(r2)
                r4.rewind()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0374:
                r6.enforceInterface(r2)
                r4.fastForward()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0384:
                r6.enforceInterface(r2)
                r4.previous()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0394:
                r6.enforceInterface(r2)
                r4.next()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x03a4:
                r6.enforceInterface(r2)
                r4.stop()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x03b4:
                r6.enforceInterface(r2)
                r4.pause()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x03c4:
                r6.enforceInterface(r2)
                long r5 = r6.readLong()
                r4.skipToQueueItem(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x03d8:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x03ea
                android.os.Parcelable$Creator r5 = android.net.Uri.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                android.net.Uri r5 = (android.net.Uri) r5
                goto L_0x03eb
            L_0x03ea:
                r5 = r3
            L_0x03eb:
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x03fa
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x03fa:
                r4.playFromUri(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0407:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x041d
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x041d:
                r4.playFromSearch(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x042a:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0440
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r3 = r6
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0440:
                r4.playFromMediaId(r5, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x044d:
                r6.enforceInterface(r2)
                r4.play()
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x045d:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                int r8 = r6.readInt()
                java.lang.String r6 = r6.readString()
                r4.setVolumeTo(r5, r8, r6)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0479:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                int r8 = r6.readInt()
                java.lang.String r6 = r6.readString()
                r4.adjustVolume(r5, r8, r6)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0495:
                r6.enforceInterface(r2)
                androidx.media3.session.legacy.ParcelableVolumeInfo r5 = r4.getVolumeAttributes()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x04b4
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x04bd
            L_0x04b4:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x04bd:
                return r1
            L_0x04be:
                r6.enforceInterface(r2)
                long r5 = r4.getFlags()
                java.lang.Object r8 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r8 = (android.os.Parcel) r8
                r8.writeNoException()
                java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r7 = (android.os.Parcel) r7
                r7.writeLong(r5)
                return r1
            L_0x04d8:
                r6.enforceInterface(r2)
                android.app.PendingIntent r5 = r4.getLaunchPendingIntent()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                if (r5 == 0) goto L_0x04f7
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r1)
                r5.writeToParcel(r7, r1)
                goto L_0x0500
            L_0x04f7:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeInt(r0)
            L_0x0500:
                return r1
            L_0x0501:
                r6.enforceInterface(r2)
                java.lang.String r5 = r4.getTag()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeString(r5)
                return r1
            L_0x051b:
                r6.enforceInterface(r2)
                java.lang.String r5 = r4.getPackageName()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeString(r5)
                return r1
            L_0x0535:
                r6.enforceInterface(r2)
                boolean r5 = r4.isTransportControlEnabled()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x054f:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                androidx.media3.session.legacy.IMediaControllerCallback r5 = androidx.media3.session.legacy.IMediaControllerCallback.Stub.asInterface(r5)
                r4.unregisterCallbackListener(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x0567:
                r6.enforceInterface(r2)
                android.os.IBinder r5 = r6.readStrongBinder()
                androidx.media3.session.legacy.IMediaControllerCallback r5 = androidx.media3.session.legacy.IMediaControllerCallback.Stub.asInterface(r5)
                r4.registerCallbackListener(r5)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x057f:
                r6.enforceInterface(r2)
                int r5 = r6.readInt()
                if (r5 == 0) goto L_0x0591
                android.os.Parcelable$Creator r5 = android.view.KeyEvent.CREATOR
                java.lang.Object r5 = r5.createFromParcel(r6)
                r3 = r5
                android.view.KeyEvent r3 = (android.view.KeyEvent) r3
            L_0x0591:
                boolean r5 = r4.sendMediaButton(r3)
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeNoException()
                java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r6 = (android.os.Parcel) r6
                r6.writeInt(r5)
                return r1
            L_0x05a8:
                r6.enforceInterface(r2)
                java.lang.String r5 = r6.readString()
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x05be
                android.os.Parcelable$Creator r8 = android.os.Bundle.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.os.Bundle r8 = (android.os.Bundle) r8
                goto L_0x05bf
            L_0x05be:
                r8 = r3
            L_0x05bf:
                int r0 = r6.readInt()
                if (r0 == 0) goto L_0x05ce
                android.os.Parcelable$Creator<androidx.media3.session.legacy.MediaSessionCompat$ResultReceiverWrapper> r0 = androidx.media3.session.legacy.MediaSessionCompat.ResultReceiverWrapper.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r3 = r6
                androidx.media3.session.legacy.MediaSessionCompat$ResultReceiverWrapper r3 = (androidx.media3.session.legacy.MediaSessionCompat.ResultReceiverWrapper) r3
            L_0x05ce:
                r4.sendCommand(r5, r8, r3)
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeNoException()
                return r1
            L_0x05db:
                java.lang.Object r5 = androidx.media3.common.util.Assertions.checkNotNull(r7)
                android.os.Parcel r5 = (android.os.Parcel) r5
                r5.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.legacy.IMediaSession.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaSession {
            public static IMediaSession sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return "android.support.v4.media.session.IMediaSession";
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void sendCommand(String str, Bundle bundle, MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (resultReceiverWrapper != null) {
                        obtain.writeInt(1);
                        resultReceiverWrapper.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendCommand(str, bundle, resultReceiverWrapper);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean sendMediaButton(KeyEvent keyEvent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    boolean z = true;
                    if (keyEvent != null) {
                        obtain.writeInt(1);
                        keyEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendMediaButton(keyEvent);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).registerCallbackListener(iMediaControllerCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterCallbackListener(IMediaControllerCallback iMediaControllerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaControllerCallback != null ? iMediaControllerCallback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).unregisterCallbackListener(iMediaControllerCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isTransportControlEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    boolean z = false;
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isTransportControlEnabled();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getPackageName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getPackageName();
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getTag();
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public PendingIntent getLaunchPendingIntent() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getLaunchPendingIntent();
                    }
                    obtain2.readException();
                    PendingIntent pendingIntent = obtain2.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return pendingIntent;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getFlags() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getFlags();
                    }
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    obtain2.recycle();
                    obtain.recycle();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelableVolumeInfo getVolumeAttributes() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getVolumeAttributes();
                    }
                    obtain2.readException();
                    ParcelableVolumeInfo createFromParcel = obtain2.readInt() != 0 ? ParcelableVolumeInfo.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void adjustVolume(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).adjustVolume(i, i2, str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setVolumeTo(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setVolumeTo(i, i2, str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public MediaMetadataCompat getMetadata() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getMetadata();
                    }
                    obtain2.readException();
                    MediaMetadataCompat createFromParcel = obtain2.readInt() != 0 ? MediaMetadataCompat.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public PlaybackStateCompat getPlaybackState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getPlaybackState();
                    }
                    obtain2.readException();
                    PlaybackStateCompat createFromParcel = obtain2.readInt() != 0 ? PlaybackStateCompat.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<MediaSessionCompat.QueueItem> getQueue() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getQueue();
                    }
                    obtain2.readException();
                    ArrayList<MediaSessionCompat.QueueItem> createTypedArrayList = obtain2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
                    obtain2.recycle();
                    obtain.recycle();
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CharSequence getQueueTitle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getQueueTitle();
                    }
                    obtain2.readException();
                    CharSequence charSequence = obtain2.readInt() != 0 ? (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return charSequence;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getExtras() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getExtras();
                    }
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRatingType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getRatingType();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isCaptioningEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    boolean z = false;
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isCaptioningEnabled();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getRepeatMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getRepeatMode();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isShuffleModeEnabledRemoved() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    boolean z = false;
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).isShuffleModeEnabledRemoved();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getShuffleMode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getShuffleMode();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(41, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).addQueueItem(mediaDescriptionCompat);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(42, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).addQueueItemAt(mediaDescriptionCompat, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (mediaDescriptionCompat != null) {
                        obtain.writeInt(1);
                        mediaDescriptionCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(43, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).removeQueueItem(mediaDescriptionCompat);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeQueueItemAt(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (this.mRemote.transact(44, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).removeQueueItemAt(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSessionInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (!this.mRemote.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).getSessionInfo();
                    }
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prepare() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(33, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepare();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(34, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromMediaId(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(35, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromSearch(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(36, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).prepareFromUri(uri, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).play();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(14, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromMediaId(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(15, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromSearch(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(16, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).playFromUri(uri, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void skipToQueueItem(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeLong(j);
                    if (this.mRemote.transact(17, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).skipToQueueItem(j);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(18, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).pause();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(19, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).stop();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(20, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).next();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(21, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).previous();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void fastForward() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(22, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).fastForward();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void rewind() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (this.mRemote.transact(23, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rewind();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void seekTo(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeLong(j);
                    if (this.mRemote.transact(24, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).seekTo(j);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        obtain.writeInt(1);
                        ratingCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(25, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rate(ratingCompat);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (ratingCompat != null) {
                        obtain.writeInt(1);
                        ratingCompat.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(51, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).rateWithExtras(ratingCompat, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlaybackSpeed(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeFloat(f);
                    if (this.mRemote.transact(49, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setPlaybackSpeed(f);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCaptioningEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(46, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setCaptioningEnabled(z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRepeatMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (this.mRemote.transact(39, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setRepeatMode(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setShuffleModeEnabledRemoved(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(40, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setShuffleModeEnabledRemoved(z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setShuffleMode(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeInt(i);
                    if (this.mRemote.transact(48, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).setShuffleMode(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(26, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).sendCustomAction(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaSession iMediaSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMediaSession == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMediaSession;
                return true;
            }
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
