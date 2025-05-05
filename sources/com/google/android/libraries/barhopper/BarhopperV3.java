package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class BarhopperV3 implements Closeable {
    private static final long NULL_NATIVE_CONTEXT = 0;
    private static final String TAG = "BarhopperV3";
    private long nativeContext;

    public BarhopperV3() {
        System.loadLibrary("barhopper_v3");
    }

    private native void closeNative(long j);

    private native long createNative();

    private native long createNativeWithClientOptions(byte[] bArr);

    private native byte[] recognizeBitmapNative(long j, Bitmap bitmap, RecognitionOptions recognitionOptions);

    private native byte[] recognizeBufferNative(long j, int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeNative(long j, int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedBufferNative(long j, int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedNative(long j, int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions);

    public void close() {
        long j = this.nativeContext;
        if (j != 0) {
            closeNative(j);
            this.nativeContext = 0;
        }
    }

    public void create() {
        if (this.nativeContext != 0) {
            SentryLogcatAdapter.w(TAG, "Native context already exists.");
            return;
        }
        long createNative = createNative();
        this.nativeContext = createNative;
        if (createNative == 0) {
            throw new IllegalStateException("Failed to create native context.");
        }
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j != 0) {
            return toProto(recognizeStridedBufferNative(j, i, i2, i3, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    private static BarhopperProto$BarhopperResponse toProto(byte[] bArr) {
        bArr.getClass();
        try {
            return BarhopperProto$BarhopperResponse.zzb(bArr, zzdo.zza());
        } catch (zzeo e) {
            throw new IllegalStateException("Received unexpected BarhopperResponse buffer: {0}", e);
        }
    }

    public void create(BarhopperV3Options barhopperV3Options) {
        if (this.nativeContext == 0) {
            try {
                int zzE = barhopperV3Options.zzE();
                byte[] bArr = new byte[zzE];
                zzdj zzA = zzdj.zzA(bArr, 0, zzE);
                barhopperV3Options.zzaa(zzA);
                zzA.zzB();
                long createNativeWithClientOptions = createNativeWithClientOptions(bArr);
                this.nativeContext = createNativeWithClientOptions;
                if (createNativeWithClientOptions == 0) {
                    throw new IllegalArgumentException("Failed to create native context with client options.");
                }
            } catch (IOException e) {
                String name = barhopperV3Options.getClass().getName();
                throw new RuntimeException("Serializing " + name + " to a byte array threw an IOException (should never happen).", e);
            }
        } else {
            SentryLogcatAdapter.w(TAG, "Native context already exists.");
        }
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j != 0) {
            return toProto(recognizeStridedNative(j, i, i2, i3, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j != 0) {
            return toProto(recognizeBufferNative(j, i, i2, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    public BarhopperProto$BarhopperResponse recognize(int i, int i2, byte[] bArr, RecognitionOptions recognitionOptions) {
        long j = this.nativeContext;
        if (j != 0) {
            return toProto(recognizeNative(j, i, i2, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    public BarhopperProto$BarhopperResponse recognize(Bitmap bitmap, RecognitionOptions recognitionOptions) {
        if (this.nativeContext != 0) {
            if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                Log.d(TAG, "Input bitmap config is not ARGB_8888. Converting it to ARGB_8888 from ".concat(String.valueOf(String.valueOf(bitmap.getConfig()))));
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, bitmap.isMutable());
            }
            return toProto(recognizeBitmapNative(this.nativeContext, bitmap, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }
}
