package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import java.io.Serializable;

public class ChannelAwareTrigger implements NotificationTrigger, Serializable {
    public static final Parcelable.Creator<ChannelAwareTrigger> CREATOR = new Parcelable.Creator<ChannelAwareTrigger>() {
        public ChannelAwareTrigger createFromParcel(Parcel parcel) {
            return new ChannelAwareTrigger(parcel);
        }

        public ChannelAwareTrigger[] newArray(int i) {
            return new ChannelAwareTrigger[i];
        }
    };
    private String mChannelId;

    public int describeContents() {
        return 0;
    }

    public ChannelAwareTrigger(String str) {
        this.mChannelId = str;
    }

    public ChannelAwareTrigger(Parcel parcel) {
        this.mChannelId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mChannelId);
    }

    public String getNotificationChannel() {
        return this.mChannelId;
    }
}
