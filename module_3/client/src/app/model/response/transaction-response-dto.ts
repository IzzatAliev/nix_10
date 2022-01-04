import {ResponseDto} from "./response-dto";
import {AccountResponseDto} from "./account-response-dto";
import {CategoryResponseDto} from "./category-response-dto";

export interface TransactionResponseDto extends ResponseDto {

  account: AccountResponseDto;
  category: CategoryResponseDto;
  amount: number;
}
