package coil.decode;

import android.graphics.ImageDecoder;
import android.util.Size;
import coil.size.Sizes;
import coil.util.GifUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"<anonymous>", "", "decoder", "Landroid/graphics/ImageDecoder;", "info", "Landroid/graphics/ImageDecoder$ImageInfo;", "source", "Landroid/graphics/ImageDecoder$Source;", "onHeaderDecoded", "androidx/core/graphics/ImageDecoderKt$decodeDrawable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageDecoder.kt */
public final class ImageDecoderDecoder$decode$drawable$1$invoke$$inlined$decodeDrawable$1 implements ImageDecoder.OnHeaderDecodedListener {
    final /* synthetic */ Ref.ObjectRef $imageDecoder$inlined;
    final /* synthetic */ Ref.BooleanRef $isSampled$inlined;
    final /* synthetic */ ImageDecoderDecoder this$0;

    public ImageDecoderDecoder$decode$drawable$1$invoke$$inlined$decodeDrawable$1(Ref.ObjectRef objectRef, ImageDecoderDecoder imageDecoderDecoder, Ref.BooleanRef booleanRef) {
        this.$imageDecoder$inlined = objectRef;
        this.this$0 = imageDecoderDecoder;
        this.$isSampled$inlined = booleanRef;
    }

    public final void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        this.$imageDecoder$inlined.element = imageDecoder;
        Size size = imageInfo.getSize();
        int width = size.getWidth();
        int height = size.getHeight();
        coil.size.Size size2 = this.this$0.options.getSize();
        int px = Sizes.isOriginal(size2) ? width : GifUtils.toPx(size2.getWidth(), this.this$0.options.getScale());
        coil.size.Size size3 = this.this$0.options.getSize();
        int px2 = Sizes.isOriginal(size3) ? height : GifUtils.toPx(size3.getHeight(), this.this$0.options.getScale());
        if (width > 0 && height > 0 && !(width == px && height == px2)) {
            double computeSizeMultiplier = DecodeUtils.computeSizeMultiplier(width, height, px, px2, this.this$0.options.getScale());
            this.$isSampled$inlined.element = computeSizeMultiplier < 1.0d;
            if (this.$isSampled$inlined.element || !this.this$0.options.getAllowInexactSize()) {
                imageDecoder.setTargetSize(MathKt.roundToInt(((double) width) * computeSizeMultiplier), MathKt.roundToInt(computeSizeMultiplier * ((double) height)));
            }
        }
        this.this$0.configureImageDecoderProperties(imageDecoder);
    }
}
