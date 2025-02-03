package kr.kdev.demo;

import lombok.experimental.UtilityClass;
import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class ExcelUtil {
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/MIME_types/Common_types
    public static final MediaType MIME_XLS = MediaType.parseMediaType("application/vnd.ms-excel"); // AbstractXlsView
    public static final MediaType MIME_XLSX = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // AbstractXlsxView

    public static ContentDisposition contentDisposition(String filename) {
        return ContentDisposition.attachment().filename(filename, StandardCharsets.UTF_8).build();
    }
}
