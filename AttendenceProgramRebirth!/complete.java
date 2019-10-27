import java.io.*;
import java.util.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class complete
{
  private static ArrayList<Student> data;
  private static ArrayList<String> dates;
  
  public static void main(String[] paramArrayOfString)
  {
    data = getStu("data.xlsx");
    ArrayList localArrayList1 = getList();
    
    Scanner localScanner = new Scanner(System.in);
    
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2;
    Object localObject4;
    Object localObject6;
    try
    {
      dates = new ArrayList();
      FileInputStream localFileInputStream = new FileInputStream(new File("list.xlsx"));
      localObject1 = new XSSFWorkbook(localFileInputStream);
      XSSFSheet localXSSFSheet = ((XSSFWorkbook)localObject1).getSheetAt(0);
      
      Iterator localIterator = localXSSFSheet.iterator();
      localObject2 = (Row)localIterator.next();
      localObject4 = ((Row)localObject2).cellIterator();
      ((Iterator)localObject4).next();
      ((Iterator)localObject4).next();
      ((Iterator)localObject4).next();
      while (localIterator.hasNext())
      {
        localObject6 = (Cell)((Iterator)localObject4).next();
        dates.add(((Cell)localObject6).getStringCellValue());
      }
    }
    catch (Exception localException1) {}
    System.out.println("Start scanning now!");
    ArrayList localArrayList3 = new ArrayList();
    for (;;)
    {
      localObject1 = localScanner.nextLine();
      
      int i = 1;
      try
      {
        i = Integer.parseInt((String)localObject1);
      }
      catch (Exception localException2)
      {
        System.out.println("Use Numbers plz");
      }
      continue;
      if (i == 0) {
        break;
      }
      if (i != 1) {
        if (Integer.toString(i).length() != 6)
        {
          System.out.println("An ID has 6 digits! Try again.");
        }
        else
        {
          Student localStudent1 = findName(i);
          if (localStudent1 == null)
          {
            System.out.println("Not a valid ID");
          }
          else
          {
            localObject2 = localArrayList2.iterator();
            for (;;)
            {
              if (!((Iterator)localObject2).hasNext()) {
                break label339;
              }
              localObject4 = (Student)((Iterator)localObject2).next();
              if (((Student)localObject4).ID() == i)
              {
                System.out.println("This ID was already scanned.");
                break;
              }
            }
            label339:
            localArrayList2.add(localStudent1);
            localArrayList3.add(Integer.valueOf(i));
            try
            {
              localObject2 = new FileWriter("backup.txt");
              for (int k = 0; k < localArrayList3.size(); k++)
              {
                ((FileWriter)localObject2).write(Integer.toString(((Integer)localArrayList3.get(k)).intValue()));
                ((FileWriter)localObject2).write(" \n");
              }
              ((FileWriter)localObject2).close();
            }
            catch (Exception localException3) {}
          }
        }
      }
    }
    Object localObject1 = attendence();
    
    ArrayList localArrayList4 = new ArrayList();
    label527:
    for (int j = 0; j < localArrayList2.size(); j++)
    {
      for (localObject3 = localArrayList1.iterator(); ((Iterator)localObject3).hasNext();)
      {
        localObject5 = (Student)((Iterator)localObject3).next();
        if (((Student)localArrayList2.get(j)).ID() == ((Student)localObject5).ID()) {
          break label527;
        }
      }
      localArrayList4.add(localArrayList2.get(j));
      localArrayList2.remove(j);
    }
    j = 0;
    for (Object localObject3 = localArrayList1.iterator(); ((Iterator)localObject3).hasNext();)
    {
      localObject5 = (Student)((Iterator)localObject3).next();
      
      localObject6 = localArrayList2.iterator();
      for (;;)
      {
        if (!((Iterator)localObject6).hasNext()) {
          break label632;
        }
        Student localStudent2 = (Student)((Iterator)localObject6).next();
        if (((Student)localObject5).ID() == localStudent2.ID())
        {
          ((ArrayList)((ArrayList)localObject1).get(j)).add(Integer.valueOf(1));
          j++;
          break;
        }
      }
      ((ArrayList)((ArrayList)localObject1).get(j)).add(Integer.valueOf(0));
      j++;
    }
    label632:
    for (localObject3 = localArrayList4.iterator(); ((Iterator)localObject3).hasNext();)
    {
      localObject5 = (Student)((Iterator)localObject3).next();
      
      localObject6 = new ArrayList();
      for (int n = 0; n < ((ArrayList)((ArrayList)localObject1).get(n)).size() - 1; n++) {
        ((ArrayList)localObject6).add(Integer.valueOf(0));
      }
      ((ArrayList)localObject6).add(Integer.valueOf(1));
      ((ArrayList)localObject1).add(localObject6);
    }
    System.out.println("Scanning finished.");
    System.out.println("Creating dump file...");
    
    localObject3 = new XSSFWorkbook();
    Object localObject5 = ((XSSFWorkbook)localObject3).createSheet("Scanned ID's");
    int m = 0;
    
    XSSFFont localXSSFFont = ((XSSFWorkbook)localObject3).createFont();
    localXSSFFont.setBoldweight((short)700);
    XSSFCellStyle localXSSFCellStyle = ((XSSFWorkbook)localObject3).createCellStyle();
    localXSSFCellStyle.setFont(localXSSFFont);
    
    XSSFRow localXSSFRow = ((XSSFSheet)localObject5).createRow(m);
    m++;
    Cell localCell = localXSSFRow.createCell(0);
    localCell.setCellValue("Last Name");
    localCell.setCellStyle(localXSSFCellStyle);
    localCell = localXSSFRow.createCell(1);
    localCell.setCellValue("First Name");
    localCell.setCellStyle(localXSSFCellStyle);
    localCell = localXSSFRow.createCell(2);
    localCell.setCellValue("ID Number");
    localCell.setCellStyle(localXSSFCellStyle);
    for (int i1 = 0; i1 < dates.size(); i1++)
    {
      localCell = localXSSFRow.createCell(3 + i1);
      localCell.setCellValue((String)dates.get(i1));
      localCell.setCellStyle(localXSSFCellStyle);
    }
    localCell = localXSSFRow.createCell(2 + ((ArrayList)((ArrayList)localObject1).get(0)).size());
    Calendar localCalendar = Calendar.getInstance();
    
    String str = localCalendar.getDisplayName(2, 2, Locale.ENGLISH) + "_" + String.valueOf(localCalendar.get(5)) + "_" + String.valueOf(localCalendar.get(1));
    localCell.setCellValue(str);
    localCell.setCellStyle(localXSSFCellStyle);
    
    j = 0;
    m = 1;
    for (int i2 = 0; i2 < ((ArrayList)localObject1).size(); i2++)
    {
      localXSSFRow = ((XSSFSheet)localObject5).createRow(m++);
      try
      {
        Student localStudent3 = (Student)localArrayList1.get(i2);
        localCell = localXSSFRow.createCell(0);
        localCell.setCellValue(localStudent3.Namel());
        
        localCell = localXSSFRow.createCell(1);
        localCell.setCellValue(localStudent3.Namef());
        localCell = localXSSFRow.createCell(2);
        localCell.setCellValue(localStudent3.ID());
      }
      catch (Exception localException5)
      {
        Student localStudent4 = (Student)localArrayList4.get(i2 - localArrayList1.size());
        localCell = localXSSFRow.createCell(0);
        localCell.setCellValue(localStudent4.Namel());
        localCell = localXSSFRow.createCell(1);
        localCell.setCellValue(localStudent4.Namef());
        localCell = localXSSFRow.createCell(2);
        localCell.setCellValue(localStudent4.ID());
      }
      for (int i3 = 3; i3 < ((ArrayList)((ArrayList)localObject1).get(j)).size() + 3; i3++)
      {
        localCell = localXSSFRow.createCell(i3);
        localCell.setCellValue(((Integer)((ArrayList)((ArrayList)localObject1).get(j)).get(i3 - 3)).intValue());
      }
      j++;
    }
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(new File("list.xlsx"));
      ((XSSFWorkbook)localObject3).write(localFileOutputStream);
      localFileOutputStream.close();
      System.out.println("Dump file created successfully");
    }
    catch (Exception localException4)
    {
      localException4.printStackTrace();
    }
  }
  
  private static ArrayList<ArrayList<Integer>> attendence()
  {
    System.out.println("loading....");
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(new File("list.xlsx"));
      XSSFWorkbook localXSSFWorkbook = new XSSFWorkbook(localFileInputStream);
      XSSFSheet localXSSFSheet = localXSSFWorkbook.getSheetAt(0);
      
      Iterator localIterator1 = localXSSFSheet.iterator();
      localIterator1.next();
      while (localIterator1.hasNext())
      {
        ArrayList localArrayList2 = new ArrayList();
        Row localRow = (Row)localIterator1.next();
        int i = 3;
        Iterator localIterator2 = localRow.cellIterator();
        Integer localInteger = Integer.valueOf(0);
        localIterator2.next();
        localIterator2.next();
        localIterator2.next();
        while (localIterator2.hasNext())
        {
          Cell localCell = (Cell)localIterator2.next();
          localCell.setCellType(1);
          localInteger = Integer.valueOf(Integer.parseInt(localCell.getStringCellValue().substring(0, 1)));
          localArrayList2.add(localInteger);
          i++;
        }
        localArrayList1.add(localArrayList2);
      }
      localFileInputStream.close();
      
      System.out.println("...done");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList1;
  }
  
  private static ArrayList<Student> getStu(String paramString)
  {
    System.out.println("Loading database...");
    ArrayList localArrayList = new ArrayList();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(new File(paramString));
      XSSFWorkbook localXSSFWorkbook = new XSSFWorkbook(localFileInputStream);
      XSSFSheet localXSSFSheet = localXSSFWorkbook.getSheetAt(0);
      
      Iterator localIterator1 = localXSSFSheet.iterator();
      localIterator1.next();
      while (localIterator1.hasNext())
      {
        Row localRow = (Row)localIterator1.next();
        int i = 0;
        Iterator localIterator2 = localRow.cellIterator();
        String str1 = "";String str2 = "";
        int j = 0;
        while ((i <= 2) && (localIterator2.hasNext()))
        {
          Cell localCell = (Cell)localIterator2.next();
          if (i == 0)
          {
            localCell.setCellType(1);
            j = Integer.parseInt(localCell.getStringCellValue());
          }
          else if (i == 1)
          {
            str2 = localCell.getStringCellValue();
          }
          else if (i == 2)
          {
            str1 = localCell.getStringCellValue();
          }
          i++;
        }
        localArrayList.add(new Student(str2, str1, j));
      }
      localFileInputStream.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  private static ArrayList<Student> getList()
  {
    System.out.println("Loading database...");
    ArrayList localArrayList = new ArrayList();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(new File("list.xlsx"));
      XSSFWorkbook localXSSFWorkbook = new XSSFWorkbook(localFileInputStream);
      XSSFSheet localXSSFSheet = localXSSFWorkbook.getSheetAt(0);
      
      Iterator localIterator1 = localXSSFSheet.iterator();
      localIterator1.next();
      while (localIterator1.hasNext())
      {
        Row localRow = (Row)localIterator1.next();
        int i = 0;
        Iterator localIterator2 = localRow.cellIterator();
        String str1 = "";String str2 = "";
        int j = 0;
        while ((i <= 2) && (localIterator2.hasNext()))
        {
          Cell localCell = (Cell)localIterator2.next();
          if (i == 2)
          {
            localCell.setCellType(1);
            String str3 = localCell.getStringCellValue().substring(0, 6);
            
            j = Integer.valueOf(str3).intValue();
          }
          if (i == 0) {
            str2 = localCell.getStringCellValue();
          }
          if (i == 1) {
            str1 = localCell.getStringCellValue();
          }
          i++;
        }
        localArrayList.add(new Student(str2, str1, j));
      }
      localFileInputStream.close();
      
      System.out.println("...done");
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localArrayList;
  }
  
  public static Student findName(int paramInt)
  {
    for (Student localStudent : data) {
      if (localStudent.ID() == paramInt) {
        return localStudent;
      }
    }
    return null;
  }
}
