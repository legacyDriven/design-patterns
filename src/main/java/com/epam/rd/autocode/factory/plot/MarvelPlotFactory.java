package com.epam.rd.autocode.factory.plot;

public class MarvelPlotFactory implements PlotFactory{

    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    private final static String heroModifier = "brave ";
    private final static String[] marvelPlotData = {
            " threatens the world. But ",
            " on guard. So, no way that intrigues of ",
            " overcome the willpower of inflexible heroes"
    };

    public MarvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public Plot plot() {
        return new PlotImpl(epicCrisis.name() + marvelPlotData[0] + addHeroes(heroes) + marvelPlotData[1] + villain.name() + marvelPlotData[2]);
    }

    private String addHeroes(Character[] heroes){
        if(heroes.length==1)
            return heroModifier + heroes[0].name();
        else { StringBuilder sb = new StringBuilder();
            for(int i = 0; i < heroes.length; i++){
                if(i != heroes.length-1) sb.append(heroModifier).append(heroes[i].name()).append(", ");
                else sb.append(heroModifier).append(heroes[i].name());
            }
            return sb.toString();
        }
    }
}
