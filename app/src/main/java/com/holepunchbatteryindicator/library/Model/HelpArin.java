package com.holepunchbatteryindicator.library.Model;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;

public class HelpArin {
    public final boolean IS_CHECK = true;
    public final boolean IS_HEADER = true;
    public final boolean IS_HIDE = true;
    public final int NO_ICON = 0;
    public final String ONLY_SEPARATOR = "";
    private HelpItem helpItem;
    private List<HelpItem> listHelp = new ArrayList();

    private void newHelpItem() {
        this.helpItem = new HelpItem();
    }

    public void add(String str) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void addNoCheck(String str) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void addHide(String str) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void addHideNoCheck(String str) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void add(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void add(String str, Drawable drawable) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(drawable);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void addNoCheck(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void addHide(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void addHideNoCheck(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void add(String str, int i, int i2) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCheck(true);
        this.helpItem.setCounter(i2);
        this.listHelp.add(this.helpItem);
    }

    public void add(String str, Drawable drawable, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(drawable);
        this.helpItem.setCheck(true);
        this.helpItem.setCounter(i);
        this.listHelp.add(this.helpItem);
    }

    public void addNoCheck(String str, int i, int i2) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCheck(false);
        this.helpItem.setCounter(i2);
        this.listHelp.add(this.helpItem);
    }

    public void addHide(String str, int i, int i2) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCounter(i2);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(true);
        this.listHelp.add(this.helpItem);
    }

    public void addHideNoCheck(String str, int i, int i2) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setCounter(i2);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void addSubHeader(String str) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(0);
        this.helpItem.setHeader(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void addSubHeader(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setCounter(i);
        this.helpItem.setIcon(0);
        this.helpItem.setCheck(false);
        this.helpItem.setHeader(true);
        this.listHelp.add(this.helpItem);
    }

    public void addSeparator() {
        newHelpItem();
        this.helpItem.setName("");
        this.helpItem.setIcon(0);
        this.helpItem.setHeader(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public void addSubHeaderHide(String str, int i) {
        newHelpItem();
        this.helpItem.setName(str);
        this.helpItem.setIcon(i);
        this.helpItem.setHeader(true);
        this.helpItem.setHide(true);
        this.helpItem.setCheck(false);
        this.listHelp.add(this.helpItem);
    }

    public HelpItem get(int i) {
        return this.listHelp.get(i);
    }

    public List<HelpItem> getHelp() {
        return this.listHelp;
    }

    public int getCount() {
        return this.listHelp.size();
    }
}
