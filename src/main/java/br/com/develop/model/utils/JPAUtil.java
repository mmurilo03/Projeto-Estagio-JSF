package br.com.develop.model.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("my_persistence_unit");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
