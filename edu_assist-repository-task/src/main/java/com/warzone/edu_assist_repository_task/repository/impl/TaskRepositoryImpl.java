package com.warzone.edu_assist_repository_task.repository.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.internal.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.warzone.edu_assist_dao_student.dto.StudentDTO;
import com.warzone.edu_assist_dao_task.dao.TaskDAO;
import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_dao_task.dto.TaskDTO;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_repository_student.repository.StudentRepository;
import com.warzone.edu_assist_repository_task.repository.TaskRepository;
import com.warzone.edu_assist_status.TaskStatus;
@Repository
@Scope(value=WebApplicationContext.SCOPE_SESSION,proxyMode=ScopedProxyMode.INTERFACES)
public class TaskRepositoryImpl implements TaskRepository {
	private  HashMap<Integer,List<Task>> classesid_taskdtos = new HashMap<>();
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public List<Task> findTaskReportByClassesid(int classesid) {
		if(classesid_taskdtos.containsKey(classesid)) {
			return classesid_taskdtos.get(classesid);
		}else {
			List<TaskDTO> taskdtos = taskDAO.findTaskByClassid(classesid);
			if(!taskdtos.isEmpty()) {
				List<Task> tasks = new ArrayList<>();
				for(TaskDTO taskDTO : taskdtos) {
					Task task = new Task();
					task.setAffix_position(taskDTO.getAffix_position());
					task.setClasses_id(task.getClasses_id());
					task.setEnd_date(taskDTO.getEnd_date());
					task.setPub_date(taskDTO.getPub_date());
					task.setTask_content(taskDTO.getTask_content());
					task.setTask_directory(taskDTO.getTask_directory());
					task.setTask_id(taskDTO.getTask_id());
					task.setTask_name(taskDTO.getTask_name());
					List<Task_Completion> task_Completions = new ArrayList<>();
					for(TaskComplectionDTO taskComplectionDTO : taskDTO.getTaskComplectionDTOs()) {
						Task_Completion task_Completion = new Task_Completion();
						task_Completion.setComplete_status(taskComplectionDTO.getComplete_status());
						task_Completion.setStudent_id(taskComplectionDTO.getStudent_id());
						task_Completion.setStudent_name(taskComplectionDTO.getStudent_name());
						task_Completion.setTask_id(taskComplectionDTO.getTask_id());
						task_Completion.setTask_position(taskComplectionDTO.getTask_position());
						task_Completion.setClassa(taskComplectionDTO.getClassa());
						task_Completions.add(task_Completion);
					}
					task.setTask_Completions(task_Completions);
					tasks.add(task);
				}
				classesid_taskdtos.put(classesid, tasks);
				return classesid_taskdtos.get(classesid);
			}else {
				return null;
			}
		}
	}

	@Override
	@Transactional
	public int addTask(TaskDTO task) {
		taskDAO.insertTask(task);
		List<TaskComplectionDTO> taskComplectionDTOs = new ArrayList<>();
		int classesid = task.getClasses_id();
		int task_id = task.getTask_id();
		List<Student> students = studentRepository.getStudentByClassid(classesid);
		for(Student student : students) {
			TaskComplectionDTO taskComplectionDTO = new TaskComplectionDTO();
			taskComplectionDTO.setStudent_id(student.getStudentid());
			taskComplectionDTO.setStudent_name(student.getName());
			taskComplectionDTO.setTask_id(task_id);
			taskComplectionDTO.setClassa(student.getClassa());
			taskComplectionDTOs.add(taskComplectionDTO);
		}
		taskDAO.batchInsertTaskComplectionMeasure(taskComplectionDTOs);
		classesid_taskdtos.clear();
		return task_id;
	}

	@Override
	public boolean deleteTaskByTaskid(int taskid) {
		boolean flag = false;
		if(taskDAO.deleteTaskByTask_id(taskid)==1)
			flag=true;
		classesid_taskdtos.clear();
		return flag;
	}

	@Override
	public List<Task_Completion> getComplectionStudentList(int classesid,int taskid) {
		List<Task_Completion> taskComplections = new ArrayList<>();
		if(!classesid_taskdtos.containsKey(classesid)) {
			return null;
		}else {
			for(Task task : classesid_taskdtos.get(classesid)) {
				if(task.getTask_id()==taskid) {
				for(Task_Completion taskComplection : task.getTask_Completions()) {
					if(taskComplection.getComplete_status().equals(TaskStatus.完成.getStatus())) {
						taskComplections.add(taskComplection);
					}
				}
				}
			}
		}
		return taskComplections;
	}

	@Override
	public List<Task_Completion> getUnComplectionStudentList(int classesid,int taskid) {
		List<Task_Completion> taskComplectionDTOs = new ArrayList<>();
		if(!classesid_taskdtos.containsKey(classesid)) {
			return null;
		}else {
			for(Task taskDTO : classesid_taskdtos.get(classesid)) {
				if(taskDTO.getTask_id()==taskid) {
				for(Task_Completion taskComplectionDTO : taskDTO.getTask_Completions()) {
					if(taskComplectionDTO.getComplete_status().equals(TaskStatus.未完成.getStatus())) {
						taskComplectionDTOs.add(taskComplectionDTO);
					}
				}
				}
			}
		}
		return taskComplectionDTOs;
	}

