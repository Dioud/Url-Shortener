package com.supinfo.project.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Urls")
public class Url {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String url;
	private String urlGenere;
	private String date;
	private Boolean enable;

	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="url")
	private List<Stat> stats;


	public Url() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlGenere() {
		return urlGenere;
	}

	public void setUrlGenere(String urlGenere) {
		this.urlGenere = urlGenere;
	}

	public String  getDate() {
		return date;
	}
	public void setDate(String string) {
		this.date = string;
	}
	public Boolean  getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Stat> getStats() {
		return stats;
	}
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}
}

