package expo.modules.contacts;

import android.content.ContentProviderResult;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "<anonymous parameter 0>", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$5"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$17 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$17(ContactsModule contactsModule) {
        super(2);
        this.this$0 = contactsModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Map map = (Map) promise;
        this.this$0.ensurePermissions();
        Contact access$getContactById = this.this$0.getContactById(map.containsKey("id") ? (String) map.get("id") : null, ContactsModuleKt.defaultFields);
        if (access$getContactById != null) {
            ContentProviderResult[] applyBatch = this.this$0.getResolver().applyBatch("com.android.contacts", this.this$0.mutateContact(access$getContactById, map).toUpdateOperationList());
            Intrinsics.checkNotNullExpressionValue(applyBatch, "applyBatch(...)");
            if (!(!(applyBatch.length == 0))) {
                throw new ContactUpdateException();
            }
            return;
        }
        throw new ContactNotFoundException();
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
