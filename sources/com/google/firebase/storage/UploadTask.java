package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.AdaptiveStreamBuffer;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.internal.Sleeper;
import com.google.firebase.storage.internal.SleeperImpl;
import com.google.firebase.storage.internal.StorageReferenceUri;
import com.google.firebase.storage.internal.Util;
import com.google.firebase.storage.network.NetworkRequest;
import com.google.firebase.storage.network.ResumableUploadByteRequest;
import com.google.firebase.storage.network.ResumableUploadCancelRequest;
import com.google.firebase.storage.network.ResumableUploadQueryRequest;
import com.google.firebase.storage.network.ResumableUploadStartRequest;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

public class UploadTask extends StorageTask<TaskSnapshot> {
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final int MAXIMUM_CHUNK_SIZE = 33554432;
    static final int PREFERRED_CHUNK_SIZE = 262144;
    private static final String RESUMABLE_FINAL_STATUS = "final";
    private static final String TAG = "UploadTask";
    private static final String X_GOOG_UPLOAD_URL = "X-Goog-Upload-URL";
    static Clock clock = DefaultClock.getInstance();
    private static final Random random = new Random();
    static Sleeper sleeper = new SleeperImpl();
    /* access modifiers changed from: private */
    public final InteropAppCheckTokenProvider mAppCheckProvider;
    /* access modifiers changed from: private */
    public final InternalAuthProvider mAuthProvider;
    private final AtomicLong mBytesUploaded;
    private int mCurrentChunkSize;
    private volatile Exception mException;
    private boolean mIsStreamOwned;
    private volatile StorageMetadata mMetadata;
    private volatile int mResultCode;
    private ExponentialBackoffSender mSender;
    private volatile Exception mServerException;
    private volatile String mServerStatus;
    /* access modifiers changed from: private */
    public final StorageReference mStorageRef;
    private final AdaptiveStreamBuffer mStreamBuffer;
    private final long mTotalByteCount;
    private volatile Uri mUploadUri;
    private final Uri mUri;
    private volatile long maxSleepTime;
    private final int minimumSleepInterval;
    private int sleepTime;

