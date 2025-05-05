package com.google.firebase.messaging;

import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

class RequestDeduplicator {
    private final Executor executor;
    private final Map<String, Task<String>> getTokenRequests = new ArrayMap();

    interface GetTokenRequest {
        Task<String> start();
    }

    RequestDeduplicator(Executor executor2) {
        this.executor = executor2;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.google.android.gms.tasks.Task<java.lang.String> getOrStartGetTokenRequest(java.lang.String r5, com.google.firebase.messaging.RequestDeduplicator.GetTokenRequest r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Joining ongoing request for: "
            java.lang.String r1 = "Making new request for: "
            monitor-enter(r4)
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r2 = r4.getTokenRequests     // Catch:{ all -> 0x005c }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ all -> 0x005c }
            com.google.android.gms.tasks.Task r2 = (com.google.android.gms.tasks.Task) r2     // Catch:{ all -> 0x005c }
            r3 = 3
            if (r2 == 0) goto L_0x002c
            java.lang.String r6 = "FirebaseMessaging"
            boolean r6 = android.util.Log.isLoggable(r6, r3)     // Catch:{ all -> 0x005c }
            if (r6 == 0) goto L_0x002a
            java.lang.String r6 = "FirebaseMessaging"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r1.<init>(r0)     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r5 = r1.append(r5)     // Catch:{ all -> 0x005c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x005c }
            android.util.Log.d(r6, r5)     // Catch:{ all -> 0x005c }
        L_0x002a:
            monitor-exit(r4)
            return r2
        L_0x002c:
            java.lang.String r0 = "FirebaseMessaging"
            boolean r0 = android.util.Log.isLoggable(r0, r3)     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0046
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r2.<init>(r1)     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r1 = r2.append(r5)     // Catch:{ all -> 0x005c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x005c }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x005c }
        L_0x0046:
            com.google.android.gms.tasks.Task r6 = r6.start()     // Catch:{ all -> 0x005c }
            java.util.concurrent.Executor r0 = r4.executor     // Catch:{ all -> 0x005c }
            com.google.firebase.messaging.RequestDeduplicator$$ExternalSyntheticLambda0 r1 = new com.google.firebase.messaging.RequestDeduplicator$$ExternalSyntheticLambda0     // Catch:{ all -> 0x005c }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x005c }
            com.google.android.gms.tasks.Task r6 = r6.continueWithTask(r0, r1)     // Catch:{ all -> 0x005c }
            java.util.Map<java.lang.String, com.google.android.gms.tasks.Task<java.lang.String>> r0 = r4.getTokenRequests     // Catch:{ all -> 0x005c }
            r0.put(r5, r6)     // Catch:{ all -> 0x005c }
            monitor-exit(r4)
            return r6
        L_0x005c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.RequestDeduplicator.getOrStartGetTokenRequest(java.lang.String, com.google.firebase.messaging.RequestDeduplicator$GetTokenRequest):com.google.android.gms.tasks.Task");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getOrStartGetTokenRequest$0$com-google-firebase-messaging-RequestDeduplicator  reason: not valid java name */
    public /* synthetic */ Task m798lambda$getOrStartGetTokenRequest$0$comgooglefirebasemessagingRequestDeduplicator(String str, Task task) throws Exception {
        synchronized (this) {
            this.getTokenRequests.remove(str);
        }
        return task;
    }
}
