package com.knapptown.gpmdataexplorer.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataStringDecoder {

    private static final Logger logger = LoggerFactory.getLogger(DataStringDecoder.class);

    /**
     * Decoding a data string. If the data string contains a set of characters matching
     * "&#~~;", those set of characters will be treated as ASCII codes and be converted
     * to characters in the original data string.
     * @param dataString A data string potentially containing encoded characters.
     * @return A decoded data string.
     */
    public String decodeDataString(String dataString) {
        logger.debug("Decoding data string: '" + dataString + "'.");
        StringBuilder decoded = new StringBuilder();
        for(int index = 0; index < dataString.length(); index++) {
            char chr = dataString.charAt(index);
            if(chr == '&' && dataString.charAt(index + 1) == '#') {
                try {
                    int endIndex = dataString.indexOf(";", index);
                    String encodedChar = dataString.substring(index + 2, endIndex);
                    decoded.append((char) Integer.parseInt(encodedChar));
                    index = endIndex;
                } catch (Exception ex) {
                    decoded.append(chr);
                }
            } else {
                decoded.append(chr);
            }
        }
        logger.debug("Decoded data string: '" + decoded + "'.");
        return decoded.toString();
    }

}
