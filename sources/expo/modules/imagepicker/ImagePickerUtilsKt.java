package expo.modules.imagepicker;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.webkit.MimeTypeMap;
import androidx.core.content.FileProvider;
import expo.modules.core.utilities.FileUtilities;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.InterruptibleKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0013\u001a&\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0013\u001a\u0018\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0000\u001a\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0002H\u0000\u001a\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0002\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010 \u001a\u00020\u001eH\u0000\u001a\u0012\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\"*\u00020#H\u0000\u001a\u000e\u0010$\u001a\u0004\u0018\u00010\u0018*\u00020\u0002H\u0000\u001a\f\u0010%\u001a\u00020&*\u00020\u0010H\u0000\u001a\f\u0010%\u001a\u00020&*\u00020\u0018H\u0000\u001a\u0014\u0010'\u001a\u00020\u0002*\u00020\u00102\u0006\u0010(\u001a\u00020)H\u0000\u001a\f\u0010*\u001a\u00020\u0018*\u00020&H\u0000\u001a\f\u0010*\u001a\u00020\u0018*\u00020\u0018H\u0000\u001a\u0014\u0010+\u001a\u00020,*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003\"\u001b\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006-"}, d2 = {"isDownloadsProviderUri", "", "Landroid/net/Uri;", "(Landroid/net/Uri;)Z", "isMediaProviderUri", "isMediaStoreAssetUri", "items", "", "Landroid/content/ClipData$Item;", "Landroid/content/ClipData;", "getItems", "(Landroid/content/ClipData;)Ljava/lang/Iterable;", "copyExifData", "", "sourceUri", "targetFile", "Ljava/io/File;", "contentResolver", "Landroid/content/ContentResolver;", "(Landroid/net/Uri;Ljava/io/File;Landroid/content/ContentResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyFile", "createOutputFile", "cacheDir", "extension", "", "getType", "uri", "getTypeFromFileUrl", "url", "extractInt", "", "Landroid/media/MediaMetadataRetriever;", "key", "getAllDataUris", "", "Landroid/content/Intent;", "getMediaStoreAssetId", "toBitmapCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "toContentUri", "context", "Landroid/content/Context;", "toImageFileExtension", "toMediaType", "Lexpo/modules/imagepicker/MediaType;", "expo-image-picker_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerUtils.kt */
public final class ImagePickerUtilsKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImagePickerUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                android.graphics.Bitmap$CompressFormat[] r0 = android.graphics.Bitmap.CompressFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerUtilsKt.WhenMappings.<clinit>():void");
        }
    }

    public static final File createOutputFile(File file, String str) {
        Intrinsics.checkNotNullParameter(file, "cacheDir");
        Intrinsics.checkNotNullParameter(str, "extension");
        String generateOutputPath = FileUtilities.generateOutputPath(file, ImagePickerConstants.CACHE_DIR_NAME, str);
        try {
            File file2 = new File(generateOutputPath);
            file2.createNewFile();
            return file2;
        } catch (IOException e) {
            Intrinsics.checkNotNull(generateOutputPath);
            throw new FailedToCreateFileException(generateOutputPath, e);
        }
    }

    public static final String getType(ContentResolver contentResolver, Uri uri) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String type = contentResolver.getType(uri);
        if (type == null) {
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
            type = getTypeFromFileUrl(uri2);
        }
        if (type != null) {
            return type;
        }
        throw new FailedToDeduceTypeException();
    }

    private static final String getTypeFromFileUrl(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    public static final Uri toContentUri(File file, Context context) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".ImagePickerFileProvider", file);
            Intrinsics.checkNotNull(uriForFile);
            return uriForFile;
        } catch (Exception unused) {
            Uri fromFile = Uri.fromFile(file);
            Intrinsics.checkNotNull(fromFile);
            return fromFile;
        }
    }

    public static final Bitmap.CompressFormat toBitmapCompressFormat(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        if (StringsKt.endsWith(FilesKt.getExtension(file), "png", true)) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public static final String toImageFileExtension(Bitmap.CompressFormat compressFormat) {
        Intrinsics.checkNotNullParameter(compressFormat, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[compressFormat.ordinal()];
        if (i == 1) {
            return ".png";
        }
        if (i == 2) {
            return ".jpeg";
        }
        throw new RuntimeException("Compress format not supported '" + compressFormat.name() + "'");
    }

    public static final String toImageFileExtension(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.endsWith(str, "png", true)) {
            return ".png";
        }
        if (StringsKt.endsWith(str, "gif", true)) {
            return ".gif";
        }
        if (StringsKt.endsWith(str, "bmp", true)) {
            return ".bmp";
        }
        if (!StringsKt.endsWith(str, "jpeg", true)) {
            SentryLogcatAdapter.w(ImagePickerConstants.TAG, "Image file " + str + " is of unsupported type. Falling back to JPEG instead.");
        }
        return ".jpeg";
    }

    public static final MediaType toMediaType(Uri uri, ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        CharSequence type = getType(contentResolver, uri);
        if (StringsKt.contains$default(type, (CharSequence) "image/", false, 2, (Object) null)) {
            return MediaType.IMAGE;
        }
        if (StringsKt.contains$default(type, (CharSequence) "video/", false, 2, (Object) null)) {
            return MediaType.VIDEO;
        }
        throw new FailedToDeduceTypeException();
    }

    public static final Bitmap.CompressFormat toBitmapCompressFormat(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.endsWith(str, "png", true) || StringsKt.endsWith(str, "gif", true) || StringsKt.endsWith(str, "bmp", true)) {
            return Bitmap.CompressFormat.PNG;
        }
        if (!StringsKt.endsWith(str, "jpeg", true)) {
            SentryLogcatAdapter.w(ImagePickerConstants.TAG, "Image file " + str + " is of unsupported type. Falling back to JPEG instead.");
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public static final int extractInt(MediaMetadataRetriever mediaMetadataRetriever, int i) {
        Intrinsics.checkNotNullParameter(mediaMetadataRetriever, "<this>");
        String extractMetadata = mediaMetadataRetriever.extractMetadata(i);
        if (extractMetadata != null) {
            return Integer.parseInt(extractMetadata);
        }
        throw new FailedToExtractVideoMetadataException((File) null, (Throwable) null, 3, (DefaultConstructorMarker) null);
    }

    public static final Iterable<ClipData.Item> getItems(ClipData clipData) {
        Intrinsics.checkNotNullParameter(clipData, "<this>");
        return new ImagePickerUtilsKt$items$1(clipData);
    }

    public static final List<Uri> getAllDataUris(Intent intent) {
        Iterable<ClipData.Item> items;
        Intrinsics.checkNotNullParameter(intent, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Uri data = intent.getData();
        if (data != null) {
            linkedHashSet.add(data);
        }
        ClipData clipData = intent.getClipData();
        if (!(clipData == null || (items = getItems(clipData)) == null)) {
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(items, 10));
            for (ClipData.Item uri : items) {
                arrayList.add(uri.getUri());
            }
            linkedHashSet.addAll((List) arrayList);
        }
        return CollectionsKt.toList(linkedHashSet);
    }

    public static final Object copyFile(Uri uri, File file, ContentResolver contentResolver, Continuation<? super Unit> continuation) {
        return InterruptibleKt.runInterruptible$default((CoroutineContext) null, new ImagePickerUtilsKt$copyFile$2(file, uri, contentResolver), continuation, 1, (Object) null);
    }

    public static final Object copyExifData(Uri uri, File file, ContentResolver contentResolver, Continuation<? super Unit> continuation) {
        return InterruptibleKt.runInterruptible$default((CoroutineContext) null, new ImagePickerUtilsKt$copyExifData$2(file, uri, contentResolver), continuation, 1, (Object) null);
    }

    public static final boolean isMediaProviderUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual((Object) uri.getAuthority(), (Object) "com.android.providers.media.documents");
    }

    public static final boolean isDownloadsProviderUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        return Intrinsics.areEqual((Object) uri.getAuthority(), (Object) "com.android.providers.downloads.documents");
    }

    public static final boolean isMediaStoreAssetUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        if (!isMediaProviderUri(uri)) {
            if (!isDownloadsProviderUri(uri)) {
                return false;
            }
            String documentId = DocumentsContract.getDocumentId(uri);
            Intrinsics.checkNotNullExpressionValue(documentId, "getDocumentId(...)");
            if (StringsKt.startsWith$default(documentId, "msf:", false, 2, (Object) null)) {
                return true;
            }
            return false;
        }
        return true;
    }

    public static final String getMediaStoreAssetId(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        if (!isMediaStoreAssetUri(uri)) {
            return null;
        }
        String documentId = DocumentsContract.getDocumentId(uri);
        Intrinsics.checkNotNull(documentId);
        CharSequence charSequence = documentId;
        if (!StringsKt.contains$default(charSequence, (char) AbstractJsonLexerKt.COLON, false, 2, (Object) null)) {
            return documentId;
        }
        return (String) StringsKt.split$default(charSequence, new char[]{AbstractJsonLexerKt.COLON}, false, 0, 6, (Object) null).get(1);
    }
}
