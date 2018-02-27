package io.github.upiota.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.upiota.server.job.AppInitializer;

@Component
public class AppInitRunner implements CommandLineRunner {
	
	
	@Autowired
	private List<AppInitializer> appInitializers;
	
	

	@Override
	public void run(String... args) throws Exception {

		
		if(appInitializers != null && appInitializers.size() > 0) {
			appInitializers.forEach(s->{
				s.run();
			});
		}
	}

}
