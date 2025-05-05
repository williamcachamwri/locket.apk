package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class NotificationAction implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationAction> CREATOR = new Parcelable.Creator<NotificationAction>() {
        public NotificationAction createFromParcel(Parcel parcel) {
            return new NotificationAction(parcel);
        }

        public NotificationAction[] newArray(int i) {
            return new NotificationAction[i];
        }
    };
    private final String mIdentifier;
    private final boolean mOpensAppToForeground;
    private final String mTitle;

    public int describeContents() {
        return 0;
    }

    public NotificationAction(String str, String str2, boolean z) {
        this.mIdentifier = str;
        this.mTitle = str2;
        this.mOpensAppToForeground = z;
    }

    protected NotificationAction(Parcel parcel) {
        this.mIdentifier = parcel.readString();
        this.mTitle = parcel.readString();
        this.mOpensAppToForeground = parcel.readByte() != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentifier);
        parcel.writeString(this.mTitle);
        parcel.writeByte(this.mOpensAppToForeground ? (byte) 1 : 0);
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean opensAppToForeground() {
        return this.mOpensAppToForeground;
    }
}
