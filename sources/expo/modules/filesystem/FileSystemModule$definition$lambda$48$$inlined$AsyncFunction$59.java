package expo.modules.filesystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$59 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FileSystemModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileSystemModule$definition$lambda$48$$inlined$AsyncFunction$59(FileSystemModule fileSystemModule) {
        super(1);
        this.this$0 = fileSystemModule;
    }

    public final Object invoke(Object[] objArr) {
        Uri parse;
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        Promise promise = objArr[1];
        if (promise != null) {
            Promise promise2 = promise;
            Activity currentActivity = this.this$0.getAppContext().getCurrentActivity();
            if (currentActivity == null) {
                throw new Exceptions.MissingActivity();
            } else if (this.this$0.dirPermissionsRequest == null) {
                Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                if (!(str == null || (parse = Uri.parse(FileSystemModuleKt.slashifyFilePath(str))) == null)) {
                    intent.putExtra("android.provider.extra.INITIAL_URI", parse);
                }
                this.this$0.dirPermissionsRequest = promise2;
                currentActivity.startActivityForResult(intent, 5394);
                return Unit.INSTANCE;
            } else {
                throw new FileSystemPendingPermissionsRequestException();
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
        }
    }
}
