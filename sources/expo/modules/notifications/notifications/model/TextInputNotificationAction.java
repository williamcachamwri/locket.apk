package expo.modules.notifications.notifications.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TextInputNotificationAction extends NotificationAction {
    public static final Parcelable.Creator<TextInputNotificationAction> CREATOR = new Parcelable.Creator<TextInputNotificationAction>() {
        public TextInputNotificationAction createFromParcel(Parcel parcel) {
            return new TextInputNotificationAction(parcel);
        }

        public TextInputNotificationAction[] newArray(int i) {
            return new TextInputNotificationAction[i];
        }
    };
    private final String mPlaceholder;

    public TextInputNotificationAction(String str, String str2, boolean z, String str3) {
        super(str, str2, z);
        this.mPlaceholder = str3;
    }

    private TextInputNotificationAction(Parcel parcel) {
        super(parcel);
        this.mPlaceholder = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mPlaceholder);
    }

    public String getPlaceholder() {
        return this.mPlaceholder;
    }
}
