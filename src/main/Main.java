package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.performDemo();
	}

	private void performDemo() {
		Session session = buildSessionFactory().getCurrentSession();
		session.beginTransaction();
		// delete
		deleteTableContent(session);
		// insert
		insertLocales(session);
		// select
		List<Locale> allLocales = loadAllLocales(session);
		// update
		correctLocales(allLocales);
		// debug output
		showResult(allLocales);
		session.getTransaction().commit();
	}

	private void createLocale(Session session, String type) {
		Locale locale = new Locale();
		locale.setType(type);
		session.save(locale);
	}

	private void deleteTableContent(Session session) {
		session.createQuery("delete from " + Locale.class.getName()).executeUpdate();
	}

	private void insertLocales(Session session) {
		String[] localeTypes = new String[] { "DE_de", "en_GB", "en_US" };
		for (String localeType : localeTypes) {
			createLocale(session, localeType);
		}
	}

	private List<Locale> loadAllLocales(Session session) {
		return session.createQuery("from " + Locale.class.getName()).list();
	}

	private void showResult(List<Locale> allLocales) {
		System.out.println("== RESULT =====================================================");
		for (Locale locale : allLocales) {
			System.out.println("id: " + locale.getId());
			System.out.println("type: " + locale.getType());
		}
	}

	private void correctLocales(List<Locale> allLocales) {
		for (Locale locale : allLocales) {
			if (locale.getType().equals("DE_de")) {
				locale.setType("de_DE");
			}
		}
	}

	public SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

}
