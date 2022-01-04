import {ResponseDto} from "./response-dto";
import {UserResponseDto} from "./user-response-dto";

export interface AccountResponseDto extends ResponseDto {

  name: string;
  balance: number;
  user: UserResponseDto;
}
