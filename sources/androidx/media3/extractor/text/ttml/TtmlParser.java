package androidx.media3.extractor.text.ttml;

import android.text.Layout;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlParser implements SubtitleParser {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    public static final int CUE_REPLACEMENT_BEHAVIOR = 1;
    private static final int DEFAULT_CELL_ROWS = 15;
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)% ([-+]?\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)px ([-+]?\\d+\\.?\\d*?)px$");
    static final Pattern SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    private static final String TAG = "TtmlParser";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    public int getCueReplacementBehavior() {
        return 1;
    }

    public TtmlParser() {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        LegacySubtitleUtil.toCuesWithTiming(parseToLegacySubtitle(bArr, i, i2), outputOptions, consumer);
    }

    public Subtitle parseToLegacySubtitle(byte[] bArr, int i, int i2) {
        char c;
        FrameAndTickRate frameAndTickRate;
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            hashMap2.put("", new TtmlRegion(""));
            TtsExtent ttsExtent = null;
            newPullParser.setInput(new ByteArrayInputStream(bArr, i, i2), (String) null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate2 = DEFAULT_FRAME_AND_TICK_RATE;
            TtmlSubtitle ttmlSubtitle = null;
            int i3 = 0;
            int i4 = 15;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i3 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRate2 = parseFrameAndTickRates(newPullParser);
                            c = 15;
                            i4 = parseCellRows(newPullParser, 15);
                            ttsExtent = parseTtsExtent(newPullParser);
                        } else {
                            c = 15;
                        }
                        TtsExtent ttsExtent2 = ttsExtent;
                        FrameAndTickRate frameAndTickRate3 = frameAndTickRate2;
                        int i5 = i4;
                        if (!isSupportedTag(name)) {
                            Log.i(TAG, "Ignoring unsupported tag: " + newPullParser.getName());
                            i3++;
                            frameAndTickRate2 = frameAndTickRate3;
                            char c2 = c;
                            ttsExtent = ttsExtent2;
                            i4 = i5;
                        } else {
                            if (TtmlNode.TAG_HEAD.equals(name)) {
                                frameAndTickRate = frameAndTickRate3;
                                parseHeader(newPullParser, hashMap, i5, ttsExtent2, hashMap2, hashMap3);
                            } else {
                                frameAndTickRate = frameAndTickRate3;
                                try {
                                    TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                    arrayDeque.push(parseNode);
                                    if (ttmlNode != null) {
                                        ttmlNode.addChild(parseNode);
                                    }
                                } catch (SubtitleDecoderException e) {
                                    Log.w(TAG, "Suppressing parser error", e);
                                    i3++;
                                }
                            }
                            frameAndTickRate2 = frameAndTickRate;
                            ttsExtent = ttsExtent2;
                            i4 = i5;
                        }
                    } else if (eventType == 4) {
                        ((TtmlNode) Assertions.checkNotNull(ttmlNode)).addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                    } else if (eventType == 3) {
                        if (newPullParser.getName().equals(TtmlNode.TAG_TT)) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull((TtmlNode) arrayDeque.peek()), hashMap, hashMap2, hashMap3);
                        }
                        arrayDeque.pop();
                    }
                } else if (eventType == 2) {
                    i3++;
                } else if (eventType == 3) {
                    i3--;
                }
                newPullParser.next();
            }
            return (Subtitle) Assertions.checkNotNull(ttmlSubtitle);
        } catch (XmlPullParserException e2) {
            throw new IllegalStateException("Unable to decode source", e2);
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        }
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) {
        float f;
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, " ");
            Assertions.checkArgument(split.length == 2, "frameRateMultiplier doesn't have 2 parts");
            f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
        } else {
            f = 1.0f;
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f, i, i2);
    }

    private static int parseCellRows(XmlPullParser xmlPullParser, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return i;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return i;
        }
        boolean z = true;
        try {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)));
            int parseInt2 = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)));
            if (parseInt == 0 || parseInt2 == 0) {
                z = false;
            }
            Assertions.checkArgument(z, "Invalid cell resolution " + parseInt + " " + parseInt2);
            return parseInt2;
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return i;
        }
    }

    private static TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring non-pixel tts extent: " + attributeValue);
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2))));
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed tts extent: " + attributeValue);
            return null;
        }
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, int i, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                String id = parseStyleAttributes.getId();
                if (id != null) {
                    map.put(id, parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, i, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private static void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_METADATA));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x019f, code lost:
        if (r0.equals("tb") == false) goto L_0x017e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x016f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TtmlRegion parseRegionAttributes(org.xmlpull.v1.XmlPullParser r17, int r18, androidx.media3.extractor.text.ttml.TtmlParser.TtsExtent r19) {
        /*
            r0 = r17
            r1 = r19
            java.lang.String r2 = "id"
            java.lang.String r4 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r2)
            r2 = 0
            if (r4 != 0) goto L_0x000e
            return r2
        L_0x000e:
            java.lang.String r3 = "origin"
            java.lang.String r3 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r3)
            java.lang.String r5 = "TtmlParser"
            if (r3 == 0) goto L_0x0207
            java.util.regex.Pattern r6 = PERCENTAGE_COORDINATES
            java.util.regex.Matcher r7 = r6.matcher(r3)
            java.util.regex.Pattern r8 = PIXEL_COORDINATES
            java.util.regex.Matcher r9 = r8.matcher(r3)
            boolean r10 = r7.matches()
            java.lang.String r11 = "Ignoring region with malformed origin: "
            java.lang.String r12 = "Ignoring region with missing tts:extent: "
            r13 = 1120403456(0x42c80000, float:100.0)
            r14 = 2
            r15 = 1
            if (r10 == 0) goto L_0x0067
            java.lang.String r9 = r7.group(r15)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x0056 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0056 }
            float r9 = r9 / r13
            java.lang.String r7 = r7.group(r14)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)     // Catch:{ NumberFormatException -> 0x0056 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x0056 }
            float r7 = java.lang.Float.parseFloat(r7)     // Catch:{ NumberFormatException -> 0x0056 }
            float r7 = r7 / r13
            r16 = r9
            r9 = r7
            r7 = r16
            goto L_0x00a6
        L_0x0056:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x0067:
            boolean r7 = r9.matches()
            if (r7 == 0) goto L_0x01f4
            if (r1 != 0) goto L_0x0080
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x0080:
            java.lang.String r7 = r9.group(r15)     // Catch:{ NumberFormatException -> 0x01e3 }
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)     // Catch:{ NumberFormatException -> 0x01e3 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x01e3 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x01e3 }
            java.lang.String r9 = r9.group(r14)     // Catch:{ NumberFormatException -> 0x01e3 }
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)     // Catch:{ NumberFormatException -> 0x01e3 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ NumberFormatException -> 0x01e3 }
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x01e3 }
            float r7 = (float) r7     // Catch:{ NumberFormatException -> 0x01e3 }
            int r10 = r1.width     // Catch:{ NumberFormatException -> 0x01e3 }
            float r10 = (float) r10     // Catch:{ NumberFormatException -> 0x01e3 }
            float r7 = r7 / r10
            float r9 = (float) r9     // Catch:{ NumberFormatException -> 0x01e3 }
            int r10 = r1.height     // Catch:{ NumberFormatException -> 0x01e3 }
            float r10 = (float) r10
            float r9 = r9 / r10
        L_0x00a6:
            java.lang.String r10 = "extent"
            java.lang.String r10 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r10)
            if (r10 == 0) goto L_0x01dd
            java.util.regex.Matcher r6 = r6.matcher(r10)
            java.util.regex.Matcher r8 = r8.matcher(r10)
            boolean r10 = r6.matches()
            java.lang.String r11 = "Ignoring region with malformed extent: "
            if (r10 == 0) goto L_0x00ef
            java.lang.String r1 = r6.group(r15)     // Catch:{ NumberFormatException -> 0x00de }
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)     // Catch:{ NumberFormatException -> 0x00de }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x00de }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x00de }
            float r1 = r1 / r13
            java.lang.String r6 = r6.group(r14)     // Catch:{ NumberFormatException -> 0x00de }
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x00de }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x00de }
            float r2 = java.lang.Float.parseFloat(r6)     // Catch:{ NumberFormatException -> 0x00de }
            float r2 = r2 / r13
            r10 = r2
            goto L_0x0130
        L_0x00de:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x00ef:
            boolean r6 = r8.matches()
            if (r6 == 0) goto L_0x01ca
            if (r1 != 0) goto L_0x0108
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x0108:
            java.lang.String r6 = r8.group(r15)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x01b9 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r8 = r8.group(r14)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.Object r8 = androidx.media3.common.util.Assertions.checkNotNull(r8)     // Catch:{ NumberFormatException -> 0x01b9 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ NumberFormatException -> 0x01b9 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ NumberFormatException -> 0x01b9 }
            float r6 = (float) r6     // Catch:{ NumberFormatException -> 0x01b9 }
            int r10 = r1.width     // Catch:{ NumberFormatException -> 0x01b9 }
            float r10 = (float) r10     // Catch:{ NumberFormatException -> 0x01b9 }
            float r6 = r6 / r10
            float r8 = (float) r8     // Catch:{ NumberFormatException -> 0x01b9 }
            int r1 = r1.height     // Catch:{ NumberFormatException -> 0x01b9 }
            float r1 = (float) r1
            float r8 = r8 / r1
            r1 = r6
            r10 = r8
        L_0x0130:
            java.lang.String r2 = "displayAlign"
            java.lang.String r2 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r2)
            r3 = 0
            if (r2 == 0) goto L_0x015d
            java.lang.String r2 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r2)
            r2.hashCode()
            java.lang.String r5 = "center"
            boolean r5 = r2.equals(r5)
            if (r5 != 0) goto L_0x0155
            java.lang.String r5 = "after"
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L_0x0151
            goto L_0x015d
        L_0x0151:
            float r9 = r9 + r10
            r6 = r9
            r8 = r14
            goto L_0x015f
        L_0x0155:
            r2 = 1073741824(0x40000000, float:2.0)
            float r2 = r10 / r2
            float r9 = r9 + r2
            r6 = r9
            r8 = r15
            goto L_0x015f
        L_0x015d:
            r8 = r3
            r6 = r9
        L_0x015f:
            r2 = 1065353216(0x3f800000, float:1.0)
            r5 = r18
            float r5 = (float) r5
            float r12 = r2 / r5
            java.lang.String r2 = "writingMode"
            java.lang.String r0 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r2)
            if (r0 == 0) goto L_0x01aa
            java.lang.String r0 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r0)
            r0.hashCode()
            int r2 = r0.hashCode()
            r5 = -1
            switch(r2) {
                case 3694: goto L_0x0198;
                case 3553396: goto L_0x018c;
                case 3553576: goto L_0x0180;
                default: goto L_0x017e;
            }
        L_0x017e:
            r3 = r5
            goto L_0x01a2
        L_0x0180:
            java.lang.String r2 = "tbrl"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x018a
            goto L_0x017e
        L_0x018a:
            r3 = r14
            goto L_0x01a2
        L_0x018c:
            java.lang.String r2 = "tblr"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0196
            goto L_0x017e
        L_0x0196:
            r3 = r15
            goto L_0x01a2
        L_0x0198:
            java.lang.String r2 = "tb"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01a2
            goto L_0x017e
        L_0x01a2:
            switch(r3) {
                case 0: goto L_0x01a8;
                case 1: goto L_0x01a8;
                case 2: goto L_0x01a6;
                default: goto L_0x01a5;
            }
        L_0x01a5:
            goto L_0x01aa
        L_0x01a6:
            r13 = r15
            goto L_0x01ad
        L_0x01a8:
            r13 = r14
            goto L_0x01ad
        L_0x01aa:
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r13 = r0
        L_0x01ad:
            androidx.media3.extractor.text.ttml.TtmlRegion r0 = new androidx.media3.extractor.text.ttml.TtmlRegion
            r2 = 0
            r11 = 1
            r3 = r0
            r5 = r7
            r7 = r2
            r9 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r0
        L_0x01b9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x01ca:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Ignoring region with unsupported extent: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x01dd:
            java.lang.String r0 = "Ignoring region without an extent"
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x01e3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x01f4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Ignoring region with unsupported origin: "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        L_0x0207:
            java.lang.String r0 = "Ignoring region without an origin"
            androidx.media3.common.util.Log.w(r5, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseRegionAttributes(org.xmlpull.v1.XmlPullParser, int, androidx.media3.extractor.text.ttml.TtmlParser$TtsExtent):androidx.media3.extractor.text.ttml.TtmlRegion");
    }

    private static String[] parseStyleIds(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.split(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01e0, code lost:
        if (r3.equals("text") == false) goto L_0x01d7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, androidx.media3.extractor.text.ttml.TtmlStyle r13) {
        /*
            int r0 = r12.getAttributeCount()
            r1 = 0
            r2 = r1
        L_0x0006:
            if (r2 >= r0) goto L_0x02f9
            java.lang.String r3 = r12.getAttributeValue(r2)
            java.lang.String r4 = r12.getAttributeName(r2)
            r4.hashCode()
            int r5 = r4.hashCode()
            r6 = 5
            r7 = 4
            r8 = -1
            r9 = 3
            r10 = 2
            r11 = 1
            switch(r5) {
                case -1550943582: goto L_0x00d4;
                case -1224696685: goto L_0x00c8;
                case -1065511464: goto L_0x00bb;
                case -879295043: goto L_0x00ae;
                case -734428249: goto L_0x00a2;
                case 3355: goto L_0x0097;
                case 3511770: goto L_0x008c;
                case 94842723: goto L_0x0081;
                case 109403361: goto L_0x0073;
                case 110138194: goto L_0x0065;
                case 365601008: goto L_0x0058;
                case 921125321: goto L_0x004a;
                case 1115953443: goto L_0x003d;
                case 1287124693: goto L_0x0030;
                case 1754920356: goto L_0x0023;
                default: goto L_0x0020;
            }
        L_0x0020:
            r4 = r8
            goto L_0x00df
        L_0x0023:
            java.lang.String r5 = "multiRowAlign"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x002c
            goto L_0x0020
        L_0x002c:
            r4 = 14
            goto L_0x00df
        L_0x0030:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0039
            goto L_0x0020
        L_0x0039:
            r4 = 13
            goto L_0x00df
        L_0x003d:
            java.lang.String r5 = "rubyPosition"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0046
            goto L_0x0020
        L_0x0046:
            r4 = 12
            goto L_0x00df
        L_0x004a:
            java.lang.String r5 = "textEmphasis"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0054
            goto L_0x0020
        L_0x0054:
            r4 = 11
            goto L_0x00df
        L_0x0058:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0061
            goto L_0x0020
        L_0x0061:
            r4 = 10
            goto L_0x00df
        L_0x0065:
            java.lang.String r5 = "textCombine"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x006f
            goto L_0x0020
        L_0x006f:
            r4 = 9
            goto L_0x00df
        L_0x0073:
            java.lang.String r5 = "shear"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x007d
            goto L_0x0020
        L_0x007d:
            r4 = 8
            goto L_0x00df
        L_0x0081:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x008a
            goto L_0x0020
        L_0x008a:
            r4 = 7
            goto L_0x00df
        L_0x008c:
            java.lang.String r5 = "ruby"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0095
            goto L_0x0020
        L_0x0095:
            r4 = 6
            goto L_0x00df
        L_0x0097:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00a0
            goto L_0x0020
        L_0x00a0:
            r4 = r6
            goto L_0x00df
        L_0x00a2:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00ac
            goto L_0x0020
        L_0x00ac:
            r4 = r7
            goto L_0x00df
        L_0x00ae:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00b9
            goto L_0x0020
        L_0x00b9:
            r4 = r9
            goto L_0x00df
        L_0x00bb:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00c6
            goto L_0x0020
        L_0x00c6:
            r4 = r10
            goto L_0x00df
        L_0x00c8:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00d2
            goto L_0x0020
        L_0x00d2:
            r4 = r11
            goto L_0x00df
        L_0x00d4:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00de
            goto L_0x0020
        L_0x00de:
            r4 = r1
        L_0x00df:
            java.lang.String r5 = "TtmlParser"
            switch(r4) {
                case 0: goto L_0x02e7;
                case 1: goto L_0x02de;
                case 2: goto L_0x02d1;
                case 3: goto L_0x026e;
                case 4: goto L_0x025e;
                case 5: goto L_0x0247;
                case 6: goto L_0x01c9;
                case 7: goto L_0x01a8;
                case 8: goto L_0x019a;
                case 9: goto L_0x016d;
                case 10: goto L_0x0150;
                case 11: goto L_0x0142;
                case 12: goto L_0x0115;
                case 13: goto L_0x00f4;
                case 14: goto L_0x00e6;
                default: goto L_0x00e4;
            }
        L_0x00e4:
            goto L_0x02f5
        L_0x00e6:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            android.text.Layout$Alignment r3 = parseAlignment(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setMultiRowAlign(r3)
            goto L_0x02f5
        L_0x00f4:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            int r4 = androidx.media3.common.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x0101 }
            r13.setBackgroundColor(r4)     // Catch:{ IllegalArgumentException -> 0x0101 }
            goto L_0x02f5
        L_0x0101:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed parsing background value: "
            r4.<init>(r6)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            androidx.media3.common.util.Log.w(r5, r3)
            goto L_0x02f5
        L_0x0115:
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r3)
            r3.hashCode()
            java.lang.String r4 = "before"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0138
            java.lang.String r4 = "after"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x012e
            goto L_0x02f5
        L_0x012e:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyPosition(r10)
            goto L_0x02f5
        L_0x0138:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyPosition(r11)
            goto L_0x02f5
        L_0x0142:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TextEmphasis r3 = androidx.media3.extractor.text.ttml.TextEmphasis.parse(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextEmphasis(r3)
            goto L_0x02f5
        L_0x0150:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)     // Catch:{ SubtitleDecoderException -> 0x0159 }
            parseFontSize(r3, r13)     // Catch:{ SubtitleDecoderException -> 0x0159 }
            goto L_0x02f5
        L_0x0159:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed parsing fontSize value: "
            r4.<init>(r6)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            androidx.media3.common.util.Log.w(r5, r3)
            goto L_0x02f5
        L_0x016d:
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r3)
            r3.hashCode()
            java.lang.String r4 = "all"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0190
            java.lang.String r4 = "none"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0186
            goto L_0x02f5
        L_0x0186:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextCombine(r1)
            goto L_0x02f5
        L_0x0190:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextCombine(r11)
            goto L_0x02f5
        L_0x019a:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            float r3 = parseShear(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setShearPercentage(r3)
            goto L_0x02f5
        L_0x01a8:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            int r4 = androidx.media3.common.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01b5 }
            r13.setFontColor(r4)     // Catch:{ IllegalArgumentException -> 0x01b5 }
            goto L_0x02f5
        L_0x01b5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Failed parsing color value: "
            r4.<init>(r6)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r3 = r3.toString()
            androidx.media3.common.util.Log.w(r5, r3)
            goto L_0x02f5
        L_0x01c9:
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r3)
            r3.hashCode()
            int r4 = r3.hashCode()
            switch(r4) {
                case -618561360: goto L_0x0210;
                case -410956671: goto L_0x0205;
                case -250518009: goto L_0x01fa;
                case -136074796: goto L_0x01ee;
                case 3016401: goto L_0x01e3;
                case 3556653: goto L_0x01d9;
                default: goto L_0x01d7;
            }
        L_0x01d7:
            r6 = r8
            goto L_0x021a
        L_0x01d9:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x021a
            goto L_0x01d7
        L_0x01e3:
            java.lang.String r4 = "base"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01ec
            goto L_0x01d7
        L_0x01ec:
            r6 = r7
            goto L_0x021a
        L_0x01ee:
            java.lang.String r4 = "textContainer"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01f8
            goto L_0x01d7
        L_0x01f8:
            r6 = r9
            goto L_0x021a
        L_0x01fa:
            java.lang.String r4 = "delimiter"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0203
            goto L_0x01d7
        L_0x0203:
            r6 = r10
            goto L_0x021a
        L_0x0205:
            java.lang.String r4 = "container"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x020e
            goto L_0x01d7
        L_0x020e:
            r6 = r11
            goto L_0x021a
        L_0x0210:
            java.lang.String r4 = "baseContainer"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0219
            goto L_0x01d7
        L_0x0219:
            r6 = r1
        L_0x021a:
            switch(r6) {
                case 0: goto L_0x023d;
                case 1: goto L_0x0233;
                case 2: goto L_0x0229;
                case 3: goto L_0x021f;
                case 4: goto L_0x023d;
                case 5: goto L_0x021f;
                default: goto L_0x021d;
            }
        L_0x021d:
            goto L_0x02f5
        L_0x021f:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r9)
            goto L_0x02f5
        L_0x0229:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r7)
            goto L_0x02f5
        L_0x0233:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r11)
            goto L_0x02f5
        L_0x023d:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r10)
            goto L_0x02f5
        L_0x0247:
            java.lang.String r4 = "style"
            java.lang.String r5 = r12.getName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x02f5
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setId(r3)
            goto L_0x02f5
        L_0x025e:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setBold(r3)
            goto L_0x02f5
        L_0x026e:
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r3)
            r3.hashCode()
            int r4 = r3.hashCode()
            switch(r4) {
                case -1461280213: goto L_0x029f;
                case -1026963764: goto L_0x0293;
                case 913457136: goto L_0x0288;
                case 1679736913: goto L_0x027d;
                default: goto L_0x027c;
            }
        L_0x027c:
            goto L_0x02a9
        L_0x027d:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0286
            goto L_0x02a9
        L_0x0286:
            r8 = r9
            goto L_0x02a9
        L_0x0288:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0291
            goto L_0x02a9
        L_0x0291:
            r8 = r10
            goto L_0x02a9
        L_0x0293:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x029d
            goto L_0x02a9
        L_0x029d:
            r8 = r11
            goto L_0x02a9
        L_0x029f:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x02a8
            goto L_0x02a9
        L_0x02a8:
            r8 = r1
        L_0x02a9:
            switch(r8) {
                case 0: goto L_0x02c8;
                case 1: goto L_0x02bf;
                case 2: goto L_0x02b6;
                case 3: goto L_0x02ad;
                default: goto L_0x02ac;
            }
        L_0x02ac:
            goto L_0x02f5
        L_0x02ad:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setLinethrough(r11)
            goto L_0x02f5
        L_0x02b6:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setLinethrough(r1)
            goto L_0x02f5
        L_0x02bf:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setUnderline(r11)
            goto L_0x02f5
        L_0x02c8:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setUnderline(r1)
            goto L_0x02f5
        L_0x02d1:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            android.text.Layout$Alignment r3 = parseAlignment(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x02f5
        L_0x02de:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setFontFamily(r3)
            goto L_0x02f5
        L_0x02e7:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setItalic(r3)
        L_0x02f5:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x02f9:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, androidx.media3.extractor.text.ttml.TtmlStyle):androidx.media3.extractor.text.ttml.TtmlStyle");
    }

    private static TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    private static Layout.Alignment parseAlignment(String str) {
        String lowerCase = Ascii.toLowerCase(str);
        lowerCase.hashCode();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -1364013995:
                if (lowerCase.equals(TtmlNode.CENTER)) {
                    c = 0;
                    break;
                }
                break;
            case 100571:
                if (lowerCase.equals("end")) {
                    c = 1;
                    break;
                }
                break;
            case 3317767:
                if (lowerCase.equals("left")) {
                    c = 2;
                    break;
                }
                break;
            case 108511772:
                if (lowerCase.equals("right")) {
                    c = 3;
                    break;
                }
                break;
            case 109757538:
                if (lowerCase.equals("start")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Layout.Alignment.ALIGN_CENTER;
            case 1:
            case 3:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 2:
            case 4:
                return Layout.Alignment.ALIGN_NORMAL;
            default:
                return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r20, androidx.media3.extractor.text.ttml.TtmlNode r21, java.util.Map<java.lang.String, androidx.media3.extractor.text.ttml.TtmlRegion> r22, androidx.media3.extractor.text.ttml.TtmlParser.FrameAndTickRate r23) throws androidx.media3.extractor.text.SubtitleDecoderException {
        /*
            r0 = r20
            r9 = r21
            r1 = r23
            int r2 = r20.getAttributeCount()
            r3 = 0
            androidx.media3.extractor.text.ttml.TtmlStyle r5 = parseStyleAttributes(r0, r3)
            java.lang.String r4 = ""
            r10 = r3
            r12 = r10
            r11 = r4
            r3 = 0
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0024:
            if (r3 >= r2) goto L_0x00b5
            java.lang.String r4 = r0.getAttributeName(r3)
            java.lang.String r8 = r0.getAttributeValue(r3)
            r4.hashCode()
            int r19 = r4.hashCode()
            r6 = 1
            switch(r19) {
                case -934795532: goto L_0x0073;
                case 99841: goto L_0x0068;
                case 100571: goto L_0x005d;
                case 93616297: goto L_0x0052;
                case 109780401: goto L_0x0046;
                case 1292595405: goto L_0x003b;
                default: goto L_0x0039;
            }
        L_0x0039:
            r7 = -1
            goto L_0x007d
        L_0x003b:
            java.lang.String r7 = "backgroundImage"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x0044
            goto L_0x0039
        L_0x0044:
            r7 = 5
            goto L_0x007d
        L_0x0046:
            java.lang.String r7 = "style"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x0050
            goto L_0x0039
        L_0x0050:
            r7 = 4
            goto L_0x007d
        L_0x0052:
            java.lang.String r7 = "begin"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x005b
            goto L_0x0039
        L_0x005b:
            r7 = 3
            goto L_0x007d
        L_0x005d:
            java.lang.String r7 = "end"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x0066
            goto L_0x0039
        L_0x0066:
            r7 = 2
            goto L_0x007d
        L_0x0068:
            java.lang.String r7 = "dur"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x0071
            goto L_0x0039
        L_0x0071:
            r7 = r6
            goto L_0x007d
        L_0x0073:
            java.lang.String r7 = "region"
            boolean r4 = r4.equals(r7)
            if (r4 != 0) goto L_0x007c
            goto L_0x0039
        L_0x007c:
            r7 = 0
        L_0x007d:
            switch(r7) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x00a3;
                case 2: goto L_0x009e;
                case 3: goto L_0x0097;
                case 4: goto L_0x008e;
                case 5: goto L_0x0081;
                default: goto L_0x0080;
            }
        L_0x0080:
            goto L_0x009b
        L_0x0081:
            java.lang.String r4 = "#"
            boolean r4 = r8.startsWith(r4)
            if (r4 == 0) goto L_0x009b
            java.lang.String r12 = r8.substring(r6)
            goto L_0x009b
        L_0x008e:
            java.lang.String[] r4 = parseStyleIds(r8)
            int r6 = r4.length
            if (r6 <= 0) goto L_0x009b
            r10 = r4
            goto L_0x009b
        L_0x0097:
            long r13 = parseTimeExpression(r8, r1)
        L_0x009b:
            r4 = r22
            goto L_0x00b1
        L_0x009e:
            long r15 = parseTimeExpression(r8, r1)
            goto L_0x009b
        L_0x00a3:
            long r17 = parseTimeExpression(r8, r1)
            goto L_0x009b
        L_0x00a8:
            r4 = r22
            boolean r6 = r4.containsKey(r8)
            if (r6 == 0) goto L_0x00b1
            r11 = r8
        L_0x00b1:
            int r3 = r3 + 1
            goto L_0x0024
        L_0x00b5:
            if (r9 == 0) goto L_0x00d1
            long r1 = r9.startTimeUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00d6
            int r1 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00c9
            long r1 = r9.startTimeUs
            long r13 = r13 + r1
        L_0x00c9:
            int r1 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x00d6
            long r1 = r9.startTimeUs
            long r15 = r15 + r1
            goto L_0x00d6
        L_0x00d1:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x00d6:
            r1 = r13
            int r6 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r6 != 0) goto L_0x00ef
            int r6 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1))
            if (r6 == 0) goto L_0x00e4
            long r17 = r1 + r17
            r3 = r17
            goto L_0x00f0
        L_0x00e4:
            if (r9 == 0) goto L_0x00ef
            long r6 = r9.endTimeUs
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00ef
            long r3 = r9.endTimeUs
            goto L_0x00f0
        L_0x00ef:
            r3 = r15
        L_0x00f0:
            java.lang.String r0 = r20.getName()
            r6 = r10
            r7 = r11
            r8 = r12
            r9 = r21
            androidx.media3.extractor.text.ttml.TtmlNode r0 = androidx.media3.extractor.text.ttml.TtmlNode.buildNode(r0, r1, r3, r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseNode(org.xmlpull.v1.XmlPullParser, androidx.media3.extractor.text.ttml.TtmlNode, java.util.Map, androidx.media3.extractor.text.ttml.TtmlParser$FrameAndTickRate):androidx.media3.extractor.text.ttml.TtmlNode");
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals(TtmlNode.TAG_BODY) || str.equals(TtmlNode.TAG_DIV) || str.equals("p") || str.equals(TtmlNode.TAG_SPAN) || str.equals("br") || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals("image") || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + split.length + ".");
        }
        if (matcher.matches()) {
            String str2 = (String) Assertions.checkNotNull(matcher.group(3));
            str2.hashCode();
            char c = 65535;
            switch (str2.hashCode()) {
                case 37:
                    if (str2.equals("%")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3240:
                    if (str2.equals("em")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3592:
                    if (str2.equals("px")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                default:
                    throw new SubtitleDecoderException("Invalid unit for fontSize: '" + str2 + "'.");
            }
            ttmlStyle.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    private static float parseShear(String str) {
        Matcher matcher = SIGNED_PERCENTAGE.matcher(str);
        if (!matcher.matches()) {
            Log.w(TAG, "Invalid value for shear: " + str);
            return Float.MAX_VALUE;
        }
        try {
            return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1)))));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse shear: " + str, e);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bc, code lost:
        if (r13.equals("ms") == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f4, code lost:
        r8 = r8 / r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0102, code lost:
        r8 = r8 * r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r13, androidx.media3.extractor.text.ttml.TtmlParser.FrameAndTickRate r14) throws androidx.media3.extractor.text.SubtitleDecoderException {
        /*
            java.util.regex.Pattern r0 = CLOCK_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L_0x0085
            java.lang.String r13 = r0.group(r7)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r7 = java.lang.Long.parseLong(r13)
            r9 = 3600(0xe10, double:1.7786E-320)
            long r7 = r7 * r9
            double r7 = (double) r7
            java.lang.String r13 = r0.group(r6)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r9 = java.lang.Long.parseLong(r13)
            r11 = 60
            long r9 = r9 * r11
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r0.group(r5)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r5 = java.lang.Long.parseLong(r13)
            double r5 = (double) r5
            double r7 = r7 + r5
            java.lang.String r13 = r0.group(r4)
            r4 = 0
            if (r13 == 0) goto L_0x0057
            double r9 = java.lang.Double.parseDouble(r13)
            goto L_0x0058
        L_0x0057:
            r9 = r4
        L_0x0058:
            double r7 = r7 + r9
            r13 = 5
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x006a
            long r9 = java.lang.Long.parseLong(r13)
            float r13 = (float) r9
            float r1 = r14.effectiveFrameRate
            float r13 = r13 / r1
            double r9 = (double) r13
            goto L_0x006b
        L_0x006a:
            r9 = r4
        L_0x006b:
            double r7 = r7 + r9
            r13 = 6
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x0081
            long r0 = java.lang.Long.parseLong(r13)
            double r0 = (double) r0
            int r13 = r14.subFrameRate
            double r4 = (double) r13
            double r0 = r0 / r4
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            double r4 = r0 / r13
        L_0x0081:
            double r7 = r7 + r4
            double r7 = r7 * r2
            long r13 = (long) r7
            return r13
        L_0x0085:
            java.util.regex.Pattern r0 = OFFSET_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x010b
            java.lang.String r13 = r0.group(r7)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            double r8 = java.lang.Double.parseDouble(r13)
            java.lang.String r13 = r0.group(r6)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            r13.hashCode()
            int r0 = r13.hashCode()
            r1 = -1
            switch(r0) {
                case 102: goto L_0x00e1;
                case 104: goto L_0x00d6;
                case 109: goto L_0x00cb;
                case 116: goto L_0x00bf;
                case 3494: goto L_0x00b6;
                default: goto L_0x00b4;
            }
        L_0x00b4:
            r4 = r1
            goto L_0x00eb
        L_0x00b6:
            java.lang.String r0 = "ms"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00eb
            goto L_0x00b4
        L_0x00bf:
            java.lang.String r0 = "t"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00c9
            goto L_0x00b4
        L_0x00c9:
            r4 = r5
            goto L_0x00eb
        L_0x00cb:
            java.lang.String r0 = "m"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00d4
            goto L_0x00b4
        L_0x00d4:
            r4 = r6
            goto L_0x00eb
        L_0x00d6:
            java.lang.String r0 = "h"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00df
            goto L_0x00b4
        L_0x00df:
            r4 = r7
            goto L_0x00eb
        L_0x00e1:
            java.lang.String r0 = "f"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00ea
            goto L_0x00b4
        L_0x00ea:
            r4 = 0
        L_0x00eb:
            switch(r4) {
                case 0: goto L_0x0104;
                case 1: goto L_0x00fd;
                case 2: goto L_0x00fa;
                case 3: goto L_0x00f6;
                case 4: goto L_0x00ef;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            goto L_0x0108
        L_0x00ef:
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x00f4:
            double r8 = r8 / r13
            goto L_0x0108
        L_0x00f6:
            int r13 = r14.tickRate
            double r13 = (double) r13
            goto L_0x00f4
        L_0x00fa:
            r13 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0102
        L_0x00fd:
            r13 = 4660134898793709568(0x40ac200000000000, double:3600.0)
        L_0x0102:
            double r8 = r8 * r13
            goto L_0x0108
        L_0x0104:
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            goto L_0x00f4
        L_0x0108:
            double r8 = r8 * r2
            long r13 = (long) r8
            return r13
        L_0x010b:
            androidx.media3.extractor.text.SubtitleDecoderException r14 = new androidx.media3.extractor.text.SubtitleDecoderException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Malformed time expression: "
            r0.<init>(r1)
            java.lang.StringBuilder r13 = r0.append(r13)
            java.lang.String r13 = r13.toString()
            r14.<init>((java.lang.String) r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseTimeExpression(java.lang.String, androidx.media3.extractor.text.ttml.TtmlParser$FrameAndTickRate):long");
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }
}
