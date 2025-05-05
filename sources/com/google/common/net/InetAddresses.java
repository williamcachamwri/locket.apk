package com.google.common.net;

import com.google.common.base.CharMatcher;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.CheckForNull;
import kotlin.UShort;

@ElementTypesAreNonnullByDefault
public final class InetAddresses {
    /* access modifiers changed from: private */
    public static final Inet4Address ANY4 = ((Inet4Address) forString("0.0.0.0"));
    private static final char IPV4_DELIMITER = '.';
    private static final CharMatcher IPV4_DELIMITER_MATCHER = CharMatcher.is('.');
    private static final int IPV4_PART_COUNT = 4;
    private static final char IPV6_DELIMITER = ':';
    private static final CharMatcher IPV6_DELIMITER_MATCHER = CharMatcher.is(':');
    private static final int IPV6_PART_COUNT = 8;
    private static final Inet4Address LOOPBACK4 = ((Inet4Address) forString("127.0.0.1"));

    private InetAddresses() {
    }

    private static Inet4Address getInet4Address(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", bArr.length);
        return (Inet4Address) bytesToInetAddress(bArr, (String) null);
    }

    public static InetAddress forString(String str) {
        Scope scope = new Scope();
        byte[] ipStringToBytes = ipStringToBytes(str, scope);
        if (ipStringToBytes != null) {
            return bytesToInetAddress(ipStringToBytes, scope.scope);
        }
        throw formatIllegalArgumentException("'%s' is not an IP string literal.", str);
    }

    public static boolean isInetAddress(String str) {
        return ipStringToBytes(str, (Scope) null) != null;
    }

    private static final class Scope {
        /* access modifiers changed from: private */
        public String scope;

        private Scope() {
        }
    }

