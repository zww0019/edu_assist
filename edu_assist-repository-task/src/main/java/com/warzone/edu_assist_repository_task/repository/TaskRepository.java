package com.warzone.edu_assist_repository_task.repository;

import java.util.HashMap;
import java.util.List;

import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_status.TaskStatus;

public interface TaskRepository {
	public List<Task> findTaskReportByClassesid(int classesid);
	public int addTask(TaskDTO task);
	public boolean deleteTaskByTaskid(int taskid);
	public List<Task_Completion> getComplectionStudentList(int classesid,int taskid);
	public List<Task_Completion> getUnComplectionStudentList(int classesid,int taskid);
	public boolean updateComplectionStatus(int taskid,String studentid,String position);
	public boolean updateContentByTaskid(int taskid,String content);
	public void updateTaskDelay(int taskid,String studentid,String verifier,String status);
	public void updateTaskPath(int taskid,String task_directory );
	public void updateTaskAffix(int taskid,String affix_path);
	public List<Task_Completion> geTaskComplectionDTOsByClassesIDAndNaturalClass(int classesid,String classa,int taskid);
	public List<Task_Completion> getTaskComplectionDTOsByClassesID(int classesid,int taskid);
	public Task_Completion geTaskComplectionDTOByStudentid(int classesid,int taskid,String studentid);
	public HashMap<TaskStatus, List<Task_Completion>> getTaskComplectionDTOsOFNaturalClass(int classesid,int taskid,String classname);
	public Task getTaskByTaskid(int taskid);
}
