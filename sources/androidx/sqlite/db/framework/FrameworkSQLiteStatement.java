package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;

class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    private final SQLiteStatement mDelegate;

    FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.mDelegate = sQLiteStatement;
    }

    public void execute() {
        this.mDelegate.execute();
    }

    public int executeUpdateDelete() {
        String sQLiteStatement = this.mDelegate.toString();
        String substring = sQLiteStatement.substring(sQLiteStatement.indexOf(58) + 2);
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.query", substring) : null;
        try {
            int executeUpdateDelete = this.mDelegate.executeUpdateDelete();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            if (startChild != null) {
                startChild.finish();
            }
            return executeUpdateDelete;
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            if (startChild != null) {
                startChild.finish();
            }
            throw th;
        }
    }

    public long executeInsert() {
        String sQLiteStatement = this.mDelegate.toString();
        String substring = sQLiteStatement.substring(sQLiteStatement.indexOf(58) + 2);
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.query", substring) : null;
        try {
            long executeInsert = this.mDelegate.executeInsert();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            if (startChild != null) {
                startChild.finish();
            }
            return executeInsert;
        } catch (Exception e) {
            if (startChild != null) {
                startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                startChild.setThrowable(e);
            }
            throw e;
        } catch (Throwable th) {
            if (startChild != null) {
                startChild.finish();
            }
            throw th;
        }
    }

    public long simpleQueryForLong() {
        return this.mDelegate.simpleQueryForLong();
    }

    public String simpleQueryForString() {
        return this.mDelegate.simpleQueryForString();
    }
}
