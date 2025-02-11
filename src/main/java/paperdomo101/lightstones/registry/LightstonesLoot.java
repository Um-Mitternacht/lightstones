package paperdomo101.lightstones.registry;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import paperdomo101.lightstones.Lightstones;

public class LightstonesLoot {

    private static final Identifier PIGLIN_BARTERING_LOOT_TABLE_ID = new Identifier("minecraft", "gameplay/piglin_bartering");
    private static final Identifier TREASURE_FISHING_LOOT_TABLE_ID = new Identifier("minecraft", "gameplay/fishing/treasure");

    public static void init() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {

            if (PIGLIN_BARTERING_LOOT_TABLE_ID.equals(id)) {
                if (Lightstones.CONFIG.piglinsBarterBlightstones) {
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                            .rolls(UniformLootNumberProvider.create(Lightstones.CONFIG.piglinBlightstoneChanceMin, Lightstones.CONFIG.fishLightstonesChanceMax))
                            .with(ItemEntry.builder(LightstonesItems.BLIGHTSTONE));
                    supplier.pool(poolBuilder);
                }
            }
            if (TREASURE_FISHING_LOOT_TABLE_ID.equals(id) && Lightstones.CONFIG.lightstonesAreFishingTreasure) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(UniformLootNumberProvider.create(Lightstones.CONFIG.fishLightstonesChanceMin, Lightstones.CONFIG.fishLightstonesChanceMax))
                        .with(ItemEntry.builder(LightstonesItems.LIGHTSTONE));

                supplier.pool(poolBuilder);
            }
        });
    }
}
