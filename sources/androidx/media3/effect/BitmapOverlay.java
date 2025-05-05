package androidx.media3.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.Matrix;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import androidx.media3.datasource.DataSourceBitmapLoader;
import java.util.concurrent.ExecutionException;

public abstract class BitmapOverlay extends TextureOverlay {
    private final float[] flipVerticallyMatrix;
    private Bitmap lastBitmap;
    private int lastBitmapGenerationId;
    private int lastTextureId = -1;

    public abstract Bitmap getBitmap(long j) throws VideoFrameProcessingException;

    public BitmapOverlay() {
        float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
        Matrix.scaleM(create4x4IdentityMatrix, 0, 1.0f, -1.0f, 1.0f);
        this.flipVerticallyMatrix = create4x4IdentityMatrix;
    }

    public static BitmapOverlay createStaticBitmapOverlay(final Bitmap bitmap) {
        return new BitmapOverlay() {
            public Bitmap getBitmap(long j) {
                return bitmap;
            }
        };
    }

    public static BitmapOverlay createStaticBitmapOverlay(final Bitmap bitmap, final OverlaySettings overlaySettings) {
        return new BitmapOverlay() {
            public Bitmap getBitmap(long j) {
                return bitmap;
            }

            public OverlaySettings getOverlaySettings(long j) {
                return overlaySettings;
            }
        };
    }

    public static BitmapOverlay createStaticBitmapOverlay(final Context context, final Uri uri, final OverlaySettings overlaySettings) {
        return new BitmapOverlay() {
            private Bitmap lastBitmap;

            public Bitmap getBitmap(long j) throws VideoFrameProcessingException {
                if (this.lastBitmap == null) {
                    try {
                        this.lastBitmap = (Bitmap) new DataSourceBitmapLoader(context).loadBitmap(uri).get();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new VideoFrameProcessingException((Throwable) e);
                    } catch (ExecutionException e2) {
                        throw new VideoFrameProcessingException((Throwable) e2);
                    }
                }
                return this.lastBitmap;
            }

            public OverlaySettings getOverlaySettings(long j) {
                return overlaySettings;
            }
        };
    }

    public Size getTextureSize(long j) {
        return new Size(((Bitmap) Assertions.checkNotNull(this.lastBitmap)).getWidth(), ((Bitmap) Assertions.checkNotNull(this.lastBitmap)).getHeight());
    }

    public int getTextureId(long j) throws VideoFrameProcessingException {
        Bitmap bitmap = getBitmap(j);
        int generationId = bitmap.getGenerationId();
        if (!(bitmap == this.lastBitmap && generationId == this.lastBitmapGenerationId)) {
            this.lastBitmap = bitmap;
            this.lastBitmapGenerationId = generationId;
            try {
                if (this.lastTextureId == -1) {
                    this.lastTextureId = GlUtil.generateTexture();
                }
                GlUtil.setTexture(this.lastTextureId, bitmap);
            } catch (GlUtil.GlException e) {
                throw new VideoFrameProcessingException((Throwable) e);
            }
        }
        return this.lastTextureId;
    }

    public float[] getVertexTransformation(long j) {
        return this.flipVerticallyMatrix;
    }

    public void release() throws VideoFrameProcessingException {
        super.release();
        this.lastBitmap = null;
        int i = this.lastTextureId;
        if (i != -1) {
            try {
                GlUtil.deleteTexture(i);
            } catch (GlUtil.GlException e) {
                throw new VideoFrameProcessingException((Throwable) e);
            }
        }
        this.lastTextureId = -1;
    }
}
