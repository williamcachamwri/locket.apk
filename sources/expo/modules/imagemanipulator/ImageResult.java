package expo.modules.imagemanipulator;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\n\u001a\u0004\b\u0011\u0010\fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lexpo/modules/imagemanipulator/ImageResult;", "Lexpo/modules/kotlin/records/Record;", "uri", "", "width", "", "height", "base64", "(Ljava/lang/String;IILjava/lang/String;)V", "getBase64$annotations", "()V", "getBase64", "()Ljava/lang/String;", "getHeight$annotations", "getHeight", "()I", "getUri$annotations", "getUri", "getWidth$annotations", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManipulationArguments.kt */
public final class ImageResult implements Record {
    private final String base64;
    private final int height;
    private final String uri;
    private final int width;

    public static /* synthetic */ ImageResult copy$default(ImageResult imageResult, String str, int i, int i2, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imageResult.uri;
        }
        if ((i3 & 2) != 0) {
            i = imageResult.width;
        }
        if ((i3 & 4) != 0) {
            i2 = imageResult.height;
        }
        if ((i3 & 8) != 0) {
            str2 = imageResult.base64;
        }
        return imageResult.copy(str, i, i2, str2);
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public final String component1() {
        return this.uri;
    }

    public final int component2() {
        return this.width;
    }

    public final int component3() {
        return this.height;
    }

    public final String component4() {
        return this.base64;
    }

    public final ImageResult copy(String str, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(str, "uri");
        return new ImageResult(str, i, i2, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageResult)) {
            return false;
        }
        ImageResult imageResult = (ImageResult) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) imageResult.uri) && this.width == imageResult.width && this.height == imageResult.height && Intrinsics.areEqual((Object) this.base64, (Object) imageResult.base64);
    }

    public int hashCode() {
        int hashCode = ((((this.uri.hashCode() * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height)) * 31;
        String str = this.base64;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.uri;
        int i = this.width;
        int i2 = this.height;
        return "ImageResult(uri=" + str + ", width=" + i + ", height=" + i2 + ", base64=" + this.base64 + ")";
    }

    public ImageResult(String str, int i, int i2, String str2) {
        Intrinsics.checkNotNullParameter(str, "uri");
        this.uri = str;
        this.width = i;
        this.height = i2;
        this.base64 = str2;
    }

    public final String getUri() {
        return this.uri;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getBase64() {
        return this.base64;
    }
}
