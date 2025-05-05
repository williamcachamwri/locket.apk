package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import java.util.ArrayList;
import java.util.List;

public final class WorkNameDao_Impl implements WorkNameDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkName> __insertionAdapterOfWorkName;

    public WorkNameDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkName = new EntityInsertionAdapter<WorkName>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkName workName) {
                if (workName.name == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, workName.name);
                }
                if (workName.workSpecId == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, workName.workSpecId);
                }
            }
        };
    }

    public void insert(WorkName workName) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkNameDao") : null;
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkName.insert(workName);
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

    public List<String> getWorkSpecIdsWithName(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkNameDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT work_spec_id FROM workname WHERE name=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
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

    public List<String> getNamesForWorkSpecId(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkNameDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT name FROM workname WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
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
