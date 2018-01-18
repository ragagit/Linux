package solid;

import DepInv.DatabaseHandler;
import DepInv.MySQLDatabase;
import DepInv.OracleDatabase;
import IntSeg.BalancedTree;
import IntSeg.BinarySearchTree;
import IntSeg.Tree;
import command.Light;
import command.Switcher;
import command.TurnOffCommand;
import command.TurnOnCommand;
import commandII.Algorithm;
import iterator.Iterator;
import iterator.NameRepository;
import java.util.ArrayList;
import java.util.List;
import liskov.ElectricCar;
import liskov.ElectricVehicle;
import liskov.Vehicle;
import nullobject.CustomerFactory;
import observer.WeatherObserver;
import observer.WeatherStation;
import openclosed.Insertsort;
import openclosed.Mergesort;
import openclosed.Quicksort;
import openclosed.SortManager;
import openclosed.Sorter;
import strategy.Add;
import strategy.Manager;
import strategy.Multiply;
import template.Algorithm_;
import template.BubbleSort;
import visitor.Chair;
import visitor.ShoppingCart;
import visitor.ShoppingCartVisitor;
import visitor.ShoppingItem;
import visitor.Table;

/**
 *
 * 1. - Single Responsibility principle. A function can do only one thing if
 * there is more than one reason to change it breaks the principle Also if the
 * name has the word And like printandsave.
 *
 * 2.- Open-Close principle. Application should take care of the frequent
 * changes. The design of the code would need to be that the changes can be
 * minimal when there are requirements. This can be achieved by: a) strategy
 * pattern b) template pattern
 *
 * 3.- Liskov Substitution principle. During development there is inheritance of
 * classes It would be great if the new derived class could work without
 * replacing the functionality of the other classes. Otherwise the new classes
 * produce undesired results when they are used in the existing program modules.
 * Child classes should never break the class parent definition. Subtypes must
 * be substitutable for their base type. The Child class can be replaced by the
 * parent class without affecting the functionality of the application. This can
 * be achieved by the use of template pattern.
 *
 * 4.- Interface segregation. We can have an abstraction of the system using
 * interfaces for example List<String> var = new ArrayList<>(). List<String> is
 * the interface and new ArrayList<>() is the implementation. Suppose that we
 * have an interface and we want to use only 2. However we need to implement all
 * of them. That is called "Fat Interface". The Interface segregation principle
 * states that no client should be forced to depend on methods that they don't
 * use. So we need to break them in smaller interfaces so they better satisfy
 * the needs of the client.
 *
 * 5.- Dependency Inversion principle. High level modules shouldn't depend on
 * low level modules We have to use a layer of abstraction between high level
 * and low level modules. 
 * 
 * High level <---> Abstraction Layer (Interfaces) <---> High level module. 
 * 
 * Abstraction Layer shouldn't depend on the low level
 * modules. - Creating an object of a class with new results in a class being
 * tightly couple to another class - Dependency inversion helps to create
 * loosely coupled modules - Modules should depend on abstraction In Object
 * Oriented Programming inversion of control can be implemented in several ways:
 * Using design patterns: factory, templates or strategy patterns. 
 * Using service locator pattern. 
 * Using dependency injection.
 *
 * -- Strategy pattern -- 
 * 
 * Principle #1 Take what changes and encapsulate it. 
 * Separate code that changes <-------> code that stays the same.
 * Later you can change the parts that change without affecting the ones that don't
 * 
 * Principle #2 Program to an interface not an implementation.
 * Dog dog = new Dog(); // Not good; programming to an implementation
 * Animal dog = new Dog(); // Good programming 
 * Sometimes is easier to separate behavior from implementation: easier to reuse + we can add
 * different behavior without affecting the existing one.
 * 
 * Principle #3 Favor composition over inheritance. HAS-A relationship It gives you more flexibility
 * a) You can encapsulate stuff into their own classes
 * b) You can change behavior at run time using interfaces.
 * Inheritance IS-A relationship.
 * 
 * -- Observer pattern --
 * Defines a one to many dependencies -> if one object changes all its dependents are notified automatically
 * When two objects are loosely couple they can interact but they know little about each other.
 * The only thing the subject knows about the observer is that it implements a certain interface.
 * We can add observers whenever we want we just need to implement the observer interfaces
 * We don't have to modify the subject to add new type of observers.
 * We can independently use subjects or observers.
 * We can change the subject or observer independently.
 * 
 * -- Command pattern --
 * We can encapsulate method invocation, it encapsulates a request as an object
 * The object invoking the computation doesn't know about the implementation
 * There 4 components: command, receiver, invoker and client
 * 1) Command: knows about the receiver and invokes a method of the receiver, parameters
 * are part of the command.
 * 2) Receiver: does the work itself.
 * 3) Invoker: knows how to execute a command, and optionally does bookkeeping about the command
 * execution
 * 4) The client decides which commands to execute at which points to execute a command, it
 * passes the command object to the invoker.
 * 
 * -- Template --
 * In template pattern an abstract class exposes a defined way/template to execute its methods
 * The subclass can override the methods but the invocation needs to be as described in the abstract class
 * 
 * -- Null object pattern --
 * Motivation: reference may be null
 * It can be very elaborate to deal with null references
 * We have to check whether a reference is null or not -> NullPointerException if we call methods on null objects
 * Solution. The null pointer design. Create an abstract class specifying several operations to be done.
 * Concrete classes extending this class and a null object class providing "do nothing implementation"
 * 
 * -- Visitor --
 * Way to separate an algorithm from the object structure on within it operates.
 * As a result of this separation is the ability of adding new operations to existing object structures
 * without modifying those structures.
 * 
 * -- Single Pattern --
 * Restrict the instantiation of a class to a one single instance.
 * This is useful when exactly one object is needed to coordinate actions across the system
 * 
 */
