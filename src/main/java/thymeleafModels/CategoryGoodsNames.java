package thymeleafModels;

public class CategoryGoodsNames {
    private String categoryName;
    private String subCategoryName;
    private String itemsName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }
}
