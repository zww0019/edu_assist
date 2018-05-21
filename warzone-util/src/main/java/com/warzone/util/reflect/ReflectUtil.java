package com.warzone.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
public class  ReflectUtil<T> {
	/**
	 * 根据字段名执行该字段的Setter方法
	 * @param o	该类的实例
	 * @param fieldName	字段名
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static void setFieldValueByName(Object o, String fieldName, Object value, Class<?> type) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		// 获取getter的名字
		String setterName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		//执行该属性的getter方法,获取结果
		o.getClass().getMethod(setterName,type).invoke(o, value);
		
	}
	/**
	 * 获取私有属性的值
	 * @param o
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getPrivateFieldValueByName(Object o,String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field field=o.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		Object ret=field.get(o);
		field.setAccessible(false);
		return ret;
	}
}
