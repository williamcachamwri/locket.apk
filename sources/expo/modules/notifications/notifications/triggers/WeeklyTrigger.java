package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

public class WeeklyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<WeeklyTrigger> CREATOR = new Parcelable.Creator<WeeklyTrigger>() {
        public WeeklyTrigger createFromParcel(Parcel parcel) {
            return new WeeklyTrigger(parcel);
        }

        public WeeklyTrigger[] newArray(int i) {
            return new WeeklyTrigger[i];
        }
    };
    private int mHour;
    private int mMinute;
    private int mWeekday;

    public int describeContents() {
        return 0;
    }

    public WeeklyTrigger(int i, int i2, int i3, String str) {
        super(str);
        this.mWeekday = i;
        this.mHour = i2;
        this.mMinute = i3;
    }

    private WeeklyTrigger(Parcel parcel) {
        super(parcel);
        this.mWeekday = parcel.readInt();
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    public int getWeekday() {
        return this.mWeekday;
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public Date nextTriggerDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(7, this.mWeekday);
        instance.set(11, this.mHour);
        instance.set(12, this.mMinute);
        instance.set(13, 0);
        instance.set(14, 0);
        if (instance.before(Calendar.getInstance())) {
            instance.add(8, 1);
        }
        return instance.getTime();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mWeekday);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}
