package com.warzone.edu_assist_repository_classes.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.vfs2.impl.VirtualFileProvider;

import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_classes.NaturalClasses;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;


public interface ClassesRepository {
	public List<Classes> getDetailClasses(int sessionid);
	public int getStudentsSize(int session_id,int classesid);
	public List<Classes> getSimplyClasses(int sessionid);
	public void createClasses(ClassesDTO classesDTO,int session_id);
	public void batchInsertStudentToClasses(int classesid,List<StudentDTO> studentDTOs);
	public void updateTaskAffix(String path,int taskid);
	public List<Student> getStudentByClassa(int sessionid,int classesid,String classa);
	public Classes getClassesByClassesID(int sessionid,int classesid);
	public void publishTask(TaskDTO taskDTO);
	public void updateTaskPath(int taskid,String task_directory );
	public HashMap<Integer, List<Task>> getSessionidAndTasksByStudentid(String studentid) ;
	public List<ClassesDTO> getClassesByStudentid(String studentid);
	public ClassesDTO getClasssesBySessionid(int sessionid);
}
