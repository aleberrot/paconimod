package net.aleberrot.paconi_mod.event;

import net.aleberrot.paconi_mod.PaconiMod;
import net.aleberrot.paconi_mod.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = PaconiMod.MOD_ID)
public class HammerEvent {
    /*
    @SubscribeEvent
    public static void onHammerBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getPlayer() instanceof ServerPlayer serverPlayer) {
            ItemStack mainHandItem = serverPlayer.getMainHandItem();

            // Verificar si lo que tiene en la mano es un Hammer
            if (mainHandItem.getItem() instanceof HammerItem hammer) {
                BlockPos initialBlockPos = event.getPos();

                // Obtenemos la lista de bloques 3x3 (rango 1 a cada lado)
                // Usamos una pequeña precaución para evitar bucles infinitos
                List<BlockPos> extraBlocks = HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer);

                for (BlockPos pos : extraBlocks) {
                    if (pos.equals(initialBlockPos)) continue;

                    // Romper el bloque y soltar los items (si el martillo puede picarlo)
                    serverPlayer.gameMode.destroyBlock(pos);

                    // Opcional: Gastar durabilidad por cada bloque extra
                    // mainHandItem.hurtAndBreak(1, serverPlayer, LivingEntity.getSlotForHand(serverPlayer.getUsedItemHand()));
                }
            }
        }
    }
    */

    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
}
