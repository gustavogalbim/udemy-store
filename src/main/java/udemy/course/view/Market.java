package udemy.course.view;

import udemy.course.helper.Utils;
import udemy.course.model.Store;

import java.util.*;

public class Market {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Store> products;
    private static Map<Store, Integer> shopping;

    public static void main(String[] args) {
        products = new ArrayList<>();
        shopping = new HashMap<>();
        Market.menu();
    }

    private static void menu() {
        System.out.println("Bem-vindo(a)");
        System.out.println("Selecione uma opção: ");
        System.out.println("[1] Cadastrar produto");
        System.out.println("[2] Listar produtos");
        System.out.println("[3] Comprar produto");
        System.out.println("[4] Visualizar carrinho");
        System.out.println("[5] Sair");

        int options = 0;
        try {
            options = Integer.parseInt(Market.scanner.nextLine());
        } catch (InputMismatchException e) {
            Market.menu();
        } catch (NumberFormatException f) {
            Market.menu();
        }

        switch (options) {
            case 1:
                Market.registerProduct();
                break;
            case 2:
                Market.listProducts();
                break;
            case 3:
                Market.buyProducts();
                break;
            case 4:
                Market.shoppingCar();
                break;
            case 5:
                System.out.println("Volte sempre!");
                Utils.pause(2);
                System.exit(0);
            default:
                System.out.println("Opção invalida.");
                Utils.pause(2);
                Market.menu();
                break;
        }
    }

    private static void registerProduct() {
        System.out.println("Cadastro de produto");
        System.out.println("Informe o nome do produto: ");
        String name = Market.scanner.nextLine();
        System.out.println("Informe o preço do produto: ");
        Double price = Market.scanner.nextDouble();

        Store products = new Store(name, price);
        Market.products.add(products);

        System.out.println("O produto " +products.getName()+ " foi cadastrado com sucesso.");
        Utils.pause(1);
        Market.menu();
    }

    private static void listProducts() {
        if(Market.products.size() > 0) {
            System.out.println("Listando produtos..");
            for (Store p : Market.products) {
                System.out.println("\n"+ p);
            }
        } else {
            System.out.println("Ainda não existem produtos cadastrados");
        }
        Utils.pause(2);
        Market.menu();
    }

    private static void buyProducts() {
        if (Market.shopping.size() > 0) {
            System.out.println("Informe o codigo do produto que deseja adicionar ao carrinho: " + "\n");
            System.out.println("Produtos disponiveis: ");
            for(Store p : Market.products) {
                System.out.println(p);
            }
            int code = Integer.parseInt(Market.scanner.nextLine());
            boolean available = false;
            for (Store p: Market.products) {
                if(p.getCode() == code) {
                    int amount = 0;
                    try {
                        amount = Market.shopping.get(p);
                        Market.shopping.put(p, amount + 1);
                    } catch (NullPointerException e) {
                        Market.shopping.put(p, 1);
                    }
                    System.out.println("O produto " +p.getName()+ " foi adicionado ao carrinho.");
                    available = true;
                }
                if (available) {
                    System.out.println("Deseja adicionar outros produtos?");
                    System.out.println("Informe [1 - sim] ou [2 - não]");
                    int operation = Integer.parseInt(Market.scanner.nextLine());
                    if (operation == 1 ) {
                        Market.buyProducts();
                    } else {
                        System.out.println("Por favor, aguarde!");
                        Utils.pause(2);
                        Market.closeShopping();
                    }
                } else {
                    System.out.println("Não foi encontrado o produto com o código " +code);
                    Utils.pause(2);
                    Market.menu();
                }
            }
        } else {
            System.out.println("Não existe produto cadastrado no mercado.");
            Utils.pause(2);
            Market.menu();
        }
    }

    private static void shoppingCar() {
        if(Market.shopping.size() > 0) {
            System.out.println("Produtos no carrinho: ");
            for(Store p : Market.shopping.keySet()) {
                System.out.println("Produto: " +p+ "\nQuantidade: " +Market.shopping.get(p));
            }
        } else {
            System.out.println("Não existe produtos no carrinho.");
        }
        Utils.pause(2);
        Market.menu();
    }

    private static void closeShopping() {
        Double totalAmount = 0.0;
        System.out.println("Produtos do carrinho \n");
        for (Store p : Market.shopping.keySet()) {
            int amount = Market.shopping.get(p);
            totalAmount += p.getValue() * amount;
            System.out.println(p);
            System.out.println("Quantidade: " +amount+ "\n");
        }
        System.out.println("Sua fatura é " +Utils.doubleToString(totalAmount));
        Market.shopping.clear();
        System.out.println("Obrigado! Volte sempre.");
        Utils.pause(2);
        Market.menu();
    }

}
