package net.rockystone.soundprofiles.util.sound;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SoundProfile {
    private Path path;
    public String name;
    private List<Double> volumes;

    private static final String soundProfilesDirectory = "soundprofiles_profiles";
    private static final String configDir = FabricLoader.getInstance().getConfigDir().toString();

    public SoundProfile(String name) {
        this.name = name;
        path = Paths.get(configDir, soundProfilesDirectory, name);

        genSoundProfilesDirectoryIfAbsent();
        volumes = readFile(path);
    }

    private ArrayList<Double> readFile(Path path) {
        ArrayList<Double> returnVolumes = new ArrayList();

        try {
            File file = path.toFile();
            if (!file.exists()) {
                writeFile();
            }

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                if (parts[0].equals("SOUND_CATEGORY")) {
                    continue;
                }
                returnVolumes.add(Double.valueOf(parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnVolumes;
    }

    private void writeFile() {
        try {

            File file = path.toFile();
            if (!file.exists() || volumes.isEmpty()) {
                setDefaultVolumes();
            }
            FileWriter writer = new FileWriter(file);
            writer.write("SOUND_CATEGORY,DOUBLE_FROM_0.0_TO_1.0\n");
            writer.write("MASTER," + volumes.get(0) + "\n");
            writer.write("HOSTILE," + volumes.get(1) + "\n");
            writer.write("AMBIENT," + volumes.get(2) + "\n");
            writer.write("BLOCKS," + volumes.get(3) + "\n");
            writer.write("MUSIC," + volumes.get(4) + "\n");
            writer.write("NEUTRAL," + volumes.get(5) + "\n");
            writer.write("PLAYERS," + volumes.get(6) + "\n");
            writer.write("RECORDS," + volumes.get(7) + "\n");
            writer.write("VOICE," + volumes.get(8) + "\n");
            writer.write("WEATHER," + volumes.get(9) + "\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getVolumes() {
        return volumes;
    }

    public void setVolumeValues(List<Double> volumes) {
        this.volumes = volumes;
        writeFile();
    }

    public void applyProfile(MinecraftClient client) {
        client.options.getSoundVolumeOption(SoundCategory.MASTER).setValue(volumes.get(0));
        client.options.getSoundVolumeOption(SoundCategory.HOSTILE).setValue(volumes.get(1));
        client.options.getSoundVolumeOption(SoundCategory.AMBIENT).setValue(volumes.get(2));
        client.options.getSoundVolumeOption(SoundCategory.BLOCKS).setValue(volumes.get(3));
        client.options.getSoundVolumeOption(SoundCategory.MUSIC).setValue(volumes.get(4));
        client.options.getSoundVolumeOption(SoundCategory.NEUTRAL).setValue(volumes.get(5));
        client.options.getSoundVolumeOption(SoundCategory.PLAYERS).setValue(volumes.get(6));
        client.options.getSoundVolumeOption(SoundCategory.RECORDS).setValue(volumes.get(7));
        client.options.getSoundVolumeOption(SoundCategory.VOICE).setValue(volumes.get(8));
        client.options.getSoundVolumeOption(SoundCategory.WEATHER).setValue(volumes.get(9));
    }

    //Below methods used for setting/generating default values for first run of modded client
    private void setDefaultVolumes() {
        double master, hostile, ambient, blocks, music, neutral, players, records, voice, weather;

        switch (name) {
            case "normal":
                master = 0.75;
                hostile = 1.0;
                ambient = 1.0;
                blocks = 0.75;
                music = 0.3;
                neutral = 0.2;
                players = 1.0;
                records = 0.25;
                voice = 1.0;
                weather = 0.2;
                break;

            case "quiet":
                master = 0.1;
                hostile = 0.1;
                ambient = 0.1;
                blocks = 0.1;
                music = 0.1;
                neutral = 0.1;
                players = 0.1;
                records = 0.1;
                voice = 0.1;
                weather = 0.1;
                break;

            case "full":
                master = 1.0;
                hostile = 1.0;
                ambient = 1.0;
                blocks = 1.0;
                music = 1.0;
                neutral = 1.0;
                players = 1.0;
                records = 1.0;
                voice = 1.0;
                weather = 1.0;
                break;

            case "custom":
                master = 0.5;
                hostile = 0.5;
                ambient = 0.5;
                blocks = 0.5;
                music = 0.5;
                neutral = 0.5;
                players = 0.5;
                records = 0.5;
                voice = 0.5;
                weather = 0.5;
                break;

            default:
                master = 1.0;
                hostile = 1.0;
                ambient = 1.0;
                blocks = 1.0;
                music = 1.0;
                neutral = 1.0;
                players = 1.0;
                records = 1.0;
                voice = 1.0;
                weather = 0.5;
        }
        volumes = new ArrayList();
        volumes.add(master);
        volumes.add(hostile);
        volumes.add(ambient);
        volumes.add(blocks);
        volumes.add(music);
        volumes.add(neutral);
        volumes.add(players);
        volumes.add(records);
        volumes.add(voice);
        volumes.add(weather);
    }

    private void genSoundProfilesDirectoryIfAbsent() {
        new File(configDir, soundProfilesDirectory).mkdirs();
    }
}
