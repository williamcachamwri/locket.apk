package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.Data;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import java.util.ArrayList;
import java.util.List;

public final class WorkProgressDao_Impl implements WorkProgressDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkProgress> __insertionAdapterOfWorkProgress;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public WorkProgressDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkProgress = new EntityInsertionAdapter<WorkProgress>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkProgress workProgress) {
                if (workProgress.mWorkSpecId == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, workProgress.mWorkSpecId);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workProgress.mProgress);
                if (byteArrayInternal == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindBlob(2, byteArrayInternal);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE from WorkProgress where work_spec_id=?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM WorkProgress";
            }
        };
    }

    public void insert(WorkProgress workProgress) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkProgressDao") : null;
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkProgress.insert(workProgress);
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

    public void delete(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkProgressDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
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
            this.__preparedStmtOfDelete.release(acquire);
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
            this.__preparedStmtOfDelete.release(acquire);
            throw th;
        }
    }

    public void deleteAll() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkProgressDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteAll.acquire();
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
            this.__preparedStmtOfDeleteAll.release(acquire);
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
            this.__preparedStmtOfDeleteAll.release(acquire);
            throw th;
        }
    }

    public Data getProgressForWorkSpecId(String str) {
        ISpan span = Sentry.getSpan();
        Data data = null;
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkProgressDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT progress FROM WorkProgress WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                data = Data.fromByteArray(query.getBlob(0));
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return data;
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

    public List<Data> getProgressForWorkSpecIds(List<String> list) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkProgressDao") : null;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT progress FROM WorkProgress WHERE work_spec_id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (String next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, next);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.fromByteArray(query.getBlob(0)));
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
