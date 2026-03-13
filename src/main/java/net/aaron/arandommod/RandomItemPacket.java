package net.aaron.arandommod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class RandomItemPacket {

    public RandomItemPacket() {}

    public RandomItemPacket(FriendlyByteBuf buf) {}

    public void toBytes(FriendlyByteBuf buf) {}

    public static void handle(RandomItemPacket msg, Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayer player = ctx.get().getSender();

            if (player == null) return;

            List<Item> items = new ArrayList<>(ForgeRegistries.ITEMS.getValues());

            Random rand = new Random();
            Item randomItem = items.get(rand.nextInt(items.size()));

            player.addItem(new ItemStack(randomItem));

        });

        ctx.get().setPacketHandled(true);
    }
}