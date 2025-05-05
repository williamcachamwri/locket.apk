package androidx.media3.extractor.metadata.flac;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import com.google.common.primitives.Ints;

@Deprecated
public class VorbisComment implements Metadata.Entry {
    public static final Parcelable.Creator<VorbisComment> CREATOR = new Parcelable.Creator<VorbisComment>() {
        public VorbisComment createFromParcel(Parcel parcel) {
            return new VorbisComment(parcel);
        }

        public VorbisComment[] newArray(int i) {
            return new VorbisComment[i];
        }
    };
    public final String key;
    public final String value;

    public int describeContents() {
        return 0;
    }

    public VorbisComment(String str, String str2) {
        this.key = Ascii.toUpperCase(str);
        this.value = str2;
    }

    protected VorbisComment(Parcel parcel) {
        this.key = (String) Util.castNonNull(parcel.readString());
        this.value = (String) Util.castNonNull(parcel.readString());
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.key;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1935137620:
                if (str.equals("TOTALTRACKS")) {
                    c = 0;
                    break;
                }
                break;
            case -215998278:
                if (str.equals("TOTALDISCS")) {
                    c = 1;
                    break;
                }
                break;
            case -113312716:
                if (str.equals("TRACKNUMBER")) {
                    c = 2;
                    break;
                }
                break;
            case 62359119:
                if (str.equals("ALBUM")) {
                    c = 3;
                    break;
                }
                break;
            case 67703139:
                if (str.equals("GENRE")) {
                    c = 4;
                    break;
                }
                break;
            case 79833656:
                if (str.equals("TITLE")) {
                    c = 5;
                    break;
                }
                break;
            case 428414940:
                if (str.equals("DESCRIPTION")) {
                    c = 6;
                    break;
                }
                break;
            case 993300766:
                if (str.equals("DISCNUMBER")) {
                    c = 7;
                    break;
                }
                break;
            case 1746739798:
                if (str.equals("ALBUMARTIST")) {
                    c = 8;
                    break;
                }
                break;
            case 1939198791:
                if (str.equals("ARTIST")) {
                    c = 9;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Integer tryParse = Ints.tryParse(this.value);
                if (tryParse != null) {
                    builder.setTotalTrackCount(tryParse);
                    return;
                }
                return;
            case 1:
                Integer tryParse2 = Ints.tryParse(this.value);
                if (tryParse2 != null) {
                    builder.setTotalDiscCount(tryParse2);
                    return;
                }
                return;
            case 2:
                Integer tryParse3 = Ints.tryParse(this.value);
                if (tryParse3 != null) {
                    builder.setTrackNumber(tryParse3);
                    return;
                }
                return;
            case 3:
                builder.setAlbumTitle(this.value);
                return;
            case 4:
                builder.setGenre(this.value);
                return;
            case 5:
                builder.setTitle(this.value);
                return;
            case 6:
                builder.setDescription(this.value);
                return;
            case 7:
                Integer tryParse4 = Ints.tryParse(this.value);
                if (tryParse4 != null) {
                    builder.setDiscNumber(tryParse4);
                    return;
                }
                return;
            case 8:
                builder.setAlbumArtist(this.value);
                return;
            case 9:
                builder.setArtist(this.value);
                return;
            default:
                return;
        }
    }

    public String toString() {
        return "VC: " + this.key + "=" + this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VorbisComment vorbisComment = (VorbisComment) obj;
        if (!this.key.equals(vorbisComment.key) || !this.value.equals(vorbisComment.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((527 + this.key.hashCode()) * 31) + this.value.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeString(this.value);
    }
}
