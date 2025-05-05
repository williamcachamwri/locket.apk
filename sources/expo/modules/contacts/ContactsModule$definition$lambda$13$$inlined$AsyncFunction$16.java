package expo.modules.contacts;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$16 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$16(ContactsModule contactsModule) {
        super(1);
        this.this$0 = contactsModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0074, code lost:
        kotlin.io.CloseableKt.closeFinally(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0077, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke(java.lang.Object[] r11) {
        /*
            r10 = this;
            java.lang.String r0 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 0
            r1 = r11[r0]
            if (r1 == 0) goto L_0x007e
            java.util.Map r1 = (java.util.Map) r1
            r2 = 1
            r11 = r11[r2]
            java.lang.String r11 = (java.lang.String) r11
            expo.modules.contacts.ContactsModule r11 = r10.this$0
            r11.ensurePermissions()
            expo.modules.contacts.ContactsModule r11 = r10.this$0
            r3 = 0
            expo.modules.contacts.Contact r11 = r11.mutateContact(r3, r1)
            java.util.ArrayList r11 = r11.toInsertOperationList()
            expo.modules.contacts.ContactsModule r1 = r10.this$0
            android.content.ContentResolver r1 = r1.getResolver()
            java.lang.String r4 = "com.android.contacts"
            android.content.ContentProviderResult[] r11 = r1.applyBatch(r4, r11)
            java.lang.String r1 = "applyBatch(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r1)
            int r1 = r11.length
            if (r1 != 0) goto L_0x0037
            r1 = r2
            goto L_0x0038
        L_0x0037:
            r1 = r0
        L_0x0038:
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x0078
            expo.modules.contacts.ContactsModule r1 = r10.this$0
            android.content.ContentResolver r4 = r1.getResolver()
            r11 = r11[r0]
            android.net.Uri r5 = r11.uri
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.String r11 = "contact_id"
            java.lang.String[] r6 = new java.lang.String[]{r11}
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)
            java.io.Closeable r11 = (java.io.Closeable) r11
            r1 = r11
            android.database.Cursor r1 = (android.database.Cursor) r1     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x006b
            r1.moveToNext()     // Catch:{ all -> 0x0071 }
            long r0 = r1.getLong(r0)     // Catch:{ all -> 0x0071 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0071 }
            kotlin.io.CloseableKt.closeFinally(r11, r3)
            return r0
        L_0x006b:
            expo.modules.contacts.RetrieveIdException r0 = new expo.modules.contacts.RetrieveIdException     // Catch:{ all -> 0x0071 }
            r0.<init>()     // Catch:{ all -> 0x0071 }
            throw r0     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r11, r0)
            throw r1
        L_0x0078:
            expo.modules.contacts.AddContactException r11 = new expo.modules.contacts.AddContactException
            r11.<init>()
            throw r11
        L_0x007e:
            java.lang.NullPointerException r11 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>"
            r11.<init>(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.ContactsModule$definition$lambda$13$$inlined$AsyncFunction$16.invoke(java.lang.Object[]):java.lang.Object");
    }
}
