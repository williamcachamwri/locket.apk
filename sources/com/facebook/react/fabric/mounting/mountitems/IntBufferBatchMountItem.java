package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;

final class IntBufferBatchMountItem implements BatchMountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_REMOVE_DELETE_TREE = 2048;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mSurfaceId;

    IntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        this.mSurfaceId = i;
        this.mCommitNumber = i2;
        this.mIntBuffer = iArr;
        this.mObjBuffer = objArr;
        int i3 = 0;
        this.mIntBufferLen = iArr != null ? iArr.length : 0;
        this.mObjBufferLen = objArr != null ? objArr.length : i3;
    }

    private void beginMarkers(String str) {
        Systrace.beginSection(0, "FabricUIManager::" + str);
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, (String) null, this.mCommitNumber);
        }
    }

    private void endMarkers() {
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, (String) null, this.mCommitNumber);
        }
        Systrace.endSection(0);
    }

    private static StateWrapper castToState(Object obj) {
        if (obj != null) {
            return (StateWrapper) obj;
        }
        return null;
    }

    private static EventEmitterWrapper castToEventEmitter(Object obj) {
        if (obj != null) {
            return (EventEmitterWrapper) obj;
        }
        return null;
    }

    public void execute(MountingManager mountingManager) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(TAG, "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.mSurfaceId));
        } else if (surfaceManager.isStopped()) {
            FLog.e(TAG, "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.mSurfaceId));
        } else {
            if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                FLog.d(TAG, "Executing IntBufferBatchMountItem on surface [%d]", (Object) Integer.valueOf(this.mSurfaceId));
            }
            beginMarkers("mountViews");
            int i7 = 0;
            int i8 = 0;
            while (i7 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i9 = i7 + 1;
                int i10 = iArr[i7];
                int i11 = i10 & -2;
                if ((i10 & 1) != 0) {
                    int i12 = iArr[i9];
                    i9++;
                    i = i12;
                } else {
                    i = 1;
                }
                int i13 = i8;
                i7 = i9;
                for (int i14 = 0; i14 < i; i14++) {
                    if (i11 == 2) {
                        int i15 = i13 + 1;
                        String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i13]);
                        int i16 = i7 + 1;
                        int i17 = this.mIntBuffer[i7];
                        Object[] objArr = this.mObjBuffer;
                        int i18 = i15 + 1;
                        Object obj = objArr[i15];
                        int i19 = i18 + 1;
                        int i20 = i19 + 1;
                        i5 = i16 + 1;
                        surfaceManager.createView(fabricComponentName, i17, obj, castToState(objArr[i18]), castToEventEmitter(this.mObjBuffer[i19]), this.mIntBuffer[i16] == 1);
                        i13 = i20;
                    } else {
                        if (i11 == 4) {
                            surfaceManager.deleteView(this.mIntBuffer[i7]);
                            i7++;
                        } else {
                            if (i11 == 8) {
                                int[] iArr2 = this.mIntBuffer;
                                int i21 = i7 + 1;
                                int i22 = i21 + 1;
                                i6 = i22 + 1;
                                surfaceManager.addViewAt(iArr2[i21], iArr2[i7], iArr2[i22]);
                            } else if (i11 == 16) {
                                int[] iArr3 = this.mIntBuffer;
                                int i23 = i7 + 1;
                                int i24 = i23 + 1;
                                i6 = i24 + 1;
                                surfaceManager.removeViewAt(iArr3[i7], iArr3[i23], iArr3[i24]);
                            } else if (i11 == 2048) {
                                int[] iArr4 = this.mIntBuffer;
                                int i25 = i7 + 1;
                                int i26 = i25 + 1;
                                i6 = i26 + 1;
                                surfaceManager.removeDeleteTreeAt(iArr4[i7], iArr4[i25], iArr4[i26]);
                            } else {
                                if (i11 == 32) {
                                    i2 = i7 + 1;
                                    i3 = i13 + 1;
                                    surfaceManager.updateProps(this.mIntBuffer[i7], this.mObjBuffer[i13]);
                                } else if (i11 == 64) {
                                    i2 = i7 + 1;
                                    i3 = i13 + 1;
                                    surfaceManager.updateState(this.mIntBuffer[i7], castToState(this.mObjBuffer[i13]));
                                } else if (i11 == 128) {
                                    int[] iArr5 = this.mIntBuffer;
                                    int i27 = i7 + 1;
                                    int i28 = iArr5[i7];
                                    int i29 = i27 + 1;
                                    int i30 = iArr5[i27];
                                    int i31 = i29 + 1;
                                    int i32 = iArr5[i29];
                                    int i33 = i31 + 1;
                                    int i34 = iArr5[i31];
                                    int i35 = i33 + 1;
                                    int i36 = iArr5[i33];
                                    int i37 = i35 + 1;
                                    i5 = i37 + 1;
                                    surfaceManager.updateLayout(i28, i30, i32, i34, i36, iArr5[i35], iArr5[i37]);
                                } else {
                                    if (i11 == 512) {
                                        int[] iArr6 = this.mIntBuffer;
                                        int i38 = i7 + 1;
                                        int i39 = iArr6[i7];
                                        int i40 = i38 + 1;
                                        int i41 = iArr6[i38];
                                        int i42 = i40 + 1;
                                        int i43 = iArr6[i40];
                                        int i44 = i42 + 1;
                                        i4 = i44 + 1;
                                        surfaceManager.updatePadding(i39, i41, i43, iArr6[i42], iArr6[i44]);
                                    } else if (i11 == 1024) {
                                        int[] iArr7 = this.mIntBuffer;
                                        int i45 = i7 + 1;
                                        int i46 = iArr7[i7];
                                        int i47 = i45 + 1;
                                        int i48 = iArr7[i45];
                                        int i49 = i47 + 1;
                                        int i50 = iArr7[i47];
                                        int i51 = i49 + 1;
                                        i4 = i51 + 1;
                                        surfaceManager.updateOverflowInset(i46, i48, i50, iArr7[i49], iArr7[i51]);
                                    } else if (i11 == 256) {
                                        i2 = i7 + 1;
                                        i3 = i13 + 1;
                                        surfaceManager.updateEventEmitter(this.mIntBuffer[i7], castToEventEmitter(this.mObjBuffer[i13]));
                                    } else {
                                        throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i11 + " at index: " + i7);
                                    }
                                    i7 = i4;
                                }
                                i7 = i2;
                                i13 = i3;
                            }
                            i7 = i6;
                        }
                    }
                    i7 = i5;
                }
                i8 = i13;
            }
            endMarkers();
        }
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean isBatchEmpty() {
        return this.mIntBufferLen == 0;
    }

    public String toString() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("IntBufferBatchMountItem [surface:%d]:\n", new Object[]{Integer.valueOf(this.mSurfaceId)}));
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i8 = i6 + 1;
                int i9 = iArr[i6];
                int i10 = i9 & -2;
                if ((i9 & 1) != 0) {
                    i = iArr[i8];
                    i8++;
                } else {
                    i = 1;
                }
                i6 = i8;
                int i11 = 0;
                while (true) {
                    if (i11 < i) {
                        if (i10 == 2) {
                            int i12 = i6 + 1;
                            i5 = i12 + 1;
                            sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i12]), FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i7])}));
                            i7 = i7 + 1 + 3;
                        } else {
                            if (i10 == 4) {
                                i3 = i6 + 1;
                                sb.append(String.format("DELETE [%d]\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6])}));
                            } else if (i10 == 8) {
                                int i13 = i6 + 1;
                                int i14 = i13 + 1;
                                i5 = i14 + 1;
                                sb.append(String.format("INSERT [%d]->[%d] @%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i13]), Integer.valueOf(this.mIntBuffer[i14])}));
                            } else if (i10 == 16) {
                                int i15 = i6 + 1;
                                int i16 = i15 + 1;
                                i5 = i16 + 1;
                                sb.append(String.format("REMOVE [%d]->[%d] @%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i15]), Integer.valueOf(this.mIntBuffer[i16])}));
                            } else if (i10 == 2048) {
                                int i17 = i6 + 1;
                                int i18 = i17 + 1;
                                i5 = i18 + 1;
                                sb.append(String.format("REMOVE+DELETE TREE [%d]->[%d] @%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i17]), Integer.valueOf(this.mIntBuffer[i18])}));
                            } else {
                                if (i10 == 32) {
                                    i4 = i7 + 1;
                                    Object obj = this.mObjBuffer[i7];
                                    i3 = i6 + 1;
                                    sb.append(String.format("UPDATE PROPS [%d]: %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), "<hidden>"}));
                                } else if (i10 == 64) {
                                    i4 = i7 + 1;
                                    castToState(this.mObjBuffer[i7]);
                                    i3 = i6 + 1;
                                    sb.append(String.format("UPDATE STATE [%d]: %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), "<hidden>"}));
                                } else {
                                    if (i10 == 128) {
                                        int[] iArr2 = this.mIntBuffer;
                                        int i19 = i6 + 1;
                                        int i20 = iArr2[i6];
                                        int i21 = i19 + 1;
                                        int i22 = i21 + 1;
                                        int i23 = i22 + 1;
                                        int i24 = i23 + 1;
                                        int i25 = i24 + 1;
                                        i2 = i25 + 1;
                                        sb.append(String.format("UPDATE LAYOUT [%d]->[%d]: x:%d y:%d w:%d h:%d displayType:%d\n", new Object[]{Integer.valueOf(iArr2[i19]), Integer.valueOf(i20), Integer.valueOf(this.mIntBuffer[i21]), Integer.valueOf(this.mIntBuffer[i22]), Integer.valueOf(this.mIntBuffer[i23]), Integer.valueOf(this.mIntBuffer[i24]), Integer.valueOf(this.mIntBuffer[i25])}));
                                    } else if (i10 == 512) {
                                        int i26 = i6 + 1;
                                        int i27 = i26 + 1;
                                        int i28 = i27 + 1;
                                        int i29 = i28 + 1;
                                        i2 = i29 + 1;
                                        sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i26]), Integer.valueOf(this.mIntBuffer[i27]), Integer.valueOf(this.mIntBuffer[i28]), Integer.valueOf(this.mIntBuffer[i29])}));
                                    } else if (i10 == 1024) {
                                        int i30 = i6 + 1;
                                        int i31 = i30 + 1;
                                        int i32 = i31 + 1;
                                        int i33 = i32 + 1;
                                        i2 = i33 + 1;
                                        sb.append(String.format("UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6]), Integer.valueOf(this.mIntBuffer[i30]), Integer.valueOf(this.mIntBuffer[i31]), Integer.valueOf(this.mIntBuffer[i32]), Integer.valueOf(this.mIntBuffer[i33])}));
                                    } else if (i10 == 256) {
                                        i7++;
                                        i3 = i6 + 1;
                                        sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", new Object[]{Integer.valueOf(this.mIntBuffer[i6])}));
                                    } else {
                                        FLog.e(TAG, "String so far: " + sb.toString());
                                        throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i10 + " at index: " + i6);
                                    }
                                    i6 = i2;
                                    i11++;
                                }
                                i7 = i4;
                            }
                            i6 = i3;
                            i11++;
                        }
                        i6 = i5;
                        i11++;
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.e(TAG, "Caught exception trying to print", (Throwable) e);
            StringBuilder sb2 = new StringBuilder();
            for (int i34 = 0; i34 < this.mIntBufferLen; i34++) {
                sb2.append(this.mIntBuffer[i34]);
                sb2.append(", ");
            }
            FLog.e(TAG, sb2.toString());
            for (int i35 = 0; i35 < this.mObjBufferLen; i35++) {
                String str = TAG;
                Object obj2 = this.mObjBuffer[i35];
                FLog.e(str, obj2 != null ? obj2.toString() : "null");
            }
            return "";
        }
    }
}
