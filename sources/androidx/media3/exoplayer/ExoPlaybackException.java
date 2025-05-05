package androidx.media3.exoplayer;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class ExoPlaybackException extends PlaybackException {
    private static final String FIELD_IS_RECOVERABLE = Util.intToStringMaxRadix(1006);
    private static final String FIELD_RENDERER_FORMAT = Util.intToStringMaxRadix(1004);
    private static final String FIELD_RENDERER_FORMAT_SUPPORT = Util.intToStringMaxRadix(1005);
    private static final String FIELD_RENDERER_INDEX = Util.intToStringMaxRadix(1003);
    private static final String FIELD_RENDERER_NAME = Util.intToStringMaxRadix(1002);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(1001);
    public static final int TYPE_REMOTE = 3;
    public static final int TYPE_RENDERER = 1;
    public static final int TYPE_SOURCE = 0;
    public static final int TYPE_UNEXPECTED = 2;
    final boolean isRecoverable;
    public final MediaSource.MediaPeriodId mediaPeriodId;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final String rendererName;
    public final int type;

    @Documented
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static ExoPlaybackException createForSource(IOException iOException, int i) {
        return new ExoPlaybackException(0, iOException, i);
    }

    public static ExoPlaybackException createForRenderer(Throwable th, String str, int i, Format format, int i2, boolean z, int i3) {
        return new ExoPlaybackException(1, th, (String) null, i3, str, i, format, format == null ? 4 : i2, z);
    }

    @Deprecated
    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException) {
        return createForUnexpected(runtimeException, 1000);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException runtimeException, int i) {
        return new ExoPlaybackException(2, runtimeException, i);
    }

    public static ExoPlaybackException createForRemote(String str) {
        return new ExoPlaybackException(3, (Throwable) null, str, 1001, (String) null, -1, (Format) null, 4, false);
    }

    private ExoPlaybackException(int i, Throwable th, int i2) {
        this(i, th, (String) null, i2, (String) null, -1, (Format) null, 4, false);
    }

    private ExoPlaybackException(int i, Throwable th, String str, int i2, String str2, int i3, Format format, int i4, boolean z) {
        this(deriveMessage(i, str, str2, i3, format, i4), th, i2, i, str2, i3, format, i4, (MediaSource.MediaPeriodId) null, SystemClock.elapsedRealtime(), z);
    }

    private ExoPlaybackException(Bundle bundle) {
        super(bundle);
        Format format;
        this.type = bundle.getInt(FIELD_TYPE, 2);
        this.rendererName = bundle.getString(FIELD_RENDERER_NAME);
        this.rendererIndex = bundle.getInt(FIELD_RENDERER_INDEX, -1);
        Bundle bundle2 = bundle.getBundle(FIELD_RENDERER_FORMAT);
        if (bundle2 == null) {
            format = null;
        } else {
            format = Format.fromBundle(bundle2);
        }
        this.rendererFormat = format;
        this.rendererFormatSupport = bundle.getInt(FIELD_RENDERER_FORMAT_SUPPORT, 4);
        this.isRecoverable = bundle.getBoolean(FIELD_IS_RECOVERABLE, false);
        this.mediaPeriodId = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ExoPlaybackException(String str, Throwable th, int i, int i2, String str2, int i3, Format format, int i4, MediaSource.MediaPeriodId mediaPeriodId2, long j, boolean z) {
        super(str, th, i, Bundle.EMPTY, j);
        int i5 = i2;
        boolean z2 = z;
        boolean z3 = false;
        Assertions.checkArgument(!z2 || i5 == 1);
        Assertions.checkArgument((th != null || i5 == 3) ? true : z3);
        this.type = i5;
        this.rendererName = str2;
        this.rendererIndex = i3;
        this.rendererFormat = format;
        this.rendererFormatSupport = i4;
        this.mediaPeriodId = mediaPeriodId2;
        this.isRecoverable = z2;
    }

    public IOException getSourceException() {
        Assertions.checkState(this.type == 0);
        return (IOException) Assertions.checkNotNull(getCause());
    }

    public Exception getRendererException() {
        boolean z = true;
        if (this.type != 1) {
            z = false;
        }
        Assertions.checkState(z);
        return (Exception) Assertions.checkNotNull(getCause());
    }

    public RuntimeException getUnexpectedException() {
        Assertions.checkState(this.type == 2);
        return (RuntimeException) Assertions.checkNotNull(getCause());
    }

    public boolean errorInfoEquals(PlaybackException playbackException) {
        if (!super.errorInfoEquals(playbackException)) {
            return false;
        }
        ExoPlaybackException exoPlaybackException = (ExoPlaybackException) Util.castNonNull(playbackException);
        if (this.type == exoPlaybackException.type && Util.areEqual(this.rendererName, exoPlaybackException.rendererName) && this.rendererIndex == exoPlaybackException.rendererIndex && Util.areEqual(this.rendererFormat, exoPlaybackException.rendererFormat) && this.rendererFormatSupport == exoPlaybackException.rendererFormatSupport && Util.areEqual(this.mediaPeriodId, exoPlaybackException.mediaPeriodId) && this.isRecoverable == exoPlaybackException.isRecoverable) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public ExoPlaybackException copyWithMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId2) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, mediaPeriodId2, this.timestampMs, this.isRecoverable);
    }

    private static String deriveMessage(int i, String str, String str2, int i2, Format format, int i3) {
        String str3;
        if (i != 0) {
            str3 = i != 1 ? i != 3 ? "Unexpected runtime error" : "Remote error" : str2 + " error, index=" + i2 + ", format=" + format + ", format_supported=" + Util.getFormatSupportString(i3);
        } else {
            str3 = "Source error";
        }
        return !TextUtils.isEmpty(str) ? str3 + ": " + str : str3;
    }

    public static ExoPlaybackException fromBundle(Bundle bundle) {
        return new ExoPlaybackException(bundle);
    }

    public Bundle toBundle() {
        Bundle bundle = super.toBundle();
        bundle.putInt(FIELD_TYPE, this.type);
        bundle.putString(FIELD_RENDERER_NAME, this.rendererName);
        bundle.putInt(FIELD_RENDERER_INDEX, this.rendererIndex);
        Format format = this.rendererFormat;
        if (format != null) {
            bundle.putBundle(FIELD_RENDERER_FORMAT, format.toBundle(false));
        }
        bundle.putInt(FIELD_RENDERER_FORMAT_SUPPORT, this.rendererFormatSupport);
        bundle.putBoolean(FIELD_IS_RECOVERABLE, this.isRecoverable);
        return bundle;
    }
}
