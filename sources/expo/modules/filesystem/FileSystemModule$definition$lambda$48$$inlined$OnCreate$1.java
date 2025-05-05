package expo.modules.filesystem;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "expo/modules/kotlin/modules/ModuleDefinitionBuilder$OnCreate$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$OnCreate$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$OnCreate$1(FileSystemModule fileSystemModule) {
        super(0);
        this.this$0 = fileSystemModule;
    }

    public final void invoke() {
        try {
            FileSystemModule fileSystemModule = this.this$0;
            File filesDir = fileSystemModule.getContext().getFilesDir();
            Intrinsics.checkNotNullExpressionValue(filesDir, "getFilesDir(...)");
            fileSystemModule.ensureDirExists(filesDir);
            FileSystemModule fileSystemModule2 = this.this$0;
            File cacheDir = fileSystemModule2.getContext().getCacheDir();
            Intrinsics.checkNotNullExpressionValue(cacheDir, "getCacheDir(...)");
            fileSystemModule2.ensureDirExists(cacheDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
