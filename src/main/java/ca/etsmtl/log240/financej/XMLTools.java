package ca.etsmtl.log240.financej;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class XMLTools {
    public void writeXMLFile(ArrayList<User> usersList) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("Users");
            document.appendChild(root);

            for (User user : usersList) {
                // user element
                Element userElement = document.createElement("User");
                root.appendChild(userElement);

                // username element
                Element username = document.createElement("Username");
                username.appendChild(document.createTextNode(user.getUsername()));
                userElement.appendChild(username);

                // password element
                Element password = document.createElement("Password");
                password.appendChild(document.createTextNode(user.getPassword()));
                userElement.appendChild(password);

                // role element
                Element role = document.createElement("Role");
                role.appendChild(document.createTextNode(user.getRole()));
                userElement.appendChild(role);
            }

            // create the xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Users.xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("Users saved to XML file.");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    public ArrayList<User> parseXML() {
        String xmlFile = "Users.xml";
        ArrayList<User> userList = new ArrayList<User>();
        try {
            File fXmlFile = new File(xmlFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("User");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String username = eElement.getElementsByTagName("Username").item(0).getTextContent();
                    String password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    String role = eElement.getElementsByTagName("Role").item(0).getTextContent();
                    User user = new User(username, password, role);
                    userList.add(user);
                }
            }
        } catch (Exception e) {
        }
        System.out.println("Users loaded from XML file.");
        return userList;
    }
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkIfUserExists(String text) {
        ArrayList<User> usersList = FinanceJ.users;
        for (User user : usersList) {
            if (user.getUsername().equals(text)) {
                return true;
            }
        }
        return false;
    }

    public static int getUserAuthorisation(String text) {
        ArrayList<User> usersList = FinanceJ.users;
        for (User user : usersList) {
            if (user.getUsername().equals(text)) {
                if (user.getRole().equals("admin")) {
                    return 0;
                } else if (user.getRole().equals("Advanced User")){
                    return 1;
                } else if (user.getRole().equals("Normal User")){
                    return 2;
                }
            }
        }
        return -1;
    }

    public static String getUserRole(String text) {
        ArrayList<User> usersList = FinanceJ.users;
        for (User user : usersList) {
            if (user.getUsername().equals(text)) {
                return user.getRole();
            }
        }
        if (text.equals("admin")) {
            return "Administrator";
        }
        return null;
    }
}