package com.emc.viprstub.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.IOException;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 23.09.2014.
 */
@JsonDeserialize(using = VirtualPoolType.VirtualPoolTypeDeserializer.class)
public enum VirtualPoolType {
    VOLUME("block"),
    FILE_SYSTEM("file"),
    OBJECT_STORE("object");

    private final String value;

    private VirtualPoolType(final String value) {
        this.value = value;
    }

    public static VirtualPoolType forValue(String value) {
        for (VirtualPoolType type : VirtualPoolType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static class VirtualPoolTypeDeserializer extends JsonDeserializer<VirtualPoolType> {

        @Override
        public VirtualPoolType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return VirtualPoolType.forValue(jsonParser.getText());
        }
    }
}
