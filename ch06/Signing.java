import java.io.*;
import java.security.*;
import java.util.*;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dom.*;
import javax.xml.crypto.dsig.dom.*;
import javax.xml.crypto.dsig.keyinfo.*;
import javax.xml.crypto.dsig.spec.*;
import javax.xml.soap.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.xml.sax.*;

public class Signing {

  public static void main(String[] args) throws Exception {

    SOAPMessage soapMessage = createSOAPMessage();

    SOAPPart soapPart = soapMessage.getSOAPPart();
    Source source = soapPart.getContent();
    Node root = generateDOM(source);

    dumpDocument(root);

    KeyPair keypair = generateDSAKeyPair();
    XMLSignature sig = generateXMLSignature(keypair);

    System.out.println("Signing the message...");
    signTree(root, keypair.getPrivate(), sig);

    dumpDocument(root);

    System.out.println("Validate the signature...");
    boolean valid = validateXMLSignature(keypair.getPublic(), root, sig);
    System.out.println("Signature valid? " + valid);
  }

  private static SOAPMessage createSOAPMessage() throws SOAPException {
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
    SOAPPart soapPart = soapMessage.getSOAPPart();
    SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

    SOAPHeader soapHeader = soapEnvelope.getHeader();
    SOAPHeaderElement headerElement = soapHeader.addHeaderElement(
      soapEnvelope.createName("Signature", "SOAP-SEC",
      "http://schemas.xmlsoap.org/soap/security/2000-12"));

    SOAPBody soapBody = soapEnvelope.getBody();
    soapBody.addAttribute(soapEnvelope.createName("id", "SOAP-SEC",
      "http://schemas.xmlsoap.org/soap/security/2000-12"), "Body");
    Name bodyName =soapEnvelope.createName("FooBar", "z", "http://example.com");
    SOAPBodyElement gltp = soapBody.addBodyElement(bodyName);

    return soapMessage;
  }

  private static Node generateDOM(Source source)
      throws ParserConfigurationException, SAXException, IOException {

    Node root;
    if (source instanceof DOMSource) {
      root = ((DOMSource)source).getNode();
    } else if (source instanceof SAXSource) {
      InputSource inSource = ((SAXSource)source).getInputSource();
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = null;

      synchronized (dbf) {
        db = dbf.newDocumentBuilder();
      }

      Document doc = db.parse(inSource);
      root = (Node) doc.getDocumentElement();

    } else {
      throw new IllegalArgumentException(
        "Class type: " + source.getClass().getName());
    }

    return root;
  }

  private static KeyPair generateDSAKeyPair() throws NoSuchAlgorithmException {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
    kpg.initialize(1024, new SecureRandom());
    return kpg.generateKeyPair();
  }

  private static XMLSignature generateXMLSignature(KeyPair keypair) 
      throws NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        KeyException {
    XMLSignatureFactory sigFactory = XMLSignatureFactory.getInstance("DOM");
    Reference ref = sigFactory.newReference("#Body", 
      sigFactory.newDigestMethod(DigestMethod.SHA1, null));
    SignedInfo signedInfo = sigFactory.newSignedInfo(
      sigFactory.newCanonicalizationMethod(
      CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, 
      (C14NMethodParameterSpec) null),
      sigFactory.newSignatureMethod(SignatureMethod.DSA_SHA1, null),
      Collections.singletonList(ref));
    KeyInfoFactory kif = sigFactory.getKeyInfoFactory();
    KeyValue kv = kif.newKeyValue(keypair.getPublic());
    KeyInfo keyInfo = kif.newKeyInfo(Collections.singletonList(kv));

    return sigFactory.newXMLSignature(signedInfo, keyInfo);
  }

  private static void signTree(Node root, PrivateKey privateKey, XMLSignature sig) 
      throws MarshalException, XMLSignatureException {

    Element envelope = getFirstChildElement(root);
    Element header = getFirstChildElement(envelope);
    DOMSignContext sigContext = new DOMSignContext(privateKey, header);
    sigContext.putNamespacePrefix(XMLSignature.XMLNS, "ds");
    sigContext.setIdAttributeNS(getNextSiblingElement(header),
      "http://schemas.xmlsoap.org/soap/security/2000-12","id");
    sig.sign(sigContext);
  }

  private static boolean validateXMLSignature(
      PublicKey publicKey, Node root, XMLSignature sig)
        throws XMLSignatureException {

    Element envelope = getFirstChildElement(root);
    Element header = getFirstChildElement(envelope);
    Element sigElement = getFirstChildElement(header);
    DOMValidateContext valContext = new DOMValidateContext(publicKey, sigElement);
    valContext.setIdAttributeNS(getNextSiblingElement(header),
      "http://schemas.xmlsoap.org/soap/security/2000-12", "id");
    return sig.validate(valContext);
  }

  private static void dumpDocument(Node root) throws TransformerException {

    Console console = System.console();
    console.printf("%n");
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(new DOMSource(root), new StreamResult(console.writer()));
    console.printf("%n");
  }

  private static Element getFirstChildElement(Node node) {

    Node child = node.getFirstChild();
    while ((child != null) && (child.getNodeType() != Node.ELEMENT_NODE)) {
      child = child.getNextSibling();
    }
    return (Element) child;
  }

  public static Element getNextSiblingElement(Node node) {

    Node sibling = node.getNextSibling();
    while ((sibling != null) && (sibling.getNodeType() != Node.ELEMENT_NODE)) {
      sibling = sibling.getNextSibling();
    }
    return (Element) sibling;
  }
}

