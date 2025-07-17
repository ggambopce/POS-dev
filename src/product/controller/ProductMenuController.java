package product.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import global.util.MessageBox;
import main.controller.Controller;
import product.entity.Product;
import product.service.ProductService;
import product.service.ProductServiceImplement;
import product.view.ProductUI;
import purchase.controller.PurchaseController;
import stock.controller.StockController;

import java.util.List;

public class ProductMenuController implements Controller {

    private final InputProvider input;
    private final OutputRenderer output;
    private ProductUI view;

    public ProductMenuController(InputProvider input, OutputRenderer output) {
        this.input = input;
        this.output = output;
        this.view = null;
    }

    @Override
    public void run() {
        ProductService productService = new ProductServiceImplement();
        Controller purchaseController = new PurchaseController();
        Controller stockController = new StockController();
        view = new ProductUI(output);
        while (true) {
            view.displayHeader();
            view.displayMenu();
            view.displayLast();

            String choice = input.readLine();
            switch (choice) {
                // 구매
                case "1" :
                    purchaseController.run();
                    break;
                // 입고등록
                case "2" :
                    stockController.run();
                    break;
                // 전체 제품 목록 보기
                case "3" :
                    view.showProductList();
                    List<Product> products = productService.findAllProducts();
                    view.showProductList(products);
                    break;
                // 제품명으로 검색
                case "4" :
                    productService.findByProductName();
                    break;
                // 신제품 등록
                case "5" :
                    view.promptInputProductName();
                    String name = input.readLine();
                    view.promptInputManufacturer();
                    String manufacturer = input.readLine();
                    view.promptInputGender();
                    char gender = input.readLine().toUpperCase().charAt(0);

                    if (gender != 'Y' && gender != 'N') {
                        output.println("Y 또는 N으로 입력해야 합니다.");
                    } else {
                        output.println("다시 입력해주세요");
                        return;
                    }
                    view.promptInputPrice();
                    int price = Integer.parseInt(input.readLine());
                    Product product = new Product(name, manufacturer, gender, price);
                    productService.registerProduct(product);
                    break;
                // 종료
                case "0" :
                    view.showProductClose();

                    return;
                default:
                    MessageBox.showWarningByWrongInput(input, output);
            }
        }
    }
}
