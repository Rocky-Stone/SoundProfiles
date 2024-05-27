package net.rockystone.soundprofiles.util.sound;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SoundProfile {
    private Map<String, Double> volumes;
    private Path path;

    private static final String soundProfilesDirectory = "sound-profiles_profiles";
    private static final String configDir = FabricLoader.getInstance().getConfigDir().toString();

    public SoundProfile(String fileName) {
        path = Paths.get(configDir,soundProfilesDirectory, fileName);
        genSoundProfilesDirectory();
        genDefaultSoundProfile();
        volumes = readFile(path);
    }

    private HashMap<String, Double> readFile(Path path) {
        HashMap<String, Double> returnVolumes = new HashMap();

        try {
            File file = path.toFile();
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                if (parts[0].equals("SOUND_CATEGORY")) {
                    continue;
                }
                returnVolumes.put(parts[0], Double.valueOf(parts[1]));
            }
        } catch (Exception e) {

        }

        return returnVolumes;
    }

    public Map<String, Double> getVolumes() {
        return volumes;
    }

    public void refresh() {
        volumes = readFile(path);
    }

    private void genSoundProfilesDirectory() {
        new File(configDir, soundProfilesDirectory).mkdirs();
    }

    private void genDefaultSoundProfile() {
        try {

            File file = path.toFile();
            if (!file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write("SOUND_CATEGORY,DOUBLE_FROM_0.0_TO_1.0\n");
                writer.write("MASTER,0.5\n");
                writer.write("HOSTILE,0.5\n");
                writer.write("AMBIENT,0.5\n");
                writer.write("BLOCKS,0.5\n");
                writer.write("MUSIC,0.5\n");
                writer.write("NEUTRAL,0.5\n");
                writer.write("PLAYERS,0.5\n");
                writer.write("RECORDS,0.5\n");
                writer.write("VOICE,0.5\n");
                writer.write("WEATHER,0.5");
                writer.close();
            }
        } catch (Exception e) {

        }
    }
}
