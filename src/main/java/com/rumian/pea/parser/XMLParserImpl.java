package com.rumian.pea.parser;

import com.rumian.pea.graph.model.entity.AdjacencyListsWithMeta;
import com.rumian.pea.graph.model.entity.AdjacencyMatrixWithMeta;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Service
public class XMLParserImpl implements XMLParser {

   private static final String VERTEX = "vertex";
   private static final String EDGE = "edge";
   private static final String COST = "cost";

   public AdjacencyMatrixWithMeta getAdjacencyMatrix(File xmlFile)throws Exception{
      return getAdjacencyLists(xmlFile).toMatrix();
   }


   private AdjacencyListsWithMeta getAdjacencyLists(File xmlFile) throws Exception {
      AdjacencyListsWithMeta list = new AdjacencyListsWithMeta();
      Document doc = createDocument(xmlFile);
      setListMeta(doc, list);

      NodeList vertexList = doc.getElementsByTagName(VERTEX);
      for (int currentVertexNumber = 0; currentVertexNumber < vertexList.getLength(); currentVertexNumber++) {
         processVertex(list, vertexList, currentVertexNumber);
      }
      return list;
   }

   private void processVertex(AdjacencyListsWithMeta list, NodeList vertexList, int currentVertexNumber) {
      Node vertexNode = vertexList.item(currentVertexNumber);
      if (isElement(vertexNode)) {
         NodeList edgeList = ((Element) vertexNode).getElementsByTagName(EDGE);
         for (int currentEdgeNumber = 0; currentEdgeNumber < edgeList.getLength(); currentEdgeNumber++) {
            processEdge(list, currentVertexNumber, edgeList, currentEdgeNumber);
         }
      }
   }

   private void processEdge(AdjacencyListsWithMeta list, int currentVertexNumber, NodeList edgeList, int currentEdgeNumber) {
      Node edgeNode = edgeList.item(currentEdgeNumber);
      if (isElement(edgeNode)) {
         Double cost = Double.parseDouble(((Element) edgeNode).getAttribute(COST));
         Integer destinationVertexNumber = Integer.parseInt(edgeNode.getTextContent());
         list.addToList(currentVertexNumber, destinationVertexNumber, cost);
      }
   }

   private boolean isElement(Node edgeNode) {
      return edgeNode.getNodeType() == Node.ELEMENT_NODE;
   }

   private Document createDocument(File xmlFile) throws ParserConfigurationException, SAXException, IOException {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(xmlFile);
      doc.getDocumentElement().normalize();
      return doc;
   }

   private void setListMeta(Document doc, AdjacencyListsWithMeta list) {
      setListName(doc, list);
      setListSource(doc, list);
      setListDescription(doc, list);
      setListPrecision(doc, list);
      setListIgnoredDigits(doc, list);
   }

   private void setListIgnoredDigits(Document doc, AdjacencyListsWithMeta list) {
      list.setIgnoredDigits(Integer.parseInt(doc.getElementsByTagName("ignoredDigits").item(0).getTextContent()));
   }

   private void setListPrecision(Document doc, AdjacencyListsWithMeta list) {
      list.setPrecision(Integer.parseInt(doc.getElementsByTagName("doublePrecision").item(0).getTextContent()));
   }

   private void setListDescription(Document doc, AdjacencyListsWithMeta list) {
      list.setDescription(doc.getElementsByTagName("description").item(0).getTextContent());
   }

   private void setListSource(Document doc, AdjacencyListsWithMeta list) {
      list.setSource(doc.getElementsByTagName("source").item(0).getTextContent());
   }

   private void setListName(Document doc, AdjacencyListsWithMeta list) {
      list.setName(doc.getElementsByTagName("name").item(0).getTextContent());
   }
}
