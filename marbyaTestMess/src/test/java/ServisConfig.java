import org.aeonbits.owner.Config;

@Config.Sources({"classpath:SendMessageToArtem.properties"})

public interface ServisConfig extends Config {
    @Key("loginVk")
    String loginVk();

    @Key("passVk")
    String passVk();

}

