package dev.sergevas.iot.robotics.kipisopych.bot.adapter.out.face;

import dev.sergevas.iot.robotics.kipisopych.bot.application.port.out.face.FacialController;
import io.quarkus.arc.DefaultBean;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@DefaultBean
public class MockFaceAdapter implements FacialController {

    @Override
    public Uni<Void> simulateTalkingFace(long sleep, int time) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debugf("Enter simulate talking face: sleep=%d time=%d", sleep, time);
            Thread.sleep(sleep);
            Log.debug("Exit simulate talking face");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> blinkOnStartup() {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debug("Enter blinkOnStartup");
            Thread.sleep(2000);
            Log.debug("Exit blinkOnStartup");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> lightsOn() {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debug("Enter lightsOn");
            Thread.sleep(2000);
            Log.debug("Exit lightsOn");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> lightsOff() {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debug("Enter lightsOff");
            Thread.sleep(2000);
            Log.debug("Exit lightsOff");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public Uni<Void> displayBCD(int valueToDisplay) {
        return Uni.createFrom().item(Unchecked.supplier(() -> {
            Log.debugf("Enter simulate displayBCD valueToDisplay=%d", valueToDisplay);
            Thread.sleep(10000);
            Log.debug("Exit simulate displayBCD");
            return null;
        })).runSubscriptionOn(Infrastructure.getDefaultExecutor()).replaceWithVoid();
    }

    @Override
    public void blinkRightEye(double speed) {
        Log.infof("Kip Isopych is blinking with the right eye with speed %f", speed);
    }

    @Override
    public void blinkBothEyes(double speed) {
        Log.infof("Kip Isopych is blinking with his both eyes with speed %f", speed);
    }

    @Override
    public void moveMouth(long sleep, int time) {
        Log.infof("Kip Isopych is moving his mouth sleep %d time %d", sleep, time);
    }
}
