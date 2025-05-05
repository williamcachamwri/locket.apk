package com.brentvatne.common.api;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import com.brentvatne.common.toolbox.DebugLog;
import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@kotlin.Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 Y2\u00020\u0001:\u0002YZB\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010T\u001a\u0002022\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010V\u001a\u00020\u0016H\u0016J\u000e\u0010W\u001a\u0002022\u0006\u0010X\u001a\u00020\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001a\u0010\u001e\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001d\u0010-\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020(0.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u000202X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00103\"\u0004\b7\u00105R\u001c\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0018\"\u0004\b@\u0010\u001aR\u001c\u0010A\u001a\u0004\u0018\u00010BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0018\"\u0004\bI\u0010\u001aR\u001a\u0010J\u001a\u000202X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u00103\"\u0004\bL\u00105R\u001c\u0010M\u001a\u0004\u0018\u00010NX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0010\u0010S\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0002\n\u0000¨\u0006["}, d2 = {"Lcom/brentvatne/common/api/Source;", "", "()V", "adsProps", "Lcom/brentvatne/common/api/AdsProps;", "getAdsProps", "()Lcom/brentvatne/common/api/AdsProps;", "setAdsProps", "(Lcom/brentvatne/common/api/AdsProps;)V", "bufferConfig", "Lcom/brentvatne/common/api/BufferConfig;", "getBufferConfig", "()Lcom/brentvatne/common/api/BufferConfig;", "setBufferConfig", "(Lcom/brentvatne/common/api/BufferConfig;)V", "cmcdProps", "Lcom/brentvatne/common/api/CMCDProps;", "getCmcdProps", "()Lcom/brentvatne/common/api/CMCDProps;", "setCmcdProps", "(Lcom/brentvatne/common/api/CMCDProps;)V", "contentStartTime", "", "getContentStartTime", "()I", "setContentStartTime", "(I)V", "cropEndMs", "getCropEndMs", "setCropEndMs", "cropStartMs", "getCropStartMs", "setCropStartMs", "drmProps", "Lcom/brentvatne/common/api/DRMProps;", "getDrmProps", "()Lcom/brentvatne/common/api/DRMProps;", "setDrmProps", "(Lcom/brentvatne/common/api/DRMProps;)V", "extension", "", "getExtension", "()Ljava/lang/String;", "setExtension", "(Ljava/lang/String;)V", "headers", "", "getHeaders", "()Ljava/util/Map;", "isAsset", "", "()Z", "setAsset", "(Z)V", "isLocalAssetFile", "setLocalAssetFile", "metadata", "Lcom/brentvatne/common/api/Source$Metadata;", "getMetadata", "()Lcom/brentvatne/common/api/Source$Metadata;", "setMetadata", "(Lcom/brentvatne/common/api/Source$Metadata;)V", "minLoadRetryCount", "getMinLoadRetryCount", "setMinLoadRetryCount", "sideLoadedTextTracks", "Lcom/brentvatne/common/api/SideLoadedTextTrackList;", "getSideLoadedTextTracks", "()Lcom/brentvatne/common/api/SideLoadedTextTrackList;", "setSideLoadedTextTracks", "(Lcom/brentvatne/common/api/SideLoadedTextTrackList;)V", "startPositionMs", "getStartPositionMs", "setStartPositionMs", "textTracksAllowChunklessPreparation", "getTextTracksAllowChunklessPreparation", "setTextTracksAllowChunklessPreparation", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "uriString", "equals", "other", "hashCode", "isEquals", "source", "Companion", "Metadata", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Source.kt */
public final class Source {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_SRC_ADS = "ad";
    private static final String PROP_SRC_BUFFER_CONFIG = "bufferConfig";
    private static final String PROP_SRC_CMCD = "cmcd";
    private static final String PROP_SRC_CONTENT_START_TIME = "contentStartTime";
    private static final String PROP_SRC_CROP_END = "cropEnd";
    private static final String PROP_SRC_CROP_START = "cropStart";
    private static final String PROP_SRC_DRM = "drm";
    private static final String PROP_SRC_HEADERS = "requestHeaders";
    private static final String PROP_SRC_IS_ASSET = "isAsset";
    private static final String PROP_SRC_IS_LOCAL_ASSET_FILE = "isLocalAssetFile";
    private static final String PROP_SRC_METADATA = "metadata";
    private static final String PROP_SRC_MIN_LOAD_RETRY_COUNT = "minLoadRetryCount";
    private static final String PROP_SRC_START_POSITION = "startPosition";
    private static final String PROP_SRC_TEXT_TRACKS = "textTracks";
    private static final String PROP_SRC_TEXT_TRACKS_ALLOW_CHUNKLESS_PREPARATION = "textTracksAllowChunklessPreparation";
    private static final String PROP_SRC_TYPE = "type";
    private static final String PROP_SRC_URI = "uri";
    private static final String TAG = "Source";
    private AdsProps adsProps;
    private BufferConfig bufferConfig = new BufferConfig();
    private CMCDProps cmcdProps;
    private int contentStartTime = -1;
    private int cropEndMs = -1;
    private int cropStartMs = -1;
    private DRMProps drmProps;
    private String extension;
    private final Map<String, String> headers = new HashMap();
    private boolean isAsset;
    private boolean isLocalAssetFile;
    private Metadata metadata;
    private int minLoadRetryCount = 3;
    private SideLoadedTextTrackList sideLoadedTextTracks;
    private int startPositionMs = -1;
    private boolean textTracksAllowChunklessPreparation;
    private Uri uri;
    /* access modifiers changed from: private */
    public String uriString;

    @JvmStatic
    public static final Source parse(ReadableMap readableMap, Context context) {
        return Companion.parse(readableMap, context);
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(Uri uri2) {
        this.uri = uri2;
    }

    public final boolean isLocalAssetFile() {
        return this.isLocalAssetFile;
    }

    public final void setLocalAssetFile(boolean z) {
        this.isLocalAssetFile = z;
    }

    public final boolean isAsset() {
        return this.isAsset;
    }

    public final void setAsset(boolean z) {
        this.isAsset = z;
    }

    public final int getStartPositionMs() {
        return this.startPositionMs;
    }

    public final void setStartPositionMs(int i) {
        this.startPositionMs = i;
    }

    public final int getCropStartMs() {
        return this.cropStartMs;
    }

    public final void setCropStartMs(int i) {
        this.cropStartMs = i;
    }

    public final int getCropEndMs() {
        return this.cropEndMs;
    }

    public final void setCropEndMs(int i) {
        this.cropEndMs = i;
    }

    public final int getContentStartTime() {
        return this.contentStartTime;
    }

    public final void setContentStartTime(int i) {
        this.contentStartTime = i;
    }

    public final String getExtension() {
        return this.extension;
    }

    public final void setExtension(String str) {
        this.extension = str;
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(Metadata metadata2) {
        this.metadata = metadata2;
    }

    public final int getMinLoadRetryCount() {
        return this.minLoadRetryCount;
    }

    public final void setMinLoadRetryCount(int i) {
        this.minLoadRetryCount = i;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final DRMProps getDrmProps() {
        return this.drmProps;
    }

    public final void setDrmProps(DRMProps dRMProps) {
        this.drmProps = dRMProps;
    }

    public final boolean getTextTracksAllowChunklessPreparation() {
        return this.textTracksAllowChunklessPreparation;
    }

    public final void setTextTracksAllowChunklessPreparation(boolean z) {
        this.textTracksAllowChunklessPreparation = z;
    }

    public final CMCDProps getCmcdProps() {
        return this.cmcdProps;
    }

    public final void setCmcdProps(CMCDProps cMCDProps) {
        this.cmcdProps = cMCDProps;
    }

    public final AdsProps getAdsProps() {
        return this.adsProps;
    }

    public final void setAdsProps(AdsProps adsProps2) {
        this.adsProps = adsProps2;
    }

    public final BufferConfig getBufferConfig() {
        return this.bufferConfig;
    }

    public final void setBufferConfig(BufferConfig bufferConfig2) {
        Intrinsics.checkNotNullParameter(bufferConfig2, "<set-?>");
        this.bufferConfig = bufferConfig2;
    }

    public final SideLoadedTextTrackList getSideLoadedTextTracks() {
        return this.sideLoadedTextTracks;
    }

    public final void setSideLoadedTextTracks(SideLoadedTextTrackList sideLoadedTextTrackList) {
        this.sideLoadedTextTracks = sideLoadedTextTrackList;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.uriString, this.uri, Integer.valueOf(this.startPositionMs), Integer.valueOf(this.cropStartMs), Integer.valueOf(this.cropEndMs), this.extension, this.metadata, this.headers});
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Source)) {
            return false;
        }
        Source source = (Source) obj;
        if (!Intrinsics.areEqual((Object) this.uri, (Object) source.uri) || this.cropStartMs != source.cropStartMs || this.cropEndMs != source.cropEndMs || this.startPositionMs != source.startPositionMs || !Intrinsics.areEqual((Object) this.extension, (Object) source.extension) || !Intrinsics.areEqual((Object) this.drmProps, (Object) source.drmProps) || this.contentStartTime != source.contentStartTime || !Intrinsics.areEqual((Object) this.cmcdProps, (Object) source.cmcdProps) || !Intrinsics.areEqual((Object) this.sideLoadedTextTracks, (Object) source.sideLoadedTextTracks) || !Intrinsics.areEqual((Object) this.adsProps, (Object) source.adsProps) || this.minLoadRetryCount != source.minLoadRetryCount || this.isLocalAssetFile != source.isLocalAssetFile || this.isAsset != source.isAsset || !Intrinsics.areEqual((Object) this.bufferConfig, (Object) source.bufferConfig)) {
            return false;
        }
        return true;
    }

    public final boolean isEquals(Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return Intrinsics.areEqual((Object) this, (Object) source);
    }

    @kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u0019"}, d2 = {"Lcom/brentvatne/common/api/Source$Metadata;", "", "()V", "artist", "", "getArtist", "()Ljava/lang/String;", "setArtist", "(Ljava/lang/String;)V", "description", "getDescription", "setDescription", "imageUri", "Landroid/net/Uri;", "getImageUri", "()Landroid/net/Uri;", "setImageUri", "(Landroid/net/Uri;)V", "subtitle", "getSubtitle", "setSubtitle", "title", "getTitle", "setTitle", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Source.kt */
    public static final class Metadata {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final String PROP_SRC_METADATA_ARTIST = "artist";
        private static final String PROP_SRC_METADATA_DESCRIPTION = "description";
        private static final String PROP_SRC_METADATA_IMAGE_URI = "imageUri";
        private static final String PROP_SRC_METADATA_SUBTITLE = "subtitle";
        private static final String PROP_SRC_METADATA_TITLE = "title";
        private String artist;
        private String description;
        private Uri imageUri;
        private String subtitle;
        private String title;

        @JvmStatic
        public static final Metadata parse(ReadableMap readableMap) {
            return Companion.parse(readableMap);
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final void setSubtitle(String str) {
            this.subtitle = str;
        }

        public final String getDescription() {
            return this.description;
        }

        public final void setDescription(String str) {
            this.description = str;
        }

        public final String getArtist() {
            return this.artist;
        }

        public final void setArtist(String str) {
            this.artist = str;
        }

        public final Uri getImageUri() {
            return this.imageUri;
        }

        public final void setImageUri(Uri uri) {
            this.imageUri = uri;
        }

        @kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/brentvatne/common/api/Source$Metadata$Companion;", "", "()V", "PROP_SRC_METADATA_ARTIST", "", "PROP_SRC_METADATA_DESCRIPTION", "PROP_SRC_METADATA_IMAGE_URI", "PROP_SRC_METADATA_SUBTITLE", "PROP_SRC_METADATA_TITLE", "parse", "Lcom/brentvatne/common/api/Source$Metadata;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: Source.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final Metadata parse(ReadableMap readableMap) {
                if (readableMap == null) {
                    return null;
                }
                Metadata metadata = new Metadata();
                metadata.setTitle(ReactBridgeUtils.safeGetString(readableMap, Metadata.PROP_SRC_METADATA_TITLE));
                metadata.setSubtitle(ReactBridgeUtils.safeGetString(readableMap, Metadata.PROP_SRC_METADATA_SUBTITLE));
                metadata.setDescription(ReactBridgeUtils.safeGetString(readableMap, "description"));
                metadata.setArtist(ReactBridgeUtils.safeGetString(readableMap, Metadata.PROP_SRC_METADATA_ARTIST));
                try {
                    metadata.setImageUri(Uri.parse(ReactBridgeUtils.safeGetString(readableMap, Metadata.PROP_SRC_METADATA_IMAGE_URI)));
                } catch (Exception unused) {
                    DebugLog.e(Source.TAG, "Could not parse imageUri in metadata");
                }
                return metadata;
            }
        }
    }

    @kotlin.Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004H\u0003J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/brentvatne/common/api/Source$Companion;", "", "()V", "PROP_SRC_ADS", "", "PROP_SRC_BUFFER_CONFIG", "PROP_SRC_CMCD", "PROP_SRC_CONTENT_START_TIME", "PROP_SRC_CROP_END", "PROP_SRC_CROP_START", "PROP_SRC_DRM", "PROP_SRC_HEADERS", "PROP_SRC_IS_ASSET", "PROP_SRC_IS_LOCAL_ASSET_FILE", "PROP_SRC_METADATA", "PROP_SRC_MIN_LOAD_RETRY_COUNT", "PROP_SRC_START_POSITION", "PROP_SRC_TEXT_TRACKS", "PROP_SRC_TEXT_TRACKS_ALLOW_CHUNKLESS_PREPARATION", "PROP_SRC_TYPE", "PROP_SRC_URI", "TAG", "getUriFromAssetId", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "uriString", "isValidScheme", "", "scheme", "parse", "Lcom/brentvatne/common/api/Source;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Source.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Uri getUriFromAssetId(Context context, String str) {
            Resources resources = context.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            int identifier = resources.getIdentifier(str, "drawable", packageName);
            if (identifier == 0) {
                identifier = resources.getIdentifier(str, "raw", packageName);
            }
            if (identifier > 0) {
                return new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).path(String.valueOf(identifier)).build();
            }
            DebugLog.d(Source.TAG, "cannot find identifier");
            return null;
        }

        @JvmStatic
        public final Source parse(ReadableMap readableMap, Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Source source = new Source();
            if (readableMap != null) {
                String safeGetString = ReactBridgeUtils.safeGetString(readableMap, "uri", (String) null);
                if (safeGetString == null || TextUtils.isEmpty(safeGetString)) {
                    DebugLog.d(Source.TAG, "isEmpty uri:" + safeGetString);
                } else {
                    Uri parse = Uri.parse(safeGetString);
                    if (parse == null) {
                        DebugLog.d(Source.TAG, "Invalid uri:" + safeGetString);
                        return source;
                    } else if (isValidScheme(parse.getScheme()) || (parse = getUriFromAssetId(context, safeGetString)) != null) {
                        source.uriString = safeGetString;
                        source.setUri(parse);
                        source.setLocalAssetFile(ReactBridgeUtils.safeGetBool(readableMap, Source.PROP_SRC_IS_LOCAL_ASSET_FILE, false));
                        source.setAsset(ReactBridgeUtils.safeGetBool(readableMap, Source.PROP_SRC_IS_ASSET, false));
                        source.setStartPositionMs(ReactBridgeUtils.safeGetInt(readableMap, Source.PROP_SRC_START_POSITION, -1));
                        source.setCropStartMs(ReactBridgeUtils.safeGetInt(readableMap, Source.PROP_SRC_CROP_START, -1));
                        source.setCropEndMs(ReactBridgeUtils.safeGetInt(readableMap, Source.PROP_SRC_CROP_END, -1));
                        source.setContentStartTime(ReactBridgeUtils.safeGetInt(readableMap, Source.PROP_SRC_CONTENT_START_TIME, -1));
                        source.setExtension(ReactBridgeUtils.safeGetString(readableMap, "type", (String) null));
                        source.setDrmProps(DRMProps.Companion.parse(ReactBridgeUtils.safeGetMap(readableMap, Source.PROP_SRC_DRM)));
                        source.setCmcdProps(CMCDProps.Companion.parse(ReactBridgeUtils.safeGetMap(readableMap, Source.PROP_SRC_CMCD)));
                        source.setAdsProps(AdsProps.Companion.parse(ReactBridgeUtils.safeGetMap(readableMap, Source.PROP_SRC_ADS)));
                        source.setTextTracksAllowChunklessPreparation(ReactBridgeUtils.safeGetBool(readableMap, Source.PROP_SRC_TEXT_TRACKS_ALLOW_CHUNKLESS_PREPARATION, true));
                        source.setSideLoadedTextTracks(SideLoadedTextTrackList.Companion.parse(ReactBridgeUtils.safeGetArray(readableMap, Source.PROP_SRC_TEXT_TRACKS)));
                        source.setMinLoadRetryCount(ReactBridgeUtils.safeGetInt(readableMap, Source.PROP_SRC_MIN_LOAD_RETRY_COUNT, 3));
                        source.setBufferConfig(BufferConfig.Companion.parse(ReactBridgeUtils.safeGetMap(readableMap, Source.PROP_SRC_BUFFER_CONFIG)));
                        ReadableArray safeGetArray = ReactBridgeUtils.safeGetArray(readableMap, Source.PROP_SRC_HEADERS);
                        if (safeGetArray != null && safeGetArray.size() > 0) {
                            int size = safeGetArray.size();
                            for (int i = 0; i < size; i++) {
                                ReadableMap map = safeGetArray.getMap(i);
                                Intrinsics.checkNotNullExpressionValue(map, "getMap(...)");
                                String string = map.getString("key");
                                String string2 = map.getString("value");
                                if (!(string == null || string2 == null)) {
                                    source.getHeaders().put(string, string2);
                                }
                            }
                        }
                        source.setMetadata(Metadata.Companion.parse(ReactBridgeUtils.safeGetMap(readableMap, "metadata")));
                    } else {
                        DebugLog.d(Source.TAG, "cannot find identifier");
                        return source;
                    }
                }
            }
            return source;
        }

        private final boolean isValidScheme(String str) {
            if (str == null) {
                return false;
            }
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (Intrinsics.areEqual((Object) lowerCase, (Object) "http") || Intrinsics.areEqual((Object) lowerCase, (Object) "https") || Intrinsics.areEqual((Object) lowerCase, (Object) "content") || Intrinsics.areEqual((Object) lowerCase, (Object) "file") || Intrinsics.areEqual((Object) lowerCase, (Object) "rtsp") || Intrinsics.areEqual((Object) lowerCase, (Object) UriUtil.LOCAL_ASSET_SCHEME)) {
                return true;
            }
            return false;
        }
    }
}
