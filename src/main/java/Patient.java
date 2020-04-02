import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class Patient {
    private String name;
    private String surname;
    private BigInteger pesel;
    private double wallet;

    public void wyswietlPodstawoweDane()
    {
        System.out.println(pesel +" " + name + " " + surname);
    }

    public void wypiszDane()
    {
        System.out.println("Stan portfela : " +  wallet);
    }
}
