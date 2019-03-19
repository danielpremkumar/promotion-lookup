package com.xcc.promotion.lookup.configuration;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.sql.SQLException;

@Configuration
@ConfigurationProperties("oracle")
public class OracleConfig {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String juUrl;

    @NotNull
    private String dbUrl;

    @NotNull
    private String caUrl;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJuUrl() {
        return juUrl;
    }

    public void setJuUrl(String juUrl) {
        this.juUrl = juUrl;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getCaUrl() {
        return caUrl;
    }

    public void setCaUrl(String caUrl) {
        this.caUrl = caUrl;
    }

    @Bean(name = "dbDataSource")
    DataSource dbDataSource() throws SQLException {
        OracleDataSource dataSource = initDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(dbUrl);
        return dataSource;
    }

    @Bean(name = "caDataSource")
    DataSource caDataSource() throws SQLException {
        OracleDataSource dataSource = initDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(caUrl);
        return dataSource;
    }

    @Bean(name = "juDataSource")
    DataSource juDataSource() throws SQLException {
        OracleDataSource dataSource = initDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(juUrl);
        return dataSource;
    }

    private OracleDataSource initDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        java.util.Properties prop = new java.util.Properties();
        prop.setProperty("MinLimit", "0");
        prop.setProperty("MaxLimit", "1");
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setConnectionProperties(prop);
        dataSource.setFastConnectionFailoverEnabled(true);
        return dataSource;
    }
}
