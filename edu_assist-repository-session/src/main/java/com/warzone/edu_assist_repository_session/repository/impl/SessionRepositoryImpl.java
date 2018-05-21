package com.warzone.edu_assist_repository_session.repository.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowWarningsStatement;
import com.warzone.edu_assist_dao_academicyear.dao.AcademicYearDAO;
import com.warzone.edu_assist_dao_academicyear.dto.AcademicYearDTO;
import com.warzone.edu_assist_dao_classes.dao.ClassesDAO;
import com.warzone.edu_assist_dao_course.dao.CourseDAO;
import com.warzone.edu_assist_dao_course.dto.CourseDTO;
import com.warzone.edu_assist_dao_session.dao.SessionDAO;
import com.warzone.edu_assist_dao_session.dto.SessionDTO;
import com.warzone.edu_assist_domain_course.Course;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Classes;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;
import com.warzone.edu_assist_repository_session.repository.SessionRepository;
@Repository
@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.INTERFACES)
public class SessionRepositoryImpl implements SessionRepository{
	private String teacher_id;
	private List<Academic_year> admyear_list = new ArrayList<>();
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private CourseDAO courseDao;
	@Autowired
	private ClassesDAO classesDAO;
	@Autowired
	private AcademicYearDAO academicYearDAO;
	
	
	@Override
	@Transactional
	public boolean addSession(AcademicYearDTO academicyearDTO,CourseDTO courseDTO,SessionDTO sessionDTO) {
		boolean flag = false;
		int course_id;
		int session_id;
		CourseDTO existCourse = courseDao.selectCourseByCourseName(courseDTO.getCourse_name());
		if(existCourse==null){
			courseDao.insertCourse(courseDTO);
			course_id=courseDTO.getCourse_id();
		}else{
			course_id = existCourse.getCourse_id();
		}
		courseDTO.setCourse_id(course_id);
		if(!isRepeatSession(academicyearDTO, sessionDTO.getLecturer_id(), courseDTO)) {
		sessionDTO.setCourse_id(courseDTO.getCourse_id());
		sessionDAO.insertSession(sessionDTO);
		session_id=sessionDTO.getSession_id();
		academicyearDTO.setSession_id(session_id);
		academicYearDAO.insertAcademicYear(academicyearDTO);
		admyear_list.clear();
		flag=true;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean deleteSessionBySessionID(int id) {
		boolean flag = false;
		if(sessionDAO.deleteSession(id)==0) {
			flag=false;
		}
		admyear_list.clear();
		return flag;
	}


	@Override
	public boolean updateSessionNameBySessionID(int session_id,String newName) {
		boolean flag=false;
		if(sessionDAO.updateSessionName(session_id, newName)!=0) {
			admyear_list.clear();
			flag=true;
		}
		return flag;
	}

	@Override
	public List<Academic_year> getAcademic_yearSessionInfoByTeacherid(String teacherid) {
		this.teacher_id=teacherid;
		if(admyear_list.isEmpty()){
			admyear_list = sessionDAO.selectSessionByTeacher_id(teacherid);
		}
		return admyear_list;
	}
	@Override
	public List<Session> getSessionByAcademicYearSemester(AcademicYearDTO academicYearDTO) {
		if(admyear_list.isEmpty()){
			admyear_list = sessionDAO.selectSessionByTeacher_id(this.teacher_id);
		}
		for(Academic_year academic_year:admyear_list) {
			if(academic_year.getStart_year().equals(academicYearDTO.getStart_year()) && academic_year.getEnd_year().equals(academicYearDTO.getEnd_year())) {
				for(Semester temp : academic_year.getSemesters()) {
					if(temp.getSemester().equals(academicYearDTO.getSemester())){
						return temp.getSessions();
					}
				}
			}
		}
		return null;
	}
	
	private boolean isRepeatSession(AcademicYearDTO academicYearDTO, String lecturer_id, CourseDTO courseDTO) {
		if(admyear_list.isEmpty()){
			admyear_list = sessionDAO.selectSessionByTeacher_id(this.teacher_id);
		}
		boolean flag=false;
		for(Academic_year academic_year : admyear_list) {
			if(academic_year.getStart_year().equals(academicYearDTO.getStart_year())&&academic_year.getEnd_year().equals(academicYearDTO.getEnd_year())){
				for(Semester semester : academic_year.getSemesters()) {
					if(semester.getSemester().equals(academicYearDTO.getSemester())) {
						for(Session session : semester.getSessions()) {
							System.out.println("已经存在"+session.getCourse().getCourse_id()+"要添加的ID"+courseDTO.getCourse_id());
							if(session.getCourse().getCourse_id()==courseDTO.getCourse_id()) {
								flag=true;
							}
						}
					}
				}
			}
		}
		return flag;
	}
	@Override
	public int getSessionID(String teacher_id,String start_year,String end_year,String semester,String course) {
		if(admyear_list.isEmpty()){
			admyear_list = sessionDAO.selectSessionByTeacher_id(this.teacher_id);
		}
		for(Academic_year academic_year:admyear_list) {
			if(academic_year.getStart_year().equals(start_year)&&academic_year.getEnd_year().equals(end_year)) {
				for(Semester st:academic_year.getSemesters()) {
					if(st.getSemester().equals(semester)) {
						for(Session session:st.getSessions()) {
							if(session.getCourse().getCourse_name().equals(course)) {
								return session.getSession_id();
							}
						}
					}
				}
			}
		}
		return -1;
	}
	@Override
	public Session getSession(String start_year, String end_year, String semester, String course) {
		if(admyear_list.isEmpty()){
			admyear_list = sessionDAO.selectSessionByTeacher_id(this.teacher_id);
		}
		for(Session session : getSessionByAcademicYearSemester(new AcademicYearDTO(start_year, end_year, semester))) {
			if(session.getCourse().getCourse_name().equals(course)) {
				return session;
			}
		}
		return null;
	}
	@Override
	public Academic_year filterAcademic_year(String teacherid, String start_year,String end_year) {
		List<Academic_year> academic_years = getAcademic_yearSessionInfoByTeacherid(teacherid);
		for(int i = 0;i<academic_years.size();i++) {
			Academic_year academic_year = academic_years.get(i);
			if(academic_year.getStart_year().equals(start_year) && academic_year.getEnd_year().equals(end_year)) {
				return academic_year;
			}
		}
		return null;
	}
	@Override
	public Academic_year filterSemester(String teacherid, String start_year, String end_year, String semester_name) {
		Academic_year teacher_year_academicyear = filterAcademic_year(teacherid, start_year, end_year);
		List<Semester> semesters = teacher_year_academicyear.getSemesters();
		Academic_year temp = new Academic_year();
		List<Semester> temp_semester = new ArrayList<>();
		temp.setStart_year(teacher_year_academicyear.getStart_year());
		temp.setEnd_year(teacher_year_academicyear.getEnd_year());
		for(int i =0;i<semesters.size();i++) {
			Semester semester = semesters.get(i);
			if(semester.getSemester().equals(semester_name)) {
				temp_semester.add(semester);
			}
		}
		temp.setSemesters(temp_semester);
		return temp;
	}
	@Override
	public void setSessionCompleted(int sessionid) {
		// TODO Auto-generated method stub
		sessionDAO.updateSessionStatus(sessionid);
		admyear_list.clear();
	}
	@Override
	public String getSessionStatus(String teacherid, String start_year, String end_year, String semester_name,String course) {
		for(Academic_year academic_year : admyear_list) {
			if(academic_year.getStart_year().equals(start_year) && academic_year.getEnd_year().equals(end_year)) {
				for(Semester semester : academic_year.getSemesters()) {
					if(semester.getSemester().equals(semester_name)) {
						for(Session session : semester.getSessions()) {
							if(session.getCourse().getCourse_name().equals(course)) {
								return session.getCompletion_status();
							}
						}
					}
				}
			}
		}
		return null;
	}
	@Override
	public Academic_year getQualityAcademicyearBySessionid(int sessionid) {
		
		Academic_year academic_year = sessionDAO.selectSessinBySessionid(sessionid);
		return academic_year;
	}
	
}
