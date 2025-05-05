package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.impl.model.WorkSpec;
import io.sentry.ISpan;
import io.sentry.Sentry;
import io.sentry.SpanStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public final class WorkSpecDao_Impl implements WorkSpecDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<WorkSpec> __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetPeriodStartTime;

    public WorkSpecDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter<WorkSpec>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`period_start_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkSpec workSpec) {
                if (workSpec.id == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, workSpec.id);
                }
                supportSQLiteStatement.bindLong(2, (long) WorkTypeConverters.stateToInt(workSpec.state));
                if (workSpec.workerClassName == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, workSpec.workerClassName);
                }
                if (workSpec.inputMergerClassName == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                }
                byte[] byteArrayInternal = Data.toByteArrayInternal(workSpec.input);
                if (byteArrayInternal == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindBlob(5, byteArrayInternal);
                }
                byte[] byteArrayInternal2 = Data.toByteArrayInternal(workSpec.output);
                if (byteArrayInternal2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindBlob(6, byteArrayInternal2);
                }
                supportSQLiteStatement.bindLong(7, workSpec.initialDelay);
                supportSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                supportSQLiteStatement.bindLong(9, workSpec.flexDuration);
                supportSQLiteStatement.bindLong(10, (long) workSpec.runAttemptCount);
                supportSQLiteStatement.bindLong(11, (long) WorkTypeConverters.backoffPolicyToInt(workSpec.backoffPolicy));
                supportSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                supportSQLiteStatement.bindLong(13, workSpec.periodStartTime);
                supportSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                supportSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                supportSQLiteStatement.bindLong(16, workSpec.expedited ? 1 : 0);
                supportSQLiteStatement.bindLong(17, (long) WorkTypeConverters.outOfQuotaPolicyToInt(workSpec.outOfQuotaPolicy));
                Constraints constraints = workSpec.constraints;
                if (constraints != null) {
                    supportSQLiteStatement.bindLong(18, (long) WorkTypeConverters.networkTypeToInt(constraints.getRequiredNetworkType()));
                    supportSQLiteStatement.bindLong(19, constraints.requiresCharging() ? 1 : 0);
                    supportSQLiteStatement.bindLong(20, constraints.requiresDeviceIdle() ? 1 : 0);
                    supportSQLiteStatement.bindLong(21, constraints.requiresBatteryNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(22, constraints.requiresStorageNotLow() ? 1 : 0);
                    supportSQLiteStatement.bindLong(23, constraints.getTriggerContentUpdateDelay());
                    supportSQLiteStatement.bindLong(24, constraints.getTriggerMaxContentDelay());
                    byte[] contentUriTriggersToByteArray = WorkTypeConverters.contentUriTriggersToByteArray(constraints.getContentUriTriggers());
                    if (contentUriTriggersToByteArray == null) {
                        supportSQLiteStatement.bindNull(25);
                    } else {
                        supportSQLiteStatement.bindBlob(25, contentUriTriggersToByteArray);
                    }
                } else {
                    supportSQLiteStatement.bindNull(18);
                    supportSQLiteStatement.bindNull(19);
                    supportSQLiteStatement.bindNull(20);
                    supportSQLiteStatement.bindNull(21);
                    supportSQLiteStatement.bindNull(22);
                    supportSQLiteStatement.bindNull(23);
                    supportSQLiteStatement.bindNull(24);
                    supportSQLiteStatement.bindNull(25);
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetPeriodStartTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET period_start_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
    }

    public void insertWorkSpec(WorkSpec workSpec) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec);
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
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
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

    public void setOutput(String str, Data data) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetOutput.acquire();
        byte[] byteArrayInternal = Data.toByteArrayInternal(data);
        if (byteArrayInternal == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindBlob(1, byteArrayInternal);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
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
            this.__preparedStmtOfSetOutput.release(acquire);
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
            this.__preparedStmtOfSetOutput.release(acquire);
            throw th;
        }
    }

    public void setPeriodStartTime(String str, long j) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetPeriodStartTime.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
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
            this.__preparedStmtOfSetPeriodStartTime.release(acquire);
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
            this.__preparedStmtOfSetPeriodStartTime.release(acquire);
            throw th;
        }
    }

    public int incrementWorkSpecRunAttemptCount(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
            return executeUpdateDelete;
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
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(acquire);
            throw th;
        }
    }

    public int resetWorkSpecRunAttemptCount(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
            return executeUpdateDelete;
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
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(acquire);
            throw th;
        }
    }

    public int markWorkSpecScheduled(String str, long j) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
            return executeUpdateDelete;
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
            this.__preparedStmtOfMarkWorkSpecScheduled.release(acquire);
            throw th;
        }
    }

    public int resetScheduledState() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfResetScheduledState.acquire();
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            this.__preparedStmtOfResetScheduledState.release(acquire);
            return executeUpdateDelete;
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
            this.__preparedStmtOfResetScheduledState.release(acquire);
            throw th;
        }
    }

    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
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
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(acquire);
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
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(acquire);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0202 A[SYNTHETIC, Splitter:B:56:0x0202] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0211  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.work.impl.model.WorkSpec getWorkSpec(java.lang.String r30) {
        /*
            r29 = this;
            r1 = r29
            r0 = r30
            io.sentry.ISpan r2 = io.sentry.Sentry.getSpan()
            r3 = 0
            if (r2 == 0) goto L_0x0014
            java.lang.String r4 = "db.sql.room"
            java.lang.String r5 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r2 = r2.startChild(r4, r5)
            goto L_0x0015
        L_0x0014:
            r2 = r3
        L_0x0015:
            java.lang.String r4 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE id=?"
            r5 = 1
            androidx.room.RoomSQLiteQuery r4 = androidx.room.RoomSQLiteQuery.acquire(r4, r5)
            if (r0 != 0) goto L_0x0022
            r4.bindNull(r5)
            goto L_0x0025
        L_0x0022:
            r4.bindString(r5, r0)
        L_0x0025:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r6 = 0
            android.database.Cursor r7 = androidx.room.util.DBUtil.query(r0, r4, r6, r3)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r0)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r8 = "requires_charging"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r8)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r9 = "requires_device_idle"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r9)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r10 = "requires_battery_not_low"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r10)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r11 = "requires_storage_not_low"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r11)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r12 = "trigger_content_update_delay"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r12)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r13 = "trigger_max_content_delay"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r13)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r14 = "content_uri_triggers"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r14)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r15 = "id"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r15)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r3 = "state"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r3)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r5 = "worker_class_name"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r5)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r6 = "input_merger_class_name"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r6)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r1)     // Catch:{ Exception -> 0x01fd, all -> 0x01f9 }
            r16 = r4
            java.lang.String r4 = "output"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r4)     // Catch:{ Exception -> 0x01f7 }
            r17 = r2
            java.lang.String r2 = "initial_delay"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r18 = r2
            java.lang.String r2 = "interval_duration"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r19 = r2
            java.lang.String r2 = "flex_duration"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r20 = r2
            java.lang.String r2 = "run_attempt_count"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r21 = r2
            java.lang.String r2 = "backoff_policy"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r22 = r2
            java.lang.String r2 = "backoff_delay_duration"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r23 = r2
            java.lang.String r2 = "period_start_time"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r24 = r2
            java.lang.String r2 = "minimum_retention_duration"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r25 = r2
            java.lang.String r2 = "schedule_requested_at"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r26 = r2
            java.lang.String r2 = "run_in_foreground"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r27 = r2
            java.lang.String r2 = "out_of_quota_policy"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r2)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            boolean r28 = r7.moveToFirst()     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r28 == 0) goto L_0x01de
            java.lang.String r15 = r7.getString(r15)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            java.lang.String r5 = r7.getString(r5)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r28 = r2
            androidx.work.Constraints r2 = new androidx.work.Constraints     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r2.<init>()     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r0 = r7.getInt(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r2.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r0 = r7.getInt(r8)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r0 == 0) goto L_0x010b
            r0 = 1
            goto L_0x010c
        L_0x010b:
            r0 = 0
        L_0x010c:
            r2.setRequiresCharging(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r0 = r7.getInt(r9)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r0 == 0) goto L_0x0117
            r0 = 1
            goto L_0x0118
        L_0x0117:
            r0 = 0
        L_0x0118:
            r2.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r0 = r7.getInt(r10)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r0 == 0) goto L_0x0123
            r0 = 1
            goto L_0x0124
        L_0x0123:
            r0 = 0
        L_0x0124:
            r2.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r0 = r7.getInt(r11)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r0 == 0) goto L_0x012f
            r0 = 1
            goto L_0x0130
        L_0x012f:
            r0 = 0
        L_0x0130:
            r2.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            long r8 = r7.getLong(r12)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r2.setTriggerContentUpdateDelay(r8)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            long r8 = r7.getLong(r13)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r2.setTriggerMaxContentDelay(r8)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            byte[] r0 = r7.getBlob(r14)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.ContentUriTriggers r0 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r2.setContentUriTriggers(r0)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.impl.model.WorkSpec r0 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.<init>(r15, r5)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            int r3 = r7.getInt(r3)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.WorkInfo$State r3 = androidx.work.impl.model.WorkTypeConverters.intToState(r3)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.state = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            java.lang.String r3 = r7.getString(r6)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.inputMergerClassName = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            byte[] r1 = r7.getBlob(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.Data r1 = androidx.work.Data.fromByteArray(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.input = r1     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            byte[] r1 = r7.getBlob(r4)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.Data r1 = androidx.work.Data.fromByteArray(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.output = r1     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r18
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.initialDelay = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r19
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.intervalDuration = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r20
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.flexDuration = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r21
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.runAttemptCount = r1     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r22
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.BackoffPolicy r1 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.backoffPolicy = r1     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r23
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.backoffDelayDuration = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r24
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.periodStartTime = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r25
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.minimumRetentionDuration = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r26
            long r3 = r7.getLong(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.scheduleRequestedAt = r3     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r27
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            if (r1 == 0) goto L_0x01cb
            r5 = 1
            goto L_0x01cc
        L_0x01cb:
            r5 = 0
        L_0x01cc:
            r0.expedited = r5     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r1 = r28
            int r1 = r7.getInt(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            androidx.work.OutOfQuotaPolicy r1 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r1)     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.outOfQuotaPolicy = r1     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r0.constraints = r2     // Catch:{ Exception -> 0x01f3, all -> 0x01ef }
            r3 = r0
            goto L_0x01df
        L_0x01de:
            r3 = 0
        L_0x01df:
            r7.close()
            if (r17 == 0) goto L_0x01eb
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r2 = r17
            r2.finish(r0)
        L_0x01eb:
            r16.release()
            return r3
        L_0x01ef:
            r0 = move-exception
            r2 = r17
            goto L_0x020c
        L_0x01f3:
            r0 = move-exception
            r2 = r17
            goto L_0x0200
        L_0x01f7:
            r0 = move-exception
            goto L_0x0200
        L_0x01f9:
            r0 = move-exception
            r16 = r4
            goto L_0x020c
        L_0x01fd:
            r0 = move-exception
            r16 = r4
        L_0x0200:
            if (r2 == 0) goto L_0x020a
            io.sentry.SpanStatus r1 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x020b }
            r2.setStatus(r1)     // Catch:{ all -> 0x020b }
            r2.setThrowable(r0)     // Catch:{ all -> 0x020b }
        L_0x020a:
            throw r0     // Catch:{ all -> 0x020b }
        L_0x020b:
            r0 = move-exception
        L_0x020c:
            r7.close()
            if (r2 == 0) goto L_0x0214
            r2.finish()
        L_0x0214:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getWorkSpec(java.lang.String):androidx.work.impl.model.WorkSpec");
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x0283 A[SYNTHETIC, Splitter:B:63:0x0283] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0292  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.work.impl.model.WorkSpec[] getWorkSpecs(java.util.List<java.lang.String> r37) {
        /*
            r36 = this;
            r1 = r36
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.StringBuilder r0 = androidx.room.util.StringUtil.newStringBuilder()
            java.lang.String r4 = "SELECT "
            r0.append(r4)
            java.lang.String r4 = "*"
            r0.append(r4)
            java.lang.String r4 = " FROM workspec WHERE id IN ("
            r0.append(r4)
            int r4 = r37.size()
            androidx.room.util.StringUtil.appendPlaceholders(r0, r4)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            r5 = 0
            int r4 = r4 + r5
            androidx.room.RoomSQLiteQuery r4 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            java.util.Iterator r0 = r37.iterator()
            r7 = 1
        L_0x0042:
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x005a
            java.lang.Object r8 = r0.next()
            java.lang.String r8 = (java.lang.String) r8
            if (r8 != 0) goto L_0x0054
            r4.bindNull(r7)
            goto L_0x0057
        L_0x0054:
            r4.bindString(r7, r8)
        L_0x0057:
            int r7 = r7 + 1
            goto L_0x0042
        L_0x005a:
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r4, r5, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r7 = "requires_charging"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r8 = "requires_device_idle"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r9 = "requires_battery_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r10 = "requires_storage_not_low"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r11 = "trigger_content_update_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r12 = "trigger_max_content_delay"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r13 = "content_uri_triggers"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r14 = "id"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r15 = "state"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r5 = "worker_class_name"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r6 = "input_merger_class_name"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x027d, all -> 0x0278 }
            r16 = r4
            java.lang.String r4 = "output"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0275, all -> 0x0272 }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r18 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r19 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r20 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r21 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r22 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r23 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r24 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r25 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r26 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r27 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r28 = r3
            int r3 = r2.getCount()     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.impl.model.WorkSpec[] r3 = new androidx.work.impl.model.WorkSpec[r3]     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r29 = 0
        L_0x0121:
            boolean r30 = r2.moveToNext()     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r30 == 0) goto L_0x0258
            r30 = r3
            java.lang.String r3 = r2.getString(r14)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r31 = r14
            java.lang.String r14 = r2.getString(r5)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r32 = r5
            androidx.work.Constraints r5 = new androidx.work.Constraints     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r5.<init>()     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r33 = r2.getInt(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r34 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r33)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r5.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r0 == 0) goto L_0x014f
            r0 = 1
            goto L_0x0150
        L_0x014f:
            r0 = 0
        L_0x0150:
            r5.setRequiresCharging(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r0 == 0) goto L_0x015b
            r0 = 1
            goto L_0x015c
        L_0x015b:
            r0 = 0
        L_0x015c:
            r5.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r0 == 0) goto L_0x0167
            r0 = 1
            goto L_0x0168
        L_0x0167:
            r0 = 0
        L_0x0168:
            r5.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r0 = r2.getInt(r10)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r0 == 0) goto L_0x0173
            r0 = 1
            goto L_0x0174
        L_0x0173:
            r0 = 0
        L_0x0174:
            r5.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r0 = r7
            r33 = r8
            long r7 = r2.getLong(r11)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r5.setTriggerContentUpdateDelay(r7)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            long r7 = r2.getLong(r12)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r5.setTriggerMaxContentDelay(r7)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            byte[] r7 = r2.getBlob(r13)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.ContentUriTriggers r7 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r7)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r5.setContentUriTriggers(r7)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.impl.model.WorkSpec r7 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.<init>(r3, r14)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r3 = r2.getInt(r15)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.WorkInfo$State r3 = androidx.work.impl.model.WorkTypeConverters.intToState(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.state = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            java.lang.String r3 = r2.getString(r6)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.inputMergerClassName = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            byte[] r3 = r2.getBlob(r1)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.Data r3 = androidx.work.Data.fromByteArray(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.input = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            byte[] r3 = r2.getBlob(r4)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            androidx.work.Data r3 = androidx.work.Data.fromByteArray(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.output = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r14 = r0
            r8 = r1
            r3 = r18
            long r0 = r2.getLong(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.initialDelay = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r18 = r3
            r1 = r4
            r0 = r19
            long r3 = r2.getLong(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.intervalDuration = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r19 = r0
            r4 = r1
            r3 = r20
            long r0 = r2.getLong(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.flexDuration = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r0 = r21
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.runAttemptCount = r1     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r1 = r22
            int r20 = r2.getInt(r1)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r21 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r20)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.backoffPolicy = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r20 = r3
            r22 = r4
            r0 = r23
            long r3 = r2.getLong(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.backoffDelayDuration = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r23 = r0
            r4 = r1
            r3 = r24
            long r0 = r2.getLong(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.periodStartTime = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r24 = r3
            r1 = r4
            r0 = r25
            long r3 = r2.getLong(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.minimumRetentionDuration = r3     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r25 = r0
            r4 = r1
            r3 = r26
            long r0 = r2.getLong(r3)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r0 = r27
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            if (r1 == 0) goto L_0x0229
            r1 = 1
            goto L_0x022a
        L_0x0229:
            r1 = 0
        L_0x022a:
            r7.expedited = r1     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r1 = r28
            int r26 = r2.getInt(r1)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r27 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r26)     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r7.constraints = r5     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            r30[r29] = r7     // Catch:{ Exception -> 0x026e, all -> 0x026a }
            int r29 = r29 + 1
            r28 = r1
            r26 = r3
            r1 = r8
            r7 = r14
            r3 = r30
            r14 = r31
            r5 = r32
            r8 = r33
            r0 = r34
            r35 = r22
            r22 = r4
            r4 = r35
            goto L_0x0121
        L_0x0258:
            r30 = r3
            r2.close()
            if (r17 == 0) goto L_0x0266
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x0266:
            r16.release()
            return r30
        L_0x026a:
            r0 = move-exception
            r1 = r17
            goto L_0x028d
        L_0x026e:
            r0 = move-exception
            r1 = r17
            goto L_0x0281
        L_0x0272:
            r0 = move-exception
            r1 = r3
            goto L_0x028d
        L_0x0275:
            r0 = move-exception
            r1 = r3
            goto L_0x0281
        L_0x0278:
            r0 = move-exception
            r1 = r3
            r16 = r4
            goto L_0x028d
        L_0x027d:
            r0 = move-exception
            r1 = r3
            r16 = r4
        L_0x0281:
            if (r1 == 0) goto L_0x028b
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x028c }
            r1.setStatus(r3)     // Catch:{ all -> 0x028c }
            r1.setThrowable(r0)     // Catch:{ all -> 0x028c }
        L_0x028b:
            throw r0     // Catch:{ all -> 0x028c }
        L_0x028c:
            r0 = move-exception
        L_0x028d:
            r2.close()
            if (r1 == 0) goto L_0x0295
            r1.finish()
        L_0x0295:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getWorkSpecs(java.util.List):androidx.work.impl.model.WorkSpec[]");
    }

    public List<WorkSpec.IdAndState> getWorkSpecIdAndStatesForName(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                WorkSpec.IdAndState idAndState = new WorkSpec.IdAndState();
                idAndState.id = query.getString(columnIndexOrThrow);
                idAndState.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                arrayList.add(idAndState);
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

    public List<String> getAllWorkSpecIds() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
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

    public LiveData<List<String>> getAllWorkSpecIdsLiveData() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, true, new Callable<List<String>>() {
            public List<String> call() throws Exception {
                Cursor query;
                ISpan span = Sentry.getSpan();
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        arrayList.add(query.getString(0));
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    if (startChild != null) {
                        startChild.setStatus(SpanStatus.OK);
                    }
                    query.close();
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    return arrayList;
                } catch (Exception e) {
                    if (startChild != null) {
                        try {
                            startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                            startChild.setThrowable(e);
                        } catch (Throwable th) {
                            WorkSpecDao_Impl.this.__db.endTransaction();
                            if (startChild != null) {
                                startChild.finish();
                            }
                            throw th;
                        }
                    }
                    throw e;
                } catch (Throwable th2) {
                    query.close();
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public WorkInfo.State getState(String str) {
        ISpan span = Sentry.getSpan();
        WorkInfo.State state = null;
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                state = WorkTypeConverters.intToState(query.getInt(0));
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return state;
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: androidx.work.impl.model.WorkSpec$WorkInfoPojo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: androidx.work.impl.model.WorkSpec$WorkInfoPojo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: androidx.work.impl.model.WorkSpec$WorkInfoPojo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.util.List<androidx.work.Data>] */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.work.impl.model.WorkSpec.WorkInfoPojo getWorkStatusPojoForId(java.lang.String r12) {
        /*
            r11 = this;
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r1 = 0
            if (r0 == 0) goto L_0x0010
            java.lang.String r2 = "db.sql.room"
            java.lang.String r3 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r2, r3)
            goto L_0x0011
        L_0x0010:
            r0 = r1
        L_0x0011:
            java.lang.String r2 = "SELECT id, state, output, run_attempt_count FROM workspec WHERE id=?"
            r3 = 1
            androidx.room.RoomSQLiteQuery r2 = androidx.room.RoomSQLiteQuery.acquire(r2, r3)
            if (r12 != 0) goto L_0x001e
            r2.bindNull(r3)
            goto L_0x0021
        L_0x001e:
            r2.bindString(r3, r12)
        L_0x0021:
            androidx.room.RoomDatabase r12 = r11.__db
            r12.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r12 = r11.__db
            r12.beginTransaction()
            androidx.room.RoomDatabase r12 = r11.__db     // Catch:{ Exception -> 0x0120 }
            android.database.Cursor r12 = androidx.room.util.DBUtil.query(r12, r2, r3, r1)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r3 = "id"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r12, r3)     // Catch:{ all -> 0x0116 }
            java.lang.String r4 = "state"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r12, r4)     // Catch:{ all -> 0x0116 }
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r12, r5)     // Catch:{ all -> 0x0116 }
            java.lang.String r6 = "run_attempt_count"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r12, r6)     // Catch:{ all -> 0x0116 }
            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0116 }
            r7.<init>()     // Catch:{ all -> 0x0116 }
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap     // Catch:{ all -> 0x0116 }
            r8.<init>()     // Catch:{ all -> 0x0116 }
        L_0x0054:
            boolean r9 = r12.moveToNext()     // Catch:{ all -> 0x0116 }
            if (r9 == 0) goto L_0x008f
            boolean r9 = r12.isNull(r3)     // Catch:{ all -> 0x0116 }
            if (r9 != 0) goto L_0x0074
            java.lang.String r9 = r12.getString(r3)     // Catch:{ all -> 0x0116 }
            java.lang.Object r10 = r7.get(r9)     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r10 = (java.util.ArrayList) r10     // Catch:{ all -> 0x0116 }
            if (r10 != 0) goto L_0x0074
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0116 }
            r10.<init>()     // Catch:{ all -> 0x0116 }
            r7.put(r9, r10)     // Catch:{ all -> 0x0116 }
        L_0x0074:
            boolean r9 = r12.isNull(r3)     // Catch:{ all -> 0x0116 }
            if (r9 != 0) goto L_0x0054
            java.lang.String r9 = r12.getString(r3)     // Catch:{ all -> 0x0116 }
            java.lang.Object r10 = r8.get(r9)     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r10 = (java.util.ArrayList) r10     // Catch:{ all -> 0x0116 }
            if (r10 != 0) goto L_0x0054
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0116 }
            r10.<init>()     // Catch:{ all -> 0x0116 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0116 }
            goto L_0x0054
        L_0x008f:
            r9 = -1
            r12.moveToPosition(r9)     // Catch:{ all -> 0x0116 }
            r11.__fetchRelationshipWorkTagAsjavaLangString(r7)     // Catch:{ all -> 0x0116 }
            r11.__fetchRelationshipWorkProgressAsandroidxWorkData(r8)     // Catch:{ all -> 0x0116 }
            boolean r9 = r12.moveToFirst()     // Catch:{ all -> 0x0116 }
            if (r9 == 0) goto L_0x00f9
            boolean r9 = r12.isNull(r3)     // Catch:{ all -> 0x0116 }
            if (r9 != 0) goto L_0x00b0
            java.lang.String r9 = r12.getString(r3)     // Catch:{ all -> 0x0116 }
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x0116 }
            goto L_0x00b1
        L_0x00b0:
            r7 = r1
        L_0x00b1:
            if (r7 != 0) goto L_0x00b8
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0116 }
            r7.<init>()     // Catch:{ all -> 0x0116 }
        L_0x00b8:
            boolean r9 = r12.isNull(r3)     // Catch:{ all -> 0x0116 }
            if (r9 != 0) goto L_0x00c8
            java.lang.String r1 = r12.getString(r3)     // Catch:{ all -> 0x0116 }
            java.lang.Object r1 = r8.get(r1)     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x0116 }
        L_0x00c8:
            if (r1 != 0) goto L_0x00cf
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0116 }
            r1.<init>()     // Catch:{ all -> 0x0116 }
        L_0x00cf:
            androidx.work.impl.model.WorkSpec$WorkInfoPojo r8 = new androidx.work.impl.model.WorkSpec$WorkInfoPojo     // Catch:{ all -> 0x0116 }
            r8.<init>()     // Catch:{ all -> 0x0116 }
            java.lang.String r3 = r12.getString(r3)     // Catch:{ all -> 0x0116 }
            r8.id = r3     // Catch:{ all -> 0x0116 }
            int r3 = r12.getInt(r4)     // Catch:{ all -> 0x0116 }
            androidx.work.WorkInfo$State r3 = androidx.work.impl.model.WorkTypeConverters.intToState(r3)     // Catch:{ all -> 0x0116 }
            r8.state = r3     // Catch:{ all -> 0x0116 }
            byte[] r3 = r12.getBlob(r5)     // Catch:{ all -> 0x0116 }
            androidx.work.Data r3 = androidx.work.Data.fromByteArray(r3)     // Catch:{ all -> 0x0116 }
            r8.output = r3     // Catch:{ all -> 0x0116 }
            int r3 = r12.getInt(r6)     // Catch:{ all -> 0x0116 }
            r8.runAttemptCount = r3     // Catch:{ all -> 0x0116 }
            r8.tags = r7     // Catch:{ all -> 0x0116 }
            r8.progress = r1     // Catch:{ all -> 0x0116 }
            r1 = r8
        L_0x00f9:
            androidx.room.RoomDatabase r3 = r11.__db     // Catch:{ all -> 0x0116 }
            r3.setTransactionSuccessful()     // Catch:{ all -> 0x0116 }
            if (r0 == 0) goto L_0x0105
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.OK     // Catch:{ all -> 0x0116 }
            r0.setStatus(r3)     // Catch:{ all -> 0x0116 }
        L_0x0105:
            r12.close()     // Catch:{ Exception -> 0x0120 }
            r2.release()     // Catch:{ Exception -> 0x0120 }
            androidx.room.RoomDatabase r12 = r11.__db
            r12.endTransaction()
            if (r0 == 0) goto L_0x0115
            r0.finish()
        L_0x0115:
            return r1
        L_0x0116:
            r1 = move-exception
            r12.close()     // Catch:{ Exception -> 0x0120 }
            r2.release()     // Catch:{ Exception -> 0x0120 }
            throw r1     // Catch:{ Exception -> 0x0120 }
        L_0x011e:
            r12 = move-exception
            goto L_0x012c
        L_0x0120:
            r12 = move-exception
            if (r0 == 0) goto L_0x012b
            io.sentry.SpanStatus r1 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x011e }
            r0.setStatus(r1)     // Catch:{ all -> 0x011e }
            r0.setThrowable(r12)     // Catch:{ all -> 0x011e }
        L_0x012b:
            throw r12     // Catch:{ all -> 0x011e }
        L_0x012c:
            androidx.room.RoomDatabase r1 = r11.__db
            r1.endTransaction()
            if (r0 == 0) goto L_0x0136
            r0.finish()
        L_0x0136:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getWorkStatusPojoForId(java.lang.String):androidx.work.impl.model.WorkSpec$WorkInfoPojo");
    }

    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForIds(List<String> list) {
        Cursor query;
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
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
        this.__db.beginTransaction();
        try {
            query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap.get(string)) == null) {
                        arrayMap.put(string, new ArrayList());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap2.get(string2)) == null) {
                        arrayMap2.put(string2, new ArrayList());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            query.close();
            acquire.release();
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            return arrayList;
        } catch (Exception e) {
            if (startChild != null) {
                try {
                    startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                    startChild.setThrowable(e);
                } catch (Throwable th) {
                    this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    throw th;
                }
            }
            throw e;
        } catch (Throwable th2) {
            query.close();
            acquire.release();
            throw th2;
        }
    }

    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForIds(List<String> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (String next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, next);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() {
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Cursor query;
                ISpan span = Sentry.getSpan();
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    if (startChild != null) {
                        startChild.setStatus(SpanStatus.OK);
                    }
                    query.close();
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    return arrayList;
                } catch (Exception e) {
                    if (startChild != null) {
                        try {
                            startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                            startChild.setThrowable(e);
                        } catch (Throwable th) {
                            WorkSpecDao_Impl.this.__db.endTransaction();
                            if (startChild != null) {
                                startChild.finish();
                            }
                            throw th;
                        }
                    }
                    throw e;
                } catch (Throwable th2) {
                    query.close();
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForTag(String str) {
        Cursor query;
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap.get(string)) == null) {
                        arrayMap.put(string, new ArrayList());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap2.get(string2)) == null) {
                        arrayMap2.put(string2, new ArrayList());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            query.close();
            acquire.release();
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            return arrayList;
        } catch (Exception e) {
            if (startChild != null) {
                try {
                    startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                    startChild.setThrowable(e);
                } catch (Throwable th) {
                    this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    throw th;
                }
            }
            throw e;
        } catch (Throwable th2) {
            query.close();
            acquire.release();
            throw th2;
        }
    }

    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForTag(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() {
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Cursor query;
                ISpan span = Sentry.getSpan();
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    if (startChild != null) {
                        startChild.setStatus(SpanStatus.OK);
                    }
                    query.close();
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    return arrayList;
                } catch (Exception e) {
                    if (startChild != null) {
                        try {
                            startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                            startChild.setThrowable(e);
                        } catch (Throwable th) {
                            WorkSpecDao_Impl.this.__db.endTransaction();
                            if (startChild != null) {
                                startChild.finish();
                            }
                            throw th;
                        }
                    }
                    throw e;
                } catch (Throwable th2) {
                    query.close();
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public List<WorkSpec.WorkInfoPojo> getWorkStatusPojoForName(String str) {
        Cursor query;
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            while (query.moveToNext()) {
                if (!query.isNull(columnIndexOrThrow)) {
                    String string = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap.get(string)) == null) {
                        arrayMap.put(string, new ArrayList());
                    }
                }
                if (!query.isNull(columnIndexOrThrow)) {
                    String string2 = query.getString(columnIndexOrThrow);
                    if (((ArrayList) arrayMap2.get(string2)) == null) {
                        arrayMap2.put(string2, new ArrayList());
                    }
                }
            }
            query.moveToPosition(-1);
            __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
            __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                }
                WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                workInfoPojo.id = query.getString(columnIndexOrThrow);
                workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                workInfoPojo.tags = arrayList2;
                workInfoPojo.progress = arrayList3;
                arrayList.add(workInfoPojo);
            }
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            query.close();
            acquire.release();
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            return arrayList;
        } catch (Exception e) {
            if (startChild != null) {
                try {
                    startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                    startChild.setThrowable(e);
                } catch (Throwable th) {
                    this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    throw th;
                }
            }
            throw e;
        } catch (Throwable th2) {
            query.close();
            acquire.release();
            throw th2;
        }
    }

    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkStatusPojoLiveDataForName(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, new Callable<List<WorkSpec.WorkInfoPojo>>() {
            public List<WorkSpec.WorkInfoPojo> call() throws Exception {
                Cursor query;
                ISpan span = Sentry.getSpan();
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "state");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "output");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "run_attempt_count");
                    ArrayMap arrayMap = new ArrayMap();
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap.get(string)) == null) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        if (!query.isNull(columnIndexOrThrow)) {
                            String string2 = query.getString(columnIndexOrThrow);
                            if (((ArrayList) arrayMap2.get(string2)) == null) {
                                arrayMap2.put(string2, new ArrayList());
                            }
                        }
                    }
                    query.moveToPosition(-1);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                    WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        ArrayList arrayList2 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        ArrayList arrayList3 = !query.isNull(columnIndexOrThrow) ? (ArrayList) arrayMap2.get(query.getString(columnIndexOrThrow)) : null;
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        WorkSpec.WorkInfoPojo workInfoPojo = new WorkSpec.WorkInfoPojo();
                        workInfoPojo.id = query.getString(columnIndexOrThrow);
                        workInfoPojo.state = WorkTypeConverters.intToState(query.getInt(columnIndexOrThrow2));
                        workInfoPojo.output = Data.fromByteArray(query.getBlob(columnIndexOrThrow3));
                        workInfoPojo.runAttemptCount = query.getInt(columnIndexOrThrow4);
                        workInfoPojo.tags = arrayList2;
                        workInfoPojo.progress = arrayList3;
                        arrayList.add(workInfoPojo);
                    }
                    WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                    if (startChild != null) {
                        startChild.setStatus(SpanStatus.OK);
                    }
                    query.close();
                    WorkSpecDao_Impl.this.__db.endTransaction();
                    if (startChild != null) {
                        startChild.finish();
                    }
                    return arrayList;
                } catch (Exception e) {
                    if (startChild != null) {
                        try {
                            startChild.setStatus(SpanStatus.INTERNAL_ERROR);
                            startChild.setThrowable(e);
                        } catch (Throwable th) {
                            WorkSpecDao_Impl.this.__db.endTransaction();
                            if (startChild != null) {
                                startChild.finish();
                            }
                            throw th;
                        }
                    }
                    throw e;
                } catch (Throwable th2) {
                    query.close();
                    throw th2;
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public List<Data> getInputsFromPrerequisites(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
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

    public List<String> getUnfinishedWorkWithTag(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
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

    public List<String> getUnfinishedWorkWithName(String str) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
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

    public List<String> getAllUnfinishedWork() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
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

    public boolean hasUnfinishedWork() {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        boolean z = false;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst() && query.getInt(0) != 0) {
                z = true;
            }
            query.close();
            if (startChild != null) {
                startChild.finish(SpanStatus.OK);
            }
            acquire.release();
            return z;
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

    public LiveData<Long> getScheduleRequestedAtLiveData(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT schedule_requested_at FROM workspec WHERE id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, false, new Callable<Long>() {
            public Long call() throws Exception {
                ISpan span = Sentry.getSpan();
                Long l = null;
                ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
                Cursor query = DBUtil.query(WorkSpecDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
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

    /* JADX WARNING: Removed duplicated region for block: B:55:0x024d A[SYNTHETIC, Splitter:B:55:0x024d] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.work.impl.model.WorkSpec> getEligibleWorkForScheduling(int r36) {
        /*
            r35 = this;
            r1 = r35
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.String r0 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY period_start_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND state NOT IN (2, 3, 5))"
            r4 = 1
            androidx.room.RoomSQLiteQuery r5 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            r0 = r36
            long r6 = (long) r0
            r5.bindLong(r4, r6)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r6 = 0
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r5, r6, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r7 = "requires_charging"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r8 = "requires_device_idle"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r9 = "requires_battery_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r10 = "requires_storage_not_low"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r11 = "trigger_content_update_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r12 = "trigger_max_content_delay"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r13 = "content_uri_triggers"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r14 = "id"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r15 = "state"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r4 = "worker_class_name"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r6 = "input_merger_class_name"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            r16 = r5
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x023f, all -> 0x023c }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r18 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r19 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r20 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r21 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r22 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r23 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r24 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r25 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r26 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r27 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r28 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r29 = r5
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
        L_0x00ec:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r5 == 0) goto L_0x0224
            java.lang.String r5 = r2.getString(r14)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r30 = r14
            java.lang.String r14 = r2.getString(r4)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r31 = r4
            androidx.work.Constraints r4 = new androidx.work.Constraints     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.<init>()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r32 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r33 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r32)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0118
            r0 = 1
            goto L_0x0119
        L_0x0118:
            r0 = 0
        L_0x0119:
            r4.setRequiresCharging(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0124
            r0 = 1
            goto L_0x0125
        L_0x0124:
            r0 = 0
        L_0x0125:
            r4.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0130
            r0 = 1
            goto L_0x0131
        L_0x0130:
            r0 = 0
        L_0x0131:
            r4.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r10)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x013c
            r0 = 1
            goto L_0x013d
        L_0x013c:
            r0 = 0
        L_0x013d:
            r4.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r7
            r32 = r8
            long r7 = r2.getLong(r11)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setTriggerContentUpdateDelay(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            long r7 = r2.getLong(r12)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setTriggerMaxContentDelay(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            byte[] r7 = r2.getBlob(r13)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.ContentUriTriggers r7 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setContentUriTriggers(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.impl.model.WorkSpec r7 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.<init>(r5, r14)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r5 = r2.getInt(r15)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.WorkInfo$State r5 = androidx.work.impl.model.WorkTypeConverters.intToState(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.state = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            java.lang.String r5 = r2.getString(r6)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.inputMergerClassName = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            byte[] r5 = r2.getBlob(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.Data r5 = androidx.work.Data.fromByteArray(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.input = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r5 = r29
            byte[] r8 = r2.getBlob(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.Data r8 = androidx.work.Data.fromByteArray(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.output = r8     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r14 = r1
            r8 = r18
            r18 = r0
            long r0 = r2.getLong(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.initialDelay = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r29 = r5
            r1 = r6
            r0 = r19
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.intervalDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r19 = r0
            r6 = r1
            r5 = r20
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.flexDuration = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r21
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.runAttemptCount = r1     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r1 = r22
            int r20 = r2.getInt(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r21 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r20)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.backoffPolicy = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r20 = r5
            r22 = r6
            r0 = r23
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.backoffDelayDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r23 = r0
            r6 = r1
            r5 = r24
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.periodStartTime = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r24 = r5
            r1 = r6
            r0 = r25
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.minimumRetentionDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r25 = r0
            r6 = r1
            r5 = r26
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r27
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r1 == 0) goto L_0x01f5
            r1 = 1
            goto L_0x01f6
        L_0x01f5:
            r1 = 0
        L_0x01f6:
            r7.expedited = r1     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r1 = r28
            int r26 = r2.getInt(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r27 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r26)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.constraints = r4     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r3.add(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r28 = r1
            r26 = r5
            r1 = r14
            r7 = r18
            r14 = r30
            r4 = r31
            r0 = r33
            r18 = r8
            r8 = r32
            r34 = r22
            r22 = r6
            r6 = r34
            goto L_0x00ec
        L_0x0224:
            r2.close()
            if (r17 == 0) goto L_0x0230
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x0230:
            r16.release()
            return r3
        L_0x0234:
            r0 = move-exception
            r1 = r17
            goto L_0x0257
        L_0x0238:
            r0 = move-exception
            r1 = r17
            goto L_0x024b
        L_0x023c:
            r0 = move-exception
            r1 = r3
            goto L_0x0257
        L_0x023f:
            r0 = move-exception
            r1 = r3
            goto L_0x024b
        L_0x0242:
            r0 = move-exception
            r1 = r3
            r16 = r5
            goto L_0x0257
        L_0x0247:
            r0 = move-exception
            r1 = r3
            r16 = r5
        L_0x024b:
            if (r1 == 0) goto L_0x0255
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x0256 }
            r1.setStatus(r3)     // Catch:{ all -> 0x0256 }
            r1.setThrowable(r0)     // Catch:{ all -> 0x0256 }
        L_0x0255:
            throw r0     // Catch:{ all -> 0x0256 }
        L_0x0256:
            r0 = move-exception
        L_0x0257:
            r2.close()
            if (r1 == 0) goto L_0x025f
            r1.finish()
        L_0x025f:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getEligibleWorkForScheduling(int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x024d A[SYNTHETIC, Splitter:B:55:0x024d] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.work.impl.model.WorkSpec> getAllEligibleWorkSpecsForScheduling(int r36) {
        /*
            r35 = this;
            r1 = r35
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.String r0 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 ORDER BY period_start_time LIMIT ?"
            r4 = 1
            androidx.room.RoomSQLiteQuery r5 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            r0 = r36
            long r6 = (long) r0
            r5.bindLong(r4, r6)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r6 = 0
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r5, r6, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r7 = "requires_charging"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r8 = "requires_device_idle"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r9 = "requires_battery_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r10 = "requires_storage_not_low"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r11 = "trigger_content_update_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r12 = "trigger_max_content_delay"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r13 = "content_uri_triggers"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r14 = "id"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r15 = "state"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r4 = "worker_class_name"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r6 = "input_merger_class_name"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x0247, all -> 0x0242 }
            r16 = r5
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x023f, all -> 0x023c }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r18 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r19 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r20 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r21 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r22 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r23 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r24 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r25 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r26 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r27 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r28 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r29 = r5
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
        L_0x00ec:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r5 == 0) goto L_0x0224
            java.lang.String r5 = r2.getString(r14)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r30 = r14
            java.lang.String r14 = r2.getString(r4)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r31 = r4
            androidx.work.Constraints r4 = new androidx.work.Constraints     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.<init>()     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r32 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r33 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r32)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0118
            r0 = 1
            goto L_0x0119
        L_0x0118:
            r0 = 0
        L_0x0119:
            r4.setRequiresCharging(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0124
            r0 = 1
            goto L_0x0125
        L_0x0124:
            r0 = 0
        L_0x0125:
            r4.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x0130
            r0 = 1
            goto L_0x0131
        L_0x0130:
            r0 = 0
        L_0x0131:
            r4.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r0 = r2.getInt(r10)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r0 == 0) goto L_0x013c
            r0 = 1
            goto L_0x013d
        L_0x013c:
            r0 = 0
        L_0x013d:
            r4.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r7
            r32 = r8
            long r7 = r2.getLong(r11)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setTriggerContentUpdateDelay(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            long r7 = r2.getLong(r12)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setTriggerMaxContentDelay(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            byte[] r7 = r2.getBlob(r13)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.ContentUriTriggers r7 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r4.setContentUriTriggers(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.impl.model.WorkSpec r7 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.<init>(r5, r14)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            int r5 = r2.getInt(r15)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.WorkInfo$State r5 = androidx.work.impl.model.WorkTypeConverters.intToState(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.state = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            java.lang.String r5 = r2.getString(r6)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.inputMergerClassName = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            byte[] r5 = r2.getBlob(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.Data r5 = androidx.work.Data.fromByteArray(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.input = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r5 = r29
            byte[] r8 = r2.getBlob(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            androidx.work.Data r8 = androidx.work.Data.fromByteArray(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.output = r8     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r14 = r1
            r8 = r18
            r18 = r0
            long r0 = r2.getLong(r8)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.initialDelay = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r29 = r5
            r1 = r6
            r0 = r19
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.intervalDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r19 = r0
            r6 = r1
            r5 = r20
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.flexDuration = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r21
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.runAttemptCount = r1     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r1 = r22
            int r20 = r2.getInt(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r21 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r20)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.backoffPolicy = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r20 = r5
            r22 = r6
            r0 = r23
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.backoffDelayDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r23 = r0
            r6 = r1
            r5 = r24
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.periodStartTime = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r24 = r5
            r1 = r6
            r0 = r25
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.minimumRetentionDuration = r5     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r25 = r0
            r6 = r1
            r5 = r26
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r0 = r27
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            if (r1 == 0) goto L_0x01f5
            r1 = 1
            goto L_0x01f6
        L_0x01f5:
            r1 = 0
        L_0x01f6:
            r7.expedited = r1     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r1 = r28
            int r26 = r2.getInt(r1)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r27 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r26)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r7.constraints = r4     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r3.add(r7)     // Catch:{ Exception -> 0x0238, all -> 0x0234 }
            r28 = r1
            r26 = r5
            r1 = r14
            r7 = r18
            r14 = r30
            r4 = r31
            r0 = r33
            r18 = r8
            r8 = r32
            r34 = r22
            r22 = r6
            r6 = r34
            goto L_0x00ec
        L_0x0224:
            r2.close()
            if (r17 == 0) goto L_0x0230
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x0230:
            r16.release()
            return r3
        L_0x0234:
            r0 = move-exception
            r1 = r17
            goto L_0x0257
        L_0x0238:
            r0 = move-exception
            r1 = r17
            goto L_0x024b
        L_0x023c:
            r0 = move-exception
            r1 = r3
            goto L_0x0257
        L_0x023f:
            r0 = move-exception
            r1 = r3
            goto L_0x024b
        L_0x0242:
            r0 = move-exception
            r1 = r3
            r16 = r5
            goto L_0x0257
        L_0x0247:
            r0 = move-exception
            r1 = r3
            r16 = r5
        L_0x024b:
            if (r1 == 0) goto L_0x0255
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x0256 }
            r1.setStatus(r3)     // Catch:{ all -> 0x0256 }
            r1.setThrowable(r0)     // Catch:{ all -> 0x0256 }
        L_0x0255:
            throw r0     // Catch:{ all -> 0x0256 }
        L_0x0256:
            r0 = move-exception
        L_0x0257:
            r2.close()
            if (r1 == 0) goto L_0x025f
            r1.finish()
        L_0x025f:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getAllEligibleWorkSpecsForScheduling(int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x024b A[SYNTHETIC, Splitter:B:55:0x024b] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.work.impl.model.WorkSpec> getScheduledWork() {
        /*
            r35 = this;
            r1 = r35
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.String r0 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=0 AND schedule_requested_at<>-1"
            r4 = 0
            androidx.room.RoomSQLiteQuery r5 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r5, r4, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r6 = "requires_charging"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r7 = "requires_device_idle"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r8 = "requires_battery_not_low"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r9 = "requires_storage_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r10 = "trigger_content_update_delay"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r11 = "trigger_max_content_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r12 = "content_uri_triggers"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r13 = "id"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r14 = "state"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r15 = "worker_class_name"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r4 = "input_merger_class_name"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r16 = r5
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x023d, all -> 0x023a }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r18 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r19 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r20 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r21 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r22 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r23 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r24 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r25 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r26 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r27 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r28 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r29 = r5
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
        L_0x00e5:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r5 == 0) goto L_0x0222
            java.lang.String r5 = r2.getString(r13)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r30 = r13
            java.lang.String r13 = r2.getString(r15)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r31 = r15
            androidx.work.Constraints r15 = new androidx.work.Constraints     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.<init>()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r32 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r33 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r32)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r32 = 1
            if (r0 == 0) goto L_0x0114
            r0 = r32
            goto L_0x0115
        L_0x0114:
            r0 = 0
        L_0x0115:
            r15.setRequiresCharging(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x0121
            r0 = r32
            goto L_0x0122
        L_0x0121:
            r0 = 0
        L_0x0122:
            r15.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x012e
            r0 = r32
            goto L_0x012f
        L_0x012e:
            r0 = 0
        L_0x012f:
            r15.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x013b
            r0 = r32
            goto L_0x013c
        L_0x013b:
            r0 = 0
        L_0x013c:
            r15.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r6
            r34 = r7
            long r6 = r2.getLong(r10)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setTriggerContentUpdateDelay(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            long r6 = r2.getLong(r11)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setTriggerMaxContentDelay(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            byte[] r6 = r2.getBlob(r12)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.ContentUriTriggers r6 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setContentUriTriggers(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.impl.model.WorkSpec r6 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.<init>(r5, r13)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r5 = r2.getInt(r14)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.WorkInfo$State r5 = androidx.work.impl.model.WorkTypeConverters.intToState(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.state = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            java.lang.String r5 = r2.getString(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.inputMergerClassName = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            byte[] r5 = r2.getBlob(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.Data r5 = androidx.work.Data.fromByteArray(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.input = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r5 = r29
            byte[] r7 = r2.getBlob(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.Data r7 = androidx.work.Data.fromByteArray(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.output = r7     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r13 = r1
            r7 = r18
            r18 = r0
            long r0 = r2.getLong(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.initialDelay = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r4
            r29 = r5
            r0 = r19
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.intervalDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r19 = r0
            r5 = r1
            r4 = r20
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.flexDuration = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r21
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.runAttemptCount = r1     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r22
            int r20 = r2.getInt(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r21 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r20)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.backoffPolicy = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r20 = r4
            r22 = r5
            r0 = r23
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.backoffDelayDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r23 = r0
            r5 = r1
            r4 = r24
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.periodStartTime = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r24 = r4
            r1 = r5
            r0 = r25
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.minimumRetentionDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r25 = r0
            r5 = r1
            r4 = r26
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r27
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r1 == 0) goto L_0x01f5
            r1 = r32
            goto L_0x01f6
        L_0x01f5:
            r1 = 0
        L_0x01f6:
            r6.expedited = r1     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r28
            int r26 = r2.getInt(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r27 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r26)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.constraints = r15     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r3.add(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r28 = r1
            r26 = r4
            r1 = r13
            r6 = r18
            r4 = r22
            r13 = r30
            r15 = r31
            r0 = r33
            r22 = r5
            r18 = r7
            r7 = r34
            goto L_0x00e5
        L_0x0222:
            r2.close()
            if (r17 == 0) goto L_0x022e
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x022e:
            r16.release()
            return r3
        L_0x0232:
            r0 = move-exception
            r1 = r17
            goto L_0x0255
        L_0x0236:
            r0 = move-exception
            r1 = r17
            goto L_0x0249
        L_0x023a:
            r0 = move-exception
            r1 = r3
            goto L_0x0255
        L_0x023d:
            r0 = move-exception
            r1 = r3
            goto L_0x0249
        L_0x0240:
            r0 = move-exception
            r1 = r3
            r16 = r5
            goto L_0x0255
        L_0x0245:
            r0 = move-exception
            r1 = r3
            r16 = r5
        L_0x0249:
            if (r1 == 0) goto L_0x0253
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x0254 }
            r1.setStatus(r3)     // Catch:{ all -> 0x0254 }
            r1.setThrowable(r0)     // Catch:{ all -> 0x0254 }
        L_0x0253:
            throw r0     // Catch:{ all -> 0x0254 }
        L_0x0254:
            r0 = move-exception
        L_0x0255:
            r2.close()
            if (r1 == 0) goto L_0x025d
            r1.finish()
        L_0x025d:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getScheduledWork():java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x024b A[SYNTHETIC, Splitter:B:55:0x024b] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.work.impl.model.WorkSpec> getRunningWork() {
        /*
            r35 = this;
            r1 = r35
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.String r0 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE state=1"
            r4 = 0
            androidx.room.RoomSQLiteQuery r5 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r5, r4, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r6 = "requires_charging"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r7 = "requires_device_idle"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r8 = "requires_battery_not_low"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r9 = "requires_storage_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r10 = "trigger_content_update_delay"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r11 = "trigger_max_content_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r12 = "content_uri_triggers"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r13 = "id"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r14 = "state"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r15 = "worker_class_name"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r4 = "input_merger_class_name"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x0245, all -> 0x0240 }
            r16 = r5
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x023d, all -> 0x023a }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r18 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r19 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r20 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r21 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r22 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r23 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r24 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r25 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r26 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r27 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r28 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r29 = r5
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
        L_0x00e5:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r5 == 0) goto L_0x0222
            java.lang.String r5 = r2.getString(r13)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r30 = r13
            java.lang.String r13 = r2.getString(r15)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r31 = r15
            androidx.work.Constraints r15 = new androidx.work.Constraints     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.<init>()     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r32 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r33 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r32)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r32 = 1
            if (r0 == 0) goto L_0x0114
            r0 = r32
            goto L_0x0115
        L_0x0114:
            r0 = 0
        L_0x0115:
            r15.setRequiresCharging(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x0121
            r0 = r32
            goto L_0x0122
        L_0x0121:
            r0 = 0
        L_0x0122:
            r15.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x012e
            r0 = r32
            goto L_0x012f
        L_0x012e:
            r0 = 0
        L_0x012f:
            r15.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r0 == 0) goto L_0x013b
            r0 = r32
            goto L_0x013c
        L_0x013b:
            r0 = 0
        L_0x013c:
            r15.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r6
            r34 = r7
            long r6 = r2.getLong(r10)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setTriggerContentUpdateDelay(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            long r6 = r2.getLong(r11)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setTriggerMaxContentDelay(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            byte[] r6 = r2.getBlob(r12)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.ContentUriTriggers r6 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r15.setContentUriTriggers(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.impl.model.WorkSpec r6 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.<init>(r5, r13)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            int r5 = r2.getInt(r14)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.WorkInfo$State r5 = androidx.work.impl.model.WorkTypeConverters.intToState(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.state = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            java.lang.String r5 = r2.getString(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.inputMergerClassName = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            byte[] r5 = r2.getBlob(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.Data r5 = androidx.work.Data.fromByteArray(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.input = r5     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r5 = r29
            byte[] r7 = r2.getBlob(r5)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            androidx.work.Data r7 = androidx.work.Data.fromByteArray(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.output = r7     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r13 = r1
            r7 = r18
            r18 = r0
            long r0 = r2.getLong(r7)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.initialDelay = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r4
            r29 = r5
            r0 = r19
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.intervalDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r19 = r0
            r5 = r1
            r4 = r20
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.flexDuration = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r21
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.runAttemptCount = r1     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r22
            int r20 = r2.getInt(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r21 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r20)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.backoffPolicy = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r20 = r4
            r22 = r5
            r0 = r23
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.backoffDelayDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r23 = r0
            r5 = r1
            r4 = r24
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.periodStartTime = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r24 = r4
            r1 = r5
            r0 = r25
            long r4 = r2.getLong(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.minimumRetentionDuration = r4     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r25 = r0
            r5 = r1
            r4 = r26
            long r0 = r2.getLong(r4)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r0 = r27
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            if (r1 == 0) goto L_0x01f5
            r1 = r32
            goto L_0x01f6
        L_0x01f5:
            r1 = 0
        L_0x01f6:
            r6.expedited = r1     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r1 = r28
            int r26 = r2.getInt(r1)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r27 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r26)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r6.constraints = r15     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r3.add(r6)     // Catch:{ Exception -> 0x0236, all -> 0x0232 }
            r28 = r1
            r26 = r4
            r1 = r13
            r6 = r18
            r4 = r22
            r13 = r30
            r15 = r31
            r0 = r33
            r22 = r5
            r18 = r7
            r7 = r34
            goto L_0x00e5
        L_0x0222:
            r2.close()
            if (r17 == 0) goto L_0x022e
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x022e:
            r16.release()
            return r3
        L_0x0232:
            r0 = move-exception
            r1 = r17
            goto L_0x0255
        L_0x0236:
            r0 = move-exception
            r1 = r17
            goto L_0x0249
        L_0x023a:
            r0 = move-exception
            r1 = r3
            goto L_0x0255
        L_0x023d:
            r0 = move-exception
            r1 = r3
            goto L_0x0249
        L_0x0240:
            r0 = move-exception
            r1 = r3
            r16 = r5
            goto L_0x0255
        L_0x0245:
            r0 = move-exception
            r1 = r3
            r16 = r5
        L_0x0249:
            if (r1 == 0) goto L_0x0253
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x0254 }
            r1.setStatus(r3)     // Catch:{ all -> 0x0254 }
            r1.setThrowable(r0)     // Catch:{ all -> 0x0254 }
        L_0x0253:
            throw r0     // Catch:{ all -> 0x0254 }
        L_0x0254:
            r0 = move-exception
        L_0x0255:
            r2.close()
            if (r1 == 0) goto L_0x025d
            r1.finish()
        L_0x025d:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getRunningWork():java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x024c A[SYNTHETIC, Splitter:B:55:0x024c] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x025b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.work.impl.model.WorkSpec> getRecentlyCompletedWork(long r35) {
        /*
            r34 = this;
            r1 = r34
            io.sentry.ISpan r0 = io.sentry.Sentry.getSpan()
            r2 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r3 = "db.sql.room"
            java.lang.String r4 = "androidx.work.impl.model.WorkSpecDao"
            io.sentry.ISpan r0 = r0.startChild(r3, r4)
            r3 = r0
            goto L_0x0014
        L_0x0013:
            r3 = r2
        L_0x0014:
            java.lang.String r0 = "SELECT `required_network_type`, `requires_charging`, `requires_device_idle`, `requires_battery_not_low`, `requires_storage_not_low`, `trigger_content_update_delay`, `trigger_max_content_delay`, `content_uri_triggers`, `WorkSpec`.`id` AS `id`, `WorkSpec`.`state` AS `state`, `WorkSpec`.`worker_class_name` AS `worker_class_name`, `WorkSpec`.`input_merger_class_name` AS `input_merger_class_name`, `WorkSpec`.`input` AS `input`, `WorkSpec`.`output` AS `output`, `WorkSpec`.`initial_delay` AS `initial_delay`, `WorkSpec`.`interval_duration` AS `interval_duration`, `WorkSpec`.`flex_duration` AS `flex_duration`, `WorkSpec`.`run_attempt_count` AS `run_attempt_count`, `WorkSpec`.`backoff_policy` AS `backoff_policy`, `WorkSpec`.`backoff_delay_duration` AS `backoff_delay_duration`, `WorkSpec`.`period_start_time` AS `period_start_time`, `WorkSpec`.`minimum_retention_duration` AS `minimum_retention_duration`, `WorkSpec`.`schedule_requested_at` AS `schedule_requested_at`, `WorkSpec`.`run_in_foreground` AS `run_in_foreground`, `WorkSpec`.`out_of_quota_policy` AS `out_of_quota_policy` FROM workspec WHERE period_start_time >= ? AND state IN (2, 3, 5) ORDER BY period_start_time DESC"
            r4 = 1
            androidx.room.RoomSQLiteQuery r5 = androidx.room.RoomSQLiteQuery.acquire(r0, r4)
            r6 = r35
            r5.bindLong(r4, r6)
            androidx.room.RoomDatabase r0 = r1.__db
            r0.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r0 = r1.__db
            r6 = 0
            android.database.Cursor r2 = androidx.room.util.DBUtil.query(r0, r5, r6, r2)
            java.lang.String r0 = "required_network_type"
            int r0 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r0)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r7 = "requires_charging"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r7)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r8 = "requires_device_idle"
            int r8 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r8)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r9 = "requires_battery_not_low"
            int r9 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r9)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r10 = "requires_storage_not_low"
            int r10 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r10)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r11 = "trigger_content_update_delay"
            int r11 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r11)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r12 = "trigger_max_content_delay"
            int r12 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r12)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r13 = "content_uri_triggers"
            int r13 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r13)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r14 = "id"
            int r14 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r14)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r15 = "state"
            int r15 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r15)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r4 = "worker_class_name"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r4)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r6 = "input_merger_class_name"
            int r6 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r6)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            java.lang.String r1 = "input"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r1)     // Catch:{ Exception -> 0x0246, all -> 0x0241 }
            r16 = r5
            java.lang.String r5 = "output"
            int r5 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r5)     // Catch:{ Exception -> 0x023e, all -> 0x023b }
            r17 = r3
            java.lang.String r3 = "initial_delay"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r36 = r3
            java.lang.String r3 = "interval_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r18 = r3
            java.lang.String r3 = "flex_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r19 = r3
            java.lang.String r3 = "run_attempt_count"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r20 = r3
            java.lang.String r3 = "backoff_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r21 = r3
            java.lang.String r3 = "backoff_delay_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r22 = r3
            java.lang.String r3 = "period_start_time"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r23 = r3
            java.lang.String r3 = "minimum_retention_duration"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r24 = r3
            java.lang.String r3 = "schedule_requested_at"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r25 = r3
            java.lang.String r3 = "run_in_foreground"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r26 = r3
            java.lang.String r3 = "out_of_quota_policy"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r2, r3)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r27 = r3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r28 = r5
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
        L_0x00eb:
            boolean r5 = r2.moveToNext()     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r5 == 0) goto L_0x0223
            java.lang.String r5 = r2.getString(r14)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r29 = r14
            java.lang.String r14 = r2.getString(r4)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r30 = r4
            androidx.work.Constraints r4 = new androidx.work.Constraints     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r4.<init>()     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r31 = r2.getInt(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r32 = r0
            androidx.work.NetworkType r0 = androidx.work.impl.model.WorkTypeConverters.intToNetworkType(r31)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r4.setRequiredNetworkType(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r0 = r2.getInt(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r0 == 0) goto L_0x0117
            r0 = 1
            goto L_0x0118
        L_0x0117:
            r0 = 0
        L_0x0118:
            r4.setRequiresCharging(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r0 = r2.getInt(r8)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r0 == 0) goto L_0x0123
            r0 = 1
            goto L_0x0124
        L_0x0123:
            r0 = 0
        L_0x0124:
            r4.setRequiresDeviceIdle(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r0 = r2.getInt(r9)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r0 == 0) goto L_0x012f
            r0 = 1
            goto L_0x0130
        L_0x012f:
            r0 = 0
        L_0x0130:
            r4.setRequiresBatteryNotLow(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r0 = r2.getInt(r10)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r0 == 0) goto L_0x013b
            r0 = 1
            goto L_0x013c
        L_0x013b:
            r0 = 0
        L_0x013c:
            r4.setRequiresStorageNotLow(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r0 = r7
            r31 = r8
            long r7 = r2.getLong(r11)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r4.setTriggerContentUpdateDelay(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            long r7 = r2.getLong(r12)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r4.setTriggerMaxContentDelay(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            byte[] r7 = r2.getBlob(r13)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            androidx.work.ContentUriTriggers r7 = androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r4.setContentUriTriggers(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            androidx.work.impl.model.WorkSpec r7 = new androidx.work.impl.model.WorkSpec     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.<init>(r5, r14)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            int r5 = r2.getInt(r15)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            androidx.work.WorkInfo$State r5 = androidx.work.impl.model.WorkTypeConverters.intToState(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.state = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            java.lang.String r5 = r2.getString(r6)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.inputMergerClassName = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            byte[] r5 = r2.getBlob(r1)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            androidx.work.Data r5 = androidx.work.Data.fromByteArray(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.input = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r5 = r28
            byte[] r8 = r2.getBlob(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            androidx.work.Data r8 = androidx.work.Data.fromByteArray(r8)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.output = r8     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r8 = r36
            r14 = r0
            r36 = r1
            long r0 = r2.getLong(r8)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.initialDelay = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r28 = r5
            r1 = r6
            r0 = r18
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.intervalDuration = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r18 = r0
            r6 = r1
            r5 = r19
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.flexDuration = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r0 = r20
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.runAttemptCount = r1     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r1 = r21
            int r19 = r2.getInt(r1)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r20 = r0
            androidx.work.BackoffPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToBackoffPolicy(r19)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.backoffPolicy = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r19 = r5
            r21 = r6
            r0 = r22
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.backoffDelayDuration = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r22 = r0
            r6 = r1
            r5 = r23
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.periodStartTime = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r23 = r5
            r1 = r6
            r0 = r24
            long r5 = r2.getLong(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.minimumRetentionDuration = r5     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r24 = r0
            r6 = r1
            r5 = r25
            long r0 = r2.getLong(r5)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.scheduleRequestedAt = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r0 = r26
            int r1 = r2.getInt(r0)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            if (r1 == 0) goto L_0x01f4
            r1 = 1
            goto L_0x01f5
        L_0x01f4:
            r1 = 0
        L_0x01f5:
            r7.expedited = r1     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r1 = r27
            int r25 = r2.getInt(r1)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r26 = r0
            androidx.work.OutOfQuotaPolicy r0 = androidx.work.impl.model.WorkTypeConverters.intToOutOfQuotaPolicy(r25)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.outOfQuotaPolicy = r0     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r7.constraints = r4     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r3.add(r7)     // Catch:{ Exception -> 0x0237, all -> 0x0233 }
            r27 = r1
            r25 = r5
            r7 = r14
            r14 = r29
            r4 = r30
            r0 = r32
            r1 = r36
            r36 = r8
            r8 = r31
            r33 = r21
            r21 = r6
            r6 = r33
            goto L_0x00eb
        L_0x0223:
            r2.close()
            if (r17 == 0) goto L_0x022f
            io.sentry.SpanStatus r0 = io.sentry.SpanStatus.OK
            r1 = r17
            r1.finish(r0)
        L_0x022f:
            r16.release()
            return r3
        L_0x0233:
            r0 = move-exception
            r1 = r17
            goto L_0x0256
        L_0x0237:
            r0 = move-exception
            r1 = r17
            goto L_0x024a
        L_0x023b:
            r0 = move-exception
            r1 = r3
            goto L_0x0256
        L_0x023e:
            r0 = move-exception
            r1 = r3
            goto L_0x024a
        L_0x0241:
            r0 = move-exception
            r1 = r3
            r16 = r5
            goto L_0x0256
        L_0x0246:
            r0 = move-exception
            r1 = r3
            r16 = r5
        L_0x024a:
            if (r1 == 0) goto L_0x0254
            io.sentry.SpanStatus r3 = io.sentry.SpanStatus.INTERNAL_ERROR     // Catch:{ all -> 0x0255 }
            r1.setStatus(r3)     // Catch:{ all -> 0x0255 }
            r1.setThrowable(r0)     // Catch:{ all -> 0x0255 }
        L_0x0254:
            throw r0     // Catch:{ all -> 0x0255 }
        L_0x0255:
            r0 = move-exception
        L_0x0256:
            r2.close()
            if (r1 == 0) goto L_0x025e
            r1.finish()
        L_0x025e:
            r16.release()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkSpecDao_Impl.getRecentlyCompletedWork(long):java.util.List");
    }

    public int setState(WorkInfo.State state, String... strArr) {
        ISpan span = Sentry.getSpan();
        ISpan startChild = span != null ? span.startChild("db.sql.room", "androidx.work.impl.model.WorkSpecDao") : null;
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("UPDATE workspec SET state=");
        newStringBuilder.append("?");
        newStringBuilder.append(" WHERE id IN (");
        StringUtil.appendPlaceholders(newStringBuilder, strArr.length);
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        compileStatement.bindLong(1, (long) WorkTypeConverters.stateToInt(state));
        int i = 2;
        for (String str : strArr) {
            if (str == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindString(i, str);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            int executeUpdateDelete = compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            if (startChild != null) {
                startChild.setStatus(SpanStatus.OK);
            }
            this.__db.endTransaction();
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
            this.__db.endTransaction();
            if (startChild != null) {
                startChild.finish();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(ArrayMap<String, ArrayList<String>> arrayMap) {
        ArrayList arrayList;
        Set<String> keySet = arrayMap.keySet();
        if (!keySet.isEmpty()) {
            if (arrayMap.size() > 999) {
                ArrayMap arrayMap2 = new ArrayMap(999);
                int size = arrayMap.size();
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    arrayMap2.put(arrayMap.keyAt(i), arrayMap.valueAt(i));
                    i++;
                    i2++;
                    if (i2 == 999) {
                        __fetchRelationshipWorkTagAsjavaLangString(arrayMap2);
                        arrayMap2 = new ArrayMap(999);
                        i2 = 0;
                    }
                }
                if (i2 > 0) {
                    __fetchRelationshipWorkTagAsjavaLangString(arrayMap2);
                    return;
                }
                return;
            }
            StringBuilder newStringBuilder = StringUtil.newStringBuilder();
            newStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
            int size2 = keySet.size();
            StringUtil.appendPlaceholders(newStringBuilder, size2);
            newStringBuilder.append(")");
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
            int i3 = 1;
            for (String next : keySet) {
                if (next == null) {
                    acquire.bindNull(i3);
                } else {
                    acquire.bindString(i3, next);
                }
                i3++;
            }
            Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
                if (columnIndex != -1) {
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndex) && (arrayList = arrayMap.get(query.getString(columnIndex))) != null) {
                            arrayList.add(query.getString(0));
                        }
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }

    /* access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(ArrayMap<String, ArrayList<Data>> arrayMap) {
        ArrayList arrayList;
        Set<String> keySet = arrayMap.keySet();
        if (!keySet.isEmpty()) {
            if (arrayMap.size() > 999) {
                ArrayMap arrayMap2 = new ArrayMap(999);
                int size = arrayMap.size();
                int i = 0;
                int i2 = 0;
                while (i < size) {
                    arrayMap2.put(arrayMap.keyAt(i), arrayMap.valueAt(i));
                    i++;
                    i2++;
                    if (i2 == 999) {
                        __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                        arrayMap2 = new ArrayMap(999);
                        i2 = 0;
                    }
                }
                if (i2 > 0) {
                    __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap2);
                    return;
                }
                return;
            }
            StringBuilder newStringBuilder = StringUtil.newStringBuilder();
            newStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
            int size2 = keySet.size();
            StringUtil.appendPlaceholders(newStringBuilder, size2);
            newStringBuilder.append(")");
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
            int i3 = 1;
            for (String next : keySet) {
                if (next == null) {
                    acquire.bindNull(i3);
                } else {
                    acquire.bindString(i3, next);
                }
                i3++;
            }
            Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
                if (columnIndex != -1) {
                    while (query.moveToNext()) {
                        if (!query.isNull(columnIndex) && (arrayList = arrayMap.get(query.getString(columnIndex))) != null) {
                            arrayList.add(Data.fromByteArray(query.getBlob(0)));
                        }
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }
}
