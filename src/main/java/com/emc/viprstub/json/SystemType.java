package com.emc.viprstub.json;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * @author Vladimir Belov-Fedorov
 *         vladimir.belov-fedorov@emc.com
 *         Created on 27.01.2015.
 */
public enum SystemType {
    VNX_BLOCK("vnxblock"),
    VMAX("vmax"),
    OPENSTACK("openstack"),
    VNX_FILE("vnxfile"),
    ISILON("isilon"),
    NETAPP("netapp");

    private final String value;

    private SystemType(final String value) {
        this.value = value;
    }

    public static SystemType forValue(String value) {
        for (SystemType systemType : SystemType.values()) {
            if (systemType.value.equalsIgnoreCase(value)) {
                return systemType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static class DriveTypeDeserializer extends JsonDeserializer<SystemType> {

        @Override
        public SystemType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return SystemType.forValue(jsonParser.getText());
        }
    }
}
