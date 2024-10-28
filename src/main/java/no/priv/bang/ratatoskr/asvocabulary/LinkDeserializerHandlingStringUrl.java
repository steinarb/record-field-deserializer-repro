package no.priv.bang.ratatoskr.asvocabulary;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LinkDeserializerHandlingStringUrl extends StdDeserializer<LinkOrObject> {

    protected LinkDeserializerHandlingStringUrl() {
        super(LinkOrObject.class);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public LinkOrObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        p.skipChildren();
        return null;
    }

}
