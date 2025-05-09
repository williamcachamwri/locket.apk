package com.google.firestore.admin.v1;

import com.google.firestore.admin.v1.Index;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface IndexOrBuilder extends MessageLiteOrBuilder {
    Index.IndexField getFields(int i);

    int getFieldsCount();

    List<Index.IndexField> getFieldsList();

    String getName();

    ByteString getNameBytes();

    Index.QueryScope getQueryScope();

    int getQueryScopeValue();

    Index.State getState();

    int getStateValue();
}
