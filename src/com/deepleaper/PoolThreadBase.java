package com.deepleaper;

public class PoolThreadBase extends Thread {

    private NumberKeeper begin;

    public PoolThreadBase(NumberKeeper begin) {
        this.begin = begin;
    }

    @Override
    public void run() {
        while(!this.begin.isEnd()) {
            this.begin.increase();
        }
    }
}
