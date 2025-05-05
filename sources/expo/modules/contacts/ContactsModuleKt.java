package expo.modules.contacts;

import android.os.Bundle;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a\u001a\u0010\r\u001a\u00020\u000e*\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u001a\u001a\u0010\r\u001a\u00020\u000e*\u0004\u0018\u00010\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"DEFAULT_PROJECTION", "", "", "RC_EDIT_CONTACT", "", "defaultFields", "", "safeGet", "T", "", "", "key", "(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Object;", "toBundle", "Landroid/os/Bundle;", "Lexpo/modules/contacts/Contact;", "keys", "Lexpo/modules/contacts/ContactPage;", "expo-contacts_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class ContactsModuleKt {
    /* access modifiers changed from: private */
    public static final List<String> DEFAULT_PROJECTION = CollectionsKt.listOf("raw_contact_id", Columns.CONTACT_ID, "lookup", Columns.MIMETYPE, Columns.DISPLAY_NAME, Columns.PHOTO_URI, Columns.PHOTO_THUMBNAIL_URI, Columns.DATA, Columns.TYPE, Columns.DATA_5, Columns.LABEL, Columns.DATA_4, Columns.DATA_6, Columns.DATA_7, Columns.DATA_8, Columns.DATA_9, Columns.DATA, Columns.DATA_4, Columns.DATA_5);
    public static final int RC_EDIT_CONTACT = 2137;
    /* access modifiers changed from: private */
    public static final Set<String> defaultFields = SetsKt.setOf("phoneNumbers", "emails", "addresses", "note", "birthday", "dates", "instantMessageAddresses", "urlAddresses", "extraNames", "relationships", "phoneticFirstName", "phoneticLastName", "phoneticMiddleName", "namePrefix", "nameSuffix", "name", "firstName", "middleName", "lastName", "nickname", "id", "jobTitle", "company", "department", "image", "imageAvailable", "note");

    public static final Bundle toBundle(ContactPage contactPage, Set<String> set) {
        List list;
        List<Contact> data;
        Intrinsics.checkNotNullParameter(set, UserMetadata.KEYDATA_FILENAME);
        if (contactPage == null || (data = contactPage.getData()) == null) {
            list = CollectionsKt.emptyList();
        } else {
            Iterable<Contact> iterable = data;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Contact map : iterable) {
                arrayList.add(map.toMap(set));
            }
            list = (List) arrayList;
        }
        boolean z = false;
        boolean hasNextPage = contactPage != null ? contactPage.getHasNextPage() : false;
        if (contactPage != null) {
            z = contactPage.getHasPreviousPage();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", new ArrayList(list));
        bundle.putBoolean("hasNextPage", hasNextPage);
        bundle.putBoolean("hasPreviousPage", z);
        return bundle;
    }

    public static final Bundle toBundle(Contact contact, Set<String> set) {
        List list;
        Intrinsics.checkNotNullParameter(set, UserMetadata.KEYDATA_FILENAME);
        Bundle map = contact != null ? contact.toMap(set) : null;
        if (map == null || (list = CollectionsKt.listOf(map)) == null) {
            list = CollectionsKt.emptyList();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", new ArrayList(list));
        bundle.putBoolean("hasNextPage", false);
        bundle.putBoolean("hasPreviousPage", false);
        return bundle;
    }

    public static final <T> T safeGet(Map<String, ? extends Object> map, String str) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(str, "key");
        T t = map.get(str);
        if (t == null) {
            return null;
        }
        return t;
    }
}
