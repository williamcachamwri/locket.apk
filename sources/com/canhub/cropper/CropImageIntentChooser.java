package com.canhub.cropper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import expo.modules.imagepicker.MediaTypes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u000fH\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fJ\u0014\u0010\u001c\u001a\u00020\u00002\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\"\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00192\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/canhub/cropper/CropImageIntentChooser;", "", "activity", "Landroidx/activity/ComponentActivity;", "callback", "Lcom/canhub/cropper/CropImageIntentChooser$ResultCallback;", "(Landroidx/activity/ComponentActivity;Lcom/canhub/cropper/CropImageIntentChooser$ResultCallback;)V", "cameraImgUri", "Landroid/net/Uri;", "intentChooser", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "priorityIntentList", "", "", "title", "getCameraIntents", "context", "Landroid/content/Context;", "packageManager", "Landroid/content/pm/PackageManager;", "getGalleryIntents", "action", "hasCameraPermissionInManifest", "", "isExplicitCameraPermissionRequired", "setIntentChooserTitle", "setupPriorityAppsList", "appsList", "showChooserIntent", "", "includeCamera", "includeGallery", "Companion", "ResultCallback", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageIntentChooser.kt */
public final class CropImageIntentChooser {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String GOOGLE_PHOTOS = "com.google.android.apps.photos";
    public static final String GOOGLE_PHOTOS_GO = "com.google.android.apps.photosgo";
    public static final String MIUI_GALLERY = "com.miui.gallery";
    public static final String ONEPLUS_GALLERY = "com.oneplus.gallery";
    public static final String SAMSUNG_GALLERY = "com.sec.android.gallery3d";
    private final ComponentActivity activity;
    private final ResultCallback callback;
    private Uri cameraImgUri;
    private final ActivityResultLauncher<Intent> intentChooser;
    private List<String> priorityIntentList = CollectionsKt.listOf(GOOGLE_PHOTOS, GOOGLE_PHOTOS_GO, SAMSUNG_GALLERY, ONEPLUS_GALLERY, MIUI_GALLERY);
    private String title;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/canhub/cropper/CropImageIntentChooser$ResultCallback;", "", "onCancelled", "", "onSuccess", "uri", "Landroid/net/Uri;", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageIntentChooser.kt */
    public interface ResultCallback {
        void onCancelled();

        void onSuccess(Uri uri);
    }

    public CropImageIntentChooser(ComponentActivity componentActivity, ResultCallback resultCallback) {
        Intrinsics.checkNotNullParameter(componentActivity, "activity");
        Intrinsics.checkNotNullParameter(resultCallback, "callback");
        this.activity = componentActivity;
        this.callback = resultCallback;
        String string = componentActivity.getString(R.string.pick_image_chooser_title);
        Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…pick_image_chooser_title)");
        this.title = string;
        ActivityResultLauncher<Intent> registerForActivityResult = componentActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CropImageIntentChooser$$ExternalSyntheticLambda0(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "activity.registerForActi…)\n            }\n        }");
        this.intentChooser = registerForActivityResult;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/canhub/cropper/CropImageIntentChooser$Companion;", "", "()V", "GOOGLE_PHOTOS", "", "GOOGLE_PHOTOS_GO", "MIUI_GALLERY", "ONEPLUS_GALLERY", "SAMSUNG_GALLERY", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageIntentChooser.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: intentChooser$lambda-1  reason: not valid java name */
    public static final void m1202intentChooser$lambda1(CropImageIntentChooser cropImageIntentChooser, ActivityResult activityResult) {
        Uri uri;
        Intrinsics.checkNotNullParameter(cropImageIntentChooser, "this$0");
        if (activityResult.getResultCode() == -1) {
            Intent data = activityResult.getData();
            if (data == null || (uri = data.getData()) == null) {
                uri = cropImageIntentChooser.cameraImgUri;
            }
            cropImageIntentChooser.callback.onSuccess(uri);
            return;
        }
        cropImageIntentChooser.callback.onCancelled();
    }

