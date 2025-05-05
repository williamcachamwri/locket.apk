package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.StreamDownloadTask;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.StorageReferenceUri;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class StorageReference implements Comparable<StorageReference> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "StorageReference";
    private final FirebaseStorage mFirebaseStorage;
    private final Uri mStorageUri;

    StorageReference(Uri uri, FirebaseStorage firebaseStorage) {
        boolean z = true;
        Preconditions.checkArgument(uri != null, "storageUri cannot be null");
        Preconditions.checkArgument(firebaseStorage == null ? false : z, "FirebaseApp cannot be null");
        this.mStorageUri = uri;
        this.mFirebaseStorage = firebaseStorage;
    }

    public StorageReference child(String str) {
        Preconditions.checkArgument(!TextUtils.isEmpty(str), "childName cannot be null or empty");
        return new StorageReference(this.mStorageUri.buildUpon().appendEncodedPath(Slashes.preserveSlashEncode(Slashes.normalizeSlashes(str))).build(), this.mFirebaseStorage);
    }

    public StorageReference getParent() {
        String path = this.mStorageUri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        String str = "/";
        if (path.equals(str)) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(47);
        if (lastIndexOf != -1) {
            str = path.substring(0, lastIndexOf);
        }
        return new StorageReference(this.mStorageUri.buildUpon().path(str).build(), this.mFirebaseStorage);
    }

    public StorageReference getRoot() {
        return new StorageReference(this.mStorageUri.buildUpon().path("").build(), this.mFirebaseStorage);
    }

    public String getName() {
        String path = this.mStorageUri.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public String getPath() {
        return this.mStorageUri.getPath();
    }

    public String getBucket() {
        return this.mStorageUri.getAuthority();
    }

    public FirebaseStorage getStorage() {
        return this.mFirebaseStorage;
    }

    /* access modifiers changed from: package-private */
    public FirebaseApp getApp() {
        return getStorage().getApp();
    }

    public UploadTask putBytes(byte[] bArr) {
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putBytes(byte[] bArr, StorageMetadata storageMetadata) {
        boolean z = true;
        Preconditions.checkArgument(bArr != null, "bytes cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, bArr);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri) {
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, uri, (Uri) null);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata) {
        boolean z = true;
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, (Uri) null);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata, Uri uri2) {
        boolean z = true;
        Preconditions.checkArgument(uri != null, "uri cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, uri2);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream) {
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream, StorageMetadata storageMetadata) {
        boolean z = true;
        Preconditions.checkArgument(inputStream != null, "stream cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, inputStream);
        uploadTask.queue();
        return uploadTask;
    }

    public List<UploadTask> getActiveUploadTasks() {
        return StorageTaskManager.getInstance().getUploadTasksUnder(this);
    }

    public List<FileDownloadTask> getActiveDownloadTasks() {
        return StorageTaskManager.getInstance().getDownloadTasksUnder(this);
    }

    public Task<StorageMetadata> getMetadata() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new GetMetadataTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<Uri> getDownloadUrl() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new GetDownloadUrlTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<StorageMetadata> updateMetadata(StorageMetadata storageMetadata) {
        Preconditions.checkNotNull(storageMetadata);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new UpdateMetadataTask(this, taskCompletionSource, storageMetadata));
        return taskCompletionSource.getTask();
    }

    public Task<byte[]> getBytes(final long j) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(new StreamDownloadTask.StreamProcessor() {
            public void doInBackground(StreamDownloadTask.TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[16384];
                    int i = 0;
                    while (true) {
                        int read = inputStream.read(bArr, 0, 16384);
                        if (read != -1) {
                            i += read;
                            if (((long) i) <= j) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } else {
                                SentryLogcatAdapter.e(StorageReference.TAG, "the maximum allowed buffer size was exceeded.");
                                throw new IndexOutOfBoundsException("the maximum allowed buffer size was exceeded.");
                            }
                        } else {
                            byteArrayOutputStream.flush();
                            taskCompletionSource.setResult(byteArrayOutputStream.toByteArray());
                            return;
                        }
                    }
                } finally {
                    inputStream.close();
                }
            }
        }).addOnSuccessListener((OnSuccessListener) new OnSuccessListener<StreamDownloadTask.TaskSnapshot>() {
            public void onSuccess(StreamDownloadTask.TaskSnapshot taskSnapshot) {
                if (!taskCompletionSource.getTask().isComplete()) {
                    SentryLogcatAdapter.e(StorageReference.TAG, "getBytes 'succeeded', but failed to set a Result.");
                    taskCompletionSource.setException(StorageException.fromErrorStatus(Status.RESULT_INTERNAL_ERROR));
                }
            }
        }).addOnFailureListener((OnFailureListener) new OnFailureListener() {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<StorageReference> cls = StorageReference.class;
            }

            public void onFailure(Exception exc) {
                taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exc, 0));
            }
        });
        streamDownloadTask.queue();
        return taskCompletionSource.getTask();
    }

    public FileDownloadTask getFile(Uri uri) {
        FileDownloadTask fileDownloadTask = new FileDownloadTask(this, uri);
        fileDownloadTask.queue();
        return fileDownloadTask;
    }

    public FileDownloadTask getFile(File file) {
        return getFile(Uri.fromFile(file));
    }

    public StreamDownloadTask getStream() {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    public StreamDownloadTask getStream(StreamDownloadTask.StreamProcessor streamProcessor) {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.setStreamProcessor(streamProcessor);
        streamDownloadTask.queue();
        return streamDownloadTask;
    }

    public Task<Void> delete() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new DeleteStorageTask(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<ListResult> list(int i) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        if (i > 1000) {
            z = false;
        }
        Preconditions.checkArgument(z, "maxResults must be at most 1000");
        return listHelper(Integer.valueOf(i), (String) null);
    }

    public Task<ListResult> list(int i, String str) {
        boolean z = true;
        Preconditions.checkArgument(i > 0, "maxResults must be greater than zero");
        Preconditions.checkArgument(i <= 1000, "maxResults must be at most 1000");
        if (str == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "pageToken must be non-null to resume a previous list() operation");
        return listHelper(Integer.valueOf(i), str);
    }

    public Task<ListResult> listAll() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Executor commandPoolExecutor = StorageTaskScheduler.getInstance().getCommandPoolExecutor();
        final Executor executor = commandPoolExecutor;
        final TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        listHelper((Integer) null, (String) null).continueWithTask(commandPoolExecutor, new Continuation<ListResult, Task<Void>>() {
            public Task<Void> then(Task<ListResult> task) {
                if (task.isSuccessful()) {
                    ListResult result = task.getResult();
                    arrayList.addAll(result.getPrefixes());
                    arrayList2.addAll(result.getItems());
                    if (result.getPageToken() != null) {
                        StorageReference.this.listHelper((Integer) null, result.getPageToken()).continueWithTask(executor, this);
                    } else {
                        taskCompletionSource2.setResult(new ListResult(arrayList, arrayList2, (String) null));
                    }
                } else {
                    taskCompletionSource2.setException(task.getException());
                }
                return Tasks.forResult(null);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public Task<ListResult> listHelper(Integer num, String str) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StorageTaskScheduler.getInstance().scheduleCommand(new ListTask(this, num, str, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public Uri getStorageUri() {
        return this.mStorageUri;
    }

    public String toString() {
        return "gs://" + this.mStorageUri.getAuthority() + this.mStorageUri.getEncodedPath();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StorageReference)) {
            return false;
        }
        return ((StorageReference) obj).toString().equals(toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int compareTo(StorageReference storageReference) {
        return this.mStorageUri.compareTo(storageReference.mStorageUri);
    }

    /* access modifiers changed from: package-private */
    public StorageReferenceUri getStorageReferenceUri() {
        return new StorageReferenceUri(this.mStorageUri, this.mFirebaseStorage.getEmulatorSettings());
    }
}
