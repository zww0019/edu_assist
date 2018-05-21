package com.warzone.util.datamapping;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.warzone.util.reflect.ReflectUtil;

public class  DataMapper<T> {
	
	public List<T> DatasMapping(List<Model> models,Class<T> tar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		if(models!=null){
			List<T> ts = new ArrayList<T>();
			for(Model model : models){
				List<MetaData> metaDatas = model.getMetaDatas();
				T t = tar.newInstance();
				for(MetaData metaData : metaDatas){
					ReflectUtil.setFieldValueByName(t, metaData.getName(), metaData.getValue(), String.class);
				}
				ts.add(t);
			}
			return ts;
		}else{
			throw new IllegalArgumentException("Models为空！！！");
		}
	}
}
