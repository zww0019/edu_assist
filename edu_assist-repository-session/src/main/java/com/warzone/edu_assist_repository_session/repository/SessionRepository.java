package com.warzone.edu_assist_repository_session.repository;

import java.util.List;

import com.warzone.edu_assist_dao_academicyear.dto.AcademicYearDTO;
import com.warzone.edu_assist_dao_course.dto.CourseDTO;
import com.warzone.edu_assist_dao_session.dto.SessionDTO;
import com.warzone.edu_assist_domain_course.Course;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;

public interface SessionRepository {
	public List<Academic_year> getAcademic_yearSessionInfoByTeacherid(String teacherid);
	public boolean addSession(AcademicYearDTO academicyearDTO,CourseDTO courseDTO,SessionDTO sessionDTO);
	public boolean deleteSessionBySessionID(int id);
	public boolean updateSessionNameBySessionID(int session_id,String newName);
	public List<Session> getSessionByAcademicYearSemester(AcademicYearDTO academicYearDTO);
	public int getSessionID(String teacher_id,String start_year,String end_year,String semester,String course);
	public Session getSession(String start_year,String end_year,String semester,String course);
	public Academic_year filterAcademic_year(String teacherid, String start_year,String end_year);
	public Academic_year filterSemester(String teacherid, String start_year, String end_year, String semester_name) ;
	public void setSessionCompleted(int sessionid);
	public String getSessionStatus(String teacherid, String start_year, String end_year, String semester_name,String course);
	public Academic_year getQualityAcademicyearBySessionid(int sessionid);
}
