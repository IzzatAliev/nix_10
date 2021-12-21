import {ResponseDto} from "./response-dto";

export interface TransactionResponseDto extends ResponseDto {

  account: string;
  category: string;
  amount: number;
}
