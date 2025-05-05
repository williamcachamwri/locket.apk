package expo.modules.clipboard;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardImage.kt */
final class ClipboardImageKt$bitmapFromContentUriAsync$2 extends Lambda implements Function0<Bitmap> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Uri $imageUri;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ClipboardImageKt$bitmapFromContentUriAsync$2(Context context, Uri uri) {
        super(0);
        this.$context = context;
        this.$imageUri = uri;
    }

    public final Bitmap invoke() {
        ContentResolver contentResolver = this.$context.getContentResolver();
        if (Build.VERSION.SDK_INT < 28) {
            return MediaStore.Images.Media.getBitmap(contentResolver, this.$imageUri);
        }
        ImageDecoder.Source createSource = ImageDecoder.createSource(contentResolver, this.$imageUri);
        Intrinsics.checkNotNullExpressionValue(createSource, "createSource(...)");
        return ImageDecoder.decodeBitmap(createSource);
    }
}
