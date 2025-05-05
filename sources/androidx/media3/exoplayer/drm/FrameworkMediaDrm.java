package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.media.UnsupportedSchemeException;
import android.media.metrics.LogSessionId;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.extractor.mp4.PsshAtomUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class FrameworkMediaDrm implements ExoMediaDrm {
    private static final String CENC_SCHEME_MIME_TYPE = "cenc";
    public static final ExoMediaDrm.Provider DEFAULT_PROVIDER = new FrameworkMediaDrm$$ExternalSyntheticLambda1();
    private static final String MOCK_LA_URL = "<LA_URL>https://x</LA_URL>";
    private static final String MOCK_LA_URL_VALUE = "https://x";
    private static final String TAG = "FrameworkMediaDrm";
    private static final int UTF_16_BYTES_PER_CHARACTER = 2;
    private final MediaDrm mediaDrm;
    private int referenceCount = 1;
    private final UUID uuid;

    public int getCryptoType() {
        return 2;
    }

    static /* synthetic */ ExoMediaDrm lambda$static$0(UUID uuid2) {
        try {
            return newInstance(uuid2);
        } catch (UnsupportedDrmException unused) {
            Log.e(TAG, "Failed to instantiate a FrameworkMediaDrm for uuid: " + uuid2 + ".");
            return new DummyExoMediaDrm();
        }
    }

    public static boolean isCryptoSchemeSupported(UUID uuid2) {
        return MediaDrm.isCryptoSchemeSupported(adjustUuid(uuid2));
    }

    public static FrameworkMediaDrm newInstance(UUID uuid2) throws UnsupportedDrmException {
        try {
            return new FrameworkMediaDrm(uuid2);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(1, e);
        } catch (Exception e2) {
            throw new UnsupportedDrmException(2, e2);
        }
    }

    private FrameworkMediaDrm(UUID uuid2) throws UnsupportedSchemeException {
        Assertions.checkNotNull(uuid2);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        MediaDrm mediaDrm2 = new MediaDrm(adjustUuid(uuid2));
        this.mediaDrm = mediaDrm2;
        if (C.WIDEVINE_UUID.equals(uuid2) && needsForceWidevineL3Workaround()) {
            forceWidevineL3(mediaDrm2);
        }
    }

    public void setOnEventListener(ExoMediaDrm.OnEventListener onEventListener) {
        FrameworkMediaDrm$$ExternalSyntheticLambda2 frameworkMediaDrm$$ExternalSyntheticLambda2;
        MediaDrm mediaDrm2 = this.mediaDrm;
        if (onEventListener == null) {
            frameworkMediaDrm$$ExternalSyntheticLambda2 = null;
        } else {
            frameworkMediaDrm$$ExternalSyntheticLambda2 = new FrameworkMediaDrm$$ExternalSyntheticLambda2(this, onEventListener);
        }
        mediaDrm2.setOnEventListener(frameworkMediaDrm$$ExternalSyntheticLambda2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnEventListener$1$androidx-media3-exoplayer-drm-FrameworkMediaDrm  reason: not valid java name */
    public /* synthetic */ void m512lambda$setOnEventListener$1$androidxmedia3exoplayerdrmFrameworkMediaDrm(ExoMediaDrm.OnEventListener onEventListener, MediaDrm mediaDrm2, byte[] bArr, int i, int i2, byte[] bArr2) {
        onEventListener.onEvent(this, bArr, i, i2, bArr2);
    }

    public void setOnKeyStatusChangeListener(ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener) {
        FrameworkMediaDrm$$ExternalSyntheticLambda3 frameworkMediaDrm$$ExternalSyntheticLambda3;
        if (Util.SDK_INT >= 23) {
            MediaDrm mediaDrm2 = this.mediaDrm;
            if (onKeyStatusChangeListener == null) {
                frameworkMediaDrm$$ExternalSyntheticLambda3 = null;
            } else {
                frameworkMediaDrm$$ExternalSyntheticLambda3 = new FrameworkMediaDrm$$ExternalSyntheticLambda3(this, onKeyStatusChangeListener);
            }
            mediaDrm2.setOnKeyStatusChangeListener(frameworkMediaDrm$$ExternalSyntheticLambda3, (Handler) null);
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnKeyStatusChangeListener$2$androidx-media3-exoplayer-drm-FrameworkMediaDrm  reason: not valid java name */
    public /* synthetic */ void m514lambda$setOnKeyStatusChangeListener$2$androidxmedia3exoplayerdrmFrameworkMediaDrm(ExoMediaDrm.OnKeyStatusChangeListener onKeyStatusChangeListener, MediaDrm mediaDrm2, byte[] bArr, List list, boolean z) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaDrm.KeyStatus keyStatus = (MediaDrm.KeyStatus) it.next();
            arrayList.add(new ExoMediaDrm.KeyStatus(keyStatus.getStatusCode(), keyStatus.getKeyId()));
        }
        onKeyStatusChangeListener.onKeyStatusChange(this, bArr, arrayList, z);
    }

    public void setOnExpirationUpdateListener(ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener) {
        FrameworkMediaDrm$$ExternalSyntheticLambda0 frameworkMediaDrm$$ExternalSyntheticLambda0;
        if (Util.SDK_INT >= 23) {
            MediaDrm mediaDrm2 = this.mediaDrm;
            if (onExpirationUpdateListener == null) {
                frameworkMediaDrm$$ExternalSyntheticLambda0 = null;
            } else {
                frameworkMediaDrm$$ExternalSyntheticLambda0 = new FrameworkMediaDrm$$ExternalSyntheticLambda0(this, onExpirationUpdateListener);
            }
            mediaDrm2.setOnExpirationUpdateListener(frameworkMediaDrm$$ExternalSyntheticLambda0, (Handler) null);
            return;
        }
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnExpirationUpdateListener$3$androidx-media3-exoplayer-drm-FrameworkMediaDrm  reason: not valid java name */
    public /* synthetic */ void m513lambda$setOnExpirationUpdateListener$3$androidxmedia3exoplayerdrmFrameworkMediaDrm(ExoMediaDrm.OnExpirationUpdateListener onExpirationUpdateListener, MediaDrm mediaDrm2, byte[] bArr, long j) {
        onExpirationUpdateListener.onExpirationUpdate(this, bArr, j);
    }

    public byte[] openSession() throws MediaDrmException {
        return this.mediaDrm.openSession();
    }

    public void closeSession(byte[] bArr) {
        this.mediaDrm.closeSession(bArr);
    }

    public void setPlayerIdForSession(byte[] bArr, PlayerId playerId) {
        if (Util.SDK_INT >= 31) {
            try {
                Api31.setLogSessionIdOnMediaDrmSession(this.mediaDrm, bArr, playerId);
            } catch (UnsupportedOperationException unused) {
                Log.w(TAG, "setLogSessionId failed.");
            }
        }
    }

    public ExoMediaDrm.KeyRequest getKeyRequest(byte[] bArr, List<DrmInitData.SchemeData> list, int i, HashMap<String, String> hashMap) throws NotProvisionedException {
        DrmInitData.SchemeData schemeData;
        String str;
        byte[] bArr2;
        if (list != null) {
            schemeData = getSchemeData(this.uuid, list);
            bArr2 = adjustRequestInitData(this.uuid, (byte[]) Assertions.checkNotNull(schemeData.data));
            str = adjustRequestMimeType(this.uuid, schemeData.mimeType);
        } else {
            schemeData = null;
            bArr2 = null;
            str = null;
        }
        MediaDrm.KeyRequest keyRequest = this.mediaDrm.getKeyRequest(bArr, bArr2, str, i, hashMap);
        byte[] adjustRequestData = adjustRequestData(this.uuid, keyRequest.getData());
        String adjustLicenseServerUrl = adjustLicenseServerUrl(keyRequest.getDefaultUrl());
        if (TextUtils.isEmpty(adjustLicenseServerUrl) && schemeData != null && !TextUtils.isEmpty(schemeData.licenseServerUrl)) {
            adjustLicenseServerUrl = schemeData.licenseServerUrl;
        }
        return new ExoMediaDrm.KeyRequest(adjustRequestData, adjustLicenseServerUrl, Util.SDK_INT >= 23 ? keyRequest.getRequestType() : Integer.MIN_VALUE);
    }

    private String adjustLicenseServerUrl(String str) {
        if (MOCK_LA_URL.equals(str)) {
            return "";
        }
        if (Util.SDK_INT >= 33 && "https://default.url".equals(str)) {
            String propertyString = getPropertyString("version");
            if (Objects.equals(propertyString, "1.2") || Objects.equals(propertyString, "aidl-1")) {
                return "";
            }
        }
        return str;
    }

    public byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws NotProvisionedException, DeniedByServerException {
        if (C.CLEARKEY_UUID.equals(this.uuid)) {
            bArr2 = ClearKeyUtil.adjustResponseData(bArr2);
        }
        return this.mediaDrm.provideKeyResponse(bArr, bArr2);
    }

    public ExoMediaDrm.ProvisionRequest getProvisionRequest() {
        MediaDrm.ProvisionRequest provisionRequest = this.mediaDrm.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    public void provideProvisionResponse(byte[] bArr) throws DeniedByServerException {
        this.mediaDrm.provideProvisionResponse(bArr);
    }

    public Map<String, String> queryKeyStatus(byte[] bArr) {
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean requiresSecureDecoder(byte[] r4, java.lang.String r5) {
        /*
            r3 = this;
            int r0 = androidx.media3.common.util.Util.SDK_INT
            r1 = 31
            if (r0 < r1) goto L_0x0017
            boolean r0 = r3.isMediaDrmRequiresSecureDecoderImplemented()
            if (r0 == 0) goto L_0x0017
            android.media.MediaDrm r0 = r3.mediaDrm
            int r4 = r0.getSecurityLevel(r4)
            boolean r4 = androidx.media3.exoplayer.drm.FrameworkMediaDrm.Api31.requiresSecureDecoder(r0, r5, r4)
            goto L_0x0039
        L_0x0017:
            r0 = 0
            android.media.MediaCrypto r1 = new android.media.MediaCrypto     // Catch:{ MediaCryptoException -> 0x0033, all -> 0x002c }
            java.util.UUID r2 = r3.uuid     // Catch:{ MediaCryptoException -> 0x0033, all -> 0x002c }
            r1.<init>(r2, r4)     // Catch:{ MediaCryptoException -> 0x0033, all -> 0x002c }
            boolean r4 = r1.requiresSecureDecoderComponent(r5)     // Catch:{ MediaCryptoException -> 0x002a, all -> 0x0027 }
            r1.release()
            goto L_0x0039
        L_0x0027:
            r4 = move-exception
            r0 = r1
            goto L_0x002d
        L_0x002a:
            r0 = r1
            goto L_0x0033
        L_0x002c:
            r4 = move-exception
        L_0x002d:
            if (r0 == 0) goto L_0x0032
            r0.release()
        L_0x0032:
            throw r4
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            r0.release()
        L_0x0038:
            r4 = 1
        L_0x0039:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.FrameworkMediaDrm.requiresSecureDecoder(byte[], java.lang.String):boolean");
    }

    public synchronized void acquire() {
        Assertions.checkState(this.referenceCount > 0);
        this.referenceCount++;
    }

    public synchronized void release() {
        int i = this.referenceCount - 1;
        this.referenceCount = i;
        if (i == 0) {
            this.mediaDrm.release();
        }
    }

    public void restoreKeys(byte[] bArr, byte[] bArr2) {
        this.mediaDrm.restoreKeys(bArr, bArr2);
    }

    public void removeOfflineLicense(byte[] bArr) {
        if (Util.SDK_INT >= 29) {
            this.mediaDrm.removeOfflineLicense(bArr);
            return;
        }
        throw new UnsupportedOperationException();
    }

    public List<byte[]> getOfflineLicenseKeySetIds() {
        if (Util.SDK_INT >= 29) {
            return this.mediaDrm.getOfflineLicenseKeySetIds();
        }
        throw new UnsupportedOperationException();
    }

    public PersistableBundle getMetrics() {
        if (Util.SDK_INT < 28) {
            return null;
        }
        return this.mediaDrm.getMetrics();
    }

    public String getPropertyString(String str) {
        return this.mediaDrm.getPropertyString(str);
    }

    public byte[] getPropertyByteArray(String str) {
        return this.mediaDrm.getPropertyByteArray(str);
    }

    public void setPropertyString(String str, String str2) {
        this.mediaDrm.setPropertyString(str, str2);
    }

    public void setPropertyByteArray(String str, byte[] bArr) {
        this.mediaDrm.setPropertyByteArray(str, bArr);
    }

    public FrameworkCryptoConfig createCryptoConfig(byte[] bArr) throws MediaCryptoException {
        return new FrameworkCryptoConfig(adjustUuid(this.uuid), bArr);
    }

    private boolean isMediaDrmRequiresSecureDecoderImplemented() {
        if (!this.uuid.equals(C.WIDEVINE_UUID)) {
            return this.uuid.equals(C.CLEARKEY_UUID);
        }
        String propertyString = getPropertyString("version");
        return !propertyString.startsWith("v5.") && !propertyString.startsWith("14.") && !propertyString.startsWith("15.") && !propertyString.startsWith("16.0");
    }

    private static DrmInitData.SchemeData getSchemeData(UUID uuid2, List<DrmInitData.SchemeData> list) {
        boolean z;
        if (!C.WIDEVINE_UUID.equals(uuid2)) {
            return list.get(0);
        }
        if (Util.SDK_INT >= 28 && list.size() > 1) {
            DrmInitData.SchemeData schemeData = list.get(0);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= list.size()) {
                    z = true;
                    break;
                }
                DrmInitData.SchemeData schemeData2 = list.get(i);
                byte[] bArr = (byte[]) Assertions.checkNotNull(schemeData2.data);
                if (!Util.areEqual(schemeData2.mimeType, schemeData.mimeType) || !Util.areEqual(schemeData2.licenseServerUrl, schemeData.licenseServerUrl) || !PsshAtomUtil.isPsshAtom(bArr)) {
                    z = false;
                } else {
                    i2 += bArr.length;
                    i++;
                }
            }
            z = false;
            if (z) {
                byte[] bArr2 = new byte[i2];
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    byte[] bArr3 = (byte[]) Assertions.checkNotNull(list.get(i4).data);
                    int length = bArr3.length;
                    System.arraycopy(bArr3, 0, bArr2, i3, length);
                    i3 += length;
                }
                return schemeData.copyWithData(bArr2);
            }
        }
        for (int i5 = 0; i5 < list.size(); i5++) {
            DrmInitData.SchemeData schemeData3 = list.get(i5);
            int parseVersion = PsshAtomUtil.parseVersion((byte[]) Assertions.checkNotNull(schemeData3.data));
            if (Util.SDK_INT < 23 && parseVersion == 0) {
                return schemeData3;
            }
            if (Util.SDK_INT >= 23 && parseVersion == 1) {
                return schemeData3;
            }
        }
        return list.get(0);
    }

    private static UUID adjustUuid(UUID uuid2) {
        return (Util.SDK_INT >= 27 || !C.CLEARKEY_UUID.equals(uuid2)) ? uuid2 : C.COMMON_PSSH_UUID;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0062, code lost:
        r2 = androidx.media3.extractor.mp4.PsshAtomUtil.parseSchemeSpecificData(r3, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] adjustRequestInitData(java.util.UUID r2, byte[] r3) {
        /*
            java.util.UUID r0 = androidx.media3.common.C.PLAYREADY_UUID
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x001a
            byte[] r0 = androidx.media3.extractor.mp4.PsshAtomUtil.parseSchemeSpecificData(r3, r2)
            if (r0 != 0) goto L_0x000f
            goto L_0x0010
        L_0x000f:
            r3 = r0
        L_0x0010:
            java.util.UUID r0 = androidx.media3.common.C.PLAYREADY_UUID
            byte[] r3 = addLaUrlAttributeIfMissing(r3)
            byte[] r3 = androidx.media3.extractor.mp4.PsshAtomUtil.buildPsshAtom(r0, r3)
        L_0x001a:
            int r0 = androidx.media3.common.util.Util.SDK_INT
            r1 = 23
            if (r0 >= r1) goto L_0x0028
            java.util.UUID r0 = androidx.media3.common.C.WIDEVINE_UUID
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0062
        L_0x0028:
            java.util.UUID r0 = androidx.media3.common.C.PLAYREADY_UUID
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "Amazon"
            java.lang.String r1 = androidx.media3.common.util.Util.MANUFACTURER
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0069
            java.lang.String r0 = "AFTB"
            java.lang.String r1 = androidx.media3.common.util.Util.MODEL
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0062
            java.lang.String r0 = "AFTS"
            java.lang.String r1 = androidx.media3.common.util.Util.MODEL
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0062
            java.lang.String r0 = "AFTM"
            java.lang.String r1 = androidx.media3.common.util.Util.MODEL
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0062
            java.lang.String r0 = "AFTT"
            java.lang.String r1 = androidx.media3.common.util.Util.MODEL
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0069
        L_0x0062:
            byte[] r2 = androidx.media3.extractor.mp4.PsshAtomUtil.parseSchemeSpecificData(r3, r2)
            if (r2 == 0) goto L_0x0069
            return r2
        L_0x0069:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.FrameworkMediaDrm.adjustRequestInitData(java.util.UUID, byte[]):byte[]");
    }

    private static String adjustRequestMimeType(UUID uuid2, String str) {
        return (Util.SDK_INT >= 26 || !C.CLEARKEY_UUID.equals(uuid2) || (!MimeTypes.VIDEO_MP4.equals(str) && !MimeTypes.AUDIO_MP4.equals(str))) ? str : "cenc";
    }

    private static byte[] adjustRequestData(UUID uuid2, byte[] bArr) {
        return C.CLEARKEY_UUID.equals(uuid2) ? ClearKeyUtil.adjustRequestData(bArr) : bArr;
    }

    private static void forceWidevineL3(MediaDrm mediaDrm2) {
        mediaDrm2.setPropertyString("securityLevel", "L3");
    }

    private static boolean needsForceWidevineL3Workaround() {
        return "ASUS_Z00AD".equals(Util.MODEL);
    }

    private static byte[] addLaUrlAttributeIfMissing(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        int readLittleEndianInt = parsableByteArray.readLittleEndianInt();
        short readLittleEndianShort = parsableByteArray.readLittleEndianShort();
        short readLittleEndianShort2 = parsableByteArray.readLittleEndianShort();
        if (readLittleEndianShort == 1 && readLittleEndianShort2 == 1) {
            String readString = parsableByteArray.readString(parsableByteArray.readLittleEndianShort(), StandardCharsets.UTF_16LE);
            if (readString.contains("<LA_URL>")) {
                return bArr;
            }
            int indexOf = readString.indexOf("</DATA>");
            if (indexOf == -1) {
                Log.w(TAG, "Could not find the </DATA> tag. Skipping LA_URL workaround.");
            }
            String str = readString.substring(0, indexOf) + MOCK_LA_URL + readString.substring(indexOf);
            int i = readLittleEndianInt + 52;
            ByteBuffer allocate = ByteBuffer.allocate(i);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(i);
            allocate.putShort((short) readLittleEndianShort);
            allocate.putShort((short) readLittleEndianShort2);
            allocate.putShort((short) (str.length() * 2));
            allocate.put(str.getBytes(StandardCharsets.UTF_16LE));
            return allocate.array();
        }
        Log.i(TAG, "Unexpected record count or type. Skipping LA_URL workaround.");
        return bArr;
    }

    private static class Api31 {
        private Api31() {
        }

        public static boolean requiresSecureDecoder(MediaDrm mediaDrm, String str, int i) {
            return mediaDrm.requiresSecureDecoder(str, i);
        }

        public static void setLogSessionIdOnMediaDrmSession(MediaDrm mediaDrm, byte[] bArr, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                ((MediaDrm.PlaybackComponent) Assertions.checkNotNull(mediaDrm.getPlaybackComponent(bArr))).setLogSessionId(logSessionId);
            }
        }
    }
}
