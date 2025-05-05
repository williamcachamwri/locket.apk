package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Label;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.exoplayer.dash.manifest.SegmentBase;
import androidx.media3.exoplayer.upstream.ParsingLoadable;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.SentryThread;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final int[] MPEG_CHANNEL_CONFIGURATION_MAPPING = {-1, 1, 2, 3, 4, 5, 6, 8, 2, 3, 4, 7, 8, 24, 8, 12, 10, 12, 14, 12, 14};
    private static final String TAG = "MpdParser";
    private final XmlPullParserFactory xmlParserFactory;

    private static long getFinalAvailabilityTimeOffset(long j, long j2) {
        if (j2 != C.TIME_UNSET) {
            j = j2;
        }
        return j == Long.MAX_VALUE ? C.TIME_UNSET : j;
    }

    public DashManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            if (newPullParser.next() == 2 && "MPD".equals(newPullParser.getName())) {
                return parseMediaPresentationDescription(newPullParser, uri);
            }
            throw ParserException.createForMalformedManifest("inputStream does not contain a valid media presentation description", (Throwable) null);
        } catch (XmlPullParserException e) {
            throw ParserException.createForMalformedManifest((String) null, e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01e5 A[LOOP:0: B:23:0x00a4->B:79:0x01e5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01a1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.exoplayer.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r47, android.net.Uri r48) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r46 = this;
            r14 = r46
            r12 = r47
            r0 = 0
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.String r2 = "profiles"
            java.lang.String[] r1 = r14.parseProfiles(r12, r2, r1)
            boolean r13 = r14.isDvbProfileDeclared(r1)
            java.lang.String r1 = "availabilityStartTime"
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r15 = parseDateTime(r12, r1, r9)
            java.lang.String r1 = "mediaPresentationDuration"
            long r17 = parseDuration(r12, r1, r9)
            java.lang.String r1 = "minBufferTime"
            long r19 = parseDuration(r12, r1, r9)
            java.lang.String r1 = "type"
            r11 = 0
            java.lang.String r1 = r12.getAttributeValue(r11, r1)
            java.lang.String r2 = "dynamic"
            boolean r21 = r2.equals(r1)
            if (r21 == 0) goto L_0x0040
            java.lang.String r1 = "minimumUpdatePeriod"
            long r1 = parseDuration(r12, r1, r9)
            r22 = r1
            goto L_0x0042
        L_0x0040:
            r22 = r9
        L_0x0042:
            if (r21 == 0) goto L_0x004d
            java.lang.String r1 = "timeShiftBufferDepth"
            long r1 = parseDuration(r12, r1, r9)
            r24 = r1
            goto L_0x004f
        L_0x004d:
            r24 = r9
        L_0x004f:
            if (r21 == 0) goto L_0x005a
            java.lang.String r1 = "suggestedPresentationDelay"
            long r1 = parseDuration(r12, r1, r9)
            r26 = r1
            goto L_0x005c
        L_0x005a:
            r26 = r9
        L_0x005c:
            java.lang.String r1 = "publishTime"
            long r28 = parseDateTime(r12, r1, r9)
            if (r21 == 0) goto L_0x0067
            r3 = 0
            goto L_0x0068
        L_0x0067:
            r3 = r9
        L_0x0068:
            androidx.media3.exoplayer.dash.manifest.BaseUrl r5 = new androidx.media3.exoplayer.dash.manifest.BaseUrl
            java.lang.String r6 = r48.toString()
            java.lang.String r7 = r48.toString()
            r8 = 1
            if (r13 == 0) goto L_0x0077
            r1 = r8
            goto L_0x007b
        L_0x0077:
            r30 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r30
        L_0x007b:
            r5.<init>(r6, r7, r1, r8)
            androidx.media3.exoplayer.dash.manifest.BaseUrl[] r1 = new androidx.media3.exoplayer.dash.manifest.BaseUrl[r8]
            r1[r0] = r5
            java.util.ArrayList r7 = com.google.common.collect.Lists.newArrayList((E[]) r1)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            if (r21 == 0) goto L_0x0094
            r1 = r9
            goto L_0x0096
        L_0x0094:
            r1 = 0
        L_0x0096:
            r30 = r0
            r31 = r30
            r32 = r1
            r34 = r11
            r35 = r34
            r36 = r35
            r37 = r36
        L_0x00a4:
            r47.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x00bf
            if (r30 != 0) goto L_0x00b7
            long r3 = r14.parseAvailabilityTimeOffsetUs(r12, r3)
            r30 = r8
        L_0x00b7:
            java.util.List r0 = r14.parseBaseUrl(r12, r7, r13)
            r6.addAll(r0)
            goto L_0x00cd
        L_0x00bf:
            java.lang.String r0 = "ProgramInformation"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x00d9
            androidx.media3.exoplayer.dash.manifest.ProgramInformation r0 = r46.parseProgramInformation(r47)
            r34 = r0
        L_0x00cd:
            r41 = r6
            r43 = r7
            r42 = r8
            r44 = r9
            r14 = r11
            r11 = r5
            goto L_0x0199
        L_0x00d9:
            java.lang.String r0 = "UTCTiming"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x00e8
            androidx.media3.exoplayer.dash.manifest.UtcTimingElement r0 = r46.parseUtcTiming(r47)
            r35 = r0
            goto L_0x00cd
        L_0x00e8:
            java.lang.String r0 = "Location"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x00ff
            java.lang.String r0 = r48.toString()
            java.lang.String r1 = r47.nextText()
            android.net.Uri r0 = androidx.media3.common.util.UriUtil.resolveToUri(r0, r1)
            r36 = r0
            goto L_0x00cd
        L_0x00ff:
            java.lang.String r0 = "ServiceDescription"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x010e
            androidx.media3.exoplayer.dash.manifest.ServiceDescriptionElement r0 = r46.parseServiceDescription(r47)
            r37 = r0
            goto L_0x00cd
        L_0x010e:
            java.lang.String r0 = "Period"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r12, r0)
            if (r0 == 0) goto L_0x0188
            if (r31 != 0) goto L_0x0188
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L_0x0120
            r2 = r6
            goto L_0x0121
        L_0x0120:
            r2 = r7
        L_0x0121:
            r0 = r46
            r1 = r47
            r38 = r3
            r3 = r32
            r40 = r5
            r41 = r6
            r5 = r38
            r43 = r7
            r42 = r8
            r7 = r15
            r44 = r9
            r9 = r24
            r14 = r11
            r11 = r13
            android.util.Pair r0 = r0.parsePeriod(r1, r2, r3, r5, r7, r9, r11)
            java.lang.Object r1 = r0.first
            androidx.media3.exoplayer.dash.manifest.Period r1 = (androidx.media3.exoplayer.dash.manifest.Period) r1
            long r2 = r1.startMs
            int r2 = (r2 > r44 ? 1 : (r2 == r44 ? 0 : -1))
            if (r2 != 0) goto L_0x0167
            if (r21 == 0) goto L_0x014f
            r11 = r40
            r8 = r42
            goto L_0x0185
        L_0x014f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unable to determine start of period "
            r0.<init>(r1)
            int r1 = r40.size()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedManifest(r0, r14)
            throw r0
        L_0x0167:
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r2 = r0.longValue()
            int r0 = (r2 > r44 ? 1 : (r2 == r44 ? 0 : -1))
            if (r0 != 0) goto L_0x0178
            r11 = r40
            r9 = r44
            goto L_0x017e
        L_0x0178:
            long r4 = r1.startMs
            long r9 = r4 + r2
            r11 = r40
        L_0x017e:
            r11.add(r1)
            r32 = r9
            r8 = r31
        L_0x0185:
            r31 = r8
            goto L_0x0197
        L_0x0188:
            r38 = r3
            r41 = r6
            r43 = r7
            r42 = r8
            r44 = r9
            r14 = r11
            r11 = r5
            maybeSkipTag(r47)
        L_0x0197:
            r3 = r38
        L_0x0199:
            java.lang.String r0 = "MPD"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isEndTag(r12, r0)
            if (r0 == 0) goto L_0x01e5
            int r0 = (r17 > r44 ? 1 : (r17 == r44 ? 0 : -1))
            if (r0 != 0) goto L_0x01b6
            int r0 = (r32 > r44 ? 1 : (r32 == r44 ? 0 : -1))
            if (r0 == 0) goto L_0x01ac
            r3 = r32
            goto L_0x01b8
        L_0x01ac:
            if (r21 == 0) goto L_0x01af
            goto L_0x01b6
        L_0x01af:
            java.lang.String r0 = "Unable to determine duration of static manifest."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedManifest(r0, r14)
            throw r0
        L_0x01b6:
            r3 = r17
        L_0x01b8:
            boolean r0 = r11.isEmpty()
            if (r0 != 0) goto L_0x01de
            r0 = r46
            r1 = r15
            r5 = r19
            r7 = r21
            r8 = r22
            r38 = r11
            r10 = r24
            r12 = r26
            r14 = r28
            r16 = r34
            r17 = r35
            r18 = r37
            r19 = r36
            r20 = r38
            androidx.media3.exoplayer.dash.manifest.DashManifest r0 = r0.buildMediaPresentationDescription(r1, r3, r5, r7, r8, r10, r12, r14, r16, r17, r18, r19, r20)
            return r0
        L_0x01de:
            java.lang.String r0 = "No periods found."
            androidx.media3.common.ParserException r0 = androidx.media3.common.ParserException.createForMalformedManifest(r0, r14)
            throw r0
        L_0x01e5:
            r5 = r11
            r11 = r14
            r6 = r41
            r8 = r42
            r7 = r43
            r9 = r44
            r14 = r46
            goto L_0x00a4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, android.net.Uri):androidx.media3.exoplayer.dash.manifest.DashManifest");
    }

    /* access modifiers changed from: protected */
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, ProgramInformation programInformation, UtcTimingElement utcTimingElement, ServiceDescriptionElement serviceDescriptionElement, Uri uri, List<Period> list) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, j7, programInformation, utcTimingElement, serviceDescriptionElement, uri, list);
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue((String) null, "schemeIdUri"), xmlPullParser.getAttributeValue((String) null, "value"));
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* access modifiers changed from: protected */
    public ServiceDescriptionElement parseServiceDescription(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long j = -9223372036854775807L;
        long j2 = -9223372036854775807L;
        long j3 = -9223372036854775807L;
        float f = -3.4028235E38f;
        float f2 = -3.4028235E38f;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Latency")) {
                j = parseLong(xmlPullParser2, "target", C.TIME_UNSET);
                j2 = parseLong(xmlPullParser2, "min", C.TIME_UNSET);
                j3 = parseLong(xmlPullParser2, "max", C.TIME_UNSET);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "PlaybackRate")) {
                f = parseFloat(xmlPullParser2, "min", -3.4028235E38f);
                f2 = parseFloat(xmlPullParser2, "max", -3.4028235E38f);
            }
            long j4 = j;
            long j5 = j2;
            long j6 = j3;
            float f3 = f;
            float f4 = f2;
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "ServiceDescription")) {
                return new ServiceDescriptionElement(j4, j5, j6, f3, f4);
            }
            j = j4;
            j2 = j5;
            j3 = j6;
            f = f3;
            f2 = f4;
        }
    }

    /* access modifiers changed from: protected */
    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, List<BaseUrl> list, long j, long j2, long j3, long j4, boolean z) throws XmlPullParserException, IOException {
        long j5;
        ArrayList arrayList;
        Object obj;
        ArrayList arrayList2;
        ArrayList arrayList3;
        long j6;
        long j7;
        SegmentBase parseSegmentTemplate;
        DashManifestParser dashManifestParser = this;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        Object obj2 = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "id");
        long parseDuration = parseDuration(xmlPullParser2, "start", j);
        long j8 = C.TIME_UNSET;
        long j9 = j3 != C.TIME_UNSET ? j3 + parseDuration : -9223372036854775807L;
        long parseDuration2 = parseDuration(xmlPullParser2, "duration", C.TIME_UNSET);
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        long j10 = j2;
        boolean z2 = false;
        long j11 = -9223372036854775807L;
        SegmentBase segmentBase = null;
        Descriptor descriptor = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "BaseURL")) {
                if (!z2) {
                    j10 = dashManifestParser.parseAvailabilityTimeOffsetUs(xmlPullParser2, j10);
                    z2 = true;
                }
                arrayList6.addAll(dashManifestParser.parseBaseUrl(xmlPullParser2, list, z));
                arrayList = arrayList5;
                arrayList2 = arrayList6;
                j5 = j8;
                obj = obj2;
                arrayList3 = arrayList4;
            } else {
                List<BaseUrl> list2 = list;
                boolean z3 = z;
                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AdaptationSet")) {
                    j6 = j10;
                    arrayList2 = arrayList6;
                    arrayList3 = arrayList4;
                    arrayList3.add(parseAdaptationSet(xmlPullParser, !arrayList6.isEmpty() ? arrayList6 : list2, segmentBase, parseDuration2, j10, j11, j9, j4, z));
                    xmlPullParser2 = xmlPullParser;
                    arrayList = arrayList5;
                } else {
                    j6 = j10;
                    ArrayList arrayList7 = arrayList5;
                    arrayList2 = arrayList6;
                    arrayList3 = arrayList4;
                    xmlPullParser2 = xmlPullParser;
                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, "EventStream")) {
                        ArrayList arrayList8 = arrayList7;
                        arrayList8.add(parseEventStream(xmlPullParser));
                        arrayList = arrayList8;
                    } else {
                        ArrayList arrayList9 = arrayList7;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentBase")) {
                            arrayList = arrayList9;
                            segmentBase = parseSegmentBase(xmlPullParser2, (SegmentBase.SingleSegmentBase) null);
                            obj = null;
                            j10 = j6;
                            j5 = C.TIME_UNSET;
                        } else {
                            arrayList = arrayList9;
                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                                long parseAvailabilityTimeOffsetUs = parseAvailabilityTimeOffsetUs(xmlPullParser2, C.TIME_UNSET);
                                obj = null;
                                parseSegmentTemplate = parseSegmentList(xmlPullParser, (SegmentBase.SegmentList) null, j9, parseDuration2, j6, parseAvailabilityTimeOffsetUs, j4);
                                j11 = parseAvailabilityTimeOffsetUs;
                                j10 = j6;
                                j5 = C.TIME_UNSET;
                            } else {
                                obj = null;
                                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTemplate")) {
                                    long parseAvailabilityTimeOffsetUs2 = parseAvailabilityTimeOffsetUs(xmlPullParser2, C.TIME_UNSET);
                                    j5 = -9223372036854775807L;
                                    parseSegmentTemplate = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) null, ImmutableList.of(), j9, parseDuration2, j6, parseAvailabilityTimeOffsetUs2, j4);
                                    j11 = parseAvailabilityTimeOffsetUs2;
                                    j10 = j6;
                                } else {
                                    j7 = C.TIME_UNSET;
                                    if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AssetIdentifier")) {
                                        descriptor = parseDescriptor(xmlPullParser2, "AssetIdentifier");
                                    } else {
                                        maybeSkipTag(xmlPullParser);
                                    }
                                    j10 = j6;
                                }
                            }
                            segmentBase = parseSegmentTemplate;
                        }
                    }
                }
                obj = null;
                j7 = C.TIME_UNSET;
                j10 = j6;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "Period")) {
                return Pair.create(buildPeriod(attributeValue, parseDuration, arrayList3, arrayList, descriptor), Long.valueOf(parseDuration2));
            }
            arrayList4 = arrayList3;
            arrayList6 = arrayList2;
            obj2 = obj;
            arrayList5 = arrayList;
            j8 = j5;
            dashManifestParser = this;
        }
    }

    /* access modifiers changed from: protected */
    public Period buildPeriod(String str, long j, List<AdaptationSet> list, List<EventStream> list2, Descriptor descriptor) {
        return new Period(str, j, list, list2, descriptor);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v4, resolved type: androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentTemplate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v6, resolved type: androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v7, resolved type: androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v15, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r41v4, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0358 A[LOOP:0: B:1:0x0080->B:72:0x0358, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0316 A[EDGE_INSN: B:73:0x0316->B:66:0x0316 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.exoplayer.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r57, java.util.List<androidx.media3.exoplayer.dash.manifest.BaseUrl> r58, androidx.media3.exoplayer.dash.manifest.SegmentBase r59, long r60, long r62, long r64, long r66, long r68, boolean r70) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r56 = this;
            r15 = r56
            r14 = r57
            java.lang.String r0 = "id"
            r1 = -1
            long r27 = parseLong(r14, r0, r1)
            int r0 = r56.parseContentType(r57)
            java.lang.String r1 = "mimeType"
            r13 = 0
            java.lang.String r29 = r14.getAttributeValue(r13, r1)
            java.lang.String r1 = "codecs"
            java.lang.String r30 = r14.getAttributeValue(r13, r1)
            java.lang.String r1 = "width"
            r2 = -1
            int r31 = parseInt(r14, r1, r2)
            java.lang.String r1 = "height"
            int r32 = parseInt(r14, r1, r2)
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r33 = parseFrameRate(r14, r1)
            java.lang.String r1 = "audioSamplingRate"
            int r34 = parseInt(r14, r1, r2)
            java.lang.String r12 = "lang"
            java.lang.String r1 = r14.getAttributeValue(r13, r12)
            java.lang.String r3 = "label"
            java.lang.String r35 = r14.getAttributeValue(r13, r3)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r36 = 0
            r37 = r59
            r38 = r1
            r39 = r2
            r41 = r13
            r40 = r36
            r1 = r62
            r62 = r64
        L_0x0080:
            r57.next()
            java.lang.String r13 = "BaseURL"
            boolean r13 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r13)
            if (r13 == 0) goto L_0x00be
            if (r40 != 0) goto L_0x0093
            long r1 = r15.parseAvailabilityTimeOffsetUs(r14, r1)
            r40 = 1
        L_0x0093:
            r13 = r58
            r64 = r1
            r17 = r11
            r11 = r70
            java.util.List r1 = r15.parseBaseUrl(r14, r13, r11)
            r3.addAll(r1)
            r1 = r64
            r15 = r4
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r51 = r10
            r53 = r12
            r4 = r17
        L_0x00b3:
            r55 = r38
            r54 = 0
            r16 = r62
            r38 = r3
            r3 = r9
            goto L_0x030e
        L_0x00be:
            r13 = r58
            r18 = r1
            r17 = r11
            r11 = r70
            java.lang.String r1 = "ContentProtection"
            boolean r1 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r1 == 0) goto L_0x00f9
            android.util.Pair r1 = r56.parseContentProtection(r57)
            java.lang.Object r2 = r1.first
            if (r2 == 0) goto L_0x00dc
            java.lang.Object r2 = r1.first
            r41 = r2
            java.lang.String r41 = (java.lang.String) r41
        L_0x00dc:
            java.lang.Object r2 = r1.second
            if (r2 == 0) goto L_0x00e7
            java.lang.Object r1 = r1.second
            androidx.media3.common.DrmInitData$SchemeData r1 = (androidx.media3.common.DrmInitData.SchemeData) r1
            r10.add(r1)
        L_0x00e7:
            r15 = r4
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r51 = r10
            r53 = r12
            r4 = r17
            r1 = r18
            goto L_0x00b3
        L_0x00f9:
            java.lang.String r1 = "ContentComponent"
            boolean r1 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r1 == 0) goto L_0x0130
            r2 = 0
            java.lang.String r1 = r14.getAttributeValue(r2, r12)
            r15 = r38
            java.lang.String r1 = checkLanguageConsistency(r15, r1)
            int r15 = r56.parseContentType(r57)
            int r0 = checkContentTypeConsistency(r0, r15)
            r55 = r1
            r54 = r2
            r38 = r3
            r15 = r4
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r3 = r9
            r51 = r10
            r53 = r12
            r4 = r17
            r1 = r18
        L_0x012c:
            r16 = r62
            goto L_0x030e
        L_0x0130:
            r15 = r38
            r2 = 0
            java.lang.String r1 = "Role"
            boolean r16 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r16 == 0) goto L_0x0160
            androidx.media3.exoplayer.dash.manifest.Descriptor r1 = parseDescriptor(r14, r1)
            r7.add(r1)
        L_0x0142:
            r44 = r0
            r54 = r2
            r38 = r3
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r3 = r9
            r51 = r10
            r53 = r12
            r55 = r15
            r42 = r18
            r0 = r62
            r15 = r4
            r4 = r17
            goto L_0x0308
        L_0x0160:
            java.lang.String r1 = "AudioChannelConfiguration"
            boolean r1 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r1 == 0) goto L_0x0187
            int r1 = r56.parseAudioChannelConfiguration(r57)
            r39 = r1
            r54 = r2
            r38 = r3
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r3 = r9
            r51 = r10
            r53 = r12
            r55 = r15
            r1 = r18
            r15 = r4
            r4 = r17
            goto L_0x012c
        L_0x0187:
            java.lang.String r1 = "Accessibility"
            boolean r16 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r16 == 0) goto L_0x0197
            androidx.media3.exoplayer.dash.manifest.Descriptor r1 = parseDescriptor(r14, r1)
            r8.add(r1)
            goto L_0x0142
        L_0x0197:
            java.lang.String r1 = "EssentialProperty"
            boolean r16 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r16 == 0) goto L_0x01a7
            androidx.media3.exoplayer.dash.manifest.Descriptor r1 = parseDescriptor(r14, r1)
            r6.add(r1)
            goto L_0x0142
        L_0x01a7:
            java.lang.String r1 = "SupplementalProperty"
            boolean r16 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r16 == 0) goto L_0x01b7
            androidx.media3.exoplayer.dash.manifest.Descriptor r1 = parseDescriptor(r14, r1)
            r5.add(r1)
            goto L_0x0142
        L_0x01b7:
            java.lang.String r1 = "Representation"
            boolean r1 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r1)
            if (r1 == 0) goto L_0x0235
            boolean r1 = r3.isEmpty()
            if (r1 != 0) goto L_0x01c9
            r1 = r0
            r16 = r3
            goto L_0x01cc
        L_0x01c9:
            r1 = r0
            r16 = r13
        L_0x01cc:
            r0 = r56
            r44 = r1
            r42 = r18
            r1 = r57
            r18 = r2
            r2 = r16
            r38 = r3
            r3 = r29
            r45 = r4
            r4 = r30
            r46 = r5
            r5 = r31
            r47 = r6
            r6 = r32
            r48 = r7
            r7 = r33
            r49 = r8
            r8 = r39
            r50 = r9
            r9 = r34
            r51 = r10
            r10 = r15
            r52 = r17
            r11 = r48
            r53 = r12
            r12 = r49
            r54 = r18
            r13 = r47
            r14 = r46
            r55 = r15
            r15 = r37
            r16 = r66
            r18 = r60
            r20 = r42
            r22 = r62
            r24 = r68
            r26 = r70
            androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.parseRepresentation(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r20, r22, r24, r26)
            androidx.media3.common.Format r1 = r0.format
            java.lang.String r1 = r1.sampleMimeType
            int r1 = androidx.media3.common.MimeTypes.getTrackType(r1)
            r14 = r44
            int r1 = checkContentTypeConsistency(r14, r1)
            r15 = r45
            r15.add(r0)
            r14 = r57
            r16 = r62
            r0 = r1
            r1 = r42
            goto L_0x02a1
        L_0x0235:
            r14 = r0
            r54 = r2
            r38 = r3
            r46 = r5
            r47 = r6
            r48 = r7
            r49 = r8
            r50 = r9
            r51 = r10
            r53 = r12
            r55 = r15
            r52 = r17
            r42 = r18
            r15 = r4
            java.lang.String r0 = "SegmentBase"
            r13 = r57
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r13, r0)
            if (r0 == 0) goto L_0x0271
            r0 = r37
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase r0 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SingleSegmentBase) r0
            r11 = r56
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase r0 = r11.parseSegmentBase(r13, r0)
            r16 = r62
            r37 = r0
            r0 = r14
            r1 = r42
            r3 = r50
            r4 = r52
            r14 = r13
            goto L_0x030e
        L_0x0271:
            r11 = r56
            java.lang.String r0 = "SegmentList"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r13, r0)
            if (r0 == 0) goto L_0x02a7
            r0 = r62
            long r16 = r11.parseAvailabilityTimeOffsetUs(r13, r0)
            r2 = r37
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentList r2 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SegmentList) r2
            r0 = r56
            r1 = r57
            r3 = r66
            r5 = r60
            r7 = r42
            r9 = r16
            r44 = r14
            r14 = r11
            r11 = r68
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentList r0 = r0.parseSegmentList(r1, r2, r3, r5, r7, r9, r11)
            r37 = r0
            r14 = r13
        L_0x029d:
            r1 = r42
            r0 = r44
        L_0x02a1:
            r3 = r50
            r4 = r52
            goto L_0x030e
        L_0x02a7:
            r0 = r62
            r44 = r14
            r14 = r11
            java.lang.String r2 = "SegmentTemplate"
            boolean r2 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r13, r2)
            if (r2 == 0) goto L_0x02d4
            long r16 = r14.parseAvailabilityTimeOffsetUs(r13, r0)
            r2 = r37
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentTemplate r2 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SegmentTemplate) r2
            r0 = r56
            r1 = r57
            r3 = r46
            r4 = r66
            r6 = r60
            r8 = r42
            r10 = r16
            r14 = r13
            r12 = r68
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentTemplate r0 = r0.parseSegmentTemplate(r1, r2, r3, r4, r6, r8, r10, r12)
            r37 = r0
            goto L_0x029d
        L_0x02d4:
            r14 = r13
            java.lang.String r2 = "InbandEventStream"
            boolean r3 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r2)
            if (r3 == 0) goto L_0x02e9
            androidx.media3.exoplayer.dash.manifest.Descriptor r2 = parseDescriptor(r14, r2)
            r3 = r50
            r3.add(r2)
            r4 = r52
            goto L_0x0308
        L_0x02e9:
            r3 = r50
            java.lang.String r2 = "Label"
            boolean r2 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r2)
            if (r2 == 0) goto L_0x02fd
            androidx.media3.common.Label r2 = r56.parseLabel(r57)
            r4 = r52
            r4.add(r2)
            goto L_0x0308
        L_0x02fd:
            r4 = r52
            boolean r2 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r57)
            if (r2 == 0) goto L_0x0308
            r56.parseAdaptationSetChild(r57)
        L_0x0308:
            r16 = r0
            r1 = r42
            r0 = r44
        L_0x030e:
            java.lang.String r5 = "AdaptationSet"
            boolean r5 = androidx.media3.common.util.XmlPullParserUtil.isEndTag(r14, r5)
            if (r5 == 0) goto L_0x0358
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = r15.size()
            r1.<init>(r2)
            r2 = r36
        L_0x0321:
            int r5 = r15.size()
            if (r2 >= r5) goto L_0x0345
            java.lang.Object r5 = r15.get(r2)
            androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo r5 = (androidx.media3.exoplayer.dash.manifest.DashManifestParser.RepresentationInfo) r5
            r57 = r56
            r58 = r5
            r59 = r35
            r60 = r4
            r61 = r41
            r62 = r51
            r63 = r3
            androidx.media3.exoplayer.dash.manifest.Representation r5 = r57.buildRepresentation(r58, r59, r60, r61, r62, r63)
            r1.add(r5)
            int r2 = r2 + 1
            goto L_0x0321
        L_0x0345:
            r57 = r56
            r58 = r27
            r60 = r0
            r61 = r1
            r62 = r49
            r63 = r47
            r64 = r46
            androidx.media3.exoplayer.dash.manifest.AdaptationSet r0 = r57.buildAdaptationSet(r58, r60, r61, r62, r63, r64)
            return r0
        L_0x0358:
            r9 = r3
            r11 = r4
            r4 = r15
            r62 = r16
            r3 = r38
            r5 = r46
            r6 = r47
            r7 = r48
            r8 = r49
            r10 = r51
            r12 = r53
            r13 = r54
            r38 = r55
            r15 = r56
            goto L_0x0080
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.util.List, androidx.media3.exoplayer.dash.manifest.SegmentBase, long, long, long, long, long, boolean):androidx.media3.exoplayer.dash.manifest.AdaptationSet");
    }

    /* access modifiers changed from: protected */
    public AdaptationSet buildAdaptationSet(long j, int i, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3, List<Descriptor> list4) {
        return new AdaptationSet(j, i, list, list2, list3, list4);
    }

    /* access modifiers changed from: protected */
    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY);
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if (MimeTypes.BASE_TYPE_AUDIO.equals(attributeValue)) {
            return 1;
        }
        if (MimeTypes.BASE_TYPE_VIDEO.equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        if ("image".equals(attributeValue)) {
            return 4;
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.util.UUID} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v19, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r0 = null;
        r4 = null;
        r6 = null;
        r5 = r5;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.lang.String, androidx.media3.common.DrmInitData.SchemeData> parseContentProtection(org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = r10.getAttributeValue(r1, r0)
            java.lang.String r2 = "MpdParser"
            r3 = 0
            if (r0 == 0) goto L_0x009f
            java.lang.String r0 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r0)
            r0.hashCode()
            int r4 = r0.hashCode()
            r5 = -1
            switch(r4) {
                case -1980789791: goto L_0x0040;
                case 489446379: goto L_0x0034;
                case 755418770: goto L_0x0028;
                case 1812765994: goto L_0x001c;
                default: goto L_0x001b;
            }
        L_0x001b:
            goto L_0x004b
        L_0x001c:
            java.lang.String r4 = "urn:mpeg:dash:mp4protection:2011"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0026
            goto L_0x004b
        L_0x0026:
            r5 = 3
            goto L_0x004b
        L_0x0028:
            java.lang.String r4 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0032
            goto L_0x004b
        L_0x0032:
            r5 = 2
            goto L_0x004b
        L_0x0034:
            java.lang.String r4 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x003e
            goto L_0x004b
        L_0x003e:
            r5 = 1
            goto L_0x004b
        L_0x0040:
            java.lang.String r4 = "urn:uuid:e2719d58-a985-b3c9-781a-b030af78d30e"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r5 = r3
        L_0x004b:
            switch(r5) {
                case 0: goto L_0x009c;
                case 1: goto L_0x0096;
                case 2: goto L_0x0093;
                case 3: goto L_0x004f;
                default: goto L_0x004e;
            }
        L_0x004e:
            goto L_0x009f
        L_0x004f:
            java.lang.String r0 = "value"
            java.lang.String r0 = r10.getAttributeValue(r1, r0)
            java.lang.String r4 = "default_KID"
            java.lang.String r4 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValueIgnorePrefix(r10, r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L_0x008c
            java.lang.String r5 = "00000000-0000-0000-0000-000000000000"
            boolean r5 = r5.equals(r4)
            if (r5 != 0) goto L_0x008c
            java.lang.String r5 = "\\s+"
            java.lang.String[] r4 = r4.split(r5)
            int r5 = r4.length
            java.util.UUID[] r5 = new java.util.UUID[r5]
            r6 = r3
        L_0x0074:
            int r7 = r4.length
            if (r6 >= r7) goto L_0x0082
            r7 = r4[r6]
            java.util.UUID r7 = java.util.UUID.fromString(r7)
            r5[r6] = r7
            int r6 = r6 + 1
            goto L_0x0074
        L_0x0082:
            java.util.UUID r4 = androidx.media3.common.C.COMMON_PSSH_UUID
            byte[] r4 = androidx.media3.extractor.mp4.PsshAtomUtil.buildPsshAtom(r4, r5, r1)
            java.util.UUID r5 = androidx.media3.common.C.COMMON_PSSH_UUID
            r6 = r1
            goto L_0x00a3
        L_0x008c:
            java.lang.String r4 = "Ignoring <ContentProtection> with schemeIdUri=\"urn:mpeg:dash:mp4protection:2011\" (ClearKey) due to missing required default_KID attribute."
            androidx.media3.common.util.Log.w(r2, r4)
            r4 = r1
            goto L_0x00a1
        L_0x0093:
            java.util.UUID r5 = androidx.media3.common.C.WIDEVINE_UUID
            goto L_0x0098
        L_0x0096:
            java.util.UUID r5 = androidx.media3.common.C.PLAYREADY_UUID
        L_0x0098:
            r0 = r1
            r4 = r0
            r6 = r4
            goto L_0x00a3
        L_0x009c:
            java.util.UUID r5 = androidx.media3.common.C.CLEARKEY_UUID
            goto L_0x0098
        L_0x009f:
            r0 = r1
            r4 = r0
        L_0x00a1:
            r5 = r4
            r6 = r5
        L_0x00a3:
            r10.next()
            java.lang.String r7 = "clearkey:Laurl"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r10, r7)
            r8 = 4
            if (r7 != 0) goto L_0x00b7
            java.lang.String r7 = "dashif:Laurl"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x00c2
        L_0x00b7:
            int r7 = r10.next()
            if (r7 != r8) goto L_0x00c2
            java.lang.String r6 = r10.getText()
            goto L_0x0120
        L_0x00c2:
            java.lang.String r7 = "ms:laurl"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x00d1
            java.lang.String r6 = "licenseUrl"
            java.lang.String r6 = r10.getAttributeValue(r1, r6)
            goto L_0x0120
        L_0x00d1:
            if (r4 != 0) goto L_0x00f6
            java.lang.String r7 = "pssh"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isStartTagIgnorePrefix(r10, r7)
            if (r7 == 0) goto L_0x00f6
            int r7 = r10.next()
            if (r7 != r8) goto L_0x00f6
            java.lang.String r4 = r10.getText()
            byte[] r4 = android.util.Base64.decode(r4, r3)
            java.util.UUID r5 = androidx.media3.extractor.mp4.PsshAtomUtil.parseUuid(r4)
            if (r5 != 0) goto L_0x0120
            java.lang.String r4 = "Skipping malformed cenc:pssh data"
            androidx.media3.common.util.Log.w(r2, r4)
            r4 = r1
            goto L_0x0120
        L_0x00f6:
            if (r4 != 0) goto L_0x011d
            java.util.UUID r7 = androidx.media3.common.C.PLAYREADY_UUID
            boolean r7 = r7.equals(r5)
            if (r7 == 0) goto L_0x011d
            java.lang.String r7 = "mspr:pro"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x011d
            int r7 = r10.next()
            if (r7 != r8) goto L_0x011d
            java.util.UUID r4 = androidx.media3.common.C.PLAYREADY_UUID
            java.lang.String r7 = r10.getText()
            byte[] r7 = android.util.Base64.decode(r7, r3)
            byte[] r4 = androidx.media3.extractor.mp4.PsshAtomUtil.buildPsshAtom(r4, r7)
            goto L_0x0120
        L_0x011d:
            maybeSkipTag(r10)
        L_0x0120:
            java.lang.String r7 = "ContentProtection"
            boolean r7 = androidx.media3.common.util.XmlPullParserUtil.isEndTag(r10, r7)
            if (r7 == 0) goto L_0x00a3
            if (r5 == 0) goto L_0x0132
            androidx.media3.common.DrmInitData$SchemeData r1 = new androidx.media3.common.DrmInitData$SchemeData
            java.lang.String r10 = "video/mp4"
            r1.<init>(r5, r6, r10, r4)
        L_0x0132:
            android.util.Pair r10 = android.util.Pair.create(r0, r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        maybeSkipTag(xmlPullParser);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v0, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v3, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v4, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v5, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v6, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v7, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v8, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v9, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r31v10, resolved type: java.util.ArrayList} */
    /* JADX WARNING: type inference failed for: r31v1 */
    /* JADX WARNING: type inference failed for: r31v2 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01f3 A[LOOP:0: B:1:0x006b->B:53:0x01f3, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019d A[EDGE_INSN: B:54:0x019d->B:45:0x019d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.exoplayer.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r36, java.util.List<androidx.media3.exoplayer.dash.manifest.BaseUrl> r37, java.lang.String r38, java.lang.String r39, int r40, int r41, float r42, int r43, int r44, java.lang.String r45, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r46, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r47, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r48, java.util.List<androidx.media3.exoplayer.dash.manifest.Descriptor> r49, androidx.media3.exoplayer.dash.manifest.SegmentBase r50, long r51, long r53, long r55, long r57, long r59, boolean r61) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r35 = this;
            r15 = r35
            r14 = r36
            java.lang.String r0 = "id"
            r1 = 0
            java.lang.String r16 = r14.getAttributeValue(r1, r0)
            java.lang.String r0 = "bandwidth"
            r2 = -1
            int r17 = parseInt(r14, r0, r2)
            java.lang.String r0 = "mimeType"
            r2 = r38
            java.lang.String r18 = parseString(r14, r0, r2)
            java.lang.String r0 = "codecs"
            r2 = r39
            java.lang.String r19 = parseString(r14, r0, r2)
            java.lang.String r0 = "width"
            r2 = r40
            int r20 = parseInt(r14, r0, r2)
            java.lang.String r0 = "height"
            r2 = r41
            int r21 = parseInt(r14, r0, r2)
            r0 = r42
            float r22 = parseFrameRate(r14, r0)
            java.lang.String r0 = "audioSamplingRate"
            r2 = r44
            int r23 = parseInt(r14, r0, r2)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r0 = r48
            r12.<init>(r0)
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = r49
            r9.<init>(r10)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r0 = 0
            r24 = r43
            r5 = r55
            r25 = r0
            r26 = r1
            r0 = r50
            r1 = r57
        L_0x006b:
            r36.next()
            java.lang.String r3 = "BaseURL"
            boolean r3 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r3 == 0) goto L_0x0091
            if (r25 != 0) goto L_0x007e
            long r5 = r15.parseAvailabilityTimeOffsetUs(r14, r5)
            r25 = 1
        L_0x007e:
            r8 = r37
            r3 = r61
            java.util.List r4 = r15.parseBaseUrl(r14, r8, r3)
            r7.addAll(r4)
        L_0x0089:
            r31 = r7
            r15 = r13
            r7 = r24
            r24 = r0
            goto L_0x00a7
        L_0x0091:
            r8 = r37
            r3 = r61
            java.lang.String r4 = "AudioChannelConfiguration"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r4)
            if (r4 == 0) goto L_0x00ab
            int r4 = r35.parseAudioChannelConfiguration(r36)
            r24 = r0
            r31 = r7
            r15 = r13
            r7 = r4
        L_0x00a7:
            r13 = r11
            r11 = r9
            goto L_0x0195
        L_0x00ab:
            java.lang.String r4 = "SegmentBase"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r4)
            if (r4 == 0) goto L_0x00ba
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase r0 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SingleSegmentBase) r0
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase r0 = r15.parseSegmentBase(r14, r0)
            goto L_0x0089
        L_0x00ba:
            java.lang.String r4 = "SegmentList"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r4)
            if (r4 == 0) goto L_0x00f6
            long r27 = r15.parseAvailabilityTimeOffsetUs(r14, r1)
            r2 = r0
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentList r2 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SegmentList) r2
            r0 = r35
            r1 = r36
            r3 = r51
            r29 = r5
            r5 = r53
            r31 = r7
            r7 = r29
            r32 = r9
            r9 = r27
            r33 = r11
            r34 = r12
            r11 = r59
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentList r0 = r0.parseSegmentList(r1, r2, r3, r5, r7, r9, r11)
            r15 = r13
        L_0x00e6:
            r7 = r24
            r1 = r27
        L_0x00ea:
            r5 = r29
            r11 = r32
            r13 = r33
            r12 = r34
        L_0x00f2:
            r24 = r0
            goto L_0x0195
        L_0x00f6:
            r29 = r5
            r31 = r7
            r32 = r9
            r33 = r11
            r34 = r12
            java.lang.String r3 = "SegmentTemplate"
            boolean r3 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r3 == 0) goto L_0x0125
            long r27 = r15.parseAvailabilityTimeOffsetUs(r14, r1)
            r2 = r0
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentTemplate r2 = (androidx.media3.exoplayer.dash.manifest.SegmentBase.SegmentTemplate) r2
            r0 = r35
            r1 = r36
            r3 = r49
            r4 = r51
            r6 = r53
            r8 = r29
            r10 = r27
            r15 = r13
            r12 = r59
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SegmentTemplate r0 = r0.parseSegmentTemplate(r1, r2, r3, r4, r6, r8, r10, r12)
            goto L_0x00e6
        L_0x0125:
            r15 = r13
            java.lang.String r3 = "ContentProtection"
            boolean r3 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r3 == 0) goto L_0x014a
            android.util.Pair r3 = r35.parseContentProtection(r36)
            java.lang.Object r4 = r3.first
            if (r4 == 0) goto L_0x013c
            java.lang.Object r4 = r3.first
            r26 = r4
            java.lang.String r26 = (java.lang.String) r26
        L_0x013c:
            java.lang.Object r4 = r3.second
            if (r4 == 0) goto L_0x0147
            java.lang.Object r3 = r3.second
            androidx.media3.common.DrmInitData$SchemeData r3 = (androidx.media3.common.DrmInitData.SchemeData) r3
            r15.add(r3)
        L_0x0147:
            r7 = r24
            goto L_0x00ea
        L_0x014a:
            java.lang.String r3 = "InbandEventStream"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r4 == 0) goto L_0x0160
            androidx.media3.exoplayer.dash.manifest.Descriptor r3 = parseDescriptor(r14, r3)
            r13 = r33
            r13.add(r3)
            r11 = r32
            r12 = r34
            goto L_0x018f
        L_0x0160:
            r13 = r33
            java.lang.String r3 = "EssentialProperty"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r4 == 0) goto L_0x0176
            androidx.media3.exoplayer.dash.manifest.Descriptor r3 = parseDescriptor(r14, r3)
            r12 = r34
            r12.add(r3)
            r11 = r32
            goto L_0x018f
        L_0x0176:
            r12 = r34
            java.lang.String r3 = "SupplementalProperty"
            boolean r4 = androidx.media3.common.util.XmlPullParserUtil.isStartTag(r14, r3)
            if (r4 == 0) goto L_0x018a
            androidx.media3.exoplayer.dash.manifest.Descriptor r3 = parseDescriptor(r14, r3)
            r11 = r32
            r11.add(r3)
            goto L_0x018f
        L_0x018a:
            r11 = r32
            maybeSkipTag(r36)
        L_0x018f:
            r7 = r24
            r5 = r29
            goto L_0x00f2
        L_0x0195:
            java.lang.String r0 = "Representation"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isEndTag(r14, r0)
            if (r0 == 0) goto L_0x01f3
            r0 = r35
            r1 = r16
            r2 = r18
            r3 = r20
            r4 = r21
            r5 = r22
            r6 = r7
            r7 = r23
            r8 = r17
            r9 = r45
            r10 = r46
            r27 = r11
            r11 = r47
            r28 = r12
            r12 = r19
            r29 = r13
            r13 = r28
            r14 = r27
            androidx.media3.common.Format r0 = r0.buildFormat(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            if (r24 == 0) goto L_0x01c7
            goto L_0x01ce
        L_0x01c7:
            androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase r1 = new androidx.media3.exoplayer.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
            r24 = r1
        L_0x01ce:
            androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo r1 = new androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo
            boolean r2 = r31.isEmpty()
            if (r2 != 0) goto L_0x01d7
            goto L_0x01d9
        L_0x01d7:
            r31 = r37
        L_0x01d9:
            r2 = -1
            r36 = r1
            r37 = r0
            r38 = r31
            r39 = r24
            r40 = r26
            r41 = r15
            r42 = r29
            r43 = r28
            r44 = r27
            r45 = r2
            r36.<init>(r37, r38, r39, r40, r41, r42, r43, r44, r45)
            return r1
        L_0x01f3:
            r10 = r49
            r9 = r11
            r11 = r13
            r13 = r15
            r0 = r24
            r15 = r35
            r24 = r7
            r7 = r31
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.util.List, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, androidx.media3.exoplayer.dash.manifest.SegmentBase, long, long, long, long, long, boolean):androidx.media3.exoplayer.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, List<Descriptor> list, List<Descriptor> list2, String str4, List<Descriptor> list3, List<Descriptor> list4) {
        String str5 = str2;
        int i6 = i;
        int i7 = i2;
        List<Descriptor> list5 = list;
        List<Descriptor> list6 = list3;
        String str6 = str4;
        String sampleMimeType = getSampleMimeType(str2, str6);
        if (MimeTypes.AUDIO_E_AC3.equals(sampleMimeType)) {
            sampleMimeType = parseEac3SupplementalProperties(list4);
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(sampleMimeType)) {
                str6 = MimeTypes.CODEC_E_AC3_JOC;
            }
        }
        int parseSelectionFlagsFromRoleDescriptors = parseSelectionFlagsFromRoleDescriptors(list5);
        int parseRoleFlagsFromRoleDescriptors = parseRoleFlagsFromRoleDescriptors(list5) | parseRoleFlagsFromAccessibilityDescriptors(list2) | parseRoleFlagsFromProperties(list6) | parseRoleFlagsFromProperties(list4);
        Pair<Integer, Integer> parseTileCountFromProperties = parseTileCountFromProperties(list6);
        String str7 = str;
        Format.Builder language = new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType(sampleMimeType).setCodecs(str6).setPeakBitrate(i5).setSelectionFlags(parseSelectionFlagsFromRoleDescriptors).setRoleFlags(parseRoleFlagsFromRoleDescriptors).setLanguage(str3);
        int i8 = -1;
        Format.Builder tileCountVertical = language.setTileCountHorizontal(parseTileCountFromProperties != null ? ((Integer) parseTileCountFromProperties.first).intValue() : -1).setTileCountVertical(parseTileCountFromProperties != null ? ((Integer) parseTileCountFromProperties.second).intValue() : -1);
        if (MimeTypes.isVideo(sampleMimeType)) {
            tileCountVertical.setWidth(i).setHeight(i7).setFrameRate(f);
        } else if (MimeTypes.isAudio(sampleMimeType)) {
            tileCountVertical.setChannelCount(i3).setSampleRate(i4);
        } else if (MimeTypes.isText(sampleMimeType)) {
            if (MimeTypes.APPLICATION_CEA608.equals(sampleMimeType)) {
                i8 = parseCea608AccessibilityChannel(list2);
            } else if (MimeTypes.APPLICATION_CEA708.equals(sampleMimeType)) {
                i8 = parseCea708AccessibilityChannel(list2);
            }
            tileCountVertical.setAccessibilityChannel(i8);
        } else if (MimeTypes.isImage(sampleMimeType)) {
            tileCountVertical.setWidth(i).setHeight(i7);
        }
        return tileCountVertical.build();
    }

    /* access modifiers changed from: protected */
    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, List<Label> list, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        RepresentationInfo representationInfo2 = representationInfo;
        String str3 = str;
        Format.Builder buildUpon = representationInfo2.format.buildUpon();
        if (str3 == null || !list.isEmpty()) {
            buildUpon.setLabels(list);
        } else {
            buildUpon.setLabel(str3);
        }
        String str4 = representationInfo2.drmSchemeType;
        if (str4 == null) {
            str4 = str2;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo2.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            fillInClearKeyInformation(arrayList3);
            filterRedundantIncompleteSchemeDatas(arrayList3);
            buildUpon.setDrmInitData(new DrmInitData(str4, (List<DrmInitData.SchemeData>) arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo2.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(representationInfo2.revisionId, buildUpon.build(), representationInfo2.baseUrls, representationInfo2.segmentBase, arrayList4, representationInfo2.essentialProperties, representationInfo2.supplementalProperties, (String) null);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long parseLong = parseLong(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.timescale : 1);
        long j3 = 0;
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.presentationTimeOffset : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.indexStart : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.indexLength;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.initialization;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentBase"));
        return buildSingleSegmentBase(rangedUri, parseLong, parseLong2, j2, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList, long j, long j2, long j3, long j4, long j5) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentList segmentList2 = segmentList;
        long j6 = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentList2 != null ? segmentList2.duration : C.TIME_UNSET);
        if (segmentList2 != null) {
            j6 = segmentList2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j6);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j3, j4);
        List<SegmentBase.SegmentTimelineElement> list = null;
        List list2 = null;
        RangedUri rangedUri = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list = parseSegmentTimeline(xmlPullParser, parseLong, j2);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentURL")) {
                if (list2 == null) {
                    list2 = new ArrayList();
                }
                list2.add(parseSegmentUrl(xmlPullParser));
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentList2.initialization;
            }
            if (list == null) {
                list = segmentList2.segmentTimeline;
            }
            if (list2 == null) {
                list2 = segmentList2.mediaSegments;
            }
        }
        return buildSegmentList(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list, finalAvailabilityTimeOffset, list2, j5, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentBase.SegmentTimelineElement> list, long j5, List<RangedUri> list2, long j6, long j7) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, j3, j4, list, j5, list2, Util.msToUs(j6), Util.msToUs(j7));
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate, List<Descriptor> list, long j, long j2, long j3, long j4, long j5) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentTemplate segmentTemplate2 = segmentTemplate;
        long j6 = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentTemplate2 != null ? segmentTemplate2.duration : C.TIME_UNSET);
        if (segmentTemplate2 != null) {
            j6 = segmentTemplate2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j6);
        long parseLastSegmentNumberSupplementalProperty = parseLastSegmentNumberSupplementalProperty(list);
        long finalAvailabilityTimeOffset = getFinalAvailabilityTimeOffset(j3, j4);
        List<SegmentBase.SegmentTimelineElement> list2 = null;
        UrlTemplate parseUrlTemplate = parseUrlTemplate(xmlPullParser2, SVGParser.XML_STYLESHEET_ATTR_MEDIA, segmentTemplate2 != null ? segmentTemplate2.mediaTemplate : null);
        UrlTemplate parseUrlTemplate2 = parseUrlTemplate(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.initializationTemplate : null);
        RangedUri rangedUri = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list2 = parseSegmentTimeline(xmlPullParser, parseLong, j2);
            } else {
                maybeSkipTag(xmlPullParser);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTemplate")) {
                break;
            }
        }
        if (segmentTemplate2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentTemplate2.initialization;
            }
            if (list2 == null) {
                list2 = segmentTemplate2.segmentTimeline;
            }
        }
        return buildSegmentTemplate(rangedUri, parseLong, parseLong2, parseLong4, parseLastSegmentNumberSupplementalProperty, parseLong3, list2, finalAvailabilityTimeOffset, parseUrlTemplate2, parseUrlTemplate, j5, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, long j5, List<SegmentBase.SegmentTimelineElement> list, long j6, UrlTemplate urlTemplate, UrlTemplate urlTemplate2, long j7, long j8) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, j3, j4, j5, list, j6, urlTemplate, urlTemplate2, Util.msToUs(j7), Util.msToUs(j8));
    }

    /* access modifiers changed from: protected */
    public EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        long j;
        ByteArrayOutputStream byteArrayOutputStream;
        ArrayList arrayList;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String parseString = parseString(xmlPullParser2, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser2, "value", "");
        long parseLong = parseLong(xmlPullParser2, "timescale", 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", 0);
        ArrayList arrayList2 = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(512);
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Event")) {
                byteArrayOutputStream = byteArrayOutputStream2;
                long j2 = parseLong2;
                j = parseLong2;
                arrayList = arrayList2;
                arrayList.add(parseEvent(xmlPullParser, parseString, parseString2, parseLong, j2, byteArrayOutputStream));
            } else {
                byteArrayOutputStream = byteArrayOutputStream2;
                j = parseLong2;
                arrayList = arrayList2;
                maybeSkipTag(xmlPullParser);
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "EventStream")) {
                break;
            }
            arrayList2 = arrayList;
            byteArrayOutputStream2 = byteArrayOutputStream;
            parseLong2 = j;
        }
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = ((Long) pair.first).longValue();
            eventMessageArr[i] = (EventMessage) pair.second;
        }
        return buildEventStream(parseString, parseString2, parseLong, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public EventStream buildEventStream(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        return new EventStream(str, str2, j, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public Pair<Long, EventMessage> parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j, long j2, ByteArrayOutputStream byteArrayOutputStream) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long parseLong = parseLong(xmlPullParser2, "id", 0);
        long parseLong2 = parseLong(xmlPullParser2, "duration", C.TIME_UNSET);
        long parseLong3 = parseLong(xmlPullParser2, "presentationTime", 0);
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(parseLong2, 1000, j);
        long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(parseLong3 - j2, 1000000, j);
        String parseString = parseString(xmlPullParser2, "messageData", (String) null);
        byte[] parseEventObject = parseEventObject(xmlPullParser2, byteArrayOutputStream);
        Long valueOf = Long.valueOf(scaleLargeTimestamp2);
        if (parseString != null) {
            parseEventObject = Util.getUtf8Bytes(parseString);
        }
        return Pair.create(valueOf, buildEvent(str, str2, parseLong, scaleLargeTimestamp, parseEventObject));
    }

    /* access modifiers changed from: protected */
    public byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, StandardCharsets.UTF_8.name());
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    newSerializer.startDocument((String) null, false);
                    break;
                case 1:
                    newSerializer.endDocument();
                    break;
                case 2:
                    newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    newSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    newSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    newSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    newSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    newSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    newSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    newSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        newSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public EventMessage buildEvent(String str, String str2, long j, long j2, byte[] bArr) {
        return new EventMessage(str, str2, j2, j, bArr);
    }

    /* access modifiers changed from: protected */
    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser, long j, long j2) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        long j4 = -9223372036854775807L;
        boolean z = false;
        int i = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, ExifInterface.LATITUDE_SOUTH)) {
                long parseLong = parseLong(xmlPullParser2, "t", C.TIME_UNSET);
                if (z) {
                    j3 = addSegmentTimelineElementsToList(arrayList, j3, j4, i, parseLong);
                }
                if (parseLong == C.TIME_UNSET) {
                    parseLong = j3;
                }
                j4 = parseLong(xmlPullParser2, "d", C.TIME_UNSET);
                i = parseInt(xmlPullParser2, "r", 0);
                z = true;
                j3 = parseLong;
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTimeline"));
        if (z) {
            addSegmentTimelineElementsToList(arrayList, j3, j4, i, Util.scaleLargeTimestamp(j2, j, 1000));
        }
        return arrayList;
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> list, long j, long j2, int i, long j3) {
        int ceilDivide = i >= 0 ? i + 1 : (int) Util.ceilDivide(j3 - j, j2);
        for (int i2 = 0; i2 < ceilDivide; i2++) {
            list.add(buildSegmentTimelineElement(j, j2));
            j += j2;
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    /* access modifiers changed from: protected */
    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    /* access modifiers changed from: protected */
    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, SVGParser.XML_STYLESHEET_ATTR_MEDIA, "mediaRange");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j2 = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - j2) + 1;
                return buildRangedUri(attributeValue, j2, j);
            }
        } else {
            j2 = 0;
        }
        j = -1;
        return buildRangedUri(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public ProgramInformation parseProgramInformation(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        String parseString = parseString(xmlPullParser, "moreInformationURL", (String) null);
        String parseString2 = parseString(xmlPullParser, "lang", (String) null);
        String str2 = null;
        String str3 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Title")) {
                str = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Source")) {
                str2 = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, ExifInterface.TAG_COPYRIGHT)) {
                str3 = xmlPullParser.nextText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
            String str4 = str3;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ProgramInformation")) {
                return new ProgramInformation(str, str2, str4, parseString, parseString2);
            }
            str3 = str4;
        }
    }

    /* access modifiers changed from: protected */
    public Label parseLabel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return new Label(xmlPullParser.getAttributeValue((String) null, "lang"), parseText(xmlPullParser, "Label"));
    }

    /* access modifiers changed from: protected */
    public List<BaseUrl> parseBaseUrl(XmlPullParser xmlPullParser, List<BaseUrl> list, boolean z) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "dvb:priority");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : z ? 1 : Integer.MIN_VALUE;
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "dvb:weight");
        int parseInt2 = attributeValue2 != null ? Integer.parseInt(attributeValue2) : 1;
        String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "serviceLocation");
        String parseText = parseText(xmlPullParser, "BaseURL");
        if (UriUtil.isAbsolute(parseText)) {
            if (attributeValue3 == null) {
                attributeValue3 = parseText;
            }
            return Lists.newArrayList((E[]) new BaseUrl[]{new BaseUrl(parseText, attributeValue3, parseInt, parseInt2)});
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            BaseUrl baseUrl = list.get(i);
            String resolve = UriUtil.resolve(baseUrl.url, parseText);
            String str = attributeValue3 == null ? resolve : attributeValue3;
            if (z) {
                parseInt = baseUrl.priority;
                parseInt2 = baseUrl.weight;
                str = baseUrl.serviceLocation;
            }
            arrayList.add(new BaseUrl(resolve, str, parseInt, parseInt2));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public long parseAvailabilityTimeOffsetUs(XmlPullParser xmlPullParser, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "availabilityTimeOffset");
        if (attributeValue == null) {
            return j;
        }
        if ("INF".equals(attributeValue)) {
            return Long.MAX_VALUE;
        }
        return (long) (Float.parseFloat(attributeValue) * 1000000.0f);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int parseAudioChannelConfiguration(org.xmlpull.v1.XmlPullParser r4) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = "schemeIdUri"
            r1 = 0
            java.lang.String r0 = parseString(r4, r0, r1)
            r0.hashCode()
            int r1 = r0.hashCode()
            r2 = -1
            switch(r1) {
                case -2128649360: goto L_0x0059;
                case -1352850286: goto L_0x004d;
                case -1138141449: goto L_0x0042;
                case -986633423: goto L_0x0036;
                case -79006963: goto L_0x002b;
                case 312179081: goto L_0x0020;
                case 2036691300: goto L_0x0014;
                default: goto L_0x0012;
            }
        L_0x0012:
            r0 = r2
            goto L_0x0064
        L_0x0014:
            java.lang.String r1 = "urn:dolby:dash:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001e
            goto L_0x0012
        L_0x001e:
            r0 = 6
            goto L_0x0064
        L_0x0020:
            java.lang.String r1 = "tag:dts.com,2018:uhd:audio_channel_configuration"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0029
            goto L_0x0012
        L_0x0029:
            r0 = 5
            goto L_0x0064
        L_0x002b:
            java.lang.String r1 = "tag:dts.com,2014:dash:audio_channel_configuration:2012"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0034
            goto L_0x0012
        L_0x0034:
            r0 = 4
            goto L_0x0064
        L_0x0036:
            java.lang.String r1 = "urn:mpeg:mpegB:cicp:ChannelConfiguration"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0040
            goto L_0x0012
        L_0x0040:
            r0 = 3
            goto L_0x0064
        L_0x0042:
            java.lang.String r1 = "tag:dolby.com,2014:dash:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x004b
            goto L_0x0012
        L_0x004b:
            r0 = 2
            goto L_0x0064
        L_0x004d:
            java.lang.String r1 = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0057
            goto L_0x0012
        L_0x0057:
            r0 = 1
            goto L_0x0064
        L_0x0059:
            java.lang.String r1 = "urn:dts:dash:audio_channel_configuration:2012"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0063
            goto L_0x0012
        L_0x0063:
            r0 = 0
        L_0x0064:
            switch(r0) {
                case 0: goto L_0x007f;
                case 1: goto L_0x0077;
                case 2: goto L_0x0072;
                case 3: goto L_0x006d;
                case 4: goto L_0x007f;
                case 5: goto L_0x0068;
                case 6: goto L_0x0072;
                default: goto L_0x0067;
            }
        L_0x0067:
            goto L_0x0083
        L_0x0068:
            int r2 = parseDtsxChannelConfiguration(r4)
            goto L_0x0083
        L_0x006d:
            int r2 = parseMpegChannelConfiguration(r4)
            goto L_0x0083
        L_0x0072:
            int r2 = parseDolbyChannelConfiguration(r4)
            goto L_0x0083
        L_0x0077:
            java.lang.String r0 = "value"
            int r2 = parseInt(r4, r0, r2)
            goto L_0x0083
        L_0x007f:
            int r2 = parseDtsChannelConfiguration(r4)
        L_0x0083:
            r4.next()
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = androidx.media3.common.util.XmlPullParserUtil.isEndTag(r4, r0)
            if (r0 == 0) goto L_0x0083
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseAudioChannelConfiguration(org.xmlpull.v1.XmlPullParser):int");
    }

    /* access modifiers changed from: protected */
    public int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                i |= parseSelectionFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseSelectionFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        return (str.equals("forced_subtitle") || str.equals("forced-subtitle")) ? 2 : 0;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromRoleDescriptors(List<Descriptor> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                i |= parseRoleFlagsFromDashRoleScheme(descriptor.value);
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> list) {
        int parseTvaAudioPurposeCsValue;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if (Ascii.equalsIgnoreCase("urn:mpeg:dash:role:2011", descriptor.schemeIdUri)) {
                parseTvaAudioPurposeCsValue = parseRoleFlagsFromDashRoleScheme(descriptor.value);
            } else if (Ascii.equalsIgnoreCase("urn:tva:metadata:cs:AudioPurposeCS:2007", descriptor.schemeIdUri)) {
                parseTvaAudioPurposeCsValue = parseTvaAudioPurposeCsValue(descriptor.value);
            }
            i |= parseTvaAudioPurposeCsValue;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromProperties(List<Descriptor> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/trickmode", list.get(i2).schemeIdUri)) {
                i |= 16384;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromDashRoleScheme(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2060497896:
                if (str.equals("subtitle")) {
                    c = 0;
                    break;
                }
                break;
            case -1724546052:
                if (str.equals("description")) {
                    c = 1;
                    break;
                }
                break;
            case -1580883024:
                if (str.equals("enhanced-audio-intelligibility")) {
                    c = 2;
                    break;
                }
                break;
            case -1574842690:
                if (str.equals("forced_subtitle")) {
                    c = 3;
                    break;
                }
                break;
            case -1408024454:
                if (str.equals(SVGParser.XML_STYLESHEET_ATTR_ALTERNATE)) {
                    c = 4;
                    break;
                }
                break;
            case -1396432756:
                if (str.equals("forced-subtitle")) {
                    c = 5;
                    break;
                }
                break;
            case 99825:
                if (str.equals("dub")) {
                    c = 6;
                    break;
                }
                break;
            case 3343801:
                if (str.equals(SentryThread.JsonKeys.MAIN)) {
                    c = 7;
                    break;
                }
                break;
            case 3530173:
                if (str.equals("sign")) {
                    c = 8;
                    break;
                }
                break;
            case 552573414:
                if (str.equals("caption")) {
                    c = 9;
                    break;
                }
                break;
            case 899152809:
                if (str.equals("commentary")) {
                    c = 10;
                    break;
                }
                break;
            case 1629013393:
                if (str.equals("emergency")) {
                    c = 11;
                    break;
                }
                break;
            case 1855372047:
                if (str.equals("supplementary")) {
                    c = 12;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 3:
            case 5:
                return 128;
            case 1:
                return 512;
            case 2:
                return 2048;
            case 4:
                return 2;
            case 6:
                return 16;
            case 7:
                return 1;
            case 8:
                return 256;
            case 9:
                return 64;
            case 10:
                return 8;
            case 11:
                return 32;
            case 12:
                return 4;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int parseTvaAudioPurposeCsValue(String str) {
        if (str == null) {
            return 0;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    c = 1;
                    break;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                if (str.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    c = 2;
                    break;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    c = 3;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 512;
            case 1:
                return 2048;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 1;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public String[] parseProfiles(XmlPullParser xmlPullParser, String str, String[] strArr) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return strArr;
        }
        return attributeValue.split(",");
    }

    /* access modifiers changed from: protected */
    public Pair<Integer, Integer> parseTileCountFromProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ((Ascii.equalsIgnoreCase("http://dashif.org/thumbnail_tile", descriptor.schemeIdUri) || Ascii.equalsIgnoreCase("http://dashif.org/guidelines/thumbnail_tile", descriptor.schemeIdUri)) && descriptor.value != null) {
                String[] split = Util.split(descriptor.value, ViewHierarchyNode.JsonKeys.X);
                if (split.length != 2) {
                    continue;
                } else {
                    try {
                        return Pair.create(Integer.valueOf(Integer.parseInt(split[0])), Integer.valueOf(Integer.parseInt(split[1])));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return null;
    }

    public static void maybeSkipTag(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                    i++;
                } else if (XmlPullParserUtil.isEndTag(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.hasData()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (arrayList.get(i).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static void fillInClearKeyInformation(ArrayList<DrmInitData.SchemeData> arrayList) {
        String str;
        int i = 0;
        while (true) {
            if (i >= arrayList.size()) {
                str = null;
                break;
            }
            DrmInitData.SchemeData schemeData = arrayList.get(i);
            if (C.CLEARKEY_UUID.equals(schemeData.uuid) && schemeData.licenseServerUrl != null) {
                str = schemeData.licenseServerUrl;
                arrayList.remove(i);
                break;
            }
            i++;
        }
        if (str != null) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                DrmInitData.SchemeData schemeData2 = arrayList.get(i2);
                if (C.COMMON_PSSH_UUID.equals(schemeData2.uuid) && schemeData2.licenseServerUrl == null) {
                    arrayList.set(i2, new DrmInitData.SchemeData(C.CLEARKEY_UUID, str, schemeData2.mimeType, schemeData2.data));
                }
            }
        }
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (MimeTypes.isText(str) || MimeTypes.isImage(str)) {
            return str;
        }
        if (!MimeTypes.APPLICATION_MP4.equals(str)) {
            return null;
        }
        String mediaMimeType = MimeTypes.getMediaMimeType(str2);
        return MimeTypes.TEXT_VTT.equals(mediaMimeType) ? MimeTypes.APPLICATION_MP4VTT : mediaMimeType;
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, "value", (String) null);
        String parseString3 = parseString(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(parseString, parseString2, parseString3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            String str = descriptor.schemeIdUri;
            if ("tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(str) && "JOC".equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(str) && MimeTypes.CODEC_E_AC3_JOC.equals(descriptor.value)) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
        }
        return MimeTypes.AUDIO_E_AC3;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDateTime(attributeValue);
    }

    protected static String parseText(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String str2 = "";
        do {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                str2 = xmlPullParser.getText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return str2;
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static float parseFloat(XmlPullParser xmlPullParser, String str, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? f : Float.parseFloat(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    protected static int parseMpegChannelConfiguration(XmlPullParser xmlPullParser) {
        int parseInt = parseInt(xmlPullParser, "value", -1);
        if (parseInt < 0) {
            return -1;
        }
        int[] iArr = MPEG_CHANNEL_CONFIGURATION_MAPPING;
        if (parseInt < iArr.length) {
            return iArr[parseInt];
        }
        return -1;
    }

    protected static int parseDtsChannelConfiguration(XmlPullParser xmlPullParser) {
        int parseInt = parseInt(xmlPullParser, "value", -1);
        if (parseInt <= 0 || parseInt >= 33) {
            return -1;
        }
        return parseInt;
    }

    protected static int parseDtsxChannelConfiguration(XmlPullParser xmlPullParser) {
        int bitCount;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "value");
        if (attributeValue == null || (bitCount = Integer.bitCount(Integer.parseInt(attributeValue, 16))) == 0) {
            return -1;
        }
        return bitCount;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser r4) {
        /*
            r0 = 0
            java.lang.String r1 = "value"
            java.lang.String r4 = r4.getAttributeValue(r0, r1)
            r0 = -1
            if (r4 != 0) goto L_0x000c
            return r0
        L_0x000c:
            java.lang.String r4 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r4)
            r4.hashCode()
            int r1 = r4.hashCode()
            r2 = 2
            r3 = 1
            switch(r1) {
                case 1596796: goto L_0x004a;
                case 2937391: goto L_0x003f;
                case 3094034: goto L_0x0034;
                case 3094035: goto L_0x0029;
                case 3133436: goto L_0x001e;
                default: goto L_0x001c;
            }
        L_0x001c:
            r4 = r0
            goto L_0x0054
        L_0x001e:
            java.lang.String r1 = "fa01"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0027
            goto L_0x001c
        L_0x0027:
            r4 = 4
            goto L_0x0054
        L_0x0029:
            java.lang.String r1 = "f801"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0032
            goto L_0x001c
        L_0x0032:
            r4 = 3
            goto L_0x0054
        L_0x0034:
            java.lang.String r1 = "f800"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x003d
            goto L_0x001c
        L_0x003d:
            r4 = r2
            goto L_0x0054
        L_0x003f:
            java.lang.String r1 = "a000"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0048
            goto L_0x001c
        L_0x0048:
            r4 = r3
            goto L_0x0054
        L_0x004a:
            java.lang.String r1 = "4000"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x0053
            goto L_0x001c
        L_0x0053:
            r4 = 0
        L_0x0054:
            switch(r4) {
                case 0: goto L_0x0060;
                case 1: goto L_0x005f;
                case 2: goto L_0x005d;
                case 3: goto L_0x005b;
                case 4: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            return r0
        L_0x0058:
            r4 = 8
            return r4
        L_0x005b:
            r4 = 6
            return r4
        L_0x005d:
            r4 = 5
            return r4
        L_0x005f:
            return r2
        L_0x0060:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.dash.manifest.DashManifestParser.parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser):int");
    }

    protected static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if (Ascii.equalsIgnoreCase("http://dashif.org/guidelines/last-segment-number", descriptor.schemeIdUri)) {
                return Long.parseLong(descriptor.value);
            }
        }
        return -1;
    }

    private boolean isDvbProfileDeclared(String[] strArr) {
        for (String startsWith : strArr) {
            if (startsWith.startsWith("urn:dvb:dash:profile:dvb-dash:")) {
                return true;
            }
        }
        return false;
    }

    protected static final class RepresentationInfo {
        public final ImmutableList<BaseUrl> baseUrls;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final List<Descriptor> essentialProperties;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;
        public final List<Descriptor> supplementalProperties;

        public RepresentationInfo(Format format2, List<BaseUrl> list, SegmentBase segmentBase2, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2, List<Descriptor> list2, List<Descriptor> list3, long j) {
            this.format = format2;
            this.baseUrls = ImmutableList.copyOf(list);
            this.segmentBase = segmentBase2;
            this.drmSchemeType = str;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.essentialProperties = list2;
            this.supplementalProperties = list3;
            this.revisionId = j;
        }
    }
}
