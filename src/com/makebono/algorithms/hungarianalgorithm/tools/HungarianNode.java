package com.makebono.algorithms.hungarianalgorithm.tools;

import java.util.ArrayList;

/** 
 * @ClassName: HungarianNode 
 * @Description: HungarianNode
 * @author makebono
 * @date 2017年11月21日 上午10:40:27 
 *  
 */
public class HungarianNode {
    private final int m;
    private final int n;
    private final int data;
    private HungarianNode parent;
    private final ArrayList<HungarianNode> children;
    private boolean valid;

    public HungarianNode(final int m, final int n, final int data) {
        this.m = m;
        this.n = n;
        this.data = data;
        this.parent = null;
        this.children = new ArrayList<HungarianNode>();
        this.valid = false;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public boolean isValid() {
        return this.valid;
    }

    public int getData() {
        return this.data;
    }

    public HungarianNode parent() {
        return this.parent;
    }

    public void addChild(final HungarianNode newChild) {
        this.children.add(newChild);
        newChild.setParent(this);
    }

    public void addChild(final int m, final int n, final int child) {
        final HungarianNode newChild = new HungarianNode(m, n, child);
        this.children.add(newChild);
        newChild.setParent(this);
    }

    public ArrayList<HungarianNode> getChild() {
        return this.children;
    }

    public void setValid() {
        this.valid = true;
    }

    public void setParent(final HungarianNode parent) {
        this.parent = parent;
    }
}
