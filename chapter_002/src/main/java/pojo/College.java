package pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setSurname("Suvorov");
        student.setName("Evgeniy");
        student.setPatronymic("Vladimirovich");
        student.setBeginDate(new Date());

        System.out.println(student.getSurname() + " " + student.getName() + " " + student.getPatronymic() + " start at " + student.getBeginDate());
    }
}
