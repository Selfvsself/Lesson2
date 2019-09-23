import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        File file;
        String pathFile;
        MyReader reader;

        System.out.println();

        while (true) {                                                                                                  //Запускаем бесконечный цикл который в консоли требует ввода файла
            System.out.println("Введите путь: ");
            pathFile = scanner.nextLine();
            System.out.println();

            file = new File(pathFile);
            if (file.exists() && file.isFile()) {                                                                       //Проверяем существует ли этот файл и является ли он файлом, а не директорией
                reader = new MyReader(file);
                reader.printOnScreen();
                break;
            } else {
                System.out.println("Такого файла не существует " + file);
                System.out.println("Введите другой файл");
                System.out.println();
            }
        }
    }
}
