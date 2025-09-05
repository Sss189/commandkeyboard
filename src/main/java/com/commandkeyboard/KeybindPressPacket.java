package com.commandkeyboard;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class KeybindPressPacket implements IMessage {

    private List<Integer> keyCodes;

    public KeybindPressPacket() {
    }

    public KeybindPressPacket(List<Integer> keyCodes) {
        this.keyCodes = keyCodes;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        keyCodes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            keyCodes.add(buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(keyCodes.size());
        for (Integer keyCode : keyCodes) {
            buf.writeInt(keyCode);
        }
    }

    public List<Integer> getKeyCodes() {
        return keyCodes;
    }
}