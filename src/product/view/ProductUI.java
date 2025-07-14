package product.view;

import global.io.InputProvider;
import global.io.OutputRenderer;
import main.view.View;
import product.controller.ProductController;
import purchase.controller.PurchaseController;
import stock.controller.StockController;

import java.util.Scanner;

public class ProductUI implements View {
    private final InputProvider input;
    private final OutputRenderer output;

    public ProductUI(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void displayHeader() {
        output.println("=====================================");
        output.println("        사원 POS 상품관리 메뉴]        ");
        output.println("-------------------------------------");
    }

    @Override
    public void displayMenu() {
        output.println("1. 구매");
        output.println("2. 입고 등록");
        output.println("3. 전체 제품 목록 보기");
        output.println("4. 제품명으로 검색");
        output.println("5. 신제품 등록");
        output.println("0. 종료");

    }

    @Override
    public void displayLast() {
        output.println("=====================================");
        output.print("메뉴를 선택하세요 > ");
    }

    // 신제품 등록
    public void promptInputProductName() {
        output.println("상품 이름을 입력하세요.");
        output.print("상품명 입력:  ");
    }
    public void promptInputManufacturer() {
        output.println("제조사를 입력하세요.");
        output.println("제조사 입력: ");
    }
    public void promptInputGender() {
        output.println("성인 전용 여부 이벽 (Y/N)");
    }

    public void promptInputPrice() {
        output.println("가격을 입력하세요.");
        output.println("가격 입력: ");
    }

    @Override
    public void displayFooter() {

    }




//    private final ProductController controller = new ProductController();
//    private final PurchaseController purchaseController = new PurchaseController();
//    private final StockController stockController = new StockController();
//
//    public void start() {
//        while (true) {
//            System.out.println("\n===== 제품 관리 메뉴 =====");
//            System.out.println("1. 구매");
//            System.out.println("2. 입고 등록");
//            System.out.println("3. 전체 제품 목록 보기");
//            System.out.println("4. 제품명으로 검색");
//            System.out.println("5. 신제품 등록");
//            System.out.println("0. 종료");
//            System.out.print("선택 > ");
//
//            int choice = sc.nextInt();
//            sc.nextLine();
//
//            switch (choice) {
//                case 1 -> purchaseController.purchaseProduct();
//                case 2 -> stockController.registerStock();
//                case 3 -> controller.showAllProducts();
//                case 4 -> controller.searchByProductName();
//                case 5 -> controller.registerProduct();
//                case 0 -> {
//                    System.out.println("프로그램을 종료합니다.");
//                    return;
//                }
//                default -> System.out.println("올바른 번호를 선택하세요.");
//            }
//        }
//    }
}
