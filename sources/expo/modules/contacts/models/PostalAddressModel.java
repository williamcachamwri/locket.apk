package expo.modules.contacts.models;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.provider.ContactsContract;
import androidx.autofill.HintConstants;
import com.amplitude.api.Constants;
import expo.modules.contacts.Columns;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001e\u0010\u0011\u001a\u00020\u000e2\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u001c"}, d2 = {"Lexpo/modules/contacts/models/PostalAddressModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "contentValues", "Landroid/content/ContentValues;", "getContentValues", "()Landroid/content/ContentValues;", "dataAlias", "getDataAlias", "fromCursor", "", "cursor", "Landroid/database/Cursor;", "fromMap", "readableMap", "", "", "getInsertOperation", "Landroid/content/ContentProviderOperation;", "rawId", "getLabelFromCursor", "mapStringToType", "", "label", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PostalAddressModel.kt */
public final class PostalAddressModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/postal-address_v2";
    private final String dataAlias = "formattedAddress";

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    public int mapStringToType(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) "home")) {
            return 1;
        }
        return Intrinsics.areEqual((Object) str, (Object) "work") ? 2 : 3;
    }

    public void fromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        super.fromCursor(cursor);
        putString(cursor, "formattedAddress", Columns.DATA);
        putString(cursor, "street", Columns.DATA_4);
        putString(cursor, "poBox", Columns.DATA_5);
        putString(cursor, "neighborhood", Columns.DATA_6);
        putString(cursor, "city", Columns.DATA_7);
        putString(cursor, "region", Columns.DATA_8);
        putString(cursor, "state", Columns.DATA_8);
        putString(cursor, HintConstants.AUTOFILL_HINT_POSTAL_CODE, Columns.DATA_9);
        putString(cursor, Constants.AMP_TRACKING_OPTION_COUNTRY, Columns.DATA_10);
    }

    public void fromMap(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "readableMap");
        super.fromMap(map);
        mapValue(map, "region", "state");
    }

    public ContentProviderOperation getInsertOperation(String str) {
        ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        Intrinsics.checkNotNullExpressionValue(newInsert, "newInsert(...)");
        if (str == null) {
            newInsert.withValueBackReference("raw_contact_id", 0);
        } else {
            newInsert.withValue("raw_contact_id", str);
        }
        ContentProviderOperation build = newInsert.withValue(Columns.MIMETYPE, getContentType()).withValue(Columns.TYPE, getType()).withValue(Columns.DATA_4, getString("street")).withValue(Columns.DATA_7, getString("city")).withValue(Columns.DATA_8, getString("region")).withValue(Columns.DATA_9, getString(HintConstants.AUTOFILL_HINT_POSTAL_CODE)).withValue(Columns.DATA_10, getString(Constants.AMP_TRACKING_OPTION_COUNTRY)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = super.getContentValues();
        contentValues.put(Columns.DATA_4, getString("street"));
        contentValues.put(Columns.DATA_7, getString("city"));
        contentValues.put(Columns.DATA_8, getString("region"));
        contentValues.put(Columns.DATA_10, getString(Constants.AMP_TRACKING_OPTION_COUNTRY));
        contentValues.put(Columns.DATA_9, getString(HintConstants.AUTOFILL_HINT_POSTAL_CODE));
        return contentValues;
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
            return "home";
        }
        if (i != 2) {
            return i != 3 ? "unknown" : "other";
        }
        return "work";
    }
}
