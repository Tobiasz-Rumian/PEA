package com.rumian.pea.parser;

import com.rumian.pea.graph.model.entity.AdjacencyListsWithMeta;
import com.rumian.pea.graph.model.entity.AdjacencyMatrixWithMeta;

import java.io.File;

public interface XMLParser {
   AdjacencyMatrixWithMeta getAdjacencyMatrix(File xmlFile) throws Exception;
}
