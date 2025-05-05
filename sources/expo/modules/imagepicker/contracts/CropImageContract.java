package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.os.BundleKt;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageActivity;
import com.canhub.cropper.CropImageOptions;
import expo.modules.imagepicker.ImagePickerUtilsKt;
import expo.modules.kotlin.activityresult.AppContextActivityResultContract;
import expo.modules.kotlin.providers.AppContextProvider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\"\u0010\f\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lexpo/modules/imagepicker/contracts/CropImageContract;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "Lexpo/modules/imagepicker/contracts/CropImageContractOptions;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CropImageContract.kt */
public final class CropImageContract implements AppContextActivityResultContract<CropImageContractOptions, ImagePickerContractResult> {
    private final AppContextProvider appContextProvider;

    public CropImageContract(AppContextProvider appContextProvider2) {
        Intrinsics.checkNotNullParameter(appContextProvider2, "appContextProvider");
        this.appContextProvider = appContextProvider2;
    }

    public Intent createIntent(Context context, CropImageContractOptions cropImageContractOptions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cropImageContractOptions, "input");
        Intent intent = new Intent(context, CropImageActivity.class);
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        Bitmap.CompressFormat bitmapCompressFormat = ImagePickerUtilsKt.toBitmapCompressFormat(ImagePickerUtilsKt.getType(contentResolver, Uri.parse(cropImageContractOptions.getSourceUri())));
        Uri fromFile = Uri.fromFile(ImagePickerUtilsKt.createOutputFile(this.appContextProvider.getAppContext().getCacheDirectory(), ImagePickerUtilsKt.toImageFileExtension(bitmapCompressFormat)));
        Pair[] pairArr = new Pair[2];
        pairArr[0] = TuplesKt.to(CropImage.CROP_IMAGE_EXTRA_SOURCE, Uri.parse(cropImageContractOptions.getSourceUri()));
        CropImageOptions cropImageOptions = new CropImageOptions();
        cropImageOptions.outputCompressFormat = bitmapCompressFormat;
        cropImageOptions.outputCompressQuality = (int) (cropImageContractOptions.getOptions().getQuality() * ((double) 100));
        cropImageOptions.customOutputUri = fromFile;
        Pair<Integer, Integer> aspect = cropImageContractOptions.getOptions().getAspect();
        if (aspect != null) {
            int intValue = aspect.component1().intValue();
            int intValue2 = aspect.component2().intValue();
            cropImageOptions.aspectRatioX = intValue;
            cropImageOptions.aspectRatioY = intValue2;
            cropImageOptions.fixAspectRatio = true;
            cropImageOptions.initialCropWindowPaddingRatio = 0.0f;
        }
        Unit unit = Unit.INSTANCE;
        pairArr[1] = TuplesKt.to(CropImage.CROP_IMAGE_EXTRA_OPTIONS, cropImageOptions);
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE, BundleKt.bundleOf(pairArr));
        return intent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public expo.modules.imagepicker.contracts.ImagePickerContractResult parseResult(expo.modules.imagepicker.contracts.CropImageContractOptions r5, int r6, android.content.Intent r7) {
        /*
            r4 = this;
            java.lang.String r0 = "input"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            java.lang.String r2 = "CROP_IMAGE_EXTRA_RESULT"
            r3 = 0
            if (r0 < r1) goto L_0x0019
            if (r7 == 0) goto L_0x0022
            java.lang.Class<com.canhub.cropper.CropImage$ActivityResult> r0 = com.canhub.cropper.CropImage.ActivityResult.class
            java.lang.Object r7 = r7.getParcelableExtra(r2, r0)
            com.canhub.cropper.CropImage$ActivityResult r7 = (com.canhub.cropper.CropImage.ActivityResult) r7
            goto L_0x0023
        L_0x0019:
            if (r7 == 0) goto L_0x0022
            android.os.Parcelable r7 = r7.getParcelableExtra(r2)
            com.canhub.cropper.CropImage$ActivityResult r7 = (com.canhub.cropper.CropImage.ActivityResult) r7
            goto L_0x0023
        L_0x0022:
            r7 = r3
        L_0x0023:
            if (r6 == 0) goto L_0x0073
            if (r7 != 0) goto L_0x0028
            goto L_0x0073
        L_0x0028:
            android.net.Uri r6 = r7.getUriContent()
            if (r6 == 0) goto L_0x0067
            expo.modules.kotlin.providers.AppContextProvider r7 = r4.appContextProvider
            expo.modules.kotlin.AppContext r7 = r7.getAppContext()
            android.content.Context r7 = r7.getReactContext()
            if (r7 == 0) goto L_0x005b
            android.content.ContentResolver r7 = r7.getContentResolver()
            expo.modules.imagepicker.contracts.CropImageContract$parseResult$1 r0 = new expo.modules.imagepicker.contracts.CropImageContract$parseResult$1
            r0.<init>(r5, r6, r7, r3)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            r5 = 1
            java.lang.Object unused = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r3, r0, r5, r3)
            expo.modules.imagepicker.contracts.ImagePickerContractResult$Success r5 = new expo.modules.imagepicker.contracts.ImagePickerContractResult$Success
            expo.modules.imagepicker.MediaType r7 = expo.modules.imagepicker.MediaType.IMAGE
            kotlin.Pair r6 = kotlin.TuplesKt.to(r7, r6)
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            r5.<init>(r6)
            expo.modules.imagepicker.contracts.ImagePickerContractResult r5 = (expo.modules.imagepicker.contracts.ImagePickerContractResult) r5
            return r5
        L_0x005b:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "React Application Context is null"
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0067:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "Required value was null."
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0073:
            expo.modules.imagepicker.contracts.ImagePickerContractResult$Cancelled r5 = expo.modules.imagepicker.contracts.ImagePickerContractResult.Cancelled.INSTANCE
            expo.modules.imagepicker.contracts.ImagePickerContractResult r5 = (expo.modules.imagepicker.contracts.ImagePickerContractResult) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.contracts.CropImageContract.parseResult(expo.modules.imagepicker.contracts.CropImageContractOptions, int, android.content.Intent):expo.modules.imagepicker.contracts.ImagePickerContractResult");
    }
}
