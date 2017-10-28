package com.rumian.pea.parser;

import com.rumian.pea.graph.model.entity.AdjacencyListsWithMeta;
import io.vavr.control.Try;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class XMLParserImplTest {
   private static final String TEST_PATH = "C:\\Users\\zekori96\\Documents\\Projekt pea\\src\\test\\resources\\burma14.xml";//TODO: Get relative path.
   private AdjacencyListsWithMeta list;

   @BeforeEach
   void setUp() {
      XMLParserImpl xmlParser = new XMLParserImpl();
      list = Try.of(()->xmlParser.getAdjacencyLists(new File(TEST_PATH))).get();
   }

   @Test
   @DisplayName("Testing meta")
   void testMetaData() {
      assertThat(list.getName()).isEqualTo("burma14");
      assertThat(list.getDescription()).isEqualTo("14-Staedte in Burma (Zaw Win)");
      assertThat(list.getIgnoredDigits()).isEqualTo(5);
      assertThat(list.getPrecision()).isEqualTo(15);
      assertThat(list.getSource()).isEqualTo("TSPLIB");
   }

   @Test
   @DisplayName("Testing data")
   void testAdjacencyList() {
      List<List<Double>> adjacencyList = list.getAdjacencyLists();
      assertThat(adjacencyList.size()).isEqualTo(14);
      assertThat(adjacencyList.get(0).get(1)).isEqualTo(153);
   }
}