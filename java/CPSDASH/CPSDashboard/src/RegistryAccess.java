import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

import org.apache.log4j.Logger;

public class RegistryAccess{

    private static final String REGQUERY_CMD = "reg query";
    private static final String REGQUERY_VFLAG = "/v";
    private static final String REGQUERY_SFLAG = "/s";
    private static final String REG_STR = "REG_SZ";
    private static final String REG_DWORD = "REG_DWORD";
    private static final String REG_HK_LOC = "HKEY_LOCAL_MACHINE";
    private static final String REG_SOFTWARE = "SOFTWARE";
    private static final String REG_PRINTERON = "PrinterOn Corporation";
    private static final String REG_PATH = "Path";
    private static final String REG_INSTALL_PATH = "InstallPath";
    private static final String REG_TOMCAT = "Tomcat";
    private static final String REG_PRINTANYWHERE ="PrintAnywhere";
    private static final String REG_PRINTWHERE = "PrintWhere 3.6";
    private static final String SPC = " ";
    private static final String QUOTE ="\"";
    private static final String REG_APACHE_SOFT = "Apache Software Foundation";
    private static final String SLASH = "\\";
    private static final String REG_VERSION = "Version";
    private static final String REG_BUILD = "Build";
    private static final String REG_SOFT_VER = "SoftwareVersion";
    private static final String REG_APACHE_TOM = "Apache Tomcat";
    private static final String REG_ADOBE = "Adobe";
    private static final String REG_ACROBAT_READER ="Acrobat Reader";
    private static final String REG_DRPRINT = "Dr. Print";
    private static final String REG_CPS = "Central Print Services";
    private static final String REG_CPSDASH = "CPS Dashboard";
    private static final String REG_EXP_STR = "REG_EXPAND_SZ";
    private static final String REG_SYSTEM = "SYSTEM";
    private static final String REG_CCS = "CurrentControlSet";
    private static final String REG_SESS_MANG = "Session Manager";
    private static final String REG_CONTROL = "Control";
    private static final String REG_ENV = "Environment";
    private static final String REG_CAT_HOME = "CATALINA_HOME";
    private static final String REG_PDS = "Print Delivery Station";
    private static final String REG_PDG = "Print Delivery Gateway";
    private static final String REG_PONAD = "PrinterOn Advertiser";

    private Logger logger = CPSDashLogger.getCPSDashLogger();
    private String line;
    private Vector<CPSDashComponent> pocomponents;
    public static Vector<CPSDashComponent> allcomponents;

    public RegistryAccess(){
        pocomponents = null;
        line = "";
    }

    /**
     * This is a static function and makes the component vector available to
     * all classes. The vector contains installation path and version of each
     * of the PrinterOn components.
     * @param comps
     */
    public static void setAllComponents( Vector<CPSDashComponent> comps ){
        RegistryAccess.allcomponents = comps;
    }
    /**
     * Returns CPSDashComponent vector containing PrinterOn component installation
     * path and version.
     * @return Vector CPSDashComponent
     */
    public static Vector<CPSDashComponent> getAllComponents(){
        return RegistryAccess.allcomponents;
    }
    /**
     * This function is static and returns path and version of a specific
     * PrinterOn component.
     * @param compName String component name
     * @return CPSDashComponent
     */
    public static CPSDashComponent searchComp( String compName ){
        for( int i=0; i < RegistryAccess.allcomponents.size(); i++){
            if( RegistryAccess.allcomponents.elementAt(i).getCompName().equals(compName) ){
                return RegistryAccess.allcomponents.elementAt(i);
            }
        }
        return null;
    }

    /**
     * Adds a single component to the component vector.
     * @param comp CPSDashComponent
     */
    public void addComponentToPOList( CPSDashComponent comp ){

        if( pocomponents == null )
            pocomponents = new Vector<CPSDashComponent>();

        if( comp != null )
            pocomponents.add(comp);

    }

    /**
     * Creates a vector with all PrinterOn components.
     * @return Vector CPSDashComponent.
     */
    public Vector<CPSDashComponent> getAllPORegistryComponents(){

        logger.debug("getAllPORegistryComponents() START");

        addComponentToPOList(getRegistryComponent(CPSDashConstants.TOMCAT_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.PRINTANYWHERE_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.PRINTWHERE_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.PDS_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.DRPRINT_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.CPS_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.CPSDASH_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.PONAD_COMP));
        addComponentToPOList(getRegistryComponent(CPSDashConstants.PDG_COMP));
        
        logger.debug("getAllPORegistryComponents() END");

        return pocomponents;
    }

