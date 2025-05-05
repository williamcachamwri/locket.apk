package expo.modules.contacts;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$11 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$11(ContactsModule contactsModule) {
        super(1);
        this.this$0 = contactsModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        ContactQuery contactQuery = objArr[0];
        if (contactQuery != null) {
            ContactQuery contactQuery2 = contactQuery;
            Promise promise = objArr[1];
            if (promise != null) {
                this.this$0.ensureReadPermission();
                return BuildersKt__Builders_commonKt.launch$default(this.this$0.getAppContext().getBackgroundCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new ContactsModule$definition$1$5$1(contactQuery2, this.this$0, promise, (Continuation<? super ContactsModule$definition$1$5$1>) null), 3, (Object) null);
            }
            throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.contacts.ContactQuery");
    }
}
