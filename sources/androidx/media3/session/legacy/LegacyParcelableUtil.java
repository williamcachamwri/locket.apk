package androidx.media3.session.legacy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.List;

public final class LegacyParcelableUtil {
    private LegacyParcelableUtil() {
    }

    public static <T extends Parcelable, U extends Parcelable> T convert(U u, Parcelable.Creator<T> creator) {
        if (u == null) {
            return null;
        }
        Parcelable parcelable = (Parcelable) maybeApplyMediaDescriptionParcelableBugWorkaround(u);
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            return (Parcelable) maybeApplyMediaDescriptionParcelableBugWorkaround((Parcelable) creator.createFromParcel(obtain));
        } finally {
            obtain.recycle();
        }
    }

    public static <T extends Parcelable, U extends Parcelable> ArrayList<T> convertList(List<U> list, Parcelable.Creator<T> creator) {
        if (list == null) {
            return null;
        }
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convert((Parcelable) list.get(i), creator));
        }
        return arrayList;
    }

    private static <T> T maybeApplyMediaDescriptionParcelableBugWorkaround(T t) {
        if (Util.SDK_INT < 21 || Util.SDK_INT >= 23) {
            return t;
        }
        if (!(t instanceof MediaBrowserCompat.MediaItem)) {
            return t instanceof MediaDescriptionCompat ? rebuildMediaDescriptionCompat((MediaDescriptionCompat) t) : t;
        }
        MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) t;
        return new MediaBrowserCompat.MediaItem(rebuildMediaDescriptionCompat(mediaItem.getDescription()), mediaItem.getFlags());
    }

    private static MediaDescriptionCompat rebuildMediaDescriptionCompat(MediaDescriptionCompat mediaDescriptionCompat) {
        return new MediaDescriptionCompat.Builder().setMediaId(mediaDescriptionCompat.getMediaId()).setTitle(mediaDescriptionCompat.getTitle()).setSubtitle(mediaDescriptionCompat.getSubtitle()).setDescription(mediaDescriptionCompat.getDescription()).setIconBitmap(mediaDescriptionCompat.getIconBitmap()).setIconUri(mediaDescriptionCompat.getIconUri()).setExtras(mediaDescriptionCompat.getExtras()).setMediaUri(mediaDescriptionCompat.getMediaUri()).build();
    }
}
