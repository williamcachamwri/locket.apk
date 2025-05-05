package androidx.media3.container;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;

public final class Mp4OrientationData implements Metadata.Entry {
    public static final Parcelable.Creator<Mp4OrientationData> CREATOR = new Parcelable.Creator<Mp4OrientationData>() {
        public Mp4OrientationData createFromParcel(Parcel parcel) {
            return new Mp4OrientationData(parcel);
        }

        public Mp4OrientationData[] newArray(int i) {
            return new Mp4OrientationData[i];
        }
    };
    public final int orientation;

    public int describeContents() {
        return 0;
    }

    public Mp4OrientationData(int i) {
        Assertions.checkArgument(i == 0 || i == 90 || i == 180 || i == 270, "Unsupported orientation");
        this.orientation = i;
    }

    private Mp4OrientationData(Parcel parcel) {
        this.orientation = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mp4OrientationData)) {
            return false;
        }
        if (this.orientation == ((Mp4OrientationData) obj).orientation) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return 527 + Integer.hashCode(this.orientation);
    }

    public String toString() {
        return "Orientation= " + this.orientation;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.orientation);
    }
}
