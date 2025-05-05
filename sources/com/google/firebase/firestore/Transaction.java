package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Transaction {
    private final FirebaseFirestore firestore;
    private final com.google.firebase.firestore.core.Transaction transaction;

    public interface Function<TResult> {
        TResult apply(Transaction transaction) throws FirebaseFirestoreException;
    }

    Transaction(com.google.firebase.firestore.core.Transaction transaction2, FirebaseFirestore firebaseFirestore) {
        this.transaction = (com.google.firebase.firestore.core.Transaction) Preconditions.checkNotNull(transaction2);
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firebaseFirestore);
    }

    public Transaction set(DocumentReference documentReference, Object obj) {
        return set(documentReference, obj, SetOptions.OVERWRITE);
    }

    public Transaction set(DocumentReference documentReference, Object obj, SetOptions setOptions) {
        UserData.ParsedSetData parsedSetData;
        this.firestore.validateReference(documentReference);
        Preconditions.checkNotNull(obj, "Provided data must not be null.");
        Preconditions.checkNotNull(setOptions, "Provided options must not be null.");
        if (setOptions.isMerge()) {
            parsedSetData = this.firestore.getUserDataReader().parseMergeData(obj, setOptions.getFieldMask());
        } else {
            parsedSetData = this.firestore.getUserDataReader().parseSetData(obj);
        }
        this.transaction.set(documentReference.getKey(), parsedSetData);
        return this;
    }

    public Transaction update(DocumentReference documentReference, Map<String, Object> map) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(map));
    }

    public Transaction update(DocumentReference documentReference, String str, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, str, obj, objArr)));
    }

    public Transaction update(DocumentReference documentReference, FieldPath fieldPath, Object obj, Object... objArr) {
        return update(documentReference, this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, obj, objArr)));
    }

    private Transaction update(DocumentReference documentReference, UserData.ParsedUpdateData parsedUpdateData) {
        this.firestore.validateReference(documentReference);
        this.transaction.update(documentReference.getKey(), parsedUpdateData);
        return this;
    }

    public Transaction delete(DocumentReference documentReference) {
        this.firestore.validateReference(documentReference);
        this.transaction.delete(documentReference.getKey());
        return this;
    }

    private Task<DocumentSnapshot> getAsync(DocumentReference documentReference) {
        return this.transaction.lookup(Collections.singletonList(documentReference.getKey())).continueWith(Executors.DIRECT_EXECUTOR, new Transaction$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getAsync$0$com-google-firebase-firestore-Transaction  reason: not valid java name */
    public /* synthetic */ DocumentSnapshot m642lambda$getAsync$0$comgooglefirebasefirestoreTransaction(Task task) throws Exception {
        if (task.isSuccessful()) {
            List list = (List) task.getResult();
            if (list.size() == 1) {
                MutableDocument mutableDocument = (MutableDocument) list.get(0);
                if (mutableDocument.isFoundDocument()) {
                    return DocumentSnapshot.fromDocument(this.firestore, mutableDocument, false, false);
                }
                if (mutableDocument.isNoDocument()) {
                    return DocumentSnapshot.fromNoDocument(this.firestore, mutableDocument.getKey(), false);
                }
                throw Assert.fail("BatchGetDocumentsRequest returned unexpected document type: " + mutableDocument.getClass().getCanonicalName(), new Object[0]);
            }
            throw Assert.fail("Mismatch in docs returned from document lookup.", new Object[0]);
        }
        throw task.getException();
    }

    public DocumentSnapshot get(DocumentReference documentReference) throws FirebaseFirestoreException {
        this.firestore.validateReference(documentReference);
        try {
            return (DocumentSnapshot) Tasks.await(getAsync(documentReference));
        } catch (ExecutionException e) {
            if (e.getCause() instanceof FirebaseFirestoreException) {
                throw ((FirebaseFirestoreException) e.getCause());
            }
            throw new RuntimeException(e.getCause());
        } catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }
}
