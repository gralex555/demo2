package com.alexey.order.controller;

import com.alexey.order.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestKafkaController {
    final SenderService senderService;

    @GetMapping("/sendTestRequest")
    public void sendTestRequest(@RequestParam String fileName) {
        senderService.sendKafkaMessage("storage-service", fileName);
    }
}
