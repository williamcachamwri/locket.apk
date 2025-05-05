package expo.modules.notifications.notifications.model.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;

public class FirebaseNotificationTrigger implements NotificationTrigger {
    public static final Parcelable.Creator<FirebaseNotificationTrigger> CREATOR = new Parcelable.Creator<FirebaseNotificationTrigger>() {
        public FirebaseNotificationTrigger createFromParcel(Parcel parcel) {
            return new FirebaseNotificationTrigger(parcel);
        }

        public FirebaseNotificationTrigger[] newArray(int i) {
            return new FirebaseNotificationTrigger[i];
        }
    };
    private RemoteMessage mRemoteMessage;

    public int describeContents() {
        return 0;
    }

    public FirebaseNotificationTrigger(RemoteMessage remoteMessage) {
        this.mRemoteMessage = remoteMessage;
    }

    private FirebaseNotificationTrigger(Parcel parcel) {
        this.mRemoteMessage = (RemoteMessage) parcel.readParcelable(getClass().getClassLoader());
    }

    public RemoteMessage getRemoteMessage() {
        return this.mRemoteMessage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRemoteMessage, 0);
    }

    public String getNotificationChannel() {
        if (getRemoteMessage().getData().containsKey("channelId")) {
            return getRemoteMessage().getData().get("channelId");
        }
        return super.getNotificationChannel();
    }
}
