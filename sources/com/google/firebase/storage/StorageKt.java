package com.google.firebase.storage;

import android.net.Uri;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.UploadTask;
import io.sentry.Session;
import io.sentry.protocol.App;
import java.io.InputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\r\u001a\u00020\u000e2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010¢\u0006\u0002\b\u0013\u001a\u0011\u0010\u0014\u001a\u00020\u0015*\u00060\u0016R\u00020\u0017H\u0002\u001a\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018*\u00020\u001aH\u0002\u001a\u0011\u0010\u0014\u001a\u00020\u0015*\u00060\u001bR\u00020\u001cH\u0002\u001a\u0011\u0010\u0014\u001a\u00020\u0015*\u00060\u001dR\u00020\u001eH\u0002\u001a\u0011\u0010\u001f\u001a\u00020\u0015*\u00060\u0016R\u00020\u0017H\u0002\u001a\u0013\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018*\u00020\u001aH\u0002\u001a\u0011\u0010\u001f\u001a\u00020\u0015*\u00060\u001bR\u00020\u001cH\u0002\u001a\u0011\u0010\u001f\u001a\u00020\u0015*\u00060\u001dR\u00020\u001eH\u0002\u001a\u000f\u0010 \u001a\u0004\u0018\u00010!*\u00020\u001aH\u0002\u001a\u0011\u0010 \u001a\u00020\"*\u00060\u001bR\u00020\u001cH\u0002\u001a\u0013\u0010 \u001a\u0004\u0018\u00010\u000e*\u00060\u001dR\u00020\u001eH\u0002\u001a\u0013\u0010#\u001a\u0004\u0018\u00010$*\u00060\u001dR\u00020\u001eH\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010%\u001a\u00020&\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020!\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010'\u001a\u00020!\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\";\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0012\b\u0000\u0010\b*\f0\tR\b\u0012\u0004\u0012\u0002H\b0\n*\b\u0012\u0004\u0012\u0002H\b0\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006("}, d2 = {"storage", "Lcom/google/firebase/storage/FirebaseStorage;", "Lcom/google/firebase/Firebase;", "getStorage", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/storage/FirebaseStorage;", "taskState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/google/firebase/storage/TaskState;", "T", "Lcom/google/firebase/storage/StorageTask$SnapshotBase;", "Lcom/google/firebase/storage/StorageTask;", "getTaskState", "(Lcom/google/firebase/storage/StorageTask;)Lkotlinx/coroutines/flow/Flow;", "storageMetadata", "Lcom/google/firebase/storage/StorageMetadata;", "init", "Lkotlin/Function1;", "Lcom/google/firebase/storage/StorageMetadata$Builder;", "", "Lkotlin/ExtensionFunctionType;", "component1", "", "Lcom/google/firebase/storage/FileDownloadTask$TaskSnapshot;", "Lcom/google/firebase/storage/FileDownloadTask;", "", "Lcom/google/firebase/storage/StorageReference;", "Lcom/google/firebase/storage/ListResult;", "Lcom/google/firebase/storage/StreamDownloadTask$TaskSnapshot;", "Lcom/google/firebase/storage/StreamDownloadTask;", "Lcom/google/firebase/storage/UploadTask$TaskSnapshot;", "Lcom/google/firebase/storage/UploadTask;", "component2", "component3", "", "Ljava/io/InputStream;", "component4", "Landroid/net/Uri;", "app", "Lcom/google/firebase/FirebaseApp;", "url", "com.google.firebase-firebase-storage"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Storage.kt */
public final class StorageKt {
    public static final FirebaseStorage getStorage(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseStorage instance = FirebaseStorage.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseStorage storage(Firebase firebase2, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(str, "url");
        FirebaseStorage instance = FirebaseStorage.getInstance(str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(url)");
        return instance;
    }

    public static final FirebaseStorage storage(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseStorage instance = FirebaseStorage.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseStorage storage(Firebase firebase2, FirebaseApp firebaseApp, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        Intrinsics.checkNotNullParameter(str, "url");
        FirebaseStorage instance = FirebaseStorage.getInstance(firebaseApp, str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app, url)");
        return instance;
    }

    public static final StorageMetadata storageMetadata(Function1<? super StorageMetadata.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        function1.invoke(builder);
        StorageMetadata build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final long component1(UploadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getBytesTransferred();
    }

    public static final long component2(UploadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getTotalByteCount();
    }

    public static final StorageMetadata component3(UploadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getMetadata();
    }

    public static final Uri component4(UploadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getUploadSessionUri();
    }

    public static final long component1(StreamDownloadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getBytesTransferred();
    }

    public static final long component2(StreamDownloadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getTotalByteCount();
    }

    public static final InputStream component3(StreamDownloadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        InputStream stream = taskSnapshot.getStream();
        Intrinsics.checkNotNullExpressionValue(stream, "stream");
        return stream;
    }

    public static final long component1(FileDownloadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getBytesTransferred();
    }

    public static final long component2(FileDownloadTask.TaskSnapshot taskSnapshot) {
        Intrinsics.checkNotNullParameter(taskSnapshot, "<this>");
        return taskSnapshot.getTotalByteCount();
    }

    public static final List<StorageReference> component1(ListResult listResult) {
        Intrinsics.checkNotNullParameter(listResult, "<this>");
        List<StorageReference> items = listResult.getItems();
        Intrinsics.checkNotNullExpressionValue(items, FirebaseAnalytics.Param.ITEMS);
        return items;
    }

    public static final List<StorageReference> component2(ListResult listResult) {
        Intrinsics.checkNotNullParameter(listResult, "<this>");
        List<StorageReference> prefixes = listResult.getPrefixes();
        Intrinsics.checkNotNullExpressionValue(prefixes, "prefixes");
        return prefixes;
    }

    public static final String component3(ListResult listResult) {
        Intrinsics.checkNotNullParameter(listResult, "<this>");
        return listResult.getPageToken();
    }

    public static final <T extends StorageTask<T>.SnapshotBase> Flow<TaskState<T>> getTaskState(StorageTask<T> storageTask) {
        Intrinsics.checkNotNullParameter(storageTask, "<this>");
        return FlowKt.callbackFlow(new StorageKt$taskState$1(storageTask, (Continuation<? super StorageKt$taskState$1>) null));
    }
}
