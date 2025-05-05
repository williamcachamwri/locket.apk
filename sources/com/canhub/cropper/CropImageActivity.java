package com.canhub.cropper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.databinding.CropImageActivityBinding;
import com.canhub.cropper.utils.GetUriForFileKt;
import expo.modules.imagepicker.MediaTypes;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 C2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002CDB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J*\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\nH\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0012\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,H\u0016J\u0012\u0010-\u001a\u00020\u00142\b\u0010.\u001a\u0004\u0018\u00010\nH\u0014J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020!H\u0014J(\u00101\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aH\u0016J\b\u00102\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020\u0014H\u0016J\b\u00104\u001a\u00020\u0014H\u0002J\u0010\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020\u00142\u0006\u00109\u001a\u00020\u001cH\u0016J\u0010\u0010:\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0016J*\u0010;\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u000e\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010<\u001a\u00020\u0014H\u0016J\u001c\u0010=\u001a\u00020\u00142\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u00140>H\u0016J\b\u0010?\u001a\u00020\u0014H\u0002J \u0010@\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%2\u0006\u0010A\u001a\u00020\u001c2\u0006\u0010B\u001a\u00020\u001cH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\n0\n0\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006E"}, d2 = {"Lcom/canhub/cropper/CropImageActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/canhub/cropper/CropImageView$OnSetImageUriCompleteListener;", "Lcom/canhub/cropper/CropImageView$OnCropImageCompleteListener;", "()V", "binding", "Lcom/canhub/cropper/databinding/CropImageActivityBinding;", "cropImageOptions", "Lcom/canhub/cropper/CropImageOptions;", "cropImageUri", "Landroid/net/Uri;", "cropImageView", "Lcom/canhub/cropper/CropImageView;", "latestTmpUri", "pickImageGallery", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "takePicture", "cropImage", "", "getResultIntent", "Landroid/content/Intent;", "uri", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "sampleSize", "", "getTmpFileUri", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onCropImageComplete", "view", "result", "Lcom/canhub/cropper/CropImageView$CropResult;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPickImageResult", "resultUri", "onSaveInstanceState", "outState", "onSetImageUriComplete", "onStart", "onStop", "openCamera", "openSource", "source", "Lcom/canhub/cropper/CropImageActivity$Source;", "rotateImage", "degrees", "setCropImageView", "setResult", "setResultCancel", "showImageSourceDialog", "Lkotlin/Function1;", "showIntentChooser", "updateMenuItemIconColor", "itemId", "color", "Companion", "Source", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageActivity.kt */
public class CropImageActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {
    @Deprecated
    public static final String BUNDLE_KEY_TMP_URI = "bundle_key_tmp_uri";
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private CropImageActivityBinding binding;
    private CropImageOptions cropImageOptions;
    private Uri cropImageUri;
    private CropImageView cropImageView;
    private Uri latestTmpUri;
    private final ActivityResultLauncher<String> pickImageGallery;
    private final ActivityResultLauncher<Uri> takePicture;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/canhub/cropper/CropImageActivity$Source;", "", "(Ljava/lang/String;I)V", "CAMERA", "GALLERY", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageActivity.kt */
    public enum Source {
        CAMERA,
        GALLERY
    }

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Source.values().length];
            iArr[Source.CAMERA.ordinal()] = 1;
            iArr[Source.GALLERY.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CropImageActivity() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.GetContent(), new CropImageActivity$$ExternalSyntheticLambda1(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…mageResult(uri)\n        }");
        this.pickImageGallery = registerForActivityResult;
        ActivityResultLauncher<Uri> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.TakePicture(), new CropImageActivity$$ExternalSyntheticLambda2(this));
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResul…ckImageResult(null)\n    }");
        this.takePicture = registerForActivityResult2;
    }

    /* access modifiers changed from: private */
    /* renamed from: pickImageGallery$lambda-0  reason: not valid java name */
    public static final void m1199pickImageGallery$lambda0(CropImageActivity cropImageActivity, Uri uri) {
        Intrinsics.checkNotNullParameter(cropImageActivity, "this$0");
        cropImageActivity.onPickImageResult(uri);
    }

    /* access modifiers changed from: private */
    /* renamed from: takePicture$lambda-1  reason: not valid java name */
    public static final void m1201takePicture$lambda1(CropImageActivity cropImageActivity, Boolean bool) {
        Intrinsics.checkNotNullParameter(cropImageActivity, "this$0");
        Intrinsics.checkNotNullExpressionValue(bool, "it");
        cropImageActivity.onPickImageResult(bool.booleanValue() ? cropImageActivity.latestTmpUri : null);
    }

    public void onCreate(Bundle bundle) {
        CharSequence charSequence;
        Uri uri;
        super.onCreate(bundle);
        CropImageActivityBinding inflate = CropImageActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.binding = inflate;
        CropImageOptions cropImageOptions2 = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView((View) inflate.getRoot());
        CropImageActivityBinding cropImageActivityBinding = this.binding;
        if (cropImageActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            cropImageActivityBinding = null;
        }
        CropImageView cropImageView2 = cropImageActivityBinding.cropImageView;
        Intrinsics.checkNotNullExpressionValue(cropImageView2, "binding.cropImageView");
        setCropImageView(cropImageView2);
        Bundle bundleExtra = getIntent().getBundleExtra(CropImage.CROP_IMAGE_EXTRA_BUNDLE);
        this.cropImageUri = bundleExtra != null ? (Uri) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_SOURCE) : null;
        CropImageOptions cropImageOptions3 = bundleExtra != null ? (CropImageOptions) bundleExtra.getParcelable(CropImage.CROP_IMAGE_EXTRA_OPTIONS) : null;
        if (cropImageOptions3 == null) {
            cropImageOptions3 = new CropImageOptions();
        }
        this.cropImageOptions = cropImageOptions3;
        if (bundle == null) {
            Uri uri2 = this.cropImageUri;
            if (uri2 == null || Intrinsics.areEqual((Object) uri2, (Object) Uri.EMPTY)) {
                CropImageOptions cropImageOptions4 = this.cropImageOptions;
                if (cropImageOptions4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                    cropImageOptions4 = null;
                }
                if (cropImageOptions4.showIntentChooser) {
                    showIntentChooser();
                } else {
                    CropImageOptions cropImageOptions5 = this.cropImageOptions;
                    if (cropImageOptions5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                        cropImageOptions5 = null;
                    }
                    if (cropImageOptions5.imageSourceIncludeGallery) {
                        CropImageOptions cropImageOptions6 = this.cropImageOptions;
                        if (cropImageOptions6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                            cropImageOptions6 = null;
                        }
                        if (cropImageOptions6.imageSourceIncludeCamera) {
                            showImageSourceDialog(new CropImageActivity$onCreate$1(this));
                        }
                    }
                    CropImageOptions cropImageOptions7 = this.cropImageOptions;
                    if (cropImageOptions7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                        cropImageOptions7 = null;
                    }
                    if (cropImageOptions7.imageSourceIncludeGallery) {
                        this.pickImageGallery.launch(MediaTypes.ImageAllMimeType);
                    } else {
                        CropImageOptions cropImageOptions8 = this.cropImageOptions;
                        if (cropImageOptions8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                            cropImageOptions8 = null;
                        }
                        if (cropImageOptions8.imageSourceIncludeCamera) {
                            openCamera();
                        } else {
                            finish();
                        }
                    }
                }
            } else {
                CropImageView cropImageView3 = this.cropImageView;
                if (cropImageView3 != null) {
                    cropImageView3.setImageUriAsync(this.cropImageUri);
                }
            }
        } else {
            String string = bundle.getString(BUNDLE_KEY_TMP_URI);
            if (string != null) {
                uri = Uri.parse(string);
                Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
            } else {
                uri = null;
            }
            this.latestTmpUri = uri;
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            CropImageOptions cropImageOptions9 = this.cropImageOptions;
            if (cropImageOptions9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions9 = null;
            }
            if (cropImageOptions9.activityTitle.length() > 0) {
                CropImageOptions cropImageOptions10 = this.cropImageOptions;
                if (cropImageOptions10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                } else {
                    cropImageOptions2 = cropImageOptions10;
                }
                charSequence = cropImageOptions2.activityTitle;
            } else {
                charSequence = getResources().getString(R.string.crop_image_activity_title);
            }
            setTitle(charSequence);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private final void showIntentChooser() {
        CropImageIntentChooser cropImageIntentChooser = new CropImageIntentChooser(this, new CropImageActivity$showIntentChooser$ciIntentChooser$1(this));
        CropImageOptions cropImageOptions2 = this.cropImageOptions;
        Uri uri = null;
        if (cropImageOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            cropImageOptions2 = null;
        }
        String str = cropImageOptions2.intentChooserTitle;
        if (str != null) {
            if (!(!StringsKt.isBlank(str))) {
                str = null;
            }
            if (str != null) {
                cropImageIntentChooser.setIntentChooserTitle(str);
            }
        }
        List<String> list = cropImageOptions2.intentChooserPriorityList;
        if (list != null) {
            if (!(!list.isEmpty())) {
                list = null;
            }
            if (list != null) {
                cropImageIntentChooser.setupPriorityAppsList(list);
            }
        }
        if (cropImageOptions2.imageSourceIncludeCamera) {
            uri = getTmpFileUri();
        }
        cropImageIntentChooser.showChooserIntent(cropImageOptions2.imageSourceIncludeCamera, cropImageOptions2.imageSourceIncludeGallery, uri);
    }

    /* access modifiers changed from: private */
    public final void openSource(Source source) {
        int i = WhenMappings.$EnumSwitchMapping$0[source.ordinal()];
        if (i == 1) {
            openCamera();
        } else if (i == 2) {
            this.pickImageGallery.launch(MediaTypes.ImageAllMimeType);
        }
    }

    private final void openCamera() {
        Uri tmpFileUri = getTmpFileUri();
        this.latestTmpUri = tmpFileUri;
        this.takePicture.launch(tmpFileUri);
    }

    private final Uri getTmpFileUri() {
        File createTempFile = File.createTempFile("tmp_image_file", ".png", getCacheDir());
        createTempFile.createNewFile();
        createTempFile.deleteOnExit();
        Intrinsics.checkNotNullExpressionValue(createTempFile, "tmpFile");
        return GetUriForFileKt.getUriForFile(this, createTempFile);
    }

    public void showImageSourceDialog(Function1<? super Source, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "openSource");
        new AlertDialog.Builder(this).setCancelable(false).setTitle(R.string.pick_image_chooser_title).setItems((CharSequence[]) new String[]{getString(R.string.pick_image_camera), getString(R.string.pick_image_gallery)}, (DialogInterface.OnClickListener) new CropImageActivity$$ExternalSyntheticLambda0(function1)).show();
    }

    /* access modifiers changed from: private */
    /* renamed from: showImageSourceDialog$lambda-10  reason: not valid java name */
    public static final void m1200showImageSourceDialog$lambda10(Function1 function1, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(function1, "$openSource");
        function1.invoke(i == 0 ? Source.CAMERA : Source.GALLERY);
    }

    public void onStart() {
        super.onStart();
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 != null) {
            cropImageView2.setOnSetImageUriCompleteListener(this);
        }
        CropImageView cropImageView3 = this.cropImageView;
        if (cropImageView3 != null) {
            cropImageView3.setOnCropImageCompleteListener(this);
        }
    }

    public void onStop() {
        super.onStop();
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 != null) {
            cropImageView2.setOnSetImageUriCompleteListener((CropImageView.OnSetImageUriCompleteListener) null);
        }
        CropImageView cropImageView3 = this.cropImageView;
        if (cropImageView3 != null) {
            cropImageView3.setOnCropImageCompleteListener((CropImageView.OnCropImageCompleteListener) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putString(BUNDLE_KEY_TMP_URI, String.valueOf(this.latestTmpUri));
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onCreateOptionsMenu(android.view.Menu r9) {
        /*
            r8 = this;
            java.lang.String r0 = "menu"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions
            java.lang.String r1 = "cropImageOptions"
            r2 = 0
            if (r0 != 0) goto L_0x0010
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0010:
            boolean r0 = r0.skipEditing
            r3 = 1
            if (r0 == 0) goto L_0x0016
            return r3
        L_0x0016:
            android.view.MenuInflater r0 = r8.getMenuInflater()
            int r4 = com.canhub.cropper.R.menu.crop_image_menu
            r0.inflate(r4, r9)
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions
            if (r0 != 0) goto L_0x0027
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0027:
            boolean r0 = r0.allowRotation
            if (r0 != 0) goto L_0x0036
            int r0 = com.canhub.cropper.R.id.ic_rotate_left_24
            r9.removeItem(r0)
            int r0 = com.canhub.cropper.R.id.ic_rotate_right_24
            r9.removeItem(r0)
            goto L_0x004b
        L_0x0036:
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions
            if (r0 != 0) goto L_0x003e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x003e:
            boolean r0 = r0.allowCounterRotation
            if (r0 == 0) goto L_0x004b
            int r0 = com.canhub.cropper.R.id.ic_rotate_left_24
            android.view.MenuItem r0 = r9.findItem(r0)
            r0.setVisible(r3)
        L_0x004b:
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions
            if (r0 != 0) goto L_0x0053
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0053:
            boolean r0 = r0.allowFlipping
            if (r0 != 0) goto L_0x005c
            int r0 = com.canhub.cropper.R.id.ic_flip_24
            r9.removeItem(r0)
        L_0x005c:
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions
            if (r0 != 0) goto L_0x0064
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r0 = r2
        L_0x0064:
            java.lang.CharSequence r0 = r0.cropMenuCropButtonTitle
            if (r0 == 0) goto L_0x007b
            int r0 = com.canhub.cropper.R.id.crop_image_menu_crop
            android.view.MenuItem r0 = r9.findItem(r0)
            com.canhub.cropper.CropImageOptions r4 = r8.cropImageOptions
            if (r4 != 0) goto L_0x0076
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r4 = r2
        L_0x0076:
            java.lang.CharSequence r4 = r4.cropMenuCropButtonTitle
            r0.setTitle(r4)
        L_0x007b:
            com.canhub.cropper.CropImageOptions r0 = r8.cropImageOptions     // Catch:{ Exception -> 0x00a9 }
            if (r0 != 0) goto L_0x0083
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch:{ Exception -> 0x00a9 }
            r0 = r2
        L_0x0083:
            int r0 = r0.cropMenuCropButtonIcon     // Catch:{ Exception -> 0x00a9 }
            if (r0 == 0) goto L_0x00a7
            r0 = r8
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ Exception -> 0x00a9 }
            com.canhub.cropper.CropImageOptions r4 = r8.cropImageOptions     // Catch:{ Exception -> 0x00a9 }
            if (r4 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)     // Catch:{ Exception -> 0x00a9 }
            r4 = r2
        L_0x0092:
            int r4 = r4.cropMenuCropButtonIcon     // Catch:{ Exception -> 0x00a9 }
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r0, r4)     // Catch:{ Exception -> 0x00a9 }
            int r4 = com.canhub.cropper.R.id.crop_image_menu_crop     // Catch:{ Exception -> 0x00a2 }
            android.view.MenuItem r4 = r9.findItem(r4)     // Catch:{ Exception -> 0x00a2 }
            r4.setIcon(r0)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00b5
        L_0x00a2:
            r4 = move-exception
            r7 = r4
            r4 = r0
            r0 = r7
            goto L_0x00ab
        L_0x00a7:
            r0 = r2
            goto L_0x00b5
        L_0x00a9:
            r0 = move-exception
            r4 = r2
        L_0x00ab:
            java.lang.String r5 = "Failed to read menu crop drawable"
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r6 = "AIC"
            io.sentry.android.core.SentryLogcatAdapter.w(r6, r5, r0)
            r0 = r4
        L_0x00b5:
            com.canhub.cropper.CropImageOptions r4 = r8.cropImageOptions
            if (r4 != 0) goto L_0x00bd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r4 = r2
        L_0x00bd:
            int r4 = r4.activityMenuIconColor
            if (r4 == 0) goto L_0x0100
            int r4 = com.canhub.cropper.R.id.ic_rotate_left_24
            com.canhub.cropper.CropImageOptions r5 = r8.cropImageOptions
            if (r5 != 0) goto L_0x00cb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r5 = r2
        L_0x00cb:
            int r5 = r5.activityMenuIconColor
            r8.updateMenuItemIconColor(r9, r4, r5)
            int r4 = com.canhub.cropper.R.id.ic_rotate_right_24
            com.canhub.cropper.CropImageOptions r5 = r8.cropImageOptions
            if (r5 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r5 = r2
        L_0x00da:
            int r5 = r5.activityMenuIconColor
            r8.updateMenuItemIconColor(r9, r4, r5)
            int r4 = com.canhub.cropper.R.id.ic_flip_24
            com.canhub.cropper.CropImageOptions r5 = r8.cropImageOptions
            if (r5 != 0) goto L_0x00e9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r5 = r2
        L_0x00e9:
            int r5 = r5.activityMenuIconColor
            r8.updateMenuItemIconColor(r9, r4, r5)
            if (r0 == 0) goto L_0x0100
            int r0 = com.canhub.cropper.R.id.crop_image_menu_crop
            com.canhub.cropper.CropImageOptions r4 = r8.cropImageOptions
            if (r4 != 0) goto L_0x00fa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            goto L_0x00fb
        L_0x00fa:
            r2 = r4
        L_0x00fb:
            int r1 = r2.activityMenuIconColor
            r8.updateMenuItemIconColor(r9, r0, r1)
        L_0x0100:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropImageActivity.onCreateOptionsMenu(android.view.Menu):boolean");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.crop_image_menu_crop) {
            cropImage();
            return true;
        }
        CropImageOptions cropImageOptions2 = null;
        if (itemId == R.id.ic_rotate_left_24) {
            CropImageOptions cropImageOptions3 = this.cropImageOptions;
            if (cropImageOptions3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            } else {
                cropImageOptions2 = cropImageOptions3;
            }
            rotateImage(-cropImageOptions2.rotationDegrees);
            return true;
        } else if (itemId == R.id.ic_rotate_right_24) {
            CropImageOptions cropImageOptions4 = this.cropImageOptions;
            if (cropImageOptions4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            } else {
                cropImageOptions2 = cropImageOptions4;
            }
            rotateImage(cropImageOptions2.rotationDegrees);
            return true;
        } else if (itemId == R.id.ic_flip_24_horizontally) {
            CropImageView cropImageView2 = this.cropImageView;
            if (cropImageView2 == null) {
                return true;
            }
            cropImageView2.flipImageHorizontally();
            return true;
        } else if (itemId == R.id.ic_flip_24_vertically) {
            CropImageView cropImageView3 = this.cropImageView;
            if (cropImageView3 == null) {
                return true;
            }
            cropImageView3.flipImageVertically();
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            setResultCancel();
            return true;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    /* access modifiers changed from: protected */
    public void onPickImageResult(Uri uri) {
        if (uri == null) {
            setResultCancel();
            return;
        }
        this.cropImageUri = uri;
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 != null) {
            cropImageView2.setImageUriAsync(uri);
        }
    }

    public void onSetImageUriComplete(CropImageView cropImageView2, Uri uri, Exception exc) {
        CropImageView cropImageView3;
        CropImageView cropImageView4;
        Intrinsics.checkNotNullParameter(cropImageView2, "view");
        Intrinsics.checkNotNullParameter(uri, "uri");
        CropImageOptions cropImageOptions2 = null;
        if (exc == null) {
            CropImageOptions cropImageOptions3 = this.cropImageOptions;
            if (cropImageOptions3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions3 = null;
            }
            if (!(cropImageOptions3.initialCropWindowRectangle == null || (cropImageView4 = this.cropImageView) == null)) {
                CropImageOptions cropImageOptions4 = this.cropImageOptions;
                if (cropImageOptions4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                    cropImageOptions4 = null;
                }
                cropImageView4.setCropRect(cropImageOptions4.initialCropWindowRectangle);
            }
            CropImageOptions cropImageOptions5 = this.cropImageOptions;
            if (cropImageOptions5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions5 = null;
            }
            if (cropImageOptions5.initialRotation > 0 && (cropImageView3 = this.cropImageView) != null) {
                CropImageOptions cropImageOptions6 = this.cropImageOptions;
                if (cropImageOptions6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                    cropImageOptions6 = null;
                }
                cropImageView3.setRotatedDegrees(cropImageOptions6.initialRotation);
            }
            CropImageOptions cropImageOptions7 = this.cropImageOptions;
            if (cropImageOptions7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            } else {
                cropImageOptions2 = cropImageOptions7;
            }
            if (cropImageOptions2.skipEditing) {
                cropImage();
                return;
            }
            return;
        }
        setResult((Uri) null, exc, 1);
    }

    public void onCropImageComplete(CropImageView cropImageView2, CropImageView.CropResult cropResult) {
        Intrinsics.checkNotNullParameter(cropImageView2, "view");
        Intrinsics.checkNotNullParameter(cropResult, "result");
        setResult(cropResult.getUriContent(), cropResult.getError(), cropResult.getSampleSize());
    }

    public void cropImage() {
        CropImageOptions cropImageOptions2 = this.cropImageOptions;
        CropImageOptions cropImageOptions3 = null;
        if (cropImageOptions2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            cropImageOptions2 = null;
        }
        if (cropImageOptions2.noOutputImage) {
            setResult((Uri) null, (Exception) null, 1);
            return;
        }
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 != null) {
            CropImageOptions cropImageOptions4 = this.cropImageOptions;
            if (cropImageOptions4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions4 = null;
            }
            Bitmap.CompressFormat compressFormat = cropImageOptions4.outputCompressFormat;
            CropImageOptions cropImageOptions5 = this.cropImageOptions;
            if (cropImageOptions5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions5 = null;
            }
            int i = cropImageOptions5.outputCompressQuality;
            CropImageOptions cropImageOptions6 = this.cropImageOptions;
            if (cropImageOptions6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions6 = null;
            }
            int i2 = cropImageOptions6.outputRequestWidth;
            CropImageOptions cropImageOptions7 = this.cropImageOptions;
            if (cropImageOptions7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions7 = null;
            }
            int i3 = cropImageOptions7.outputRequestHeight;
            CropImageOptions cropImageOptions8 = this.cropImageOptions;
            if (cropImageOptions8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
                cropImageOptions8 = null;
            }
            CropImageView.RequestSizeOptions requestSizeOptions = cropImageOptions8.outputRequestSizeOptions;
            CropImageOptions cropImageOptions9 = this.cropImageOptions;
            if (cropImageOptions9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cropImageOptions");
            } else {
                cropImageOptions3 = cropImageOptions9;
            }
            cropImageView2.croppedImageAsync(compressFormat, i, i2, i3, requestSizeOptions, cropImageOptions3.customOutputUri);
        }
    }

    public void setCropImageView(CropImageView cropImageView2) {
        Intrinsics.checkNotNullParameter(cropImageView2, "cropImageView");
        this.cropImageView = cropImageView2;
    }

    public void rotateImage(int i) {
        CropImageView cropImageView2 = this.cropImageView;
        if (cropImageView2 != null) {
            cropImageView2.rotateImage(i);
        }
    }

    public void setResult(Uri uri, Exception exc, int i) {
        setResult(exc != null ? 204 : -1, getResultIntent(uri, exc, i));
        finish();
    }

    public void setResultCancel() {
        setResult(0);
        finish();
    }

    public Intent getResultIntent(Uri uri, Exception exc, int i) {
        CropImageView cropImageView2 = this.cropImageView;
        Uri imageUri = cropImageView2 != null ? cropImageView2.getImageUri() : null;
        CropImageView cropImageView3 = this.cropImageView;
        float[] cropPoints = cropImageView3 != null ? cropImageView3.getCropPoints() : null;
        CropImageView cropImageView4 = this.cropImageView;
        Rect cropRect = cropImageView4 != null ? cropImageView4.getCropRect() : null;
        CropImageView cropImageView5 = this.cropImageView;
        int rotatedDegrees = cropImageView5 != null ? cropImageView5.getRotatedDegrees() : 0;
        CropImageView cropImageView6 = this.cropImageView;
        CropImage.ActivityResult activityResult = new CropImage.ActivityResult(imageUri, uri, exc, cropPoints, cropRect, rotatedDegrees, cropImageView6 != null ? cropImageView6.getWholeImageRect() : null, i);
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, activityResult);
        return intent;
    }

    public void updateMenuItemIconColor(Menu menu, int i, int i2) {
        Drawable icon;
        Intrinsics.checkNotNullParameter(menu, "menu");
        MenuItem findItem = menu.findItem(i);
        if (findItem != null && (icon = findItem.getIcon()) != null) {
            try {
                icon.mutate();
                icon.setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(i2, BlendModeCompat.SRC_ATOP));
                findItem.setIcon(icon);
            } catch (Exception e) {
                SentryLogcatAdapter.w("AIC", "Failed to update menu item color", e);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/canhub/cropper/CropImageActivity$Companion;", "", "()V", "BUNDLE_KEY_TMP_URI", "", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropImageActivity.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
