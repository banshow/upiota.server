package io.github.upiota.server.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.upiota.server.util.JacksonMapper;

public class RestResultGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RestResultGenerator.class);

    /**
     * 生成响应成功的(不带正文)的结果
     * @param message 成功提示信息
     * @return ResponseResult
     */
    public static ResponseResult genResult(String message) {
        ResponseResult responseResult = ResponseResult.newInstance();
        responseResult.setSuccess(true);
        responseResult.setMsg(message);
        return responseResult;
    }

    /**
     * 生成响应成功(带正文)的结果
     * @param data    结果正文
     * @param message 成功提示信息
     * @return ResponseResult<T>
     */
    public static ResponseResult genResult(Map<Object,Object> data, String message) {
        ResponseResult result = ResponseResult.newInstance();
        result.setSuccess(true);
        result.setData(data);
        result.setMsg(message);
        if (logger.isDebugEnabled()) {
            logger.debug("--------> result:{}", JacksonMapper.toJsonString(result));
        }
        return result;
    }

    
    /**
     * 生成响应失败的结果
     * @param message 自定义错误信息
     * @return ResponseResult
     */
    public static ResponseResult genErrorResult(String message) {
        ResponseResult result = ResponseResult.newInstance();
        result.setSuccess(false);
        result.setMsg(message);
        if (logger.isDebugEnabled()) {
            logger.debug("--------> result:{}", JacksonMapper.toJsonString(result));
        }
        return result;
    }
    
    /**
     * 生成响应失败的结果
     * @param message
     * @param errorCode
     * @return
     */
    public static ResponseResult genErrorResult(String message,String errorCode) {
    	ResponseResult result = ResponseResult.newInstance();
    	result.setSuccess(false);
    	result.setMsg(message);
    	result.setCode(errorCode);
    	if (logger.isDebugEnabled()) {
    		logger.debug("--------> result:{}", JacksonMapper.toJsonString(result));
    	}
    	return result;
    }

}
