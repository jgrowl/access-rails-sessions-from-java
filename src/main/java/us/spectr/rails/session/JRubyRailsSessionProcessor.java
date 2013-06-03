package us.spectr.rails.session;

import org.apache.commons.codec.binary.Base64;
import org.jruby.Ruby;
import org.jruby.RubyHash;
import org.jruby.runtime.marshal.UnmarshalStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * User: jonathan
 * Date: 5/26/13
 * Time: 4:21 PM
 */
public class JRubyRailsSessionProcessor implements RailsSessionProcessor {

    // TODO: Find a way to get around using jruby.
    static Ruby ruby = Ruby.newInstance();

    @Override
    public Map<String, Object> unmarshalString(String railsSessionString) {
        try {
            InputStream inputStream = new ByteArrayInputStream(railsSessionString.getBytes());
            UnmarshalStream stream = new UnmarshalStream(ruby, inputStream, null, false);
            RubyHash rubyHash = stream.unmarshalObject().convertToHash();
            @SuppressWarnings("unchecked")
            Map<String, Object> javaMap = (Map<String, Object>) rubyHash.toJava(Map.class);
            return javaMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String marshalMap(Map<String, Object> railsSessionMap) {
        RubyHash rubyHash = new RubyHash(ruby);
        for (Map.Entry<String, Object> entry : railsSessionMap.entrySet()) {
            rubyHash.put(entry.getKey(), entry.getValue());
        }

        return rubyHash.toString();
    }

    @Override
    public InputStream base64DecodeString(String encodedMessage) {
        return new ByteArrayInputStream(Base64.decodeBase64(encodedMessage));
    }

    @Override
    public int getWardenUserIdAsInt(String sessionId) {
//        val data = StoreConnection.getSessionData(sessionId)
//        data.get("warden.user.user.key").get(0).get(0).asInt()
        return 0;
    }
}
