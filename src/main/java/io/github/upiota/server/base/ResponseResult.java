package io.github.upiota.server.base;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult{
	private boolean success;

    private String msg;

    private Map<Object,Object> data = new HashMap<Object,Object>();

    private String code;

    private Long totalCount;
    
    private ResponseResult() {
    }

    public static ResponseResult newInstance() {
        return new ResponseResult();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public Map<Object,Object> getData() {
        return data;
    }

    public void setData(Map<Object,Object> data) {
        this.data = data;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	
	public ResponseResult putData(Object key,Object data){
		this.data.put(key, data!=null?data:new HashMap<Object,Object>());
		return this;
	}

}
