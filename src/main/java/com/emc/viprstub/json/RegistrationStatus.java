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
@JsonRootName(value = "registration_status")
@JsonDeserialize(using = RegistrationStatus.RegistrationStatusDeserializer.class)
public enum RegistrationStatus {

    REGISTERED("REGISTERED"),
    UNREGISTERED("UNREGISTERED");

    private final String value;

    private RegistrationStatus(final String value) {
        this.value = value;
    }

    public static RegistrationStatus forValue(String value) {
        for (RegistrationStatus status : RegistrationStatus.values()) {
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

    public static class RegistrationStatusDeserializer extends JsonDeserializer<RegistrationStatus> {

        @Override
        public RegistrationStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return RegistrationStatus.forValue(jsonParser.getText());
        }
    }
}
