package androidx.datastore.preferences.protobuf;

import java.util.Map;

public interface StructOrBuilder extends MessageLiteOrBuilder {
    boolean containsFields(String str);

    @Deprecated
    Map<String, Value> getFields();

    int getFieldsCount();

    Map<String, Value> getFieldsMap();

    Value getFieldsOrDefault(String str, Value value);

    Value getFieldsOrThrow(String str);
}
