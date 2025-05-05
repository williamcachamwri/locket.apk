package expo.modules.contacts;

import java.util.Comparator;
import kotlin.jvm.functions.Function2;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ContactsModule$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ Function2 f$0;

    public /* synthetic */ ContactsModule$$ExternalSyntheticLambda1(Function2 function2) {
        this.f$0 = function2;
    }

    public final int compare(Object obj, Object obj2) {
        return ContactsModule.sortContactsBy$lambda$38(this.f$0, obj, obj2);
    }
}
