package de.aelpecyem.elementaristics.common.capability;

import de.aelpecyem.elementaristics.common.capability.ascension.AscensionProgress;
import de.aelpecyem.elementaristics.common.capability.magan.MaganStorage;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.common.network.packets.sync.PacketSyncCapability;
import de.aelpecyem.elementaristics.util.SoulHelper;
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
import java.util.Random;

public class ElementaristicsCapability implements ICapabilitySerializable<CompoundNBT>, Capability.IStorage<ElementaristicsCapability> {
    @CapabilityInject(ElementaristicsCapability.class)
    public static final Capability<ElementaristicsCapability> CAPABILITY = null;
    private ElementaristicsCapability instance﻿;
    public static final int MAX_MAGAN_BASE = 100;

    public AscensionProgress ascensionProgress;
    public MaganStorage maganStorage;

    //todo regen and max magan buffers --- add those later
    public ElementaristicsCapability() {
        this.instance﻿ = this;
        this.maganStorage = new MaganStorage();
        this.ascensionProgress = new AscensionProgress();

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
        if (ascensionProgress.getSoul() == null){
            ascensionProgress.setSoul(SoulHelper.getRandomSoulWithChance());
        }
        ascensionProgress.writeToNBT(tag);
        maganStorage.writeToNBT(tag);

        return tag;
    }

    @Override
    public void readNBT(Capability<ElementaristicsCapability> capability, ElementaristicsCapability instance, Direction direction, INBT inbt) {
        CompoundNBT tag = (CompoundNBT) inbt;
        ascensionProgress = new AscensionProgress();
        maganStorage = new MaganStorage();
        ascensionProgress.readFromNBT(tag);
        maganStorage.readFromNBT(tag);
    }

    public void readUpdatePacket(CompoundNBT data, int mode) {
        if (mode == 1) {
            maganStorage = new MaganStorage();
            maganStorage.readFromNBT(data);
            maganStorage.isDirty = false;
        } else if (mode == 2) {
            ascensionProgress = new AscensionProgress();
            ascensionProgress.readFromNBT(data);
            ascensionProgress.isDirty = false;
        } else {
            deserializeNBT(data);
            maganStorage.isDirty = false;
            ascensionProgress.isDirty = false;
        }
    }

    public CompoundNBT writeUpdatePacket(int mode) {
        if (mode == 1) {
            maganStorage.isDirty = false;
            return maganStorage.writeToNBT(new CompoundNBT());
        } else if (mode == 2) {
            ascensionProgress.isDirty = false;
            return ascensionProgress.writeToNBT(new CompoundNBT());
        } else {
            maganStorage.isDirty = false;
            ascensionProgress.isDirty = false;
            return serializeNBT();
        }
    }

    public int getMode() {
        int mode = -1;
        if (maganStorage.isDirty) {
            mode = 1;
            if (ascensionProgress.isDirty) return 0;
        } else if (ascensionProgress.isDirty) {
            mode = 2;
        }
        return mode;
    }

    public static class Util {
        public static void setMagan(int amount, @Nonnull PlayerEntity entity) {
            ElementaristicsCapability capability = getCapability(entity);
            capability.maganStorage.setCurrentMagan(amount);
        }

        public static int getMagan(@Nonnull PlayerEntity entity) {
            return getCapability(entity).maganStorage.getCurrentMagan();
        }

        public static void syncAll(PlayerEntity to, ElementaristicsCapability capability) {
            int mode = capability.getMode();
            if (mode >= 0)
                PacketHandler.Util.sendToPlayer(to, new PacketSyncCapability(capability.writeUpdatePacket(mode), mode));
        }

        public static void syncAll(PlayerEntity to) {
            syncAll(to, ElementaristicsCapability.getCapability(to));
        }
    }
}
