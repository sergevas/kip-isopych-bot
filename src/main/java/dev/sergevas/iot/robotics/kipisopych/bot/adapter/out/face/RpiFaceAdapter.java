package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.LedStripType;
import com.github.mbelling.ws281x.Ws281xLedStrip;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialControllerException;
import dev.sergevas.iot.robotics.kipisopych.bot.domain.pomodoro.PomodoroType;
import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static com.github.mbelling.ws281x.Color.BLACK;
import static com.github.mbelling.ws281x.Color.WHITE;

@ApplicationScoped
@IfBuildProfile("prod")
public class RpiFaceAdapter implements FacialController {

    private static final Logger log = LoggerFactory.getLogger(RpiFaceAdapter.class);

    enum BCD {
        _0('0', "0000"),
        _1('1', "0001"),
        _2('2', "0010"),
        _3('3', "0011"),
        _4('4', "0100"),
        _5('5', "0101"),
        _6('6', "0110"),
        _7('7', "0111"),
        _8('8', "1000"),
        _9('9', "1001");

        private final char decValue;
        private final String bcdValue;

        BCD(char decValue, String bcdValue) {
            this.decValue = decValue;
            this.bcdValue = bcdValue;
        }

        public char getDecValue() {
            return decValue;
        }

        public String getBcdValue() {
            return bcdValue;
        }

        public static BCD getByDecValue(char val) {
            return Arrays.stream(BCD.values())
                    .filter(bcd -> bcd.decValue == val)
                    .findAny()
                    .orElse(_0);
        }
    }

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

    private Ws281xLedStrip strip;

    @PostConstruct
    public void init() {
        colors = new Color[]{
                new Color(19, 237, 63),
                new Color(12, 179, 240),
                new Color(46, 12, 240),
                new Color(240, 12, 58)
        };
        mouthColor = new Color(196, 10, 35);
        strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
    }

    @Override
    public Uni<Void> simulateTalkingFace(long sleep, int time) {
        Log.debugf("Enter simulate talking face: sleep %d time %d", sleep, time);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                strip.setStrip(Color.BLACK);
                int pixelNum = 4;
                for (int circle = 1; circle <= time; circle++) {
                    for (int pixel = 0; pixel < pixelNum; pixel++) {
                        strip.setPixel(pixel, colors[pixel % colors.length]);
                        strip.setPixel(pixel + 4, colors[pixel % colors.length]);
                        strip.setPixel(11 - pixel, mouthColor);
                        strip.setPixel(pixel + 12, mouthColor);
                        strip.render();
                        Thread.sleep(sleep);
                        strip.setPixel(pixel, BLACK);
                        strip.setPixel(pixel + 4, BLACK);
                    }
                    strip.setStrip(Color.BLACK);
                    strip.render();
                }
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit simulate talking face");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> blinkOnStartup() {
        Log.debug("Enter blinkOnStartup");
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                var time = 3;
                var color = new Color(210, 45, 45);
                var sleep = 400;
                strip.setStrip(Color.BLACK);
                for (int circle = 1; circle <= time; circle++) {
                    if (circle > 1) {
                        color = new Color(19, 128, 236);
                        sleep = 200;
                    }
                    renderBothEyes(strip, color);
                    Thread.sleep(sleep);
                    renderBothEyes(strip, BLACK);
                    Thread.sleep(sleep);
                }
                renderBothEyes(strip, color);
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit blinkOnStartup");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    private Color wheel(int pos) {
        if (pos < 85) {
            return new Color(pos * 3, 255 - pos * 3, 0);
        } else if (pos < 170) {
            pos -= 85;
            return new Color(255 - pos * 3, 0, pos * 3);
        } else {
            pos -= 170;
            return new Color(0, pos * 3, 255 - pos * 3);
        }
    }

    @Override
    public Uni<Void> dance() {
        Log.debug("Enter dance");
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                final int wait_ms = 20;
                final int iterations = 4;
                for (int j = 0; j < 256 * iterations; j++) {
                    for (int i = 0; i < ledsCount; i++) {
                        strip.setPixel(i, wheel((i + j) & 255));
                    }
                    strip.render();
                    Thread.sleep(wait_ms);
                }
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit dance");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> lightsOn() {
        Log.debug("Enter lightsOn");
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                strip.setStrip(WHITE);
                strip.render();
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit lightsOn");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> lightsOff() {
        Log.debug("Enter lightsOff");
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                strip.setStrip(BLACK);
                strip.render();
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit lightsOff");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public void moveMouth(long sleep, int time) {
        Log.debugf("Enter moveMouth: sleep %d time %d", sleep, time);

        Log.debug("Exit moveMouth");
    }

    private void renderBothEyes(Ws281xLedStrip strip, Color color) {
        for (int pixel = 0; pixel < 8; pixel++) {
            strip.setPixel(pixel, color);
        }
        strip.render();
    }

    @Override
    public Uni<Void> renderPomodoroTypeDependantEyes(PomodoroType pomodoroType) {
        Log.debugf("Enter renderPomodoroTypeDependantEyes: pomodoroType=%s", pomodoroType);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
                Color pomodoroStateDependantColor = switch (pomodoroType) {
                    case POMODORO -> new Color(0, 71, 171); // Кобальт синий
                    case SHORT_BREAK -> new Color(3, 108, 86); // Фталевый зеленый
                    case LONG_BREAK -> new Color(233, 116, 81); // Сиена жженая
                };
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 16, pwmChannel, invert, stripType, true);
                renderBothEyes(strip, pomodoroStateDependantColor);
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit renderPomodoroTypeDependantEyes");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> displayBCD(int valueToDisplay) {
        Log.debugf("Enter displayBCD: valueToDisplay=%d", valueToDisplay);
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            try {
//                var strip = new Ws281xLedStrip(ledsCount, gpioPin, frequencyHz, dma, 8, pwmChannel, invert, stripType, false);
                var valueToDisplayStr = Integer.toString(valueToDisplay);
                var strValue = valueToDisplayStr.length() == 1 ? "0" + valueToDisplayStr : valueToDisplayStr;
                var strArr = strValue.toCharArray();
                Log.debugf("strValue=%s", strValue);
                for (int i = 0; i < strArr.length; i++) {
                    Log.debugf("i=%d", i);
                    var bitChars = BCD.getByDecValue(strArr[i]).getBcdValue().toCharArray();
                    Log.debugf("bitChars=%s", Arrays.toString(bitChars));
                    for (int j = 15 - 4 * i; j > (15 - 4 * (i + 1)); j--) {
                        Log.debugf("bitChars[%d]=%c", bitChars[i]);
                        if (bitChars[15 - 4 * i - j] == '1') {
                            if (j > 11) { // We set high tetrad with different color
                                strip.setPixel(j, new Color(54, 154, 204));
                            } else {
                                strip.setPixel(j, Color.DARK_GRAY);
                            }
                        } else {
                            strip.setPixel(j, BLACK); // Киноварь
                        }
                    }
                }
                strip.render();
            } catch (Exception e) {
                Log.error(e);
                throw new FacialControllerException(e);
            }
            Log.debug("Exit displayBCD");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
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
}
