package androidx.media3.session;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public final class LibraryResult<V> {
    private static final String FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(1);
    private static final String FIELD_PARAMS = Util.intToStringMaxRadix(2);
    private static final String FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
    private static final String FIELD_SESSION_ERROR = Util.intToStringMaxRadix(5);
    private static final String FIELD_VALUE = Util.intToStringMaxRadix(3);
    private static final String FIELD_VALUE_TYPE = Util.intToStringMaxRadix(4);
    public static final int RESULT_ERROR_BAD_VALUE = -3;
    public static final int RESULT_ERROR_INVALID_STATE = -2;
    public static final int RESULT_ERROR_IO = -5;
    public static final int RESULT_ERROR_NOT_SUPPORTED = -6;
    public static final int RESULT_ERROR_PERMISSION_DENIED = -4;
    public static final int RESULT_ERROR_SESSION_AUTHENTICATION_EXPIRED = -102;
    public static final int RESULT_ERROR_SESSION_CONCURRENT_STREAM_LIMIT = -104;
    public static final int RESULT_ERROR_SESSION_DISCONNECTED = -100;
    public static final int RESULT_ERROR_SESSION_NOT_AVAILABLE_IN_REGION = -106;
    public static final int RESULT_ERROR_SESSION_PARENTAL_CONTROL_RESTRICTED = -105;
    public static final int RESULT_ERROR_SESSION_PREMIUM_ACCOUNT_REQUIRED = -103;
    public static final int RESULT_ERROR_SESSION_SETUP_REQUIRED = -108;
    public static final int RESULT_ERROR_SESSION_SKIP_LIMIT_REACHED = -107;
    public static final int RESULT_ERROR_UNKNOWN = -1;
    public static final int RESULT_INFO_SKIPPED = 1;
    public static final int RESULT_SUCCESS = 0;
    private static final int VALUE_TYPE_ERROR = 4;
    private static final int VALUE_TYPE_ITEM = 2;
    private static final int VALUE_TYPE_ITEM_LIST = 3;
    private static final int VALUE_TYPE_VOID = 1;
    public final long completionTimeMs;
    public final MediaLibraryService.LibraryParams params;
    public final int resultCode;
    public final SessionError sessionError;
    public final V value;
    private final int valueType;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    public static LibraryResult<Void> ofVoid() {
        return new LibraryResult(0, SystemClock.elapsedRealtime(), (MediaLibraryService.LibraryParams) null, (SessionError) null, (Object) null, 1);
    }

    public static LibraryResult<Void> ofVoid(MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult(0, SystemClock.elapsedRealtime(), libraryParams, (SessionError) null, (Object) null, 1);
    }

    public static LibraryResult<MediaItem> ofItem(MediaItem mediaItem, MediaLibraryService.LibraryParams libraryParams) {
        verifyMediaItem(mediaItem);
        return new LibraryResult(0, SystemClock.elapsedRealtime(), libraryParams, (SessionError) null, mediaItem, 2);
    }

    public static LibraryResult<ImmutableList<MediaItem>> ofItemList(List<MediaItem> list, MediaLibraryService.LibraryParams libraryParams) {
        for (MediaItem verifyMediaItem : list) {
            verifyMediaItem(verifyMediaItem);
        }
        return new LibraryResult(0, SystemClock.elapsedRealtime(), libraryParams, (SessionError) null, ImmutableList.copyOf(list), 3);
    }

    public static <V> LibraryResult<V> ofError(int i) {
        return ofError(new SessionError(i, "no error message provided", Bundle.EMPTY));
    }

    public static <V> LibraryResult<V> ofError(int i, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult(i, SystemClock.elapsedRealtime(), libraryParams, new SessionError(i, "no error message provided", Bundle.EMPTY), (Object) null, 4);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError2) {
        return new LibraryResult(sessionError2.code, SystemClock.elapsedRealtime(), (MediaLibraryService.LibraryParams) null, sessionError2, (Object) null, 4);
    }

    public static <V> LibraryResult<V> ofError(SessionError sessionError2, MediaLibraryService.LibraryParams libraryParams) {
        return new LibraryResult(sessionError2.code, SystemClock.elapsedRealtime(), libraryParams, sessionError2, (Object) null, 4);
    }

    private LibraryResult(int i, long j, MediaLibraryService.LibraryParams libraryParams, SessionError sessionError2, V v, int i2) {
        this.resultCode = i;
        this.completionTimeMs = j;
        this.params = libraryParams;
        this.sessionError = sessionError2;
        this.value = v;
        this.valueType = i2;
    }

    private static void verifyMediaItem(MediaItem mediaItem) {
        Assertions.checkNotEmpty(mediaItem.mediaId, "mediaId must not be empty");
        boolean z = true;
        Assertions.checkArgument(mediaItem.mediaMetadata.isBrowsable != null, "mediaMetadata must specify isBrowsable");
        if (mediaItem.mediaMetadata.isPlayable == null) {
            z = false;
        }
        Assertions.checkArgument(z, "mediaMetadata must specify isPlayable");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        if (r2 != 4) goto L_0x006b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle toBundle() {
        /*
            r5 = this;
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = FIELD_RESULT_CODE
            int r2 = r5.resultCode
            r0.putInt(r1, r2)
            java.lang.String r1 = FIELD_COMPLETION_TIME_MS
            long r2 = r5.completionTimeMs
            r0.putLong(r1, r2)
            androidx.media3.session.MediaLibraryService$LibraryParams r1 = r5.params
            if (r1 == 0) goto L_0x0020
            java.lang.String r2 = FIELD_PARAMS
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L_0x0020:
            androidx.media3.session.SessionError r1 = r5.sessionError
            if (r1 == 0) goto L_0x002d
            java.lang.String r2 = FIELD_SESSION_ERROR
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L_0x002d:
            java.lang.String r1 = FIELD_VALUE_TYPE
            int r2 = r5.valueType
            r0.putInt(r1, r2)
            V r1 = r5.value
            if (r1 != 0) goto L_0x0039
            return r0
        L_0x0039:
            int r2 = r5.valueType
            r3 = 1
            if (r2 == r3) goto L_0x006c
            r3 = 2
            if (r2 == r3) goto L_0x0060
            r1 = 3
            if (r2 == r1) goto L_0x0048
            r1 = 4
            if (r2 == r1) goto L_0x006c
            goto L_0x006b
        L_0x0048:
            java.lang.String r1 = FIELD_VALUE
            androidx.media3.common.BundleListRetriever r2 = new androidx.media3.common.BundleListRetriever
            V r3 = r5.value
            com.google.common.collect.ImmutableList r3 = (com.google.common.collect.ImmutableList) r3
            androidx.media3.session.LibraryResult$$ExternalSyntheticLambda0 r4 = new androidx.media3.session.LibraryResult$$ExternalSyntheticLambda0
            r4.<init>()
            com.google.common.collect.ImmutableList r3 = androidx.media3.common.util.BundleCollectionUtil.toBundleList(r3, r4)
            r2.<init>(r3)
            androidx.core.app.BundleCompat.putBinder(r0, r1, r2)
            goto L_0x006b
        L_0x0060:
            java.lang.String r2 = FIELD_VALUE
            androidx.media3.common.MediaItem r1 = (androidx.media3.common.MediaItem) r1
            android.os.Bundle r1 = r1.toBundle()
            r0.putBundle(r2, r1)
        L_0x006b:
            return r0
        L_0x006c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.LibraryResult.toBundle():android.os.Bundle");
    }

    public static LibraryResult<Void> fromVoidBundle(Bundle bundle) {
        return fromUnknownBundle(bundle);
    }

    public static LibraryResult<MediaItem> fromItemBundle(Bundle bundle) {
        return fromBundle(bundle, 2);
    }

    public static LibraryResult<ImmutableList<MediaItem>> fromItemListBundle(Bundle bundle) {
        return fromBundle(bundle, 3);
    }

    public static LibraryResult<?> fromUnknownBundle(Bundle bundle) {
        return fromBundle(bundle, (Integer) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.session.LibraryResult<?> fromBundle(android.os.Bundle r10, java.lang.Integer r11) {
        /*
            java.lang.String r0 = FIELD_RESULT_CODE
            r1 = 0
            int r3 = r10.getInt(r0, r1)
            java.lang.String r0 = FIELD_COMPLETION_TIME_MS
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r10.getLong(r0, r4)
            java.lang.String r0 = FIELD_PARAMS
            android.os.Bundle r0 = r10.getBundle(r0)
            r2 = 0
            if (r0 != 0) goto L_0x001c
            r6 = r2
            goto L_0x0021
        L_0x001c:
            androidx.media3.session.MediaLibraryService$LibraryParams r0 = androidx.media3.session.MediaLibraryService.LibraryParams.fromBundle(r0)
            r6 = r0
        L_0x0021:
            java.lang.String r0 = FIELD_SESSION_ERROR
            android.os.Bundle r0 = r10.getBundle(r0)
            if (r0 == 0) goto L_0x002f
            androidx.media3.session.SessionError r0 = androidx.media3.session.SessionError.fromBundle(r0)
        L_0x002d:
            r7 = r0
            goto L_0x003a
        L_0x002f:
            if (r3 == 0) goto L_0x0039
            androidx.media3.session.SessionError r0 = new androidx.media3.session.SessionError
            java.lang.String r7 = "no error message provided"
            r0.<init>(r3, r7)
            goto L_0x002d
        L_0x0039:
            r7 = r2
        L_0x003a:
            java.lang.String r0 = FIELD_VALUE_TYPE
            int r9 = r10.getInt(r0)
            r0 = 1
            if (r9 == r0) goto L_0x008f
            r8 = 2
            if (r9 == r8) goto L_0x0076
            r8 = 3
            if (r9 == r8) goto L_0x0053
            r10 = 4
            if (r9 != r10) goto L_0x004d
            goto L_0x008f
        L_0x004d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L_0x0053:
            if (r11 == 0) goto L_0x005b
            int r11 = r11.intValue()
            if (r11 != r8) goto L_0x005c
        L_0x005b:
            r1 = r0
        L_0x005c:
            androidx.media3.common.util.Assertions.checkState(r1)
            java.lang.String r11 = FIELD_VALUE
            android.os.IBinder r10 = androidx.core.app.BundleCompat.getBinder(r10, r11)
            if (r10 != 0) goto L_0x0068
            goto L_0x008f
        L_0x0068:
            androidx.media3.session.LibraryResult$$ExternalSyntheticLambda1 r11 = new androidx.media3.session.LibraryResult$$ExternalSyntheticLambda1
            r11.<init>()
            com.google.common.collect.ImmutableList r10 = androidx.media3.common.BundleListRetriever.getList(r10)
            com.google.common.collect.ImmutableList r2 = androidx.media3.common.util.BundleCollectionUtil.fromBundleList(r11, r10)
            goto L_0x008f
        L_0x0076:
            if (r11 == 0) goto L_0x007e
            int r11 = r11.intValue()
            if (r11 != r8) goto L_0x007f
        L_0x007e:
            r1 = r0
        L_0x007f:
            androidx.media3.common.util.Assertions.checkState(r1)
            java.lang.String r11 = FIELD_VALUE
            android.os.Bundle r10 = r10.getBundle(r11)
            if (r10 != 0) goto L_0x008b
            goto L_0x008f
        L_0x008b:
            androidx.media3.common.MediaItem r2 = androidx.media3.common.MediaItem.fromBundle(r10)
        L_0x008f:
            r8 = r2
            androidx.media3.session.LibraryResult r10 = new androidx.media3.session.LibraryResult
            r2 = r10
            r2.<init>(r3, r4, r6, r7, r8, r9)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.LibraryResult.fromBundle(android.os.Bundle, java.lang.Integer):androidx.media3.session.LibraryResult");
    }
}
