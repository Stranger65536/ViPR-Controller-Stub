package com.emc.viprstub.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.io.IOException;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 08.09.2014.
 */
@JsonRootName(value = "pool_service_type")
@JsonDeserialize(using = PoolServiceType.PoolServiceTypeDeserializer.class)
public enum PoolServiceType {

    BLOCK("block"),
    FILE("file"),
    OBJECT("object");

    private final String value;

    private PoolServiceType(final String value) {
        this.value = value;
    }

    public static PoolServiceType forValue(String value) {
        for (PoolServiceType type : PoolServiceType.values()) {
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

    public static class PoolServiceTypeDeserializer extends JsonDeserializer<PoolServiceType> {

        @Override
        public PoolServiceType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return PoolServiceType.forValue(jsonParser.getText());
        }
    }
}
