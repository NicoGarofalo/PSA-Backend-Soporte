package support.exception;

public class UnknownTicketException  extends RuntimeException {

    public UnknownTicketException(String message){
        super(message);
    }

    public UnknownTicketException(){
        super();
    }
}
