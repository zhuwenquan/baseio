package com.generallycloud.nio.balance.router;

import com.generallycloud.nio.balance.BalanceFacadeSocketSession;
import com.generallycloud.nio.balance.BalanceReverseSocketSession;
import com.generallycloud.nio.balance.HashedBalanceReadFuture;
import com.generallycloud.nio.protocol.ReadFuture;

public class HashedBalanceRouter extends AbstractBalanceRouter {

	public HashedBalanceRouter(int maxNode) {
		this.nodeGroup = new NodeGroup(maxNode);
	}

	private NodeGroup	nodeGroup;

	public void addRouterSession(BalanceReverseSocketSession session) {
		Machine machine = new Machine(session);
		nodeGroup.addMachine(machine);
	}

	public void removeRouterSession(BalanceReverseSocketSession session) {
		Machine machine = (Machine) session.getAttachment();
		if (machine == null) {
			return;
		}
		nodeGroup.removeMachine(machine);
	}

	public BalanceReverseSocketSession getRouterSession(BalanceFacadeSocketSession session, ReadFuture future) {

		HashedBalanceReadFuture f = (HashedBalanceReadFuture) future;

		Machine machine = nodeGroup.getMachine(f.getHashCode());
		
		if (machine == null) {
			return null;
		}
		
		return machine.session;
	}

	public BalanceReverseSocketSession getRouterSession(BalanceFacadeSocketSession session) {

		Machine machine = (Machine) session.getAttachment();

		if (machine == null) {
			return null;
		}

		return machine.session;
	}
}
