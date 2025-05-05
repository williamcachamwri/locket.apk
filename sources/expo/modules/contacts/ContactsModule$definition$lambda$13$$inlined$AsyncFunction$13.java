package expo.modules.contacts;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class ContactsModule$definition$lambda$13$$inlined$AsyncFunction$13 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ContactsModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContactsModule$definition$lambda$13$$inlined$AsyncFunction$13(ContactsModule contactsModule) {
        super(2);
        this.this$0 = contactsModule;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        kotlin.io.CloseableKt.closeFinally(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.lang.Object[] r9, expo.modules.kotlin.Promise r10) {
        /*
            r8 = this;
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r0 = 0
            r9 = r9[r0]
            if (r9 == 0) goto L_0x0080
            java.util.Map r9 = (java.util.Map) r9
            java.lang.String r10 = (java.lang.String) r10
            expo.modules.contacts.ContactsModule r10 = r8.this$0
            r10.ensurePermissions()
            expo.modules.contacts.ContactsModule r10 = r8.this$0
            r1 = 0
            expo.modules.contacts.Contact r9 = r10.mutateContact(r1, r9)
            java.util.ArrayList r9 = r9.toInsertOperationList()
            expo.modules.contacts.ContactsModule r10 = r8.this$0
            android.content.ContentResolver r10 = r10.getResolver()
            java.lang.String r2 = "com.android.contacts"
            android.content.ContentProviderResult[] r9 = r10.applyBatch(r2, r9)
            java.lang.String r10 = "applyBatch(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            int r10 = r9.length
            r2 = 1
            if (r10 != 0) goto L_0x003a
            r10 = r2
            goto L_0x003b
        L_0x003a:
            r10 = r0
        L_0x003b:
            r10 = r10 ^ r2
            if (r10 == 0) goto L_0x007a
            expo.modules.contacts.ContactsModule r10 = r8.this$0
            android.content.ContentResolver r2 = r10.getResolver()
            r9 = r9[r0]
            android.net.Uri r3 = r9.uri
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.String r9 = "contact_id"
            java.lang.String[] r4 = new java.lang.String[]{r9}
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)
            java.io.Closeable r9 = (java.io.Closeable) r9
            r10 = r9
            android.database.Cursor r10 = (android.database.Cursor) r10     // Catch:{ all -> 0x0073 }
            if (r10 == 0) goto L_0x006d
            r10.moveToNext()     // Catch:{ all -> 0x0073 }
            long r2 = r10.getLong(r0)     // Catch:{ all -> 0x0073 }
            java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0073 }
            kotlin.io.CloseableKt.closeFinally(r9, r1)
            return
        L_0x006d:
            expo.modules.contacts.RetrieveIdException r10 = new expo.modules.contacts.RetrieveIdException     // Catch:{ all -> 0x0073 }
            r10.<init>()     // Catch:{ all -> 0x0073 }
            throw r10     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r9, r10)
            throw r0
        L_0x007a:
            expo.modules.contacts.AddContactException r9 = new expo.modules.contacts.AddContactException
            r9.<init>()
            throw r9
        L_0x0080:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException
            java.lang.String r10 = "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.contacts.ContactsModule$definition$lambda$13$$inlined$AsyncFunction$13.invoke(java.lang.Object[], expo.modules.kotlin.Promise):void");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
