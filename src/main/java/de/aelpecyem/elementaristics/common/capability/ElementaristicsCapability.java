package de.aelpecyem.elementaristics.common.capability;

import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.common.network.packets.sync.PacketSyncCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElementaristicsCapability implements ICapabilitySerializable<CompoundNBT>, Capability.IStorage<ElementaristicsCapability> {
    @CapabilityInject(ElementaristicsCapability.class)
    public static final Capability<ElementaristicsCapability> CAPABILITY = null;
    private ElementaristicsCapability instance﻿;
    public static final int MAX_MAGAN_BASE = 100;
    public int currentMagan = 0;

    public int ascensionStage = 0; //In starndard ascension: 1 - Knowing, 2 - Destroying, 3 - Rethinking, 4 - Reshaping, 5 - Creating, 6 - Activating

    //todo regen and max magan buffers --- add those later
    //this also counts for souls
    //also: packets
    public ElementaristicsCapability() {
        this.instance﻿ = this;
        //set soul
        //set max magan based on this
    }


    public static ElementaristicsCapability getCapability(PlayerEntity player) {
        return player.getCapability(CAPABILITY).orElse(null);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction direction) {
        return CAPABILITY.orEmpty(capability, LazyOptional.of(() -> this.instance﻿));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return getCapability(cap, null);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) CAPABILITY.getStorage().writeNBT(CAPABILITY, this, null);
    }

    @Override
    public void deserializeNBT(CompoundNBT compoundNBT) {
        CAPABILITY.getStorage().readNBT(CAPABILITY, this, null, compoundNBT);
    }

    @Nullable
    @Override
    public INBT writeNBT(Capability<ElementaristicsCapability> capability, ElementaristicsCapability instance, Direction direction) {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("currentMagan", instance.currentMagan);
        return tag;
    }

    @Override
    public void readNBT(Capability<ElementaristicsCapability> capability, ElementaristicsCapability instance, Direction direction, INBT inbt) {
        CompoundNBT tag = (CompoundNBT) inbt;
        instance.currentMagan = tag.getInt("currentMagan");
    }

    public static class Util {
        public static void setMagan(int amount, @Nonnull PlayerEntity entity) {
            ElementaristicsCapability capability = getCapability(entity);
            capability.currentMagan = amount;
        }

        public static int getMagan(@Nonnull PlayerEntity entity) {
            return getCapability(entity).currentMagan;
        }

        public static void sync(PlayerEntity to, ElementaristicsCapability capability) {
            PacketHandler.Util.sendToPlayer(to, new PacketSyncCapability(capability.serializeNBT()));
        }

        public static void sync(PlayerEntity to) {
            sync(to, ElementaristicsCapability.getCapability(to));
        }
    }
}
