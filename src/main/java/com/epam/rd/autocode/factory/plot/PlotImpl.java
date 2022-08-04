package com.epam.rd.autocode.factory.plot;

public class PlotImpl implements Plot {

    private final String plot;

    public PlotImpl(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return plot;
    }
}
