package org.gomterview.videoencoder.video.controller;

import org.gomterview.videoencoder.video.service.VideoEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class VideoController {

    public VideoController(VideoEncoder encoder) {
        this.encoder = encoder;
    }

    private final VideoEncoder encoder;

    @PostMapping("/encode")
    public ResponseEntity<String> encodeVideo(@RequestParam("file") final MultipartFile file) throws IOException {
        encoder.encodeVideo(file);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
