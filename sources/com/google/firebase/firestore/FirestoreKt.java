package com.google.firebase.firestore;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.MemoryEagerGcSettings;
import com.google.firebase.firestore.MemoryLruGcSettings;
import com.google.firebase.firestore.PersistentCacheSettings;
import io.sentry.Session;
import io.sentry.protocol.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001a\u001f\u0010\f\u001a\u00020\r2\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001a\u001f\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001a\u001f\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001a\u001f\u0010\u0015\u001a\u00020\u00162\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b\u001a+\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u001a0\u0019\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\b\u001a/\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\u001f0\u0019\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u00020 2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\b\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\"\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010#\u001a\u00020$\u001a$\u0010%\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&2\u0006\u0010'\u001a\u00020(H\b¢\u0006\u0002\u0010)\u001a,\u0010%\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010*\u001a\u00020+H\b¢\u0006\u0002\u0010,\u001a$\u0010%\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&2\u0006\u0010-\u001a\u00020$H\b¢\u0006\u0002\u0010.\u001a,\u0010%\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&2\u0006\u0010-\u001a\u00020$2\u0006\u0010*\u001a\u00020+H\b¢\u0006\u0002\u0010/\u001a\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020&0\u0019*\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u001a\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0019*\u00020 2\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u001a\u001c\u00102\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&H\b¢\u0006\u0002\u00103\u001a$\u00102\u001a\u0004\u0018\u0001H\u001a\"\u0006\b\u0000\u0010\u001a\u0018\u0001*\u00020&2\u0006\u0010*\u001a\u00020+H\b¢\u0006\u0002\u00104\u001a\u001e\u00102\u001a\u0002H\u001a\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u000205H\b¢\u0006\u0002\u00106\u001a&\u00102\u001a\u0002H\u001a\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u0002052\u0006\u0010*\u001a\u00020+H\b¢\u0006\u0002\u00107\u001a\u001f\u00108\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001f\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u000201H\b\u001a'\u00108\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001f\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u001b*\u0002012\u0006\u0010*\u001a\u00020+H\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u00069"}, d2 = {"firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "Lcom/google/firebase/Firebase;", "getFirestore", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/firestore/FirebaseFirestore;", "firestoreSettings", "Lcom/google/firebase/firestore/FirebaseFirestoreSettings;", "init", "Lkotlin/Function1;", "Lcom/google/firebase/firestore/FirebaseFirestoreSettings$Builder;", "", "Lkotlin/ExtensionFunctionType;", "memoryCacheSettings", "Lcom/google/firebase/firestore/MemoryCacheSettings;", "Lcom/google/firebase/firestore/MemoryCacheSettings$Builder;", "memoryEagerGcSettings", "Lcom/google/firebase/firestore/MemoryEagerGcSettings;", "Lcom/google/firebase/firestore/MemoryEagerGcSettings$Builder;", "memoryLruGcSettings", "Lcom/google/firebase/firestore/MemoryLruGcSettings;", "Lcom/google/firebase/firestore/MemoryLruGcSettings$Builder;", "persistentCacheSettings", "Lcom/google/firebase/firestore/PersistentCacheSettings;", "Lcom/google/firebase/firestore/PersistentCacheSettings$Builder;", "dataObjects", "Lkotlinx/coroutines/flow/Flow;", "T", "", "Lcom/google/firebase/firestore/DocumentReference;", "metadataChanges", "Lcom/google/firebase/firestore/MetadataChanges;", "", "Lcom/google/firebase/firestore/Query;", "app", "Lcom/google/firebase/FirebaseApp;", "database", "", "getField", "Lcom/google/firebase/firestore/DocumentSnapshot;", "fieldPath", "Lcom/google/firebase/firestore/FieldPath;", "(Lcom/google/firebase/firestore/DocumentSnapshot;Lcom/google/firebase/firestore/FieldPath;)Ljava/lang/Object;", "serverTimestampBehavior", "Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;", "(Lcom/google/firebase/firestore/DocumentSnapshot;Lcom/google/firebase/firestore/FieldPath;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;)Ljava/lang/Object;", "field", "(Lcom/google/firebase/firestore/DocumentSnapshot;Ljava/lang/String;)Ljava/lang/Object;", "(Lcom/google/firebase/firestore/DocumentSnapshot;Ljava/lang/String;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;)Ljava/lang/Object;", "snapshots", "Lcom/google/firebase/firestore/QuerySnapshot;", "toObject", "(Lcom/google/firebase/firestore/DocumentSnapshot;)Ljava/lang/Object;", "(Lcom/google/firebase/firestore/DocumentSnapshot;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;)Ljava/lang/Object;", "Lcom/google/firebase/firestore/QueryDocumentSnapshot;", "(Lcom/google/firebase/firestore/QueryDocumentSnapshot;)Ljava/lang/Object;", "(Lcom/google/firebase/firestore/QueryDocumentSnapshot;Lcom/google/firebase/firestore/DocumentSnapshot$ServerTimestampBehavior;)Ljava/lang/Object;", "toObjects", "com.google.firebase-firebase-firestore"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Firestore.kt */
public final class FirestoreKt {
    public static final FirebaseFirestore getFirestore(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseFirestore instance = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseFirestore firestore(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseFirestore instance = FirebaseFirestore.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseFirestore firestore(Firebase firebase2, FirebaseApp firebaseApp, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        Intrinsics.checkNotNullParameter(str, "database");
        FirebaseFirestore instance = FirebaseFirestore.getInstance(firebaseApp, str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app, database)");
        return instance;
    }

    public static final FirebaseFirestore firestore(Firebase firebase2, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(str, "database");
        FirebaseFirestore instance = FirebaseFirestore.getInstance(str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(database)");
        return instance;
    }

    public static final /* synthetic */ <T> T toObject(DocumentSnapshot documentSnapshot) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.toObject(cls);
    }

    public static final /* synthetic */ <T> T toObject(DocumentSnapshot documentSnapshot, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.toObject(cls, serverTimestampBehavior);
    }

    public static final /* synthetic */ <T> T getField(DocumentSnapshot documentSnapshot, String str) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(str, "field");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.get(str, cls);
    }

    public static final /* synthetic */ <T> T getField(DocumentSnapshot documentSnapshot, String str, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(str, "field");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.get(str, cls, serverTimestampBehavior);
    }

    public static final /* synthetic */ <T> T getField(DocumentSnapshot documentSnapshot, FieldPath fieldPath) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(fieldPath, "fieldPath");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.get(fieldPath, cls);
    }

    public static final /* synthetic */ <T> T getField(DocumentSnapshot documentSnapshot, FieldPath fieldPath, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Intrinsics.checkNotNullParameter(documentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(fieldPath, "fieldPath");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        return documentSnapshot.get(fieldPath, cls, serverTimestampBehavior);
    }

    public static final /* synthetic */ <T> T toObject(QueryDocumentSnapshot queryDocumentSnapshot) {
        Intrinsics.checkNotNullParameter(queryDocumentSnapshot, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        T object = queryDocumentSnapshot.toObject(cls);
        Intrinsics.checkNotNullExpressionValue(object, "toObject(T::class.java)");
        return object;
    }

    public static final /* synthetic */ <T> T toObject(QueryDocumentSnapshot queryDocumentSnapshot, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Intrinsics.checkNotNullParameter(queryDocumentSnapshot, "<this>");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        T object = queryDocumentSnapshot.toObject(cls, serverTimestampBehavior);
        Intrinsics.checkNotNullExpressionValue(object, "toObject(T::class.java, serverTimestampBehavior)");
        return object;
    }

    public static final /* synthetic */ <T> List<T> toObjects(QuerySnapshot querySnapshot) {
        Intrinsics.checkNotNullParameter(querySnapshot, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        List<T> objects = querySnapshot.toObjects(cls);
        Intrinsics.checkNotNullExpressionValue(objects, "toObjects(T::class.java)");
        List list = objects;
        return objects;
    }

    public static final /* synthetic */ <T> List<T> toObjects(QuerySnapshot querySnapshot, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Intrinsics.checkNotNullParameter(querySnapshot, "<this>");
        Intrinsics.checkNotNullParameter(serverTimestampBehavior, "serverTimestampBehavior");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        List<T> objects = querySnapshot.toObjects(cls, serverTimestampBehavior);
        Intrinsics.checkNotNullExpressionValue(objects, "toObjects(T::class.java, serverTimestampBehavior)");
        List list = objects;
        return objects;
    }

    public static final FirebaseFirestoreSettings firestoreSettings(Function1<? super FirebaseFirestoreSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        FirebaseFirestoreSettings.Builder builder = new FirebaseFirestoreSettings.Builder();
        function1.invoke(builder);
        FirebaseFirestoreSettings build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final MemoryCacheSettings memoryCacheSettings(Function1<? super MemoryCacheSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        MemoryCacheSettings.Builder newBuilder = MemoryCacheSettings.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        function1.invoke(newBuilder);
        MemoryCacheSettings build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final MemoryEagerGcSettings memoryEagerGcSettings(Function1<? super MemoryEagerGcSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        MemoryEagerGcSettings.Builder newBuilder = MemoryEagerGcSettings.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        function1.invoke(newBuilder);
        MemoryEagerGcSettings build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final MemoryLruGcSettings memoryLruGcSettings(Function1<? super MemoryLruGcSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        MemoryLruGcSettings.Builder newBuilder = MemoryLruGcSettings.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        function1.invoke(newBuilder);
        MemoryLruGcSettings build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final PersistentCacheSettings persistentCacheSettings(Function1<? super PersistentCacheSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        PersistentCacheSettings.Builder newBuilder = PersistentCacheSettings.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        function1.invoke(newBuilder);
        PersistentCacheSettings build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static /* synthetic */ Flow snapshots$default(DocumentReference documentReference, MetadataChanges metadataChanges, int i, Object obj) {
        if ((i & 1) != 0) {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        return snapshots(documentReference, metadataChanges);
    }

    public static final Flow<DocumentSnapshot> snapshots(DocumentReference documentReference, MetadataChanges metadataChanges) {
        Intrinsics.checkNotNullParameter(documentReference, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        return FlowKt.callbackFlow(new FirestoreKt$snapshots$1(documentReference, metadataChanges, (Continuation<? super FirestoreKt$snapshots$1>) null));
    }

    public static /* synthetic */ Flow snapshots$default(Query query, MetadataChanges metadataChanges, int i, Object obj) {
        if ((i & 1) != 0) {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        return snapshots(query, metadataChanges);
    }

    public static final Flow<QuerySnapshot> snapshots(Query query, MetadataChanges metadataChanges) {
        Intrinsics.checkNotNullParameter(query, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        return FlowKt.callbackFlow(new FirestoreKt$snapshots$2(query, metadataChanges, (Continuation<? super FirestoreKt$snapshots$2>) null));
    }

    public static /* synthetic */ Flow dataObjects$default(Query query, MetadataChanges metadataChanges, int i, Object obj) {
        if ((i & 1) != 0) {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        Intrinsics.checkNotNullParameter(query, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        Flow<QuerySnapshot> snapshots = snapshots(query, metadataChanges);
        Intrinsics.needClassReification();
        return new FirestoreKt$dataObjects$$inlined$map$1(snapshots);
    }

    public static final /* synthetic */ <T> Flow<List<T>> dataObjects(Query query, MetadataChanges metadataChanges) {
        Intrinsics.checkNotNullParameter(query, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        Flow<QuerySnapshot> snapshots = snapshots(query, metadataChanges);
        Intrinsics.needClassReification();
        return new FirestoreKt$dataObjects$$inlined$map$1(snapshots);
    }

    public static /* synthetic */ Flow dataObjects$default(DocumentReference documentReference, MetadataChanges metadataChanges, int i, Object obj) {
        if ((i & 1) != 0) {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        Intrinsics.checkNotNullParameter(documentReference, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        Flow<DocumentSnapshot> snapshots = snapshots(documentReference, metadataChanges);
        Intrinsics.needClassReification();
        return new FirestoreKt$dataObjects$$inlined$map$2(snapshots);
    }

    public static final /* synthetic */ <T> Flow<T> dataObjects(DocumentReference documentReference, MetadataChanges metadataChanges) {
        Intrinsics.checkNotNullParameter(documentReference, "<this>");
        Intrinsics.checkNotNullParameter(metadataChanges, "metadataChanges");
        Flow<DocumentSnapshot> snapshots = snapshots(documentReference, metadataChanges);
        Intrinsics.needClassReification();
        return new FirestoreKt$dataObjects$$inlined$map$2(snapshots);
    }
}
