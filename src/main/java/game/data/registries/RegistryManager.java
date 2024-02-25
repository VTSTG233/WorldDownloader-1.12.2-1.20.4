package game.data.registries;

import game.data.chunk.BlockEntityRegistry;
import game.data.container.ItemRegistry;
import game.data.container.MenuRegistry;
import game.data.villagers.VillagerProfessionRegistry;
import game.data.villagers.VillagerTypeRegistry;

import java.io.IOException;

public class RegistryManager {
    // 使用 volatile 关键字保证 instance 的可见性
    private static volatile RegistryManager instance;
    private MenuRegistry menuRegistry;
    private ItemRegistry itemRegistry;
    private BlockEntityRegistry blockEntityRegistry;
    private VillagerProfessionRegistry villagerProfessionRegistry;
    private VillagerTypeRegistry villagerTypeRegistry;

    private RegistryManager() { }

    public static RegistryManager getInstance() {
        // 使用双重检查锁实现线程安全的单例模式
        if (instance == null) {
            synchronized (RegistryManager.class) {
                if (instance == null) {
                    instance = new RegistryManager();
                }
            }
        }
        return instance;
    }

    public static void setInstance(RegistryManager registryManager) {
    }


    public MenuRegistry getMenuRegistry() {
        return menuRegistry;
    }

    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    public BlockEntityRegistry getBlockEntityRegistry() {
        return blockEntityRegistry;
    }

    public VillagerProfessionRegistry getVillagerProfessionRegistry() {
        return villagerProfessionRegistry;
    }

    public VillagerTypeRegistry getVillagerTypeRegistry() {
        return villagerTypeRegistry;
    }


    public void setRegistries(RegistryLoader loader) {
        try {
            // 使用 try-catch 语句处理可能的 IOException
            this.menuRegistry = loader.generateMenuRegistry();
            this.itemRegistry = loader.generateItemRegistry();
            this.blockEntityRegistry = loader.generateBlockEntityRegistry();
            this.villagerProfessionRegistry = loader.generateVillagerProfessionRegistry();
            this.villagerTypeRegistry = loader.generateVillagerTypeRegistry();
        } catch (IOException e) {
            // 打印异常信息并处理异常
            e.printStackTrace();
            // 您可以根据您的需求选择合适的异常处理方式，例如：
            // throw new RuntimeException(e); // 抛出运行时异常
            // return; // 直接返回
            // 或者其他方式
        }
    }
}
