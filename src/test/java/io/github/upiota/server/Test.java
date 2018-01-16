package io.github.upiota.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.upiota.server.sys.repository.UserRepository;

public class Test {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//PasswordEncoder pe = new BCryptPasswordEncoder();

		//SelectProvider sp = SelectProvider.class.newInstance();
		//System.out.println(pe.encode("admin"));
		
		
		
        //一个类可能实现多个接口,每个接口上定义的泛型类型都可取到
        Type[] interfacesTypes = UserRepository.class.getGenericInterfaces();
        for (Type t : interfacesTypes) {
            Type[] genericType2 = ((ParameterizedType) t).getActualTypeArguments();
            for (Type t2 : genericType2) {
                System.out.println(Class.forName(t2.getTypeName()));
            }
        }
	}

}
