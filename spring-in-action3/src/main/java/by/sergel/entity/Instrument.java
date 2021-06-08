package by.sergel.entity;

import by.sergel.exception.PerformanceException;

public interface Instrument {
    void play();

    class Juggler implements Performer{
        private int beanBags = 3;

        public Juggler() {
        }

        public Juggler(int beanBags) {
            this.beanBags = beanBags;
        }

        @Override
        public void perform(){
            System.out.println("JUGGLING " + beanBags + " BEANBAGS!!!");
        }
    }

    class PoeticJuggler extends Juggler {
        private Poem poem;

        public PoeticJuggler(Poem poem) {
            this.poem = poem;
        }

        public PoeticJuggler(int beanBags, Poem poem) {
            super(beanBags);
            this.poem = poem;
        }

        @Override
        public void perform(){
            super.perform();
            System.out.println("While reciting...");
            poem.recite();
        }
    }

    class Sonnet29 implements Poem {
        private static final String[] LINES = {
                "Когда в раздоре с миром и судьбой,",
                "Припомнив годы, полные невзгод,",
                "Тревожу я бесплодною мольбой",
                "Глухой и равнодушный небосвод",
                "И, жалуясь на горестный удел,",
                "Готов меняться жребием своим",
                "С тем, кто в искусстве больше преуспел,",
                "Богат надеждой и людьми любим, -",
                "Тогда, внезапно вспомнив о тебе,",
                "Я малодушье жалкое кляну,",
                "И жаворонком, вопреки судьбе,",
                "Моя душа несется в вышину.",
                "С твоей любовью, с памятью о ней",
                "Всех королей на свете я сильней."
        };

        @Override
        public void recite(){
            for(String line : LINES){
                System.out.println(line);
            }
        }
    }

    class Instrumentalist implements Performer{
        private String song;
        private Instrument instrument;

        @Override
        public void perform() throws PerformanceException {
            System.out.println("Playing " + song + " :");
            instrument.play();
        }

        public void setSong(String song) {
            this.song = song;
        }

        public void setInstrument(Instrument instrument) {
            this.instrument = instrument;
        }
    }
}
