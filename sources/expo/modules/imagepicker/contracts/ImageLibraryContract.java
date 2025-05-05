package expo.modules.imagepicker.contracts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import expo.modules.imagepicker.ImagePickerUtilsKt;
import expo.modules.imagepicker.contracts.ImagePickerContractResult;
import expo.modules.kotlin.activityresult.AppContextActivityResultContract;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.providers.AppContextProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\"\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lexpo/modules/imagepicker/contracts/ImageLibraryContract;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultContract;", "Lexpo/modules/imagepicker/contracts/ImageLibraryContractOptions;", "Lexpo/modules/imagepicker/contracts/ImagePickerContractResult;", "appContextProvider", "Lexpo/modules/kotlin/providers/AppContextProvider;", "(Lexpo/modules/kotlin/providers/AppContextProvider;)V", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "input", "parseResult", "resultCode", "", "intent", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLibraryContract.kt */
public final class ImageLibraryContract implements AppContextActivityResultContract<ImageLibraryContractOptions, ImagePickerContractResult> {
    private final AppContextProvider appContextProvider;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImageLibraryContract.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.imagepicker.MediaTypes[] r0 = expo.modules.imagepicker.MediaTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.imagepicker.MediaTypes r1 = expo.modules.imagepicker.MediaTypes.VIDEOS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.imagepicker.MediaTypes r1 = expo.modules.imagepicker.MediaTypes.IMAGES     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.contracts.ImageLibraryContract.WhenMappings.<clinit>():void");
        }
    }

    public ImageLibraryContract(AppContextProvider appContextProvider2) {
        Intrinsics.checkNotNullParameter(appContextProvider2, "appContextProvider");
        this.appContextProvider = appContextProvider2;
    }

    private final ContentResolver getContentResolver() {
        Context reactContext = this.appContextProvider.getAppContext().getReactContext();
        ContentResolver contentResolver = reactContext != null ? reactContext.getContentResolver() : null;
        if (contentResolver != null) {
            return contentResolver;
        }
        throw new Exceptions.ReactContextLost();
    }

    public Intent createIntent(Context context, ImageLibraryContractOptions imageLibraryContractOptions) {
        ActivityResultContracts.PickVisualMedia.VisualMediaType visualMediaType;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageLibraryContractOptions, "input");
        PickVisualMediaRequest.Builder builder = new PickVisualMediaRequest.Builder();
        int i = WhenMappings.$EnumSwitchMapping$0[imageLibraryContractOptions.getOptions().getMediaTypes().ordinal()];
        if (i == 1) {
            visualMediaType = ActivityResultContracts.PickVisualMedia.VideoOnly.INSTANCE;
        } else if (i != 2) {
            visualMediaType = ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE;
        } else {
            visualMediaType = ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE;
        }
        PickVisualMediaRequest build = builder.setMediaType(visualMediaType).build();
        if (imageLibraryContractOptions.getOptions().getAllowsMultipleSelection()) {
            int selectionLimit = imageLibraryContractOptions.getOptions().getSelectionLimit();
            if (selectionLimit == 1) {
                return new ActivityResultContracts.PickVisualMedia().createIntent(context, build);
            }
            if (selectionLimit > 1) {
                return new ActivityResultContracts.PickMultipleVisualMedia(selectionLimit).createIntent(context, build);
            }
            if (selectionLimit == 0) {
                return new ActivityResultContracts.PickMultipleVisualMedia(0, 1, (DefaultConstructorMarker) null).createIntent(context, build);
            }
        }
        return new ActivityResultContracts.PickVisualMedia().createIntent(context, build);
    }

    public ImagePickerContractResult parseResult(ImageLibraryContractOptions imageLibraryContractOptions, int i, Intent intent) {
        List<Uri> allDataUris;
        ImagePickerContractResult imagePickerContractResult;
        Intrinsics.checkNotNullParameter(imageLibraryContractOptions, "input");
        if (i == 0) {
            return ImagePickerContractResult.Cancelled.INSTANCE;
        }
        if (intent != null) {
            ImagePickerContractResult.Success success = null;
            Intent intent2 = i == -1 ? intent : null;
            if (!(intent2 == null || (allDataUris = ImagePickerUtilsKt.getAllDataUris(intent2)) == null)) {
                if (imageLibraryContractOptions.getOptions().getAllowsMultipleSelection()) {
                    Iterable<Uri> iterable = allDataUris;
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                    for (Uri uri : iterable) {
                        arrayList.add(TuplesKt.to(ImagePickerUtilsKt.toMediaType(uri, getContentResolver()), uri));
                    }
                    imagePickerContractResult = new ImagePickerContractResult.Success((List) arrayList);
                } else if (intent.getData() != null) {
                    Uri data = intent.getData();
                    if (data != null) {
                        Intrinsics.checkNotNull(data);
                        success = new ImagePickerContractResult.Success(CollectionsKt.listOf(TuplesKt.to(ImagePickerUtilsKt.toMediaType(data, getContentResolver()), data)));
                    }
                    imagePickerContractResult = success;
                } else {
                    Uri uri2 = (Uri) CollectionsKt.firstOrNull(allDataUris);
                    if (uri2 != null) {
                        imagePickerContractResult = new ImagePickerContractResult.Success(CollectionsKt.listOf(TuplesKt.to(ImagePickerUtilsKt.toMediaType(uri2, getContentResolver()), uri2)));
                    } else {
                        imagePickerContractResult = ImagePickerContractResult.Error.INSTANCE;
                    }
                }
                if (imagePickerContractResult != null) {
                    return imagePickerContractResult;
                }
            }
        }
        return ImagePickerContractResult.Error.INSTANCE;
    }
}
