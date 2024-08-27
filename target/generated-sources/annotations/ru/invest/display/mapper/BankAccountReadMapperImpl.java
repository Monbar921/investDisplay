package ru.invest.display.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.entity.BankAccount;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T22:27:58+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class BankAccountReadMapperImpl implements BankAccountReadMapper {

    @Override
    public BankAccountReadDto map(BankAccount source) {
        if ( source == null ) {
            return null;
        }

        String name = null;
        double price = 0.0d;
        double quantity = 0.0d;
        String platform = null;
        double interest = 0.0d;
        LocalDate startDate = null;
        LocalDate endDate = null;

        name = source.getName();
        price = source.getPrice();
        quantity = source.getQuantity();
        platform = source.getPlatform();
        interest = source.getInterest();
        startDate = source.getStartDate();
        endDate = source.getEndDate();

        BankAccountReadDto bankAccountReadDto = new BankAccountReadDto( name, price, quantity, platform, interest, startDate, endDate );

        return bankAccountReadDto;
    }
}
