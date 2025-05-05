package androidx.media3.transformer;

import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class TransformationRequest {
    public final String audioMimeType;
    public final int hdrMode;
    public final int outputHeight;
    public final String videoMimeType;

    public static final class Builder {
        private String audioMimeType;
        private int hdrMode;
        private int outputHeight;
        private String videoMimeType;

        public Builder() {
            this.outputHeight = -1;
        }

        private Builder(TransformationRequest transformationRequest) {
            this.outputHeight = transformationRequest.outputHeight;
            this.audioMimeType = transformationRequest.audioMimeType;
            this.videoMimeType = transformationRequest.videoMimeType;
            this.hdrMode = transformationRequest.hdrMode;
        }

        public Builder setVideoMimeType(String str) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            Assertions.checkArgument(normalizeMimeType == null || MimeTypes.isVideo(normalizeMimeType), "Not a video MIME type: " + normalizeMimeType);
            this.videoMimeType = normalizeMimeType;
            return this;
        }

        public Builder setAudioMimeType(String str) {
            String normalizeMimeType = MimeTypes.normalizeMimeType(str);
            Assertions.checkArgument(normalizeMimeType == null || MimeTypes.isAudio(normalizeMimeType), "Not an audio MIME type: " + normalizeMimeType);
            this.audioMimeType = normalizeMimeType;
            return this;
        }

        public Builder setHdrMode(int i) {
            this.hdrMode = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setResolution(int i) {
            this.outputHeight = i;
            return this;
        }

        public TransformationRequest build() {
            return new TransformationRequest(this.outputHeight, this.audioMimeType, this.videoMimeType, this.hdrMode);
        }
    }

    private TransformationRequest(int i, String str, String str2, int i2) {
        this.outputHeight = i;
        this.audioMimeType = str;
        this.videoMimeType = str2;
        this.hdrMode = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformationRequest)) {
            return false;
        }
        TransformationRequest transformationRequest = (TransformationRequest) obj;
        if (this.outputHeight != transformationRequest.outputHeight || !Util.areEqual(this.audioMimeType, transformationRequest.audioMimeType) || !Util.areEqual(this.videoMimeType, transformationRequest.videoMimeType) || this.hdrMode != transformationRequest.hdrMode) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.outputHeight * 31;
        String str = this.audioMimeType;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.videoMimeType;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((hashCode + i2) * 31) + this.hdrMode;
    }

    public String toString() {
        return "TransformationRequest{outputHeight=" + this.outputHeight + ", audioMimeType='" + this.audioMimeType + "', videoMimeType='" + this.videoMimeType + "', hdrMode=" + this.hdrMode + AbstractJsonLexerKt.END_OBJ;
    }

    public Builder buildUpon() {
        return new Builder();
    }
}
