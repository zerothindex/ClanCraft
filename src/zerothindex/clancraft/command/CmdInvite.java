package zerothindex.clancraft.command;

import zerothindex.clancraft.ClanPlugin;
import zerothindex.clancraft.MessageReceiver;
import zerothindex.clancraft.WorldPlayer;
import zerothindex.clancraft.clan.ClanPlayer;

public class CmdInvite extends CommandBase {

	@Override
	public String getName() {
		return "invite";
	}

	@Override
	public String getDescription() {
		return "invite a player to join your clan";
	}

	@Override
	public String getUsage() {
		return "/c invite <player>";
	}

	@Override
	public boolean playerOnly() {
		return true;
	}

	@Override
	public boolean handle(MessageReceiver sender, String[] args) {
		if (args.length != 2) return false;
		ClanPlayer cp = ClanPlugin.getInstance().getClanPlayer((WorldPlayer)sender);
		if (cp.getClan() == null) {
			sender.message("<r>You aren't part of a clan.");
			return true;
		}
		if (cp.getRole() != ClanPlayer.ROLE_LEADER) {
			sender.message("<r>You must be a clan leader to do that.");
			return true;
		}
		ClanPlayer invited = ClanPlugin.getInstance().findClanPlayer(args[1]);
		if (invited == null) {
			sender.message("<r>Couldn't find \""+args[1]+"\".");
			return true;
		}
		if (invited.getClan().equals(cp.getClan())) {
			sender.message("<r>That player is already part of this clan!");
			return true;
		}
		cp.getClan().addInvite(invited.getName());
		invited.message("<t>You have been invited to "+cp.getClan().getName()+
				".\n Type '/c join "+cp.getClan().getName()+" to join!");
		sender.message("<t>You invited \""+args[1]+"\" to your clan.");
		return true;
	}

}
