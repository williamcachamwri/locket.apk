package expo.modules.contacts;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.contacts.ContactsModule$definition$1$5$1", f = "ContactsModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ContactsModule.kt */
final class ContactsModule$definition$1$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContactQuery $options;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContactsModule$definition$1$5$1(ContactQuery contactQuery, ContactsModule contactsModule, Promise promise, Continuation<? super ContactsModule$definition$1$5$1> continuation) {
        super(2, continuation);
        this.$options = contactQuery;
        this.this$0 = contactsModule;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContactsModule$definition$1$5$1(this.$options, this.this$0, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContactsModule$definition$1$5$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ContactPage contactPage;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$options.getId() != null) {
                this.$promise.resolve(ContactsModuleKt.toBundle(this.this$0.getContactById(this.$options.getId(), this.$options.getFields()), this.$options.getFields()));
                return Unit.INSTANCE;
            }
            String name = this.$options.getName();
            CharSequence charSequence = name;
            if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
                contactPage = this.this$0.getContactByName("%" + name + "%", this.$options.getFields(), this.$options.getSort());
            } else {
                contactPage = this.this$0.getAllContactsAsync(this.$options);
            }
            this.$promise.resolve(ContactsModuleKt.toBundle(contactPage, this.$options.getFields()));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
