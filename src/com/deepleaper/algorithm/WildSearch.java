package com.deepleaper.algorithm;

public class WildSearch {

    static class node {
        public int x;
        public int y;
        public node f;
        public int s;
    }

    public static node up(node input) {
        node result = new node();
        result.y = input.y - 1;
        result.x = input.x;
        result.f = input;
        result.s = input.s + 1;
        return result;
    }

    public static node down(node input) {
        node result = new node();
        result.y = input.y + 1;
        result.x = input.x;
        result.f = input;
        result.s = input.s + 1;
        return result;
    }

    public static node left(node input) {
        node result = new node();
        result.x = input.x - 1;
        result.y = input.y;
        result.f = input;
        result.s = input.s + 1;
        return result;
    }

    public static node right(node input) {
        node result = new node();
        result.x = input.x + 1;
        result.y = input.y;
        result.f = input;
        result.s = input.s + 1;
        return result;
    }

    public static void main(String[] args) {
        //5*4
        int[][] map = new int[][]{
                {
                        0, 0, 1, 0
                },
                {
                        0, 0, 0, 0
                },
                {
                        0, 0, 1, 0
                },
                {
                        0, 1, 0, 0
                },
                {
                        0, 0, 0, 1
                }
        };

        int m = 50, n = 50;
        node[] nodes = new node[m * n];

        int targetX = 3, targetY = 2;
        int startX = 0, startY = 0;
        int head = 0, tail = 0;

        int[][] book = new int[5][4];

        node start = new node();
        start.x = startX;
        start.y = startY;
        //起始点作为第一个元素
        nodes[0] = start;
        tail++;

        while (head < tail) {
            node headNode = nodes[head];
            node next = null;
            for (int d = 0; d <= 3; d++) {
                switch (d) {
                    case 0:
                        next = up(headNode);
                        break;
                    case 1:
                        next = down(headNode);
                        break;
                    case 2:
                        next = left(headNode);
                        break;
                    case 3:
                        next = right(headNode);
                        break;
                }

                //越界 or 已走过则跳过
                if (next.x < 0 || next.y < 0 || next.x >= m || next.y >= n || next.x >= book.length || next.y >= book[0].length || map[next.x][next.y] == 1)
                    continue;

                book[next.x][next.y] = 1;
                next.f = headNode;
                next.s = headNode.s + 1;
                //存储一个合法路径队列
                nodes[tail] = next;
                //指向队列下一个空位 用于填充
                tail++;

                if (next.x == targetX && next.y == targetY) {
                    System.out.println("step " + next.s);
                    return;
                }
            }
            //一个位置上下左右均尝试过则对nodes步进一个单位
            head++;
        }
    }
}
