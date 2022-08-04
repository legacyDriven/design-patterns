package com.epam.rd.autocode.factory.plot;

public class ClassicDisneyPlotFactory implements PlotFactory {

    private static final String[] classicDisneyPlotData = {" is great. ", " and ",
            " love each other. ", " interferes with their happiness but loses in the end."};
    private final Character hero;
    private final Character beloved;
    private final Character villain;


    public ClassicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }
    @Override
    public Plot plot() {
        return new PlotImpl(hero.name()
                +classicDisneyPlotData[0]
                + hero.name()
                + classicDisneyPlotData[1]
                + beloved.name()
                + classicDisneyPlotData[2]
                + villain.name()
                + classicDisneyPlotData[3]);
    }
}
