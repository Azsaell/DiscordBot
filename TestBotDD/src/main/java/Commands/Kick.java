package Commands;

import Commands_utilities.Command;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class Kick extends Command {

    public Kick() {
        name = "kick";
        help = "This command kicks mentioned users";
        aliases = Arrays.asList("k");
    }
    @Override
    protected void execute(MessageReceivedEvent e){
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        if (args.length < 2) {
            e.getChannel().sendMessage("Wspomnij (@) użytkownika, którego chcesz wyrzucić-").queue();
        } else {
            if(e.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                for (Member member : e.getMessage().getMentionedMembers()) {
                    member.kick().queue();
                    e.getChannel().sendMessage("Użytkownik " + e.getMember().getUser().getName() + " wyrzucił " + member.getUser().getName()).queue();
                }
            } else {
                e.getChannel().sendMessage("Nie masz uprawnień do wyrzucania użytkowników").queue();
            }
        }
    }
}
