package de.aelpecyem.elementaristics.common.network.packets;


import de.aelpecyem.elementaristics.common.network.PacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class PacketBase<T extends PacketBase<T>> {
    @Nonnull
    public abstract Encoder<T> encoder();

    @Nonnull
    public abstract Decoder<T> decoder();

    @Nonnull
    public abstract Handler<T> handler();

    public static interface Encoder<T extends PacketBase<T>> extends BiConsumer<T, PacketBuffer> {
    }

    public static interface Decoder<T extends PacketBase<T>> extends Function<PacketBuffer, T> {
    }

    public static interface Handler<T extends PacketBase<T>> extends BiConsumer<T, Supplier<NetworkEvent.Context>> {
        @Override
        default void accept(T t, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context ctx = contextSupplier.get();
            switch (ctx.getDirection().getReceptionSide()) {
                case CLIENT:
                    this.handleClient(t, ctx);
                    break;
                case SERVER:
                    this.handleServer(t, ctx);
                    break;
            }
            ctx.setPacketHandled(true);
        }

        @OnlyIn(Dist.CLIENT)
        default void handleClient(T packet, NetworkEvent.Context context) {
            this.handle(packet, context, LogicalSide.CLIENT);
        }

        default void handleServer(T packet, NetworkEvent.Context context) {
            this.handle(packet, context, LogicalSide.SERVER);
        }

        void handle(T packet, NetworkEvent.Context context, LogicalSide side);
    }

    protected final void replyWith(T packet, NetworkEvent.Context ctx) {
        PacketHandler.HANDLER.reply(packet, ctx);
    }
}
