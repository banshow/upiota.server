package io.github.upiota.server.base;

public enum ExceptionCodeEnum {
	ILLEGAL_PARAMS,INTERNAL_SERVER_ERROR,BUSINESS_ERROR,AUTH;
	public String toCnString(){
		String cnString="";
		switch(ordinal()){
			case 0:cnString="请求参数不合法";break;
			case 1:cnString="接口内部异常";break;
			case 2:cnString="业务异常";break;
			case 3:cnString="身份验证";break;
		}
		return cnString;
	}
}
