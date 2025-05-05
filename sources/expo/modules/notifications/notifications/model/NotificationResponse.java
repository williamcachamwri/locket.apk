package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NotificationResponse implements Parcelable {
    public static final Parcelable.Creator<NotificationResponse> CREATOR = new Parcelable.Creator<NotificationResponse>() {
        public NotificationResponse createFromParcel(Parcel parcel) {
            return new NotificationResponse(parcel);
        }

        public NotificationResponse[] newArray(int i) {
            return new NotificationResponse[i];
        }
    };
    public static final String DEFAULT_ACTION_IDENTIFIER = "expo.modules.notifications.actions.DEFAULT";
    private NotificationAction mAction;
    private Notification mNotification;

    public int describeContents() {
        return 0;
    }

    public NotificationResponse(NotificationAction notificationAction, Notification notification) {
        this.mAction = notificationAction;
        this.mNotification = notification;
    }

    public NotificationAction getAction() {
        return this.mAction;
    }

    public String getActionIdentifier() {
        return this.mAction.getIdentifier();
    }

    public Notification getNotification() {
        return this.mNotification;
    }

    protected NotificationResponse(Parcel parcel) {
        this.mAction = (NotificationAction) parcel.readParcelable(getClass().getClassLoader());
        this.mNotification = (Notification) parcel.readParcelable(getClass().getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAction, 0);
        parcel.writeParcelable(this.mNotification, 0);
    }
}
