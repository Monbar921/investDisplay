package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.entity.BankAccount;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T22:27:58+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class BankAccountCreateMapperImpl implements BankAccountCreateMapper {

    @Override
    public BankAccount map(BankAccountCreateDto source) {
        if ( source == null ) {
            return null;
        }

        BankAccount.BankAccountBuilder<?, ?> bankAccount = BankAccount.builder();

        bankAccount.name( source.name() );
        bankAccount.price( source.price() );
        bankAccount.quantity( source.quantity() );
        bankAccount.platform( source.platform() );
        bankAccount.interest( source.interest() );
        bankAccount.startDate( source.startDate() );
        bankAccount.endDate( source.endDate() );

        return bankAccount.build();
    }
}
