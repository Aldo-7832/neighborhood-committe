package mx.edu.utez.neighborhoodcommitte.entity.dto;

import mx.edu.utez.neighborhoodcommitte.entity.Category;

public class RequestDto {
    
    private Category category;
    private String description;
    private String attachmentName;

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAttachmentName() {
        return attachmentName;
    }
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

}
