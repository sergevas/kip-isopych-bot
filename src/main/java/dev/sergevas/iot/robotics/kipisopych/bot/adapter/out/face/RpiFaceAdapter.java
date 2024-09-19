package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.mbelling.ws281x.Color.BLACK;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiFaceAdapter implements FacialController {

    private static final Logger log = LoggerFactory.getLogger(RpiFaceAdapter.class);
    @ConfigProperty(name = "ledstrip.ledsCount")
    int ledsCount;
    @ConfigProperty(name = "ledstrip.gpioPin")
    int gpioPin;
    @ConfigProperty(name = "ledstrip.frequencyHz")
    int frequencyHz;
    @ConfigProperty(name = "ledstrip.dma")
    int dma;
    @ConfigProperty(name = "ledstrip.pwmChannel")
    int pwmChannel;
    @ConfigProperty(name = "ledstrip.invert")
    boolean invert;
    @ConfigProperty(name = "ledstrip.stripType")
    LedStripType stripType;

    private Color[] colors;
    private Color mouthColor;

    @PostConstruct
    public void init() {
        colors = new Color[]{
                new Color(19, 237, 63),
                new Color(12, 179, 240),
                new Color(46, 12, 240),
                new Color(240, 12, 58)
        };
        mouthColor = new Color(196, 10, 35);
    }

    private void drawAllColors(Ws281xLedStrip strip, int pixel) throws InterruptedException {
        for (Color color : colors) {
            strip.setPixel(pixel, color);
            strip.setPixel(pixel + 4, color);
            strip.render();
            Thread.sleep(7);
        }
    }

    @Override
    public void simulateTalkingFace(int sleep, int time) {
        try {
            var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
            strip.setStrip(Color.BLACK);
            int numOfCircles = 20;
            int pixelNum = 4;
            for (int circle = 1; circle <= numOfCircles; circle++) {
                for (int pixel = 0; pixel < pixelNum; pixel++) {
//                    drawAllColors(strip, pixel);
                    strip.setPixel(pixel, colors[pixel % colors.length]);
                    strip.setPixel(pixel + 4, colors[pixel % colors.length]);
                    strip.setPixel(11 - pixel, mouthColor);
                    strip.setPixel(pixel + 12, mouthColor);
                    strip.render();
                    Thread.sleep(50);
                    strip.setPixel(pixel, BLACK);
                    strip.setPixel(pixel + 4, BLACK);
                }
                strip.setStrip(Color.BLACK);
                strip.render();
            }
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Override
    public void blinkRightEye(double speed) {

    }

    @Override
    public void blinkBothEyes(double speed) {
        try {
            var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 32, pwmChannel, invert, stripType, true);
            var colourBlue = Color.BLUE;
            var colourBlack = Color.BLACK;
            var pixel = 0;
            strip.setStrip(colourBlack);
            for (int i = 0; i <= ledsCount; i++) {
                strip.setPixel(i, colourBlue);
                strip.render();
                Thread.sleep(1000);
            }
            strip.setPixel(pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            Thread.sleep(500);
            pixel = 0;
            strip.setPixel(pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlack);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            strip.setPixel(++pixel, colourBlue);
            strip.render();
            Thread.sleep(500);
            strip.setStrip(colourBlack);
            strip.render();
            Thread.sleep(500);
            strip.setStrip(colourBlue);
            strip.render();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Override
    public void moveMouth(double speed) {
    }
}
