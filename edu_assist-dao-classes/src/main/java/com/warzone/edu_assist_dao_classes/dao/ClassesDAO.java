package com.warzone.edu_assist_dao_classes.dao;

import java.util.List;

import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.util.dao.BaseDAO;

@BaseDAO
public interface ClassesDAO {
	public int insertClasses(ClassesDTO classes);
	public int deleteClassesByClassesID(int classesid);
	public List<ClassesDTO> selectClassBySessionID(int session_id);
	public List<ClassesDTO> selectClassesByStudentid(String studentid);
	}
