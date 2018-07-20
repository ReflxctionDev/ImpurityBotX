package net.reflxction.impuritybot.commands.user;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.reflxction.impuritybot.applicants.Apply;
import net.reflxction.impuritybot.core.commands.AbstractCommand;
import net.reflxction.impuritybot.core.commands.CommandCategory;
import net.reflxction.impuritybot.events.commands.CommandEvent;
import net.reflxction.impuritybot.main.ImpurityBot;

public class ApplyCommand extends AbstractCommand {

    @Override
    public String getCommand() {
        return "apply";
    }

    @Override
    public void process(CommandEvent event, String[] args) {
        MessageChannel channel = event.getChannel();
        User user = event.getMember().getUser();
        JDA jda = event.getJda();
        Guild guild = event.getGuild();
        Message message = event.getMessage();
        try {
            user.openPrivateChannel().complete();
        } catch (Exception ignored) {}
        if (!channel.equals(user.openPrivateChannel().complete())) {
            channel.sendMessage("**Please head on to my pm and run the apply command there**").queue();
            return;
        }
        if (args.length != 1) {
            channel.sendMessage("**Invalid usage!** Try " + getUsage()).queue();
            return;
        }
        if (!event.getMember().getUser().getMutualGuilds().contains(ImpurityBot.getImpurityGuild())) {
            event.getChannel().sendMessage("**You are not in the impurity discord!\nLink: https://discordapp.com/invite/VYMewXg").queue();
            return;
        }
        Apply apply = new Apply(args[0], user);
        apply.apply();
        event.getChannel().sendMessage("**Success!** We successfully sen't the application info to the applications team!").queue();
    }

    @Override
    public String[] getAliases() {
        return new String[0];
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.USER;
    }

    @Override
    public long getDelay() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Applies and sends the application to the events team";
    }

    @Override
    public String getUsage() {
        return "-apply <ign>";
    }
}
