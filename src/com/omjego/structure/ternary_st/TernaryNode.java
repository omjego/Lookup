package com.omjego.structure.ternary_st;

import com.omjego.word.Word;

public class TernaryNode {

    private Object word;                  //Word stored in the node.
    private char ch;
    private TernaryNode left, mid, right; // Three pointer nodes for < ch, == ch and > ch respec.
    private boolean isEnd;

    TernaryNode(char ch) {
        this.ch = ch;
    }

    public Object getWord() {
        return word;
    }

    public void setWord(Object word) {
        this.word = word;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public TernaryNode getLeft() {
        return left;
    }

    public void setLeft(TernaryNode left) {
        this.left = left;
    }

    public TernaryNode getMid() {
        return mid;
    }

    public void setMid(TernaryNode mid) {
        this.mid = mid;
    }

    public TernaryNode getRight() {
        return right;
    }

    public void setRight(TernaryNode right) {
        this.right = right;
    }
}
