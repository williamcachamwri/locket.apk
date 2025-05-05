package expo.modules.filesystem;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.documentfile.provider.DocumentFile;
import androidx.tracing.Trace;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.interfaces.filesystem.FilePermissionModuleInterface;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import io.sentry.instrumentation.file.SentryFileInputStream;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001:\u0005VWXYZB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J(\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010%\u001a\u00020&H\u0017J\u0018\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*H@¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001aH\u0002J\u0018\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0002J \u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020\u00162\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0012H\u0002J\u0010\u00104\u001a\u00020-2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00105\u001a\u0002062\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u00107\u001a\u0002082\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000208H\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010=2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u0010>\u001a\u00020?2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0010\u0010@\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010A\u001a\u0002082\u0006\u00100\u001a\u00020\u0016H\u0002J\u0012\u0010B\u001a\u0002082\b\u0010C\u001a\u0004\u0018\u00010\u0012H\u0003J\u0010\u0010D\u001a\u00020\u00122\u0006\u0010E\u001a\u00020\u0012H\u0002J\u001a\u0010F\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010G2\b\u0010H\u001a\u0004\u0018\u00010\u0012H\u0002J\u0016\u0010I\u001a\b\u0012\u0004\u0012\u0002020G2\u0006\u00100\u001a\u00020\u0016H\u0002J\u0018\u0010J\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010G2\u0006\u00100\u001a\u00020\u0016H\u0002J \u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020=2\u0006\u0010M\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020\u0015H\u0002J\u0010\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RH\u0002J\f\u0010S\u001a\u00020-*\u00020\u0016H\u0002J\f\u0010T\u001a\u00020-*\u00020\u0016H\u0002J\f\u0010U\u001a\u00020\u001a*\u00020\u0016H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0014\u001a\u00020\u0015*\u00020\u00168BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0017¨\u0006["}, d2 = {"Lexpo/modules/filesystem/FileSystemModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "client", "Lokhttp3/OkHttpClient;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "dirPermissionsRequest", "Lexpo/modules/kotlin/Promise;", "moduleCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "okHttpClient", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "taskHandlers", "", "", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "isSAFUri", "", "Landroid/net/Uri;", "(Landroid/net/Uri;)Z", "contentUriFromFile", "file", "Ljava/io/File;", "createRequestBody", "Lokhttp3/RequestBody;", "options", "Lexpo/modules/filesystem/FileSystemUploadOptions;", "decorator", "Lexpo/modules/filesystem/RequestBodyDecorator;", "createUploadRequest", "Lokhttp3/Request;", "url", "fileUriString", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "downloadResumableTask", "", "params", "Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "(Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ensureDirExists", "", "dir", "ensurePermission", "uri", "permission", "Lexpo/modules/interfaces/filesystem/Permission;", "errorMsg", "forceDelete", "getFileSize", "", "getInputStream", "Ljava/io/InputStream;", "getInputStreamBytes", "", "inputStream", "getNearestSAFFile", "Landroidx/documentfile/provider/DocumentFile;", "getOutputStream", "Ljava/io/OutputStream;", "md5", "openAssetInputStream", "openResourceInputStream", "resourceName", "parseFileUri", "uriStr", "permissionsForPath", "Ljava/util/EnumSet;", "path", "permissionsForSAFUri", "permissionsForUri", "transformFilesFromSAF", "documentFile", "outputDir", "copy", "translateHeaders", "Landroid/os/Bundle;", "headers", "Lokhttp3/Headers;", "checkIfFileDirExists", "checkIfFileExists", "toFile", "DownloadResumableTaskParams", "DownloadTaskHandler", "ProgressListener", "ProgressResponseBody", "TaskHandler", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemModule.kt */
public class FileSystemModule extends Module {
    private OkHttpClient client;
    /* access modifiers changed from: private */
    public Promise dirPermissionsRequest;
    /* access modifiers changed from: private */
    public final CoroutineScope moduleCoroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    /* access modifiers changed from: private */
    public final Map<String, TaskHandler> taskHandlers = new HashMap();

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bà\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "", "update", "", "bytesRead", "", "contentLength", "done", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    public interface ProgressListener {
        void update(long j, long j2, boolean z);
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.filesystem.FileSystemUploadType[] r0 = expo.modules.filesystem.FileSystemUploadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.filesystem.FileSystemUploadType r1 = expo.modules.filesystem.FileSystemUploadType.BINARY_CONTENT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.filesystem.FileSystemUploadType r1 = expo.modules.filesystem.FileSystemUploadType.MULTIPART     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.AppContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        AsyncFunction asyncFunction6;
        AsyncFunction asyncFunction7;
        AsyncFunction asyncFunction8;
        AsyncFunction asyncFunction9;
        AsyncFunction asyncFunction10;
        AsyncFunction asyncFunction11;
        AsyncFunction asyncFunction12;
        AsyncFunction asyncFunction13;
        AsyncFunction asyncFunction14;
        AsyncFunction asyncFunction15;
        AsyncFunction asyncFunction16;
        AsyncFunction asyncFunction17;
        AsyncFunction asyncFunction18;
        AsyncFunction asyncFunction19;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExponentFileSystem");
            moduleDefinitionBuilder.Constants((Pair<String, ? extends Object>[]) new Pair[]{TuplesKt.to("documentDirectory", Uri.fromFile(getContext().getFilesDir()).toString() + "/"), TuplesKt.to("cacheDirectory", Uri.fromFile(getContext().getCacheDir()).toString() + "/"), TuplesKt.to("bundleDirectory", "asset:///")});
            moduleDefinitionBuilder.Events("expo-file-system.downloadProgress", "expo-file-system.uploadProgress");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new FileSystemModule$definition$lambda$48$$inlined$OnCreate$1(this)));
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (InfoOptions.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getInfoAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$1.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$2(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getInfoAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(InfoOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$4.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$5(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getInfoAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (ReadingOptions.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("readAsStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$6.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("readAsStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadingOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$9.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$10(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("readAsStringAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (WritingOptions.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("writeAsStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$11.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$12.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$13(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("writeAsStringAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$14.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(WritingOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$16.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$17(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("writeAsStringAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (DeletingOptions.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("deleteAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$18.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$19(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("deleteAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$20.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DeletingOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$21.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$22(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("deleteAsync", asyncFunction4);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (RelocatingOptions.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("moveAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$23(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("moveAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$24.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$25(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("moveAsync", asyncFunction5);
            ObjectDefinitionBuilder objectDefinitionBuilder6 = moduleDefinitionBuilder;
            if (RelocatingOptions.class == Promise.class) {
                asyncFunction6 = new AsyncFunctionWithPromiseComponent("copyAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$26(this));
            } else {
                asyncFunction6 = new AsyncFunctionComponent("copyAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(RelocatingOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$27.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$28(this));
            }
            objectDefinitionBuilder6.getAsyncFunctions().put("copyAsync", asyncFunction6);
            ObjectDefinitionBuilder objectDefinitionBuilder7 = moduleDefinitionBuilder;
            if (MakeDirectoryOptions.class == Promise.class) {
                asyncFunction7 = new AsyncFunctionWithPromiseComponent("makeDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$29.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$30(this));
            } else {
                asyncFunction7 = new AsyncFunctionComponent("makeDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$31.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(MakeDirectoryOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$32.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$33(this));
            }
            objectDefinitionBuilder7.getAsyncFunctions().put("makeDirectoryAsync", asyncFunction7);
            ObjectDefinitionBuilder objectDefinitionBuilder8 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction8 = new AsyncFunctionWithPromiseComponent("readDirectoryAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$34(this));
            } else {
                asyncFunction8 = new AsyncFunctionComponent("readDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$35.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$36(this));
            }
            objectDefinitionBuilder8.getAsyncFunctions().put("readDirectoryAsync", asyncFunction8);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getTotalDiskCapacityAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithoutArgs$1());
            moduleDefinitionBuilder.getAsyncFunctions().put("getTotalDiskCapacityAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction20 = asyncFunctionComponent;
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getFreeDiskStorageAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunctionWithoutArgs$2());
            moduleDefinitionBuilder.getAsyncFunctions().put("getFreeDiskStorageAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction21 = asyncFunctionComponent2;
            ObjectDefinitionBuilder objectDefinitionBuilder9 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction9 = new AsyncFunctionWithPromiseComponent("getContentUriAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$37(this));
            } else {
                asyncFunction9 = new AsyncFunctionComponent("getContentUriAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$38.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$39(this));
            }
            objectDefinitionBuilder9.getAsyncFunctions().put("getContentUriAsync", asyncFunction9);
            ObjectDefinitionBuilder objectDefinitionBuilder10 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction10 = new AsyncFunctionWithPromiseComponent("readSAFDirectoryAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$40(this));
            } else {
                asyncFunction10 = new AsyncFunctionComponent("readSAFDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$41.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$42(this));
            }
            objectDefinitionBuilder10.getAsyncFunctions().put("readSAFDirectoryAsync", asyncFunction10);
            ObjectDefinitionBuilder objectDefinitionBuilder11 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction11 = new AsyncFunctionWithPromiseComponent("makeSAFDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$43.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$44(this));
            } else {
                asyncFunction11 = new AsyncFunctionComponent("makeSAFDirectoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$45.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$46.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$47(this));
            }
            objectDefinitionBuilder11.getAsyncFunctions().put("makeSAFDirectoryAsync", asyncFunction11);
            ObjectDefinitionBuilder objectDefinitionBuilder12 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction12 = new AsyncFunctionWithPromiseComponent("createSAFFileAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$48.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$49.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$50(this));
            } else {
                asyncFunction12 = new AsyncFunctionComponent("createSAFFileAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$51.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$52.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$53.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$54(this));
            }
            objectDefinitionBuilder12.getAsyncFunctions().put("createSAFFileAsync", asyncFunction12);
            ObjectDefinitionBuilder objectDefinitionBuilder13 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction13 = new AsyncFunctionWithPromiseComponent("requestDirectoryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$55.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$56(this));
            } else {
                asyncFunction13 = new AsyncFunctionComponent("requestDirectoryPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$57.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$58.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$59(this));
            }
            objectDefinitionBuilder13.getAsyncFunctions().put("requestDirectoryPermissionsAsync", asyncFunction13);
            ObjectDefinitionBuilder objectDefinitionBuilder14 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction14 = new AsyncFunctionWithPromiseComponent("uploadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$60.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$61.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$62.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$63(this, moduleDefinitionBuilder));
            } else {
                asyncFunction14 = new AsyncFunctionComponent("uploadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$64.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$65.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$66.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$67.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$68(this, moduleDefinitionBuilder));
            }
            objectDefinitionBuilder14.getAsyncFunctions().put("uploadAsync", asyncFunction14);
            ObjectDefinitionBuilder objectDefinitionBuilder15 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction15 = new AsyncFunctionWithPromiseComponent("uploadTaskStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$69.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$70.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$71.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$72.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$73(this));
            } else {
                asyncFunction15 = new AsyncFunctionComponent("uploadTaskStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$74.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$75.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$76.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(FileSystemUploadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$77.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$78.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$79(this));
            }
            objectDefinitionBuilder15.getAsyncFunctions().put("uploadTaskStartAsync", asyncFunction15);
            ObjectDefinitionBuilder objectDefinitionBuilder16 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction16 = new AsyncFunctionWithPromiseComponent("downloadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$80.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$81.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$82.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$83(this));
            } else {
                asyncFunction16 = new AsyncFunctionComponent("downloadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$84.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$85.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$86.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$87.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$88(this));
            }
            objectDefinitionBuilder16.getAsyncFunctions().put("downloadAsync", asyncFunction16);
            ObjectDefinitionBuilder objectDefinitionBuilder17 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction17 = new AsyncFunctionWithPromiseComponent("networkTaskCancelAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$89(this));
            } else {
                asyncFunction17 = new AsyncFunctionComponent("networkTaskCancelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$90.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$91(this));
            }
            objectDefinitionBuilder17.getAsyncFunctions().put("networkTaskCancelAsync", asyncFunction17);
            ObjectDefinitionBuilder objectDefinitionBuilder18 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction18 = new AsyncFunctionWithPromiseComponent("downloadResumableStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$92.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$93.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$94.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$95.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$96.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$97(this));
            } else {
                asyncFunction18 = new AsyncFunctionComponent("downloadResumableStartAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$98.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$99.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$100.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(DownloadOptions.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$101.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), true, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$102.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$103.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$104(this));
            }
            objectDefinitionBuilder18.getAsyncFunctions().put("downloadResumableStartAsync", asyncFunction18);
            ObjectDefinitionBuilder objectDefinitionBuilder19 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction19 = new AsyncFunctionWithPromiseComponent("downloadResumablePauseAsync", new AnyType[0], new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$105(this));
            } else {
                asyncFunction19 = new AsyncFunctionComponent("downloadResumablePauseAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$106.INSTANCE))}, new FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$107(this));
            }
            objectDefinitionBuilder19.getAsyncFunctions().put("downloadResumablePauseAsync", asyncFunction19);
            moduleDefinitionBuilder.getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new FileSystemModule$definition$lambda$48$$inlined$OnActivityResult$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new FileSystemModule$definition$lambda$48$$inlined$OnDestroy$1(this)));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    private final void checkIfFileExists(Uri uri) throws IOException {
        File file = toFile(uri);
        if (!file.exists()) {
            throw new IOException("Directory for '" + file.getPath() + "' doesn't exist.");
        }
    }

    /* access modifiers changed from: private */
    public final void checkIfFileDirExists(Uri uri) throws IOException {
        File file = toFile(uri);
        File parentFile = file.getParentFile();
        if (parentFile == null || !parentFile.exists()) {
            String path = file.getPath();
            throw new IOException("Directory for '" + path + "' doesn't exist. Please make sure directory '" + file.getParent() + "' exists before calling downloadAsync.");
        }
    }

    /* access modifiers changed from: private */
    public final void ensureDirExists(File file) throws IOException {
        if (!file.isDirectory() && !file.mkdirs()) {
            throw new IOException("Couldn't create directory '" + file + "'");
        }
    }

    private final EnumSet<Permission> permissionsForPath(String str) {
        FilePermissionModuleInterface filePermission = getAppContext().getFilePermission();
        if (filePermission != null) {
            return filePermission.getPathPermissions(getContext(), str);
        }
        return null;
    }

    private final EnumSet<Permission> permissionsForUri(Uri uri) {
        if (isSAFUri(uri)) {
            return permissionsForSAFUri(uri);
        }
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "content")) {
            return EnumSet.of(Permission.READ);
        }
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) UriUtil.LOCAL_ASSET_SCHEME)) {
            return EnumSet.of(Permission.READ);
        }
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            return permissionsForPath(uri.getPath());
        }
        if (uri.getScheme() == null) {
            return EnumSet.of(Permission.READ);
        }
        return EnumSet.noneOf(Permission.class);
    }

    private final EnumSet<Permission> permissionsForSAFUri(Uri uri) {
        DocumentFile nearestSAFFile = getNearestSAFFile(uri);
        EnumSet<Permission> noneOf = EnumSet.noneOf(Permission.class);
        if (nearestSAFFile != null) {
            if (nearestSAFFile.canRead()) {
                noneOf.add(Permission.READ);
            }
            if (nearestSAFFile.canWrite()) {
                noneOf.add(Permission.WRITE);
            }
        }
        Intrinsics.checkNotNullExpressionValue(noneOf, "apply(...)");
        return noneOf;
    }

    /* access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission, String str) throws IOException {
        EnumSet<Permission> permissionsForUri = permissionsForUri(uri);
        boolean z = false;
        if (permissionsForUri != null && permissionsForUri.contains(permission)) {
            z = true;
        }
        if (!z) {
            throw new IOException(str);
        }
    }

    /* access modifiers changed from: private */
    public final void ensurePermission(Uri uri, Permission permission) throws IOException {
        if (permission == Permission.READ) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't readable.");
        }
        if (permission == Permission.WRITE) {
            ensurePermission(uri, permission, "Location '" + uri + "' isn't writable.");
        }
        ensurePermission(uri, permission, "Location '" + uri + "' doesn't have permission '" + permission.name() + "'.");
    }

    /* access modifiers changed from: private */
    public final InputStream openAssetInputStream(Uri uri) throws IOException {
        String path = uri.getPath();
        if (path != null) {
            Intrinsics.checkNotNullExpressionValue(path, "requireNotNull(...)");
            String substring = path.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            InputStream open = getContext().getAssets().open(substring);
            Intrinsics.checkNotNullExpressionValue(open, "open(...)");
            return open;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    /* access modifiers changed from: private */
    public final InputStream openResourceInputStream(String str) throws IOException {
        int identifier = getContext().getResources().getIdentifier(str, "raw", getContext().getPackageName());
        if (identifier == 0 && (identifier = getContext().getResources().getIdentifier(str, "drawable", getContext().getPackageName())) == 0) {
            throw new FileNotFoundException("No resource found with the name '" + str + "'");
        }
        InputStream openRawResource = getContext().getResources().openRawResource(identifier);
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        return openRawResource;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b5, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b9, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00bc, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00bd, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c0, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void transformFilesFromSAF(androidx.documentfile.provider.DocumentFile r5, java.io.File r6, boolean r7) throws java.io.IOException {
        /*
            r4 = this;
            boolean r0 = r5.exists()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = r6.isDirectory()
            java.lang.String r1 = "Couldn't create folder in output dir."
            if (r0 != 0) goto L_0x0028
            java.io.File r0 = r6.getParentFile()
            if (r0 == 0) goto L_0x003b
            boolean r2 = r0.exists()
            if (r2 != 0) goto L_0x003b
            boolean r0 = r0.mkdirs()
            if (r0 == 0) goto L_0x0022
            goto L_0x003b
        L_0x0022:
            java.io.IOException r5 = new java.io.IOException
            r5.<init>(r1)
            throw r5
        L_0x0028:
            boolean r0 = r6.exists()
            if (r0 != 0) goto L_0x003b
            boolean r0 = r6.mkdirs()
            if (r0 == 0) goto L_0x0035
            goto L_0x003b
        L_0x0035:
            java.io.IOException r5 = new java.io.IOException
            r5.<init>(r1)
            throw r5
        L_0x003b:
            boolean r0 = r5.isDirectory()
            if (r0 == 0) goto L_0x005f
            androidx.documentfile.provider.DocumentFile[] r0 = r5.listFiles()
            java.lang.String r1 = "listFiles(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.length
            r2 = 0
        L_0x004c:
            if (r2 >= r1) goto L_0x0059
            r3 = r0[r2]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r4.transformFilesFromSAF(r3, r6, r7)
            int r2 = r2 + 1
            goto L_0x004c
        L_0x0059:
            if (r7 != 0) goto L_0x005e
            r5.delete()
        L_0x005e:
            return
        L_0x005f:
            java.lang.String r0 = r5.getName()
            if (r0 == 0) goto L_0x00c1
            boolean r1 = r6.isDirectory()
            if (r1 == 0) goto L_0x0075
            java.io.File r1 = new java.io.File
            java.lang.String r6 = r6.getPath()
            r1.<init>(r6, r0)
            goto L_0x007e
        L_0x0075:
            java.io.File r1 = new java.io.File
            java.lang.String r6 = r6.getPath()
            r1.<init>(r6)
        L_0x007e:
            android.content.Context r6 = r4.getContext()
            android.content.ContentResolver r6 = r6.getContentResolver()
            android.net.Uri r0 = r5.getUri()
            java.io.InputStream r6 = r6.openInputStream(r0)
            java.io.Closeable r6 = (java.io.Closeable) r6
            r0 = r6
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ all -> 0x00ba }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x00ba }
            r2.<init>(r1)     // Catch:{ all -> 0x00ba }
            java.io.FileOutputStream r1 = io.sentry.instrumentation.file.SentryFileOutputStream.Factory.create((java.io.FileOutputStream) r2, (java.io.File) r1)     // Catch:{ all -> 0x00ba }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ all -> 0x00ba }
            r2 = r1
            java.io.FileOutputStream r2 = (java.io.FileOutputStream) r2     // Catch:{ all -> 0x00b3 }
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ all -> 0x00b3 }
            org.apache.commons.io.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r2)     // Catch:{ all -> 0x00b3 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ all -> 0x00ba }
            kotlin.io.CloseableKt.closeFinally(r6, r0)
            if (r7 != 0) goto L_0x00c1
            r5.delete()
            goto L_0x00c1
        L_0x00b3:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00b5 }
        L_0x00b5:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r5)     // Catch:{ all -> 0x00ba }
            throw r7     // Catch:{ all -> 0x00ba }
        L_0x00ba:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r5)
            throw r7
        L_0x00c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.transformFilesFromSAF(androidx.documentfile.provider.DocumentFile, java.io.File, boolean):void");
    }

    /* access modifiers changed from: private */
    public final Uri contentUriFromFile(File file) {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            Uri uriForFile = FileProvider.getUriForFile(currentActivity.getApplication(), currentActivity.getApplication().getPackageName() + ".FileSystemFileProvider", file);
            Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
            return uriForFile;
        }
        throw new Exceptions.MissingActivity();
    }

    /* access modifiers changed from: private */
    public final Request createUploadRequest(String str, String str2, FileSystemUploadOptions fileSystemUploadOptions, RequestBodyDecorator requestBodyDecorator) throws IOException {
        Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(str2));
        Intrinsics.checkNotNull(parse);
        ensurePermission(parse, Permission.READ);
        checkIfFileExists(parse);
        Request.Builder url = new Request.Builder().url(str);
        Map<String, String> headers = fileSystemUploadOptions.getHeaders();
        if (headers != null) {
            for (Map.Entry next : headers.entrySet()) {
                url.addHeader((String) next.getKey(), (String) next.getValue());
            }
        }
        return url.method(fileSystemUploadOptions.getHttpMethod().getValue(), createRequestBody(fileSystemUploadOptions, requestBodyDecorator, toFile(parse))).build();
    }

    private final RequestBody createRequestBody(FileSystemUploadOptions fileSystemUploadOptions, RequestBodyDecorator requestBodyDecorator, File file) {
        int i = WhenMappings.$EnumSwitchMapping$0[fileSystemUploadOptions.getUploadType().ordinal()];
        if (i == 1) {
            return requestBodyDecorator.decorate(RequestBody.Companion.create(file, (MediaType) null));
        }
        if (i == 2) {
            MultipartBody.Builder type = new MultipartBody.Builder((String) null, 1, (DefaultConstructorMarker) null).setType(MultipartBody.FORM);
            Map<String, String> parameters = fileSystemUploadOptions.getParameters();
            if (parameters != null) {
                for (Map.Entry next : parameters.entrySet()) {
                    type.addFormDataPart((String) next.getKey(), next.getValue().toString());
                }
            }
            String mimeType = fileSystemUploadOptions.getMimeType();
            if (mimeType == null) {
                mimeType = URLConnection.guessContentTypeFromName(file.getName());
                Intrinsics.checkNotNullExpressionValue(mimeType, "guessContentTypeFromName(...)");
            }
            String fieldName = fileSystemUploadOptions.getFieldName();
            if (fieldName == null) {
                fieldName = file.getName();
            }
            Intrinsics.checkNotNull(fieldName);
            type.addFormDataPart(fieldName, file.getName(), requestBodyDecorator.decorate(RequestBody.Companion.create(file, MediaType.Companion.parse(mimeType))));
            return type.build();
        }
        throw new NoWhenBranchMatchedException();
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadResumableTaskParams;", "", "options", "Lexpo/modules/filesystem/DownloadOptions;", "call", "Lokhttp3/Call;", "file", "Ljava/io/File;", "isResume", "", "promise", "Lexpo/modules/kotlin/Promise;", "(Lexpo/modules/filesystem/DownloadOptions;Lokhttp3/Call;Ljava/io/File;ZLexpo/modules/kotlin/Promise;)V", "getCall", "()Lokhttp3/Call;", "getFile", "()Ljava/io/File;", "()Z", "getOptions", "()Lexpo/modules/filesystem/DownloadOptions;", "getPromise", "()Lexpo/modules/kotlin/Promise;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    private static final class DownloadResumableTaskParams {
        private final Call call;
        private final File file;
        private final boolean isResume;
        private final DownloadOptions options;
        private final Promise promise;

        public static /* synthetic */ DownloadResumableTaskParams copy$default(DownloadResumableTaskParams downloadResumableTaskParams, DownloadOptions downloadOptions, Call call2, File file2, boolean z, Promise promise2, int i, Object obj) {
            if ((i & 1) != 0) {
                downloadOptions = downloadResumableTaskParams.options;
            }
            if ((i & 2) != 0) {
                call2 = downloadResumableTaskParams.call;
            }
            Call call3 = call2;
            if ((i & 4) != 0) {
                file2 = downloadResumableTaskParams.file;
            }
            File file3 = file2;
            if ((i & 8) != 0) {
                z = downloadResumableTaskParams.isResume;
            }
            boolean z2 = z;
            if ((i & 16) != 0) {
                promise2 = downloadResumableTaskParams.promise;
            }
            return downloadResumableTaskParams.copy(downloadOptions, call3, file3, z2, promise2);
        }

        public final DownloadOptions component1() {
            return this.options;
        }

        public final Call component2() {
            return this.call;
        }

        public final File component3() {
            return this.file;
        }

        public final boolean component4() {
            return this.isResume;
        }

        public final Promise component5() {
            return this.promise;
        }

        public final DownloadResumableTaskParams copy(DownloadOptions downloadOptions, Call call2, File file2, boolean z, Promise promise2) {
            Intrinsics.checkNotNullParameter(downloadOptions, "options");
            Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
            Intrinsics.checkNotNullParameter(file2, "file");
            Intrinsics.checkNotNullParameter(promise2, BaseJavaModule.METHOD_TYPE_PROMISE);
            return new DownloadResumableTaskParams(downloadOptions, call2, file2, z, promise2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DownloadResumableTaskParams)) {
                return false;
            }
            DownloadResumableTaskParams downloadResumableTaskParams = (DownloadResumableTaskParams) obj;
            return Intrinsics.areEqual((Object) this.options, (Object) downloadResumableTaskParams.options) && Intrinsics.areEqual((Object) this.call, (Object) downloadResumableTaskParams.call) && Intrinsics.areEqual((Object) this.file, (Object) downloadResumableTaskParams.file) && this.isResume == downloadResumableTaskParams.isResume && Intrinsics.areEqual((Object) this.promise, (Object) downloadResumableTaskParams.promise);
        }

        public int hashCode() {
            return (((((((this.options.hashCode() * 31) + this.call.hashCode()) * 31) + this.file.hashCode()) * 31) + Boolean.hashCode(this.isResume)) * 31) + this.promise.hashCode();
        }

        public String toString() {
            DownloadOptions downloadOptions = this.options;
            Call call2 = this.call;
            File file2 = this.file;
            boolean z = this.isResume;
            return "DownloadResumableTaskParams(options=" + downloadOptions + ", call=" + call2 + ", file=" + file2 + ", isResume=" + z + ", promise=" + this.promise + ")";
        }

        public DownloadResumableTaskParams(DownloadOptions downloadOptions, Call call2, File file2, boolean z, Promise promise2) {
            Intrinsics.checkNotNullParameter(downloadOptions, "options");
            Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
            Intrinsics.checkNotNullParameter(file2, "file");
            Intrinsics.checkNotNullParameter(promise2, BaseJavaModule.METHOD_TYPE_PROMISE);
            this.options = downloadOptions;
            this.call = call2;
            this.file = file2;
            this.isResume = z;
            this.promise = promise2;
        }

        public final DownloadOptions getOptions() {
            return this.options;
        }

        public final Call getCall() {
            return this.call;
        }

        public final File getFile() {
            return this.file;
        }

        public final boolean isResume() {
            return this.isResume;
        }

        public final Promise getPromise() {
            return this.promise;
        }
    }

    /* access modifiers changed from: private */
    public final Object downloadResumableTask(DownloadResumableTaskParams downloadResumableTaskParams, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FileSystemModule$downloadResumableTask$2(downloadResumableTaskParams, this, (Continuation<? super FileSystemModule$downloadResumableTask$2>) null), continuation);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0012\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "", "call", "Lokhttp3/Call;", "(Lokhttp3/Call;)V", "getCall", "()Lokhttp3/Call;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    private static class TaskHandler {
        private final Call call;

        public TaskHandler(Call call2) {
            Intrinsics.checkNotNullParameter(call2, NotificationCompat.CATEGORY_CALL);
            this.call = call2;
        }

        public final Call getCall() {
            return this.call;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$DownloadTaskHandler;", "Lexpo/modules/filesystem/FileSystemModule$TaskHandler;", "fileUri", "Landroid/net/Uri;", "call", "Lokhttp3/Call;", "(Landroid/net/Uri;Lokhttp3/Call;)V", "getFileUri", "()Landroid/net/Uri;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    private static final class DownloadTaskHandler extends TaskHandler {
        private final Uri fileUri;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DownloadTaskHandler(Uri uri, Call call) {
            super(call);
            Intrinsics.checkNotNullParameter(uri, "fileUri");
            Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
            this.fileUri = uri;
        }

        public final Uri getFileUri() {
            return this.fileUri;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lexpo/modules/filesystem/FileSystemModule$ProgressResponseBody;", "Lokhttp3/ResponseBody;", "responseBody", "progressListener", "Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "(Lokhttp3/ResponseBody;Lexpo/modules/filesystem/FileSystemModule$ProgressListener;)V", "bufferedSource", "Lokio/BufferedSource;", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "source", "Lokio/Source;", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystemModule.kt */
    private static final class ProgressResponseBody extends ResponseBody {
        private BufferedSource bufferedSource;
        /* access modifiers changed from: private */
        public final ProgressListener progressListener;
        /* access modifiers changed from: private */
        public final ResponseBody responseBody;

        public ProgressResponseBody(ResponseBody responseBody2, ProgressListener progressListener2) {
            Intrinsics.checkNotNullParameter(progressListener2, "progressListener");
            this.responseBody = responseBody2;
            this.progressListener = progressListener2;
        }

        public MediaType contentType() {
            ResponseBody responseBody2 = this.responseBody;
            if (responseBody2 != null) {
                return responseBody2.contentType();
            }
            return null;
        }

        public long contentLength() {
            ResponseBody responseBody2 = this.responseBody;
            if (responseBody2 != null) {
                return responseBody2.contentLength();
            }
            return -1;
        }

        public BufferedSource source() {
            BufferedSource bufferedSource2 = this.bufferedSource;
            if (bufferedSource2 != null) {
                return bufferedSource2;
            }
            ResponseBody responseBody2 = this.responseBody;
            Intrinsics.checkNotNull(responseBody2);
            return Okio.buffer(source(responseBody2.source()));
        }

        private final Source source(Source source) {
            return new FileSystemModule$ProgressResponseBody$source$1(source, this);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized OkHttpClient getOkHttpClient() {
        Object obj;
        if (this.client == null) {
            OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS);
            try {
                obj = getAppContext().getLegacyModuleRegistry().getModule(CookieHandler.class);
            } catch (Exception unused) {
                obj = null;
            }
            CookieHandler cookieHandler = (CookieHandler) obj;
            if (cookieHandler != null) {
                writeTimeout.cookieJar(new JavaNetCookieJar(cookieHandler));
                this.client = writeTimeout.build();
            } else {
                throw new CookieHandlerNotFoundException();
            }
        }
        return this.client;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String md5(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r3)
            java.io.FileInputStream r3 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r0, (java.io.File) r3)
            java.io.InputStream r3 = (java.io.InputStream) r3
            java.io.Closeable r3 = (java.io.Closeable) r3
            r0 = r3
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ all -> 0x0027 }
            byte[] r0 = org.apache.commons.codec.digest.DigestUtils.md5((java.io.InputStream) r0)     // Catch:{ all -> 0x0027 }
            char[] r0 = org.apache.commons.codec.binary.Hex.encodeHex(r0)     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = "encodeHex(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = new java.lang.String     // Catch:{ all -> 0x0027 }
            r1.<init>(r0)     // Catch:{ all -> 0x0027 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r3, r0)
            return r1
        L_0x0027:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.filesystem.FileSystemModule.md5(java.io.File):java.lang.String");
    }

    /* access modifiers changed from: private */
    public final void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                IOException e = null;
                for (File file2 : listFiles) {
                    try {
                        Intrinsics.checkNotNull(file2);
                        forceDelete(file2);
                    } catch (IOException e2) {
                        e = e2;
                    }
                }
                if (e != null) {
                    throw e;
                } else if (!file.delete()) {
                    throw new IOException("Unable to delete directory " + file + ".");
                }
            } else {
                throw new IOException("Failed to list contents of " + file);
            }
        } else if (!file.delete()) {
            throw new IOException("Unable to delete file: " + file);
        }
    }

    /* access modifiers changed from: private */
    public final long getFileSize(File file) {
        Object obj;
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        Collection arrayList = new ArrayList(listFiles.length);
        for (File file2 : listFiles) {
            Intrinsics.checkNotNull(file2);
            arrayList.add(Long.valueOf(getFileSize(file2)));
        }
        Iterator it = ((List) arrayList).iterator();
        if (!it.hasNext()) {
            obj = null;
        } else {
            Object next = it.next();
            while (it.hasNext()) {
                next = Long.valueOf(((Number) next).longValue() + ((Number) it.next()).longValue());
            }
            obj = next;
        }
        Long l = (Long) obj;
        if (l != null) {
            return l.longValue();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public final InputStream getInputStream(Uri uri) throws IOException {
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            File file = toFile(uri);
            return SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        } else if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) UriUtil.LOCAL_ASSET_SCHEME)) {
            return openAssetInputStream(uri);
        } else {
            if (isSAFUri(uri)) {
                InputStream openInputStream = getContext().getContentResolver().openInputStream(uri);
                Intrinsics.checkNotNull(openInputStream);
                return openInputStream;
            }
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        }
    }

    /* access modifiers changed from: private */
    public final OutputStream getOutputStream(Uri uri) throws IOException {
        OutputStream outputStream;
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            File file = toFile(uri);
            outputStream = SentryFileOutputStream.Factory.create(new FileOutputStream(file), file);
        } else if (isSAFUri(uri)) {
            outputStream = getContext().getContentResolver().openOutputStream(uri);
            Intrinsics.checkNotNull(outputStream);
        } else {
            throw new IOException("Unsupported scheme for location '" + uri + "'.");
        }
        Intrinsics.checkNotNull(outputStream);
        return outputStream;
    }

    /* access modifiers changed from: private */
    public final DocumentFile getNearestSAFFile(Uri uri) {
        DocumentFile fromSingleUri = DocumentFile.fromSingleUri(getContext(), uri);
        return (fromSingleUri == null || !fromSingleUri.isFile()) ? DocumentFile.fromTreeUri(getContext(), uri) : fromSingleUri;
    }

    /* access modifiers changed from: private */
    public final File toFile(Uri uri) {
        if (uri.getPath() != null) {
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            return new File(path);
        }
        throw new IOException("Invalid Uri: " + uri);
    }

    /* access modifiers changed from: private */
    public final boolean isSAFUri(Uri uri) {
        if (!Intrinsics.areEqual((Object) uri.getScheme(), (Object) "content")) {
            return false;
        }
        String host = uri.getHost();
        return host != null ? StringsKt.startsWith$default(host, "com.android.externalstorage", false, 2, (Object) null) : false;
    }

    /* access modifiers changed from: private */
    public final String parseFileUri(String str) {
        String substring = str.substring(StringsKt.indexOf$default((CharSequence) str, (char) AbstractJsonLexerKt.COLON, 0, false, 6, (Object) null) + 3);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    /* access modifiers changed from: private */
    public final byte[] getInputStreamBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    /* access modifiers changed from: private */
    public final Bundle translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                String string = bundle.getString(name);
                bundle.putString(name, string + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return bundle;
    }
}
