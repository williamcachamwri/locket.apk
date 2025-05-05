package androidx.camera.core;

import android.util.Size;
import androidx.camera.core.ImageProxy;
import java.nio.ByteBuffer;

final class ImageProxyDownsampler {

    enum DownsamplingMethod {
        NEAREST_NEIGHBOR,
        AVERAGING
    }

    private ImageProxyDownsampler() {
    }

    static ForwardingImageProxy downsample(ImageProxy imageProxy, int i, int i2, DownsamplingMethod downsamplingMethod) {
        byte[] bArr;
        int i3;
        ImageProxy imageProxy2 = imageProxy;
        int i4 = i;
        int i5 = i2;
        if (imageProxy.getFormat() != 35) {
            throw new UnsupportedOperationException("Only YUV_420_888 format is currently supported.");
        } else if (imageProxy.getWidth() < i4 || imageProxy.getHeight() < i5) {
            throw new IllegalArgumentException("Downsampled dimension " + new Size(i4, i5) + " is not <= original dimension " + new Size(imageProxy.getWidth(), imageProxy.getHeight()) + ".");
        } else if (imageProxy.getWidth() == i4 && imageProxy.getHeight() == i5) {
            return new ForwardingImageProxyImpl(imageProxy2, imageProxy.getPlanes(), i4, i5);
        } else {
            int[] iArr = {imageProxy.getWidth(), imageProxy.getWidth() / 2, imageProxy.getWidth() / 2};
            int[] iArr2 = {imageProxy.getHeight(), imageProxy.getHeight() / 2, imageProxy.getHeight() / 2};
            int i6 = i4 / 2;
            int[] iArr3 = {i4, i6, i6};
            int i7 = i5 / 2;
            int[] iArr4 = {i5, i7, i7};
            ImageProxy.PlaneProxy[] planeProxyArr = new ImageProxy.PlaneProxy[3];
            int i8 = 0;
            for (int i9 = 3; i8 < i9; i9 = 3) {
                ImageProxy.PlaneProxy planeProxy = imageProxy.getPlanes()[i8];
                ByteBuffer buffer = planeProxy.getBuffer();
                byte[] bArr2 = new byte[(iArr3[i8] * iArr4[i8])];
                int ordinal = downsamplingMethod.ordinal();
                if (ordinal == 0) {
                    i3 = 1;
                    bArr = bArr2;
                    resizeNearestNeighbor(buffer, iArr[i8], planeProxy.getPixelStride(), planeProxy.getRowStride(), iArr2[i8], bArr, iArr3[i8], iArr4[i8]);
                } else if (ordinal != 1) {
                    i3 = 1;
                    bArr = bArr2;
                } else {
                    int i10 = iArr[i8];
                    int pixelStride = planeProxy.getPixelStride();
                    i3 = 1;
                    int rowStride = planeProxy.getRowStride();
                    byte[] bArr3 = bArr2;
                    resizeAveraging(buffer, i10, pixelStride, rowStride, iArr2[i8], bArr3, iArr3[i8], iArr4[i8]);
                    bArr = bArr3;
                }
                planeProxyArr[i8] = createPlaneProxy(iArr3[i8], i3, bArr);
                i8++;
            }
            return new ForwardingImageProxyImpl(imageProxy2, planeProxyArr, i4, i5);
        }
    }

    private static void resizeNearestNeighbor(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6) {
        float f = ((float) i) / ((float) i5);
        float f2 = ((float) i4) / ((float) i6);
        byte[] bArr2 = new byte[i3];
        int[] iArr = new int[i5];
        for (int i7 = 0; i7 < i5; i7++) {
            iArr[i7] = ((int) (((float) i7) * f)) * i2;
        }
        synchronized (byteBuffer) {
            byteBuffer.rewind();
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = i8 * i5;
                byteBuffer.position(Math.min((int) (((float) i8) * f2), i4 - 1) * i3);
                byteBuffer.get(bArr2, 0, Math.min(i3, byteBuffer.remaining()));
                for (int i10 = 0; i10 < i5; i10++) {
                    bArr[i9 + i10] = bArr2[iArr[i10]];
                }
            }
        }
    }

    private static void resizeAveraging(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6) {
        ByteBuffer byteBuffer2 = byteBuffer;
        int i7 = i3;
        int i8 = i4;
        int i9 = i5;
        int i10 = i6;
        float f = ((float) i) / ((float) i9);
        float f2 = ((float) i8) / ((float) i10);
        byte[] bArr2 = new byte[i7];
        byte[] bArr3 = new byte[i7];
        int[] iArr = new int[i9];
        int i11 = 0;
        for (int i12 = 0; i12 < i9; i12++) {
            iArr[i12] = ((int) (((float) i12) * f)) * i2;
        }
        synchronized (byteBuffer) {
            byteBuffer.rewind();
            int i13 = 0;
            while (i13 < i10) {
                int i14 = (int) (((float) i13) * f2);
                int i15 = i8 - 1;
                int i16 = i13 * i9;
                byteBuffer2.position(Math.min(i14, i15) * i7);
                byteBuffer2.get(bArr2, i11, Math.min(i7, byteBuffer.remaining()));
                byteBuffer2.position(Math.min(i14 + 1, i15) * i7);
                byteBuffer2.get(bArr3, i11, Math.min(i7, byteBuffer.remaining()));
                for (int i17 = i11; i17 < i9; i17++) {
                    int i18 = iArr[i17];
                    bArr[i16 + i17] = (byte) ((((((bArr2[i18] & 255) + (bArr2[i18 + i2] & 255)) + (bArr3[i18] & 255)) + (bArr3[i18 + i2] & 255)) / 4) & 255);
                }
                i13++;
                i11 = 0;
            }
        }
    }

    private static ImageProxy.PlaneProxy createPlaneProxy(int i, int i2, byte[] bArr) {
        return new ImageProxy.PlaneProxy(bArr, i, i2) {
            final ByteBuffer mBuffer;
            final /* synthetic */ byte[] val$data;
            final /* synthetic */ int val$pixelStride;
            final /* synthetic */ int val$rowStride;

            {
                this.val$data = r1;
                this.val$rowStride = r2;
                this.val$pixelStride = r3;
                this.mBuffer = ByteBuffer.wrap(r1);
            }

            public int getRowStride() {
                return this.val$rowStride;
            }

            public int getPixelStride() {
                return this.val$pixelStride;
            }

            public ByteBuffer getBuffer() {
                return this.mBuffer;
            }
        };
    }

    private static final class ForwardingImageProxyImpl extends ForwardingImageProxy {
        private final int mDownsampledHeight;
        private final ImageProxy.PlaneProxy[] mDownsampledPlanes;
        private final int mDownsampledWidth;

        ForwardingImageProxyImpl(ImageProxy imageProxy, ImageProxy.PlaneProxy[] planeProxyArr, int i, int i2) {
            super(imageProxy);
            this.mDownsampledPlanes = planeProxyArr;
            this.mDownsampledWidth = i;
            this.mDownsampledHeight = i2;
        }

        public int getWidth() {
            return this.mDownsampledWidth;
        }

        public int getHeight() {
            return this.mDownsampledHeight;
        }

        public ImageProxy.PlaneProxy[] getPlanes() {
            return this.mDownsampledPlanes;
        }
    }
}
