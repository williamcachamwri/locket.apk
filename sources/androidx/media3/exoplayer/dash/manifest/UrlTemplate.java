package androidx.media3.exoplayer.dash.manifest;

import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class UrlTemplate {
    private static final String BANDWIDTH = "Bandwidth";
    private static final int BANDWIDTH_ID = 3;
    private static final String DEFAULT_FORMAT_TAG = "%01d";
    private static final String ESCAPED_DOLLAR = "$$";
    private static final String NUMBER = "Number";
    private static final int NUMBER_ID = 2;
    private static final String REPRESENTATION = "RepresentationID";
    private static final int REPRESENTATION_ID = 1;
    private static final String TIME = "Time";
    private static final int TIME_ID = 4;
    private final List<String> identifierFormatTags;
    private final List<Integer> identifiers;
    private final List<String> urlPieces;

    public static UrlTemplate compile(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        parseTemplate(str, arrayList, arrayList2, arrayList3);
        return new UrlTemplate(arrayList, arrayList2, arrayList3);
    }

    private UrlTemplate(List<String> list, List<Integer> list2, List<String> list3) {
        this.urlPieces = list;
        this.identifiers = list2;
        this.identifierFormatTags = list3;
    }

    public String buildUri(String str, long j, int i, long j2) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.identifiers.size(); i2++) {
            sb.append(this.urlPieces.get(i2));
            if (this.identifiers.get(i2).intValue() == 1) {
                sb.append(str);
            } else if (this.identifiers.get(i2).intValue() == 2) {
                sb.append(String.format(Locale.US, this.identifierFormatTags.get(i2), new Object[]{Long.valueOf(j)}));
            } else if (this.identifiers.get(i2).intValue() == 3) {
                sb.append(String.format(Locale.US, this.identifierFormatTags.get(i2), new Object[]{Integer.valueOf(i)}));
            } else if (this.identifiers.get(i2).intValue() == 4) {
                sb.append(String.format(Locale.US, this.identifierFormatTags.get(i2), new Object[]{Long.valueOf(j2)}));
            }
        }
        sb.append(this.urlPieces.get(this.identifiers.size()));
        return sb.toString();
    }

    private static void parseTemplate(String str, List<String> list, List<Integer> list2, List<String> list3) {
        String str2;
        list.add("");
        int i = 0;
        while (i < str.length()) {
            int indexOf = str.indexOf("$", i);
            char c = 65535;
            if (indexOf == -1) {
                list.set(list2.size(), list.get(list2.size()) + str.substring(i));
                i = str.length();
            } else if (indexOf != i) {
                list.set(list2.size(), list.get(list2.size()) + str.substring(i, indexOf));
                i = indexOf;
            } else if (str.startsWith(ESCAPED_DOLLAR, i)) {
                list.set(list2.size(), list.get(list2.size()) + "$");
                i += 2;
            } else {
                list3.add("");
                int i2 = i + 1;
                int indexOf2 = str.indexOf("$", i2);
                String substring = str.substring(i2, indexOf2);
                if (substring.equals(REPRESENTATION)) {
                    list2.add(1);
                } else {
                    int indexOf3 = substring.indexOf("%0");
                    if (indexOf3 != -1) {
                        str2 = substring.substring(indexOf3);
                        if (!str2.endsWith("d") && !str2.endsWith(ViewHierarchyNode.JsonKeys.X) && !str2.endsWith("X")) {
                            str2 = str2 + "d";
                        }
                        substring = substring.substring(0, indexOf3);
                    } else {
                        str2 = DEFAULT_FORMAT_TAG;
                    }
                    substring.hashCode();
                    switch (substring.hashCode()) {
                        case -1950496919:
                            if (substring.equals(NUMBER)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 2606829:
                            if (substring.equals(TIME)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 38199441:
                            if (substring.equals(BANDWIDTH)) {
                                c = 2;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            list2.add(2);
                            break;
                        case 1:
                            list2.add(4);
                            break;
                        case 2:
                            list2.add(3);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid template: " + str);
                    }
                    list3.set(list2.size() - 1, str2);
                }
                list.add("");
                i = indexOf2 + 1;
            }
        }
    }
}