    public static /* synthetic */ void showChooserIntent$default(CropImageIntentChooser cropImageIntentChooser, boolean z, boolean z2, Uri uri, int i, Object obj) {
        if ((i & 4) != 0) {
            uri = null;
        }
        cropImageIntentChooser.showChooserIntent(z, z2, uri);
    }

    public final void showChooserIntent(boolean z, boolean z2, Uri uri) {
        Intent intent;
        this.cameraImgUri = uri;
        List arrayList = new ArrayList();
        PackageManager packageManager = this.activity.getPackageManager();
        if (!isExplicitCameraPermissionRequired(this.activity) && z) {
            Intrinsics.checkNotNullExpressionValue(packageManager, "packageManager");
            arrayList.addAll(getCameraIntents(this.activity, packageManager));
        }
        if (z2) {
            Intrinsics.checkNotNullExpressionValue(packageManager, "packageManager");
            List<Intent> galleryIntents = getGalleryIntents(packageManager, "android.intent.action.GET_CONTENT");
            if (galleryIntents.isEmpty()) {
                galleryIntents = getGalleryIntents(packageManager, "android.intent.action.PICK");
            }
            arrayList.addAll(galleryIntents);
        }
        if (arrayList.isEmpty()) {
            intent = new Intent();
        } else {
            Intent intent2 = new Intent("android.intent.action.CHOOSER", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (z2) {
                intent2.setAction("android.intent.action.PICK");
                intent2.setType(MediaTypes.ImageAllMimeType);
            }
            intent = intent2;
        }
        Intent createChooser = Intent.createChooser(intent, this.title);
        Object[] array = arrayList.toArray(new Parcelable[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) array);
        this.intentChooser.launch(createChooser);
    }

    private final List<Intent> getCameraIntents(Context context, PackageManager packageManager) {
        List<Intent> arrayList = new ArrayList<>();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "packageManager.queryInte…ivities(captureIntent, 0)");
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
            intent2.setPackage(next.activityInfo.packageName);
            if (context instanceof Activity) {
                context.grantUriPermission(next.activityInfo.packageName, this.cameraImgUri, 3);
            }
            intent2.putExtra("output", this.cameraImgUri);
            arrayList.add(intent2);
        }
        return arrayList;
    }

    private final List<Intent> getGalleryIntents(PackageManager packageManager, String str) {
        Intent intent;
        Object obj;
        List<Intent> arrayList = new ArrayList<>();
        if (Intrinsics.areEqual((Object) str, (Object) "android.intent.action.GET_CONTENT")) {
            intent = new Intent(str);
        } else {
            intent = new Intent(str, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        intent.setType(MediaTypes.ImageAllMimeType);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "packageManager.queryInte…ivities(galleryIntent, 0)");
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
            intent2.setPackage(next.activityInfo.packageName);
            arrayList.add(intent2);
        }
        List arrayList2 = new ArrayList();
        for (String next2 : this.priorityIntentList) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((Intent) obj).getPackage(), (Object) next2)) {
                    break;
                }
            }
            Intent intent3 = (Intent) obj;
            if (intent3 != null) {
                arrayList.remove(intent3);
                arrayList2.add(intent3);
            }
        }
        arrayList.addAll(0, arrayList2);
        return arrayList;
    }

    private final boolean isExplicitCameraPermissionRequired(Context context) {
        return hasCameraPermissionInManifest(context) && context.checkSelfPermission("android.permission.CAMERA") != 0;
    }

    private final boolean hasCameraPermissionInManifest(Context context) {
        boolean z;
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                String str = strArr[i];
                if (str != null && StringsKt.equals(str, "android.permission.CAMERA", true)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final CropImageIntentChooser setupPriorityAppsList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "appsList");
        CropImageIntentChooser cropImageIntentChooser = this;
        this.priorityIntentList = list;
        return this;
    }

    public final CropImageIntentChooser setIntentChooserTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        CropImageIntentChooser cropImageIntentChooser = this;
        this.title = str;
        return this;
    }
}
