import {ResponseDto} from "./response-dto";

export interface AccountResponseDto extends ResponseDto {

  name: string;
  balance: number;
  user: string;
}
