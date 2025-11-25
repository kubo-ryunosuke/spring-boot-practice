package com.example.backend.application;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class TaxService {
    
    // 消費税率（本来は設定ファイルから読むが今回は固定）
    private final double taxRate = 0.1;

    @PostConstruct
    public void init() {
        System.out.println("★ [LifeCycle] TaxCalculator: 初期化処理 (PostConstruct)");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("★ [LifeCycle] TaxCalculator: 終了処理 (PreDestroy)");
    }

    public int calculateTax(int amount) {
        return (int) (amount * taxRate);
    }
}
