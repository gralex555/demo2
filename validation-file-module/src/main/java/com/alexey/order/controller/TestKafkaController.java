package com.alexey.order.controller;
import com.alexey.order.service.FileValidationChain;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TestKafkaController {
 //   final SenderService senderService;
    final FileValidationChain fileValidationChain;

    @GetMapping("/sendTestRequest")
    public void sendTestRequest(@RequestPart MultipartFile dto, @RequestParam boolean strictMode) throws IOException {
        fileValidationChain.validate(dto, strictMode);
    }
}
