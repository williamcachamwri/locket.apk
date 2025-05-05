package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Date;

public class DateTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<DateTrigger> CREATOR = new Parcelable.Creator<DateTrigger>() {
        public DateTrigger createFromParcel(Parcel parcel) {
            return new DateTrigger(parcel);
        }

        public DateTrigger[] newArray(int i) {
            return new DateTrigger[i];
        }
    };
    private Date mTriggerDate;

    public int describeContents() {
        return 0;
    }

    public DateTrigger(long j, String str) {
        super(str);
        this.mTriggerDate = new Date(j);
    }

    private DateTrigger(Parcel parcel) {
        super(parcel);
        this.mTriggerDate = new Date(parcel.readLong());
    }

    public Date getTriggerDate() {
        return this.mTriggerDate;
    }

    public Date nextTriggerDate() {
        if (this.mTriggerDate.before(new Date())) {
            return null;
        }
        return this.mTriggerDate;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mTriggerDate.getTime());
    }
}
