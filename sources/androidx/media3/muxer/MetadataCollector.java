package androidx.media3.muxer;

import androidx.media3.common.Metadata;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4OrientationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.XmpData;
import java.util.HashSet;
import java.util.Set;

final class MetadataCollector {
    public Mp4LocationData locationData;
    public Set<MdtaMetadataEntry> metadataEntries = new HashSet();
    public Mp4OrientationData orientationData = new Mp4OrientationData(0);
    public Mp4TimestampData timestampData;
    public XmpData xmpData;

    public MetadataCollector() {
        long unixTimeToMp4TimeSeconds = Mp4TimestampData.unixTimeToMp4TimeSeconds(System.currentTimeMillis());
        this.timestampData = new Mp4TimestampData(unixTimeToMp4TimeSeconds, unixTimeToMp4TimeSeconds);
    }

    public void addMetadata(Metadata.Entry entry) {
        if (entry instanceof Mp4OrientationData) {
            this.orientationData = (Mp4OrientationData) entry;
        } else if (entry instanceof Mp4LocationData) {
            this.locationData = (Mp4LocationData) entry;
        } else if (entry instanceof Mp4TimestampData) {
            this.timestampData = (Mp4TimestampData) entry;
        } else if (entry instanceof MdtaMetadataEntry) {
            this.metadataEntries.add((MdtaMetadataEntry) entry);
        } else if (entry instanceof XmpData) {
            this.xmpData = (XmpData) entry;
        } else {
            throw new IllegalArgumentException("Unsupported metadata");
        }
    }

    public void removeMdtaMetadataEntry(MdtaMetadataEntry mdtaMetadataEntry) {
        this.metadataEntries.remove(mdtaMetadataEntry);
    }
}
