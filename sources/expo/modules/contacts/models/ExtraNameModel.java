package expo.modules.contacts.models;

import android.database.Cursor;
import expo.modules.contacts.Columns;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000f"}, d2 = {"Lexpo/modules/contacts/models/ExtraNameModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "dataAlias", "getDataAlias", "getLabelFromCursor", "cursor", "Landroid/database/Cursor;", "mapStringToType", "", "label", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExtraNameModel.kt */
public final class ExtraNameModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/nickname";
    private final String dataAlias = "value";

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mapStringToType(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0041
            int r0 = r2.hashCode()
            switch(r0) {
                case -2028219097: goto L_0x0036;
                case -1946065477: goto L_0x002b;
                case 269062575: goto L_0x0020;
                case 688538947: goto L_0x0015;
                case 1544803905: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0041
        L_0x000a:
            java.lang.String r0 = "default"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0013
            goto L_0x0041
        L_0x0013:
            r2 = 1
            goto L_0x0042
        L_0x0015:
            java.lang.String r0 = "maidenName"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x001e
            goto L_0x0041
        L_0x001e:
            r2 = 3
            goto L_0x0042
        L_0x0020:
            java.lang.String r0 = "initials"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0029
            goto L_0x0041
        L_0x0029:
            r2 = 5
            goto L_0x0042
        L_0x002b:
            java.lang.String r0 = "otherName"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0034
            goto L_0x0041
        L_0x0034:
            r2 = 2
            goto L_0x0042
        L_0x0036:
            java.lang.String r0 = "shortName"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            r2 = 4
            goto L_0x0042
        L_0x0041:
            r2 = 0
        L_0x0042:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.models.ExtraNameModel.mapStringToType(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public String getLabelFromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String labelFromCursor = super.getLabelFromCursor(cursor);
        if (labelFromCursor != null) {
            return labelFromCursor;
        }
        int i = cursor.getInt(cursor.getColumnIndexOrThrow(Columns.TYPE));
        if (i == 1) {
            return "nickname";
        }
        if (i == 2) {
            return "otherName";
        }
        if (i == 3) {
            return "maidenName";
        }
        if (i != 4) {
            return i != 5 ? "unknown" : "initials";
        }
        return "shortName";
    }
}
