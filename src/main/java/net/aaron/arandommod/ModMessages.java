package net.aaron.arandommod;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;

    public static void register() {

        INSTANCE = NetworkRegistry.newSimpleChannel(
                ResourceLocation.fromNamespaceAndPath(ArandomMod.MOD_ID, "messages"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        int id = 0;

        INSTANCE.registerMessage(
                id++,
                RandomItemPacket.class,
                RandomItemPacket::toBytes,
                RandomItemPacket::new,
                RandomItemPacket::handle
        );
    }
}