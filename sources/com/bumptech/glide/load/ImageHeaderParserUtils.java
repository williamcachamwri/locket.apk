package com.bumptech.glide.load;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class ImageHeaderParserUtils {
    private static final int MARK_READ_LIMIT = 5242880;

    private interface OrientationReader {
        int getOrientationAndRewind(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    private interface TypeReader {
        ImageHeaderParser.ImageType getTypeAndRewind(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    private ImageHeaderParserUtils() {
    }

    public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> list, final InputStream inputStream, ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(MARK_READ_LIMIT);
        return getTypeInternal(list, new TypeReader() {
            public ImageHeaderParser.ImageType getTypeAndRewind(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getType(inputStream);
                } finally {
                    inputStream.reset();
                }
            }
        });
    }

    public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> list, final ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        return getTypeInternal(list, new TypeReader() {
            public ImageHeaderParser.ImageType getTypeAndRewind(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getType(byteBuffer);
                } finally {
                    ByteBufferUtil.rewind(byteBuffer);
                }
            }
        });
    }

    public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> list, final ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, final ArrayPool arrayPool) throws IOException {
        return getTypeInternal(list, new TypeReader() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.bumptech.glide.load.ImageHeaderParser.ImageType getTypeAndRewind(com.bumptech.glide.load.ImageHeaderParser r5) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x002b }
                    java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x002b }
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r3 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this     // Catch:{ all -> 0x002b }
                    android.os.ParcelFileDescriptor r3 = r3.rewindAndGet()     // Catch:{ all -> 0x002b }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x002b }
                    r2.<init>(r3)     // Catch:{ all -> 0x002b }
                    java.io.FileInputStream r2 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r2, (java.io.FileDescriptor) r3)     // Catch:{ all -> 0x002b }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r3     // Catch:{ all -> 0x002b }
                    r1.<init>(r2, r3)     // Catch:{ all -> 0x002b }
                    com.bumptech.glide.load.ImageHeaderParser$ImageType r5 = r5.getType((java.io.InputStream) r1)     // Catch:{ all -> 0x0028 }
                    r1.release()
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    return r5
                L_0x0028:
                    r5 = move-exception
                    r0 = r1
                    goto L_0x002c
                L_0x002b:
                    r5 = move-exception
                L_0x002c:
                    if (r0 == 0) goto L_0x0031
                    r0.release()
                L_0x0031:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass3.getTypeAndRewind(com.bumptech.glide.load.ImageHeaderParser):com.bumptech.glide.load.ImageHeaderParser$ImageType");
            }
        });
    }

    private static ImageHeaderParser.ImageType getTypeInternal(List<ImageHeaderParser> list, TypeReader typeReader) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType typeAndRewind = typeReader.getTypeAndRewind(list.get(i));
            if (typeAndRewind != ImageHeaderParser.ImageType.UNKNOWN) {
                return typeAndRewind;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    public static int getOrientation(List<ImageHeaderParser> list, final ByteBuffer byteBuffer, final ArrayPool arrayPool) throws IOException {
        if (byteBuffer == null) {
            return -1;
        }
        return getOrientationInternal(list, new OrientationReader() {
            public int getOrientationAndRewind(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getOrientation(byteBuffer, arrayPool);
                } finally {
                    ByteBufferUtil.rewind(byteBuffer);
                }
            }
        });
    }

    public static int getOrientation(List<ImageHeaderParser> list, final InputStream inputStream, final ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(MARK_READ_LIMIT);
        return getOrientationInternal(list, new OrientationReader() {
            public int getOrientationAndRewind(ImageHeaderParser imageHeaderParser) throws IOException {
                try {
                    return imageHeaderParser.getOrientation(inputStream, arrayPool);
                } finally {
                    inputStream.reset();
                }
            }
        });
    }

    public static int getOrientation(List<ImageHeaderParser> list, final ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, final ArrayPool arrayPool) throws IOException {
        return getOrientationInternal(list, new OrientationReader() {
            /* JADX WARNING: Removed duplicated region for block: B:11:0x0030  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int getOrientationAndRewind(com.bumptech.glide.load.ImageHeaderParser r5) throws java.io.IOException {
                /*
                    r4 = this;
                    r0 = 0
                    com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream r1 = new com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream     // Catch:{ all -> 0x002d }
                    java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x002d }
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r3 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this     // Catch:{ all -> 0x002d }
                    android.os.ParcelFileDescriptor r3 = r3.rewindAndGet()     // Catch:{ all -> 0x002d }
                    java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ all -> 0x002d }
                    r2.<init>(r3)     // Catch:{ all -> 0x002d }
                    java.io.FileInputStream r2 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r2, (java.io.FileDescriptor) r3)     // Catch:{ all -> 0x002d }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r3 = r3     // Catch:{ all -> 0x002d }
                    r1.<init>(r2, r3)     // Catch:{ all -> 0x002d }
                    com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r0 = r3     // Catch:{ all -> 0x002a }
                    int r5 = r5.getOrientation((java.io.InputStream) r1, (com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool) r0)     // Catch:{ all -> 0x002a }
                    r1.release()
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    return r5
                L_0x002a:
                    r5 = move-exception
                    r0 = r1
                    goto L_0x002e
                L_0x002d:
                    r5 = move-exception
                L_0x002e:
                    if (r0 == 0) goto L_0x0033
                    r0.release()
                L_0x0033:
                    com.bumptech.glide.load.data.ParcelFileDescriptorRewinder r0 = com.bumptech.glide.load.data.ParcelFileDescriptorRewinder.this
                    r0.rewindAndGet()
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.ImageHeaderParserUtils.AnonymousClass6.getOrientationAndRewind(com.bumptech.glide.load.ImageHeaderParser):int");
            }
        });
    }

    private static int getOrientationInternal(List<ImageHeaderParser> list, OrientationReader orientationReader) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int orientationAndRewind = orientationReader.getOrientationAndRewind(list.get(i));
            if (orientationAndRewind != -1) {
                return orientationAndRewind;
            }
        }
        return -1;
    }
}
