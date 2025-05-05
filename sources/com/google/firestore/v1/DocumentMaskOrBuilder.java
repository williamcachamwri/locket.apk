package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface DocumentMaskOrBuilder extends MessageLiteOrBuilder {
    String getFieldPaths(int i);

    ByteString getFieldPathsBytes(int i);

    int getFieldPathsCount();

    List<String> getFieldPathsList();
}
