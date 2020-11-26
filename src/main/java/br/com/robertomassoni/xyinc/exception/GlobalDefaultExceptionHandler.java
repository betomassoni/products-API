package br.com.robertomassoni.xyinc.exception;

import br.com.robertomassoni.xyinc.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    
    @ExceptionHandler(value = XyincException.EntityNotFoundException.class)
    public ResponseEntity notFoundErrorHandler(Exception ex, WebRequest request) throws Exception {
        Response response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = XyincException.EntityException.class)
    public ResponseEntity defaultErrorHandler(Exception ex, WebRequest request) throws Exception {
        Response response = Response.exception();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
   
    
}
