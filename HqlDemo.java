package com.curdop;

import com.curdop.dao.ProductDAO;
import com.curdop.dao.ProductHQLDAO;
import com.curdop.entity.Product;

import java.util.List;
import java.util.Scanner;

public class HqlDemo {

    private final ProductDAO dao = new ProductDAO();
    private final ProductHQLDAO hql = new ProductHQLDAO();

    public void insertAdditionalProducts() {
        dao.addProduct(new Product("Monitor", "Electronics", 12000, 15));
        dao.addProduct(new Product("Headphones", "Electronics", 1500, 30));
        dao.addProduct(new Product("Chair", "Furniture", 3500, 8));
        dao.addProduct(new Product("Table", "Furniture", 5000, 5));
        dao.addProduct(new Product("Pen Drive", "Accessories", 600, 40));
        dao.addProduct(new Product("Notebook", "Stationery", 120, 100));
        dao.addProduct(new Product("Printer", "Electronics", 18000, 2));
        System.out.println("Inserted 7 additional products ✅");
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== HQL Operations (Skill-3) ===");
            System.out.println("1. Insert 5-8 additional products");
            System.out.println("2. Sort by price ASC");
            System.out.println("3. Sort by price DESC");
            System.out.println("4. Sort by quantity DESC (highest first)");
            System.out.println("5. Pagination: First 3 products");
            System.out.println("6. Pagination: Next 3 products");
            System.out.println("7. Aggregates: count total");
            System.out.println("8. Aggregates: count where quantity > 0");
            System.out.println("9. Grouped count by description");
            System.out.println("10. Min & Max price");
            System.out.println("11. Filter by price range (BETWEEN)");
            System.out.println("12. LIKE: name starts with");
            System.out.println("13. LIKE: name ends with");
            System.out.println("14. LIKE: name contains");
            System.out.println("15. Name exact length");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            int ch;
            try { ch = Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Invalid input"); continue; }

            switch (ch) {
                case 1 -> insertAdditionalProducts();
                case 2 -> printList(hql.getProductsSortedByPriceAsc());
                case 3 -> printList(hql.getProductsSortedByPriceDesc());
                case 4 -> printList(hql.getProductsSortedByQuantityDesc());
                case 5 -> printList(hql.getProductsPage(0, 3));
                case 6 -> printList(hql.getProductsPage(3, 3));
                case 7 -> System.out.println("Total products = " + hql.countTotalProducts());
                case 8 -> System.out.println("Products with quantity > 0 = " + hql.countInStockProducts());
                case 9 -> {
                    System.out.println("\nDescription -> Count");
                    for (Object[] row : hql.countGroupedByDescription()) {
                        System.out.println(row[0] + " -> " + row[1]);
                    }
                }
                case 10 -> {
                    Object[] mm = hql.minMaxPrice();
                    System.out.println("Min price = " + mm[0]);
                    System.out.println("Max price = " + mm[1]);
                }
                case 11 -> {
                    System.out.print("Min price: ");
                    double min = Double.parseDouble(sc.nextLine());
                    System.out.print("Max price: ");
                    double max = Double.parseDouble(sc.nextLine());
                    printList(hql.getProductsWithinPriceRange(min, max));
                }
                case 12 -> {
                    System.out.print("Prefix (e.g., M): ");
                    String p = sc.nextLine();
                    printList(hql.nameStartsWith(p));
                }
                case 13 -> {
                    System.out.print("Suffix (e.g., r): ");
                    String s = sc.nextLine();
                    printList(hql.nameEndsWith(s));
                }
                case 14 -> {
                    System.out.print("Contains (e.g., on): ");
                    String sub = sc.nextLine();
                    printList(hql.nameContains(sub));
                }
                case 15 -> {
                    System.out.print("Exact length (e.g., 5): ");
                    int len = Integer.parseInt(sc.nextLine());
                    printList(hql.nameWithExactLength(len));
                }
                case 0 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void printList(List<Product> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        list.forEach(System.out::println);
    }
}
