package int204.exam.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderDto {
    private Integer id;
    private String orderDate;
    private String status;
    private String comments;
    private Integer cusNo;


    public String getThank(){
        return "Khob khun ti aud noon " + id;
    }
    public Integer getSum(){
        return (cusNo + id);
    }
}
