/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raga.javaspringreview;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * - To create a Spring project just choose Maven->Web App and include the following dependencies:
 * 
 *  org.springframework
 *  spring-context
 *  cglib
 *  cglib
 *  Create interfaces, classes and add the main().
 *  
 * - To get Application Context 
 *  public class ApplicationContextProvider implements ApplicationContextAware
 * 
 * - To use xml bean configuration
 *  String confFile = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(confFile);
        SpringFirstTest sft = (SpringFirstTest) context.getBean("springTest");
 *  < bean id="springTest" class="com.java2novice.beans.SpringFirstTest" / >
   
 * - To include other beans xml based configuration 
 *  < import resource="bean-config.xml" / >
 * 
 * - Using Java base configuration
 *  @Configuration
    public class JdbcConfig {
 
        @Bean(name="jdbcService")
        public MyJdbcService getJdbcService(){
        return new MyJdbcService();
        }
    }

*   @Configuration
    public class businessConfig {
 
        @Bean(name="bussService")
        public MyBusinessService getBusinessService(){
            return new MyBusinessService();
        }
    }
    
    @Configuration
    @Import({JdbcConfig.class, businessConfig.class})
    public class MyAppConfig {
 
    }
*
* - Dependency Injection.
*   The concept says that you do not create your objects but describe how they should be created. 
*   You don't directly connect your components and services together in code but describe which services are 
*   needed by which components in a configuration file. A container is then responsible for hooking it all up.
*   Spring supports 2 types of dependency injection
*   a) Constructor based dependency injection
*   b) Setter based dependency injection . In this method we trust the container to create the bean then call setter.
*       but what about if you forget to specify any bean to inject in the configuration, the injection will not be done
*       and they won't function properly. Using constructor based dependency imposes dependency injection done properly.
* 
* - Spring bean scopes. Use @Scope("protoype")
*   1) singleton: Returns a single bean instance per Spring IoC container.
    2) prototype: Returns a new bean instance each time when requested.
    3) request: Returns a single instance for every HTTP request call.
    4) session: Returns a single instance for every HTTP session.
    5) global session: global session scope is equal as session scope on portlet-based web applications.
    If no bean scope is specified in bean configuration file, then it will be by default 'singleton'.
* - init and destroy methods they can be used to init the bean after creation and clean up before it gets destroyed
*   @PostConstruct, @PreDestroy
* 
* - BeanPostProcessor gives you a way to do some operations before creating the spring bean and immediately after 
*   creating the spring bean. The following interface needs to be implemented and has two methods
*   public class MyBeanPostProcessor implements BeanPostProcessor
*       postProcessBeforeInitialization()
*       postProcessAfterInitialization()
* 
* - Reading property file
*   @PropertySource("db.properties")
*   @Autowired
    Environment env;
    env.getProperty("db.host.url")
*
* - How to inject Date into Spring bean
* - How to inject Date with CustomDateEditor
* - Spring bean inheritance configuration
* - @Required
*   Spring provides @Required annotation to check spring dependencies. If the required dependencies are not set, 
*   then it throws BeanInitializationException.
* - How to inject List into spring bean
* - How to inject Set into spring bean
* - How to inject Map into spring bean
* - @ComponentScan
*   @Component: It is a basic auto component scan annotation, it indicates annotated class is a auto scan component.
    @Repository: You need to use this annotation with in the persistence layer, which acts like database repository.
    @Service: It indicates annotated class is a Service component in the business layer.
    @Controller: Annotated class indicates that it is a controller components, and mainly used at presentation layer.
