package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
@Parameter(names = "-c", description = "Group count")
  public int count;
@Parameter(names = "-f", description = "Target file")
public String file;
@Parameter(names = "-d", description = "Data format")
public String format;


  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<GroupDate> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    }
    else if(format.equals("xml")) {
      saveAsXml(groups, new File(file));
    }
    else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private void saveAsXml(List<GroupDate> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupDate.class);
    String xml = xstream.toXML(groups); // в качестве параметра передаем тот объект, который нужно серилизовать, превратить из объектного представления в строчку формата xml
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }


  private  void saveAsCsv(List<GroupDate> groups, File file) throws IOException {
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