    /**
     * This function creates the command to query the windows registry for each one
     * of the PrinterOn component.
     * @param compName
     * @return
     */
    public String createRegCommand( String compName ){

        String cmd = "";

        if( compName.indexOf(REG_TOMCAT) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Environment" /s
            //cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_APACHE_SOFT + SLASH + REG_TOMCAT + QUOTE + SPC + REGQUERY_SFLAG;
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SYSTEM + SLASH + REG_CCS + SLASH + REG_CONTROL + SLASH + REG_SESS_MANG + SLASH + REG_ENV + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() - cmd:" + cmd );
        }else if( compName.indexOf(REG_PRINTANYWHERE) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\PrintAnywhere" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_PRINTANYWHERE + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() -  cmd:" + cmd );
        }else if( compName.indexOf(REG_DRPRINT) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\Dr. Print" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_DRPRINT + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() -  cmd:" + cmd );
        }else if( compName.indexOf(REG_PRINTWHERE) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\PrintWhere 3.5" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_PRINTWHERE + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() -  cmd:" + cmd );
        }else if( compName.indexOf(REG_ACROBAT_READER) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Adobe\Acrobat Reader" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_ADOBE + SLASH + REG_ACROBAT_READER + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() - cmd:" + cmd );
        }else if( compName.indexOf(REG_CPS) != -1 ){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\Central Print Services" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_CPS + QUOTE + SPC + REGQUERY_SFLAG;
        }else if( compName.indexOf(REG_CPSDASH) != -1 ){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\CPS Dashboard" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_CPSDASH + QUOTE + SPC + REGQUERY_SFLAG;
        }else if( compName.indexOf(REG_PDS) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\Print Delivery Station" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_PDS + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() -  cmd:" + cmd );            	
        }else if( compName.indexOf(REG_PDG) != -1){
        //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\Print Delivery Station" /s
        cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_PDG + QUOTE + SPC + REGQUERY_SFLAG;
        logger.debug("createRegCommand() -  cmd:" + cmd );
        }
        else if( compName.indexOf(REG_PONAD) != -1){
            //reg query "HKEY_LOCAL_MACHINE\SOFTWARE\PrinterOn Corporation\PrinterOn Advertiser" /s
            cmd = REGQUERY_CMD + SPC + QUOTE + REG_HK_LOC + SLASH + REG_SOFTWARE + SLASH + REG_PRINTERON + SLASH + REG_PONAD + QUOTE + SPC + REGQUERY_SFLAG;
            logger.debug("createRegCommand() -  cmd:" + cmd );            
        }


        return cmd;
    }

    /**
     * Return the component path
     * @param line
     * @return String
     */
    public String getCompPath( String line ){

        int p;
        logger.debug("getCompPath() START");
        logger.debug("line:" + line);

        p = line.indexOf(REG_STR);

        if( p != -1 )
           return line.substring( p + REG_STR.length()).trim();

        p = line.indexOf(REG_EXP_STR);
        if( p != -1 )
            return line.substring( p + REG_EXP_STR.length()).trim();

        logger.debug("getCompPath() END");
        return " ";
    }

    /**
     * Returns the component version
     * @param line
     * @return String
     */

    public String getCompVersion( String line){

        int p;
        logger.debug("getCompVersion() START");
        logger.debug("line:" + line);

        p = line.indexOf(REG_STR);

        if( p != -1 )
            return line.substring( p + REG_STR.length()).trim();
        
        logger.debug("getCompVersion() END");

        return " ";
       
    }

    /**
     * Returns the path pattern and version pattern to be used on querying the windows resgistry.
     * @param compName Component Name
     * @param type
     * @return
     */
    public String getPattern( String compName, String type )
    {
        String patt = " ";

        if( type.equals("PATH")){

            if( compName.equals(CPSDashConstants.TOMCAT_COMP)){
                patt = "CATALINA_HOME";
            }
            else if( compName.equals(CPSDashConstants.PRINTANYWHERE_COMP)){
                patt = "Path";
            }
            else if( compName.equals(CPSDashConstants.PRINTWHERE_COMP)){
                patt = "Install Path";
            }
            else if( compName.equals(CPSDashConstants.PDS_COMP)){
                patt = "InstallPath";
            }
            else if( compName.equals(CPSDashConstants.PDG_COMP)){
                patt = "InstallPath";
            }
            else if( compName.equals(CPSDashConstants.DRPRINT_COMP)){
                patt = "Path";
            }
            else if( compName.equals(CPSDashConstants.CPS_COMP)){
                patt = "Path";
            }
            else if( compName.equals(CPSDashConstants.CPSDASH_COMP)){
                patt = "Path";
            }
            else if( compName.equals(CPSDashConstants.PONAD_COMP)){
                patt = "Path";
            }            
        }
        else if( type.equals("VER")){

            if( compName.equals(CPSDashConstants.TOMCAT_COMP)){
                patt = "TomcatVersion";
            }
            else if( compName.equals(CPSDashConstants.PRINTANYWHERE_COMP)){
                patt = "Version";
            }
            else if( compName.equals(CPSDashConstants.PRINTWHERE_COMP)){
                patt = "Build";
            }
            else if( compName.equals(CPSDashConstants.PDS_COMP)){
                patt = "SoftwareVersion";
            }
            else if( compName.equals(CPSDashConstants.PDG_COMP)){
                patt = "SoftwareVersion";
            }
            else if( compName.equals(CPSDashConstants.DRPRINT_COMP)){
                patt = "SoftwareVersion";
            }
            else if( compName.equals(CPSDashConstants.CPS_COMP)){
                patt = "CPSVersion";
            }
            else if( compName.equals(CPSDashConstants.CPSDASH_COMP)){
                patt = "Version";
            }
            else if( compName.equals(CPSDashConstants.PONAD_COMP)){
                patt = "Version";
            }            
        }
        logger.debug("compName:" + compName + " pattern:" + patt );
        return patt;

    }
    /**
     * This function executes a query to the Windows registry.
     * @param compName
     * @return CPSDashComponent
     */
    public CPSDashComponent getRegistryComponent( String compName ){

        String path = null;
        String version = null;
        String dataPath = null;
        String cmd = "";
        CPSDashComponent comp = null;
        String pattPath = "";
        String pattVer = "";

        logger.debug("getRegistryComponent() START compName:" + compName );
        //Creating Reg query command
        cmd = createRegCommand( compName );

        //We need to get the pattern the component has in the registry for path and version it could be ComPath, SOftwareVersion. etc
        pattPath = getPattern( compName, "PATH" );
        pattVer = getPattern( compName, "VER" );

        try{
            
            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(reader);
           // Thread.sleep(1000);
            
            try {
	            while( (line = bufferedreader.readLine() ) != null ){

	                if( line.trim().startsWith( pattPath )){
	                    path = getCompPath(line);
	                    logger.debug("path:" + path);                    
	                }else if( line.trim().startsWith(  pattVer ) ){
	                    version = getCompVersion(line);
	                    logger.debug("version:" + version);
	                }else if( line.trim().startsWith( "Temp" ) ){
	                	dataPath = getCompVersion(line);
	                    logger.debug("Temp:" + version);
	                }          
	            }
            } finally {
            	bufferedreader.close();
            	reader.close();
            	process.destroy();
            }
            
            if (compName.compareToIgnoreCase(CPSDashConstants.PDS_COMP) == 0)
            	path = path + SLASH + "Client";

            if (compName.compareToIgnoreCase(CPSDashConstants.PDG_COMP) == 0)
                path = path + SLASH + "Gateway";
            
            if( ( path != null && !path.equals(" ") ) && ( version != null && ! version.equals(" ") ) ){
                logger.debug("Creating comp object for: " + path  + " " + version );
                comp = new CPSDashComponent(compName, path, version);
                comp.setDataPath(dataPath);
            }

        }catch( Exception e){
           logger.error(e.getMessage().toString());
           return comp;
        }


        logger.debug("getRegistryComponent() END");
        
        return comp;
    }


    /**
     * This function queries the registry and returns the value of a specific entry.
     * @param entry String.
     * @return String
     */
    public String getRegistryValue(String path, String key ){

      String value = "";
      //String cmd = "reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\PrinterOn Corporation\\CPS Dashboard\" /s";
      String cmd = "reg query \"" + path + "\"" + " /s";
      String line ="";
      logger.debug("getRegistryValue() BEGIN");
      logger.debug( "cmd: " + cmd );

        try{

            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader reader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedreader = new BufferedReader(reader);

            Thread.sleep(1000);

            try {
	            while( (line = bufferedreader.readLine() ) != null ){
	                if(  line.indexOf( key ) != -1 ){
	                    value = getCompVal( line );
	                    logger.debug("value:" + value);
	                }
	            }
            } finally {
            	bufferedreader.close();
            	reader.close();
            	process.destroy();
            }

        }catch( InterruptedException ie ){
            logger.error(ie.getMessage().toString());
        }catch( Exception e){
           logger.error(e.getMessage().toString());
        }
      logger.debug("getRegistryValue() END");

        return value;
    }

    /**
     * This function gets the value of a registry key.
     * @param line
     * @return String
     */
    public String getCompVal( String line ){

        logger.debug("getCompPath() START");
        logger.debug("line:" + line);
        String REG_STR = "REG_SZ";
        int p = line.indexOf(REG_STR);

        if( p == -1)
            return "";

        logger.debug("getCompPath() END");

        return line.substring( p + REG_STR.length()).trim();
    }


}