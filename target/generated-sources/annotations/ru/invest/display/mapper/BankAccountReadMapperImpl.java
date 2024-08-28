package ru.invest.display.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.dto.ProductReadDto;
import ru.invest.display.entity.BankAccount;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-28T22:25:20+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class BankAccountReadMapperImpl implements BankAccountReadMapper {

    @Override
    public BankAccountReadDto map(BankAccount source) {
        if ( source == null ) {
            return null;
        }

        double interest = 0.0d;
        LocalDate startDate = null;
        LocalDate endDate = null;

        interest = source.getInterest();
        startDate = source.getStartDate();
        endDate = source.getEndDate();

        ProductReadDto productReadDto = null;

        BankAccountReadDto bankAccountReadDto = new BankAccountReadDto( productReadDto, interest, startDate, endDate );

        return bankAccountReadDto;
    }
}
