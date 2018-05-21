package com.warzone.edu_assist_service_task.path;

import com.warzone.edu_assist_util.path.Catalogue;
import com.warzone.edu_assist_util.path.Director;
import com.warzone.edu_assist_util.path.Path;

public class TaskUPPath extends Path{
	private String classes_id;
	private String task_id;
	
	@Override
	protected Catalogue catalogue() {
		// TODO Auto-generated method stub
		return Catalogue.学生上交作业附件类;
	}

	@Override
	protected Director directorRule() {
		Director director = new Director();
		director.setName("/"+classes_id);
		Director director2 = new Director();
		director.setSub(director2);
		director2.setName("/"+task_id);
		return director;
	}

	public String getClasses_id() {
		return classes_id;
	}

	public void setClasses_id(String classes_id) {
		this.classes_id = classes_id;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

}
