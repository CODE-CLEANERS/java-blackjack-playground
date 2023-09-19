package nextstep.practice.optional;

import java.util.Optional;

public class ComputerStore {
    public static final String UNKNOWN_VERSION = "UNKNOWN";

    public static String getVersion(Computer computer) {
        String version = UNKNOWN_VERSION;
        if (computer != null) {
            Computer.Soundcard soundcard = computer.getSoundcard();
            if (soundcard != null) {
                Computer.USB usb = soundcard.getUsb();
                if (usb != null) {
                    version = usb.getVersion();
                }
            }
        }
        return version;
    }

    public static String getVersionOptional(Computer computer) {
        return Optional.ofNullable(computer).map(Computer::getSoundcard).map(Computer.Soundcard::getUsb).map(Computer.USB::getVersion).orElse(UNKNOWN_VERSION);
//        return null;
    }
}
