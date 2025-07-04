package ra.entity;

import ra.presentation.FinanceApplication;
import ra.validator.Validation;

import java.util.Date;
import java.util.Scanner;

public class Expense implements IExpense {
    private int id;
    private String description;
    private double amount;
    private int category;
    private int paymentMethod;
    private Date createdAt;
    private Date updatedAt;

    public Expense() {
    }

    public Expense(int id, String description, double amount, int category, int paymentMethod, Date createdAt, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public void inputData(Scanner sc) {
        this.id = inputId();
        this.description = inputDescription(sc);
        this.amount = inputAmount(sc);
        this.category = inputCategory(sc);
        this.paymentMethod = inputPaymentMethod(sc);
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public int inputId() {
        if (FinanceApplication.currentIndex == 0) {
            return 1;
        }
        int indexMax = FinanceApplication.expenses[0].getId();
        for (int i = 1; i < FinanceApplication.currentIndex; i++) {
            if (FinanceApplication.expenses[i].getId() > indexMax) {
                indexMax = FinanceApplication.expenses[i].getId();
            }
        }
        return indexMax + 1;
    }

    public String inputDescription(Scanner sc) {
        do {
            System.out.println("Nhập nội dung khoản chi:");
            this.description = sc.nextLine();
            if (Validation.isEmpty(description)) {
                System.err.println("Nội dung không được để trống, vui lòng nhập lại");
            } else {
                if (description.length() < 100) {
                    return description;
                }
                System.err.println("Nội dung không được quá 100 kí tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public double inputAmount(Scanner sc) {
        do {
            System.out.print("Nhập vào số tiền đã chi: ");
            if (sc.hasNextDouble()) {
                double amount = Double.parseDouble(sc.nextLine());
                if (amount > 0) {
                    return amount;
                }
                System.err.println("Số tiền đã chi phải lớn hơn 0");
            }
            System.err.println("Số tiền đã chi phải là số hoặc không để trống, vui lòng nhập lại");
        } while (true);
    }

    public int inputCategory(Scanner sc) {
        do {
            if (sc.hasNextInt()) {
                System.out.println("Nhập vào danh mục khoản chi (từ 1 đến 4)");
                int numberOfCategory = Integer.parseInt(sc.nextLine());
                if (numberOfCategory < 1 || numberOfCategory > 4) {
                    System.err.println("Vui lòng nhập số trong khoảng từ 1 đến 4");
                }
                return numberOfCategory;

            }
            System.err.println("Danh mục khoản chi là số hoặc không được để trống, vui lòng nhp lại");
        } while (true);
    }

    public int inputPaymentMethod(Scanner sc) {
        do {
            if (sc.hasNextInt()) {
                System.out.println("Nhập vào phương thức thanh toán (từ 1 đến 3)");
                int inpaymentMethod = Integer.parseInt(sc.nextLine());
                if (inpaymentMethod < 1 || inpaymentMethod > 3) {
                    System.err.println("Vui lòng nhập số trong khoảng từ 1 đến 3");
                }
                return inpaymentMethod;

            }
            System.err.println("Phương thức thanh toán là số hoặc không được để trống, vui lòng nhp lại");
        } while (true);
    }

    public static String findNameCategoryByNumber(int categoryNumber){
        if (categoryNumber == 1) {
            return "Ăn uống";
        }
        if (categoryNumber == 2) {
            return "Đi lại";
        }
        if (categoryNumber == 3) {
           return  "Mua sắm";
        }
        if (categoryNumber == 4) {
            return "Khác";
        }
        return null;
    }

    public static String findNamePaymentMethodByNumber(int paymentMethodNumber){
        if (paymentMethodNumber == 1) {
            return "Tiền mặt";
        }
        if (paymentMethodNumber == 2) {
            return "Chuyển khoản";
        }
        if (paymentMethodNumber == 3) {
            return "Thẻ tín dụng";
        }
        return null;
    }

    @Override
    public void displayData() {
        System.out.printf("Id: %d\n " +
                        "Nội dung khoản chi: %s\n " +
                        "Số tiền chi: %.1f\n " +
                        "Danh mục khoản chi: %s\n " +
                        "Phương thức thanh toán: %s\n " +
                        "Ngày tạo: " + this.createdAt +
                        "Ngày cập nhật gần nhất: " + this.updatedAt,
                this.id, this.description,
                this.amount, findNameCategoryByNumber(this.category),findNamePaymentMethodByNumber(this.paymentMethod));
    }
}
