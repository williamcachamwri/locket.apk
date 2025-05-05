package androidx.media3.exoplayer.util;

import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.upstream.Loader;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

public final class SntpClient {
    public static final String DEFAULT_NTP_HOST = "time.android.com";
    public static final int DEFAULT_TIMEOUT_MS = 1000;
    private static final int MAX_RETRY_COUNT = 10;
    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_PACKET_SIZE = 48;
    private static final int NTP_PORT = 123;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;
    private static final int NTP_VERSION = 3;
    private static final long OFFSET_1900_TO_1970 = 2208988800L;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int TRANSMIT_TIME_OFFSET = 40;
    /* access modifiers changed from: private */
    public static long elapsedRealtimeOffsetMs = 0;
    /* access modifiers changed from: private */
    public static boolean isInitialized = false;
    /* access modifiers changed from: private */
    public static long lastUpdateElapsedRealtime = -9223372036854775807L;
    /* access modifiers changed from: private */
    public static final Object loaderLock = new Object();
    private static long maxElapsedTimeUntilUpdateMs = -9223372036854775807L;
    private static String ntpHost = "time.android.com";
    private static int timeoutMs = 1000;
    /* access modifiers changed from: private */
    public static final Object valueLock = new Object();

    public interface InitializationCallback {
        void onInitializationFailed(IOException iOException);

        void onInitialized();
    }

    private SntpClient() {
    }

    public static String getNtpHost() {
        String str;
        synchronized (valueLock) {
            str = ntpHost;
        }
        return str;
    }

    public static void setNtpHost(String str) {
        synchronized (valueLock) {
            if (!ntpHost.equals(str)) {
                ntpHost = str;
                isInitialized = false;
            }
        }
    }

    public static int getTimeoutMs() {
        int i;
        synchronized (valueLock) {
            i = timeoutMs;
        }
        return i;
    }

    public static void setTimeoutMs(int i) {
        synchronized (valueLock) {
            if (timeoutMs != i) {
                timeoutMs = i;
                isInitialized = false;
            }
        }
    }

    public static void setMaxElapsedTimeUntilUpdateMs(long j) {
        synchronized (valueLock) {
            maxElapsedTimeUntilUpdateMs = j;
        }
    }

    public static long getMaxElapsedTimeUntilUpdateMs() {
        long j;
        synchronized (valueLock) {
            j = maxElapsedTimeUntilUpdateMs;
        }
        return j;
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (valueLock) {
            if (!(lastUpdateElapsedRealtime == C.TIME_UNSET || maxElapsedTimeUntilUpdateMs == C.TIME_UNSET)) {
                isInitialized = isInitialized && SystemClock.elapsedRealtime() - lastUpdateElapsedRealtime < maxElapsedTimeUntilUpdateMs;
            }
            z = isInitialized;
        }
        return z;
    }

    public static long getElapsedRealtimeOffsetMs() {
        long j;
        synchronized (valueLock) {
            j = isInitialized ? elapsedRealtimeOffsetMs : C.TIME_UNSET;
        }
        return j;
    }

    public static void initialize(Loader loader, InitializationCallback initializationCallback) {
        if (!isInitialized()) {
            if (loader == null) {
                loader = new Loader("SntpClient");
            }
            loader.startLoading(new NtpTimeLoadable(), new NtpTimeCallback(initializationCallback), 1);
        } else if (initializationCallback != null) {
            initializationCallback.onInitialized();
        }
    }

    /* access modifiers changed from: private */
    public static long loadNtpTimeOffsetMs() throws IOException {
        SocketTimeoutException socketTimeoutException;
        int i;
        int i2;
        DatagramSocket datagramSocket = new DatagramSocket();
        try {
            datagramSocket.setSoTimeout(getTimeoutMs());
            InetAddress[] allByName = InetAddress.getAllByName(getNtpHost());
            int length = allByName.length;
            socketTimeoutException = null;
            i = 0;
            i2 = 0;
            while (i < length) {
                byte[] bArr = new byte[48];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, allByName[i], NTP_PORT);
                bArr[0] = Ascii.ESC;
                long currentTimeMillis = System.currentTimeMillis();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                writeTimestamp(bArr, 40, currentTimeMillis);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(new DatagramPacket(bArr, 48));
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                long j = currentTimeMillis + (elapsedRealtime2 - elapsedRealtime);
                byte b = bArr[0];
                long readTimestamp = readTimestamp(bArr, 24);
                long readTimestamp2 = readTimestamp(bArr, 32);
                long readTimestamp3 = readTimestamp(bArr, 40);
                checkValidServerReply((byte) ((b >> 6) & 3), (byte) (b & 7), bArr[1] & 255, readTimestamp3);
                long j2 = (j + (((readTimestamp2 - readTimestamp) + (readTimestamp3 - j)) / 2)) - elapsedRealtime2;
                datagramSocket.close();
                return j2;
            }
        } catch (SocketTimeoutException e) {
            SocketTimeoutException socketTimeoutException2 = e;
            if (socketTimeoutException == null) {
                socketTimeoutException = socketTimeoutException2;
            } else {
                socketTimeoutException.addSuppressed(socketTimeoutException2);
            }
            int i3 = i2 + 1;
            if (i2 >= 10) {
                break;
            }
            i++;
            i2 = i3;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                datagramSocket.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
        throw ((SocketTimeoutException) Assertions.checkNotNull(socketTimeoutException));
    }

