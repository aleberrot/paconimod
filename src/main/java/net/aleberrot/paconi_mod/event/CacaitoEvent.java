package net.aleberrot.paconi_mod.event;

import net.aleberrot.paconi_mod.PaconiMod;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = PaconiMod.MOD_ID)
public class CacaitoEvent {
    @SubscribeEvent
    public static  void onRightClickWithCacao(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();

        // Tiene cacao en la mano
        boolean tieneCacao = player.getItemInHand(event.getHand()).is(Items.COCOA_BEANS);

        // Esta agachado
        boolean estaAgachado = player.isCrouching();

        // Verificar que tenga cacao en la mano usada
        if(tieneCacao && estaAgachado){
            // Del lado del servidor
            if(!event.getLevel().isClientSide){
                // Enviar mensaje de pedir cacaito
                player.sendSystemMessage(player.getName().copy()
                        .append(Component.literal(" esta pidiendo cacaito"))
                        .withStyle(ChatFormatting.GOLD)
                );
            }

            // Move mano
            player.swing(event.getHand(), true);
        }
    }
}
