import java.io.*;
import java.util.TreeMap;

class MyReader {

    /*Мне показалось удобнее будет если сделать класс в который будем передавать файл
    и этот класс будет выполнять нужные операции для выполнения домашнего задания
    Мы создаем экземпляр этого класса и при создании экземпляра пы пробегаем по файлу
    и заполняем TreeMap words всеми словами которые есть в файле.
    И методом getCommonWords() находим слова которые повторяются больше всего*/

    private TreeMap<String, Integer> words;
    private TreeMap<String, Integer> commonWords;
    private File file;

    MyReader(File file) throws IOException {
        words = new TreeMap<>();
        this.file = file;

        seeFile();
        getCommonWords();
    }

    private void seeFile() throws IOException {                                                                         //Метод который просматривает файл

        StringBuilder word = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            int symbol = reader.read();
            while (symbol != -1) {
                if (Character.isLetter(symbol)) {                                                                       //Читаем символ и если это буква то добавляем ее в слово
                    word.append((char) symbol);
                } else {                                                                                                //Как только символ это не буква то мы добавляем слово в коллекцию отдельным методом и обнуляем слово
                    addWord(word.toString());                                                                           //Мне показалось если тут будут еще два if условия то будет сложнее разобраться поэтому вынес в отдельный метод
                    word = new StringBuilder();
                }
                symbol = reader.read();                                                                                 //читаем следующий символ
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addWord(String word) {                                                                                 //Отдельный метод для добавления слова в коллекцию
        Integer count;
        if(!word.equals("")) {                                                                                          //Если слово не пустое то мы пытаемся добавляем слово в коллекцию увеличивая count
            count = words.get(word.toLowerCase());
            if (count == null) {
                count = 0;
            }
            words.put(word.toLowerCase(), ++count);
        }                                                                                                               //Если слово пустое то значит встретились два символа(не буквы) друг за другом
    }

    private void writeOnScreen(TreeMap<String, Integer> collection) {                                                   //Этот метод выводит нужную коллекцию на экран
        for (String k : collection.keySet()) {
            System.out.println(words.get(k) + " - " + k);
        }
    }

    void printOnScreen() {                                                                                              //Этим методом выводим на экран ответ домашнего задания
        System.out.println("Кол-во повторений каждого слова в файле: ");
        writeOnScreen(words);                                                                                           //Выводим список всех слов
        System.out.println();
        System.out.println("Слова которые повторяются чаще всего");
        writeOnScreen(commonWords);                                                                                     //Выводим список часто повторяемых слов
        System.out.println();
    }

    private void getCommonWords() {                                                                                     //Этот метод заполняет коллекцию словами которые чаще всего повторяются
        commonWords = new TreeMap<>();
        int count = 1;                                                                                                  //Переменная в которой записанно максимальное кол-во повторений
        for (String key : words.keySet()) {
            if (count < words.get(key)) {                                                                               //Если попадается слово которое встречалось чаще чем максимальное кол-во повторений до этого
                commonWords.clear();                                                                                    //То вы обнуляем коллекцию которую хотим вернуть
                count = words.get(key);                                                                                 //Присваиваем новое максимальное кол-во повторений в переменную
                commonWords.put(key, count);                                                                            //И кладем первое слово которое больше всего повторяется
            } else if (count == words.get(key)) {                                                                       //Если встречается слово которе встречается так же часто как максимальное кол-во до этого то мы тоже кладем его в коллекцию
                commonWords.put(key, count);
            }                                                                                                           //Если слово встречалось реже то ничего не делаем
        }
    }
}
