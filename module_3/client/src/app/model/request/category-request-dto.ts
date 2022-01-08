export interface CategoryRequestDto {

  name: string;
  price: number;
  accountId: number;
  income: boolean;
}

export enum CategoryName {

  BUYING_CHOCOLATE = 'BUYING_CHOCOLATE',
  UTILITIES = 'UTILITIES',
  BUYING_TSHIRT = 'BUYING_TSHIRT',
  CHARITY = 'CHARITY',
  MOBILE_PHONE_RECHARGE = 'MOBILE_PHONE_RECHARGE',
  PASS_TO_FRIEND = 'PASS_TO_FRIEND',
  PAY_FOR_LIGHT = 'PAY_FOR_LIGHT'
}
