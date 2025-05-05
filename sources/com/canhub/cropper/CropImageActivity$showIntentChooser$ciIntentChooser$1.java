package com.canhub.cropper;

import android.net.Uri;
import com.canhub.cropper.CropImageIntentChooser;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/canhub/cropper/CropImageActivity$showIntentChooser$ciIntentChooser$1", "Lcom/canhub/cropper/CropImageIntentChooser$ResultCallback;", "onCancelled", "", "onSuccess", "uri", "Landroid/net/Uri;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageActivity.kt */
public final class CropImageActivity$showIntentChooser$ciIntentChooser$1 implements CropImageIntentChooser.ResultCallback {
    final /* synthetic */ CropImageActivity this$0;

    CropImageActivity$showIntentChooser$ciIntentChooser$1(CropImageActivity cropImageActivity) {
        this.this$0 = cropImageActivity;
    }

    public void onSuccess(Uri uri) {
        this.this$0.onPickImageResult(uri);
    }

    public void onCancelled() {
        this.this$0.setResultCancel();
    }
}
