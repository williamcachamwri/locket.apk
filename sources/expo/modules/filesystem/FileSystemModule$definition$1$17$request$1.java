package expo.modules.filesystem;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lokhttp3/RequestBody;", "requestBody", "decorate"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemModule.kt */
final class FileSystemModule$definition$1$17$request$1 implements RequestBodyDecorator {
    public static final FileSystemModule$definition$1$17$request$1 INSTANCE = new FileSystemModule$definition$1$17$request$1();

    FileSystemModule$definition$1$17$request$1() {
    }

    public final RequestBody decorate(RequestBody requestBody) {
        Intrinsics.checkNotNullParameter(requestBody, "requestBody");
        return requestBody;
    }
}
