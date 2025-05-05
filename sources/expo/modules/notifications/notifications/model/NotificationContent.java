package expo.modules.notifications.notifications.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationContent implements Parcelable, Serializable {
    public static final Parcelable.Creator<NotificationContent> CREATOR = new Parcelable.Creator<NotificationContent>() {
        public NotificationContent createFromParcel(Parcel parcel) {
            return new NotificationContent(parcel);
        }

        public NotificationContent[] newArray(int i) {
            return new NotificationContent[i];
        }
    };
    private static final long serialVersionUID = 397666843266836802L;
    /* access modifiers changed from: private */
    public boolean mAutoDismiss;
    /* access modifiers changed from: private */
    public Number mBadgeCount;
    /* access modifiers changed from: private */
    public JSONObject mBody;
    /* access modifiers changed from: private */
    public String mCategoryId;
    /* access modifiers changed from: private */
    public Number mColor;
    /* access modifiers changed from: private */
    public NotificationPriority mPriority;
    /* access modifiers changed from: private */
    public boolean mShouldPlayDefaultSound;
    /* access modifiers changed from: private */
    public boolean mShouldUseDefaultVibrationPattern;
    /* access modifiers changed from: private */
    public Uri mSound;
    /* access modifiers changed from: private */
    public boolean mSticky;
    /* access modifiers changed from: private */
    public String mSubtitle;
    /* access modifiers changed from: private */
    public String mText;
    /* access modifiers changed from: private */
    public String mTitle;
    /* access modifiers changed from: private */
    public long[] mVibrationPattern;

    private void readObjectNoData() throws ObjectStreamException {
    }

    public int describeContents() {
        return 0;
    }

    protected NotificationContent() {
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getText() {
        return this.mText;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public Number getBadgeCount() {
        return this.mBadgeCount;
    }

    public boolean shouldPlayDefaultSound() {
        return this.mShouldPlayDefaultSound;
    }

    public Uri getSound() {
        return this.mSound;
    }

    public boolean shouldUseDefaultVibrationPattern() {
        return this.mShouldUseDefaultVibrationPattern;
    }

    public long[] getVibrationPattern() {
        return this.mVibrationPattern;
    }

    public JSONObject getBody() {
        return this.mBody;
    }

    public NotificationPriority getPriority() {
        return this.mPriority;
    }

    public Number getColor() {
        return this.mColor;
    }

    public boolean isAutoDismiss() {
        return this.mAutoDismiss;
    }

    public String getCategoryId() {
        return this.mCategoryId;
    }

    public boolean isSticky() {
        return this.mSticky;
    }

    protected NotificationContent(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mText = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mBadgeCount = (Number) parcel.readSerializable();
        boolean z = false;
        this.mShouldPlayDefaultSound = parcel.readByte() != 0;
        this.mSound = (Uri) parcel.readParcelable(getClass().getClassLoader());
        this.mShouldUseDefaultVibrationPattern = parcel.readByte() != 0;
        this.mVibrationPattern = parcel.createLongArray();
        try {
            this.mBody = new JSONObject(parcel.readString());
        } catch (NullPointerException | JSONException unused) {
        }
        Number number = (Number) parcel.readSerializable();
        if (number != null) {
            this.mPriority = NotificationPriority.fromNativeValue(number.intValue());
        }
        this.mColor = (Number) parcel.readSerializable();
        this.mAutoDismiss = parcel.readByte() == 1;
        this.mCategoryId = parcel.readString();
        this.mSticky = parcel.readByte() == 1 ? true : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mText);
        parcel.writeString(this.mSubtitle);
        parcel.writeSerializable(this.mBadgeCount);
        parcel.writeByte(this.mShouldPlayDefaultSound ? (byte) 1 : 0);
        parcel.writeParcelable(this.mSound, 0);
        parcel.writeByte(this.mShouldUseDefaultVibrationPattern ? (byte) 1 : 0);
        parcel.writeLongArray(this.mVibrationPattern);
        JSONObject jSONObject = this.mBody;
        Integer num = null;
        parcel.writeString(jSONObject != null ? jSONObject.toString() : null);
        NotificationPriority notificationPriority = this.mPriority;
        if (notificationPriority != null) {
            num = Integer.valueOf(notificationPriority.getNativeValue());
        }
        parcel.writeSerializable(num);
        parcel.writeSerializable(this.mColor);
        parcel.writeByte(this.mAutoDismiss ? (byte) 1 : 0);
        parcel.writeString(this.mCategoryId);
        parcel.writeByte(this.mSticky ? (byte) 1 : 0);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.mTitle);
        objectOutputStream.writeObject(this.mText);
        objectOutputStream.writeObject(this.mSubtitle);
        objectOutputStream.writeObject(this.mBadgeCount);
        objectOutputStream.writeByte(this.mShouldPlayDefaultSound ? 1 : 0);
        Uri uri = this.mSound;
        String str = null;
        objectOutputStream.writeObject(uri == null ? null : uri.toString());
        objectOutputStream.writeByte(this.mShouldUseDefaultVibrationPattern ? 1 : 0);
        long[] jArr = this.mVibrationPattern;
        if (jArr == null) {
            objectOutputStream.writeInt(-1);
        } else {
            objectOutputStream.writeInt(jArr.length);
            for (long writeLong : this.mVibrationPattern) {
                objectOutputStream.writeLong(writeLong);
            }
        }
        JSONObject jSONObject = this.mBody;
        objectOutputStream.writeObject(jSONObject != null ? jSONObject.toString() : null);
        NotificationPriority notificationPriority = this.mPriority;
        objectOutputStream.writeObject(notificationPriority != null ? Integer.valueOf(notificationPriority.getNativeValue()) : null);
        objectOutputStream.writeObject(this.mColor);
        objectOutputStream.writeByte(this.mAutoDismiss ? 1 : 0);
        String str2 = this.mCategoryId;
        if (str2 != null) {
            str = str2.toString();
        }
        objectOutputStream.writeObject(str);
        objectOutputStream.writeByte(this.mSticky ? 1 : 0);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.mTitle = (String) objectInputStream.readObject();
        this.mText = (String) objectInputStream.readObject();
        this.mSubtitle = (String) objectInputStream.readObject();
        this.mBadgeCount = (Number) objectInputStream.readObject();
        boolean z = false;
        this.mShouldPlayDefaultSound = objectInputStream.readByte() == 1;
        String str = (String) objectInputStream.readObject();
        if (str == null) {
            this.mSound = null;
        } else {
            this.mSound = Uri.parse(str);
        }
        this.mShouldUseDefaultVibrationPattern = objectInputStream.readByte() == 1;
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            this.mVibrationPattern = null;
        } else {
            this.mVibrationPattern = new long[readInt];
            for (int i = 0; i < readInt; i++) {
                this.mVibrationPattern[i] = objectInputStream.readLong();
            }
        }
        String str2 = (String) objectInputStream.readObject();
        if (str2 == null) {
            this.mBody = null;
        } else {
            try {
                this.mBody = new JSONObject(str2);
            } catch (NullPointerException | JSONException unused) {
            }
        }
        Number number = (Number) objectInputStream.readObject();
        if (number != null) {
            this.mPriority = NotificationPriority.fromNativeValue(number.intValue());
        }
        this.mColor = (Number) objectInputStream.readObject();
        this.mAutoDismiss = objectInputStream.readByte() == 1;
        String str3 = (String) objectInputStream.readObject();
        if (str3 == null) {
            this.mCategoryId = null;
        } else {
            this.mCategoryId = new String(str3);
        }
        if (objectInputStream.readByte() == 1) {
            z = true;
        }
        this.mSticky = z;
    }

    public static class Builder {
        private boolean mAutoDismiss;
        private Number mBadgeCount;
        private JSONObject mBody;
        private String mCategoryId;
        private Number mColor;
        private NotificationPriority mPriority;
        private boolean mShouldPlayDefaultSound;
        private boolean mShouldUseDefaultVibrationPattern;
        private Uri mSound;
        private boolean mSticky;
        private String mSubtitle;
        private String mText;
        private String mTitle;
        private long[] mVibrationPattern;

        public Builder() {
            useDefaultSound();
            useDefaultVibrationPattern();
        }

        public Builder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public Builder setSubtitle(String str) {
            this.mSubtitle = str;
            return this;
        }

        public Builder setText(String str) {
            this.mText = str;
            return this;
        }

        public Builder setBody(JSONObject jSONObject) {
            this.mBody = jSONObject;
            return this;
        }

        public Builder setPriority(NotificationPriority notificationPriority) {
            this.mPriority = notificationPriority;
            return this;
        }

        public Builder setBadgeCount(Number number) {
            this.mBadgeCount = number;
            return this;
        }

        public Builder useDefaultVibrationPattern() {
            this.mShouldUseDefaultVibrationPattern = true;
            this.mVibrationPattern = null;
            return this;
        }

        public Builder setVibrationPattern(long[] jArr) {
            this.mShouldUseDefaultVibrationPattern = false;
            this.mVibrationPattern = jArr;
            return this;
        }

        public Builder useDefaultSound() {
            this.mShouldPlayDefaultSound = true;
            this.mSound = null;
            return this;
        }

        public Builder setSound(Uri uri) {
            this.mShouldPlayDefaultSound = false;
            this.mSound = uri;
            return this;
        }

        public Builder setColor(Number number) {
            this.mColor = number;
            return this;
        }

        public Builder setAutoDismiss(boolean z) {
            this.mAutoDismiss = z;
            return this;
        }

        public Builder setCategoryId(String str) {
            this.mCategoryId = str;
            return this;
        }

        public Builder setSticky(boolean z) {
            this.mSticky = z;
            return this;
        }

        public NotificationContent build() {
            NotificationContent notificationContent = new NotificationContent();
            notificationContent.mTitle = this.mTitle;
            notificationContent.mSubtitle = this.mSubtitle;
            notificationContent.mText = this.mText;
            notificationContent.mBadgeCount = this.mBadgeCount;
            notificationContent.mShouldUseDefaultVibrationPattern = this.mShouldUseDefaultVibrationPattern;
            notificationContent.mVibrationPattern = this.mVibrationPattern;
            notificationContent.mShouldPlayDefaultSound = this.mShouldPlayDefaultSound;
            notificationContent.mSound = this.mSound;
            notificationContent.mBody = this.mBody;
            notificationContent.mPriority = this.mPriority;
            notificationContent.mColor = this.mColor;
            notificationContent.mAutoDismiss = this.mAutoDismiss;
            notificationContent.mCategoryId = this.mCategoryId;
            notificationContent.mSticky = this.mSticky;
            return notificationContent;
        }
    }
}
