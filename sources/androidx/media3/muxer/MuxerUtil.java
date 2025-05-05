package androidx.media3.muxer;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4OrientationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.XmpData;
import com.google.common.primitives.Longs;
import java.util.List;

public final class MuxerUtil {
    public static final long UNSIGNED_INT_MAX_VALUE = 4294967295L;

    private MuxerUtil() {
    }

    public static boolean isMetadataSupported(Metadata.Entry entry) {
        return (entry instanceof Mp4OrientationData) || (entry instanceof Mp4LocationData) || ((entry instanceof Mp4TimestampData) && isMp4TimestampDataSupported((Mp4TimestampData) entry)) || (((entry instanceof MdtaMetadataEntry) && isMdtaMetadataEntrySupported((MdtaMetadataEntry) entry)) || (entry instanceof XmpData));
    }

    static boolean isEditableVideoTrack(Format format) {
        return (format.roleFlags & 32768) > 0 && (format.auxiliaryTrackType == 1 || format.auxiliaryTrackType == 2 || format.auxiliaryTrackType == 3 || format.auxiliaryTrackType == 4);
    }

    static MdtaMetadataEntry getEditableTracksOffsetMetadata(long j) {
        return new MdtaMetadataEntry(MdtaMetadataEntry.KEY_EDITABLE_TRACKS_OFFSET, Longs.toByteArray(j), 78);
    }

    static MdtaMetadataEntry getEditableTracksLengthMetadata(long j) {
        return new MdtaMetadataEntry(MdtaMetadataEntry.KEY_EDITABLE_TRACKS_LENGTH, Longs.toByteArray(j), 78);
    }

    static void populateEditableVideoTracksMetadata(MetadataCollector metadataCollector, Mp4TimestampData mp4TimestampData, boolean z, List<Track> list) {
        metadataCollector.addMetadata(mp4TimestampData);
        metadataCollector.addMetadata(getEditableTracksSamplesLocationMetadata(z));
        metadataCollector.addMetadata(getEditableTracksMapMetadata(list));
    }

    private static MdtaMetadataEntry getEditableTracksSamplesLocationMetadata(boolean z) {
        return new MdtaMetadataEntry(MdtaMetadataEntry.KEY_EDITABLE_TRACKS_SAMPLES_LOCATION, new byte[]{z ? (byte) 1 : 0}, 75);
    }

    private static MdtaMetadataEntry getEditableTracksMapMetadata(List<Track> list) {
        int i;
        int size = list.size();
        byte[] bArr = new byte[(size + 2)];
        bArr[0] = 1;
        bArr[1] = (byte) size;
        for (int i2 = 0; i2 < size; i2++) {
            Track track = list.get(i2);
            int i3 = track.format.auxiliaryTrackType;
            if (i3 != 1) {
                i = 2;
                if (i3 == 2) {
                    i = 1;
                } else if (i3 == 3) {
                    continue;
                } else if (i3 == 4) {
                    i = 3;
                } else {
                    throw new IllegalArgumentException("Unsupported editable track type " + track.format.auxiliaryTrackType);
                }
            } else {
                i = 0;
            }
            bArr[i2 + 2] = (byte) i;
        }
        return new MdtaMetadataEntry(MdtaMetadataEntry.KEY_EDITABLE_TRACKS_MAP, bArr, 0);
    }

    private static boolean isMdtaMetadataEntrySupported(MdtaMetadataEntry mdtaMetadataEntry) {
        return mdtaMetadataEntry.typeIndicator == 1 || mdtaMetadataEntry.typeIndicator == 23;
    }

    private static boolean isMp4TimestampDataSupported(Mp4TimestampData mp4TimestampData) {
        return mp4TimestampData.creationTimestampSeconds <= UNSIGNED_INT_MAX_VALUE && mp4TimestampData.modificationTimestampSeconds <= UNSIGNED_INT_MAX_VALUE;
    }
}
