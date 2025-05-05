package com.brentvatne.common.api;

import android.net.Uri;
import com.brentvatne.common.toolbox.ReactBridgeUtils;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/brentvatne/common/api/SideLoadedTextTrack;", "", "()V", "language", "", "getLanguage", "()Ljava/lang/String;", "setLanguage", "(Ljava/lang/String;)V", "title", "getTitle", "setTitle", "type", "getType", "setType", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SideLoadedTextTrack.kt */
public final class SideLoadedTextTrack {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String SIDELOAD_TEXT_TRACK_LANGUAGE = "language";
    /* access modifiers changed from: private */
    public static final String SIDELOAD_TEXT_TRACK_TITLE = "title";
    /* access modifiers changed from: private */
    public static final String SIDELOAD_TEXT_TRACK_TYPE = "type";
    /* access modifiers changed from: private */
    public static final String SIDELOAD_TEXT_TRACK_URI = "uri";
    private String language;
    private String title;
    private String type;
    private Uri uri;

    public SideLoadedTextTrack() {
        Uri uri2 = Uri.EMPTY;
        Intrinsics.checkNotNullExpressionValue(uri2, "EMPTY");
        this.uri = uri2;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        this.language = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final void setUri(Uri uri2) {
        Intrinsics.checkNotNullParameter(uri2, "<set-?>");
        this.uri = uri2;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/brentvatne/common/api/SideLoadedTextTrack$Companion;", "", "()V", "SIDELOAD_TEXT_TRACK_LANGUAGE", "", "getSIDELOAD_TEXT_TRACK_LANGUAGE", "()Ljava/lang/String;", "SIDELOAD_TEXT_TRACK_TITLE", "getSIDELOAD_TEXT_TRACK_TITLE", "SIDELOAD_TEXT_TRACK_TYPE", "getSIDELOAD_TEXT_TRACK_TYPE", "SIDELOAD_TEXT_TRACK_URI", "getSIDELOAD_TEXT_TRACK_URI", "parse", "Lcom/brentvatne/common/api/SideLoadedTextTrack;", "src", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SideLoadedTextTrack.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getSIDELOAD_TEXT_TRACK_LANGUAGE() {
            return SideLoadedTextTrack.SIDELOAD_TEXT_TRACK_LANGUAGE;
        }

        public final String getSIDELOAD_TEXT_TRACK_TITLE() {
            return SideLoadedTextTrack.SIDELOAD_TEXT_TRACK_TITLE;
        }

        public final String getSIDELOAD_TEXT_TRACK_URI() {
            return SideLoadedTextTrack.SIDELOAD_TEXT_TRACK_URI;
        }

        public final String getSIDELOAD_TEXT_TRACK_TYPE() {
            return SideLoadedTextTrack.SIDELOAD_TEXT_TRACK_TYPE;
        }

        public final SideLoadedTextTrack parse(ReadableMap readableMap) {
            SideLoadedTextTrack sideLoadedTextTrack = new SideLoadedTextTrack();
            if (readableMap == null) {
                return sideLoadedTextTrack;
            }
            sideLoadedTextTrack.setLanguage(ReactBridgeUtils.safeGetString(readableMap, getSIDELOAD_TEXT_TRACK_LANGUAGE()));
            sideLoadedTextTrack.setTitle(ReactBridgeUtils.safeGetString(readableMap, getSIDELOAD_TEXT_TRACK_TITLE(), ""));
            Uri parse = Uri.parse(ReactBridgeUtils.safeGetString(readableMap, getSIDELOAD_TEXT_TRACK_URI(), ""));
            Intrinsics.checkNotNullExpressionValue(parse, "parse(...)");
            sideLoadedTextTrack.setUri(parse);
            sideLoadedTextTrack.setType(ReactBridgeUtils.safeGetString(readableMap, getSIDELOAD_TEXT_TRACK_TYPE(), ""));
            return sideLoadedTextTrack;
        }
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }
}
