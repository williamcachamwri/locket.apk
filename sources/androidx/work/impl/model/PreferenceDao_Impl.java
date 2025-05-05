package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import java.util.concurrent.Callable;

public final class PreferenceDao_Impl implements PreferenceDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<Preference> __insertionAdapterOfPreference;

    public PreferenceDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPreference = new EntityInsertionAdapter<Preference>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Preference preference) {
                if (preference.mKey == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, preference.mKey);
                }
                if (preference.mValue == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, preference.mValue.longValue());
                }
            }
        };
    }

    public void insertPreference(Preference preference) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.PreferenceDao") : null;
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPreference.insert(preference);
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

    public Long getLongValue(String str) {
        ISpan span = Sentry.getSpan();
        Long l = null;
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.PreferenceDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    l = Long.valueOf(query.getLong(0));
                }
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return l;
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

    public LiveData<Long> getObservableLongValue(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"Preference"}, false, new Callable<Long>() {
            public Long call() throws Exception {
                ISpan span = Sentry.getSpan();
                Long l = null;
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.PreferenceDao") : null;
                Cursor query = DBUtil.query(PreferenceDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    if (query.moveToFirst()) {
                        if (!query.isNull(0)) {
                            l = Long.valueOf(query.getLong(0));
                        }
                    }
                    query.close();
                    if (startChild != null) {
                        startChild.finish(SpanStatus.OK);
                    }
                    return l;
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
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }
}
