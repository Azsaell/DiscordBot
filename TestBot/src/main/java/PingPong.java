import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PingPong extends ListenerAdapter {

    String url = "https://discord.com/oauth2/authorize?client_id=%s&scope=bot";
    final long adminID = 818794086647201822L;
    final long roleid = 818781151388106752L;  //new user role id
    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if(e.getMessage().getContentRaw().equals("!ping")) {
            e.getChannel().sendMessage("pong").queue();
        }
        else if (e.getMessage().getContentRaw().equals("!time")) {
            e.getChannel().sendMessage(time()).queue();
        }
        else if (e.getMessage().getContentRaw().equals("!invite")) {
            e.getChannel().sendMessage(String.format(url, e.getJDA().getSelfUser().getId())).queue();
        }
        if(e.getMessage().getContentRaw().equals("!quit")) {
            e.getChannel().getJDA().shutdown();
        }
        if(e.getMessage().getContentRaw().equals("!pong")) {
            e.getChannel().sendMessage("ping").queue();
        }
        if(e.getMessage().getContentRaw().equals("!name")) {
            e.getChannel().sendMessage(e.getAuthor().getName()).queue();
        }
        if (e.getMessage().getContentRaw().startsWith("!kick")) {
            Role adminrole = e.getGuild().getRoleById(adminID);

            String memberID = e.getGuild().getMembersWithRoles(adminrole).get(0).getUser().getId();
            if (e.getAuthor().getId().equals(memberID)) {
                System.out.println("Mozesz kick");
            } else {
                System.out.println("Nie mozesz kick");
            }

        }
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e){
        Member member = e.getMember();
        String name = e.getUser().getName();
        member.getUser().openPrivateChannel().queue(channel -> {
            channel.sendMessage("Hello " + name + "!").queue();
        });
    }

    public String time() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}