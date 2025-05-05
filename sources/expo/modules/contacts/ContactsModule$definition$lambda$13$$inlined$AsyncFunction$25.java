package expo.modules.contacts;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u0001\"\u0006\b\u0003\u0010\u0005\u0018\u00012\u0010\u0010\u0006\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "P2", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$17"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$25 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$25(ContactsModule contactsModule) {
        super(2);
        this.this$0 = contactsModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[1];
        String access$getLookupKeyForContactId = this.this$0.getLookupKeyForContactId(objArr[0]);
        if (access$getLookupKeyForContactId != null) {
            Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, access$getLookupKeyForContactId);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/x-vcard");
            intent.putExtra("android.intent.extra.STREAM", withAppendedPath);
            intent.putExtra("android.intent.extra.SUBJECT", str);
            this.this$0.getActivity().startActivity(intent);
            return;
        }
        throw new LookupKeyNotFoundException();
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
