package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public enum Attachment implements Parcelable {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");
    
    public static final Parcelable.Creator<Attachment> CREATOR = null;
    private final String zzb;

    /* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
    public static class UnsupportedAttachmentException extends Exception {
        public UnsupportedAttachmentException(String str) {
            super(String.format("Attachment %s not supported", new Object[]{str}));
        }
    }

    static {
        CREATOR = new zza();
    }

    private Attachment(String str) {
        this.zzb = str;
    }

    public static Attachment fromString(String str) throws UnsupportedAttachmentException {
        for (Attachment attachment : values()) {
            if (str.equals(attachment.zzb)) {
                return attachment;
            }
        }
        throw new UnsupportedAttachmentException(str);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.zzb;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzb);
    }
}
