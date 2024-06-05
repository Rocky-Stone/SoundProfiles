package net.rockystone.soundprofiles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.rockystone.soundprofiles.util.config.cloth.SoundProfilesConfigScreen;
import net.rockystone.soundprofiles.util.sound.SoundProfiles;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class SoundProfilesClient implements ClientModInitializer {

	private static KeyBinding normalVolumeKeyBind;
	private static KeyBinding quietVolumeKeyBind;
	private static KeyBinding fullVolumeKeyBind;
	private static KeyBinding customVolumeKeyBind;
	private static KeyBinding configKeyBind;

	public static final String MOD_ID = "soundprofiles";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("SoundProfilesClient.onInitializeClient() was called");

		SoundProfiles.createSoundProfiles();
		setKeyBindings();
		registerEvents();
	}



	public void setKeyBindings() {
		LOGGER.info("SoundProfilesClient.setKeyBindings() was called");

		normalVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.normal_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_7, "category.soundprofiles.keybindings"));
		quietVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.quiet_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_8, "category.soundprofiles.keybindings"));
		fullVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.full_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_9, "category.soundprofiles.keybindings"));
		customVolumeKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.custom_volume", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_5, "category.soundprofiles.keybindings"));
		configKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.soundprofiles.config", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_KP_2, "category.soundprofiles.keybindings"));
	}

	public void registerEvents() {
		LOGGER.info("SoundProfilesClient.registerEvents() was called");

		registerEndClientTickEvent();
	}

	public void registerEndClientTickEvent() {

		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			while (normalVolumeKeyBind.wasPressed()) {
				SoundProfiles.normalSoundProfile.applyProfile(client);
				client.player.sendMessage(Text.literal("Normal profile loaded"));
			}

			while (quietVolumeKeyBind.wasPressed()) {
				SoundProfiles.quietSoundProfile.applyProfile(client);
				client.player.sendMessage(Text.literal("Quiet profile loaded"));
			}

			while (fullVolumeKeyBind.wasPressed()) {
				SoundProfiles.fullSoundProfile.applyProfile(client);
				client.player.sendMessage(Text.literal("Full profile loaded"));
			}

			while (customVolumeKeyBind.wasPressed()) {
				SoundProfiles.customSoundProfile.applyProfile(client);
				client.player.sendMessage(Text.literal("Custom profile loaded"));
			}

			while (configKeyBind.wasPressed()) {
				SoundProfilesConfigScreen.start();
			}

		});
	}
}
