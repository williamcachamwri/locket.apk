package io.sentry.protocol;

import io.sentry.DateUtils;
import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.Span;
import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SentrySpan implements JsonUnknown, JsonSerializable {
    private final Map<String, Object> data;
    private final String description;
    private final String op;
    private final String origin;
    private final SpanId parentSpanId;
    private final SpanId spanId;
    private final Double startTimestamp;
    private final SpanStatus status;
    private final Map<String, String> tags;
    private final Double timestamp;
    private final SentryId traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String OP = "op";
        public static final String ORIGIN = "origin";
        public static final String PARENT_SPAN_ID = "parent_span_id";
        public static final String SPAN_ID = "span_id";
        public static final String START_TIMESTAMP = "start_timestamp";
        public static final String STATUS = "status";
        public static final String TAGS = "tags";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "trace_id";
    }

    public SentrySpan(Span span) {
        this(span, span.getData());
    }

    public SentrySpan(Span span, Map<String, Object> map) {
        Objects.requireNonNull(span, "span is required");
        this.description = span.getDescription();
        this.op = span.getOperation();
        this.spanId = span.getSpanId();
        this.parentSpanId = span.getParentSpanId();
        this.traceId = span.getTraceId();
        this.status = span.getStatus();
        this.origin = span.getSpanContext().getOrigin();
        Map<String, String> newConcurrentHashMap = CollectionUtils.newConcurrentHashMap(span.getTags());
        this.tags = newConcurrentHashMap == null ? new ConcurrentHashMap<>() : newConcurrentHashMap;
        this.timestamp = Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().laterDateNanosTimestampByDiff(span.getFinishDate())));
        this.startTimestamp = Double.valueOf(DateUtils.nanosToSeconds(span.getStartDate().nanoTimestamp()));
        this.data = map;
    }

    public SentrySpan(Double d, Double d2, SentryId sentryId, SpanId spanId2, SpanId spanId3, String str, String str2, SpanStatus spanStatus, String str3, Map<String, String> map, Map<String, Object> map2) {
        this.startTimestamp = d;
        this.timestamp = d2;
        this.traceId = sentryId;
        this.spanId = spanId2;
        this.parentSpanId = spanId3;
        this.op = str;
        this.description = str2;
        this.status = spanStatus;
        this.tags = map;
        this.data = map2;
        this.origin = str3;
    }

    public boolean isFinished() {
        return this.timestamp != null;
    }

    public Double getStartTimestamp() {
        return this.startTimestamp;
    }

    public Double getTimestamp() {
        return this.timestamp;
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    public String getOp() {
        return this.op;
    }

    public String getDescription() {
        return this.description;
    }

    public SpanStatus getStatus() {
        return this.status;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("start_timestamp").value(iLogger, doubleToBigDecimal(this.startTimestamp));
        if (this.timestamp != null) {
            objectWriter.name("timestamp").value(iLogger, doubleToBigDecimal(this.timestamp));
        }
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name("span_id").value(iLogger, this.spanId);
        if (this.parentSpanId != null) {
            objectWriter.name("parent_span_id").value(iLogger, this.parentSpanId);
        }
        objectWriter.name("op").value(this.op);
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (this.status != null) {
            objectWriter.name("status").value(iLogger, this.status);
        }
        if (this.origin != null) {
            objectWriter.name("origin").value(iLogger, this.origin);
        }
        if (!this.tags.isEmpty()) {
            objectWriter.name("tags").value(iLogger, this.tags);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String next : map.keySet()) {
                Object obj = this.unknown.get(next);
                objectWriter.name(next);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    private BigDecimal doubleToBigDecimal(Double d) {
        return BigDecimal.valueOf(d.doubleValue()).setScale(6, RoundingMode.DOWN);
    }

    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<SentrySpan> {
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: io.sentry.SpanId} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: io.sentry.SpanStatus} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: java.util.Map} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.util.Map} */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d7, code lost:
            r15 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d9, code lost:
            r13 = r17;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00db, code lost:
            r12 = r18;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00dd, code lost:
            r11 = r19;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public io.sentry.protocol.SentrySpan deserialize(io.sentry.JsonObjectReader r22, io.sentry.ILogger r23) throws java.lang.Exception {
            /*
                r21 = this;
                r0 = r21
                r1 = r22
                r2 = r23
                r22.beginObject()
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
            L_0x0015:
                io.sentry.vendor.gson.stream.JsonToken r3 = r22.peek()
                io.sentry.vendor.gson.stream.JsonToken r0 = io.sentry.vendor.gson.stream.JsonToken.NAME
                r16 = r15
                java.lang.String r15 = "trace_id"
                r17 = r13
                java.lang.String r13 = "op"
                r18 = r12
                java.lang.String r12 = "start_timestamp"
                r19 = r11
                java.lang.String r11 = "span_id"
                if (r3 != r0) goto L_0x0163
                java.lang.String r0 = r22.nextName()
                r0.hashCode()
                int r3 = r0.hashCode()
                r20 = -1
                switch(r3) {
                    case -2011840976: goto L_0x00b7;
                    case -1757797477: goto L_0x00ab;
                    case -1724546052: goto L_0x009f;
                    case -1526966919: goto L_0x0095;
                    case -1008619738: goto L_0x0089;
                    case -892481550: goto L_0x007d;
                    case 3553: goto L_0x0073;
                    case 3076010: goto L_0x0067;
                    case 3552281: goto L_0x0059;
                    case 55126294: goto L_0x004b;
                    case 1270300245: goto L_0x003f;
                    default: goto L_0x003d;
                }
            L_0x003d:
                goto L_0x00c0
            L_0x003f:
                boolean r3 = r0.equals(r15)
                if (r3 != 0) goto L_0x0047
                goto L_0x00c0
            L_0x0047:
                r20 = 10
                goto L_0x00c0
            L_0x004b:
                java.lang.String r3 = "timestamp"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x0055
                goto L_0x00c0
            L_0x0055:
                r20 = 9
                goto L_0x00c0
            L_0x0059:
                java.lang.String r3 = "tags"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x0063
                goto L_0x00c0
            L_0x0063:
                r20 = 8
                goto L_0x00c0
            L_0x0067:
                java.lang.String r3 = "data"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x0070
                goto L_0x00c0
            L_0x0070:
                r20 = 7
                goto L_0x00c0
            L_0x0073:
                boolean r3 = r0.equals(r13)
                if (r3 != 0) goto L_0x007a
                goto L_0x00c0
            L_0x007a:
                r20 = 6
                goto L_0x00c0
            L_0x007d:
                java.lang.String r3 = "status"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x0086
                goto L_0x00c0
            L_0x0086:
                r20 = 5
                goto L_0x00c0
            L_0x0089:
                java.lang.String r3 = "origin"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x0092
                goto L_0x00c0
            L_0x0092:
                r20 = 4
                goto L_0x00c0
            L_0x0095:
                boolean r3 = r0.equals(r12)
                if (r3 != 0) goto L_0x009c
                goto L_0x00c0
            L_0x009c:
                r20 = 3
                goto L_0x00c0
            L_0x009f:
                java.lang.String r3 = "description"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x00a8
                goto L_0x00c0
            L_0x00a8:
                r20 = 2
                goto L_0x00c0
            L_0x00ab:
                java.lang.String r3 = "parent_span_id"
                boolean r3 = r0.equals(r3)
                if (r3 != 0) goto L_0x00b4
                goto L_0x00c0
            L_0x00b4:
                r20 = 1
                goto L_0x00c0
            L_0x00b7:
                boolean r3 = r0.equals(r11)
                if (r3 != 0) goto L_0x00be
                goto L_0x00c0
            L_0x00be:
                r20 = 0
            L_0x00c0:
                switch(r20) {
                    case 0: goto L_0x0154;
                    case 1: goto L_0x0147;
                    case 2: goto L_0x013c;
                    case 3: goto L_0x0125;
                    case 4: goto L_0x011e;
                    case 5: goto L_0x010d;
                    case 6: goto L_0x0108;
                    case 7: goto L_0x0100;
                    case 8: goto L_0x00f8;
                    case 9: goto L_0x00e1;
                    case 10: goto L_0x00ce;
                    default: goto L_0x00c3;
                }
            L_0x00c3:
                if (r14 != 0) goto L_0x00ca
                java.util.concurrent.ConcurrentHashMap r14 = new java.util.concurrent.ConcurrentHashMap
                r14.<init>()
            L_0x00ca:
                r1.nextUnknown(r2, r14, r0)
                goto L_0x00d7
            L_0x00ce:
                io.sentry.protocol.SentryId$Deserializer r0 = new io.sentry.protocol.SentryId$Deserializer
                r0.<init>()
                io.sentry.protocol.SentryId r7 = r0.deserialize((io.sentry.JsonObjectReader) r1, (io.sentry.ILogger) r2)
            L_0x00d7:
                r15 = r16
            L_0x00d9:
                r13 = r17
            L_0x00db:
                r12 = r18
            L_0x00dd:
                r11 = r19
                goto L_0x015f
            L_0x00e1:
                java.lang.Double r6 = r22.nextDoubleOrNull()     // Catch:{ NumberFormatException -> 0x00e6 }
                goto L_0x00d7
            L_0x00e6:
                java.util.Date r0 = r22.nextDateOrNull(r23)
                if (r0 == 0) goto L_0x00f6
                double r11 = io.sentry.DateUtils.dateToSeconds(r0)
                java.lang.Double r0 = java.lang.Double.valueOf(r11)
                r6 = r0
                goto L_0x00d7
            L_0x00f6:
                r6 = 0
                goto L_0x00d7
            L_0x00f8:
                java.lang.Object r0 = r22.nextObjectOrNull()
                r4 = r0
                java.util.Map r4 = (java.util.Map) r4
                goto L_0x00d7
            L_0x0100:
                java.lang.Object r0 = r22.nextObjectOrNull()
                r15 = r0
                java.util.Map r15 = (java.util.Map) r15
                goto L_0x00d9
            L_0x0108:
                java.lang.String r10 = r22.nextStringOrNull()
                goto L_0x00d7
            L_0x010d:
                io.sentry.SpanStatus$Deserializer r0 = new io.sentry.SpanStatus$Deserializer
                r0.<init>()
                java.lang.Object r0 = r1.nextOrNull(r2, r0)
                r12 = r0
                io.sentry.SpanStatus r12 = (io.sentry.SpanStatus) r12
                r15 = r16
                r13 = r17
                goto L_0x00dd
            L_0x011e:
                java.lang.String r13 = r22.nextStringOrNull()
                r15 = r16
                goto L_0x00db
            L_0x0125:
                java.lang.Double r5 = r22.nextDoubleOrNull()     // Catch:{ NumberFormatException -> 0x012a }
                goto L_0x00d7
            L_0x012a:
                java.util.Date r0 = r22.nextDateOrNull(r23)
                if (r0 == 0) goto L_0x013a
                double r11 = io.sentry.DateUtils.dateToSeconds(r0)
                java.lang.Double r0 = java.lang.Double.valueOf(r11)
                r5 = r0
                goto L_0x00d7
            L_0x013a:
                r5 = 0
                goto L_0x00d7
            L_0x013c:
                java.lang.String r11 = r22.nextStringOrNull()
                r15 = r16
                r13 = r17
                r12 = r18
                goto L_0x015f
            L_0x0147:
                io.sentry.SpanId$Deserializer r0 = new io.sentry.SpanId$Deserializer
                r0.<init>()
                java.lang.Object r0 = r1.nextOrNull(r2, r0)
                r9 = r0
                io.sentry.SpanId r9 = (io.sentry.SpanId) r9
                goto L_0x00d7
            L_0x0154:
                io.sentry.SpanId$Deserializer r0 = new io.sentry.SpanId$Deserializer
                r0.<init>()
                io.sentry.SpanId r8 = r0.deserialize((io.sentry.JsonObjectReader) r1, (io.sentry.ILogger) r2)
                goto L_0x00d7
            L_0x015f:
                r0 = r21
                goto L_0x0015
            L_0x0163:
                if (r5 == 0) goto L_0x01a0
                if (r7 == 0) goto L_0x0199
                if (r8 == 0) goto L_0x0192
                if (r10 == 0) goto L_0x018b
                if (r4 != 0) goto L_0x0173
                java.util.HashMap r0 = new java.util.HashMap
                r0.<init>()
                goto L_0x0174
            L_0x0173:
                r0 = r4
            L_0x0174:
                io.sentry.protocol.SentrySpan r2 = new io.sentry.protocol.SentrySpan
                r4 = r2
                r11 = r19
                r12 = r18
                r13 = r17
                r3 = r14
                r14 = r0
                r15 = r16
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
                r2.setUnknown(r3)
                r22.endObject()
                return r2
            L_0x018b:
                r0 = r21
                java.lang.Exception r1 = r0.missingRequiredFieldException(r13, r2)
                throw r1
            L_0x0192:
                r0 = r21
                java.lang.Exception r1 = r0.missingRequiredFieldException(r11, r2)
                throw r1
            L_0x0199:
                r0 = r21
                java.lang.Exception r1 = r0.missingRequiredFieldException(r15, r2)
                throw r1
            L_0x01a0:
                r0 = r21
                java.lang.Exception r1 = r0.missingRequiredFieldException(r12, r2)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.sentry.protocol.SentrySpan.Deserializer.deserialize(io.sentry.JsonObjectReader, io.sentry.ILogger):io.sentry.protocol.SentrySpan");
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, (Throwable) illegalStateException);
            return illegalStateException;
        }
    }
}
