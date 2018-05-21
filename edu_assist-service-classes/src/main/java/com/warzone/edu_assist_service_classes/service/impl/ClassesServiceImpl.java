package com.warzone.edu_assist_service_classes.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warzone.edu_assist_batchinsert.service.BatchInsertService;
import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_repository_classes.repository.ClassesRepository;
import com.warzone.edu_assist_repository_task.repository.TaskRepository;
import com.warzone.edu_assist_service_classes.service.ClassesService;
import com.warzone.util.datamapping.Model;
@Service
public class ClassesServiceImpl implements ClassesService {

	@Autowired
	private ClassesRepository classesRepository;
	@Autowired
	private BatchInsertService batchInsertService;
	@Override
	public List<Classes> getDetailClassesBySessionID(int sessionid) {
		// TODO Auto-generated method stub
		return classesRepository.getDetailClasses(sessionid);
	}
	@Override
	public int getStudentSize(int sessionid,int classesid) {
		// TODO Auto-generated method stub
		return classesRepository.getStudentsSize(sessionid,classesid);
	}
	@Override
	public List<Classes> getSimplyClassesBySessionID(int sessionid) {
		// TODO Auto-generated method stub
		return classesRepository.getSimplyClasses(sessionid);
	}
	@Override
	public void createClasses(int session_id, ClassesDTO classesDTO) {
		// TODO Auto-generated method stub
		classesRepository.createClasses(classesDTO, session_id);
	}
	@Override
	public void importStudentToClasses(List<Model> models, int classesid)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, InstantiationException {
		classesRepository.batchInsertStudentToClasses(classesid, batchInsertService.getStudentList(models));
	}
	@Override
	public void updateAffixPath(int taskid, String path) {
		// TODO Auto-generated method stub
		classesRepository.updateTaskAffix(path, taskid);
	}
	@Override
	public Classes getClassesByClassesid(int sessionid, int classesid) {
		// TODO Auto-generated method stub
		return classesRepository.getClassesByClassesID(sessionid, classesid);
	}
	@Override
	public HashMap<Integer, List<Task>> getSessionidAndTasksByStudentid(String studentid) {
		// TODO Auto-generated method stub
		return classesRepository.getSessionidAndTasksByStudentid(studentid);
	}
	@Override
	public List<ClassesDTO> getClassesByStudentid(String studentid) {
		// TODO Auto-generated method stub
		return classesRepository.getClassesByStudentid(studentid);
	}

}
