package support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Could not find product with wanted id")
public class UnknownProductException  extends RuntimeException {

    public UnknownProductException(String message){
        super(message);
    }

    public UnknownProductException(){
        super();
    }
}
