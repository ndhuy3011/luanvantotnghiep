package com.stu.luanvan.exception;

import com.stu.luanvan.locales.ExceptionLocales;
import org.jboss.logging.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExHandler extends ResponseEntityExceptionHandler {
    private Logger logger = Logger.getLogger(CustomExHandler.class);
    /**
     *  mã lỗi 404
     * @param ex Thu thập lỗi
     * @param req webrequests
     * @return không tìm thấy dự liệu
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateEx.class)
    public ResponseError handlerDuplicate(DuplicateEx ex, WebRequest req){
        return new ResponseError(HttpStatus.CONFLICT,ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEx.class)
    public ResponseError handlerNotFound(NotFoundEx ex, WebRequest req){
        return new ResponseError(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseError handlerException(Exception ex, WebRequest req){
        logger.error(ex);
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,ExceptionLocales.INTERNAL_SERVER_ERROR);
    }
    /**
     *  lỗi code 400
     * @param ex Thu nhập lỗi
     * @param req webrequests
     * @return gửi dữ liệu không phù hợp với request nhận
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestEx.class)
    public ResponseError handlerBadRequest(BadRequestEx ex,WebRequest req){
        return new ResponseError(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    /**
     * Mã lỗi 403
     * @param ex thu nhập lỗi
     * @param req webrequests
     * @return không có quyền truy cập
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseError handlerAccessDeniedForbidden(ForbiddenEx ex,WebRequest req){
        return new ResponseError(HttpStatus.FORBIDDEN,ExceptionLocales.FORBIDDEN_ERROR);
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseError handlerAuthentication(ForbiddenEx ex,WebRequest req){
        return new ResponseError(HttpStatus.FORBIDDEN,ExceptionLocales.FORBIDDEN_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseError handlerValidation(BadRequestEx ex,WebRequest req){
        return new ResponseError(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("errors", ExceptionLocales.RequestMethodNotSupport);
        return new ResponseEntity<>(body ,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        Map<String, Object> body1 = new LinkedHashMap<>();
        body1.put("status", status.value());
        body1.put("errors", ExceptionLocales.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(body1 ,headers,status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request)
    {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        // Get all errors
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("errors", ExceptionLocales.FOUND_ERROR);
        return new ResponseEntity<>(body, headers, status);
    }


}
