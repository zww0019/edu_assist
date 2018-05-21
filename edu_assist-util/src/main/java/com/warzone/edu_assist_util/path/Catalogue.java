package com.warzone.edu_assist_util.path;

public enum Catalogue {
	教师作业要求附件类("/teacher_task_affix"),
	学生上交作业附件类("/student_task_file");
	
	private String catalogue;

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}
	
	private Catalogue(String str) {
		this.catalogue = str;
	}
}
