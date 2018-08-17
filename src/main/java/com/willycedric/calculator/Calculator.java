package com.willycedric.calculator;

import org.springframework.stereotype.Service;
/**
 *Calulator business logic
 */
@Service
public class Calculator {
     int sum(int a, int b) {
          return a + b;
     }
}