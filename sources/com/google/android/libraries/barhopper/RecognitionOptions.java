package com.google.android.libraries.barhopper;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class RecognitionOptions {
    public static final int AZTEC = 4096;
    public static final int CODABAR = 8;
    public static final int CODE_128 = 1;
    public static final int CODE_39 = 2;
    public static final int CODE_93 = 4;
    public static final int DATA_MATRIX = 16;
    public static final int EAN_13 = 32;
    public static final int EAN_8 = 64;
    public static final int ITF = 128;
    public static final int LOCAL_BLOCK = 1;
    public static final int NIBLACK_W16_K00 = 32;
    public static final int NIBLACK_W32_K00 = 16;
    public static final int NIBLACK_W64_K02 = 8;
    public static final int NIBLACK_W75_K02 = 4;
    public static final int ONED = 2;
    public static final int PDF417 = 2048;
    public static final int QR_CODE = 256;
    public static final int TEZ_CODE = 32768;
    public static final int UNRECOGNIZED = 0;
    public static final int UPC_A = 512;
    public static final int UPC_E = 1024;
    private int barcodeFormats = 0;
    private int binarizerOptions = 3;
    private boolean enableQrAlignmentGrid = false;
    private boolean enableUseKeypointAsFinderPattern = false;
    private OnedRecognitionOptions onedRecognitionOptions = new OnedRecognitionOptions();
    private boolean outputUnrecognizedBarcodes = false;
    private boolean useQrMobilenetV3 = false;

    public int getBarcodeFormats() {
        return this.barcodeFormats;
    }

    public int getBinarizerOptions() {
        return this.binarizerOptions;
    }

    public boolean getEnableQrAlignmentGrid() {
        return this.enableQrAlignmentGrid;
    }

    public boolean getEnableUseKeypointAsFinderPattern() {
        return this.enableUseKeypointAsFinderPattern;
    }

    public OnedRecognitionOptions getOnedRecognitionOptions() {
        return this.onedRecognitionOptions;
    }

    public boolean getOutputUnrecognizedBarcodes() {
        return this.outputUnrecognizedBarcodes;
    }

    public boolean getUseQrMobilenetV3() {
        return this.useQrMobilenetV3;
    }

    public void setBarcodeFormats(int i) {
        this.barcodeFormats = i;
    }

    public void setBinarizerOptions(int i) {
        this.binarizerOptions = i;
    }

    public void setEnableQrAlignmentGrid(boolean z) {
        this.enableQrAlignmentGrid = z;
    }

    public void setEnableUseKeypointAsFinderPattern(boolean z) {
        this.enableUseKeypointAsFinderPattern = z;
    }

    public void setOnedRecognitionOptions(OnedRecognitionOptions onedRecognitionOptions2) {
        this.onedRecognitionOptions = onedRecognitionOptions2;
    }

    public void setOutputUnrecognizedBarcodes(boolean z) {
        this.outputUnrecognizedBarcodes = z;
    }

    public void setUseQrMobilenetV3(boolean z) {
        this.useQrMobilenetV3 = z;
    }
}
