package us.spectr.rails.session;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Map;

/**
 * User: jonathan
 * Date: 5/30/13
 * Time: 11:02 PM
 */
public interface RailsSessionProcessor {

    /**
     * Unmarshals a ruby object string into a Java Map. Rails' persistent session stores (active record, redis, mongo, etc)
     * all store their session as a marshaled ruby object.
     *
     * @param railsSessionString
     * @return
     */
    public Map<String, Object> unmarshalString(String railsSessionString);

    public String marshalMap(Map<String, Object> railsSessionMap);

    /**
     * Base64 decodes a string. Rails' active record store base64 encodes all session by default.
     * @param encodedMessage
     * @return
     */
    public InputStream base64DecodeString(String encodedMessage);

    public Integer getWardenUserIdAsInt(HttpSession session);

    public String getWardenUserIdAsString(HttpSession session);
}
