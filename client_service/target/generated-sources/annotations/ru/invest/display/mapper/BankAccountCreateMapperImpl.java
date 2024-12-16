package ru.invest.display.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.BankAccountCreateDto;
import ru.invest.display.dto.ProductCreateDto;
import ru.invest.display.dto.UserCreateDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T17:24:40+0700",
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

        bankAccount.user( productCreateDtoToUser( source.product() ) );
        bankAccount.name( sourceProductName( source ) );
        bankAccount.price( sourceProductPrice( source ) );
        bankAccount.quantity( sourceProductQuantity( source ) );
        bankAccount.startDate( sourceProductStartDate( source ) );
        bankAccount.interest( source.interest() );
        bankAccount.endDate( source.endDate() );
        bankAccount.bank( source.bank() );

        return bankAccount.build();
    }

    private String productCreateDtoUserUsername(ProductCreateDto productCreateDto) {
        UserCreateDto user = productCreateDto.user();
        if ( user == null ) {
            return null;
        }
        return user.username();
    }

    protected User productCreateDtoToUser(ProductCreateDto productCreateDto) {
        if ( productCreateDto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( productCreateDtoUserUsername( productCreateDto ) );

        return user.build();
    }

    private String sourceProductName(BankAccountCreateDto bankAccountCreateDto) {
        ProductCreateDto product = bankAccountCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.name();
    }

    private double sourceProductPrice(BankAccountCreateDto bankAccountCreateDto) {
        ProductCreateDto product = bankAccountCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.price();
    }

    private double sourceProductQuantity(BankAccountCreateDto bankAccountCreateDto) {
        ProductCreateDto product = bankAccountCreateDto.product();
        if ( product == null ) {
            return 0.0d;
        }
        return product.quantity();
    }

    private LocalDate sourceProductStartDate(BankAccountCreateDto bankAccountCreateDto) {
        ProductCreateDto product = bankAccountCreateDto.product();
        if ( product == null ) {
            return null;
        }
        return product.startDate();
    }
}
