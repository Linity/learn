# spring-boot集成mybatis的方式

## 公共部分
1. 添加数据库驱动
2. 添加数据库连接池
3. 配置数据源
4. 配置数据库连接池

## 原生集成mybatis （详细参考doc/MybatisSpringConfig）

1. 在Spring中注册MyBatis的核心组件Bean：SqlSessionFactory，SqlSession，以及Spring的事务管理器。

2. 另外，在构建SqlSessionFactory时还可以注册MyBatis的xml映射器。

3. mybatis依赖

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${version.mybatis}</version>
        </dependency>
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>${version.mybatis.mapper}</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>${version.pagehelper}</version>
        </dependency>
        
        <!-- mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${version.mybatis.spring}</version>
        </dependency>
        
        <!-- spring事务 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        
        <!-- spring jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
    
## 通过MyBatis-Spring-Boot-Starter集成

当以这种方式集成时，如果需要编写xml sql文件，则需要配置mybatis-config.xml文件

### 添加依赖

        <!-- 在Spring Boot中集成MyBatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
   
### 默认配置
默认情况下，插件MyBatis-Spring-Boot-Starter将进行如下配置：

- 自动检查Spring Boot的数据源配置并构建DataSource对象
- 通过SqlSessionFactoryBean使用数据源构建并注册SqlSessionFactory对象
- 从SqlSessionFactory中创建并注册一个SqlSessionTemplate实例，其实就是构建一个SqlSession对象
- 自动扫描接口映射器，并将这些映射器与SqlSessionTemplate实例进行关联，同时将它们注册到Spring容器中

### 定义使用接口映射器
插件MyBatis-Spring-Boot-Starter会自动搜索使用了注解@Mapper的接口映射器并将其注册到Spring容器中，因此在这里不能使用@Repository注解标记MyBatis的映射器接口，这与原生方式集成MyBatis有所不同。

- 定义

        @Mapper
        public interface AccMapper {
            @Select("select * from account where id = #{id}")
            Account getAccount(@Param("id") long id);
        }

- 使用
        
        @RestController
        @RequestMapping("/acc")
        public class AccController {
            // 直接通过自动注入的方式使用接口映射器
            @Autowired
            AccMapper accMapper;
        
            @GetMapping("/{id}")
            @ResponseBody
            public Object acc(@PathVariable("id") long id) {
                return accMapper.getAccount(id);
            }
        }

### spring-boot高级定制
- 定制MyBatis运行时参数

        mybatis:
            check-config-location: true                             # 是否检测MyBatis运行参数配置文件
            config-location: classpath:/mybatis-config.xml          # 指定MyBatis运行参数配置文件位置
            mapper-locations: classpath:/mapper/**/*.xml            # 注册XML映射器
            type-aliases-package: test.springboot.model             # 配置Java类型包名
            type-handlers-package: test.springboot.handlers         # 配置类型处理器包名
            executor-type: SIMPLE                                   # 指定执行器类型
            configuration:
                default-fetch-size: 20
                default-statement-timeout: 30
