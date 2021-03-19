package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<GroupDate> groups = generateGroups(count);
    save(groups, file);
  }

  private static void save(List<GroupDate> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());// проверяем, относительный путь файла и оказывается директория проекта, потому что по разному тесты и программы
    // нужно список сохранить в файл
    Writer writer = new FileWriter(file); //нужно файл открыть на запись
    for (GroupDate group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(),group.getFooter() ));
    }
    writer.close(); // необходимо закрыть для явного сохранения данных

  }

  private static List<GroupDate> generateGroups(int count) {
    List<GroupDate> groups = new ArrayList<GroupDate>(); // создаём список типа GroupDate
    for (int i = 0; i < count; i++) {
      groups.add(new GroupDate().withName(String.format("test %s", i)).withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
    }
    return groups;
  }
}
