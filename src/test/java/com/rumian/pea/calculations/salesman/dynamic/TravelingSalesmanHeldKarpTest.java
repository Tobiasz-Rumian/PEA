package com.rumian.pea.calculations.salesman.dynamic;

import com.rumian.pea.graph.model.entity.AdjacencyMatrixWithMeta;
import com.rumian.pea.parser.XMLParserImpl;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class TravelingSalesmanHeldKarpTest {
   private static final String TEST_PATH = "C:\\Users\\zekori96\\Documents\\Projekt pea\\src\\test\\resources\\burma14.xml";//TODO: Get relative path.
   private AdjacencyMatrixWithMeta matrix;

   @BeforeEach
   void setUp() {
      XMLParserImpl xmlParser = new XMLParserImpl();
      matrix = Try.of(() -> xmlParser.getAdjacencyMatrix(new File(TEST_PATH))).get();
   }

   @Test
   void minCost() {
      TravelingSalesmanHeldKarp travelingSalesmanHeldKarp = new TravelingSalesmanHeldKarp();
      travelingSalesmanHeldKarp.minCost(matrix.getAdjacencyMatrix());
   }

}