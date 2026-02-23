package org.utad.DataProvider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataProvider {
    private static SessionFactory factory = null;

    //constructor vacio
    private DataProvider() {
    }

    public static SessionFactory getFactory() {
        if (factory == null) {
            var cfg = new Configuration().configure();
            factory = cfg.buildSessionFactory();

        }
        return factory;

    }
}
