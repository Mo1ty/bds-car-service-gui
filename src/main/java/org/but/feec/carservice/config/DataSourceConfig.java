package org.but.feec.carservice.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static final String APPLICATION_PROPERTIES = "application.properties";

    public static void initializeDataSource(){
        try (InputStream resourceStream = DataSourceConfig.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES)) {
            Properties properties = new Properties();

            properties.load(resourceStream);

            config.setJdbcUrl(properties.getProperty("datasource.url"));
            config.setUsername(properties.getProperty("datasource.username"));
            config.setPassword(properties.getProperty("datasource.password"));

            logger.info("Attempt to log into database with this data:");
//            logger.info("Username: " + config.getUsername());
//            logger.info("Password: " + config.getPassword());
            ds = new HikariDataSource(config);
            logger.info("Database connected successfully!");

        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            logger.error("Configuration of the datasource was not successful.", e);
        } catch (Exception e) {
            logger.error(e.getClass() + "  --  " + e.getCause() + "  --  " + e.getMessage());
            logger.error("Could not connect to the database.", e);
        }
    }

    private DataSourceConfig() {
    }

    public static DataSource getDataSource() {
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        logger.info("Attempt to get logs!");
        return ds.getConnection();

    }
}
