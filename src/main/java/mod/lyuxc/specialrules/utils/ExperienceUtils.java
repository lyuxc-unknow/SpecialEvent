package mod.lyuxc.specialrules.utils;

import net.minecraft.world.entity.player.Player;

public class ExperienceUtils {
    /**
     * 获取玩家当前经验等级等额的经验点数
     * @param level 传入玩家经验等级
     * */
    public static int getXpValue(int level) {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        } else {
            return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
        }
    }

    /**
     * 获取经验总和
     * @param player 需要获取的玩家
     * */
    public static int getXPoint(Player player) {
        return getXpValue(player.experienceLevel + 1) - player.getXpNeededForNextLevel();
    }

    /**
     * 减去玩家拥有的经验，原地址
     * <a href="https://github.com/DenisMasterHerobrine/AngelRing/blob/master/src/main/java/dev/denismasterherobrine/angelring/utils/ExperienceUtils.java#L113">public static void decreaseXP(Player player, float amount)</a>
     * @param player 传入玩家需要修改的玩家
     * @param amount 减去的经验
     * */
    public static void reduceXP(Player player, float amount) {
        if (player.totalExperience - amount <= 0) {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            return;
        }
        player.totalExperience -= (int) amount;
        if (player.experienceProgress * (float)ExperienceUtils.getXpValue(player.experienceLevel) <= amount) {
            amount -= player.experienceProgress * (float)ExperienceUtils.getXpValue(player.experienceLevel);
            player.experienceProgress = 1.0f;
            player.experienceLevel--;
        }
        while (ExperienceUtils.getXpValue(player.experienceLevel) < amount) {
            amount -= ExperienceUtils.getXpValue(player.experienceLevel);
            player.experienceLevel--;
        }
        player.experienceProgress -= amount / (float)ExperienceUtils.getXpValue(player.experienceLevel);
    }
}
