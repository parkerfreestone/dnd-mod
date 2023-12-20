package dev.dndmod.core.entity;

import dev.dndmod.core.DNDMod;
import dev.dndmod.core.entity.custom.BombProjectileEntity;
import dev.dndmod.core.entity.custom.OrcEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DNDMod.MOD_ID);

    public static final RegistryObject<EntityType<OrcEntity>> ORC =
            ENTITY_TYPES.register("orc",
                    () -> EntityType.Builder.of(OrcEntity::new, MobCategory.MONSTER)
                            .sized(1f, 2.5f).build("orc"));

    public static final RegistryObject<EntityType<BombProjectileEntity>> BOMB_PROJECTILE =
            ENTITY_TYPES.register("bomb_projectile",
                    () -> EntityType.Builder.<BombProjectileEntity>of(BombProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f).build("bomb_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
