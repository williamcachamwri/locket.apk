package androidx.media3.session;

import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.facebook.imageutils.JfifUtil;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

final class QueueTimeline extends Timeline {
    public static final QueueTimeline DEFAULT = new QueueTimeline(ImmutableList.of(), (QueuedMediaItem) null);
    private static final Object FAKE_WINDOW_UID = new Object();
    private final QueuedMediaItem fakeQueuedMediaItem;
    private final ImmutableList<QueuedMediaItem> queuedMediaItems;

    private QueueTimeline(ImmutableList<QueuedMediaItem> immutableList, QueuedMediaItem queuedMediaItem) {
        this.queuedMediaItems = immutableList;
        this.fakeQueuedMediaItem = queuedMediaItem;
    }

    public static QueueTimeline create(List<MediaSessionCompat.QueueItem> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            MediaSessionCompat.QueueItem queueItem = list.get(i);
            builder.add((Object) new QueuedMediaItem(LegacyConversions.convertToMediaItem(queueItem), queueItem.getQueueId(), C.TIME_UNSET));
        }
        return new QueueTimeline(builder.build(), (QueuedMediaItem) null);
    }

    public QueueTimeline copy() {
        return new QueueTimeline(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }

    public long getQueueId(int i) {
        if (i < 0 || i >= this.queuedMediaItems.size()) {
            return -1;
        }
        return ((QueuedMediaItem) this.queuedMediaItems.get(i)).queueId;
    }

    public QueueTimeline copyWithFakeMediaItem(MediaItem mediaItem, long j) {
        return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1, j));
    }

    public QueueTimeline copyWithClearedFakeMediaItem() {
        return new QueueTimeline(this.queuedMediaItems, (QueuedMediaItem) null);
    }

    public QueueTimeline copyWithNewMediaItem(int i, MediaItem mediaItem, long j) {
        Assertions.checkArgument(i < this.queuedMediaItems.size() || (i == this.queuedMediaItems.size() && this.fakeQueuedMediaItem != null));
        if (i == this.queuedMediaItems.size()) {
            return new QueueTimeline(this.queuedMediaItems, new QueuedMediaItem(mediaItem, -1, j));
        }
        long j2 = ((QueuedMediaItem) this.queuedMediaItems.get(i)).queueId;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        builder.add((Object) new QueuedMediaItem(mediaItem, j2, j));
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i + 1, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithNewMediaItems(int i, List<MediaItem> list) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        for (int i2 = 0; i2 < list.size(); i2++) {
            builder.add((Object) new QueuedMediaItem(list.get(i2), -1, C.TIME_UNSET));
        }
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithRemovedMediaItems(int i, int i2) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.addAll((Iterable) this.queuedMediaItems.subList(0, i));
        ImmutableList<QueuedMediaItem> immutableList = this.queuedMediaItems;
        builder.addAll((Iterable) immutableList.subList(i2, immutableList.size()));
        return new QueueTimeline(builder.build(), this.fakeQueuedMediaItem);
    }

    public QueueTimeline copyWithMovedMediaItems(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList(this.queuedMediaItems);
        Util.moveItems(arrayList, i, i2, i3);
        return new QueueTimeline(ImmutableList.copyOf(arrayList), this.fakeQueuedMediaItem);
    }

    public boolean contains(MediaItem mediaItem) {
        QueuedMediaItem queuedMediaItem = this.fakeQueuedMediaItem;
        if (queuedMediaItem != null && mediaItem.equals(queuedMediaItem.mediaItem)) {
            return true;
        }
        for (int i = 0; i < this.queuedMediaItems.size(); i++) {
            if (mediaItem.equals(((QueuedMediaItem) this.queuedMediaItems.get(i)).mediaItem)) {
                return true;
            }
        }
        return false;
    }

    public MediaItem getMediaItemAt(int i) {
        if (i >= getWindowCount()) {
            return null;
        }
        return getQueuedMediaItem(i).mediaItem;
    }

    public int getWindowCount() {
        return this.queuedMediaItems.size() + (this.fakeQueuedMediaItem == null ? 0 : 1);
    }

    public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i);
        window.set(FAKE_WINDOW_UID, queuedMediaItem.mediaItem, (Object) null, C.TIME_UNSET, C.TIME_UNSET, C.TIME_UNSET, true, false, (MediaItem.LiveConfiguration) null, 0, Util.msToUs(queuedMediaItem.durationMs), i, i, 0);
        return window;
    }

    public int getPeriodCount() {
        return getWindowCount();
    }

    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        QueuedMediaItem queuedMediaItem = getQueuedMediaItem(i);
        period.set(Long.valueOf(queuedMediaItem.queueId), (Object) null, i, Util.msToUs(queuedMediaItem.durationMs), 0);
        return period;
    }

    public int getIndexOfPeriod(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Object getUidOfPeriod(int i) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QueueTimeline)) {
            return false;
        }
        QueueTimeline queueTimeline = (QueueTimeline) obj;
        if (!Objects.equal(this.queuedMediaItems, queueTimeline.queuedMediaItems) || !Objects.equal(this.fakeQueuedMediaItem, queueTimeline.fakeQueuedMediaItem)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.queuedMediaItems, this.fakeQueuedMediaItem);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r1.fakeQueuedMediaItem;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media3.session.QueueTimeline.QueuedMediaItem getQueuedMediaItem(int r2) {
        /*
            r1 = this;
            com.google.common.collect.ImmutableList<androidx.media3.session.QueueTimeline$QueuedMediaItem> r0 = r1.queuedMediaItems
            int r0 = r0.size()
            if (r2 != r0) goto L_0x000d
            androidx.media3.session.QueueTimeline$QueuedMediaItem r0 = r1.fakeQueuedMediaItem
            if (r0 == 0) goto L_0x000d
            goto L_0x0016
        L_0x000d:
            com.google.common.collect.ImmutableList<androidx.media3.session.QueueTimeline$QueuedMediaItem> r0 = r1.queuedMediaItems
            java.lang.Object r2 = r0.get(r2)
            r0 = r2
            androidx.media3.session.QueueTimeline$QueuedMediaItem r0 = (androidx.media3.session.QueueTimeline.QueuedMediaItem) r0
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.QueueTimeline.getQueuedMediaItem(int):androidx.media3.session.QueueTimeline$QueuedMediaItem");
    }

    private static final class QueuedMediaItem {
        public final long durationMs;
        public final MediaItem mediaItem;
        public final long queueId;

        public QueuedMediaItem(MediaItem mediaItem2, long j, long j2) {
            this.mediaItem = mediaItem2;
            this.queueId = j;
            this.durationMs = j2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof QueuedMediaItem)) {
                return false;
            }
            QueuedMediaItem queuedMediaItem = (QueuedMediaItem) obj;
            if (this.queueId == queuedMediaItem.queueId && this.mediaItem.equals(queuedMediaItem.mediaItem) && this.durationMs == queuedMediaItem.durationMs) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long j = this.queueId;
            long j2 = this.durationMs;
            return ((((JfifUtil.MARKER_EOI + ((int) (j ^ (j >>> 32)))) * 31) + this.mediaItem.hashCode()) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }
    }
}
