package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzlr implements zznj {
    static final zznj zza = new zzlr();

    private zzlr() {
    }

    public final boolean zza(int i) {
        zzls zzls;
        zzls zzls2 = zzls.EDITION_UNKNOWN;
        if (i == 0) {
            zzls = zzls.EDITION_UNKNOWN;
        } else if (i == 1) {
            zzls = zzls.EDITION_1_TEST_ONLY;
        } else if (i == 2) {
            zzls = zzls.EDITION_2_TEST_ONLY;
        } else if (i == 900) {
            zzls = zzls.EDITION_LEGACY;
        } else if (i != Integer.MAX_VALUE) {
            switch (i) {
                case 998:
                    zzls = zzls.EDITION_PROTO2;
                    break;
                case 999:
                    zzls = zzls.EDITION_PROTO3;
                    break;
                case 1000:
                    zzls = zzls.EDITION_2023;
                    break;
                case 1001:
                    zzls = zzls.EDITION_2024;
                    break;
                default:
                    switch (i) {
                        case 99997:
                            zzls = zzls.EDITION_99997_TEST_ONLY;
                            break;
                        case 99998:
                            zzls = zzls.EDITION_99998_TEST_ONLY;
                            break;
                        case 99999:
                            zzls = zzls.EDITION_99999_TEST_ONLY;
                            break;
                        default:
                            zzls = null;
                            break;
                    }
            }
        } else {
            zzls = zzls.EDITION_MAX;
        }
        return zzls != null;
    }
}
