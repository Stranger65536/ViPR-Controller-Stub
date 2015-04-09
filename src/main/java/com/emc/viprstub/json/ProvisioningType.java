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
@JsonDeserialize(using = ProvisioningType.ProvisioningTypeDeserializer.class)
public enum ProvisioningType {
    THICK("Thick"),
    THIN("Thin");

    private final String value;

    private ProvisioningType(final String value) {
        this.value = value;
    }

    public static ProvisioningType forValue(String value) {
        for (ProvisioningType type : ProvisioningType.values()) {
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

    public static class ProvisioningTypeDeserializer extends JsonDeserializer<ProvisioningType> {

        @Override
        public ProvisioningType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return ProvisioningType.forValue(jsonParser.getText());
        }
    }
}
