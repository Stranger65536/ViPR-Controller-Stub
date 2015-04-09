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
 *         Created on 08.09.2014.
 */
@JsonDeserialize(using = VolumeResourceType.VolumeResourceTypeDeserializer.class)
public enum VolumeResourceType {

    THICK("THICK_ONLY"),
    THIN("THIN_ONLY"),
    THIN_AND_THICK("THIN_AND_THICK");

    private final String value;

    private VolumeResourceType(final String value) {
        this.value = value;
    }

    public static VolumeResourceType forValue(String value) {
        for (VolumeResourceType type : VolumeResourceType.values()) {
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

    public static class VolumeResourceTypeDeserializer extends JsonDeserializer<VolumeResourceType> {

        @Override
        public VolumeResourceType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return VolumeResourceType.forValue(jsonParser.getText());
        }
    }

}
