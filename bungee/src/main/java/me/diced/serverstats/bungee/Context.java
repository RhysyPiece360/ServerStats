package me.diced.serverstats.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import java.util.List;

public class Context {
    private CommandSender sender;

    public Context(CommandSender sender) {
        this.sender = sender;
    }

    public void sendMessage(List<String> messages) {
        ComponentBuilder builder = new ComponentBuilder();
        for (String msg : messages) {
            builder.append(msg + "\n");
        }

        this.sender.sendMessage(builder.create());
    }

    public static ChatColor heatmapColor(double actual, double reference)
    {
        ChatColor color = ChatColor.GRAY;
        if (actual >= 0.0D) color = ChatColor.DARK_GREEN;
        if (actual > 0.5D * reference) color = ChatColor.YELLOW;
        if (actual > 0.8D * reference) color = ChatColor.RED;
        if (actual > reference) color = ChatColor.LIGHT_PURPLE;
        return color;
    }
}
