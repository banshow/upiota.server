package io.github.upiota.server.base;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理器
 * @author lining
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 统一的rest接口异常处理器
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler
    @ResponseBody
    private ResponseResult globalExceptionHandler(Exception e) {
        logger.error("--------->接口调用异常!", e);
        return RestResultGenerator.genErrorResult(e.getMessage(),ExceptionCodeEnum.INTERNAL_SERVER_ERROR.name());
    }

    
    @ExceptionHandler(value=AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(Exception e) {
    	   logger.error("--------->接口调用异常!", e);
           return RestResultGenerator.genErrorResult(ExceptionCodeEnum.INTERNAL_SERVER_ERROR.toCnString(),ExceptionCodeEnum.INTERNAL_SERVER_ERROR.name());
    }
    
    /**
     * 统一的参数异常处理器
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(value=IllegalArgumentException.class)
    @ResponseBody
    private ResponseResult illegalArgumentExceptionHandler(IllegalArgumentException e) {
    	logger.error("--------->非法参数异常!", e);
    	return RestResultGenerator.genErrorResult(e.getMessage(),ExceptionCodeEnum.ILLEGAL_PARAMS.name());
    }
    
	
    /**
     * 统一的参数验证异常处理器
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(value=BindException.class)
    @ResponseBody
    private ResponseResult bindExceptionHandler(BindException e) {
    	logger.error("--------->参数验证异常!", e);
    	BindingResult bindingResult = e.getBindingResult();
    	String message = "";
    	if(bindingResult.hasErrors()){
	           List<ObjectError>  list = bindingResult.getAllErrors();
	           message = list.stream().parallel().map(s->s.getDefaultMessage()).collect(Collectors.joining("|"));
	     }
    	return RestResultGenerator.genErrorResult(message,ExceptionCodeEnum.ILLEGAL_PARAMS.name());
    }
    
    
    /**
     * 统一的参数验证异常处理器
     * @param e 捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    @ResponseBody
    private ResponseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    	logger.error("--------->参数验证异常!", e);
    	BindingResult bindingResult = e.getBindingResult();
    	String message = "";
    	if(bindingResult.hasErrors()){
    		List<ObjectError>  list = bindingResult.getAllErrors();
    		message = list.stream().parallel().map(s->s.getDefaultMessage()).collect(Collectors.joining("|"));
    	}
    	return RestResultGenerator.genErrorResult(message,ExceptionCodeEnum.ILLEGAL_PARAMS.name());
    }
    
    
}
