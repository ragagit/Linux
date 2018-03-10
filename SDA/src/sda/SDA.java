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
    Abstraction 
    It breaks a concept down into a simplified description of details and behavior in a context.
    An abstraction should follow the rule of least astonishment. This rule suggests that essential 
    attributes and behaviours should be captured with no surprises and no definitions that fall beyond 
    its scope.
    Context or a specific perspective is critical when forming an abstraction.
    The essential characteristics of an abstraction can be understood in two ways: through basic 
    attributes and through basic behaviours or responsibilities.
    Basic attributes are characteristics that do not disappear over time.
    
    Encapsulation
    This principle involves a concept that allows something to be contained in a capsule, some 
    of which you can access from the outside and some of which you cannot.
    Bundle attributes and behavior
    Expose data and function through interfaces.
    Restrict access to data and functions (Data Integrity).
    Changable implementation. The code may chnage but the interface won't.
    Class can be seen as a black box that can be accessed by its interface.
    Encapsulation achieves an abstraction barrier through black box thinking where the internal 
    workings of a class are not relevant to the outside world.

    Decomposition
    It consists of taking a whole thing, and dividing it into different parts.
    can also indicate taking separate parts with different functionalities and combining them to create 
    a whole.
    The Nature of Parts
    A whole might have a fixed or dynamic number of a certain type of part.
    
    Generalization 
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
    -Nalysis phase.

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
    First rule a mthod M in an object be can calle any mthod within O itself
    Second rule a method M can call the methos of any parameter P
    Third rule: A method M can called method N of I if I is instatiated in M;
    Fourth Rule: Any method M in object O can call any method of any object that is dierct component of O
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

        Prinitive Obsession. When you rely on primitive too much on a higher level. They can be used more at a lower level.
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

        Logical View focuses on functionality of the system and the objects that are with it to achieve requirements.
        Class and stae diagrams.
        
        Process View. Achieving non-functional requirements. Performance and availability. Shows execution order.
        UML Sequence diagram or Activity Diagram

        Develomment View. Structure of the software. Programming languages, tools. Project management is important here.

        Physical View. How elemnts int the Logical, Process and Developmen view are mapped to nodes or harware.
            UML deployment diagram.
            
        Scenario. Align with use cases.There is a script that shows the interaction with objects and processes

        UML Component Diagram
        UML Package Diagram
        UML Deployment Diagram. Artifact physical result of development process.
            Specifications Level Diagram. Overview of the artifacts and deployment targets
            Instance Level Diagram. It can be more specific with artifacts and deployment targets.
        Node: Deployment targets that contain artifacts for execution.

        UML Activity Diagram. Captures the dynamic behavior of the system. It escribes activties.
        Partitions devide activities up to different categories.


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
