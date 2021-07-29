package me.athlaeos.progressivelydifficultmobs.managers;

import me.athlaeos.progressivelydifficultmobs.utils.general.Config;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class PluginConfigurationManager {

    private static PluginConfigurationManager manager = null;
    private final ConfigManager configManager;
    private final Config translationConfig;
    private final Config mainConfig;
    private final Config itemConfig;
    private final YamlConfiguration translationYaml;
    private final YamlConfiguration mainYaml;

    private final String goodKarmaGainNegative;
    private final String goodKarmaGainPositive;
    private final String badKarmaGainNegative;
    private final String badKarmaGainPositive;
    private int minKarmaLevel;
    private int maxKarmaLevel;
    private double baseKarma;
    private final double baseBadKarmaMultiplier;
    private final double baseGoodKarmaMultiplier;
    private boolean lockKarma;
    private double karmaBuffer;
    private boolean forceKarma;
    private final double defaultKarmaOnHostileKilled;
    private final double defaultKarmaOnPassiveKilled;
    private final double karmaOnRaidWin;
    private final double karmaOnRaidLoss;
    private final boolean permanentEnableOnToggle;
    private final int maxCurse;
    private double cursedEnemyChance;
    private final boolean vanillaCursed;
    private final boolean cursedDropPrevention;
    private final double curseHealthMultiplier;
    private final double curseDamageMultiplier;
    private final double curseBossHealthMultiplier;
    private final double curseBossDamageMultiplier;
    private final boolean useAnimationRunnables;
    private final boolean useAnimationParticles;
    private final double evilDeathKarmaPenalty;
    private final double goodDeathKarmaPenalty;
    private final boolean evilDeathKarmaPenaltyFractional;
    private final boolean goodDeathKarmaPenaltyFractional;
    private final boolean evilDeathKarmaPenaltyMitigated;
    private final boolean goodDeathKarmaPenaltyMitigated;
    private final boolean curseDropDoubling;
    private final double curseEXPMultiplier;

    private final String PAPIKarmaDisabled;
    private final String PAPIKarmaEnabled;

    private final String errorNoPermission;
    private final String baseCommandHelp;
    private final String commandNotFoundError;
    private final String invalidNumberError;
    private final String playerNotFoundError;
    private final String playerTooMuchCurseError;
    private final String playerCantUseItemError;

    private final String translationPermissions;
    private final String translationYes;
    private final String translationNo;

    private final List<String> evilRevealingEntity;
    private final List<String> evilRevealingPlayer;

    private final String karmaEnabledByDefault;
    private final String karmaCantBeDisabled;
    private final String karmaEnabledMessage;
    private final String karmaDisabledMessage;
    private final String toggleKarmaCommandDescription;

    private final String setKarmaMessage;
    private final String setKarmaDescription;

    private final String setBadKarmaMultiplierMessage;
    private final String setGoodKarmaMultiplierMessage;
    private final String setKarmaMultiplierDescription;

    private final String setCurseMessage;
    private final String setCurseDescription;

    private final String addKarmaMessage;
    private final String addKarmaDescription;

    private final String addCurseMessage;
    private final String addCurseDescription;

    private final String saveChangesMessage;
    private final String saveChangesCommandDescription;

    private final String pluginReloadedMessage;
    private final String reloadCommandDescription;

    private final String manageLootTablesCommandDescription;

    private final String manageMonstersCommandDescription;

    private final String localDifficultyMessage;
    private final String localDifficultyCommandDescription;

    private final String getKarmaMessage;
    private final String getKarmaCommandDescription;

    private final String getKarmaMultiplierMessage;
    private final String getKarmaMultiplierCommandDescription;

    private final String getCurseMessage;
    private final String getCurseCommandDescription;

    private final String createMonsterInvalidMonsterError;
    private final String createMonsterInvalidNameError;
    private final String createMonsterMessage;
    private final String createMonsterCommandDescription;

    private final String createLootTableInvalidNameError;
    private final String createLootTableInvalidIconError;
    private final String createLootTableCommandDescription;

    private final String getItemMessage;
    private final String getItemCommandDescription;

    private final String getSpawnEggInvalidNameError;
    private final String getSpawnEggCommandDescription;

    private final String getCooldownMessage;
    private final String setCooldownMessage;
    private final String cooldownCommandDescription;
    private final String invalidCooldownKeyError;

    private final String removePerkSuccessMessage;
    private final String removePerkErrorMessage;
    private final String addPerkSuccessMessage;
    private final String addPerkErrorMessage;
    private final String getPerksMessage;
    private final String getPerksSyntax;
    private final String perksCommandDescription;

    private final String changeNickCommandDescription;

    public PluginConfigurationManager() {
        configManager = ConfigManager.getInstance();
        translationConfig = configManager.getConfig("translations.yml");
        mainConfig = configManager.getConfig("config.yml");
        itemConfig = configManager.getConfig("items.yml");

        translationYaml = translationConfig.get();
        mainYaml = mainConfig.get();

        translationConfig.copyDefaults(true);
        // Following settings are mostly used for PlayerKarmaManager
        badKarmaGainNegative = mainYaml.getString("bad_karma_gain_negative");
        badKarmaGainPositive = mainYaml.getString("bad_karma_gain_positive");
        goodKarmaGainNegative = mainYaml.getString("good_karma_gain_negative");
        goodKarmaGainPositive = mainYaml.getString("good_karma_gain_positive");
        minKarmaLevel = mainYaml.getInt("min_karma_lv");
        maxKarmaLevel = mainYaml.getInt("max_karma_lv");
        baseKarma = mainYaml.getDouble("default_karma");
        baseBadKarmaMultiplier = mainYaml.getDouble("default_bad_karma_multiplier");
        baseGoodKarmaMultiplier = mainYaml.getDouble("default_good_karma_multiplier");
        lockKarma = mainYaml.getBoolean("i_have_chosen_my_fate");
        defaultKarmaOnHostileKilled = mainYaml.getDouble("karma_onkill_hostile");
        defaultKarmaOnPassiveKilled = mainYaml.getDouble("karma_onkill_passive");
        karmaOnRaidWin = mainYaml.getDouble("karma_raid_victory");
        karmaOnRaidLoss = mainYaml.getDouble("karma_raid_loss");
        karmaBuffer = mainYaml.getDouble("karma_buffer");
        permanentEnableOnToggle = mainYaml.getBoolean("permanent_enable_on_toggle");
        maxCurse = mainYaml.getInt("max_curse");
        cursedEnemyChance = mainYaml.getDouble("curse_chance");
        vanillaCursed = mainYaml.getBoolean("vanilla_cursed");
        cursedDropPrevention = mainYaml.getBoolean("cursed_drop_prevention");
        curseHealthMultiplier = mainYaml.getDouble("cursed_health_multiplier");
        curseDamageMultiplier = mainYaml.getDouble("cursed_damage_multiplier");
        curseBossHealthMultiplier = mainYaml.getDouble("cursed_boss_health_multiplier");
        curseBossDamageMultiplier = mainYaml.getDouble("cursed_boss_damage_multiplier");
        evilDeathKarmaPenalty = mainYaml.getDouble("karma_influence_player_evil.karma_influence");
        evilDeathKarmaPenaltyFractional = mainYaml.getBoolean("karma_influence_player_evil.fractional");
        evilDeathKarmaPenaltyMitigated = mainYaml.getBoolean("karma_influence_player_evil.mitigated");
        goodDeathKarmaPenalty = mainYaml.getDouble("karma_influence_player_good.karma_influence");
        goodDeathKarmaPenaltyFractional = mainYaml.getBoolean("karma_influence_player_good.fractional");
        goodDeathKarmaPenaltyMitigated = mainYaml.getBoolean("karma_influence_player_good.mitigated");
        curseDropDoubling = mainYaml.getBoolean("cursed_drop_doubling");
        curseEXPMultiplier = mainYaml.getDouble("cursed_exp_multiplier");

        if (cursedEnemyChance < 0) cursedEnemyChance = 0D;

        if (karmaBuffer < 0) {
            this.setKarmaBuffer(0D);
            System.out.println("[PDM] Config error: karma_buffer was set below 0, reset to 0");
        }

        if (minKarmaLevel > 0) {
            minKarmaLevel = 0;
            System.out.println("[PDM] Config error: min_karma_lv was set above 0, reset to 0");
        }
        if (maxKarmaLevel < 0) {
            maxKarmaLevel = 0;
            System.out.println("[PDM] Config error: max_karma_lv was set below 0, reset to 0");
        }

        forceKarma = mainYaml.getBoolean("force_karma");
        useAnimationRunnables = mainYaml.getBoolean("animation_runnables");
        useAnimationParticles = mainYaml.getBoolean("animation_particles");

        // Translation options
        PAPIKarmaDisabled = translationYaml.getString("papi_karma_not_enabled");
        PAPIKarmaEnabled = translationYaml.getString("papi_karma_enabled");

        errorNoPermission = translationYaml.getString("command_no_permission");
        baseCommandHelp = translationYaml.getString("pdm_base_command_help");
        commandNotFoundError = translationYaml.getString("command_invalid");
        invalidNumberError = translationYaml.getString("invalid_number_amount");
        playerNotFoundError = translationYaml.getString("player_not_found");
        playerTooMuchCurseError = translationYaml.getString("warning_player_curse_exceeded_limit");
        playerCantUseItemError = translationYaml.getString("warning_player_item_duration_active");

        translationPermissions = translationYaml.getString("translation_permissions");
        translationYes = translationYaml.getString("translation_yes");
        translationNo = translationYaml.getString("translation_no");

        evilRevealingEntity = translationYaml.getStringList("evil_revealing_messages_entity");
        evilRevealingPlayer = translationYaml.getStringList("evil_revealing_messages_player");

        karmaEnabledByDefault = translationYaml.getString("warning_karma_enabled_by_default");
        karmaCantBeDisabled = translationYaml.getString("warning_karma_cant_be_disabled");
        karmaEnabledMessage = translationYaml.getString("toggle_karma_message_enabled");
        karmaDisabledMessage = translationYaml.getString("toggle_karma_message_disabled");
        toggleKarmaCommandDescription = translationYaml.getString("toggle_karma_command_description");

        getCooldownMessage = translationYaml.getString("view_cooldown_message");
        setCooldownMessage = translationYaml.getString("edit_cooldown_message");
        cooldownCommandDescription = translationYaml.getString("invalid_cooldowns_key_error");
        invalidCooldownKeyError = translationYaml.getString("manage_cooldowns_command_description");

        setKarmaMessage = translationYaml.getString("set_karma_message");
        setKarmaDescription = translationYaml.getString("set_karma_command_description");

        setBadKarmaMultiplierMessage = translationYaml.getString("set_bad_karma_multiplier_message");
        setGoodKarmaMultiplierMessage = translationYaml.getString("set_good_karma_multiplier_message");
        setKarmaMultiplierDescription = translationYaml.getString("set_karma_multiplier_command_description");

        setCurseMessage = translationYaml.getString("set_curse_message");
        setCurseDescription = translationYaml.getString("set_curse_command_description");

        addKarmaMessage = translationYaml.getString("add_karma_message");
        addKarmaDescription = translationYaml.getString("add_karma_command_description");

        addCurseMessage = translationYaml.getString("add_curse_message");
        addCurseDescription = translationYaml.getString("add_curse_command_description");

        saveChangesMessage = translationYaml.getString("saved_changes_message");
        saveChangesCommandDescription = translationYaml.getString("save_changes_command_description");

        pluginReloadedMessage = translationYaml.getString("reloaded_plug_command");
        reloadCommandDescription = translationYaml.getString("reload_command_description");

        manageLootTablesCommandDescription = translationYaml.getString("manage_loot_tables_command_description");

        manageMonstersCommandDescription = translationYaml.getString("manage_monsters_command_description");

        localDifficultyMessage = translationYaml.getString("local_difficulty_message");
        localDifficultyCommandDescription = translationYaml.getString("local_difficulty_command_description");

        getKarmaMessage = translationYaml.getString("get_karma_message");
        getKarmaCommandDescription = translationYaml.getString("get_karma_command_description");

        getKarmaMultiplierMessage = translationYaml.getString("get_karma_multiplier_message");
        getKarmaMultiplierCommandDescription = translationYaml.getString("get_karma_multiplier_command_description");

        getCurseMessage = translationYaml.getString("get_curse_message");
        getCurseCommandDescription = translationYaml.getString("get_curse_command_description");

        createMonsterInvalidMonsterError = translationYaml.getString("create_monster_invalid_monster_error_message");
        createMonsterInvalidNameError = translationYaml.getString("create_monster_already_exists_error_message");
        createMonsterMessage = translationYaml.getString("create_monster_message");
        createMonsterCommandDescription = translationYaml.getString("create_monster_command_description");

        createLootTableInvalidNameError = translationYaml.getString("create_loot_table_already_exists_error_message");
        createLootTableInvalidIconError = translationYaml.getString("create_loot_table_invalid_material_error_message");
        createLootTableCommandDescription = translationYaml.getString("create_loot_table_command_description");

        getItemMessage = translationYaml.getString("get_item_message");
        getItemCommandDescription = translationYaml.getString("get_item_command_description");

        getSpawnEggInvalidNameError = translationYaml.getString("get_spawn_egg_error_invalid_name");
        getSpawnEggCommandDescription = translationYaml.getString("get_spawn_egg_command_description");

        removePerkSuccessMessage = translationYaml.getString("remove_player_perk_message");
        removePerkErrorMessage = translationYaml.getString("remove_player_perk_error");
        addPerkSuccessMessage = translationYaml.getString("add_player_perk_message");
        addPerkErrorMessage = translationYaml.getString("add_player_perk_error");
        getPerksMessage = translationYaml.getString("get_player_perks_message");
        getPerksSyntax = translationYaml.getString("get_player_perks_item_syntax");
        perksCommandDescription = translationYaml.getString("perks_command_description");

        changeNickCommandDescription = translationYaml.getString("change_nick_command_description");

        translationConfig.copyDefaults(true);
        mainConfig.copyDefaults(true);
        itemConfig.copyDefaults(true);
    }

    public static PluginConfigurationManager getInstance() {
        if (manager == null) {
            manager = new PluginConfigurationManager();
        }
        return manager;
    }

    public void reload() {
        manager = new PluginConfigurationManager();
    }

    public double getBaseKarma() {
        return baseKarma;
    }

    public void setBaseKarma(double baseKarma) {
        this.baseKarma = baseKarma;
    }

    public double getBaseBadKarmaMultiplier() {
        return baseBadKarmaMultiplier;
    }

    public double getKarmaBuffer() {
        return karmaBuffer;
    }

    public void setKarmaBuffer(double karmaBuffer) {
        this.karmaBuffer = karmaBuffer;
    }

    public boolean isLockKarma() {
        return lockKarma;
    }

    public void setLockKarma(boolean lockKarma) {
        this.lockKarma = lockKarma;
    }

    public boolean isForceKarma() {
        return forceKarma;
    }

    public void setForceKarma(boolean forceKarma) {
        this.forceKarma = forceKarma;
    }

    public String getBaseCommandHelp() {
        return baseCommandHelp;
    }

    public String getErrorNoPermission() {
        return errorNoPermission;
    }

    public String getCommandNotFoundError() {
        return commandNotFoundError;
    }

    public String getKarmaEnabledByDefault() {
        return karmaEnabledByDefault;
    }

    public String getKarmaEnabledMessage() {
        return karmaEnabledMessage;
    }

    public String getKarmaDisabledMessage() {
        return karmaDisabledMessage;
    }

    public String getToggleKarmaCommandDescription() {
        return toggleKarmaCommandDescription;
    }

    public String getInvalidNumberError() {
        return invalidNumberError;
    }

    public String getSetKarmaMessage() {
        return setKarmaMessage;
    }

    public String getPlayerNotFoundError() {
        return playerNotFoundError;
    }

    public String getSetKarmaDescription() {
        return setKarmaDescription;
    }

    public String getSaveChangesMessage() {
        return saveChangesMessage;
    }

    public String getSaveChangesCommandDescription() {
        return saveChangesCommandDescription;
    }

    public String getPluginReloadedMessage() {
        return pluginReloadedMessage;
    }

    public String getReloadCommandDescription() {
        return reloadCommandDescription;
    }

    public String getManageLootTablesCommandDescription() {
        return manageLootTablesCommandDescription;
    }

    public String getManageMonstersCommandDescription() {
        return manageMonstersCommandDescription;
    }

    public String getLocalDifficultyMessage() {
        return localDifficultyMessage;
    }

    public String getLocalDifficultyCommandDescription() {
        return localDifficultyCommandDescription;
    }

    public String getGetKarmaMessage() {
        return getKarmaMessage;
    }

    public String getGetKarmaCommandDescription() {
        return getKarmaCommandDescription;
    }

    public String getCreateMonsterCommandDescription() {
        return createMonsterCommandDescription;
    }

    public String getCreateMonsterInvalidMonsterError() {
        return createMonsterInvalidMonsterError;
    }

    public String getCreateMonsterInvalidNameError() {
        return createMonsterInvalidNameError;
    }

    public String getCreateMonsterMessage() {
        return createMonsterMessage;
    }

    public String getCreateLootTableCommandDescription() {
        return createLootTableCommandDescription;
    }

    public String getCreateLootTableInvalidIconError() {
        return createLootTableInvalidIconError;
    }

    public String getCreateLootTableInvalidNameError() {
        return createLootTableInvalidNameError;
    }

    public String getTranslationPermissions() {
        return translationPermissions;
    }

    public boolean isPermanentEnableOnToggle() {
        return permanentEnableOnToggle;
    }

    public String getKarmaCantBeDisabled() {
        return karmaCantBeDisabled;
    }

    public int getMaxCurse() {
        return maxCurse;
    }

    public double getCurseDamageMultiplier() {
        return curseDamageMultiplier;
    }

    public double getCursedEnemyChance() {
        return cursedEnemyChance;
    }

    public double getCurseHealthMultiplier() {
        return curseHealthMultiplier;
    }

    public boolean isVanillaCursed() {
        return vanillaCursed;
    }

    public double getCurseBossDamageMultiplier() {
        return curseBossDamageMultiplier;
    }

    public double getCurseBossHealthMultiplier() {
        return curseBossHealthMultiplier;
    }

    public boolean isCursedDropPrevention() {
        return cursedDropPrevention;
    }

    public String getPlayerCantUseItemError() {
        return playerCantUseItemError;
    }

    public String getPlayerTooMuchCurseError() {
        return playerTooMuchCurseError;
    }

    public boolean useAnimationParticles() {
        return useAnimationParticles;
    }

    public boolean useAnimationRunnables() {
        return useAnimationRunnables;
    }

    public String getTranslationNo() {
        return translationNo;
    }

    public String getTranslationYes() {
        return translationYes;
    }

    public List<String> getEvilRevealingEntity() {
        return evilRevealingEntity;
    }

    public List<String> getEvilRevealingPlayer() {
        return evilRevealingPlayer;
    }

    public String getAddKarmaDescription() {
        return addKarmaDescription;
    }

    public String getAddKarmaMessage() {
        return addKarmaMessage;
    }

    public String getAddCurseDescription() {
        return addCurseDescription;
    }

    public String getAddCurseMessage() {
        return addCurseMessage;
    }

    public String getSetCurseDescription() {
        return setCurseDescription;
    }

    public String getSetCurseMessage() {
        return setCurseMessage;
    }

    public String getGetCurseMessage() {
        return getCurseMessage;
    }

    public String getGetCurseCommandDescription() {
        return getCurseCommandDescription;
    }

    public double getKarmaOnRaidLoss() {
        return karmaOnRaidLoss;
    }

    public double getKarmaOnRaidWin() {
        return karmaOnRaidWin;
    }

    public double getDefaultKarmaOnHostileKilled() {
        return defaultKarmaOnHostileKilled;
    }

    public double getDefaultKarmaOnPassiveKilled() {
        return defaultKarmaOnPassiveKilled;
    }

    public String getGetItemCommandDescription() {
        return getItemCommandDescription;
    }

    public String getGetItemMessage() {
        return getItemMessage;
    }

    public String getSetKarmaMultiplierDescription() {
        return setKarmaMultiplierDescription;
    }

    public String getSetBadKarmaMultiplierMessage() {
        return setBadKarmaMultiplierMessage;
    }

    public double getBaseGoodKarmaMultiplier() {
        return baseGoodKarmaMultiplier;
    }

    public String getSetGoodKarmaMultiplierMessage() {
        return setGoodKarmaMultiplierMessage;
    }

    public String getGetKarmaMultiplierMessage() {
        return getKarmaMultiplierMessage;
    }

    public String getGetKarmaMultiplierCommandDescription() {
        return getKarmaMultiplierCommandDescription;
    }

    public String getGetSpawnEggCommandDescription() {
        return getSpawnEggCommandDescription;
    }

    public String getGetSpawnEggInvalidNameError() {
        return getSpawnEggInvalidNameError;
    }

    public String getAddPerkErrorMessage() {
        return addPerkErrorMessage;
    }

    public String getAddPerkSuccessMessage() {
        return addPerkSuccessMessage;
    }

    public String getRemovePerkErrorMessage() {
        return removePerkErrorMessage;
    }

    public String getRemovePerkSuccessMessage() {
        return removePerkSuccessMessage;
    }

    public String getGetPerksMessage() {
        return getPerksMessage;
    }

    public String getGetPerksSyntax() {
        return getPerksSyntax;
    }

    public String getPerksCommandDescription() {
        return perksCommandDescription;
    }

    public String getGoodKarmaGainNegative() {
        return goodKarmaGainNegative;
    }

    public String getGoodKarmaGainPositive() {
        return goodKarmaGainPositive;
    }

    public String getBadKarmaGainNegative() {
        return badKarmaGainNegative;
    }

    public String getBadKarmaGainPositive() {
        return badKarmaGainPositive;
    }

    public int getMinKarmaLevel() {
        return minKarmaLevel;
    }

    public int getMaxKarmaLevel() {
        return maxKarmaLevel;
    }

    public double getEvilDeathKarmaPenalty() {
        return evilDeathKarmaPenalty;
    }

    public double getGoodDeathKarmaPenalty() {
        return goodDeathKarmaPenalty;
    }

    public boolean isEvilDeathKarmaPenaltyFractional() {
        return evilDeathKarmaPenaltyFractional;
    }

    public boolean isGoodDeathKarmaPenaltyFractional() {
        return goodDeathKarmaPenaltyFractional;
    }

    public boolean isEvilDeathKarmaPenaltyMitigated() {
        return evilDeathKarmaPenaltyMitigated;
    }

    public boolean isGoodDeathKarmaPenaltyMitigated() {
        return goodDeathKarmaPenaltyMitigated;
    }

    public double getCurseEXPMultiplier() {
        return curseEXPMultiplier;
    }

    public boolean isCurseDropDoubling() {
        return curseDropDoubling;
    }

    public String getChangeNickCommandDescription() {
        return changeNickCommandDescription;
    }

    public String getPAPIKarmaDisabled() {
        return PAPIKarmaDisabled;
    }

    public String getPAPIKarmaEnabled() {
        return PAPIKarmaEnabled;
    }

    public String getCooldownCommandDescription() {
        return cooldownCommandDescription;
    }

    public String getGetCooldownMessage() {
        return getCooldownMessage;
    }

    public String getInvalidCooldownKeyError() {
        return invalidCooldownKeyError;
    }

    public String getSetCooldownMessage() {
        return setCooldownMessage;
    }
}
