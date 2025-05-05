package expo.modules.imagepicker;

import kotlin.Metadata;
import kotlin.Pair;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R#\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/imagepicker/ImagePickerConstants;", "", "()V", "CACHE_DIR_NAME", "", "EXIF_TAGS", "", "Lkotlin/Pair;", "getEXIF_TAGS", "()Ljava/lang/Iterable;", "MAXIMUM_QUALITY", "", "TAG", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerConstants.kt */
public final class ImagePickerConstants {
    public static final String CACHE_DIR_NAME = "ImagePicker";
    private static final Iterable<Pair<String, String>> EXIF_TAGS = new ImagePickerConstants$EXIF_TAGS$1();
    public static final ImagePickerConstants INSTANCE = new ImagePickerConstants();
    public static final double MAXIMUM_QUALITY = 1.0d;
    public static final String TAG = "ExponentImagePicker";

    private ImagePickerConstants() {
    }

    public final Iterable<Pair<String, String>> getEXIF_TAGS() {
        return EXIF_TAGS;
    }
}
