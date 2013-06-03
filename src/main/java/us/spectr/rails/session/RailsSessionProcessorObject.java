package us.spectr.rails.session;

import java.io.InputStream;
import java.util.Map;

/**
 * This is just a convenience class that wraps the RailsSessionProcessor statically.
 *
 * User: jonathan
 * Date: 5/30/13
 * Time: 11:23 PM
 */
public class RailsSessionProcessorObject {

    private static RailsSessionProcessor sRailsSessionProcessor = new JRubyRailsSessionProcessor();

    public static Map<String, Object> unmarshalString(String railsSessionString) {
        return sRailsSessionProcessor.unmarshalString(railsSessionString);
    }

    public static InputStream base64DecodeString(String encodedMessage) {
        return sRailsSessionProcessor.base64DecodeString(encodedMessage);
    }

    public static int getWardenUserIdAsInt(String sessionId) {
        return sRailsSessionProcessor.getWardenUserIdAsInt(sessionId);
    }

    public static String marshalMap(Map<String, Object> railsSessionMap) {
        return sRailsSessionProcessor.marshalMap(railsSessionMap);
    }
}
