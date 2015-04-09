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
@JsonRootName(value = "operational_status")
@JsonDeserialize(using = OperationalStatus.OperationalStatusDeserializer.class)
public enum OperationalStatus {

    NOT_READY("NOT_READY"),
    READY("READY");

    private final String value;

    private OperationalStatus(final String value) {
        this.value = value;
    }

    public static OperationalStatus forValue(String value) {
        for (OperationalStatus status : OperationalStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static class OperationalStatusDeserializer extends JsonDeserializer<OperationalStatus> {

        @Override
        public OperationalStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return OperationalStatus.forValue(jsonParser.getText());
        }
    }
}
