/*
 * Software Design and Architect
 * 
 * - Software Design looks at the lower-level aspects of a system.
 * - Software architecture looks at the higher-level aspects of a system
 * - The role of a systems architect is the interface between product, customer 
 *   and engineering team.
 *   Importante priciple, keep it simple.
 *  System Architect needs to be aware of technology out there.

 *  Object-oriented thinking involves examining the problems or concepts at hand, 
    breaking them down into component parts, and thinking of those as objects.
 *  object-oriented thinking involves representing key concepts through objects in 
    your software.
 *  Objects have details and behaviors. By using objects the code stays organized,
    flexible and resusable.
    When software is developed, it generally goes through a process. In simple terms, 
    a process takes a problem and creates a solution that involves software. 
    A process is generally iterative. These interactions consist of taking a set of requirements
    based on the problem and use them to create "conceptual design" mock-ups and
    "technical design" diagrams that can be used to create a working software implementation.
    Requirements are conditions or capabilities that must be implemented in a product, 
    based on client or user request. Trade-off mjst be established. Like speed for example.
    
    Conceptual Design: 
    Components 
    Connections
    Responsibilities.
    
    Technical Design is based on Cenceptual Design and requirements. technical designs 
    begin by splitting components into smaller and smaller components that are specific enough 
    to be designed in detail. Technical diagrams are used and visualize how to address specific 
    issues for each component.

    Quality Attributes, there might be trade-offs such as performance, convenience and security
    that need to be balanced.
    Satisfying Quality. Qualities are achieved through satisfying functional and non- functional 
    requirements, which in turn are the basis for the design process.
    Functional requirements describe what the system or application is expected to do.
    Non-functional requirements specify how well the system or application does what it does. 
    Also includes reusability, flexibility, and maintainability.

    Compromise
    A good question to ask to help you determine what compromises can be made is: Is there a way 
    to cut back on a certain quality to balance another?

    Some common qualities to take into account in software design include: performance, 
    maintainability, security, and backwards compatibility. To develop the product, qualities must 
    be balanced with resources available such as cost, time, and manpower.

    Class Responsibility Colaborator Cards
    CRC cards are designed with three sections: the class name at the top of the card, the 
    responsibilities of the class on the left side of the card, and the collaborators on the right 
    side of the card. 

                            class Name

               Responsibilities     Collaboration
    User Story
    As a _______, I want to ________, so that ________

    As an "online shopper", I want to add an "item" to my "shopping cart", so that I can purchase it.
    Nouns represent objects.

    As an online shopper, I want to "add" an item to my shopping cart, so that I can "purchase" it.
    Verbs represent requirements that your objects might have.

    Connections. One online shopper is typically associated with one shopping cart. The shopping 
    cart should be capable of holding multiple items.

    Object Types:
    Entity Objects. they correspond to some real world entity.
    Boundary Objects. Objects that sit at the boundary between two systems.
    Control objects are objects which are responsible for coordination.

    Four Design Principles
    Abstraction. Behaviors and Attributes.
    It breaks a concept down into a simplified description of details and behavior in a context.
    An abstraction should follow the rule of least astonishment. This rule suggests that essential 
    attributes and behaviours should be captured with no surprises and no definitions that fall beyond 
    its scope.
    Context or a specific perspective is critical when forming an abstraction.
    The essential characteristics of an abstraction can be understood in two ways: through basic 
    attributes and through basic behaviours or responsibilities.
    Basic attributes are characteristics that do not disappear over time.
    
    Encapsulation. Bundle, Restrict, Expose
    This principle involves a concept that allows something to be contained in a capsule, some 
    of which you can access from the outside and some of which you cannot.
    Bundle attributes and behavior
    Expose data and function through interfaces.
    Restrict access to data and functions (Data Integrity).
    Changable implementation. The code may chnage but the interface won't.
    Class can be seen as a black box that can be accessed by its interface.
    Encapsulation achieves an abstraction barrier through black box thinking where the internal 
    workings of a class are not relevant to the outside world.

    Decomposition. Breaking down components
    It consists of taking a whole thing, and dividing it into different parts.
    can also indicate taking separate parts with different functionalities and combining them to create 
    a whole.
    The Nature of Parts
    A whole might have a fixed or dynamic number of a certain type of part.
    
    Generalization. Factor out conceptual commonalities. 
    It helps to reduce redundancy when solving problems.
    In coding, algorithmic behaviours are often modelled through methods. A method allows a programmer to 
    generalize a behaviour, so the behavior can be applied to different input data. This generality reduces 
    the need to have identical code throughout a program.
    object-oriented modelling achieves generalization by classes through inheritance.
    inheritance allows a superclass to form a generalization and for its subclasses to be more specialized.
    Both methods and inheritance exemplify the generalization design principle through the D.R.Y. or 
    “Don’t Repeat Yourself” rule.

    UML Class Diagram.
    Abstraction. Class name plus attributes and responsibilities.
    Encapsulation. - means private + public. Bundel, Restrict, Expose (Interface)
    Composition.
        Association. Some relationship, objects may interact, loose partership, exist independently.

            class Wine{
                public void getFood(Food food){...}
            }
        Aggreagtion. "has-a" relationship. Relationship from the whole to the parts is weak Although
                    the parts belong to the whole they can exist independently. 

            class AirLine{

                private ArrayList<CrewMember> crew;
                public Airline(){
                    crew = new ArrayList<CrewMemeber>();
                }
                public void add( CrewMember crewMember ){
                    crew.add(crewMember)
                }

            }
       Composition. Strong "has-a" relationship. Complete dependent classes. One can't exist 
        without the other
    
        class Human{
            private Brain brain;
            public Human(){
                brain = new Brain();
            }

        }
    Generalization. - 
    class abstract Animal{
        protected int numberOfLegs;
        protected int numberOfTails;
        protected String name;

        public Animal( ... ){
            ...
        }
        public void walk(){...}
        public void run(){...}
    }
    class Dog extends Animal{
        public Dog(...){
            super(...)
        }
    }

    Interface. Only declares method signature, describe behavior.

    public interface IAnimal{
        public void walk();
        public void eat();
    }
    class Dog implements IAnimal{
        public void walk(){...}
        public void eat(){...}
    }

 * 
    - Coupling and Cohesion
    Module - Programming unit.
    To evaluate design complexity we use Coupling and Cohesion
    
    Coupling - mesures the complexity of joining a module with other modules.
    Degree - Number of connections between the module and other modules
    Ease. How obvious are the connections between the moduele and the others.
    Flexibility. How interchangable the other modules are for this one. 

    Cohesion. Clarity of the responsibility of the module. High cohesion one task
        low cohesion it doesn manu tasks.

    Concern. Anything that matters in providing a solution to a problem.
    Separation of concerns. Encapsulate attributes and responsibilities within a class
            that are related to the same concern.

    Information hiding. Minimum information given to others to use the module and hide 
                        everything else. Private, Public, Protected(class, subclass, package)
                        Default,

    Conceptual Integrity. It is creating consistent software among people. Ways to achieve it:
                 Communication
                 Code review
                 Good software architecture.
                 Unifeid concepts

    Inheritance issue. It could be overused. You can ask yourself:
        Am I using inheritance to simply share attributes or behaviors without further adding
        anythung special in my class.

    Liskov Principle. A subclass can replace a superclass if and only if the subclass doesn't change
        the functionality of the superclass. The following examole breaks it.

        class Animal{
            private int numberOfLegs;
            Animal(int legs){this.numberOfLegs=legs;}

            public void walk(){...}
            public void run(){...}

        }
        class Whale extends Animal{
            private Swim swim;

            public void walk(){
                this.swim
        }

                        
    Sequence Diagrams. Describes how objects in your system interact to complete a specific task.

    State Diagram. It describes how your system behaves and responds. It can also describes a single
        object and illustrates how that object behaves in response to a series of events.

       State - it is the way an object exists at a particular point in time.

       State Name
       State Variables. Variables related to the state of the object.
       Activities.
            Entry. action where the state just entered from another state.
            do. Actions that occure while the object is in a certain state.
            exit. actions when move to another state.

    Model Checking. 
    -Modelling phase.
    -Running phase.
    -Analysis phase.

    Design Patterns
    It is a practical proven solution to a recurring problem.

    Creational Patterns:
        How you handle the creation of new objects.

    Singleton private constructor and global instance.
    
    Factory.
            Knife orderKnife( String knifeType ){
            if( knifeType.equals("steak")
                knife = new SteakKnife();
            if( knifeType.equals("chef")
                knife = new ChefKnife();
            .....
        }
        return knife;

    Factory method
        public abstract class KnifeStore{
            Knife knife;
            public Knife orderKnife(String knifeType){
                knife = createKnife(knifeType);
                return knife;
            }
            abstract Knife createKnife(String knifeType);
    }

    Structural Pattern: 
        Describe how objects are connected to each other.

        Facade: Wrapper class that encapsulates a subsystem in order to hide the subsystem's complexity.

        Adapter: It will facilitate communication between two existing systems by providing a compatible
                interface.

        Proxy. Place holder to represent someone else. Proxy is a light representation of the original object.
            When to use it:
                To act as a virtual proxy. 
                To act as a protection proxy.
                To act as a remote proxy.

        Composite: To compose nested structures of objects. How to build a tree like structure of objects
            and treat them uniformly. Enforces polymorphism through interface or abstract class. Uses 
            recursive composition that allow objects to be composed of other objects that are of common type.

        Decorator Pattern. Dynamically attach new behavior to an object using aggregation to combine behavior
            at run time.


    Behavioral Patterns:
        How objects distribute work. How they work together for a common goal.

    Template: Defines an algorithm steps generally deferring the implementation to subclasses.

    Chain of Command: Series of handler objects that are linked together.

    State pattern: It behaves depending on its current state.

    Observer: When there is a one-to-many object relationship and one needs to update another.

    Liskov Principle.
    Open/Close principle
    Composing Object Principle: With inheritance you end up with a dependency on super classes.
        with composition your system ends up loosley couple. 
        The disadvantage is that you may end up with many classes to cover all behaviors.

    Flexible: Easy to extend.
    Reusable: You don't have to reimplement something that has already been done.
    
    Dependency Inversion: High level modules should depend on high level generalizations and no on
    low level details.

    Interface segregation: A class should not force to depend on methods it doesn't use.

    Principle of least knowledge. Law of Demetre. Classes should know and interact with a few classes as possible. 
    First rule a mthod M in an object be can called any mthod within O itself
    Second rule a method M can call the methos of any parameter P
    Third rule: A method M can called method N of I if I is instatiated in M;
    Fourth Rule: Any method M in object O can call any method of any object that is direct component of O
    A class shouldn't haave acces to the whole system, it should interact what it considers its immediate friends.

    class Friend{
        public void N(){}
    }

    class O{
        public void M(Friend P){
        P.N();
        Friend I = new Friend();
        I.N();
        }
    }
      
    **** Antipattens and Code Smells ****

    Refactoring could happen when adding new features.

    Smells:
        No comments. Too many coments are signs of bad code as well. Or saying if you 
        change here don't forget to change there.
        
        Duplicated code

        Long methods.

        Large classes.

        Data classes. They contain just data but not real functionality. Those would have setters and getters.

        Data clumps. Group of data appearing together in the instance variables of a class o parameters to a methof/
        
            public void doSoemthing( int, x, int y, int z ){}
            public void doSomething(Point3D point) // However be careful not to create just a Data class.
        
        Long parameter list. One solution would be parameters objects.

        Divergent Change. When you have to change a class in many different ways for many reasons.
                    One common reason for this is lack of separation of concerns.
        
        Shotgun surgery. When you want to implement a requirement and that requires to make changes all over the place.
            You can solve this problem by moving methods around, maybe they can be consolidated in one class.
        
        Feature Envy. When two classes or methos talk to each other often probably they shuld be together. If you see a
                methid talking to another class to do its work then that method should be on the other class.

        Inappropiate intimacy. Whe two class depend on each other through two way communication. One way to avoid this
            us factoring out the methids that talk often into another class.

        Message Chain.
            a.getB().getC().doSomething();

        Primitive Obsession. When you rely on primitive too much on a higher level. They can be used more at a lower level.
            For example a psotal code as a String or create a class called PostalCode.

        Switch Statements::
        class Animal{
            public void say(int type){
                switch(type){
                    case DOG:
                        ...
                    break;
                case CAT:
                        ...
                    break;
                }
        }

        public interface Animal{
            public void say();
        }
        class Dog implements Animal{
            public void say(){...}
        }

        class Cat implements Animal{
            public void say(){...}
        }

        Speculative Generality. Write code that is not needed ut it might in the future. Over egineering.
            with Agile dev just in time design.

        Refuse Bequest. When a class inherits something that is not needed.

        Refactory. Improving the design of Exisiting Code. Martin Fowler.


        - *** Software Architecture *** -

        It defines what elements are included in the system, what functions each elemnt has and how each
        element relates to one another. 

        - Kruchten's 4 + 1 View Model

        1.- Logical View (UML classes/State diagram) focuses on functionality of the system and the objects that are with it to achieve requirements.
        Class and stae diagrams.
        
        2.- Process View (UML Activity Diagram). Achieving non-functional requirements. Performance and availability. Shows execution order.
        UML Sequence diagram or Activity Diagram

        3.- Develomment View (UMl Component/Package Diagram). Structure of the software. Programming languages, tools. Project management is important here.

        4.- Physical View (UML Deployment View). How elemnts int the Logical, Process and Developmen view are mapped to nodes or harware.
            UML deployment diagram.
            
        4+1.- Scenario. Align with use cases.There is a script that shows the interaction with objects and processes


        UML Component Diagram
        UML Package Diagram
        UML Deployment Diagram. Artifact physical result of development process.
            Specifications Level Diagram. Overview of the artifacts and deployment targets
            Instance Level Diagram. It can be more specific with artifacts and deployment targets.
        Node: Deployment targets that contain artifacts for execution.

        UML Activity Diagram. Captures the dynamic behavior of the system. It escribes activties.
        Partitions devide activities up to different categories.


        **** Architecture Styles ****

        - Abstract Data Types and Object Oriented.
          The programming paradigm of the language to implement the system will influence the architecture style of that
          system. Each programming paradigm has its own set of constructs and their use will shape the system you create.
          Object Oriented Languages such as Java/C++ Abstraction, Encapsulation, Decompisition and Generalization, Design
          Patterns Structurals (Relationships between objects), Creational (creating new Objects) and Behaviorals (how they 
          work individually or as a group to achive a goal.
          Abstrcat Data Types can be represented by a class with attributes and behaviors, encapulsation restricts access
          to the data. 
          You start by identifying the objects with its attributes and bahaviors. For instance a food dispenser for pets.
          We have two objects food dispenser and pets collar. But not always is easy to identofy the classes and OOD may not
          be the right choice.
        - Main program and Subroutine architectural style. Like C is based on functions. Call tree. Data store in variables.
          promotes modularity and function reuse 

                                                          Main Program

                                                Subroutine 1        Subroutine 2

                                             Subr 3       Subr 4    Subr 5     Subr 6

        -Databases. How do we ensure that the data won't be lost? File? Not practical. Database. Features:
            Data Integrity.
            Data Persitance.
            Data backup.
            Data restoration.
            
         Data Centric Software Architecture. Two types of components
            Central Data. Used to store and serve data to all components connected to it.
            Data Accessors. Compnents that connect to the Central Data component.

        Management and optimization of trasaction can be automated with DBMS.

        Drawbacks. The system relies on the Central Data Component.
                    Your system depends onwhat is in the database.
                    Difficult to change the existing data schema. If it changes the Data Accessors must change.

        - Layered Systems.
          A layer is a collaction of components that work together for a common purpose.
          The components in a leyer only ineteracts with components on the same layer or adjacent layers.
          Layers allow separation of concerns. Many layers canbe separated in:
            Presentation
            Logic 
            Data

                                            Utilities and Applications (OS)
                                            Libraries and Tools
                                            Kernel
          One advantage of the layered architecture is that one layer can used the other layer without knowing
          exactly how that layer works. Also there can be run at different levels of authorization or privilages.
          User space is not allow to communicate with the hardware directly or allocate resources.
          It is loosely coupled and follow the least knowledge principle.

          Disadvantages. The events follow button to top. It could be relaxed.
          Advantages. Intuitive and powerful. Separation of conerns, layering loosly coupled. Adaptive.

        - Client Server n-tier. Are like layered systems but tiers often refer to components that are in different
          physical machines. Client-Server Request-Response. 2 Tier architecture. If we introduce a database then
          we need to introduce another layer to isolate client app from db.

          Presentation Tier - Client Application Software.
          Application Tier - Application Server Process.
          Data Tier - Database

          Disadvantages. Demands extra resources to manage client/server relationships. Adding more layers means extra
          complexity to the system and more difficult to mantain. A server acts as single point of failure.
          Advantages. Scalable, centralizing, computer power, separation of concerns.

        - Interpreters. There are systems where the user can interact using scripts, macros or rules. Like Excel.

        - State Transition System.
            State. The information that a system remembers defines its state. For example, a queue system can be in a number of 
            different "states": an empty state, a queued state, or a full state.
            Transition. A transition is used to describe the change of a system from one state to another. A single system 
            state can have multiple transitions; majority of systems today will have multiple transitions branching from one
            system state. This makes behaviors non-deterministic, since we cannot predict what the next state of the system
            will be.
            Behavior. The behavior of a system describes what the system will do when exposed to a condition, which can vary from timed system events to user input.
            State transition systems can be labelled or unlabelled.

            An unlabelled state transition system is defined as a set of state, S, and transition, →, pairs that are used 
            to describe the system's behavior. If p and q are two different states in S, then the transition between them 
            is depicted by (p → q)

            A labelled state transition system simple includes a set of labels, ~, with addition to the state-transition 
            pair. Given the same states p and q in S, then the transition between the two states is shown as (p ⭇ q).
        
        - Dataflow system. Pipes and Filters.
            A series of transformation on a set of data. Filters Perform transformation on data they receive. Pipes serve
            as connector for data being transformed. 
            Advantage. They are loosely couple, each filter can be used as a black box. Reusability.
            Disadvantages. May reduce performance due to filters overhead. Filters may be ovaerloaded with massive amount
            of data. It cannot be used for interactive applications.
            
        - Inplicit Invocation Systems
            Event Based. Events driven program paradigm. The fundamental elemnts of the system are events. Signals User Inputs
            or data from other systems. Events are indicators of change or triggers to functions. Functions take the form
            of Events Generators, Event Consumers. Event consumers are called implicitly from event generators
            Event based functions experience implicit invocation. Functions are not in direct communication with each other.
            It is mediated by a Event Bus. It is like the observer design pattern. Have a main loop listening for events.
            The system can be xtended by adding new events and event handlers. We can run into race conditio on data. One
            way to avoid this is with semaphores which toggles between two values available or unavailable.

        - Publish Subscriber. Two main differences with Event Based is that the components are either publisher or subscribers.
            The relationship between publisher and subscriber vary in the form or closeness.
            In a simple implementation of the publish-subscribe architectural style, to receive messages, a subscriber 
            registers their interest to publishers through a call-back. Publishers maintain a list of subscribers and 
            communicate to them through procedure calls to the registered call-backs. In this way, subscribers are informed
            of messages implicitly.

        - Process Control. Feedback loop.
            Sensor
            Controller
            Actuator
            Process
            Checks the difference between set point and actual reading and acts accordingly. Open loop ssytems don't

        *** Architectural Trade-Off ***
            Software architect is design to address a set of requirements.
            Quality Attributes:
            - Maintainability. The ease at which your system is capable of undergoing changes
            - Reusability. The extent in which functions or partis used in another.
            - Flexibility. How ell a system can adapt to requirements changes.
            - Modifiability. The ability of a system to cope with changes, incorporate new, or remove existing functionality.
            - Testability. How easy is to demostrate errors through executables test.
            - Conceptual Integrity. The consistency across the entire system such as naming convention.
            User Perspective
            - Availability. The amount of time the system is operational over a set period of time.
            - Interoperability. The ability of your system to understand communication and share data with external systems.
            - Security. The system's ability to protect snsitive data from aunauthorized used.
            - Performance. The system's throughput and latency in response to user commands and events.
            - Usability. The ease at which the system'sfunctions can be learbed and used by the user.
            
            For your system architecture you should:
            -Involve all groups of stakeholders in the design so all concerns are headr and address.
            -Adopting good documentation to ensure the details of your system don't get lost
            -Setting rules for design and implementation so you can achieve high standards for quality attributes.
            -Developers perspective
            -User perspective.

            Analyzing and Evaluating an Architecture.
            
            Stimulus        Artifact        Response Measure

                           Environment

            Stimulus Anything taht creates a stimulus, internal or exeternal. A condition that cause the system to respon
            Environment Mode of the system when receiving the stimulus.
            Artifact. Part of the system affected by the stimulus.
            Response. Result of the receiving stimulus.
            You should focused on situation outside of the normal execution of normal path such as Incorect
            input, heavy load or security breach.

            ATAM Architecture TradeOff Anaylsis Method. The evaluator doesn't need to know the architecture of the system.
            This involves three groups:

            -Evaulation Team. Designers, Peers, Outsiders.
            -Project Decision makers.Project managers, client, product owner, sowftware architect, technical lead.
            -Architect Stakeholders. Ens users, developer, support staff.
            
            ATAM Process:
            1.- Present the ATAM. Evaluation, expectations procedures, output and address of concerns.
            2.- Present the business drivers business problem and goal, features and requirements, constraints and ascopes.
            3.- Present the architecture. Constarints of the project that affects cost, difficulty of the problem and quality e
                expectations.
            4.- Identify the architectural approaches. Architectual patterns that have been used.
            5.- Create a quality attribute tree.
            6.- Analyze architectural approaches. Fromd the tree analyze each ASR (Architectural Significant Requirement) and determine how to address it.
            7.- Brain Storm and prioritize scenarios. Each group creates a list of quality attributes that are important for them.
            8.- Reanalyze architectural approaches. Create a utility tree with 5 to 10 higher priorities.
            9.- Present the results.

            Releatioship to Organizational Structure.
            Conway's Law. A system will tend to take a form that is congruous to the organization that produced it.
            If you are working on a team depending on the organization it coild end up loosely or tightly coupled.
            For instance on a web project (3 tier) one team could work on the presentation, other business logic and the 
            third on the data layer. If there are five teams maybe it is better to create one more to make sure the pieces
            fit together.

            Product Lines and Product Families
            Code reuse is important. Like iOS code is shared among a family of devices. Lower cost. Beter user experience
            less learning curve, time to market.
            Software Product Lines in Action. Van der Linden, Schmid and Rommes.
            - Separate the features that saty the same across the products.
                Commonalities. Same across products
                Variations. Variations among products.
                Product specific. Features specific to one product.

                Domain Engineering. Development of commonalities and Variations. Infrastructure.
                Application Engineering. Developing a product using commonalities.
                Capacity of variation. There are three main techniques.
                -Adaptation. For instance to setting in a configuration file. Add new methods or override
                -Replacement. Replace a default component.
                -Extension. AddOns, plugins

        *** Service Oriented Architecture ***

        Service unlike a component is somewhere else. There are two part service request and service response. Build a system
        based on other services. It has tradeoffs that are difficult to control like:
        -Response time. How quickly it can be provided.
        -Supportability.Will it be available in the future?
        -Availability. Is the service always running.

        Sevice Principles.
        Modular and loosely coupled, reusable and combineable.
        Composable. Usable combination with other services.
        Platform and Language independent. Ot can achieve by using standard protocols.
        Selfdescribing. It should describe how to interact with it. Describes its interface. 
        WSDL Web Service Description Language.
        Self Advertising. UDDI Universal Description, Discovery and Integration.
        
        Web Systems Evolution.
        Internet 1969 ARPANET usd to send small amount of data between two comupters.
        1990 Tim Berners Lee propose a way to share documents with mark up language. Static web page.
        1993 Dynamic Web Page.
        Web service communicate through standards HTTP, XML, JSON.

        Web Systems Architecture.
        Layer a set of components that work together for a common purpose. We use layers to identify hiararchies in the system.
        and restrict how layers interact with each other. Component in the layer interact with component in the sam layer or
        adjacent layer through interfaces. Lower layer usually give service to the upper layer. Adding more layers performance
        suffers as communication increases among layers.

        Presentation Tier
            Web Browser
            Web Server
        Application Tier
            Application Server
        Data Tier
            Database

        In static web content the Data tier is a static web page and there is no application layer.
        
        HTML/XML/JSON
        JASON key-value pair. In JASON an array of objects are enclosed in square brackets [ ]
        
        HTTP
        Hyper links. URI addresses to identify resources. URL subset of URI to locate resources. Both are used to locate a
        resource but URL tells the protocol how to locate and access the resource, provides the protocol and address.
        HTTP is built on top of TCP. HTTP Request is formed:

        Request Line . Request method, request uri, protocol. It may add parameter to the request separated by a ? mark
            Headers. 
                Mandatory. 
                    Host Header Domain Name or address of the host. 
                    Accept Header. What kind of content will accept as a response. 
                Optional. If body is present
                    Content lenght header
                    Content type header.
            A blank line
            Message Body

        Response:
            Status Line. Protocol version and status code. 200 OK
            Headers. If body is included, then Content Lenght and Content type is included.
            Blank Line
            Message Body
        
        HTTP Encoding. Special characters need to be encoded using % and the hexadecimal number. Space could be either %20 or +.
        paremeter with = and separation with &

        HTTP Request methods: GET, POST and PUT, 

        GET /awardwinningcats/?breed=ragdoll HTTP/1.1

        Host: catfanciersassociation.com

        User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:10.0) Gecko/20100101 Firefox/10.0

        Accept: "* / *"

        HTTP is stateless the request is not preserved. It can use a cookie.

        JavaScript. It can be included in HTML <script> </script>.
        When you load a web page in your browser your HTML becomes a DOM Object, so it can be modified by JavaScript.
        You can reuse JavaScript code like Disqus.

        RPC Remote Procedure Call
        
        Middleware is a type of architecture used to facilitate  the communicationof serviceses
        available and the requests for these services between two applications that are
        operating on ebvironmentally diffrent systems.
        Client and servers become more specialized and come to a situationin whihc cannot communicate. To solve this
        we use a middleware that provide a common interface for all the components (Mediator Desogn Pattern).
        There are many middleware one of them is RPC. This allows the client to invoke procedure on the server.
        1980's Birell and Nielson RPC were created. Three components:
        Client
        Server
        Interface Definition Language.
        1.- Client Invoke the procedure.
        2.- Client stub marshalls the parameters.
        3.- Message is sent to the server.
        4.- The server stub recives and unmarshall the message
        5.- The server execute the procedure and send it to the server stub
        6.- The results are sent back tot he client stub.
        7.- The client component recives and uses the results.

        Object Brokers
        They combine the distributed computed aspect of a RPC with object design principles.
        1970's Introduction of OOP to the world. 
        1990's Address issues of how middleware handle OOP. The most common is CORBA Common Object Request Object Architecture
        offers the fondation for .NET and Java 2 Enterprise System.
        CORBA cosist of three components:
        Object Request Broker. sits between client and CORBA service, it also marshall and unmarshall all method call it receives.
        CORBA services
        CORBA Facilities. Services at application level.

        Web Services First Generation
        A service is some functionality that is exposed for use by others with Web technolgies. World Wide Web Consortium.
        define WebService as Software System designed to support interoprable machine to machine interaction over a network.
        TCP, HTTP and XML are involved. In order for a service requester use a service it must invoke it. Invokation is done
        with SOAP which is based on XML and therefore system written in different languages can communicate. WSDL (Wisdel)
        UDDI. SOAP, WSDL and UDDI (Universal Description, Discovery and Integration) are the foundation standards of Web Services.
        On top of that are WS-Security, WS Coordination. WS-BPEL (Business Process Execution Language allows developer to create
        services using other services, they are called composite services. Basic Service doesn't rely on other services.
        This case middleware is replaced by web standards.

        SOAP
        Requester and responder communicate through XML.
        <?xml version="1.0">
        <soap:Envelope ... >

        <soap:Header>
        ....
        </soap:Header>

        <soap:Body>
        ...
        </soap:Body>

        </soap:Envelope>

        There are two type of SOAP message:
        Document
        RPC style

        There are four messaging patterns possible:
        Request-Response
        Solicite Response
        One Way
        Notification.

        WDSL
        Created by Microsoft, IBM and Ariba
        Binding is the process of creating the necessary code to intefcae with the provider.

        UDDI
        Created by Microsoft, Aiba and IBM in 2000. It brings service requeters and service providers together.
        Service Provider publish themself to a UDDI registry. Service requesters can search the registry. The UDDi registry
        assigns a unique URI to the service contained in:
        White Pages. Basic Information
        Yellow Pages. More detail Info
        Green Pages. Technical Info.
        
        BPEL (Business Process Execution Language)
        Some service can be composes of other services. Service composition. Combine service at a higher level.

        REST Services
        REpresentational State Transfer. Client Server architecture in the Request Response design.
        Request are sent as representation of resources. A resource is a piece of information that is self contained such
        as Images, Documents, Objects Representations. REST has five constraints:
        1.- Client- Server architecture
        2.- Layer System
        3.- Interaction must be stateless
        4.- Client can cache, are cacheable
        5.- Uniform Interface. GET, PUT, POST and DELETE. There should be a URI, Responses have specific headers. ANd
            the response can be XML, JSON or Simple text.

        Designing REST Services
        Good practice for a REST API:
        -Use only Nouns for your URI
        -GET method should not alter the state of your resources. Leave it to POST, PU, DELETE.
        -Use plural Nouns for you URI.
        -Use sub-resources for relationships between resources. You can show the connection in the URI
            GET /students/3
            GET /students/3/courses/2
        -Use HTTP headers to specify the inout/output format.
        -Provide users with filtering and paging for collections.

            GET /courses?department=computing+science
            use offsets and limits.
            GET /courses?offset=10&limit=5
        
        -Version your API.
        
            http://api.yourservice.com/v2/students/34/courses

        -Provide proper HTTP status code.

        Example.
        Which services do I need to provide

        Resource        GET     POST    PUT     DELETE
        /students
        /students/SID
        /courses
        /courses/CID
        /students/SID/courses
            
        Create a class Student
        Build HTTP request using some of the existing Java libraries such as:
            Restlet
            Spring
            Jersey
            RESTEasy
        You can also use curl -i to test your service.

        Microservices
        Monolitic application takes a long time to develope, performance issues, hard to mainatin adn scale.
        Microservice is a process that is responsible to performa a single independent task.
        Often a microservice doesn't obey a full layer architetural style. Each microservice own its data.
        Each microservice can use a different architecture resources,frameworks and languages that are independent to other
        services.
        It allows developer to use different tools and it allows to rty new technologies without affecting the oveall system.
        Easier to maintain as are loosely coupled, scalable, resiliant to failure. They can be developed independently by teams
        and quicker. Each tema doesn't need to know the whole system to do their job. They can be local or remote. Code reuse,
        
        Drawbacks. System with microservices are distributed systems, testing is more complex, difficult to reproduce bugs,
        central service for coordination. Messaging among microservice might have an overhead. Stateless
        


















        

*/
package sda;

/**
 *
 * @author raga
 */
public class SDA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
