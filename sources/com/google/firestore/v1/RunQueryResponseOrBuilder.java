package com.google.firestore.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Timestamp;

public interface RunQueryResponseOrBuilder extends MessageLiteOrBuilder {
    Document getDocument();

    Timestamp getReadTime();

    int getSkippedResults();

    ByteString getTransaction();

    boolean hasDocument();

    boolean hasReadTime();
}
