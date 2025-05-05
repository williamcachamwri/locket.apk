package expo.modules.imagepicker.contracts;

import expo.modules.imagepicker.ImagePickerOptions;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lexpo/modules/imagepicker/contracts/CameraContractOptions;", "Ljava/io/Serializable;", "uri", "", "options", "Lexpo/modules/imagepicker/ImagePickerOptions;", "(Ljava/lang/String;Lexpo/modules/imagepicker/ImagePickerOptions;)V", "getOptions", "()Lexpo/modules/imagepicker/ImagePickerOptions;", "getUri", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraContract.kt */
public final class CameraContractOptions implements Serializable {
    private final ImagePickerOptions options;
    private final String uri;

    public static /* synthetic */ CameraContractOptions copy$default(CameraContractOptions cameraContractOptions, String str, ImagePickerOptions imagePickerOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cameraContractOptions.uri;
        }
        if ((i & 2) != 0) {
            imagePickerOptions = cameraContractOptions.options;
        }
        return cameraContractOptions.copy(str, imagePickerOptions);
    }

    public final String component1() {
        return this.uri;
    }

    public final ImagePickerOptions component2() {
        return this.options;
    }

    public final CameraContractOptions copy(String str, ImagePickerOptions imagePickerOptions) {
        Intrinsics.checkNotNullParameter(str, "uri");
        Intrinsics.checkNotNullParameter(imagePickerOptions, "options");
        return new CameraContractOptions(str, imagePickerOptions);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraContractOptions)) {
            return false;
        }
        CameraContractOptions cameraContractOptions = (CameraContractOptions) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) cameraContractOptions.uri) && Intrinsics.areEqual((Object) this.options, (Object) cameraContractOptions.options);
    }

    public int hashCode() {
        return (this.uri.hashCode() * 31) + this.options.hashCode();
    }

    public String toString() {
        String str = this.uri;
        return "CameraContractOptions(uri=" + str + ", options=" + this.options + ")";
    }

    public CameraContractOptions(String str, ImagePickerOptions imagePickerOptions) {
        Intrinsics.checkNotNullParameter(str, "uri");
        Intrinsics.checkNotNullParameter(imagePickerOptions, "options");
        this.uri = str;
        this.options = imagePickerOptions;
    }

    public final String getUri() {
        return this.uri;
    }

    public final ImagePickerOptions getOptions() {
        return this.options;
    }
}
