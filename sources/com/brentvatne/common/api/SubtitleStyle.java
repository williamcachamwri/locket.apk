package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u001e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u001e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0014@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/brentvatne/common/api/SubtitleStyle;", "", "()V", "<set-?>", "", "fontSize", "getFontSize", "()I", "", "opacity", "getOpacity", "()F", "paddingBottom", "getPaddingBottom", "paddingLeft", "getPaddingLeft", "paddingRight", "getPaddingRight", "paddingTop", "getPaddingTop", "", "subtitlesFollowVideo", "getSubtitlesFollowVideo", "()Z", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitleStyle.kt */
public final class SubtitleStyle {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PROP_FONT_SIZE_TRACK = "fontSize";
    private static final String PROP_OPACITY = "opacity";
    private static final String PROP_PADDING_BOTTOM = "paddingBottom";
    private static final String PROP_PADDING_LEFT = "paddingLeft";
    private static final String PROP_PADDING_RIGHT = "paddingRight";
    private static final String PROP_PADDING_TOP = "paddingTop";
    private static final String PROP_SUBTITLES_FOLLOW_VIDEO = "subtitlesFollowVideo";
    /* access modifiers changed from: private */
    public int fontSize = -1;
    /* access modifiers changed from: private */
    public float opacity = 1.0f;
    /* access modifiers changed from: private */
    public int paddingBottom;
    /* access modifiers changed from: private */
    public int paddingLeft;
    /* access modifiers changed from: private */
    public int paddingRight;
    /* access modifiers changed from: private */
    public int paddingTop;
    /* access modifiers changed from: private */
    public boolean subtitlesFollowVideo = true;

    @JvmStatic
    public static final SubtitleStyle parse(ReadableMap readableMap) {
        return Companion.parse(readableMap);
    }

    public final int getFontSize() {
        return this.fontSize;
    }

    public final int getPaddingLeft() {
        return this.paddingLeft;
    }

    public final int getPaddingRight() {
        return this.paddingRight;
    }

    public final int getPaddingTop() {
        return this.paddingTop;
    }

    public final int getPaddingBottom() {
        return this.paddingBottom;
    }

    public final float getOpacity() {
        return this.opacity;
    }

    public final boolean getSubtitlesFollowVideo() {
        return this.subtitlesFollowVideo;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/brentvatne/common/api/SubtitleStyle$Companion;", "", "()V", "PROP_FONT_SIZE_TRACK", "", "PROP_OPACITY", "PROP_PADDING_BOTTOM", "PROP_PADDING_LEFT", "PROP_PADDING_RIGHT", "PROP_PADDING_TOP", "PROP_SUBTITLES_FOLLOW_VIDEO", "parse", "Lcom/brentvatne/common/api/SubtitleStyle;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SubtitleStyle.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SubtitleStyle parse(ReadableMap readableMap) {
            SubtitleStyle subtitleStyle = new SubtitleStyle();
            subtitleStyle.fontSize = ReactBridgeUtils.safeGetInt(readableMap, "fontSize", -1);
            subtitleStyle.paddingBottom = ReactBridgeUtils.safeGetInt(readableMap, "paddingBottom", 0);
            subtitleStyle.paddingTop = ReactBridgeUtils.safeGetInt(readableMap, "paddingTop", 0);
            subtitleStyle.paddingLeft = ReactBridgeUtils.safeGetInt(readableMap, "paddingLeft", 0);
            subtitleStyle.paddingRight = ReactBridgeUtils.safeGetInt(readableMap, "paddingRight", 0);
            subtitleStyle.opacity = ReactBridgeUtils.safeGetFloat(readableMap, "opacity", 1.0f);
            subtitleStyle.subtitlesFollowVideo = ReactBridgeUtils.safeGetBool(readableMap, SubtitleStyle.PROP_SUBTITLES_FOLLOW_VIDEO, true);
            return subtitleStyle;
        }
    }
}
