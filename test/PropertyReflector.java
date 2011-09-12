
import java.lang.reflect.Field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.StringWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class PropertyReflector {
    
    private Document document;
    
    public String first_name;
    public String last_name;
    public String email;
    
    public PropertyReflector()
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();
            this.document = impl.createDocument(null,null,null);
        } catch(Exception e) {
            // fucking checked exceptions piss me off
        }
    }
    
    protected String getEntityName()
    {
        Pattern p1 = Pattern.compile("([A-Z]+)([A-Z][a-z])");
        Pattern p2 = Pattern.compile("([a-z\\d])([A-Z])");
        
        String name = p1.matcher(this.getClass().getName()).replaceAll("$1_$2");
        name =  p2.matcher(name).replaceAll("$1_$2");
        name = name.replace('-', '_').toLowerCase();
        
        return name;
    }
    
    protected Field[] getFields()
    {
        return this.getClass().getFields();
    }
    
    public void create()
    {
        try {

            Element entity = this.document.createElement(this.getEntityName());

            for (Field field : this.getFields()) {
                
                Object value = field.get(this);
                if (value != null) {
                    Element tag = this.document.createElement(field.getName());
                    Text text = this.document.createTextNode(value.toString());
                    tag.appendChild(text);
                    entity.appendChild(tag);            
                }
            }
            
            this.document.appendChild(entity);
            
            StringWriter writer = new StringWriter(); 
            Transformer converter = TransformerFactory.newInstance().newTransformer();
            converter.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            converter.setOutputProperty(OutputKeys.INDENT, "yes");
            converter.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            converter.transform(new DOMSource(this.document), new StreamResult(writer)); 
                        
            System.out.println(writer.toString());
            
        } catch(Exception e) {
            // do nothing
        }        
    }
    
    public static void main(String[] args)
    {

        PropertyReflector model = new PropertyReflector();
        
        model.first_name = "Mark";
        model.email = "me@maetl.net";
        
        model.create();
        
    }
    
}