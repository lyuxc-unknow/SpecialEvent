package mod.lyuxc.specialrules.world;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import mod.lyuxc.specialrules.Config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SpecialRulesData {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private String rule = Config.noneCurse;
    private int switchTime = 0;

    public String getRule() {
        return rule;
    }
    public void setRule(String rule) {
        this.rule = rule;
    }
    public int getSwitchTime() {
        return switchTime;
    }
    public void setSwitchTime(int switchTime) {
        this.switchTime = switchTime;
    }
    public void save(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(this,writer);
        }
    }
    public static SpecialRulesData load(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader,SpecialRulesData.class);
        }
    }
}
