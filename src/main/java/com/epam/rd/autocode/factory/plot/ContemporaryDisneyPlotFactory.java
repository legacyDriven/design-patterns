package com.epam.rd.autocode.factory.plot;

public class ContemporaryDisneyPlotFactory implements PlotFactory {
    private static final String[] contemporaryDisneyPlotData = {
            " feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - ",
            ". ",
            " stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny ",
            " restore the spirit and ",
            " overcomes the crisis and gains gratitude and respect"
    };
    
    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;


    public ContemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }
    @Override
    public Plot plot() {
        return new PlotImpl(hero.name()
                + contemporaryDisneyPlotData[0]
                + epicCrisis.name()
                + contemporaryDisneyPlotData[1]
                + hero.name()
                + contemporaryDisneyPlotData[2]
                + funnyFriend.name()
                + contemporaryDisneyPlotData[3]
                + hero.name()
                + contemporaryDisneyPlotData[4]
        );
    }

    public static void main(String[] args) {
        ContemporaryDisneyPlotFactory fac  = new ContemporaryDisneyPlotFactory(() -> "raz", ()-> "dwa", ()-> "trzy");
        System.out.println(fac.plot().toString());
    }
}
