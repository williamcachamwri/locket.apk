package expo.modules.imagepicker;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R$\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/imagepicker/ImagePickerResponse;", "Lexpo/modules/kotlin/records/Record;", "canceled", "", "assets", "", "Lexpo/modules/imagepicker/ImagePickerAsset;", "(ZLjava/util/List;)V", "getAssets$annotations", "()V", "getAssets", "()Ljava/util/List;", "getCanceled$annotations", "getCanceled", "()Z", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerResponse.kt */
public final class ImagePickerResponse implements Record {
    private final List<ImagePickerAsset> assets;
    private final boolean canceled;

    public ImagePickerResponse() {
        this(false, (List) null, 3, (DefaultConstructorMarker) null);
    }

    @Field
    public static /* synthetic */ void getAssets$annotations() {
    }

    @Field
    public static /* synthetic */ void getCanceled$annotations() {
    }

    public ImagePickerResponse(boolean z, List<ImagePickerAsset> list) {
        this.canceled = z;
        this.assets = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ImagePickerResponse(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : list);
    }

    public final boolean getCanceled() {
        return this.canceled;
    }

    public final List<ImagePickerAsset> getAssets() {
        return this.assets;
    }
}
