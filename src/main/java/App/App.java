package App;

import entities.tickets.Ticket;
import services.tickets.TicketService;


/**
 * JavaFX App
 */
public class App {
    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        Ticket ticket1 = new Ticket(20.0f, "Description 1", "image url", 1,2);
        Ticket ticket2 = new Ticket(20.0f, "Description 2", "image url", 1,3);

        ticket1=ticketService.insert(ticket1);
        ticket2=ticketService.insert(ticket2);
        System.out.println(ticketService.select());
        ticket2.setEvent_id(2);
        ticketService.update(ticket2);
        System.out.println(ticketService.select());
        //ticket2.setDescription("New description for ticket 2");
        //ticketService.update(ticket2);
        //System.out.println(ticketService.select());
        //Ticket ticket = ticketService.select(11);
        //ticket.setDescription("New ticket for 11 again");
        //ticketService.update(ticket);
        //ticketService.delete(12);
        
    }
}