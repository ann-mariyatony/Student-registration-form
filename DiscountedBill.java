package com.canteen.model;

/**
 * Demonstrates Inheritance (extends Bill) and Polymorphism (overrides getTotal).
 */
public class DiscountedBill extends Bill {
    private double discountRate; 

    public DiscountedBill(Order order, double discountRate) {
        super(order); 
        this.discountRate = discountRate;
    }

    @Override
    public double getTotal() {
        double originalTotal = super.getTotal();
        return originalTotal * (1.0 - discountRate);
    }

    /**
     * Overrides the bill generation to include the discount details.
     * It uses the parent's generated text and inserts the discount line.
     * @return A formatted bill string including the discount.
     */
    @Override
    public String generateBillText() {
        // 1. Get the full bill text from the parent class (Subtotal, Tax, and original FINAL TOTAL)
        String standardBill = super.generateBillText(); 
        
        double originalTotal = super.getTotal(); 
        double discountAmount = originalTotal * discountRate;
        
        // 2. Locate the line to be replaced/inserted before (FINAL TOTAL)
        int finalTotalIndex = standardBill.lastIndexOf("FINAL TOTAL:");

        // 3. Build the new line for the discount
        String discountLine = "Discount (" + (int)(discountRate * 100) + "%): -" + discountAmount + "\n";
        
        // 4. Build the final discounted bill string by combining parts:
        //    a) Everything before FINAL TOTAL
        String part1 = standardBill.substring(0, finalTotalIndex);
        //    b) The NEW discount line
        String part2 = discountLine;
        //    c) The CORRECTED FINAL TOTAL line (calling the overridden getTotal())
        String part3 = "FINAL TOTAL:     " + getTotal() + "\n";
        String part4 = "================================";

        return part1 + part2 + part3 + part4;
    }
}