package com.commandkeyboard;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event); // 调用父类的 preInit 方法以注册网络包
        // 客户端特有代码，例如注册按键绑定
        // KeybindRegistry.registerKeybinds();
    }

}
