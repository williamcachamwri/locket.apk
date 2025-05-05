package com.facebook.imagepipeline.decoder;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.transformation.CircularTransformation;
import com.facebook.imagepipeline.transformation.TransformationUtils;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class DefaultImageDecoder implements ImageDecoder {
    @Nullable
    private final ImageDecoder mAnimatedGifDecoder;
    @Nullable
    private final ImageDecoder mAnimatedWebPDecoder;
    @Nullable
    private final Map<ImageFormat, ImageDecoder> mCustomDecoders;
    private final ImageDecoder mDefaultDecoder;
    /* access modifiers changed from: private */
    public final Supplier<Boolean> mEnableEncodedImageColorSpaceUsage;
    private final PlatformDecoder mPlatformDecoder;

    public DefaultImageDecoder(@Nullable ImageDecoder imageDecoder, @Nullable ImageDecoder imageDecoder2, PlatformDecoder platformDecoder) {
        this(imageDecoder, imageDecoder2, platformDecoder, (Map<ImageFormat, ImageDecoder>) null);
    }

    public DefaultImageDecoder(@Nullable ImageDecoder imageDecoder, @Nullable ImageDecoder imageDecoder2, PlatformDecoder platformDecoder, @Nullable Map<ImageFormat, ImageDecoder> map) {
        this.mDefaultDecoder = new ImageDecoder() {
            @Nullable
            public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
                ColorSpace colorSpace;
                ImageFormat imageFormat = encodedImage.getImageFormat();
                if (((Boolean) DefaultImageDecoder.this.mEnableEncodedImageColorSpaceUsage.get()).booleanValue()) {
                    colorSpace = imageDecodeOptions.colorSpace == null ? encodedImage.getColorSpace() : imageDecodeOptions.colorSpace;
                } else {
                    colorSpace = imageDecodeOptions.colorSpace;
                }
                ColorSpace colorSpace2 = colorSpace;
                if (imageFormat == DefaultImageFormats.JPEG) {
                    return DefaultImageDecoder.this.decodeJpeg(encodedImage, i, qualityInfo, imageDecodeOptions, colorSpace2);
                }
                if (imageFormat == DefaultImageFormats.GIF) {
                    return DefaultImageDecoder.this.decodeGif(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                    return DefaultImageDecoder.this.decodeAnimatedWebp(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, imageDecodeOptions);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        };
        this.mAnimatedGifDecoder = imageDecoder;
        this.mAnimatedWebPDecoder = imageDecoder2;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = map;
        this.mEnableEncodedImageColorSpaceUsage = Suppliers.BOOLEAN_FALSE;
    }

    public DefaultImageDecoder(@Nullable ImageDecoder imageDecoder, @Nullable ImageDecoder imageDecoder2, PlatformDecoder platformDecoder, @Nullable Map<ImageFormat, ImageDecoder> map, Supplier<Boolean> supplier) {
        this.mDefaultDecoder = new ImageDecoder() {
            @Nullable
            public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
                ColorSpace colorSpace;
                ImageFormat imageFormat = encodedImage.getImageFormat();
                if (((Boolean) DefaultImageDecoder.this.mEnableEncodedImageColorSpaceUsage.get()).booleanValue()) {
                    colorSpace = imageDecodeOptions.colorSpace == null ? encodedImage.getColorSpace() : imageDecodeOptions.colorSpace;
                } else {
                    colorSpace = imageDecodeOptions.colorSpace;
                }
                ColorSpace colorSpace2 = colorSpace;
                if (imageFormat == DefaultImageFormats.JPEG) {
                    return DefaultImageDecoder.this.decodeJpeg(encodedImage, i, qualityInfo, imageDecodeOptions, colorSpace2);
                }
                if (imageFormat == DefaultImageFormats.GIF) {
                    return DefaultImageDecoder.this.decodeGif(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                    return DefaultImageDecoder.this.decodeAnimatedWebp(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, imageDecodeOptions);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        };
        this.mAnimatedGifDecoder = imageDecoder;
        this.mAnimatedWebPDecoder = imageDecoder2;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = map;
        this.mEnableEncodedImageColorSpaceUsage = supplier;
    }

    @Nullable
    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        InputStream inputStream;
        if (imageDecodeOptions.customImageDecoder != null) {
            return imageDecodeOptions.customImageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        ImageFormat imageFormat = encodedImage.getImageFormat();
        if ((imageFormat == null || imageFormat == ImageFormat.UNKNOWN) && (inputStream = encodedImage.getInputStream()) != null) {
            imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(inputStream);
            encodedImage.setImageFormat(imageFormat);
        }
        Map<ImageFormat, ImageDecoder> map = this.mCustomDecoders;
        if (map == null || (imageDecoder = map.get(imageFormat)) == null) {
            return this.mDefaultDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }

    @Nullable
    public CloseableImage decodeGif(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        if (encodedImage.getWidth() == -1 || encodedImage.getHeight() == -1) {
            throw new DecodeException("image width or height is incorrect", encodedImage);
        } else if (imageDecodeOptions.forceStaticImage || (imageDecoder = this.mAnimatedGifDecoder) == null) {
            return decodeStaticImage(encodedImage, imageDecodeOptions);
        } else {
            return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
        CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace = this.mPlatformDecoder.decodeFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, (Rect) null, imageDecodeOptions.colorSpace);
        try {
            boolean maybeApplyTransformation = TransformationUtils.maybeApplyTransformation(imageDecodeOptions.bitmapTransformation, decodeFromEncodedImageWithColorSpace);
            Preconditions.checkNotNull(decodeFromEncodedImageWithColorSpace);
            CloseableStaticBitmap of = CloseableStaticBitmap.of(decodeFromEncodedImageWithColorSpace, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            of.putExtra("is_rounded", Boolean.valueOf(maybeApplyTransformation && (imageDecodeOptions.bitmapTransformation instanceof CircularTransformation)));
            return of;
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) decodeFromEncodedImageWithColorSpace);
        }
    }

    public CloseableStaticBitmap decodeJpeg(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions, @Nullable ColorSpace colorSpace) {
        CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = this.mPlatformDecoder.decodeJPEGFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, (Rect) null, i, colorSpace);
        try {
            boolean maybeApplyTransformation = TransformationUtils.maybeApplyTransformation(imageDecodeOptions.bitmapTransformation, decodeJPEGFromEncodedImageWithColorSpace);
            Preconditions.checkNotNull(decodeJPEGFromEncodedImageWithColorSpace);
            CloseableStaticBitmap of = CloseableStaticBitmap.of(decodeJPEGFromEncodedImageWithColorSpace, qualityInfo, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            of.putExtra("is_rounded", Boolean.valueOf(maybeApplyTransformation && (imageDecodeOptions.bitmapTransformation instanceof CircularTransformation)));
            return of;
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) decodeJPEGFromEncodedImageWithColorSpace);
        }
    }

    @Nullable
    public CloseableImage decodeAnimatedWebp(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        if (imageDecodeOptions.forceStaticImage || (imageDecoder = this.mAnimatedWebPDecoder) == null) {
            return decodeStaticImage(encodedImage, imageDecodeOptions);
        }
        return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }
}
