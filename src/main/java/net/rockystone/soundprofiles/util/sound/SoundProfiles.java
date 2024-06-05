package net.rockystone.soundprofiles.util.sound;

import net.rockystone.soundprofiles.SoundProfilesClient;

public class SoundProfiles {

    public static SoundProfile quietSoundProfile = new SoundProfile("quiet");
    public static SoundProfile normalSoundProfile = new SoundProfile("normal");
    public static SoundProfile fullSoundProfile = new SoundProfile("full");
    public static SoundProfile customSoundProfile = new SoundProfile("custom");

    public static void createSoundProfiles() {
        SoundProfilesClient.LOGGER.info("Creating sound profiles");

    }
}
