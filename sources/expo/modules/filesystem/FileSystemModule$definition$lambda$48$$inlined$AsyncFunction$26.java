package expo.modules.filesystem;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.interfaces.filesystem.Permission;
import expo.modules.kotlin.Promise;
import io.sentry.instrumentation.file.SentryFileOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "<anonymous parameter 0>", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$5"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$26 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$26(FileSystemModule fileSystemModule) {
        super(2);
        this.this$0 = fileSystemModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        RelocatingOptions relocatingOptions = (RelocatingOptions) promise;
        Uri parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(relocatingOptions.getFrom()));
        FileSystemModule fileSystemModule = this.this$0;
        Intrinsics.checkNotNull(parse);
        fileSystemModule.ensurePermission(parse, Permission.READ, "Location '" + parse + "' isn't readable.");
        Uri parse2 = Uri.parse(FileSystemModuleKt.slashifyFilePath(relocatingOptions.getTo()));
        FileSystemModule fileSystemModule2 = this.this$0;
        Intrinsics.checkNotNull(parse2);
        fileSystemModule2.ensurePermission(parse2, Permission.WRITE);
        if (Intrinsics.areEqual((Object) parse.getScheme(), (Object) "file")) {
            File access$toFile = this.this$0.toFile(parse);
            File access$toFile2 = this.this$0.toFile(parse2);
            if (access$toFile.isDirectory()) {
                FileUtils.copyDirectory(access$toFile, access$toFile2);
            } else {
                FileUtils.copyFile(access$toFile, access$toFile2);
            }
        } else if (this.this$0.isSAFUri(parse)) {
            DocumentFile access$getNearestSAFFile = this.this$0.getNearestSAFFile(parse);
            if (access$getNearestSAFFile == null || !access$getNearestSAFFile.exists()) {
                throw new FileSystemCopyFailedException(parse);
            }
            this.this$0.transformFilesFromSAF(access$getNearestSAFFile, this.this$0.toFile(parse2), true);
        } else if (Intrinsics.areEqual((Object) parse.getScheme(), (Object) "content")) {
            InputStream openInputStream = this.this$0.getContext().getContentResolver().openInputStream(parse);
            File access$toFile3 = this.this$0.toFile(parse2);
            IOUtils.copy(openInputStream, (OutputStream) SentryFileOutputStream.Factory.create(new FileOutputStream(access$toFile3), access$toFile3));
        } else if (Intrinsics.areEqual((Object) parse.getScheme(), (Object) UriUtil.LOCAL_ASSET_SCHEME)) {
            InputStream access$openAssetInputStream = this.this$0.openAssetInputStream(parse);
            File access$toFile4 = this.this$0.toFile(parse2);
            IOUtils.copy(access$openAssetInputStream, (OutputStream) SentryFileOutputStream.Factory.create(new FileOutputStream(access$toFile4), access$toFile4));
        } else if (parse.getScheme() == null) {
            InputStream access$openResourceInputStream = this.this$0.openResourceInputStream(relocatingOptions.getFrom());
            File access$toFile5 = this.this$0.toFile(parse2);
            IOUtils.copy(access$openResourceInputStream, (OutputStream) SentryFileOutputStream.Factory.create(new FileOutputStream(access$toFile5), access$toFile5));
        } else {
            throw new IOException("Unsupported scheme for location '" + parse + "'.");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
