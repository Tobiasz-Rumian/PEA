package com.rumian.pea.parser;

import com.rumian.pea.graph.model.entity.AdjacencyMatrixWithMeta;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class XMLParserImplTest {
   private static final String TEST_PATH = "C:\\Users\\zekori96\\Documents\\Projekt pea\\src\\test\\resources\\burma14.xml";//TODO: Get relative path.
   private AdjacencyMatrixWithMeta matrix;

   @BeforeEach
   void setUp() {
      XMLParserImpl xmlParser = new XMLParserImpl();
      matrix = Try.of(() -> xmlParser.getAdjacencyMatrix(new File(TEST_PATH))).get();
   }

   @Test
   @DisplayName("Testing meta")
   void testMetaData() {
      assertThat(matrix.getName()).isEqualTo("burma14");
      assertThat(matrix.getDescription()).isEqualTo("14-Staedte in Burma (Zaw Win)");
      assertThat(matrix.getIgnoredDigits()).isEqualTo(5);
      assertThat(matrix.getPrecision()).isEqualTo(15);
      assertThat(matrix.getSource()).isEqualTo("TSPLIB");
   }

   @Test
   @DisplayName("Testing data")
   void testAdjacencyList() {
      double[][] matrix = this.matrix.getAdjacencyMatrix();
      assertThat(matrix.length).isEqualTo(14);
      assertThat(matrix[0][1]).isEqualTo(153);
   }
}