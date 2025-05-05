package androidx.media3.extractor.metadata.icy;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

public final class IcyHeaders implements Metadata.Entry {
    public static final Parcelable.Creator<IcyHeaders> CREATOR = new Parcelable.Creator<IcyHeaders>() {
        public IcyHeaders createFromParcel(Parcel parcel) {
            return new IcyHeaders(parcel);
        }

        public IcyHeaders[] newArray(int i) {
            return new IcyHeaders[i];
        }
    };
    public static final String REQUEST_HEADER_ENABLE_METADATA_NAME = "Icy-MetaData";
    public static final String REQUEST_HEADER_ENABLE_METADATA_VALUE = "1";
    private static final String RESPONSE_HEADER_BITRATE = "icy-br";
    private static final String RESPONSE_HEADER_GENRE = "icy-genre";
    private static final String RESPONSE_HEADER_METADATA_INTERVAL = "icy-metaint";
    private static final String RESPONSE_HEADER_NAME = "icy-name";
    private static final String RESPONSE_HEADER_PUB = "icy-pub";
    private static final String RESPONSE_HEADER_URL = "icy-url";
    private static final String TAG = "IcyHeaders";
    public final int bitrate;
    public final String genre;
    public final boolean isPublic;
    public final int metadataInterval;
    public final String name;
    public final String url;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.metadata.icy.IcyHeaders parse(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r14) {
        /*
            java.lang.String r0 = "Invalid metadata interval: "
            java.lang.String r1 = "Invalid bitrate: "
            java.lang.String r2 = "icy-br"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            java.lang.String r3 = "IcyHeaders"
            r4 = 1
            r5 = 0
            r6 = -1
            if (r2 == 0) goto L_0x004c
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            int r7 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            int r7 = r7 * 1000
            if (r7 <= 0) goto L_0x0023
            r1 = r4
            goto L_0x004a
        L_0x0023:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0037 }
            r8.<init>(r1)     // Catch:{ NumberFormatException -> 0x0037 }
            java.lang.StringBuilder r1 = r8.append(r2)     // Catch:{ NumberFormatException -> 0x0037 }
            java.lang.String r1 = r1.toString()     // Catch:{ NumberFormatException -> 0x0037 }
            androidx.media3.common.util.Log.w(r3, r1)     // Catch:{ NumberFormatException -> 0x0037 }
            r1 = r5
            r7 = r6
            goto L_0x004a
        L_0x0036:
            r7 = r6
        L_0x0037:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r8 = "Invalid bitrate header: "
            r1.<init>(r8)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            androidx.media3.common.util.Log.w(r3, r1)
            r1 = r5
        L_0x004a:
            r8 = r7
            goto L_0x004e
        L_0x004c:
            r1 = r5
            r8 = r6
        L_0x004e:
            java.lang.String r2 = "icy-genre"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            r7 = 0
            if (r2 == 0) goto L_0x0062
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r9 = r1
            r1 = r4
            goto L_0x0063
        L_0x0062:
            r9 = r7
        L_0x0063:
            java.lang.String r2 = "icy-name"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x0076
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r10 = r1
            r1 = r4
            goto L_0x0077
        L_0x0076:
            r10 = r7
        L_0x0077:
            java.lang.String r2 = "icy-url"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x008a
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            r11 = r1
            r1 = r4
            goto L_0x008b
        L_0x008a:
            r11 = r7
        L_0x008b:
            java.lang.String r2 = "icy-pub"
            java.lang.Object r2 = r14.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L_0x00a4
            java.lang.Object r1 = r2.get(r5)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "1"
            boolean r1 = r1.equals(r2)
            r12 = r1
            r1 = r4
            goto L_0x00a5
        L_0x00a4:
            r12 = r5
        L_0x00a5:
            java.lang.String r2 = "icy-metaint"
            java.lang.Object r14 = r14.get(r2)
            java.util.List r14 = (java.util.List) r14
            if (r14 == 0) goto L_0x00e1
            java.lang.Object r14 = r14.get(r5)
            java.lang.String r14 = (java.lang.String) r14
            int r2 = java.lang.Integer.parseInt(r14)     // Catch:{ NumberFormatException -> 0x00d1 }
            if (r2 <= 0) goto L_0x00bd
            r6 = r2
            goto L_0x00ce
        L_0x00bd:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x00d0 }
            r4.<init>(r0)     // Catch:{ NumberFormatException -> 0x00d0 }
            java.lang.StringBuilder r4 = r4.append(r14)     // Catch:{ NumberFormatException -> 0x00d0 }
            java.lang.String r4 = r4.toString()     // Catch:{ NumberFormatException -> 0x00d0 }
            androidx.media3.common.util.Log.w(r3, r4)     // Catch:{ NumberFormatException -> 0x00d0 }
            r4 = r1
        L_0x00ce:
            r1 = r4
            goto L_0x00e1
        L_0x00d0:
            r6 = r2
        L_0x00d1:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r0)
            java.lang.StringBuilder r14 = r2.append(r14)
            java.lang.String r14 = r14.toString()
            androidx.media3.common.util.Log.w(r3, r14)
        L_0x00e1:
            r13 = r6
            if (r1 == 0) goto L_0x00ea
            androidx.media3.extractor.metadata.icy.IcyHeaders r14 = new androidx.media3.extractor.metadata.icy.IcyHeaders
            r7 = r14
            r7.<init>(r8, r9, r10, r11, r12, r13)
        L_0x00ea:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.metadata.icy.IcyHeaders.parse(java.util.Map):androidx.media3.extractor.metadata.icy.IcyHeaders");
    }

    public IcyHeaders(int i, String str, String str2, String str3, boolean z, int i2) {
        Assertions.checkArgument(i2 == -1 || i2 > 0);
        this.bitrate = i;
        this.genre = str;
        this.name = str2;
        this.url = str3;
        this.isPublic = z;
        this.metadataInterval = i2;
    }

    IcyHeaders(Parcel parcel) {
        this.bitrate = parcel.readInt();
        this.genre = parcel.readString();
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.isPublic = Util.readBoolean(parcel);
        this.metadataInterval = parcel.readInt();
    }

    public void populateMediaMetadata(MediaMetadata.Builder builder) {
        String str = this.name;
        if (str != null) {
            builder.setStation(str);
        }
        String str2 = this.genre;
        if (str2 != null) {
            builder.setGenre(str2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IcyHeaders icyHeaders = (IcyHeaders) obj;
        if (this.bitrate != icyHeaders.bitrate || !Util.areEqual(this.genre, icyHeaders.genre) || !Util.areEqual(this.name, icyHeaders.name) || !Util.areEqual(this.url, icyHeaders.url) || this.isPublic != icyHeaders.isPublic || this.metadataInterval != icyHeaders.metadataInterval) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (527 + this.bitrate) * 31;
        String str = this.genre;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        if (str3 != null) {
            i2 = str3.hashCode();
        }
        return ((((hashCode2 + i2) * 31) + (this.isPublic ? 1 : 0)) * 31) + this.metadataInterval;
    }

    public String toString() {
        return "IcyHeaders: name=\"" + this.name + "\", genre=\"" + this.genre + "\", bitrate=" + this.bitrate + ", metadataInterval=" + this.metadataInterval;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.genre);
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        Util.writeBoolean(parcel, this.isPublic);
        parcel.writeInt(this.metadataInterval);
    }
}
