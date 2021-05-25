package by.serhel;

import by.serhel.composite.AbstractElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;
import by.serhel.exception.CustomFileException;
import by.serhel.parser.*;
import by.serhel.reader.CustomReader;
import by.serhel.service.TextElementService;
import by.serhel.service.impl.TextElementServiceImpl;

public class Main {
    public static void main(String[] args) throws CustomFileException {
        AbstractParser parser = new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser(new SymbolParser(null)))));
        String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n\tIt is a established fact that a reader will be of a page when looking at its layout...";
//
        TextElement element = new TextElement();
//        text = CustomReader.readText("text.txt");
        text = "LOLO olo koko, ko. O a ui.";
        parser.parse(text, element);

        TextElementServiceImpl service = new TextElementServiceImpl();
        System.out.println(service.countVowelsInSentence(element));
        element = new TextElement();
        parser.parse("", element);
        System.out.println(service.countVowelsInSentence(element));
//        System.out.println(element.toString());
//        System.out.println(element.toString().equals(text));
//        print(element);

//        Files.write(Paths.get("text.txt"), element.toString().getBytes());
    }

    private static void print(AbstractElement element){
        if(element instanceof TextElement){
            element = (TextElement)element;
            for(AbstractElement el : element.getChild()){
                if(el.getType().equals(TextElementType.LEXEME)){
                    System.out.println(el.toString());
                } else{
                    print(el);
                }
            }
        }
    }
}
