package de.aelpecyem.elementaristics.common.network;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.network.packets.PacketBase;
import de.aelpecyem.elementaristics.common.network.packets.sync.PacketSyncCapability;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import javax.annotation.Nonnull;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.function.Supplier;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "0";
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Elementaristics.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static int nextId = 0;

    public static int next() {
        return nextId++;
    }

    public static void initPackets() {
        registerMessage(PacketSyncCapability::new);
    }

    private static <T extends PacketBase<T>> void registerMessage(Supplier<T> pktSupplier) {
        T packet = pktSupplier.get();
        HANDLER.messageBuilder((Class<T>) packet.getClass(), next())
                .encoder(packet.encoder())
                .decoder(packet.decoder())
                .consumer(packet.handler())
                .add();
    }

    //methods taken from https://github.com/HellFirePvP/AstralSorcery/blob/1.14.3-indev/src/main/java/hellfirepvp/astralsorcery/common/util/data/ByteBufUtils.java
    public static class Util {
        public static void sendToPlayer(PlayerEntity player, PacketBase packet) {
            if (player instanceof ServerPlayerEntity) {
                HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), packet);
            }
        }

        public static void writeNBTTag(PacketBuffer byteBuf, @Nonnull CompoundNBT tag) {
            try (DataOutputStream dos = new DataOutputStream(new ByteBufOutputStream(byteBuf))) {
                CompressedStreamTools.write(tag, dos);
            } catch (Exception exc) {
            }
        }

        @Nonnull
        public static CompoundNBT readNBTTag(PacketBuffer byteBuf) {
            try (DataInputStream dis = new DataInputStream(new ByteBufInputStream(byteBuf))) {
                return CompressedStreamTools.read(dis);
            } catch (Exception exc) {
            }
            throw new IllegalStateException("Could not load NBT Tag from incoming byte buffer!");
        }
    }
}
