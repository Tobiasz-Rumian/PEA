package com.rumian.pea.parser;

import com.rumian.pea.graph.model.entity.AdjacencyListsWithMeta;

import java.io.File;

public interface XMLParser {
   AdjacencyListsWithMeta getAdjacencyLists(File xmlFile) throws Exception;
}
