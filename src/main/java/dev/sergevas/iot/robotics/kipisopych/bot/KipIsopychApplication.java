package dev.sergevas.iot.robotics.kipisopych.bot;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "type", description = "Помощник девелопера")
        },
        info = @Info(
                title = "АПИ Исопыча",
                version = "1.0.0",
                contact = @Contact(
                        name = "Спросите Серёжу, если нужны подробности. Его все знают",
                        url = "https://github.com/sergevas/kip-isopych-bot"
                ),
                description = "Это - ресурсы Исопыча. Их можно смело дергать. И тогда Исопыч покажет, на что способен."
        )
)
public class KipIsopychApplication extends Application {
}
