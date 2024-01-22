import lombok.Data;

import java.time.LocalDate;
@Data
public class Customer {
    private String FIO;
    private LocalDate dateOfBirth;
    private String phone;

    public Customer(String FIO, LocalDate dateOfBirth, String phone) {
        this.FIO = FIO;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
    }
}
