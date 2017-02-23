import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class CPSDashConfig{

    public static CPSDashConfig instance = null;
    Logger logger = CPSDashLogger.getCPSDashLogger();
    String version, lookandfeel;
    String configFileName;
    String CPSDashPath = "";
    String CPSDashVersion = "";
    String CPSDataPath = "";
    boolean blinking = false;

    /**
     * CPSDashConfig constructor, gets CPSDashboard info.
     */
    CPSDashConfig(){

        CPSDashComponent comp = RegistryAccess.searchComp(CPSDashConstants.CPSDASH_COMP);

        if( comp != null ){
            CPSDashPath = comp.getCompPath();
            CPSDashVersion = comp.getCompVersion();
            CPSDataPath = comp.getDataPath();
        }
        else{
            JOptionPane.showMessageDialog(null, "Unable to find CPS Dashboard installation path. Reinstall Software" , "Fatal Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * This is a static function that makes available to the public the configuration
     * instance by setting it to a static variable of the same type.
     * @param obj CPSDashConfig
     */
    public static void setCPSDashConfigInstance( CPSDashConfig obj ){
        instance = obj;
    }

    /**
     * This function returns the CPSDashboard configuration instance.
     * @return CPSDashConfig
     */
    public static CPSDashConfig getCPSDashConfigInstance(){
        return instance;
    }

    /**
     * Sets the Look and Feel of CPSDashboard
     * @param mode String
     */
    public void setLookAndFeelMode(String mode){
         lookandfeel = mode;
    }
    /**
     * Returns the current Look and Feel of CPSDashboard.
     * @return String
     */
    public String getLookAndFeelMode(){
        return lookandfeel;
    }

    /**
     * Returns CPSDashboard intallation path
     * @return String
     */
    public String getCPSDashPath(){
        return CPSDashPath;
    }

    public String getCPSDataPath(){
        return CPSDataPath;
    }
    /**
     * Returns the CPSDashboard version
     * @return String
     */
    public String getCPSDashVersion(){
        return CPSDashVersion;
    }

    /**
     * Returns whether the blinking of the monitor icons is set or not.
     * @return boolean
     */
    public boolean getBlinking(){
        return blinking;
    }

    /**
     * Sets blinking option for monitoring icon when the CPSDashboard options get saved.
     * @param blnk boolean
     */
    public void setBlinking( boolean blnk){
        blinking = blnk;
    }

    /**
     * Reads CPSDashboard configuration file.
     * @param fileName String
     * @return boolean
     */
    public boolean readCPSDashConfig( String fileName ){
        Document doc = null;
        String buf = null;
        configFileName = fileName;

        logger.debug("readCPSDashCOnfig() START");
        logger.info("reading " + fileName );

        File configFile = new File(fileName);
        try{
            buf = readAllFromInputStream( new FileInputStream(configFile) );
        }catch( Exception e){
            logger.error(e.getMessage().toString());
            return false;
        }

        try{
            doc = generateDOM(buf);
        }catch( Exception e){
            logger.error(e.getMessage().toString());
            return false;
        }
        Element root = doc.getRootElement();

        importFromJDomElement(root);

        logger.debug("readCPSDashCOnfig() END");

        return true;
    }

        /** Creates a JDOM Document object from an XML String. This is a
     *  convienience method that is useful for handling the XML results
     *  of API calls as JDOM objects.
     *
     *  @param xml The XML string for which a JDOM Document will be built.
     *  @throws Exception If an error occurs when building the Document.
     *  @return The JDOM Document corresponding the the XML string.
     */
    public Document generateDOM(String xml) throws Exception {

        logger.debug("generateDOM() START");

        try {
            SAXBuilder builder = new SAXBuilder();
            return builder.build(new StringReader(xml));
        } catch (JDOMException jde) {
            logger.error("Error: Could not parse the input string. String is empty, corrupt, or not valid XML. Re-throwing exception.");
            throw new Exception(
                    "ERR_CPS_XmlParseException" +
                    "Could not parse API result. Please contact the System Administrator." +
                    "DOM_BUILDER: XML Parsing exception. Input may not be XML, or may be syntactically incorrect. Check CPSDashboard log for details.");
        } catch (IOException ioe) {
            logger.error("Error: And I/O Exception occurred. Could not read and/or not parse the input. Re-throwing exception.");
            throw new Exception(
                    "ERR_CPS_XmlParseException" +
                    "Could not parse API result. Please contact the System Administrator." +
                    "DOM_BUILDER: XML Parsing exception. Input may not be XML, or may be syntactically incorrect. Check CPSDashboard log for details.");
        }

        //logger.debug("generateDOM() END");
    }

    /** Reads entire output from an InputStream. This private helper method
    *  reads the entire contents of a URLConnection response steam into
    *  a StringBuffer, then converts is to a String
    *
    *  <b><i>WARNING:</i></b> Do not use this method to read in data from
    *  a Servlet Stream; it does not do character encoding coversions.
    *
    *  @param is -- The InputStream from which to read data
    *  @return The entire contents of the response.
    */
    public String readAllFromInputStream(InputStream is) throws IOException {
        StringBuffer result = new StringBuffer();
        BufferedReader reader = null;

        logger.debug("readAllFromInputStream() START");

        try {
            if (is != null) { 
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null) {
                    result.append(line);
                    line = reader.readLine();
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        logger.debug("readAllFromInputStream() END");

        return result.toString();

    }

    /**
     * Import the content of a JDom Element
     * @param root Element
     */
    public void importFromJDomElement( Element root ){

        logger.debug("importFromDJDomElement() START");

        if( root.getAttribute("version") != null ){
            version = root.getAttribute("version").getValue();
        }
        if( root.getChild("lookandfeel") != null){
            lookandfeel = root.getChild("lookandfeel").getAttributeValue("lnf");
        }
        if( root.getChild("blinking") != null ){
            String blk = root.getChild("blinking").getAttributeValue("blink");
            if( blk.equals("true"))
                blinking = true;
            else
                blinking = false;
        }

        logger.debug("importFromDJDomElement() END");
    }

    /**
     * Creates a JDom from a XML String.
     * @param xmlString
     * @throws java.io.IOException
     * @throws java.lang.Exception
     */
    public void importFromXmlString(String xmlString) throws IOException, Exception {
        logger.debug("importFromXmlString() START");
        try {
            importFromJDomElement(generateDOM(xmlString).getRootElement());
        } catch (Exception jde) {
            throw new JDOMException("Could not parse XML input.\nRoot Cause: \n" +jde.fillInStackTrace());
        }
        logger.debug("importFromXmlString() END");
    }

    /**
     *  Returns an XML String containing the Business Object's state. This
     *  method calls exportAsJDomElement(), then converts the resulting JDOM
     *  Element into a String.
     *
     *  @return The XML string representing the Business Object.
     */
    public String exportAsXmlString(boolean includeHeader) throws IOException {
        String result = null;
        logger.debug("exportAsXmlString() START");

        try {
            return generateXmlString(exportAsJDomElement());
        } catch (Exception jde) {
            throw new IOException("Root Cause: \n" +jde.fillInStackTrace());
        }

        //logger.debug("exportAsXmlString() END");
    }

    /**
     * Exports elements to a JDom object.
     * @return Element
     */
    public Element exportAsJDomElement() {
        logger.debug("exportAsJDomElement() START");
        String blnk = "";
        Element root = new Element("CPSDashConfig");
        root.setAttribute("version", CPSDashConstants.VERSION );

        root.addContent( new Element("lookandfeel").setAttribute("lnf", lookandfeel) );
//        if( blinking )
//            blnk = "true";
//        else
            blnk = "false";

        root.addContent( new Element("blinking").setAttribute("blink", blnk) );

        logger.debug("exportAsJDomElement() END");
        return root;
    }
    /**
     *  Creates an XML String from a JDOM Element. This is a convienience
     *  method.
     *
     *  @param root  The JDOM Element string from which to create an XML string.
     *  @return The XML String correcponding to the JDOM Element.
     */
    protected String generateXmlString(Element root) throws IOException {

        logger.debug("generateXmlString() START");

        StringWriter writer = new StringWriter();
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(outputter.getFormat().getPrettyFormat());
        outputter.output(root, writer);

        logger.debug("generateXmlString() END");

        return writer.toString();
    }

    /**
     *  Writes an XML configuration file to disk.
     *
     *  @return TRUE if the configuration file was written correctly.
     */
    public boolean writeConfigFile(){
        // write config file
        logger.debug("writeCondigFile() START");
        logger.info("Writing config file" + configFileName );

        try{
            FileWriter writer = new FileWriter(configFileName);
            writer.write(exportAsXmlString(false));
            writer.flush();
            writer.close();
        }catch( IOException e){
            CPSDashLogger.logger.error( e.getMessage().toString());
            return false;
        }
        logger.info("Config file written.");
        logger.debug("writeConfigFile() END");

        return true;
    }

}