package net.rockystone.soundprofiles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.rockystone.soundprofiles.util.sound.SoundProfile;
import net.rockystone.soundprofiles.util.sound.SoundProfileSetter;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class SoundProfilesClient implements ClientModInitializer {

    private static KeyBinding normalVolumeKeyBind;
    private static KeyBinding quiet1VolumeKeyBind;
    private static KeyBinding fullVolumeKeyBind;
    private static KeyBinding customVolumeKeyBind;

    public static final String MOD_ID = "sound-profiles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static HashMap<String, SoundProfile> soundProfiles = new HashMap();

    @Override
    public void onInitializeClient() {
        LOGGER.info("SoundProfilesClient.onInitializeClient() was called");

        setKeyBindings();
        setSoundProfiles();
        registerEvents();

    }

    private static void setSoundProfiles() {
        LOGGER.info("SoundProfilesClient.setSoundProfiles() was called");

        SoundProfile normalSoundProfile = new SoundProfile("sound_profile_normal");
        SoundProfile quietSoundProfile = new SoundProfile("sound_profile_quiet");
        SoundProfile fullSoundProfile = new SoundProfile("sound_profile_full");
        SoundProfile customSoundProfile = new SoundProfile("sound_profile_custom");

        soundProfiles.put("normal", normalSoundProfile);
        soundProfiles.put("quiet", quietSoundProfile);
        soundProfiles.put("full", fullSoundProfile);
        soundProfiles.put("custom", customSoundProfile);
    }

    public static SoundProfile getSoundProfile(String fileName) {
        return soundProfiles.getOrDefault(fileName, soundProfiles.get("normal"));
    }

    public void setKeyBindings() {
        LOGGER.info("SoundProfilesClient.setKeyBindings() was called");

        normalVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.normal_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_7, "category.soundprofiles.keybinds"));
        quiet1VolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.quiet_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, "category.soundprofiles.keybinds"));
        fullVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.full_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_9, "category.soundprofiles.keybinds"));
        customVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.custom_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_5, "category.soundprofiles.keybinds"));
    }

    public void registerEvents() {
        LOGGER.info("SoundProfilesClient.registerEvents() was called");

        registerEndClientTickEvent();
    }

    public void registerEndClientTickEvent() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (normalVolumeKeyBind.wasPressed()) {
                SoundProfileSetter.setVolumesFromSoundProfile(client, "normal");
            }

            while (quiet1VolumeKeyBind.wasPressed()) {
                SoundProfileSetter.setVolumesFromSoundProfile(client, "quiet");
            }

            while (fullVolumeKeyBind.wasPressed()) {
                SoundProfileSetter.setVolumesFromSoundProfile(client, "full");
            }

            while (customVolumeKeyBind.wasPressed()) {
                SoundProfileSetter.setVolumesFromSoundProfile(client, "custom");
            }

        });
    }
}
