package com.google.mlkit.vision.barcode.bundled.internal;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbl;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbn;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class ThickBarcodeScannerCreator extends zzbn {
    public zzbl newBarcodeScanner(IObjectWrapper iObjectWrapper, zzbc zzbc) {
        return new zzb((Context) ObjectWrapper.unwrap(iObjectWrapper), zzbc);
    }
}
