package re.com.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"re.com.controller","re.com.service.imp","re.com.repository.imp"})
@EnableTransactionManagement
public class AppConfig {
    //1. Cấu hình Spring - ViewResolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    //2. Cấu hình datasource kết nối với CSDL
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/flight_list_db");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    //3. Cấu hình hibernate properties
    public Properties addionalProperties() {
        Properties properties = new Properties();
        /*
         * Các mode của hbm2ddl.auto
         * - create: Khi ứng dụng chạy, xóa toàn bộ các bảng trong CSDL và tạo mới theo các entity --> thích hợp khi mới xây dựng ứng dụng
         * - update: Kiểm tra các bảng (cột trong bảng) trong CSDL có sự khác biệt gì so với các entity ko, có --> cập nhật lại các bảng trong CSDL (cập nhật thừa) --> trong quá trình phát triển
         *      + Entity: clm1, clm2, clm3 - Table: clm1, clm2 --> sinh theem clum3 trong table
         *      + Entity: clm1, clm2 - Table: clm1,clm2, clm3
         *      + Entity: clm1, clm2 - Table: clm1, clm2 --> sua clm1 trong entity thang clm3 --> table: clum1, clm2, clm3
         * - create-drop: khi ứng dụng chạy, xóa toàn bộ bảng trong CSDL --> tạo lại theo entiy, khi ứng dụng kết thúc --> xóa toàn bộ bảng trong CSDL --> kiểm thử
         * - validate: kiểm tra có sự khác biệt giữa entity và CSDL không --> có --> exception
         * */
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        //Lần đầu tiên sử dụng Hibernate thì nó thường sinh SAN database --> khi sinh bảng nó sẽ lock bảng đó
        //MySQL: SAN:tạo bảng sẽ lock bảng đó - InnoDB
        /*
         * Hibernate:
         * - Categories 1: N Product
         * - Entity: thuộc tính và rằng buộc các thuộc tính (PK, unique, FK)
         * - Tạo bảng Category (lock)
         * - Tạo bảng Product
         * - Tạo rằng buộc của Category (alter)
         * - Tạo rằng buộc của Project (alter)
         * */
        //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8InnoDBDialect");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

    //4. Cấu hình EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        //Chỉ định package chứa các entity để tạo các entity (chỉ sai thì ko tìm được các entity để khởi tạo)
        entityManagerFactory.setPackagesToScan("re.com.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(addionalProperties());
        return entityManagerFactory;
    }

    //5. Cấu hình EntityManager
    @Bean
    /*
     * IAPP, App1 extends IApp, App2 extends IApp
     * @Autowire IApp: ko biết là tiêm đối tượng app1 hay app2
     * @Qualifier(value = "app1")
     * */
    //@Qualifier = @Autowire + chỉ định rõ tiêm đối tượng tên là gì
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    //6. Cấu hình transaction
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}