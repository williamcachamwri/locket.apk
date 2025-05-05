package expo.modules.contacts;

import android.content.ContentProviderResult;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$19 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$19(ContactsModule contactsModule) {
        super(1);
        this.this$0 = contactsModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        boolean z = false;
        Map map = objArr[0];
        if (map != null) {
            Map map2 = map;
            this.this$0.ensurePermissions();
            String str = map2.containsKey("id") ? (String) map2.get("id") : null;
            Contact access$getContactById = this.this$0.getContactById(str, ContactsModuleKt.defaultFields);
            if (access$getContactById != null) {
                ContentProviderResult[] applyBatch = this.this$0.getResolver().applyBatch("com.android.contacts", this.this$0.mutateContact(access$getContactById, map2).toUpdateOperationList());
                Intrinsics.checkNotNullExpressionValue(applyBatch, "applyBatch(...)");
                if (applyBatch.length == 0) {
                    z = true;
                }
                if (!z) {
                    return str;
                }
                throw new ContactUpdateException();
            }
            throw new ContactNotFoundException();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
    }
}
