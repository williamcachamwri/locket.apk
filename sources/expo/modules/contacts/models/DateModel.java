package expo.modules.contacts.models;

import android.database.Cursor;
import expo.modules.contacts.Columns;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0014"}, d2 = {"Lexpo/modules/contacts/models/DateModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "dataAlias", "getDataAlias", "fromMap", "", "readableMap", "", "", "getLabelFromCursor", "cursor", "Landroid/database/Cursor;", "mapStringToType", "", "label", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DateModel.kt */
public final class DateModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/contact_event";
    private final String dataAlias = "date";

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    public int mapStringToType(String str) {
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -940675184) {
                if (hashCode == 106069776) {
                    return !str.equals("other") ? 0 : 2;
                }
                if (hashCode == 1069376125 && str.equals("birthday")) {
                    return 3;
                }
            } else if (str.equals("anniversary")) {
                return 1;
            }
        }
    }

    public void fromMap(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "readableMap");
        super.fromMap(map);
        String str = (String) map.get("date");
        Intrinsics.checkNotNull(str);
        boolean z = !StringsKt.startsWith$default(str, "--", false, 2, (Object) null);
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("--MM-dd", Locale.getDefault());
        if (z) {
            try {
                instance.setTime(simpleDateFormat.parse(str));
            } catch (Exception unused) {
            }
        } else {
            instance.setTime(simpleDateFormat2.parse(str));
        }
        if (z) {
            getMap().putInt("year", instance.get(1));
        }
        getMap().putInt("month", instance.get(2) + 1);
        getMap().putInt("day", instance.get(5));
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
            return "anniversary";
        }
        if (i != 2) {
            return i != 3 ? "unknown" : "birthday";
        }
        return "other";
    }
}
