package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TextInputNotificationResponse extends NotificationResponse {
    public static final Parcelable.Creator<TextInputNotificationResponse> CREATOR = new Parcelable.Creator<TextInputNotificationResponse>() {
        public TextInputNotificationResponse createFromParcel(Parcel parcel) {
            return new TextInputNotificationResponse(parcel);
        }

        public TextInputNotificationResponse[] newArray(int i) {
            return new TextInputNotificationResponse[i];
        }
    };
    private String mUserText;

    public TextInputNotificationResponse(NotificationAction notificationAction, Notification notification, String str) {
        super(notificationAction, notification);
        this.mUserText = str;
    }

    public String getUserText() {
        return this.mUserText;
    }

    protected TextInputNotificationResponse(Parcel parcel) {
        super(parcel);
        this.mUserText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mUserText);
    }
}
