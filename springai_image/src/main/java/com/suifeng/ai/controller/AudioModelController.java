package com.suifeng.ai.controller;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestController
public class AudioModelController {

    @Autowired
    private DashScopeSpeechSynthesisModel speechSynthesisModel;

    private static final String TEXT = "床前明月光，疑是地上霜。";

    private static final String PATH = "D:\\Code\\SpringAiProjecct\\springai_image\\src\\main\\resources\\tts";

    @GetMapping("/tts")
    public void tts() {
        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
                .withSpeed(1.0)
                .withPitch(0.9)
                .withVolume(60)
                .build();
        SpeechSynthesisResponse response = speechSynthesisModel.call(
                new SpeechSynthesisPrompt(TEXT, options));
        File file = new File(PATH + "/output.mp3");
        try(FileOutputStream fos = new FileOutputStream(file)){
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
