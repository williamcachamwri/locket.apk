package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.enums.NotificationPriority;

public class NotificationBehavior implements Parcelable {
    public static final Parcelable.Creator<NotificationBehavior> CREATOR = new Parcelable.Creator<NotificationBehavior>() {
        public NotificationBehavior createFromParcel(Parcel parcel) {
            return new NotificationBehavior(parcel);
        }

        public NotificationBehavior[] newArray(int i) {
            return new NotificationBehavior[i];
        }
    };
    private final String mPriorityOverride;
    private final boolean mShouldPlaySound;
    private final boolean mShouldSetBadge;
    private final boolean mShouldShowAlert;

    public int describeContents() {
        return 0;
    }

    public NotificationBehavior(boolean z, boolean z2, boolean z3, String str) {
        this.mShouldShowAlert = z;
        this.mShouldPlaySound = z2;
        this.mShouldSetBadge = z3;
        this.mPriorityOverride = str;
    }

    private NotificationBehavior(Parcel parcel) {
        boolean z = true;
        this.mShouldShowAlert = parcel.readByte() != 0;
        this.mShouldPlaySound = parcel.readByte() != 0;
        this.mShouldSetBadge = parcel.readByte() == 0 ? false : z;
        this.mPriorityOverride = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mShouldShowAlert ? (byte) 1 : 0);
        parcel.writeByte(this.mShouldPlaySound ? (byte) 1 : 0);
        parcel.writeByte(this.mShouldSetBadge ? (byte) 1 : 0);
        parcel.writeString(this.mPriorityOverride);
    }

    public NotificationPriority getPriorityOverride() {
        String str = this.mPriorityOverride;
        if (str == null) {
            return null;
        }
        return NotificationPriority.fromEnumValue(str);
    }

    public boolean shouldShowAlert() {
        return this.mShouldShowAlert;
    }

    public boolean shouldPlaySound() {
        return this.mShouldPlaySound;
    }

    public boolean shouldSetBadge() {
        return this.mShouldSetBadge;
    }
}
