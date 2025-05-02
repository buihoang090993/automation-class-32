package javaTest;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_DataType {
    // Kiểu dữ liệu nguyên thủy (Primitive Type)
    // Ký tự
//    char
    char c = 'K'; // 1 ký tự, lưu trong dấu nháy đơn

    // Số nguyên
//    byte
//    byte: -128 đến 127
    byte bNumber = 126;
//    short
//    short: -2^15 đến 2^15 - 1
    short sNumber = 32767;
//    int
//    int: -2^31 đến 2^31 - 1
    int iNumber = 2147483647;
//    long
//    long: -2^63 đến 2^63 - 1
    long lNumber = 312345699;

    // Số thực: điểm số, lương
//    float
    float fNumber = 9.57f;
//    double
    double dNumber = 9.5784;

    // Logic: Giới tính, đúng/sai
//    boolean
    boolean answer = true;

    // Kiểu dữ liệu tham chiếu (Reference Type)
    // Chuỗi
//    String
    String fullName = "Hoàng"; // Lưu trong dấu nháy kép

    // Array
    String[] students = {"Nguyễn Văn Nam", "Lê Việt Dương", "Đinh Phương Văn"};

    // List/Set/Queue
    static List<String> studentAddress = new ArrayList<String>();

    // Class/Object
    FirefoxDriver firefoxDriver = new FirefoxDriver();

    public static void main(String[] args) {
        studentAddress.add("Nguyễn Văn Đại");
    }
}
