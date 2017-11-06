package com.rumian.pea;

import com.rumian.pea.calculations.salesman.dynamic.DynamicSalesmanCalculator;
import com.rumian.pea.graph.model.entity.AdjacencyMatrixWithMeta;
import com.rumian.pea.parser.XMLParserImpl;
import io.vavr.control.Try;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class PeaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PeaApplication.class, args);
		String TEST_PATH = "C:\\Users\\zekori96\\Documents\\Projekt pea\\src\\test\\resources\\burma14.xml";//TODO: Get relative path.
		AdjacencyMatrixWithMeta matrix;
		XMLParserImpl xmlParser = new XMLParserImpl();
		matrix = Try.of(() -> xmlParser.getAdjacencyMatrix(new File(TEST_PATH))).get();
		int startVertex = 0;
		DynamicSalesmanCalculator calculator = new DynamicSalesmanCalculator(matrix.getAdjacencyMatrix(), startVertex);
		calculator.doCalculations();
		//TravelingSalesmanHeldKarp travelingSalesmanHeldKarp = new TravelingSalesmanHeldKarp();
		//travelingSalesmanHeldKarp.minCost(matrix.getAdjacencyMatrix());
	}
}
