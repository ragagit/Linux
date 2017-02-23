/**
 * This class is used to store and retrieve information of each one of the
 * PrinterOn components.
 * @author garciar
 */
class CPSDashComponent{
    String compName;
    String compVersion;
    String compPath;
    String compLogPath;
    String dataPath;

    /**
     * This default constructor initializes the component variable to empty strings.
     */
    CPSDashComponent(){
        compName = "";
        compVersion = "";
        compPath = "";
        dataPath = "";
    }

    /**
     * This constructor initializes a CPSDashComponent with passing arguments.
     * @param name String
     * @param path String
     * @param version String
     */
    CPSDashComponent( String name, String path, String version ){
        compName = name;
        compVersion = version;
        compPath = path; 
        dataPath = "";
    }

    /**
     * Sets component name
     * @param name String
     */
    public void setCompName( String name ){
        compName = name;
    }

    /**
     * Gets component name
     * @return String
     */
    public String getCompName(){
        return compName;
    }

    /**
     * Sets component version
     * @param version String
     */
    public void setCompVersion( String version ){
        compVersion = version;
    }

    /**
     * Returns the component version
     * @return String
     */
    public String getCompVersion(){
        return compVersion;
    }

    /**
     * Sets component installation path
     * @param path String
     */
    public void setCompPath( String path ){
        compPath = path;
    }

    /**
     * Returns component installation path
     * @return String
     */
    public String getCompPath(){
        return compPath;
    }
    
    public String getDataPath(){
        return dataPath;
    }
    
    public void setDataPath(String path){
        dataPath = path;
    }
    
    /**
     * Sets component Log path
     * @param path String
     */
    public void setCompLogPath( String path ){
    	compLogPath = path;
    }

    /**
     * Returns component Log path
     * @return String
     */
    public String getCompLogPath(){
        return compLogPath;
    }    
}