package br.com.excelreader.controller;

import br.com.excelreader.service.ExcelReaderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ExcelReaderController {

    private final ExcelReaderService readerService;

    public ExcelReaderController(ExcelReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam MultipartFile file, @RequestParam Integer numberOfSheet) throws IOException {
        return readerService.upload(file, numberOfSheet);
    }
}
