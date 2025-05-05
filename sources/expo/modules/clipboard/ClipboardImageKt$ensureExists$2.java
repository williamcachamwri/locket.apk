package expo.modules.clipboard;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardImage.kt */
final class ClipboardImageKt$ensureExists$2 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ File $this_ensureExists;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ClipboardImageKt$ensureExists$2(File file) {
        super(0);
        this.$this_ensureExists = file;
    }

    public final Boolean invoke() {
        File parentFile = this.$this_ensureExists.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        return Boolean.valueOf(this.$this_ensureExists.createNewFile());
    }
}
