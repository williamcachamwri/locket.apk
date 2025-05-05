package expo.modules.imagepicker;

import expo.modules.imagepicker.contracts.CameraContractOptions;
import expo.modules.imagepicker.contracts.ImageLibraryContractOptions;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020BR$\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR2\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00108\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u0003\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0003\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u00058\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR$\u0010&\u001a\u00020'8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b(\u0010\u0003\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010-\u001a\u00020.8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b/\u0010\u0003\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00104\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b5\u0010\u0003\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020\u00118\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00107\"\u0004\b<\u00109¨\u0006C"}, d2 = {"Lexpo/modules/imagepicker/ImagePickerOptions;", "Lexpo/modules/kotlin/records/Record;", "Ljava/io/Serializable;", "()V", "allowsEditing", "", "getAllowsEditing$annotations", "getAllowsEditing", "()Z", "setAllowsEditing", "(Z)V", "allowsMultipleSelection", "getAllowsMultipleSelection$annotations", "getAllowsMultipleSelection", "setAllowsMultipleSelection", "aspect", "Lkotlin/Pair;", "", "getAspect$annotations", "getAspect", "()Lkotlin/Pair;", "setAspect", "(Lkotlin/Pair;)V", "base64", "getBase64$annotations", "getBase64", "setBase64", "cameraType", "Lexpo/modules/imagepicker/CameraType;", "getCameraType$annotations", "getCameraType", "()Lexpo/modules/imagepicker/CameraType;", "setCameraType", "(Lexpo/modules/imagepicker/CameraType;)V", "exif", "getExif$annotations", "getExif", "setExif", "mediaTypes", "Lexpo/modules/imagepicker/MediaTypes;", "getMediaTypes$annotations", "getMediaTypes", "()Lexpo/modules/imagepicker/MediaTypes;", "setMediaTypes", "(Lexpo/modules/imagepicker/MediaTypes;)V", "quality", "", "getQuality$annotations", "getQuality", "()D", "setQuality", "(D)V", "selectionLimit", "getSelectionLimit$annotations", "getSelectionLimit", "()I", "setSelectionLimit", "(I)V", "videoMaxDuration", "getVideoMaxDuration", "setVideoMaxDuration", "toCameraContractOptions", "Lexpo/modules/imagepicker/contracts/CameraContractOptions;", "uri", "", "toImageLibraryContractOptions", "Lexpo/modules/imagepicker/contracts/ImageLibraryContractOptions;", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerOptions.kt */
public final class ImagePickerOptions implements Record, Serializable {
    private boolean allowsEditing;
    private boolean allowsMultipleSelection;
    private Pair<Integer, Integer> aspect;
    private boolean base64;
    private CameraType cameraType = CameraType.BACK;
    private boolean exif;
    private MediaTypes mediaTypes = MediaTypes.IMAGES;
    private double quality = 0.2d;
    private int selectionLimit;
    private int videoMaxDuration;

    @Field
    public static /* synthetic */ void getAllowsEditing$annotations() {
    }

    @Field
    public static /* synthetic */ void getAllowsMultipleSelection$annotations() {
    }

    @Field
    public static /* synthetic */ void getAspect$annotations() {
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getCameraType$annotations() {
    }

    @Field
    public static /* synthetic */ void getExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getMediaTypes$annotations() {
    }

    @Field
    public static /* synthetic */ void getQuality$annotations() {
    }

    @Field
    public static /* synthetic */ void getSelectionLimit$annotations() {
    }

    public final boolean getAllowsEditing() {
        return this.allowsEditing;
    }

    public final void setAllowsEditing(boolean z) {
        this.allowsEditing = z;
    }

    public final boolean getAllowsMultipleSelection() {
        return this.allowsMultipleSelection;
    }

    public final void setAllowsMultipleSelection(boolean z) {
        this.allowsMultipleSelection = z;
    }

    public final double getQuality() {
        return this.quality;
    }

    public final void setQuality(double d) {
        this.quality = d;
    }

    public final int getSelectionLimit() {
        return this.selectionLimit;
    }

    public final void setSelectionLimit(int i) {
        this.selectionLimit = i;
    }

    public final boolean getBase64() {
        return this.base64;
    }

    public final void setBase64(boolean z) {
        this.base64 = z;
    }

    public final boolean getExif() {
        return this.exif;
    }

    public final void setExif(boolean z) {
        this.exif = z;
    }

    public final MediaTypes getMediaTypes() {
        return this.mediaTypes;
    }

    public final void setMediaTypes(MediaTypes mediaTypes2) {
        Intrinsics.checkNotNullParameter(mediaTypes2, "<set-?>");
        this.mediaTypes = mediaTypes2;
    }

    public final int getVideoMaxDuration() {
        return this.videoMaxDuration;
    }

    public final void setVideoMaxDuration(int i) {
        this.videoMaxDuration = i;
    }

    public final Pair<Integer, Integer> getAspect() {
        return this.aspect;
    }

    public final void setAspect(Pair<Integer, Integer> pair) {
        this.aspect = pair;
    }

    public final CameraType getCameraType() {
        return this.cameraType;
    }

    public final void setCameraType(CameraType cameraType2) {
        Intrinsics.checkNotNullParameter(cameraType2, "<set-?>");
        this.cameraType = cameraType2;
    }

    public final CameraContractOptions toCameraContractOptions(String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        return new CameraContractOptions(str, this);
    }

    public final ImageLibraryContractOptions toImageLibraryContractOptions() {
        return new ImageLibraryContractOptions(this);
    }
}
