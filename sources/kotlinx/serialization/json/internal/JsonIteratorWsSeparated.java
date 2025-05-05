package kotlinx.serialization.json.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.internal.StreamingJsonDecoder;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\t\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\rR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/JsonIteratorWsSeparated;", "T", "", "json", "Lkotlinx/serialization/json/Json;", "lexer", "Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/ReaderJsonLexer;Lkotlinx/serialization/DeserializationStrategy;)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonIterator.kt */
final class JsonIteratorWsSeparated<T> implements Iterator<T>, KMappedMarker {
    private final DeserializationStrategy<T> deserializer;
    private final Json json;
    private final ReaderJsonLexer lexer;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public JsonIteratorWsSeparated(Json json2, ReaderJsonLexer readerJsonLexer, DeserializationStrategy<? extends T> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(json2, "json");
        Intrinsics.checkNotNullParameter(readerJsonLexer, "lexer");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        this.json = json2;
        this.lexer = readerJsonLexer;
        this.deserializer = deserializationStrategy;
    }

    public T next() {
        return new StreamingJsonDecoder(this.json, WriteMode.OBJ, this.lexer, this.deserializer.getDescriptor(), (StreamingJsonDecoder.DiscriminatorHolder) null).decodeSerializableValue(this.deserializer);
    }

    public boolean hasNext() {
        return this.lexer.isNotEof();
    }
}
