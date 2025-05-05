package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

public class Notification implements Parcelable {
    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>() {
        public Notification createFromParcel(Parcel parcel) {
            return new Notification(parcel);
        }

        public Notification[] newArray(int i) {
            return new Notification[i];
        }
    };
    private Date mDate;
    private NotificationRequest mRequest;

    public int describeContents() {
        return 0;
    }

    public Notification(NotificationRequest notificationRequest) {
        this(notificationRequest, new Date());
    }

    public Notification(NotificationRequest notificationRequest, Date date) {
        this.mRequest = notificationRequest;
        this.mDate = date;
    }

    protected Notification(Parcel parcel) {
        this.mRequest = (NotificationRequest) parcel.readParcelable(getClass().getClassLoader());
        this.mDate = new Date(parcel.readLong());
    }

    public Date getDate() {
        return this.mDate;
    }

    public NotificationRequest getNotificationRequest() {
        return this.mRequest;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRequest, 0);
        parcel.writeLong(this.mDate.getTime());
    }
}
