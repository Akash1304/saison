package com.akash.saison.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JsonParsingUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonParsingUtil.class);

    @Autowired
    private ObjectMapper objectMapper;

    public <T> void writeJsonObjectToFile(T object, String filePath) throws IOException {
        File file = new File(filePath.trim());
        if (!file.exists()) {
            file.createNewFile();
        }
        logger.info(file.getAbsolutePath(), file.exists());
        objectMapper.writeValue(file, object);
    }

    /**
     * Returns the parsed {@link Object} from the {@link String jsonString} provided using {@link
     * ObjectMapper} - will need a type cast
     *
     * @param jsonString - {@link String}
     * @param valueType  - {@link Class}
     * @return {@link Object}
     */
    public <T> T fromJson(String jsonString, Class<T> valueType) {
        try {
            if (jsonString != null) {
                return objectMapper.readValue(jsonString, valueType);
            }
        } catch (Exception e) {
            logger.error("Error in fromJson(), jsonString: " + jsonString, e);
        }
        return null;
    }
}
