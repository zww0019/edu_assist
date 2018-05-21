package com.warzone.edu_assist_service_session.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.warzone.edu_assist_dao_academicyear.dto.AcademicYearDTO;
import com.warzone.edu_assist_dao_course.dto.CourseDTO;
import com.warzone.edu_assist_dao_session.dto.SessionDTO;
import com.warzone.edu_assist_repository_session.repository.SessionRepository;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;
import com.warzone.edu_assist_service_session.service.SessionService;
import com.warzone.edu_assist_service_session.vo.AcademicYearVO;
import com.warzone.edu_assist_service_session.vo.SessionVO;
@Service
public class SessionServiceImpl implements SessionService {
	@Autowired
	private SessionRepository sessionRepository;
	@Override
	public List<Academic_year> getAcademicYearTreeByTeacherid(String teacherid) {
		return sessionRepository.getAcademic_yearSessionInfoByTeacherid(teacherid);
	}

	@Override
	public boolean addSession(SessionVO sessionVO) {
		AcademicYearDTO academicYearDTO = new AcademicYearDTO();
		academicYearDTO.setStart_year(sessionVO.getStart_year());
		academicYearDTO.setEnd_year(sessionVO.getEnd_year());
		academicYearDTO.setSemester(sessionVO.getSemester());
		//只设置course_name,不用设置course_id,
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourse_name(sessionVO.getCourse());
		//只设置lecturer_id即可
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setLecturer_id(sessionVO.getLecturer_id());
		return sessionRepository.addSession(academicYearDTO,courseDTO, sessionDTO);
	}

	@Override
	public boolean deleteSessionBySessionid(int sessionid) {
		return sessionRepository.deleteSessionBySessionID(sessionid);
	}

	@Override
	public boolean updateSessionNameBySessionid(int sessionid,String name) {
		return sessionRepository.updateSessionNameBySessionID(sessionid, name);
	}

	@Override
	public List<Session> getSessionByAcademicYearSemester(AcademicYearVO academicYearVO) {
		AcademicYearDTO academicYearDTO = new AcademicYearDTO();
		academicYearDTO.setEnd_year(academicYearVO.getEnd_year());
		academicYearDTO.setStart_year(academicYearVO.getStart_year());
		academicYearDTO.setSemester(academicYearVO.getSemester());
		return sessionRepository.getSessionByAcademicYearSemester(academicYearDTO);
	}

	@Override
	public Academic_year getAcademicYearByTeacheridAndYear(String teacherid, String start_year, String end_year) {
		return sessionRepository.filterAcademic_year(teacherid, start_year, end_year);
	}

	@Override
	public Academic_year getSemester(String teacherid, String start_year, String end_year, String semester) {
		// TODO Auto-generated method stub
		return sessionRepository.filterSemester(teacherid, start_year, end_year, semester);
	}

	@Override
	public int getSessionID(String teacherid, String start_year, String end_year,String semester ,String course) {
		// TODO Auto-generated method stub
		return sessionRepository.getSessionID(teacherid, start_year, end_year, semester, course);
	}

	@Override
	public void setSessionComplete(int sessionid) {
		// TODO Auto-generated method stub
		sessionRepository.setSessionCompleted(sessionid);
	}

	@Override
	public String getSessionStatus(String teacherid, String start_year, String end_year, String semester_name,
			String course) {
		// TODO Auto-generated method stub
		return sessionRepository.getSessionStatus(teacherid, start_year, end_year, semester_name, course);
	}

	@Override
	public Academic_year getQualityAcademicyearBySessionid(int sessionid) {
		// TODO Auto-generated method stub
		return sessionRepository.getQualityAcademicyearBySessionid(sessionid);
	}
	
	

}
