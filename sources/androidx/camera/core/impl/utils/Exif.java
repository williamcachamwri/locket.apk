package androidx.camera.core.impl.utils;

import android.location.Location;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.exifinterface.media.ExifInterface;
import com.canhub.cropper.CropImageOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public final class Exif {
    private static final List<String> ALL_EXIF_TAGS = getAllExifTags();
    private static final ThreadLocal<SimpleDateFormat> DATETIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        }
    };
    private static final List<String> DO_NOT_COPY_EXIF_TAGS = Arrays.asList(new String[]{ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_PIXEL_X_DIMENSION, ExifInterface.TAG_PIXEL_Y_DIMENSION, ExifInterface.TAG_COMPRESSION, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH, ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH, "ThumbnailOrientation"});
    public static final long INVALID_TIMESTAMP = -1;
    private static final String KILOMETERS_PER_HOUR = "K";
    private static final String KNOTS = "N";
    private static final String MILES_PER_HOUR = "M";
    private static final String TAG = "Exif";
    public static final String TAG_THUMBNAIL_ORIENTATION = "ThumbnailOrientation";
    private static final ThreadLocal<SimpleDateFormat> TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.US);
        }
    };
    private final ExifInterface mExifInterface;
    private boolean mRemoveTimestamp = false;

    private Exif(ExifInterface exifInterface) {
        this.mExifInterface = exifInterface;
    }

    public static Exif createFromFile(File file) throws IOException {
        return createFromFileString(file.toString());
    }

    public static Exif createFromImageProxy(ImageProxy imageProxy) throws IOException {
        ByteBuffer buffer = imageProxy.getPlanes()[0].getBuffer();
        buffer.rewind();
        byte[] bArr = new byte[buffer.capacity()];
        buffer.get(bArr);
        return createFromInputStream(new ByteArrayInputStream(bArr));
    }

    public static Exif createFromFileString(String str) throws IOException {
        return new Exif(new ExifInterface(str));
    }

    public static Exif createFromInputStream(InputStream inputStream) throws IOException {
        return new Exif(new ExifInterface(inputStream));
    }

    private static String convertToExifDateTime(long j) {
        return DATETIME_FORMAT.get().format(new Date(j));
    }

    private static Date convertFromExifDateTime(String str) throws ParseException {
        return DATETIME_FORMAT.get().parse(str);
    }

    private static Date convertFromExifDate(String str) throws ParseException {
        return DATE_FORMAT.get().parse(str);
    }

    private static Date convertFromExifTime(String str) throws ParseException {
        return TIME_FORMAT.get().parse(str);
    }

    public void save() throws IOException {
        if (!this.mRemoveTimestamp) {
            attachLastModifiedTimestamp();
        }
        this.mExifInterface.saveAttributes();
    }

    public void copyToCroppedImage(Exif exif) {
        ArrayList<String> arrayList = new ArrayList<>(ALL_EXIF_TAGS);
        arrayList.removeAll(DO_NOT_COPY_EXIF_TAGS);
        for (String str : arrayList) {
            String attribute = this.mExifInterface.getAttribute(str);
            String attribute2 = exif.mExifInterface.getAttribute(str);
            if (attribute != null && !Objects.equals(attribute, attribute2)) {
                exif.mExifInterface.setAttribute(str, attribute);
            }
        }
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "Exif{width=%s, height=%s, rotation=%d, isFlippedVertically=%s, isFlippedHorizontally=%s, location=%s, timestamp=%s, description=%s}", new Object[]{Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(getRotation()), Boolean.valueOf(isFlippedVertically()), Boolean.valueOf(isFlippedHorizontally()), getLocation(), Long.valueOf(getTimestamp()), getDescription()});
    }

    public int getOrientation() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
    }

    public void setOrientation(int i) {
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public int getWidth() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
    }

    public int getHeight() {
        return this.mExifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
    }

    public String getDescription() {
        return this.mExifInterface.getAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION);
    }

    public void setDescription(String str) {
        this.mExifInterface.setAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION, str);
    }

    public int getRotation() {
        switch (getOrientation()) {
            case 3:
            case 4:
                return RotationOptions.ROTATE_180;
            case 5:
                return 270;
            case 6:
            case 7:
                return 90;
            case 8:
                return 270;
            default:
                return 0;
        }
    }

    public boolean isFlippedVertically() {
        int orientation = getOrientation();
        return orientation == 4 || orientation == 5 || orientation == 7;
    }

    public boolean isFlippedHorizontally() {
        return getOrientation() == 2;
    }

    private void attachLastModifiedTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        String convertToExifDateTime = convertToExifDateTime(currentTimeMillis);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME, convertToExifDateTime);
        try {
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME, Long.toString(currentTimeMillis - convertFromExifDateTime(convertToExifDateTime).getTime()));
        } catch (ParseException unused) {
        }
    }

    public long getLastModifiedTimestamp() {
        long parseTimestamp = parseTimestamp(this.mExifInterface.getAttribute(ExifInterface.TAG_DATETIME));
        if (parseTimestamp == -1) {
            return -1;
        }
        String attribute = this.mExifInterface.getAttribute(ExifInterface.TAG_SUBSEC_TIME);
        if (attribute == null) {
            return parseTimestamp;
        }
        try {
            long parseLong = Long.parseLong(attribute);
            while (parseLong > 1000) {
                parseLong /= 10;
            }
            return parseTimestamp + parseLong;
        } catch (NumberFormatException unused) {
            return parseTimestamp;
        }
    }

    public long getTimestamp() {
        long parseTimestamp = parseTimestamp(this.mExifInterface.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL));
        if (parseTimestamp == -1) {
            return -1;
        }
        String attribute = this.mExifInterface.getAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL);
        if (attribute == null) {
            return parseTimestamp;
        }
        try {
            long parseLong = Long.parseLong(attribute);
            while (parseLong > 1000) {
                parseLong /= 10;
            }
            return parseTimestamp + parseLong;
        } catch (NumberFormatException unused) {
            return parseTimestamp;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.location.Location getLocation() {
        /*
            r16 = this;
            r0 = r16
            androidx.exifinterface.media.ExifInterface r1 = r0.mExifInterface
            java.lang.String r2 = "GPSProcessingMethod"
            java.lang.String r1 = r1.getAttribute(r2)
            androidx.exifinterface.media.ExifInterface r2 = r0.mExifInterface
            double[] r2 = r2.getLatLong()
            androidx.exifinterface.media.ExifInterface r3 = r0.mExifInterface
            r4 = 0
            double r6 = r3.getAltitude(r4)
            androidx.exifinterface.media.ExifInterface r3 = r0.mExifInterface
            java.lang.String r8 = "GPSSpeed"
            double r8 = r3.getAttributeDouble(r8, r4)
            androidx.exifinterface.media.ExifInterface r3 = r0.mExifInterface
            java.lang.String r10 = "GPSSpeedRef"
            java.lang.String r3 = r3.getAttribute(r10)
            java.lang.String r10 = "K"
            if (r3 != 0) goto L_0x002d
            r3 = r10
        L_0x002d:
            androidx.exifinterface.media.ExifInterface r11 = r0.mExifInterface
            java.lang.String r12 = "GPSDateStamp"
            java.lang.String r11 = r11.getAttribute(r12)
            androidx.exifinterface.media.ExifInterface r12 = r0.mExifInterface
            java.lang.String r13 = "GPSTimeStamp"
            java.lang.String r12 = r12.getAttribute(r13)
            long r11 = r0.parseTimestamp(r11, r12)
            if (r2 != 0) goto L_0x0045
            r1 = 0
            return r1
        L_0x0045:
            if (r1 != 0) goto L_0x0049
            java.lang.String r1 = TAG
        L_0x0049:
            android.location.Location r13 = new android.location.Location
            r13.<init>(r1)
            r1 = 0
            r14 = r2[r1]
            r13.setLatitude(r14)
            r14 = 1
            r1 = r2[r14]
            r13.setLongitude(r1)
            int r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0061
            r13.setAltitude(r6)
        L_0x0061:
            int r1 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x00b5
            int r1 = r3.hashCode()
            r2 = 75
            if (r1 == r2) goto L_0x008a
            r2 = 77
            if (r1 == r2) goto L_0x0080
            r2 = 78
            if (r1 == r2) goto L_0x0076
            goto L_0x0092
        L_0x0076:
            java.lang.String r1 = "N"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0092
            r1 = r14
            goto L_0x0093
        L_0x0080:
            java.lang.String r1 = "M"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0092
            r1 = 0
            goto L_0x0093
        L_0x008a:
            boolean r1 = r3.equals(r10)
            if (r1 == 0) goto L_0x0092
            r1 = 2
            goto L_0x0093
        L_0x0092:
            r1 = -1
        L_0x0093:
            if (r1 == 0) goto L_0x00a9
            if (r1 == r14) goto L_0x00a0
            androidx.camera.core.impl.utils.Exif$Speed$Converter r1 = androidx.camera.core.impl.utils.Exif.Speed.fromKilometersPerHour(r8)
            double r1 = r1.toMetersPerSecond()
            goto L_0x00b1
        L_0x00a0:
            androidx.camera.core.impl.utils.Exif$Speed$Converter r1 = androidx.camera.core.impl.utils.Exif.Speed.fromKnots(r8)
            double r1 = r1.toMetersPerSecond()
            goto L_0x00b1
        L_0x00a9:
            androidx.camera.core.impl.utils.Exif$Speed$Converter r1 = androidx.camera.core.impl.utils.Exif.Speed.fromMilesPerHour(r8)
            double r1 = r1.toMetersPerSecond()
        L_0x00b1:
            float r1 = (float) r1
            r13.setSpeed(r1)
        L_0x00b5:
            r1 = -1
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x00be
            r13.setTime(r11)
        L_0x00be:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.Exif.getLocation():android.location.Location");
    }

    public void rotate(int i) {
        int i2;
        if (i % 90 != 0) {
            Logger.w(TAG, String.format(Locale.US, "Can only rotate in right angles (eg. 0, 90, 180, 270). %d is unsupported.", new Object[]{Integer.valueOf(i)}));
            this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(0));
            return;
        }
        int i3 = i % CropImageOptions.DEGREES_360;
        int orientation = getOrientation();
        while (i3 < 0) {
            i3 += 90;
            switch (orientation) {
                case 2:
                    orientation = 5;
                    break;
                case 3:
                case 8:
                    orientation = 6;
                    break;
                case 4:
                    orientation = 7;
                    break;
                case 5:
                    orientation = 4;
                    break;
                case 6:
                    orientation = 1;
                    break;
                case 7:
                    orientation = 2;
                    break;
                default:
                    orientation = 8;
                    break;
            }
        }
        while (i3 > 0) {
            i3 -= 90;
            switch (orientation) {
                case 2:
                    i2 = 7;
                    break;
                case 3:
                    i2 = 8;
                    break;
                case 4:
                    i2 = 5;
                    break;
                case 5:
                    i2 = 2;
                    break;
                case 6:
                    i2 = 3;
                    break;
                case 7:
                    i2 = 4;
                    break;
                case 8:
                    i2 = 1;
                    break;
                default:
                    i2 = 6;
                    break;
            }
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(orientation));
    }

    public void flipVertically() {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 3;
                break;
            case 3:
                i = 2;
                break;
            case 4:
                i = 1;
                break;
            case 5:
                i = 8;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 6;
                break;
            case 8:
                i = 5;
                break;
            default:
                i = 4;
                break;
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public void flipHorizontally() {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 1;
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 3;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 5;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 7;
                break;
            default:
                i = 2;
                break;
        }
        this.mExifInterface.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public String getMetadata() {
        return this.mExifInterface.getAttribute(ExifInterface.TAG_XMP);
    }

    public ExifInterface getExifInterface() {
        return this.mExifInterface;
    }

    public void attachTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        String convertToExifDateTime = convertToExifDateTime(currentTimeMillis);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, convertToExifDateTime);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, convertToExifDateTime);
        try {
            String l = Long.toString(currentTimeMillis - convertFromExifDateTime(convertToExifDateTime).getTime());
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, l);
            this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, l);
        } catch (ParseException unused) {
        }
        this.mRemoveTimestamp = false;
    }

    public void removeTimestamp() {
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, (String) null);
        this.mRemoveTimestamp = true;
    }

    public void attachLocation(Location location) {
        this.mExifInterface.setGpsInfo(location);
    }

    public void removeLocation() {
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_SPEED, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_SPEED_REF, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_DATESTAMP, (String) null);
        this.mExifInterface.setAttribute(ExifInterface.TAG_GPS_TIMESTAMP, (String) null);
    }

    private long parseTimestamp(String str, String str2) {
        if (str == null && str2 == null) {
            return -1;
        }
        if (str2 == null) {
            try {
                return convertFromExifDate(str).getTime();
            } catch (ParseException unused) {
                return -1;
            }
        } else if (str != null) {
            return parseTimestamp(str + " " + str2);
        } else {
            try {
                return convertFromExifTime(str2).getTime();
            } catch (ParseException unused2) {
                return -1;
            }
        }
    }

    private long parseTimestamp(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return convertFromExifDateTime(str).getTime();
        } catch (ParseException unused) {
            return -1;
        }
    }

    private static final class Speed {
        private Speed() {
        }

        static Converter fromKilometersPerHour(double d) {
            return new Converter(d * 0.621371d);
        }

        static Converter fromMilesPerHour(double d) {
            return new Converter(d);
        }

        static Converter fromKnots(double d) {
            return new Converter(d * 1.15078d);
        }

        static final class Converter {
            final double mMph;

            Converter(double d) {
                this.mMph = d;
            }

            /* access modifiers changed from: package-private */
            public double toMetersPerSecond() {
                return this.mMph / 2.23694d;
            }
        }
    }

    public static List<String> getAllExifTags() {
        return Arrays.asList(new String[]{ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.TAG_BITS_PER_SAMPLE, ExifInterface.TAG_COMPRESSION, ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION, ExifInterface.TAG_ORIENTATION, ExifInterface.TAG_SAMPLES_PER_PIXEL, ExifInterface.TAG_PLANAR_CONFIGURATION, ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING, ExifInterface.TAG_Y_CB_CR_POSITIONING, ExifInterface.TAG_X_RESOLUTION, ExifInterface.TAG_Y_RESOLUTION, ExifInterface.TAG_RESOLUTION_UNIT, ExifInterface.TAG_STRIP_OFFSETS, ExifInterface.TAG_ROWS_PER_STRIP, ExifInterface.TAG_STRIP_BYTE_COUNTS, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT, ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, ExifInterface.TAG_TRANSFER_FUNCTION, ExifInterface.TAG_WHITE_POINT, ExifInterface.TAG_PRIMARY_CHROMATICITIES, ExifInterface.TAG_Y_CB_CR_COEFFICIENTS, ExifInterface.TAG_REFERENCE_BLACK_WHITE, ExifInterface.TAG_DATETIME, ExifInterface.TAG_IMAGE_DESCRIPTION, ExifInterface.TAG_MAKE, ExifInterface.TAG_MODEL, ExifInterface.TAG_SOFTWARE, ExifInterface.TAG_ARTIST, ExifInterface.TAG_COPYRIGHT, ExifInterface.TAG_EXIF_VERSION, ExifInterface.TAG_FLASHPIX_VERSION, ExifInterface.TAG_COLOR_SPACE, ExifInterface.TAG_GAMMA, ExifInterface.TAG_PIXEL_X_DIMENSION, ExifInterface.TAG_PIXEL_Y_DIMENSION, ExifInterface.TAG_COMPONENTS_CONFIGURATION, ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL, ExifInterface.TAG_MAKER_NOTE, ExifInterface.TAG_USER_COMMENT, ExifInterface.TAG_RELATED_SOUND_FILE, ExifInterface.TAG_DATETIME_ORIGINAL, ExifInterface.TAG_DATETIME_DIGITIZED, ExifInterface.TAG_OFFSET_TIME, ExifInterface.TAG_OFFSET_TIME_ORIGINAL, ExifInterface.TAG_OFFSET_TIME_DIGITIZED, ExifInterface.TAG_SUBSEC_TIME, ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_EXPOSURE_PROGRAM, ExifInterface.TAG_SPECTRAL_SENSITIVITY, ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, ExifInterface.TAG_OECF, ExifInterface.TAG_SENSITIVITY_TYPE, ExifInterface.TAG_STANDARD_OUTPUT_SENSITIVITY, ExifInterface.TAG_RECOMMENDED_EXPOSURE_INDEX, ExifInterface.TAG_ISO_SPEED, ExifInterface.TAG_ISO_SPEED_LATITUDE_YYY, ExifInterface.TAG_ISO_SPEED_LATITUDE_ZZZ, ExifInterface.TAG_SHUTTER_SPEED_VALUE, ExifInterface.TAG_APERTURE_VALUE, ExifInterface.TAG_BRIGHTNESS_VALUE, ExifInterface.TAG_EXPOSURE_BIAS_VALUE, ExifInterface.TAG_MAX_APERTURE_VALUE, ExifInterface.TAG_SUBJECT_DISTANCE, ExifInterface.TAG_METERING_MODE, ExifInterface.TAG_LIGHT_SOURCE, ExifInterface.TAG_FLASH, ExifInterface.TAG_SUBJECT_AREA, ExifInterface.TAG_FOCAL_LENGTH, ExifInterface.TAG_FLASH_ENERGY, ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE, ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION, ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION, ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, ExifInterface.TAG_SUBJECT_LOCATION, ExifInterface.TAG_EXPOSURE_INDEX, ExifInterface.TAG_SENSING_METHOD, ExifInterface.TAG_FILE_SOURCE, ExifInterface.TAG_SCENE_TYPE, ExifInterface.TAG_CFA_PATTERN, ExifInterface.TAG_CUSTOM_RENDERED, ExifInterface.TAG_EXPOSURE_MODE, ExifInterface.TAG_WHITE_BALANCE, ExifInterface.TAG_DIGITAL_ZOOM_RATIO, ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM, ExifInterface.TAG_SCENE_CAPTURE_TYPE, ExifInterface.TAG_GAIN_CONTROL, ExifInterface.TAG_CONTRAST, ExifInterface.TAG_SATURATION, ExifInterface.TAG_SHARPNESS, ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION, ExifInterface.TAG_SUBJECT_DISTANCE_RANGE, ExifInterface.TAG_IMAGE_UNIQUE_ID, "CameraOwnerName", ExifInterface.TAG_BODY_SERIAL_NUMBER, ExifInterface.TAG_LENS_SPECIFICATION, ExifInterface.TAG_LENS_MAKE, ExifInterface.TAG_LENS_MODEL, ExifInterface.TAG_LENS_SERIAL_NUMBER, ExifInterface.TAG_GPS_VERSION_ID, ExifInterface.TAG_GPS_LATITUDE_REF, ExifInterface.TAG_GPS_LATITUDE, ExifInterface.TAG_GPS_LONGITUDE_REF, ExifInterface.TAG_GPS_LONGITUDE, ExifInterface.TAG_GPS_ALTITUDE_REF, ExifInterface.TAG_GPS_ALTITUDE, ExifInterface.TAG_GPS_TIMESTAMP, ExifInterface.TAG_GPS_SATELLITES, ExifInterface.TAG_GPS_STATUS, ExifInterface.TAG_GPS_MEASURE_MODE, ExifInterface.TAG_GPS_DOP, ExifInterface.TAG_GPS_SPEED_REF, ExifInterface.TAG_GPS_SPEED, ExifInterface.TAG_GPS_TRACK_REF, ExifInterface.TAG_GPS_TRACK, ExifInterface.TAG_GPS_IMG_DIRECTION_REF, ExifInterface.TAG_GPS_IMG_DIRECTION, ExifInterface.TAG_GPS_MAP_DATUM, ExifInterface.TAG_GPS_DEST_LATITUDE_REF, ExifInterface.TAG_GPS_DEST_LATITUDE, ExifInterface.TAG_GPS_DEST_LONGITUDE_REF, ExifInterface.TAG_GPS_DEST_LONGITUDE, ExifInterface.TAG_GPS_DEST_BEARING_REF, ExifInterface.TAG_GPS_DEST_BEARING, ExifInterface.TAG_GPS_DEST_DISTANCE_REF, ExifInterface.TAG_GPS_DEST_DISTANCE, ExifInterface.TAG_GPS_PROCESSING_METHOD, ExifInterface.TAG_GPS_AREA_INFORMATION, ExifInterface.TAG_GPS_DATESTAMP, ExifInterface.TAG_GPS_DIFFERENTIAL, ExifInterface.TAG_GPS_H_POSITIONING_ERROR, ExifInterface.TAG_INTEROPERABILITY_INDEX, ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH, ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH, "ThumbnailOrientation", ExifInterface.TAG_DNG_VERSION, ExifInterface.TAG_DEFAULT_CROP_SIZE, ExifInterface.TAG_ORF_THUMBNAIL_IMAGE, ExifInterface.TAG_ORF_PREVIEW_IMAGE_START, ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH, ExifInterface.TAG_ORF_ASPECT_FRAME, ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER, ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER, ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER, ExifInterface.TAG_RW2_SENSOR_TOP_BORDER, ExifInterface.TAG_RW2_ISO, ExifInterface.TAG_RW2_JPG_FROM_RAW, ExifInterface.TAG_XMP, ExifInterface.TAG_NEW_SUBFILE_TYPE, ExifInterface.TAG_SUBFILE_TYPE});
    }
}
