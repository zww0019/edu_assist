package com.warzone.edu_assist.web.dto;

import java.util.List;

public class VSemesterDTO {
	private String name;
	private List<VSessionDTO> vSessionDTOs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<VSessionDTO> getvSessionDTOs() {
		return vSessionDTOs;
	}
	public void setvSessionDTOs(List<VSessionDTO> vSessionDTOs) {
		this.vSessionDTOs = vSessionDTOs;
	}
	
	
	
}