	@Override
	public boolean updateComplectionStatus(int taskid, String studentid,String position) {
		boolean flag = false;
		if(taskDAO.updateTaskComplectionStatus(taskid, studentid,position)==1) {
			flag=true;
			classesid_taskdtos.clear();
		}
		return flag;
	}

	@Override
	public boolean updateContentByTaskid(int taskid,String content) {
		boolean flag=false;
		if(taskDAO.updateContent(content, taskid)==1) {
			flag=true;
			classesid_taskdtos.clear();
		}
		return flag;
	}

	@Override
	@Transactional
	public void updateTaskDelay(int taskid, String studentid, String verifier,String status) {
		taskDAO.updateTaskDelayStatus(taskid, studentid, verifier,status);
		if(status.equals("2")) {
			Date date = taskDAO.findTaskDelayDTOByTask_id(taskid).getTo_date();
			taskDAO.updateTaskEndDate(taskid, date);
		}
	}

	@Override
	public void updateTaskPath(int taskid, String task_directory) {
		// TODO Auto-generated method stub
		taskDAO.updatePath(taskid, task_directory);
	}

	@Override
	public void updateTaskAffix(int taskid, String affix_path) {
		// TODO Auto-generated method stub
		taskDAO.updateTaskAffix(taskid, affix_path);
	}

	@Override
	public List<Task_Completion> geTaskComplectionDTOsByClassesIDAndNaturalClass(int classesid, String classa,int taskid) {
		if(!classesid_taskdtos.containsKey(classesid)) {
			findTaskReportByClassesid(classesid);
		}
		List<Task_Completion> taskComplectionDTOs = new ArrayList<>();
		for(Task taskDTO : classesid_taskdtos.get(classesid)) {
			if(taskDTO.getTask_id()==taskid) {
				for(Task_Completion taskComplectionDTO : taskDTO.getTask_Completions()) {
					if(taskComplectionDTO.getClassa().equals(classa)) {
						taskComplectionDTOs.add(taskComplectionDTO);
					}
				}
			}
		}
		return taskComplectionDTOs;
	}

	@Override
	public List<Task_Completion> getTaskComplectionDTOsByClassesID(int classesid, int taskid) {
		if(!classesid_taskdtos.containsKey(classesid)) {
			findTaskReportByClassesid(classesid);
		}
		for(Task taskDTO : classesid_taskdtos.get(classesid)) {
			if(taskDTO.getTask_id()==taskid) {
				return taskDTO.getTask_Completions();
			}
		}
		return null;
	}


	@Override
	public Task_Completion geTaskComplectionDTOByStudentid(int classesid, int taskid, String studentid) {
		if(!classesid_taskdtos.containsKey(classesid)) {
			findTaskReportByClassesid(classesid);
		}
		for(Task taskDTO : classesid_taskdtos.get(classesid)) {
			if(taskDTO.getTask_id()==taskid) {
				for(Task_Completion taskComplectionDTO : taskDTO.getTask_Completions()) {
					if(taskComplectionDTO.getStudent_id().equals(studentid)) {
						return taskComplectionDTO;
					}
				}
			}
		}
		return null;
	}

	@Override
	public HashMap<TaskStatus, List<Task_Completion>> getTaskComplectionDTOsOFNaturalClass(int classesid, int taskid, String classname) {
		if(!classesid_taskdtos.containsKey(classesid)) {
			findTaskReportByClassesid(classesid);
		}
		HashMap<TaskStatus, List<Task_Completion>> taskComplection = new HashMap<>();
		if(taskComplection.isEmpty()) {
			taskComplection.put(TaskStatus.完成, new ArrayList<>());
			taskComplection.put(TaskStatus.未完成, new ArrayList<>());
		}
		for(Task taskDTO : classesid_taskdtos.get(classesid)) {
			if(taskDTO.getTask_id()==taskid) {
				for(Task_Completion taskComplectionDTO : taskDTO.getTask_Completions()) {
					if(taskComplectionDTO.getClassa().equals(classname)) {
						if(taskComplectionDTO.getComplete_status().equals(TaskStatus.完成.getStatus())) {
							taskComplection.get(TaskStatus.完成).add(taskComplectionDTO);
						}else {
							taskComplection.get(TaskStatus.未完成).add(taskComplectionDTO);
						}
					}
					
				}
			}
		}
		return taskComplection;
	}

	@Override
	public Task getTaskByTaskid(int taskid) {
		// TODO Auto-generated method stub
		TaskDTO taskDTO = taskDAO.selectTaskByTaskid(taskid);
		Task task = new Task();
		task.setTask_content(taskDTO.getTask_content());
		task.setTask_directory(taskDTO.getTask_directory());
		return task;
	}

}
