package expo.modules.contacts;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0007R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"Lexpo/modules/contacts/QueryArguments;", "", "projection", "", "", "selection", "selectionArgs", "([Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)V", "getProjection", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getSelection", "()Ljava/lang/String;", "getSelectionArgs", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContactsModule.kt */
public final class QueryArguments {
    private final String[] projection;
    private final String selection;
    private final String[] selectionArgs;

    public QueryArguments(String[] strArr, String str, String[] strArr2) {
        Intrinsics.checkNotNullParameter(strArr, "projection");
        Intrinsics.checkNotNullParameter(str, "selection");
        Intrinsics.checkNotNullParameter(strArr2, "selectionArgs");
        this.projection = strArr;
        this.selection = str;
        this.selectionArgs = strArr2;
    }

    public final String[] getProjection() {
        return this.projection;
    }

    public final String getSelection() {
        return this.selection;
    }

    public final String[] getSelectionArgs() {
        return this.selectionArgs;
    }
}
