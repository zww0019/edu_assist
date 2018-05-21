package com.warzone.edu_assist_service_classes.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.util.datamapping.Model;

public interface ClassesService {
	public List<Classes> getDetailClassesBySessionID(int sessionid);
	public int getStudentSize(int sessionid,int classesid);
	public List<Classes> getSimplyClassesBySessionID(int sessionid);
	public void createClasses(int session_id,ClassesDTO classesDTO);
	public void importStudentToClasses(List<Model> models,int classesid)throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException;
	public void updateAffixPath(int taskid,String path);
	public Classes getClassesByClassesid(int sessionid,int classesid);
	public HashMap<Integer, List<Task>> getSessionidAndTasksByStudentid(String studentid) ;
	public List<ClassesDTO> getClassesByStudentid(String studentid);
}
