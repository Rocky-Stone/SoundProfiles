package net.rockystone.soundprofiles.util.sound;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.rockystone.soundprofiles.SoundProfilesClient;

import java.util.Map;

public class SoundProfileSetter {

    public static void setVolumesFromSoundProfile(MinecraftClient client, String profileName) {

        SoundProfilesClient.getSoundProfile(profileName).refresh();
        Map<String, Double> currentSoundProfileVolumes = SoundProfilesClient.getSoundProfile(profileName).getVolumes();
        currentSoundProfileVolumes.keySet().stream().forEach(key -> {
            String category = key;
            double value = currentSoundProfileVolumes.get(key);

            switch (category) {
                case "MASTER":
                    client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(value);
                    break;
                case "HOSTILE":
                    client.options.getSoundVolumeOption(SoundCategory.HOSTILE).setValue(value);
                    break;
                case "AMBIENT":
                    client.options.getSoundVolumeOption(SoundCategory.AMBIENT).setValue(value);
                    break;
                case "BLOCKS":
                    client.options.getSoundVolumeOption(SoundCategory.BLOCKS).setValue(value);
                    break;
                case "MUSIC":
                    client.options.getSoundVolumeOption(SoundCategory.MUSIC).setValue(value);
                    break;
                case "NEUTRAL":
                    client.options.getSoundVolumeOption(SoundCategory.NEUTRAL).setValue(value);
                    break;
                case "PLAYERS":
                    client.options.getSoundVolumeOption(SoundCategory.PLAYERS).setValue(value);
                    break;
                case "RECORDS":
                    client.options.getSoundVolumeOption(SoundCategory.RECORDS).setValue(value);
                    break;
                case "VOICE":
                    client.options.getSoundVolumeOption(SoundCategory.VOICE).setValue(value);
                    break;
                case "WEATHER":
                    client.options.getSoundVolumeOption(SoundCategory.WEATHER).setValue(value);
                    break;

            }
        });
    }
}
