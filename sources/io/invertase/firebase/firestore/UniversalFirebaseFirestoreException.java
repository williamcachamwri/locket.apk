package io.invertase.firebase.firestore;

public class UniversalFirebaseFirestoreException extends Exception {
    private final String code;
    private final String message;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    UniversalFirebaseFirestoreException(com.google.firebase.firestore.FirebaseFirestoreException r28, java.lang.Throwable r29) {
        /*
            r27 = this;
            r0 = r27
            r1 = r29
            if (r28 == 0) goto L_0x000b
            java.lang.String r2 = r28.getMessage()
            goto L_0x000d
        L_0x000b:
            java.lang.String r2 = ""
        L_0x000d:
            r0.<init>(r2, r1)
            java.lang.String r2 = "permission-denied"
            java.lang.String r3 = "Operation was attempted past the valid range."
            java.lang.String r4 = "out-of-range"
            java.lang.String r5 = "Some requested document was not found."
            java.lang.String r6 = "not-found"
            java.lang.String r7 = "Client specified an invalid argument. Note that this differs from failed-precondition. invalid-argument indicates arguments that are problematic regardless of the state of the system (e.g., an invalid field name)."
            java.lang.String r8 = "invalid-argument"
            java.lang.String r9 = "Internal errors. Means some invariants expected by underlying system has been broken. If you see one of these errors, something is very broken."
            java.lang.String r10 = "internal"
            java.lang.String r11 = "failed-precondition"
            java.lang.String r12 = "Deadline expired before operation could complete. For operations that change the state of the system, this error may be returned even if the operation has completed successfully. For example, a successful response from a server could have been delayed long enough for the deadline to expire."
            java.lang.String r13 = "deadline-exceeded"
            java.lang.String r14 = "Unrecoverable data loss or corruption."
            java.lang.String r15 = "data-loss"
            java.lang.String r16 = "The operation was cancelled (typically by the caller)."
            java.lang.String r17 = "cancelled"
            java.lang.String r18 = "Some document that we attempted to create already exists."
            java.lang.String r19 = "already-exists"
            java.lang.String r20 = "The operation was aborted, typically due to a concurrency issue like transaction aborts, etc."
            java.lang.String r21 = "aborted"
            java.lang.String r22 = "An unknown error occurred"
            java.lang.String r23 = "unknown"
            if (r1 == 0) goto L_0x01b0
            java.lang.String r24 = r29.getMessage()
            if (r24 == 0) goto L_0x01b0
            java.lang.String r1 = r29.getMessage()
            r24 = r2
            java.lang.String r2 = ":"
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x01b2
            java.lang.String r1 = r29.getMessage()
            java.lang.String r2 = "([A-Z_]{3,25}):\\s(.*)"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.regex.Matcher r1 = r2.matcher(r1)
            boolean r2 = r1.find()
            if (r2 == 0) goto L_0x01b2
            r2 = 1
            java.lang.String r2 = r1.group(r2)
            java.lang.String r2 = r2.trim()
            r25 = r3
            r3 = 2
            java.lang.String r1 = r1.group(r3)
            java.lang.String r1 = r1.trim()
            r2.hashCode()
            int r3 = r2.hashCode()
            r26 = -1
            switch(r3) {
                case -1842427240: goto L_0x014c;
                case -1711692763: goto L_0x0140;
                case -1416305653: goto L_0x0134;
                case -1031784143: goto L_0x0128;
                case -1025686472: goto L_0x011c;
                case -849706474: goto L_0x0110;
                case -476794961: goto L_0x0104;
                case -376214182: goto L_0x00f8;
                case 433141802: goto L_0x00ea;
                case 695165606: goto L_0x00dc;
                case 979228314: goto L_0x00ce;
                case 1023286998: goto L_0x00c0;
                case 1353037501: goto L_0x00b2;
                case 1487498288: goto L_0x00a4;
                case 1661336131: goto L_0x0096;
                case 1854913705: goto L_0x0088;
                default: goto L_0x0086;
            }
        L_0x0086:
            goto L_0x0157
        L_0x0088:
            java.lang.String r3 = "UNIMPLEMENTED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0092
            goto L_0x0157
        L_0x0092:
            r26 = 15
            goto L_0x0157
        L_0x0096:
            java.lang.String r3 = "ALREADY_EXISTS"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a0
            goto L_0x0157
        L_0x00a0:
            r26 = 14
            goto L_0x0157
        L_0x00a4:
            java.lang.String r3 = "UNAVAILABLE"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00ae
            goto L_0x0157
        L_0x00ae:
            r26 = 13
            goto L_0x0157
        L_0x00b2:
            java.lang.String r3 = "INTERNAL"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00bc
            goto L_0x0157
        L_0x00bc:
            r26 = 12
            goto L_0x0157
        L_0x00c0:
            java.lang.String r3 = "NOT_FOUND"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00ca
            goto L_0x0157
        L_0x00ca:
            r26 = 11
            goto L_0x0157
        L_0x00ce:
            java.lang.String r3 = "FAILED_PRECONDITION"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00d8
            goto L_0x0157
        L_0x00d8:
            r26 = 10
            goto L_0x0157
        L_0x00dc:
            java.lang.String r3 = "OUT_OF_RANGE"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00e6
            goto L_0x0157
        L_0x00e6:
            r26 = 9
            goto L_0x0157
        L_0x00ea:
            java.lang.String r3 = "UNKNOWN"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00f4
            goto L_0x0157
        L_0x00f4:
            r26 = 8
            goto L_0x0157
        L_0x00f8:
            java.lang.String r3 = "DEADLINE_EXCEEDED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0101
            goto L_0x0157
        L_0x0101:
            r26 = 7
            goto L_0x0157
        L_0x0104:
            java.lang.String r3 = "ABORTED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x010d
            goto L_0x0157
        L_0x010d:
            r26 = 6
            goto L_0x0157
        L_0x0110:
            java.lang.String r3 = "UNAUTHENTICATED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0119
            goto L_0x0157
        L_0x0119:
            r26 = 5
            goto L_0x0157
        L_0x011c:
            java.lang.String r3 = "RESOURCE_EXHAUSTED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0125
            goto L_0x0157
        L_0x0125:
            r26 = 4
            goto L_0x0157
        L_0x0128:
            java.lang.String r3 = "CANCELLED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0131
            goto L_0x0157
        L_0x0131:
            r26 = 3
            goto L_0x0157
        L_0x0134:
            java.lang.String r3 = "PERMISSION_DENIED"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x013d
            goto L_0x0157
        L_0x013d:
            r26 = 2
            goto L_0x0157
        L_0x0140:
            java.lang.String r3 = "INVALID_ARGUMENT"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0149
            goto L_0x0157
        L_0x0149:
            r26 = 1
            goto L_0x0157
        L_0x014c:
            java.lang.String r3 = "DATA_LOSS"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0155
            goto L_0x0157
        L_0x0155:
            r26 = 0
        L_0x0157:
            switch(r26) {
                case 0: goto L_0x01ad;
                case 1: goto L_0x01aa;
                case 2: goto L_0x01a4;
                case 3: goto L_0x019f;
                case 4: goto L_0x019a;
                case 5: goto L_0x0195;
                case 6: goto L_0x0190;
                case 7: goto L_0x018d;
                case 8: goto L_0x0187;
                case 9: goto L_0x0183;
                case 10: goto L_0x0175;
                case 11: goto L_0x0172;
                case 12: goto L_0x016e;
                case 13: goto L_0x0168;
                case 14: goto L_0x0162;
                case 15: goto L_0x015c;
                default: goto L_0x015a;
            }
        L_0x015a:
            goto L_0x01b4
        L_0x015c:
            java.lang.String r1 = "unimplemented"
            java.lang.String r2 = "Operation is not implemented or not supported/enabled."
            goto L_0x01b7
        L_0x0162:
            r2 = r18
            r1 = r19
            goto L_0x01b7
        L_0x0168:
            java.lang.String r1 = "unavailable"
            java.lang.String r2 = "The service is currently unavailable. This is a most likely a transient condition and may be corrected by retrying with a backoff."
            goto L_0x01b7
        L_0x016e:
            r2 = r9
            r1 = r10
            goto L_0x01b7
        L_0x0172:
            r2 = r5
            r1 = r6
            goto L_0x01b7
        L_0x0175:
            java.lang.String r2 = "query requires an index"
            boolean r2 = r1.contains(r2)
            if (r2 == 0) goto L_0x017e
            goto L_0x0180
        L_0x017e:
            java.lang.String r1 = "Operation was rejected because the system is not in a state required for the operation's execution. Ensure your query has been indexed via the Firebase console."
        L_0x0180:
            r2 = r1
            r1 = r11
            goto L_0x01b7
        L_0x0183:
            r1 = r4
            r2 = r25
            goto L_0x01b7
        L_0x0187:
            java.lang.String r1 = "Unknown error or an error from a different error domain."
            r2 = r1
            r1 = r23
            goto L_0x01b7
        L_0x018d:
            r2 = r12
            r1 = r13
            goto L_0x01b7
        L_0x0190:
            r2 = r20
            r1 = r21
            goto L_0x01b7
        L_0x0195:
            java.lang.String r1 = "unauthenticated"
            java.lang.String r2 = "The request does not have valid authentication credentials for the operation."
            goto L_0x01b7
        L_0x019a:
            java.lang.String r1 = "resource-exhausted"
            java.lang.String r2 = "Some resource has been exhausted, perhaps a per-user quota, or perhaps the entire file system is out of space."
            goto L_0x01b7
        L_0x019f:
            r2 = r16
            r1 = r17
            goto L_0x01b7
        L_0x01a4:
            java.lang.String r1 = "The caller does not have permission to execute the specified operation."
            r2 = r1
            r1 = r24
            goto L_0x01b7
        L_0x01aa:
            r2 = r7
            r1 = r8
            goto L_0x01b7
        L_0x01ad:
            r2 = r14
            r1 = r15
            goto L_0x01b7
        L_0x01b0:
            r24 = r2
        L_0x01b2:
            r25 = r3
        L_0x01b4:
            r1 = 0
            r2 = r22
        L_0x01b7:
            if (r1 != 0) goto L_0x022d
            if (r28 == 0) goto L_0x022d
            int[] r1 = io.invertase.firebase.firestore.UniversalFirebaseFirestoreException.AnonymousClass1.$SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code
            com.google.firebase.firestore.FirebaseFirestoreException$Code r2 = r28.getCode()
            int r2 = r2.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x0228;
                case 2: goto L_0x0223;
                case 3: goto L_0x021e;
                case 4: goto L_0x021b;
                case 5: goto L_0x0218;
                case 6: goto L_0x01fd;
                case 7: goto L_0x01fa;
                case 8: goto L_0x01f7;
                case 9: goto L_0x01f4;
                case 10: goto L_0x01f0;
                case 11: goto L_0x01eb;
                case 12: goto L_0x01e5;
                case 13: goto L_0x01df;
                case 14: goto L_0x01d9;
                case 15: goto L_0x01d3;
                case 16: goto L_0x01cd;
                default: goto L_0x01ca;
            }
        L_0x01ca:
            r3 = r22
            goto L_0x01cf
        L_0x01cd:
            java.lang.String r3 = "Unknown error or an error from a different error domain."
        L_0x01cf:
            r2 = r23
            goto L_0x022f
        L_0x01d3:
            java.lang.String r2 = "unimplemented"
            java.lang.String r3 = "Operation is not implemented or not supported/enabled."
            goto L_0x022f
        L_0x01d9:
            java.lang.String r2 = "unavailable"
            java.lang.String r3 = "The service is currently unavailable. This is a most likely a transient condition and may be corrected by retrying with a backoff."
            goto L_0x022f
        L_0x01df:
            java.lang.String r2 = "unauthenticated"
            java.lang.String r3 = "The request does not have valid authentication credentials for the operation."
            goto L_0x022f
        L_0x01e5:
            java.lang.String r2 = "resource-exhausted"
            java.lang.String r3 = "Some resource has been exhausted, perhaps a per-user quota, or perhaps the entire file system is out of space."
            goto L_0x022f
        L_0x01eb:
            java.lang.String r3 = "The caller does not have permission to execute the specified operation."
            r2 = r24
            goto L_0x022f
        L_0x01f0:
            r2 = r4
            r3 = r25
            goto L_0x022f
        L_0x01f4:
            r3 = r5
            r2 = r6
            goto L_0x022f
        L_0x01f7:
            r3 = r7
            r2 = r8
            goto L_0x022f
        L_0x01fa:
            r3 = r9
            r2 = r10
            goto L_0x022f
        L_0x01fd:
            java.lang.String r1 = r28.getMessage()
            if (r1 == 0) goto L_0x0214
            java.lang.String r1 = r28.getMessage()
            java.lang.String r2 = "query requires an index"
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x0214
            java.lang.String r3 = r28.getMessage()
            goto L_0x0216
        L_0x0214:
            java.lang.String r3 = "Operation was rejected because the system is not in a state required for the operation's execution. Ensure your query has been indexed via the Firebase console."
        L_0x0216:
            r2 = r11
            goto L_0x022f
        L_0x0218:
            r3 = r12
            r2 = r13
            goto L_0x022f
        L_0x021b:
            r3 = r14
            r2 = r15
            goto L_0x022f
        L_0x021e:
            r3 = r16
            r2 = r17
            goto L_0x022f
        L_0x0223:
            r3 = r18
            r2 = r19
            goto L_0x022f
        L_0x0228:
            r3 = r20
            r2 = r21
            goto L_0x022f
        L_0x022d:
            r3 = r2
            r2 = r1
        L_0x022f:
            r0.code = r2
            r0.message = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.UniversalFirebaseFirestoreException.<init>(com.google.firebase.firestore.FirebaseFirestoreException, java.lang.Throwable):void");
    }

    /* renamed from: io.invertase.firebase.firestore.UniversalFirebaseFirestoreException$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.firebase.firestore.FirebaseFirestoreException$Code[] r0 = com.google.firebase.firestore.FirebaseFirestoreException.Code.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = r0
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ABORTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.ALREADY_EXISTS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.CANCELLED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DATA_LOSS     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.DEADLINE_EXCEEDED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.FAILED_PRECONDITION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INTERNAL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.INVALID_ARGUMENT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x006c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.NOT_FOUND     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.OUT_OF_RANGE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.PERMISSION_DENIED     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x009c }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAUTHENTICATED     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNIMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.google.firebase.firestore.FirebaseFirestoreException$Code r1 = com.google.firebase.firestore.FirebaseFirestoreException.Code.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.invertase.firebase.firestore.UniversalFirebaseFirestoreException.AnonymousClass1.<clinit>():void");
        }
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
