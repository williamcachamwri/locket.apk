package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/SaveFormat;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "PNG", "JPG", "BASE64", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SaveFormat.kt */
public enum SaveFormat {
    PNG("png"),
    JPG("jpg"),
    BASE64(Constants.BASE64);
    
    public static final Companion Companion = null;
    private final String value;

    public static EnumEntries<SaveFormat> getEntries() {
        return $ENTRIES;
    }

    private SaveFormat(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        SaveFormat[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/SaveFormat$Companion;", "", "()V", "getFormat", "Lcom/jimmydaddy/imagemarker/base/SaveFormat;", "format", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SaveFormat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
            if (r2.equals("png") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
            if (r2.equals("jpg") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003d, code lost:
            if (r2.equals("PNG") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
            if (r2.equals("JPG") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
            if (r2.equals(com.jimmydaddy.imagemarker.base.Constants.BASE64) == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
            return com.jimmydaddy.imagemarker.base.SaveFormat.PNG;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            return com.jimmydaddy.imagemarker.base.SaveFormat.JPG;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            return com.jimmydaddy.imagemarker.base.SaveFormat.BASE64;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
            if (r2.equals("BASE64") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
            if (r2.equals("jpeg") == false) goto L_0x005b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
            if (r2.equals("JPEG") != false) goto L_0x004c;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.jimmydaddy.imagemarker.base.SaveFormat getFormat(java.lang.String r2) {
            /*
                r1 = this;
                if (r2 == 0) goto L_0x005b
                int r0 = r2.hashCode()
                switch(r0) {
                    case -1396204209: goto L_0x004f;
                    case 73665: goto L_0x0043;
                    case 79369: goto L_0x0037;
                    case 105441: goto L_0x002e;
                    case 111145: goto L_0x0025;
                    case 2283624: goto L_0x001c;
                    case 3268712: goto L_0x0013;
                    case 1952093519: goto L_0x000a;
                    default: goto L_0x0009;
                }
            L_0x0009:
                goto L_0x005b
            L_0x000a:
                java.lang.String r0 = "BASE64"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x0058
                goto L_0x005b
            L_0x0013:
                java.lang.String r0 = "jpeg"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x004c
                goto L_0x005b
            L_0x001c:
                java.lang.String r0 = "JPEG"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x005b
                goto L_0x004c
            L_0x0025:
                java.lang.String r0 = "png"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x0040
                goto L_0x005b
            L_0x002e:
                java.lang.String r0 = "jpg"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x004c
                goto L_0x005b
            L_0x0037:
                java.lang.String r0 = "PNG"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x0040
                goto L_0x005b
            L_0x0040:
                com.jimmydaddy.imagemarker.base.SaveFormat r2 = com.jimmydaddy.imagemarker.base.SaveFormat.PNG
                goto L_0x005d
            L_0x0043:
                java.lang.String r0 = "JPG"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x004c
                goto L_0x005b
            L_0x004c:
                com.jimmydaddy.imagemarker.base.SaveFormat r2 = com.jimmydaddy.imagemarker.base.SaveFormat.JPG
                goto L_0x005d
            L_0x004f:
                java.lang.String r0 = "base64"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L_0x0058
                goto L_0x005b
            L_0x0058:
                com.jimmydaddy.imagemarker.base.SaveFormat r2 = com.jimmydaddy.imagemarker.base.SaveFormat.BASE64
                goto L_0x005d
            L_0x005b:
                com.jimmydaddy.imagemarker.base.SaveFormat r2 = com.jimmydaddy.imagemarker.base.SaveFormat.JPG
            L_0x005d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.SaveFormat.Companion.getFormat(java.lang.String):com.jimmydaddy.imagemarker.base.SaveFormat");
        }
    }
}
