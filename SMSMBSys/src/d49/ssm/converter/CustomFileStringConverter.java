package d49.ssm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class CustomFileStringConverter implements Converter<MultipartFile, String> {

    @Override
    public String convert(MultipartFile arg0) {
        return null;
    }

}


