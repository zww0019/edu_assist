package com.warzone.edu_assist_service_session.service;

import java.util.List;

import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_dao_course.dto.CourseDTO;
import com.warzone.edu_assist_dao_session.dto.SessionDTO;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;
import com.warzone.edu_assist_service_session.vo.AcademicYearVO;
import com.warzone.edu_assist_service_session.vo.SessionVO;

public interface SessionService {
	public List<Academic_year> getAcademicYearTreeByTeacherid(String teacherid);
	public boolean addSession(SessionVO sessionVO);
	public boolean deleteSessionBySessionid(int sessionid);
	public boolean updateSessionNameBySessionid(int sessionid,String name);
	public List<Session> getSessionByAcademicYearSemester(AcademicYearVO academicYearVO);
	public Academic_year getAcademicYearByTeacheridAndYear(String teacherid,String start_year,String end_year);
	public Academic_year getSemester(String teacherid,String start_year,String end_year,String semester);
	public int getSessionID(String teacherid,String start_year,String end_year,String semester,String course);
	public void setSessionComplete(int sessionid);
	public String getSessionStatus(String teacherid, String start_year, String end_year, String semester_name,String course);
	public Academic_year getQualityAcademicyearBySessionid(int sessionid);
}
