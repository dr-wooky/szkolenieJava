package pl.training.bank.rest;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMapperConfig implements ContextResolver<ObjectMapper>{

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
        return objectMapper;
    }
}
