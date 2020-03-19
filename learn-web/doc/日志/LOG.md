# Slf4j与log4j及log4j2的关系及使用方法

## slf4j

slf4j仅仅是一个为Java程序提供日志输出的统一接口，并不是一个具体的日志实现方案。
就比如JDBC一样，只是一种规则而已。
所以单独的slf4j是不能工作的，必须搭配其他具体的日志实现方案。
比如log4j或者log4j2，要在系统中使用slf4j，我们需要引入的核心包为：slf4j-api-1.6.4.jar。

如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(XXX.class); 
可以在方法前用注解@Slf4j，然后直接使用log.info去打印日志。
如果注解@Slf4j注入后找不到变量log，那就给IDE安装lombok插件（idea中：1、File  → settings →  Plugins,  然后点击“Browse repositories”；2、输入 lombok 搜索插件， 点install安装，安装完重启idea）。

## log4j

如果在我们系统中单独使用log4j的话，我们只需要引入log4j的核心包就可以了，我这里用的是：log4j-1.2.17.jar，然后在系统中使用如下代码输出日志：

private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Log4jTest.class);

## log4j2

如果在我们系统中单独使用log4j2的话，我们只需要引入log4j2的核心包就可以了，我这里用的是：log4j-api-2.7.jar和log4j-core-2.7.jar，然后在系统中使用如下代码输出日志：

private static org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(Log4jTest.class);

## log4j与log4j2的区别

1.获取Logger的api不一样，log4j的api为org.apache.log4j.Logger，而log4j2的api为org.apache.logging.log4j.Logger
2.配置方式不一样，log4j2对properties的配置支持不是很好，它的格式一般为xml格式或者yaml格式，这种格式的可读性比较好，各种配置一目了然
3.Log4j2.0基于LMAX Disruptor的异步日志在多线程环境下性能会远远优于Log4j 1.x和logback（官方数据是10倍以上）。

## slf4j+log4j

如果我们在系统中需要使用slf4j和log4j来进行日志输出的话，我们需要引入下面的桥接jar包：
slf4j核心jar包：slf4j-api-1.6.4.jar
log4j核心jar包：log4j-1.2.17.jar
slf4j与log4j的桥接包：slf4j-log4j12-1.6.1.jar，这个包的作用就是使用slf4j的api，但是底层实现是基于log4j.
private static final Logger logger = LoggerFactory.getLogger(Slf4jTest2.class);

## slf4j+log4j2

如果我们在系统中需要使用slf4j和log4j2来进行日志输出的话，我们需要引入下面的jar包：
slf4j核心jar包：slf4j-api-1.6.4.jar
log4j2核心jar包：log4j-api-2.7.jar和log4j-core-2.7.jar
slf4j与log4j2的桥接包：log4j-slf4j-impl-2.7.jar，这个包的作用就是使用slf4j的api，但是底层实现是基于log4j2.
private static final Logger logger = LoggerFactory.getLogger(Slf4jTest2.class);

## slf4j+log4j不修改代码升级到log4j2

如果我们系统中刚开始用的是slf4j和log4j，然后出于性能考虑，要升级到slf4j和log4j2，
并且不需要改动任何代码的话(因为我们系统可能是一个大工程，然后基本上每个类都会有日志输出，改动代码可能牵一发而动全身)，
出于这个考虑，我们可以这样来进行修改(修改依赖)
    1. 删除项目中存在的Log4j1.x所必须的log4j和slf4j-log4j12等依赖，例如从我们上面做的去升级的话，需要删除log4j-1.2.17.jar和slf4j-log4j12-1.6.1.jar
    2. 添加log4j2和slf4j桥接包：log4j-slf4j-impl-2.7.jar替换log4j和slf4j桥接包：slf4j-log4j12-1.6.1.jar
    3. 如果我们在系统中使用了log4j的api去获取Logger的话：
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Log4jTest.class)；
       我们需要添加log4j-1.2-api-2.7.jar去替换log4j-1.2.17.jar
    4. 将log4j的properties文件修改为log4j2的xml文件，使用
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Self4jTest.class);
        
## spring-boot 默认使用日志框架Logback

