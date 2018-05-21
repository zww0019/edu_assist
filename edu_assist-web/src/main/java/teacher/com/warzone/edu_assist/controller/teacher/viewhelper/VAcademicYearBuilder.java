package com.warzone.edu_assist.controller.teacher.viewhelper;

import java.util.ArrayList;
import java.util.List;

import com.warzone.edu_assist.web.dto.VAcademicYearDTO;
import com.warzone.edu_assist.web.dto.VSemesterDTO;
import com.warzone.edu_assist.web.dto.VSessionDTO;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;

public class VAcademicYearBuilder {
	public VAcademicYearDTO getAcademicYearByYear(String start_year,String end_year,List<Academic_year> academic_years) {
		VAcademicYearDTO vAcademicYearDTO = new VAcademicYearDTO();
		return vAcademicYearDTO;
	}
	
}