* 
* - How to filter components in auto scanning
* - Spring expression language basic. 
*   Spring 3 introduced the Spring Expression Language (SpEL), a powerful expression language, used to wire values 
*   into bean’s properties. You can get any spring bean reference or its property using dot operator. 
* 
*    @Component("beanOrder")
    public class Order {
 
        @Value("Java Book")
        private String item;
        public String getDescription(String prefix){
            return prefix+": "+item+" | "+price+" | "+address;
        }
        ....
    }
       
    @Component("paymentGateway")
    public class PaymentGateway {
 
        @Value("#{beanOrder.item}")
        private String itemName;
     
        @Value("#{beanOrder}")
        private Order order;
        @Value("#{beanOrder.getDescription('LOG ENTRY')}")
        private String orderDesc;
        
        @Value("#{myNumberBean.no > 300}")
        private boolean isGreaterThan;
        
        @Value("#{beanOrder.taxPaid ? true : false}")
        private boolean canProcessOrder;
        
        // Regular expressions
        @Value("#{empFormBean.age matches '[0-9]{2}' and empFormBean.age < 60 } " )
        private boolean validAge;
        ....
        <, <=, ==, !=, <, >=, eq, ne, gt, and, or, !, +, -, * /, ^, %
    
* - How to use collections with spring expression language
*   @Component("myCompanyBean")
    public class MyCompany {
 
    private List<Employee> empList;
    private Map<String, Integer> empIdMap;
     
    @Value("#{myCompanyBean.empList[0]}")
    private Employee firstEmployee;
     
    @Value("#{myCompanyBean.empIdMap['Nataraja Gootooru']}")
    private int starEmpId;
     
    public MyCompany(){
        //populate list
        empList = new ArrayList<Employee>();
        empList.add(new Employee(1016, "Nataraja Gootooru"));
        empList.add(new Employee(1015, "Nagesh Yenamala"));
        empList.add(new Employee(1017, "Nikesh Penumalli"));
        //populate map
        empIdMap = new HashMap<String, Integer>();
        empIdMap.put("Nataraja Gootooru", 1016);
        empIdMap.put("Nagesh Yenamala", 1015);
        empIdMap.put("Nikesh Penumalli", 1017);
    } 

* - Autowire
*   no, by type, by name, by type, constructor, auto detect xml based.
*   Spring also provides annotation based auto-wiring by providing @Autowired annotation. you can use @Autowired 
*   annotation to auto wire spring bean on setter method, instance variable, and constructor. If you use @Autowired 
*   annotation, spring container auto-wires the bean by matching data type.
*   //Instance variable
*   public class PaymentGateway {
 
    @Autowired
    private Order order;
    
    //By setter
    public class PaymentGateway {
 
    private Order order;
     
    public Order getOrder() {
        return order;
    }
 
    @Autowired
    public void setOrder(Order order) {
        this.order = order;
    }
     
    //By constructor
    public class PaymentGateway {
 
    private Order order;
     
    @Autowired
    public PaymentGateway(Order ord){
        this.order = ord;
    }
    
    NOTE: To enable annotation, you must include the following in xml file
    < context:annotation-config / >
    
    Dependency Check: When you use @Autowired, it will make dependency check to make sure proper auto-wiring on 
    property. If spring fails to find matching bean for auto-wiring, it throws an exception. If you don't dependency 
    check, then use "required" attribute of @Autowired annotation, and set it false. Here is an example:
    public class PaymentGateway {
 
    private Order order;
     
    @Autowired(required=false)
    public PaymentGateway(Order ord){
        this.order = ord;
    }
*
* - @Qualifier
*   It is used when there are two beans with similar names, this would confuse the container. Use @Qualifier to tell
*   which one to use.
*       
* - Log4j
*   Add dependency
*   
    log4j
    log4j
    1.2.16
    jar
*   Create a log4j.properties file in src/main/resources then right click on project->properties->spring framework
*   and add the file
* 
* - @Schedule
*   public class InventoryPullerJob {
 
    @Scheduled(fixedDelay=5000)
    @Scheduled(fixedRate=5000)
    @Scheduled(fixedRate=60*60*1000, initialDelay=10*60*1000)
    @Scheduled(cron="* /2 * * * * MON-FRI")
    public void updateEmployeeInventory(){
        System.out.println("Started fixed delay job");     
         // add your scheduled job logic here       
        }
    }

* - email with Spring
* 
*   javax.mail
    mail
    1.4.7
*   @Service("emailService")
    public class EmailService {
 
    @Autowired
    private MailSender mailSender;
     
    public void sendEmail(String to, String from, String sub, String msgBody){
         
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(sub);
        message.setText(msgBody);
        mailSender.send(message);
    }
}
* 
* - spring JDBC
*       < dependency>
            < groupId>mysql< /groupId>
            < artifactId>mysql-connector-java< /artifactId>
            < version>5.1.31< /version>
        < /dependency>
 *  < dependency>
            < groupId>org.springframework</groupId>
            < artifactId>spring-jdbc</artifactId>
            < version>${spring.version}</version>
        < /dependency>
        
* - spring JDBC template
* - Spring JDBC  with JdbcDaoSupport
* 
*   public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao{

* - Spring JDBC with JdbcDaoSupport and BeanPropertyRowMapper
* - Spring JDBC batch updates using JdbcTemplate
* - AOP Aspect Oriented Programming.
*   In Spring, aspect-oriented programming (AOP) is a programming paradigm that aims to increase modularity by 
*   allowing the separation of cross-cutting concerns. AOP forms a basis for aspect-oriented software development.
*   a) Before advice @Before
*   b) After @After
*   c) After returning advice @AfterReturning
*   d) After throwing advice @AfterThrowing
*   e) Around advice @Around
*   f) Pointcuts Last few pages talked about spring advices (before advice, after return advice and around advice). 
*   The disadvantages of these are, these advices will intercept all available methods. What if we want to intercept 
*   only one method or two specific methods and we dont want to intercept rest all methods?
    Spring comes with a concept called Pointcuts, which allows you to intercept advices based on either method name 
    or regular expression.

*   @Aspect
    public class RunBeforeExecution {
 
    @Before("execution(* com.java2novice.bean.MyBusinessService.runMyBusinessLogic(..))")
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("Inside RunBeforeExecution.before() method...");
        System.out.println("inserted before : " + joinPoint.getSignature().getName());
    }
}
* 

* 
*/
public class SpringDemo {
 
    static final Logger logger = Logger.getLogger(SpringDemo.class);
    
    public static void main(String a[]){
        
        logger.info("getting application context file...");
        
        ApplicationContext context 
                        = new AnnotationConfigApplicationContext(MyAppConfig.class);
        
        logger.info("getting bean reference...");
        
        //MyColor color = (MyColor) context.getBean("myColorRedBean");
        //color.printColor();
        MyColorManager colMan = context.getBean(MyColorManager.class);
        colMan.showColor();
        colMan.showMessage();
        
        logger.info("done...");
        
        MyDbConfig dbConfig = (MyDbConfig) context.getBean("dbConfig");
        System.out.println(dbConfig.toString());
               
    }
}