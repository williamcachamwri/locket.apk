package expo.modules.contacts.models;

import android.database.Cursor;
import com.google.android.gms.common.Scopes;
import expo.modules.contacts.Columns;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\f"}, d2 = {"Lexpo/modules/contacts/models/UrlAddressModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "dataAlias", "getDataAlias", "getLabelFromCursor", "cursor", "Landroid/database/Cursor;", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UrlAddressModel.kt */
public final class UrlAddressModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/website";
    private final String dataAlias = "url";

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    /* access modifiers changed from: protected */
    public String getLabelFromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        String labelFromCursor = super.getLabelFromCursor(cursor);
        if (labelFromCursor != null) {
            return labelFromCursor;
        }
        switch (cursor.getInt(cursor.getColumnIndexOrThrow(Columns.TYPE))) {
            case 1:
                return "homepage";
            case 2:
                return "blog";
            case 3:
                return Scopes.PROFILE;
            case 4:
                return "home";
            case 5:
                return "work";
            case 6:
                return "ftp";
            case 7:
                return "other";
            default:
                return "unknown";
        }
    }
}
