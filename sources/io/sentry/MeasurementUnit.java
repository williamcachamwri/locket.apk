package io.sentry;

import java.util.Locale;

public interface MeasurementUnit {
    public static final String NONE = "none";

    public enum Duration implements MeasurementUnit {
        NANOSECOND,
        MICROSECOND,
        MILLISECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        WEEK
    }

    public enum Fraction implements MeasurementUnit {
        RATIO,
        PERCENT
    }

    public enum Information implements MeasurementUnit {
        BIT,
        BYTE,
        KILOBYTE,
        KIBIBYTE,
        MEGABYTE,
        MEBIBYTE,
        GIGABYTE,
        GIBIBYTE,
        TERABYTE,
        TEBIBYTE,
        PETABYTE,
        PEBIBYTE,
        EXABYTE,
        EXBIBYTE
    }

    String name();

    public static final class Custom implements MeasurementUnit {
        private final String name;

        public Custom(String str) {
            this.name = str;
        }

        public String name() {
            return this.name;
        }
    }

    String apiName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