public class solid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookInt bk = new Book(457, "John Smith");
        bk.save();

        // -- Open-closed principle --
        
        //if we had to add a new sort algorithm we wee need to modify the code in SortManager and SortType
        // So instead of abstract class we declare an interface with a sort method and get rid of SortType
        // OPEN FOR EXPANSION BUT CLOSED FOR MODIFICATION
        //Sorter ms = new Mergesort();
        //Sorter is = new Insertsort();
        SortManager sm = new SortManager();

        sm.sort(new Mergesort());
        sm.sort(new Insertsort());
        sm.sort(new Quicksort());

        // -- Liskov substitution principle --
        
        //Vehicle vehic = new ElectricCar();
        //This violates the Liskov principle, we can't call addFuel. 
//        if (vehic instanceof ElectricCar) {
//            System.out.println("Unable to add fuel");
//        } else {
//            vehic.addFuel();
//        }
        //Using the parent type instead of the subtype doesn't break the functionality of the application
        //Liskov principle 
        ElectricVehicle ve = new ElectricCar();
        ve.chargeBattery();

        ElectricCar ec = new ElectricCar();
        ec.chargeBattery();

        // -- Interface segregation -- 
        
        Tree balancedTree = new BalancedTree();
        Tree BinarySearchTree = new BinarySearchTree();
        
        // -- Dependency Inversion --
        
        //DatabaseHandler dbh = new DatabaseHandler();
        DatabaseHandler dbh = new DatabaseHandler( new MySQLDatabase() );
        dbh.connect();
        dbh.disconnect();
        DatabaseHandler dbh_ = new DatabaseHandler( new OracleDatabase() );
        dbh_.connect();
        dbh_.disconnect();
        
        // -- Strategy pattern -- 
        Manager mna = new Manager();
        mna.setStrategy(new Add());
        mna.operation(2, 5);
 
        Manager mnm = new Manager();
        mnm.setStrategy(new Multiply());
        mnm.operation(5, 6);
        
        // -- Observer pattern --
        WeatherStation subject = new WeatherStation();
        WeatherObserver observer = new WeatherObserver(subject);
        
        subject.setPressure(100);
        subject.setTemperature(24);
        subject.setHumidity(50);
        
        // -- Command Pattern --
        Light light = new Light();
        Switcher switcher = new Switcher();
        TurnOnCommand turnOnCommand = new TurnOnCommand(light);
        TurnOffCommand turnOffCommand = new TurnOffCommand(light);
        switcher.addCommand(turnOnCommand);
        switcher.addCommand(turnOffCommand);
        switcher.executeCommand();
         
        // -- Command Pattern Queue model --
        Algorithm algorithm = new Algorithm();
        
        Thread t1 = new Thread( new Runnable()
           {
            @Override
            public void run(){
                algorithm.producer();
            }
        });
        
        Thread t2 = new Thread( new Runnable()
           {
            @Override
            public void run(){
                algorithm.consumer();
            }
        });
        
        t1.start();
        t2.start();
        
        // --- Iterator ---
        NameRepository nameRepository = new NameRepository();
        
        for( Iterator it = nameRepository.getIterator(); it.hasNext(); ){
            String name = (String)it.next();
            System.out.println(name);
        }
        
        // -- Template --
        int []numbers = { 4, 7, 8, 1, 3, -1 };
        
        Algorithm_ bubble = new BubbleSort(numbers);
        bubble.sort();
        
        
        // -- Null object pattern --
        CustomerFactory custFact = new CustomerFactory();
        
        System.out.println(custFact.getCustomer("Tom").getCustomer());
        
        // -- Visitor --
        List<ShoppingItem> items = new ArrayList<>();
        items.add(new Table("Desk", 20));
        items.add(new Chair("Chair1", 10));
        items.add(new Chair("Chair2", 10));
        
        ShoppingCartVisitor shoppingCart = new ShoppingCart();
        
        double sum = 0;
        
        for( ShoppingItem sh : items ){
            sum = sum + sh.accept(shoppingCart);
        }
              
        System.out.println(sum);
        
        // -- Single Pattern --
        
    }

}
