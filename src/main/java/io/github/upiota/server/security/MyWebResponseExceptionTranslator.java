package io.github.upiota.server.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

public class MyWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		ResponseEntity<OAuth2Exception> re = super.translate(e);
		OAuth2Exception oe = re.getBody();
		if(oe.getHttpErrorCode() == 400) {
			HttpHeaders headers = re.getHeaders();
			return new ResponseEntity<OAuth2Exception>(oe,headers,HttpStatus.OK);
		}
		return re;
	}

}
