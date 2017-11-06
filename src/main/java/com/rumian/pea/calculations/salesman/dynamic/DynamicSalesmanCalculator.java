package com.rumian.pea.calculations.salesman.dynamic;

import com.rumian.pea.subset.model.entity.Subset;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DynamicSalesmanCalculator {
   private List<Subset> subsets;
   private double[][] adjacencyMatrix;
   private int startVertex;

   public DynamicSalesmanCalculator(double[][] adjacencyMatrix, int startVertex) {
      this.adjacencyMatrix = adjacencyMatrix;
      this.startVertex = startVertex;
   }

   public void doCalculations(){
      Set<Integer> vertexSet = new HashSet<>();
      for (int i=0;i<adjacencyMatrix.length;i++) vertexSet.add(i);
      vertexSet.remove(startVertex);
      subsets = powerSet(vertexSet);
      subsets.forEach(subset -> {
         if(subset.getVisitedVertexes().size()==adjacencyMatrix.length) subset.setCost(subset.getCost()+adjacencyMatrix[subset.getFirstVertex()][subset.getLastVertex()]);
      });
      subsets.sort(new SubsetSizeComparator());
      calculateCost();
      subsets.forEach(Subset::printVertexes);
   }

   private static class SubsetSizeComparator implements Comparator<Subset> {
      @Override
      public int compare(Subset o1, Subset o2) {
         return o1.getVisitedVertexes().size() - o2.getVisitedVertexes().size();
      }
   }

   private void calculateCost(){
      for (Subset s:subsets) {

      }
   }



   private List<Subset> powerSet(Set<Integer> vertexSet) {
      List<Subset> sets = new LinkedList<>();
      if (vertexSet.isEmpty()) {
         Set<Integer> newSet = new HashSet<>();
         newSet.add(startVertex);
         Subset subset = new Subset(newSet,startVertex,startVertex);
         subset.setCost(0);
         sets.add(subset);
         return sets;
      }
      List<Integer> list = new ArrayList<>(vertexSet);
      Integer head = list.get(0);
      Set<Integer> rest = new HashSet<>(list.subList(1, list.size()));
      for (Subset set : powerSet(rest)) {
         if(set.getVisitedVertexes().size()==1){
            Subset subset = new Subset(new HashSet<>(),head,set.getLastVertex());
            subset.getVisitedVertexes().add(head);
            subset.getVisitedVertexes().addAll(set.getVisitedVertexes());
            subset.setCost(adjacencyMatrix[head][set.getLastVertex()]);
            sets.add(subset);
            sets.add(set);
         } else{
            Subset subset = new Subset(new HashSet<>(),head,set.getLastVertex());
            double fromFirstVertexCost = adjacencyMatrix[head][set.getFirstVertex()]+set.getCost();
            double fromLastVertexCost = adjacencyMatrix[set.getLastVertex()][head]+set.getCost();
            if(fromFirstVertexCost>fromLastVertexCost){
               subset.setFirstVertex(set.getFirstVertex());
               subset.setLastVertex(head);
            }
            subset.setCost(Math.min(fromFirstVertexCost,fromLastVertexCost));
            subset.getVisitedVertexes().add(head);
            subset.getVisitedVertexes().addAll(set.getVisitedVertexes());
            sets.add(subset);
            sets.add(set);
         }

      }
      return sets;
   }

}
