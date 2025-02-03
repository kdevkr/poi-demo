package kr.kdev.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;

import static kr.kdev.demo.ExcelUtil.MIME_XLSX;
import static kr.kdev.demo.ExcelUtil.contentDisposition;

@RestController
public class ExcelController {
    private static final ClassPathResource resource = new ClassPathResource("sample.xlsx");

    @GetMapping("/file")
    public ResponseEntity<StreamingResponseBody> download() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MIME_XLSX);
        headers.setContentDisposition(contentDisposition(resource.getFilename()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream -> {
                    byte[] bytes = resource.getContentAsByteArray();
                    StreamUtils.copy(bytes, outputStream);
                });
    }

    @GetMapping("/file/resource")
    public ResponseEntity<InputStreamResource> downloadUsingResource() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MIME_XLSX);
        headers.setContentDisposition(contentDisposition(resource.getFilename()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(resource.getInputStream()));
    }
}
