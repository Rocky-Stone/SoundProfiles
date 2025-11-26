package net.rockystone.soundprofiles.util.config.cloth;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.rockystone.soundprofiles.util.sound.SoundProfile;
import net.rockystone.soundprofiles.util.sound.SoundProfiles;

import java.util.ArrayList;
import java.util.List;

public class SoundProfilesConfigScreen {

    public static void start() {
        List<Double> quietVolumes = new ArrayList();
        List<Double> normalVolumes = new ArrayList();
        List<Double> fullVolumes = new ArrayList();
        List<Double> customVolumes = new ArrayList();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(Text.translatable("config.soundprofiles.config_title"));

        setProfileVolumeValues( customVolumes, SoundProfiles.customSoundProfile);
        setProfileVolumeValues(fullVolumes, SoundProfiles.fullSoundProfile);
        setProfileVolumeValues(normalVolumes, SoundProfiles.normalSoundProfile);
        setProfileVolumeValues(quietVolumes, SoundProfiles.quietSoundProfile);

        //This runs when you save on the config screen
        builder.setSavingRunnable(() -> {
            SoundProfiles.quietSoundProfile.setVolumeValues(quietVolumes);
            SoundProfiles.normalSoundProfile.setVolumeValues(normalVolumes);
            SoundProfiles.fullSoundProfile.setVolumeValues(fullVolumes);
            SoundProfiles.customSoundProfile.setVolumeValues(customVolumes);
        });

        //Sets the tabs on the top of the config screen
        ConfigCategory customProfile = builder.getOrCreateCategory(Text.translatable("config_category.soundprofiles.custom_profile"));
        ConfigCategory fullProfile = builder.getOrCreateCategory(Text.translatable("config_category.soundprofiles.full_profile"));
        ConfigCategory normalProfile = builder.getOrCreateCategory(Text.translatable("config_category.soundprofiles.normal_profile"));
        ConfigCategory quietProfile = builder.getOrCreateCategory(Text.translatable("config_category.soundprofiles.quiet_profile"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //Adds the buttons for the different tabs
        addEntries(customProfile, entryBuilder, customVolumes, 0.5d);
        addEntries(fullProfile, entryBuilder, fullVolumes, 1.0d);
        addEntries(normalProfile, entryBuilder, normalVolumes, 0.75d);
        addEntries(quietProfile, entryBuilder, quietVolumes, 0.1d);

        //Sets the screen to be the newly created config screen
        Screen screen = builder.build();
        MinecraftClient.getInstance().setScreen(screen);

    }

    private static void setProfileVolumeValues(List list, SoundProfile profile) {
        for (int i = 0; i < profile.getVolumes().size(); i++) {
            list.add(profile.getVolumes().get(i));
        }
    }

    private static void addEntries(ConfigCategory category, ConfigEntryBuilder entryBuilder, List<Double> volumes, Double defaultValue) {
        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.master_volume"), volumes.get(0))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(0, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.hostile_volume"), volumes.get(1))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(1, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.ambient_volume"), volumes.get(2))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(2, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.blocks_volume"), volumes.get(3))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(3, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.music_volume"), volumes.get(4))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(4, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.neutral_volume"), volumes.get(5))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(5, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.players_volume"), volumes.get(6))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(6, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.records_volume"), volumes.get(7))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(7, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.voice_volume"), volumes.get(8))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(8, newValue)) //Called when user save the config
                .build());

        category.addEntry(entryBuilder.startDoubleField(Text.translatable("config_option.soundprofiles.weather_volume"), volumes.get(9))
                .setDefaultValue(defaultValue) //Used when user click "Reset"
                .setTooltip(Text.translatable("config_tooltip.soundprofiles.tooltip"))
                .setSaveConsumer(newValue -> volumes.set(9, newValue)) //Called when user save the config
                .build());
    }
}
