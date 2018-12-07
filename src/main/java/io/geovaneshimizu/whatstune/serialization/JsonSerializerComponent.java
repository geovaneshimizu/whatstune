package io.geovaneshimizu.whatstune.serialization;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class JsonSerializerComponent extends JsonObjectSerializer<JsonSerializable> {

    @Override
    protected void serializeObject(JsonSerializable serializable,
                                   JsonGenerator jsonGenerator,
                                   SerializerProvider provider) throws IOException {
        serializable.writeJson(jsonGenerator);
    }
}
