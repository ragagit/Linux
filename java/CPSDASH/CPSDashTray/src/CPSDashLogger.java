import java.io.File;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

class CPSDashLogger{

    public static Logger logger = null;
    public String logName = "CPSDashboard";

    /**
     * Sets Log file name.
     * @param name String
     */
    void setLogName(String name){
        logName = name;
    }
    /**
     * CPSDashLogger constructor, sets log file name.
     * @param name String
     */
    CPSDashLogger( String name){
        logName = name;
    }

    /**
     * This function does all the necessary configuration for the CPSDashboard logger, such
     * as log file creation and messages format.
     */
    void configLogger( String path){


      //File f = new File(path + "\\log\\CPSDashboard.log");

      String pattern = "%d [%l] %p - %m%n";
      CPSDashLogger.logger = Logger.getLogger(logName);
      Logger logger = CPSDashLogger.logger;
      PatternLayout layout = new PatternLayout(pattern);
      ConsoleAppender appender = new ConsoleAppender(layout);
      logger.addAppender(appender);
      FileAppender appenderFile = null;

//      RegistryAccess reg = new RegistryAccess();
//      String path = reg.getRegistryComponent(CPSDashConstants.CPSDASH_COMP).getCompPath();

      File f = new File(path + "\\log");
      if( !f.exists()){
            f.mkdir();         
      }

      try{
          appenderFile = new FileAppender(layout, path + "\\log\\CPSDashboard.log", true );          
      }catch( Exception e ){ System.out.println(e.getMessage().toString());}

      
      logger.addAppender(appenderFile);
      logger.debug("path of log file: " + path );


      //DOMConfigurator.configure(".\\conf\\CpsDashLogConf.xml");

      logger.setLevel(Level.DEBUG);

    }

    /**
     * This static function makes the CPSDashLogger available to all objects by
     * setting the Logger to a static and public variable.
     * @param logger  Logger
     */
    public static void setCPSDashLogger( Logger logger ){
        CPSDashLogger.logger = logger;
    }

    /**
     * Returns public Logger.
     * @return Logger
     */
    public static Logger getCPSDashLogger(){
         return CPSDashLogger.logger;
    }

}