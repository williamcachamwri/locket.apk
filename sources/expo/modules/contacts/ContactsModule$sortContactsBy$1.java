package expo.modules.contacts;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "p1", "Lexpo/modules/contacts/Contact;", "kotlin.jvm.PlatformType", "p2", "invoke", "(Lexpo/modules/contacts/Contact;Lexpo/modules/contacts/Contact;)Ljava/lang/Integer;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
final class ContactsModule$sortContactsBy$1 extends Lambda implements Function2<Contact, Contact, Integer> {
    public static final ContactsModule$sortContactsBy$1 INSTANCE = new ContactsModule$sortContactsBy$1();

    ContactsModule$sortContactsBy$1() {
        super(2);
    }

    public final Integer invoke(Contact contact, Contact contact2) {
        return Integer.valueOf(StringsKt.compareTo(contact.getFinalFirstName(), contact2.getFinalFirstName(), true));
    }
}
