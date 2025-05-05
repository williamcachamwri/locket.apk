package androidx.media3.session;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import java.util.ArrayList;
import java.util.List;

final class MediaUtils {
    public static final long POSITION_DIFF_TOLERANCE_MS = 100;
    private static final String TAG = "MediaUtils";
    public static final int TRANSACTION_SIZE_LIMIT_IN_BYTES = 262144;
    public static final MediaBrowserServiceCompat.BrowserRoot defaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot(MediaLibraryService.SERVICE_INTERFACE, (Bundle) null);

    public static boolean areEqualError(PlaybackStateCompat playbackStateCompat, PlaybackStateCompat playbackStateCompat2) {
        boolean z = playbackStateCompat != null && playbackStateCompat.getState() == 7;
        boolean z2 = playbackStateCompat2 != null && playbackStateCompat2.getState() == 7;
        if (!z || !z2) {
            if (z == z2) {
                return true;
            }
            return false;
        } else if (((PlaybackStateCompat) Util.castNonNull(playbackStateCompat)).getErrorCode() != ((PlaybackStateCompat) Util.castNonNull(playbackStateCompat2)).getErrorCode() || !TextUtils.equals(((PlaybackStateCompat) Util.castNonNull(playbackStateCompat)).getErrorMessage(), ((PlaybackStateCompat) Util.castNonNull(playbackStateCompat2)).getErrorMessage())) {
            return false;
        } else {
            return true;
        }
    }

    public static <T extends Parcelable> List<T> truncateListBySize(List<T> list, int i) {
        ArrayList arrayList = new ArrayList();
        Parcel obtain = Parcel.obtain();
        int i2 = 0;
        while (i2 < list.size()) {
            try {
                Parcelable parcelable = (Parcelable) list.get(i2);
                obtain.writeParcelable(parcelable, 0);
                if (obtain.dataSize() >= i) {
                    break;
                }
                arrayList.add(parcelable);
                i2++;
            } finally {
                obtain.recycle();
            }
        }
        return arrayList;
    }

    public static <T> List<T> removeNullElements(List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T next : list) {
            if (next != null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static Player.Commands createPlayerCommandsWith(int i) {
        return new Player.Commands.Builder().add(i).build();
    }

    public static Player.Commands createPlayerCommandsWithout(int i) {
        return new Player.Commands.Builder().addAllCommands().remove(i).build();
    }

    public static Player.Commands intersect(Player.Commands commands, Player.Commands commands2) {
        if (commands == null || commands2 == null) {
            return Player.Commands.EMPTY;
        }
        Player.Commands.Builder builder = new Player.Commands.Builder();
        for (int i = 0; i < commands.size(); i++) {
            if (commands2.contains(commands.get(i))) {
                builder.add(commands.get(i));
            }
        }
        return builder.build();
    }

    public static Pair<PlayerInfo, PlayerInfo.BundlingExclusions> mergePlayerInfo(PlayerInfo playerInfo, PlayerInfo.BundlingExclusions bundlingExclusions, PlayerInfo playerInfo2, PlayerInfo.BundlingExclusions bundlingExclusions2, Player.Commands commands) {
        PlayerInfo.BundlingExclusions bundlingExclusions3;
        if (!bundlingExclusions2.isTimelineExcluded || !commands.contains(17) || bundlingExclusions.isTimelineExcluded) {
            bundlingExclusions3 = bundlingExclusions2;
        } else {
            playerInfo2 = playerInfo2.copyWithTimeline(playerInfo.timeline);
            bundlingExclusions3 = new PlayerInfo.BundlingExclusions(false, bundlingExclusions2.areCurrentTracksExcluded);
        }
        if (bundlingExclusions2.areCurrentTracksExcluded && commands.contains(30) && !bundlingExclusions.areCurrentTracksExcluded) {
            playerInfo2 = playerInfo2.copyWithCurrentTracks(playerInfo.currentTracks);
            bundlingExclusions3 = new PlayerInfo.BundlingExclusions(bundlingExclusions3.isTimelineExcluded, false);
        }
        return new Pair<>(playerInfo2, bundlingExclusions3);
    }

    public static int[] generateUnshuffledIndices(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        return iArr;
    }

    public static int calculateBufferedPercentage(long j, long j2) {
        if (j == C.TIME_UNSET || j2 == C.TIME_UNSET) {
            return 0;
        }
        if (j2 == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((j * 100) / j2), 0, 100);
    }

    public static void setMediaItemsWithStartIndexAndPosition(Player player, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (mediaItemsWithStartPosition.startIndex == -1) {
            if (player.isCommandAvailable(20)) {
                player.setMediaItems(mediaItemsWithStartPosition.mediaItems, true);
            } else if (!mediaItemsWithStartPosition.mediaItems.isEmpty()) {
                player.setMediaItem((MediaItem) mediaItemsWithStartPosition.mediaItems.get(0), true);
            }
        } else if (player.isCommandAvailable(20)) {
            player.setMediaItems(mediaItemsWithStartPosition.mediaItems, mediaItemsWithStartPosition.startIndex, mediaItemsWithStartPosition.startPositionMs);
        } else if (!mediaItemsWithStartPosition.mediaItems.isEmpty()) {
            player.setMediaItem((MediaItem) mediaItemsWithStartPosition.mediaItems.get(0), mediaItemsWithStartPosition.startPositionMs);
        }
    }

    public static boolean areSessionPositionInfosInSamePeriodOrAd(SessionPositionInfo sessionPositionInfo, SessionPositionInfo sessionPositionInfo2) {
        return sessionPositionInfo.positionInfo.mediaItemIndex == sessionPositionInfo2.positionInfo.mediaItemIndex && sessionPositionInfo.positionInfo.periodIndex == sessionPositionInfo2.positionInfo.periodIndex && sessionPositionInfo.positionInfo.adGroupIndex == sessionPositionInfo2.positionInfo.adGroupIndex && sessionPositionInfo.positionInfo.adIndexInAdGroup == sessionPositionInfo2.positionInfo.adIndexInAdGroup;
    }

    public static long getUpdatedCurrentPositionMs(PlayerInfo playerInfo, long j, long j2, long j3) {
        boolean z = playerInfo.sessionPositionInfo.equals(SessionPositionInfo.DEFAULT) || j2 < playerInfo.sessionPositionInfo.eventTimeMs;
        if (!playerInfo.isPlaying) {
            if (z || j == C.TIME_UNSET) {
                return playerInfo.sessionPositionInfo.positionInfo.positionMs;
            }
            return j;
        } else if (!z && j != C.TIME_UNSET) {
            return j;
        } else {
            if (j3 == C.TIME_UNSET) {
                j3 = SystemClock.elapsedRealtime() - playerInfo.sessionPositionInfo.eventTimeMs;
            }
            long j4 = playerInfo.sessionPositionInfo.positionInfo.positionMs + ((long) (((float) j3) * playerInfo.playbackParameters.speed));
            return playerInfo.sessionPositionInfo.durationMs != C.TIME_UNSET ? Math.min(j4, playerInfo.sessionPositionInfo.durationMs) : j4;
        }
    }

    private MediaUtils() {
    }
}
