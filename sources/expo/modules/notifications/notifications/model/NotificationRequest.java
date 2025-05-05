package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import java.io.Serializable;

public class NotificationRequest implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationRequest> CREATOR = new Parcelable.Creator<NotificationRequest>() {
        public NotificationRequest createFromParcel(Parcel parcel) {
            return new NotificationRequest(parcel);
        }

        public NotificationRequest[] newArray(int i) {
            return new NotificationRequest[i];
        }
    };
    private NotificationContent mContent;
    private String mIdentifier;
    private NotificationTrigger mTrigger;

    public int describeContents() {
        return 0;
    }

    public NotificationRequest(String str, NotificationContent notificationContent, NotificationTrigger notificationTrigger) {
        this.mIdentifier = str;
        this.mContent = notificationContent;
        this.mTrigger = notificationTrigger;
    }

    public NotificationContent getContent() {
        return this.mContent;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public NotificationTrigger getTrigger() {
        return this.mTrigger;
    }

    protected NotificationRequest(Parcel parcel) {
        this.mIdentifier = parcel.readString();
        this.mContent = (NotificationContent) parcel.readParcelable(getClass().getClassLoader());
        this.mTrigger = (NotificationTrigger) parcel.readParcelable(getClass().getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentifier);
        parcel.writeParcelable(this.mContent, 0);
        parcel.writeParcelable(this.mTrigger, 0);
    }
}
