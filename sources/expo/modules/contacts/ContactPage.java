package expo.modules.contacts;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\tHÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\tHÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lexpo/modules/contacts/ContactPage;", "", "data", "", "Lexpo/modules/contacts/Contact;", "hasPreviousPage", "", "hasNextPage", "total", "", "(Ljava/util/List;ZZI)V", "getData", "()Ljava/util/List;", "getHasNextPage", "()Z", "getHasPreviousPage", "getTotal", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class ContactPage {
    private final List<Contact> data;
    private final boolean hasNextPage;
    private final boolean hasPreviousPage;
    private final int total;

    public static /* synthetic */ ContactPage copy$default(ContactPage contactPage, List<Contact> list, boolean z, boolean z2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = contactPage.data;
        }
        if ((i2 & 2) != 0) {
            z = contactPage.hasPreviousPage;
        }
        if ((i2 & 4) != 0) {
            z2 = contactPage.hasNextPage;
        }
        if ((i2 & 8) != 0) {
            i = contactPage.total;
        }
        return contactPage.copy(list, z, z2, i);
    }

    public final List<Contact> component1() {
        return this.data;
    }

    public final boolean component2() {
        return this.hasPreviousPage;
    }

    public final boolean component3() {
        return this.hasNextPage;
    }

    public final int component4() {
        return this.total;
    }

    public final ContactPage copy(List<Contact> list, boolean z, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(list, "data");
        return new ContactPage(list, z, z2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ContactPage)) {
            return false;
        }
        ContactPage contactPage = (ContactPage) obj;
        return Intrinsics.areEqual((Object) this.data, (Object) contactPage.data) && this.hasPreviousPage == contactPage.hasPreviousPage && this.hasNextPage == contactPage.hasNextPage && this.total == contactPage.total;
    }

    public int hashCode() {
        return (((((this.data.hashCode() * 31) + Boolean.hashCode(this.hasPreviousPage)) * 31) + Boolean.hashCode(this.hasNextPage)) * 31) + Integer.hashCode(this.total);
    }

    public String toString() {
        List<Contact> list = this.data;
        boolean z = this.hasPreviousPage;
        boolean z2 = this.hasNextPage;
        return "ContactPage(data=" + list + ", hasPreviousPage=" + z + ", hasNextPage=" + z2 + ", total=" + this.total + ")";
    }

    public ContactPage(List<Contact> list, boolean z, boolean z2, int i) {
        Intrinsics.checkNotNullParameter(list, "data");
        this.data = list;
        this.hasPreviousPage = z;
        this.hasNextPage = z2;
        this.total = i;
    }

    public final List<Contact> getData() {
        return this.data;
    }

    public final boolean getHasPreviousPage() {
        return this.hasPreviousPage;
    }

    public final boolean getHasNextPage() {
        return this.hasNextPage;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ContactPage(List list, boolean z, boolean z2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? list.size() : i);
    }

    public final int getTotal() {
        return this.total;
    }
}
