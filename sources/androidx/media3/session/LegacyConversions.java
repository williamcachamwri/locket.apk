package androidx.media3.session;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.HeartRating;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PercentageRating;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.StarRating;
import androidx.media3.common.ThumbRating;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SessionCommands;
import androidx.media3.session.legacy.AudioAttributesCompat;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.RatingCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class LegacyConversions {
    public static final ImmutableSet<String> KNOWN_METADATA_COMPAT_KEYS = ImmutableSet.of("android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.DURATION", "android.media.metadata.ALBUM", "android.media.metadata.AUTHOR", "android.media.metadata.WRITER", "android.media.metadata.COMPOSER", "android.media.metadata.COMPILATION", "android.media.metadata.DATE", "android.media.metadata.YEAR", "android.media.metadata.GENRE", "android.media.metadata.TRACK_NUMBER", "android.media.metadata.NUM_TRACKS", "android.media.metadata.DISC_NUMBER", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.ART", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.USER_RATING", "android.media.metadata.RATING", "android.media.metadata.DISPLAY_TITLE", "android.media.metadata.DISPLAY_SUBTITLE", "android.media.metadata.DISPLAY_DESCRIPTION", "android.media.metadata.DISPLAY_ICON", "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.MEDIA_ID", "android.media.metadata.MEDIA_URI", "android.media.metadata.BT_FOLDER_TYPE", "android.media.metadata.ADVERTISEMENT", "android.media.metadata.DOWNLOAD_STATUS", MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT);
    private static final String TAG = "LegacyConversions";
    public static final MediaBrowserServiceCompat.BrowserRoot defaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot(MediaLibraryService.SERVICE_INTERFACE, (Bundle) null);

    private static int convertToFolderType(long j) {
        if (j == 0) {
            return 0;
        }
        if (j == 1) {
            return 1;
        }
        if (j == 2) {
            return 2;
        }
        if (j == 3) {
            return 3;
        }
        if (j == 4) {
            return 4;
        }
        if (j == 5) {
            return 5;
        }
        return j == 6 ? 6 : 0;
    }

    public static int convertToLegacyErrorCode(int i) {
        if (i == -110) {
            return 8;
        }
        if (i == -109) {
            return 11;
        }
        if (i == -6) {
            return 2;
        }
        if (i == -2) {
            return 1;
        }
        if (i == 1) {
            return 10;
        }
        switch (i) {
            case -107:
                return 9;
            case -106:
                return 7;
            case -105:
                return 6;
            case -104:
                return 5;
            case -103:
                return 4;
            case -102:
                return 3;
            default:
                return 0;
        }
    }

    public static int convertToPlaybackStateCompatShuffleMode(boolean z) {
        return z ? 1 : 0;
    }

    public static long convertToQueueItemId(int i) {
        if (i == -1) {
            return -1;
        }
        return (long) i;
    }

    private static int convertToSessionErrorCode(int i) {
        switch (i) {
            case 1:
                return -2;
            case 2:
                return -6;
            case 3:
                return -102;
            case 4:
                return -103;
            case 5:
                return -104;
            case 6:
                return -105;
            case 7:
                return -106;
            case 8:
                return -110;
            case 9:
                return -107;
            case 10:
                return 1;
            case 11:
                return -109;
            default:
                return -1;
        }
    }

    private static boolean hasAction(long j, long j2) {
        return (j & j2) != 0;
    }

    public static class ConversionException extends Exception {
        private ConversionException(String str) {
            super(str);
        }
    }

    public static PlaybackException convertToPlaybackException(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null || playbackStateCompat.getState() != 7) {
            return null;
        }
        CharSequence errorMessage = playbackStateCompat.getErrorMessage();
        Bundle extras = playbackStateCompat.getExtras();
        String obj = errorMessage != null ? errorMessage.toString() : null;
        int convertToPlaybackExceptionErrorCode = convertToPlaybackExceptionErrorCode(playbackStateCompat.getErrorCode());
        if (extras == null) {
            extras = Bundle.EMPTY;
        }
        return new PlaybackException(obj, (Throwable) null, convertToPlaybackExceptionErrorCode, extras);
    }

    public static SessionError convertToSessionError(PlaybackStateCompat playbackStateCompat, Context context) {
        if (playbackStateCompat == null) {
            return null;
        }
        return convertToSessionError(playbackStateCompat.getState(), playbackStateCompat.getErrorCode(), playbackStateCompat.getErrorMessage(), playbackStateCompat.getExtras(), context);
    }

    static SessionError convertToSessionError(int i, int i2, CharSequence charSequence, Bundle bundle, Context context) {
        String str;
        if (i == 7 || i2 == 0) {
            return null;
        }
        int convertToSessionErrorCode = convertToSessionErrorCode(i2);
        if (charSequence != null) {
            str = charSequence.toString();
        } else {
            str = getSessionErrorMessage(convertToSessionErrorCode, context);
        }
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        return new SessionError(convertToSessionErrorCode, str, bundle);
    }

    private static String getSessionErrorMessage(int i, Context context) {
        if (i == -100) {
            return context.getString(R.string.error_message_disconnected);
        }
        if (i == 1) {
            return context.getString(R.string.error_message_info_cancelled);
        }
        if (i == -6) {
            return context.getString(R.string.error_message_not_supported);
        }
        if (i == -5) {
            return context.getString(R.string.error_message_io);
        }
        if (i == -4) {
            return context.getString(R.string.error_message_permission_denied);
        }
        if (i == -3) {
            return context.getString(R.string.error_message_bad_value);
        }
        if (i == -2) {
            return context.getString(R.string.error_message_invalid_state);
        }
        switch (i) {
            case -110:
                return context.getString(R.string.error_message_content_already_playing);
            case -109:
                return context.getString(R.string.error_message_end_of_playlist);
            case -108:
                return context.getString(R.string.error_message_setup_required);
            case -107:
                return context.getString(R.string.error_message_skip_limit_reached);
            case -106:
                return context.getString(R.string.error_message_not_available_in_region);
            case -105:
                return context.getString(R.string.error_message_parental_control_restricted);
            case -104:
                return context.getString(R.string.error_message_concurrent_stream_limit);
            case -103:
                return context.getString(R.string.error_message_premium_account_required);
            case -102:
                return context.getString(R.string.error_message_authentication_expired);
            default:
                return context.getString(R.string.error_message_fallback);
        }
    }

    private static int convertToPlaybackExceptionErrorCode(int i) {
        int convertToSessionErrorCode = convertToSessionErrorCode(i);
        if (convertToSessionErrorCode == -5) {
            return 2000;
        }
        if (convertToSessionErrorCode != -1) {
            return convertToSessionErrorCode;
        }
        return 1000;
    }

    public static int convertToLegacyErrorCode(PlaybackException playbackException) {
        return convertToLegacyErrorCode(playbackException.errorCode);
    }

    public static MediaBrowserCompat.MediaItem convertToBrowserItem(MediaItem mediaItem, Bitmap bitmap) {
        MediaDescriptionCompat convertToMediaDescriptionCompat = convertToMediaDescriptionCompat(mediaItem, bitmap);
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        int i = (mediaMetadata.isBrowsable == null || !mediaMetadata.isBrowsable.booleanValue()) ? 0 : 1;
        if (mediaMetadata.isPlayable != null && mediaMetadata.isPlayable.booleanValue()) {
            i |= 2;
        }
        return new MediaBrowserCompat.MediaItem(convertToMediaDescriptionCompat, i);
    }

    public static MediaItem convertToMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        return convertToMediaItem(mediaItem.getDescription(), mediaItem.isBrowsable(), mediaItem.isPlayable());
    }

    public static MediaItem convertToMediaItem(MediaSessionCompat.QueueItem queueItem) {
        return convertToMediaItem(queueItem.getDescription());
    }

    public static MediaItem convertToMediaItem(MediaDescriptionCompat mediaDescriptionCompat) {
        Assertions.checkNotNull(mediaDescriptionCompat);
        return convertToMediaItem(mediaDescriptionCompat, false, true);
    }

    public static MediaItem convertToMediaItem(MediaMetadataCompat mediaMetadataCompat, int i) {
        return convertToMediaItem(mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID"), mediaMetadataCompat, i);
    }

    public static MediaItem convertToMediaItem(String str, MediaMetadataCompat mediaMetadataCompat, int i) {
        MediaItem.Builder builder = new MediaItem.Builder();
        if (str != null) {
            builder.setMediaId(str);
        }
        String string = mediaMetadataCompat.getString("android.media.metadata.MEDIA_URI");
        if (string != null) {
            builder.setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(Uri.parse(string)).build());
        }
        builder.setMediaMetadata(convertToMediaMetadata(mediaMetadataCompat, i));
        return builder.build();
    }

    private static MediaItem convertToMediaItem(MediaDescriptionCompat mediaDescriptionCompat, boolean z, boolean z2) {
        String mediaId = mediaDescriptionCompat.getMediaId();
        MediaItem.Builder builder = new MediaItem.Builder();
        if (mediaId == null) {
            mediaId = "";
        }
        return builder.setMediaId(mediaId).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(mediaDescriptionCompat.getMediaUri()).build()).setMediaMetadata(convertToMediaMetadata(mediaDescriptionCompat, 0, z, z2)).build();
    }

    public static ImmutableList<MediaItem> convertBrowserItemListToMediaItemList(List<MediaBrowserCompat.MediaItem> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            builder.add((Object) convertToMediaItem(list.get(i)));
        }
        return builder.build();
    }

    public static List<MediaItem> convertToMediaItemList(Timeline timeline) {
        ArrayList arrayList = new ArrayList();
        Timeline.Window window = new Timeline.Window();
        for (int i = 0; i < timeline.getWindowCount(); i++) {
            arrayList.add(timeline.getWindow(i, window).mediaItem);
        }
        return arrayList;
    }

    public static MediaSessionCompat.QueueItem convertToQueueItem(MediaItem mediaItem, int i, Bitmap bitmap) {
        return new MediaSessionCompat.QueueItem(convertToMediaDescriptionCompat(mediaItem, bitmap), convertToQueueItemId(i));
    }

    public static Timeline.Window convertToWindow(MediaItem mediaItem, int i) {
        Timeline.Window window = r21;
        Timeline.Window window2 = new Timeline.Window();
        window.set(0, mediaItem, (Object) null, 0, 0, 0, true, false, (MediaItem.LiveConfiguration) null, 0, C.TIME_UNSET, i, i, 0);
        return window2;
    }

    public static Timeline.Period convertToPeriod(int i) {
        Timeline.Period period = new Timeline.Period();
        period.set((Object) null, (Object) null, i, C.TIME_UNSET, 0, AdPlaybackState.NONE, true);
        return period;
    }

    public static MediaMetadata convertToMediaMetadata(CharSequence charSequence) {
        if (charSequence == null) {
            return MediaMetadata.EMPTY;
        }
        return new MediaMetadata.Builder().setTitle(charSequence).build();
    }

    public static MediaMetadata convertToMediaMetadata(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        return convertToMediaMetadata(mediaDescriptionCompat, i, false, true);
    }

    private static MediaMetadata convertToMediaMetadata(MediaDescriptionCompat mediaDescriptionCompat, int i, boolean z, boolean z2) {
        byte[] bArr;
        if (mediaDescriptionCompat == null) {
            return MediaMetadata.EMPTY;
        }
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        builder.setSubtitle(mediaDescriptionCompat.getSubtitle()).setDescription(mediaDescriptionCompat.getDescription()).setArtworkUri(mediaDescriptionCompat.getIconUri()).setUserRating(convertToRating(RatingCompat.newUnratedRating(i)));
        Bitmap iconBitmap = mediaDescriptionCompat.getIconBitmap();
        Bundle bundle = null;
        if (iconBitmap != null) {
            try {
                bArr = convertToByteArray(iconBitmap);
            } catch (IOException e) {
                Log.w(TAG, "Failed to convert iconBitmap to artworkData", e);
                bArr = null;
            }
            builder.setArtworkData(bArr, 3);
        }
        Bundle extras = mediaDescriptionCompat.getExtras();
        if (extras != null) {
            bundle = new Bundle(extras);
        }
        if (bundle != null && bundle.containsKey("android.media.extra.BT_FOLDER_TYPE")) {
            builder.setFolderType(Integer.valueOf(convertToFolderType(bundle.getLong("android.media.extra.BT_FOLDER_TYPE"))));
            bundle.remove("android.media.extra.BT_FOLDER_TYPE");
        }
        builder.setIsBrowsable(Boolean.valueOf(z));
        if (bundle != null && bundle.containsKey(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)) {
            builder.setMediaType(Integer.valueOf((int) bundle.getLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)));
            bundle.remove(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT);
        }
        if (bundle != null && bundle.containsKey("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST")) {
            builder.setSupportedCommands(ImmutableList.copyOf((Collection) Assertions.checkNotNull(bundle.getStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST"))));
        }
        if (bundle == null || !bundle.containsKey("androidx.media3.mediadescriptioncompat.title")) {
            builder.setTitle(mediaDescriptionCompat.getTitle());
        } else {
            builder.setTitle(bundle.getCharSequence("androidx.media3.mediadescriptioncompat.title"));
            builder.setDisplayTitle(mediaDescriptionCompat.getTitle());
            bundle.remove("androidx.media3.mediadescriptioncompat.title");
        }
        if (bundle != null && !bundle.isEmpty()) {
            builder.setExtras(bundle);
        }
        builder.setIsPlayable(Boolean.valueOf(z2));
        return builder.build();
    }

    public static MediaMetadata convertToMediaMetadata(MediaMetadataCompat mediaMetadataCompat, int i) {
        if (mediaMetadataCompat == null) {
            return MediaMetadata.EMPTY;
        }
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        CharSequence text = mediaMetadataCompat.getText("android.media.metadata.TITLE");
        CharSequence text2 = mediaMetadataCompat.getText("android.media.metadata.DISPLAY_TITLE");
        MediaMetadata.Builder title = builder.setTitle(text != null ? text : text2);
        if (text == null) {
            text2 = null;
        }
        title.setDisplayTitle(text2).setSubtitle(mediaMetadataCompat.getText("android.media.metadata.DISPLAY_SUBTITLE")).setDescription(mediaMetadataCompat.getText("android.media.metadata.DISPLAY_DESCRIPTION")).setArtist(mediaMetadataCompat.getText("android.media.metadata.ARTIST")).setAlbumTitle(mediaMetadataCompat.getText("android.media.metadata.ALBUM")).setAlbumArtist(mediaMetadataCompat.getText("android.media.metadata.ALBUM_ARTIST")).setOverallRating(convertToRating(mediaMetadataCompat.getRating("android.media.metadata.RATING")));
        if (mediaMetadataCompat.containsKey("android.media.metadata.DURATION")) {
            long j = mediaMetadataCompat.getLong("android.media.metadata.DURATION");
            if (j >= 0) {
                builder.setDurationMs(Long.valueOf(j));
            }
        }
        Rating convertToRating = convertToRating(mediaMetadataCompat.getRating("android.media.metadata.USER_RATING"));
        if (convertToRating != null) {
            builder.setUserRating(convertToRating);
        } else {
            builder.setUserRating(convertToRating(RatingCompat.newUnratedRating(i)));
        }
        if (mediaMetadataCompat.containsKey("android.media.metadata.YEAR")) {
            builder.setRecordingYear(Integer.valueOf((int) mediaMetadataCompat.getLong("android.media.metadata.YEAR")));
        }
        String firstString = getFirstString(mediaMetadataCompat, "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.ART_URI");
        if (firstString != null) {
            builder.setArtworkUri(Uri.parse(firstString));
        }
        Bitmap firstBitmap = getFirstBitmap(mediaMetadataCompat, "android.media.metadata.DISPLAY_ICON", "android.media.metadata.ALBUM_ART", "android.media.metadata.ART");
        if (firstBitmap != null) {
            try {
                builder.setArtworkData(convertToByteArray(firstBitmap), 3);
            } catch (IOException e) {
                Log.w(TAG, "Failed to convert artworkBitmap to artworkData", e);
            }
        }
        boolean containsKey = mediaMetadataCompat.containsKey("android.media.metadata.BT_FOLDER_TYPE");
        builder.setIsBrowsable(Boolean.valueOf(containsKey));
        if (containsKey) {
            builder.setFolderType(Integer.valueOf(convertToFolderType(mediaMetadataCompat.getLong("android.media.metadata.BT_FOLDER_TYPE"))));
        }
        if (mediaMetadataCompat.containsKey(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)) {
            builder.setMediaType(Integer.valueOf((int) mediaMetadataCompat.getLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)));
        }
        builder.setIsPlayable(true);
        Bundle bundle = mediaMetadataCompat.getBundle();
        UnmodifiableIterator<String> it = KNOWN_METADATA_COMPAT_KEYS.iterator();
        while (it.hasNext()) {
            bundle.remove(it.next());
        }
        if (!bundle.isEmpty()) {
            builder.setExtras(bundle);
        }
        return builder.build();
    }

    private static Bitmap getFirstBitmap(MediaMetadataCompat mediaMetadataCompat, String... strArr) {
        for (String str : strArr) {
            if (mediaMetadataCompat.containsKey(str)) {
                return mediaMetadataCompat.getBitmap(str);
            }
        }
        return null;
    }

    private static String getFirstString(MediaMetadataCompat mediaMetadataCompat, String... strArr) {
        for (String str : strArr) {
            if (mediaMetadataCompat.containsKey(str)) {
                return mediaMetadataCompat.getString(str);
            }
        }
        return null;
    }

    public static MediaMetadataCompat convertToMediaMetadataCompat(MediaMetadata mediaMetadata, String str, Uri uri, long j, Bitmap bitmap) {
        MediaMetadataCompat.Builder putString = new MediaMetadataCompat.Builder().putString("android.media.metadata.MEDIA_ID", str);
        if (mediaMetadata.title != null) {
            putString.putText("android.media.metadata.TITLE", mediaMetadata.title);
        }
        if (mediaMetadata.displayTitle != null) {
            putString.putText("android.media.metadata.DISPLAY_TITLE", mediaMetadata.displayTitle);
        }
        if (mediaMetadata.subtitle != null) {
            putString.putText("android.media.metadata.DISPLAY_SUBTITLE", mediaMetadata.subtitle);
        }
        if (mediaMetadata.description != null) {
            putString.putText("android.media.metadata.DISPLAY_DESCRIPTION", mediaMetadata.description);
        }
        if (mediaMetadata.artist != null) {
            putString.putText("android.media.metadata.ARTIST", mediaMetadata.artist);
        }
        if (mediaMetadata.albumTitle != null) {
            putString.putText("android.media.metadata.ALBUM", mediaMetadata.albumTitle);
        }
        if (mediaMetadata.albumArtist != null) {
            putString.putText("android.media.metadata.ALBUM_ARTIST", mediaMetadata.albumArtist);
        }
        if (mediaMetadata.recordingYear != null) {
            putString.putLong("android.media.metadata.YEAR", (long) mediaMetadata.recordingYear.intValue());
        }
        if (uri != null) {
            putString.putString("android.media.metadata.MEDIA_URI", uri.toString());
        }
        if (mediaMetadata.artworkUri != null) {
            putString.putString("android.media.metadata.DISPLAY_ICON_URI", mediaMetadata.artworkUri.toString());
            putString.putString("android.media.metadata.ALBUM_ART_URI", mediaMetadata.artworkUri.toString());
            putString.putString("android.media.metadata.ART_URI", mediaMetadata.artworkUri.toString());
        }
        if (bitmap != null) {
            putString.putBitmap("android.media.metadata.DISPLAY_ICON", bitmap);
            putString.putBitmap("android.media.metadata.ALBUM_ART", bitmap);
        }
        if (!(mediaMetadata.folderType == null || mediaMetadata.folderType.intValue() == -1)) {
            putString.putLong("android.media.metadata.BT_FOLDER_TYPE", convertToExtraBtFolderType(mediaMetadata.folderType.intValue()));
        }
        if (j == C.TIME_UNSET && mediaMetadata.durationMs != null) {
            j = mediaMetadata.durationMs.longValue();
        }
        if (j != C.TIME_UNSET) {
            putString.putLong("android.media.metadata.DURATION", j);
        }
        RatingCompat convertToRatingCompat = convertToRatingCompat(mediaMetadata.userRating);
        if (convertToRatingCompat != null) {
            putString.putRating("android.media.metadata.USER_RATING", convertToRatingCompat);
        }
        RatingCompat convertToRatingCompat2 = convertToRatingCompat(mediaMetadata.overallRating);
        if (convertToRatingCompat2 != null) {
            putString.putRating("android.media.metadata.RATING", convertToRatingCompat2);
        }
        if (mediaMetadata.mediaType != null) {
            putString.putLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT, (long) mediaMetadata.mediaType.intValue());
        }
        if (mediaMetadata.extras != null) {
            for (String str2 : mediaMetadata.extras.keySet()) {
                Object obj = mediaMetadata.extras.get(str2);
                if (obj == null || (obj instanceof CharSequence)) {
                    putString.putText(str2, (CharSequence) obj);
                } else if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                    putString.putLong(str2, ((Number) obj).longValue());
                }
            }
        }
        return putString.build();
    }

    public static MediaDescriptionCompat convertToMediaDescriptionCompat(MediaItem mediaItem, Bitmap bitmap) {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        MediaDescriptionCompat.Builder mediaId = new MediaDescriptionCompat.Builder().setMediaId(mediaItem.mediaId.equals("") ? null : mediaItem.mediaId);
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        if (bitmap != null) {
            mediaId.setIconBitmap(bitmap);
        }
        Bundle bundle = mediaMetadata.extras;
        if (bundle != null) {
            bundle = new Bundle(bundle);
        }
        boolean z = (mediaMetadata.folderType == null || mediaMetadata.folderType.intValue() == -1) ? false : true;
        boolean z2 = mediaMetadata.mediaType != null;
        if (z || z2) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (z) {
                bundle.putLong("android.media.extra.BT_FOLDER_TYPE", convertToExtraBtFolderType(((Integer) Assertions.checkNotNull(mediaMetadata.folderType)).intValue()));
            }
            if (z2) {
                bundle.putLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT, (long) ((Integer) Assertions.checkNotNull(mediaMetadata.mediaType)).intValue());
            }
        }
        if (!mediaMetadata.supportedCommands.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST", new ArrayList(mediaMetadata.supportedCommands));
        }
        if (mediaMetadata.displayTitle != null) {
            charSequence3 = mediaMetadata.displayTitle;
            charSequence2 = mediaMetadata.subtitle;
            charSequence = mediaMetadata.description;
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putCharSequence("androidx.media3.mediadescriptioncompat.title", mediaMetadata.title);
        } else {
            CharSequence[] charSequenceArr = new CharSequence[3];
            int i = 0;
            int i2 = 0;
            while (i < 3 && i2 < MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER.length) {
                int i3 = i2 + 1;
                CharSequence text = getText(MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER[i2], mediaMetadata);
                if (!TextUtils.isEmpty(text)) {
                    charSequenceArr[i] = text;
                    i++;
                }
                i2 = i3;
            }
            charSequence3 = charSequenceArr[0];
            charSequence2 = charSequenceArr[1];
            charSequence = charSequenceArr[2];
        }
        return mediaId.setTitle(charSequence3).setSubtitle(charSequence2).setDescription(charSequence).setIconUri(mediaMetadata.artworkUri).setMediaUri(mediaItem.requestMetadata.mediaUri).setExtras(bundle).build();
    }

    private static CharSequence getText(String str, MediaMetadata mediaMetadata) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1853648227:
                if (str.equals("android.media.metadata.ARTIST")) {
                    c = 0;
                    break;
                }
                break;
            case -1224124471:
                if (str.equals("android.media.metadata.WRITER")) {
                    c = 1;
                    break;
                }
                break;
            case 1684534006:
                if (str.equals("android.media.metadata.COMPOSER")) {
                    c = 2;
                    break;
                }
                break;
            case 1879671865:
                if (str.equals("android.media.metadata.ALBUM")) {
                    c = 3;
                    break;
                }
                break;
            case 1897146402:
                if (str.equals("android.media.metadata.TITLE")) {
                    c = 4;
                    break;
                }
                break;
            case 1965214221:
                if (str.equals("android.media.metadata.ALBUM_ARTIST")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return mediaMetadata.artist;
            case 1:
                return mediaMetadata.writer;
            case 2:
                return mediaMetadata.composer;
            case 3:
                return mediaMetadata.albumTitle;
            case 4:
                return mediaMetadata.title;
            case 5:
                return mediaMetadata.albumArtist;
            default:
                return null;
        }
    }

    private static long convertToExtraBtFolderType(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            default:
                throw new IllegalArgumentException("Unrecognized FolderType: " + i);
        }
    }

    public static Rating convertToRating(RatingCompat ratingCompat) {
        if (ratingCompat == null) {
            return null;
        }
        switch (ratingCompat.getRatingStyle()) {
            case 1:
                if (ratingCompat.isRated()) {
                    return new HeartRating(ratingCompat.hasHeart());
                }
                return new HeartRating();
            case 2:
                if (ratingCompat.isRated()) {
                    return new ThumbRating(ratingCompat.isThumbUp());
                }
                return new ThumbRating();
            case 3:
                if (ratingCompat.isRated()) {
                    return new StarRating(3, ratingCompat.getStarRating());
                }
                return new StarRating(3);
            case 4:
                if (ratingCompat.isRated()) {
                    return new StarRating(4, ratingCompat.getStarRating());
                }
                return new StarRating(4);
            case 5:
                if (ratingCompat.isRated()) {
                    return new StarRating(5, ratingCompat.getStarRating());
                }
                return new StarRating(5);
            case 6:
                if (ratingCompat.isRated()) {
                    return new PercentageRating(ratingCompat.getPercentRating());
                }
                return new PercentageRating();
            default:
                return null;
        }
    }

    public static RatingCompat convertToRatingCompat(Rating rating) {
        if (rating == null) {
            return null;
        }
        int ratingCompatStyle = getRatingCompatStyle(rating);
        if (!rating.isRated()) {
            return RatingCompat.newUnratedRating(ratingCompatStyle);
        }
        switch (ratingCompatStyle) {
            case 1:
                return RatingCompat.newHeartRating(((HeartRating) rating).isHeart());
            case 2:
                return RatingCompat.newThumbRating(((ThumbRating) rating).isThumbsUp());
            case 3:
            case 4:
            case 5:
                return RatingCompat.newStarRating(ratingCompatStyle, ((StarRating) rating).getStarRating());
            case 6:
                return RatingCompat.newPercentageRating(((PercentageRating) rating).getPercent());
            default:
                return null;
        }
    }

    public static int convertToPlaybackStateCompatState(Player player, boolean z) {
        if (player.getPlayerError() != null) {
            return 7;
        }
        int playbackState = player.getPlaybackState();
        boolean shouldShowPlayButton = Util.shouldShowPlayButton(player, z);
        if (playbackState == 1) {
            return 0;
        }
        if (playbackState == 2) {
            return shouldShowPlayButton ? 2 : 6;
        }
        if (playbackState == 3) {
            return shouldShowPlayButton ? 2 : 3;
        }
        if (playbackState == 4) {
            return 1;
        }
        throw new IllegalArgumentException("Unrecognized State: " + playbackState);
    }

    public static PlaybackParameters convertToPlaybackParameters(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return PlaybackParameters.DEFAULT;
        }
        return new PlaybackParameters(playbackStateCompat.getPlaybackSpeed());
    }

    public static boolean convertToPlayWhenReady(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return false;
        }
        switch (playbackStateCompat.getState()) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    public static int convertToPlaybackState(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j) throws ConversionException {
        if (playbackStateCompat == null) {
            return 1;
        }
        switch (playbackStateCompat.getState()) {
            case 0:
            case 1:
            case 7:
            case 8:
                return 1;
            case 2:
                long convertToDurationMs = convertToDurationMs(mediaMetadataCompat);
                if (convertToDurationMs != C.TIME_UNSET && convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j) >= convertToDurationMs) {
                    return 4;
                }
                return 3;
            case 3:
                return 3;
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
                return 2;
            default:
                throw new ConversionException("Invalid state of PlaybackStateCompat: " + playbackStateCompat.getState());
        }
    }

    public static boolean convertToIsPlaying(PlaybackStateCompat playbackStateCompat) {
        return playbackStateCompat != null && playbackStateCompat.getState() == 3;
    }

    public static boolean convertToIsPlayingAd(MediaMetadataCompat mediaMetadataCompat) {
        return (mediaMetadataCompat == null || mediaMetadataCompat.getLong("android.media.metadata.ADVERTISEMENT") == 0) ? false : true;
    }

    public static long convertToCurrentPositionMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j) {
        long j2;
        if (playbackStateCompat == null) {
            return 0;
        }
        if (playbackStateCompat.getState() == 3) {
            j2 = getCurrentPosition(playbackStateCompat, j);
        } else {
            j2 = playbackStateCompat.getPosition();
        }
        long j3 = j2;
        long convertToDurationMs = convertToDurationMs(mediaMetadataCompat);
        if (convertToDurationMs == C.TIME_UNSET) {
            return Math.max(0, j3);
        }
        return Util.constrainValue(j3, 0, convertToDurationMs);
    }

    private static long getCurrentPosition(PlaybackStateCompat playbackStateCompat, long j) {
        return playbackStateCompat.getCurrentPosition(j == C.TIME_UNSET ? null : Long.valueOf(j));
    }

    public static long convertToDurationMs(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null || !mediaMetadataCompat.containsKey("android.media.metadata.DURATION")) {
            return C.TIME_UNSET;
        }
        long j = mediaMetadataCompat.getLong("android.media.metadata.DURATION");
        if (j <= 0) {
            return C.TIME_UNSET;
        }
        return j;
    }

    public static long convertToBufferedPositionMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j) {
        long bufferedPosition = playbackStateCompat == null ? 0 : playbackStateCompat.getBufferedPosition();
        long convertToCurrentPositionMs = convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j);
        long convertToDurationMs = convertToDurationMs(mediaMetadataCompat);
        if (convertToDurationMs == C.TIME_UNSET) {
            return Math.max(convertToCurrentPositionMs, bufferedPosition);
        }
        return Util.constrainValue(bufferedPosition, convertToCurrentPositionMs, convertToDurationMs);
    }

    public static long convertToTotalBufferedDurationMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j) {
        return convertToBufferedPositionMs(playbackStateCompat, mediaMetadataCompat, j) - convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j);
    }

    public static int convertToBufferedPercentage(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j) {
        return MediaUtils.calculateBufferedPercentage(convertToBufferedPositionMs(playbackStateCompat, mediaMetadataCompat, j), convertToDurationMs(mediaMetadataCompat));
    }

    public static int getRatingCompatStyle(Rating rating) {
        if (rating instanceof HeartRating) {
            return 1;
        }
        if (rating instanceof ThumbRating) {
            return 2;
        }
        if (!(rating instanceof StarRating)) {
            return rating instanceof PercentageRating ? 6 : 0;
        }
        int maxStars = ((StarRating) rating).getMaxStars();
        int i = 3;
        if (maxStars != 3) {
            i = 4;
            if (maxStars != 4) {
                i = 5;
                if (maxStars != 5) {
                    return 0;
                }
            }
        }
        return i;
    }

    public static int convertToRepeatMode(int i) {
        if (i == -1 || i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (!(i == 2 || i == 3)) {
                Log.w(TAG, "Unrecognized PlaybackStateCompat.RepeatMode: " + i + " was converted to `Player.REPEAT_MODE_OFF`");
                return 0;
            }
        }
        return i2;
    }

    public static int convertToPlaybackStateCompatRepeatMode(int i) {
        if (i == 0) {
            return 0;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                Log.w(TAG, "Unrecognized RepeatMode: " + i + " was converted to `PlaybackStateCompat.REPEAT_MODE_NONE`");
                return 0;
            }
        }
        return i2;
    }

    public static boolean convertToShuffleModeEnabled(int i) {
        if (i == -1 || i == 0) {
            return false;
        }
        if (i == 1 || i == 2) {
            return true;
        }
        throw new IllegalArgumentException("Unrecognized ShuffleMode: " + i);
    }

    public static MediaLibraryService.LibraryParams convertToLibraryParams(Context context, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        try {
            bundle.setClassLoader(context.getClassLoader());
            int i = bundle.getInt("androidx.media.MediaBrowserCompat.Extras.KEY_ROOT_CHILDREN_SUPPORTED_FLAGS", -1);
            if (i >= 0) {
                bundle.remove("androidx.media.MediaBrowserCompat.Extras.KEY_ROOT_CHILDREN_SUPPORTED_FLAGS");
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                bundle.putBoolean(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY, z);
            }
            return new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).setRecent(bundle.getBoolean("android.service.media.extra.RECENT")).setOffline(bundle.getBoolean("android.service.media.extra.OFFLINE")).setSuggested(bundle.getBoolean("android.service.media.extra.SUGGESTED")).build();
        } catch (Exception unused) {
            return new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build();
        }
    }

    public static Bundle convertToRootHints(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams == null) {
            return null;
        }
        Bundle bundle = new Bundle(libraryParams.extras);
        if (libraryParams.extras.containsKey(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY)) {
            boolean z = libraryParams.extras.getBoolean(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY, false);
            bundle.remove(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY);
            bundle.putInt("androidx.media.MediaBrowserCompat.Extras.KEY_ROOT_CHILDREN_SUPPORTED_FLAGS", z ? 1 : 3);
        }
        bundle.putBoolean("android.service.media.extra.RECENT", libraryParams.isRecent);
        bundle.putBoolean("android.service.media.extra.OFFLINE", libraryParams.isOffline);
        bundle.putBoolean("android.service.media.extra.SUGGESTED", libraryParams.isSuggested);
        return bundle;
    }

    public static Player.Commands convertToPlayerCommands(PlaybackStateCompat playbackStateCompat, int i, long j, boolean z) {
        long j2;
        int i2 = i;
        Player.Commands.Builder builder = new Player.Commands.Builder();
        if (playbackStateCompat == null) {
            j2 = 0;
        } else {
            j2 = playbackStateCompat.getActions();
        }
        if ((hasAction(j2, 4) && hasAction(j2, 2)) || hasAction(j2, 512)) {
            builder.add(1);
        }
        if (hasAction(j2, 16384)) {
            builder.add(2);
        }
        if ((hasAction(j2, 32768) && hasAction(j2, 1024)) || ((hasAction(j2, 65536) && hasAction(j2, 2048)) || (hasAction(j2, 131072) && hasAction(j2, 8192)))) {
            builder.addAll(31, 2);
        }
        if (hasAction(j2, 8)) {
            builder.add(11);
        }
        if (hasAction(j2, 64)) {
            builder.add(12);
        }
        if (hasAction(j2, 256)) {
            builder.addAll(5, 4);
        }
        if (hasAction(j2, 32)) {
            builder.addAll(9, 8);
        }
        if (hasAction(j2, 16)) {
            builder.addAll(7, 6);
        }
        if (hasAction(j2, 4194304)) {
            builder.add(13);
        }
        if (hasAction(j2, 1)) {
            builder.add(3);
        }
        if (i2 == 1) {
            builder.addAll(26, 34);
        } else if (i2 == 2) {
            builder.addAll(26, 34, 25, 33);
        }
        builder.addAll(23, 17, 18, 16, 21, 32);
        if ((j & 4) != 0) {
            builder.add(20);
            if (hasAction(j2, 4096)) {
                builder.add(10);
            }
        }
        if (z) {
            if (hasAction(j2, 262144)) {
                builder.add(15);
            }
            if (hasAction(j2, 2097152)) {
                builder.add(14);
            }
        }
        return builder.build();
    }

    public static SessionCommands convertToSessionCommands(PlaybackStateCompat playbackStateCompat, boolean z) {
        List<PlaybackStateCompat.CustomAction> customActions;
        SessionCommands.Builder builder = new SessionCommands.Builder();
        builder.addAllSessionCommands();
        if (!z) {
            builder.remove((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING);
        }
        if (!(playbackStateCompat == null || (customActions = playbackStateCompat.getCustomActions()) == null)) {
            for (PlaybackStateCompat.CustomAction next : customActions) {
                String action = next.getAction();
                Bundle extras = next.getExtras();
                if (extras == null) {
                    extras = Bundle.EMPTY;
                }
                builder.add(new SessionCommand(action, extras));
            }
        }
        return builder.build();
    }

    public static ImmutableList<CommandButton> convertToMediaButtonPreferences(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return ImmutableList.of();
        }
        List<PlaybackStateCompat.CustomAction> customActions = playbackStateCompat.getCustomActions();
        if (customActions == null) {
            return ImmutableList.of();
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (PlaybackStateCompat.CustomAction next : customActions) {
            String action = next.getAction();
            Bundle extras = next.getExtras();
            int i = 0;
            if (extras != null) {
                i = extras.getInt(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_COMPAT, 0);
            }
            CommandButton.Builder builder2 = new CommandButton.Builder(i, next.getIcon());
            if (extras == null) {
                extras = Bundle.EMPTY;
            }
            builder.add((Object) builder2.setSessionCommand(new SessionCommand(action, extras)).setDisplayName(next.getName()).setEnabled(true).build());
        }
        return builder.build();
    }

    public static AudioAttributes convertToAudioAttributes(AudioAttributesCompat audioAttributesCompat) {
        if (audioAttributesCompat == null) {
            return AudioAttributes.DEFAULT;
        }
        return new AudioAttributes.Builder().setContentType(audioAttributesCompat.getContentType()).setFlags(audioAttributesCompat.getFlags()).setUsage(audioAttributesCompat.getUsage()).build();
    }

    public static AudioAttributes convertToAudioAttributes(MediaControllerCompat.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return AudioAttributes.DEFAULT;
        }
        return convertToAudioAttributes(playbackInfo.getAudioAttributes());
    }

    public static AudioAttributesCompat convertToAudioAttributesCompat(AudioAttributes audioAttributes) {
        return new AudioAttributesCompat.Builder().setContentType(audioAttributes.contentType).setFlags(audioAttributes.flags).setUsage(audioAttributes.usage).build();
    }

    public static int getLegacyStreamType(AudioAttributes audioAttributes) {
        int legacyStreamType = convertToAudioAttributesCompat(audioAttributes).getLegacyStreamType();
        if (legacyStreamType == Integer.MIN_VALUE) {
            return 3;
        }
        return legacyStreamType;
    }

    public static <T> T getFutureResult(Future<T> future, long j) throws ExecutionException, TimeoutException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j2 = j;
        while (true) {
            try {
                T t = future.get(j2, TimeUnit.MILLISECONDS);
                if (z) {
                    Thread.currentThread().interrupt();
                }
                return t;
            } catch (InterruptedException unused) {
                z = true;
                long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                if (elapsedRealtime2 < j) {
                    j2 = j - elapsedRealtime2;
                } else {
                    throw new TimeoutException();
                }
            } catch (Throwable th) {
                if (1 != 0) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
    }

    public static DeviceInfo convertToDeviceInfo(MediaControllerCompat.PlaybackInfo playbackInfo, String str) {
        if (playbackInfo == null) {
            return DeviceInfo.UNKNOWN;
        }
        return new DeviceInfo.Builder(playbackInfo.getPlaybackType() == 2 ? 1 : 0).setMaxVolume(playbackInfo.getMaxVolume()).setRoutingControllerId(str).build();
    }

    public static int convertToDeviceVolume(MediaControllerCompat.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return 0;
        }
        return playbackInfo.getCurrentVolume();
    }

    public static boolean convertToIsDeviceMuted(MediaControllerCompat.PlaybackInfo playbackInfo) {
        return playbackInfo != null && playbackInfo.getCurrentVolume() == 0;
    }

    public static CommandButton convertCustomBrowseActionToCommandButton(Bundle bundle) {
        String string = bundle.getString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ID");
        if (string == null) {
            return null;
        }
        CommandButton.Builder sessionCommand = new CommandButton.Builder().setSessionCommand(new SessionCommand(string, Bundle.EMPTY));
        String string2 = bundle.getString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_LABEL");
        if (string2 != null) {
            sessionCommand.setDisplayName(string2);
        }
        String string3 = bundle.getString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ICON_URI");
        if (string3 != null) {
            try {
                sessionCommand.setIconUri(Uri.parse(string3));
            } catch (Throwable th) {
                Log.e(TAG, "error parsing icon URI of legacy browser action " + string, th);
            }
        }
        Bundle bundle2 = bundle.getBundle("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_EXTRAS");
        if (bundle2 != null) {
            sessionCommand.setExtras(bundle2);
        }
        return sessionCommand.build();
    }

    public static Bundle convertToBundle(CommandButton commandButton) {
        Bundle bundle = new Bundle();
        if (commandButton.sessionCommand != null) {
            bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ID", commandButton.sessionCommand.customAction);
        }
        bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_LABEL", commandButton.displayName.toString());
        if (commandButton.iconUri != null) {
            bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_ICON_URI", commandButton.iconUri.toString());
        }
        if (!commandButton.extras.isEmpty()) {
            bundle.putBundle("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_EXTRAS", commandButton.extras);
        }
        return bundle;
    }

    public static int extractMaxCommandsForMediaItemFromRootHints(Bundle bundle) {
        return Math.max(0, bundle.getInt("androidx.media.utils.MediaBrowserCompat.extras.CUSTOM_BROWSER_ACTION_LIMIT", 0));
    }

    private static byte[] convertToByteArray(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private LegacyConversions() {
    }
}
