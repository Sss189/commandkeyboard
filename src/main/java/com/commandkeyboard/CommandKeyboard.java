package com.commandkeyboard;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

// Mod主类
@Mod(modid = "commandkeyboard", name = "Command Keyboard", version = "1.0")
public class CommandKeyboard {

    public static final String MODID = "commandkeyboard";
    public static final String NAME = "Command Keyboard";
    public static final String VERSION = "1.0";

    // 创建代理实例
    @SidedProxy(clientSide = "com.commandkeyboard.ClientProxy", serverSide = "com.commandkeyboard.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // 在预初始化阶段注册网络相关内容
        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandKeybindPress());
    }
}