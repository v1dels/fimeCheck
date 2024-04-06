package ru.don1x.fimecheck.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.don1x.fimecheck.Cooldowns;
import ru.don1x.fimecheck.Utils;

import java.util.List;

public class CommandConfirmCheat implements CommandSub {
    public CommandConfirmCheat() {
    }

    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return false;
        } else if (!(sender instanceof Player)) {
            Utils.sendMessage(sender, Utils.getMessage("only-players"));
            return true;
        } else {
            Player player = Bukkit.getPlayer(args[0]);
            if (!Utils.isCheck(player.getName())) {
                Utils.sendMessage(sender, Utils.getMessage("cheatconfirm.already"));
                return true;
            } else {
                Utils.getStringList("settings.quit-commands").forEach((x) -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), x.replace("%player%", sender.getName()));
                });
                Utils.removeCheck(player.getName());
                Cooldowns.removeCooldown(player.getName());
                return true;
            }
        }
    }

    public List<String> tab(CommandSender p0, String[] p1) {
        return null;
    }

    public String command() {
        return "confirmcheat";
    }

    public List<String> aliases() {
        return null;
    }

    public String permission() {
        return "fimecheck.confirmcheat";
    }

    public String description() {
        return Utils.getMessage("confess.confirmcheat");
    }
}

