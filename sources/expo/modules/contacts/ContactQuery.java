package expo.modules.contacts;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Set;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u00020\u00118\u0006XD¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0002\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u00020\u00118\u0006XD¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0014R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\f¨\u0006\u001b"}, d2 = {"Lexpo/modules/contacts/ContactQuery;", "Lexpo/modules/kotlin/records/Record;", "()V", "fields", "", "", "getFields$annotations", "getFields", "()Ljava/util/Set;", "id", "getId$annotations", "getId", "()Ljava/lang/String;", "name", "getName$annotations", "getName", "pageOffset", "", "getPageOffset$annotations", "getPageOffset", "()I", "pageSize", "getPageSize$annotations", "getPageSize", "sort", "getSort$annotations", "getSort", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class ContactQuery implements Record {
    private final Set<String> fields = ContactsModuleKt.defaultFields;
    private final String id;
    private final String name;
    private final int pageOffset;
    private final int pageSize;
    private final String sort;

    @Field
    public static /* synthetic */ void getFields$annotations() {
    }

    @Field
    public static /* synthetic */ void getId$annotations() {
    }

    @Field
    public static /* synthetic */ void getName$annotations() {
    }

    @Field
    public static /* synthetic */ void getPageOffset$annotations() {
    }

    @Field
    public static /* synthetic */ void getPageSize$annotations() {
    }

    @Field
    public static /* synthetic */ void getSort$annotations() {
    }

    public final int getPageSize() {
        return this.pageSize;
    }

    public final int getPageOffset() {
        return this.pageOffset;
    }

    public final Set<String> getFields() {
        return this.fields;
    }

    public final String getSort() {
        return this.sort;
    }

    public final String getName() {
        return this.name;
    }

    public final String getId() {
        return this.id;
    }
}