    private boolean isValidHttpResponseCode(int i) {
        return i == 308 || (i >= 200 && i < 300);
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, byte[] bArr) {
        this.mBytesUploaded = new AtomicLong(0);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.sleepTime = 0;
        this.minimumSleepInterval = 1000;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(bArr);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = (long) bArr.length;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        InternalAuthProvider authProvider = storage.getAuthProvider();
        this.mAuthProvider = authProvider;
        InteropAppCheckTokenProvider appCheckProvider = storage.getAppCheckProvider();
        this.mAppCheckProvider = appCheckProvider;
        this.mUri = null;
        this.mStreamBuffer = new AdaptiveStreamBuffer(new ByteArrayInputStream(bArr), 262144);
        this.mIsStreamOwned = true;
        this.maxSleepTime = storage.getMaxChunkUploadRetry();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), authProvider, appCheckProvider, storage.getMaxUploadRetryTimeMillis());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: long} */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    UploadTask(com.google.firebase.storage.StorageReference r12, com.google.firebase.storage.StorageMetadata r13, android.net.Uri r14, android.net.Uri r15) {
        /*
            r11 = this;
            java.lang.String r0 = "UploadTask"
            java.lang.String r1 = "could not retrieve file size for upload "
            r11.<init>()
            java.util.concurrent.atomic.AtomicLong r2 = new java.util.concurrent.atomic.AtomicLong
            r3 = 0
            r2.<init>(r3)
            r11.mBytesUploaded = r2
            r2 = 262144(0x40000, float:3.67342E-40)
            r11.mCurrentChunkSize = r2
            r3 = 0
            r11.mUploadUri = r3
            r11.mException = r3
            r11.mServerException = r3
            r4 = 0
            r11.mResultCode = r4
            r11.sleepTime = r4
            r4 = 1000(0x3e8, float:1.401E-42)
            r11.minimumSleepInterval = r4
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r12)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            com.google.firebase.storage.FirebaseStorage r4 = r12.getStorage()
            r11.mStorageRef = r12
            r11.mMetadata = r13
            com.google.firebase.auth.internal.InternalAuthProvider r7 = r4.getAuthProvider()
            r11.mAuthProvider = r7
            com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider r8 = r4.getAppCheckProvider()
            r11.mAppCheckProvider = r8
            r11.mUri = r14
            long r5 = r4.getMaxChunkUploadRetry()
            r11.maxSleepTime = r5
            com.google.firebase.storage.internal.ExponentialBackoffSender r13 = new com.google.firebase.storage.internal.ExponentialBackoffSender
            com.google.firebase.FirebaseApp r5 = r12.getApp()
            android.content.Context r6 = r5.getApplicationContext()
            long r9 = r4.getMaxUploadRetryTimeMillis()
            r5 = r13
            r5.<init>(r6, r7, r8, r9)
            r11.mSender = r13
            r4 = -1
            com.google.firebase.storage.FirebaseStorage r12 = r12.getStorage()     // Catch:{ FileNotFoundException -> 0x00bf }
            com.google.firebase.FirebaseApp r12 = r12.getApp()     // Catch:{ FileNotFoundException -> 0x00bf }
            android.content.Context r12 = r12.getApplicationContext()     // Catch:{ FileNotFoundException -> 0x00bf }
            android.content.ContentResolver r12 = r12.getContentResolver()     // Catch:{ FileNotFoundException -> 0x00bf }
            java.lang.String r13 = "r"
            android.os.ParcelFileDescriptor r13 = r12.openFileDescriptor(r14, r13)     // Catch:{ NullPointerException -> 0x0099, IOException -> 0x0080 }
            if (r13 == 0) goto L_0x00a0
            long r6 = r13.getStatSize()     // Catch:{ NullPointerException -> 0x0099, IOException -> 0x0080 }
            r13.close()     // Catch:{ NullPointerException -> 0x007e, IOException -> 0x007c }
            goto L_0x00a1
        L_0x007c:
            r13 = move-exception
            goto L_0x0082
        L_0x007e:
            r13 = move-exception
            goto L_0x009b
        L_0x0080:
            r13 = move-exception
            r6 = r4
        L_0x0082:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00bc }
            r14.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00bc }
            android.net.Uri r1 = r11.mUri     // Catch:{ FileNotFoundException -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ FileNotFoundException -> 0x00bc }
            java.lang.StringBuilder r14 = r14.append(r1)     // Catch:{ FileNotFoundException -> 0x00bc }
            java.lang.String r14 = r14.toString()     // Catch:{ FileNotFoundException -> 0x00bc }
            io.sentry.android.core.SentryLogcatAdapter.w(r0, r14, r13)     // Catch:{ FileNotFoundException -> 0x00bc }
            goto L_0x00a1
        L_0x0099:
            r13 = move-exception
            r6 = r4
        L_0x009b:
            java.lang.String r14 = "NullPointerException during file size calculation."
            io.sentry.android.core.SentryLogcatAdapter.w(r0, r14, r13)     // Catch:{ FileNotFoundException -> 0x00bc }
        L_0x00a0:
            r6 = r4
        L_0x00a1:
            android.net.Uri r13 = r11.mUri     // Catch:{ FileNotFoundException -> 0x00bc }
            java.io.InputStream r3 = r12.openInputStream(r13)     // Catch:{ FileNotFoundException -> 0x00bc }
            if (r3 == 0) goto L_0x00db
            int r12 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r12 != 0) goto L_0x00b4
            int r12 = r3.available()     // Catch:{ IOException -> 0x00b4 }
            if (r12 < 0) goto L_0x00b4
            long r6 = (long) r12
        L_0x00b4:
            r4 = r6
            java.io.BufferedInputStream r12 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00bf }
            r12.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00bf }
            r3 = r12
            goto L_0x00da
        L_0x00bc:
            r12 = move-exception
            r4 = r6
            goto L_0x00c0
        L_0x00bf:
            r12 = move-exception
        L_0x00c0:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "could not locate file for uploading:"
            r13.<init>(r14)
            android.net.Uri r14 = r11.mUri
            java.lang.String r14 = r14.toString()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r13)
            r11.mException = r12
        L_0x00da:
            r6 = r4
        L_0x00db:
            r11.mTotalByteCount = r6
            com.google.firebase.storage.internal.AdaptiveStreamBuffer r12 = new com.google.firebase.storage.internal.AdaptiveStreamBuffer
            r12.<init>(r3, r2)
            r11.mStreamBuffer = r12
            r12 = 1
            r11.mIsStreamOwned = r12
            r11.mUploadUri = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.UploadTask.<init>(com.google.firebase.storage.StorageReference, com.google.firebase.storage.StorageMetadata, android.net.Uri, android.net.Uri):void");
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, InputStream inputStream) {
        this.mBytesUploaded = new AtomicLong(0);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.sleepTime = 0;
        this.minimumSleepInterval = 1000;
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(inputStream);
        FirebaseStorage storage = storageReference.getStorage();
        this.mTotalByteCount = -1;
        this.mStorageRef = storageReference;
        this.mMetadata = storageMetadata;
        InternalAuthProvider authProvider = storage.getAuthProvider();
        this.mAuthProvider = authProvider;
        InteropAppCheckTokenProvider appCheckProvider = storage.getAppCheckProvider();
        this.mAppCheckProvider = appCheckProvider;
        this.mStreamBuffer = new AdaptiveStreamBuffer(inputStream, 262144);
        this.mIsStreamOwned = false;
        this.mUri = null;
        this.maxSleepTime = storage.getMaxChunkUploadRetry();
        this.mSender = new ExponentialBackoffSender(storageReference.getApp().getApplicationContext(), authProvider, appCheckProvider, storage.getMaxUploadRetryTimeMillis());
    }

    /* access modifiers changed from: package-private */
    public StorageReference getStorage() {
        return this.mStorageRef;
    }

    /* access modifiers changed from: package-private */
    public long getTotalByteCount() {
        return this.mTotalByteCount;
    }

    /* access modifiers changed from: protected */
    public void schedule() {
        StorageTaskScheduler.getInstance().scheduleUpload(getRunnable());
    }

    /* access modifiers changed from: package-private */
    public void run() {
        this.mSender.reset();
        if (!tryChangeState(4, false)) {
            Log.d(TAG, "The upload cannot continue as it is not in a valid state.");
            return;
        }
        if (this.mStorageRef.getParent() == null) {
            this.mException = new IllegalArgumentException("Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile...");
        }
        if (this.mException == null) {
            if (this.mUploadUri == null) {
                beginResumableUpload();
            } else {
                recoverStatus(false);
            }
            boolean shouldContinue = shouldContinue();
            while (shouldContinue) {
                uploadChunk();
                shouldContinue = shouldContinue();
                if (shouldContinue) {
                    tryChangeState(4, false);
                }
            }
            if (this.mIsStreamOwned && getInternalState() != 16) {
                try {
                    this.mStreamBuffer.close();
                } catch (IOException e) {
                    SentryLogcatAdapter.e(TAG, "Unable to close stream.", e);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void resetState() {
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.mServerStatus = null;
    }

    private void beginResumableUpload() {
        JSONObject jSONObject = null;
        String contentType = this.mMetadata != null ? this.mMetadata.getContentType() : null;
        if (this.mUri != null && TextUtils.isEmpty(contentType)) {
            contentType = this.mStorageRef.getStorage().getApp().getApplicationContext().getContentResolver().getType(this.mUri);
        }
        if (TextUtils.isEmpty(contentType)) {
            contentType = APPLICATION_OCTET_STREAM;
        }
        StorageReferenceUri storageReferenceUri = this.mStorageRef.getStorageReferenceUri();
        FirebaseApp app = this.mStorageRef.getApp();
        if (this.mMetadata != null) {
            jSONObject = this.mMetadata.createJSONObject();
        }
        ResumableUploadStartRequest resumableUploadStartRequest = new ResumableUploadStartRequest(storageReferenceUri, app, jSONObject, contentType);
        if (sendWithRetry(resumableUploadStartRequest)) {
            String resultString = resumableUploadStartRequest.getResultString(X_GOOG_UPLOAD_URL);
            if (!TextUtils.isEmpty(resultString)) {
                this.mUploadUri = Uri.parse(resultString);
            }
        }
    }

    private boolean shouldContinue() {
        if (getInternalState() == 128) {
            return false;
        }
        if (Thread.interrupted()) {
            this.mException = new InterruptedException();
            tryChangeState(64, false);
            return false;
        } else if (getInternalState() == 32) {
            tryChangeState(256, false);
            return false;
        } else if (getInternalState() == 8) {
            tryChangeState(16, false);
            return false;
        } else if (!serverStateValid()) {
            return false;
        } else {
            if (this.mUploadUri == null) {
                if (this.mException == null) {
                    this.mException = new IllegalStateException("Unable to obtain an upload URL.");
                }
                tryChangeState(64, false);
                return false;
            } else if (this.mException != null) {
                tryChangeState(64, false);
                return false;
            } else {
                boolean z = this.mServerException != null || this.mResultCode < 200 || this.mResultCode >= 300;
                long elapsedRealtime = clock.elapsedRealtime() + this.maxSleepTime;
                long elapsedRealtime2 = clock.elapsedRealtime() + ((long) this.sleepTime);
                if (z) {
                    if (elapsedRealtime2 > elapsedRealtime || !recoverStatus(true)) {
                        if (serverStateValid()) {
                            tryChangeState(64, false);
                        }
                        return false;
                    }
                    this.sleepTime = Math.max(this.sleepTime * 2, 1000);
                }
                return true;
            }
        }
    }

    private boolean serverStateValid() {
        if (!RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return true;
        }
        if (this.mException == null) {
            this.mException = new IOException("The server has terminated the upload session", this.mServerException);
        }
        tryChangeState(64, false);
        return false;
    }

    private boolean recoverStatus(boolean z) {
        ResumableUploadQueryRequest resumableUploadQueryRequest = new ResumableUploadQueryRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri);
        if (RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return false;
        }
        if (z) {
            if (!sendWithRetry(resumableUploadQueryRequest)) {
                return false;
            }
        } else if (!send(resumableUploadQueryRequest)) {
            return false;
        }
        if (RESUMABLE_FINAL_STATUS.equals(resumableUploadQueryRequest.getResultString("X-Goog-Upload-Status"))) {
            this.mException = new IOException("The server has terminated the upload session");
            return false;
        }
        String resultString = resumableUploadQueryRequest.getResultString("X-Goog-Upload-Size-Received");
        long parseLong = !TextUtils.isEmpty(resultString) ? Long.parseLong(resultString) : 0;
        long j = this.mBytesUploaded.get();
        int i = (j > parseLong ? 1 : (j == parseLong ? 0 : -1));
        if (i > 0) {
            this.mException = new IOException("Unexpected error. The server lost a chunk update.");
            return false;
        } else if (i >= 0) {
            return true;
        } else {
            try {
                long j2 = parseLong - j;
                if (((long) this.mStreamBuffer.advance((int) j2)) != j2) {
                    this.mException = new IOException("Unexpected end of stream encountered.");
                    return false;
                } else if (this.mBytesUploaded.compareAndSet(j, parseLong)) {
                    return true;
                } else {
                    SentryLogcatAdapter.e(TAG, "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
                    this.mException = new IllegalStateException("uploaded bytes changed unexpectedly.");
                    return false;
                }
            } catch (IOException e) {
                SentryLogcatAdapter.e(TAG, "Unable to recover position in Stream during resumable upload", e);
                this.mException = e;
                return false;
            }
        }
    }

    private boolean delaySend(NetworkRequest networkRequest) {
        try {
            Log.d(TAG, "Waiting " + this.sleepTime + " milliseconds");
            sleeper.sleep(this.sleepTime + random.nextInt(250));
            boolean send = send(networkRequest);
            if (send) {
                this.sleepTime = 0;
            }
            return send;
        } catch (InterruptedException e) {
            SentryLogcatAdapter.w(TAG, "thread interrupted during exponential backoff.");
            Thread.currentThread().interrupt();
            this.mServerException = e;
            return false;
        }
    }

    private void uploadChunk() {
        try {
            this.mStreamBuffer.fill(this.mCurrentChunkSize);
            int min = Math.min(this.mCurrentChunkSize, this.mStreamBuffer.available());
            ResumableUploadByteRequest resumableUploadByteRequest = new ResumableUploadByteRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri, this.mStreamBuffer.get(), this.mBytesUploaded.get(), min, this.mStreamBuffer.isFinished());
            if (!delaySend(resumableUploadByteRequest)) {
                this.mCurrentChunkSize = 262144;
                Log.d(TAG, "Resetting chunk size to " + this.mCurrentChunkSize);
                return;
            }
            this.mBytesUploaded.getAndAdd((long) min);
            if (!this.mStreamBuffer.isFinished()) {
                this.mStreamBuffer.advance(min);
                int i = this.mCurrentChunkSize;
                if (i < MAXIMUM_CHUNK_SIZE) {
                    this.mCurrentChunkSize = i * 2;
                    Log.d(TAG, "Increasing chunk size to " + this.mCurrentChunkSize);
                    return;
                }
                return;
            }
            try {
                this.mMetadata = new StorageMetadata.Builder(resumableUploadByteRequest.getResultBody(), this.mStorageRef).build();
                tryChangeState(4, false);
                tryChangeState(128, false);
            } catch (JSONException e) {
                SentryLogcatAdapter.e(TAG, "Unable to parse resulting metadata from upload:" + resumableUploadByteRequest.getRawResult(), e);
                this.mException = e;
            }
        } catch (IOException e2) {
            SentryLogcatAdapter.e(TAG, "Unable to read bytes for uploading", e2);
            this.mException = e2;
        }
    }

    private boolean send(NetworkRequest networkRequest) {
        networkRequest.performRequest(Util.getCurrentAuthToken(this.mAuthProvider), Util.getCurrentAppCheckToken(this.mAppCheckProvider), this.mStorageRef.getApp().getApplicationContext());
        return processResultValid(networkRequest);
    }

    private boolean sendWithRetry(NetworkRequest networkRequest) {
        this.mSender.sendWithExponentialBackoff(networkRequest);
        return processResultValid(networkRequest);
    }

    private boolean processResultValid(NetworkRequest networkRequest) {
        int resultCode = networkRequest.getResultCode();
        if (this.mSender.isRetryableError(resultCode)) {
            resultCode = -2;
        }
        this.mResultCode = resultCode;
        this.mServerException = networkRequest.getException();
        this.mServerStatus = networkRequest.getResultString("X-Goog-Upload-Status");
        return isValidHttpResponseCode(this.mResultCode) && this.mServerException == null;
    }

    /* access modifiers changed from: protected */
    public void onCanceled() {
        this.mSender.cancel();
        final ResumableUploadCancelRequest resumableUploadCancelRequest = this.mUploadUri != null ? new ResumableUploadCancelRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp(), this.mUploadUri) : null;
        if (resumableUploadCancelRequest != null) {
            StorageTaskScheduler.getInstance().scheduleCommand(new Runnable() {
                public void run() {
                    resumableUploadCancelRequest.performRequest(Util.getCurrentAuthToken(UploadTask.this.mAuthProvider), Util.getCurrentAppCheckToken(UploadTask.this.mAppCheckProvider), UploadTask.this.mStorageRef.getApp().getApplicationContext());
                }
            });
        }
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
        super.onCanceled();
    }

    /* access modifiers changed from: package-private */
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException != null ? this.mException : this.mServerException, this.mResultCode), this.mBytesUploaded.get(), this.mUploadUri, this.mMetadata);
    }

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesUploaded;
        private final StorageMetadata mMetadata;
        private final Uri mUploadUri;

        TaskSnapshot(Exception exc, long j, Uri uri, StorageMetadata storageMetadata) {
            super(exc);
            this.mBytesUploaded = j;
            this.mUploadUri = uri;
            this.mMetadata = storageMetadata;
        }

        public long getBytesTransferred() {
            return this.mBytesUploaded;
        }

        public long getTotalByteCount() {
            return UploadTask.this.getTotalByteCount();
        }

        public Uri getUploadSessionUri() {
            return this.mUploadUri;
        }

        public StorageMetadata getMetadata() {
            return this.mMetadata;
        }
    }
}
