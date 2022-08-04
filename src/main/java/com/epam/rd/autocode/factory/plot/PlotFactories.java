package com.epam.rd.autocode.factory.plot;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        if(hero == null || beloved == null || villain == null) throw new UnsupportedOperationException();
        return new ClassicDisneyPlotFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        if(hero == null || epicCrisis == null || funnyFriend == null) throw  new UnsupportedOperationException();
        return new ContemporaryDisneyPlotFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        if(heroes == null || epicCrisis == null || villain == null) throw new UnsupportedOperationException();
        return new MarvelPlotFactory(heroes, epicCrisis, villain);
    }
}
