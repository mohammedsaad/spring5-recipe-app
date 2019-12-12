package guru.springframework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handelNotFound(Exception ex){
    log.error("Handling not Found Exception");
    ModelAndView errorView = new ModelAndView("recipe/errors/404Error");
    errorView.addObject("Exception",ex);
    return errorView;
}
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handelBadRequest(Exception ex){
        log.error("Handling not Bad Request Exception");
        ModelAndView errorView = new ModelAndView("recipe/errors/400Error");
        errorView.addObject("Exception",ex);
        return errorView;
    }
}
