package com.arise.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.arise.domain.Ticket;

public class TicketRepository {
	
	private Map<UUID,Ticket> tickets = new ConcurrentHashMap<>();
	
	public Ticket save(Ticket ticket) {
		tickets.put(ticket.getId(), ticket);
		return ticket;
	}
	
	public Optional<Ticket> findById(UUID ticketId){
		return Optional.ofNullable(tickets.get(ticketId));
	}
	
	public List<Ticket> findActiceTickets(){
		return tickets.values().stream().filter(Ticket::isActive).toList();
	}
	
	public void clear() {
		tickets.clear();
	}
	
	public void deactivateTicket(UUID ticketId) {
		tickets.computeIfPresent(ticketId, (id,ticket) -> {
			ticket.deactivate();
			return ticket;
		});
	}
	
}
