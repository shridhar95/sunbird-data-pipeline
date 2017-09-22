package org.ekstep.ep.samza.task;

import com.google.gson.Gson;
import org.apache.samza.system.IncomingMessageEnvelope;
import org.ekstep.ep.samza.domain.Event;
import org.ekstep.ep.samza.logger.Logger;
import org.ekstep.ep.samza.reader.Telemetry;

import java.util.Map;

public class TelemetryValidatorSource {
    static Logger LOGGER = new Logger(TelemetryValidatorSource.class);
    private final Telemetry telemetry;

    private IncomingMessageEnvelope envelope;

    public TelemetryValidatorSource(IncomingMessageEnvelope envelope) {
        this.envelope = envelope;
        this.telemetry = initializeTelemetry(envelope);
    }

    private Telemetry initializeTelemetry(IncomingMessageEnvelope envelope) {
        return new Telemetry(getMap());
    }

    public Event getEvent(){
        return new Event(getMap());
    }

    private Map<String, Object> getMap() {
        String message = (String) envelope.getMessage();
        return (Map<String, Object>) new Gson().fromJson(message, Map.class);
    }

    public String getMessage() {
        return envelope.toString();
    }
}
