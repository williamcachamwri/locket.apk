package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.android.gms.internal.mlkit_common.zzah;
import com.google.android.gms.internal.mlkit_common.zzai;
import com.google.android.gms.tasks.Tasks;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public class OptionalModuleUtils {
    public static final String BARCODE = "barcode";
    public static final String BARCODE_MODULE_ID = "com.google.android.gms.vision.barcode";
    public static final String CUSTOM_ICA = "custom_ica";
    public static final String CUSTOM_ICA_MODULE_ID = "com.google.android.gms.vision.custom.ica";
    public static final String DEPRECATED_DYNAMITE_MODULE_ID = "com.google.android.gms.vision.dynamite";
    public static final String DOCSCAN_CROP_MODULE_ID = "com.google.android.gms.mlkit_docscan_crop";
    public static final String DOCSCAN_DETECT_MODULE_ID = "com.google.android.gms.mlkit_docscan_detect";
    public static final String DOCSCAN_ENHANCE_MODULE_ID = "com.google.android.gms.mlkit_docscan_enhance";
    public static final String DOCSCAN_SHADOW_REMOVAL_MODULE_ID = "com.google.android.gms.mlkit_docscan_shadow";
    public static final String DOCSCAN_STAIN_REMOVAL_MODULE_ID = "com.google.android.gms.mlkit_docscan_stain";
    public static final Feature[] EMPTY_FEATURES = new Feature[0];
    public static final String FACE = "face";
    public static final String FACE_MODULE_ID = "com.google.android.gms.vision.face";
    public static final Feature FEATURE_BARCODE;
    public static final Feature FEATURE_CUSTOM_ICA;
    public static final Feature FEATURE_DOCSCAN_CROP = new Feature("mlkit.docscan.crop", 1);
    public static final Feature FEATURE_DOCSCAN_DETECT = new Feature("mlkit.docscan.detect", 1);
    public static final Feature FEATURE_DOCSCAN_ENHANCE = new Feature("mlkit.docscan.enhance", 1);
    public static final Feature FEATURE_DOCSCAN_SHADOW_REMOVAL = new Feature("mlkit.docscan.shadow", 1);
    public static final Feature FEATURE_DOCSCAN_STAIN_REMOVAL = new Feature("mlkit.docscan.stain", 1);
    public static final Feature FEATURE_DOCSCAN_UI = new Feature("mlkit.docscan.ui", 1);
    public static final Feature FEATURE_FACE;
    public static final Feature FEATURE_ICA;
    public static final Feature FEATURE_IMAGE_CAPTION = new Feature("mlkit.image.caption", 1);
    public static final Feature FEATURE_IMAGE_QUALITY_AESTHETIC = new Feature("mlkit.quality.aesthetic", 1);
    public static final Feature FEATURE_IMAGE_QUALITY_TECHNICAL = new Feature("mlkit.quality.technical", 1);
    public static final Feature FEATURE_LANGID;
    public static final Feature FEATURE_MLKIT_BARCODE_UI;
    public static final Feature FEATURE_NLCLASSIFIER;
    public static final Feature FEATURE_OCR;
    public static final Feature FEATURE_OCR_CHINESE = new Feature("mlkit.ocr.chinese", 1);
    public static final Feature FEATURE_OCR_COMMON = new Feature("mlkit.ocr.common", 1);
    public static final Feature FEATURE_OCR_DEVANAGARI = new Feature("mlkit.ocr.devanagari", 1);
    public static final Feature FEATURE_OCR_JAPANESE = new Feature("mlkit.ocr.japanese", 1);
    public static final Feature FEATURE_OCR_KOREAN = new Feature("mlkit.ocr.korean", 1);
    public static final Feature FEATURE_SMART_REPLY;
    public static final Feature FEATURE_SUBJECT_SEGMENTATION = new Feature("mlkit.segmentation.subject", 1);
    public static final Feature FEATURE_TFLITE_DYNAMITE;
    public static final String ICA = "ica";
    public static final String ICA_MODULE_ID = "com.google.android.gms.vision.ica";
    public static final String IMAGE_CAPTION_MODULE_ID = "com.google.android.gms.mlkit_image_caption";
    public static final String IMAGE_QUALITY_AESTHETIC_MODULE_ID = "com.google.android.gms.mlkit_quality_aesthetic";
    public static final String IMAGE_QUALITY_TECHNICAL_MODULE_ID = "com.google.android.gms.mlkit_quality_technical";
    public static final String LANGID = "langid";
    public static final String LANGID_MODULE_ID = "com.google.android.gms.mlkit.langid";
    public static final String MLKIT_BARCODE_UI = "barcode_ui";
    public static final String NLCLASSIFIER = "nlclassifier";
    public static final String NLCLASSIFIER_MODULE_ID = "com.google.android.gms.mlkit.nlclassifier";
    public static final String OCR = "ocr";
    public static final String OCR_CHINESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_chinese";
    public static final String OCR_COMMON_MODULE_ID = "com.google.android.gms.mlkit_ocr_common";
    public static final String OCR_DEVANAGARI_MODULE_ID = "com.google.android.gms.mlkit_ocr_devanagari";
    public static final String OCR_JAPANESE_MODULE_ID = "com.google.android.gms.mlkit_ocr_japanese";
    public static final String OCR_KOREAN_MODULE_ID = "com.google.android.gms.mlkit_ocr_korean";
    public static final String OCR_MODULE_ID = "com.google.android.gms.vision.ocr";
    public static final String SMART_REPLY = "smart_reply";
    public static final String SMART_REPLY_MODULE_ID = "com.google.android.gms.mlkit_smartreply";
    public static final String SUBJECT_SEGMENTATION_MODULE_ID = "com.google.android.gms.mlkit_subject_segmentation";
    public static final String TFLITE_DYNAMITE = "tflite_dynamite";
    public static final String TFLITE_DYNAMITE_MODULE_ID = "com.google.android.gms.tflite_dynamite";
    private static final zzai zza;
    private static final zzai zzb;

    static {
        Feature feature = new Feature("vision.barcode", 1);
        FEATURE_BARCODE = feature;
        Feature feature2 = new Feature("vision.custom.ica", 1);
        FEATURE_CUSTOM_ICA = feature2;
        Feature feature3 = new Feature("vision.face", 1);
        FEATURE_FACE = feature3;
        Feature feature4 = new Feature("vision.ica", 1);
        FEATURE_ICA = feature4;
        Feature feature5 = new Feature("vision.ocr", 1);
        FEATURE_OCR = feature5;
        Feature feature6 = new Feature("mlkit.langid", 1);
        FEATURE_LANGID = feature6;
        Feature feature7 = new Feature("mlkit.nlclassifier", 1);
        FEATURE_NLCLASSIFIER = feature7;
        Feature feature8 = new Feature(TFLITE_DYNAMITE, 1);
        FEATURE_TFLITE_DYNAMITE = feature8;
        Feature feature9 = new Feature("mlkit.barcode.ui", 1);
        FEATURE_MLKIT_BARCODE_UI = feature9;
        Feature feature10 = new Feature("mlkit.smartreply", 1);
        FEATURE_SMART_REPLY = feature10;
        zzah zzah = new zzah();
        zzah.zza(BARCODE, feature);
        zzah.zza(CUSTOM_ICA, feature2);
        zzah.zza(FACE, feature3);
        zzah.zza(ICA, feature4);
        zzah.zza(OCR, feature5);
        zzah.zza(LANGID, feature6);
        zzah.zza(NLCLASSIFIER, feature7);
        zzah.zza(TFLITE_DYNAMITE, feature8);
        zzah.zza(MLKIT_BARCODE_UI, feature9);
        zzah.zza(SMART_REPLY, feature10);
        zza = zzah.zzb();
        zzah zzah2 = new zzah();
        zzah2.zza(BARCODE_MODULE_ID, feature);
        zzah2.zza(CUSTOM_ICA_MODULE_ID, feature2);
        zzah2.zza(FACE_MODULE_ID, feature3);
        zzah2.zza(ICA_MODULE_ID, feature4);
        zzah2.zza(OCR_MODULE_ID, feature5);
        zzah2.zza(LANGID_MODULE_ID, feature6);
        zzah2.zza(NLCLASSIFIER_MODULE_ID, feature7);
        zzah2.zza(TFLITE_DYNAMITE_MODULE_ID, feature8);
        zzah2.zza(SMART_REPLY_MODULE_ID, feature10);
        zzb = zzah2.zzb();
    }

    private OptionalModuleUtils() {
    }

    @Deprecated
    public static boolean areAllRequiredModulesAvailable(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            return areAllRequiredModulesAvailable(context, zza(zzb, list));
        }
        try {
            for (String load : list) {
                DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, load);
            }
            return true;
        } catch (DynamiteModule.LoadingException unused) {
            return false;
        }
    }

    @Deprecated
    public static void requestDownload(Context context, String str) {
        requestDownload(context, (List<String>) zzaf.zzh(str));
    }

    private static Feature[] zza(Map map, List list) {
        Feature[] featureArr = new Feature[list.size()];
        for (int i = 0; i < list.size(); i++) {
            featureArr[i] = (Feature) Preconditions.checkNotNull((Feature) map.get(list.get(i)));
        }
        return featureArr;
    }

    @Deprecated
    public static void requestDownload(Context context, List<String> list) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) >= 221500000) {
            requestDownload(context, zza(zza, list));
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }

    public static boolean areAllRequiredModulesAvailable(Context context, Feature[] featureArr) {
        try {
            return ((ModuleAvailabilityResponse) Tasks.await(ModuleInstall.getClient(context).areModulesAvailable(new zzq(featureArr)).addOnFailureListener(new zzr()))).areModulesAvailable();
        } catch (InterruptedException | ExecutionException e) {
            SentryLogcatAdapter.e("OptionalModuleUtils", "Failed to complete the task of features availability check", e);
            return false;
        }
    }

    public static void requestDownload(Context context, Feature[] featureArr) {
        ModuleInstall.getClient(context).installModules(ModuleInstallRequest.newBuilder().addApi(new zzo(featureArr)).build()).addOnFailureListener(new zzp());
    }
}
