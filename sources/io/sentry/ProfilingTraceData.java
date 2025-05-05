package io.sentry;

import com.amplitude.api.DeviceInfo;
import io.sentry.ProfilingTransactionData;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public final class ProfilingTraceData implements JsonUnknown, JsonSerializable {
    private static final String DEFAULT_ENVIRONMENT = "production";
    public static final String TRUNCATION_REASON_BACKGROUNDED = "backgrounded";
    public static final String TRUNCATION_REASON_NORMAL = "normal";
    public static final String TRUNCATION_REASON_TIMEOUT = "timeout";
    /* access modifiers changed from: private */
    public int androidApiLevel;
    /* access modifiers changed from: private */
    public String buildId;
    /* access modifiers changed from: private */
    public String cpuArchitecture;
    /* access modifiers changed from: private */
    public List<Integer> deviceCpuFrequencies;
    private final Callable<List<Integer>> deviceCpuFrequenciesReader;
    /* access modifiers changed from: private */
    public boolean deviceIsEmulator;
    /* access modifiers changed from: private */
    public String deviceLocale;
    /* access modifiers changed from: private */
    public String deviceManufacturer;
    /* access modifiers changed from: private */
    public String deviceModel;
    /* access modifiers changed from: private */
    public String deviceOsBuildNumber;
    /* access modifiers changed from: private */
    public String deviceOsName;
    /* access modifiers changed from: private */
    public String deviceOsVersion;
    /* access modifiers changed from: private */
    public String devicePhysicalMemoryBytes;
    /* access modifiers changed from: private */
    public String durationNs;
    /* access modifiers changed from: private */
    public String environment;
    /* access modifiers changed from: private */
    public final Map<String, ProfileMeasurement> measurementsMap;
    /* access modifiers changed from: private */
    public String platform;
    /* access modifiers changed from: private */
    public String profileId;
    /* access modifiers changed from: private */
    public String release;
    /* access modifiers changed from: private */
    public String sampledProfile;
    private final File traceFile;
    /* access modifiers changed from: private */
    public String traceId;
    /* access modifiers changed from: private */
    public String transactionId;
    /* access modifiers changed from: private */
    public String transactionName;
    /* access modifiers changed from: private */
    public List<ProfilingTransactionData> transactions;
    /* access modifiers changed from: private */
    public String truncationReason;
    private Map<String, Object> unknown;
    /* access modifiers changed from: private */
    public String versionCode;

    public static final class JsonKeys {
        public static final String ANDROID_API_LEVEL = "android_api_level";
        public static final String ARCHITECTURE = "architecture";
        public static final String BUILD_ID = "build_id";
        public static final String DEVICE_CPU_FREQUENCIES = "device_cpu_frequencies";
        public static final String DEVICE_IS_EMULATOR = "device_is_emulator";
        public static final String DEVICE_LOCALE = "device_locale";
        public static final String DEVICE_MANUFACTURER = "device_manufacturer";
        public static final String DEVICE_MODEL = "device_model";
        public static final String DEVICE_OS_BUILD_NUMBER = "device_os_build_number";
        public static final String DEVICE_OS_NAME = "device_os_name";
        public static final String DEVICE_OS_VERSION = "device_os_version";
        public static final String DEVICE_PHYSICAL_MEMORY_BYTES = "device_physical_memory_bytes";
        public static final String DURATION_NS = "duration_ns";
        public static final String ENVIRONMENT = "environment";
        public static final String MEASUREMENTS = "measurements";
        public static final String PLATFORM = "platform";
        public static final String PROFILE_ID = "profile_id";
        public static final String RELEASE = "version_name";
        public static final String SAMPLED_PROFILE = "sampled_profile";
        public static final String TRACE_ID = "trace_id";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String TRANSACTION_LIST = "transactions";
        public static final String TRANSACTION_NAME = "transaction_name";
        public static final String TRUNCATION_REASON = "truncation_reason";
        public static final String VERSION_CODE = "version_code";
    }

    private ProfilingTraceData() {
        this(new File("dummy"), NoOpTransaction.getInstance());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProfilingTraceData(java.io.File r20, io.sentry.ITransaction r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r3 = r21
            java.util.ArrayList r4 = new java.util.ArrayList
            r2 = r4
            r4.<init>()
            java.lang.String r4 = "0"
            r5 = 0
            java.lang.String r6 = ""
            io.sentry.ProfilingTraceData$$ExternalSyntheticLambda0 r8 = new io.sentry.ProfilingTraceData$$ExternalSyntheticLambda0
            r7 = r8
            r8.<init>()
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            java.lang.String r16 = "normal"
            java.util.HashMap r18 = new java.util.HashMap
            r17 = r18
            r18.<init>()
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.ProfilingTraceData.<init>(java.io.File, io.sentry.ITransaction):void");
    }

    static /* synthetic */ List lambda$new$0() throws Exception {
        return new ArrayList();
    }

    public ProfilingTraceData(File file, List<ProfilingTransactionData> list, ITransaction iTransaction, String str, int i, String str2, Callable<List<Integer>> callable, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, String str9, String str10, Map<String, ProfileMeasurement> map) {
        this.deviceCpuFrequencies = new ArrayList();
        this.sampledProfile = null;
        this.traceFile = file;
        this.cpuArchitecture = str2;
        this.deviceCpuFrequenciesReader = callable;
        this.androidApiLevel = i;
        this.deviceLocale = Locale.getDefault().toString();
        String str11 = "";
        this.deviceManufacturer = str3 != null ? str3 : str11;
        this.deviceModel = str4 != null ? str4 : str11;
        this.deviceOsVersion = str5 != null ? str5 : str11;
        this.deviceIsEmulator = bool != null ? bool.booleanValue() : false;
        this.devicePhysicalMemoryBytes = str6 != null ? str6 : "0";
        this.deviceOsBuildNumber = str11;
        this.deviceOsName = DeviceInfo.OS_NAME;
        this.platform = DeviceInfo.OS_NAME;
        this.buildId = str7 != null ? str7 : str11;
        this.transactions = list;
        this.transactionName = iTransaction.getName();
        this.durationNs = str;
        this.versionCode = str11;
        this.release = str8 != null ? str8 : str11;
        this.transactionId = iTransaction.getEventId().toString();
        this.traceId = iTransaction.getSpanContext().getTraceId().toString();
        this.profileId = UUID.randomUUID().toString();
        this.environment = str9 != null ? str9 : "production";
        this.truncationReason = str10;
        if (!isTruncationReasonValid()) {
            this.truncationReason = TRUNCATION_REASON_NORMAL;
        }
        this.measurementsMap = map;
    }

    private boolean isTruncationReasonValid() {
        return this.truncationReason.equals(TRUNCATION_REASON_NORMAL) || this.truncationReason.equals(TRUNCATION_REASON_TIMEOUT) || this.truncationReason.equals(TRUNCATION_REASON_BACKGROUNDED);
    }

    public File getTraceFile() {
        return this.traceFile;
    }

    public int getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    public String getCpuArchitecture() {
        return this.cpuArchitecture;
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDeviceOsBuildNumber() {
        return this.deviceOsBuildNumber;
    }

    public String getDeviceOsName() {
        return this.deviceOsName;
    }

    public String getDeviceOsVersion() {
        return this.deviceOsVersion;
    }

    public boolean isDeviceIsEmulator() {
        return this.deviceIsEmulator;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getBuildId() {
        return this.buildId;
    }

    public String getTransactionName() {
        return this.transactionName;
    }

    public String getRelease() {
        return this.release;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public List<ProfilingTransactionData> getTransactions() {
        return this.transactions;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getSampledProfile() {
        return this.sampledProfile;
    }

    public String getDurationNs() {
        return this.durationNs;
    }

    public List<Integer> getDeviceCpuFrequencies() {
        return this.deviceCpuFrequencies;
    }

    public String getDevicePhysicalMemoryBytes() {
        return this.devicePhysicalMemoryBytes;
    }

    public String getTruncationReason() {
        return this.truncationReason;
    }

    public Map<String, ProfileMeasurement> getMeasurementsMap() {
        return this.measurementsMap;
    }

    public void setAndroidApiLevel(int i) {
        this.androidApiLevel = i;
    }

    public void setCpuArchitecture(String str) {
        this.cpuArchitecture = str;
    }

    public void setDeviceLocale(String str) {
        this.deviceLocale = str;
    }

    public void setDeviceManufacturer(String str) {
        this.deviceManufacturer = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceOsBuildNumber(String str) {
        this.deviceOsBuildNumber = str;
    }

    public void setDeviceOsVersion(String str) {
        this.deviceOsVersion = str;
    }

    public void setDeviceIsEmulator(boolean z) {
        this.deviceIsEmulator = z;
    }

    public void setDeviceCpuFrequencies(List<Integer> list) {
        this.deviceCpuFrequencies = list;
    }

    public void setDevicePhysicalMemoryBytes(String str) {
        this.devicePhysicalMemoryBytes = str;
    }

    public void setTruncationReason(String str) {
        this.truncationReason = str;
    }

    public void setTransactions(List<ProfilingTransactionData> list) {
        this.transactions = list;
    }

    public void setBuildId(String str) {
        this.buildId = str;
    }

    public void setTransactionName(String str) {
        this.transactionName = str;
    }

    public void setDurationNs(String str) {
        this.durationNs = str;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public void setTransactionId(String str) {
        this.transactionId = str;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setSampledProfile(String str) {
        this.sampledProfile = str;
    }

    public void readDeviceCpuFrequencies() {
        try {
            this.deviceCpuFrequencies = this.deviceCpuFrequenciesReader.call();
        } catch (Throwable unused) {
        }
    }

    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.ANDROID_API_LEVEL).value(iLogger, Integer.valueOf(this.androidApiLevel));
        objectWriter.name(JsonKeys.DEVICE_LOCALE).value(iLogger, this.deviceLocale);
        objectWriter.name("device_manufacturer").value(this.deviceManufacturer);
        objectWriter.name("device_model").value(this.deviceModel);
        objectWriter.name(JsonKeys.DEVICE_OS_BUILD_NUMBER).value(this.deviceOsBuildNumber);
        objectWriter.name(JsonKeys.DEVICE_OS_NAME).value(this.deviceOsName);
        objectWriter.name(JsonKeys.DEVICE_OS_VERSION).value(this.deviceOsVersion);
        objectWriter.name(JsonKeys.DEVICE_IS_EMULATOR).value(this.deviceIsEmulator);
        objectWriter.name(JsonKeys.ARCHITECTURE).value(iLogger, this.cpuArchitecture);
        objectWriter.name(JsonKeys.DEVICE_CPU_FREQUENCIES).value(iLogger, this.deviceCpuFrequencies);
        objectWriter.name(JsonKeys.DEVICE_PHYSICAL_MEMORY_BYTES).value(this.devicePhysicalMemoryBytes);
        objectWriter.name("platform").value(this.platform);
        objectWriter.name(JsonKeys.BUILD_ID).value(this.buildId);
        objectWriter.name(JsonKeys.TRANSACTION_NAME).value(this.transactionName);
        objectWriter.name(JsonKeys.DURATION_NS).value(this.durationNs);
        objectWriter.name("version_name").value(this.release);
        objectWriter.name(JsonKeys.VERSION_CODE).value(this.versionCode);
        if (!this.transactions.isEmpty()) {
            objectWriter.name(JsonKeys.TRANSACTION_LIST).value(iLogger, this.transactions);
        }
        objectWriter.name("transaction_id").value(this.transactionId);
        objectWriter.name("trace_id").value(this.traceId);
        objectWriter.name(JsonKeys.PROFILE_ID).value(this.profileId);
        objectWriter.name("environment").value(this.environment);
        objectWriter.name(JsonKeys.TRUNCATION_REASON).value(this.truncationReason);
        if (this.sampledProfile != null) {
            objectWriter.name(JsonKeys.SAMPLED_PROFILE).value(this.sampledProfile);
        }
        objectWriter.name("measurements").value(iLogger, this.measurementsMap);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String next : map.keySet()) {
                Object obj = this.unknown.get(next);
                objectWriter.name(next);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<ProfilingTraceData> {
        public ProfilingTraceData deserialize(JsonObjectReader jsonObjectReader, ILogger iLogger) throws Exception {
            jsonObjectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            ProfilingTraceData profilingTraceData = new ProfilingTraceData();
            while (jsonObjectReader.peek() == JsonToken.NAME) {
                String nextName = jsonObjectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -2133529830:
                        if (nextName.equals("device_manufacturer")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1981468849:
                        if (nextName.equals(JsonKeys.ANDROID_API_LEVEL)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1430655860:
                        if (nextName.equals(JsonKeys.BUILD_ID)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1172160413:
                        if (nextName.equals(JsonKeys.DEVICE_LOCALE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1102636175:
                        if (nextName.equals(JsonKeys.PROFILE_ID)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -716656436:
                        if (nextName.equals(JsonKeys.DEVICE_OS_BUILD_NUMBER)) {
                            c = 5;
                            break;
                        }
                        break;
                    case -591076352:
                        if (nextName.equals("device_model")) {
                            c = 6;
                            break;
                        }
                        break;
                    case -512511455:
                        if (nextName.equals(JsonKeys.DEVICE_IS_EMULATOR)) {
                            c = 7;
                            break;
                        }
                        break;
                    case -478065584:
                        if (nextName.equals(JsonKeys.DURATION_NS)) {
                            c = 8;
                            break;
                        }
                        break;
                    case -362243017:
                        if (nextName.equals("measurements")) {
                            c = 9;
                            break;
                        }
                        break;
                    case -332426004:
                        if (nextName.equals(JsonKeys.DEVICE_PHYSICAL_MEMORY_BYTES)) {
                            c = 10;
                            break;
                        }
                        break;
                    case -212264198:
                        if (nextName.equals(JsonKeys.DEVICE_CPU_FREQUENCIES)) {
                            c = 11;
                            break;
                        }
                        break;
                    case -102985484:
                        if (nextName.equals(JsonKeys.VERSION_CODE)) {
                            c = 12;
                            break;
                        }
                        break;
                    case -102670958:
                        if (nextName.equals("version_name")) {
                            c = 13;
                            break;
                        }
                        break;
                    case -85904877:
                        if (nextName.equals("environment")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 508853068:
                        if (nextName.equals(JsonKeys.TRANSACTION_NAME)) {
                            c = 15;
                            break;
                        }
                        break;
                    case 796476189:
                        if (nextName.equals(JsonKeys.DEVICE_OS_NAME)) {
                            c = 16;
                            break;
                        }
                        break;
                    case 839674195:
                        if (nextName.equals(JsonKeys.ARCHITECTURE)) {
                            c = 17;
                            break;
                        }
                        break;
                    case 1010584092:
                        if (nextName.equals("transaction_id")) {
                            c = 18;
                            break;
                        }
                        break;
                    case 1052553990:
                        if (nextName.equals(JsonKeys.DEVICE_OS_VERSION)) {
                            c = 19;
                            break;
                        }
                        break;
                    case 1163928186:
                        if (nextName.equals(JsonKeys.TRUNCATION_REASON)) {
                            c = 20;
                            break;
                        }
                        break;
                    case 1270300245:
                        if (nextName.equals("trace_id")) {
                            c = 21;
                            break;
                        }
                        break;
                    case 1874684019:
                        if (nextName.equals("platform")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 1953158756:
                        if (nextName.equals(JsonKeys.SAMPLED_PROFILE)) {
                            c = 23;
                            break;
                        }
                        break;
                    case 1954122069:
                        if (nextName.equals(JsonKeys.TRANSACTION_LIST)) {
                            c = 24;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        String nextStringOrNull = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull == null) {
                            break;
                        } else {
                            String unused = profilingTraceData.deviceManufacturer = nextStringOrNull;
                            break;
                        }
                    case 1:
                        Integer nextIntegerOrNull = jsonObjectReader.nextIntegerOrNull();
                        if (nextIntegerOrNull == null) {
                            break;
                        } else {
                            int unused2 = profilingTraceData.androidApiLevel = nextIntegerOrNull.intValue();
                            break;
                        }
                    case 2:
                        String nextStringOrNull2 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull2 == null) {
                            break;
                        } else {
                            String unused3 = profilingTraceData.buildId = nextStringOrNull2;
                            break;
                        }
                    case 3:
                        String nextStringOrNull3 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull3 == null) {
                            break;
                        } else {
                            String unused4 = profilingTraceData.deviceLocale = nextStringOrNull3;
                            break;
                        }
                    case 4:
                        String nextStringOrNull4 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull4 == null) {
                            break;
                        } else {
                            String unused5 = profilingTraceData.profileId = nextStringOrNull4;
                            break;
                        }
                    case 5:
                        String nextStringOrNull5 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull5 == null) {
                            break;
                        } else {
                            String unused6 = profilingTraceData.deviceOsBuildNumber = nextStringOrNull5;
                            break;
                        }
                    case 6:
                        String nextStringOrNull6 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull6 == null) {
                            break;
                        } else {
                            String unused7 = profilingTraceData.deviceModel = nextStringOrNull6;
                            break;
                        }
                    case 7:
                        Boolean nextBooleanOrNull = jsonObjectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull == null) {
                            break;
                        } else {
                            boolean unused8 = profilingTraceData.deviceIsEmulator = nextBooleanOrNull.booleanValue();
                            break;
                        }
                    case 8:
                        String nextStringOrNull7 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull7 == null) {
                            break;
                        } else {
                            String unused9 = profilingTraceData.durationNs = nextStringOrNull7;
                            break;
                        }
                    case 9:
                        Map nextMapOrNull = jsonObjectReader.nextMapOrNull(iLogger, new ProfileMeasurement.Deserializer());
                        if (nextMapOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.measurementsMap.putAll(nextMapOrNull);
                            break;
                        }
                    case 10:
                        String nextStringOrNull8 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull8 == null) {
                            break;
                        } else {
                            String unused10 = profilingTraceData.devicePhysicalMemoryBytes = nextStringOrNull8;
                            break;
                        }
                    case 11:
                        List list = (List) jsonObjectReader.nextObjectOrNull();
                        if (list == null) {
                            break;
                        } else {
                            List unused11 = profilingTraceData.deviceCpuFrequencies = list;
                            break;
                        }
                    case 12:
                        String nextStringOrNull9 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull9 == null) {
                            break;
                        } else {
                            String unused12 = profilingTraceData.versionCode = nextStringOrNull9;
                            break;
                        }
                    case 13:
                        String nextStringOrNull10 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull10 == null) {
                            break;
                        } else {
                            String unused13 = profilingTraceData.release = nextStringOrNull10;
                            break;
                        }
                    case 14:
                        String nextStringOrNull11 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull11 == null) {
                            break;
                        } else {
                            String unused14 = profilingTraceData.environment = nextStringOrNull11;
                            break;
                        }
                    case 15:
                        String nextStringOrNull12 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull12 == null) {
                            break;
                        } else {
                            String unused15 = profilingTraceData.transactionName = nextStringOrNull12;
                            break;
                        }
                    case 16:
                        String nextStringOrNull13 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull13 == null) {
                            break;
                        } else {
                            String unused16 = profilingTraceData.deviceOsName = nextStringOrNull13;
                            break;
                        }
                    case 17:
                        String nextStringOrNull14 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull14 == null) {
                            break;
                        } else {
                            String unused17 = profilingTraceData.cpuArchitecture = nextStringOrNull14;
                            break;
                        }
                    case 18:
                        String nextStringOrNull15 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull15 == null) {
                            break;
                        } else {
                            String unused18 = profilingTraceData.transactionId = nextStringOrNull15;
                            break;
                        }
                    case 19:
                        String nextStringOrNull16 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull16 == null) {
                            break;
                        } else {
                            String unused19 = profilingTraceData.deviceOsVersion = nextStringOrNull16;
                            break;
                        }
                    case 20:
                        String nextStringOrNull17 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull17 == null) {
                            break;
                        } else {
                            String unused20 = profilingTraceData.truncationReason = nextStringOrNull17;
                            break;
                        }
                    case 21:
                        String nextStringOrNull18 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull18 == null) {
                            break;
                        } else {
                            String unused21 = profilingTraceData.traceId = nextStringOrNull18;
                            break;
                        }
                    case 22:
                        String nextStringOrNull19 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull19 == null) {
                            break;
                        } else {
                            String unused22 = profilingTraceData.platform = nextStringOrNull19;
                            break;
                        }
                    case 23:
                        String nextStringOrNull20 = jsonObjectReader.nextStringOrNull();
                        if (nextStringOrNull20 == null) {
                            break;
                        } else {
                            String unused23 = profilingTraceData.sampledProfile = nextStringOrNull20;
                            break;
                        }
                    case 24:
                        List nextListOrNull = jsonObjectReader.nextListOrNull(iLogger, new ProfilingTransactionData.Deserializer());
                        if (nextListOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.transactions.addAll(nextListOrNull);
                            break;
                        }
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        jsonObjectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            profilingTraceData.setUnknown(concurrentHashMap);
            jsonObjectReader.endObject();
            return profilingTraceData;
        }
    }
}
