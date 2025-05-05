package expo.modules.clipboard;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 &2\u00020\u0001:\u0003&'(B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J1\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0005H\u0016JO\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0011\u001a\u00020\u00122\u0010\u0010!\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010#J;\u0010$\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010%R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lexpo/modules/clipboard/ClipboardFileProvider;", "Landroid/content/ContentProvider;", "()V", "defaultProjectionColumns", "", "", "[Ljava/lang/String;", "strategy", "Lexpo/modules/clipboard/ClipboardFileProvider$PathStrategy;", "attachInfo", "", "context", "Landroid/content/Context;", "info", "Landroid/content/pm/ProviderInfo;", "delete", "", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "PathStrategy", "SimplePathStrategy", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardFileProvider.kt */
public final class ClipboardFileProvider extends ContentProvider {
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final File DEVICE_ROOT = new File("/");
    private static final String META_DATA_FILE_PROVIDER_PATHS = "expo.modules.clipboard.CLIPBOARD_FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    /* access modifiers changed from: private */
    public static final HashMap<String, PathStrategy> cache = new HashMap<>();
    private final String[] defaultProjectionColumns = {"_display_name", "_size"};
    private PathStrategy strategy;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lexpo/modules/clipboard/ClipboardFileProvider$PathStrategy;", "", "getFileForUri", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "getUriForFile", "file", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardFileProvider.kt */
    public interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    public boolean onCreate() {
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(providerInfo, "info");
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            Companion companion = Companion;
            String str = providerInfo.authority;
            Intrinsics.checkNotNullExpressionValue(str, "authority");
            this.strategy = companion.getPathStrategy$expo_clipboard_release(context, str);
            return;
        }
        throw new AssertionError("ClipboardFileProvider must be exported");
    }

    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        PathStrategy pathStrategy = this.strategy;
        if (pathStrategy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("strategy");
            pathStrategy = null;
        }
        File fileForUri = pathStrategy.getFileForUri(uri);
        String name = fileForUri.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) name, (char) FilenameUtils.EXTENSION_SEPARATOR, 0, false, 6, (Object) null);
        if (lastIndexOf$default < 0) {
            return "application/octet-stream";
        }
        String name2 = fileForUri.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        String substring = name2.substring(lastIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(substring);
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : "application/octet-stream";
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (strArr == null) {
            strArr = this.defaultProjectionColumns;
        }
        PathStrategy pathStrategy = this.strategy;
        if (pathStrategy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("strategy");
            pathStrategy = null;
        }
        File fileForUri = pathStrategy.getFileForUri(uri);
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str3 : strArr) {
            if (Intrinsics.areEqual((Object) str3, (Object) "_display_name")) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = fileForUri.getName();
            } else if (Intrinsics.areEqual((Object) str3, (Object) "_size")) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(fileForUri.length());
            }
            i2 = i;
        }
        Object[] copyOf = Arrays.copyOf(strArr3, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(...)");
        Object[] copyOf2 = Arrays.copyOf(objArr, i2);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(...)");
        MatrixCursor matrixCursor = new MatrixCursor((String[]) copyOf, 1);
        matrixCursor.addRow(copyOf2);
        return matrixCursor;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("This is a read-only provider");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("This is a read-only provider");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        throw new UnsupportedOperationException("This is a read-only provider");
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(str, "mode");
        if (Intrinsics.areEqual((Object) "r", (Object) str)) {
            PathStrategy pathStrategy = this.strategy;
            if (pathStrategy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("strategy");
                pathStrategy = null;
            }
            return ParcelFileDescriptor.open(pathStrategy.getFileForUri(uri), 268435456);
        }
        throw new IllegalArgumentException("mode must be \"r\"".toString());
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0016\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0016\"\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0002\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001cJ \u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0007J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u0010j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0011`\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lexpo/modules/clipboard/ClipboardFileProvider$Companion;", "", "()V", "ATTR_NAME", "", "ATTR_PATH", "DEVICE_ROOT", "Ljava/io/File;", "META_DATA_FILE_PROVIDER_PATHS", "TAG_CACHE_PATH", "TAG_EXTERNAL", "TAG_EXTERNAL_CACHE", "TAG_EXTERNAL_FILES", "TAG_FILES_PATH", "TAG_ROOT_PATH", "cache", "Ljava/util/HashMap;", "Lexpo/modules/clipboard/ClipboardFileProvider$PathStrategy;", "Lkotlin/collections/HashMap;", "buildPath", "base", "segments", "", "(Ljava/io/File;[Ljava/lang/String;)Ljava/io/File;", "getPathStrategy", "context", "Landroid/content/Context;", "authority", "getPathStrategy$expo_clipboard_release", "getUriForFile", "Landroid/net/Uri;", "file", "parsePathStrategy", "targetFileFromTag", "tag", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardFileProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Uri getUriForFile(Context context, String str, File file) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "authority");
            Intrinsics.checkNotNullParameter(file, "file");
            return getPathStrategy$expo_clipboard_release(context, str).getUriForFile(file);
        }

        public final PathStrategy getPathStrategy$expo_clipboard_release(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "authority");
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            synchronized (ClipboardFileProvider.cache) {
                T t = (PathStrategy) ClipboardFileProvider.cache.get(str);
                if (t == null) {
                    try {
                        objectRef.element = ClipboardFileProvider.Companion.parsePathStrategy(context, str);
                        ClipboardFileProvider.cache.put(str, objectRef.element);
                        t = (PathStrategy) objectRef.element;
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Failed to parse expo.modules.clipboard.CLIPBOARD_FILE_PROVIDER_PATHS meta-data", e);
                    } catch (XmlPullParserException e2) {
                        throw new IllegalArgumentException("Failed to parse expo.modules.clipboard.CLIPBOARD_FILE_PROVIDER_PATHS meta-data", e2);
                    }
                }
                objectRef.element = t;
                Unit unit = Unit.INSTANCE;
            }
            return (PathStrategy) objectRef.element;
        }

        private final PathStrategy parsePathStrategy(Context context, String str) throws IOException, XmlPullParserException {
            SimplePathStrategy simplePathStrategy = new SimplePathStrategy(str);
            PackageManager packageManager = context.getPackageManager();
            ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(str, 128);
            if (resolveContentProvider != null) {
                XmlResourceParser loadXmlMetaData = resolveContentProvider.loadXmlMetaData(packageManager, ClipboardFileProvider.META_DATA_FILE_PROVIDER_PATHS);
                if (loadXmlMetaData != null) {
                    while (true) {
                        int next = loadXmlMetaData.next();
                        if (next == 1) {
                            return simplePathStrategy;
                        }
                        if (next == 2) {
                            String name = loadXmlMetaData.getName();
                            Intrinsics.checkNotNull(name);
                            File targetFileFromTag = targetFileFromTag(name, context);
                            if (targetFileFromTag != null) {
                                simplePathStrategy.addRoot(loadXmlMetaData.getAttributeValue((String) null, "name"), ClipboardFileProvider.Companion.buildPath(targetFileFromTag, loadXmlMetaData.getAttributeValue((String) null, ClipboardFileProvider.ATTR_PATH)));
                            }
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Missing expo.modules.clipboard.CLIPBOARD_FILE_PROVIDER_PATHS meta-data");
                }
            } else {
                throw new IllegalArgumentException("Couldn't find meta-data for provider with authority " + str);
            }
        }

        private final File targetFileFromTag(String str, Context context) {
            switch (str.hashCode()) {
                case -1642807877:
                    if (!str.equals(ClipboardFileProvider.TAG_FILES_PATH)) {
                        return null;
                    }
                    return context.getFilesDir();
                case -1360690478:
                    if (!str.equals(ClipboardFileProvider.TAG_EXTERNAL_CACHE)) {
                        return null;
                    }
                    File[] externalCacheDirs = context.getExternalCacheDirs();
                    Intrinsics.checkNotNullExpressionValue(externalCacheDirs, "getExternalCacheDirs(...)");
                    if (!(!(externalCacheDirs.length == 0))) {
                        externalCacheDirs = null;
                    }
                    if (externalCacheDirs != null) {
                        return externalCacheDirs[0];
                    }
                    return null;
                case -913292752:
                    if (!str.equals(ClipboardFileProvider.TAG_ROOT_PATH)) {
                        return null;
                    }
                    return ClipboardFileProvider.DEVICE_ROOT;
                case -50149145:
                    if (!str.equals(ClipboardFileProvider.TAG_EXTERNAL)) {
                        return null;
                    }
                    return Environment.getExternalStorageDirectory();
                case -17221744:
                    if (!str.equals(ClipboardFileProvider.TAG_CACHE_PATH)) {
                        return null;
                    }
                    return context.getCacheDir();
                case 1308690685:
                    if (!str.equals(ClipboardFileProvider.TAG_EXTERNAL_FILES)) {
                        return null;
                    }
                    File[] externalFilesDirs = context.getExternalFilesDirs((String) null);
                    Intrinsics.checkNotNullExpressionValue(externalFilesDirs, "getExternalFilesDirs(...)");
                    if (!(!(externalFilesDirs.length == 0))) {
                        externalFilesDirs = null;
                    }
                    if (externalFilesDirs != null) {
                        return externalFilesDirs[0];
                    }
                    return null;
                default:
                    return null;
            }
        }

        private final File buildPath(File file, String... strArr) {
            for (String str : strArr) {
                if (str != null) {
                    file = new File(file, str);
                }
            }
            return file;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\f\u001a\u00020\u0007J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/modules/clipboard/ClipboardFileProvider$SimplePathStrategy;", "Lexpo/modules/clipboard/ClipboardFileProvider$PathStrategy;", "authority", "", "(Ljava/lang/String;)V", "roots", "Ljava/util/HashMap;", "Ljava/io/File;", "Lkotlin/collections/HashMap;", "addRoot", "", "name", "root", "getFileForUri", "uri", "Landroid/net/Uri;", "getUriForFile", "file", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ClipboardFileProvider.kt */
    public static final class SimplePathStrategy implements PathStrategy {
        private final String authority;
        private final HashMap<String, File> roots = new HashMap<>();

        public SimplePathStrategy(String str) {
            Intrinsics.checkNotNullParameter(str, "authority");
            this.authority = str;
        }

        public final void addRoot(String str, File file) {
            Intrinsics.checkNotNullParameter(file, "root");
            if (str != null && !TextUtils.isEmpty(str)) {
                try {
                    File canonicalFile = file.getCanonicalFile();
                    Intrinsics.checkNotNull(canonicalFile);
                    this.roots.put(str, canonicalFile);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty".toString());
            }
        }

        public Uri getUriForFile(File file) {
            String str;
            Intrinsics.checkNotNullParameter(file, "file");
            try {
                String canonicalPath = file.getCanonicalPath();
                Intrinsics.checkNotNull(canonicalPath);
                Map.Entry entry = null;
                for (Map.Entry next : this.roots.entrySet()) {
                    String path = ((File) next.getValue()).getPath();
                    Intrinsics.checkNotNull(path);
                    if (StringsKt.startsWith$default(canonicalPath, path, false, 2, (Object) null) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                        entry = next;
                    }
                }
                if (entry != null) {
                    String path2 = ((File) entry.getValue()).getPath();
                    Intrinsics.checkNotNull(path2);
                    if (StringsKt.endsWith$default(path2, "/", false, 2, (Object) null)) {
                        str = canonicalPath.substring(path2.length());
                        Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
                    } else {
                        str = canonicalPath.substring(path2.length() + 1);
                        Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
                    }
                    return new Uri.Builder().scheme("content").authority(this.authority).encodedPath(Uri.encode((String) entry.getKey()) + "/" + Uri.encode(str, "/")).build();
                }
                throw new IllegalArgumentException(("Failed to find configured root that contains " + canonicalPath).toString());
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        public File getFileForUri(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, "uri");
            String encodedPath = uri.getEncodedPath();
            Intrinsics.checkNotNull(encodedPath);
            int indexOf$default = StringsKt.indexOf$default((CharSequence) encodedPath, (char) IOUtils.DIR_SEPARATOR_UNIX, 1, false, 4, (Object) null);
            String substring = encodedPath.substring(1, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            String decode = Uri.decode(substring);
            String substring2 = encodedPath.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            String decode2 = Uri.decode(substring2);
            Intrinsics.checkNotNullExpressionValue(decode2, "decode(...)");
            File file = this.roots.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    Intrinsics.checkNotNull(canonicalFile);
                    if (FilesKt.startsWith(canonicalFile, file)) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
                }
            } else {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
        }
    }
}
