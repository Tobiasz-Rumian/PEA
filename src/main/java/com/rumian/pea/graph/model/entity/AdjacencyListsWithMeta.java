package com.rumian.pea.graph.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class AdjacencyListsWithMeta {
   private String name;
   private String source;
   private String description;
   private Integer precision;
   private Integer ignoredDigits;
   private List<List<Double>> adjacencyLists = new LinkedList<>();

   public void addToList(int x, int y, Double value){
      if(adjacencyLists.size()<=x){
         List<Double> list = new LinkedList<>();
         list.add((double)y);
         list.add(value);
       adjacencyLists.add(list);
      }else{
         adjacencyLists.get(x).add((double)y);
         adjacencyLists.get(x).add(value);
      }
   }

   public AdjacencyMatrixWithMeta toMatrix(){
      AdjacencyMatrixWithMeta matrix = new AdjacencyMatrixWithMeta(adjacencyLists.size());
      matrix.setDescription(description);
      matrix.setName(name);
      matrix.setSource(source);
      matrix.setPrecision(precision);
      matrix.setIgnoredDigits(ignoredDigits);

      double[][] adjacencyMatrix = new double[adjacencyLists.size()][adjacencyLists.size()];
      for (int x=0;x<adjacencyLists.size();x++){
         for (int y=0;y<adjacencyLists.size();y++){
            adjacencyMatrix[x][y] = Integer.MAX_VALUE;
         }
      }
      int i =0;
      for (List<Double> doubleList:adjacencyLists) {
         for (int j=0;j<doubleList.size();j++){
            matrix.addToList(i, doubleList.get(j).intValue(),doubleList.get(++j));
         }
         i++;
      }
      return matrix;
   }
}
