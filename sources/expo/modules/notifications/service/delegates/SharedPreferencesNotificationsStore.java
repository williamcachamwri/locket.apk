package expo.modules.notifications.service.delegates;

import android.content.Context;
import android.content.SharedPreferences;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006J \u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u0011\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "allNotificationRequests", "", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "getAllNotificationRequests", "()Ljava/util/Collection;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getNotificationRequest", "identifier", "", "preferencesNotificationRequestKey", "removeAllNotificationRequests", "removeNotificationRequest", "Landroid/content/SharedPreferences$Editor;", "kotlin.jvm.PlatformType", "editor", "", "saveNotificationRequest", "notificationRequest", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SharedPreferencesNotificationsStore.kt */
public final class SharedPreferencesNotificationsStore {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String NOTIFICATION_REQUEST_KEY_PREFIX = "notification_request-";
    private static final String SHARED_PREFERENCES_NAME = "expo.modules.notifications.SharedPreferencesNotificationsStore";
    private final SharedPreferences sharedPreferences;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lexpo/modules/notifications/service/delegates/SharedPreferencesNotificationsStore$Companion;", "", "()V", "NOTIFICATION_REQUEST_KEY_PREFIX", "", "SHARED_PREFERENCES_NAME", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SharedPreferencesNotificationsStore.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public SharedPreferencesNotificationsStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences2, "getSharedPreferences(...)");
        this.sharedPreferences = sharedPreferences2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0066, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.notifications.notifications.model.NotificationRequest getNotificationRequest(java.lang.String r7) throws java.io.IOException, java.lang.ClassNotFoundException {
        /*
            r6 = this;
            java.lang.String r0 = "Expected serialized object to be an instance of "
            java.lang.String r1 = "identifier"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            android.content.SharedPreferences r1 = r6.sharedPreferences
            java.lang.String r7 = r6.preferencesNotificationRequestKey(r7)
            r2 = 0
            java.lang.String r7 = r1.getString(r7, r2)
            if (r7 == 0) goto L_0x006e
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r3 = 2
            byte[] r7 = android.util.Base64.decode(r7, r3)
            r1.<init>(r7)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r7 = r1
            java.io.ByteArrayInputStream r7 = (java.io.ByteArrayInputStream) r7     // Catch:{ all -> 0x0067 }
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ all -> 0x0067 }
            java.io.InputStream r7 = (java.io.InputStream) r7     // Catch:{ all -> 0x0067 }
            r3.<init>(r7)     // Catch:{ all -> 0x0067 }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ all -> 0x0067 }
            r7 = r3
            java.io.ObjectInputStream r7 = (java.io.ObjectInputStream) r7     // Catch:{ all -> 0x0060 }
            java.lang.Object r7 = r7.readObject()     // Catch:{ all -> 0x0060 }
            boolean r4 = r7 instanceof expo.modules.notifications.notifications.model.NotificationRequest     // Catch:{ all -> 0x0060 }
            if (r4 == 0) goto L_0x0041
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ all -> 0x0067 }
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            r2 = r7
            expo.modules.notifications.notifications.model.NotificationRequest r2 = (expo.modules.notifications.notifications.model.NotificationRequest) r2
            goto L_0x006e
        L_0x0041:
            java.io.InvalidClassException r2 = new java.io.InvalidClassException     // Catch:{ all -> 0x0060 }
            java.lang.Class<expo.modules.notifications.notifications.model.NotificationRequest> r4 = expo.modules.notifications.notifications.model.NotificationRequest.class
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r5.<init>(r0)     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r0 = r5.append(r4)     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = ". Found: "
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r7 = r0.append(r7)     // Catch:{ all -> 0x0060 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0060 }
            r2.<init>(r7)     // Catch:{ all -> 0x0060 }
            throw r2     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r7)     // Catch:{ all -> 0x0067 }
            throw r0     // Catch:{ all -> 0x0067 }
        L_0x0067:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r7)
            throw r0
        L_0x006e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.delegates.SharedPreferencesNotificationsStore.getNotificationRequest(java.lang.String):expo.modules.notifications.notifications.model.NotificationRequest");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00bd, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c1, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c4, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c8, code lost:
        throw r6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0057 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection<expo.modules.notifications.notifications.model.NotificationRequest> getAllNotificationRequests() {
        /*
            r11 = this;
            android.content.SharedPreferences r0 = r11.sharedPreferences
            java.util.Map r0 = r0.getAll()
            java.lang.String r1 = "getAll(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            java.util.Map r1 = (java.util.Map) r1
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x001a:
            boolean r2 = r0.hasNext()
            r3 = 2
            r4 = 0
            if (r2 == 0) goto L_0x0048
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r5 = r2.getKey()
            java.lang.String r6 = "<get-key>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.String r6 = "notification_request-"
            r7 = 0
            boolean r3 = kotlin.text.StringsKt.startsWith$default(r5, r6, r7, r3, r4)
            if (r3 == 0) goto L_0x001a
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            r1.put(r3, r2)
            goto L_0x001a
        L_0x0048:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0057:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00d0
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            if (r2 == 0) goto L_0x00c9
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            byte[] r2 = android.util.Base64.decode(r2, r3)     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            r5.<init>(r2)     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            r2 = r5
            java.io.ByteArrayInputStream r2 = (java.io.ByteArrayInputStream) r2     // Catch:{ all -> 0x00c2 }
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch:{ all -> 0x00c2 }
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch:{ all -> 0x00c2 }
            r6.<init>(r2)     // Catch:{ all -> 0x00c2 }
            java.io.Closeable r6 = (java.io.Closeable) r6     // Catch:{ all -> 0x00c2 }
            r2 = r6
            java.io.ObjectInputStream r2 = (java.io.ObjectInputStream) r2     // Catch:{ all -> 0x00bb }
            java.lang.Object r2 = r2.readObject()     // Catch:{ all -> 0x00bb }
            boolean r7 = r2 instanceof expo.modules.notifications.notifications.model.NotificationRequest     // Catch:{ all -> 0x00bb }
            if (r7 == 0) goto L_0x0096
            kotlin.io.CloseableKt.closeFinally(r6, r4)     // Catch:{ all -> 0x00c2 }
            kotlin.io.CloseableKt.closeFinally(r5, r4)     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            expo.modules.notifications.notifications.model.NotificationRequest r2 = (expo.modules.notifications.notifications.model.NotificationRequest) r2     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            goto L_0x00ca
        L_0x0096:
            java.io.InvalidClassException r7 = new java.io.InvalidClassException     // Catch:{ all -> 0x00bb }
            java.lang.Class<expo.modules.notifications.notifications.model.NotificationRequest> r8 = expo.modules.notifications.notifications.model.NotificationRequest.class
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r9.<init>()     // Catch:{ all -> 0x00bb }
            java.lang.String r10 = "Expected serialized object to be an instance of "
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r8 = r9.append(r8)     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = ". Found: "
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r2 = r8.append(r2)     // Catch:{ all -> 0x00bb }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00bb }
            r7.<init>(r2)     // Catch:{ all -> 0x00bb }
            throw r7     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r6, r2)     // Catch:{ all -> 0x00c2 }
            throw r7     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x00c4 }
        L_0x00c4:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r2)     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
            throw r6     // Catch:{ IOException | ClassNotFoundException -> 0x00c9 }
        L_0x00c9:
            r2 = r4
        L_0x00ca:
            if (r2 == 0) goto L_0x0057
            r0.add(r2)
            goto L_0x0057
        L_0x00d0:
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.delegates.SharedPreferencesNotificationsStore.getAllNotificationRequests():java.util.Collection");
    }

    public final void saveNotificationRequest(NotificationRequest notificationRequest) throws IOException {
        Intrinsics.checkNotNullParameter(notificationRequest, NotificationsService.NOTIFICATION_REQUEST_KEY);
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        String identifier = notificationRequest.getIdentifier();
        Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
        edit.putString(preferencesNotificationRequestKey(identifier), Base64SerializationKt.encodedInBase64(notificationRequest)).apply();
    }

    public final void removeNotificationRequest(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
        removeNotificationRequest(edit, str).apply();
    }

    private final SharedPreferences.Editor removeNotificationRequest(SharedPreferences.Editor editor, String str) {
        return editor.remove(preferencesNotificationRequestKey(str));
    }

    public final Collection<String> removeAllNotificationRequests() {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        Iterable<NotificationRequest> allNotificationRequests = getAllNotificationRequests();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(allNotificationRequests, 10));
        for (NotificationRequest notificationRequest : allNotificationRequests) {
            Intrinsics.checkNotNull(edit);
            String identifier = notificationRequest.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            removeNotificationRequest(edit, identifier);
            arrayList.add(notificationRequest.getIdentifier());
        }
        edit.apply();
        return (List) arrayList;
    }

    private final String preferencesNotificationRequestKey(String str) {
        return NOTIFICATION_REQUEST_KEY_PREFIX + str;
    }
}
