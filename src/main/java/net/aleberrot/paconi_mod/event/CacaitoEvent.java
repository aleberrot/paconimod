package net.aleberrot.paconi_mod.event;

import net.aleberrot.paconi_mod.PaconiMod;
import net.aleberrot.paconi_mod.effect.ModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
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
                // Crear instancia del efecto de cacaito
                MobEffectInstance instance = new MobEffectInstance(
                        ModEffects.CACAITO_EFFECT,
                        1200,
                        0,
                        false,
                        true
                );

                // Añadir al jugador efecto de cacaito
                player.addEffect(instance);
                // Enviar mensaje de pedir cacaito
                Component message = player.getName().copy()
                                .append(Component.literal(" esta pidiendo cacaito"))
                                        .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);


                event.getLevel().getServer().getPlayerList().broadcastSystemMessage(
                        message,
                        false
                );

                player.sendSystemMessage(player.getName().copy()
                        .append(Component.literal(" así te quería agarrar perrita, pidiendo cacaito"))
                        .withStyle(ChatFormatting.DARK_RED, ChatFormatting.BOLD)
                );
            }

            // Move mano
            player.swing(event.getHand(), true);
        }
    }
}
