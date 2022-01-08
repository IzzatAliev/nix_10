export interface TransactionRequestDto {

  accountId: number;
  categoryName: string;
  amount: number;
}

export enum Amount {

  // @ts-ignore
  1=1,2,3,4,5
}
