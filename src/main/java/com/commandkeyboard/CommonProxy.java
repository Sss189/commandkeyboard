package com.commandkeyboard;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("commandkeyboard");

    public void preInit(FMLPreInitializationEvent event) {
        // 注册你的数据包及其处理器
        NETWORK.registerMessage(new KeybindPressPacketHandler(), KeybindPressPacket.class, 0, Side.CLIENT);
    }
}