    @CheckForNull
    private static byte[] ipStringToBytes(String str, Scope scope) {
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (i >= str.length()) {
                i = -1;
                break;
            }
            char charAt = str.charAt(i);
            if (charAt == '.') {
                z = true;
            } else if (charAt == ':') {
                if (z) {
                    return null;
                }
                z2 = true;
            } else if (charAt == '%') {
                break;
            } else if (Character.digit(charAt, 16) == -1) {
                return null;
            }
            i++;
        }
        if (z2) {
            if (z && (str = convertDottedQuadToHex(str)) == null) {
                return null;
            }
            if (i != -1) {
                if (scope != null) {
                    String unused = scope.scope = str.substring(i + 1);
                }
                str = str.substring(0, i);
            }
            return textToNumericFormatV6(str);
        } else if (!z || i != -1) {
            return null;
        } else {
            return textToNumericFormatV4(str);
        }
    }

    @CheckForNull
    private static byte[] textToNumericFormatV4(String str) {
        if (IPV4_DELIMITER_MATCHER.countIn(str) + 1 != 4) {
            return null;
        }
        byte[] bArr = new byte[4];
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            int indexOf = str.indexOf(46, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            try {
                bArr[i] = parseOctet(str, i2, indexOf);
                i2 = indexOf + 1;
                i++;
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return bArr;
    }

    @CheckForNull
    private static byte[] textToNumericFormatV6(String str) {
        int countIn = IPV6_DELIMITER_MATCHER.countIn(str);
        if (countIn >= 2 && countIn <= 8) {
            int i = 1;
            int i2 = countIn + 1;
            int i3 = 8 - i2;
            boolean z = false;
            for (int i4 = 0; i4 < str.length() - 1; i4++) {
                if (str.charAt(i4) == ':' && str.charAt(i4 + 1) == ':') {
                    if (z) {
                        return null;
                    }
                    i3++;
                    if (i4 == 0) {
                        i3++;
                    }
                    if (i4 == str.length() - 2) {
                        i3++;
                    }
                    z = true;
                }
            }
            if (str.charAt(0) == ':' && str.charAt(1) != ':') {
                return null;
            }
            if (str.charAt(str.length() - 1) == ':' && str.charAt(str.length() - 2) != ':') {
                return null;
            }
            if (z && i3 <= 0) {
                return null;
            }
            if (!z && i2 != 8) {
                return null;
            }
            ByteBuffer allocate = ByteBuffer.allocate(16);
            try {
                if (str.charAt(0) != ':') {
                    i = 0;
                }
                while (i < str.length()) {
                    int indexOf = str.indexOf(58, i);
                    if (indexOf == -1) {
                        indexOf = str.length();
                    }
                    if (str.charAt(i) == ':') {
                        for (int i5 = 0; i5 < i3; i5++) {
                            allocate.putShort(0);
                        }
                    } else {
                        allocate.putShort(parseHextet(str, i, indexOf));
                    }
                    i = indexOf + 1;
                }
                return allocate.array();
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    @CheckForNull
    private static String convertDottedQuadToHex(String str) {
        int lastIndexOf = str.lastIndexOf(58) + 1;
        String substring = str.substring(0, lastIndexOf);
        byte[] textToNumericFormatV4 = textToNumericFormatV4(str.substring(lastIndexOf));
        if (textToNumericFormatV4 == null) {
            return null;
        }
        String hexString = Integer.toHexString(((textToNumericFormatV4[0] & 255) << 8) | (textToNumericFormatV4[1] & 255));
        return substring + hexString + ":" + Integer.toHexString((textToNumericFormatV4[3] & 255) | ((textToNumericFormatV4[2] & 255) << 8));
    }

    private static byte parseOctet(String str, int i, int i2) {
        int i3 = i2 - i;
        if (i3 <= 0 || i3 > 3) {
            throw new NumberFormatException();
        } else if (i3 <= 1 || str.charAt(i) != '0') {
            int i4 = 0;
            while (i < i2) {
                int i5 = i4 * 10;
                int digit = Character.digit(str.charAt(i), 10);
                if (digit >= 0) {
                    i4 = i5 + digit;
                    i++;
                } else {
                    throw new NumberFormatException();
                }
            }
            if (i4 <= 255) {
                return (byte) i4;
            }
            throw new NumberFormatException();
        } else {
            throw new NumberFormatException();
        }
    }

    private static int tryParseDecimal(String str, int i, int i2) {
        int i3 = 0;
        while (i < i2) {
            if (i3 > 214748364) {
                return -1;
            }
            int i4 = i3 * 10;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                return -1;
            }
            i3 = i4 + digit;
            i++;
        }
        return i3;
    }

    private static short parseHextet(String str, int i, int i2) {
        int i3 = i2 - i;
        if (i3 <= 0 || i3 > 4) {
            throw new NumberFormatException();
        }
        int i4 = 0;
        while (i < i2) {
            i4 = (i4 << 4) | Character.digit(str.charAt(i), 16);
            i++;
        }
        return (short) i4;
    }

    private static InetAddress bytesToInetAddress(byte[] bArr, String str) {
        try {
            InetAddress byAddress = InetAddress.getByAddress(bArr);
            if (str == null) {
                return byAddress;
            }
            Preconditions.checkArgument(byAddress instanceof Inet6Address, "Unexpected state, scope should only appear for ipv6");
            Inet6Address inet6Address = (Inet6Address) byAddress;
            int tryParseDecimal = tryParseDecimal(str, 0, str.length());
            if (tryParseDecimal != -1) {
                return Inet6Address.getByAddress(inet6Address.getHostAddress(), inet6Address.getAddress(), tryParseDecimal);
            }
            NetworkInterface byName = NetworkInterface.getByName(str);
            if (byName != null) {
                return Inet6Address.getByAddress(inet6Address.getHostAddress(), inet6Address.getAddress(), byName);
            }
            throw formatIllegalArgumentException("No such interface: '%s'", str);
        } catch (SocketException | UnknownHostException e) {
            throw new IllegalArgumentException("No such interface: " + str, e);
        } catch (UnknownHostException e2) {
            throw new AssertionError(e2);
        }
    }

    public static String toAddrString(InetAddress inetAddress) {
        Preconditions.checkNotNull(inetAddress);
        if (inetAddress instanceof Inet4Address) {
            return (String) Objects.requireNonNull(inetAddress.getHostAddress());
        }
        byte[] address = inetAddress.getAddress();
        int[] iArr = new int[8];
        for (int i = 0; i < 8; i++) {
            int i2 = i * 2;
            iArr[i] = Ints.fromBytes((byte) 0, (byte) 0, address[i2], address[i2 + 1]);
        }
        compressLongestRunOfZeroes(iArr);
        return hextetsToIPv6String(iArr) + scopeWithDelimiter((Inet6Address) inetAddress);
    }

    private static String scopeWithDelimiter(Inet6Address inet6Address) {
        NetworkInterface scopedInterface = inet6Address.getScopedInterface();
        if (scopedInterface != null) {
            return "%" + scopedInterface.getName();
        }
        int scopeId = inet6Address.getScopeId();
        return scopeId != 0 ? "%" + scopeId : "";
    }

    private static void compressLongestRunOfZeroes(int[] iArr) {
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < iArr.length + 1; i4++) {
            if (i4 >= iArr.length || iArr[i4] != 0) {
                if (i3 >= 0) {
                    int i5 = i4 - i3;
                    if (i5 > i) {
                        i2 = i3;
                        i = i5;
                    }
                    i3 = -1;
                }
            } else if (i3 < 0) {
                i3 = i4;
            }
        }
        if (i >= 2) {
            Arrays.fill(iArr, i2, i + i2, -1);
        }
    }

    private static String hextetsToIPv6String(int[] iArr) {
        StringBuilder sb = new StringBuilder(39);
        int i = 0;
        boolean z = false;
        while (i < iArr.length) {
            boolean z2 = iArr[i] >= 0;
            if (z2) {
                if (z) {
                    sb.append(':');
                }
                sb.append(Integer.toHexString(iArr[i]));
            } else if (i == 0 || z) {
                sb.append("::");
            }
            i++;
            z = z2;
        }
        return sb.toString();
    }

    public static String toUriString(InetAddress inetAddress) {
        if (inetAddress instanceof Inet6Address) {
            return "[" + toAddrString(inetAddress) + "]";
        }
        return toAddrString(inetAddress);
    }

    public static InetAddress forUriString(String str) {
        InetAddress forUriStringOrNull = forUriStringOrNull(str, true);
        if (forUriStringOrNull != null) {
            return forUriStringOrNull;
        }
        throw formatIllegalArgumentException("Not a valid URI IP literal: '%s'", str);
    }

    @CheckForNull
    private static InetAddress forUriStringOrNull(String str, boolean z) {
        int i;
        Preconditions.checkNotNull(str);
        if (!str.startsWith("[") || !str.endsWith("]")) {
            i = 4;
        } else {
            str = str.substring(1, str.length() - 1);
            i = 16;
        }
        String str2 = null;
        Scope scope = z ? new Scope() : null;
        byte[] ipStringToBytes = ipStringToBytes(str, scope);
        if (ipStringToBytes == null || ipStringToBytes.length != i) {
            return null;
        }
        if (scope != null) {
            str2 = scope.scope;
        }
        return bytesToInetAddress(ipStringToBytes, str2);
    }

    public static boolean isUriInetAddress(String str) {
        return forUriStringOrNull(str, false) != null;
    }

    public static boolean isCompatIPv4Address(Inet6Address inet6Address) {
        byte b;
        if (!inet6Address.isIPv4CompatibleAddress()) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        if (address[12] == 0 && address[13] == 0 && address[14] == 0 && ((b = address[15]) == 0 || b == 1)) {
            return false;
        }
        return true;
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isCompatIPv4Address(inet6Address), "Address '%s' is not IPv4-compatible.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static boolean is6to4Address(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        if (address[0] == 32 && address[1] == 2) {
            return true;
        }
        return false;
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(is6to4Address(inet6Address), "Address '%s' is not a 6to4 address.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 2, 6));
    }

    public static final class TeredoInfo {
        private final Inet4Address client;
        private final int flags;
        private final int port;
        private final Inet4Address server;

        public TeredoInfo(@CheckForNull Inet4Address inet4Address, @CheckForNull Inet4Address inet4Address2, int i, int i2) {
            boolean z = true;
            Preconditions.checkArgument(i >= 0 && i <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", i);
            Preconditions.checkArgument((i2 < 0 || i2 > 65535) ? false : z, "flags '%s' is out of range (0 <= flags <= 0xffff)", i2);
            this.server = (Inet4Address) MoreObjects.firstNonNull(inet4Address, InetAddresses.ANY4);
            this.client = (Inet4Address) MoreObjects.firstNonNull(inet4Address2, InetAddresses.ANY4);
            this.port = i;
            this.flags = i2;
        }

        public Inet4Address getServer() {
            return this.server;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getPort() {
            return this.port;
        }

        public int getFlags() {
            return this.flags;
        }
    }

    public static boolean isTeredoAddress(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        if (address[0] == 32 && address[1] == 1 && address[2] == 0 && address[3] == 0) {
            return true;
        }
        return false;
    }

    public static TeredoInfo getTeredoInfo(Inet6Address inet6Address) {
        Preconditions.checkArgument(isTeredoAddress(inet6Address), "Address '%s' is not a Teredo address.", (Object) toAddrString(inet6Address));
        byte[] address = inet6Address.getAddress();
        Inet4Address inet4Address = getInet4Address(Arrays.copyOfRange(address, 4, 8));
        short readShort = ByteStreams.newDataInput(address, 8).readShort() & UShort.MAX_VALUE;
        int i = 65535 & (~ByteStreams.newDataInput(address, 10).readShort());
        byte[] copyOfRange = Arrays.copyOfRange(address, 12, 16);
        for (int i2 = 0; i2 < copyOfRange.length; i2++) {
            copyOfRange[i2] = (byte) (~copyOfRange[i2]);
        }
        return new TeredoInfo(inet4Address, getInet4Address(copyOfRange), i, readShort);
    }

    public static boolean isIsatapAddress(Inet6Address inet6Address) {
        if (isTeredoAddress(inet6Address)) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        if ((address[8] | 3) == 3 && address[9] == 0 && address[10] == 94 && address[11] == -2) {
            return true;
        }
        return false;
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isIsatapAddress(inet6Address), "Address '%s' is not an ISATAP address.", (Object) toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        return isCompatIPv4Address(inet6Address) || is6to4Address(inet6Address) || isTeredoAddress(inet6Address);
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        if (isCompatIPv4Address(inet6Address)) {
            return getCompatIPv4Address(inet6Address);
        }
        if (is6to4Address(inet6Address)) {
            return get6to4IPv4Address(inet6Address);
        }
        if (isTeredoAddress(inet6Address)) {
            return getTeredoInfo(inet6Address).getClient();
        }
        throw formatIllegalArgumentException("'%s' has no embedded IPv4 address.", toAddrString(inet6Address));
    }

    public static boolean isMappedIPv4Address(String str) {
        byte[] ipStringToBytes = ipStringToBytes(str, (Scope) null);
        if (ipStringToBytes == null || ipStringToBytes.length != 16) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= 10) {
                for (int i2 = 10; i2 < 12; i2++) {
                    if (ipStringToBytes[i2] != -1) {
                        return false;
                    }
                }
                return true;
            } else if (ipStringToBytes[i] != 0) {
                return false;
            } else {
                i++;
            }
        }
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress inetAddress) {
        boolean z;
        long j;
        if (inetAddress instanceof Inet4Address) {
            return (Inet4Address) inetAddress;
        }
        byte[] address = inetAddress.getAddress();
        int i = 0;
        while (true) {
            if (i >= 15) {
                z = true;
                break;
            } else if (address[i] != 0) {
                z = false;
                break;
            } else {
                i++;
            }
        }
        if (z && address[15] == 1) {
            return LOOPBACK4;
        }
        if (z && address[15] == 0) {
            return ANY4;
        }
        Inet6Address inet6Address = (Inet6Address) inetAddress;
        if (hasEmbeddedIPv4ClientAddress(inet6Address)) {
            j = (long) getEmbeddedIPv4ClientAddress(inet6Address).hashCode();
        } else {
            j = ByteBuffer.wrap(inet6Address.getAddress(), 0, 8).getLong();
        }
        int asInt = Hashing.murmur3_32_fixed().hashLong(j).asInt() | -536870912;
        if (asInt == -1) {
            asInt = -2;
        }
        return getInet4Address(Ints.toByteArray(asInt));
    }

    public static int coerceToInteger(InetAddress inetAddress) {
        return ByteStreams.newDataInput(getCoercedIPv4Address(inetAddress).getAddress()).readInt();
    }

    public static BigInteger toBigInteger(InetAddress inetAddress) {
        return new BigInteger(1, inetAddress.getAddress());
    }

    public static Inet4Address fromInteger(int i) {
        return getInet4Address(Ints.toByteArray(i));
    }

    public static Inet4Address fromIPv4BigInteger(BigInteger bigInteger) {
        return (Inet4Address) fromBigInteger(bigInteger, false);
    }

    public static Inet6Address fromIPv6BigInteger(BigInteger bigInteger) {
        return (Inet6Address) fromBigInteger(bigInteger, true);
    }

    private static InetAddress fromBigInteger(BigInteger bigInteger, boolean z) {
        int i = 0;
        Preconditions.checkArgument(bigInteger.signum() >= 0, "BigInteger must be greater than or equal to 0");
        int i2 = z ? 16 : 4;
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[i2];
        int max = Math.max(0, byteArray.length - i2);
        int length = byteArray.length - max;
        int i3 = i2 - length;
        while (i < max) {
            if (byteArray[i] == 0) {
                i++;
            } else {
                throw formatIllegalArgumentException("BigInteger cannot be converted to InetAddress because it has more than %d bytes: %s", Integer.valueOf(i2), bigInteger);
            }
        }
        System.arraycopy(byteArray, max, bArr, i3, length);
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e) {
            throw new AssertionError(e);
        }
    }

    public static InetAddress fromLittleEndianByteArray(byte[] bArr) throws UnknownHostException {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[(bArr.length - i) - 1];
        }
        return InetAddress.getByAddress(bArr2);
    }

    public static InetAddress decrement(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (length >= 0 && address[length] == 0) {
            address[length] = -1;
            length--;
        }
        Preconditions.checkArgument(length >= 0, "Decrementing %s would wrap.", (Object) inetAddress);
        address[length] = (byte) (address[length] - 1);
        return bytesToInetAddress(address, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.InetAddress increment(java.net.InetAddress r6) {
        /*
            byte[] r0 = r6.getAddress()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
        L_0x0007:
            r3 = 0
            if (r1 < 0) goto L_0x0014
            byte r4 = r0[r1]
            r5 = -1
            if (r4 != r5) goto L_0x0014
            r0[r1] = r3
            int r1 = r1 + -1
            goto L_0x0007
        L_0x0014:
            if (r1 < 0) goto L_0x0017
            r3 = r2
        L_0x0017:
            java.lang.String r4 = "Incrementing %s would wrap."
            com.google.common.base.Preconditions.checkArgument((boolean) r3, (java.lang.String) r4, (java.lang.Object) r6)
            byte r6 = r0[r1]
            int r6 = r6 + r2
            byte r6 = (byte) r6
            r0[r1] = r6
            r6 = 0
            java.net.InetAddress r6 = bytesToInetAddress(r0, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.InetAddresses.increment(java.net.InetAddress):java.net.InetAddress");
    }

    public static boolean isMaximum(InetAddress inetAddress) {
        for (byte b : inetAddress.getAddress()) {
            if (b != -1) {
                return false;
            }
        }
        return true;
    }

    private static IllegalArgumentException formatIllegalArgumentException(String str, Object... objArr) {
        return new IllegalArgumentException(String.format(Locale.ROOT, str, objArr));
    }
}
