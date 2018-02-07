package io.github.upiota.server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import io.github.upiota.framework.annotation.ApiResource;
import io.swagger.annotations.ApiOperation;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

@Component
public class MyListenerProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    	Class<? extends Object> c = bean.getClass();
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        ClassPool pool = ClassPool.getDefault();
		Loader cl = new Loader(); //javassist.Loader
        //extracting the class
        CtClass cc = null;
        Object newBean = null;
        try {
       
        
        if (methods != null) {
            for (Method method : methods) {
            	ApiResource apiResource = AnnotationUtils.findAnnotation(method, ApiResource.class);
            	if(apiResource != null) {
            		
            		 cc = pool.getCtClass(c.getName());
            		 cc.defrost();
            	        //looking for the method to apply the annotation on
            		CtMethod sayHelloMethodDescriptor = cc.getDeclaredMethod(method.getName());
					
                    // create the annotation
                    ClassFile ccFile = cc.getClassFile();
                    ConstPool constpool = ccFile.getConstPool();
                    AnnotationsAttribute attr = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
                    Annotation annot = new Annotation("io.swagger.annotations.ApiOperation", constpool);
                    annot.addMemberValue("value", new StringMemberValue("aaa",ccFile.getConstPool()));
                    attr.addAnnotation(annot);
                    // add the annotation to the method descriptor
                    sayHelloMethodDescriptor.getMethodInfo().addAttribute(attr);
                     
                    Class dynamiqueBeanClass;
        			dynamiqueBeanClass = cc.toClass(cl);
        				 //instanciating the updated class 
        			newBean = dynamiqueBeanClass.newInstance();
                  
            		
            	}
           
//            	ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
//                if (null != apiResource && null != apiOperation) {
//                	InvocationHandler h = Proxy.getInvocationHandler(apiResource);
//                	InvocationHandler aoh = Proxy.getInvocationHandler(apiOperation);
//                	try {
//						Field hField = h.getClass().getDeclaredField("memberValues");
//						hField.setAccessible(true);
//						Map memberValues = (Map) hField.get(h);
//						
//						Field aohField = aoh.getClass().getDeclaredField("memberValues");
//						aohField.setAccessible(true);
//						Map aomemberValues = (Map) aohField.get(aoh);
//						
//						aomemberValues.put("value", memberValues.get("name"));
//						System.out.println(aomemberValues);
//						//System.out.println(memberValues);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//                }
            }
            
        } } 
        catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return bean;
		}
      
        
       
        
        return newBean != null?newBean:bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
