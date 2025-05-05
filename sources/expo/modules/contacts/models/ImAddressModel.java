package expo.modules.contacts.models;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import expo.modules.contacts.Columns;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u0014"}, d2 = {"Lexpo/modules/contacts/models/ImAddressModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "contentValues", "Landroid/content/ContentValues;", "getContentValues", "()Landroid/content/ContentValues;", "dataAlias", "getDataAlias", "fromCursor", "", "cursor", "Landroid/database/Cursor;", "serializeService", "protocol", "", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImAddressModel.kt */
public final class ImAddressModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/im";
    private final String dataAlias = "username";

    private final String serializeService(int i) {
        switch (i) {
            case -1:
                return "custom";
            case 0:
                return "aim";
            case 1:
                return "msn";
            case 2:
                return "yahoo";
            case 3:
                return "skype";
            case 4:
                return "qq";
            case 5:
                return "googleTalk";
            case 6:
                return "icq";
            case 7:
                return "jabber";
            case 8:
                return "netmeeting";
            default:
                return "unknown";
        }
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    public void fromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        super.fromCursor(cursor);
        getMap().putString(NotificationCompat.CATEGORY_SERVICE, serializeService(cursor.getInt(cursor.getColumnIndexOrThrow(Columns.DATA_5))));
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = super.getContentValues();
        contentValues.put(Columns.DATA_5, getString(NotificationCompat.CATEGORY_SERVICE));
        return contentValues;
    }
}
