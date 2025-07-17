package product.controller;

import global.io.InputProvider;
import global.io.OutputRenderer;
import main.controller.Controller;
import product.entity.Product;
import product.service.ProductService;
import product.service.ProductServiceImplement;
import product.view.ProductUI;

import java.util.List;

/** 제품등록
 *  새로운 제품을 등록합니다.
 *  입고등록과 다름
 */
public class ProductRegisterController implements Controller {

    private final InputProvider input;
    private final OutputRenderer output;
    private final ProductUI view;
    ProductService productService;



    public ProductRegisterController(InputProvider input, OutputRenderer output) {

        this.input = input;
        this.output = output;
        this.view = new ProductUI(output);
        productService = new ProductServiceImplement();
    }

    @Override
    public void run() {
        view.displayProductRegisterHeader();

        view.promptInputProductName();
        String productName = input.readLine();

        view.promptInputManufacturer();
        String manufacturer = input.readLine();

        view.promptInputGender();
        char adultOnly = input.readLine().toUpperCase().charAt(0);
        if (adultOnly != 'Y' && adultOnly != 'N') {
            System.out.println("Y 또는 N으로 입력해야 합니다.");
            return;
        }

        view.promptInputPrice();
        int price = Integer.parseInt(input.readLine());

        Product product = new Product(0, productName, manufacturer,adultOnly, price, 0);
        productService.registerProduct(product);

        view.showRegisterSuccess();
    }


    public void showAllProducts() {

    }


    private void printProduct(Product p) {
        String stars = "*".repeat(p.getStock());
        System.out.printf("""
        -------------------------------
        제품ID: %d
        제품명: %s
        제조사: %s
        성인전용: %s
        가격: %,d원
        재고: %s (%d개)
        -------------------------------
        """,
                p.getProductId(),
                p.getProductName(),
                p.getManufacturer(),
                p.getAdultOnly() == 'Y' ? "19세 이상" : "모두 가능",
                p.getPrice(),
                stars,
                p.getStock());
    }


}