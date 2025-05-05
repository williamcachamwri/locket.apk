package expo.modules.imagepicker.contracts;

import android.net.Uri;
import expo.modules.imagepicker.MediaType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "", "()V", "Cancelled", "Error", "Success", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Cancelled;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Error;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContractsUtils.kt */
public abstract class ImagePickerContractResult {
    public /* synthetic */ ImagePickerContractResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\u0002\u0010\u0007R#\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Success;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "data", "", "Lkotlin/Pair;", "Lexpo/modules/imagepicker/MediaType;", "Landroid/net/Uri;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContractsUtils.kt */
    public static final class Success extends ImagePickerContractResult {
        private final List<Pair<MediaType, Uri>> data;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Success(List<? extends Pair<? extends MediaType, ? extends Uri>> list) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(list, "data");
            this.data = list;
        }

        public final List<Pair<MediaType, Uri>> getData() {
            return this.data;
        }
    }

    private ImagePickerContractResult() {
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Cancelled;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "()V", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContractsUtils.kt */
    public static final class Cancelled extends ImagePickerContractResult {
        public static final Cancelled INSTANCE = new Cancelled();

        private Cancelled() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/imagepicker/contracts/ImagePickerContractResult$Error;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "()V", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ContractsUtils.kt */
    public static final class Error extends ImagePickerContractResult {
        public static final Error INSTANCE = new Error();

        private Error() {
            super((DefaultConstructorMarker) null);
        }
    }
}
