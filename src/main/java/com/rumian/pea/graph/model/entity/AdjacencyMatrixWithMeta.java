package com.rumian.pea.graph.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AdjacencyMatrixWithMeta {
   private String name;
   private String source;
   private String description;
   private Integer precision;
   private Integer ignoredDigits;
   private double[][] adjacencyMatrix;

   public AdjacencyMatrixWithMeta(int size) {
      this.adjacencyMatrix = new double[size][size];
   }

   public void addToList(int x, int y, Double value){
      adjacencyMatrix[x][y] = value;
   }
}
