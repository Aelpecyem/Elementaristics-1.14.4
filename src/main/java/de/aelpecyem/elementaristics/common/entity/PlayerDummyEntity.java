package de.aelpecyem.elementaristics.common.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.HandSide;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class PlayerDummyEntity extends CreatureEntity {
    private static final DataParameter<String> PLAYER_UUID = EntityDataManager.createKey(PlayerDummyEntity.class, DataSerializers.STRING);

    public PlayerDummyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }


    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(PLAYER_UUID, "");
    }

    @Override
    public void tick() {
        if (getPlayer() == null){
            remove(false);
        }else{
            //check if the dimension is MIND
            setPose(Pose.SLEEPING);
        }
        super.tick();
    }

    @Nullable
    @Override
    public Direction getBedDirection() {
        return Direction.EAST; //this will change later
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (getPlayer() != null){
            bringPlayerBack();
        }
        return super.attackEntityFrom(source, amount);
    }

    public void bringPlayerBack(){
        PlayerEntity player = getPlayer();
        if (player.dimension != dimension) player.dimension = dimension;
        player.setPositionAndRotation(posX, posY, posZ, rotationYaw, rotationPitch);
        player.setHealth(getHealth());
        remove();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public UUID getPlayerUUID() {
        return this.dataManager.get(PLAYER_UUID).isEmpty() ? null : UUID.fromString(dataManager.get(PLAYER_UUID));
    }

    public void setPlayerUUID(UUID uuid){
        this.dataManager.set(PLAYER_UUID, uuid != null ? uuid.toString() : "");
    }

    public PlayerEntity getPlayer(){
        return getPlayerUUID() != null ? world.getPlayerByUuid(getPlayerUUID()) : null;
    }

    public void setPlayer(PlayerEntity player){
        if (player == null) setPlayerUUID(null);

        this.dataManager.set(PLAYER_UUID, player.getUniqueID().toString());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.getPlayerUUID() == null) {
            compound.putString("OwnerUUID", "");
        } else {
            compound.putString("OwnerUUID", this.getPlayerUUID().toString());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        String s;
        if (compound.contains("OwnerUUID", 8)) {
            s = compound.getString("OwnerUUID");
        } else {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }
        if (!s.isEmpty()) {
            this.setPlayerUUID(UUID.fromString(s));
        }
    }
}
