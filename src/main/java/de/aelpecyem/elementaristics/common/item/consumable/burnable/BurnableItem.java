package de.aelpecyem.elementaristics.common.item.consumable.burnable;

import de.aelpecyem.elementaristics.common.item.BaseItem;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;

public abstract class BurnableItem extends BaseItem {
    public BurnableItem(String name) {
        super(name);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.setInvulnerable(true);
        if (entity.isBurning()) {
            burnEffect(stack, entity);
            entity.setInvulnerable(false);
            entity.remove();
        }
        return false;
    }

    public abstract void burnEffect(ItemStack stack, ItemEntity entity);
}
