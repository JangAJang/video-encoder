package org.gomterview.videoencoder.video.service;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service()
public class VideoEncoder {

    public void encodeVideo() throws IOException {
        FFmpeg ffmpeg = new FFmpeg("/usr/local/bin/ffmpeg");
        FFprobe ffprobe = new FFprobe("/usr/local/bin/ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput("input.mp4")
                .overrideOutputFiles(true)
                .addOutput("output.mp4")
                .setFormat("mp4")
                .setTargetSize(250_000)
                .disableSubtitle()
                .setAudioChannels(1)
                .setAudioCodec("aac")
                .setAudioSampleRate(48_000)
                .setAudioBitRate(32768)
                .setVideoCodec("libx264")
                .setVideoFrameRate(24, 1)
                .setVideoResolution(640, 480)
                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createTwoPassJob(builder).run();
    }
}
