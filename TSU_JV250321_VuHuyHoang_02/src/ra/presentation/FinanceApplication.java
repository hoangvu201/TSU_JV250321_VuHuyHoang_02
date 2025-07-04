package ra.presentation;

import ra.entity.Expense;

import java.util.Date;
import java.util.Scanner;

public class FinanceApplication {
    public static Expense[] expenses = new Expense[1000];
    public static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("----------PERSONAL FINAL MANAGER MENU-------------");
            System.out.println("1.Hiển thị danh sách khoản chi tiêu");
            System.out.println("2.Thêm khoản chi mới");
            System.out.println("3.Sửa thông tin khoản chi");
            System.out.println("4.Xoá khoản chi");
            System.out.println("5.Tìm kiếm khoản chi");
            System.out.println("6.Thống kê tổng chi tiêu từng danh mục");
            System.out.println("7.Thống kê tổng chi tiêu theo từng phương thức thanh toán");
            System.out.println("8.Sắp xếp khoản chi theo ngày chi tiêu");
            System.out.println("9.Thoát chương trình");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    do {
                        if (currentIndex == 0) {
                            System.err.println("Mảng chưa có dữ liệu vui lòng nhập dữ liệu");
                        } else {
                            System.out.println("Danh sách khoản chi tiêu");
                            for (int i = 0; i < currentIndex; i++) {
                                expenses[i].displayData();
                                System.out.println();
                            }
                            break;
                        }

                    } while (true);
                    break;
                case 2:
                    createNewData(sc);
                    break;
                case 3:
                    fixDataById(sc);
                    break;
                case 4:
                    deleteFromListById(sc);
                    break;
                case 5:
                    searchById(sc);
                    break;
                case 6:
                    displayByCategory();
                    break;
                case 7:
                    displayDataByPaymentMethod();
                    break;
                case 8:
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 9");
            }
        } while (true);
    }

    private static void displayDataByPaymentMethod() {
        double sumPaymentMethod1 = 0;
        double sumPaymentMethod2 = 0;
        double sumPaymentMethod3 = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (expenses[i].getPaymentMethod() == 1) {
                sumPaymentMethod1 += expenses[i].getAmount();
            } else if (expenses[i].getPaymentMethod() == 2) {
                sumPaymentMethod2 += expenses[i].getAmount();
            } else {
                sumPaymentMethod3 += expenses[i].getAmount();
            }
        }
        System.out.printf("Tổng số tiền thanh toán bằng phương thức %s là: %f", Expense.findNamePaymentMethodByNumber(1), sumPaymentMethod1);
        System.out.printf("Tổng số tiền thanh toán bằng phương thức %s là: %f", Expense.findNamePaymentMethodByNumber(2), sumPaymentMethod2);
        System.out.printf("Tổng số tiền thanh toán bằng phương thức %s là: %f", Expense.findNamePaymentMethodByNumber(3), sumPaymentMethod3);
    }

    private static void displayByCategory() {
        int countCategoryByNumber1 = 0;
        int countCategoryByNumber2 = 0;
        int countCategoryByNumber3 = 0;
        int countCategoryByNumber4 = 0;
        for (int i = 0; i < currentIndex; i++) {
            if (expenses[i].getCategory() == 1) {
                countCategoryByNumber1++;
            } else if (expenses[i].getCategory() == 2) {
                countCategoryByNumber2++;
            } else if (expenses[i].getCategory() == 3) {
                countCategoryByNumber3++;
            } else {
                countCategoryByNumber4++;
            }
        }
        System.out.printf("Số lần chi tiêu danh mục %s là %d", Expense.findNameCategoryByNumber(1), countCategoryByNumber1);
        System.out.printf("Số lần chi tiêu danh mục %s là %d", Expense.findNameCategoryByNumber(2), countCategoryByNumber2);
        System.out.printf("Số lần chi tiêu danh mục %s là %d", Expense.findNameCategoryByNumber(3), countCategoryByNumber3);
        System.out.printf("Số lần chi tiêu danh mục %s là %d", Expense.findNameCategoryByNumber(4), countCategoryByNumber4);
    }

    private static void searchById(Scanner sc) {
        System.out.print("Nhập vào id khoản chi để tìm kiếm: ");
        int findId = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < currentIndex; i++) {
            if (expenses[i].getId() == findId) {
                expenses[i].displayData();
            }
        }
    }

    private static void deleteFromListById(Scanner sc) {
        System.out.print("Nhập vào id khoản chi muốn xoá: ");
        int deleteId = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < currentIndex; i++) {
            if (expenses[i].getId() == deleteId) {
                for (int j = 0; j < currentIndex - 1; j++) {
                    expenses[i] = expenses[i + 1];
                }
            }
            expenses[currentIndex - 1] = null;
            currentIndex--;
            break;
        }
    }

    private static void createNewData(Scanner sc) {
        System.out.println("Nhập dữ liệu cho khoản chi mới");
        Expense expense = new Expense();
        expense.inputData(sc);
        expenses[currentIndex] = expense;
        currentIndex++;
    }

    private static void fixDataById(Scanner sc) {
        System.out.print("Nhập id khoản chi cần sửa: ");
        int fixId = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < currentIndex; i++) {
            if (expenses[i].getId() == fixId) {
                boolean isExit = false;
                do {
                    System.out.println("1.Sửa nội dung khoản chi");
                    System.out.println("2.Sửa số tiền khoản chi");
                    System.out.println("3.Sửa danh mục khoản chi");
                    System.out.println("4.Sửa phương thức thanh toán");
                    System.out.println("5.Sửa ngày thanh toán");
                    System.out.println("6.Thoát");
                    int choiceNumber = Integer.parseInt(sc.nextLine());
                    switch (choiceNumber) {
                        case 1:
                            System.out.println("Nhập vào nội dung mới: ");
                            expenses[i].setDescription(sc.nextLine());
                            break;
                        case 2:
                            System.out.println("Nhập vào số tiền mới:");
                            expenses[i].setAmount(Double.parseDouble(sc.nextLine()));
                            break;
                        case 3:
                            System.out.println("Nhập vào danh mục mới");
                            expenses[i].setCategory(Integer.parseInt(sc.nextLine()));
                            break;
                        case 4:
                            System.out.println("Nhập vào phương thức thanh toán");
                            expenses[i].setPaymentMethod(Integer.parseInt(sc.nextLine()));
                            break;
                        case 5:
                            expenses[i].setUpdatedAt(new Date());
                            break;
                        case 6:
                            isExit = true;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1 đến 5");
                    }
                } while (!isExit);
            }
        }
    }
}