    private static long readTimestamp(byte[] bArr, int i) {
        long read32 = read32(bArr, i);
        long read322 = read32(bArr, i + 4);
        if (read32 == 0 && read322 == 0) {
            return 0;
        }
        return ((read32 - OFFSET_1900_TO_1970) * 1000) + ((read322 * 1000) / 4294967296L);
    }

    private static void writeTimestamp(byte[] bArr, int i, long j) {
        if (j == 0) {
            Arrays.fill(bArr, i, i + 8, (byte) 0);
            return;
        }
        long j2 = j / 1000;
        long j3 = j2 + OFFSET_1900_TO_1970;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) (j3 >> 24));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) (j3 >> 16));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) (j3 >> 8));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) (j3 >> 0));
        long j4 = ((j - (j2 * 1000)) * 4294967296L) / 1000;
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) (j4 >> 24));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) (j4 >> 16));
        bArr[i7] = (byte) ((int) (j4 >> 8));
        bArr[i7 + 1] = (byte) ((int) (Math.random() * 255.0d));
    }

    private static long read32(byte[] bArr, int i) {
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        byte b5 = b & true;
        int i2 = b;
        if (b5 == true) {
            i2 = (b & Byte.MAX_VALUE) + 128;
        }
        byte b6 = b2 & true;
        int i3 = b2;
        if (b6 == true) {
            i3 = (b2 & Byte.MAX_VALUE) + 128;
        }
        byte b7 = b3 & true;
        int i4 = b3;
        if (b7 == true) {
            i4 = (b3 & Byte.MAX_VALUE) + 128;
        }
        byte b8 = b4 & true;
        int i5 = b4;
        if (b8 == true) {
            i5 = (b4 & Byte.MAX_VALUE) + 128;
        }
        return (((long) i2) << 24) + (((long) i3) << 16) + (((long) i4) << 8) + ((long) i5);
    }

    private static void checkValidServerReply(byte b, byte b2, int i, long j) throws IOException {
        if (b == 3) {
            throw new IOException("SNTP: Unsynchronized server");
        } else if (b2 != 4 && b2 != 5) {
            throw new IOException("SNTP: Untrusted mode: " + b2);
        } else if (i == 0 || i > 15) {
            throw new IOException("SNTP: Untrusted stratum: " + i);
        } else if (j == 0) {
            throw new IOException("SNTP: Zero transmitTime");
        }
    }

    private static final class NtpTimeLoadable implements Loader.Loadable {
        public void cancelLoad() {
        }

        private NtpTimeLoadable() {
        }

        public void load() throws IOException {
            synchronized (SntpClient.loaderLock) {
                synchronized (SntpClient.valueLock) {
                    if (!SntpClient.isInitialized) {
                        long access$400 = SntpClient.loadNtpTimeOffsetMs();
                        synchronized (SntpClient.valueLock) {
                            long unused = SntpClient.lastUpdateElapsedRealtime = SystemClock.elapsedRealtime();
                            long unused2 = SntpClient.elapsedRealtimeOffsetMs = access$400;
                            boolean unused3 = SntpClient.isInitialized = true;
                        }
                    }
                }
            }
        }
    }

    private static final class NtpTimeCallback implements Loader.Callback<Loader.Loadable> {
        private final InitializationCallback callback;

        public void onLoadCanceled(Loader.Loadable loadable, long j, long j2, boolean z) {
        }

        public NtpTimeCallback(InitializationCallback initializationCallback) {
            this.callback = initializationCallback;
        }

        public void onLoadCompleted(Loader.Loadable loadable, long j, long j2) {
            if (this.callback == null) {
                return;
            }
            if (!SntpClient.isInitialized()) {
                this.callback.onInitializationFailed(new IOException(new ConcurrentModificationException()));
            } else {
                this.callback.onInitialized();
            }
        }

        public Loader.LoadErrorAction onLoadError(Loader.Loadable loadable, long j, long j2, IOException iOException, int i) {
            InitializationCallback initializationCallback = this.callback;
            if (initializationCallback != null) {
                initializationCallback.onInitializationFailed(iOException);
            }
            return Loader.DONT_RETRY;
        }
    }
}
