package com.warzone.edu_assist_dao_session.dao;

import java.util.List;

import com.warzone.edu_assist_dao_session.dto.SessionDTO;
import com.warzone.edu_assist_domain_session.Academic_year;
import com.warzone.util.dao.BaseDAO;
@BaseDAO
public interface SessionDAO {
	public List<Academic_year> selectSessionByTeacher_id(String id);
	public int insertSession(SessionDTO sessionDTO);
	public int updateSessionName(int session_id,String newName);
	public int deleteSession(int sessionid);
	public int updateSessionStatus(int session_id);
	public Academic_year selectSessinBySessionid(int sessionid);
}
