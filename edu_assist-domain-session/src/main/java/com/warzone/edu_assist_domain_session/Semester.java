package com.warzone.edu_assist_domain_session;

import java.util.List;

public class Semester {
	private String semester;
	private List<Session> sessions;
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	@Override
	public String toString() {
		return "Semester [semester=" + semester + ", sessions=" + sessions + "]";
	}

	
	
}
