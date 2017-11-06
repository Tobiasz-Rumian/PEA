package com.rumian.pea.utils;

import java.math.BigDecimal;

public class TimeTracker {
   private Long startTime;

   public void start() {
      startTime = System.nanoTime();
   }

   public BigDecimal getElapsedTime() {
      Long endTime = System.nanoTime();
      return BigDecimal.valueOf(endTime - startTime);
   }
}
