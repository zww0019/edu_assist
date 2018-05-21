package com.warzone.edu_assist.controller.teacher.viewhelper;

public class VSemesterParameterDTO extends VAcademicYearParameterDTO{
	protected String semester;

	protected String getSemester() {
		return semester;
	}

	protected void setSemester(String semester) {
		this.semester = semester;
	}
	
}
