package main;

import java.util.HashSet;
import java.util.Set;

public class SelectedLocales {

	private long id;
	private Set<Locale> locales = new HashSet<Locale>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Set<Locale> getLocales() {
		return locales;
	}
	public void setLocales(Set<Locale> locales) {
		this.locales = locales;
	}

	
	
	
	
	
	
}
