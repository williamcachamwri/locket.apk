package expo.modules.imagepicker;

import expo.modules.interfaces.permissions.Permissions;
import expo.modules.kotlin.Promise;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$10 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ImagePickerModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImagePickerModule$definition$lambda$7$$inlined$AsyncFunction$10(ImagePickerModule imagePickerModule) {
        super(1);
        this.this$0 = imagePickerModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        Boolean bool = objArr[0];
        if (bool != null) {
            Boolean bool2 = bool;
            Promise promise = objArr[1];
            if (promise != null) {
                boolean booleanValue = bool2.booleanValue();
                Permissions permissions = this.this$0.getAppContext().getPermissions();
                String[] access$getMediaLibraryPermissions = this.this$0.getMediaLibraryPermissions(booleanValue);
                Permissions.getPermissionsWithPermissionsManager(permissions, promise, (String[]) Arrays.copyOf(access$getMediaLibraryPermissions, access$getMediaLibraryPermissions.length));
                return Unit.INSTANCE;
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
    }
}
