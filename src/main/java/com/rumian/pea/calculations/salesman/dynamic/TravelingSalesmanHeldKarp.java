package com.rumian.pea.calculations.salesman.dynamic;

import com.rumian.pea.utils.TimeTracker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class TravelingSalesmanHeldKarp {

   private static class Index {
      int currentVertex;
      List<Integer> vertexSet;

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         }
         if (o == null || getClass() != o.getClass()) {
            return false;
         }

         Index index = (Index) o;

         return currentVertex == index.currentVertex
                 && !(vertexSet != null
                                 ? !vertexSet.equals(index.vertexSet)
                                 : index.vertexSet != null);
      }

      @Override
      public int hashCode() {
         int result = currentVertex;
         result = 31 * result + (vertexSet != null ? vertexSet.hashCode() : 0);
         return result;
      }

      private static Index createIndex(int vertex, List<Integer> vertexSet) {
         Index i = new Index();
         i.currentVertex = vertex;
         i.vertexSet = vertexSet;
         return i;
      }
   }

   private static class ListSizeComparator implements Comparator<List<Integer>> {
      @Override
      public int compare(List<Integer> o1, List<Integer> o2) {
         return o1.size() - o2.size();
      }
   }

   public double minCost(double[][] distance) {
      TimeTracker timeTracker = new TimeTracker();
      timeTracker.start();
      //stores intermediate values in map
      Map<Index, Double> minCostDP = new HashMap<>();
      Map<Index, Integer> parent = new HashMap<>();

      List<List<Integer>> allSets = generateCombination(distance.length - 1);
      System.out.println("generated combinations total time: " + timeTracker.getElapsedTime().divide(BigDecimal.valueOf(1000000000d)));
      for (List<Integer> set : allSets) {
         for (int currentVertex = 1; currentVertex < distance.length; currentVertex++) {
            if (set.contains(currentVertex)) continue;
            Index index = Index.createIndex(currentVertex, set);
            double minCost = Double.MAX_VALUE;
            int minPrevVertex = 0;
            for (int prevVertex : set) {
               double cost = distance[prevVertex][currentVertex] + getCost(set, prevVertex, minCostDP);
               if (cost < minCost) {
                  minCost = cost;
                  minPrevVertex = prevVertex;
               }
            }
            //this happens for empty subset
            if (set.size() == 0)  minCost = distance[0][currentVertex];
            minCostDP.put(index, minCost);
            parent.put(index, minPrevVertex);
         }
      }
      System.out.println("calculated sets total time: " + timeTracker.getElapsedTime().divide(BigDecimal.valueOf(1000000000d)));
      List<Integer> set = new LinkedList<>();
      for (int i = 1; i < distance.length; i++) {
         set.add(i);
      }
      double min = Integer.MAX_VALUE;
      int prevVertex = -1;
      for (int k : set) {
         double cost = distance[k][0] + getCost(set, k, minCostDP);
         if (cost < min) {
            min = cost;
            prevVertex = k;
         }
      }

      parent.put(Index.createIndex(0, set), prevVertex);
      printTour(parent, distance.length);
      System.out.println("total cost: " + min);
      System.out.println("total time: " + timeTracker.getElapsedTime().divide(BigDecimal.valueOf(1000000000d)));
      return min;
   }

   private void printTour(Map<Index, Integer> parent, int totalVertices) {
      List<Integer> set = new LinkedList<>();
      for (int i = 0; i < totalVertices; i++) {
         set.add(i);
      }
      Integer start = 0;
      Deque<Integer> stack = new LinkedList<>();
      while (true) {
         stack.push(start);
         set.remove(start);
         start = parent.get(Index.createIndex(start, set));
         if (start == null) {
            break;
         }
      }
      StringJoiner joiner = new StringJoiner("->");
      stack.forEach(v -> joiner.add(String.valueOf(v)));
      System.out.println("\nTSP tour");
      System.out.println(joiner.toString());
   }

   private double getCost(List<Integer> set, int prevVertex, Map<Index, Double> minCostDP) {
      set.remove((Object)prevVertex);
      Index index = Index.createIndex(prevVertex, set);
      double cost = minCostDP.get(index);
      set.add(prevVertex);
      return cost;
   }

   private List<List<Integer>> generateCombination(int arraySize) {
      int input[] = new int[arraySize];
      for (int i = 0; i < input.length; i++) input[i] = i + 1;
      List<List<Integer>> allSets = new LinkedList<>();
      int result[] = new int[input.length];
      generateCombination(input, 0, 0, allSets, result);
      allSets.sort(new ListSizeComparator());
      return allSets;
   }

   private void generateCombination(int input[], int start, int pos, List<List<Integer>> allSets, int result[]) {
      if (pos == input.length) return;
      List<Integer> set = createSet(result, pos);
      allSets.add(set);
      for (int i = start; i < input.length; i++) {
         result[pos] = input[i];
         generateCombination(input, i + 1, pos + 1, allSets, result);
      }
   }

   private static List<Integer> createSet(int input[], int pos) {
      if (pos == 0) return new ArrayList<>();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < pos; i++) {
         list.add(input[i]);
      }
      return list;
   }
}
