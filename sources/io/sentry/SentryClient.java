package io.sentry;

import io.sentry.SentryOptions;
import io.sentry.Session;
import io.sentry.clientreport.DiscardReason;
import io.sentry.exception.SentryEnvelopeException;
import io.sentry.hints.AbnormalExit;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.SentryId;
import io.sentry.protocol.SentryTransaction;
import io.sentry.transport.ITransport;
import io.sentry.util.CheckInUtils;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import io.sentry.util.TracingUtils;
import java.io.Closeable;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class SentryClient implements ISentryClient {
    static final String SENTRY_PROTOCOL_VERSION = "7";
    private boolean enabled;
    private final SentryOptions options;
    private final SecureRandom random;
    private final SortBreadcrumbsByDate sortBreadcrumbsByDate = new SortBreadcrumbsByDate();
    private final ITransport transport;

    static /* synthetic */ void lambda$captureEvent$0(Session session) {
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    SentryClient(SentryOptions sentryOptions) {
        SecureRandom secureRandom = null;
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "SentryOptions is required.");
        this.enabled = true;
        ITransportFactory transportFactory = sentryOptions.getTransportFactory();
        if (transportFactory instanceof NoOpTransportFactory) {
            transportFactory = new AsyncHttpTransportFactory();
            sentryOptions.setTransportFactory(transportFactory);
        }
        this.transport = transportFactory.create(sentryOptions, new RequestDetailsResolver(sentryOptions).resolve());
        this.random = sentryOptions.getSampleRate() != null ? new SecureRandom() : secureRandom;
    }

    private boolean shouldApplyScopeData(SentryBaseEvent sentryBaseEvent, Hint hint) {
        if (HintUtils.shouldApplyScopeData(hint)) {
            return true;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Event was cached so not applying scope: %s", sentryBaseEvent.getEventId());
        return false;
    }

    private boolean shouldApplyScopeData(CheckIn checkIn, Hint hint) {
        if (HintUtils.shouldApplyScopeData(hint)) {
            return true;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Check-in was cached so not applying scope: %s", checkIn.getCheckInId());
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0158 A[Catch:{ SentryEnvelopeException | IOException -> 0x0173 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x015a A[Catch:{ SentryEnvelopeException | IOException -> 0x0173 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x015d A[Catch:{ SentryEnvelopeException | IOException -> 0x0173 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016d A[Catch:{ SentryEnvelopeException | IOException -> 0x0173 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.sentry.protocol.SentryId captureEvent(io.sentry.SentryEvent r13, io.sentry.Scope r14, io.sentry.Hint r15) {
        /*
            r12 = this;
            java.lang.String r0 = "SentryEvent is required."
            io.sentry.util.Objects.requireNonNull(r13, r0)
            if (r15 != 0) goto L_0x000c
            io.sentry.Hint r15 = new io.sentry.Hint
            r15.<init>()
        L_0x000c:
            boolean r0 = r12.shouldApplyScopeData((io.sentry.SentryBaseEvent) r13, (io.sentry.Hint) r15)
            if (r0 == 0) goto L_0x0015
            r12.addScopeAttachmentsToHint(r14, r15)
        L_0x0015:
            io.sentry.SentryOptions r0 = r12.options
            io.sentry.ILogger r0 = r0.getLogger()
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.DEBUG
            io.sentry.protocol.SentryId r2 = r13.getEventId()
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "Capturing event: %s"
            r0.log((io.sentry.SentryLevel) r1, (java.lang.String) r3, (java.lang.Object[]) r2)
            if (r13 == 0) goto L_0x005f
            java.lang.Throwable r0 = r13.getThrowable()
            if (r0 == 0) goto L_0x005f
            io.sentry.SentryOptions r1 = r12.options
            boolean r1 = r1.containsIgnoredExceptionForType(r0)
            if (r1 == 0) goto L_0x005f
            io.sentry.SentryOptions r13 = r12.options
            io.sentry.ILogger r13 = r13.getLogger()
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.DEBUG
            java.lang.Class r15 = r0.getClass()
            java.lang.Object[] r15 = new java.lang.Object[]{r15}
            java.lang.String r0 = "Event was dropped as the exception %s is ignored"
            r13.log((io.sentry.SentryLevel) r14, (java.lang.String) r0, (java.lang.Object[]) r15)
            io.sentry.SentryOptions r13 = r12.options
            io.sentry.clientreport.IClientReportRecorder r13 = r13.getClientReportRecorder()
            io.sentry.clientreport.DiscardReason r14 = io.sentry.clientreport.DiscardReason.EVENT_PROCESSOR
            io.sentry.DataCategory r15 = io.sentry.DataCategory.Error
            r13.recordLostEvent(r14, r15)
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
            return r13
        L_0x005f:
            boolean r0 = r12.shouldApplyScopeData((io.sentry.SentryBaseEvent) r13, (io.sentry.Hint) r15)
            r1 = 0
            if (r0 == 0) goto L_0x007e
            io.sentry.SentryEvent r13 = r12.applyScope(r13, r14, r15)
            if (r13 != 0) goto L_0x007e
            io.sentry.SentryOptions r13 = r12.options
            io.sentry.ILogger r13 = r13.getLogger()
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.DEBUG
            java.lang.String r15 = "Event was dropped by applyScope"
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r13.log((io.sentry.SentryLevel) r14, (java.lang.String) r15, (java.lang.Object[]) r0)
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
            return r13
        L_0x007e:
            io.sentry.SentryOptions r0 = r12.options
            java.util.List r0 = r0.getEventProcessors()
            io.sentry.SentryEvent r13 = r12.processEvent(r13, r15, r0)
            if (r13 == 0) goto L_0x00ac
            io.sentry.SentryEvent r13 = r12.executeBeforeSend(r13, r15)
            if (r13 != 0) goto L_0x00ac
            io.sentry.SentryOptions r0 = r12.options
            io.sentry.ILogger r0 = r0.getLogger()
            io.sentry.SentryLevel r2 = io.sentry.SentryLevel.DEBUG
            java.lang.String r3 = "Event was dropped by beforeSend"
            java.lang.Object[] r4 = new java.lang.Object[r1]
            r0.log((io.sentry.SentryLevel) r2, (java.lang.String) r3, (java.lang.Object[]) r4)
            io.sentry.SentryOptions r0 = r12.options
            io.sentry.clientreport.IClientReportRecorder r0 = r0.getClientReportRecorder()
            io.sentry.clientreport.DiscardReason r2 = io.sentry.clientreport.DiscardReason.BEFORE_SEND
            io.sentry.DataCategory r3 = io.sentry.DataCategory.Error
            r0.recordLostEvent(r2, r3)
        L_0x00ac:
            if (r13 != 0) goto L_0x00b1
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
            return r13
        L_0x00b1:
            r0 = 0
            if (r14 == 0) goto L_0x00be
            io.sentry.SentryClient$$ExternalSyntheticLambda1 r2 = new io.sentry.SentryClient$$ExternalSyntheticLambda1
            r2.<init>()
            io.sentry.Session r2 = r14.withSession(r2)
            goto L_0x00bf
        L_0x00be:
            r2 = r0
        L_0x00bf:
            if (r13 == 0) goto L_0x00fd
            if (r2 == 0) goto L_0x00cc
            boolean r3 = r2.isTerminated()
            if (r3 != 0) goto L_0x00ca
            goto L_0x00cc
        L_0x00ca:
            r3 = r0
            goto L_0x00d0
        L_0x00cc:
            io.sentry.Session r3 = r12.updateSessionData(r13, r15, r14)
        L_0x00d0:
            boolean r4 = r12.sample()
            if (r4 != 0) goto L_0x00fa
            io.sentry.SentryOptions r4 = r12.options
            io.sentry.ILogger r4 = r4.getLogger()
            io.sentry.SentryLevel r5 = io.sentry.SentryLevel.DEBUG
            io.sentry.protocol.SentryId r13 = r13.getEventId()
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            java.lang.String r6 = "Event %s was dropped due to sampling decision."
            r4.log((io.sentry.SentryLevel) r5, (java.lang.String) r6, (java.lang.Object[]) r13)
            io.sentry.SentryOptions r13 = r12.options
            io.sentry.clientreport.IClientReportRecorder r13 = r13.getClientReportRecorder()
            io.sentry.clientreport.DiscardReason r4 = io.sentry.clientreport.DiscardReason.SAMPLE_RATE
            io.sentry.DataCategory r5 = io.sentry.DataCategory.Error
            r13.recordLostEvent(r4, r5)
            r7 = r0
            goto L_0x00fb
        L_0x00fa:
            r7 = r13
        L_0x00fb:
            r9 = r3
            goto L_0x00ff
        L_0x00fd:
            r7 = r13
            r9 = r0
        L_0x00ff:
            boolean r13 = r12.shouldSendSessionUpdateForDroppedEvent(r2, r9)
            if (r7 != 0) goto L_0x0119
            if (r13 != 0) goto L_0x0119
            io.sentry.SentryOptions r13 = r12.options
            io.sentry.ILogger r13 = r13.getLogger()
            io.sentry.SentryLevel r14 = io.sentry.SentryLevel.DEBUG
            java.lang.String r15 = "Not sending session update for dropped event as it did not cause the session health to change."
            java.lang.Object[] r0 = new java.lang.Object[r1]
            r13.log((io.sentry.SentryLevel) r14, (java.lang.String) r15, (java.lang.Object[]) r0)
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
            return r13
        L_0x0119:
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
            if (r7 == 0) goto L_0x0127
            io.sentry.protocol.SentryId r2 = r7.getEventId()
            if (r2 == 0) goto L_0x0127
            io.sentry.protocol.SentryId r13 = r7.getEventId()
        L_0x0127:
            java.lang.Class<io.sentry.hints.Backfillable> r2 = io.sentry.hints.Backfillable.class
            boolean r2 = io.sentry.util.HintUtils.hasType(r15, r2)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            if (r2 == 0) goto L_0x013c
            if (r7 == 0) goto L_0x0155
            io.sentry.SentryOptions r2 = r12.options     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            io.sentry.Baggage r2 = io.sentry.Baggage.fromEvent(r7, r2)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            io.sentry.TraceContext r2 = r2.toTraceContext()     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            goto L_0x0148
        L_0x013c:
            if (r14 == 0) goto L_0x0155
            io.sentry.ITransaction r2 = r14.getTransaction()     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            if (r2 == 0) goto L_0x014a
            io.sentry.TraceContext r2 = r2.traceContext()     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
        L_0x0148:
            r10 = r2
            goto L_0x0156
        L_0x014a:
            io.sentry.SentryOptions r2 = r12.options     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            io.sentry.PropagationContext r2 = io.sentry.util.TracingUtils.maybeUpdateBaggage(r14, r2)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            io.sentry.TraceContext r2 = r2.traceContext()     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            goto L_0x0148
        L_0x0155:
            r10 = r0
        L_0x0156:
            if (r7 == 0) goto L_0x015a
            r2 = 1
            goto L_0x015b
        L_0x015a:
            r2 = r1
        L_0x015b:
            if (r2 == 0) goto L_0x0161
            java.util.List r0 = r12.getAttachments(r15)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
        L_0x0161:
            r8 = r0
            r11 = 0
            r6 = r12
            io.sentry.SentryEnvelope r0 = r6.buildEnvelope(r7, r8, r9, r10, r11)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            r15.clear()     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            if (r0 == 0) goto L_0x0189
            io.sentry.transport.ITransport r2 = r12.transport     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            r2.send(r0, r15)     // Catch:{ IOException -> 0x0175, SentryEnvelopeException -> 0x0173 }
            goto L_0x0189
        L_0x0173:
            r0 = move-exception
            goto L_0x0176
        L_0x0175:
            r0 = move-exception
        L_0x0176:
            io.sentry.SentryOptions r2 = r12.options
            io.sentry.ILogger r2 = r2.getLogger()
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.WARNING
            java.lang.String r4 = "Capturing event %s failed."
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            r2.log(r3, r0, r4, r13)
            io.sentry.protocol.SentryId r13 = io.sentry.protocol.SentryId.EMPTY_ID
        L_0x0189:
            if (r14 == 0) goto L_0x019e
            io.sentry.ITransaction r14 = r14.getTransaction()
            if (r14 == 0) goto L_0x019e
            java.lang.Class<io.sentry.hints.TransactionEnd> r0 = io.sentry.hints.TransactionEnd.class
            boolean r15 = io.sentry.util.HintUtils.hasType(r15, r0)
            if (r15 == 0) goto L_0x019e
            io.sentry.SpanStatus r15 = io.sentry.SpanStatus.ABORTED
            r14.forceFinish(r15, r1)
        L_0x019e:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.SentryClient.captureEvent(io.sentry.SentryEvent, io.sentry.Scope, io.sentry.Hint):io.sentry.protocol.SentryId");
    }

    private void addScopeAttachmentsToHint(Scope scope, Hint hint) {
        if (scope != null) {
            hint.addAttachments(scope.getAttachments());
        }
    }

    private boolean shouldSendSessionUpdateForDroppedEvent(Session session, Session session2) {
        if (session2 == null) {
            return false;
        }
        if (session == null) {
            return true;
        }
        if (session2.getStatus() == Session.State.Crashed && session.getStatus() != Session.State.Crashed) {
            return true;
        }
        return session2.errorCount() > 0 && session.errorCount() <= 0;
    }

    private List<Attachment> getAttachments(Hint hint) {
        List<Attachment> attachments = hint.getAttachments();
        Attachment screenshot = hint.getScreenshot();
        if (screenshot != null) {
            attachments.add(screenshot);
        }
        Attachment viewHierarchy = hint.getViewHierarchy();
        if (viewHierarchy != null) {
            attachments.add(viewHierarchy);
        }
        Attachment threadDump = hint.getThreadDump();
        if (threadDump != null) {
            attachments.add(threadDump);
        }
        return attachments;
    }

    private SentryEnvelope buildEnvelope(SentryBaseEvent sentryBaseEvent, List<Attachment> list, Session session, TraceContext traceContext, ProfilingTraceData profilingTraceData) throws IOException, SentryEnvelopeException {
        SentryId sentryId;
        ArrayList arrayList = new ArrayList();
        if (sentryBaseEvent != null) {
            arrayList.add(SentryEnvelopeItem.fromEvent(this.options.getSerializer(), sentryBaseEvent));
            sentryId = sentryBaseEvent.getEventId();
        } else {
            sentryId = null;
        }
        if (session != null) {
            arrayList.add(SentryEnvelopeItem.fromSession(this.options.getSerializer(), session));
        }
        if (profilingTraceData != null) {
            arrayList.add(SentryEnvelopeItem.fromProfilingTrace(profilingTraceData, this.options.getMaxTraceFileSize(), this.options.getSerializer()));
            if (sentryId == null) {
                sentryId = new SentryId(profilingTraceData.getProfileId());
            }
        }
        if (list != null) {
            for (Attachment fromAttachment : list) {
                arrayList.add(SentryEnvelopeItem.fromAttachment(this.options.getSerializer(), this.options.getLogger(), fromAttachment, this.options.getMaxAttachmentSize()));
            }
        }
        if (!arrayList.isEmpty()) {
            return new SentryEnvelope(new SentryEnvelopeHeader(sentryId, this.options.getSdkVersion(), traceContext), arrayList);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.sentry.SentryEvent processEvent(io.sentry.SentryEvent r7, io.sentry.Hint r8, java.util.List<io.sentry.EventProcessor> r9) {
        /*
            r6 = this;
            java.util.Iterator r9 = r9.iterator()
        L_0x0004:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x006c
            java.lang.Object r0 = r9.next()
            io.sentry.EventProcessor r0 = (io.sentry.EventProcessor) r0
            boolean r1 = r0 instanceof io.sentry.BackfillingEventProcessor     // Catch:{ all -> 0x002a }
            java.lang.Class<io.sentry.hints.Backfillable> r2 = io.sentry.hints.Backfillable.class
            boolean r2 = io.sentry.util.HintUtils.hasType(r8, r2)     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0021
            if (r1 == 0) goto L_0x0021
            io.sentry.SentryEvent r7 = r0.process((io.sentry.SentryEvent) r7, (io.sentry.Hint) r8)     // Catch:{ all -> 0x002a }
            goto L_0x0044
        L_0x0021:
            if (r2 != 0) goto L_0x0044
            if (r1 != 0) goto L_0x0044
            io.sentry.SentryEvent r7 = r0.process((io.sentry.SentryEvent) r7, (io.sentry.Hint) r8)     // Catch:{ all -> 0x002a }
            goto L_0x0044
        L_0x002a:
            r1 = move-exception
            io.sentry.SentryOptions r2 = r6.options
            io.sentry.ILogger r2 = r2.getLogger()
            io.sentry.SentryLevel r3 = io.sentry.SentryLevel.ERROR
            java.lang.Class r4 = r0.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "An exception occurred while processing event by processor: %s"
            r2.log(r3, r1, r5, r4)
        L_0x0044:
            if (r7 != 0) goto L_0x0004
            io.sentry.SentryOptions r8 = r6.options
            io.sentry.ILogger r8 = r8.getLogger()
            io.sentry.SentryLevel r9 = io.sentry.SentryLevel.DEBUG
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Event was dropped by a processor: %s"
            r8.log((io.sentry.SentryLevel) r9, (java.lang.String) r1, (java.lang.Object[]) r0)
            io.sentry.SentryOptions r8 = r6.options
            io.sentry.clientreport.IClientReportRecorder r8 = r8.getClientReportRecorder()
            io.sentry.clientreport.DiscardReason r9 = io.sentry.clientreport.DiscardReason.EVENT_PROCESSOR
            io.sentry.DataCategory r0 = io.sentry.DataCategory.Error
            r8.recordLostEvent(r9, r0)
        L_0x006c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.SentryClient.processEvent(io.sentry.SentryEvent, io.sentry.Hint, java.util.List):io.sentry.SentryEvent");
    }

    private SentryTransaction processTransaction(SentryTransaction sentryTransaction, Hint hint, List<EventProcessor> list) {
        Iterator<EventProcessor> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            EventProcessor next = it.next();
            try {
                sentryTransaction = next.process(sentryTransaction, hint);
                continue;
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, th, "An exception occurred while processing transaction by processor: %s", next.getClass().getName());
                continue;
            }
            if (sentryTransaction == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by a processor: %s", next.getClass().getName());
                this.options.getClientReportRecorder().recordLostEvent(DiscardReason.EVENT_PROCESSOR, DataCategory.Transaction);
                break;
            }
        }
        return sentryTransaction;
    }

    public void captureUserFeedback(UserFeedback userFeedback) {
        Objects.requireNonNull(userFeedback, "SentryEvent is required.");
        if (SentryId.EMPTY_ID.equals(userFeedback.getEventId())) {
            this.options.getLogger().log(SentryLevel.WARNING, "Capturing userFeedback without a Sentry Id.", new Object[0]);
            return;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing userFeedback: %s", userFeedback.getEventId());
        try {
            this.transport.send(buildEnvelope(userFeedback));
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing user feedback %s failed.", userFeedback.getEventId());
        }
    }

    private SentryEnvelope buildEnvelope(UserFeedback userFeedback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SentryEnvelopeItem.fromUserFeedback(this.options.getSerializer(), userFeedback));
        return new SentryEnvelope(new SentryEnvelopeHeader(userFeedback.getEventId(), this.options.getSdkVersion()), arrayList);
    }

    private SentryEnvelope buildEnvelope(CheckIn checkIn, TraceContext traceContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(SentryEnvelopeItem.fromCheckIn(this.options.getSerializer(), checkIn));
        return new SentryEnvelope(new SentryEnvelopeHeader(checkIn.getCheckInId(), this.options.getSdkVersion(), traceContext), arrayList);
    }

    /* access modifiers changed from: package-private */
    public Session updateSessionData(SentryEvent sentryEvent, Hint hint, Scope scope) {
        if (HintUtils.shouldApplyScopeData(hint)) {
            if (scope != null) {
                return scope.withSession(new SentryClient$$ExternalSyntheticLambda0(this, sentryEvent, hint));
            }
            this.options.getLogger().log(SentryLevel.INFO, "Scope is null on client.captureEvent", new Object[0]);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateSessionData$1$io-sentry-SentryClient  reason: not valid java name */
    public /* synthetic */ void m2379lambda$updateSessionData$1$iosentrySentryClient(SentryEvent sentryEvent, Hint hint, Session session) {
        boolean z = false;
        if (session != null) {
            String str = null;
            Session.State state = sentryEvent.isCrashed() ? Session.State.Crashed : null;
            if (Session.State.Crashed == state || sentryEvent.isErrored()) {
                z = true;
            }
            String str2 = (sentryEvent.getRequest() == null || sentryEvent.getRequest().getHeaders() == null || !sentryEvent.getRequest().getHeaders().containsKey("user-agent")) ? null : sentryEvent.getRequest().getHeaders().get("user-agent");
            Object sentrySdkHint = HintUtils.getSentrySdkHint(hint);
            if (sentrySdkHint instanceof AbnormalExit) {
                str = ((AbnormalExit) sentrySdkHint).mechanism();
                state = Session.State.Abnormal;
            }
            if (session.update(state, str2, z, str) && session.isTerminated()) {
                session.end();
                return;
            }
            return;
        }
        this.options.getLogger().log(SentryLevel.INFO, "Session is null on scope.withSession", new Object[0]);
    }

    public void captureSession(Session session, Hint hint) {
        Objects.requireNonNull(session, "Session is required.");
        if (session.getRelease() == null || session.getRelease().isEmpty()) {
            this.options.getLogger().log(SentryLevel.WARNING, "Sessions can't be captured without setting a release.", new Object[0]);
            return;
        }
        try {
            captureEnvelope(SentryEnvelope.from(this.options.getSerializer(), session, this.options.getSdkVersion()), hint);
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to capture session.", (Throwable) e);
        }
    }

    public SentryId captureEnvelope(SentryEnvelope sentryEnvelope, Hint hint) {
        Objects.requireNonNull(sentryEnvelope, "SentryEnvelope is required.");
        if (hint == null) {
            hint = new Hint();
        }
        try {
            hint.clear();
            this.transport.send(sentryEnvelope, hint);
            SentryId eventId = sentryEnvelope.getHeader().getEventId();
            if (eventId != null) {
                return eventId;
            }
            return SentryId.EMPTY_ID;
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.ERROR, "Failed to capture envelope.", (Throwable) e);
            return SentryId.EMPTY_ID;
        }
    }

    public SentryId captureTransaction(SentryTransaction sentryTransaction, TraceContext traceContext, Scope scope, Hint hint, ProfilingTraceData profilingTraceData) {
        Objects.requireNonNull(sentryTransaction, "Transaction is required.");
        if (hint == null) {
            hint = new Hint();
        }
        if (shouldApplyScopeData((SentryBaseEvent) sentryTransaction, hint)) {
            addScopeAttachmentsToHint(scope, hint);
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing transaction: %s", sentryTransaction.getEventId());
        SentryId sentryId = SentryId.EMPTY_ID;
        if (sentryTransaction.getEventId() != null) {
            sentryId = sentryTransaction.getEventId();
        }
        if (shouldApplyScopeData((SentryBaseEvent) sentryTransaction, hint)) {
            sentryTransaction = (SentryTransaction) applyScope(sentryTransaction, scope);
            if (!(sentryTransaction == null || scope == null)) {
                sentryTransaction = processTransaction(sentryTransaction, hint, scope.getEventProcessors());
            }
            if (sentryTransaction == null) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by applyScope", new Object[0]);
            }
        }
        if (sentryTransaction != null) {
            sentryTransaction = processTransaction(sentryTransaction, hint, this.options.getEventProcessors());
        }
        if (sentryTransaction == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by Event processors.", new Object[0]);
            return SentryId.EMPTY_ID;
        }
        SentryTransaction executeBeforeSendTransaction = executeBeforeSendTransaction(sentryTransaction, hint);
        if (executeBeforeSendTransaction == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Transaction was dropped by beforeSendTransaction.", new Object[0]);
            this.options.getClientReportRecorder().recordLostEvent(DiscardReason.BEFORE_SEND, DataCategory.Transaction);
            return SentryId.EMPTY_ID;
        }
        try {
            SentryEnvelope buildEnvelope = buildEnvelope(executeBeforeSendTransaction, filterForTransaction(getAttachments(hint)), (Session) null, traceContext, profilingTraceData);
            hint.clear();
            if (buildEnvelope == null) {
                return SentryId.EMPTY_ID;
            }
            this.transport.send(buildEnvelope, hint);
            return sentryId;
        } catch (SentryEnvelopeException | IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing transaction %s failed.", sentryId);
            return SentryId.EMPTY_ID;
        }
    }

    public SentryId captureCheckIn(CheckIn checkIn, Scope scope, Hint hint) {
        TraceContext traceContext;
        if (hint == null) {
            hint = new Hint();
        }
        if (checkIn.getEnvironment() == null) {
            checkIn.setEnvironment(this.options.getEnvironment());
        }
        if (checkIn.getRelease() == null) {
            checkIn.setRelease(this.options.getRelease());
        }
        if (shouldApplyScopeData(checkIn, hint)) {
            checkIn = applyScope(checkIn, scope);
        }
        if (CheckInUtils.isIgnored(this.options.getIgnoredCheckIns(), checkIn.getMonitorSlug())) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Check-in was dropped as slug %s is ignored", checkIn.getMonitorSlug());
            return SentryId.EMPTY_ID;
        }
        this.options.getLogger().log(SentryLevel.DEBUG, "Capturing check-in: %s", checkIn.getCheckInId());
        SentryId checkInId = checkIn.getCheckInId();
        if (scope != null) {
            try {
                ITransaction transaction = scope.getTransaction();
                traceContext = transaction != null ? transaction.traceContext() : TracingUtils.maybeUpdateBaggage(scope, this.options).traceContext();
            } catch (IOException e) {
                this.options.getLogger().log(SentryLevel.WARNING, e, "Capturing check-in %s failed.", checkInId);
                return SentryId.EMPTY_ID;
            }
        } else {
            traceContext = null;
        }
        SentryEnvelope buildEnvelope = buildEnvelope(checkIn, traceContext);
        hint.clear();
        this.transport.send(buildEnvelope, hint);
        return checkInId;
    }

    private List<Attachment> filterForTransaction(List<Attachment> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Attachment next : list) {
            if (next.isAddToTransactions()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private SentryEvent applyScope(SentryEvent sentryEvent, Scope scope, Hint hint) {
        if (scope == null) {
            return sentryEvent;
        }
        applyScope(sentryEvent, scope);
        if (sentryEvent.getTransaction() == null) {
            sentryEvent.setTransaction(scope.getTransactionName());
        }
        if (sentryEvent.getFingerprints() == null) {
            sentryEvent.setFingerprints(scope.getFingerprint());
        }
        if (scope.getLevel() != null) {
            sentryEvent.setLevel(scope.getLevel());
        }
        ISpan span = scope.getSpan();
        if (sentryEvent.getContexts().getTrace() == null) {
            if (span == null) {
                sentryEvent.getContexts().setTrace(TransactionContext.fromPropagationContext(scope.getPropagationContext()));
            } else {
                sentryEvent.getContexts().setTrace(span.getSpanContext());
            }
        }
        return processEvent(sentryEvent, hint, scope.getEventProcessors());
    }

    private CheckIn applyScope(CheckIn checkIn, Scope scope) {
        if (scope != null) {
            ISpan span = scope.getSpan();
            if (checkIn.getContexts().getTrace() == null) {
                if (span == null) {
                    checkIn.getContexts().setTrace(TransactionContext.fromPropagationContext(scope.getPropagationContext()));
                } else {
                    checkIn.getContexts().setTrace(span.getSpanContext());
                }
            }
        }
        return checkIn;
    }

    private <T extends SentryBaseEvent> T applyScope(T t, Scope scope) {
        if (scope != null) {
            if (t.getRequest() == null) {
                t.setRequest(scope.getRequest());
            }
            if (t.getUser() == null) {
                t.setUser(scope.getUser());
            }
            if (t.getTags() == null) {
                t.setTags(new HashMap(scope.getTags()));
            } else {
                for (Map.Entry next : scope.getTags().entrySet()) {
                    if (!t.getTags().containsKey(next.getKey())) {
                        t.getTags().put((String) next.getKey(), (String) next.getValue());
                    }
                }
            }
            if (t.getBreadcrumbs() == null) {
                t.setBreadcrumbs(new ArrayList(scope.getBreadcrumbs()));
            } else {
                sortBreadcrumbsByDate(t, scope.getBreadcrumbs());
            }
            if (t.getExtras() == null) {
                t.setExtras(new HashMap(scope.getExtras()));
            } else {
                for (Map.Entry next2 : scope.getExtras().entrySet()) {
                    if (!t.getExtras().containsKey(next2.getKey())) {
                        t.getExtras().put((String) next2.getKey(), next2.getValue());
                    }
                }
            }
            Contexts contexts = t.getContexts();
            for (Map.Entry entry : new Contexts(scope.getContexts()).entrySet()) {
                if (!contexts.containsKey(entry.getKey())) {
                    contexts.put((String) entry.getKey(), entry.getValue());
                }
            }
        }
        return t;
    }

    private void sortBreadcrumbsByDate(SentryBaseEvent sentryBaseEvent, Collection<Breadcrumb> collection) {
        List<Breadcrumb> breadcrumbs = sentryBaseEvent.getBreadcrumbs();
        if (breadcrumbs != null && !collection.isEmpty()) {
            breadcrumbs.addAll(collection);
            Collections.sort(breadcrumbs, this.sortBreadcrumbsByDate);
        }
    }

    private SentryEvent executeBeforeSend(SentryEvent sentryEvent, Hint hint) {
        SentryOptions.BeforeSendCallback beforeSend = this.options.getBeforeSend();
        if (beforeSend == null) {
            return sentryEvent;
        }
        try {
            return beforeSend.execute(sentryEvent, hint);
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "The BeforeSend callback threw an exception. It will be added as breadcrumb and continue.", th);
            return null;
        }
    }

    private SentryTransaction executeBeforeSendTransaction(SentryTransaction sentryTransaction, Hint hint) {
        SentryOptions.BeforeSendTransactionCallback beforeSendTransaction = this.options.getBeforeSendTransaction();
        if (beforeSendTransaction == null) {
            return sentryTransaction;
        }
        try {
            return beforeSendTransaction.execute(sentryTransaction, hint);
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "The BeforeSendTransaction callback threw an exception. It will be added as breadcrumb and continue.", th);
            return null;
        }
    }

    public void close() {
        this.options.getLogger().log(SentryLevel.INFO, "Closing SentryClient.", new Object[0]);
        try {
            flush(this.options.getShutdownTimeoutMillis());
            this.transport.close();
        } catch (IOException e) {
            this.options.getLogger().log(SentryLevel.WARNING, "Failed to close the connection to the Sentry Server.", (Throwable) e);
        }
        for (EventProcessor next : this.options.getEventProcessors()) {
            if (next instanceof Closeable) {
                try {
                    ((Closeable) next).close();
                } catch (IOException e2) {
                    this.options.getLogger().log(SentryLevel.WARNING, "Failed to close the event processor {}.", next, e2);
                }
            }
        }
        this.enabled = false;
    }

    public void flush(long j) {
        this.transport.flush(j);
    }

    private boolean sample() {
        if (this.options.getSampleRate() == null || this.random == null || this.options.getSampleRate().doubleValue() >= this.random.nextDouble()) {
            return true;
        }
        return false;
    }

    private static final class SortBreadcrumbsByDate implements Comparator<Breadcrumb> {
        private SortBreadcrumbsByDate() {
        }

        public int compare(Breadcrumb breadcrumb, Breadcrumb breadcrumb2) {
            return breadcrumb.getTimestamp().compareTo(breadcrumb2.getTimestamp());
        }
    }
}
