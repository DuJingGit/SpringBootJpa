package com.itmayiedu;

import com.itmayiedu.config.DBConfig2;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecond",
        transactionManagerRef = "transactionManagerSecond",
        basePackages = {"com.itmayiedu.dao.second"}
)
public class SecondDataConfig {

    @Resource
    private HibernateProperties hibernateProperties;

    @Resource
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        Map<String, Object> map = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());return map;
    }

    @Primary
    @Bean(name = "secondDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource targetDataSource(DBConfig2 testConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(testConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(testConfig.getPassword());
        mysqlXaDataSource.setUser(testConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("secondDataSource");
        xaDataSource.setMaxPoolSize(5);
        return xaDataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactorySecond")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("secondDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties())
                .packages("com.itmayiedu.entity.second")
                .persistenceUnit("targetPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager propertyTransactionManager(
            @Qualifier("entityManagerFactorySecond") EntityManagerFactory propertyEntityManagerFactory) {
        return new JpaTransactionManager(propertyEntityManagerFactory);
    }
}