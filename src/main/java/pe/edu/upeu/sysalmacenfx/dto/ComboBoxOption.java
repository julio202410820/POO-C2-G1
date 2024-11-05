package pe.edu.upeu.sysalmacenfx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComboBoxOption {
    String key;
    String value;

    @Override
    public String toString() {
        return value;
    }

    String qwe;
    String val;

    public String touString()
    {
        return val;
    }

}

