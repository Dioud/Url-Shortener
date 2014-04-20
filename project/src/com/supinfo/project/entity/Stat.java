package com.supinfo.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Stats")
public class Stat {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String referer;
	private String pays;
	private String date;

	@ManyToOne
	private Url url;


	public Stat() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer =referer;
	}
	
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays =pays;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date =string;
	}
	public Url getUrl() {
		return url;
	}
	public void setUrl(Url url) {
		this.url = url;
	}
}


