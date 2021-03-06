package zerothindex.clancraft.command;

import zerothindex.clancraft.ClanPlugin;
import zerothindex.clancraft.MessageReceiver;
import zerothindex.clancraft.WorldPlayer;
import zerothindex.clancraft.clan.ClanPlayer;

public class CmdLeave extends CommandBase {

	@Override
	public String getName() {
		return "leave";
	}

	@Override
	public String getDescription() {
		return "leave your faction";
	}

	@Override
	public String getUsage() {
		return "/c leave";
	}

	@Override
	public boolean playerOnly() {
		return true;
	}

	@Override
	public boolean handle(MessageReceiver sender, String[] args) {
		ClanPlayer cp = ClanPlugin.getInstance().getClanPlayer((WorldPlayer)sender);
		if (cp.getClan() == null) {
			sender.message("&xYou aren't part of a clan!");
		} else {
			if (cp.getClan().getSize() == 1) {
				cp.getClan().disband();
			} else {
				cp.getClan().kickMember(cp);
			}
		}
		return true;
	}

}
