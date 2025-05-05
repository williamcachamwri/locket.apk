package androidx.media3.effect;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Util;

public final class SingleColorLut implements ColorLut {
    private final Bitmap lut;
    private int lutTextureId = -1;

    public static SingleColorLut createFromCube(int[][][] iArr) {
        boolean z;
        boolean z2 = true;
        if (iArr.length > 0) {
            int[][] iArr2 = iArr[0];
            if (iArr2.length > 0 && iArr2[0].length > 0) {
                z = true;
                Assertions.checkArgument(z, "LUT must have three dimensions.");
                int length = iArr.length;
                int[][] iArr3 = iArr[0];
                if (!(length == iArr3.length && iArr.length == iArr3[0].length)) {
                    z2 = false;
                }
                Assertions.checkArgument(z2, Util.formatInvariant("All three dimensions of a LUT must match, received %d x %d x %d.", Integer.valueOf(iArr.length), Integer.valueOf(iArr[0].length), Integer.valueOf(iArr[0][0].length)));
                return new SingleColorLut(transformCubeIntoBitmap(iArr));
            }
        }
        z = false;
        Assertions.checkArgument(z, "LUT must have three dimensions.");
        int length2 = iArr.length;
        int[][] iArr32 = iArr[0];
        z2 = false;
        Assertions.checkArgument(z2, Util.formatInvariant("All three dimensions of a LUT must match, received %d x %d x %d.", Integer.valueOf(iArr.length), Integer.valueOf(iArr[0].length), Integer.valueOf(iArr[0][0].length)));
        return new SingleColorLut(transformCubeIntoBitmap(iArr));
    }

    public static SingleColorLut createFromBitmap(Bitmap bitmap) {
        boolean z = true;
        Assertions.checkArgument(bitmap.getWidth() * bitmap.getWidth() == bitmap.getHeight(), Util.formatInvariant("LUT needs to be in a N x N^2 format, received %d x %d.", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())));
        if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            z = false;
        }
        Assertions.checkArgument(z, "Color representation needs to be ARGB_8888.");
        return new SingleColorLut(bitmap);
    }

    private SingleColorLut(Bitmap bitmap) {
        this.lut = bitmap;
    }

    private static Bitmap transformCubeIntoBitmap(int[][][] iArr) {
        int length = iArr.length;
        int i = length * length;
        int[] iArr2 = new int[(i * length)];
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < length; i3++) {
                for (int i4 = 0; i4 < length; i4++) {
                    iArr2[(((length * i2) + i3) * length) + i4] = iArr[i2][i3][i4];
                }
            }
        }
        return Bitmap.createBitmap(iArr2, length, i, Bitmap.Config.ARGB_8888);
    }

    public int getLutTextureId(long j) {
        Assertions.checkState(this.lutTextureId != -1, "The LUT has not been stored as a texture in OpenGL yet. You must to call #toGlShaderProgram() first.");
        return this.lutTextureId;
    }

    public int getLength(long j) {
        return this.lut.getWidth();
    }

    public void release() throws GlUtil.GlException {
        GlUtil.deleteTexture(this.lutTextureId);
    }

    public BaseGlShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        Assertions.checkState(!z, "HDR is currently not supported.");
        try {
            this.lutTextureId = GlUtil.createTexture(this.lut);
            return new ColorLutShaderProgram(context, this, z);
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException("Could not store the LUT as a texture.", (Throwable) e);
        }
    }
}
