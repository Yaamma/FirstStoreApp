package application.DTO;

import application.entities.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
    public static TokenDto from(Token token){
        return TokenDto.builder().token(token.getToken()).build();
    }
}
