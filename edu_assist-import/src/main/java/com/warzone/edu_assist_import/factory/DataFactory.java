package com.warzone.edu_assist_import.factory;

import java.util.ArrayList;
import java.util.List;
import com.warzone.edu_assist_import.rule.ImportRule;
import com.warzone.util.datamapping.MetaData;
import com.warzone.util.datamapping.Model;
public class DataFactory {
	private static List<ImportRule> importRules = new ArrayList<ImportRule>();
	public static List<Model> importDatafromList(List<String[]> datas){
		List<Model> models = new ArrayList<Model>();
		for(ImportRule importRule : importRules){
			int startcol = importRule.getColumn().getStart();
			int endcol = importRule.getColumn().getEnd();
			int startrow = importRule.getRow().getStart();
			int endrow = importRule.getRow().getEnd();
			
			for(int i = startrow,j=0 ; i <= endrow ; i++){
				List<MetaData> metaDatas = new ArrayList<MetaData>();
				String[] cells = datas.get(i);
				Model model = new Model();
				model.setLine(j);
				
				for(int c = startcol ,k=0; c<endcol;c++){
					String field = importRule.getColumn().getFields()[k];
					String value = cells[c].trim();
					if(value.isEmpty()){
						System.out.println("("+i+","+c+")-------------------------发现了一个---------------");
						value = datas.get(i-1)[c];
						cells[c] = value;
					}
					MetaData metaData = new MetaData();
					metaData.setName(field);
					metaData.setValue(value);
					metaDatas.add(metaData);
					k++;
				}
				j++;
				model.setMetaDatas(metaDatas);
				models.add(model);
			}
		}
		return models;
	}
	
	public static void setRules(List<ImportRule> importRules){
		DataFactory.importRules=importRules;
	}
}
