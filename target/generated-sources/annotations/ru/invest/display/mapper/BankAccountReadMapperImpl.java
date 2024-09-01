package ru.invest.display.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.invest.display.dto.BankAccountReadDto;
import ru.invest.display.dto.ProductReadDto;
import ru.invest.display.dto.UserReadDto;
import ru.invest.display.entity.BankAccount;
import ru.invest.display.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-31T17:24:40+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.12 (Ubuntu)"
)
@Component
public class BankAccountReadMapperImpl implements BankAccountReadMapper {

    @Override
    public BankAccountReadDto map(BankAccount source) {
        if ( source == null ) {
            return null;
        }

        BankAccountReadDto bankAccountReadDto = new BankAccountReadDto();

        if ( source.getUser() != null ) {
            if ( bankAccountReadDto.getProduct() == null ) {
                bankAccountReadDto.setProduct( new ProductReadDto() );
            }
            userToProductReadDto( source.getUser(), bankAccountReadDto.getProduct() );
        }
        if ( bankAccountReadDto.getProduct() == null ) {
            bankAccountReadDto.setProduct( new ProductReadDto() );
        }
        bankAccountToProductReadDto( source, bankAccountReadDto.getProduct() );
        bankAccountReadDto.setInterest( source.getInterest() );
        bankAccountReadDto.setEndDate( source.getEndDate() );
        bankAccountReadDto.setBank( source.getBank() );

        return bankAccountReadDto;
    }

    protected void userToUserReadDto(User user, UserReadDto mappingTarget) {
        if ( user == null ) {
            return;
        }

        mappingTarget.setUsername( user.getUsername() );
    }

    protected void userToProductReadDto(User user, ProductReadDto mappingTarget) {
        if ( user == null ) {
            return;
        }

        if ( mappingTarget.getUser() == null ) {
            mappingTarget.setUser( new UserReadDto() );
        }
        userToUserReadDto( user, mappingTarget.getUser() );
    }

    protected void bankAccountToProductReadDto(BankAccount bankAccount, ProductReadDto mappingTarget) {
        if ( bankAccount == null ) {
            return;
        }

        mappingTarget.setName( bankAccount.getName() );
        mappingTarget.setPrice( bankAccount.getPrice() );
        mappingTarget.setQuantity( bankAccount.getQuantity() );
        mappingTarget.setStartDate( bankAccount.getStartDate() );
    }
}
