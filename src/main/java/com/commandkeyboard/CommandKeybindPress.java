package com.commandkeyboard;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import java.util.ArrayList;
import java.util.List;

public class CommandKeybindPress extends CommandBase {

    // 完整的按键列表，用于错误提示
    private static final String VALID_KEYS =
            "Valid keys include: " +
                    "A-Z, 0-9, F1-F12, " +
                    "UP, DOWN, LEFT, RIGHT, " +
                    "ESCAPE, TAB, RETURN, LCONTROL, RCONTROL, LSHIFT, RSHIFT, " +
                    "LMENU, RMENU, SPACE, BACK, DELETE, INSERT, HOME, END, " +
                    "PRIOR, NEXT, " +
                    "NUMPAD0-9, NUMPADENTER, ADD, SUBTRACT, MULTIPLY, DIVIDE, DECIMAL, " +
                    "MINUS, EQUALS, GRAVE, BACKSLASH, LBRACKET, RBRACKET, " +
                    "SEMICOLON, APOSTROPHE, COMMA, PERIOD, SLASH, CIRCUMFLEX.";

    @Override
    public String getName() {
        return "keyboardpress";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/keyboardpress <key1> <key2> ...";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(new TextComponentString("Usage: " + getUsage(sender) + ". " + VALID_KEYS));
            return;
        }

        List<Integer> keyCodes = new ArrayList<>();
        for (String keyName : args) {
            int keyCode = Keyboard.getKeyIndex(keyName.toUpperCase());
            if (keyCode == Keyboard.KEY_NONE) {
                // 错误提示信息中包含了完整的按键规则
                sender.sendMessage(new TextComponentString("Error: Invalid key name '" + keyName + "'."));
                sender.sendMessage(new TextComponentString(VALID_KEYS));
                return;
            }
            keyCodes.add(keyCode);
        }

        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;
            CommonProxy.NETWORK.sendTo(new KeybindPressPacket(keyCodes), player);
        }
    }
}