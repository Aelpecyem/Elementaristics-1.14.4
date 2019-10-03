package de.aelpecyem.elementaristics.common.capability;

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

public class CapabilityElementarstics implements ICapabilitySerializable<CompoundNBT>, Capability.IStorage<CapabilityElementarstics> {
    @CapabilityInject(CapabilityElementarstics.class)
    public static final Capability<CapabilityElementarstics> CAPABILITY = null;
    private CapabilityElementarstics instance﻿;
    public int maxMagan = 100;
    public int currentMagan = 0;

    //todo regen and max magan buffers --- add those later
    //this also counts for souls
    //also: packets
    public CapabilityElementarstics() {
        this.instance﻿ = this;
        //set soul
        //set max magan based on this
    }


    //Handler stuff
    public static CapabilityElementarstics getCapability(PlayerEntity player) {
        return player.getCapability(CAPABILITY).orElse(null);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction direction) {
        return CAPABILITY.orEmpty(capability, LazyOptional.of(() -> this.instance﻿));//LazyOptional.of(() -> CAPABILITY).cast(); //CAPABILITY).cast();
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
    public INBT writeNBT(Capability<CapabilityElementarstics> capability, CapabilityElementarstics instance, Direction direction) {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("currentMagan", instance.currentMagan);
        return tag;
    }

    @Override
    public void readNBT(Capability<CapabilityElementarstics> capability, CapabilityElementarstics instance, Direction direction, INBT inbt) {
        CompoundNBT tag = (CompoundNBT) inbt;
        instance.currentMagan = tag.getInt("currentMagan");
    }
}
