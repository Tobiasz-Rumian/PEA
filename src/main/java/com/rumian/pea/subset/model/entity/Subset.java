package com.rumian.pea.subset.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Subset {
   private Set<Integer> visitedVertexes= new HashSet<>();
   private double cost=0;
   private Integer firstVertex;
   private Integer lastVertex;

   public Subset(Set<Integer> visitedVertexes, Integer firstVertex, Integer lastVertex) {
      this.visitedVertexes = visitedVertexes;
      this.firstVertex = firstVertex;
      this.lastVertex = lastVertex;
   }
   public void printVertexes(){
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("[ ");
      for (Integer i:visitedVertexes) {
         stringBuilder.append(" , "+i);
      }
      stringBuilder.append(" ]");
      stringBuilder.append("First vertex: "+firstVertex+" ");
      stringBuilder.append("Last vertex: "+lastVertex+" ");
      stringBuilder.append("Cost: "+cost+" ");

      System.out.println(stringBuilder.toString());
   }
}
