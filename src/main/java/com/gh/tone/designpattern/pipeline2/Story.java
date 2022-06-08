package com.gh.tone.designpattern.pipeline2;

public class Story {
	public String when;
	public String where;
	public String who;
	public String what;
	public String why;
	public String how;

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}

	public void clear() {
		this.setHow(null);
		this.setWhat(null);
		this.setWhen(null);
		this.setWhere(null);
		this.setWho(null);
		this.setWhy(null);
		this.setHow(null);
	}
}
