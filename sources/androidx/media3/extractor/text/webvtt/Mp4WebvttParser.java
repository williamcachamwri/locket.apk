package androidx.media3.extractor.text.webvtt;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Collections;

public final class Mp4WebvttParser implements SubtitleParser {
    private static final int BOX_HEADER_SIZE = 8;
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    private static final int TYPE_payl = 1885436268;
    private static final int TYPE_sttg = 1937011815;
    private static final int TYPE_vttc = 1987343459;
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    public int getCueReplacementBehavior() {
        return 2;
    }

    public void parse(byte[] bArr, int i, int i2, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        this.parsableByteArray.reset(bArr, i2 + i);
        this.parsableByteArray.setPosition(i);
        ArrayList arrayList = new ArrayList();
        while (this.parsableByteArray.bytesLeft() > 0) {
            Assertions.checkArgument(this.parsableByteArray.bytesLeft() >= 8, "Incomplete Mp4Webvtt Top Level box header found.");
            int readInt = this.parsableByteArray.readInt();
            if (this.parsableByteArray.readInt() == TYPE_vttc) {
                arrayList.add(parseVttCueBox(this.parsableByteArray, readInt - 8));
            } else {
                this.parsableByteArray.skipBytes(readInt - 8);
            }
        }
        consumer.accept(new CuesWithTiming(arrayList, C.TIME_UNSET, C.TIME_UNSET));
    }

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray2, int i) {
        CharSequence charSequence = null;
        Cue.Builder builder = null;
        while (i > 0) {
            Assertions.checkArgument(i >= 8, "Incomplete vtt cue box header found.");
            int readInt = parsableByteArray2.readInt();
            int readInt2 = parsableByteArray2.readInt();
            int i2 = readInt - 8;
            String fromUtf8Bytes = Util.fromUtf8Bytes(parsableByteArray2.getData(), parsableByteArray2.getPosition(), i2);
            parsableByteArray2.skipBytes(i2);
            i = (i - 8) - i2;
            if (readInt2 == TYPE_sttg) {
                builder = WebvttCueParser.parseCueSettingsList(fromUtf8Bytes);
            } else if (readInt2 == TYPE_payl) {
                charSequence = WebvttCueParser.parseCueText((String) null, fromUtf8Bytes.trim(), Collections.emptyList());
            }
        }
        if (charSequence == null) {
            charSequence = "";
        }
        if (builder != null) {
            return builder.setText(charSequence).build();
        }
        return WebvttCueParser.newCueForText(charSequence);
    }
}
