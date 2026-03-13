package net.aaron.arandommod;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ArandomMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyHandler {

    private static final KeyMapping RANDOM_KEY =
            new KeyMapping("key.arandommod.randomitem", GLFW.GLFW_KEY_R, "key.categories.misc");

    private static long lastUseTime = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        Minecraft mc = Minecraft.getInstance();

        if (RANDOM_KEY.isDown() && mc.player != null) {

            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUseTime < 1) {
                return;
            }

            lastUseTime = currentTime;

            ModMessages.INSTANCE.sendToServer(new RandomItemPacket());
        }
    }

    @Mod.EventBusSubscriber(modid = ArandomMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class KeyRegister {

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(RANDOM_KEY);
        }
    }
}