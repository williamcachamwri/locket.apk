package expo.modules.contacts.models;

import android.database.Cursor;
import expo.modules.contacts.Columns;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\r0\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0014"}, d2 = {"Lexpo/modules/contacts/models/PhoneNumberModel;", "Lexpo/modules/contacts/models/BaseModel;", "()V", "contentType", "", "getContentType", "()Ljava/lang/String;", "dataAlias", "getDataAlias", "fromMap", "", "readableMap", "", "", "getLabelFromCursor", "cursor", "Landroid/database/Cursor;", "mapStringToType", "", "label", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PhoneNumberModel.kt */
public final class PhoneNumberModel extends BaseModel {
    private final String contentType = "vnd.android.cursor.item/phone_v2";
    private final String dataAlias = "number";

    public String getContentType() {
        return this.contentType;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    public void fromMap(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "readableMap");
        super.fromMap(map);
        String data = getData();
        Intrinsics.checkNotNull(data);
        getMap().putString("digits", new Regex("[^\\d.]").replace((CharSequence) data, ""));
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x010c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mapStringToType(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x010c
            int r0 = r2.hashCode()
            switch(r0) {
                case -1171162643: goto L_0x0100;
                case -1073799780: goto L_0x00f5;
                case -1073745133: goto L_0x00e9;
                case -1073352754: goto L_0x00de;
                case -1068855134: goto L_0x00d3;
                case -863168709: goto L_0x00c7;
                case -508612650: goto L_0x00bb;
                case -172220347: goto L_0x00af;
                case 98260: goto L_0x00a1;
                case 108243: goto L_0x0093;
                case 3208415: goto L_0x0086;
                case 3241780: goto L_0x0078;
                case 3343801: goto L_0x006a;
                case 3655441: goto L_0x005d;
                case 106069776: goto L_0x0050;
                case 106426307: goto L_0x0043;
                case 108270587: goto L_0x0035;
                case 110244366: goto L_0x0027;
                case 1076099890: goto L_0x0019;
                case 1429828318: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x010c
        L_0x000b:
            java.lang.String r0 = "assistant"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0015
            goto L_0x010c
        L_0x0015:
            r2 = 19
            goto L_0x010d
        L_0x0019:
            java.lang.String r0 = "workPager"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0023
            goto L_0x010c
        L_0x0023:
            r2 = 18
            goto L_0x010d
        L_0x0027:
            java.lang.String r0 = "telex"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0031
            goto L_0x010c
        L_0x0031:
            r2 = 15
            goto L_0x010d
        L_0x0035:
            java.lang.String r0 = "radio"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x003f
            goto L_0x010c
        L_0x003f:
            r2 = 14
            goto L_0x010d
        L_0x0043:
            java.lang.String r0 = "pager"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x004d
            goto L_0x010c
        L_0x004d:
            r2 = 6
            goto L_0x010d
        L_0x0050:
            java.lang.String r0 = "other"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x005a
            goto L_0x010c
        L_0x005a:
            r2 = 7
            goto L_0x010d
        L_0x005d:
            java.lang.String r0 = "work"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0067
            goto L_0x010c
        L_0x0067:
            r2 = 3
            goto L_0x010d
        L_0x006a:
            java.lang.String r0 = "main"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0074
            goto L_0x010c
        L_0x0074:
            r2 = 12
            goto L_0x010d
        L_0x0078:
            java.lang.String r0 = "isdn"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0082
            goto L_0x010c
        L_0x0082:
            r2 = 11
            goto L_0x010d
        L_0x0086:
            java.lang.String r0 = "home"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0090
            goto L_0x010c
        L_0x0090:
            r2 = 1
            goto L_0x010d
        L_0x0093:
            java.lang.String r0 = "mms"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x009d
            goto L_0x010c
        L_0x009d:
            r2 = 20
            goto L_0x010d
        L_0x00a1:
            java.lang.String r0 = "car"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00ab
            goto L_0x010c
        L_0x00ab:
            r2 = 9
            goto L_0x010d
        L_0x00af:
            java.lang.String r0 = "callback"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00b8
            goto L_0x010c
        L_0x00b8:
            r2 = 8
            goto L_0x010d
        L_0x00bb:
            java.lang.String r0 = "companyMain"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00c4
            goto L_0x010c
        L_0x00c4:
            r2 = 10
            goto L_0x010d
        L_0x00c7:
            java.lang.String r0 = "ttyTdd"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00d0
            goto L_0x010c
        L_0x00d0:
            r2 = 16
            goto L_0x010d
        L_0x00d3:
            java.lang.String r0 = "mobile"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00dc
            goto L_0x010c
        L_0x00dc:
            r2 = 2
            goto L_0x010d
        L_0x00de:
            java.lang.String r0 = "faxWork"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00e7
            goto L_0x010c
        L_0x00e7:
            r2 = 4
            goto L_0x010d
        L_0x00e9:
            java.lang.String r0 = "workMobile"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00f2
            goto L_0x010c
        L_0x00f2:
            r2 = 17
            goto L_0x010d
        L_0x00f5:
            java.lang.String r0 = "faxHome"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x00fe
            goto L_0x010c
        L_0x00fe:
            r2 = 5
            goto L_0x010d
        L_0x0100:
            java.lang.String r0 = "otherFax"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L_0x0109
            goto L_0x010c
        L_0x0109:
            r2 = 13
            goto L_0x010d
        L_0x010c:
            r2 = 0
        L_0x010d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.models.PhoneNumberModel.mapStringToType(java.lang.String):int");
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
        if (i == 2) {
            return "mobile";
        }
        if (i != 3) {
            return i != 7 ? "unknown" : "other";
        }
        return "work";
    }
}
