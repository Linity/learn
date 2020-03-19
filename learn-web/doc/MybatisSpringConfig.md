# spring-boot集成原生mybatis

## 通过Java方式在Spring框架中注册MyBatis的核心组件Bean，并且配置声明式事务管理

    @Configuration
    @EnableTransactionManagement
    public class MyBatisSpringConfig implements TransactionManagementConfigurer {
        @Autowired
        private DataSource dataSource;
        
        // 在Spring中注册SqlSessionFactory，在这里可以设置一下参数：
        // 1.设置分页参数
        // 2.配置MyBatis运行时参数
        // 3.注册xml映射器
        @Bean
        public SqlSessionFactory sqlSessionFactory() {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 设置数据源
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 设置映射POJO对象包名
            // sqlSessionFactoryBean.setTypeAliasesPackage("org.chench.test.springboot.model");
            
            // 分页插件
            /*PageHelper pageHelper = new PageHelper();
            Properties properties = new Properties();
            properties.setProperty("reasonable", "true");
            properties.setProperty("supportMethodsArguments", "true");
            properties.setProperty("returnPageInfo", "check");
            properties.setProperty("params", "count=countSql");
            pageHelper.setProperties(properties);*/
            //添加插件
            //sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
            
            // 配置mybatis运行时参数
            org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
            // 自动将数据库中的下划线转换为驼峰格式
            configuration.setMapUnderscoreToCamelCase(true);
            configuration.setDefaultFetchSize(100);
            configuration.setDefaultStatementTimeout(30);
            
            sqlSessionFactoryBean.setConfiguration(configuration);
            
            // 在构建SqlSessionFactory时注册xml映射器
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            try {
                sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
                return sqlSessionFactoryBean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        
        /**
         * 注入sqlSession对象
         * @param sqlSessionFactory
         * @return
         */
        @Bean(value = "sqlSession")
        public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }
    
        // Spring事务管理器
        @Bean(value = "transactionManager")
        @Override
        public PlatformTransactionManager annotationDrivenTransactionManager() {
            return new DataSourceTransactionManager(dataSource);
        }
    }

## 注册MyBatis接口映射器
MyBatis 3支持2种映射器：xml映射器和接口映射器，其中xml映射器可以在构建SqlSessionFactory时进行注册。

    @Configuration
    @AutoConfigureAfter(MyBatisSpringConfig.class) //注意，由于MapperScannerConfigurer执行的比较早，所以必须有该注解
    public class MyBatisMapperScannerConfig {
        @Bean
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            // 设置sqlSessionFactory名
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
            // 设置接口映射器基础包名
            mapperScannerConfigurer.setBasePackage("org.chench.test.springboot.mapper");
            Properties properties = new Properties();
            //properties.setProperty("mappers", "org.chench.test.springboot.mapper");
            properties.setProperty("notEmpty", "false");
            properties.setProperty("IDENTITY", "MYSQL");
            mapperScannerConfigurer.setProperties(properties);
            return mapperScannerConfigurer;
        }
    }
    
## 定义并使用映射器

- 定义接口映射器

        @Repository
        public interface AccountMapper {
            @Select("select * from account where id = #{id}")
            public Account getAccountById(@Param("id") long id);
        }
    
- 使用接口映射器

        @Service
        public class AccountService {
            // 直接注入接口映射器Bean进行使用
            @Autowired
            private AccountMapper accountMapper;
            
            public Account getAccountById(long id) {
                return accountMapper.getAccountById(id);
            }
        }