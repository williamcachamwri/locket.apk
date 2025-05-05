package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import java.util.ArrayList;
import java.util.List;

public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SystemIdInfo> __insertionAdapterOfSystemIdInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveSystemIdInfo;

    public SystemIdInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSystemIdInfo = new EntityInsertionAdapter<SystemIdInfo>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`system_id`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SystemIdInfo systemIdInfo) {
                if (systemIdInfo.workSpecId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, systemIdInfo.workSpecId);
                }
                supportSQLiteStatement.bindLong(2, (long) systemIdInfo.systemId);
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public void insertSystemIdInfo(SystemIdInfo systemIdInfo) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.SystemIdInfoDao") : null;
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSystemIdInfo.insert(systemIdInfo);
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            throw th;
        }
    }

    public void removeSystemIdInfo(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.SystemIdInfoDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveSystemIdInfo.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfRemoveSystemIdInfo.release(acquire);
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfRemoveSystemIdInfo.release(acquire);
            throw th;
        }
    }

    public SystemIdInfo getSystemIdInfo(String str) {
        ISpan span = Sentry.getSpan();
        SystemIdInfo systemIdInfo = null;
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.SystemIdInfoDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT `SystemIdInfo`.`work_spec_id` AS `work_spec_id`, `SystemIdInfo`.`system_id` AS `system_id` FROM SystemIdInfo WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "work_spec_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "system_id");
            if (query.moveToFirst()) {
                systemIdInfo = new SystemIdInfo(query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2));
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return systemIdInfo;
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            query.close();
            if (startChild != null) {
                startChild.finish();
            }
            acquire.release();
            throw th;
        }
    }

    public List<String> getWorkSpecIds() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.SystemIdInfoDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT work_spec_id FROM SystemIdInfo", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return arrayList;
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            query.close();
            if (startChild != null) {
                startChild.finish();
            }
            acquire.release();
            throw th;
        }
    }
}
