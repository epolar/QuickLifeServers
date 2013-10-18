package com.quicklife.pojo;

import java.lang.annotation.*;  

@Target(ElementType.FIELD)  //表示该注解用于域声明
@Retention(RetentionPolicy.RUNTIME) //VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。 
public @interface SetterAnnotation {
	public String setMethodName();
}
