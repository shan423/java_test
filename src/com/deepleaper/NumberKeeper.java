package com.deepleaper;

public class NumberKeeper {

    private int number;

    public NumberKeeper(int num) {
        this.number = num;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEnd()
    {
        return this.number >= Main.target || this.number < 0;
    }

    public void increase() {
        if (isEnd())
        {
            System.out.println(this.number);
            return;
        }
        ++this.number;
    }
}
