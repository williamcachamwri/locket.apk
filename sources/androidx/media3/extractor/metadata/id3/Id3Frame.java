package androidx.media3.extractor.metadata.id3;

import androidx.media3.common.Metadata;

public abstract class Id3Frame implements Metadata.Entry {
    public final String id;

    public int describeContents() {
        return 0;
    }

    public Id3Frame(String str) {
        this.id = str;
    }

    public String toString() {
        return this.id;
    }
}
