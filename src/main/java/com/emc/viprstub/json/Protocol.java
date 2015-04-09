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

@JsonDeserialize(using = Protocol.ProtocolDeserializer.class)
public enum Protocol {

    iSCSI("iSCSI"),
    FC("FC");

    private final String value;

    private Protocol(final String value) {
        this.value = value;
    }

    public static Protocol forValue(String value) {
        for (Protocol protocol : Protocol.values()) {
            if (protocol.value.equalsIgnoreCase(value)) {
                return protocol;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static class ProtocolDeserializer extends JsonDeserializer<Protocol> {

        @Override
        public Protocol deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return Protocol.forValue(jsonParser.getText());
        }
    }
}
