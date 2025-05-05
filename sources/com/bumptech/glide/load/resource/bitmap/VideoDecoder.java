package com.bumptech.glide.load.resource.bitmap;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.MediaDataSource;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VideoDecoder<T> implements ResourceDecoder<T, Bitmap> {
    private static final MediaMetadataRetrieverFactory DEFAULT_FACTORY = new MediaMetadataRetrieverFactory();
    public static final long DEFAULT_FRAME = -1;
    static final int DEFAULT_FRAME_OPTION = 2;
    public static final Option<Integer> FRAME_OPTION = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new Option.CacheKeyUpdater<Integer>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(4);

        public void update(byte[] bArr, Integer num, MessageDigest messageDigest) {
            if (num != null) {
                messageDigest.update(bArr);
                synchronized (this.buffer) {
                    this.buffer.position(0);
                    messageDigest.update(this.buffer.putInt(num.intValue()).array());
                }
            }
        }
    });
    private static final List<String> PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX = Collections.unmodifiableList(Arrays.asList(new String[]{"TP1A", "TD1A.220804.031"}));
    private static final String TAG = "VideoDecoder";
    public static final Option<Long> TARGET_FRAME = Option.disk("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new Option.CacheKeyUpdater<Long>() {
        private final ByteBuffer buffer = ByteBuffer.allocate(8);

        public void update(byte[] bArr, Long l, MessageDigest messageDigest) {
            messageDigest.update(bArr);
            synchronized (this.buffer) {
                this.buffer.position(0);
                messageDigest.update(this.buffer.putLong(l.longValue()).array());
            }
        }
    });
    private static final String WEBM_MIME_TYPE = "video/webm";
    private final BitmapPool bitmapPool;
    private final MediaMetadataRetrieverFactory factory;
    private final MediaInitializer<T> initializer;

    interface MediaInitializer<T> {
        void initializeExtractor(MediaExtractor mediaExtractor, T t) throws IOException;

        void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, T t);
    }

    public boolean handles(T t, Options options) {
        return true;
    }

    public static ResourceDecoder<AssetFileDescriptor, Bitmap> asset(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new AssetFileDescriptorInitializer());
    }

    public static ResourceDecoder<ParcelFileDescriptor, Bitmap> parcel(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ParcelFileDescriptorInitializer());
    }

    public static ResourceDecoder<ByteBuffer, Bitmap> byteBuffer(BitmapPool bitmapPool2) {
        return new VideoDecoder(bitmapPool2, new ByteBufferInitializer());
    }

    VideoDecoder(BitmapPool bitmapPool2, MediaInitializer<T> mediaInitializer) {
        this(bitmapPool2, mediaInitializer, DEFAULT_FACTORY);
    }

    VideoDecoder(BitmapPool bitmapPool2, MediaInitializer<T> mediaInitializer, MediaMetadataRetrieverFactory mediaMetadataRetrieverFactory) {
        this.bitmapPool = bitmapPool2;
        this.initializer = mediaInitializer;
        this.factory = mediaMetadataRetrieverFactory;
    }

    public Resource<Bitmap> decode(T t, int i, int i2, Options options) throws IOException {
        int i3;
        long longValue = ((Long) options.get(TARGET_FRAME)).longValue();
        if (longValue >= 0 || longValue == -1) {
            Integer num = (Integer) options.get(FRAME_OPTION);
            if (num == null) {
                num = 2;
            }
            DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
            if (downsampleStrategy == null) {
                downsampleStrategy = DownsampleStrategy.DEFAULT;
            }
            DownsampleStrategy downsampleStrategy2 = downsampleStrategy;
            MediaMetadataRetriever build = this.factory.build();
            try {
                this.initializer.initializeRetriever(build, t);
                Bitmap decodeFrame = decodeFrame(t, build, longValue, num.intValue(), i, i2, downsampleStrategy2);
                if (i3 < 29) {
                    build.release();
                }
                return BitmapResource.obtain(decodeFrame, this.bitmapPool);
            } finally {
                if (Build.VERSION.SDK_INT >= 29) {
                    build.release();
                } else {
                    build.release();
                }
            }
        } else {
            throw new IllegalArgumentException("Requested frame must be non-negative, or DEFAULT_FRAME, given: " + longValue);
        }
    }

    private Bitmap decodeFrame(T t, MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        if (!isUnsupportedFormat(t, mediaMetadataRetriever)) {
            Bitmap decodeScaledFrame = (i2 == Integer.MIN_VALUE || i3 == Integer.MIN_VALUE || downsampleStrategy == DownsampleStrategy.NONE) ? null : decodeScaledFrame(mediaMetadataRetriever, j, i, i2, i3, downsampleStrategy);
            if (decodeScaledFrame == null) {
                decodeScaledFrame = decodeOriginalFrame(mediaMetadataRetriever, j, i);
            }
            Bitmap correctHdr180DegVideoFrameOrientation = correctHdr180DegVideoFrameOrientation(mediaMetadataRetriever, decodeScaledFrame);
            if (correctHdr180DegVideoFrameOrientation != null) {
                return correctHdr180DegVideoFrameOrientation;
            }
            throw new VideoDecoderException();
        }
        throw new IllegalStateException("Cannot decode VP8 video on CrOS.");
    }

    private static Bitmap correctHdr180DegVideoFrameOrientation(MediaMetadataRetriever mediaMetadataRetriever, Bitmap bitmap) {
        if (!isHdr180RotationFixRequired()) {
            return bitmap;
        }
        boolean z = false;
        try {
            if (isHDR(mediaMetadataRetriever) && Math.abs(Integer.parseInt(mediaMetadataRetriever.extractMetadata(24))) == 180) {
                z = true;
            }
        } catch (NumberFormatException unused) {
            if (Log.isLoggable("VideoDecoder", 3)) {
                Log.d("VideoDecoder", "Exception trying to extract HDR transfer function or rotation");
            }
        }
        if (!z) {
            return bitmap;
        }
        if (Log.isLoggable("VideoDecoder", 3)) {
            Log.d("VideoDecoder", "Applying HDR 180 deg thumbnail correction");
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(180.0f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static boolean isHDR(MediaMetadataRetriever mediaMetadataRetriever) throws NumberFormatException {
        String extractMetadata = mediaMetadataRetriever.extractMetadata(36);
        String extractMetadata2 = mediaMetadataRetriever.extractMetadata(35);
        int parseInt = Integer.parseInt(extractMetadata);
        return (parseInt == 7 || parseInt == 6) && Integer.parseInt(extractMetadata2) == 6;
    }

    static boolean isHdr180RotationFixRequired() {
        if (!Build.MODEL.startsWith("Pixel") || Build.VERSION.SDK_INT != 33) {
            return Build.VERSION.SDK_INT >= 30 && Build.VERSION.SDK_INT < 33;
        }
        return isTBuildRequiringRotationFix();
    }

    private static boolean isTBuildRequiringRotationFix() {
        for (String startsWith : PIXEL_T_BUILD_ID_PREFIXES_REQUIRING_HDR_180_ROTATION_FIX) {
            if (Build.ID.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    private static Bitmap decodeScaledFrame(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, DownsampleStrategy downsampleStrategy) {
        try {
            int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (parseInt3 == 90 || parseInt3 == 270) {
                int i4 = parseInt2;
                parseInt2 = parseInt;
                parseInt = i4;
            }
            float scaleFactor = downsampleStrategy.getScaleFactor(parseInt, parseInt2, i2, i3);
            return mediaMetadataRetriever.getScaledFrameAtTime(j, i, Math.round(((float) parseInt) * scaleFactor), Math.round(scaleFactor * ((float) parseInt2)));
        } catch (Throwable th) {
            if (!Log.isLoggable("VideoDecoder", 3)) {
                return null;
            }
            Log.d("VideoDecoder", "Exception trying to decode a scaled frame on oreo+, falling back to a fullsize frame", th);
            return null;
        }
    }

    private static Bitmap decodeOriginalFrame(MediaMetadataRetriever mediaMetadataRetriever, long j, int i) {
        return mediaMetadataRetriever.getFrameAtTime(j, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063 A[Catch:{ all -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006a A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isUnsupportedFormat(T r7, android.media.MediaMetadataRetriever r8) {
        /*
            r6 = this;
            java.lang.String r0 = "VideoDecoder"
            java.lang.String r1 = android.os.Build.DEVICE
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0014
            java.lang.String r1 = android.os.Build.DEVICE
            java.lang.String r4 = ".+_cheets|cheets_.+"
            boolean r1 = r1.matches(r4)
            if (r1 == 0) goto L_0x0014
            r1 = r2
            goto L_0x0015
        L_0x0014:
            r1 = r3
        L_0x0015:
            if (r1 != 0) goto L_0x0018
            return r3
        L_0x0018:
            r1 = 12
            r4 = 0
            java.lang.String r8 = r8.extractMetadata(r1)     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "video/webm"
            boolean r8 = r1.equals(r8)     // Catch:{ all -> 0x005b }
            if (r8 != 0) goto L_0x0029
            return r3
        L_0x0029:
            android.media.MediaExtractor r8 = new android.media.MediaExtractor     // Catch:{ all -> 0x005b }
            r8.<init>()     // Catch:{ all -> 0x005b }
            com.bumptech.glide.load.resource.bitmap.VideoDecoder$MediaInitializer<T> r1 = r6.initializer     // Catch:{ all -> 0x0058 }
            r1.initializeExtractor(r8, r7)     // Catch:{ all -> 0x0058 }
            int r7 = r8.getTrackCount()     // Catch:{ all -> 0x0058 }
            r1 = r3
        L_0x0038:
            if (r1 >= r7) goto L_0x0054
            android.media.MediaFormat r4 = r8.getTrackFormat(r1)     // Catch:{ all -> 0x0058 }
            java.lang.String r5 = "mime"
            java.lang.String r4 = r4.getString(r5)     // Catch:{ all -> 0x0058 }
            java.lang.String r5 = "video/x-vnd.on2.vp8"
            boolean r4 = r5.equals(r4)     // Catch:{ all -> 0x0058 }
            if (r4 == 0) goto L_0x0051
            r8.release()
            return r2
        L_0x0051:
            int r1 = r1 + 1
            goto L_0x0038
        L_0x0054:
            r8.release()
            goto L_0x006d
        L_0x0058:
            r7 = move-exception
            r4 = r8
            goto L_0x005c
        L_0x005b:
            r7 = move-exception
        L_0x005c:
            r8 = 3
            boolean r8 = android.util.Log.isLoggable(r0, r8)     // Catch:{ all -> 0x006e }
            if (r8 == 0) goto L_0x0068
            java.lang.String r8 = "Exception trying to extract track info for a webm video on CrOS."
            android.util.Log.d(r0, r8, r7)     // Catch:{ all -> 0x006e }
        L_0x0068:
            if (r4 == 0) goto L_0x006d
            r4.release()
        L_0x006d:
            return r3
        L_0x006e:
            r7 = move-exception
            if (r4 == 0) goto L_0x0074
            r4.release()
        L_0x0074:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.VideoDecoder.isUnsupportedFormat(java.lang.Object, android.media.MediaMetadataRetriever):boolean");
    }

    static class MediaMetadataRetrieverFactory {
        MediaMetadataRetrieverFactory() {
        }

        public MediaMetadataRetriever build() {
            return new MediaMetadataRetriever();
        }
    }

    private static final class AssetFileDescriptorInitializer implements MediaInitializer<AssetFileDescriptor> {
        private AssetFileDescriptorInitializer() {
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, AssetFileDescriptor assetFileDescriptor) {
            mediaMetadataRetriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }

        public void initializeExtractor(MediaExtractor mediaExtractor, AssetFileDescriptor assetFileDescriptor) throws IOException {
            mediaExtractor.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        }
    }

    static final class ParcelFileDescriptorInitializer implements MediaInitializer<ParcelFileDescriptor> {
        ParcelFileDescriptorInitializer() {
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, ParcelFileDescriptor parcelFileDescriptor) {
            mediaMetadataRetriever.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }

        public void initializeExtractor(MediaExtractor mediaExtractor, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            mediaExtractor.setDataSource(parcelFileDescriptor.getFileDescriptor());
        }
    }

    static final class ByteBufferInitializer implements MediaInitializer<ByteBuffer> {
        ByteBufferInitializer() {
        }

        public void initializeRetriever(MediaMetadataRetriever mediaMetadataRetriever, ByteBuffer byteBuffer) {
            mediaMetadataRetriever.setDataSource(getMediaDataSource(byteBuffer));
        }

        public void initializeExtractor(MediaExtractor mediaExtractor, ByteBuffer byteBuffer) throws IOException {
            mediaExtractor.setDataSource(getMediaDataSource(byteBuffer));
        }

        private MediaDataSource getMediaDataSource(final ByteBuffer byteBuffer) {
            return new MediaDataSource() {
                public void close() {
                }

                public int readAt(long j, byte[] bArr, int i, int i2) {
                    if (j >= ((long) byteBuffer.limit())) {
                        return -1;
                    }
                    byteBuffer.position((int) j);
                    int min = Math.min(i2, byteBuffer.remaining());
                    byteBuffer.get(bArr, i, min);
                    return min;
                }

                public long getSize() {
                    return (long) byteBuffer.limit();
                }
            };
        }
    }

    private static final class VideoDecoderException extends RuntimeException {
        private static final long serialVersionUID = -2556382523004027815L;

        VideoDecoderException() {
            super("MediaMetadataRetriever failed to retrieve a frame without throwing, check the adb logs for .*MetadataRetriever.* prior to this exception for details");
        }
    }
}
