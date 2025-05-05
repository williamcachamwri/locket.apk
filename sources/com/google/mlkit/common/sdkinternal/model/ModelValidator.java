package com.google.mlkit.common.sdkinternal.model;

import com.google.mlkit.common.model.RemoteModel;
import java.io.File;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public interface ModelValidator {

    /* compiled from: com.google.mlkit:common@@18.11.0 */
    public static class ValidationResult {
        public static final ValidationResult VALID = new ValidationResult(ErrorCode.OK, (String) null);
        private final ErrorCode zza;
        private final String zzb;

        /* compiled from: com.google.mlkit:common@@18.11.0 */
        public enum ErrorCode {
            OK,
            TFLITE_VERSION_INCOMPATIBLE,
            MODEL_FORMAT_INVALID
        }

        public ValidationResult(ErrorCode errorCode, String str) {
            this.zza = errorCode;
            this.zzb = str;
        }

        public ErrorCode getErrorCode() {
            return this.zza;
        }

        public String getErrorMessage() {
            return this.zzb;
        }

        public boolean isValid() {
            return this.zza == ErrorCode.OK;
        }
    }

    ValidationResult validateModel(File file, RemoteModel remoteModel);
}
