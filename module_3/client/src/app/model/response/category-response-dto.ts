import {ResponseDto} from "./response-dto";

export interface CategoryResponseDto extends ResponseDto {

  name: string;
  price: number;
  income: boolean;
}
