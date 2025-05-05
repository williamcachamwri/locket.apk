package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

public class DailyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<DailyTrigger> CREATOR = new Parcelable.Creator<DailyTrigger>() {
        public DailyTrigger createFromParcel(Parcel parcel) {
            return new DailyTrigger(parcel);
        }

        public DailyTrigger[] newArray(int i) {
            return new DailyTrigger[i];
        }
    };
    private int mHour;
    private int mMinute;

    public int describeContents() {
        return 0;
    }

    public DailyTrigger(int i, int i2, String str) {
        super(str);
        this.mHour = i;
        this.mMinute = i2;
    }

    private DailyTrigger(Parcel parcel) {
        super(parcel);
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public Date nextTriggerDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, this.mHour);
        instance.set(12, this.mMinute);
        instance.set(13, 0);
        instance.set(14, 0);
        if (instance.before(Calendar.getInstance())) {
            instance.add(5, 1);
        }
        return instance.getTime();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}
