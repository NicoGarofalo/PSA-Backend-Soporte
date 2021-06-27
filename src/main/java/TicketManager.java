import java.util.Date;
import java.util.HashMap;

public class TicketManager {
    int idCounter;
    HashMap<String,Ticket> TicketList;

    public TicketManager(){
        TicketList = new HashMap<>();
        this.idCounter = 0;
    }

    public void createTicket(String ticketName, String ticketDescription) {
        Ticket ticket = new Ticket(this.idCounter, ticketName, ticketDescription, 0, "creator","client", null);
        TicketList.put(ticketName,ticket);
        this.idCounter++;
    }

    public boolean containsTicket(String ticketName){
        return TicketList.containsKey(ticketName);
    }

    public void deleteTicket(String ticketName){
        if(! containsTicket(ticketName)){
            throw new UnknownTicketException();
        }
        this.TicketList.remove(ticketName);
    }

    public void updateTicketDescription(String ticketName, String description){
        if(! containsTicket(ticketName)){
            throw new UnknownTicketException();
        }
        Ticket ticket = this.TicketList.get(ticketName);
        ticket.setDescription(description);
    }

    public String getTicketDescription(String ticketName){
        if(! containsTicket(ticketName)){
            throw new UnknownTicketException();
        }
        Ticket ticket = this.TicketList.get(ticketName);
        return ticket.getDescription();
    }
}
