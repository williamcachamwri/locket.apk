package com.canhub.cropper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.activity.result.contract.ActivityResultContract;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/canhub/cropper/CropImageContract;", "Landroidx/activity/result/contract/ActivityResultContract;", "Lcom/canhub/cropper/CropImageContractOptions;", "Lcom/canhub/cropper/CropImageView$CropResult;", "()V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageContract.kt */
public final class CropImageContract extends ActivityResultContract<CropImageContractOptions, CropImageView.CropResult> {
    public Intent createIntent(Context context, CropImageContractOptions cropImageContractOptions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cropImageContractOptions, "input");
        cropImageContractOptions.getCropImageOptions().validate();
        Intent intent = new Intent(context, CropImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE, cropImageContractOptions.getUri());
        bundle.putParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS, cropImageContractOptions.getCropImageOptions());
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE, bundle);
        return intent;
    }

    public CropImageView.CropResult parseResult(int i, Intent intent) {
        CropImage.ActivityResult activityResult = null;
        Parcelable parcelableExtra = intent != null ? intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_RESULT) : null;
        if (parcelableExtra instanceof CropImage.ActivityResult) {
            activityResult = (CropImage.ActivityResult) parcelableExtra;
        }
        if (activityResult == null || i == 0) {
            return CropImage.CancelledResult.INSTANCE;
        }
        return activityResult;
    }
}
