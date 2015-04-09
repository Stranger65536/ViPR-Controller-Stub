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
 *         Created on 27.01.2015.
 */
@JsonDeserialize(using = DriveType.DriveTypeDeserializer.class)
public enum DriveType {

    NONE("none"),
    SSD("SSD"),
    FC("FC"),
    SAS("SAS"),
    SATA("SATA");

    private final String value;

    private DriveType(final String value) {
        this.value = value;
    }

    public static DriveType forValue(String value) {
        for (DriveType driveType : DriveType.values()) {
            if (driveType.value.equalsIgnoreCase(value)) {
                return driveType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static class DriveTypeDeserializer extends JsonDeserializer<DriveType> {

        @Override
        public DriveType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return DriveType.forValue(jsonParser.getText());
        }
    }
}
