package expo.modules.imagepicker;

import android.content.ClipData;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\t\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u0002H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0006\"\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"expo/modules/imagepicker/ImagePickerUtilsKt$items$1$iterator$1", "", "Landroid/content/ClipData$Item;", "count", "", "getCount", "()I", "index", "getIndex", "setIndex", "(I)V", "hasNext", "", "next", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerUtils.kt */
public final class ImagePickerUtilsKt$items$1$iterator$1 implements Iterator<ClipData.Item>, KMappedMarker {
    final /* synthetic */ ClipData $this_items;
    private final int count;
    private int index;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    ImagePickerUtilsKt$items$1$iterator$1(ClipData clipData) {
        this.$this_items = clipData;
        this.count = clipData.getItemCount();
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final int getCount() {
        return this.count;
    }

    public boolean hasNext() {
        return this.index < this.count;
    }

    public ClipData.Item next() {
        ClipData clipData = this.$this_items;
        int i = this.index;
        this.index = i + 1;
        ClipData.Item itemAt = clipData.getItemAt(i);
        Intrinsics.checkNotNullExpressionValue(itemAt, "getItemAt(...)");
        return itemAt;
    }
}
