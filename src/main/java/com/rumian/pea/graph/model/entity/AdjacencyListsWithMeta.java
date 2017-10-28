package com.rumian.pea.graph.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class AdjacencyListsWithMeta {
   String name;
   String source;
   String description;
   Integer precision;
   Integer ignoredDigits;
   List<List<Double>> AdjacencyLists = new LinkedList<>();

   public void addToList(int x, int y, Double value){
      if(AdjacencyLists.size()<=x){
         List<Double> list = new LinkedList<>();
         list.add((double)y);
         list.add(value);
       AdjacencyLists.add(list);
      }else{
         AdjacencyLists.get(x).add((double)y);
         AdjacencyLists.get(x).add(value);
      }
   }
}
