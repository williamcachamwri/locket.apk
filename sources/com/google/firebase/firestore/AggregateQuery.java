package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AggregateQuery {
    private final List<AggregateField> aggregateFieldList;
    private final Query query;

    AggregateQuery(Query query2, List<AggregateField> list) {
        this.query = query2;
        this.aggregateFieldList = list;
    }

    public Query getQuery() {
        return this.query;
    }

    public List<AggregateField> getAggregateFields() {
        return this.aggregateFieldList;
    }

    public Task<AggregateQuerySnapshot> get(AggregateSource aggregateSource) {
        Preconditions.checkNotNull(aggregateSource, "AggregateSource must not be null");
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        ((Task) this.query.firestore.callClient(new AggregateQuery$$ExternalSyntheticLambda0(this))).continueWith(Executors.DIRECT_EXECUTOR, new AggregateQuery$$ExternalSyntheticLambda1(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$get$0$com-google-firebase-firestore-AggregateQuery  reason: not valid java name */
    public /* synthetic */ Task m624lambda$get$0$comgooglefirebasefirestoreAggregateQuery(FirestoreClient firestoreClient) {
        return firestoreClient.runAggregateQuery(this.query.query, this.aggregateFieldList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$get$1$com-google-firebase-firestore-AggregateQuery  reason: not valid java name */
    public /* synthetic */ Object m625lambda$get$1$comgooglefirebasefirestoreAggregateQuery(TaskCompletionSource taskCompletionSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.setResult(new AggregateQuerySnapshot(this, (Map) task.getResult()));
            return null;
        }
        taskCompletionSource.setException(task.getException());
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AggregateQuery)) {
            return false;
        }
        AggregateQuery aggregateQuery = (AggregateQuery) obj;
        if (!this.query.equals(aggregateQuery.query) || !this.aggregateFieldList.equals(aggregateQuery.aggregateFieldList)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.query, this.aggregateFieldList});
    }
}
