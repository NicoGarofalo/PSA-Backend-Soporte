import java.util.Date;

public class Ticket {

    int id;
    String name;
    String description;
    int severity;
    String creator;
    String client;
//    Date creationDate;
    Date expirationDate;
    String state;

    Ticket(int id, String name, String description, int severity, String creator, String client, Date expiration){
        this.id = id;
        this.name = name;
        this.description = description;
        this.severity = severity;
        this.creator = creator;
        this. client = client;
        this.expirationDate = expiration;
        this.state = "created";
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
}
