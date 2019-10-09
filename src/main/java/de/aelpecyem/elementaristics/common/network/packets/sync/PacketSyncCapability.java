package de.aelpecyem.elementaristics.common.network.packets.sync;

import de.aelpecyem.elementaristics.common.capability.ElementaristicsCapability;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.common.network.packets.PacketBase;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;

public class PacketSyncCapability extends PacketBase<PacketSyncCapability> {
    private CompoundNBT data;

    public PacketSyncCapability() {
    }

    public PacketSyncCapability(CompoundNBT data) {
        this.data = data;
    }

    @Nonnull
    @Override
    public Encoder<PacketSyncCapability> encoder() {
        return (pkt, buffer) -> PacketHandler.Util.writeNBTTag(buffer, pkt.data);
    }

    @Nonnull
    @Override
    public Decoder<PacketSyncCapability> decoder() {
        return p -> new PacketSyncCapability(PacketHandler.Util.readNBTTag(p));
    }

    @Nonnull
    @Override
    public Handler<PacketSyncCapability> handler() {
        return new Handler<PacketSyncCapability>() {
            @Override
            public void handle(PacketSyncCapability packet, NetworkEvent.Context context, LogicalSide side) {
            }

            @Override
            public void handleClient(PacketSyncCapability packet, NetworkEvent.Context context) {
                ElementaristicsCapability cap = ElementaristicsCapability.getCapability(Minecraft.getInstance().player);
                cap.deserializeNBT(packet.data);
            }
        };
    }
}
