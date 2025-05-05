package androidx.media3.exoplayer.upstream;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public final class CmcdData {
    private static final Joiner COMMA_JOINER = Joiner.on(",");
    private final CmcdObject cmcdObject;
    private final CmcdRequest cmcdRequest;
    private final CmcdSession cmcdSession;
    private final CmcdStatus cmcdStatus;
    private final int dataTransmissionMode;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectType {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamingFormat {
    }

    public static final class Factory {
        private static final Pattern CUSTOM_KEY_NAME_PATTERN = Pattern.compile(".*-.*");
        public static final String OBJECT_TYPE_AUDIO_ONLY = "a";
        public static final String OBJECT_TYPE_INIT_SEGMENT = "i";
        public static final String OBJECT_TYPE_MUXED_AUDIO_AND_VIDEO = "av";
        public static final String OBJECT_TYPE_VIDEO_ONLY = "v";
        public static final String STREAMING_FORMAT_DASH = "d";
        public static final String STREAMING_FORMAT_HLS = "h";
        public static final String STREAMING_FORMAT_SS = "s";
        public static final String STREAM_TYPE_LIVE = "l";
        public static final String STREAM_TYPE_VOD = "v";
        private final long bufferedDurationUs;
        private long chunkDurationUs;
        private final CmcdConfiguration cmcdConfiguration;
        private final boolean didRebuffer;
        private final boolean isBufferEmpty;
        private final boolean isLive;
        private String nextObjectRequest;
        private String nextRangeRequest;
        private String objectType;
        private final float playbackRate;
        private final String streamingFormat;
        private final ExoTrackSelection trackSelection;

        public Factory(CmcdConfiguration cmcdConfiguration2, ExoTrackSelection exoTrackSelection, long j, float f, String str, boolean z, boolean z2, boolean z3) {
            boolean z4 = true;
            Assertions.checkArgument(j >= 0);
            if (f != -3.4028235E38f && f <= 0.0f) {
                z4 = false;
            }
            Assertions.checkArgument(z4);
            this.cmcdConfiguration = cmcdConfiguration2;
            this.trackSelection = exoTrackSelection;
            this.bufferedDurationUs = j;
            this.playbackRate = f;
            this.streamingFormat = str;
            this.isLive = z;
            this.didRebuffer = z2;
            this.isBufferEmpty = z3;
            this.chunkDurationUs = C.TIME_UNSET;
        }

        public static String getObjectType(ExoTrackSelection exoTrackSelection) {
            Assertions.checkArgument(exoTrackSelection != null);
            int trackType = MimeTypes.getTrackType(exoTrackSelection.getSelectedFormat().sampleMimeType);
            if (trackType == -1) {
                trackType = MimeTypes.getTrackType(exoTrackSelection.getSelectedFormat().containerMimeType);
            }
            if (trackType == 1) {
                return OBJECT_TYPE_AUDIO_ONLY;
            }
            if (trackType == 2) {
                return "v";
            }
            return null;
        }

        public Factory setChunkDurationUs(long j) {
            Assertions.checkArgument(j >= 0);
            this.chunkDurationUs = j;
            return this;
        }

        public Factory setObjectType(String str) {
            this.objectType = str;
            return this;
        }

        public Factory setNextObjectRequest(String str) {
            this.nextObjectRequest = str;
            return this;
        }

        public Factory setNextRangeRequest(String str) {
            this.nextRangeRequest = str;
            return this;
        }

        public CmcdData createCmcdData() {
            ImmutableListMultimap<String, String> customData = this.cmcdConfiguration.requestConfig.getCustomData();
            UnmodifiableIterator<String> it = customData.keySet().iterator();
            while (it.hasNext()) {
                validateCustomDataListFormat(customData.get((Object) it.next()));
            }
            int ceilDivide = Util.ceilDivide(this.trackSelection.getSelectedFormat().bitrate, 1000);
            CmcdObject.Builder builder = new CmcdObject.Builder();
            boolean z = false;
            if (!getIsInitSegment()) {
                if (this.cmcdConfiguration.isBitrateLoggingAllowed()) {
                    builder.setBitrateKbps(ceilDivide);
                }
                if (this.cmcdConfiguration.isTopBitrateLoggingAllowed()) {
                    TrackGroup trackGroup = this.trackSelection.getTrackGroup();
                    int i = this.trackSelection.getSelectedFormat().bitrate;
                    for (int i2 = 0; i2 < trackGroup.length; i2++) {
                        i = Math.max(i, trackGroup.getFormat(i2).bitrate);
                    }
                    builder.setTopBitrateKbps(Util.ceilDivide(i, 1000));
                }
                if (this.cmcdConfiguration.isObjectDurationLoggingAllowed()) {
                    builder.setObjectDurationMs(Util.usToMs(this.chunkDurationUs));
                }
            }
            if (this.cmcdConfiguration.isObjectTypeLoggingAllowed()) {
                builder.setObjectType(this.objectType);
            }
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_OBJECT)) {
                builder.setCustomDataList(customData.get((Object) CmcdConfiguration.KEY_CMCD_OBJECT));
            }
            CmcdRequest.Builder builder2 = new CmcdRequest.Builder();
            if (!getIsInitSegment() && this.cmcdConfiguration.isBufferLengthLoggingAllowed()) {
                builder2.setBufferLengthMs(Util.usToMs(this.bufferedDurationUs));
            }
            if (this.cmcdConfiguration.isMeasuredThroughputLoggingAllowed() && this.trackSelection.getLatestBitrateEstimate() != -2147483647L) {
                builder2.setMeasuredThroughputInKbps(Util.ceilDivide(this.trackSelection.getLatestBitrateEstimate(), 1000));
            }
            if (this.cmcdConfiguration.isDeadlineLoggingAllowed()) {
                builder2.setDeadlineMs(Util.usToMs((long) (((float) this.bufferedDurationUs) / this.playbackRate)));
            }
            if (this.cmcdConfiguration.isStartupLoggingAllowed()) {
                if (this.didRebuffer || this.isBufferEmpty) {
                    z = true;
                }
                builder2.setStartup(z);
            }
            if (this.cmcdConfiguration.isNextObjectRequestLoggingAllowed()) {
                builder2.setNextObjectRequest(this.nextObjectRequest);
            }
            if (this.cmcdConfiguration.isNextRangeRequestLoggingAllowed()) {
                builder2.setNextRangeRequest(this.nextRangeRequest);
            }
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_REQUEST)) {
                builder2.setCustomDataList(customData.get((Object) CmcdConfiguration.KEY_CMCD_REQUEST));
            }
            CmcdSession.Builder builder3 = new CmcdSession.Builder();
            if (this.cmcdConfiguration.isContentIdLoggingAllowed()) {
                builder3.setContentId(this.cmcdConfiguration.contentId);
            }
            if (this.cmcdConfiguration.isSessionIdLoggingAllowed()) {
                builder3.setSessionId(this.cmcdConfiguration.sessionId);
            }
            if (this.cmcdConfiguration.isStreamingFormatLoggingAllowed()) {
                builder3.setStreamingFormat(this.streamingFormat);
            }
            if (this.cmcdConfiguration.isStreamTypeLoggingAllowed()) {
                builder3.setStreamType(this.isLive ? STREAM_TYPE_LIVE : "v");
            }
            if (this.cmcdConfiguration.isPlaybackRateLoggingAllowed()) {
                builder3.setPlaybackRate(this.playbackRate);
            }
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_SESSION)) {
                builder3.setCustomDataList(customData.get((Object) CmcdConfiguration.KEY_CMCD_SESSION));
            }
            CmcdStatus.Builder builder4 = new CmcdStatus.Builder();
            if (this.cmcdConfiguration.isMaximumRequestThroughputLoggingAllowed()) {
                builder4.setMaximumRequestedThroughputKbps(this.cmcdConfiguration.requestConfig.getRequestedMaximumThroughputKbps(ceilDivide));
            }
            if (this.cmcdConfiguration.isBufferStarvationLoggingAllowed()) {
                builder4.setBufferStarvation(this.didRebuffer);
            }
            if (customData.containsKey(CmcdConfiguration.KEY_CMCD_STATUS)) {
                builder4.setCustomDataList(customData.get((Object) CmcdConfiguration.KEY_CMCD_STATUS));
            }
            return new CmcdData(builder.build(), builder2.build(), builder3.build(), builder4.build(), this.cmcdConfiguration.dataTransmissionMode);
        }

        private boolean getIsInitSegment() {
            String str = this.objectType;
            return str != null && str.equals("i");
        }

        private void validateCustomDataListFormat(List<String> list) {
            for (String split : list) {
                Assertions.checkState(CUSTOM_KEY_NAME_PATTERN.matcher(Util.split(split, "=")[0]).matches());
            }
        }
    }

    private CmcdData(CmcdObject cmcdObject2, CmcdRequest cmcdRequest2, CmcdSession cmcdSession2, CmcdStatus cmcdStatus2, int i) {
        this.cmcdObject = cmcdObject2;
        this.cmcdRequest = cmcdRequest2;
        this.cmcdSession = cmcdSession2;
        this.cmcdStatus = cmcdStatus2;
        this.dataTransmissionMode = i;
    }

    public DataSpec addToDataSpec(DataSpec dataSpec) {
        ArrayListMultimap create = ArrayListMultimap.create();
        this.cmcdObject.populateCmcdDataMap(create);
        this.cmcdRequest.populateCmcdDataMap(create);
        this.cmcdSession.populateCmcdDataMap(create);
        this.cmcdStatus.populateCmcdDataMap(create);
        if (this.dataTransmissionMode == 0) {
            ImmutableMap.Builder builder = ImmutableMap.builder();
            for (String str : create.keySet()) {
                List list = create.get(str);
                Collections.sort(list);
                builder.put(str, COMMA_JOINER.join((Iterable<? extends Object>) list));
            }
            return dataSpec.withAdditionalHeaders(builder.buildOrThrow());
        }
        ArrayList arrayList = new ArrayList();
        for (Collection addAll : create.asMap().values()) {
            arrayList.addAll(addAll);
        }
        Collections.sort(arrayList);
        return dataSpec.buildUpon().setUri(dataSpec.uri.buildUpon().appendQueryParameter(CmcdConfiguration.CMCD_QUERY_PARAMETER_KEY, COMMA_JOINER.join((Iterable<? extends Object>) arrayList)).build()).build();
    }

    private static final class CmcdObject {
        public final int bitrateKbps;
        public final ImmutableList<String> customDataList;
        public final long objectDurationMs;
        public final String objectType;
        public final int topBitrateKbps;

        public static final class Builder {
            /* access modifiers changed from: private */
            public int bitrateKbps = C.RATE_UNSET_INT;
            /* access modifiers changed from: private */
            public ImmutableList<String> customDataList = ImmutableList.of();
            /* access modifiers changed from: private */
            public long objectDurationMs = C.TIME_UNSET;
            /* access modifiers changed from: private */
            public String objectType;
            /* access modifiers changed from: private */
            public int topBitrateKbps = C.RATE_UNSET_INT;

            public Builder setBitrateKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                this.bitrateKbps = i;
                return this;
            }

            public Builder setTopBitrateKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                this.topBitrateKbps = i;
                return this;
            }

            public Builder setObjectDurationMs(long j) {
                Assertions.checkArgument(j >= 0 || j == C.TIME_UNSET);
                this.objectDurationMs = j;
                return this;
            }

            public Builder setObjectType(String str) {
                this.objectType = str;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf(list);
                return this;
            }

            public CmcdObject build() {
                return new CmcdObject(this);
            }
        }

        private CmcdObject(Builder builder) {
            this.bitrateKbps = builder.bitrateKbps;
            this.topBitrateKbps = builder.topBitrateKbps;
            this.objectDurationMs = builder.objectDurationMs;
            this.objectType = builder.objectType;
            this.customDataList = builder.customDataList;
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.bitrateKbps != -2147483647) {
                arrayList.add("br=" + this.bitrateKbps);
            }
            if (this.topBitrateKbps != -2147483647) {
                arrayList.add("tb=" + this.topBitrateKbps);
            }
            if (this.objectDurationMs != C.TIME_UNSET) {
                arrayList.add("d=" + this.objectDurationMs);
            }
            if (!TextUtils.isEmpty(this.objectType)) {
                arrayList.add("ot=" + this.objectType);
            }
            arrayList.addAll(this.customDataList);
            if (!arrayList.isEmpty()) {
                arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_OBJECT, arrayList);
            }
        }
    }

    private static final class CmcdRequest {
        public final long bufferLengthMs;
        public final ImmutableList<String> customDataList;
        public final long deadlineMs;
        public final long measuredThroughputInKbps;
        public final String nextObjectRequest;
        public final String nextRangeRequest;
        public final boolean startup;

        public static final class Builder {
            /* access modifiers changed from: private */
            public long bufferLengthMs = C.TIME_UNSET;
            /* access modifiers changed from: private */
            public ImmutableList<String> customDataList = ImmutableList.of();
            /* access modifiers changed from: private */
            public long deadlineMs = C.TIME_UNSET;
            /* access modifiers changed from: private */
            public long measuredThroughputInKbps = -2147483647L;
            /* access modifiers changed from: private */
            public String nextObjectRequest;
            /* access modifiers changed from: private */
            public String nextRangeRequest;
            /* access modifiers changed from: private */
            public boolean startup;

            public Builder setBufferLengthMs(long j) {
                Assertions.checkArgument(j >= 0 || j == C.TIME_UNSET);
                this.bufferLengthMs = ((j + 50) / 100) * 100;
                return this;
            }

            public Builder setMeasuredThroughputInKbps(long j) {
                Assertions.checkArgument(j >= 0 || j == -2147483647L);
                this.measuredThroughputInKbps = ((j + 50) / 100) * 100;
                return this;
            }

            public Builder setDeadlineMs(long j) {
                Assertions.checkArgument(j >= 0 || j == C.TIME_UNSET);
                this.deadlineMs = ((j + 50) / 100) * 100;
                return this;
            }

            public Builder setStartup(boolean z) {
                this.startup = z;
                return this;
            }

            public Builder setNextObjectRequest(String str) {
                this.nextObjectRequest = str == null ? null : Uri.encode(str);
                return this;
            }

            public Builder setNextRangeRequest(String str) {
                this.nextRangeRequest = str;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf(list);
                return this;
            }

            public CmcdRequest build() {
                return new CmcdRequest(this);
            }
        }

        private CmcdRequest(Builder builder) {
            this.bufferLengthMs = builder.bufferLengthMs;
            this.measuredThroughputInKbps = builder.measuredThroughputInKbps;
            this.deadlineMs = builder.deadlineMs;
            this.startup = builder.startup;
            this.nextObjectRequest = builder.nextObjectRequest;
            this.nextRangeRequest = builder.nextRangeRequest;
            this.customDataList = builder.customDataList;
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.bufferLengthMs != C.TIME_UNSET) {
                arrayList.add("bl=" + this.bufferLengthMs);
            }
            if (this.measuredThroughputInKbps != -2147483647L) {
                arrayList.add("mtp=" + this.measuredThroughputInKbps);
            }
            if (this.deadlineMs != C.TIME_UNSET) {
                arrayList.add("dl=" + this.deadlineMs);
            }
            if (this.startup) {
                arrayList.add(CmcdConfiguration.KEY_STARTUP);
            }
            if (!TextUtils.isEmpty(this.nextObjectRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_OBJECT_REQUEST, this.nextObjectRequest));
            }
            if (!TextUtils.isEmpty(this.nextRangeRequest)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_NEXT_RANGE_REQUEST, this.nextRangeRequest));
            }
            arrayList.addAll(this.customDataList);
            if (!arrayList.isEmpty()) {
                arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_REQUEST, arrayList);
            }
        }
    }

    private static final class CmcdSession {
        public static final int VERSION = 1;
        public final String contentId;
        public final ImmutableList<String> customDataList;
        public final float playbackRate;
        public final String sessionId;
        public final String streamType;
        public final String streamingFormat;

        public static final class Builder {
            /* access modifiers changed from: private */
            public String contentId;
            /* access modifiers changed from: private */
            public ImmutableList<String> customDataList = ImmutableList.of();
            /* access modifiers changed from: private */
            public float playbackRate;
            /* access modifiers changed from: private */
            public String sessionId;
            /* access modifiers changed from: private */
            public String streamType;
            /* access modifiers changed from: private */
            public String streamingFormat;

            public Builder setContentId(String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.contentId = str;
                return this;
            }

            public Builder setSessionId(String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.sessionId = str;
                return this;
            }

            public Builder setStreamingFormat(String str) {
                this.streamingFormat = str;
                return this;
            }

            public Builder setStreamType(String str) {
                this.streamType = str;
                return this;
            }

            public Builder setPlaybackRate(float f) {
                Assertions.checkArgument(f > 0.0f || f == -3.4028235E38f);
                this.playbackRate = f;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf(list);
                return this;
            }

            public CmcdSession build() {
                return new CmcdSession(this);
            }
        }

        private CmcdSession(Builder builder) {
            this.contentId = builder.contentId;
            this.sessionId = builder.sessionId;
            this.streamingFormat = builder.streamingFormat;
            this.streamType = builder.streamType;
            this.playbackRate = builder.playbackRate;
            this.customDataList = builder.customDataList;
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(this.contentId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", CmcdConfiguration.KEY_CONTENT_ID, this.contentId));
            }
            if (!TextUtils.isEmpty(this.sessionId)) {
                arrayList.add(Util.formatInvariant("%s=\"%s\"", "sid", this.sessionId));
            }
            if (!TextUtils.isEmpty(this.streamingFormat)) {
                arrayList.add("sf=" + this.streamingFormat);
            }
            if (!TextUtils.isEmpty(this.streamType)) {
                arrayList.add("st=" + this.streamType);
            }
            float f = this.playbackRate;
            if (!(f == -3.4028235E38f || f == 1.0f)) {
                arrayList.add(Util.formatInvariant("%s=%.2f", CmcdConfiguration.KEY_PLAYBACK_RATE, Float.valueOf(f)));
            }
            arrayList.addAll(this.customDataList);
            if (!arrayList.isEmpty()) {
                arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_SESSION, arrayList);
            }
        }
    }

    private static final class CmcdStatus {
        public final boolean bufferStarvation;
        public final ImmutableList<String> customDataList;
        public final int maximumRequestedThroughputKbps;

        public static final class Builder {
            /* access modifiers changed from: private */
            public boolean bufferStarvation;
            /* access modifiers changed from: private */
            public ImmutableList<String> customDataList = ImmutableList.of();
            /* access modifiers changed from: private */
            public int maximumRequestedThroughputKbps = C.RATE_UNSET_INT;

            public Builder setMaximumRequestedThroughputKbps(int i) {
                Assertions.checkArgument(i >= 0 || i == -2147483647);
                if (i != -2147483647) {
                    i = ((i + 50) / 100) * 100;
                }
                this.maximumRequestedThroughputKbps = i;
                return this;
            }

            public Builder setBufferStarvation(boolean z) {
                this.bufferStarvation = z;
                return this;
            }

            public Builder setCustomDataList(List<String> list) {
                this.customDataList = ImmutableList.copyOf(list);
                return this;
            }

            public CmcdStatus build() {
                return new CmcdStatus(this);
            }
        }

        private CmcdStatus(Builder builder) {
            this.maximumRequestedThroughputKbps = builder.maximumRequestedThroughputKbps;
            this.bufferStarvation = builder.bufferStarvation;
            this.customDataList = builder.customDataList;
        }

        public void populateCmcdDataMap(ArrayListMultimap<String, String> arrayListMultimap) {
            ArrayList arrayList = new ArrayList();
            if (this.maximumRequestedThroughputKbps != -2147483647) {
                arrayList.add("rtp=" + this.maximumRequestedThroughputKbps);
            }
            if (this.bufferStarvation) {
                arrayList.add(CmcdConfiguration.KEY_BUFFER_STARVATION);
            }
            arrayList.addAll(this.customDataList);
            if (!arrayList.isEmpty()) {
                arrayListMultimap.putAll(CmcdConfiguration.KEY_CMCD_STATUS, arrayList);
            }
        }
    }
}
