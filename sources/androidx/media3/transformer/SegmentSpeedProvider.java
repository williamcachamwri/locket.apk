package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.Metadata;
import androidx.media3.common.audio.SpeedProvider;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import androidx.media3.extractor.metadata.mp4.SmtaMetadataEntry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class SegmentSpeedProvider implements SpeedProvider {
    private static final int INPUT_FRAME_RATE = 30;
    private final float baseSpeedMultiplier;
    private final ImmutableSortedMap<Long, Float> speedsByStartTimeUs;

    public SegmentSpeedProvider(Metadata metadata) {
        float captureFrameRate = getCaptureFrameRate(metadata);
        float f = captureFrameRate == -3.4028235E38f ? 1.0f : captureFrameRate / 30.0f;
        this.baseSpeedMultiplier = f;
        this.speedsByStartTimeUs = buildSpeedByStartTimeUsMap(metadata, f);
    }

    public float getSpeed(long j) {
        Assertions.checkArgument(j >= 0);
        Map.Entry<Long, Float> floorEntry = this.speedsByStartTimeUs.floorEntry(Long.valueOf(j));
        return floorEntry != null ? floorEntry.getValue().floatValue() : this.baseSpeedMultiplier;
    }

    public long getNextSpeedChangeTimeUs(long j) {
        Assertions.checkArgument(j >= 0);
        Long higherKey = this.speedsByStartTimeUs.higherKey(Long.valueOf(j));
        return higherKey != null ? higherKey.longValue() : C.TIME_UNSET;
    }

    private static ImmutableSortedMap<Long, Float> buildSpeedByStartTimeUsMap(Metadata metadata, float f) {
        ImmutableList<SlowMotionData.Segment> extractSlowMotionSegments = extractSlowMotionSegments(metadata);
        if (extractSlowMotionSegments.isEmpty()) {
            return ImmutableSortedMap.of();
        }
        TreeMap treeMap = new TreeMap();
        for (int i = 0; i < extractSlowMotionSegments.size(); i++) {
            SlowMotionData.Segment segment = (SlowMotionData.Segment) extractSlowMotionSegments.get(i);
            treeMap.put(Long.valueOf(Util.msToUs(segment.startTimeMs)), Float.valueOf(f / ((float) segment.speedDivisor)));
        }
        for (int i2 = 0; i2 < extractSlowMotionSegments.size(); i2++) {
            SlowMotionData.Segment segment2 = (SlowMotionData.Segment) extractSlowMotionSegments.get(i2);
            if (!treeMap.containsKey(Long.valueOf(Util.msToUs(segment2.endTimeMs)))) {
                treeMap.put(Long.valueOf(Util.msToUs(segment2.endTimeMs)), Float.valueOf(f));
            }
        }
        return ImmutableSortedMap.copyOf(treeMap);
    }

    private static float getCaptureFrameRate(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof SmtaMetadataEntry) {
                return ((SmtaMetadataEntry) entry).captureFrameRate;
            }
        }
        return -3.4028235E38f;
    }

    private static ImmutableList<SlowMotionData.Segment> extractSlowMotionSegments(Metadata metadata) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof SlowMotionData) {
                arrayList.addAll(((SlowMotionData) entry).segments);
            }
        }
        return ImmutableList.sortedCopyOf(SlowMotionData.Segment.BY_START_THEN_END_THEN_DIVISOR, arrayList);
    }
}
