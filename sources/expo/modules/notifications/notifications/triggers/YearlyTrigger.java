package expo.modules.notifications.notifications.triggers;

import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.interfaces.SchedulableNotificationTrigger;
import java.util.Calendar;
import java.util.Date;

public class YearlyTrigger extends ChannelAwareTrigger implements SchedulableNotificationTrigger {
    public static final Parcelable.Creator<YearlyTrigger> CREATOR = new Parcelable.Creator<YearlyTrigger>() {
        public YearlyTrigger createFromParcel(Parcel parcel) {
            return new YearlyTrigger(parcel);
        }

        public YearlyTrigger[] newArray(int i) {
            return new YearlyTrigger[i];
        }
    };
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;

    public int describeContents() {
        return 0;
    }

    public YearlyTrigger(int i, int i2, int i3, int i4, String str) {
        super(str);
        this.mDay = i;
        this.mMonth = i2;
        this.mHour = i3;
        this.mMinute = i4;
    }

    private YearlyTrigger(Parcel parcel) {
        super(parcel);
        this.mDay = parcel.readInt();
        this.mMonth = parcel.readInt();
        this.mHour = parcel.readInt();
        this.mMinute = parcel.readInt();
    }

    public int getDay() {
        return this.mDay;
    }

    public int getMonth() {
        return this.mMonth;
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public Date nextTriggerDate() {
        Calendar instance = Calendar.getInstance();
        instance.set(5, this.mDay);
        instance.set(2, this.mMonth);
        instance.set(11, this.mHour);
        instance.set(12, this.mMinute);
        instance.set(13, 0);
        instance.set(14, 0);
        if (instance.before(Calendar.getInstance())) {
            instance.add(1, 1);
        }
        return instance.getTime();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mDay);
        parcel.writeInt(this.mMonth);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
    }
}
