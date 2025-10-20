package model;

public class Goods {
    private Integer id;
    private Integer memberId;
    private String categoryName;
    private String productName;
    private int unitPrice;
    private int quantity;
    private int lineTotal;
    private int orderTotal;
    private int paidCash;
    private int changeAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(int lineTotal) {
		this.lineTotal = lineTotal;
	}
	public int getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	public int getPaidCash() {
		return paidCash;
	}
	public void setPaidCash(int paidCash) {
		this.paidCash = paidCash;
	}
	public int getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}
    
    


}
