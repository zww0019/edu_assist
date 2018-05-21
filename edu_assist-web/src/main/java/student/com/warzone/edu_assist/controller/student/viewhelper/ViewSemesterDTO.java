package com.warzone.edu_assist.controller.student.viewhelper;

import java.util.List;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

public class ViewSemesterDTO {
	private String name;
	private List<ViewSessionDTO> sessionDTOs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ViewSessionDTO> getSessionDTOs() {
		return sessionDTOs;
	}
	public void setSessionDTOs(List<ViewSessionDTO> sessionDTOs) {
		this.sessionDTOs = sessionDTOs;
	}
	
}
