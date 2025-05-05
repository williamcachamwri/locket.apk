package com.google.firebase.functions;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.FirebaseException;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseFunctionsException extends FirebaseException {
    private final Code code;
    private final Object details;

    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);
        
        private static final SparseArray<Code> STATUS_LIST = null;
        private final int value;

        static {
            STATUS_LIST = buildStatusList();
        }

        private Code(int i) {
            this.value = i;
        }

        private static SparseArray<Code> buildStatusList() {
            SparseArray<Code> sparseArray = new SparseArray<>();
            Code[] values = values();
            int length = values.length;
            int i = 0;
            while (i < length) {
                Code code = values[i];
                Code code2 = sparseArray.get(code.ordinal());
                if (code2 == null) {
                    sparseArray.put(code.ordinal(), code);
                    i++;
                } else {
                    throw new IllegalStateException("Code value duplication between " + code2 + "&" + code.name());
                }
            }
            return sparseArray;
        }

        static Code fromValue(int i) {
            return STATUS_LIST.get(i, UNKNOWN);
        }

        static Code fromHttpStatus(int i) {
            if (i == 200) {
                return OK;
            }
            if (i == 409) {
                return ABORTED;
            }
            if (i == 429) {
                return RESOURCE_EXHAUSTED;
            }
            if (i == 400) {
                return INVALID_ARGUMENT;
            }
            if (i == 401) {
                return UNAUTHENTICATED;
            }
            if (i == 403) {
                return PERMISSION_DENIED;
            }
            if (i == 404) {
                return NOT_FOUND;
            }
            if (i == 503) {
                return UNAVAILABLE;
            }
            if (i == 504) {
                return DEADLINE_EXCEEDED;
            }
            switch (i) {
                case 499:
                    return CANCELLED;
                case 500:
                    return INTERNAL;
                case TypedValues.PositionType.TYPE_TRANSITION_EASING:
                    return UNIMPLEMENTED;
                default:
                    return UNKNOWN;
            }
        }
    }

    static FirebaseFunctionsException fromResponse(Code code2, String str, Serializer serializer) {
        Object obj;
        String name = code2.name();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("error");
            if (jSONObject.opt("status") instanceof String) {
                code2 = Code.valueOf(jSONObject.getString("status"));
                name = code2.name();
            }
            if ((jSONObject.opt("message") instanceof String) && !jSONObject.getString("message").isEmpty()) {
                name = jSONObject.getString("message");
            }
            obj = jSONObject.opt(UniversalFirebaseFunctionsModule.DETAILS_KEY);
            if (obj != null) {
                try {
                    obj = serializer.decode(obj);
                } catch (IllegalArgumentException unused) {
                    code2 = Code.INTERNAL;
                    name = code2.name();
                } catch (JSONException unused2) {
                }
            }
        } catch (IllegalArgumentException unused3) {
            obj = null;
            code2 = Code.INTERNAL;
            name = code2.name();
        } catch (JSONException unused4) {
            obj = null;
        }
        if (code2 == Code.OK) {
            return null;
        }
        return new FirebaseFunctionsException(name, code2, obj);
    }

    FirebaseFunctionsException(String str, Code code2, Object obj) {
        super(str);
        this.code = code2;
        this.details = obj;
    }

    FirebaseFunctionsException(String str, Code code2, Object obj, Throwable th) {
        super(str, th);
        this.code = code2;
        this.details = obj;
    }

    public Code getCode() {
        return this.code;
    }

    public Object getDetails() {
        return this.details;
    }
}
