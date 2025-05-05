package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class NotificationCategory implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationCategory> CREATOR = new Parcelable.Creator<NotificationCategory>() {
        public NotificationCategory createFromParcel(Parcel parcel) {
            return new NotificationCategory(parcel);
        }

        public NotificationCategory[] newArray(int i) {
            return new NotificationCategory[i];
        }
    };
    private final List<NotificationAction> mActions;
    private final String mIdentifier;

    public int describeContents() {
        return 0;
    }

    public NotificationCategory(String str, List<NotificationAction> list) {
        this.mIdentifier = str;
        this.mActions = list;
    }

    private NotificationCategory(Parcel parcel) {
        this.mIdentifier = parcel.readString();
        this.mActions = parcel.readArrayList(NotificationAction.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentifier);
        parcel.writeList(this.mActions);
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public List<NotificationAction> getActions() {
        List<NotificationAction> list = this.mActions;
        return list == null ? Collections.emptyList() : list;
    }
}
