package expo.modules.contacts.models;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.google.firebase.messaging.Constants;
import expo.modules.contacts.Columns;
import expo.modules.contacts.CommonProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\r\b&\u0018\u0000 72\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u001e\u0010(\u001a\u00020%2\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010+0*H\u0016J\u000e\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\bJ\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010-\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010.\u001a\u0004\u0018\u00010\b2\u0006\u0010&\u001a\u00020'H\u0014J\u0012\u0010/\u001a\u0004\u0018\u00010\b2\b\u00100\u001a\u0004\u0018\u00010\bJ\u0012\u00101\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0016J4\u00102\u001a\u00020%2\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010+0*2\b\u00100\u001a\u0004\u0018\u00010\b2\n\b\u0002\u00103\u001a\u0004\u0018\u00010\bH\u0004J$\u00104\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u00100\u001a\u0004\u0018\u00010\b2\b\u00105\u001a\u0004\u0018\u00010\bH\u0002J$\u00106\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\b\u00100\u001a\u0004\u0018\u00010\b2\b\u00105\u001a\u0004\u0018\u00010\bH\u0004R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\r\u001a\u0004\u0018\u00010\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0014\u0010\u000f\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\nR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR\u0014\u0010\u001b\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\nR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010!\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\"\u0010\nR\u000e\u0010#\u001a\u00020\bXD¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lexpo/modules/contacts/models/BaseModel;", "Lexpo/modules/contacts/CommonProvider;", "()V", "contentValues", "Landroid/content/ContentValues;", "getContentValues", "()Landroid/content/ContentValues;", "data", "", "getData", "()Ljava/lang/String;", "dataAlias", "getDataAlias", "id", "getId", "idAlias", "getIdAlias", "insertOperation", "Landroid/content/ContentProviderOperation;", "getInsertOperation", "()Landroid/content/ContentProviderOperation;", "isPrimary", "", "()I", "isPrimaryAlias", "label", "getLabel", "labelAlias", "getLabelAlias", "map", "Landroid/os/Bundle;", "getMap", "()Landroid/os/Bundle;", "type", "getType", "typeAlias", "fromCursor", "", "cursor", "Landroid/database/Cursor;", "fromMap", "readableMap", "", "", "getDeleteOperation", "rawId", "getLabelFromCursor", "getString", "key", "mapStringToType", "mapValue", "alias", "putInt", "androidKey", "putString", "Companion", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
public abstract class BaseModel implements CommonProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String dataAlias = Columns.DATA;
    private final String idAlias = "id";
    private final String labelAlias = Constants.ScionAnalytics.PARAM_LABEL;
    private final Bundle map = new Bundle();
    private final String typeAlias = "type";

    private final String isPrimaryAlias() {
        return "isPrimary";
    }

    public int mapStringToType(String str) {
        return 0;
    }

    public final Bundle getMap() {
        return this.map;
    }

    public String getDataAlias() {
        return this.dataAlias;
    }

    public String getLabelAlias() {
        return this.labelAlias;
    }

    public String getIdAlias() {
        return this.idAlias;
    }

    public static /* synthetic */ void mapValue$default(BaseModel baseModel, Map map2, String str, String str2, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                str2 = null;
            }
            baseModel.mapValue(map2, str, str2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: mapValue");
    }

    /* access modifiers changed from: protected */
    public final void mapValue(Map<String, ? extends Object> map2, String str, String str2) {
        Intrinsics.checkNotNullParameter(map2, "readableMap");
        if (map2.containsKey(str)) {
            Object obj = map2.get(str);
            if (obj instanceof Boolean) {
                Bundle bundle = this.map;
                if (str2 != null) {
                    str = str2;
                }
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof String) {
                Bundle bundle2 = this.map;
                if (str2 != null) {
                    str = str2;
                }
                bundle2.putString(str, (String) obj);
            }
        }
    }

    public void fromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        putString(cursor, getIdAlias(), Columns.ID);
        this.map.putString(getLabelAlias(), getLabelFromCursor(cursor));
        putString(cursor, getDataAlias(), Columns.DATA);
        putString(cursor, Columns.LABEL, Columns.LABEL);
        putString(cursor, this.typeAlias, Columns.TYPE);
        putInt(cursor, isPrimaryAlias(), Columns.IS_PRIMARY);
    }

    public final ContentProviderOperation getInsertOperation() {
        return getInsertOperation((String) null);
    }

    public ContentProviderOperation getInsertOperation(String str) {
        ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        Intrinsics.checkNotNullExpressionValue(newInsert, "newInsert(...)");
        if (str == null) {
            newInsert.withValueBackReference("raw_contact_id", 0);
        } else {
            newInsert.withValue("raw_contact_id", str);
        }
        ContentProviderOperation build = newInsert.withValue(Columns.MIMETYPE, getContentType()).withValue(Columns.TYPE, Integer.valueOf(mapStringToType(getLabel()))).withValue(Columns.DATA, getData()).withValue(Columns.ID, getId()).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final ContentProviderOperation getDeleteOperation(String str) {
        Intrinsics.checkNotNullParameter(str, "rawId");
        ContentProviderOperation.Builder newDelete = ContentProviderOperation.newDelete(ContactsContract.Data.CONTENT_URI);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s=? AND %s=?", Arrays.copyOf(new Object[]{Columns.MIMETYPE, "raw_contact_id"}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        ContentProviderOperation build = newDelete.withSelection(format, new String[]{getContentType(), str}).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    private final String getId() {
        return getString(getIdAlias());
    }

    public final String getLabel() {
        return getString(getLabelAlias());
    }

    public final String getData() {
        return getString(getDataAlias());
    }

    public final String getType() {
        return getString(this.typeAlias);
    }

    private final int isPrimary() {
        if (this.map.containsKey(isPrimaryAlias())) {
            return this.map.getInt(isPrimaryAlias());
        }
        return 0;
    }

    public final String getString(String str) {
        if (this.map.containsKey(str)) {
            return this.map.getString(str);
        }
        return null;
    }

    public void fromMap(Map<String, ? extends Object> map2) {
        Intrinsics.checkNotNullParameter(map2, "readableMap");
        for (String mapValue$default : map2.keySet()) {
            mapValue$default(this, map2, mapValue$default, (String) null, 4, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public String getLabelFromCursor(Cursor cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        if (cursor.getInt(cursor.getColumnIndexOrThrow(Columns.TYPE)) != 0) {
            return null;
        }
        String string = cursor.getString(cursor.getColumnIndexOrThrow(Columns.LABEL));
        return string == null ? "unknown" : string;
    }

    /* access modifiers changed from: protected */
    public final void putString(Cursor cursor, String str, String str2) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex(str2);
        if (columnIndex != -1) {
            String string = cursor.getString(columnIndex);
            if (!TextUtils.isEmpty(string)) {
                this.map.putString(str, string);
            }
        }
    }

    private final void putInt(Cursor cursor, String str, String str2) {
        int columnIndex = cursor.getColumnIndex(str2);
        if (columnIndex != -1) {
            this.map.putInt(str, cursor.getInt(columnIndex));
        }
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.MIMETYPE, getContentType());
        contentValues.put(Columns.DATA, getData());
        contentValues.put(Columns.TYPE, getType());
        contentValues.put(Columns.LABEL, getLabel());
        contentValues.put(Columns.ID, getId());
        contentValues.put(Columns.IS_PRIMARY, Integer.valueOf(isPrimary()));
        return contentValues;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\u001c\u0010\u0007\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t\u0018\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f¨\u0006\r"}, d2 = {"Lexpo/modules/contacts/models/BaseModel$Companion;", "", "()V", "decodeList", "", "T", "Lexpo/modules/contacts/models/BaseModel;", "input", "", "", "", "clazz", "Ljava/lang/Class;", "expo-contacts_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BaseModel.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T extends BaseModel> List<T> decodeList(List<? extends Map<String, ? extends Object>> list, Class<T> cls) throws IllegalAccessException, InstantiationException {
            Intrinsics.checkNotNullParameter(cls, "clazz");
            if (list == null) {
                return null;
            }
            Iterable<Map> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Map fromMap : iterable) {
                BaseModel baseModel = (BaseModel) cls.newInstance();
                baseModel.fromMap(fromMap);
                arrayList.add(baseModel);
            }
            return CollectionsKt.toMutableList((List) arrayList);
        }
    }
}
