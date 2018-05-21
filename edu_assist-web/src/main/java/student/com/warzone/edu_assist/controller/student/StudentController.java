package com.warzone.edu_assist.controller.student;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.warzone.edu_assist.controller.student.viewhelper.ViewAcademicYearDTO;
import com.warzone.edu_assist.controller.student.viewhelper.ViewClassesDTO;
import com.warzone.edu_assist.controller.student.viewhelper.ViewSemesterDTO;
import com.warzone.edu_assist.controller.student.viewhelper.ViewSessionDTO;
import com.warzone.edu_assist.controller.student.viewhelper.ViewTaskDTO;
import com.warzone.edu_assist.web.dto.VSessionDTO;
import com.warzone.edu_assist_dao_classes.dto.ClassesDTO;
import com.warzone.edu_assist_dao_task.dto.TaskComplectionDTO;
import com.warzone.edu_assist_domain_classes.Classes;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Session;
import com.warzone.edu_assist_domain_student.Student;
import com.warzone.edu_assist_domain_task.Task;
import com.warzone.edu_assist_domain_task.Task_Completion;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_service_classes.service.ClassesService;
import com.warzone.edu_assist_service_session.service.SessionService;
import com.warzone.edu_assist_service_student.service.StudentService;
import com.warzone.edu_assist_service_task.service.TaskService;
import com.warzone.edu_assist_service_teacher.service.TeacherService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private ClassesService classService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private StudentService studentService;
	@RequestMapping(value = "mainpage", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView vModelAndView = new ModelAndView();
		User user = (User)request.getSession().getAttribute("user");
		Student student = studentService.getStudentByStudentid(user.getUserid());
		HashMap<Integer, List<Task>> sessionid_tasks = classService.getSessionidAndTasksByStudentid(student.getStudentid());
		
		List<ViewAcademicYearDTO> viewAcademicYearDTOs = new ArrayList<>();
		
		
		for(Integer sessionid : sessionid_tasks.keySet()) {
			List<ViewSemesterDTO> viewSemesterDTOs = new ArrayList<>();
			List<ViewSessionDTO> viewSessionDTOs = new ArrayList<>();
			Academic_year mYear = sessionService.getQualityAcademicyearBySessionid(sessionid);
			ViewAcademicYearDTO viewAcademicYearDTO = new ViewAcademicYearDTO();
			ViewSemesterDTO viewSemesterDTO = new ViewSemesterDTO();
			ViewSessionDTO viewSessionDTO = new ViewSessionDTO();
			if(mYear!=null) {
				//设置视图_学年
				viewAcademicYearDTO.setStart_year(mYear.getStart_year());
				viewAcademicYearDTO.setEnd_year(mYear.getEnd_year());
				//设置视图_学期
				viewSemesterDTO.setName(mYear.getSemesters().get(0).getSemester());
				//设置视图_课期
				viewSessionDTO.setCoursename(mYear.getSemesters().get(0).getSessions().get(0).getCourse().getCourse_name());
				viewSessionDTO.setStatus(mYear.getSemesters().get(0).getSessions().get(0).getCompletion_status());
			}
			//设置视图_作业
			List<ViewClassesDTO> classesDTOs = new ArrayList<>();
			
			for(Classes classes:classService.getSimplyClassesBySessionID(sessionid))
			{
				ViewClassesDTO classesDTO = new ViewClassesDTO();
				classesDTO.setClassesid(classes.getClasses_id());
				classesDTO.setClassesname(classes.getClasses_name());
				List<ViewTaskDTO> viewtaskDTOs = new ArrayList<>();
				List<Task> tasks = taskService.findTaskReportByClassesid(classes.getClasses_id());
				if(tasks!=null) {
				for(Task task : tasks) {
				ViewTaskDTO viewTaskDTO = new ViewTaskDTO();
				viewTaskDTO.setEnd_date(task.getEnd_date());
				String status="0";
				for(Task_Completion taskComplectionDTO : task.getTask_Completions()) {
					if(taskComplectionDTO.getStudent_id().equals(student.getStudentid()))
					status=taskComplectionDTO.getComplete_status();
				}
				
				viewTaskDTO.setStatus(status);
				viewTaskDTO.setTask_content(task.getTask_content());
				viewTaskDTO.setTask_id(task.getTask_id());
				viewTaskDTO.setTask_name(task.getTask_name());
				viewTaskDTO.setTask_directory(task.getTask_directory());
				viewtaskDTOs.add(viewTaskDTO);
				}
				}
				classesDTO.setTaskDTOs(viewtaskDTOs);
				classesDTOs.add(classesDTO);
			}
			viewSessionDTO.setClassesDTOs(classesDTOs);
			viewSessionDTOs.add(viewSessionDTO);
			viewSemesterDTOs.add(viewSemesterDTO);
			viewSemesterDTO.setSessionDTOs(viewSessionDTOs);
			viewAcademicYearDTO.setSemesterDTOs(viewSemesterDTOs);
			viewAcademicYearDTOs.add(viewAcademicYearDTO);
		}
		vModelAndView.addObject("student", student);
		vModelAndView.addObject("academic_years", viewAcademicYearDTOs);
		vModelAndView.setViewName("student/student_main");
		return vModelAndView;
	}
	@RequestMapping(value = "upTask", method = RequestMethod.POST)
	public void uploadTask(@RequestParam("rar_or_zip_file") MultipartFile file,@RequestParam("task_id") int task_id,HttpServletResponse response,HttpServletRequest request) throws IOException {
		Writer writer = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		Student student = studentService.getStudentByStudentid(user.getUserid());
		String task_directory = taskService.getTaskByTaskid(task_id).getTask_directory();
		String file_name = file.getOriginalFilename();
		File targetFile = new File(task_directory, file_name);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		String task_path = task_directory+"/"+file_name;
		if(taskService.updateComplectionStatus(task_id, student.getStudentid(), task_path)) {
			writer.write("success");
		}else {
			writer.write("失败");
		}
		writer.close();
	}
	// 文件上传
	/**
	 * spring mvc封装了上传文件 将其封装为一个file对象
	 */
	public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest req) {
		// 获取上传位置
		String path = req.getRealPath("/upload");
		// 获取文件名
		String filename = file.getOriginalFilename();
		try {
			InputStream is = file.getInputStream();
			OutputStream os = new FileOutputStream(new File(path, filename));
			int len = 0;
			byte[] buffer = new byte[400];
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:student_main.jsp";
	}

	@RequestMapping("/batch.do")
	public String batch(@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest req) {
		// 获取上传位置
		String path = req.getRealPath("/upload");
		for (int i = 0; i < file.length; i++) {
			// 获取文件名
			String filename = file[i].getOriginalFilename();
			try {
				InputStream is = file[i].getInputStream();
				OutputStream os = new FileOutputStream(new File(path, filename));
				int len = 0;
				byte[] buffer = new byte[400];
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
				os.close();
				is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:student_main.jsp";
	}

}
