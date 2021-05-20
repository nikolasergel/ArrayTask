package by.serhel.parser;

import by.serhel.composite.TextElement;

public abstract class AbstractParser {
    protected AbstractParser next;

    public AbstractParser() {
    }

    public AbstractParser(AbstractParser next) {
        if(next == null){
            this.next = DefaultParser.getInstance();
        }
        this.next = next;
    }

    public void setNext(AbstractParser next) {
        this.next = next;
    }

    public abstract void parse(String text, TextElement element);

    private static class DefaultParser extends AbstractParser {
        private static DefaultParser parser;

        private DefaultParser() {
        }

        public static AbstractParser getInstance(){
            if(parser == null){
                parser = new DefaultParser();
            }
            return parser;
        }

        @Override
        public void parse(String text, TextElement element) {
        }
    }
}
