package com.warzone.edu_assist.controller.resources;



import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warzone.edu_assist.controller.teacher.viewhelper.VSessionParemterDTO;
import com.warzone.edu_assist.web.dto.Node;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.edu_assist_domain_session.Semester;
import com.warzone.edu_assist_domain_session.Session;
import com.warzone.edu_assist_domain_user.User;
import com.warzone.edu_assist_service_session.service.SessionService;
import com.warzone.edu_assist_service_session.vo.SessionVO;


@Controller
@RequestMapping("resources")
public class SessionResource {
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value="{teacher_id}/sessions",method=RequestMethod.GET,produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public List<Academic_year> getAcademicYearTrees(@PathVariable String teacher_id){
		return sessionService.getAcademicYearTreeByTeacherid(teacher_id);
	}
	@RequestMapping(value="acadeYearTree",method=RequestMethod.GET)
	public @ResponseBody List<Node> getAcadeYearTree(HttpServletRequest request) {
		User user =(User)request.getSession().getAttribute("user");
		System.out.println(user.getUserid());
		List<Academic_year> academic_years = sessionService.getAcademicYearTreeByTeacherid(user.getUserid());
		List<Node> tree = new ArrayList<>();
		for(Academic_year academic_year : academic_years) {
			Node root = new Node();
			root.setText(academic_year.getStart_year()+"-"+academic_year.getEnd_year());
			List<Node> subNodes = new ArrayList<>();
			for(Semester semester: academic_year.getSemesters()) {
				Node subnode = new Node();
				subnode.setText(semester.getSemester());
				subNodes.add(subnode);
				List<Node> subsubNodes = new ArrayList<>();
				for(Session session : semester.getSessions()) {
					Node subsubnode = new Node();
					subsubnode.setText(session.getCourse().getCourse_name());
					subsubNodes.add(subsubnode);
				}
				subnode.setNodes(subsubNodes);
			}
			root.setNodes(subNodes);
			tree.add(root);
		}
		return tree;
	}
	@RequestMapping(value="session",method=RequestMethod.DELETE,produces= {"application/json;charset=UTF-8"})
	public void deleteSession(VSessionParemterDTO vSessionDTO,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Writer writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		int sessionid=sessionService.getSessionID(user.getUserid(), vSessionDTO.getStart_year(), vSessionDTO.getEnd_year(), vSessionDTO.getSemester(), vSessionDTO.getCourse());
		if(sessionid!=-1) {
			sessionService.deleteSessionBySessionid(sessionid);
			writer.write("success");
		}else {
			writer.write("不存在");
		}
	}
	@RequestMapping(value="session",method=RequestMethod.POST,produces= {"application/json;charset=UTF-8"})
	public void addSession(VSessionParemterDTO vSessionDTO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Writer writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		SessionVO sessionVO = new SessionVO();
		sessionVO.setCourse(vSessionDTO.getCourse());
		sessionVO.setEnd_year(vSessionDTO.getEnd_year());
		sessionVO.setStart_year(vSessionDTO.getStart_year());
		sessionVO.setSemester(vSessionDTO.getSemester());
		sessionVO.setLecturer_id(user.getUserid());
		if(sessionService.addSession(sessionVO)) {
			writer.write("success");
		}else {
			writer.write("fail");
		}
		writer.close();
	}
	@RequestMapping(value="session",method=RequestMethod.PUT,produces= {"application/json;charset=UTF-8"})
	public void completeSession(VSessionParemterDTO vSessionDTO,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Writer writer = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		int sessionid = sessionService.getSessionID(user.getUserid(), vSessionDTO.getStart_year(), vSessionDTO.getEnd_year(), vSessionDTO.getSemester(), vSessionDTO.getCourse());
		if(sessionid!=-1) {
			sessionService.setSessionComplete(sessionid);
			writer.write("success");
		}else {
			writer.write("不存在");
		}
		
		
	}
	//@RequestMapping(value="acadeyear",method=RequestMethod.GET,produces= {"application/json;charset=UTF-8"})
	@RequestMapping(value="{start_year}/{end_year}/sessions",method=RequestMethod.GET,produces= {"application/json;charset=UTF-8"})
	public @ResponseBody Academic_year getSessionByteacheAndAcademicyear(@PathVariable String teacherid,@PathVariable String start_year,@PathVariable String end_year,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		for(Academic_year academic_year : sessionService.getAcademicYearTreeByTeacherid(user.getUserid())) {
			if(academic_year.getStart_year().equals(start_year) && academic_year.getEnd_year().equals(end_year)) {
				return academic_year;
			}
		}
		return null;
	}
}
