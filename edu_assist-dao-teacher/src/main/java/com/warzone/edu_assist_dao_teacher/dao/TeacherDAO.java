package com.warzone.edu_assist_dao_teacher.dao;

import com.warzone.edu_assist_dao_teacher.dto.TeacherDTO;
import com.warzone.util.dao.BaseDAO;

@BaseDAO
public interface TeacherDAO {
	public TeacherDTO selectTeacherByTeacherid(String teacherid);
	public int insertTeacher(TeacherDTO teacherDTO);
}
