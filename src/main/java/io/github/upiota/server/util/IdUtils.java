package io.github.upiota.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sohu.idcenter.IdWorker;

@Component
public class IdUtils {

	private static IdWorker idWorker;
	
	@Autowired
	public void setIdWorker(IdWorker idWorker) {
		IdUtils.idWorker = idWorker;
	}


	public static Long genId() {
		return idWorker.getId();
	}
	
	
}
