package model;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Handles the creation and management of the database connection pool.
 * Provides a static method to retrieve a connection from the pool.
 *
 * @author Rinaldo Perna
 */
public class ConnectionDatabase {
    private static DataSource datasource;

    /**
     * Returns a connection from the pool. Initializes the pool if not already done.
     *
     * @return Connection to the database
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver is not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            // Use Docker service name 'db' as host, and correct root credentials
            p.setUrl("jdbc:mysql://db:3306/BuyandPlay?serverTimezone=" + TimeZone.getDefault().getID());
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername("root");
            p.setPassword("root");
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMinIdle(10);
            p.setRemoveAbandonedTimeout(60);
            p.setRemoveAbandoned(true);
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
